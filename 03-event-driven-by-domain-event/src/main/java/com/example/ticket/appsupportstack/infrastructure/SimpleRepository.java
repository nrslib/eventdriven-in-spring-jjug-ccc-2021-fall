package com.example.ticket.appsupportstack.infrastructure;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.data.domain.AbstractAggregateRoot;
import org.springframework.data.domain.AfterDomainEventPublication;
import org.springframework.data.domain.DomainEvents;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public abstract class SimpleRepository<TEntity extends AbstractAggregateRoot> {
    private final ApplicationEventPublisher applicationEventPublisher;
    private List<Method> domainEventsMethods;
    private List<Method> afterDomainEventPublicationMethods;

    protected SimpleRepository(ApplicationEventPublisher applicationEventPublisher) {
        this.applicationEventPublisher = applicationEventPublisher;
    }

    public void save(TEntity entity) {
        onSave(entity);

        var domainEventsMethods = getDomainEventsAnnotatedMethods(entity);
        domainEventsMethods.forEach(x -> {
            try {
                x.setAccessible(true);
                var res = (Collection<Object>)x.invoke(entity);
                res.forEach(applicationEventPublisher::publishEvent);
            } catch (IllegalAccessException | InvocationTargetException e) {
                e.printStackTrace();
            }
        });
        var afterDomainEventPublicationMethods = getAfterDomainEventPublicationAnnotatedMethods(entity);
        afterDomainEventPublicationMethods.forEach(x -> {
            try {
                x.setAccessible(true);
                x.invoke(entity);
            } catch (IllegalAccessException | InvocationTargetException e) {
                e.printStackTrace();
            }
        });
    }

    protected abstract void onSave(TEntity entity);

    private List<Method> getDomainEventsAnnotatedMethods(TEntity entity) {
        if (domainEventsMethods != null) {
            return domainEventsMethods;
        }

        domainEventsMethods = getAnnotatedMethodsFromSuperClass(entity, DomainEvents.class).collect(Collectors.toList());

        return domainEventsMethods;
    }

    private List<Method> getAfterDomainEventPublicationAnnotatedMethods(TEntity entity) {
        if (afterDomainEventPublicationMethods != null) {
            return afterDomainEventPublicationMethods;
        }

        afterDomainEventPublicationMethods = getAnnotatedMethodsFromSuperClass(entity, AfterDomainEventPublication.class).collect(Collectors.toList());

        return afterDomainEventPublicationMethods;
    }

    private Stream<Method> getAnnotatedMethodsFromSuperClass(Object object, Class annotationClazz) {
        var superClazz = object.getClass().getSuperclass();
        return Arrays.stream(superClazz.getDeclaredMethods()).filter(x -> x.isAnnotationPresent(annotationClazz));
    }
}
