package com.assignment.TaskManager.Controller;

import com.assignment.TaskManager.DTO.TaskDTO;
import com.assignment.TaskManager.DTO.UserDTO;
import com.assignment.TaskManager.Entities.User;
import com.assignment.TaskManager.Service.TaskService;
import com.assignment.TaskManager.Service.TaskServiceInt;
import com.assignment.TaskManager.Service.UserServiceInt;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserServiceInt userService;
    @Autowired private
    TaskServiceInt taskService;

    @GetMapping("/{id}/tasks")
    public ResponseEntity<List<TaskDTO>> getTasksByUserId(@PathVariable Long id) {
        List<TaskDTO> tasks = taskService.getTasksByUserId(id);
        return ResponseEntity.ok(tasks); }
    @GetMapping
    public ResponseEntity<Set<UserDTO>> getAllUsers() {
        Set<UserDTO> users = userService.getAllUsers();
        return ResponseEntity.ok(users);
    }
    @GetMapping("/{id}")
    public ResponseEntity<UserDTO> getUserById(@PathVariable Long id) {
        UserDTO user = userService.getUserById(id);
        return ResponseEntity.ok(user);
    }

    @PostMapping
    public ResponseEntity<UserDTO> createUser(@Valid @RequestBody User user) {
        UserDTO createdUser = userService.createUser(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdUser);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserDTO> updateUser(@PathVariable Long id, @Valid @RequestBody User userDetails) {
        UserDTO updatedUser = userService.updateUser(id, userDetails);
        return ResponseEntity.ok(updatedUser);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }
}

