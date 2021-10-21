package com.example.ticket.webapp.models.ticket.get;

import com.example.ticket.app.domain.ticket.TicketState;
import lombok.Getter;

@Getter
public class TicketGetResponseModel {
    private String id;
    private String name;
    private TicketState state;

    public TicketGetResponseModel() {
    }

    public TicketGetResponseModel(String id, String name, TicketState state) {
        this.id = id;
        this.name = name;
        this.state = state;
    }
}
