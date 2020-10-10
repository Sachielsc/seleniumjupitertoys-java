package com.planittesting.jupitertoys.model.products;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class Product {

	private WebElement element;

	public Product(WebElement element) {
		this.element = element;
	}

	public String getTitle() {
		return element.findElement(By.className("product-title")).getText();
	}

	public double getPrice() {
		return Double.parseDouble(element.findElement(By.className("product-price")).getText().replaceAll("[^0-9\\.]+", ""));
	}

	public Product clickBuyButton() {
		element.findElement(By.className("btn")).click();
		return this;
	}

	public int getRating() {
		return Integer.parseInt(element.findElement(By.className("star-level")).getText());
	}

}
