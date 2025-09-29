package com.ramonmoncao.tasks_api.templates.findAllTasks;

import com.ramonmoncao.tasks_api.port.dtos.returnTask.TaskResponseDTO;
import com.ramonmoncao.tasks_api.utils.DataUtils;

import java.util.List;
import java.util.UUID;

public class FindAllTaskResponseTemplate {

    public static List<TaskResponseDTO> getAllTaskResponse(){
        return List.of(
                TaskResponseDTO.builder()
                        .id(UUID.randomUUID())
                        .title("Task")
                        .done(false)
                        .createdAt(DataUtils.getFormatterDateTimeNow())
                        .build());
    }
}
