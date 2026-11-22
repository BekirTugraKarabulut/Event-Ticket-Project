package com.eventticket.manager;

import com.eventticket.dto.DtoTicket;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "TICKET-SERVICE")
public interface TicketClient {

    @GetMapping(path = "/tickets/{id}")
    DtoTicket getTicketById(@PathVariable(name = "id") Long id);

}
