package com.example.springsecurityclient.converter;

import com.example.springsecurityclient.entity.User;
import com.example.springsecurityclient.model.UserModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class UserConverter {

    public User convertModelToEntity(UserModel userModel){
        User user = new User();

        user.setFirstname(userModel.getFirstname());
        user.setLastname(userModel.getLastname());
        user.setPassword(userModel.getPassword());
        user.setRole("USER");
        user.setEnabled(false);

        return user;
    }
}
