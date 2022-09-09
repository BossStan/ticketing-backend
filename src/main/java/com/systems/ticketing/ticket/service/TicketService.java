package com.systems.ticketing.ticket.service;

import com.systems.ticketing.email.EmailService;
import com.systems.ticketing.ticket.Status;
import com.systems.ticketing.ticket.dto.TicketDto;
import com.systems.ticketing.ticket.entity.Ticket;
import com.systems.ticketing.ticket.repository.TicketRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class TicketService {
    @Autowired
    private EmailService emailService;
    @Autowired
    TicketRepository ticketRepository;


    public TicketDto getTicketById(Long ticketnum) {
        TicketDto dto = new TicketDto();
        Optional<Ticket> checkticket = ticketRepository.findById(ticketnum);
        Ticket ticket = ticketRepository.findByTicketNumber(ticketnum);
        if (checkticket.isEmpty()) {
            log.error("no such ticket : {} ", ticketnum);
            throw new RuntimeException("no such ticket");

        }
        dto.setTicketNumber(ticket.getTicketNumber());
        dto.setAssignee(ticket.getAssignee());
        dto.setCustomerName(ticket.getCustomerName());
        dto.setDescription(ticket.getDescription());
        dto.setStatus(ticket.getStatus());
        dto.setPriority(ticket.getPriority());
        dto.setDateCreated(ticket.getDateCreated());
        dto.setDateClosed(ticket.getDateClosed());
        log.trace("object redu : {}", dto);
        return dto;
    }


    public String saveticket(TicketDto ticketDto) {

        Ticket ticket = new Ticket();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
         LocalDateTime dateTime = LocalDateTime.now();

        ticket.setAssignee(ticketDto.getAssignee());
        ticket.setCustomerName(ticketDto.getCustomerName());
        ticket.setDescription(ticketDto.getDescription());
        ticket.setPriority(ticketDto.getPriority());
        ticket.setReceipientEmail(ticketDto.getReceipientEmail());
        ticket.setDateCreated(dateTime);
        ticket.setClosed(false);
        ticketRepository.save(ticket);

        boolean ticketExist = ticketRepository.existsById(ticket.getTicketNumber());

        if (ticketExist){
            String savedticketnum = String.valueOf(ticket.getTicketNumber().toString());
            log.info(savedticketnum);
            String link = "http://localhost:8001/viewTicket";
            String emailmessage = "Your query has been logged on and ticket number is "+ savedticketnum+" Use this link " + link + " to track your ticket";

        emailService.sendTicket(ticketDto.getReceipientEmail(), emailmessage);

        log.info("saving ticket : {}", ticket);
    }
        return "ticket saved successfully";
    }


    public String editTicket(Long ticketnum,TicketDto dto) throws IllegalArgumentException{
        LocalDateTime dateclosed = LocalDateTime.now();
        boolean ticketPresent = ticketRepository.existsById(ticketnum);
        if(ticketPresent){
            Ticket ticketUpdate=ticketRepository.findByTicketNumber(ticketnum);
            ticketUpdate.setTicketNumber(ticketnum);
            ticketUpdate.setDescription(dto.getDescription());
            ticketUpdate.setStatus(dto.getStatus());
            String message = "Dear Customer , your ticket "+ ticketUpdate.getTicketNumber()+" has been closed";
            if (dto.getStatus().toString().equals(Status.CLOSED.toString())){
                log.info("testing if riri open");
                ticketUpdate.setClosed(true);
                ticketUpdate.setDateClosed(dateclosed);
                emailService.sendClosedTicket( message, ticketUpdate.getReceipientEmail());
            }
            ticketRepository.save(ticketUpdate);

          return "ticket updated successfully";
        }else {
            throw new NullPointerException("Ticket not found");
        }


    }

    public List<Ticket> getAllTickets() {
        return ticketRepository.findAll();
    }

    public List<Ticket> getTicketsByStatus(Status status) {

        return ticketRepository.findAllByStatus(status);
    }
}
