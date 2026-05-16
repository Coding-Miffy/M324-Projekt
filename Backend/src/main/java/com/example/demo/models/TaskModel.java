package com.example.demo.models;

public class TaskModel {
    private String taskdescription;
    private String color;
    private Tag tag;

    public TaskModel() {}

    public TaskModel(String taskdescription, String color, Tag tag) {
        this.taskdescription = taskdescription;
        this.color = color;
        this.tag = tag;
    }

    public String getTaskdescription() { return taskdescription; }
    public void setTaskdescription(String taskdescription) { this.taskdescription = taskdescription; }
    public String getColor() { return color; }
    public void setColor(String color) { this.color = color; }
    public Tag getTag() { return tag; }
    public void setTag(Tag tag) { this.tag = tag; }
}