package com.ramonmoncao.tasks_api.port.dtos.updateState;

import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class UpdateStateResponseDTO {
    private UUID id;
    private String title;
    private boolean done;
    private String createdAt;
}
