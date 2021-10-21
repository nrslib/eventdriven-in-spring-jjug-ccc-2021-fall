package com.example.ticket.webapp.configs;

import com.example.ticket.app.application.notification.NotificationApplicationService;
import com.example.ticket.app.application.ticket.TicketApplicationService;
import com.example.ticket.app.domain.ticket.TicketRepository;
import com.example.ticket.app.domain.watcher.WatcherRepository;
import com.example.ticket.app.infrastructure.api.service.MailService;
import com.example.ticket.app.infrastructure.inmemory.service.NopMailService;
import com.example.ticket.webapp.handlers.ticket.TicketEventHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("debug")
public class ApplicationConfig {
    @Bean
    public TicketApplicationService ticketApplicationService(TicketRepository repository) {
        return new TicketApplicationService(repository);
    }

    @Bean
    public NotificationApplicationService notificationApplicationService(MailService mailService, WatcherRepository watcherRepository) {
        return new NotificationApplicationService(mailService, watcherRepository);
    }

    @Bean
    public MailService mailService() {
        return new NopMailService();
    }

    @Bean
    public TicketEventHandler ticketEventHandler(NotificationApplicationService service) {
        return new TicketEventHandler(service);
    }
}
