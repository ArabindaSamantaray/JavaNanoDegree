package com.example.arabinda.SpringBasics;

import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class MessageService {

    String message;

    public MessageService(String message) {
        this.message = message;
    }

    @PostConstruct
    public void postConstruct(){
        System.out.println("Message Service Bean was generated");
    }

    public String upperCase(){
        return message.toUpperCase();
    }

    public String lowerCase(){
        return message.toLowerCase();
    }
}
