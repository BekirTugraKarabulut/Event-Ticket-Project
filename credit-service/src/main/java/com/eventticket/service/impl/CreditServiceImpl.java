package com.eventticket.service.impl;

import com.eventticket.dto.DtoCredit;
import com.eventticket.dto.DtoCreditUI;
import com.eventticket.manager.PurchasedFeignClient;
import com.eventticket.model.Credit;
import com.eventticket.repository.CreditRepository;
import com.eventticket.service.CreditService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
@RequiredArgsConstructor
public class CreditServiceImpl implements CreditService {

    private final CreditRepository creditRepository;
    private final PurchasedFeignClient purchasedFeignClient;
    private final CreditNumberMaskService creditNumberMaskService;

    @Transactional
    @Override
    public DtoCredit payTicket(DtoCreditUI dtoCreditUI) {

        String username = SecurityContextHolder.getContext().getAuthentication().getName();

        Credit credit = Credit.builder()
                .username(username)
                .creditCartNumber(creditNumberMaskService.creditMask(dtoCreditUI.getCreditCartNumber()))
                .mmdd(dtoCreditUI.getMmdd())
                .createDate(new Date())
                .build();

        Credit dbCredit = creditRepository.save(credit);
        purchasedFeignClient.addPurchaseList();

        DtoCredit dtoCredit = DtoCredit.builder()
                .id(dbCredit.getId())
                .username(dbCredit.getUsername())
                .creditCartNumber(dbCredit.getCreditCartNumber())
                .mmdd(dbCredit.getMmdd())
                .createDate(dbCredit.getCreateDate())
                .build();

        return dtoCredit;
    }
}
