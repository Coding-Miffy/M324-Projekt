package com.example.demo.mapper;

import com.example.demo.dto.TaskDTO;
import com.example.demo.models.TaskModel;
import com.example.demo.models.EnumPriority;

public class TaskMapper {

    public static TaskModel toModel(TaskDTO dto) {
        return new TaskModel(dto.getTaskdescription(), dto.getColor(), dto.getPriority());
    }

    public static TaskDTO toDTO(TaskModel model) {
        TaskDTO dto = new TaskDTO();
        String color = model.getColor();
        if (color == null || color.isEmpty()) {
            color = "#ffffff";
        }
        if(EnumPriority.MEDIUM == model.getPriority()) {
            dto.setPriority(EnumPriority.MEDIUM);
        }
        dto.setTaskdescription(model.getTaskdescription());
        dto.setColor(color);
        dto.setPriority(model.getPriority());
        return dto;
    }
}