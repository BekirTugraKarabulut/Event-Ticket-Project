package com.eventticket.controller;

import com.eventticket.dto.DtoTicket;
import com.eventticket.dto.DtoTicketUI;
import com.eventticket.dto.DtoTicketUpdate;

import java.util.List;

public interface TicketController {

    public DtoTicket createTicket(DtoTicketUI dtoTicketUI);

    public List<DtoTicket> getAllTickets();

    public List<DtoTicket> getTicketsByCategoryId(int categoryId);

    public DtoTicket updateTicket(Long id , DtoTicketUpdate dtoTicketUpdate);

    public void deleteTicket(Long id);

    public DtoTicket getTicketById(Long id);

}
