package com.ust.Captone.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class ChatMessage {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String teamId;
  private String sender;
  private String content;
  private LocalDateTime timestamp;

  public ChatMessage() {
    // Default constructor
  }

  public ChatMessage(Long id, String teamId, String sender, String content, LocalDateTime timestamp) {
    this.id = id;
    this.teamId = teamId;
    this.sender = sender;
    this.content = content;
    this.timestamp = timestamp;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getTeamId() {
    return teamId;
  }

  public void setTeamId(String teamId) {
    this.teamId = teamId;
  }

  public String getSender() {
    return sender;
  }

  public void setSender(String sender) {
    this.sender = sender;
  }

  public String getContent() {
    return content;
  }

  public void setContent(String content) {
    this.content = content;
  }

  public LocalDateTime getTimestamp() {
    return timestamp;
  }

  public void setTimestamp(LocalDateTime timestamp) {
    this.timestamp = timestamp;
  }

  @Override
  public String toString() {
    return "ChatMessage [id=" + id + ", teamId=" + teamId + ", sender=" + sender + ", content=" + content
        + ", timestamp=" + timestamp + "]";
  }
}
