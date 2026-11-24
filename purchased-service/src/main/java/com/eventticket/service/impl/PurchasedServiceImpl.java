package com.eventticket.service.impl;

import com.eventticket.dto.DtoCartDetails;
import com.eventticket.dto.DtoPurchase;
import com.eventticket.manager.CartFeignClient;
import com.eventticket.model.Purchase;
import com.eventticket.repository.PurchasedRepository;
import com.eventticket.service.PurchasedService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PurchasedServiceImpl implements PurchasedService {

    private final PurchasedRepository purchasedRepository;
    private final CartFeignClient cartFeignClient;

    @Transactional
    @Override
    public List<DtoPurchase> addPurchaseList() {

        String username = SecurityContextHolder.getContext()
                .getAuthentication().getName();

        List<DtoCartDetails> dtoCartDetails =
                cartFeignClient.getCardListByUsername(username);

        List<Purchase> purchaseList = new ArrayList<>();

        for (DtoCartDetails cartDetails : dtoCartDetails) {
            Purchase purchase = new Purchase();
            purchase.setSepetId(cartDetails.getSepetId());
            purchase.setId(cartDetails.getId());
            purchase.setUsername(username);
            purchase.setName(cartDetails.getName());
            purchase.setQuantity(cartDetails.getQuantity());
            purchase.setDate(cartDetails.getDate());
            purchase.setDay(cartDetails.getDay());
            purchase.setPlace(cartDetails.getPlace());
            purchase.setTime(cartDetails.getTime());
            purchase.setImageUrl(cartDetails.getImageUrl());
            purchase.setPrice(cartDetails.getPrice());
            purchase.setAccessCode(cartDetails.getAccessCode());
            purchaseList.add(purchase);
        }

        purchasedRepository.saveAll(purchaseList);

        cartFeignClient.deleteAllCartsByUsername(username);

        return purchaseList.stream()
                .map(purchase -> {
                    DtoPurchase dto = new DtoPurchase();
                    dto.setSepetId(purchase.getSepetId());
                    dto.setId(purchase.getId());
                    dto.setUsername(purchase.getUsername());
                    dto.setName(purchase.getName());
                    dto.setQuantity(purchase.getQuantity());
                    dto.setDate(purchase.getDate());
                    dto.setDay(purchase.getDay());
                    dto.setPlace(purchase.getPlace());
                    dto.setTime(purchase.getTime());
                    dto.setImageUrl(purchase.getImageUrl());
                    dto.setPrice(purchase.getPrice());
                    dto.setAccessCode(purchase.getAccessCode());
                    return dto;
                })
                .toList();
    }

    @Override
    public List<DtoPurchase> myPurchasesGet() {

        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        List<Purchase> purchases = purchasedRepository.findByUsername(username);

            return purchases.stream()
                .map(
                        purchase -> {
                            DtoPurchase dto = new DtoPurchase();
                            dto.setPurchaseId(purchase.getPurchaseId());
                            dto.setSepetId(purchase.getSepetId());
                            dto.setId(purchase.getId());
                            dto.setUsername(purchase.getUsername());
                            dto.setName(purchase.getName());
                            dto.setQuantity(purchase.getQuantity());
                            dto.setDate(purchase.getDate());
                            dto.setDay(purchase.getDay());
                            dto.setPlace(purchase.getPlace());
                            dto.setTime(purchase.getTime());
                            dto.setImageUrl(purchase.getImageUrl());
                            dto.setPrice(purchase.getPrice());
                            dto.setAccessCode(purchase.getAccessCode());
                            return dto;
                        }
                ).toList();
    }
}
