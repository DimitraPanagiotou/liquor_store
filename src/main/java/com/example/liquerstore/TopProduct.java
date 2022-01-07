package com.example.liquerstore;

public class TopProduct {
    private int p_id;
    private String product_name;
    private String category;
    private int max_quantity;

    public TopProduct(int p_id, String product_name, String category, int max_quantity) {
        this.p_id = p_id;
        this.product_name = product_name;
        this.category = category;
        this.max_quantity = max_quantity;
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

    public int getMax_quantity() {
        return max_quantity;
    }
}
