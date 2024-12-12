package edu.miu.ticket_system.repository;

import edu.miu.ticket_system.entity.Ticket;
import edu.miu.ticket_system.enums.Priority;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TicketRepository extends JpaRepository<Ticket, Integer> {
    Ticket getTicketById(Integer id);

    List<Ticket> getTicketsByPriority(Priority priority);
}
