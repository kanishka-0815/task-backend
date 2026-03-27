package com.example.taskmanager.controller;

import com.example.taskmanager.model.Task;
import com.example.taskmanager.repository.TaskRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController 
@RequestMapping("/tasks")
public class TaskController {

    private final TaskRepository repository;

    public TaskController(TaskRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/{id}")
public Task getTaskById(@PathVariable Long id) {
    return repository.findById(id)
        .orElseThrow(() -> new RuntimeException("Task not found with id " + id));
}
    @GetMapping
    public List<Task> getAllTasks() {
        return repository.findAll();
    }

    @PostMapping
    public Task createTask(@RequestBody Task task) {
        return repository.save(task);
    }

    @PutMapping("/{id}")
public Task updateTask(@PathVariable Long id, @RequestBody Task taskDetails) {

    Task task = repository.findById(id)
            .orElseThrow(() -> new RuntimeException("Task not found with id " + id));

    task.setTitle(taskDetails.getTitle());
    task.setDescription(taskDetails.getDescription());
    task.setStatus(taskDetails.getStatus());

    return repository.save(task);
}

    @DeleteMapping("/{id}")
    public void deleteTask(@PathVariable Long id) {
        repository.deleteById(id);
    }
}