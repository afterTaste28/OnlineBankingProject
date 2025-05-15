import axios from "axios";

const API = axios.create({
    baseURL : "http://localhost:8080/api/auth",
    headers: {
        "Content-Type": "application/json"
      }
});

export const login = (creds) => {return API.post('/login', creds);}
export const register = (creds) => {return API.post('/register', creds);}
