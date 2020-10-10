package com.planittesting.jupitertoys.bdd.steps;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;
import java.util.Map;

import com.planittesting.jupitertoys.model.data.ContactData;
import com.planittesting.jupitertoys.model.pages.ContactPage;
import com.planittesting.jupitertoys.model.pages.HomePage;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class ContactSteps extends BaseSteps {

	public ContactSteps(ObjectContainer container) {
		super(container);
	}

	@Given("A Customer tries to contact us")
	public void GivenACustomerTriesToContactUs() {
		HomePage homePage = new HomePage(driver);
		homePage.clickContactMenu();
	}

	@When("Customer enters invalid Email id and invalid Telephone")
	public void WhenCustomerEntersInvalidEmailIdAndInvalidTelephone() throws Throwable {
		ContactData contactData = new ContactData()
								  .withEmail("aaaaa")
								  .withTelephone("sdfdsfsf");

		container.register("InvalidEmailAndTelephone", contactData);
		ContactPage contactPage = new ContactPage(driver);
		contactPage.setEmail(contactData.getEmail())
					.setTelephone(contactData.getTelephone());
	}

	@Then("Error message for invalid email and invalid telephone are displayed")
	public void ThenErrorMessageForInvalidEmailAndInvalidTelephoneAreIsDisplayed() {
		ContactPage contactPage = new ContactPage(driver);
		assertEquals("Please enter a valid email", contactPage.getEmailError());
		assertEquals("Please enter a valid telephone number", contactPage.getTelephoneError());
	}

	@When("Customer submits empty information")
	public void customerSubmitsEmptyInformation() {
		HomePage homePage = new HomePage(driver);
		ContactPage contactPage = homePage.clickContactMenu();
		contactPage.clickSubmitButton();
	}

	@Then("Errors for mandatory information are displayed")
	public void ErrorsForMandatoryInformationAreDisplayed() {
		ContactPage contactPage = new ContactPage(driver);
		assertAll(() -> assertEquals("Forename is required", contactPage.getForenameError()),
				() -> assertEquals("Email is required", contactPage.getEmailError()),
				() -> assertEquals("Message is required", contactPage.getMessageError()));
	}

	@When("Customer submits all mandatory information")
	public void customerSubmitsAllMandatoryInformation(DataTable table) {
		List<Map<String, String>> list = table.asMaps(String.class, String.class);

		ContactData contactData = new ContactData()
								 .withForename(list.get(0).get("ForeName"))
								 .withEmail(list.get(0).get("Email"))
								 .withTelephone(list.get(0).get("Telephone"))
								 .withMessage(list.get(0).get("Message"));

		container.register("ValidContactData", contactData);
		ContactPage contactPage = new ContactPage(driver);

		contactPage.setForename(contactData.getForename())
				   .setEmail(contactData.getEmail())
				   .setTelephone(contactData.getTelephone())
				   .setMessage(contactData.getMessage())
				   .clickSubmitButton();
	}

	@Then("Acknowledgement message is displayed")
	public void acknowledgement_message_is_displayed() {
		ContactData contactData = container.retrieve("ValidContactData");
		ContactPage contactPage = new ContactPage(driver);

		assertEquals("Thanks " + contactData.getForename() + ", we appreciate your feedback.",
				contactPage.getSuccessMessage());

	}
}