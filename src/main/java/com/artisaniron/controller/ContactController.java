package com.artisaniron.controller;

import com.artisaniron.dto.ContactForm;
import com.artisaniron.service.ContactService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/contact")
public class ContactController {
    private final ContactService contactService;

    public ContactController(ContactService contactService) {
        this.contactService = contactService;
    }

    @GetMapping
    public String contactForm(Model model) {
        if (!model.containsAttribute("contactForm")) {
            model.addAttribute("contactForm", new ContactForm());
        }
        return "contact";
    }

    @PostMapping
    public String submitContact(@Valid ContactForm form, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "contact";
        }

        contactService.submitContact(form);
        model.addAttribute("successMessage", "תודה על פנייתך. אנחנו נחזור אליך בקרוב!");
        return "contact-success";
    }
}
