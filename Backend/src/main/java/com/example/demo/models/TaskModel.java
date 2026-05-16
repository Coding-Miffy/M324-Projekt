package com.example.demo.models;

public class TaskModel {
    private String taskdescription;
    private String color;

    public TaskModel() {}

    public TaskModel(String taskdescription, String color) {
        this.taskdescription = taskdescription;
        this.color = color;
    }

    public String getTaskdescription() { return taskdescription; }
    public void setTaskdescription(String taskdescription) { this.taskdescription = taskdescription; }
    public String getColor() { return color; }
    public void setColor(String color) { this.color = color; }
}