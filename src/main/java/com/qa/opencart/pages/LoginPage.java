package com.qa.opencart.pages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.opencart.utils.Constants;
import com.qa.opencart.utils.ElementUtil;

import io.qameta.allure.Step;

public class LoginPage {

	private WebDriver driver;
	private ElementUtil elementUtil;

	// 1. By Locators: private
	private By emailId = By.id("input-email");
	private By password = By.id("input-password");
	private By loginButton = By.xpath("//input[@value='Login']");
	private By forgotPwdLink = By.linkText("Forgotten Password");
	private By footerLinks = By.xpath("//footer//div[@class='row']//a");
	private By registerLink = By.linkText("Register");
	
	

	// 2. constructor:
	public LoginPage(WebDriver driver) {
		this.driver = driver;
		elementUtil = new ElementUtil(this.driver);
	}

	// 3. page actions:
	@Step("getting Login Page Title")
	public String getLoginPageTitle() {
		return elementUtil.getPageTitle(Constants.LOGIN_PAGE_TITLE, Constants.DEFAULT_TIME_OUT);
	}

	@Step("verification Of Login Page forgot Password link")
	public boolean isForgotPwdLinkExist() {
		return elementUtil.doIsDisplayed(forgotPwdLink);
		}

	@Step("login to App with username: {0} and password: {1} ")
	public AccountsPage doLogin(String un, String pwd) {
		elementUtil.doWaitPresenceOfElementLocated(emailId, Constants.DEFAULT_TIME_OUT).sendKeys(un);
        elementUtil.doSendKeys(password, pwd);
        elementUtil.getElement(loginButton).click();
        return new AccountsPage(driver);
	
	}

	/**
	 * there two method repeated two times one with forloop
	 * another with foreach loop 
	 * but in test class only one of that asserted 
	 * this method is just to example 
	 * @return
	 */
	@Step("gettng footer links list ")
	public List<String> getFooterlinks_1() {
		List<WebElement> footerList = elementUtil.getElements(footerLinks);
		List<String> footerTextList = new ArrayList<String>();
		for(int i=0; i<footerList.size(); i++) {
			footerTextList.add(footerList.get(i).getText());
		}
		return footerTextList;
	}
	
	@Step("gettng footer links list ")
	public List<String> getFooterLinks() {
		List<WebElement> footerList = elementUtil.getElements(footerLinks);
		List<String> footerTextList = new ArrayList<String>();

		for (WebElement e : footerList) {
			footerTextList.add(e.getText());
		}
		return footerTextList;

	}

	/**
	 * navigate to register page:
	 */
	@Step("Navigating to Registartion Page...... ")
	public RegistrationPage navigateToRegisterPage() {
		elementUtil.doClick(registerLink);
		return new RegistrationPage(driver);
	}
	

	
	
	
	
	
	
	
	
}