package com.artisaniron.service;

import com.artisaniron.dto.ContactForm;
import com.artisaniron.model.ContactMessage;
import com.artisaniron.repository.ContactMessageRepository;
import org.springframework.stereotype.Service;

@Service
public class ContactService {
    private final ContactMessageRepository contactMessageRepository;
    private final EmailService emailService;

    public ContactService(ContactMessageRepository contactMessageRepository, EmailService emailService) {
        this.contactMessageRepository = contactMessageRepository;
        this.emailService = emailService;
    }

    public ContactMessage submitContact(ContactForm form) {
        ContactMessage message = new ContactMessage();
        message.setSenderName(form.getSenderName());
        message.setEmail(form.getEmail());
        message.setPhone(form.getPhone());
        message.setMessage(form.getMessage());
        message.setSubmittedAt(System.currentTimeMillis());
        message.setRead(false);

        ContactMessage saved = contactMessageRepository.save(message);

        emailService.sendContactConfirmation(form.getEmail(), form.getSenderName());
        emailService.sendContactNotificationToAdmin(
                form.getSenderName(),
                form.getEmail(),
                form.getPhone(),
                form.getMessage()
        );

        return saved;
    }
}
