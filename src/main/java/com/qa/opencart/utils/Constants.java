package com.qa.opencart.utils;

import java.util.Arrays;
import java.util.List;

public class Constants {
	
	public static final int DEFAULT_TIME_OUT = 5;

	
	public static final String LOGIN_PAGE_TITLE = "Account Login";
	public static final String Account_PAGE_TITLE = "My Account";
	public static final String Account_PAGE_header = "Your Store";
	
	public static final int Account_sectionList_Count = 4;
	public static final List<String> accuntPageSectionList 
	                                                    = Arrays.asList
	                                                    ("My Account", 
	                                    	  	        "My Orders", 
	                                    		        "My Affiliate Account", 
	                                    		        "Newsletter"
	                                    		        );
	
	
	
	public static final int Account_section_Group_List_Count = 13;
	public static final List<String> accuntPageGroup_linkList 
                                                         = Arrays.asList
                                                        ("My Account", 
                                                         "Edit Account", 
                                                         "Password", 
                                                         "Address Book",
                                                         "Wish List",
                                                         "Order History",
                                                         "Downloads",
                                                         "Recurring payments",
                                                         "Reward Points",
                                                         "Returns",
                                                         "Transactions",
                                                         "Newsletter",
                                                         "Logout"
                                                         );

	
	public static final int IMAC_IMAGE_COUNT = 3;
	public static final int MACBOOK_PRO_IMAGE_COUNT = 4;
	
	public static final String REGISTER_SUCCESS_MESSG = "Your Account Has Been Created!";
	
	public static final List<String> PRODUCTINFOPAGE_TOP_linkList 
	 = Arrays.asList
   ("Desktops", 
    "Laptops & Notebooks", 
    "Components", 
    "Tablets",
    "Software",
    "Phones & PDAs",
    "Cameras",
    "MP3 Players"
    );
	
	
	
	public static final List<String> TOP_RIGHT_linkList 
	 = Arrays.asList
  ("123456789", 
   "My Account", 
   "Wish List (0)", 
   "Shopping Cart",
   "Checkout"
   );
	
	
	/******************Sheet Names********************/
	public static final String REGISTER_SHEET_NAME = "register";
	public static final String LOGIN_SHEET_NAME = "NegativeTestData";
	
	
	public static final String REGISTARTION_PAGE_PARAGRAPH = "If you already have an account with us, please login at the login page.";
	
	
	
	
	
	
	
	
	
	
	

}