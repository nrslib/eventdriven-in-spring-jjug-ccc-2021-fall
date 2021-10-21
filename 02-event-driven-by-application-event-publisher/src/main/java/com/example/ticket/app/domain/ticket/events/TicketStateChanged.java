package com.example.ticket.app.domain.ticket.events;

import com.example.ticket.app.domain.ticket.TicketId;
import com.example.ticket.app.domain.ticket.TicketState;
import lombok.Getter;

@Getter
public class TicketStateChanged {
    private TicketId ticketId;
    private TicketState newState;
    private String name;

    public TicketStateChanged(TicketId ticketId, TicketState newState, String name) {
        this.ticketId = ticketId;
        this.newState = newState;
        this.name = name;
    }
}
