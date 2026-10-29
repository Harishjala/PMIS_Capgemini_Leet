package com.example.taskapi.controller;

import com.example.taskapi.model.TaskModel;
import com.example.taskapi.service.TaskService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tasks")
public class TaskController {

    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping
    public List<TaskModel> getTasks(@RequestParam(required = false) Boolean completed) {
        if (completed != null) {
            return taskService.getTasksByStatus(completed);
        }
        return taskService.getAllTasks();
    }

    @GetMapping("/{id}")
    public TaskModel getTask(@PathVariable Long id) {
        return taskService.getTaskById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public TaskModel createTask(@RequestBody TaskModel task) {
        return taskService.createTask(task);
    }

    @PutMapping("/{id}")
    public TaskModel updateTask(@PathVariable Long id, @RequestBody TaskModel task) {
        return taskService.updateTask(id, task);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteTask(@PathVariable Long id) {
        taskService.deleteTask(id);
    }
}