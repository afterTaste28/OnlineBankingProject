package com.aftertaste.onlinebanking.account.controller;

import com.aftertaste.onlinebanking.account.dto.CardInfoDTO;
import com.aftertaste.onlinebanking.account.entity.Card;
import com.aftertaste.onlinebanking.account.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureOrder;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

@RestController
@RequestMapping("api/account")
public class AccountController {
    private final AccountService accountService;

    @Autowired
    public AccountController(AccountService accountService){
        this.accountService = accountService;
    }

    @GetMapping("/balance")
    public BigDecimal getBalance(Authentication authentication){
        String emailId = authentication.getName();
        return accountService.getBalance(emailId);
    }

    @GetMapping("/cardDetails")
    public CardInfoDTO getCardDetails(Authentication authentication){
        String emailId = authentication.getName();
        return accountService.getCardDetails(emailId);
    }
}

