package com.example.springsecurityclient.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@EnableWebSecurity
@Configuration
public class UserConfig{

//    private static final String[] WHITE_LIST_URLS = {"users/register", "users/hello"};

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder(11);
    }


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        System.out.println("Inside security filter chain method.");

        http
                .cors()
                .and()
                .csrf()
                .disable()
                .authorizeHttpRequests()
                .requestMatchers("/users/register").authenticated()
                .requestMatchers("/hello").permitAll()
                .and().httpBasic()
                .and().formLogin();

        return http.build();
    }

}


