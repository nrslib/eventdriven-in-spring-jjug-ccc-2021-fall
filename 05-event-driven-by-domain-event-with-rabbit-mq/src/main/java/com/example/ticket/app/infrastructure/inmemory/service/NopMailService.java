package com.example.ticket.app.infrastructure.inmemory.service;

import com.example.ticket.app.infrastructure.api.service.MailService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.MessageFormat;

public class NopMailService implements MailService {
    private final Logger logger = LoggerFactory.getLogger(NopMailService.class);

    @Override
    public void send(String emailAddress, String title, String template, Object... parameters) {
        var sb = new StringBuilder()
                .append("Email: ")
                .append(emailAddress)
                .append(" | ")
                .append("Title: ")
                .append(title)
                .append(" | ")
                .append("Message: ")
                .append(MessageFormat.format(template, parameters));

        logger.info(sb.toString());
    }
}
