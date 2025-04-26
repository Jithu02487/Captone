package com.ust.Captone.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.ust.Captone.dto.TaskDto;
import com.ust.Captone.entity.Task;
import com.ust.Captone.entity.enm.statuses;
import com.ust.Captone.services.TaskService;

@RestController
public class TaskController {
	
	@Autowired
	private TaskService tsks;
	
	@PostMapping("/addTask")
	public String addTeam(@RequestBody TaskDto task) {
		return tsks.addTask(task);
	}
	
	@GetMapping("/tasks")
	public List<Task> displayTasks(){
		return tsks.findAllTasks();
	}
	
	@GetMapping("/tasksByUserId/{id}")
	public List<Task> displayTasks(@PathVariable Long id){
		return tsks.findTaskById(id);
	}
	
	@DeleteMapping("/deletTask/{id}")
	public String deleteTask(@PathVariable Long id) {
		return tsks.deleteTask(id);
	}
	
	@PutMapping("/updateTask/{id}/{status}")
	public String deleteTask(@PathVariable Long id, @PathVariable statuses status) {
		return tsks.updateTask(id, status);
	}

}
