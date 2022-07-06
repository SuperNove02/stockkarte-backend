package com.stockkarte;

import com.stockkarte.models.Task;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/task")
public class TaskResource {
    @PostMapping("/")
    public Task postUser(@RequestBody Task task){
        return task;
    }
}
