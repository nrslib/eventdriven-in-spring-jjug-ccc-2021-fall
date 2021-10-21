package com.example.ticket.app.domain.ticket;

import com.example.ticket.appsupportstack.domain.AggregateRoot;
import lombok.Getter;

import java.util.Objects;

@Getter
public class Ticket extends AggregateRoot {
    private final TicketId id;
    private String name;
    private TicketState state;

    public Ticket(TicketId id, String name, TicketState state) {
        Objects.requireNonNull(id);
        Objects.requireNonNull(name);

        this.id = id;
        this.name = name;
        this.state = state;
    }

    public void changeState(TicketState state) {
        Objects.requireNonNull(state);

        this.state = state;
    }

    public void close() {
        this.state = TicketState.CLOSED;
    }
}
