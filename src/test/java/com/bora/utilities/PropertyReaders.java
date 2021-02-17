package com.bora.utilities;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Properties;

import org.openqa.selenium.By;

public class PropertyReaders {

	Properties dataProp = new Properties();
	Properties locatorProp=new Properties();
	String datafilePath = "testDataa/url.properties";
	String locatorFilePath = "testDataa/locator.properties";

	public PropertyReaders() {
		try {
			FileInputStream datafile = new FileInputStream(datafilePath);
			FileInputStream locatorfile =new FileInputStream(locatorFilePath);
			dataProp.load(datafile);
			locatorProp.load(locatorfile);

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		PropertyReaders pr = new PropertyReaders();

	}

	public HashMap getDataMap(String[] keys) {
		HashMap<String, String> dataMap = new HashMap<String, String>();
		for (String key : keys) {
			dataMap.put(key, dataProp.getProperty(key));
		}
		return dataMap;
	}

	public By locatorReader(String key) {

		By returnLocator = null;
		String locator = locatorProp.getProperty(key);
		String locatorType = locator.split("|")[0];
		String locatorValue = locator.split("|")[1];
		switch (locatorType.toLowerCase()) {
		case "xpath":
			returnLocator = By.xpath(locatorValue);

			break;
		case "id":
			returnLocator = By.id(locatorValue);
			break;
		case "Linktext":
			returnLocator = By.linkText(locatorValue);
			break;
		default:System.err.println("Locator is not valid or not in the system");
		}

		return returnLocator;

	}

	public String urlData(String urlkey) {

		String URL = dataProp.getProperty(urlkey);
		return URL;

	}

	public String[] userData(String userKey) {

		String user = dataProp.getProperty(userKey);
		String[] userArray = user.split("|");
		return userArray;
	}
}
