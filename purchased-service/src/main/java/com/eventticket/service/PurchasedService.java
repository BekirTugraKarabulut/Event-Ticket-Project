package com.eventticket.service;

import com.eventticket.dto.DtoPurchase;

import java.util.List;

public interface PurchasedService {

    public List<DtoPurchase> addPurchaseList();

    public List<DtoPurchase> myPurchasesGet();

}
