package com.example.demo.mapper;

import com.example.demo.models.EnumPriority;
import com.example.demo.models.TaskDTO;
import com.example.demo.models.TaskModel;

public class TaskMapper {

	public static TaskModel toModel(TaskDTO dto) {
	    String color = dto.getColor();

	    if (color == null || color.isEmpty()) {
	        color = "#ffffff";
	    }

	    return new TaskModel(dto.getTaskdescription(), color, dto.getPriority());

    }

    public static TaskDTO toDTO(TaskModel model) {
        TaskDTO dto = new TaskDTO();
        String color = model.getColor();
        EnumPriority priority = model.getPriority();

        if (color == null || color.isEmpty()) {
            color = "#ffffff";
        }
        if(EnumPriority.MEDIUM == model.getPriority()) {
            dto.setPriority(EnumPriority.MEDIUM);
        }
        dto.setTaskdescription(model.getTaskdescription());
        dto.setColor(color);
        dto.setPriority(priority);
        return dto;
    }
}