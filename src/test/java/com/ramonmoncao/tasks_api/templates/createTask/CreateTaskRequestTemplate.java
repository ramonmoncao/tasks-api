package com.ramonmoncao.tasks_api.templates.createTask;

import com.ramonmoncao.tasks_api.port.dtos.createTask.CreateTaskRequestDTO;

public class CreateTaskRequestTemplate {

    public static CreateTaskRequestDTO getCreateTaskRequest() {
        return new CreateTaskRequestDTO("Nova Task");
    }
}

