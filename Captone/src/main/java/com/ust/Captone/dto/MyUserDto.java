package com.ust.Captone.dto;

import com.ust.Captone.entity.enm.Roles;

public class MyUserDto {
	
	private String name;
	private String email;
	private Roles role;
	private Long teamId;
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
	public Long getTeamId() {
		return teamId;
	}
	public void setTeamId(Long teamId) {
		this.teamId = teamId;
	}

	
}
