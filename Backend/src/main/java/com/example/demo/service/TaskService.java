package com.example.demo.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.mapper.TaskMapper;
import com.example.demo.models.Tag;
import com.example.demo.models.TaskDTO;
import com.example.demo.models.TaskModel;
import com.example.demo.repository.TaskRepository;

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

    public List<TaskModel> getTasksByTag(Tag tag) {
        return taskRepository.findByTag(tag);
    }
}