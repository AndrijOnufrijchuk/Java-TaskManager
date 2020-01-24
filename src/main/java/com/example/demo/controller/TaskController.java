
package com.example.demo.controller;

import com.example.demo.model.TaskVo;
import com.example.demo.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;


@RestController
public class TaskController {
    @Autowired
    private TaskService service;
    @GetMapping("/task/{id}")
    public ResponseEntity<TaskVo> getTask(@PathVariable String id) {
        return new ResponseEntity<>(service.getTaskById(id), HttpStatus.OK);
    }
    @GetMapping("/task")
    public ResponseEntity<List<TaskVo>> getTaskList() {
        return new ResponseEntity<>(service.getTaskList(), HttpStatus.OK);
    }
    @PostMapping("/task")
    public ResponseEntity<String> saveTask(@RequestBody TaskVo TaskVo) {
        service.saveTask(TaskVo);
        return new ResponseEntity<>("New Task successfully saved", HttpStatus.OK);
    }
    @PutMapping("/task")
    public ResponseEntity<String> updateTask(@RequestBody TaskVo TaskVo) {
        service.updateTask(TaskVo);
        return new ResponseEntity<>("Task successfully updated", HttpStatus.OK);
    }
    @DeleteMapping("/task")
    public ResponseEntity<String> deleteTask(@RequestBody TaskVo taskVo) {
        service.deleteTask(taskVo);
        return new ResponseEntity<>("Task successfully deleted", HttpStatus.OK);
    }
}