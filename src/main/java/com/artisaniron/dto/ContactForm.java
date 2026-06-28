package com.artisaniron.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class ContactForm {
    @NotBlank(message = "שם חובה")
    @Size(min = 2, max = 100, message = "שם חייב להיות בין 2 ל-100 תווים")
    private String senderName;

    @NotBlank(message = "דוא״ל חובה")
    @Email(message = "דוא״ל לא תקין")
    private String email;

    @Size(max = 20, message = "טלפון חייב להיות עד 20 תווים")
    private String phone;

    @NotBlank(message = "הודעה חובה")
    @Size(min = 10, max = 1000, message = "הודעה חייבת להיות בין 10 ל-1000 תווים")
    private String message;

    public ContactForm() {
    }

    public ContactForm(String senderName, String email, String phone, String message) {
        this.senderName = senderName;
        this.email = email;
        this.phone = phone;
        this.message = message;
    }

    public String getSenderName() {
        return senderName;
    }

    public void setSenderName(String senderName) {
        this.senderName = senderName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
