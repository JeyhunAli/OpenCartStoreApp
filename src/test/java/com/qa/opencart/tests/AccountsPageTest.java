package com.qa.opencart.tests;

import java.util.List;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import com.qa.opencart.utils.Constants;

/**
 * 
 * @author jey first creatinng object of softassert in base class so that all
 *         the test class is extending base class can access soft assert concept
 *         after declaring soft assert need to write this line
 *         softAssert.assertAll(); because if we dont write this line it will
 *         execute all the code but if there are any error it wont notify so to
 *         write is is compulsary the concept of soft assertion lets say we have
 *         soft assertion after that coming any another assertion if our soft
 *         assertion fail it wont stop execution it will execute anoter test
 *         cases ieven if there are another hard assertion as well
 *
 *
 *         here in search test we passing product name but lets say if i want to
 *         search 5-10 product at time for that i have some options passing from
 *         excel sheet, data provider testng or maintaning in constants class
 *         here im gonna use data provider with two dimensial array
 */

public class AccountsPageTest extends BaseTest {

	/**
	 * @BeforeClass will execute after @Beforetest
	 */
	@BeforeClass
	public void accountPageSetUp() {
		accountsPage = loginPage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
	}

	@Test
	public void accountPageTitleTest() {
		String title = accountsPage.getAccPageTitle();
		System.out.println("Account Page title is: " + title);
		Assert.assertEquals(title, Constants.Account_PAGE_TITLE);
	}

	@Test
	public void accountPageHeaderTest() {
		String header = accountsPage.getAccPageHeader();
		System.out.println("Account Page header is: " + header);
		Assert.assertEquals(header, Constants.Account_PAGE_header);
	}

	@Test
	public void accountPageSectionsListTest() {
		List<String> secList = accountsPage.getAccountSectionsList();
		softAssert.assertEquals(secList.size(), Constants.Account_sectionList_Count);
		Assert.assertEquals(secList, Constants.accuntPageSectionList);
		System.out.println("section list: "+ secList);
		softAssert.assertAll();
	}

	@Test
	public void accountlistgrouplinkTest() {
		List<String> listGroup = accountsPage.getAccountlistgrouplink();
		softAssert.assertEquals(listGroup.size(), Constants.Account_section_Group_List_Count);
		softAssert.assertEquals(listGroup, Constants.accuntPageGroup_linkList);
		System.out.println("section group list: "+ listGroup);
		softAssert.assertAll();
	}

	/**
	 * data provider with two dimension array
	   org.testng.internal.reflect.MethodMatcherException:  if any mismatch
	   in case if all the inside one curly brackets 
	   
	 * @return
	 * 
	 * this concept is is called delta searching concept lets say huge products are there 
	 * from each category few data passing and checking 
	 * 
	 * 
	 */
	@DataProvider
	public Object[][] productData() {
		
		return new Object[][] { 
			
			{ "iMac" }, 
			{ "MacBook" }, 
		    { "Apple" }
			
		};
		
	}

	@Test(dataProvider = "productData")
	public void searchTest(String productName) {
		searchResultsPage = accountsPage.doSearch(productName);
	    Assert.assertTrue(searchResultsPage.getSearchProductListCount()>0);
	}
	
	/**
	 * this data provider method says 
	 * you write in search bar MacBook and from results select MacBook Pro
	 * and here Apple Cinema 30\""   cinema 30 inch tv with data provider we write it with back slash
	 * @return
	 */
	
	    @DataProvider
		public Object[][] productSelectData() {
			return new Object[][]
					
		     { 
				{ "MacBook" , "MacBook Pro"},  
				{"Apple", "Apple Cinema 30\""}
			 };
		}
			
	    /**
	     * 1st calling accountsPage page search method passing searched product name 
	     * 2nd calling searchResultsPage page method to select the product from results
	     * @param productName
	     * @param mainProductName
	     */
		@Test(dataProvider = "productSelectData")
		public void selectProductTest(String productName, String mainProductName) {
			searchResultsPage = accountsPage.doSearch(productName);
			searchResultsPage.selectProduct(mainProductName);
		}
		
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
