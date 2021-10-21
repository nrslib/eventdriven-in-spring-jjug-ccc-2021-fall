package com.example.ticket.app.infrastructure.persistence.jpa.datamodel.ticket;

import com.example.ticket.app.domain.ticket.TicketState;
import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "tickets")
public class TicketDataModel {
    @Id
    private String id;
    private String name;
    @Enumerated(EnumType.STRING)
    private TicketState state;

    public TicketDataModel(String id, String name, TicketState state) {
        this.id = id;
        this.name = name;
        this.state = state;
    }

    public TicketDataModel() {
    }
}
