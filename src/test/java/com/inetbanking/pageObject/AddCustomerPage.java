package com.inetbanking.pageObject;

import java.util.Iterator;
import java.util.Set;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

public class AddCustomerPage {

	WebDriver ldriver;

	public AddCustomerPage(WebDriver rdriver) {
		ldriver = rdriver;
		PageFactory.initElements(rdriver, this);
	}

	@CacheLookup
	@FindBy(how = How.XPATH, using = "//a[normalize-space()='New Customer']")
	WebElement lnkAddNewCustomer;

	@CacheLookup
	@FindBy(how = How.XPATH, using = "//span[contains(text(),'Close')]")
	WebElement dissmiss;

	@CacheLookup
	@FindBy(how = How.NAME, using = "name")
	WebElement txtCustomername;

	@CacheLookup
	@FindBy(how = How.XPATH, using = "//*[@value='m']")
	WebElement rdGender;

	@CacheLookup
	@FindBy(how = How.NAME, using = "dob")
	WebElement txtdob;

	@CacheLookup
	@FindBy(how = How.NAME, using = "addr")
	WebElement txtaddress;

	@CacheLookup
	@FindBy(how = How.NAME, using = "city")
	WebElement txtcity;

	@CacheLookup
	@FindBy(how = How.NAME, using = "state")
	WebElement txtstate;

	@CacheLookup
	@FindBy(how = How.NAME, using = "pinno")
	WebElement txtpinno;

	@CacheLookup
	@FindBy(how = How.NAME, using = "telephoneno")
	WebElement txttelephoneno;

	@CacheLookup
	@FindBy(how = How.NAME, using = "emailid")
	WebElement txtemailid;

	@CacheLookup
	@FindBy(how = How.NAME, using = "password")
	WebElement txtpassword;

	@CacheLookup
	@FindBy(how = How.NAME, using = "sub")
	WebElement btnSubmit;

	public void clickAddNewCustomer() {
		lnkAddNewCustomer.click();
	}

	public void dissMiss() {
		/*
		 * Set wnd = ldriver.getWindowHandles(); Iterator i = wnd.iterator(); String
		 * popwnd = (String) i.next(); String prntw = (String) i.next();
		 * ldriver.switchTo().window(popwnd);
		 */
		ldriver.switchTo().frame(1);
		dissmiss.click();
	}

	public void custName(String cname) {
		ldriver.switchTo().parentFrame();
		txtCustomername.sendKeys(cname);
	}

	public void custgender() {
		rdGender.click();
	}

	public void custdob(String mm, String dd, String yy) {
		txtdob.sendKeys(mm);
		txtdob.sendKeys(dd);
		txtdob.sendKeys(yy);
	}

	public void custaddress(String caddress) {
		txtaddress.sendKeys(caddress);
	}

	public void custcity(String ccity) {
		txtcity.sendKeys(ccity);
	}

	public void custstate(String cstate) {
		txtstate.sendKeys(cstate);
	}

	public void custpinno(String cpinno) {
		txtpinno.sendKeys(String.valueOf(cpinno));
	}

	public void custtelephoneno(String ctelephoneno) {
		txttelephoneno.sendKeys(ctelephoneno);
	}

	public void custemailid(String cemailid) {
		txtemailid.sendKeys(cemailid);
	}

	public void custpassword(String cpassword) {
		txtpassword.sendKeys(cpassword);
	}

	public void custsubmit() {
		btnSubmit.click();
	}
}
