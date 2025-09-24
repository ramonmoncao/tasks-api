package com.ramonmoncao.tasks_api.domain.exception;

public class CreateTaskException extends RuntimeException {
    public CreateTaskException(String message) {
        super(message);
    }
}
