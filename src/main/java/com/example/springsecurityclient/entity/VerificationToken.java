package com.example.springsecurityclient.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Calendar;
import java.util.Date;

@Entity
@Data
@NoArgsConstructor
public class VerificationToken {

    private static final int EXPIRATION_TIME = 10;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long tokenId;
    private String token;
    private Date expirationTime;

    @OneToOne
    @JoinColumn(name = "user_id", referencedColumnName = "userId", nullable = false)
    private User user;

    public VerificationToken(User user, String token) {
        this.user = user;
        this.token = token;
        this.expirationTime = getExpirationTime(EXPIRATION_TIME);
    }

    public VerificationToken(String token) {
        this.token = token;
        this.expirationTime = getExpirationTime(EXPIRATION_TIME);
    }

    private Date getExpirationTime(int expirationTime) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(new Date().getTime());
        calendar.add(Calendar.MINUTE, expirationTime);

        return new Date(calendar.getTime().getTime());
    }
}
