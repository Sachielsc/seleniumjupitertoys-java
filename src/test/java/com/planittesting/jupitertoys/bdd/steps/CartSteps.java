package com.planittesting.jupitertoys.bdd.steps;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.IntStream;

import com.planittesting.jupitertoys.model.pages.CartPage;
import com.planittesting.jupitertoys.model.pages.HomePage;
import com.planittesting.jupitertoys.model.pages.ShopPage;
import com.planittesting.jupitertoys.model.products.Product;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;

public class CartSteps extends BaseSteps {

	public CartSteps(ObjectContainer container) {
		super(container);
	}

	@Given("^a customer adds following items to the cart$")
	public void addItemsToCart(Map<String, Integer> dataTable) {
		Map<String, Double> productsWithPrice = new HashMap<>();
		var cartNoItems =  0;
		ShopPage shopPage = new HomePage(driver).clickShopMenu();
		for(var entry : dataTable.entrySet()) {			
			Product product = shopPage.getProduct(p -> p.getTitle().equalsIgnoreCase(entry.getKey()));
			IntStream.range(0, entry.getValue()).forEach(i -> product.clickBuyButton());
			productsWithPrice.put(entry.getKey(), product.getPrice());
			cartNoItems += entry.getValue();
		};
		assertEquals(cartNoItems, shopPage.getCartCount());
		container.register("products", productsWithPrice);
	}

	@Then("^sub total should be correct for all items$")
	public void verifySubTotal() {
		Map<String, Double> itemsList = container.retrieve("products");
		CartPage cartPage = new ShopPage(driver).clickCartMenu();
		double total = 0.0;
		for (var entry : itemsList.entrySet()) {
			assertEquals(entry.getValue(), cartPage.getPrice(entry.getKey()));
			assertEquals(cartPage.getPrice(entry.getKey()) * cartPage.getQuantity(entry.getKey()), cartPage.getSubtotal(entry.getKey()), 0.00);
			total += cartPage.getSubtotal(entry.getKey());
		}
		container.register("TotalPrice", total);
	}

	@Then("^total price of items should be correct$")
	public void verifyTotal() {
		CartPage cartPage = new CartPage(driver);
		double totalPrice = container.retrieve("TotalPrice");
		assertEquals(totalPrice, cartPage.getTotal(), 0.00);
	}
}
