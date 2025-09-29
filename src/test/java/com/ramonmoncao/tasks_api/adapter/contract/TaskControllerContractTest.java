package com.ramonmoncao.tasks_api.adapter.contract;

import com.atlassian.oai.validator.mockmvc.OpenApiValidationMatchers;
import com.ramonmoncao.tasks_api.adapter.input.TaskController;
import com.ramonmoncao.tasks_api.domain.mapper.TaskAdapterMapperImpl;
import com.ramonmoncao.tasks_api.port.input.TaskService;
import com.ramonmoncao.tasks_api.templates.createTask.CreateTaskResponseTemplate;
import com.ramonmoncao.tasks_api.templates.findAllTasks.FindAllTaskResponseTemplate;
import com.ramonmoncao.tasks_api.templates.updateTask.UpdateTaskResponseTemplate;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.test.web.servlet.MockMvc;

import java.util.UUID;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(TaskController.class)
@Import(TaskAdapterMapperImpl.class) // Importa a implementação real do mapper
public class TaskControllerContractTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TaskService taskService;

    private final String openApiSwaggerFilePath = "openapi.json";

    @Test
    void testFindAllMatchesContract() throws Exception {

        when(taskService.findAll()).thenReturn(FindAllTaskResponseTemplate.getAllTaskResponse());

        mockMvc.perform(get("/api/tasks"))
                .andExpect(status().isOk())
                .andExpect(OpenApiValidationMatchers.openApi().isValid(openApiSwaggerFilePath));
    }
    @Test
    void testCreateTaskMatchesContract() throws Exception {
        when(taskService.createTask(org.mockito.ArgumentMatchers.any()))
                .thenReturn(CreateTaskResponseTemplate.getCreateTaskResponse());

        mockMvc.perform(post("/api/tasks")
                        .contentType("application/json")
                        .content("{\"title\":\"Nova Task\"}"))
                .andExpect(status().isOk())
                .andExpect(OpenApiValidationMatchers.openApi().isValid(openApiSwaggerFilePath));
    }
    @Test
    void testUpdateTaskStateMatchesContract() throws Exception {
        UUID id = UUID.randomUUID();
        when(taskService.updateState(id)).thenReturn(UpdateTaskResponseTemplate.getUpdateResponse());

        mockMvc.perform(patch("/api/tasks/toggle/" + id))
                .andExpect(status().isOk())
                .andExpect(OpenApiValidationMatchers.openApi().isValid(openApiSwaggerFilePath));
    }

    @Test
    void testDeleteTaskMatchesContract() throws Exception {
        UUID id = UUID.randomUUID();
        mockMvc.perform(delete("/api/tasks/" + id))
                .andExpect(status().isNoContent())
                .andExpect(OpenApiValidationMatchers.openApi().isValid(openApiSwaggerFilePath));
    }
}
