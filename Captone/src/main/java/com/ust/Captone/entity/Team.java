package com.ust.Captone.entity;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class Team {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String name;
	private String description;
	private Date createdAt;
	
	@JsonIgnore
	@OneToMany(mappedBy = "team", cascade = CascadeType.ALL)
	List<Meeting> meetings = new ArrayList<Meeting>();
	
	@JsonIgnore
	@OneToMany(mappedBy = "team", cascade = CascadeType.ALL)
	List<MyUser> users = new ArrayList<MyUser>();
	
	@JsonIgnore
	@OneToMany(mappedBy = "team", cascade = CascadeType.ALL)
	List<Room> rooms = new ArrayList<Room>();
	
	public List<Meeting> getMeetings() {
		return meetings;
	}
	public void setMeetings(List<Meeting> meetings) {
		this.meetings = meetings;
	}
	public List<MyUser> getUsers() {
		return users;
	}
	public void setUsers(List<MyUser> users) {
		this.users = users;
	}
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
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Date getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}
	
}
