package edu.miu.ticket_system.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import edu.miu.ticket_system.entity.Resident;
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
    @JsonManagedReference // Add this to handle the relationship
    private Resident resident;

    public Ticket(String apartmentNumber, String title, String description, LocalDateTime createdAt, LocalDateTime updatedAt, Priority priority) {
        this.apartmentNumber = apartmentNumber;
        this.title = title;
        this.description = description;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.priority = priority;
    }
}
