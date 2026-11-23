package com.eventticket.controller;

import com.eventticket.dto.DtoPurchase;

import java.util.List;

public interface PurchasedController {

    public List<DtoPurchase> addPurchaseList();

    public List<DtoPurchase> myPurchasesGet();

}
