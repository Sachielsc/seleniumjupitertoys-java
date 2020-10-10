package com.planittesting.jupitertoys.model.data;

public class ProductData {
	private String title;
    private double price;
    
    public ProductData() {
    	this.title = null;
    	this.price = 0;
    }

    public ProductData withTitle(String title) {
        this.title = title;
        return this;
    }

    public ProductData withPrice(double price) {
        this.price = price;
        return this;
    }
    
    public String getTitle() {
    	return this.title;
    }

    public double getPrice() {
    	return this.price;
    }
}
