package com.example.ticket.app.infrastructure.persistence.jpa.repositories;

import com.example.ticket.app.domain.ticket.TicketId;
import com.example.ticket.app.domain.watch.Watch;
import com.example.ticket.app.domain.watch.WatchRepository;
import com.example.ticket.app.domain.watcher.WatcherId;
import com.example.ticket.app.infrastructure.persistence.jpa.datamodel.watch.WatchDataModel;
import com.example.ticket.app.infrastructure.persistence.jpa.datamodel.watch.WatchJpaRepository;
import com.example.ticket.appsupportstack.infrastructure.SimpleRepository;
import org.springframework.context.ApplicationEventPublisher;

import java.util.List;
import java.util.stream.Collectors;

public class JpaWatchRepository extends SimpleRepository<Watch> implements WatchRepository {

    private final WatchJpaRepository jpaRepository;

    public JpaWatchRepository(ApplicationEventPublisher applicationEventPublisher, WatchJpaRepository jpaRepository) {
        super(applicationEventPublisher);

        this.jpaRepository = jpaRepository;
    }

    @Override
    protected void onSave(Watch watch) {
        var dataModel = convert(watch);
        jpaRepository.save(dataModel);
    }

    @Override
    public List<Watch> find(TicketId ticketId) {
        var watches = jpaRepository.findByTicketId(ticketId.getValue());
        return watches.stream().map(this::convert).collect(Collectors.toList());
    }

    private WatchDataModel convert(Watch watch) {
        return new WatchDataModel(
                watch.getTicketId().getValue(),
                watch.getWatcherId().getValue()
        );
    }

    private Watch convert(WatchDataModel dataModel) {
        return new Watch(
                new TicketId(dataModel.getTicketId()),
                new WatcherId(dataModel.getWatcherId())
        );
    }
}
