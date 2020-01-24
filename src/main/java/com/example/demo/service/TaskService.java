package com.example.demo.service;

import com.example.demo.model.Task;
import com.example.demo.model.TaskVo;
import com.example.demo.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TaskService {
    @Autowired
    private TaskRepository repository;
    public List<TaskVo> getTaskList() {
        return repository.findAll().stream().map(u -> {
            TaskVo vo = new TaskVo();
            vo.setId(u.getId());
            vo.setName(u.getName());
            vo.setEmail(u.getEmail());
            return vo;
        }).collect(Collectors.toList());
    }
    public TaskVo getTaskById(String id) {
        return repository.findById(id).map(u -> {
            TaskVo vo = new TaskVo();
            vo.setId(u.getId());
            vo.setName(u.getName());
            vo.setEmail(u.getEmail());
            return vo;
        }).orElse(null);
    }
    public void saveTask(TaskVo vo) {
        Task task = new Task();
        task.setName(vo.getName());
        task.setEmail(vo.getEmail());
        task.setPwd(vo.getPwd());
        repository.save(task);
    }
    public void updateTask(TaskVo vo) {
        Task task = new Task();
        task.setId(vo.getId());
        task.setName(vo.getName());
        task.setEmail(vo.getEmail());
        task.setPwd(vo.getPwd());
        repository.save(task);
    }
    public void deleteTask(TaskVo vo) {
        Task task = new Task();
        task.setId(vo.getId());
        repository.delete(task);
    }
}

