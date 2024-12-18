package edu.miu.ticket_system.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Entity

public class Resident extends User{
    private String apartmentNumber;
    private LocalDate leaseStartDate;
    private LocalDate leaseEndDate;
    @OneToMany(mappedBy = "resident")
    private List<Ticket> tickets;

    public Resident() {
    }

    public Resident(String firstName, String lastName, String userName, String email, String password, String apartmentNumber) {
        super(firstName, lastName, userName, email, password);
        this.apartmentNumber = apartmentNumber;
    }

    public String getApartmentNumber() {
        return apartmentNumber;
    }

    public void setApartmentNumber(String apartmentNumber) {
        this.apartmentNumber = apartmentNumber;
    }

    public LocalDate getLeaseStartDate() {
        return leaseStartDate;
    }

    public void setLeaseStartDate(LocalDate leaseStartDate) {
        this.leaseStartDate = leaseStartDate;
    }

    public LocalDate getLeaseEndDate() {
        return leaseEndDate;
    }

    public void setLeaseEndDate(LocalDate leaseEndDate) {
        this.leaseEndDate = leaseEndDate;
    }

    public List<Ticket> getTickets() {
        return tickets;
    }

    public void setTickets(List<Ticket> tickets) {
        this.tickets = tickets;
    }
}
