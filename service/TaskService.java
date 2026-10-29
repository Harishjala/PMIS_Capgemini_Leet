package com.example.taskapi.service;

import com.example.taskapi.exception.TaskNotFoundException;
import com.example.taskapi.model.TaskModel;
import com.example.taskapi.Repository.TaskRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskService {

    private final TaskRepository taskRepository;

    public TaskService(TaskRepository taskRepository) {   // injected by Spring
        this.taskRepository = taskRepository;
    }

    public List<TaskModel> getAllTasks() {
        return taskRepository.findAll();
    }

    public List<TaskModel> getTasksByStatus(boolean completed) {
        return taskRepository.findByCompleted(completed);
    }

    public TaskModel getTaskById(Long id) {
        return taskRepository.findById(id)
                .orElseThrow(() -> new TaskNotFoundException(id));
    }

    public TaskModel createTask(TaskModel task) {
        task.setId(null);                 // make sure we always INSERT, never overwrite
        return taskRepository.save(task);
    }

    public TaskModel updateTask(Long id, TaskModel updated) {
        TaskModel existing = getTaskById(id);  // throws 404 if missing
        existing.setTitle(updated.getTitle());
        existing.setDescription(updated.getDescription());
        existing.setCompleted(updated.isCompleted());
        return taskRepository.save(existing);
    }

    public void deleteTask(Long id) {
        TaskModel existing = getTaskById(id);  // throws 404 if missing
        taskRepository.delete(existing);
    }
}