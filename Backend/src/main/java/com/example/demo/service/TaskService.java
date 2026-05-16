package com.example.demo.service;

import com.example.demo.dto.TaskDTO;
import com.example.demo.mapper.TaskMapper;
import com.example.demo.model.TaskModel;
import com.example.demo.repository.TaskRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskService {

    private final TaskRepository taskRepository;

    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public List<TaskModel> getAllTasks() {
        return taskRepository.findAll();
    }

    public boolean addTask(TaskDTO dto) {
        if (taskRepository.existsByDescription(dto.getTaskdescription())) {
            return false;
        }
        taskRepository.save(TaskMapper.toModel(dto));
        return true;
    }

    public boolean deleteTask(TaskDTO dto) {
        return taskRepository.deleteByDescription(dto.getTaskdescription());
    }
}