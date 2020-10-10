package com.planittesting.jupitertoys.tests;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Comparator;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import com.planittesting.jupitertoys.model.pages.HomePage;
import com.planittesting.jupitertoys.model.products.Product;

public class ShopTests extends BaseTestSuite {

	@Tag("shakedown")
	@Tag("regression")
	@Test
	public void validatePriceForProduct() throws Exception {
		var homePage = new HomePage(driver);
		var shopPage = homePage.clickShopMenu();
		var product = shopPage.getProduct(p->p.getTitle().equals("Handmade Doll"));
		assertEquals(9.99, product.getPrice(), 0.001);
	}

	@Tag("buy")
	@Test
	public void buyAProductWithGivenPrice() throws Exception {
		var homePage = new HomePage(driver);
		var shopPage = homePage.clickShopMenu();
		var product = shopPage.getProduct(p -> p.getPrice() >= 10.99);
		product.clickBuyButton();
		assertEquals(1, shopPage.getCartCount());
	}

	@Test
	public void buyAllProductsWithStarRating() throws Exception {
		var homePage = new HomePage(driver);
		var shopPage = homePage.clickShopMenu();
		var products = shopPage.getProducts(p -> p.getRating() == 1);
		products.forEach(Product::clickBuyButton);
		assertEquals(products.size(), shopPage.getCartCount());
	}

	@Test
	public void buyCheapestProduct() {
		var homePage = new HomePage(driver);
		var shopPage = homePage.clickShopMenu();
		var products = shopPage.getProducts();
		var product = products.stream().min(Comparator.comparing(Product::getPrice)).orElseThrow();
		product.clickBuyButton();
	}
}
