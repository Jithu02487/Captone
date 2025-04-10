package com.ust.Captone.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ust.Captone.entity.Room;

@Repository
public interface RoomRepo extends JpaRepository<Room, Long>{

}
