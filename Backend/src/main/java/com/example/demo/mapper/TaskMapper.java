package com.example.demo.mapper;

import com.example.demo.dto.TaskDTO;
import com.example.demo.model.TaskModel;

public class TaskMapper {

    public static TaskModel toModel(TaskDTO dto) {
        return new TaskModel(dto.getTaskdescription());
    }

    public static TaskDTO toDTO(TaskModel model) {
        TaskDTO dto = new TaskDTO();
        dto.setTaskdescription(model.getTaskdescription());
        return dto;
    }
}