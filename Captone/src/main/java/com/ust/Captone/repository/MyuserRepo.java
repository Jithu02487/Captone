package com.ust.Captone.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ust.Captone.entity.MyUser;

@Repository
public interface MyuserRepo extends JpaRepository<MyUser, Long>{
	
	Optional<MyUser> findByEmail(String email);
	List<MyUser> findByTeamId(Long teamId);


}
