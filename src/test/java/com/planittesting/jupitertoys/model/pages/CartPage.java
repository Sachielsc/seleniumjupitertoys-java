package com.planittesting.jupitertoys.model.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.planittesting.jupitertoys.model.ui.Table;

public class CartPage extends BasePage {

	private Table cartItems;

	public CartPage(WebDriver driver) {
		super(driver);
	}

	public double getPrice(String productTitle) throws RuntimeException {
		return Double.parseDouble(getCartItems().getValue("Item", productTitle, "Price").getText().replaceAll("[^0-9\\.]+", ""));
	}

	public int getQuantity(String productTitle) throws RuntimeException {
		return Integer.parseInt(getCartItems().getValue("Item", productTitle, "Quantity").findElement(By.tagName("input")).getAttribute("value"));
	}

	public double getSubtotal(String productTitle) throws RuntimeException {
		return Double.parseDouble(getCartItems().getValue("Item", productTitle, "Subtotal").getText().replaceAll("[^0-9\\.]+", ""));
	}

	public double getTotal() {
		return Double.parseDouble(driver.findElement(By.className("total")).getText().replace("Total: ", ""));
	}
	
	public void clickRemoveButton(String productTitle) throws RuntimeException {
		getCartItems().getValue("Item", productTitle, "Actions").findElement(By.className("remove-item")).click();
	}
	
	public void clcikOnShopLink() {
		driver.findElement(By.linkText("Shopping")).click();
	}

	/* 
	 * Lazy instantiation of cart items
	 * Could return a new Table every time to avoid stale element situations 
	 * but in this particular application this will not happen
	 */
	private Table getCartItems() {
		if (this.cartItems == null) 
			this.cartItems = new Table(driver.findElement(By.className("cart-items")));
		return this.cartItems;
	}
}
