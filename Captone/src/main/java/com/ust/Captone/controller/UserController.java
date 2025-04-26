package com.ust.Captone.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.ust.Captone.dto.MyUserDto;
import com.ust.Captone.entity.MyUser;
import com.ust.Captone.services.MyUserService;

@RestController
public class UserController {
	
	@Autowired
	private MyUserService us;
	
	//User---------------------------------
	
		@PostMapping("/addUser")
		public String addTeam(@RequestBody MyUserDto user) {
			return us.addUser(user);
		}
		
		
		@GetMapping("/users")
		public List<MyUser> displayUsers(){
			return us.findAllUsers();
		}
		
		@GetMapping("/users/{id}")
		public MyUser displayUsersByid(@PathVariable Long id){
			return us.findUserByid(id);
		}
		
		@DeleteMapping("/deletUser/{id}")
		public String deleteUser(@PathVariable Long id) {
			return us.deleteUser(id);
		}

}
