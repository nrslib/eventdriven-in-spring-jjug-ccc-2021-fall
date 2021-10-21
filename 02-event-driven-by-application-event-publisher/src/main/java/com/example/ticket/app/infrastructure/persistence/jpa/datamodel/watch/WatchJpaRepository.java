package com.example.ticket.app.infrastructure.persistence.jpa.datamodel.watch;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface WatchJpaRepository extends JpaRepository<WatchDataModel, WatchKey> {
    @Query(value = "SELECT w FROM WatchDataModel w where w.ticketId = ?1")
    List<WatchDataModel> findByTicketId(String ticketId);
}
