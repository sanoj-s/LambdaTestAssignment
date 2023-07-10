package com.lambdatest.test;

import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.By;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class MobileWebAutomation {

	private RemoteWebDriver driver;
	private String Status = "failed";

	@BeforeMethod
	public void setup() throws MalformedURLException {
		String LT_USERNAME = "sanojs";
		String LT_ACCESS_KEY = "NFCDEhaIbjowUTW5LvUYrcamQvheDh0tI7MsFbIYj32bIMwVJo";
		String hub = "@hub.lambdatest.com/wd/hub";

		DesiredCapabilities caps = new DesiredCapabilities();
		caps.setCapability("platformName", "Android");// if you need to run on iOS then specify PlatformName as "iOS"
		caps.setCapability("deviceName", "Galaxy S20 Ultra");// if you need to run on iPhone 12 Pro Max then specify
																// deviceName as "iPhone 12 Pro Max"
		caps.setCapability("build", "TestNG With Java");
		caps.setCapability("plugin", "git-testng");
		String[] Tags = new String[] { "Feature" };
		caps.setCapability("tags", Tags);
		driver = new RemoteWebDriver(new URL("https://" + LT_USERNAME + ":" + LT_ACCESS_KEY + hub), caps);
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