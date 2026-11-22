package com.eventticket.controller.impl;

import com.eventticket.controller.CartController;
import com.eventticket.dto.DtoCart;
import com.eventticket.dto.DtoCartCreate;
import com.eventticket.dto.DtoCartDetails;
import com.eventticket.service.CartService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/carts")
@SecurityRequirement(name = "bearerAuth")
@RequiredArgsConstructor
public class CartControllerImpl implements CartController {

    private final CartService cartService;

    @PostMapping(path = "/create-cart")
    @Override
    public DtoCart createCart(@RequestBody DtoCartCreate dtoCartCreate) {
        return cartService.createCart(dtoCartCreate);
    }

    @GetMapping(path = "/list/{username}")
    @Override
    public List<DtoCartDetails> getCardListByUsername(@PathVariable(name = "username") String username) {
        return cartService.getCardListByUsername(username);
    }

    @PutMapping(path = "/update-cart/{id}")
    @Override
    public DtoCart updateCart(@PathVariable(name = "id") Long id, @RequestBody Integer quality) {
        return cartService.updateCart(id, quality);
    }

    @DeleteMapping(path = "/delete-cart/{sepetId}")
    @Override
    public void deleteCart(@PathVariable(name = "sepetId") Long sepetId) {
        cartService.deleteCart(sepetId);
    }

    @GetMapping("/sum/pay/{username}")
    @Override
    public Integer sumPay(@PathVariable(name = "username") String username) {
        return cartService.sumPay(username);
    }

}
