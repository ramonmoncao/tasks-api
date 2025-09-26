package com.ramonmoncao.tasks_api.adapter.exception.handler.response;

import lombok.*;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TaskErrorResponse{
    private HttpStatus status;
    private String error;
    private String message;
    private String timestamp;
}
