package com.example.demo.models;

public class TaskModel {
    private String taskdescription;
    private String color;
    private Tag tag;
    private EnumPriority priority;

    public TaskModel() {}

    public TaskModel(String taskdescription, String color, Tag tag, EnumPriority priority) {
        this.taskdescription = taskdescription;
        this.color = color;
        this.tag = tag;
        this.priority = priority;
    }

    public String getTaskdescription() { return taskdescription; }
    public String getColor() { return color; }
    public void setColor(String color) { this.color = color; }
    public EnumPriority getPriority() { return priority; }
    public Tag getTag() { return tag; }
    public void setTag(Tag tag) { this.tag = tag; }
}