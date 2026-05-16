package com.example.demo;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.demo.models.TaskDTO;
import com.example.demo.models.TaskModel;
import com.example.demo.repository.TaskRepository;
import com.example.demo.service.TaskService;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

@SpringBootTest
class DemoApplicationTests {

	@Test
    void addTaskShouldSaveNewTask() {
        TaskRepository repository = new TaskRepository();
        TaskService service = new TaskService(repository);

        TaskDTO dto = new TaskDTO();
        dto.setTaskdescription("Test Task");
        dto.setColor("#ff0000");

        boolean result = service.addTask(dto);
        List<TaskModel> tasks = service.getAllTasks();

        assertTrue(result);
        assertEquals(1, tasks.size());
        assertEquals("Test Task", tasks.get(0).getTaskdescription());
        assertEquals("#ff0000", tasks.get(0).getColor());
    }

    @Test
    void addTaskShouldNotSaveDuplicateTask() {
        TaskRepository repository = new TaskRepository();
        TaskService service = new TaskService(repository);

        TaskDTO dto = new TaskDTO();
        dto.setTaskdescription("Test Task");
        dto.setColor("#ff0000");

        boolean firstResult = service.addTask(dto);
        boolean secondResult = service.addTask(dto);

        assertTrue(firstResult);
        assertFalse(secondResult);
        assertEquals(1, service.getAllTasks().size());
    }
    
    @Test
    void addTaskShouldUseDefaultColorWhenNoColorIsProvided() {
        TaskRepository repository = new TaskRepository();
        TaskService service = new TaskService(repository);

        TaskDTO dto = new TaskDTO();
        dto.setTaskdescription("Task ohne Farbe");

        service.addTask(dto);

        TaskModel savedTask = service.getAllTasks().get(0);

        assertEquals("#ffffff", savedTask.getColor());
    }
}
