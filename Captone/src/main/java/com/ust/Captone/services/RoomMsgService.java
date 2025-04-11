package com.ust.Captone.services;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ust.Captone.dto.RoomMsgDto;
import com.ust.Captone.entity.MyUser;
import com.ust.Captone.entity.Room;
import com.ust.Captone.entity.RoomMessages;
import com.ust.Captone.repository.MyuserRepo;
import com.ust.Captone.repository.RoomMsgsRepo;
import com.ust.Captone.repository.RoomRepo;

@Service
public class RoomMsgService {
	
	@Autowired
	private RoomMsgsRepo rmr;
	
	@Autowired
	private RoomRepo rr;
	
	@Autowired
	private MyuserRepo mur;
	
	public String addMsg(RoomMsgDto msg) {
		
		Optional<MyUser> ouser = mur.findById(msg.getUserId());
		Optional<Room> oroom = rr.findById(msg.getRoomId());
		
		if(!ouser.isPresent()) {
			
			return "Invalid UserId...";
		}
		if(!oroom.isPresent()) {
			return "Invalid RoomId...";
		}
		
		MyUser user = ouser.get();
		Room room = oroom.get();
		
		RoomMessages roomMsg = new RoomMessages();
		roomMsg.setDate(Date.valueOf(LocalDate.now()));
		roomMsg.setMessage(msg.getMessage());
		roomMsg.setRoom(room);
		roomMsg.setUser(user);
		
		rmr.save(roomMsg);
		return "Messaged in Room...";
		
	}
	
	public String deleteRoomMsg(Long id) {
		rmr.deleteById(id);
		return "Message deleted...";
	}
	
	public List<RoomMessages> displayRoomMsgs(){
		return rmr.findAll();
	}

}
