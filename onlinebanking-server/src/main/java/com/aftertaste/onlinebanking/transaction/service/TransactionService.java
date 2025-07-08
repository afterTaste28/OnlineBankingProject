package com.aftertaste.onlinebanking.transaction.service;

import com.aftertaste.onlinebanking.account.service.AccountService;
import com.aftertaste.onlinebanking.transaction.dto.SendMoneyDTO;
import com.aftertaste.onlinebanking.transaction.dto.TransactionDTO;
import com.aftertaste.onlinebanking.transaction.entity.Transaction;
import com.aftertaste.onlinebanking.transaction.entity.TransactionStatus;
import com.aftertaste.onlinebanking.transaction.repository.TransactionRepository;
import jakarta.transaction.Transactional;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TransactionService {

    private TransactionRepository transactionRepository;
    private AccountService accountService;

    public TransactionService(TransactionRepository transactionRepository, AccountService accountService){
        this.transactionRepository = transactionRepository;
        this.accountService = accountService;
    }

    @Transactional
    public Transaction commitPendingTransaction(SendMoneyDTO sendMoneyDTO,String emailId){
        String accountNumber = accountService.getAccountNumberFromEmail(emailId);
        Transaction trx = new Transaction();
        trx.setAmount(sendMoneyDTO.getAmount());
        trx.setDescription(sendMoneyDTO.getDescription());
        trx.setSenderAccountNumber(accountNumber);
        trx.setReceiverAccountNumber(sendMoneyDTO.getRecipientAccount());
        trx.setTransactionStatus(TransactionStatus.PENDING);
        trx.setTimestamp(LocalDateTime.now());
        return transactionRepository.save(trx);
    }

    @Transactional
    public TransactionDTO moneyTransfer(Transaction trx){
        try{
            TransactionDTO transactionResponseDTO = new TransactionDTO();
            transactionResponseDTO.setId(trx.getId());
            accountService.moneyTransfer(trx);

            transactionRepository.save(trx);
        } catch(Exception e){
            trx.setTransactionStatusMessage(e.getMessage());
            trx.setTransactionStatus(TransactionStatus.FAILED);
            transactionRepository.save(trx);
        }
        return new TransactionDTO(trx);
    }

    public Page<TransactionDTO> getTransactionsForPage(int page, int size, String emailId){
        String accountNumber = accountService.getAccountNumberFromEmail(emailId);
        Pageable pg = PageRequest.of(page,size);
        Page<Transaction> transactions = transactionRepository.fetchTransactions(accountNumber, pg);
        Page<TransactionDTO> transactionDTOList = transactions
                .map(tr->{
                    String transactionType = tr.getSenderAccountNumber().equals(accountNumber)?"DEBIT":"CREDIT";
                    TransactionDTO transactionDTO = new TransactionDTO(tr);
                    transactionDTO.setTransactionType(transactionType);
                    return transactionDTO;
                });
        return transactionDTOList;
    }
}
