package com.example.demo.mapper;

import com.example.demo.models.EnumPriority;
import com.example.demo.models.Tag;
import com.example.demo.models.TaskDTO;
import com.example.demo.models.TaskModel;

public class TaskMapper {

	public static TaskModel toModel(TaskDTO dto) {
	    String color = dto.getColor();
	    Tag tag = dto.getTag();
      EnumPriority priority = dto.getPriority();

	    if (color == null || color.isEmpty()) {
	        color = "#ffffff";
	    }
    
	    return new TaskModel(dto.getTaskdescription(), color, tag, priority);
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
        dto.setTag(model.getTag());
        return dto;
    }
}