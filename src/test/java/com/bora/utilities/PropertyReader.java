package com.bora.utilities;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Properties;

import org.openqa.selenium.By;

public class PropertyReader {

	static String dataFilePath = "src/test/resources/testDataLily/url.propertiesOne";
	static String locatorFilePath = "src/test/resources/testDataLily/locator.properties";
	static Properties dataProp = new Properties();
	static Properties locatorProp = new Properties();
	public PropertyReader() {
		try {
			FileInputStream fileToRead = new FileInputStream(dataFilePath);
			dataProp.load(fileToRead);
			FileInputStream locatorToRead = new FileInputStream(locatorFilePath);
			locatorProp.load(locatorToRead);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	//data 
	public static String readURL(String key) {
		return dataProp.getProperty(key);
	}
	public static String userEmail(String key) {
		String user = dataProp.getProperty(key);
		String userName = user.split("\\|")[0];
		return userName;
	}
	public static String userPassword(String key) {
		String user = dataProp.getProperty(key);
		String userPassword = user.split("\\|")[1];
		return userPassword;
	}
	public static String postMessage(String key){
		String post = dataProp.getProperty(key);
		return post;
	}
	//locator 
	
	public By readLocator(String key){
		String locator = locatorProp.getProperty(key);
		By returnLocator = null;
		String locatorType = locator.split("\\|")[0];
		String locatorValue = locator.split("\\|")[1];
		switch (locatorType.toLowerCase()){
		case "xpath" :
			returnLocator = By.xpath(locatorValue);
			break;
		case "id" :
			returnLocator = By.id(locatorValue);
			break;
		case "linktext" :
			returnLocator = By.linkText(locatorValue);
			break;
		default:
			System.out.println("The loactor is not expected, the locator type you have is: " +locatorType);
		}
		return returnLocator;
	}
}
