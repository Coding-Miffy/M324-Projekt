package com.example.demo.models;

public class TaskDTO {
    int id;
    String text;
    Priority priority;
    public TaskDTO(int id, String text, Priority priority) {
        this.id = id;
        this.text = text;
        this.priority = priority;
    }
}
