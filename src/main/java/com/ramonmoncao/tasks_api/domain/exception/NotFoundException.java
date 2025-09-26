package com.ramonmoncao.tasks_api.domain.exception;

import java.util.UUID;

public class NotFoundException extends RuntimeException {
    public NotFoundException(String message) {
        super(message);
    }
    public NotFoundException(UUID id) {
        super("Task não encontrada com id: " + id);
    }
}
