package com.ust.Captone.services;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import com.ust.Captone.dto.TaskDto;
import com.ust.Captone.entity.MyUser;
import com.ust.Captone.entity.Task;
import com.ust.Captone.entity.enm.statuses;
import com.ust.Captone.repository.MyuserRepo;
import com.ust.Captone.repository.TaskRepo;

@Service
public class TaskService {
	
	@Autowired
	private MyuserRepo ur;
	
	@Autowired
	private TaskRepo tskr;
	
	public String addTask(TaskDto task) {
		
		Optional<MyUser> ouser =  ur.findById(task.getUserId());
		
		if(!ouser.isPresent()) {
			return "Task add failed : Invalid user id";
		}
		
		MyUser user = ouser.get();
		Task stask = new Task();
		stask.setCreatedDate(Date.valueOf(LocalDate.now()));
		stask.setDescription(task.getDescription());
		stask.setDueDate(task.getDueDate());
		stask.setStatus(task.getStatus());
		stask.setTitle(task.getTitle());

		tskr.save(stask);
		
		user.getTasks().add(stask);
		stask.getUsers().add(user);
		ur.save(user);
		return "Task added succesfully";
		
	}
	
	public List<Task> findAllTasks(){
		return tskr.findAll();
	}
	
	public String deleteTask(Long id) {
		Optional<Task> task = tskr.findById(id);
		if(!task.isPresent()){
			return "Invalid task id.";
		}
		
		Task dtask = task.get();
		tskr.delete(dtask);
		return "Task deleted.";
	}

}
