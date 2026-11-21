package com.eventticket.service.impl;

import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class GenerateAccessCode {

    public String generateCode(String name){

        return name.substring(0,2).toUpperCase() +
                "-" +
                UUID.randomUUID().toString().substring(0,8).toUpperCase();
    }

}
