package com.assignment.TaskManager.Service;

import com.assignment.TaskManager.DTO.UserDTO;
import com.assignment.TaskManager.Entities.User;
import com.assignment.TaskManager.Mapper.UserMapper;
import com.assignment.TaskManager.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UserService implements UserServiceInt {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserMapper userMapper;

    @Override
    public UserDTO createUser(User user) {
        return userMapper.toDTO(userRepository.save(user));
    }

    @Override
    public Set<UserDTO> getAllUsers() {
        return userRepository.findAll().stream()
                .map(userMapper::toDTO)
                .collect(Collectors.toSet());
    }

    @Override
    public UserDTO getUserById(Long id) {
        User user= userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found with id: " + id));
        return userMapper.toDTO(user);
    }

    @Override
    public UserDTO updateUser(Long id, User userDetails) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found with id: " + id));
        user.setFirstName(userDetails.getFirstName());
        user.setLastName(userDetails.getLastName());
        user.setTimezone(userDetails.getTimezone());
        user.setIsActive(userDetails.getIsActive());
        return userMapper.toDTO(userRepository.save(user));
    }

    @Override
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }
}
