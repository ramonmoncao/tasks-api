package com.ramonmoncao.tasks_api.templates.updateTask;

import com.ramonmoncao.tasks_api.port.dtos.updateState.UpdateStateResponseDTO;
import com.ramonmoncao.tasks_api.utils.DataUtils;

import javax.xml.crypto.Data;
import java.util.UUID;

public class UpdateTaskResponseTemplate {
    public static UpdateStateResponseDTO getUpdateResponse() {
        return UpdateStateResponseDTO.builder()
                .id(UUID.randomUUID())
                .title("Task")
                .done(true)
                .createdAt(DataUtils.getFormatterDateTimeNow())
                .build();
    }
}
