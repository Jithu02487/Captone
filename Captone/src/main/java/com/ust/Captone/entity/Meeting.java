package com.ust.Captone.entity;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

@Entity
public class Meeting {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String topic;
	private Date startedAt;
	private Date endedAt;

	@ManyToOne
	@JoinColumn(name = "team_id")
	private Team team;
	
//	@OneToMany(mappedBy = "meeting", cascade = CascadeType.ALL)
//	List<MeetingMessages> details = new ArrayList<MeetingMessages>();

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTopic() {
		return topic;
	}

	public void setTopic(String topic) {
		this.topic = topic;
	}

	public Date getStartedAt() {
		return startedAt;
	}

	public void setStartedAt(Date startedAt) {
		this.startedAt = startedAt;
	}

	public Date getEndedAt() {
		return endedAt;
	}

	public void setEndedAt(Date endedAt) {
		this.endedAt = endedAt;
	}

	public Team getTeam() {
		return team;
	}

	public void setTeam(Team team) {
		this.team = team;
	}

	@Override
	public String toString() {
		return "Meeting [id=" + id + ", topic=" + topic + ", startedAt=" + startedAt + ", endedAt="
				+ endedAt + ", team=" + team + "]";
	}

	public Meeting(Long id, Date date, String topic, Date startedAt, Date endedAt, Team team) {
		super();
		this.id = id;
		this.topic = topic;
		this.startedAt = startedAt;
		this.endedAt = endedAt;
		this.team = team;
	}

	public Meeting() {
		super();
		// TODO Auto-generated constructor stub
	}
	

}
