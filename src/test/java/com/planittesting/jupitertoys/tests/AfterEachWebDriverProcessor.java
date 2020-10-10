package com.planittesting.jupitertoys.tests;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;

import org.junit.jupiter.api.extension.AfterEachCallback;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.platform.commons.logging.Logger;
import org.junit.platform.commons.logging.LoggerFactory;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

public class AfterEachWebDriverProcessor implements AfterEachCallback {
	
	private final Logger logger = LoggerFactory.getLogger(AfterEachWebDriverProcessor.class);
	
	private WebDriver driver;
	
	public void setDriver(WebDriver driver) {
		this.driver = driver;
	}

	@Override
	public void afterEach(ExtensionContext context) throws Exception {
		if (context.getExecutionException().isPresent()) {
			saveScreenshot(context);
		}
		driver.quit();
	}
	
	private void saveScreenshot(ExtensionContext context) {
		try {
			var screenshotFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
			File destination = new File(System.getProperty("user.home")
					+File.separator
					+context.getDisplayName().replace("()", "")
					+".png");
			Files.move(screenshotFile.toPath(), destination.toPath(), StandardCopyOption.REPLACE_EXISTING);
			logger.info(()->destination.getAbsolutePath());
		} catch (IOException e) {
			logger.error(e, ()->e.getMessage());
		}
	}

}
