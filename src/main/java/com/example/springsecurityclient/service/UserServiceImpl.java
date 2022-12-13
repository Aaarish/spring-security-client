package com.example.springsecurityclient.service;


import com.example.springsecurityclient.converter.UserConverter;
import com.example.springsecurityclient.entity.User;
import com.example.springsecurityclient.entity.VerificationToken;
import com.example.springsecurityclient.model.UserModel;
import com.example.springsecurityclient.repository.UserRepo;
import com.example.springsecurityclient.repository.VerificationTokenRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private VerificationTokenRepo verificationTokenRepo;

    @Autowired
    private UserConverter userConverter;

    @Override
    public User registerUser(UserModel userModel) {
        User user = userConverter.convertModelToEntity(userModel);
        User savedUser = userRepo.save(user);

        return savedUser;
    }

    @Override
    public void saveTokenForUser(User user, String token) {
        VerificationToken verificationToken = new VerificationToken(user, token);
        verificationTokenRepo.save(verificationToken);
    }
}
