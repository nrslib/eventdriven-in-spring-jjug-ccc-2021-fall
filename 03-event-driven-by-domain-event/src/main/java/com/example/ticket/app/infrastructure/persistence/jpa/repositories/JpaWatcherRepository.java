package com.example.ticket.app.infrastructure.persistence.jpa.repositories;

import com.example.ticket.app.domain.ticket.TicketId;
import com.example.ticket.app.domain.watcher.EmailAddress;
import com.example.ticket.app.domain.watcher.Watcher;
import com.example.ticket.app.domain.watcher.WatcherId;
import com.example.ticket.app.domain.watcher.WatcherRepository;
import com.example.ticket.app.infrastructure.persistence.jpa.datamodel.watch.WatchDataModel;
import com.example.ticket.app.infrastructure.persistence.jpa.datamodel.watch.WatchJpaRepository;
import com.example.ticket.app.infrastructure.persistence.jpa.datamodel.watcher.WatcherDataModel;
import com.example.ticket.app.infrastructure.persistence.jpa.datamodel.watcher.WatcherJpaRepository;
import com.example.ticket.appsupportstack.domain.ValueObject;
import com.example.ticket.appsupportstack.infrastructure.SimpleRepository;
import org.springframework.context.ApplicationEventPublisher;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class JpaWatcherRepository extends SimpleRepository<Watcher> implements WatcherRepository {
    private final WatcherJpaRepository jpaRepository;
    private final WatchJpaRepository watchJpaRepository;

    public JpaWatcherRepository(ApplicationEventPublisher applicationEventPublisher, WatcherJpaRepository jpaRepository, WatchJpaRepository watchJpaRepository) {
        super(applicationEventPublisher);

        this.jpaRepository = jpaRepository;
        this.watchJpaRepository = watchJpaRepository;
    }

    @Override
    protected void onSave(Watcher watcher) {
        var dataModel = convert(watcher);
        jpaRepository.save(dataModel);
    }

    @Override
    public List<Watcher> findAll() {
        return jpaRepository.findAll().stream().map(this::convert).collect(Collectors.toList());
    }

    @Override
    public Optional<Watcher> find(WatcherId id) {
        return jpaRepository.findById(id.getValue()).map(this::convert);
    }

    @Override
    public List<Watcher> find(List<WatcherId> watcherIds) {
        var watchers = jpaRepository.findByWatchers(watcherIds.stream().map(ValueObject::getValue).collect(Collectors.toList()));

        return watchers.stream().map(this::convert).collect(Collectors.toList());
    }

    @Override
    public List<Watcher> find(TicketId ticketId) {
        var watches = watchJpaRepository.findByTicketId(ticketId.getValue());
        var watchers = jpaRepository.findByWatchers(watches.stream().map(WatchDataModel::getWatcherId).collect(Collectors.toList()));

        return watchers.stream().map(this::convert).collect(Collectors.toList());
    }

    private WatcherDataModel convert(Watcher watcher) {
        return new WatcherDataModel(
                watcher.getId().getValue(),
                watcher.getEmail().getValue());
    }

    private Watcher convert(WatcherDataModel dataModel) {
        return new Watcher(
                new WatcherId(dataModel.getId()),
                new EmailAddress(dataModel.getEmail()));
    }
}
