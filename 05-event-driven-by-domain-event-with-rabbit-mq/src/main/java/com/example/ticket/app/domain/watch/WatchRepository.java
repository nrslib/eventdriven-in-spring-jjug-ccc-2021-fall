package com.example.ticket.app.domain.watch;

import com.example.ticket.app.domain.ticket.TicketId;

import java.util.List;

public interface WatchRepository {
    void save(Watch watch);
    List<Watch> find(TicketId ticketId);
}
