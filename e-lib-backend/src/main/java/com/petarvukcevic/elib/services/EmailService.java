package com.petarvukcevic.elib.services;

import com.petarvukcevic.elib.dto.EmailDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmailService {

    private final JavaMailSender mailSender;

    public void sendEmail(EmailDTO email)
    {
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setTo();
        mailMessage.setSubject("eLib: OTP code");
        mailMessage.setText(email.getBody());

        mailSender.send(mailMessage);
    }
}
