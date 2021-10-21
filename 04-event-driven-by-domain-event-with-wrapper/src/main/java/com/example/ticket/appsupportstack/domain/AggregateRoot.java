package com.example.ticket.appsupportstack.domain;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public abstract class AggregateRoot {
    private List<Object> domainEvents = new ArrayList<>();

    public Collection<Object> getDomainEvents() {
        return Collections.unmodifiableList(domainEvents);
    }

    public void registerEvent(Object event) {
        this.domainEvents.add(event);
    }

    public void clearDomainEvents() {
        this.domainEvents.clear();
    }
}
