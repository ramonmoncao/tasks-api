package com.ramonmoncao.tasks_api.domain.mapper;

import com.ramonmoncao.tasks_api.adapter.dtos.createTask.CreateTaskResponseDTO;
import com.ramonmoncao.tasks_api.adapter.dtos.returnTask.TaskResponseDTO;
import com.ramonmoncao.tasks_api.adapter.dtos.updateState.UpdateStateResponseDTO;
import com.ramonmoncao.tasks_api.port.dtos.createTask.CreateTaskRequestDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TaskAdapterMapper {

    TaskResponseDTO responseToAdapterDTO(com.ramonmoncao.tasks_api.port.dtos.returnTask.TaskResponseDTO portResponseDTO);
    CreateTaskResponseDTO createResponseToAdapterDTO(com.ramonmoncao.tasks_api.port.dtos.createTask.CreateTaskResponseDTO portCreateDTO);
    CreateTaskRequestDTO createRequestToPortDTO(com.ramonmoncao.tasks_api.adapter.dtos.createTask.CreateTaskRequestDTO adapterCreateDTO);
    UpdateStateResponseDTO updateStateToAdapterDTO(com.ramonmoncao.tasks_api.port.dtos.updateState.UpdateStateResponseDTO portUpdateDTO);

}
