package com.aftertaste.onlinebanking.account.service;

import com.aftertaste.onlinebanking.account.dto.CardInfoDTO;
import com.aftertaste.onlinebanking.account.entity.Account;
import com.aftertaste.onlinebanking.account.entity.Card;
import com.aftertaste.onlinebanking.account.repository.AccountRepository;
import com.aftertaste.onlinebanking.account.utils.CardGenerator;
import com.aftertaste.onlinebanking.auth.entity.User;
import com.aftertaste.onlinebanking.auth.repository.UserRepository;
import com.aftertaste.onlinebanking.transaction.entity.Transaction;
import com.aftertaste.onlinebanking.transaction.entity.TransactionStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
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
        Card card = CardGenerator.generateCard(account,user);
        account.getCardDetails().add(card);
        accountRepository.save(account);
    }

    private String generateAccountNumber() {
        long randomNumber = 1000000000L + new Random().nextLong(9000000000L);
        return "AN" + randomNumber;
    }

    public CardInfoDTO getCardDetails(String emailId){
        User user = userRepository.findByEmailId(emailId)
                .orElseThrow(()->new UsernameNotFoundException("No user present by email:"+emailId));
        Account account = accountRepository.findByUser(user).orElseThrow(()->new RuntimeException("Account does not exist with user"+user.getEmailId()));
        return new CardInfoDTO(account.getCardDetails().get(0));
    }

    public void moneyTransfer(Transaction trx) throws Exception {

        Account sender = accountRepository.findByAccountNumber(trx.getSenderAccountNumber())
                .orElseThrow(()-> new RuntimeException("No Account with account number:"+trx.getSenderAccountNumber()));
        Account receiver = accountRepository.findByAccountNumber(trx.getReceiverAccountNumber())
                .orElseThrow(()-> new RuntimeException("No Account with account number:"+trx.getReceiverAccountNumber()));

        if(sender.equals(receiver)){
            trx.setTransactionStatus(TransactionStatus.FAILED);
            trx.setTransactionStatusMessage("Sender and Receiver cannot be same.");
            return;
        }

        if(sender.getBalance().compareTo(trx.getAmount()) == -1){
            trx.setTransactionStatus(TransactionStatus.FAILED);
            trx.setTransactionStatusMessage("Insufficient fund.");
            return;
        }

        BigDecimal senderBalance = sender.getBalance();
        BigDecimal receiverBalance = receiver.getBalance();
        BigDecimal amount = trx.getAmount();

        sender.setBalance(senderBalance.subtract(amount));
        receiver.setBalance(receiverBalance.add(amount));

        accountRepository.save(sender);
        accountRepository.save(receiver);
        trx.setTransactionStatus(TransactionStatus.SUCCESS);
        trx.setTransactionStatusMessage("Transaction Successful.");
    }

    public String getAccountNumberFromEmail(String emailId){
        User user = userRepository.findByEmailId(emailId).
                orElseThrow(()-> new UsernameNotFoundException("User not present with emailid:" +emailId));
        Account account = accountRepository.findByUser(user)
                .orElseThrow(()-> new RuntimeException("Account does not exist with user with mail:"+emailId));

        return account.getAccountNumber();
    }


}
