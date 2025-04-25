package com.ust.Captone.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ust.Captone.entity.ChatMessage;

public interface ChatMessageRepository extends JpaRepository<ChatMessage, Long> {
		List<ChatMessage> findByTeamIdOrderByTimestampAsc(String teamId);
	}

