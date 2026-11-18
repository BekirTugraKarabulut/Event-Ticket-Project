package com.eventticket.controller.impl;

import com.eventticket.controller.TicketController;
import com.eventticket.dto.DtoTicket;
import com.eventticket.dto.DtoTicketUI;
import com.eventticket.service.TicketService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/tickets")
@SecurityRequirement(name = "bearerAuth")
@RequiredArgsConstructor
public class TicketControllerImpl implements TicketController {

    private final TicketService ticketService;

    @Override
    @PostMapping(path = "/create-ticket")
    public DtoTicket createTicket(@RequestBody DtoTicketUI dtoTicketUI) {
        return ticketService.createTicket(dtoTicketUI);
    }

}
