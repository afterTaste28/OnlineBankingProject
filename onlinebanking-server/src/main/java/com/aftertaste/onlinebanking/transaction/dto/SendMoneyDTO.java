package com.aftertaste.onlinebanking.transaction.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class SendMoneyDTO {

    private String description;
    private String recipientAccount;
    private BigDecimal amount;

}
