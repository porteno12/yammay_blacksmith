package com.artisaniron.model;

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

    public InquiryRequest() {
    }

    public InquiryRequest(String id, String customerName, String email, String phone, String productType, String referencedProduct, String dimensions, String materials, String description, String budgetRange, String timeline, String status, long submittedAt, boolean read) {
        this.id = id;
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
        this.status = status;
        this.submittedAt = submittedAt;
        this.read = read;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public long getSubmittedAt() {
        return submittedAt;
    }

    public void setSubmittedAt(long submittedAt) {
        this.submittedAt = submittedAt;
    }

    public boolean isRead() {
        return read;
    }

    public void setRead(boolean read) {
        this.read = read;
    }
}
