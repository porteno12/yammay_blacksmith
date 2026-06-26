package com.artisaniron.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ContactMessage {
    private String id;
    private String senderName;
    private String email;
    private String phone;
    private String message;
    private long submittedAt;
    private boolean read;
}
