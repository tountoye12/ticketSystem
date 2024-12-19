package edu.miu.ticket_system.controller;

import edu.miu.ticket_system.entity.Ticket;
import edu.miu.ticket_system.service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/tickets")
@CrossOrigin
public class TicketController {

    @Autowired
    private TicketService ticketService;

    @GetMapping
    public List<Ticket> getAllTickets(){
        return ticketService.getAllTickets();
    }

    @PostMapping
    public ResponseEntity<Ticket> createTicket(@RequestBody Ticket ticket){
        Ticket savedTicket = ticketService.saveTicket(ticket);
        return ResponseEntity.ok(savedTicket);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<String> deleteTicket(@PathVariable Integer id){
        ticketService.delete(id);
        return ResponseEntity.ok("Ticket deleted successfully");

    }

}
