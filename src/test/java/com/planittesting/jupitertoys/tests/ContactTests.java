package com.planittesting.jupitertoys.tests;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.CsvSource;

import com.planittesting.jupitertoys.model.data.ContactData;
import com.planittesting.jupitertoys.model.pages.HomePage;
import com.planittesting.jupitertoys.tests.dataproviders.CsvToContactData;

public class ContactTests extends BaseTestSuite {

	@ParameterizedTest
	@CsvSource({
		"Juan,,a@b.com,,Hello"
	})
	public void validateMandatoryFieldsErrors(@CsvToContactData ContactData contactData) {
		var homePage = new HomePage(driver);

		var contactPage = homePage.clickContactMenu();
		contactPage.clickSubmitButton();

		assertAll(()->assertEquals("Forename is required", contactPage.getForenameError()),
				  ()->assertEquals("Email is required", contactPage.getEmailError()),
				  ()->assertEquals("Message is required", contactPage.getMessageError()));

		contactPage.setContactData(contactData);

		assertAll(()->assertEquals("", contactPage.getForenameError()),
				  ()->assertEquals("", contactPage.getEmailError()),
				  ()->assertEquals("", contactPage.getMessageError()));
	}

	@Tag("data")
	@ParameterizedTest
	@CsvFileSource(resources = "/ContactData.csv", numLinesToSkip = 1)
	public void validateDataformatErrors(@CsvToContactData ContactData contactData) {

		var homePage = new HomePage(driver);

		var contactPage = homePage.clickContactMenu();

		contactPage.setContactData(contactData);

		assertEquals("Please enter a valid telephone number", contactPage.getTelephoneError());
		assertEquals("Please enter a valid email", contactPage.getEmailError());
	}

	@ParameterizedTest
	@CsvSource({
		"Juan,Florez,a@b.com,03030303,Hello",
		"Cuong,Pham,gdgd@jfhd.com,04040404,ObjectContainer"
	})
	public void validateSuccesfulContactFormSubmition(@CsvToContactData ContactData contactData) {

		var homePage = new HomePage(driver);
		var contactPage = homePage.clickContactMenu();
		contactPage.setContactData(contactData)
		           .clickSubmitButton();

		assertEquals("Thanks "+contactData.getForename()+", we appreciate your feedback.", 
					contactPage.getSuccessMessage());

	}

}
