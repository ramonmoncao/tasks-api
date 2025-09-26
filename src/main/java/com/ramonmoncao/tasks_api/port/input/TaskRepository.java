package com.ramonmoncao.tasks_api.port.input;

import com.ramonmoncao.tasks_api.domain.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface TaskRepository extends JpaRepository<Task, UUID> {

}
