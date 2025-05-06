package com.ust.Captone.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ust.Captone.entity.Token;
import com.ust.Captone.entity.enm.tokenType;

@Repository
public interface TokenRepo extends JpaRepository<Token, Long>{
	Optional<Token> findByToken(String token);
	Optional<Token> findByTokenAndType(String token, tokenType tokenType);

}
