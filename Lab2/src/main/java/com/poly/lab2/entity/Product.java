package com.poly.lab2.entity;

public class Product {
    private String name;
    private Double price;

    // Constructor mặc định
    public Product() {
    }

    // Constructor có tham số
    public Product(String name, Double price) {
        this.name = name;
        this.price = price;
    }

    // Getter & Setter
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }
}