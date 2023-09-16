package com.inetbanking.testCases;

import java.io.IOException;


import org.openqa.selenium.NoAlertPresentException;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.inetbanking.pageObject.LoginPage;
import com.inetbanking.utilities.XLUtils;

import junit.framework.Assert;

public class TC_LoginDDT_002 extends BaseClass {

	@Test(dataProvider = "LoginData")
	public void loginDDT(String user, String password) throws InterruptedException {

		LoginPage lp = new LoginPage(driver);
		lp.setUserName(user);
		lp.setPassword(password);
		lp.clickSubmit();
		
		Thread.sleep(3000);

		if (isAlertPresent() == true) {
			driver.switchTo().alert().accept();
			driver.switchTo().defaultContent();
			Assert.assertTrue(false);
		}

		else {
			Assert.assertTrue(true);
			Thread.sleep(3000);
			lp.cliclLogout();
			driver.switchTo().alert().accept(); // logout alert
			driver.switchTo().defaultContent();
		}
	}

	public boolean isAlertPresent() { // to check alert id present of not
		try {
			driver.switchTo().alert();
			return true; // if not presnt do exception
		} catch (NoAlertPresentException e) {
			return false;
		}
	}

	@DataProvider(name = "LoginData")
	String[][] getData() throws IOException {
		String path = System.getProperty("user.dir") + "\\src\\test\\java\\com\\inetbanking\\testData\\upload.xlsx";
		int rownum = XLUtils.getRowCount(path, "Sheet1");
		int cocount = XLUtils.getCellCount(path, "Sheet1", 1);

		String logindata[][] = new String[rownum][cocount];

		for (int i = 1; i <= rownum; i++) {
			for (int j = 0; j < cocount; j++) // if j from 0 then i<=
			{
				logindata[i - 1][j] = XLUtils.getCellData(path, "Sheet1", i, j);

			}
		}
		return logindata;

	}

}
