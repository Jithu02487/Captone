package com.ust.Captone.dto;

import java.sql.Date;

public class MeetingDto {
	
	private String topic;
	private Long teamId;
	public Long getTeamId() {
		return teamId;
	}
	public void setTeamId(Long teamId) {
		this.teamId = teamId;
	}
	public String getTopic() {
		return topic;
	}
	public void setTopic(String topic) {
		this.topic = topic;
	}

}
