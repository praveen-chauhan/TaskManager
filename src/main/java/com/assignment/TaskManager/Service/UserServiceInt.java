package com.assignment.TaskManager.Service;

import com.assignment.TaskManager.DTO.UserDTO;
import com.assignment.TaskManager.Entities.User;
import java.util.Set;

public interface UserServiceInt {
    UserDTO createUser(User user);
    Set<UserDTO> getAllUsers();
    UserDTO getUserById(Long id);
    UserDTO updateUser(Long id, User userDetails);
    void deleteUser(Long id);
}
