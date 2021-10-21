package com.example.ticket.webapp.handlers.ticket;

import com.example.ticket.app.domain.ticket.events.TicketStateChanged;
import com.example.ticket.webapp.producer.Producer;
import org.springframework.transaction.event.TransactionalEventListener;

public class TicketEventHandler {
    private final Producer producer;

    public TicketEventHandler(Producer producer) {
        this.producer = producer;
    }

    @TransactionalEventListener
    public void handle(TicketStateChanged e) {
        producer.produceMessage(e);
    }
}
