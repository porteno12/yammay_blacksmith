package com.artisaniron.service;

import com.artisaniron.dto.InquiryForm;
import com.artisaniron.model.InquiryRequest;
import com.artisaniron.repository.InquiryRequestRepository;
import org.springframework.stereotype.Service;

@Service
public class InquiryService {
    private final InquiryRequestRepository inquiryRequestRepository;
    private final EmailService emailService;

    public InquiryService(InquiryRequestRepository inquiryRequestRepository, EmailService emailService) {
        this.inquiryRequestRepository = inquiryRequestRepository;
        this.emailService = emailService;
    }

    public InquiryRequest submitInquiry(InquiryForm form) {
        InquiryRequest inquiry = new InquiryRequest();
        inquiry.setCustomerName(form.getCustomerName());
        inquiry.setEmail(form.getEmail());
        inquiry.setPhone(form.getPhone());
        inquiry.setProductType(form.getProductType());
        inquiry.setReferencedProduct(form.getReferencedProduct());
        inquiry.setDimensions(form.getDimensions());
        inquiry.setMaterials(form.getMaterials());
        inquiry.setDescription(form.getDescription());
        inquiry.setBudgetRange(form.getBudgetRange());
        inquiry.setTimeline(form.getTimeline());
        inquiry.setStatus("NEW");
        inquiry.setSubmittedAt(System.currentTimeMillis());
        inquiry.setRead(false);

        InquiryRequest saved = inquiryRequestRepository.save(inquiry);

        emailService.sendInquiryConfirmation(form.getEmail(), form.getCustomerName());
        emailService.sendInquiryNotificationToAdmin(
                form.getCustomerName(),
                form.getEmail(),
                form.getProductType(),
                form.getDescription()
        );

        return saved;
    }
}
