package com.example.liquerstore;

public class Product {
    private int p_id;
    private String product_name;
    private String category;
    private int stock;
    private double price;

    public Product(int p_id, String prodct_name, String category, int stock, double price) {
        this.p_id = p_id;
        this.product_name = prodct_name;
        this.category = category;
        this.stock = stock;
        this.price = price;
    }

    public void setP_id(int p_id) {
        this.p_id = p_id;
    }

    public void setProduct_name(String product_name) {
        this.product_name = product_name;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getP_id() {
        return p_id;
    }

    public String getProduct_name() {
        return product_name;
    }

    public String getCategory() {
        return category;
    }

    public int getStock() {
        return stock;
    }

    public double getPrice() {
        return price;
    }
}
