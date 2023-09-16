package com.inetbanking.testCases;

import org.testng.annotations.Test;

import com.inetbanking.pageObject.AddCustomerPage;
import com.inetbanking.pageObject.LoginPage;
import junit.framework.Assert;
import java.io.IOException;

public class TC_AddCustomerTest_003 extends BaseClass {

	@Test
	public void addNewCustomer() throws InterruptedException, IOException {
		LoginPage lp = new LoginPage(driver);
		lp.setUserName(username);
		lp.setPassword(password);
		lp.clickSubmit();

		AddCustomerPage addcust = new AddCustomerPage(driver);

		Thread.sleep(2000);
		addcust.clickAddNewCustomer();

		Thread.sleep(3000);
		addcust.dissMiss();

		addcust.custName("Deepak");

		addcust.custgender();
		addcust.custdob("10", "15", "1985");

		Thread.sleep(3000);
		addcust.custaddress("India");
		addcust.custcity("Noida");
		addcust.custstate("uttar Pradesh");
		addcust.custpinno("4828745");

		String num = randomNum();
		addcust.custtelephoneno(num);

		// user defined function for email
		String eamil = randomstring() + "@gmail.com";
		addcust.custemailid(eamil);

		addcust.custpassword("abcdef");
		addcust.custsubmit();

		Thread.sleep(3000);

		boolean res = driver.getPageSource().contains("Customer Registered Successfully!!!");

		if (res == true) {
			Assert.assertTrue(true);
		} else {
			captureScreen(driver, "addNewCustomer");
			Assert.assertTrue(false);
		}
	}
}
