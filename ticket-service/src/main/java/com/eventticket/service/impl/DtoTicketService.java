package com.eventticket.service.impl;

import com.eventticket.dto.DtoTicket;
import com.eventticket.entity.Ticket;
import org.springframework.stereotype.Service;

@Service
public class DtoTicketService {

    public DtoTicket responseTicket(Ticket ticket){

        DtoTicket dtoTicket = DtoTicket.builder()
                .id(ticket.getId())
                .name(ticket.getName())
                .date(ticket.getDate())
                .day(ticket.getDay())
                .time(ticket.getTime())
                .place(ticket.getPlace())
                .soldOut(ticket.isSoldOut())
                .imageUrl(ticket.getImageUrl())
                .build();

        return dtoTicket;
    }

}
