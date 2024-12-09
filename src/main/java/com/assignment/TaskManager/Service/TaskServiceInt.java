package com.assignment.TaskManager.Service;

import com.assignment.TaskManager.DTO.TaskDTO;
import com.assignment.TaskManager.Entities.Task;

import java.util.List;

public interface TaskServiceInt {
    TaskDTO createTask(Task task);
    List<TaskDTO> getAllTasks();
    List<TaskDTO> getTasksByUserId(Long userId);
    TaskDTO getTaskById(Long id);
    TaskDTO updateTask(Long id, Task taskDetails);
    void deleteTask(Long id);
}

