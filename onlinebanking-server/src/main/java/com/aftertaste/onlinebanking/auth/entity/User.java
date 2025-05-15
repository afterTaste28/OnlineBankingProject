package com.aftertaste.onlinebanking.auth.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "USERS")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String firstName;
    private String lastName;

    public String getEmailId() {
        return emailId;
    }

    @Column(nullable = false, unique = true)
    private String emailId;
    private String password;

    public void setRole(Role role) {
        this.role = role;
    }

    @Enumerated(EnumType.STRING)
    private Role role;

    public User(String firstName, String lastName, String emailId, String password){
        this.firstName = firstName;
        this.lastName = lastName;
        this.emailId = emailId;
        this.password = password;
    }

    protected User(){}


}
