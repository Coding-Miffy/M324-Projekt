package com.example.demo.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.models.Tag;
import com.example.demo.models.TaskDTO;
import com.example.demo.models.TaskModel;
import com.example.demo.service.TaskService;

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

    @GetMapping("/tasks/filter")
    public List<TaskModel> getTasksByTag(@RequestParam Tag tag) {
        return taskService.getTasksByTag(tag);
    }

    @GetMapping("/tags")
    public List<Tag> getAllTags() {
        return Arrays.asList(Tag.values());
    }
}