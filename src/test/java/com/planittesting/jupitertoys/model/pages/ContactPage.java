package com.planittesting.jupitertoys.model.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.planittesting.jupitertoys.model.data.ContactData;

public class ContactPage extends BasePage {

	public ContactPage(WebDriver driver) {
		super(driver);
	}

	public void clickSubmitButton() {
		driver.findElement(By.className("btn-primary")).click();
	}

	private String getErrorText(By locator) {
		var elements = driver.findElements(locator);
		return elements.size()>0?elements.get(0).getText():"";
	}

	public String getForenameError() {
		return getErrorText(By.id("forename-err"));
	}

	public String getEmailError() {
		return getErrorText(By.id("email-err"));
	}

	public String getMessageError() {
		return getErrorText(By.id("message-err"));
	}

	public String getTelephoneError() {
		return getErrorText(By.id("telephone-err"));
	}

	public ContactPage setForename(String name) {
		driver.findElement(By.id("forename")).sendKeys(name);
		return this;
	}

	public ContactPage setEmail(String email) {
		driver.findElement(By.id("email")).sendKeys(email);
		return this;
	}

	public ContactPage setMessage(String message) {
		driver.findElement(By.id("message")).sendKeys(message);
		return this;
	}

	public ContactPage setTelephone(String telephone) {
		driver.findElement(By.id("telephone")).sendKeys(telephone);
		return this;
	}

	public ContactPage setSurname(String surname) {
		driver.findElement(By.id("surname")).sendKeys(surname);
		return this;
	}

	public String getSuccessMessage() {
		return new WebDriverWait(driver, 60)
				  .until(d->d.findElement(By.className("alert-success"))).getText();
	}

	public ContactPage setContactData(ContactData contactData) {
		if(contactData.getForename() != null) {
			this.setForename(contactData.getForename());
		}
		
		if(contactData.getSurname() != null) {
			this.setSurname(contactData.getSurname());
		}
		
		if(contactData.getEmail() != null) {
			this.setEmail(contactData.getEmail());
		}
		
		if(contactData.getTelephone() != null) {
			this.setTelephone(contactData.getTelephone());
		}
		
		if(contactData.getMessage() != null) {
			this.setMessage(contactData.getMessage());
		}
		return this;
	}

}
