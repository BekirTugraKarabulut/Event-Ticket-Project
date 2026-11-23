package com.eventticket.controller.impl;

import com.eventticket.controller.PurchasedController;
import com.eventticket.dto.DtoPurchase;
import com.eventticket.service.PurchasedService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@SecurityRequirement(name = "bearerAuth")
@RestController
@RequestMapping("/purchased")
@RequiredArgsConstructor
public class PurchasedControllerImpl implements PurchasedController {

    private final PurchasedService purchasedService;

    @PostMapping("/add-purchase")
    @Override
    public List<DtoPurchase> addPurchaseList() {
        return purchasedService.addPurchaseList();
    }

    @GetMapping(path = "/my-purchases")
    @Override
    public List<DtoPurchase> myPurchasesGet() {
        return purchasedService.myPurchasesGet();
    }

}
