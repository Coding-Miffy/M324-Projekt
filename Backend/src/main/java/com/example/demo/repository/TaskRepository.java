package com.example.demo.repository;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.example.demo.models.Tag;
import com.example.demo.models.TaskModel;

@Repository
public class TaskRepository {

    private final List<TaskModel> tasks = new ArrayList<>();

    public List<TaskModel> findAll() {
        return tasks;
    }

    public boolean existsByDescription(String description) {
        return tasks.stream()
                .anyMatch(t -> t.getTaskdescription().equals(description));
    }

    public void save(TaskModel task) {
        tasks.add(task);
    }

    public boolean deleteByDescription(String description) {
        Iterator<TaskModel> it = tasks.iterator();
        while (it.hasNext()) {
            if (it.next().getTaskdescription().equals(description)) {
                it.remove();
                return true;
            }
        }
        return false;
    }

    public List<TaskModel> findByTag(Tag tag) {
        return tasks.stream()
                .filter(t -> tag.equals(t.getTag()))
                .collect(java.util.stream.Collectors.toList());
    }
}