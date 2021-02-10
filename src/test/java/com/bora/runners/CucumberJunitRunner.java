package com.bora.runners;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
		tags = "@ui",
		plugin = { "pretty", "html:target/htmlReports/report.html" }, 
		features = { "src/test/resources/featureFiles" }, 
		glue = {"com.bora.apiStepDefinitions", "com.bora.uiStepDefinitions", "com.bora.hooks" },
		monochrome = true
)
public class CucumberJunitRunner {

}
