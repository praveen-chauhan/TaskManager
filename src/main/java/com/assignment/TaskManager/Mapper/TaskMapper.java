package com.assignment.TaskManager.Mapper;

import com.assignment.TaskManager.DTO.TaskDTO;
import com.assignment.TaskManager.Entities.Task;
import org.springframework.stereotype.Component;

@Component
public class TaskMapper {

    public TaskDTO toDTO(Task task) {
        TaskDTO taskDTO = new TaskDTO();
        taskDTO.setId(task.getId());
        taskDTO.setTitle(task.getTitle());
        taskDTO.setDescription(task.getDescription());
        taskDTO.setStatus(task.getStatus());
        taskDTO.setCreatedAt(task.getCreatedAt());
        taskDTO.setUpdatedAt(task.getUpdatedAt());
        taskDTO.setAssignedToId(task.getAssignedTo().getId());
        taskDTO.setTimezone(task.getTimezone());
        return taskDTO;
    }


}
