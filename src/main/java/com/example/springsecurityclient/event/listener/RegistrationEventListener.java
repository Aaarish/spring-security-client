package com.example.springsecurityclient.event.listener;

import com.example.springsecurityclient.entity.User;
import com.example.springsecurityclient.event.RegistrationEvent;
import com.example.springsecurityclient.service.UserServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;

import java.util.UUID;

@Slf4j
public class RegistrationEventListener implements ApplicationListener<RegistrationEvent> {

    @Autowired
    private UserServiceImpl userService;

    @Override
    public void onApplicationEvent(RegistrationEvent event) {
        User user = event.getUser();
        String token = UUID.randomUUID().toString();
        userService.saveTokenForUser(user, token);

        String url = event.getApplicationUrl() + "/verifyRegistration?token=" + token;
        log.info("Click the link to verify your user account", url);
    }
}
