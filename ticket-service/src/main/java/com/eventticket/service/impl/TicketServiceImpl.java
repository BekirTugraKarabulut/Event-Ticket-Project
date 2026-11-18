package com.eventticket.service.impl;

import com.eventticket.dto.DtoTicket;
import com.eventticket.dto.DtoTicketUI;
import com.eventticket.entity.Ticket;
import com.eventticket.repository.TicketRepository;
import com.eventticket.service.TicketService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TicketServiceImpl implements TicketService {

    private final TicketRepository ticketRepository;
    private final DtoTicketService dtoTicketService;

    @Override
    public DtoTicket createTicket(DtoTicketUI dtoTicketUI) {

        Ticket ticket = Ticket.builder()
                .name(dtoTicketUI.getName())
                .date(dtoTicketUI.getDate())
                .day(dtoTicketUI.getDay())
                .time(dtoTicketUI.getTime())
                .place(dtoTicketUI.getPlace())
                .imageUrl(dtoTicketUI.getImageUrl())
                .soldOut(dtoTicketUI.isSoldOut())
                .build();

        Ticket dbTicket = ticketRepository.save(ticket);

        return dtoTicketService.responseTicket(dbTicket);
    }
}
