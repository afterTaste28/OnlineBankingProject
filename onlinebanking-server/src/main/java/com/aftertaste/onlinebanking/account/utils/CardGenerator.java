package com.aftertaste.onlinebanking.account.utils;

import com.aftertaste.onlinebanking.account.entity.Account;
import com.aftertaste.onlinebanking.account.entity.Card;
import com.aftertaste.onlinebanking.auth.entity.User;

import java.time.LocalDate;
import java.time.YearMonth;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class CardGenerator {

    public static Card generateCard(Account account, User user){
        Card card = new Card();
        card.setAccount(account);
        card.setExpiryDate(generateExpiryDate());
        card.setCardNumber(generateRandomCardNumber());
        card.setCardHolderName(user.getFirstName() + " " + user.getLastName());
        card.setCardMatrix(generateCardMatrix());
        return card;
    }

    private static LocalDate generateExpiryDate() {
        YearMonth expiry = YearMonth.now().plusYears(10);
        return expiry.atDay(1);
    }

    private static String generateRandomCardNumber() {
        Random random = new Random();
        StringBuilder cardNumber = new StringBuilder();
        for (int i = 0; i < 16; i++) {
            cardNumber.append(random.nextInt(10));
        }
        return cardNumber.toString();
    }

    private static String generateCardMatrix() {
        Random random = new Random();
        Set<Character> selectedChars = new HashSet<>();

        // Ensure 8 unique random uppercase letters
        while (selectedChars.size() < 8) {
            char c = (char) ('A' + random.nextInt(26));
            selectedChars.add(c);
        }

        StringBuilder matrix = new StringBuilder();
        int count = 0;

        for (char ch : selectedChars) {
            int value = 10 + random.nextInt(90); // 2-digit number (10–99)
            matrix.append(ch).append(":").append(value);

            if (++count < 8) matrix.append(","); // Add comma except after last
        }

        return matrix.toString();
    }

}
