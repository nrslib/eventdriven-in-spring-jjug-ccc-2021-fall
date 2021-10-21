package com.example.ticket.webapp.configs;

import com.example.ticket.app.domain.ticket.TicketRepository;
import com.example.ticket.app.domain.watch.WatchRepository;
import com.example.ticket.app.domain.watcher.WatcherRepository;
import com.example.ticket.app.infrastructure.persistence.jpa.datamodel.ticket.TicketJpaRepository;
import com.example.ticket.app.infrastructure.persistence.jpa.datamodel.watch.WatchJpaRepository;
import com.example.ticket.app.infrastructure.persistence.jpa.datamodel.watcher.WatcherJpaRepository;
import com.example.ticket.app.infrastructure.persistence.jpa.repositories.JpaTicketRepository;
import com.example.ticket.app.infrastructure.persistence.jpa.repositories.JpaWatchRepository;
import com.example.ticket.app.infrastructure.persistence.jpa.repositories.JpaWatcherRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@Profile("debug")
@EnableJpaRepositories("com.example.ticket.app.infrastructure.persistence")
public class JpaInfrastructureConfig {
    @Bean
    public TicketRepository ticketRepository(TicketJpaRepository ticketJpaRepository) {
        return new JpaTicketRepository(ticketJpaRepository);
    }

    @Bean
    public WatcherRepository watcherRepository(WatcherJpaRepository watcherJpaRepository, WatchJpaRepository watchJpaRepository) {
        return new JpaWatcherRepository(watcherJpaRepository, watchJpaRepository);
    }

    @Bean
    public WatchRepository watchRepository(WatchJpaRepository watchJpaRepository) {
        return new JpaWatchRepository(watchJpaRepository);
    }
}
