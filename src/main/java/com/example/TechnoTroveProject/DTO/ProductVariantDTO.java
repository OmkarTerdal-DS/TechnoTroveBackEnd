package com.example.TechnoTroveProject.DTO;

import java.io.Serializable;

public class ProductVariantDTO implements Serializable {
    private static final long serialVersionUID = 1L; // Recommended for Serializable classes

    private int id;
    private String name;
    private double price;
    private String sku;
    private String imageUrl1;
    private String imageUrl2;
    private String imageUrl3;

    // Default constructor
    public ProductVariantDTO() {
    }

    // Constructor that matches the fields in the query
    public ProductVariantDTO(int id, String name, double price, String sku, String imageUrl1, String imageUrl2, String imageUrl3) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.sku = sku;
        this.imageUrl1 = imageUrl1;
        this.imageUrl2 = imageUrl2;
        this.imageUrl3 = imageUrl3;
    }

    // Getters and setters
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

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getSku() {
        return sku;
    }

    public void setSku(String sku) {
        this.sku = sku;
    }

    public String getImageUrl1() {
        return imageUrl1;
    }

    public void setImageUrl1(String imageUrl1) {
        this.imageUrl1 = imageUrl1;
    }

    public String getImageUrl2() {
        return imageUrl2;
    }

    public void setImageUrl2(String imageUrl2) {
        this.imageUrl2 = imageUrl2;
    }

    public String getImageUrl3() {
        return imageUrl3;
    }

    public void setImageUrl3(String imageUrl3) {
        this.imageUrl3 = imageUrl3;
    }
}
