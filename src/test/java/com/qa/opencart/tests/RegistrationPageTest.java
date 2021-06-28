package com.qa.opencart.tests;

import java.util.Random;

import org.apache.commons.lang3.RandomStringUtils;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.opencart.utils.Constants;
import com.qa.opencart.utils.ExcelUtil;

public class RegistrationPageTest extends BaseTest {

	@BeforeClass
	public void setUpRegistration() {
		registrationPage = loginPage.navigateToRegisterPage();
	}
	
	//generate numbers 
	public String getRandomNumber() {
		Random randomGenerator = new Random();
		String email = "testautomation"+randomGenerator.nextInt(10000)+"@gmail.com";
		return email;
	}
	//generate alphabetic 10 digit letters
	// donwload  commons-lang3-3.12.0.jar file then add as extarnal jar to the project not dependancy
	public String getrandomAlphabetic() {
		String alphabetic = "testautomation"+RandomStringUtils.randomAlphabetic(10)+"@gmail.com";
		System.out.println("generated email is: " + alphabetic );
		return alphabetic;
	}
	
	@Test
	public void getRegistrationPageParagraph_Test() {
		String pText = registrationPage.getRegistrationPageParagraph();
		System.out.println("Registration Page Paragraph: "+pText);
		softAssert.assertEquals(pText, Constants.REGISTARTION_PAGE_PARAGRAPH);
		softAssert.assertAll();
	}
	
	
	
	@DataProvider
	public Object[][] getRegisterData() {
		Object regData[][] = ExcelUtil.getTestData(Constants.REGISTER_SHEET_NAME);
		return regData;
	}
	
	@Test(dataProvider = "getRegisterData")
	public void userRegistrationTest(String firstName, String lastName, 
								 String phone, String password, String subscribe) {
		
	Assert.assertTrue
					(registrationPage.
								accountRegistration(firstName, lastName, 
										getrandomAlphabetic(), phone,
														password, subscribe));
	}
	
	
	

}