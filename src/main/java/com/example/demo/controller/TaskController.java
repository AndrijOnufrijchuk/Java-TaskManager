
package com.example.demo.controller;

import javax.validation.Valid;

import com.example.demo.model.Task;
import com.example.demo.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;


import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;



@RestController
@RequestMapping("/api")
@CrossOrigin("*")
public class TaskController {

    @Autowired
    TaskRepository taskRepository;

    @GetMapping("/tasks")
    public List<Task> getAllTasks() {

        return taskRepository.findAll();
    }

    @PostMapping("/tasks")
    public Task createTask(@Valid @RequestBody Task task) {
        task.setCompleted(false);
        return taskRepository.save(task);
    }

    @GetMapping(value="/tasks/{id}")
    public ResponseEntity<Task> getTaskById(@PathVariable("id") String id) {
        return taskRepository.findById(id)
                .map(task -> ResponseEntity.ok().body(task))
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping(value="/tasks/{id}")
    public ResponseEntity<Task> updateTask(@PathVariable("id") String id,
                                           @Valid @RequestBody Task task) {
        return taskRepository.findById(id)
                .map(taskData -> {
                    taskData.setTitle(task.getTitle());
                    taskData.setCompleted(task.getCompleted());
                    Task updatedTask = taskRepository.save(taskData);
                    return ResponseEntity.ok().body(updatedTask);
                }).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping(value="/tasks/{id}")
    public ResponseEntity<?> deleteTask(@PathVariable("id") String id) {
        return taskRepository.findById(id)
                .map(task -> {
                    taskRepository.deleteById(id);
                    return ResponseEntity.ok().build();
                }).orElse(ResponseEntity.notFound().build());
    }
}