package com.example.demo.model;

public class TaskModel {
    private String taskdescription;

    public TaskModel() {}

    public TaskModel(String taskdescription) {
        this.taskdescription = taskdescription;
    }

    public String getTaskdescription() { return taskdescription; }
    public void setTaskdescription(String taskdescription) { this.taskdescription = taskdescription; }
}