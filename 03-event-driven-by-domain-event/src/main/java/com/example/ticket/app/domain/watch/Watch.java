package com.example.ticket.app.domain.watch;

import com.example.ticket.app.domain.ticket.TicketId;
import com.example.ticket.app.domain.watcher.WatcherId;
import lombok.Getter;
import org.springframework.data.domain.AbstractAggregateRoot;

@Getter
public class Watch extends AbstractAggregateRoot<Watch> {
    private final TicketId ticketId;
    private final WatcherId watcherId;

    public Watch(TicketId ticketId, WatcherId watcherId) {
        this.ticketId = ticketId;
        this.watcherId = watcherId;
    }
}
