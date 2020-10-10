package com.planittesting.jupitertoys.model.pages;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.planittesting.jupitertoys.model.products.Product;

public class ShopPage extends BasePage {

	public ShopPage(WebDriver driver) {
		super(driver);
	}

	public Product getProduct(Function<Product, Boolean> strategy) {
		return getProducts(strategy).stream().findFirst().orElseThrow();
	}
	
	public List<Product> getProducts(Function<Product, Boolean> strategy) {
		return driver.findElements(By.className("product"))
				.stream()
				.map(Product::new)
				.filter(strategy::apply)
				.collect(Collectors.toList());	
	}
	
	public List<Product> getProducts() {
		return getProducts(p->true);		
	}
}