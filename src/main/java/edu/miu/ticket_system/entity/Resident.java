package edu.miu.ticket_system.entity;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Resident extends User{
    private String apartmentNumber;
    private LocalDate leaseStartDate;
    private LocalDate leaseEndDate;
    private List<Ticket> tickets;
}
