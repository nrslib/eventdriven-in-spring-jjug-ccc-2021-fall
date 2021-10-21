package com.example.ticket.webapp.producer;

import org.springframework.amqp.core.AmqpTemplate;

public class Producer {
    private final AmqpTemplate template;
    private final String exchange;
    private final String routingKey;

    public Producer(AmqpTemplate template, String exchange, String routingKey) {
        this.template = template;
        this.exchange = exchange;
        this.routingKey = routingKey;
    }

    public void produceMessage(Object obj) {
        template.convertAndSend(exchange, routingKey, obj);
    }
}
