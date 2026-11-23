package com.eventticket.service.impl;

import org.springframework.stereotype.Service;

@Service
public class CreditNumberMaskService {

    public String creditMask(String creditNumber){

        return creditNumber.substring(0,6) + "******"+ creditNumber.substring(12);

    }

}
