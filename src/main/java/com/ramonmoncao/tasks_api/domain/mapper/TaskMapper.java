package com.ramonmoncao.tasks_api.domain.mapper;

import com.ramonmoncao.tasks_api.port.dtos.createTask.CreateTaskResponseDTO;
import com.ramonmoncao.tasks_api.port.dtos.returnTask.TaskResponseDTO;
import com.ramonmoncao.tasks_api.port.dtos.updateState.UpdateStateResponseDTO;
import com.ramonmoncao.tasks_api.domain.model.Task;
import com.ramonmoncao.tasks_api.port.dtos.createTask.CreateTaskRequestDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TaskMapper {
    TaskResponseDTO toResponseDTO(Task task);
    Task toEntity(CreateTaskRequestDTO taskDTO);
    UpdateStateResponseDTO toUpdateStateDTO(Task task);
    CreateTaskResponseDTO toCreateResponseDTO(Task task);
}
