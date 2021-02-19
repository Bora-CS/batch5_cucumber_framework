package com.bora.utilities;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Properties;

import org.openqa.selenium.By;

public class PropertyReader {

	String dataFilePath = "testData/url.properties";
	String locatorFilePath = "testData/locator.properties";
	Properties prop = new Properties();
	Properties locatorProp = new Properties();

	
	
	public PropertyReader() {
		try {
	
			 FileInputStream	DataFile = new FileInputStream(locatorFilePath);
			 FileInputStream	LocatorFile = new FileInputStream(dataFilePath);
		
			prop.load(DataFile);
			locatorProp.load(LocatorFile);

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	
//	public PropertyReader(String fileType) {
//		try {
//			FileInputStream file;
//			if (fileType.equalsIgnoreCase("locator")) {
//				file = new FileInputStream(locatorFilePath);
//			} else {
//				file = new FileInputStream(dataFilePath);
//			}
//			prop.load(file);
//
//		} catch (FileNotFoundException e) {
//			e.printStackTrace();
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//	}



	public By locatorReader(String key) {

		String locator = locatorProp.getProperty(key);
		By returnLocator = null;
		// editProfileLink --> By.xpath("//*[@href=\"/edit-profile\"]")
		// --> By.linkText("Login")
		// LocatorType LocatorValue
		// By.xpath ("//*[@href=\"/edit-profile\"]")
		// xpath/id/linkText | //*[@href=\"/edit-profile\"]
		String locatorType = locator.split("|")[0];
		String locatorValue = locator.split("|")[1];

		switch (locatorType.toLowerCase()) {
		case "xpath": // ==
			returnLocator = By.xpath(locatorValue);
			break;
		case "id":
			returnLocator = By.id(locatorValue);
			break;
		case "linktext":
			returnLocator = By.linkText(locatorValue);
			break;
		default:
			System.out.println("locator is not valid or not in the system " + locatorType);
		}

		return returnLocator;
	}

	public HashMap getDataMap(String[] keys) { // keys --> url1, LoginPage, url2

		HashMap<String, String> dataMap = new HashMap<String, String>();

		for (String key : keys) {

			dataMap.put(key, prop.getProperty(key));

		}

		return dataMap;
	}

	public String urlData(String urlKey) {

		String URL = prop.getProperty(urlKey);

		return URL;

	}

	public String[] userData(String userKey) {

		String user = prop.getProperty(userKey);
		// user --> murad@test.com|murad001
		String userName = user.split("|")[0];

		String[] userArray = user.split("|");
		return userArray;
	}

}
