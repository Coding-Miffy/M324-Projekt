package com.example.demo;

import com.example.demo.dto.TaskDTO;
import com.example.demo.models.TaskModel;
import com.example.demo.models.EnumPriority;
import com.example.demo.repository.TaskRepository;
import com.example.demo.service.TaskService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class TaskServiceTest {

	@Mock
	private TaskRepository taskRepository;

	@InjectMocks
	private TaskService taskService;

	private TaskDTO dto;

	@BeforeEach
	void setUp() {
		dto = new TaskDTO();
		dto.setTaskdescription("Test Task");
		dto.setColor("#ff0000");
	}

	@Test
	void addTask_shouldReturnTrue_whenTaskIsNew() {
		when(taskRepository.existsByDescription("Test Task")).thenReturn(false);

		boolean result = taskService.addTask(dto);

		assertTrue(result);
		verify(taskRepository, times(1)).save(any(TaskModel.class));
	}

	@Test
	void addTask_shouldReturnFalse_whenTaskAlreadyExists() {
		when(taskRepository.existsByDescription("Test Task")).thenReturn(true);

		boolean result = taskService.addTask(dto);

		assertFalse(result);
		verify(taskRepository, never()).save(any(TaskModel.class));
	}

	@Test
	void deleteTask_shouldReturnTrue_whenTaskExists() {
		when(taskRepository.deleteByDescription("Test Task")).thenReturn(true);

		boolean result = taskService.deleteTask(dto);

		assertTrue(result);
		verify(taskRepository, times(1)).deleteByDescription("Test Task");
	}

	@Test
	void deleteTask_shouldReturnFalse_whenTaskNotFound() {
		when(taskRepository.deleteByDescription("Test Task")).thenReturn(false);

		boolean result = taskService.deleteTask(dto);

		assertFalse(result);
	}

	@Test
	void getAllTasks_shouldReturnList() {
		TaskModel model = new TaskModel("Test Task", "#ff0000", EnumPriority.LOW);
		when(taskRepository.findAll()).thenReturn(List.of(model));

		List<TaskModel> result = taskService.getAllTasks();

		assertEquals(1, result.size());
		assertEquals("Test Task", result.get(0).getTaskdescription());
	}
}