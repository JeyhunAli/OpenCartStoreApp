package com.qa.opencart.tests;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.opencart.utils.Constants;
import com.qa.opencart.utils.ExcelUtil;

public class LoginPageNegativeTest extends BaseTest {

	@Test
	public void loginPageTitleTest() {
		String title = loginPageNegative.getLoginPageTitle();
		System.out.println("login page title is:  " + title);
		Assert.assertEquals(title, Constants.LOGIN_PAGE_TITLE);
	}

	@DataProvider
	public Object[][] loginNegativeData() {
		Object[][] negativeData = ExcelUtil.getTestData(Constants.LOGIN_SHEET_NAME);
		return negativeData;
	}

	@Test(dataProvider = "loginNegativeData")
	public void appLoginNegative_Test(String username, String password) {
		softAssert.assertTrue(loginPageNegative
				.loginApp(username, password));
	}

}
