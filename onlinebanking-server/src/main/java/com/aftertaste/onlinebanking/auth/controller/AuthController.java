package com.aftertaste.onlinebanking.auth.controller;

import com.aftertaste.onlinebanking.auth.dto.LoginRequest;
import com.aftertaste.onlinebanking.auth.dto.RegisterRequest;
import com.aftertaste.onlinebanking.auth.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    static{
        System.out.println("Inside controller*************");
    }
    @Autowired
    UserService userService;

    @PostMapping("/register")
    public void registerUser(@RequestBody RegisterRequest registerRequestDTO){
        userService.registerUser(registerRequestDTO);
    }

    
    @PostMapping("/login")
    public void loginUser(@RequestBody LoginRequest loginRequestDTO){
        System.out.println("Inside login method:" +loginRequestDTO.toString());
    }

    @GetMapping("/isUp")
    public void isUp(){

        System.out.println("Up and runnign");
    }
}
