package com.aftertaste.onlinebanking.auth.dto;


import com.fasterxml.jackson.annotation.JsonProperty;

public class LoginRequest {
    @JsonProperty("emailId")
    private String emailId;
    private String password;

    public String getEmail() {
        return emailId;
    }

    public void setEmail(String emailId) {
        this.emailId = emailId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
