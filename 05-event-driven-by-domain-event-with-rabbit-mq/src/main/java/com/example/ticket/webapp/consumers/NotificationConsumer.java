package com.example.ticket.webapp.consumers;

import com.example.ticket.app.application.notification.NotificationApplicationService;
import com.example.ticket.app.domain.ticket.events.TicketStateChanged;
import org.springframework.amqp.rabbit.annotation.RabbitListener;

public class NotificationConsumer {
    private final NotificationApplicationService notificationApplicationService;

    public NotificationConsumer(NotificationApplicationService notificationApplicationService) {
        this.notificationApplicationService = notificationApplicationService;
    }

    @RabbitListener(queues = "notificationQ")
    public void receiveMessage(TicketStateChanged e) {
        notificationApplicationService.notify(e);
    }
}
