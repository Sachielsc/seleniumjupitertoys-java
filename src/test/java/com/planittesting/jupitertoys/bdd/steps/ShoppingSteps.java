package com.planittesting.jupitertoys.bdd.steps;

import java.util.List;

import org.junit.Assert;

import com.planittesting.jupitertoys.model.data.ProductData;
import com.planittesting.jupitertoys.model.data.ShopData;
import com.planittesting.jupitertoys.model.pages.CartPage;
import com.planittesting.jupitertoys.model.pages.HomePage;
import com.planittesting.jupitertoys.model.pages.ShopPage;
import com.planittesting.jupitertoys.model.products.Product;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class ShoppingSteps extends BaseSteps {
	private static final double FLOATING_POINT_ERROR = 0.001;
	
	public ShoppingSteps(ObjectContainer container) {
		super(container);
    }

    @Given("^A product with name (.*) is in the shop catalog$")
    public void givenAProductWithNameIsInTheShopCatalog(String productName) {
        HomePage homePage = new HomePage(this.driver);
        ShopPage shopPage = homePage.clickShopMenu();
		Product product = shopPage.getProduct(p -> p.getTitle().equals(productName));
        this.container.register("product", product);
    }

    @Then("^Price should be (.*)$")
    public void thenPriceShouldBe(double productPrice) {
        Product product = this.container.retrieve("product");
        Assert.assertEquals(productPrice, product.getPrice(), FLOATING_POINT_ERROR);
    }

    @When("^A customer buys the product with name (.*)$")
    public void whenACustomerBuysTheProductWithName(String productName) {
        HomePage homePage = new HomePage(this.driver);
        ShopPage shopPage = homePage.clickShopMenu();
        this.container.register("shopData", new ShopData().withCartCount(shopPage.getCartCount()));

		Product testProduct = shopPage.getProduct(p -> p.getTitle().equals(productName));
        this.container.register("productData", new ProductData().withPrice(testProduct.getPrice()));
        testProduct.clickBuyButton();
        
    }

    @Then("^The product with name (.*) must be added to the cart$")
    public void thenTheProductWithNameMustBeAddedToTheCart(String productName) throws Exception {
        ShopData shopData = this.container.retrieve("shopData");
        ProductData productData = this.container.retrieve("productData");

        ShopPage shopPage = new ShopPage(this.driver);
        Assert.assertEquals(shopData.getCartCount() + 1, shopPage.getCartCount());
        
        CartPage cartPage = shopPage.clickCartMenu();
        double subtotal = cartPage.getSubtotal(productName);
        Assert.assertEquals(productData.getPrice(), subtotal, FLOATING_POINT_ERROR);
    }

    @When("A customer buys all products cheaper than {double}")
    public void whenACustomerBuysAllProductsCheaperThan(double amount) {
    	ShopData shopData = new ShopData();
        HomePage homePage = new HomePage(this.driver);
        ShopPage shopPage = homePage.clickShopMenu();
        List<Product> products = shopPage.getProducts(p -> p.getPrice() < amount);
        products.forEach(product -> {
            product.clickBuyButton();
            shopData.getProducts().add(
                new ProductData()
                    .withTitle(product.getTitle())
                    .withPrice(product.getPrice())
            );
        });
        this.container.register("shopData", shopData);
    }

    @Then("The products must be added to the cart")
    public void thenTheProductsMustBeAddedToTheCart() {
        ShopData shopData = this.container.retrieve("shopData");

        ShopPage shopPage = new ShopPage(this.driver);
        Assert.assertEquals(shopData.getProducts().size(), shopPage.getCartCount());
    }

    @When("A customer buys the cheapest product")
    public void whenACustomerBuysTheCheapestProduct() {
    	ShopData shopData = new ShopData();
    	ProductData productData = new ProductData();

        HomePage homePage = new HomePage(this.driver);
        ShopPage shopPage = homePage.clickShopMenu();
        shopData.withCartCount(shopPage.getCartCount());
		List<Product> products = shopPage.getProducts(p -> true);
        products.sort((p1, p2) -> (int)(p1.getPrice() - p2.getPrice()));
        Product testProduct = products.get(0);
        productData
            .withTitle(testProduct.getTitle())
            .withPrice(testProduct.getPrice());
        testProduct.clickBuyButton();

        this.container.register("shopData", shopData);
        this.container.register("productData", productData);
    }

    @Then("The product must be added to the cart")
    public void thenTheProductMustBeAddedToTheCart() throws Exception
    {
        ShopData shopData = this.container.retrieve("shopData");
        ProductData productData = this.container.retrieve("productData");

        ShopPage shopPage = new ShopPage(this.driver);
        Assert.assertEquals(shopData.getCartCount() + 1, shopPage.getCartCount());

        CartPage cartPage = shopPage.clickCartMenu();
        double subtotal = cartPage.getSubtotal(productData.getTitle());
        Assert.assertEquals(productData.getPrice(), subtotal, FLOATING_POINT_ERROR);
    }
}
