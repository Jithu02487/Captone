//package com.ust.Captone.entity;
//
//import java.time.LocalDateTime;
//
//
//import jakarta.persistence.Entity;
//import jakarta.persistence.GeneratedValue;
//import jakarta.persistence.GenerationType;
//import jakarta.persistence.Id;
//import jakarta.persistence.JoinColumn;
//import jakarta.persistence.OneToOne;
//
//@Entity
//public class Token {
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;
//
//    private String token;
//
//    private LocalDateTime expiryDate;
//
//    @OneToOne
//    @JoinColumn(name = "user_id")
//    private MyUser user;
//
//    public Token() {}
//
//    public Token(String token, MyUser user) {
//        this.token = token;
//        this.user = user;
//        this.expiryDate = LocalDateTime.now().plusHours(24); // Token valid for 24 hours
//    }
//
//    // Getters and setters
//    public Long getId() {
//        return id;
//    }
//
//    public String getToken() {
//        return token;
//    }
//
//    public void setToken(String token) {
//        this.token = token;
//    }
//
//    public LocalDateTime getExpiryDate() {
//        return expiryDate;
//    }
//
//    public void setExpiryDate(LocalDateTime expiryDate) {
//        this.expiryDate = expiryDate;
//    }
//
//    public MyUser getUser() {
//        return user;
//    }
//
//    public void setUser(MyUser user) {
//        this.user = user;
//    }
//}
//
