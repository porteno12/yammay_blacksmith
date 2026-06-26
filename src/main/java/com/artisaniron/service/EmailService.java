package com.artisaniron.service;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class EmailService {
    private static final Logger logger = LoggerFactory.getLogger(EmailService.class);
    private final JavaMailSender mailSender;
    private static final String FROM_EMAIL = "forge@artisaniron.com";

    public EmailService(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    public void sendContactConfirmation(String toEmail, String senderName) {
        try {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setFrom(FROM_EMAIL);
            message.setTo(toEmail);
            message.setSubject("הודעתך התקבלה - Artisan Iron");
            message.setText("שלום " + senderName + ",\n\nתודה על פנייתך. אנחנו נחזור אליך בקרוב.\n\nArtisan Iron");

            mailSender.send(message);
            logger.info("Contact confirmation email sent to: {}", toEmail);
        } catch (Exception e) {
            logger.error("Failed to send contact confirmation email", e);
        }
    }

    public void sendContactNotificationToAdmin(String senderName, String senderEmail, String senderPhone, String message) {
        try {
            SimpleMailMessage adminMessage = new SimpleMailMessage();
            adminMessage.setFrom(FROM_EMAIL);
            adminMessage.setTo(FROM_EMAIL);
            adminMessage.setSubject("הודעה חדשה מ-" + senderName);
            adminMessage.setText(
                    "שם: " + senderName + "\n" +
                    "דוא״ל: " + senderEmail + "\n" +
                    "טלפון: " + (senderPhone != null ? senderPhone : "לא צוין") + "\n\n" +
                    "הודעה:\n" + message
            );

            mailSender.send(adminMessage);
            logger.info("Contact notification sent to admin from: {}", senderEmail);
        } catch (Exception e) {
            logger.error("Failed to send contact notification to admin", e);
        }
    }

    public void sendInquiryConfirmation(String toEmail, String customerName) {
        try {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setFrom(FROM_EMAIL);
            message.setTo(toEmail);
            message.setSubject("בקשתך לעבודה מותאמת התקבלה - Artisan Iron");
            message.setText("שלום " + customerName + ",\n\nתודה על בקשתך. אנחנו נבחן את פרטי הפרויקט שלך ונחזור אליך בקרוב עם הצעת מחיר.\n\nArtisan Iron");

            mailSender.send(message);
            logger.info("Inquiry confirmation email sent to: {}", toEmail);
        } catch (Exception e) {
            logger.error("Failed to send inquiry confirmation email", e);
        }
    }

    public void sendInquiryNotificationToAdmin(String customerName, String customerEmail, String productType, String description) {
        try {
            SimpleMailMessage adminMessage = new SimpleMailMessage();
            adminMessage.setFrom(FROM_EMAIL);
            adminMessage.setTo(FROM_EMAIL);
            adminMessage.setSubject("בקשה חדשה לעבודה מותאמת מ-" + customerName);
            adminMessage.setText(
                    "שם: " + customerName + "\n" +
                    "דוא״ל: " + customerEmail + "\n" +
                    "סוג העבודה: " + productType + "\n\n" +
                    "תיאור:\n" + description
            );

            mailSender.send(adminMessage);
            logger.info("Inquiry notification sent to admin from: {}", customerEmail);
        } catch (Exception e) {
            logger.error("Failed to send inquiry notification to admin", e);
        }
    }
}
