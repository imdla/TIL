package com.example.myproject.mysite;

public class Product {
    private Long id;
    private String productName;
    private int Price;

    public Product(Long id, String productName, int price) {
        this.id = id;
        this.productName = productName;
        Price = price;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public int getPrice() {
        return Price;
    }

    public void setPrice(int price) {
        Price = price;
    }
}
