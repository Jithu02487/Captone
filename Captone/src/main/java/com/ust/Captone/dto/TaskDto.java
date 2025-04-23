package com.ust.Capstone.dto;

import java.sql.Date;

import com.ust.Captone.entity.enm.statuses;

public class TaskDto {
	private String title;
	private String description;
	private statuses status;
	private Date  dueDate;
	private Long userId;
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public statuses getStatus() {
		return status;
	}
	public void setStatus(statuses status) {
		this.status = status;
	}
	public Date getDueDate() {
		return dueDate;
	}
	public void setDueDate(Date dueDate) {
		this.dueDate = dueDate;
	}
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	
	
	
}
