package com.planittesting.automation.driver.support;

// import java.util.logging.Logger;

import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.events.WebDriverEventListener;

public class CustomWebDriverEventListener implements WebDriverEventListener {

	// private final Logger logger = Logger.getLogger(CustomWebDriverEventListener.class.getName());
	// private final WebElement currentElement = null;
	// private final String currentUrl = "";

	@Override
	public void beforeAlertAccept(final WebDriver driver) {
		

	}

	@Override
	public void afterAlertAccept(final WebDriver driver) {
		

	}

	@Override
	public void afterAlertDismiss(final WebDriver driver) {
		

	}

	@Override
	public void beforeAlertDismiss(final WebDriver driver) {
		

	}

	@Override
	public void beforeNavigateTo(final String url, final WebDriver driver) {

	}

	@Override
	public void afterNavigateTo(final String url, final WebDriver driver) {

	}

	@Override
	public void beforeNavigateBack(final WebDriver driver) {
		

	}

	@Override
	public void afterNavigateBack(final WebDriver driver) {
		

	}

	@Override
	public void beforeNavigateForward(final WebDriver driver) {
		

	}

	@Override
	public void afterNavigateForward(final WebDriver driver) {
		

	}

	@Override
	public void beforeNavigateRefresh(final WebDriver driver) {
		

	}

	@Override
	public void afterNavigateRefresh(final WebDriver driver) {
		

	}

	@Override
	public void beforeFindBy(final By by, final WebElement element, final WebDriver driver) {

	}

	@Override
	public void afterFindBy(final By by, final WebElement element, final WebDriver driver) {
		// this.highlightElement(element, driver);
		// this.saveScreenshot(driver);
	}

	@Override
	public void beforeClickOn(final WebElement element, final WebDriver driver) {

	}

	@Override
	public void afterClickOn(final WebElement element, final WebDriver driver) {

	}

	@Override
	public void beforeChangeValueOf(final WebElement element, final WebDriver driver, final CharSequence[] keysToSend) {

	}

	@Override
	public void afterChangeValueOf(final WebElement element, final WebDriver driver, final CharSequence[] keysToSend) {

	}

	@Override
	public void beforeScript(final String script, final WebDriver driver) {
		

	}

	@Override
	public void afterScript(final String script, final WebDriver driver) {
		

	}

	@Override
	public void beforeSwitchToWindow(final String windowName, final WebDriver driver) {
		

	}

	@Override
	public void afterSwitchToWindow(final String windowName, final WebDriver driver) {
		

	}

	@Override
	public void onException(final Throwable throwable, final WebDriver driver) {
		

	}

	@Override
	public <X> void beforeGetScreenshotAs(final OutputType<X> target) {
		

	}

	@Override
	public <X> void afterGetScreenshotAs(final OutputType<X> target, final X screenshot) {
		

	}

	@Override
	public void beforeGetText(final WebElement element, final WebDriver driver) {

	}

	@Override
	public void afterGetText(final WebElement element, final WebDriver driver, final String text) {

	}

//	private void highlightElement(WebElement element, WebDriver driver) {
//		try {
//			var highlightJavascript = "arguments[0].style.cssText = 'border: 0';";
//			
//			if (currentElement != null) {
//				((JavascriptExecutor) driver).executeScript(highlightJavascript,
//						currentElement);
//			}
//			
//			currentElement = element;
//			highlightJavascript = "arguments[0].style.cssText = 'border-width: 2px; border-style: solid; border-color: red';";
//			
//			if (currentElement != null) {
//				logger.info(currentElement.getText());
//				((JavascriptExecutor) driver).executeScript(highlightJavascript,
//						currentElement);
//			}
//		} catch (Exception e) {
//			
//		}
//	}
//
//	private void saveScreenshot(WebDriver driver) {
//		var url = driver.getCurrentUrl().replace("/", "_").replace(":", "_");
//		if (!url.equals(currentUrl)) {
//			currentUrl = url;
//			try {
//				var screenshotFile = ((TakesScreenshot) driver)
//						.getScreenshotAs(OutputType.FILE);
//				File destination = new File(System.getProperty("user.home")
//						+ File.separator + currentUrl + ".png");
//				Files.move(screenshotFile.toPath(), destination.toPath(),
//						StandardCopyOption.REPLACE_EXISTING);
//				logger.info(() -> destination.getAbsolutePath());
//			} catch (IOException e) {
//				logger.fine(e.getMessage());
//			}
//		}
//	}

}
