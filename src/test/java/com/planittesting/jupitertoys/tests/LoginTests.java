package com.planittesting.jupitertoys.tests;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import com.planittesting.jupitertoys.model.pages.HomePage;

public class LoginTests extends BaseTestSuite {

	@Tag("login")
	@Tag("shakedown")
	@Tag("regression")
	@Test
	public void validateSuccessfulLogin() {
		
			var home = new HomePage(driver);

			var loginDialogue = home.clickLoginMenu();

			loginDialogue.setUsername("something")
			             .setPassword("letmein")
			             .clickAgreeCheckbox()
			             .clickLoginButton();
			
			assertEquals("something", home.getUser());
	}
}