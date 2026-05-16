package com.example.demo.mapper;

import com.example.demo.dto.TaskDTO;
import com.example.demo.model.TaskModel;

public class TaskMapper {

    public static TaskModel toModel(TaskDTO dto) {
        return new TaskModel(dto.getTaskdescription(), dto.getTaskColor);
    }

    public static TaskDTO toDTO(TaskModel model) {
        TaskDTO dto = new TaskDTO();
        if (TaskModel.getColor() == null || TaskModel.getColor().isEmpty()) {
            TaskModel.setColor("#ffffff");
        }
        dto.setTaskdescription(model.getTaskdescription());
        return dto;
    }
}