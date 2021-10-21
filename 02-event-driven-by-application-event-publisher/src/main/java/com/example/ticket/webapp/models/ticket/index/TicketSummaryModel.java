package com.example.ticket.webapp.models.ticket.index;

import lombok.Getter;

@Getter
public class TicketSummaryModel {
    private final String id;
    private final String name;

    public TicketSummaryModel(String id, String name) {
        this.id = id;
        this.name = name;
    }
}
