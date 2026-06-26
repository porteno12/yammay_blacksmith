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
public class InquiryForm {
    @NotBlank(message = "שם חובה")
    @Size(min = 2, max = 100, message = "שם חייב להיות בין 2 ל-100 תווים")
    private String customerName;

    @NotBlank(message = "דוא״ל חובה")
    @Email(message = "דוא״ל לא תקין")
    private String email;

    @Size(max = 20, message = "טלפון חייב להיות עד 20 תווים")
    private String phone;

    @NotBlank(message = "סוג המוצר חובה")
    private String productType;

    private String referencedProduct;

    @Size(max = 200, message = "מידות חייבות להיות עד 200 תווים")
    private String dimensions;

    @Size(max = 200, message = "חומרים חייבים להיות עד 200 תווים")
    private String materials;

    @NotBlank(message = "תיאור חובה")
    @Size(min = 10, max = 2000, message = "תיאור חייב להיות בין 10 ל-2000 תווים")
    private String description;

    @Size(max = 100, message = "תקציב חייב להיות עד 100 תווים")
    private String budgetRange;

    @Size(max = 100, message = "ציר זמן חייב להיות עד 100 תווים")
    private String timeline;
}
