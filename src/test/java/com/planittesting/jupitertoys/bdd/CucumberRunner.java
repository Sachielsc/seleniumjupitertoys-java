package com.planittesting.jupitertoys.bdd;


import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(plugin = { "pretty" }, 
			     features = { "src/test/resources/features" }, 
			     glue = {"com.planittesting.jupitertoys.bdd.steps" })
public class CucumberRunner {
	
}
