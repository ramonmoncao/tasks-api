package com.ramonmoncao.tasks_api.adapter.input;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ramonmoncao.tasks_api.adapter.dtos.createTask.CreateTaskRequestDTO;
import com.ramonmoncao.tasks_api.adapter.dtos.createTask.CreateTaskResponseDTO;
import com.ramonmoncao.tasks_api.adapter.dtos.returnTask.TaskResponseDTO;
import com.ramonmoncao.tasks_api.adapter.dtos.updateState.UpdateStateResponseDTO;
import com.ramonmoncao.tasks_api.domain.mapper.TaskAdapterMapper;
import com.ramonmoncao.tasks_api.port.input.TaskRepository;
import com.ramonmoncao.tasks_api.port.input.TaskService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Collections;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class TaskControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Mock
    private TaskService taskService;

    @Mock
    private TaskAdapterMapper taskAdapterMapper;


    private final ObjectMapper objectMapper = new ObjectMapper();

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void shouldReturnListOfTasks() throws Exception {
        when(taskService.findAll()).thenReturn(Collections.emptyList());
        when(taskAdapterMapper.responseToAdapterDTO(any())).thenReturn(new TaskResponseDTO());

        mockMvc.perform(get("/api/tasks"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$").isArray())
                .andDo(print());
    }

    @Test
    void shouldReturnCreatedTask() throws Exception {
        CreateTaskRequestDTO request = CreateTaskRequestDTO.builder()
                .title("Task 1")
                .build();
        CreateTaskResponseDTO response = CreateTaskResponseDTO.builder()
                .title("Task 1")
                .build();
        when(taskAdapterMapper.createRequestToPortDTO(any(CreateTaskRequestDTO.class))).thenReturn(null);
        when(taskService.createTask(any())).thenReturn(null);
        when(taskAdapterMapper.createResponseToAdapterDTO(any())).thenReturn(response);

        mockMvc.perform(post("/api/tasks")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.title").value(response.getTitle()))
                .andExpect(jsonPath("$.done").value(response.isDone()))
                .andDo(print());
    }

    @Test
    void shouldReturnNoContent() throws Exception {
        UUID taskId = UUID.randomUUID();
        mockMvc.perform(delete("/api/tasks/" + taskId))
                .andExpect(status().isNoContent());
    }

    @Test
    void shouldReturnUpdatedState() throws Exception {
        UUID taskId = UUID.randomUUID();
        UpdateStateResponseDTO response = new UpdateStateResponseDTO();
        when(taskService.updateState(taskId)).thenReturn(null);
        when(taskAdapterMapper.updateStateToAdapterDTO(any())).thenReturn(response);

        mockMvc.perform(patch("/api/tasks/toggle/" + taskId))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.done").value(response.isDone()));
    }
}