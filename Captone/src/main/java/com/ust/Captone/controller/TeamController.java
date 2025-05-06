package com.ust.Captone.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.ust.Captone.dto.TeamDto;
import com.ust.Captone.entity.Team;
import com.ust.Captone.services.TeamService;

@RestController
public class TeamController {
	
	@Autowired
	private TeamService ts;
	
	@PostMapping("/addTeam")
	public Team addTeam(@RequestBody TeamDto team) {
		return ts.addTeam(team);
	}
	
	@GetMapping("/teams")
	public List<Team> displayTeams(){
		return ts.findAllTeams();
	}
	
	@DeleteMapping("/deletTeam/{id}")
	public String deleteTeam(@PathVariable Long id) {
		return ts.deleteTeam(id);
	}

}
