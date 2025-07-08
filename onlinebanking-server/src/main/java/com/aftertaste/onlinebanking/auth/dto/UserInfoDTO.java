package com.aftertaste.onlinebanking.auth.dto;

import com.aftertaste.onlinebanking.auth.entity.User;
import lombok.Data;

@Data
public class UserInfoDTO {
    private String name;
    private String email;

    // Constructor
    public UserInfoDTO(User user) {
        this.name = user.getFirstName() + " " + user.getLastName();
        this.email = user.getEmailId();
    }
}
