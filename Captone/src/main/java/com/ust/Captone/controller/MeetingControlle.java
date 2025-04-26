package com.ust.Captone.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.ust.Captone.dto.MeetingDto;
import com.ust.Captone.entity.Meeting;
import com.ust.Captone.services.Meetingservice;

@RestController
public class MeetingControlle {

	@Autowired
	private Meetingservice ms;
	
	@PostMapping("/addMeeting")
	public String addMeeting(@RequestBody MeetingDto meeting) {
		return ms.addMeeting(meeting);
	}
	
	@GetMapping("/meetings")
	public List<Meeting> displayMeetings(){
		return ms.displayMeetings();
	}
	@DeleteMapping("/deleteMeeting")
	public String deleteMeet(Meeting meeting) {
		return ms.deleteMeeting(meeting);
	}
	
	
}
