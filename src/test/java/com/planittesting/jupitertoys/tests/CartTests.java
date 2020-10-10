package com.planittesting.jupitertoys.tests;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import com.planittesting.jupitertoys.model.pages.HomePage;

public class CartTests extends BaseTestSuite {

	@Test()
	public void validateSumsInCartPage() throws Exception {

		var homePage = new HomePage(driver);
		var shopPage = homePage.clickShopMenu();

		var product = shopPage.getProduct(
				p -> p.getTitle().equalsIgnoreCase("Handmade Doll"));
		var handmadeDollPrice = product.getPrice();
		product.clickBuyButton().clickBuyButton().clickBuyButton();

		assertEquals(3, shopPage.getCartCount());

		var cartPage = shopPage.clickCartMenu();

		assertEquals(handmadeDollPrice, cartPage.getPrice("Handmade Doll"),
				"compare price in cart page with price from shop page");
		assertEquals(
				cartPage.getPrice("Handmade Doll")
						* cartPage.getQuantity("Handmade Doll"),
				cartPage.getSubtotal("Handmade Doll"),
				"price * quantity should equal subtotal");
		assertEquals(cartPage.getSubtotal("Handmade Doll"), cartPage.getTotal(),
				"subtotals sum equals total");

	}

}
