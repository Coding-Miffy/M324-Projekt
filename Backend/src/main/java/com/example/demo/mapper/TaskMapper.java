package com.example.demo.mapper;

import com.example.demo.dto.TaskDTO;
import com.example.demo.model.TaskModel;

public class TaskMapper {

    public static TaskModel toModel(TaskDTO dto) {
        return new TaskModel(dto.getTaskdescription(), dto.getColor());
    }

    public static TaskDTO toDTO(TaskModel model) {
        TaskDTO dto = new TaskDTO();
        String color = model.getColor();
        if (color == null || color.isEmpty()) {
            color = "#ffffff";
        }
        dto.setTaskdescription(model.getTaskdescription());
        dto.setColor(color);
        return dto;
    }
}