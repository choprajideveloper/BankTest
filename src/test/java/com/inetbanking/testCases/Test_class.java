package com.inetbanking.testCases;

import java.util.Arrays;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class Test_class {

	public static WebDriver driver;

	public static void main(String[] args) {
		System.setProperty("Webdriver.chrome.driver", "./.Drivers.chromedriver.exe");

		ChromeOptions opt = new ChromeOptions();
		opt.setExperimentalOption("excludeSwitches", Arrays.asList("disable-popup-blocking"));
		driver = new ChromeDriver(opt);
		driver.get("https://www.naukri.com/mnjuser/homepage");
	}

}
