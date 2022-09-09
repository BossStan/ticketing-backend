package com.systems.ticketing.ticket.controller;

import com.systems.ticketing.ticket.Status;
import com.systems.ticketing.ticket.dto.TicketDto;
import com.systems.ticketing.ticket.entity.Ticket;
import com.systems.ticketing.ticket.service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "ticket/api/v1")
public class TicketController {
    @Autowired
    private TicketService  ticketService;

    @GetMapping("/tickets")
    public List<Ticket>getalltickets(){
        return ticketService.getAllTickets();
    }

    @GetMapping("viewTicket")
    public TicketDto viewTicketByNumber(@RequestParam Long ticketnum){
        return ticketService.getTicketById(ticketnum);
    }

    @PostMapping("addticket")
    public String addNewTicket(@RequestBody TicketDto ticketDto){
        return ticketService.saveticket(ticketDto);
    }

    @PutMapping("updateTicket/{id}")

        public String editCustomer(@PathVariable("id") Long ticketnum, @RequestBody TicketDto ticketDto){

        return ticketService.editTicket(ticketnum,ticketDto);

    }
    @GetMapping("viewTicketByStatus")
    public List<Ticket> viewTicketByStatus(@RequestParam ("status")String status){
        return ticketService.getTicketsByStatus(Status.valueOf(status));
    }

}
