package com.ust.Captone.entity;

import java.sql.Date;
import java.util.HashSet;
import java.util.Set;

import com.ust.Captone.entity.enm.statuses;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;

@Entity
public class Task {
	
	@Id
	private Long id;
	private String title;
	private String description;
	private statuses status;
	private Date  dueDate;
	private Date  endeddate;
	private Date  createdDate;
	private Date  updatedDate;
	
	@ManyToMany
	@JoinTable(name = "user_task", 
	joinColumns = @JoinColumn(name = "user_id"),
	inverseJoinColumns = @JoinColumn(name = "task_id"))
	private Set<MyUser> users = new HashSet<MyUser>();
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
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
	public Date getEndeddate() {
		return endeddate;
	}
	public void setEndeddate(Date endeddate) {
		this.endeddate = endeddate;
	}
	public Date getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}
	public Date getUpdatedDate() {
		return updatedDate;
	}
	public void setUpdatedDate(Date updatedDate) {
		this.updatedDate = updatedDate;
	}
	@Override
	public String toString() {
		return "Task [id=" + id + ", title=" + title + ", description=" + description + ", status=" + status
				 + ", dueDate=" + dueDate + ", endeddate=" + endeddate + ", createdDate="
				+ createdDate + ", updatedDate=" + updatedDate + "]";
	}
	public Task(Long id, String title, String description, statuses status, Date dueDate, Date endeddate,
			Date createdDate, Date updatedDate) {
		super();
		this.id = id;
		this.title = title;
		this.description = description;
		this.status = status;
		this.dueDate = dueDate;
		this.endeddate = endeddate;
		this.createdDate = createdDate;
		this.updatedDate = updatedDate;
	}
	public Task() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	

}
