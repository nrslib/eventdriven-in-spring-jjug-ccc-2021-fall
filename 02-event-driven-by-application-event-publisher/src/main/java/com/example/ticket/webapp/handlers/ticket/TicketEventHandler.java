package com.example.ticket.webapp.handlers.ticket;

import com.example.ticket.app.application.notification.NotificationApplicationService;
import com.example.ticket.app.domain.ticket.events.TicketStateChanged;
import org.springframework.transaction.event.TransactionalEventListener;

public class TicketEventHandler {
    private final NotificationApplicationService notificationApplicationService;

    public TicketEventHandler(NotificationApplicationService notificationApplicationService) {
        this.notificationApplicationService = notificationApplicationService;
    }

    @TransactionalEventListener
    public void handle(TicketStateChanged e) {
        notificationApplicationService.notify(e);
    }
}
