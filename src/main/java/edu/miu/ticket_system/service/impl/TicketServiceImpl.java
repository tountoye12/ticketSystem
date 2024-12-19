package edu.miu.ticket_system.service.impl;

import edu.miu.ticket_system.entity.Ticket;
import edu.miu.ticket_system.enums.Priority;
import edu.miu.ticket_system.enums.TicketStatus;
import edu.miu.ticket_system.repository.TicketRepository;
import edu.miu.ticket_system.service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class TicketServiceImpl implements TicketService {
    @Autowired
    private TicketRepository ticketRepository;

    @Override
    public List<Ticket> getAllTickets() {
        return ticketRepository.findAll();
    }

    @Override
    public Ticket saveTicket(Ticket ticket) {
        ticket.setTicketStatus(TicketStatus.OPEN);
        ticket.setCreatedAt(LocalDateTime.now());
        return ticketRepository.save(ticket);
    }

    @Override
    public Ticket getTicketById(Integer id) {
        return ticketRepository.getTicketById(id);
    }

    @Override
    public List<Ticket> getTicketsByPriority(Priority priority) {
        return ticketRepository.getTicketsByPriority(priority);
    }

    @Override
    public void delete(Integer id) {
        ticketRepository.deleteById(id);
    }

    @Override
    public List<Ticket> getTicketsByStatus(TicketStatus ticketStatus) {
        return ticketRepository.getTicketsByTicketStatus(ticketStatus);
    }
}
