package com.artisaniron.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
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
}
