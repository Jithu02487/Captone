package com.ust.Captone.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.ust.Captone.dto.RoomDto;
import com.ust.Captone.entity.Room;
import com.ust.Captone.services.RoomService;

@RestController
public class RoomController {

	
	@Autowired
	private RoomService rs;
	
	@PostMapping("/addRoom")
	public String addRoom(@RequestBody RoomDto room) {
		return rs.addRoom(room);
	}
	
	@GetMapping("/rooms")
	public List<Room> displayRooms(){
		return rs.displayRooms();
	}
	
	@DeleteMapping("/deleteRoom/{id}")
	public String deleteRoom(@PathVariable Long id) {
		return rs.deleteRoom(id);
	}
}
