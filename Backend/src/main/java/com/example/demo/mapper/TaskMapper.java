package com.example.demo.mapper;

import com.example.demo.models.TaskDTO;
import com.example.demo.models.TaskModel;

public class TaskMapper {

    public static TaskModel toModel(TaskDTO dto) {
        return new TaskModel(dto.getTaskdescription(), dto.getColor(), dto.getTag());
    }

    public static TaskDTO toDTO(TaskModel model) {
        TaskDTO dto = new TaskDTO();
        String color = model.getColor();
        if (color == null || color.isEmpty()) {
            color = "#ffffff";
        }
        dto.setTaskdescription(model.getTaskdescription());
        dto.setColor(color);
        dto.setTag(model.getTag());
        return dto;
    }
}