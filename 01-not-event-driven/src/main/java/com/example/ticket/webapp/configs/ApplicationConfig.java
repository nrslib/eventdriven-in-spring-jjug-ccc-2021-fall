package com.example.ticket.webapp.configs;

import com.example.ticket.app.application.ticket.TicketApplicationService;
import com.example.ticket.app.domain.ticket.TicketRepository;
import com.example.ticket.app.domain.watcher.WatcherRepository;
import com.example.ticket.app.infrastructure.api.service.MailService;
import com.example.ticket.app.infrastructure.inmemory.service.NopMailService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("debug")
public class ApplicationConfig {
    @Bean
    public TicketApplicationService ticketApplicationService(TicketRepository repository, WatcherRepository watcherRepository, MailService mailService) {
        return new TicketApplicationService(repository, watcherRepository, mailService);
    }

    @Bean
    public MailService mailService() {
        return new NopMailService();
    }
}
