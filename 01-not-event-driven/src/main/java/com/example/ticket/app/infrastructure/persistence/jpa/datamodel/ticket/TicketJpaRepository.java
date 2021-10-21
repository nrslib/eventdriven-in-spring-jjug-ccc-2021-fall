package com.example.ticket.app.infrastructure.persistence.jpa.datamodel.ticket;

import org.springframework.data.jpa.repository.JpaRepository;

public interface TicketJpaRepository extends JpaRepository<TicketDataModel, String> {
}
