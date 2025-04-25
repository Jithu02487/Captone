package com.ust.Captone.repository;



import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ust.Captone.entity.MyUser;
import com.ust.Captone.entity.Task;

@Repository
public interface TaskRepo extends JpaRepository<Task, Long>{
	List<Task> findByusers(MyUser user);

}
