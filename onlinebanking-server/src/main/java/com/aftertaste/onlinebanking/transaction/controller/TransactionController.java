package com.aftertaste.onlinebanking.transaction.controller;

import com.aftertaste.onlinebanking.transaction.dto.SendMoneyDTO;
import com.aftertaste.onlinebanking.transaction.dto.TransactionDTO;
import com.aftertaste.onlinebanking.transaction.entity.Transaction;
import com.aftertaste.onlinebanking.transaction.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/transact")
public class TransactionController {

    private TransactionService transactionService;

    @Autowired
    public TransactionController(TransactionService transactionService){
        this.transactionService = transactionService;
    }

    @PostMapping("/send")
    public TransactionDTO transact(@RequestBody SendMoneyDTO sendMoneyDTO, Authentication authentication){
        Transaction transaction = transactionService.commitPendingTransaction(sendMoneyDTO,authentication.getName());
        return transactionService.moneyTransfer(transaction);
    }

    @GetMapping("/transactions/{page}/{size}")
    public Page<TransactionDTO> getTransactions(@PathVariable int page, @PathVariable int size, Authentication authentication){
        return transactionService.getTransactionsForPage(page,size,authentication.getName());
    }
}
