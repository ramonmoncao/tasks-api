package com.ramonmoncao.tasks_api.domain.usecase;

import com.ramonmoncao.tasks_api.domain.exception.CreateTaskException;
import com.ramonmoncao.tasks_api.domain.exception.NotFoundException;
import com.ramonmoncao.tasks_api.domain.mapper.TaskMapper;
import com.ramonmoncao.tasks_api.domain.model.Task;
import com.ramonmoncao.tasks_api.port.input.TaskRepository;
import com.ramonmoncao.tasks_api.port.dtos.createTask.CreateTaskRequestDTO;
import com.ramonmoncao.tasks_api.port.dtos.createTask.CreateTaskResponseDTO;
import com.ramonmoncao.tasks_api.port.dtos.returnTask.TaskResponseDTO;
import com.ramonmoncao.tasks_api.port.dtos.updateState.UpdateStateResponseDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class TaskServiceImplTest {
    @Mock
    private TaskRepository taskRepository;
    @Mock
    private TaskMapper taskMapper;
    @InjectMocks
    private TaskServiceImpl taskService;

    @BeforeEach
    void setUp(){
        taskService = new TaskServiceImpl(taskRepository, taskMapper);
    }

    @Test
    void shouldCreateTask() {

        UUID id = UUID.randomUUID();
        String title = "Test Task";
        boolean done = false;
        LocalDateTime now = LocalDateTime.now();

        CreateTaskRequestDTO requestDTO = new CreateTaskRequestDTO();
        requestDTO.setTitle(title);

        Task task = new Task();
        task.setTitle(title);
        task.setDone(done);
        task.setCreatedAt(now);
        task.setId(id);

        CreateTaskResponseDTO responseDTO = new CreateTaskResponseDTO(id, title, done, now);

        when(taskMapper.toEntity(requestDTO)).thenReturn(task);
        when(taskRepository.save(task)).thenReturn(task);
        when(taskMapper.toCreateResponseDTO(task)).thenReturn(responseDTO);

        CreateTaskResponseDTO result = taskService.createTask(requestDTO);

        assertEquals(id, result.getId());
        assertEquals(title, result.getTitle());
        assertEquals(done, result.isDone());
        assertEquals(now, result.getCreatedAt());

        verify(taskMapper).toEntity(requestDTO);
        verify(taskRepository).save(task);
        verify(taskMapper).toCreateResponseDTO(task);
    }

    @Test
    void shouldThrowCreateTaskException() {
        CreateTaskRequestDTO requestDTO = new CreateTaskRequestDTO();

        CreateTaskException ex = assertThrows(CreateTaskException.class, () ->
                taskService.createTask(requestDTO)
        );
        assertEquals("Título não pode ser nulo.", ex.getMessage());
    }

    @Test
    void updateShouldThrowNotFoundException() {
        UUID id = UUID.randomUUID();
        when(taskRepository.findById(id)).thenReturn(Optional.empty());

        assertThrows(NotFoundException.class, () -> taskService.updateState(id));
    }
    @Test
    void ShouldUpdateState() {

        UUID taskId = UUID.randomUUID();
        Task task = new Task();
        task.setId(taskId);
        task.setTitle("Test Task");
        task.setDone(false);

        UpdateStateResponseDTO responseDTO = new UpdateStateResponseDTO();

        when(taskRepository.findById(taskId)).thenReturn(Optional.of(task));
        when(taskMapper.toUpdateStateDTO(task)).thenReturn(responseDTO);

        UpdateStateResponseDTO result = taskService.updateState(taskId);

        assertEquals(responseDTO, result);
        assertTrue(task.isDone());
        verify(taskRepository).findById(taskId);
        verify(taskMapper).toUpdateStateDTO(task);
    }

    @Test
    void shouldReturnAll() {
        Task task1 = new Task();
        Task task2 = new Task();

        TaskResponseDTO dto1 = new TaskResponseDTO();
        TaskResponseDTO dto2 = new TaskResponseDTO();

        when(taskRepository.findAll()).thenReturn(Arrays.asList(task1, task2));
        when(taskMapper.toResponseDTO(task1)).thenReturn(dto1);
        when(taskMapper.toResponseDTO(task2)).thenReturn(dto2);

        List<TaskResponseDTO> result = taskService.findAll();

        assertEquals(2, result.size());
        assertTrue(result.contains(dto1));
        assertTrue(result.contains(dto2));
    }

    @Test
    void shouldCallRepositoryDelete() {
        UUID id = UUID.randomUUID();
        Task task = new Task();
        task.setId(id);

        when(taskRepository.findById(id)).thenReturn(Optional.of(task));

        taskService.delete(id);

        verify(taskRepository).delete(task);
    }

    @Test
    void deleteShouldThrowNotFoundException() {
        UUID id = UUID.randomUUID();
        when(taskRepository.findById(id)).thenReturn(Optional.empty());

        assertThrows(NotFoundException.class, () -> taskService.delete(id));
    }
}