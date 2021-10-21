package com.example.ticket.app.domain.ticket;

import com.example.ticket.app.domain.ticket.events.TicketStateChanged;
import lombok.Getter;
import org.springframework.data.domain.AbstractAggregateRoot;

import java.util.Objects;

@Getter
public class Ticket extends AbstractAggregateRoot<Ticket> {
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

        registerEvent(new TicketStateChanged(id, state, name));
    }

    public void close() {
        this.state = TicketState.CLOSED;
    }
}
