package com.ust.Captone.entity;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ust.Captone.entity.enm.Roles;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "Users")
public class MyUser {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String name;
	private String email;
	private Roles role;
	private boolean enabled;
	
	@ManyToOne
	@JoinColumn(name = "team_id")
	private Team team;
	
	@ManyToMany(mappedBy = "users", cascade = CascadeType.ALL)
	@JsonIgnore
	private Set<Task> tasks = new HashSet<Task>();
	
	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
	@JsonIgnore
	private List<MeetingMessages> messages = new ArrayList<MeetingMessages>();
	
	@JsonIgnore
	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
	private List<RoomMessages> msgs = new ArrayList<RoomMessages>();
	
	
	
	public List<MeetingMessages> getMessages() {
		return messages;
	}
	public void setMessages(List<MeetingMessages> messages) {
		this.messages = messages;
	}
	public List<RoomMessages> getMsgs() {
		return msgs;
	}
	public void setMsgs(List<RoomMessages> msgs) {
		this.msgs = msgs;
	}
	public Set<Task> getTasks() {
		return tasks;
	}
	public void setTasks(Set<Task> tasks) {
		this.tasks = tasks;
	}
	
	
	public MyUser(Long id, String name, String email, Roles role, boolean enabled, Team team, Set<Task> tasks,
			List<MeetingMessages> messages, List<RoomMessages> msgs) {
		super();
		this.id = id;
		this.name = name;
		this.email = email;
		this.role = role;
		this.enabled = enabled;
		this.team = team;
		this.tasks = tasks;
		this.messages = messages;
		this.msgs = msgs;
	}
	@Override
	public String toString() {
		return "MyUser [id=" + id + ", name=" + name + ", email=" + email + ", role=" + role + ", team=" + team
				+ ", Tasks="+ tasks+"]";
	}
	public Team getTeam() {
		return team;
	}
	
	public boolean isEnabled() {
		return enabled;
	}
	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}
	public void setTeam(Team team) {
		this.team = team;
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
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Roles getRole() {
		return role;
	}
	public void setRole(Roles role) {
		this.role = role;
	}
	public MyUser() {
	}
}
