package com.ust.Captone.services;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ust.Captone.dto.MeetingMsgDto;
import com.ust.Captone.entity.Meeting;
import com.ust.Captone.entity.MeetingMessages;
import com.ust.Captone.entity.MyUser;
import com.ust.Captone.entity.Team;
import com.ust.Captone.repository.MeetingMsgsRepo;
import com.ust.Captone.repository.MeetingRepo;
import com.ust.Captone.repository.MyuserRepo;

@Service
public class MeetingMsgService {
	
	@Autowired
	private MeetingMsgsRepo mmr;
	
	@Autowired
	private MeetingRepo mr;
	
	@Autowired
	private MyuserRepo mur;
	
	
	public String addMessage(MeetingMsgDto msg) {
		Optional<Meeting> omeeting = mr.findById(msg.getMeetingId());
		Optional<MyUser> ouser = mur.findById(msg.getUserId());
		if(!omeeting.isPresent()) {
			return "Invalid MeetId";
		}else if(!ouser.isPresent()) {
			return "Invalid UserId";
		}
		
		Meeting meeting = omeeting.get();
		MeetingMessages message = new MeetingMessages();
		message.setMeeting(meeting);
		message.setMessage(msg.getMessage());
		message.setSentAt(Date.valueOf(LocalDate.now()));
		message.setUser(ouser.get());
		
		mmr.save(message);
		return "Messaged..!";
	}
	
	public List<MeetingMessages> displayMsgs(){
		return mmr.findAll();
	}
	
	public String deleteMsg(Long id) {
		Optional<MeetingMessages> msg = mmr.findById(id);
		if(!msg.isPresent()) {
			return "Invalid msg Id..";
		}
		
		mmr.deleteById(id);
		return "Message Deleted..";
	}

}
