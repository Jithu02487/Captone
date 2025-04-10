package com.ust.Captone.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ust.Captone.entity.MyUser;

@Repository
public interface MyuserRepo extends JpaRepository<MyUser, Long>{

}
