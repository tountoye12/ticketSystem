package edu.miu.ticket_system.entity;

import edu.miu.ticket_system.enums.Priority;
import edu.miu.ticket_system.enums.TicketStatus;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
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
    private Integer id;
    private String apartmentNumber;
    private String title;
    private String description;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private Priority priority;
    private TicketStatus ticketStatus;
    private String resolutionDetails;
    private LocalDateTime completedAt;
    private LocalDateTime approvedAt;


}
