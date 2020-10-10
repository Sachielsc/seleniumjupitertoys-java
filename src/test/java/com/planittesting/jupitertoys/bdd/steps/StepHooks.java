package com.planittesting.jupitertoys.bdd.steps;



import java.util.concurrent.TimeUnit;

import com.planittesting.automation.driver.support.CustomWebDriverEventListener;
import org.openqa.selenium.WebDriver;

import com.planittesting.automation.driver.DriverFactory;
// import com.planittesting.automation.driver.DriverFactoryBuilder;
import com.planittesting.automation.environment.EnvironmentVariables;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import org.openqa.selenium.support.events.EventFiringWebDriver;


public class StepHooks {

	private ObjectContainer objectContainer;
	

	public StepHooks(ObjectContainer objectContainer) {
		this.objectContainer = objectContainer;
	}

	@Before
	public void setup() throws Exception {
		WebDriver driver = new EventFiringWebDriver(
				new DriverFactory()
						.setHeadless(EnvironmentVariables.isHeadless())
						.setGridUrl(EnvironmentVariables.getGridUrl())
						.getInstance(EnvironmentVariables.getBrowser())
		).register(new CustomWebDriverEventListener());
		driver.manage().timeouts().implicitlyWait(EnvironmentVariables.getImplicitWait(), TimeUnit.SECONDS);
		driver.manage().window().maximize();
		driver.navigate().to(EnvironmentVariables.getUrl());

		this.objectContainer.setDriver(driver);
	}

	@After
	public void cleanup() {
		objectContainer.getDriver().quit();
	}

}
