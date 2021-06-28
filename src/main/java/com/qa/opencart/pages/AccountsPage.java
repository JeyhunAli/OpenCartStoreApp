package com.qa.opencart.pages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.opencart.utils.Constants;
import com.qa.opencart.utils.ElementUtil;

public class AccountsPage {

	private WebDriver driver;
	private ElementUtil elementUtil;

	// 1. By Locators: private
	private By header = By.cssSelector("div#logo a");
	private By contentheader = By.cssSelector("div#content h2");
	private By listgrouplink = By.cssSelector("div.list-group > a");
	private By logaOut = By.xpath("(//a[contains(text(), 'Logout')])[position()=1]");
	private By searchField = By.xpath("//input[@name='search' and @type='text']");
	private By searchButton = By.cssSelector("div#search button");

	// 2. constructor:
	public AccountsPage(WebDriver driver) {
		this.driver = driver;
		elementUtil = new ElementUtil(driver);
	}

	// 3. page actions:
	public String getAccPageTitle() {
		return elementUtil.getPageTitle(Constants.Account_PAGE_TITLE, Constants.DEFAULT_TIME_OUT);
	}

	public boolean getAccPageUrl() {
		return elementUtil.waitForURL("route=account", Constants.DEFAULT_TIME_OUT);
	}

	public String getAccPageHeader() {
		return elementUtil.doGetText(header);
	}

	public List<String> getAccountSectionsList() {
		List<String> accSecValList = new ArrayList<String>();
		List<WebElement> accSecList = elementUtil.waitForVisibilityOfElements(contentheader,
				Constants.DEFAULT_TIME_OUT);
		for (WebElement e : accSecList) {
			accSecValList.add(e.getText());
		}
		return accSecValList;
	}
	

	public List<String> getAccountlistgrouplink() {
		List<String> accountListGroup = new ArrayList<String>();
		List<WebElement> acclist = elementUtil.waitForVisibilityOfElements(listgrouplink, Constants.DEFAULT_TIME_OUT);
		for (WebElement e : acclist) {
			accountListGroup.add(e.getText());
		}
		return accountListGroup;
	}

	
	public boolean isLogoutLinkDisplayed() {
		return elementUtil.doIsDisplayed(logaOut);
	}

	
	public void logout() {
		if (isLogoutLinkDisplayed()) {
			elementUtil.doClick(logaOut);
		}
	}

	public SearchResultsPage doSearch(String productName) {
		System.out.println("Searching product: " + productName);
		elementUtil.doSendKeysWithFirstClear(searchField, productName);
		elementUtil.doClick(searchButton);
		return new SearchResultsPage(driver);
	}

}
