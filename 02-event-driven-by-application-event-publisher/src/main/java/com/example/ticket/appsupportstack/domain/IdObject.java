package com.example.ticket.appsupportstack.domain;

import lombok.Data;

@Data
public class IdObject {
    private String id;

    public IdObject(String value) {
        this.id = value;
    }
}
