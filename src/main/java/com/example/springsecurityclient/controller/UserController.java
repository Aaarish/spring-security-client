package com.example.springsecurityclient.controller;

import com.example.springsecurityclient.entity.User;
import com.example.springsecurityclient.event.RegistrationEvent;
import com.example.springsecurityclient.model.UserModel;
import com.example.springsecurityclient.service.UserServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {
    @Autowired
    private UserServiceImpl userService;

    private ApplicationEventPublisher eventPublisher;

    @GetMapping("hello")
    public String hello(){
        return "Hello World!";
    }

    @PostMapping("users/register")
    public String registerUser(@RequestBody UserModel userModel, HttpServletRequest http){
        User registeredUser = userService.registerUser(userModel);

        eventPublisher.publishEvent(new RegistrationEvent(registeredUser, applicationUrl(http)));

        return userModel.getFirstname() + " " + userModel.getLastname() + " successfully registered.";
    }

    private String applicationUrl(HttpServletRequest http) {
        return "http://" + http.getServerName() + ":" + http.getServerPort() + http.getContextPath();
    }
}
