package com.example.ticket.webapp.models.ticket.put;

import com.example.ticket.app.domain.ticket.TicketState;
import lombok.Data;

@Data
public class TicketPutRequestModel {
    private TicketState state;
}
