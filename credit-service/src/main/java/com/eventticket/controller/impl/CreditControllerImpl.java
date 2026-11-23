package com.eventticket.controller.impl;

import com.eventticket.controller.CreditController;
import com.eventticket.dto.DtoCredit;
import com.eventticket.dto.DtoCreditUI;
import com.eventticket.service.CreditService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SecurityRequirement(name = "bearerAuth")
@RestController
@RequestMapping(path = "/credit")
@RequiredArgsConstructor
public class CreditControllerImpl implements CreditController {

    private final CreditService creditService;

    @PostMapping(path = "/pay")
    @Override
    public DtoCredit payTicket(@RequestBody DtoCreditUI dtoCreditUI) {
        return creditService.payTicket(dtoCreditUI);
    }

}
