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

    private final UserService userService;

    @Autowired
    public AuthController(UserService userService){
        this.userService = userService;
    }

    @PostMapping("/register")
    public void registerUser(@RequestBody RegisterRequest registerRequestDTO){
        userService.registerUser(registerRequestDTO);
    }

    
    @PostMapping("/login")
    public String loginUser(@RequestBody LoginRequest loginRequestDTO){
        return userService.loginUser(loginRequestDTO);
    }
}
