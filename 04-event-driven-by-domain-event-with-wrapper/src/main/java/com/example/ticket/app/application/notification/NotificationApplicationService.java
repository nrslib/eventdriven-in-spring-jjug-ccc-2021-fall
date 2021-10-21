package com.example.ticket.app.application.notification;

import com.example.ticket.app.domain.ticket.events.TicketStateChanged;
import com.example.ticket.app.domain.watcher.WatcherRepository;
import com.example.ticket.app.infrastructure.api.service.MailService;

public class NotificationApplicationService {
    private final MailService mailService;
    private final WatcherRepository watcherRepository;

    public NotificationApplicationService(MailService mailService, WatcherRepository watcherRepository) {
        this.mailService = mailService;
        this.watcherRepository = watcherRepository;
    }

    public void notify(TicketStateChanged e) {
        var watchers = watcherRepository.find(e.getTicketId());
        var title = "Ticket status changed. (" + e.getName() + ")";
        watchers.forEach(x -> mailService.send(
                x.getEmail().getValue(),
                title,
                "Current status: {0}",
                e.getNewState().toString()));
    }
}
