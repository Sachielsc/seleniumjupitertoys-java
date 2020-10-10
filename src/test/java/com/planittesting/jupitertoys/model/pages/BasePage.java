package com.planittesting.jupitertoys.model.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.planittesting.jupitertoys.model.dialogues.LoginDialogue;

public abstract class BasePage {

	protected final WebDriver driver;

	public BasePage(WebDriver driver) {
		this.driver = driver;
	}

	public ContactPage clickContactMenu() {
		driver.findElement(By.cssSelector("#nav-contact a")).click();
		return new ContactPage(driver);
	}

	public LoginDialogue clickLoginMenu() {
		driver.findElement(By.cssSelector("#nav-login a")).click();
		return new LoginDialogue(driver.findElement(By.className("popup")));
	}

	public ShopPage clickShopMenu() {
		driver.findElement(By.cssSelector("#nav-shop a")).click();
		return new ShopPage(driver);
	}

	public int getCartCount() {
		return Integer.parseInt(driver.findElement(By.className("cart-count")).getText());
	}
	
	public String getUser() {
		return driver.findElement(By.cssSelector("#nav-user a")).getText();
	}

	public CartPage clickCartMenu() {
		driver.findElement(By.cssSelector("#nav-cart a")).click();
		return new CartPage(driver);
	}
}
