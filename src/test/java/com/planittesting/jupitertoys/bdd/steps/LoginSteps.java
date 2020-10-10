package com.planittesting.jupitertoys.bdd.steps;

import org.junit.Assert;

import com.planittesting.jupitertoys.model.data.LoginData;
import com.planittesting.jupitertoys.model.dialogues.LoginDialogue;
import com.planittesting.jupitertoys.model.pages.HomePage;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class LoginSteps extends BaseSteps {

	public LoginSteps(ObjectContainer container) {
		super(container);
	}

	@Given("A valid user")
	public void givenAValidUser() {
		var username = "anyone";//EnvironmentVariables.getInstance().getUsername();
		var password = "letmein";//EnvironmentVariables.getInstance().getValidpwd();
		container.register("login", new LoginData().withUsername(username).withPassword(password));
	}

	@When("User logs in to the application")
	public void whenUserLogsInToTheApplication() {
		LoginData loginData = container.retrieve("login");
		HomePage homePage = new HomePage(this.driver);
		LoginDialogue loginDialogue = homePage.clickLoginMenu();
		loginDialogue.login(loginData);
		container.register("login dialog", loginDialogue);
	}

	@Then("User should see the welcome message")
	public void thenUserShouldSeeTheWelcomeMessage() {
		LoginData loginData = container.retrieve("login");
		HomePage homePage = new HomePage(this.driver);
		Assert.assertEquals(loginData.getUsername(), homePage.getUser());
	}

	@Given("An invalid user")
	public void givenAnInvalidUser() {
		var username = "anyone";//EnvironmentVariables.getInstance().getUsername();
		var password = "wrongpassword";//EnvironmentVariables.getInstance().getInvalidpwd();
		container.register("login", new LoginData().withUsername(username).withPassword(password));
	}

	@Then("User should see the incorrect login error message")
	public void thenUserShouldSeeTheIncorrectLoginErrorMessage() {
		LoginDialogue loginDialogue = container.retrieve("login dialog");
		Assert.assertEquals("Your login details are incorrect", loginDialogue.getErrorMessage());
	}
}