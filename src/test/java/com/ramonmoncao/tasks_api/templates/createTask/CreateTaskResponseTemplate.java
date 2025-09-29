package com.ramonmoncao.tasks_api.templates.createTask;

import com.ramonmoncao.tasks_api.port.dtos.createTask.CreateTaskResponseDTO;
import com.ramonmoncao.tasks_api.utils.DataUtils;

import javax.xml.crypto.Data;
import java.util.UUID;

public class CreateTaskResponseTemplate {
    public static CreateTaskResponseDTO getCreateTaskResponse() {
        return CreateTaskResponseDTO.builder()
                .id(UUID.randomUUID())
                .title("Nova Task")
                .done(false)
                .createdAt(DataUtils.getFormatterDateTimeNow())
                .build();
    }
}
