package com.bora.utilities;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Properties;

import org.openqa.selenium.By;

public class PropertyReader {
	String dataFilePath = "src/test/resources/testData/url.properties";
	String locatorFilePath = "src/test/resources/testData/locator.properties";
	Properties prop = new Properties();
	Properties locatorProp = new Properties();

	public PropertyReader() {

		try {
			FileInputStream DataFile = new FileInputStream(dataFilePath);
			FileInputStream LocatorFile = new FileInputStream(locatorFilePath);
			prop.load(DataFile);
			Properties locatorProp;
			locatorProp.load(LocatorFile);

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

//	public static void main(String[] args) {
//		PropertyReader pr = new PropertyReader();// create object
//		System.out.println(pr.urlData());
//	}

	public By locatorReader(String Key) {

		String locator = locatorProp.getProperty(Key);
		By returnLocator = null;
		String locatorType = locator.split("|")[0];
		String locatorValue = locator.split("|")[1];
		switch (locatorType.toLowerCase()) {
		case "xpath":
			returnLocator = By.xpath(locatorValue);
			break;
		case "id":
			returnLocator = By.id(locatorValue);
			break;
		case "linktext":
			returnLocator = By.linkText(locatorValue);
			break;
		default:
			System.out.println("locator is not valid or not in the system" + locatorType);
		}
		return returnLocator;

	}

	public HashMap getDataMap(String[] keys) {// keys --> url,LoginPage, url2

		HashMap<String, String> dataMap = new HashMap<String, String>();
		for (String key : keys) {
			dataMap.put(key, prop.getProperty(key));
		}
		return dataMap;
	}

	public String urlData(String urlKey) {

		String URL = prop.getProperty(urlKey);
		// String URL = prop.getProperty("url3");
		return URL;
	}

	public String[] userData(String userKey) {

	String user = prop.getProperty(userKey);
	//String user = prop.getProperty("user1");
	//user ---> "murad@test.com| murad001"
	String userName =user.split("|")[0];
	
	String[]userArray =user.split("|");
	return userArray;
	}
}
