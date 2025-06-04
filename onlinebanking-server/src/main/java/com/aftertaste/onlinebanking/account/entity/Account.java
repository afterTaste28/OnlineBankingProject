package com.aftertaste.onlinebanking.account.entity;

import com.aftertaste.onlinebanking.auth.entity.User;
import jakarta.persistence.*;

import java.math.BigDecimal;


@Entity
@Table(name = "ACCOUNT")
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long accountId;

    @Column(unique = true)
    private String accountNumber;

    @Column(nullable = false)
    private BigDecimal balance = BigDecimal.ZERO;

    @OneToOne
    @JoinColumn(name = "user_id", nullable = false, unique = true)
    private User user;

    public Account(String accountNumber, BigDecimal balance, User user) {
        this.accountNumber = accountNumber;
        this.balance = balance;
        this.user = user;
    }
    public Account(){

    }

    public BigDecimal getBalance() {
        return balance;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
