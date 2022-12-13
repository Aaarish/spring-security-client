package com.example.springsecurityclient.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "users")
@Data
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;
    private String firstname;
    private String lastname;
    private String password;
    private String role;
    private boolean enabled;
}
