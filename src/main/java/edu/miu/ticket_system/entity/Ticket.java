package edu.miu.ticket_system.entity;

import edu.miu.ticket_system.enums.Priority;
import edu.miu.ticket_system.enums.TicketStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Ticket {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer id;
    private String apartmentNumber;
    private String title;
    private String description;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    @Enumerated(EnumType.STRING)
    private Priority priority;
    @Enumerated(EnumType.STRING)
    private TicketStatus ticketStatus;
    private String resolutionDetails;
    private LocalDateTime completedAt;
    private LocalDateTime approvedAt;

    @ManyToOne
    private Resident resident;

}
