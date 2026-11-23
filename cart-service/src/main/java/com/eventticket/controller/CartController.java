package com.eventticket.controller;

import com.eventticket.dto.DtoCart;
import com.eventticket.dto.DtoCartCreate;
import com.eventticket.dto.DtoCartDetails;

import java.util.List;

public interface CartController {

    public DtoCart createCart(DtoCartCreate dtoCartCreate);

    public List<DtoCartDetails> getCardListByUsername(String username);

    public DtoCart updateCart(Long id,Integer quality);

    public void deleteCart(Long sepetId);

    public Integer sumPay(String username);

    public void deleteAllCartsByUsername(String username);

}
