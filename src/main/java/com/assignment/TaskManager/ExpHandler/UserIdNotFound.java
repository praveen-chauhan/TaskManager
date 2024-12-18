package com.assignment.TaskManager.ExpHandler;

import lombok.Data;

@Data
public class UserIdNotFound {
    String error;
    UserIdNotFound(String st){
        this.error=st;
    }
}
