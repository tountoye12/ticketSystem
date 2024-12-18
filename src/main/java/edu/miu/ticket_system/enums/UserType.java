package edu.miu.ticket_system.enums;

import edu.miu.ticket_system.entity.Ticket;
import jakarta.persistence.OneToMany;

import java.util.List;

public enum UserType {
    LEASE_OFFICE_STAFF, RESIDENT, MaintenanceStaff, ADMINISTRATOR;


    private static class MaintenanceStaff{
        private Specialization specialization;
        private List<Ticket> assignedTicket;

    }


}


