package edu.miu.ticket_system.entity;

import edu.miu.ticket_system.enums.Specialization;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class MaintenanceStaff extends User{

    private Specialization specialization;
    @OneToMany
    private List<Ticket>  assignedTicket;

}
