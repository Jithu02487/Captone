package com.ust.Captone.controller;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

import org.apache.http.HttpStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ust.Captone.dto.MyUserDto;
import com.ust.Captone.entity.MyUser;
import com.ust.Captone.entity.Token;
import com.ust.Captone.entity.enm.tokenType;
import com.ust.Captone.repository.MyuserRepo;
import com.ust.Captone.repository.TokenRepo;
import com.ust.Captone.services.EmailService;
import com.ust.Captone.services.MyUserService;

import jakarta.mail.MessagingException;
import jakarta.transaction.Transactional;

@RestController
public class UserController {
	
	@Autowired
	private MyUserService us;
	
	@Autowired
	private MyuserRepo mr;
	
	@Autowired
	private TokenRepo tokenRepository;
	
	@Autowired
	private EmailService emailService;
	
	//User---------------------------------
	
		@PostMapping("/addUser")
		public String addUser(@RequestBody MyUserDto user) {
			return us.signup(user);
		}
		
		@PostMapping("/addUserByHead")
		public String addUserByHead(@RequestBody MyUserDto user) {
			return us.addUser(user);
		}
		
		
		@GetMapping("/users")
		public List<MyUser> displayUsers(){
			return us.findAllUsers();
		}
		
		@DeleteMapping("/users/{teamid}")
		public String UsersByTeam(@PathVariable Long teamid) {
			return us.deleteUser(teamid);
		}
		
		@GetMapping("/users/{id}")
		public MyUser displayUsersByid(@PathVariable Long id){
			return us.findUserByid(id);
		}
		
		@DeleteMapping("/deletUser/{id}")
		public String deleteUser(@PathVariable Long id) {
			return us.deleteUser(id);
		}
		
//		Verify Email
		
		@Transactional
		@GetMapping("/verify")
		public ResponseEntity<String> verifyEmail(@RequestParam String token) {
			Optional<Token> optionalToken = tokenRepository.findByTokenAndType(token, tokenType.VERIFICATION);
	        if (optionalToken.isEmpty()) {
	            return ResponseEntity.badRequest().body("Invalid verification token.");
	        }

	        Token tokenEntity = optionalToken.get();
	        if (tokenEntity.getExpiryDate().isBefore(LocalDateTime.now())) {
	            return ResponseEntity.badRequest().body("Token has expired.");
	        }

	        MyUser user = tokenEntity.getUser();
	        user.setEnabled(true);
	        mr.save(user);

//	        tokenRepository.deleteById(tokenEntity.getId()); // Optional: Cleanup

	        return ResponseEntity.ok("Email verified successfully!");
	    }
		
//		ResetPassword
		@PostMapping("/reset/{email}")
		public String reset(@PathVariable String email) {
			Optional<MyUser> userOpt = mr.findByEmail(email);
			
			if(userOpt.isPresent()) {
				MyUser user = userOpt.get();
				String token = UUID.randomUUID().toString();
				Token resetToken = new Token(token,user,tokenType.PASSWORD_RESET);
				tokenRepository.save(resetToken);
				String body = "<p>Click the link to reset your passwordl:</p>";
				String url = "http://localhost:3000/reset?token=";
				try {
					emailService.sendEmail(email, token, body,url);
				} catch (MessagingException e) {
			        return "Failed to send password reset email: " + e.getMessage();
			    }
			}
			
			return "Reset password email sent";
		}
		
		@PostMapping("/passReset/{token}")
		public ResponseEntity<String> changePass(
		        @PathVariable String token,
		        @RequestBody Map<String, String> body) {

		    String newpass = body.get("newpass");

		    Optional<Token> otoken = tokenRepository.findByTokenAndType(token, tokenType.PASSWORD_RESET);
		    if (otoken.isEmpty()) {
		        return ResponseEntity.status(HttpStatus.SC_BAD_REQUEST).body("Invalid or expired token");
		    }

		    MyUser user = otoken.get().getUser();
		    if (user == null) {
		        return ResponseEntity.status(HttpStatus.SC_BAD_REQUEST).body("User not found");
		    }

		    // You should hash the password before saving it!
		    user.setPassword(newpass);  // assuming you have passwordEncoder bean

		    mr.save(user);

		    return ResponseEntity.ok("Password reset success");
		}


}
