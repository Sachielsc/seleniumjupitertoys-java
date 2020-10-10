package com.planittesting.jupitertoys.model.dialogues;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.planittesting.jupitertoys.model.data.LoginData;

public class LoginDialogue {

	private WebElement element;

	public LoginDialogue(WebElement element) {
		this.element = element;
	}

	public LoginDialogue setUsername(String username) {
		element.findElement(By.id("loginUserName")).sendKeys(username);
		return this;
	}

	public LoginDialogue setPassword(String password) {
		element.findElement(By.id("loginPassword")).sendKeys(password);
		return this;
	}

	public void clickLoginButton() {
		element.findElement(By.className("btn-primary")).click();
	}
	
	public LoginDialogue clickAgreeCheckbox() {
		element.findElement(By.id("agree")).click();
		return this;
	}
	
	public void login(LoginData loginData) {
		this.setUsername(loginData.getUsername());
		this.setPassword(loginData.getPassword());
		this.clickAgreeCheckbox();
		this.clickLoginButton();
	}

	public String getErrorMessage() {
		return element.findElement(By.id("login-error")).getText();
	}
}