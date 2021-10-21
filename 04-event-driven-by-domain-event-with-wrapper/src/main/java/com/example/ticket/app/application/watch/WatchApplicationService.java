package com.example.ticket.app.application.watch;

import com.example.ticket.app.domain.ticket.TicketId;
import com.example.ticket.app.domain.ticket.TicketRepository;
import com.example.ticket.app.domain.watch.WatchRepository;
import com.example.ticket.app.domain.watcher.WatcherId;
import com.example.ticket.app.domain.watcher.WatcherRepository;
import com.example.ticket.appsupportstack.application.NotFoundException;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;

public class WatchApplicationService {
    private final TicketRepository ticketRepository;
    private final WatcherRepository watcherRepository;
    private final WatchRepository watchRepository;

    public WatchApplicationService(TicketRepository ticketRepository, WatcherRepository watcherRepository, WatchRepository watchRepository) {
        this.ticketRepository = ticketRepository;
        this.watcherRepository = watcherRepository;
        this.watchRepository = watchRepository;
    }

    @Transactional
    public void watch(String rawWatcherId, String rawTicketId) {
        Objects.requireNonNull(rawWatcherId);
        Objects.requireNonNull(rawTicketId);

        var watcherId = new WatcherId(rawWatcherId);
        var wacher = watcherRepository.find(watcherId).orElseThrow(() -> new NotFoundException("watcherId", rawWatcherId));

        var ticketId = new TicketId(rawTicketId);
        var ticket = ticketRepository.find(ticketId).orElseThrow(() -> new NotFoundException("ticketId", rawTicketId));

        var watch = wacher.watch(ticket);
        watchRepository.save(watch);
    }
}
