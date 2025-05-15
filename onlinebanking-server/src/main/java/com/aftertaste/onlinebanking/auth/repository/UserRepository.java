package com.aftertaste.onlinebanking.auth.repository;

import com.aftertaste.onlinebanking.auth.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long>, CustomUserRepository {
    public User findByEmailId(String emailId);
}
