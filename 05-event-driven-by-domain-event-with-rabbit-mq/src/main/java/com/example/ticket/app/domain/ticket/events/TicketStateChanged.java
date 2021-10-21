package com.example.ticket.app.domain.ticket.events;

import com.example.ticket.app.domain.ticket.TicketState;
import lombok.Data;

import java.io.Serializable;

@Data
public class TicketStateChanged implements Serializable {
    private String ticketId;
    private TicketState newState;
    private String name;

    public TicketStateChanged() {}

    public TicketStateChanged(String ticketId, TicketState newState, String name) {
        this.ticketId = ticketId;
        this.newState = newState;
        this.name = name;
    }
}
