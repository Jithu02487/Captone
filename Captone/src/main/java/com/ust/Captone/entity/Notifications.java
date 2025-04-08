package com.ust.Captone.entity;

import java.sql.Date;

import com.ust.Captone.entity.enm.Type;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Notifications {

	@Id
	private Long id;
	private Type type;
	private String message;
	private Date timeToSend;
	private boolean delivered;
	
	@ManyToOne
	@JoinColumn(name = "user_id")
	private MyUser user;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Type getType() {
		return type;
	}

	public void setType(Type type) {
		this.type = type;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Date getTimeToSend() {
		return timeToSend;
	}

	public void setTimeToSend(Date timeToSend) {
		this.timeToSend = timeToSend;
	}

	public boolean isDelivered() {
		return delivered;
	}

	public void setDelivered(boolean delivered) {
		this.delivered = delivered;
	}

	public MyUser getUser() {
		return user;
	}

	public void setUser(MyUser user) {
		this.user = user;
	}

	@Override
	public String toString() {
		return "Notifications [id=" + id + ", type=" + type + ", message=" + message + ", timeToSend=" + timeToSend
				+ ", delivered=" + delivered + ", user=" + user + "]";
	}

	public Notifications(Long id, Type type, String message, Date timeToSend, boolean delivered, MyUser user) {
		super();
		this.id = id;
		this.type = type;
		this.message = message;
		this.timeToSend = timeToSend;
		this.delivered = delivered;
		this.user = user;
	}

	public Notifications() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	
}
