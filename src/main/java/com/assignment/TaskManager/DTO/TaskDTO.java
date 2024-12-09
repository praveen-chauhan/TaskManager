package com.assignment.TaskManager.DTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
import java.time.Instant;

@Data
public class TaskDTO {
    private Long id;
    private String title;

    private String description;
    private String status;

    private Instant createdAt;
    private Instant updatedAt;
    private Long assignedToId;
    private String timezone;
}
