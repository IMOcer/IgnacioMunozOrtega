package com.uem.model;

import com.uem.Calculator;

public class Article {
    private String name;
    private int quantity;
    private double price;
    private double discount; // en porcentaje: 10 = 10%

    private final Calculator calculator = new Calculator();
    
    public Article() {
}

    public Article(String name, int quantity, double price, double discount) {
        this.name = name;
        this.quantity = quantity;
        this.price = price;
        this.discount = discount;
    }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public int getQuantity() { return quantity; }
    public void setQuantity(int quantity) { this.quantity = quantity; }

    public double getPrice() { return price; }
    public void setPrice(double price) { this.price = price; }

    public double getDiscount() { return discount; }
    public void setDiscount(double discount) { this.discount = discount; }

    public double getGrossAmount() {
        return calculator.multiply(quantity, price);
    }

    public double getDiscountedAmount() {
        return calculator.multiply(getGrossAmount(), 1 - discount / 100);
    }

    @Override
    public String toString() {
        return "Article{name='" + name + "', quantity=" + quantity
                + ", price=" + price + ", discount=" + discount + "}";
    }
}