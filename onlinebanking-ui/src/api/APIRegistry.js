import axios from "axios";
import { useContext } from "react";
import { UserContext } from "../context/UserProvider";

const AuthAPI = axios.create({
    baseURL : "http://localhost:8080/api/auth",
    headers: {
        "Content-Type": "application/json"
      }
});
const PostLoginAPI = axios.create({
    baseURL : "http://localhost:8080/api",
    headers: {
        "Content-Type": "application/json"
      }
});


PostLoginAPI.interceptors.request.use(
  (config)=>{
    const token = localStorage.getItem("token");
    if(token)
      config.headers.Authorization = `Bearer ${token}`;
    return config;
  }
)
PostLoginAPI.interceptors.response.use(
  response => response,
  (error) => {
    if (error.response && (error.response.status === 401 || error.response.status === 403)) {
    
      localStorage.removeItem("token");
      const {isLoggedIn, setIsLoggedIn} = useContext(UserContext);
      setIsLoggedIn(false);
      
    }
    return Promise.reject(error);
  }
);
export const login = (creds) => {return AuthAPI.post('/login', creds);}
export const register = (creds) => {return AuthAPI.post('/register', creds);}
export const getBalance = () => {return PostLoginAPI.get('/account/balance');}
export const getUserInfo = () => {return PostLoginAPI.get('/auth/userInfo');}
export const getCardDetails = () => {return PostLoginAPI.get('/account/cardDetails');}
export const sendMoney = (sendMoneyDTO) => {return PostLoginAPI.post('/transact/send',sendMoneyDTO);}
export const getTransactions = (page,size) => {return PostLoginAPI.get(`/transact/transactions/${page}/${size}`);}
export const getRecentTransactions = (count) => {return PostLoginAPI.get(`/transact/transactions`,{
  params: { count },
});}

export const getHashedPassword = async (password)=>{
  const encoder = new TextEncoder();
  const data = encoder.encode(password);
  const hashBuffer = await crypto.subtle.digest('SHA-256', data);
  const hashArray = Array.from(new Uint8Array(hashBuffer));
  const hashHex = hashArray.map(b => b.toString(16).padStart(2, '0')).join('');
  return hashHex;
}
