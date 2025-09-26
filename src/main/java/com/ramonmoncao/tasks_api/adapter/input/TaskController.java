package com.ramonmoncao.tasks_api.adapter.input;

import com.ramonmoncao.tasks_api.adapter.dtos.createTask.CreateTaskRequestDTO;
import com.ramonmoncao.tasks_api.adapter.dtos.createTask.CreateTaskResponseDTO;
import com.ramonmoncao.tasks_api.adapter.dtos.returnTask.TaskResponseDTO;
import com.ramonmoncao.tasks_api.adapter.dtos.updateState.UpdateStateResponseDTO;
import com.ramonmoncao.tasks_api.domain.mapper.TaskAdapterMapper;
import com.ramonmoncao.tasks_api.port.input.TaskService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/tasks")
public class TaskController {
    private final TaskAdapterMapper taskAdapterMapper;
    private final TaskService taskService;

    public TaskController(TaskService taskService, TaskAdapterMapper taskAdapterMapper){
        this.taskService = taskService;
        this.taskAdapterMapper = taskAdapterMapper;
    }

    @GetMapping
    public ResponseEntity<List<TaskResponseDTO>> listAll(){
        List<TaskResponseDTO> list = taskService.findAll()
                .stream().map(taskAdapterMapper::responseToAdapterDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(list);
    }

    @PostMapping
    public ResponseEntity<CreateTaskResponseDTO> createTask(@RequestBody CreateTaskRequestDTO createTaskRequestDTO){
         CreateTaskResponseDTO response = taskAdapterMapper
                 .createResponseToAdapterDTO(
                         taskService.createTask(
                            taskAdapterMapper
                                    .createRequestToPortDTO(createTaskRequestDTO)));
         return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTask(@PathVariable UUID id){
        taskService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/toggle/{id}")
    public ResponseEntity<UpdateStateResponseDTO> updateTaskState(@PathVariable UUID id){
        UpdateStateResponseDTO response = taskAdapterMapper.updateStateToAdapterDTO(
                taskService.updateState(id));

        return ResponseEntity.ok(response);
    }

}
