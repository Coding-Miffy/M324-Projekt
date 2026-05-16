package com.example.demo;

import com.example.demo.models.Tag;
import com.example.demo.models.TaskDTO;
import com.example.demo.models.TaskModel;
import com.example.demo.repository.TaskRepository;
import com.example.demo.service.TaskService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class TaskServiceTest {

    private TaskRepository taskRepository;
    private TaskService taskService;

    @BeforeEach
    void setUp() {
        taskRepository = new TaskRepository();
        taskService = new TaskService(taskRepository);
    }

    @Test
    void addTask_savesTaskWithTag() {
        TaskDTO dto = new TaskDTO();
        dto.setTaskdescription("Hausaufgaben machen");
        dto.setColor("#ff0000");
        dto.setTag(Tag.SCHULE);

        boolean result = taskService.addTask(dto);

        assertTrue(result);
        List<TaskModel> all = taskService.getAllTasks();
        assertEquals(1, all.size());
        assertEquals(Tag.SCHULE, all.get(0).getTag());
    }

    @Test
    void addTask_duplicateDescriptionReturnsFalse() {
        TaskDTO dto = new TaskDTO();
        dto.setTaskdescription("Einkaufen gehen");
        dto.setTag(Tag.EINKAUFEN);

        taskService.addTask(dto);
        boolean result = taskService.addTask(dto);

        assertFalse(result);
        assertEquals(1, taskService.getAllTasks().size());
    }

    @Test
    void addTask_noTag_savesWithNullTag() {
        TaskDTO dto = new TaskDTO();
        dto.setTaskdescription("Aufgabe ohne Tag");

        taskService.addTask(dto);

        TaskModel saved = taskService.getAllTasks().get(0);
        assertNull(saved.getTag());
    }

    @Test
    void getTasksByTag_returnsOnlyMatchingTasks() {
        TaskDTO arbeit = new TaskDTO();
        arbeit.setTaskdescription("Meeting vorbereiten");
        arbeit.setTag(Tag.ARBEIT);

        TaskDTO privat = new TaskDTO();
        privat.setTaskdescription("Freunde treffen");
        privat.setTag(Tag.PRIVAT);

        TaskDTO schule = new TaskDTO();
        schule.setTaskdescription("Referat schreiben");
        schule.setTag(Tag.SCHULE);

        taskService.addTask(arbeit);
        taskService.addTask(privat);
        taskService.addTask(schule);

        List<TaskModel> result = taskService.getTasksByTag(Tag.ARBEIT);

        assertEquals(1, result.size());
        assertEquals("Meeting vorbereiten", result.get(0).getTaskdescription());
        assertEquals(Tag.ARBEIT, result.get(0).getTag());
    }

    @Test
    void getTasksByTag_noMatch_returnsEmptyList() {
        TaskDTO dto = new TaskDTO();
        dto.setTaskdescription("Lernen");
        dto.setTag(Tag.SCHULE);
        taskService.addTask(dto);

        List<TaskModel> result = taskService.getTasksByTag(Tag.EINKAUFEN);

        assertTrue(result.isEmpty());
    }

    @Test
    void deleteTask_removesTask() {
        TaskDTO dto = new TaskDTO();
        dto.setTaskdescription("Aufgabe loeschen");
        dto.setTag(Tag.PRIVAT);
        taskService.addTask(dto);

        boolean deleted = taskService.deleteTask(dto);

        assertTrue(deleted);
        assertTrue(taskService.getAllTasks().isEmpty());
    }

    @Test
    void deleteTask_notFound_returnsFalse() {
        TaskDTO dto = new TaskDTO();
        dto.setTaskdescription("Nicht vorhanden");

        boolean result = taskService.deleteTask(dto);

        assertFalse(result);
    }
}
