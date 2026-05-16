package com.example.demo.models;

public class TaskModel {
    private String taskdescription;
    private String color;
    private EnumPriority priority;

    public TaskModel() {}

    public TaskModel(String taskdescription, String color,  EnumPriority priority) {
        this.taskdescription = taskdescription;
        this.color = color;
        this.priority = priority;
    }

    public String getTaskdescription() { return taskdescription; }
    public String getColor() { return color; }
    public EnumPriority getPriority() { return priority; }
}