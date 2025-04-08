package com.ust.Captone.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class UserDetails {

	@Id
	private Long id;
	private String workingHoursStart;
	private String workingHoursEnd;
	private String status;
	
	@ManyToOne
	@JoinColumn(name = "user_id")
	private MyUser user;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getWorkingHoursStart() {
		return workingHoursStart;
	}

	public void setWorkingHoursStart(String workingHoursStart) {
		this.workingHoursStart = workingHoursStart;
	}

	public String getWorkingHoursEnd() {
		return workingHoursEnd;
	}

	public void setWorkingHoursEnd(String workingHoursEnd) {
		this.workingHoursEnd = workingHoursEnd;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public MyUser getUser() {
		return user;
	}

	public void setUser(MyUser user) {
		this.user = user;
	}

	@Override
	public String toString() {
		return "UserDetails [id=" + id + ", workingHoursStart=" + workingHoursStart + ", workingHoursEnd="
				+ workingHoursEnd + ", status=" + status + ", user=" + user + "]";
	}

	public UserDetails(Long id, String workingHoursStart, String workingHoursEnd, String status, MyUser user) {
		super();
		this.id = id;
		this.workingHoursStart = workingHoursStart;
		this.workingHoursEnd = workingHoursEnd;
		this.status = status;
		this.user = user;
	}

	public UserDetails() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
}
