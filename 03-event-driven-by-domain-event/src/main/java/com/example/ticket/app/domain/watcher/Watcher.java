package com.example.ticket.app.domain.watcher;

import com.example.ticket.app.domain.ticket.Ticket;
import com.example.ticket.app.domain.watch.Watch;
import lombok.Getter;
import org.springframework.data.domain.AbstractAggregateRoot;

@Getter
public class Watcher extends AbstractAggregateRoot<Watcher> {
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
