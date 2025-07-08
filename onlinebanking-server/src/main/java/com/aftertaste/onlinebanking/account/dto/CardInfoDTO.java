package com.aftertaste.onlinebanking.account.dto;

import com.aftertaste.onlinebanking.account.entity.Account;
import com.aftertaste.onlinebanking.account.entity.Card;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
public class CardInfoDTO {

    private String cardNumber;
    private String cardHolderName;
    private LocalDate expiryDate;
    private String cardMatrix;

    public CardInfoDTO(Card card){
        this.cardHolderName = card.getCardHolderName();
        this.cardMatrix = card.getCardMatrix();
        this.cardNumber = card.getCardNumber();
        this.expiryDate = card.getExpiryDate();
    }
}
