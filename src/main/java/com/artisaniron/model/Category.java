package com.artisaniron.model;

public class Category {
    private String id;
    private String slug;
    private String name;

    public Category() {
    }

    public Category(String id, String slug, String name) {
        this.id = id;
        this.slug = slug;
        this.name = name;
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
}
