package com.planittesting.automation.driver.support;

import java.util.concurrent.TimeUnit;
import java.util.function.Function;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CleanWebDriverWait extends WebDriverWait {

	private final WebDriver driver;
	private long implicitWaitTimeOutInSeconds;

	public CleanWebDriverWait(WebDriver driver, long timeOutInSeconds, long implicitWaitTimeOutInSeconds) {
		super(driver, timeOutInSeconds);
		this.driver = driver;
		this.implicitWaitTimeOutInSeconds = implicitWaitTimeOutInSeconds;
	}

	public <V> V until(Function<? super WebDriver, V> isTrue) {
		this.driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
		try {
			return super.until(isTrue);
		} catch (Exception e) {
			throw e;
		} finally {
			this.driver.manage().timeouts().implicitlyWait(this.implicitWaitTimeOutInSeconds, TimeUnit.SECONDS);
		}
	}

}
