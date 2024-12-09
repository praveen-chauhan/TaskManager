package com.assignment.TaskManager.Mapper;

import com.assignment.TaskManager.DTO.UserDTO;
import com.assignment.TaskManager.Entities.Task;
import com.assignment.TaskManager.Entities.User;
import org.springframework.stereotype.Component;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class UserMapper {

    public UserDTO toDTO(User user) {
        UserDTO userDTO = new UserDTO();
        userDTO.setId(user.getId());
        userDTO.setFirstName(user.getFirstName());
        userDTO.setLastName(user.getLastName());
        userDTO.setTimezone(user.getTimezone());
        userDTO.setIsActive(user.getIsActive());
        List<Long> taskIds = new ArrayList<>();
        if (user.getTasks() != null) {
            taskIds = user.getTasks().stream().map(Task::getId).collect(Collectors.toList());
        }
        userDTO.setTaskIds(taskIds);
        return userDTO;
    }

}
