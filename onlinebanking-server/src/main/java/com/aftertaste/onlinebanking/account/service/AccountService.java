package com.aftertaste.onlinebanking.account.service;

import com.aftertaste.onlinebanking.account.entity.Account;
import com.aftertaste.onlinebanking.account.entity.Card;
import com.aftertaste.onlinebanking.account.repository.AccountRepository;
import com.aftertaste.onlinebanking.account.utils.CardGenerator;
import com.aftertaste.onlinebanking.auth.entity.User;
import com.aftertaste.onlinebanking.auth.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Random;

@Service
public class AccountService {
    private final AccountRepository accountRepository;
    private final UserRepository userRepository;

    @Autowired
    public AccountService(AccountRepository accountRepository, UserRepository userRepository){
        this.accountRepository = accountRepository;
        this.userRepository = userRepository;
    }

    public BigDecimal getBalance(String emailId){
        User user = userRepository.findByEmailId(emailId)
                .orElseThrow(()->new UsernameNotFoundException("No user present by email:"+emailId));
        Account account = accountRepository.findByUser(user)
                .orElseThrow(()->new UsernameNotFoundException("No account present for user with email:"+user.getEmailId()));
        return account.getBalance();
    }

    public void createAccount(User user){
        String accountNumber = generateAccountNumber();
        Account account = new Account(accountNumber,BigDecimal.ZERO,user);
        Card card = CardGenerator.generateCard(account);
        account.getCardDetails().add(card);
        accountRepository.save(account);
    }

    private String generateAccountNumber() {
        long randomNumber = 1000000000L + new Random().nextLong(9000000000L);
        return "AN" + randomNumber;
    }


}
