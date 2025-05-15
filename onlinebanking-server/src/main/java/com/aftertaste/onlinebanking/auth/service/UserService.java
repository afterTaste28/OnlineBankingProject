package com.aftertaste.onlinebanking.auth.service;

import com.aftertaste.onlinebanking.auth.dto.RegisterRequest;
import com.aftertaste.onlinebanking.auth.entity.User;

public interface UserService {
    public void registerUser(RegisterRequest registerRequestDTO);
}
