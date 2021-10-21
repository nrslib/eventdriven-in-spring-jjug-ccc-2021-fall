package com.example.ticket.appsupportstack.domain;

import org.springframework.data.domain.AbstractAggregateRoot;

import java.util.Collection;

public abstract class AggregateRoot<T> extends AbstractAggregateRoot<AggregateRoot<T>> {
    public Collection<Object> domainEvents() {
        return super.domainEvents();
    }

    public void clearDomainEvents() {
        super.clearDomainEvents();
    }
}
