package com.artisaniron.controller;

import com.artisaniron.dto.InquiryForm;
import com.artisaniron.service.InquiryService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/inquiry")
public class InquiryController {
    private final InquiryService inquiryService;

    public InquiryController(InquiryService inquiryService) {
        this.inquiryService = inquiryService;
    }

    @GetMapping
    public String inquiryForm(@RequestParam(required = false) String product, Model model) {
        if (!model.containsAttribute("inquiryForm")) {
            InquiryForm form = new InquiryForm();
            if (product != null && !product.isEmpty()) {
                form.setReferencedProduct(product);
            }
            model.addAttribute("inquiryForm", form);
        }
        return "inquiry";
    }

    @PostMapping
    public String submitInquiry(@Valid InquiryForm form, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "inquiry";
        }

        inquiryService.submitInquiry(form);
        model.addAttribute("successMessage", "תודה על בקשתך. אנחנו נחזור אליך בקרוב עם הצעת מחיר!");
        return "inquiry-success";
    }
}
