package com.ust.Captone.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ust.Captone.dto.MyUserDto;
import com.ust.Captone.entity.MyUser;
import com.ust.Captone.entity.Task;
import com.ust.Captone.entity.Team;
import com.ust.Captone.repository.MyuserRepo;
import com.ust.Captone.repository.TeamRepo;

@Service
public class MyUserService {
	
	@Autowired
	private MyuserRepo ur;
	
	@Autowired
	private TeamRepo tr;
	
	
//	Adding a new user
	public String addUser(MyUserDto user) {
		Optional<Team>oteam = tr.findById(user.getTeamId());
		if(!oteam.isPresent()) {
			return "Invalid Request: Team is not present";
		}
		
		MyUser savedUser = new MyUser();
		savedUser.setEmail(user.getEmail());
		savedUser.setName(user.getName());
		savedUser.setRole(user.getRole());
		savedUser.setTeam(oteam.get());
		ur.save(savedUser);
		return "Succesfully added User";
		
	}
	
	public List<MyUser> findAllUsers(){
		return ur.findAll();
	}
	
	public String deleteUser(Long id) {
		Optional<MyUser> user = ur.findById(id);
		if(!user.isPresent()){
			return "Invalid user id.";
		}
		
		MyUser duser = user.get();
		ur.delete(duser);
		return "User deleted.";
	}

}
