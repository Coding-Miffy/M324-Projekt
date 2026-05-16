package com.example.demo.models;

public class TaskDTO {
    private String taskdescription;
    private String color;
    private EnumPriority priority;
    public TaskDTO() {}

    public String getTaskdescription() { return taskdescription; }
    public void setTaskdescription(String taskdescription) { this.taskdescription = taskdescription; }
    public String getColor() { return color; }
    public void setColor(String color) { this.color = color; }
    public void setPriority(EnumPriority priority) {
        this.priority = priority;
    }

    public EnumPriority getPriority() {
        return priority;
    }
    }