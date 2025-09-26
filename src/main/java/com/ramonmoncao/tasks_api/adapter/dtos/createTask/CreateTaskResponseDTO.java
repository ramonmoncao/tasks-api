package com.ramonmoncao.tasks_api.adapter.dtos.createTask;

import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class CreateTaskResponseDTO {
    private UUID id;
    private String title;
    private boolean done;
    private LocalDateTime createdAt;
}
