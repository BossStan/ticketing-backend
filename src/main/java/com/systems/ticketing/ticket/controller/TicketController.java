package com.systems.ticketing.ticket.controller;

import com.systems.ticketing.ticket.dto.TicketDto;
import com.systems.ticketing.ticket.service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "ticket/api/v1")
public class TicketController {
    @Autowired
    private TicketService  ticketService;

    @GetMapping("viewTicket")
    public TicketDto viewTicketByNumber(@RequestParam Long ticketnum){
        return ticketService.getTicketById(ticketnum);
    }

    @PostMapping("addticket")
    public String addNewTicket(@RequestBody TicketDto ticketDto){
        return ticketService.saveticket(ticketDto);
    }

}
