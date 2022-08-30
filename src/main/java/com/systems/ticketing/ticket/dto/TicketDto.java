package com.systems.ticketing.ticket.dto;

import com.systems.ticketing.ticket.Status;
import lombok.Data;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.time.LocalDateTime;
@Data
public class TicketDto {
    private Long ticketNumber;
    @Enumerated(EnumType.STRING)
    private Status status;
    private String customerName;
    private String priority;
    private String assignee;
    private String description;
    private LocalDateTime dateCreated;
    private LocalDateTime dateClosed;
    private String receipientEmail;

}
