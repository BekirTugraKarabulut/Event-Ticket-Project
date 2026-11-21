package com.eventticket.controller.impl;

import com.eventticket.controller.TicketController;
import com.eventticket.dto.DtoTicket;
import com.eventticket.dto.DtoTicketUI;
import com.eventticket.dto.DtoTicketUpdate;
import com.eventticket.service.TicketService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @Override
    @GetMapping(path = "/all")
    public List<DtoTicket> getAllTickets() {
        return ticketService.getAllTickets();
    }

    @Override
    @GetMapping(path = "/category/{categoryId}")
    public List<DtoTicket> getTicketsByCategoryId(@PathVariable(name = "categoryId") int categoryId) {
        return ticketService.getTicketsByCategoryId(categoryId);
    }

    @Override
    @PutMapping(path = "/update/{id}")
    public DtoTicket updateTicket(@PathVariable(name = "id") Long id,@RequestBody DtoTicketUpdate dtoTicketUpdate) {
        return ticketService.updateTicket(id, dtoTicketUpdate);
    }

    @Override
    @DeleteMapping(path = "/delete/{id}")
    public void deleteTicket(@PathVariable(name = "id") Long id) {
        ticketService.deleteTicket(id);
    }

}
