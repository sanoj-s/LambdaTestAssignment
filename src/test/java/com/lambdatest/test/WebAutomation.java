package com.lambdatest.test;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.remote.AbstractDriverOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class WebAutomation {

	private RemoteWebDriver driver;
	private String Status = "failed";

	@BeforeMethod
	@Parameters({ "browser", "version", "platform" })
	public void setup(String browser, String version, String platform) throws MalformedURLException {
		String LT_USERNAME = "sanoj.swaminathan";
		String LT_ACCESS_KEY = "Kc7tZLfuVmz65AdK0j960CYlusuyaJ8SaKm4tCPdWcVk7dUDDH";
		String hub = "@hub.lambdatest.com/wd/hub";

		// Set browser options based on parameters

		AbstractDriverOptions browserOptions = null;
		if (browser.toLowerCase().contains("chrome")) {
			browserOptions = new ChromeOptions();
		} else if (browser.toLowerCase().equals("microsoftedge")) {
			browserOptions = new EdgeOptions();
		} else {
			System.exit(0);
		}
		browserOptions.setPlatformName(platform);
		browserOptions.setBrowserVersion(version);
		HashMap<String, Object> ltOptions = new HashMap<>();
		ltOptions.put("project", "Sample");
		ltOptions.put("w3c", true);
		ltOptions.put("visual", true);
		ltOptions.put("video", true);
		ltOptions.put("network", true);
		ltOptions.put("console", "true");
		ltOptions.put("build", "Sample Build");
		ltOptions.put("name", "Sample Test");
		browserOptions.setCapability("LT:Options", ltOptions);
		driver = new RemoteWebDriver(new URL("https://" + LT_USERNAME + ":" + LT_ACCESS_KEY + hub), browserOptions);
	}

	@Test()
	public void lambdaTestSample() throws InterruptedException {
		System.out.println("Navigate to https://www.lambdatest.com");
		driver.get("https://www.lambdatest.com");
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(40L));
		wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector("*")));

		WebElement lnkSeeAllIntegrations = wait.until(ExpectedConditions
				.visibilityOfElementLocated(By.xpath("//div//div//a[text()='Explore all Integrations']")));
		JavascriptExecutor executor = (JavascriptExecutor) driver;
		executor.executeScript("arguments[0].click();", lnkSeeAllIntegrations);

		Set<String> allHandles = driver.getWindowHandles();
		List<String> windowHandlesList = new ArrayList<>(allHandles);
		for (String handle : windowHandlesList) {
			System.out.println("Window handle name is : " + handle);
		}
		Assert.assertEquals(driver.getCurrentUrl(), "https://www.lambdatest.com/integrations", "Urls do not match");
	}

	@AfterMethod
	public void tearDown() {
		driver.close();
	}
}
