package com.aftertaste.onlinebanking.transaction.dto;

import com.aftertaste.onlinebanking.transaction.entity.Transaction;
import com.aftertaste.onlinebanking.transaction.entity.TransactionStatus;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class TransactionDTO {
    private Long id;
    private String senderAccountNumber;
    private String receiverAccountNumber;
    private BigDecimal amount;
    private LocalDateTime timestamp;

    private TransactionStatus transactionStatus;
    private String description;
    private String transactionStatusMessage;
    private String transactionType;

    public TransactionDTO(Transaction trx){
        this.id = trx.getId();
        this.senderAccountNumber = trx.getSenderAccountNumber();
        this.receiverAccountNumber = trx.getReceiverAccountNumber();
        this.amount = trx.getAmount();
        this.timestamp = trx.getTimestamp();
        this.transactionStatus = trx.getTransactionStatus();
        this.description = trx.getDescription();
        this.transactionStatusMessage = trx.getTransactionStatusMessage();
    }
}
