package com.ust.Captone.services;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ust.Captone.dto.MeetingDto;
import com.ust.Captone.entity.Meeting;
import com.ust.Captone.entity.Team;
import com.ust.Captone.repository.MeetingRepo;
import com.ust.Captone.repository.TeamRepo;

@Service
public class Meetingservice {
	
	@Autowired
	private MeetingRepo mr;
	
	@Autowired
	private TeamRepo tr;
	
	
	public String addMeeting(MeetingDto meeting) {
		
		Optional<Team> oteam = tr.findById(meeting.getTeamId());

	    if (!oteam.isPresent()) {
	        return "Invalid TeamId";
	    }

	    Team team = oteam.get();

	    Meeting savedMeeting = new Meeting();
	    savedMeeting.setStartedAt(Date.valueOf(LocalDate.now()));
	    savedMeeting.setEndedAt(null); // optional, depends on your DTO
	    savedMeeting.setTopic(meeting.getTopic());
	    savedMeeting.setTeam(team);  // ✅ Owning side

	    mr.save(savedMeeting);

	    return "Meeting added successfully";
		
	}
	
	public String deleteMeeting(Meeting meeting) {
		mr.delete(meeting);
		return "Meeting deleted succesfully";
	}
	
	public List<Meeting> displayMeetings(){
		return mr.findAll();
	}

}
