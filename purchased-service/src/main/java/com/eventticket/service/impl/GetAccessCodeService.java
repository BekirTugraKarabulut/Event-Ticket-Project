package com.eventticket.service.impl;

import com.eventticket.model.Purchase;
import com.eventticket.repository.PurchasedRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class GetAccessCodeService {

    private final PurchasedRepository purchasedRepository;

    public String getAccessCodeByPurchasedId(Long purchasedId){

        Optional<Purchase> purchase = purchasedRepository.findByPurchaseId(purchasedId);

        if(purchase.isPresent()){
            return purchase.get().getAccessCode();
        }else{
            throw new RuntimeException("Purchase not found");
        }

    }

}
