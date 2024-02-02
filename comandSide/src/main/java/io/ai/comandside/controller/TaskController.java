package io.ai.comandside.controller;

import io.ai.comandside.command.task.AssignExecutorToTaskCommand;
import io.ai.comandside.command.task.ChangeTaskNameCommand;
import io.ai.comandside.command.task.ChangeTaskStatusCommand;
import io.ai.comandside.command.task.CreateTaskCommand;
import io.ai.comandside.command.task.status.CreateTaskStatusCommand;
import io.ai.comandside.command.task.status.DeleteTaskStatusCommand;
import io.ai.comandside.service.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/task")
@RequiredArgsConstructor
public class TaskController {

    private final TaskService taskService;

    @PostMapping
    public ResponseEntity<HttpStatus> createTask(@RequestBody CreateTaskCommand command) {
        taskService.create(command);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/change-task-name")
    public ResponseEntity<HttpStatus> changeTaskName(@RequestBody ChangeTaskNameCommand command) {
        taskService.changeName(command);
        return ResponseEntity.ok().build();

    }

    @PutMapping("/change-task-status")
    public ResponseEntity<HttpStatus> changeTaskStatus(@RequestBody ChangeTaskStatusCommand command) {
        taskService.changeTaskStatus(command);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/assign-executor")
    public ResponseEntity<HttpStatus> assignExecutor(@RequestBody AssignExecutorToTaskCommand command) {
        taskService.assignExecutor(command);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/create-task-status")
    public ResponseEntity<HttpStatus> createTaskStatus(@RequestBody CreateTaskStatusCommand command) {
        taskService.createTaskStatus(command);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/delete-task-status")
    public ResponseEntity<HttpStatus> deleteTaskStatus(@RequestBody DeleteTaskStatusCommand command) {
        taskService.deleteTaskStatus(command);
        return ResponseEntity.ok().build();
    }
}
