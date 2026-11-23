package com.eventticket.manager;

import com.eventticket.dto.DtoCartDetails;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "CART-SERVICE")
public interface CartFeignClient {

    @GetMapping(path = "/carts/list/{username}")
    List<DtoCartDetails> getCardListByUsername(@PathVariable(name = "username") String username);

    @DeleteMapping(path = "/carts/all/{username}")
    void deleteAllCartsByUsername(@PathVariable(name = "username") String username);

}
