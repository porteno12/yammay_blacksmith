package com.artisaniron.controller.admin;

import com.artisaniron.repository.ContactMessageRepository;
import com.artisaniron.repository.InquiryRequestRepository;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin/submissions")
@PreAuthorize("hasRole('ADMIN')")
public class AdminSubmissionsController {
    private final ContactMessageRepository contactMessageRepository;
    private final InquiryRequestRepository inquiryRequestRepository;

    public AdminSubmissionsController(ContactMessageRepository contactMessageRepository,
                                     InquiryRequestRepository inquiryRequestRepository) {
        this.contactMessageRepository = contactMessageRepository;
        this.inquiryRequestRepository = inquiryRequestRepository;
    }

    @GetMapping
    public String submissions(Model model) {
        try {
            model.addAttribute("messages", contactMessageRepository.findAll());
            model.addAttribute("inquiries", inquiryRequestRepository.findAll());
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        return "admin/submissions";
    }

    @PostMapping("/messages/{id}/read")
    public String markMessageAsRead(@PathVariable String id) {
        contactMessageRepository.markAsRead(id);
        return "redirect:/admin/submissions";
    }

    @PostMapping("/inquiries/{id}/read")
    public String markInquiryAsRead(@PathVariable String id) {
        inquiryRequestRepository.markAsRead(id);
        return "redirect:/admin/submissions";
    }

    @PostMapping("/inquiries/{id}/status")
    public String updateInquiryStatus(@PathVariable String id, String status) {
        inquiryRequestRepository.updateStatus(id, status);
        return "redirect:/admin/submissions";
    }
}
