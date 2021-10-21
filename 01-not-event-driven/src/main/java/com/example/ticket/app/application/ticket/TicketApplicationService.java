package com.example.ticket.app.application.ticket;

import com.example.ticket.app.domain.ticket.Ticket;
import com.example.ticket.app.domain.ticket.TicketId;
import com.example.ticket.app.domain.ticket.TicketRepository;
import com.example.ticket.app.domain.ticket.TicketState;
import com.example.ticket.app.domain.watcher.WatcherRepository;
import com.example.ticket.app.infrastructure.api.service.MailService;
import com.example.ticket.appsupportstack.application.NotFoundException;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class TicketApplicationService {
    private final TicketRepository ticketRepository;
    private final WatcherRepository watcherRepository;
    private final MailService mailService;

    public TicketApplicationService(TicketRepository ticketRepository, WatcherRepository watcherRepository, MailService mailService) {
        this.ticketRepository = ticketRepository;
        this.watcherRepository = watcherRepository;
        this.mailService = mailService;
    }

    public List<Ticket> getAll() {
        return ticketRepository.findAll();
    }

    public Optional<Ticket> find(String rawId) {
        var id = new TicketId(rawId);
        var ticket = ticketRepository.find(id);

        return ticket;
    }

    @Transactional
    public String create(String name) {
        var rawId = UUID.randomUUID().toString();
        var ticket = new Ticket(
                new TicketId(rawId),
                name,
                TicketState.CREATED
        );
        ticketRepository.save(ticket);

        return rawId;
    }

    @Transactional(isolation = Isolation.SERIALIZABLE)
    public void changeState(String rawTicketId, TicketState newState) {
        var ticketId = new TicketId(rawTicketId);
        var ticket = ticketRepository.find(ticketId)
                .orElseThrow(() -> new NotFoundException("ticketId", ticketId));
        ticket.changeState(newState);
        ticketRepository.save(ticket);

        var watchers = watcherRepository.find(ticket.getId());
        var title = "Ticket status changed. (" + ticket.getName() + ")";
        watchers.forEach(x -> mailService.send(
                x.getEmail().getValue(),
                title,
                "Current status: {0}",
                ticket.getState().toString()));
    }

    @Transactional
    public void close(String rawId) {
        var ticket = getTicket(new TicketId(rawId));
        ticket.close();

        ticketRepository.save(ticket);
    }

    private Ticket getTicket(TicketId id) {
        return ticketRepository.find(id).orElseThrow(() -> new NotFoundException("ticketId", id.getValue()));
    }
}
