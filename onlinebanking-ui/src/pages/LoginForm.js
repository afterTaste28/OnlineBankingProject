import React, {useState,useContext} from "react";
import { TextField, Button, Box, Typography, Container } from "@mui/material";
import { getHashedPassword,login } from "../api/APIRegistry";
import { useNavigate } from "react-router-dom";
import { UserContext } from "../context/UserProvider";


function LoginForm(){
    const navigate = useNavigate();
    const [form, setForm] = useState({emailId:"",password:""});
    const {setIsLoggedIn} = useContext(UserContext);
    const handleChange = (e)=>{
        setForm({...form, [e.target.name]:e.target.value})
    }
    
    const handleSubmit = async (e) => {
        e.preventDefault();

        try{
            form.password = await getHashedPassword(form.password);
            const resp = await login(form);
            localStorage.setItem("token", resp.data);
            alert("Login successful");
            setIsLoggedIn(true);
            navigate('/dashboard');
        } catch(err){
            alert("Login failed:"+err.response.data.errorString);
        }
    }
    return (
        <Container className="custom-container" maxWidth="sm">
            <Typography>Login Form</Typography>
            <form onSubmit={handleSubmit}>
                <TextField 
                    fullWidth   
                    label="Email-id"  
                    variant="outlined"  
                    margin="normal"  
                    type="email"
                    name="emailId"
                    value={form.emailId}
                    required
                    onChange={handleChange}
                    autoComplete="email"  />
                <TextField 
                    fullWidth  
                    name="password"  
                    label="Password"  
                    variant="outlined"  
                    margin="normal" 
                    type="password" 
                    value={form.password}
                    required
                    onChange={handleChange}
                    autoComplete="password" />
                <Button 
                    fullWidth 
                    type="submit"
                    variant="contained"  
                    color="primary">
                    Login
                </Button>
            </form>
        </Container> 
    );
}

export default LoginForm;
