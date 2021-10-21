package com.example.ticket.appsupportstack.application;

import java.text.MessageFormat;

public class NotFoundException extends RuntimeException {
    public NotFoundException(String idName, Object idObject) {
        super(MessageFormat.format("notfound. ({0}:{1})", idName, idObject.toString()));
    }

    public NotFoundException(String message) {
        super(message);
    }

    public NotFoundException() {
    }
}
