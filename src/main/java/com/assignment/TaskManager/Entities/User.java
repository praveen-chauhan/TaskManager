package com.assignment.TaskManager.Entities;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import java.util.List;

@Entity(name="users")
@Data
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "First Name is mandatory")
    @Column(nullable = false)
    private String firstName;

    @NotBlank(message = "Last Name is mandatory")
    @Column(nullable = false)
    private String lastName;

    @NotBlank(message = "Timezone is mandatory")
    @Column(nullable = false)
    private String timezone;

    @NotNull(message = "isActive is mandatory")
    @Column(nullable = false)
    private Boolean isActive;

    @OneToMany(mappedBy = "assignedTo", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    @JsonManagedReference
    private List<Task> tasks;
}
