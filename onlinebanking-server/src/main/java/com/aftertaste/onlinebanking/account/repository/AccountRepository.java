package com.aftertaste.onlinebanking.account.repository;

import com.aftertaste.onlinebanking.account.entity.Account;
import com.aftertaste.onlinebanking.auth.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AccountRepository extends JpaRepository<Account, Long> {
    public Optional<Account> findByUser(User user);
}
