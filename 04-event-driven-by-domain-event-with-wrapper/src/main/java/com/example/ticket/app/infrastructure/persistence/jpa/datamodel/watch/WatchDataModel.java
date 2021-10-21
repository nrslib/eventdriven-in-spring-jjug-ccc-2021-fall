package com.example.ticket.app.infrastructure.persistence.jpa.datamodel.watch;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

@Entity
@Data
@Table(name = "watches")
@IdClass(WatchKey.class)
public class WatchDataModel {
    @Id
    private String ticketId;
    @Id
    private String watcherId;

    public WatchDataModel(String ticketId, String watcherId) {
        this.ticketId = ticketId;
        this.watcherId = watcherId;
    }

    public WatchDataModel() {
    }
}
