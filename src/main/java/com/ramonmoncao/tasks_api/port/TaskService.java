package com.ramonmoncao.tasks_api.port;

import com.ramonmoncao.tasks_api.port.dtos.createTask.CreateTaskRequestDTO;
import com.ramonmoncao.tasks_api.port.dtos.createTask.CreateTaskResponseDTO;
import com.ramonmoncao.tasks_api.port.dtos.returnTask.TaskResponseDTO;
import com.ramonmoncao.tasks_api.port.dtos.updateState.UpdateStateResponseDTO;

import java.util.List;
import java.util.UUID;

public interface TaskService {
     CreateTaskResponseDTO createTask(CreateTaskRequestDTO createTaskRequestDTO);
     UpdateStateResponseDTO updateState(UUID id);
     List<TaskResponseDTO> findAll();
     void delete(UUID id);
}
