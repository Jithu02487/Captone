package com.ust.Captone.entity;

import java.sql.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class MeetingMessages {
		
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String message;
	private Date sentAt;
	
	@ManyToOne
	@JoinColumn(name = "meeting_id")
	private Meeting meeting;
	
	@ManyToOne
	@JoinColumn(name = "user_id")
	private MyUser user;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Date getSentAt() {
		return sentAt;
	}

	public void setSentAt(Date sentAt) {
		this.sentAt = sentAt;
	}

	public Meeting getMeeting() {
		return meeting;
	}

	public void setMeeting(Meeting meeting) {
		this.meeting = meeting;
	}

	public MyUser getUser() {
		return user;
	}

	public void setUser(MyUser user) {
		this.user = user;
	}

	@Override
	public String toString() {
		return "MeetingDetails [id=" + id + ", message=" + message + ", sentAt=" + sentAt + ", meeting=" + meeting
				+ ", user=" + user + "]";
	}

	public MeetingMessages(Long id, String message, Date sentAt, Meeting meeting, MyUser user) {
		super();
		this.id = id;
		this.message = message;
		this.sentAt = sentAt;
		this.meeting = meeting;
		this.user = user;
	}

	public MeetingMessages() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	
}
