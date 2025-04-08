package com.ust.Captone.entity;

import java.sql.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Room {
	
	@Id
	private Long id;
	private String name;
	private String topic;
	private Date createdAt;
	
	@ManyToOne
	@JoinColumn(name = "team_id")
	private Team team;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTopic() {
		return topic;
	}

	public void setTopic(String topic) {
		this.topic = topic;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public Team getTeam() {
		return team;
	}

	public void setTeam(Team team) {
		this.team = team;
	}

	@Override
	public String toString() {
		return "Room [id=" + id + ", name=" + name + ", topic=" + topic + ", createdAt=" + createdAt + ", team=" + team
				+ "]";
	}

	public Room(Long id, String name, String topic, Date createdAt, Team team) {
		super();
		this.id = id;
		this.name = name;
		this.topic = topic;
		this.createdAt = createdAt;
		this.team = team;
	}

	public Room() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	

}
