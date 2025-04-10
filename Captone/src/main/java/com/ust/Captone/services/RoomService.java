package com.ust.Captone.services;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ust.Captone.dto.RoomDto;
import com.ust.Captone.entity.Room;
import com.ust.Captone.entity.Team;
import com.ust.Captone.repository.RoomRepo;
import com.ust.Captone.repository.TeamRepo;

@Service
public class RoomService {
	
	@Autowired
	private RoomRepo rr;
	
	@Autowired
	private TeamRepo tr;
	
	
	public String addRoom(RoomDto room) {
		Optional<Team> oteam = tr.findById(room.getTeamId());
		
		if(!oteam.isPresent()) {
			return "Invalid team id.";
		}
		
		Team team = new Team();
		team=oteam.get();
		
		Room savedRoom = new Room();
		savedRoom.setCreatedAt(Date.valueOf(LocalDate.now()));
		savedRoom.setName(room.getName());
		savedRoom.setTeam(team);
		savedRoom.setTopic(room.getTopic());
		
		rr.save(savedRoom);
		return "Room created";
	}

	
	public String deleteRoom(Long id) {
		Optional<Room> oroom = rr.findById(id);
		if(!oroom.isPresent()) {
			return "Invalid room id";
		}
		Room room =oroom.get();
		rr.delete(room);
		return "Room deleted";
		
	}
	
	public List<Room> displayRooms(){
		return rr.findAll();
	}
}
