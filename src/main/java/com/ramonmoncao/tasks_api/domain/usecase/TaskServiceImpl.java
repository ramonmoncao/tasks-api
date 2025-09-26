package com.ramonmoncao.tasks_api.domain.usecase;

import com.ramonmoncao.tasks_api.domain.exception.CreateTaskException;
import com.ramonmoncao.tasks_api.domain.exception.NotFoundException;
import com.ramonmoncao.tasks_api.domain.mapper.TaskMapper;
import com.ramonmoncao.tasks_api.domain.model.Task;
import com.ramonmoncao.tasks_api.port.input.TaskRepository;
import com.ramonmoncao.tasks_api.port.input.TaskService;
import com.ramonmoncao.tasks_api.port.dtos.createTask.CreateTaskRequestDTO;
import com.ramonmoncao.tasks_api.port.dtos.createTask.CreateTaskResponseDTO;
import com.ramonmoncao.tasks_api.port.dtos.returnTask.TaskResponseDTO;
import com.ramonmoncao.tasks_api.port.dtos.updateState.UpdateStateResponseDTO;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class TaskServiceImpl implements TaskService {

    private final TaskRepository taskRepository;

    private final TaskMapper taskMapper;

    public TaskServiceImpl(TaskRepository taskRepository, TaskMapper taskMapper) {
        this.taskRepository = taskRepository;
        this.taskMapper = taskMapper;
    }

    @Override
    public CreateTaskResponseDTO createTask(CreateTaskRequestDTO createTaskRequestDTO) {
        if(createTaskRequestDTO.getTitle()==null || createTaskRequestDTO.getTitle().isBlank())
            throw new CreateTaskException("Título não pode ser vazio.");
        try {
            Task task = taskMapper.toEntity(createTaskRequestDTO);
            task = taskRepository.save(task);
            return taskMapper.toCreateResponseDTO(task);
        } catch (Exception e) {
            throw new CreateTaskException(e.getMessage());
        }
    }

    @Override
    public UpdateStateResponseDTO updateState(UUID id) {
        Task task = taskRepository.findById(id)
                .orElseThrow(()-> new NotFoundException(id));
        task.setDone(!task.isDone());
        taskRepository.save(task);
        return  taskMapper.toUpdateStateDTO(task);
    }

    @Override
    public List<TaskResponseDTO> findAll() {
        List<TaskResponseDTO> list = taskRepository.findAll()
                .stream().map(taskMapper::toResponseDTO)
                .collect(Collectors.toList());
        return list;
    }

    @Override
    public void delete(UUID id) {
        Task task = taskRepository.findById(id)
                .orElseThrow(()->new NotFoundException(id));
        taskRepository.delete(task);
    }
}
