package com.example.ticket.appsupportstack.infrastructure;

import com.example.ticket.appsupportstack.domain.AggregateRoot;
import org.springframework.context.ApplicationEventPublisher;

public abstract class SimpleRepository<TEntity extends AggregateRoot> {
    private final ApplicationEventPublisher applicationEventPublisher;

    public SimpleRepository(ApplicationEventPublisher applicationEventPublisher) {
        this.applicationEventPublisher = applicationEventPublisher;
    }

    public void save(TEntity entity) {
        onSave(entity);

        entity.getDomainEvents().forEach(applicationEventPublisher::publishEvent);
        entity.clearDomainEvents();
    }

    protected abstract void onSave(TEntity entity);
}
