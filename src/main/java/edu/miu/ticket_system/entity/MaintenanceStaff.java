package edu.miu.ticket_system.entity;

import edu.miu.ticket_system.enums.Specialization;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;

import java.util.List;

@Entity

public class MaintenanceStaff extends User{

    private Specialization specialization;
    @OneToMany
    private List<Ticket>  assignedTicket;

    public MaintenanceStaff(String firstName, String lastName, String userName, String email, String password, Specialization specialization) {
        super(firstName, lastName, userName, email, password);
        this.specialization = specialization;
    }

    public MaintenanceStaff() {

    }

    public Specialization getSpecialization() {
        return specialization;
    }

    public void setSpecialization(Specialization specialization) {
        this.specialization = specialization;
    }

    public List<Ticket> getAssignedTicket() {
        return assignedTicket;
    }

    public void setAssignedTicket(List<Ticket> assignedTicket) {
        this.assignedTicket = assignedTicket;
    }
}
