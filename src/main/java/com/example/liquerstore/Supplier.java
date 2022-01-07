package com.example.liquerstore;

public class Supplier {
    private int company_id;
    private String company_name;
    private int p_id;
    private double price;

    public Supplier(int company_id, String company_name, int p_id, double price) {
        this.company_id = company_id;
        this.company_name = company_name;
        this.p_id = p_id;
        this.price = price;
    }

    public int getCompany_id() {
        return company_id;
    }

    public void setCompany_id(int company_id) {
        this.company_id = company_id;
    }

    public String getCompany_name() {
        return company_name;
    }

    public void setCompany_name(String company_name) {
        this.company_name = company_name;
    }

    public int getP_id() {
        return p_id;
    }

    public void setP_id(int p_id) {
        this.p_id = p_id;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
