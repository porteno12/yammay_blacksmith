package com.artisaniron.model;

public class ContactMessage {
    private String id;
    private String senderName;
    private String email;
    private String phone;
    private String message;
    private long submittedAt;
    private boolean read;

    public ContactMessage() {
    }

    public ContactMessage(String id, String senderName, String email, String phone, String message, long submittedAt, boolean read) {
        this.id = id;
        this.senderName = senderName;
        this.email = email;
        this.phone = phone;
        this.message = message;
        this.submittedAt = submittedAt;
        this.read = read;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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
