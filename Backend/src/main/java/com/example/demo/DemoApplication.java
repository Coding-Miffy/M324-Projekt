package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DemoApplication {
	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}
<<<<<<< HEAD
}
=======

	private List<Task> tasks = new ArrayList<>();

	@CrossOrigin
	@GetMapping("/")
	public List<Task> getTasks() {

		System.out.println("API EP '/' returns task-list of size " + tasks.size() + ".");
		if (tasks.size() > 0) {
			int i = 1;
			for (Task task : tasks) {
				System.out.println("-task " + (i++) + ":" + task.getTaskdescription());
			}
		}
		return tasks; // actual task list (internally converted to a JSON stream)
	}

	@CrossOrigin
	@PostMapping("/tasks")
	public String addTask(@RequestBody String taskdescription) {
		System.out.println("API EP '/tasks': '" + taskdescription + "'");
		ObjectMapper mapper = new ObjectMapper();
		try {
			Task task;
			task = mapper.readValue(taskdescription, Task.class);
			if (task.getColor() == null || task.getColor().isEmpty()) {
			    task.setColor("#ffffff");
			}
			for (Task t : tasks) {
				if (t.getTaskdescription().equals(task.getTaskdescription())) {
					System.out.println(">>>task: '" + task.getTaskdescription() + "' already exists!");
					return "redirect:/"; // duplicates will be ignored
				}
			}
			System.out.println("...adding task: '" + task.getTaskdescription() + "'");
			tasks.add(task);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		return "redirect:/";
	}

	@CrossOrigin
	@PostMapping("/delete")
	public String delTask(@RequestBody String taskdescription) {
		System.out.println("API EP '/delete': '" + taskdescription + "'");
		ObjectMapper mapper = new ObjectMapper();
		try {
			Task task;
			task = mapper.readValue(taskdescription, Task.class);
			Iterator<Task> it = tasks.iterator();
			while (it.hasNext()) {
				Task t = it.next();
				if (t.getTaskdescription().equals(task.getTaskdescription())) {
					System.out.println("...deleting task: '" + task.getTaskdescription() + "'");
					it.remove();
					return "redirect:/";
				}
			}
			System.out.println(">>>task: '" + task.getTaskdescription() + "' not found!");
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		return "redirect:/";
	}

}
>>>>>>> main
