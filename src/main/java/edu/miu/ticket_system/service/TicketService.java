package edu.miu.ticket_system.service;

import edu.miu.ticket_system.entity.Ticket;
import edu.miu.ticket_system.enums.Priority;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;


public interface TicketService {
    List<Ticket> getAllTickets();

    Ticket saveTicket(Ticket ticket);

    Ticket getTicketById(Integer id);

    List<Ticket> getTicketsByPriority(Priority priority);

    void delete(Integer id);
}
