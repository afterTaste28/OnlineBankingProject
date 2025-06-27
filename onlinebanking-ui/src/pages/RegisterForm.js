import React, {useContext, useState} from "react";
import { TextField, Button, Box, Typography, Container } from "@mui/material";
import { getHashedPassword, register } from "../api/APIRegistry";
import { useNavigate } from 'react-router-dom';


function RegisterForm(){
    const navigate = useNavigate();
    const [form, setForm] = useState({firstName:"",lastName:"",emailId:"",password:""});
    
    const handleChange = (e) => {
        setForm({...form, [e.target.name]:e.target.value})
    }
    
    const handleSubmit = async (e) => {
        e.preventDefault();
        try{
            form.password = await getHashedPassword(form.password);
            const resp = await register(form);
            alert("Register Successful");
            navigate('/login');
        } catch(e){
            alert("Registration failed:"+e.response.data.errorString);
        }
    }
    return (
        <Container className="custom-container" maxWidth="sm">
            <Typography>Register Form</Typography>
            <form onSubmit={handleSubmit}>
                <TextField 
                    fullWidth  
                    name="firstName"  
                    label="First Name"  
                    variant="outlined"  
                    margin="normal"  
                    type="text"
                    value={form.firstName}
                    required
                    onChange={handleChange}
                    autoComplete="given-name"  />
                <TextField 
                    fullWidth  
                    name="lastName"  
                    label="Last Name"  
                    variant="outlined"  
                    margin="normal"  
                    type="text"
                    value={form.lastName}
                    required
                    onChange={handleChange}
                    autoComplete="family-name"  />
                <TextField 
                    fullWidth  
                    name="emailId"  
                    label="Email-id"  
                    variant="outlined"  
                    margin="normal"  
                    type="email"
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
                    label="Register"
                    variant="contained"  
                    color="primary">
                    Register
                </Button>
            </form>
        </Container> 
    );
}

export default RegisterForm;
