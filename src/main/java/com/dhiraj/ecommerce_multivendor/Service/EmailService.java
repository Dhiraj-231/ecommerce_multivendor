package com.dhiraj.ecommerce_multivendor.Service;

import org.springframework.mail.MailException;
import org.springframework.mail.MailSendException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class EmailService {

    private final JavaMailSender mailSender;

    public void sendVerificationOtpEmail(String userEmail, String Otp, String subject, String body)
            throws MessagingException {

        try {
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, "utf-8");
            helper.setSubject(subject);
            helper.setText(body);
            helper.setTo(userEmail);
            mailSender.send(message);
        } catch (MailException e) {
            throw new MailSendException("Failed to send verification OTP email.");
        }
    }
}
