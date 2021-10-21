package com.example.ticket.webapp.models.ticket.index;

import lombok.Getter;

import java.util.List;

@Getter
public class TicketIndexResponseModel {
    private final List<TicketSummaryModel> summaries;

    public TicketIndexResponseModel(List<TicketSummaryModel> summaries) {
        this.summaries = summaries;
    }
}
