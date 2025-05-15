package com.aftertaste.onlinebanking.auth.repository;

import com.aftertaste.onlinebanking.auth.entity.User;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

@Repository
public class CustomUserRepositoryImpl implements CustomUserRepository{
    @PersistenceContext
    EntityManager entityManager;

    @Override
    public User findByCustomParam(String emailId) {
       System.out.println("Here in Custom Implementation of repository");
        return null;
    }
}
