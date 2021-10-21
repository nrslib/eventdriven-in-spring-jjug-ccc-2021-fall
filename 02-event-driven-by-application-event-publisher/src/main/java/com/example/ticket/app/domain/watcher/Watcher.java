package com.example.ticket.app.domain.watcher;

import com.example.ticket.app.domain.ticket.Ticket;
import com.example.ticket.app.domain.watch.Watch;
import lombok.Getter;

@Getter
public class Watcher {
    private final WatcherId id;
    private EmailAddress email;

    public Watcher(WatcherId id, EmailAddress email) {
        this.id = id;
        this.email = email;
    }

    public Watch watch(Ticket ticket) {
        return new Watch(ticket.getId(), id);
    }
}
