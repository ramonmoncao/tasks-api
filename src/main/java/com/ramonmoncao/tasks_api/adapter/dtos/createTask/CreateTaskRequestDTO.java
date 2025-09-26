package com.ramonmoncao.tasks_api.adapter.dtos.createTask;

import lombok.*;

import java.time.LocalDateTime;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class CreateTaskRequestDTO {
    @NonNull
    private String title;
}
