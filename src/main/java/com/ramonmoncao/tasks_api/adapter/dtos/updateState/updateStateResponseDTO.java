package com.ramonmoncao.tasks_api.adapter.dtos.updateState;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class updateStateResponseDTO {
    private UUID id;
    private String title;
    private boolean done;
    private LocalDateTime createdAt;
}
