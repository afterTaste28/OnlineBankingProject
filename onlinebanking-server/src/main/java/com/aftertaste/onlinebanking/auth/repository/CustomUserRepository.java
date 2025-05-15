package com.aftertaste.onlinebanking.auth.repository;

import com.aftertaste.onlinebanking.auth.entity.User;

public interface CustomUserRepository {
    public User findByCustomParam(String emailId);
}
