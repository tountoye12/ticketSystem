package edu.miu.ticket_system.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Resident extends User {
    private String apartmentNumber;
    private LocalDate leaseStartDate;
    private LocalDate leaseEndDate;

    @OneToMany(mappedBy = "resident")
    @JsonBackReference // Add this to handle the relationship
    private List<Ticket> tickets;
}
