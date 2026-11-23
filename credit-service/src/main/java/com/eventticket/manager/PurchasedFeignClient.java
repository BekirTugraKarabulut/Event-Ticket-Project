package com.eventticket.manager;

import com.eventticket.dto.DtoPurchase;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@FeignClient(name = "PURCHASED-SERVICE")
public interface PurchasedFeignClient {

    @PostMapping("/purchased/add-purchase")
    List<DtoPurchase> addPurchaseList();


}
