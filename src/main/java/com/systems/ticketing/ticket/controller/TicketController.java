package com.systems.ticketing.ticket.controller;

import com.systems.ticketing.ticket.Status;
import com.systems.ticketing.ticket.dto.TicketDto;
import com.systems.ticketing.ticket.entity.Ticket;
import com.systems.ticketing.ticket.service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "ticket/api/v1")
public class TicketController {
    @Autowired
    private TicketService ticketService;

    @GetMapping("/tickets")
    public ResponseEntity<?> getalltickets() {
        return new ResponseEntity<>(ticketService.getAllTickets(), HttpStatus.OK);
    }

    @GetMapping("viewTicket")
    public ResponseEntity<?> viewTicketByNumber(@RequestParam Long ticketnum) {
        return new ResponseEntity<>(ticketService.getTicketById(ticketnum), HttpStatus.OK);
    }

    @PostMapping("addticket")
    public ResponseEntity<?> addNewTicket(@RequestBody TicketDto ticketDto) {
        return new ResponseEntity<>(ticketService.saveticket(ticketDto), HttpStatus.CREATED);
    }

    @PutMapping("updateTicket/{id}")

    public ResponseEntity<?> editCustomer(@PathVariable("id") Long ticketnum, @RequestBody TicketDto ticketDto) {

        return new ResponseEntity<>(ticketService.editTicket(ticketnum, ticketDto), HttpStatus.OK);

    }

    @GetMapping("viewTicketByStatus")
    public ResponseEntity<?> viewTicketByStatus(@RequestParam("status") String status) {
        return new ResponseEntity<>(ticketService.getTicketsByStatus(Status.valueOf(status)), HttpStatus.OK);
    }

}
