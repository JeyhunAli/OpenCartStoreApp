package com.qa.opencart.tests;

import java.util.List;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.qa.opencart.utils.Constants;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;

@Epic("EPIC - 101 desigining and login with correct credentials to Login Page")
@Story("User Story - 101.1 Login Page features")
public class LoginPageTest extends  BaseTest {
	
	@Description("login page title test .....")
	@Severity(SeverityLevel.NORMAL)
	@Test
	public void loginPageTitleTest() {
		String title = loginPage.getLoginPageTitle();
		System.out.println("login page title is:  "+ title);
		Assert.assertEquals(title, Constants.LOGIN_PAGE_TITLE);
	}
	
	@Description("login page forgot password link test .....")
	@Severity(SeverityLevel.CRITICAL)
	@Test
	public void forgotPwdLinkTest() {
		Assert.assertTrue(loginPage.isForgotPwdLinkExist());
	}

	@Description("login page correct credentials test .....")
	@Severity(SeverityLevel.BLOCKER)
	@Test
	public void loginTest() {
		loginPage.doLogin(prop.getProperty("username").trim(), prop.getProperty("password").trim());
	}
	
	@Description("login page footer Links test .....")
	@Severity(SeverityLevel.CRITICAL)
	@Test
	public void loginPagefooterLinksTest() {
		List<String> footerList = loginPage.getFooterLinks();
		//this will print in the form string
		for(String f: footerList) {
			System.out.println(f);
		}
		//this will print in the form of array
		System.out.println("footer Link List:     "+footerList);
		Assert.assertEquals(footerList.size(), 15);
		Assert.assertTrue(footerList.contains("About Us"));
		
	
	
	
	
	
	
	}
	
	
	
	
	
	

}
