package com.systems.ticketing.ticket.repository;

import com.systems.ticketing.ticket.Status;
import com.systems.ticketing.ticket.entity.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface TicketRepository extends JpaRepository<Ticket,Long> {
     Ticket findByTicketNumber(Long ticketnum);

    Optional<Ticket> findById(Long ticketid);

    List<Ticket> findAllByStatus(Status status);
}

