package com.ust.Captone.entity;

import java.util.HashSet;
import java.util.Set;

import com.ust.Captone.entity.enm.Roles;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "Users")
public class MyUser {
	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	private Long id;
	private String name;
	private String email;
	private Roles role;
	
	@ManyToOne
	@JoinColumn(name = "team_id")
	private Team team;
	
	@ManyToMany(mappedBy = "users")
	private Set<Task> tasks = new HashSet<Task>();
	
	public MyUser(Long id, String name, String email, Roles role, Team team) {
		super();
		this.id = id;
		this.name = name;
		this.email = email;
		this.role = role;
		this.team = team;
	}
	public Team getTeam() {
		return team;
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
