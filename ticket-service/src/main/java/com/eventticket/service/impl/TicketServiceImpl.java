package com.eventticket.service.impl;

import com.eventticket.dto.DtoTicket;
import com.eventticket.dto.DtoTicketUI;
import com.eventticket.dto.DtoTicketUpdate;
import com.eventticket.entity.Ticket;
import com.eventticket.repository.TicketRepository;
import com.eventticket.service.TicketService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TicketServiceImpl implements TicketService {

    private final TicketRepository ticketRepository;
    private final DtoTicketService dtoTicketService;
    private final GenerateAccessCode generateAccessCode;

    @Override
    public DtoTicket createTicket(DtoTicketUI dtoTicketUI) {

        Ticket ticket = Ticket.builder()
                .name(dtoTicketUI.getName())
                .categoryId(dtoTicketUI.getCategoryId())
                .date(dtoTicketUI.getDate())
                .day(dtoTicketUI.getDay())
                .time(dtoTicketUI.getTime())
                .place(dtoTicketUI.getPlace())
                .imageUrl(dtoTicketUI.getImageUrl())
                .capacity(dtoTicketUI.getCapacity())
                .soldOut(dtoTicketUI.isSoldOut())
                .accessCode(generateAccessCode.generateCode(dtoTicketUI.getName()))
                .price(dtoTicketUI.getPrice())
                .build();

        Ticket dbTicket = ticketRepository.save(ticket);

        return dtoTicketService.responseTicket(dbTicket);
    }

    @Override
    public List<DtoTicket> getAllTickets() {

        List<Ticket> tickets = ticketRepository.findAll();

        return tickets.stream()
                .map(dtoTicketService::responseTicket)
                .toList();

    }

    @Override
    public List<DtoTicket> getTicketsByCategoryId(int categoryId) {

        List<Ticket> tickets = ticketRepository.findByCategoryId(categoryId);

        return tickets.stream()
                .map(dtoTicketService::responseTicket)
                .toList();
    }

    @Override
    public DtoTicket updateTicket(Long id , DtoTicketUpdate dtoTicketUpdate) {

        Optional<Ticket> ticket = ticketRepository.findById(id);

        if(ticket.isPresent()){

            ticket.get().setTime(dtoTicketUpdate.getTime());
            ticket.get().setDay(dtoTicketUpdate.getDay());
            ticketRepository.save(ticket.get());

            return dtoTicketService.responseTicket(ticket.get());
        }else{
            throw new RuntimeException("Ticket not found");
        }

    }

    @Override
    public void deleteTicket(Long id) {

        Optional<Ticket> ticket = ticketRepository.findById(id);

        if(ticket.isPresent()){
            ticketRepository.delete(ticket.get());
        }else{
            throw new RuntimeException("Ticket not found");
        }

    }

    @Override
    public DtoTicket getTicketById(Long id) {

        Optional<Ticket> ticket = ticketRepository.findById(id);

        if(ticket.isPresent()){
            return dtoTicketService.responseTicket(ticket.get());
        }else{
            throw new RuntimeException("Ticket not found");
        }

    }

}
