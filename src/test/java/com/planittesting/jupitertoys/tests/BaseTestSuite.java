package com.planittesting.jupitertoys.tests;

import java.util.concurrent.TimeUnit;

import com.planittesting.automation.driver.support.CustomWebDriverEventListener;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.RegisterExtension;
import org.junit.jupiter.api.parallel.Execution;
import org.junit.jupiter.api.parallel.ExecutionMode;
import org.openqa.selenium.WebDriver;

import com.planittesting.automation.driver.DriverFactory;
// import com.planittesting.automation.driver.DriverFactoryBuilder;
//import com.planittesting.automation.environment.EnvironmentVariables;
import com.planittesting.automation.environment.EnvironmentVariables;
import org.openqa.selenium.support.events.EventFiringWebDriver;

@Execution(ExecutionMode.CONCURRENT)
public class BaseTestSuite {

	protected WebDriver driver;

	@RegisterExtension
	AfterEachWebDriverProcessor webDriverProcessor = new AfterEachWebDriverProcessor();

	@BeforeEach
	public void setupTest() throws Exception {
		driver = new EventFiringWebDriver(
			new DriverFactory()
				.setHeadless(EnvironmentVariables.isHeadless())
				.setGridUrl(EnvironmentVariables.getGridUrl())
				.getInstance(EnvironmentVariables.getBrowser())
		).register(new CustomWebDriverEventListener());
		driver.manage().timeouts().implicitlyWait(EnvironmentVariables.getImplicitWait(), TimeUnit.SECONDS);
		driver.manage().window().maximize();
		driver.navigate().to(EnvironmentVariables.getUrl());
	}

	@AfterEach
	public void shutdownTest() {
		webDriverProcessor.setDriver(driver);
	}

}
