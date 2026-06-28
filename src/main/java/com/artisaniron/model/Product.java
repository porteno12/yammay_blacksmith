package com.artisaniron.model;

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

    public Product() {
    }

    public Product(String id, String slug, String name, String description, String materials, double price, String imageBase64, String categorySlug, boolean featured, int sortOrder, long createdAt) {
        this.id = id;
        this.slug = slug;
        this.name = name;
        this.description = description;
        this.materials = materials;
        this.price = price;
        this.imageBase64 = imageBase64;
        this.categorySlug = categorySlug;
        this.featured = featured;
        this.sortOrder = sortOrder;
        this.createdAt = createdAt;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSlug() {
        return slug;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getMaterials() {
        return materials;
    }

    public void setMaterials(String materials) {
        this.materials = materials;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getImageBase64() {
        return imageBase64;
    }

    public void setImageBase64(String imageBase64) {
        this.imageBase64 = imageBase64;
    }

    public String getCategorySlug() {
        return categorySlug;
    }

    public void setCategorySlug(String categorySlug) {
        this.categorySlug = categorySlug;
    }

    public boolean isFeatured() {
        return featured;
    }

    public void setFeatured(boolean featured) {
        this.featured = featured;
    }

    public int getSortOrder() {
        return sortOrder;
    }

    public void setSortOrder(int sortOrder) {
        this.sortOrder = sortOrder;
    }

    public long getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(long createdAt) {
        this.createdAt = createdAt;
    }
}
