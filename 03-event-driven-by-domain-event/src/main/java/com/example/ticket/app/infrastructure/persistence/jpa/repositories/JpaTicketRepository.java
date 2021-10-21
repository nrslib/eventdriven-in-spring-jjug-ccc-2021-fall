package com.example.ticket.app.infrastructure.persistence.jpa.repositories;

import com.example.ticket.app.domain.ticket.Ticket;
import com.example.ticket.app.domain.ticket.TicketId;
import com.example.ticket.app.domain.ticket.TicketRepository;
import com.example.ticket.app.infrastructure.persistence.jpa.datamodel.ticket.TicketDataModel;
import com.example.ticket.app.infrastructure.persistence.jpa.datamodel.ticket.TicketJpaRepository;
import com.example.ticket.appsupportstack.infrastructure.SimpleRepository;
import org.springframework.context.ApplicationEventPublisher;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class JpaTicketRepository extends SimpleRepository<Ticket> implements TicketRepository {
    private final TicketJpaRepository jpaRepository;

    public JpaTicketRepository(ApplicationEventPublisher applicationEventPublisher, TicketJpaRepository jpaRepository) {
        super(applicationEventPublisher);

        this.jpaRepository = jpaRepository;
    }

    @Override
    public List<Ticket> findAll() {
        return jpaRepository.findAll().stream().map(this::convert).collect(Collectors.toList());
    }

    @Override
    public Optional<Ticket> find(TicketId id) {
        var optTicket = jpaRepository.findById(id.getValue());
        return optTicket.map(this::convert);
    }

    @Override
    protected void onSave(Ticket ticket) {
        var dataModel = new TicketDataModel(
                ticket.getId().getValue(),
                ticket.getName(),
                ticket.getState()
        );
        jpaRepository.save(dataModel);
    }

    private Ticket convert(TicketDataModel dataModel) {
        return new Ticket(
                new TicketId(dataModel.getId()),
                dataModel.getName(),
                dataModel.getState()
        );
    }
}
