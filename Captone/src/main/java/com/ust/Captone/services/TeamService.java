package com.ust.Captone.services;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ust.Captone.dto.TeamDto;
import com.ust.Captone.entity.MyUser;
import com.ust.Captone.entity.Team;
import com.ust.Captone.repository.TeamRepo;

@Service
public class TeamService {
	
	@Autowired
	private TeamRepo tr;
	
	public String addTeam(TeamDto team) {
		Team savedTeam = new Team();
		savedTeam.setName(team.getName());
		savedTeam.setDescription(team.getDescription());
		savedTeam.setCreatedAt(Date.valueOf(LocalDate.now()));
		Team TeamSaved = tr.save(savedTeam);
		if(TeamSaved!=null) {
			return "Succesfully added new team";
		}
		return "Team add failed";
	}
	
	public List<Team> findAllTeams(){
		return tr.findAll();
	}

}
