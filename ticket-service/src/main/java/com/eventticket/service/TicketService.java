package com.eventticket.service;

import com.eventticket.dto.DtoTicket;
import com.eventticket.dto.DtoTicketUI;

public interface TicketService {

    public DtoTicket createTicket(DtoTicketUI dtoTicketUI);

}
