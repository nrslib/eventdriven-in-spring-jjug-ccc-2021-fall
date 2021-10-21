package com.example.ticket.app.infrastructure.persistence.jpa.datamodel.watch;

import lombok.Data;

import java.io.Serializable;

@Data
public class WatchKey implements Serializable {
    private String ticketId;
    private String watcherId;
}
