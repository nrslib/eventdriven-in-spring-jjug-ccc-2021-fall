package com.example.ticket.appsupportstack.domain;

import lombok.Getter;

import java.util.Objects;

@Getter
public abstract class ValueObject<T> {
    private final T value;

    protected ValueObject(T value) {
        Objects.requireNonNull(value);

        this.value = value;
    }

    @Override
    public String toString() {
        return value.toString();
    }
}
