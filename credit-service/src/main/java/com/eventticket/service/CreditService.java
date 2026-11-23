package com.eventticket.service;

import com.eventticket.dto.DtoCredit;
import com.eventticket.dto.DtoCreditUI;

public interface CreditService {

    public DtoCredit payTicket(DtoCreditUI dtoCreditUI);

}
