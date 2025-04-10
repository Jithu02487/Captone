package com.ust.Captone.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ust.Captone.entity.Meeting;

@Repository
public interface MeetingRepo extends JpaRepository<Meeting, Long>{

}
