package com.artisaniron.controller.admin;

import com.artisaniron.repository.ContactMessageRepository;
import com.artisaniron.repository.InquiryRequestRepository;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
@PreAuthorize("hasRole('ADMIN')")
public class AdminDashboardController {
    private final ContactMessageRepository contactMessageRepository;
    private final InquiryRequestRepository inquiryRequestRepository;

    public AdminDashboardController(ContactMessageRepository contactMessageRepository,
                                   InquiryRequestRepository inquiryRequestRepository) {
        this.contactMessageRepository = contactMessageRepository;
        this.inquiryRequestRepository = inquiryRequestRepository;
    }

    @GetMapping
    public String dashboard(Model model) {
        try {
            long unreadMessages = contactMessageRepository.findAll().stream()
                    .filter(m -> !m.isRead())
                    .count();

            long unreadInquiries = inquiryRequestRepository.findAll().stream()
                    .filter(i -> !i.isRead())
                    .count();

            model.addAttribute("unreadMessages", unreadMessages);
            model.addAttribute("unreadInquiries", unreadInquiries);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        return "admin/dashboard";
    }
}
