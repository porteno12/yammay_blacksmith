package com.artisaniron.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class InquiryRequest {
    private String id;
    private String customerName;
    private String email;
    private String phone;
    private String productType;
    private String referencedProduct;
    private String dimensions;
    private String materials;
    private String description;
    private String budgetRange;
    private String timeline;
    private String status;
    private long submittedAt;
    private boolean read;
}
