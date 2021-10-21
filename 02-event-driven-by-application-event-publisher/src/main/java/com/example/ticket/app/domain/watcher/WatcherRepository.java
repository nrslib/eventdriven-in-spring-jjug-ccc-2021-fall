package com.example.ticket.app.domain.watcher;

import com.example.ticket.app.domain.ticket.TicketId;

import java.util.List;
import java.util.Optional;

public interface WatcherRepository {
    void save(Watcher watcher);
    List<Watcher> findAll();
    Optional<Watcher> find(WatcherId id);
    List<Watcher> find(List<WatcherId> watcherIds);
    List<Watcher> find(TicketId ticketId);
}
