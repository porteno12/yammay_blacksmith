package com.artisaniron.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

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

    public InquiryForm() {
    }

    public InquiryForm(String customerName, String email, String phone, String productType, String referencedProduct, String dimensions, String materials, String description, String budgetRange, String timeline) {
        this.customerName = customerName;
        this.email = email;
        this.phone = phone;
        this.productType = productType;
        this.referencedProduct = referencedProduct;
        this.dimensions = dimensions;
        this.materials = materials;
        this.description = description;
        this.budgetRange = budgetRange;
        this.timeline = timeline;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
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

    public String getProductType() {
        return productType;
    }

    public void setProductType(String productType) {
        this.productType = productType;
    }

    public String getReferencedProduct() {
        return referencedProduct;
    }

    public void setReferencedProduct(String referencedProduct) {
        this.referencedProduct = referencedProduct;
    }

    public String getDimensions() {
        return dimensions;
    }

    public void setDimensions(String dimensions) {
        this.dimensions = dimensions;
    }

    public String getMaterials() {
        return materials;
    }

    public void setMaterials(String materials) {
        this.materials = materials;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getBudgetRange() {
        return budgetRange;
    }

    public void setBudgetRange(String budgetRange) {
        this.budgetRange = budgetRange;
    }

    public String getTimeline() {
        return timeline;
    }

    public void setTimeline(String timeline) {
        this.timeline = timeline;
    }
}
