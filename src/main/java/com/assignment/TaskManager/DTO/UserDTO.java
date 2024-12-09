package com.assignment.TaskManager.DTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
public class UserDTO {

    private Long id;

    @NotBlank(message = "First Name is mandatory")
    private String firstName;

    @NotBlank(message = "Last Name is mandatory")
    private String lastName;

    @NotBlank(message = "Timezone is mandatory")
    private String timezone;

    @NotNull(message = "isActive is mandatory")
    private Boolean isActive;

    private List<Long> taskIds;
}
