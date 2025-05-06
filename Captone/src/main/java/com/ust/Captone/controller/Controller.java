package com.ust.Captone.controller;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ust.Captone.dto.MyUserDto;
import com.ust.Captone.dto.RoomMsgDto;
import com.ust.Captone.entity.ChatMessage;
import com.ust.Captone.entity.MyUser;
import com.ust.Captone.entity.RoomMessages;
import com.ust.Captone.entity.Team;
import com.ust.Captone.entity.Token;
import com.ust.Captone.repository.ChatMessageRepository;
import com.ust.Captone.services.EmailService;
import com.ust.Captone.services.MeetingMsgService;
import com.ust.Captone.services.RoomMsgService;
import com.ust.Captone.services.RoomService;
import com.ust.Captone.services.TokenService;

import jakarta.mail.MessagingException;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class Controller {
	
	
//Services----------------------
	
	@Autowired
	  private ChatMessageRepository repository;
	
	
//	@Autowired
//	private MyuserRepo mur;
	
//	@Autowired
//	private MyUserService us;
	
//	@Autowired
//	private TeamService ts;
	
//	@Autowired
//	private TaskService tsks;
	
//	@Autowired
//	private Meetingservice ms;
	
	@Autowired
	private RoomService rs;
	
	@Autowired
	private MeetingMsgService mms;
	
	@Autowired
	private RoomMsgService rms;
	
	@Autowired
	private SimpMessagingTemplate messagingTemplate;
	
	@Autowired
    private EmailService emailService;
	
	
	
	
//	Chat Messages--------------------------------------
	
	
	@MessageMapping("/chat/team/{teamId}")
	public void sendTeamMessage(@DestinationVariable String teamId, ChatMessage message) {
	    message.setTimestamp(LocalDateTime.now());
	    message.setTeamId(teamId);
	    repository.save(message);

	    // Dynamically send to the correct topic
	    messagingTemplate.convertAndSend("/topic/messages/team/" + teamId, message);
	}

	@GetMapping("/api/messages/team/{teamId}")
	public List<ChatMessage> getTeamMessages(@PathVariable String teamId) {
	  return repository.findByTeamIdOrderByTimestampAsc(teamId);
	}

	
//RoomMessages---------------------------
	
	@PostMapping("/addRoomMsg")
	public String addRoomMsg(@RequestBody RoomMsgDto msg) {
		return rms.addMsg(msg);
	}
	
	@GetMapping("/roomMsgs")
	public List<RoomMessages> displayRoomMsgs(){
		return rms.displayRoomMsgs();
	}

	
	@DeleteMapping("/deleteRoomMsg/{id}")
	public String deleteRoomMsg(@PathVariable Long id) {
		return rms.deleteRoomMsg(id);
	}
	
	
//	Token Managing---------------------------
	
//	@GetMapping("/verify")
//	public ResponseEntity<String> verifyUser(@RequestParam("token") String token) {
//	    Token verificationToken = tr.findByToken(token);
//	    if (verificationToken == null) {
//	        return ResponseEntity.badRequest().body("Invalid token.");
//	    }
//
//	    MyUser user = verificationToken.getUser();
//	    user.setEnabled(true);
//	    mur.save(user);
//	    tr.delete(verificationToken);
//	    return ResponseEntity.ok("Email verified successfully!");
//	}

//	Email Check------------------------------------------------
	
	@Autowired
	private TokenService ts;
	
	@GetMapping("/tokens")
	public List<Token> tokens(){
		return ts.tokens();
	}
	
	@DeleteMapping("/deleteToken")
	public String deleteToken(@RequestParam String token) {
		ts.delete(token);
		System.out.println("token deleted");
		return "token deleted";
	}

}
