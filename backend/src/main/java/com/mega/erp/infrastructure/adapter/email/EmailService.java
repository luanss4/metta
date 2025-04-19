package com.mega.erp.infrastructure.adapter.email;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

/**
 * Service for sending emails.
 * This service provides methods for sending both simple text emails and HTML emails.
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class EmailService {

    private final JavaMailSender mailSender;

    /**
     * Sends a simple text email.
     *
     * @param to      recipient email address
     * @param subject email subject
     * @param text    email body text
     */
    public void sendSimpleEmail(String to, String subject, String text) {
        log.info("Sending simple email to: {}", to);
        
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setSubject(subject);
        message.setText(text);
        
        mailSender.send(message);
        
        log.info("Simple email sent successfully to: {}", to);
    }

    /**
     * Sends an HTML email.
     *
     * @param to      recipient email address
     * @param subject email subject
     * @param htmlContent email body in HTML format
     * @throws MessagingException if there's an error creating or sending the message
     */
    public void sendHtmlEmail(String to, String subject, String htmlContent) throws MessagingException {
        log.info("Sending HTML email to: {}", to);
        
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");
        
        helper.setTo(to);
        helper.setSubject(subject);
        helper.setText(htmlContent, true);
        
        mailSender.send(message);
        
        log.info("HTML email sent successfully to: {}", to);
    }

    /**
     * Sends a password reset email with a reset token.
     *
     * @param to        recipient email address
     * @param resetToken the password reset token
     * @param baseUrl   the base URL of the application for constructing the reset link
     */
    public void sendPasswordResetEmail(String to, String resetToken, String baseUrl) {
        log.info("Sending password reset email to: {}", to);
        
        String subject = "Password Reset Request";
        String resetUrl = baseUrl + "/reset-password?token=" + resetToken;
        
        String emailContent = "Hello,\n\n" +
                "You have requested to reset your password. Please click on the link below to reset your password:\n\n" +
                resetUrl + "\n\n" +
                "If you did not request a password reset, please ignore this email.\n\n" +
                "Regards,\nMega ERP Team";
        
        sendSimpleEmail(to, subject, emailContent);
        
        log.info("Password reset email sent successfully to: {}", to);
    }
}