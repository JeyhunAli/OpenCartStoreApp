package com.qa.opencart.tests;

import java.util.List;
import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.qa.opencart.utils.Constants;

public class ProductInfoPageTest extends BaseTest {

	@BeforeClass
	public void productInfoSetUp() {
		accountsPage = loginPage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
	}

	@Test
	public void productInfoHeaderTest() {
		searchResultsPage = accountsPage.doSearch("Macbook");
		productInfoPage = searchResultsPage.selectProduct("MacBook Pro");
		String productHeader = productInfoPage.getProductHeaderText();
		System.out.println("productHeader is:   " + productHeader);
		Assert.assertEquals(productInfoPage.getProductHeaderText(), "MacBook Pro");
		
	}
	
	@Test
	public void getProductPageTopLinksTest() {
		searchResultsPage = accountsPage.doSearch("Macbook");
		productInfoPage = searchResultsPage.selectProduct("MacBook Pro");
		//top two lines will bring you to product info page by selecting macbook
	    softAssert.assertEquals(productInfoPage.getProductPageTopLinks(),Constants.PRODUCTINFOPAGE_TOP_linkList);
	    softAssert.assertAll();
	}
	
	@Test
	public void getTOPRightLinksList_Test() {
		searchResultsPage = accountsPage.doSearch("Macbook");
		productInfoPage = searchResultsPage.selectProduct("MacBook Pro");
		List<String> listofTopRightLink = productInfoPage.getTOPRightLinksList();
		System.out.println("top right links list: "+ listofTopRightLink);
		softAssert.assertEquals(listofTopRightLink, Constants.TOP_RIGHT_linkList);
		softAssert.assertAll();
	}
	
	@Test
	public void productImagesTest() {
		searchResultsPage = accountsPage.doSearch("iMac");
		productInfoPage = searchResultsPage.selectProduct("iMac");
		Assert.assertEquals(productInfoPage.getProductImagesCount(), Constants.IMAC_IMAGE_COUNT);
	}

	@Test
	public void productInfoTest() {
		searchResultsPage = accountsPage.doSearch("Macbook");
		productInfoPage = searchResultsPage.selectProduct("MacBook Pro");
		Map<String, String> actProductInfoMap = productInfoPage.getProductInfo();

		actProductInfoMap.forEach((k, v) -> System.out.println(k + " : " + v));

		softAssert.assertEquals(actProductInfoMap.get("name"), "MacBook Pro");
		softAssert.assertEquals(actProductInfoMap.get("Brand"), "Apple");
		softAssert.assertEquals(actProductInfoMap.get("Product Code"), "Product 18");
		softAssert.assertTrue(actProductInfoMap.get("price").contains("2,000"));

		softAssert.assertAll();
	}

}