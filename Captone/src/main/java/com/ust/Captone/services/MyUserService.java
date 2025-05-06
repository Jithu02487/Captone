package com.ust.Captone.services;

import java.io.Console;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import com.ust.Captone.dto.MyUserDto;
import com.ust.Captone.entity.MyUser;
import com.ust.Captone.entity.Team;
import com.ust.Captone.entity.Token;
import com.ust.Captone.entity.enm.tokenType;
import com.ust.Captone.repository.MyuserRepo;
import com.ust.Captone.repository.TeamRepo;
import com.ust.Captone.repository.TokenRepo;

import jakarta.mail.MessagingException;

@Service
public class MyUserService {
	
	@Autowired
	private MyuserRepo ur;
	
	@Autowired
	private TeamRepo tr;
	
	@Autowired
    private TokenRepo verificationTokenRepository;
	
	@Autowired
	private EmailService emailService;
	


	
	
//	Signuping a new user
	public String signup(MyUserDto user) {
	    Optional<Team> oteam = tr.findById(user.getTeamId());
	    if (!oteam.isPresent()) {
	        return "Invalid Request: Team is not present";
	    }

	    // Create and save user
	    MyUser savedUser = new MyUser();
	    savedUser.setEmail(user.getEmail());
	    savedUser.setPassword(user.getPassword());
	    savedUser.setName(user.getName());
	    savedUser.setRole(user.getRole());
	    savedUser.setTeam(oteam.get());
	    savedUser.setEnabled(false); // Ensure user starts as unverified
	    ur.save(savedUser);

	    // Generate token and save
	    String token = UUID.randomUUID().toString();
	    System.out.println("Tocken is "+ token);
	    Token verificationToken = new Token(token, savedUser,tokenType.VERIFICATION);
	    verificationTokenRepository.save(verificationToken);
	    System.out.println("Saved token ID: " + verificationToken.getId());
	    
	    String body = "<p>Click the link to verify your email:</p>";
	    String url = "http://localhost:3000/verify?token=";

	    // Send verification email
	    try {
	        emailService.sendEmail(user.getEmail(), token,body,url);
	    } catch (MessagingException e) {
	        return "User created, but failed to send verification email: " + e.getMessage();
	    }

	    return "User created successfully. Verification email sent.";
	}

	
//	Adding New User
	public String addUser(MyUserDto user) {
	    Optional<Team> oteam = tr.findById(user.getTeamId());
	    if (!oteam.isPresent()) {
	        return "Invalid Request: Team is not present";
	    }

	    // Create and save user
	    MyUser savedUser = new MyUser();
	    savedUser.setEmail(user.getEmail());
	    savedUser.setPassword(user.getPassword());
	    savedUser.setName(user.getName());
	    savedUser.setRole(user.getRole());
	    savedUser.setTeam(oteam.get());
	    savedUser.setEnabled(false); // Ensure user starts as unverified
	    ur.save(savedUser);

	    // Generate token and save
	    String token = UUID.randomUUID().toString();
	    System.out.println("Tocken is "+ token);
	    Token verificationToken = new Token(token, savedUser,tokenType.VERIFICATION);
	    verificationTokenRepository.save(verificationToken);
	    System.out.println("Saved token ID: " + verificationToken.getId());
	    
	    String password = user.getPassword();  // your generated password

	    String body = "<p>Hi,</p>"
	                + "<p>You have been added to a team on <strong>TaskPulse</strong>. Here is your first (temporary) password:</p>"
	                + "<p><strong>" + password + "</strong></p>"
	                + "<p><strong>Important:</strong> Please do not share this email or your password with anyone.</p>"
	                + "<p>To activate your account, please verify your email by clicking the link below:</p>"
	                + "<p>After verifying your email, we strongly recommend that you reset your password to keep your account secure.</p>"
	                + "<p>Welcome to the team!</p>"
	                + "<p>— The TaskPulse Team</p>";

	    String url = "http://localhost:3000/verify?token=";

	    // Send verification email
	    try {
	        emailService.sendEmail(user.getEmail(), token,body,url);
	    } catch (MessagingException e) {
	        return "User created, but failed to send verification email: " + e.getMessage();
	    }

	    return "User created successfully. Verification email sent.";
	}
	
	public List<MyUser> findAllUsers(){
		return ur.findAll();
	}
	
	public String deleteUser(Long id) {
		Optional<MyUser> user = ur.findById(id);
		if(!user.isPresent()){
			return "Invalid user id.";
		}
		
		MyUser duser = user.get();
		ur.delete(duser);
		return "User deleted.";
	}
	
	public MyUser findUserByid(Long id) {
		return ur.findById(id).get();
	}
	

}
