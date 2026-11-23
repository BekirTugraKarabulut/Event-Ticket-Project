package com.eventticket.controller;

import com.eventticket.dto.DtoCredit;
import com.eventticket.dto.DtoCreditUI;

public interface CreditController {

    public DtoCredit payTicket(DtoCreditUI dtoCreditUI);

}
