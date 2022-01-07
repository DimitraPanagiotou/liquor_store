package com.example.liquerstore;

import java.util.Date;

public class Receipt {
    private int barcode;
    private int s_id;
    private int receipt_p_id;
    private Date date;
    private int quantity;
    private double total_value;

    public Receipt(int barcode, int s_id, int receipt_p_id, Date date, int quantity, double total_value) {
        this.barcode = barcode;
        this.s_id = s_id;
        this.receipt_p_id = receipt_p_id;
        this.date = date;
        this.quantity = quantity;
        this.total_value = total_value;
    }

    public void setBarcode(int barcode) {
        this.barcode = barcode;
    }

    public void setS_id(int s_id) {
        this.s_id = s_id;
    }

    public void setReceipt_p_id(int receipt_p_id) {
        this.receipt_p_id = receipt_p_id;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setTotal_value(double total_value) {
        this.total_value = total_value;
    }

    public int getBarcode() {
        return barcode;
    }

    public int getS_id() {
        return s_id;
    }

    public int getReceipt_p_id() {
        return receipt_p_id;
    }

    public Date getDate() {
        return date;
    }

    public int getQuantity() {
        return quantity;
    }

    public double getTotal_value() {
        return total_value;
    }
}
