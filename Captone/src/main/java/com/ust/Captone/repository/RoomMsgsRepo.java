package com.ust.Captone.repository;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ust.Captone.entity.RoomMessages;

@Repository
public interface RoomMsgsRepo extends JpaRepository<RoomMessages, Long>{

}
