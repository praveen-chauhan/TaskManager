package com.assignment.TaskManager.Service;

import com.assignment.TaskManager.DTO.TaskDTO;
import com.assignment.TaskManager.Entities.Task;
import com.assignment.TaskManager.Entities.User;
import com.assignment.TaskManager.ExpHandler.TaskNotFoundException;
import com.assignment.TaskManager.Mapper.TaskMapper;
import com.assignment.TaskManager.repository.TaskRepository;
import com.assignment.TaskManager.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TaskService implements TaskServiceInt {

    @Autowired
    private TaskRepository taskRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private TaskMapper taskMapper;

    @Override
    public TaskDTO createTask(Task task) {
        User user = userRepository.findById(task.getAssignedTo().getId())
                .orElseThrow(() -> new RuntimeException("User not found with id: " + task.getAssignedTo().getId()));
        System.out.println(task.getAssignedTo().getId());
        task.setAssignedTo(user);
        task.setTimezone(user.getTimezone());
        return taskMapper.toDTO(taskRepository.save(task));
    }

    @Override
    public List<TaskDTO> getTasksByUserId(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found with id: " + userId));
        List<Task> tasks = taskRepository.findByAssignedToId(user.getId());
        return tasks.stream().map(taskMapper::toDTO).collect(Collectors.toList());
    }

    @Override
    public List<TaskDTO> getAllTasks() {
        return taskRepository.findAll().stream()
                .map(taskMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public TaskDTO getTaskById(Long id) {
        Task task= taskRepository.findById(id)
                .orElseThrow(() -> new TaskNotFoundException("Task not found with id: " + id));
        return taskMapper.toDTO(task);
    }

    @Override
    public TaskDTO updateTask(Long id, Task taskDetails) {
        Task task = taskRepository.findById(id)
                .orElseThrow(() -> new TaskNotFoundException("Task not found with id: " + id));
        User user = userRepository.findById(taskDetails.getAssignedTo().getId())
                .orElseThrow(() -> new RuntimeException("User not found with id: " + taskDetails.getAssignedTo().getId()));
        task.setTitle(taskDetails.getTitle());
        task.setDescription(taskDetails.getDescription());
        task.setStatus(taskDetails.getStatus());
        task.setAssignedTo(user);
        task.setTimezone(user.getTimezone());
        return taskMapper.toDTO(taskRepository.save(task));
    }

    @Override
    public void deleteTask(Long id) {
        if (!taskRepository.existsById(id)) {
            throw new TaskNotFoundException("Task not found with id: " + id);
        }
        taskRepository.deleteById(id);
    }
}
