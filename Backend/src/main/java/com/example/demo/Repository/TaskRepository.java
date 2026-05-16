package com.example.demo.repository;

import com.example.demo.model.TaskModel;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

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
}