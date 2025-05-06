package com.ust.Captone.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ust.Captone.entity.Token;
import com.ust.Captone.repository.TokenRepo;

@Service
public class TokenService {
	
	@Autowired
	private TokenRepo tr;
	
	public List<Token> tokens(){
		return tr.findAll();
	}
	
	public void delete(String token) {
		Optional<Token> tkn = tr.findByToken(token);
		if(tkn.isPresent()) {
			tr.delete(tkn.get());
		}
	}

}
