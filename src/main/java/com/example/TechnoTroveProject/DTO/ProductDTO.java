package com.example.TechnoTroveProject.DTO;

import java.io.Serializable;

public class ProductDTO implements Serializable {
    private static final long serialVersionUID = 1L; // Recommended for Serializable classes

    private int id;
    private String name;
    private String description;
    private int categoryId;
    private String imagePath;

    // Constructor
    public ProductDTO(int id, String name, String description, int categoryId, String imagePath) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.categoryId = categoryId;
        this.imagePath = imagePath;
    }

    // Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }
}
