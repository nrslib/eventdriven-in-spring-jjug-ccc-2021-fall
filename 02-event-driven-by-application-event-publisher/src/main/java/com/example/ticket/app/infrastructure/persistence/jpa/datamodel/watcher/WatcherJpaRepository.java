package com.example.ticket.app.infrastructure.persistence.jpa.datamodel.watcher;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface WatcherJpaRepository extends JpaRepository<WatcherDataModel, String> {
    @Query(value = "SELECT watcher FROM WatcherDataModel watcher WHERE watcher.id IN (:watcherIds)")
    List<WatcherDataModel> findByWatchers(@Param("watcherIds") List<String> watcherIds);
}
