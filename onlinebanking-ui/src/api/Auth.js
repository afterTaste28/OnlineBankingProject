import axios from "axios";

const API = axios.create({
    baseURL : "http://localhost:8080/api/auth",
    headers: {
        "Content-Type": "application/json"
      }
});

export const login = (creds) => {return API.post('/login', creds);}
export const register = (creds) => {return API.post('/register', creds);}

export const getHashedPassword = async (password)=>{
  const encoder = new TextEncoder();
  const data = encoder.encode(password);
  const hashBuffer = await crypto.subtle.digest('SHA-256', data);
  const hashArray = Array.from(new Uint8Array(hashBuffer));
  const hashHex = hashArray.map(b => b.toString(16).padStart(2, '0')).join('');
  return hashHex;
}
