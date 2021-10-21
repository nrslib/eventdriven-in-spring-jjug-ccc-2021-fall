package com.example.ticket.webapp.controllers;

import com.example.ticket.app.application.ticket.TicketApplicationService;
import com.example.ticket.webapp.models.ticket.get.TicketGetResponseModel;
import com.example.ticket.webapp.models.ticket.index.TicketIndexResponseModel;
import com.example.ticket.webapp.models.ticket.index.TicketSummaryModel;
import com.example.ticket.webapp.models.ticket.post.TicketPostRequestModel;
import com.example.ticket.webapp.models.ticket.put.TicketPutRequestModel;
import org.springframework.web.bind.annotation.*;

import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/ticket")
public class TicketController {
    private final TicketApplicationService ticketApplicationService;

    public TicketController(TicketApplicationService ticketApplicationService) {
        this.ticketApplicationService = ticketApplicationService;
    }

    @GetMapping
    public TicketIndexResponseModel index() {
        var list = ticketApplicationService.getAll()
                .stream()
                .map(x -> new TicketSummaryModel(x.getId().getValue(), x.getName()))
                .collect(Collectors.toList());

        return new TicketIndexResponseModel(list);
    }

    @GetMapping("{id}")
    public TicketGetResponseModel get(@PathVariable String id) {
        var optTicket = ticketApplicationService.find(id);
        if (optTicket.isEmpty()) {
            return new TicketGetResponseModel();
        }

        var ticket = optTicket.get();
        return new TicketGetResponseModel(
                ticket.getId().getValue(),
                ticket.getName(),
                ticket.getState()
        );
    }

    @PostMapping
    public String post(@RequestBody TicketPostRequestModel body) {
        var id = ticketApplicationService.create(body.getName());

        return id;
    }

    @PutMapping("{id}")
    public void post(@PathVariable String id, @RequestBody TicketPutRequestModel body) {
        if (body.getState() != null) {
            ticketApplicationService.changeState(id, body.getState());
        }
    }

    @PutMapping("{id}/close")
    public void postClose(@PathVariable String id) {
        ticketApplicationService.close(id);
    }
}