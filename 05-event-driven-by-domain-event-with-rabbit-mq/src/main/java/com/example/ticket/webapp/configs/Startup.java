package com.example.ticket.webapp.configs;

import com.example.ticket.app.domain.ticket.Ticket;
import com.example.ticket.app.domain.ticket.TicketId;
import com.example.ticket.app.domain.ticket.TicketRepository;
import com.example.ticket.app.domain.ticket.TicketState;
import com.example.ticket.app.domain.watch.Watch;
import com.example.ticket.app.domain.watch.WatchRepository;
import com.example.ticket.app.domain.watcher.EmailAddress;
import com.example.ticket.app.domain.watcher.Watcher;
import com.example.ticket.app.domain.watcher.WatcherId;
import com.example.ticket.app.domain.watcher.WatcherRepository;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class Startup {
    private final TicketRepository ticketRepository;
    private final WatcherRepository watcherRepository;
    private final WatchRepository watchRepository;

    public Startup(TicketRepository ticketRepository, WatcherRepository watcherRepository, WatchRepository watchRepository) {
        this.ticketRepository = ticketRepository;
        this.watcherRepository = watcherRepository;
        this.watchRepository = watchRepository;
    }

    @PostConstruct
    public void startup() {
        var ticketId = new TicketId("test-ticket-id");
        var watcherId = new WatcherId("test-watcher-id");

        ticketRepository.save(
                new Ticket(
                        ticketId,
                        "test-ticket",
                        TicketState.CREATED));

        watcherRepository.save(
                new Watcher(
                        watcherId,
                        new EmailAddress("test-email@example.com")
                )
        );

        watchRepository.save(
                new Watch(
                        ticketId,
                        watcherId
                )
        );
    }
}
