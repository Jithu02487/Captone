package com.ust.Captone.controller;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ust.Captone.dto.MeetingDto;
import com.ust.Captone.dto.MeetingMsgDto;
import com.ust.Captone.dto.MyUserDto;
import com.ust.Captone.dto.RoomDto;
import com.ust.Captone.dto.RoomMsgDto;
import com.ust.Captone.dto.TaskDto;
import com.ust.Captone.dto.TeamDto;
import com.ust.Captone.entity.ChatMessage;
import com.ust.Captone.entity.Meeting;
import com.ust.Captone.entity.MeetingMessages;
import com.ust.Captone.entity.MyUser;
import com.ust.Captone.entity.Room;
import com.ust.Captone.entity.RoomMessages;
import com.ust.Captone.entity.Task;
import com.ust.Captone.entity.Team;
import com.ust.Captone.entity.enm.statuses;
import com.ust.Captone.repository.ChatMessageRepository;
import com.ust.Captone.repository.MyuserRepo;
import com.ust.Captone.services.EmailService;
import com.ust.Captone.services.MeetingMsgService;
import com.ust.Captone.services.Meetingservice;
import com.ust.Captone.services.MyUserService;
import com.ust.Captone.services.RoomMsgService;
import com.ust.Captone.services.RoomService;
import com.ust.Captone.services.TaskService;
import com.ust.Captone.services.TeamService;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class Controller {
	
	
//Services----------------------
	
	@Autowired
	  private ChatMessageRepository repository;
	
	
//	@Autowired
//	private TokenRepo tr;
	
	@Autowired
	private MyuserRepo mur;
	
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

	
	
	
	
//Team--------------------------------------------
	
//	@PostMapping("/addTeam")
//	public String addTeam(@RequestBody TeamDto team) {
//		return ts.addTeam(team);
//	}
//	
//	@GetMapping("/teams")
//	public List<Team> displayTeams(){
//		return ts.findAllTeams();
//	}
//	
//	@DeleteMapping("/deletTeam/{id}")
//	public String deleteTeam(@PathVariable Long id) {
//		return ts.deleteTeam(id);
//	}
	
//Meeting-----------------------------------------
	
//	@PostMapping("/addMeeting")
//	public String addMeeting(@RequestBody MeetingDto meeting) {
//		return ms.addMeeting(meeting);
//	}
//	
//	@GetMapping("/meetings")
//	public List<Meeting> displayMeetings(){
//		return ms.displayMeetings();
//	}
//	@DeleteMapping("/deleteMeeting")
//	public String deleteMeet(Meeting meeting) {
//		return ms.deleteMeeting(meeting);
//	}
	
//MeetingMessage---------------------
	
//	@PostMapping("/addMeetMsg")
//	public String addMeetMsg(@RequestBody MeetingMsgDto msg) {
//		return mms.addMessage(msg);
//	}
//		
//	@GetMapping("/meetMsgs")
//	public List<MeetingMessages> msgs(){
//		return mms.displayMsgs();
//	}
//		
//	@DeleteMapping("/deletMeetingMsg/{id}")
//	public String deleteMsg(@PathVariable Long id) {
//		return mms.deleteMsg(id);
//	}
	
	
//User---------------------------------
	
//	@PostMapping("/addUser")
//	public String addTeam(@RequestBody MyUserDto user) {
//		return us.addUser(user);
//	}
//	
//	
//	@GetMapping("/users")
//	public List<MyUser> displayUsers(){
//		return us.findAllUsers();
//	}
//	
//	@GetMapping("/users/{id}")
//	public MyUser displayUsersByid(@PathVariable Long id){
//		return us.findUserByid(id);
//	}
//	
//	@DeleteMapping("/deletUser/{id}")
//	public String deleteUser(@PathVariable Long id) {
//		return us.deleteUser(id);
//	}
//	
	
//Task------------------
	
//	@PostMapping("/addTask")
//	public String addTeam(@RequestBody TaskDto task) {
//		return tsks.addTask(task);
//	}
//	
//	@GetMapping("/tasks")
//	public List<Task> displayTasks(){
//		return tsks.findAllTasks();
//	}
//	
//	@GetMapping("/tasksByUserId/{id}")
//	public List<Task> displayTasks(@PathVariable Long id){
//		return tsks.findTaskById(id);
//	}
//	
//	@DeleteMapping("/deletTask/{id}")
//	public String deleteTask(@PathVariable Long id) {
//		return tsks.deleteTask(id);
//	}
//	
//	@PutMapping("/updateTask/{id}/{status}")
//	public String deleteTask(@PathVariable Long id, @PathVariable statuses status) {
//		return tsks.updateTask(id, status);
//	}
	
//Room---------------------------
	
//	@PostMapping("/addRoom")
//	public String addRoom(@RequestBody RoomDto room) {
//		return rs.addRoom(room);
//	}
//	
//	@GetMapping("/rooms")
//	public List<Room> displayRooms(){
//		return rs.displayRooms();
//	}
//	
//	@DeleteMapping("/deleteRoom/{id}")
//	public String deleteRoom(@PathVariable Long id) {
//		return rs.deleteRoom(id);
//	}
	
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
	
	@PostMapping("/send")
    public ResponseEntity<String> sendEmail(
            @RequestParam String to,
            @RequestParam String subject,
            @RequestParam String body) {
        try {
            emailService.sendEmail(to, subject, body);
            return ResponseEntity.ok("Email sent successfully via SendGrid!");
        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body("Failed to send email: " + e.getMessage());
        }
    }
}
