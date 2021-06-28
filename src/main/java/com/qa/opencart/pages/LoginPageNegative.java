package com.qa.opencart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.opencart.utils.Constants;
import com.qa.opencart.utils.ElementUtil;

public class LoginPageNegative {

	private WebDriver driver;
	private ElementUtil elementUtil;

	private By emailId = By.id("input-email");
	private By password = By.id("input-password");
	private By loginButton = By.xpath("//input[@value='Login']");
	private By errorMesg = By.cssSelector("div.alert.alert-danger.alert-dismissible");

	public LoginPageNegative(WebDriver driver) {
		this.driver = driver;
		elementUtil = new ElementUtil(this.driver);
	}

	/**
	 * @return title of the page
	 */
	public String getLoginPageTitle() {
		return elementUtil.getPageTitle(Constants.LOGIN_PAGE_TITLE, Constants.DEFAULT_TIME_OUT);
	}

	/**
	 * @param un
	 * @param pwd
	 * @return negative test case with negative data
	 */
	public boolean loginApp(String un, String pwd) {
		elementUtil.doSendKeysWithFirstClear(emailId, un);
		// elementUtil.doVisibilityOfElement(password, Constants.DEFAULT_TIME_OUT);
		// elementUtil.doClear(password);
		elementUtil.doSendKeysWithFirstClear(password, pwd);
		elementUtil.doActionsClick(loginButton);
		String errorMesseage = elementUtil.doGetText(errorMesg);
		if (errorMesseage.contains("No match for E-Mail Address and/or Password.")) {
			return true;
		}
		return false;

	}

}
