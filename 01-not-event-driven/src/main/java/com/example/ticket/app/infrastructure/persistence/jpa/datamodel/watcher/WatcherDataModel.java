package com.example.ticket.app.infrastructure.persistence.jpa.datamodel.watcher;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Data
@Table(name = "watchers")
public class WatcherDataModel {
    @Id
    private String id;
    private String email;

    public WatcherDataModel(String id, String email) {
        this.id = id;
        this.email = email;
    }

    public WatcherDataModel() {
    }
}
