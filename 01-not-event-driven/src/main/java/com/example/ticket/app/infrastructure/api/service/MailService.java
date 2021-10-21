package com.example.ticket.app.infrastructure.api.service;

public interface MailService {
    void send(String emailAddress, String title, String template, Object... parameters);
}
