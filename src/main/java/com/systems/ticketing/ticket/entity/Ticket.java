package com.systems.ticketing.ticket.entity;

import com.systems.ticketing.ticket.Status;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Ticket {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
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
    private boolean isClosed;

}
