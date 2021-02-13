package com.bora.hooks;

import com.bora.utilities.DriverFactory;

import io.cucumber.java.After;
import io.cucumber.java.Before;

public class Hooks {
	
	@Before("@ui and not @api")
	public void setUp() {
		DriverFactory.getInstance();
	}

	@After("@ui and not @api")
	public void cleanUp() {
		DriverFactory.cleanUp();
	}

}
