package com.lambdatest.test;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;

import org.openqa.selenium.By;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class MobileNativeAutomation {

	private RemoteWebDriver driver;
	private String Status = "failed";

	@BeforeMethod
	public void setup() throws MalformedURLException {
		String LT_USERNAME = "sanojs";
		String LT_ACCESS_KEY = "NFCDEhaIbjowUTW5LvUYrcamQvheDh0tI7MsFbIYj32bIMwVJo";

		DesiredCapabilities capabilities = new DesiredCapabilities();
		HashMap<String, Object> ltOptions = new HashMap<String, Object>();
		ltOptions.put("w3c", true);
		ltOptions.put("platformName", "ios");
		ltOptions.put("deviceName", "iPad 10.9 (2022)");
		ltOptions.put("platformVersion", "16");
		ltOptions.put("app", "your iOS application path from LambdaTest");// you have to upload your application into
																			// LambdaTest cloud to get the application
																			// path like lt://APP10020106163
		ltOptions.put("isRealMobile", true);
		capabilities.setCapability("lt:options", ltOptions);
		driver = new RemoteWebDriver(
				new URL("https://" + LT_USERNAME + ":" + LT_ACCESS_KEY + "@beta-hub.lambdatest.com/wd/hub"),
				capabilities);

	}

	@Test
	public void basicTest() throws InterruptedException {
		System.out.println("Loading Url");
		driver.get("https://journeyofquality.com/");
		System.out.println("Checking Box");
		driver.findElement(By.name("li1")).click();
		System.out.println("TestFinished");
	}

	@AfterMethod
	public void tearDown() {
		driver.executeScript("lambda-status=" + Status);
		driver.quit();
	}

}