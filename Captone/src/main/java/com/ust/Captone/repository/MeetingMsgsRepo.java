package com.ust.Captone.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ust.Captone.entity.MeetingMessages;

@Repository
public interface MeetingMsgsRepo extends JpaRepository<MeetingMessages, Long> {

}
