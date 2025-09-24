package com.ramonmoncao.tasks_api.service.dtos.createTask;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CreateTaskRequestDTO {
    private String title;
    private boolean done;
    private LocalDateTime createdAt;
}
