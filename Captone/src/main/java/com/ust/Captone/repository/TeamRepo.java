package com.ust.Captone.repository;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ust.Captone.entity.Team;

@Repository
public interface TeamRepo extends JpaRepository<Team, Long>{

}
