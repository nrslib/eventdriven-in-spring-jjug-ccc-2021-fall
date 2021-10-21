package com.example.ticket.app.application.watcher;

import com.example.ticket.app.domain.watcher.EmailAddress;
import com.example.ticket.app.domain.watcher.Watcher;
import com.example.ticket.app.domain.watcher.WatcherId;
import com.example.ticket.app.domain.watcher.WatcherRepository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

public class WatcherApplicationService {
    private final WatcherRepository watcherRepository;

    public WatcherApplicationService(WatcherRepository watcherRepository) {
        this.watcherRepository = watcherRepository;
    }

    public List<Watcher> getAll() {
        return watcherRepository.findAll();
    }

    @Transactional
    public void register(String rawMailAddress) {
        Objects.requireNonNull(rawMailAddress);

        var watcher = new Watcher(
                new WatcherId(UUID.randomUUID().toString()),
                new EmailAddress(rawMailAddress));
        watcherRepository.save(watcher);
    }
}
