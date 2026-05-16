package com.example.demo.controller;

import com.example.demo.dto.TaskDTO;
import com.example.demo.models.TaskModel;
import com.example.demo.service.TaskService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
public class TaskController {

    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping("/")
    public List<TaskModel> getTasks() {
        return taskService.getAllTasks();
    }

    @PostMapping("/tasks")
    public String addTask(@RequestBody TaskDTO dto) {
        boolean added = taskService.addTask(dto);
        return "redirect:/";
    }

    @PostMapping("/delete")
    public String delTask(@RequestBody TaskDTO dto) {
        taskService.deleteTask(dto);
        return "redirect:/";
    }
}