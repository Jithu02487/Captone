package com.ust.Captone.services;

import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;

@Service
public class EmailService {

    private final JavaMailSender javaMailSender;

    // Injecting JavaMailSender through constructor
    public EmailService(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }

    // Method to send an email
    public void sendEmail(String to, String token,String content,String url) throws MessagingException {
        String subject = "Verify Your Email";
        String verificationUrl = url + token;
        String body = content
                    + "<a href=\"" + verificationUrl + "\">Verify Now</a>";

        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);
        helper.setFrom("jithuvudayan02487@gmail.com");
        helper.setTo(to);
        helper.setSubject(subject);
        helper.setText(body, true); // HTML content

        javaMailSender.send(message);
    }
}
