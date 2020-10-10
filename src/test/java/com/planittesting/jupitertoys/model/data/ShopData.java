package com.planittesting.jupitertoys.model.data;

import java.util.ArrayList;
import java.util.List;

public class ShopData {
	private List<ProductData> products;
    private int cartCount;
    
    public ShopData() {
    	this.products = new ArrayList<>();
    	this.cartCount = 0;
    }

    public ShopData withProducts(List<ProductData> products) {
        this.products = products;
        return this;
    }

    public ShopData withCartCount(int count) {
        this.cartCount = count;
        return this;
    }
    
    public List<ProductData> getProducts() {
    	return this.products;
    }

    public int getCartCount() {
    	return this.cartCount;
    }
}
