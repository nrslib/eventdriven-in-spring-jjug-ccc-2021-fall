package com.example.ticket.app.domain.ticket;

import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TicketRepository {
    List<Ticket> findAll();
    Optional<Ticket> find(TicketId id);
    void save(Ticket ticket);
}
