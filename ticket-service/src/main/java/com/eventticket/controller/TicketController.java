package com.eventticket.controller;

import com.eventticket.dto.DtoTicket;
import com.eventticket.dto.DtoTicketUI;

public interface TicketController {

    public DtoTicket createTicket(DtoTicketUI dtoTicketUI);

}
