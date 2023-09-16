package com.inetbanking.testCases;

import java.io.File;



import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.log4j.Logger;

import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import org.openqa.selenium.firefox.FirefoxDriver;


import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import com.inetbanking.utilities.ReadConfig;

public class BaseClass {

//	public String baseURL = "https://demo.guru99.com/v3/index.php ";
//	public String username="mngr524551";
//	public String password="usubygy";

	ReadConfig readConfig = new ReadConfig();

	public String baseURL = readConfig.getApplicationURL();
	public String username = readConfig.getUsername();
	public String password = readConfig.getPassword();

	public static WebDriver driver;


	public static Logger logger;

	@Parameters("browser")
	@BeforeClass
	public void setup(String br) {
//		System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir")+"//Drivers//chromedriver.exe"); ye jb direct base class mai value ho

		logger = Logger.getLogger("ebanking");
		PropertyConfigurator.configure("Log4j2.properties");

		if (br.equals("chrome")) {
			System.setProperty("Webdriver.chrome.driver", readConfig.getChromePath()); 
			driver = new ChromeDriver();
		}

		else if (br.equals("firefox")) {
			System.setProperty("webdriver.gecko.driver", readConfig.getFirefoxPath());
			driver = new FirefoxDriver();
		}
		driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
		driver.manage().window().maximize();
		driver.get(baseURL);
		
		
		
	}

	/*
	 * @AfterClass public void tearDown() { driver.quit(); }
	 */

	public void captureScreen(WebDriver driver, String tname) throws IOException {
		TakesScreenshot ts = (TakesScreenshot) driver;
		File source = ts.getScreenshotAs(OutputType.FILE);
		File target = new File(System.getProperty("user.dir") + "/Screenshots/" + tname + ".png");
		FileUtils.copyFile(source, target);
		System.out.println("Screenshot taken");

	}
	
	
	public String randomstring() {
		String generatedstring = RandomStringUtils.randomAlphabetic(6); // 6 char string
		return (generatedstring);
	}

	public String randomNum() {
		String generatedstring2 = RandomStringUtils.randomNumeric(6); // 6 char string
		return (generatedstring2);
	}
}
