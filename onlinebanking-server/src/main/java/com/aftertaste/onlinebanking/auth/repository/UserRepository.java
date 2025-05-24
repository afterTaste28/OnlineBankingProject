package com.aftertaste.onlinebanking.auth.repository;

import com.aftertaste.onlinebanking.auth.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long>, CustomUserRepository {
    public Optional<User> findByEmailId(String emailId);
}
