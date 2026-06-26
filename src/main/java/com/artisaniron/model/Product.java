package com.artisaniron.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Product {
    private String id;
    private String slug;
    private String name;
    private String description;
    private String materials;
    private double price;
    private String imageBase64;
    private String categorySlug;
    private boolean featured;
    private int sortOrder;
    private long createdAt;
}
