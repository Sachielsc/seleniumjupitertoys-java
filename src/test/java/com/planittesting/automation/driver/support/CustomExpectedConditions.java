package com.planittesting.automation.driver.support;

import java.util.function.Function;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class CustomExpectedConditions {

	public static Function<? super WebDriver, Boolean> elementNotPresent(WebElement parentElement, By childLocator) {
		return driver -> {
			try {
				return parentElement.findElements(childLocator).isEmpty();
			} catch (NoSuchElementException e) {
				// Returns true because the element is not present in DOM. The
				// try block checks if the element is present but is invisible.
				return true;
			} catch (StaleElementReferenceException e) {
				// Returns true because stale element reference implies that element
				// is no longer visible.
				return true;
			}
		};
	}
}
