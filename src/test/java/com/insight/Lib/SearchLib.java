package com.insight.Lib;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

import org.apache.commons.collections.functors.IfClosure;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import com.insight.ObjRepo.CMTObj;
import com.insight.ObjRepo.CartObj;
import com.insight.ObjRepo.CommonObj;
import com.insight.ObjRepo.InvoiceHistoryObj;
import com.insight.ObjRepo.productsDisplayInfoObj;

public class SearchLib extends CommonObj {

	/**
	 * This method is to click on the Primary header and select the product
	 * category from the drop-down and verifies the successful navigation.
	 * 
	 * @param header
	 * @param headerlist
	 * @throws Throwable
	 */
	public void clickOnSecondaryHeaderAndNavigate(String header, String headerlist) throws Throwable {

		click(getSecondaryHeaderMenu(header), "Secondary header link "+header);
		click(getSecondaryMenuListItems(headerlist), "Secondary header links list "+headerlist);
		By BreadCrumb = productsDisplayInfoObj.getBreadCrumbs(headerlist);

		if (isElementPresent(BreadCrumb, "Bread Crumb")) {
			reporter.SuccessReport("Verify the navigation", "Sucessfully Navigated to : " ,headerlist +" page");
		} else {
			reporter.failureReport("Verify the navigation", "Navigation is not Sucessfully : " , headerlist);
		}
	}

	/**
	 * This method is to click on the Type of the product in the product
	 * category page.
	 * 
	 * @param typeOfProduct
	 * @throws Throwable
	 */
	public void selectTheProductByTypeAndVerifyNavigation(String typeOfProduct) throws Throwable {
		click(productsDisplayInfoObj.getProductByType(typeOfProduct), "Product type "+typeOfProduct);
		By BreadCrumb = productsDisplayInfoObj.getBreadCrumbs(typeOfProduct);
		if (isElementPresent(BreadCrumb, "Bread Crumb")) {
			reporter.SuccessReport("Verify the navigation", "Sucessfully Navigated to search results page " , typeOfProduct);
		} else {
			reporter.failureReport("Verify the navigation", "Navigation is not Sucessfull " , typeOfProduct);
		}
	}

	/**
	 * This method is to search the required product in the product category
	 * page Narrow Results search box.
	 * 
	 * @param ProductName
	 * @throws Throwable
	 */
	public void searchProductInProductDisplayPage(String ProductName) throws Throwable {
		Thread.sleep(3000);
		WebElement element = driver.findElement(productsDisplayInfoObj.NARROW_RESULTS_SEARCH);
		safeClearAndTypeAndClickEnter(element, ProductName);
	}

	/**
	 * This method is to navigate to product results page and search for a product.
	 * @param header
	 * @param headerlist
	 * @param typeOfProduct
	 * @param ProductName
	 * @throws Throwable
	 */
	public void shopAndSearchProduct(String header, String headerlist,String typeOfProduct,String ProductName) throws Throwable{
		clickOnSecondaryHeaderAndNavigate(header, headerlist);
		selectTheProductByTypeAndVerifyNavigation(typeOfProduct);
		Thread.sleep(3000);
		searchProductInProductDisplayPage(ProductName);
	}
	
	/**
	 * Method is to verify the Product Name in Compare Similar Products Page
	 * 
	 * @param ProductName
	 * @throws Throwable
	 */
	public void verifyTheProductNameInCompareSimilarProductsPage(String ProductName) throws Throwable {

		String masterProductName = getText(productsDisplayInfoObj.COMPARE_MASTER_PROD_NAME,
				"compare Master Product Name");
		Thread.sleep(3000);
		if (isElementPresent(productsDisplayInfoObj.COMPARE_SIMILAR_PROD_HEADING, "similar product heading")
				&& ProductName.contains(masterProductName)) {
			reporter.SuccessReport("Verify the Master Product in Compare Similar Products Page",
					"Master product displayed Sucessfully with Similar products","Master Product : "+masterProductName);
			reporter.SuccessReport("Selected Product and Master Product in Compare Similar Products Page", "Master Product is Same as Selected Product", "Master Product : "+ProductName);
		} else {
			reporter.failureReport("Verify the Product Name in Compare Similar Products Page",
					"Master product is not displayed.Expected is : ",masterProductName);
		}
	}

	/**
	 * Method is to verify the similar products count
	 * @return
	 * @throws Throwable 
	 */
	public void verifySimilarProductsExists() throws Throwable {
		ProductDisplayInfoLib pipLib=new ProductDisplayInfoLib();
		pipLib.verifySimilarProductLabelExists();
		List<WebElement> elements=driver.findElements(productsDisplayInfoObj.SIMILAR_PRODUCTS);
		int similarProducts=elements.size()-1;
		if(similarProducts!=0) {
			reporter.SuccessReport("Similar products", "Similar products exists",Integer.toString(similarProducts));
		}else {
			reporter.failureReport("Similar products", "Similar products does not exists",Integer.toString(similarProducts));
		}
	}
	
	/**
	 * Method is to click On Add To My Compare List Link and verify the Compare
	 * your list items number
	 * 
	 * @param n
	 * @throws Throwable
	 */
	public void clickOnAddToMyCompareListLinkandVerify(int n) throws Throwable {
		for (i = 0; i <n; i++) {
			Thread.sleep(3000);
			clickUntil(productsDisplayInfoObj.getAddToMyCompareListLink(Integer.toString(i)), productsDisplayInfoObj.URCOMPARELIST(Integer.toString(i)),"compare Link");
			Thread.sleep(3000);
			scrollToBottomWithCordinate("100");
			String compareNum=verifyCompareList();
			Thread.sleep(3000);
			String itemnumber=Integer.toString(i+1);
			if(compareNum.equals(itemnumber)) {
				reporter.SuccessReport("Verify compared products", "Compare list products are : ", compareNum);
			}else {
				reporter.failureReport("Verify compared products", "Compare list products are not correct ", compareNum);
			}
		}
	}
	
	
    public void clickOnAddToMyCompareListLink(int n) throws Throwable {
	   
    	for (i = 0; i <n; i++) {
    		Thread.sleep(3000);
    		clickUntil(productsDisplayInfoObj.getAddToMyCompareListLink(Integer.toString(i)), productsDisplayInfoObj.URCOMPARELIST(Integer.toString(i)),"compare Link");
    		if(isVisibleOnly(productsDisplayInfoObj.URCOMPARELIST(Integer.toString(i)), "")) {
    			reporter.SuccessReport("verify item added to compare list", "Item added to compare list :", "Added compare Item " +(i+1));
    		}else {
    			reporter.failureReport("verify item added to compare list", "Item not added to compare list :", "",driver);
    		}
    		Thread.sleep(3000);
    		scrollToBottomWithCordinate("100");
    		
    	}
}
	
	/**
	 * Method is to click On Add To My Compare List Link and verify the Compare
	 * your list items number
	 * 
	 * @param n
	 * @throws Throwable
	 */
	public void clickOnAddToMyCompareListLinkIndividually(int n) throws Throwable {
			Thread.sleep(3000);
			clickUntil(productsDisplayInfoObj.getAddToMyCompareListLink(Integer.toString(i)), productsDisplayInfoObj.URCOMPARELIST(Integer.toString(i)),"compare Link");
			Thread.sleep(3000);
			scrollToBottomWithCordinate("100");
	}
	
	/**
	 * This method is to click on the compared products items link
	 * @throws Throwable
	 */
	public void clickOnComparedItemsLink() throws Throwable{
		scrollUp();
		if(isElementPresent(productsDisplayInfoObj.COMPARE_YOUR_LIST_LINK, "compare List Number")) {
			click(productsDisplayInfoObj.COMPARE_YOUR_LIST_LINK,"compare List Number link");
		}else {
			reporter.failureReport("Verify compare items link", "Compare items link does not exists", "NA", driver);
		}
		
		Thread.sleep(2000);
	}

	/**
	 * This method is to remove the product from the compare product details
	 * page.
	 * 
	 * @throws Throwable
	 */
	public void clickOnCloseIconInCompareProductsPage() throws Throwable {
		waitForVisibilityOfElement(productsDisplayInfoObj.CLOSE_ICON,  "CLOSE_ICON");
		Thread.sleep(3000);
		click(productsDisplayInfoObj.CLOSE_ICON, "Deleted one product");
	}


	/**
	 * This method is to perform the general search operation in the Home page
	 * 
	 * @param productName
	 * @throws Throwable
	 */
	public void searchInHomePage(String productName) throws Throwable {
		if (isVisibleOnly(CommonObj.ACCEPT_COOKIES, "Accept cookie")) {
			click(CommonObj.ACCEPT_COOKIES, "Accept cookie");
		}
		Thread.sleep(3000);
		waitForVisibilityOfElement(SEARCH,"Search Field");
		typeForSearchingProduct(SEARCH,productName,"Search Text: "+productName);
		WebElement ele=driver.findElement(SEARCH);
		ele.sendKeys(Keys.ENTER);
	}

	/**
	 * Method is to verify the Results For Search Term in products display page.
	 * 
	 * @param productName
	 * @throws Throwable
	 */
	public void verifyTheResultsForSearchTerm(String productName) throws Throwable {
		waitForVisibilityOfElement(RESULT_FOR_SEARCH, "RESULT FOR SEARCH");
		String result = getText(RESULT_FOR_SEARCH, "Searched result").replace("\"", "");
		if (result.equals(productName)) {
			reporter.SuccessReport("Verify the results for search term in products display page ",
					"Verification is sucessfull.search term / Bread crumb is: ","Bread crumb : "+result);
		} else {
			reporter.failureReport("Verify the results for search term in products display page",
					"Expected search result is  " + productName + "Actual is: " , result);
		}

	}

	/**
	 * This method is to select the particular filter category and verify
	 * whether the filter is applied.
	 * 
	 * @param filter
	 * @throws Throwable
	 */
	public void filterSelectionInProductsSearchPage(String filter) throws Throwable {

		String result = null;
		//JSClick(productsDisplayInfoObj.getFilterSelection(filter), "filter Name : "+filter);
		clickUntil(productsDisplayInfoObj.getFilterSelection(filter),productsDisplayInfoObj.FILTER_ITEM, "filter Name");
		/*scrollBottom();
		scrollToBottom();
		click(productsDisplayInfoObj.getFilterSelection(filter), "filter Name : "+filter);*/
		Thread.sleep(3000);
		waitForVisibilityOfElement(productsDisplayInfoObj.FILTER_ITEM, "filter item");
		
		boolean flag = true;
		if (flag) {

			// to catch all web elements into list
			List<WebElement> myList = driver.findElements(productsDisplayInfoObj.FILTER_ITEM);
			List<String> all_elements_text = new ArrayList<>();
			for (int i = 0; i < myList.size(); i++) {
				Thread.sleep(3000);
				// loading text of each element in to array all_elements_text
				all_elements_text.add(myList.get(i).getText());
				result = myList.get(i).getText();
				Thread.sleep(3000);
				if (result.equals(filter)) {
					Thread.sleep(3000);
					reporter.SuccessReport("Verify the results for search term in products display page ",
							"Verification is sucessfull. Expected filter is:" , result);
				} else if (filter.equals("Show only in-stock items") && result.equals("In Stock Only")) {
					reporter.SuccessReport("Verify the results for search term in products display page ",
							"Verification is sucessfull. Expected filter is:" , "filter : "+result);
				}
			}
		} else {
			reporter.failureReport("Verify the results for search term in products display page",
					"Expected search result is  " + filter + "Actual is: " , result);
		}
	}

	/**
	 * Method is to select the filter by clicking on more drop down
	 * @param filter
	 * @param filterHeading
	 * @throws Throwable
	 */
	public void selectManufacturerFiter(String filter,String filterHeading) throws Throwable {
		if(isVisibleOnly(productsDisplayInfoObj.getFilterSelection(filter),"filter")) {
			JSClick(productsDisplayInfoObj.getFilterSelection(filter), "filter Name : "+filter);
		}else {
			//clickUntil(productsDisplayInfoObj.filterSeeMoreDD(filterHeading), productsDisplayInfoObj.getFilterSelection(filter),"show more", "");
			JSClick(productsDisplayInfoObj.filterSeeMoreDD(filterHeading), "show more");
			if(isVisibleOnly(productsDisplayInfoObj.getFilterSelection(filter),"filter")) {
				JSClick(productsDisplayInfoObj.getFilterSelection(filter), "filter Name : "+filter);
		}else {
			reporter.failureReport("Verify filter ", filter+" is not visible", "", driver);
		   }
		}
	}

	/**
	 * Method is to navigate to the Custom catalog in the CMT tool and Include
	 * manufacturers and update it.
	 * 
	 * @param type
	 * @throws Throwable
	 */
	public void IncludeManufacturersInCatlogAndUpdate(String type) throws Throwable {
		waitForVisibilityOfElement(CMTObj.MANUFACTURERS_TAB_CATALOG, "Include manufacturers tab");
		click(CMTObj.MANUFACTURERS_TAB_CATALOG, "include manufacturers tab");
		if(isElementPresent(CMTObj.getCurrentIncludedManufacturers(type), "selected manufacturer ")){
			//do nothing
		}else{ 
			selectByVisibleText(CMTObj.INCLUDE_MANUFACTURERS, type, "include manufacturers options");
			click(CMTObj.RIGHT_ARROW_INCLUDE_MFRS, "include Right arrow");
			click(CMTObj.UPDATE_BTN, "Update button");
		}
	}

	/**
	 * This method is to click on the Primary header and select the product
	 * category from the drop-down and verifies the successful navigation.
	 * 
	 * @param header
	 * @param headerlist
	 * @throws Throwable
	 */
	public void clickOnSecondaryDDAndSelectListitem(String header, String headerlist) throws Throwable {
		click(getSecondaryHeaderMenu(header), header);
		click(getSecondaryMenuListItems(headerlist), headerlist);
	}

	/**
	 * Method is to click on the primary header and select Shop All buttons.
	 * 
	 * @param header
	 * @param shopAll
	 * @throws Throwable
	 */
	public void clickonShopAllButtonsInHeaderList(String header, String shopAll) throws Throwable {
		waitForVisibilityOfElement(getSecondaryHeaderMenu(header), "Header menu");
		Thread.sleep(4000);
		click(getSecondaryHeaderMenu(header), "Secondary header link :"+header);
		click(getShopAllBtnsFromMenuList(shopAll), "shop All button : "+shopAll);
	}

	/**
	 * Method is to to get the Menu items in the SHOP ALL PRODUCTS/ BRANDS page
	 * and verify them.
	 * 
	 * @param ShopAllMenus
	 * @throws Throwable
	 */
	public void verifyMenusInShopAllProductsPage(String ShopAllMenus) throws Throwable {
		String result = null;
		// adding the menu items to list
		List<WebElement> myList = driver.findElements(SHOP_ALL_PRODUCTS_MENUS_LIST);
		List<String> all_elements_text = new ArrayList<>();
		for (int i = 0; i < myList.size(); i++) {
			all_elements_text.add(myList.get(i).getText());
			result = myList.get(i).getText();
			String strArray[] = ShopAllMenus.split(","); // Getting test data as  list and  verifying
			if (result.equals(strArray[i])) {

				reporter.SuccessReport("Verify the results for menu items ",
						"Menus verification is sucessfull. Expected menu item is:" , result);
			} else {
				reporter.failureReport("Verify the results for menu items",
						"Expected menu item is  " + strArray[i] + "Actual menu item is: " , result);
			}
		}
	}

	/**
	 * This method is to click on menu items in the Shop all products and select
	 * by category.
	 * 
	 * @param menuItem
	 * @throws Throwable
	 */
	public void clickMenuItemCategoryInShopAllProductsPage(String menuItem, String categoty) throws Throwable {
		click(getShopAllLinksInHeaders(menuItem), "Shop all button : "+menuItem);
		click(getShopAllProductsByCategory(categoty), "By categoty : "+categoty);
	}

	/**
	 * Method is to select a particular brand in the Shop all brands page
	 * 
	 * @param brand
	 * @param url
	 * @throws Throwable
	 */
	public void selectTopBrandsInShopAllBrandsPage(String brand, String url) throws Throwable {
		Boolean flag=false;
		if(isElementPresent(getTopBrands(brand), "Top brands")){
			Thread.sleep(2000);
			click(getTopBrands(brand), "Top Brands: "+brand);
			Thread.sleep(2000);
			flag=verify_url(driver, url);
			if(flag=true) {
				reporter.SuccessReport("Verify results are displayed", brand+" page is displayed", brand);
			}else {
				reporter.failureReport("Verify results are displayed", brand+" page is not displayed", brand,driver);
			}
		}else{
			reporter.failureReport("Verify top brands are displayed", brand+" is not present to select ", brand);
		}
		
	}

	/**
	 * This method is to select brand by alphabet
	 * 
	 * @param url
	 * @throws Throwable
	 */
	public void selectBrandByAlphabetOrderSection(String url,String brand) throws Throwable {
		click(getShopByBrandByAlphabet(brand), "brand By Alphabets");
		Thread.sleep(2000);
		verify_url(driver, url);
	}

	/**
	 * This method is to remove filters in the product search page.
	 * 
	 * @param filter
	 * @throws Throwable
	 */
	public void removeTheFilter(String filter) throws Throwable {
		click(productsDisplayInfoObj.getRemoveFilterName(filter), "Remove filter: "+filter);
	}

	public void removeTheFilterForInStockOnly(String filter) throws Throwable {
		if(isVisibleOnly(productsDisplayInfoObj.getRemoveFilterName(filter), "Filter")) {
			click(productsDisplayInfoObj.getRemoveFilterName(filter), "Remove filter: "+filter);	
		}else {
			// Do nothing
		}
	}

	/**
	 * Method is to search for an user in the CMT users screen
	 * 
	 * @param lnameEmailUname
	 * @throws Throwable
	 */
	public void searchUsers(String lnameEmailUname) throws Throwable {
		waitForVisibilityOfElement(CMTObj.LNAME_EMAIL_USERNAME, "LNAME EMAIL USERNAME: "+lnameEmailUname);
		type(CMTObj.LNAME_EMAIL_USERNAME, lnameEmailUname, "LNAME EMAIL USERNAME: " +lnameEmailUname);
		click(CMTObj.USERNAME_SEARCH_BUTTON, "USERNAME SEARCH BUTTON");
	}

	/**
	 * This method is to verify the filtered user and click on the check box.
	 * 
	 * @param contactName
	 * @throws Throwable
	 */
	public void verifyUserandClick(String contactName) throws Throwable {
		String actualuser = getText(CMTObj.getUser(contactName), "USER CONTACT NAME");
		if (actualuser.contains(contactName)) {
			reporter.SuccessReport("Verifying User Contact Name :", "User Contact Name present is : " , contactName);
			click(CMTObj.getUser(contactName), "USER CONTACT NAME");
		} else {
			reporter.failureReport("Verifying User Contact Name :",
					"User Contact Name not present is : " ,contactName);
		}
	}
	
	/**
	 * This method is to verify whether the header menu is present or not after the permissions setup.
	 * @param header
	 * @throws Throwable
	 */
	
	public void verifyMenusDisabledOnHomePage(String header, String headerlist,String ShopBrand) throws Throwable{
		waitForVisibilityOfElement(getSecondaryHeaderMenu(headerlist),"Header name");
		String headerName[]=header.split(",");
		    for(i=0;i<headerName.length;i++){
			if(isElementNotPresent(getSecondaryHeaderMenu(headerName[i]),"Home page header")){
				reporter.SuccessReport("Verifying the header menu is present "," Menu is not present.Menu is :", "Menu: "+headerName[i]);
			} else {
				reporter.failureReport("Verifying the header menu is present "," Menu is present in the header. Permissions are not disabled properly.Menu item is: ",headerName[i]);
	}
  }
		    click(getSecondaryHeaderMenu(headerlist), "Header Link: Shop -->  : "+headerlist);
			isElementNotPresent(getShopAllBtnsFromMenuList(ShopBrand), "Shop By Brand button");
}
	/**
	 * This method is to verify whether the header menu is present when the permissions are enabled.
	 * @param header
	 * @throws Throwable
	 */
		
	public void verifyMenuEnabledOnHomeScreen(String header,String headerlist,String ShopBrand) throws Throwable{
		waitForVisibilityOfElement(getSecondaryHeaderMenu(headerlist),"Header name");
		String headerName[]=header.split(",");
	    for(i=0;i<headerName.length;i++){
		if(isElementPresent(getSecondaryHeaderMenu(headerName[i]),"Home page header")){
			reporter.SuccessReport("Verifying the header menu is present "," Menu is present in the header.Menu is :",headerName[i]);
		} else {
			reporter.failureReport("Verifying the header menu is present "," Menu is not present in the header. Permissions are enabled properly.Menu item is: ",headerName[i]);
			}
		}
		click(getSecondaryHeaderMenu(headerlist), "Header Link: Shop --> :"+headerlist);
		isElementPresent(getShopAllBtnsFromMenuList(ShopBrand), "Shop By Brand button");
	}
	
	/**
	 * This method is to search in the header search box and select from the  auto suggestions if displayed.
	 * @param searchText
	 * @throws Throwable
	 */
	public void searchInHeaderSelectFromSuggestions(String searchText) throws Throwable{
		WebElement element = driver.findElement(SEARCH);
		typeForSearchingProduct(SEARCH,searchText , "Search text : "+searchText);
		Thread.sleep(7000);
		String result =null;
		if(isElementPresent(SEARCH_SUGGESSIONS, "Search suggessions")){
			List<WebElement> myList = driver.findElements(SEARCH_SUGGESSIONS);
			List<String> all_elements_text = new ArrayList<>();
			for (int i = 0; i < myList.size(); i++) {
				all_elements_text.add(myList.get(i).getText());
				result = myList.get(i).getText();
				reporter.SuccessReport("search suggestions are displayed ","suggestions displayed : ",result);
			}
		/*click(SEARCH_SUGGESSIONS, "Search suggessions");
		reporter.SuccessReport("Verifying whether the suggessions are displayed ","Suggessions are displayed for : ",searchText);*/
			element.sendKeys(Keys.ENTER);
		}else {
			reporter.failureReport("Verifying whether the suggestions are displayed  "," Enter a valid text.You entered : ",searchText,driver);
		}
		/*else 
			if(isElementNotPresent(SEARCH_SUGGESSIONS, "Search suggessions")){
				element.sendKeys(Keys.ENTER);
			reporter.SuccessReport("Verifying whether the suggessions are displayed ","Suggessions are  displayed",searchText);
		}
			else {
			reporter.failureReport("Verifying whether the suggessions are displayed  "," Enter a valid text.You entered : ",searchText);
		}*/
	}
	
	/**
	 * verify Search Suggestions are Not Displayed
	 * @throws Throwable
	 */
	public void verifySearchSuggestionsareNotDisplayed(String searchText) throws Throwable {
		typeForSearchingProduct(SEARCH,searchText , "Search text");
		if(isElementNotPresent(SEARCH_SUGGESSIONS, "Search suggessions")){
			reporter.SuccessReport("Verifying whether the suggestions are displayed ","suggestions are  not displayed","");
		}else {
			reporter.failureReport("Verifying whether the suggestions are displayed  "," search  suggestions are displayed ","",driver);
		}
	}
	/**
	 * This method is to  navigate to Account tools screen and Enable/ Disable Show search suggestions setting.
	 * @param tabName
	 * @throws Throwable
	 */
	public void selectAccountTools(String tabName,String tabName1) throws Throwable{
		click(TOOLS_DD_HEADER, "Tools drop down on header");
		click(FAVORITES, "FAVORITES","Link: Account Favorites");
	    if(isElementPresent(USER_PREFERENCE, "User preference tab", true)){
	    	click(getFavoritesTabs(tabName),"Account favorites : "+tabName,tabName);
	    	if(isElementPresent(ACCOUNT_FAVORITES,"ACCOUNT FAVORITES")){
	    		LOG.info("Element is present and active");
	    	}else{
	    		reporter.failureReport("Verify the selected tab in active state","Selected tab is not in active state",tabName,driver);
	    	}
	    }
	    click(getFavoritesTabs(tabName1), "User preference tab");
	}
	
	public void enableSearchSuggestions() throws Throwable {
		if(isElementPresent(SHOW_KEYWORD_SUGGESSIONS_CHECKED, "SHOW KEYWORD SUGGESTIONS CHECKED")){
		// Do nothing 
		}else {
			scrollBottom();
			 click(SHOW_KEYWORD_SUGGESSIONS_UNCHECKED, "Show key word suggestions check box Enabled");
		}
	}
	
	public void disableSearchSuggestions() throws Throwable {
		if(isElementPresent(SHOW_KEYWORD_SUGGESSIONS_UNCHECKED, "SHOW KEYWORD SUGGESTIONS CHECKED")){
		// Do nothing 
		}else {
			scrollBottom();
			 click(SHOW_KEYWORD_SUGGESSIONS_CHECKED, "Show key word suggestions check box Disabled");
		}
	}
	
	public void updateSuggessions() throws Throwable {
		click(UPDATE_PREF_BTN, "Update button in user preference tab");
	}
	
	public void verifyFilterSelectedBySuggestions() throws Throwable{
		String expectedText=getText(SEARCH_SUGGESSIONS, "Search suggestions");
		String actualTest=getText(productsDisplayInfoObj.FILTER_ITEM, "Filter item");
		if(actualTest.contains(expectedText)){
			reporter.SuccessReport("Verify the filter displayed","searched product filter is displayed",actualTest);
		}else {
			reporter.failureReport("Verify the filter displayed","searched product filter is not displayed displayed",expectedText,driver);
		}
	}
	
	/**
	 * 
	 * @param tabName
	 * @param tabName1
	 * @param searchText
	 * @throws Throwable
	 */
	public void enableOrDisableSearchSuggestions(String tabName,String tabName1, String searchText) throws Throwable{
		
		selectAccountTools(tabName,tabName1);
		searchInHeaderSelectFromSuggestions(searchText);
	}
	
	/**
	 * This method is to verify the Contract All label on the home screen.
	 * @throws Throwable
	 */
	public void verifyContractAllDisplayed() throws Throwable{
		if(isVisible(CONTRACT_DD, "Contract drop -down in Home page")){
			reporter.SuccessReport("Verify the contract drop down displayed","Default Contract: Contract – All","Default Contract: Contract – All");
		}else{
			reporter.failureReport("Verify the contract drop down displayed","Default Contract: Contract – All is not displayed in the home page","");
		}
	}
	
	public void verifyContract(String contractName) throws Throwable {
		String contract=getText(SELETED_CONTRACT, "Selected contract");
		if(contract.contains(contractName)) {
			reporter.SuccessReport("Verify selected contract", "Selected contract is:", contractName, driver);
		}
		else {
			reporter.failureReport("Verify selected contract", "Selected contract is not as expected", contract, driver);
		}
	}
	
	/**
	 * This method is to click on More prices Available link and verify whether all the contracts are available ,
	 *  Open market price availability and your price availability.
	 * @throws Throwable
	 */
	public void clickMorePricesAndViewContracts(String itemNum) throws Throwable{
		String result = null;
		boolean flag = true;
		
		
		if (flag) {
			List<WebElement> myList = driver.findElements(productsDisplayInfoObj.ALL_CONTRACT_PRICES);
			List<String> all_elements_text = new ArrayList<>();
			for (int i = 0; i < myList.size(); i++) {
				all_elements_text.add(myList.get(i).getText());
				result = myList.get(i).getText();
				
				if(myList.size()<1 ){
					reporter.failureReport("Verify the contract prices displayed ","contract price are not displayed ","");
				}else{
					reporter.SuccessReport("Verify the contract prices displayed ","contract price is displayed as : " , result);
				}
				
				if (isElementPresent(productsDisplayInfoObj.OPEN_MARKET, "Open Market")) {
					String openMarket = getText(productsDisplayInfoObj.OPEN_MARKET, "Open Market"); // To get the open market price to verify
					if(result.contains(openMarket)){
					reporter.SuccessReport("Verify the Open Market price","Open Market price is displayed as : " , openMarket);
				   }
				}
				else if (isElementPresent(productsDisplayInfoObj.YOUR_PRICE, "Your price")) {   // verifying your price is present 
				String yourPrice=getText(productsDisplayInfoObj.YOUR_PRICE, "Your price");
					reporter.SuccessReport("Verify the Your price",
							"Your price is displayed as : " , yourPrice);
			  }
			}
			click(productsDisplayInfoObj.CLOSE_CONTRACTS_POPUP, "close popup");
		} else {
			reporter.failureReport("Verify the Open Market price", "Open Market price is not displayed","",driver);
		}
	}
	

	public void clickOnMorePrices() throws Throwable {
		Thread.sleep(4000);
		List<WebElement> myList1 = driver.findElements(productsDisplayInfoObj.LIST_OF_ITEMS_SEARCH_RESULTS);
		for (int i = 0; i < myList1.size(); i++) {
			if(isVisibleOnly(productsDisplayInfoObj.more_Prices(Integer.toString(i)), "More prices link")) {
				click(productsDisplayInfoObj.more_Prices(Integer.toString(i)), "More prices available link");
			break;
			}else {
				// do nothing
			}
		}
	}
	/**
	 * Method is to get all contracts in the contract prices popup
	 * @throws Throwable
	 */
	
	public void allContractPricesPopup() throws Throwable {
		String result=null;
		if(isVisibleOnly(productsDisplayInfoObj.ALL_CONTRACT_PRICES, "contract prices")) {
			List<WebElement> myList = driver.findElements(productsDisplayInfoObj.ALL_CONTRACT_PRICES);
			List<String> all_elements_text = new ArrayList<>();
			reporter.SuccessReport("Verify that there are multiple Contract Prices", "	There are multiple Contract Prices", "Number of contract prices : "+myList.size());
			for (int i = 0; i < myList.size(); i++) {
				all_elements_text.add(myList.get(i).getText());
				result = myList.get(i).getText();
					reporter.SuccessReport("Available Contracts ","contracts Exists ","Contract: "+result);
			}	
		}else {
			reporter.failureReport("Available contracts", "contracts does not exists", "", driver);
		}
	}
	
	public boolean verifyDefaultUSContractInAllContractPricesPopup(String status) throws Throwable {
		boolean Status=false;
		switch (status) {
		case "checked":
		if(isCheckBoxSelected(productsDisplayInfoObj.US_CONTRACTS_RADIO_BTN)) {
			Status= true;
			reporter.SuccessReport("Verify Defaulted Contract", "Defaulted Contract is USC", "Defaulted Contract: U.S. COMMUNITIES IT PRODUCTS & SERVICES");
		}else {
			Status=false;
			reporter.failureReport("Verify Defaulted Contract", "Defaulted Contract is not USC", "",driver);
		}
		break;
	case "unchecked":
		if(!isCheckBoxSelected(productsDisplayInfoObj.US_CONTRACTS_RADIO_BTN)) {
			Status=true;
			reporter.SuccessReport("Verify Defaulted Contract", "Defaulted Contract is not  U.S. COMMUNITIES", "");
		}else {
			Status=false;
			reporter.failureReport("Verify Defaulted Contract", "Defaulted Contract is USC", "",driver);
		}
	default:
		break;
	}
	return Status;
	  }
	
	/**
	 * 
	 * @param contract
	 * @throws Throwable
	 */
	public void selectContractOnAllContractPricesPopup(String contract) throws Throwable {
		if(isVisibleOnly(productsDisplayInfoObj.defaultContractRadioButton(contract), "contract")) {
			click(productsDisplayInfoObj.defaultContractRadioButton(contract), "Web Radio Filter Option: "+contract, contract);
		}else {
			reporter.failureReport("verify contract present", contract+" does not exists", "", driver);
		}
	}
	public void increaseQuantity(String quantity) throws Throwable {
		if(isVisibleOnly(productsDisplayInfoObj.QUANTITY_CONTRACT_ALL, "quantity")){
			clearData(productsDisplayInfoObj.QUANTITY_CONTRACT_ALL);
			type(productsDisplayInfoObj.QUANTITY_CONTRACT_ALL,quantity,"quantity");
		}else {
			reporter.failureReport("verify quantity exists", "Quantity field does not exists", quantity, driver);
		}
	}
	 
	
	/**
	 * This method is to verify the default contract price displayed.
	 * @throws Throwable
	 */
	public void VerifyDefaultUSDContractPrice() throws Throwable{
		Thread.sleep(3000);
		String actaulPrice=getText(productsDisplayInfoObj.DEFAULT_PRICE_OF_PRODUCT, "Default price").replace("USD", "");
		Thread.sleep(3000);
		click(productsDisplayInfoObj.MORE_PRICES, "More prices available link");
		String USDprice=getText(productsDisplayInfoObj.USD_PRICE, "USd price");
		Thread.sleep(3000);
		if (USDprice.contains(actaulPrice)){
			reporter.SuccessReport("Verify the product default price displayed ","product default price displayed correctly as : " ,actaulPrice);
		}else{
			reporter.failureReport("Verify the product default price displayed ","product default price not displayed correctly ","",driver);
		}
		click(productsDisplayInfoObj.CLOSE_CONTRACTS_POPUP, "close popup");
	}
	
	/**
	 * This method is to select a new contract in the home page contracts DD.
	 * @param contractName
	 * @throws Throwable
	 */
	public void selectNewcontract(String contractName) throws Throwable{
		Thread.sleep(3000);
		clickUntil(CONTRACT_DD, getContractsFromDD(contractName), "contract drop down");
		Thread.sleep(3000);
		click(getContractsFromDD(contractName),"Contract name : "+contractName);
		Thread.sleep(3000);
		if(isElementPresent(CONTRACT_TITLE, "contract title")){
			reporter.SuccessReport("Verify the contracts page displayed ","contracts title page displayed successfully as : ",getText(CONTRACT_TITLE, "Contract title") );
		}else{
			reporter.failureReport("Verify the contracts page displayed ","contracts title page is not displayed successfully",contractName);
		}
	}
	
	/**
	 * 
	 * @throws Throwable
	 */
	public void verifyUSCcontractNotPresent() throws Throwable{
		Thread.sleep(3000);
		String contractLabel=getText(productsDisplayInfoObj.DEFAULT_CONTRACT_LABEL_PROD_LIST, "DEFAULT CONTRACT LABEL PRODUCT LIST");
		if(isElementNotPresent(productsDisplayInfoObj.DEFAULT_USC_LABEL_PROD_LIST, "USC label")&&contractLabel.contains("...")){
			
			reporter.SuccessReport("Verify the default contracts displayed in product search page first product ","USC contract price is not displayed by default for the product.Displayed contract is: ",contractLabel);
		}else{
			reporter.failureReport("Verify the default contracts displayed in product search page first product ","USC is displayed by default",contractLabel,driver);
		}
	}
	
	/**
	 * This method is to select account tools from the side menu and click on product from the product group and verify description. 
	 * @param toolsMenuName
	 * @param dropDown
	 * @param productGroup
	 * @param productName
	 * @throws Throwable
	 */
	public void selectAccountToolsFromSideMenuAndClickOnProductGrp(String toolsMenuName, String dropDown ,String productGroup,String productName) throws Throwable{


		if (isElementNotPresent(InvoiceHistoryLib.COSE_ACCOUNT_TOOLS, "Account tools menu icon")) {
			click(ACCOUNT_TOOLS, "Account tools menu icon");
		}
		else {
			click(Account_Tools1,"");
			click(ACCOUNT_TOOLS, "Account tools menu icon");
		}
		   click(getAccountToolsMenu(toolsMenuName), "Account tools menu",toolsMenuName);
		   click(getAccountToolsDD(toolsMenuName, dropDown), "Select account tools",dropDown);
		   click(getCompanyStandardsProductGroup(productGroup, productName), "select product from product group","product group : "+productGroup+"Product name: "+productName);
		   Thread.sleep(1000);
		   String prodDesc=getText(PROD_DESC_ACCOUNT_TOOLS, "product description account tools");
		   clickUntil(PROD_DESC_ACCOUNT_TOOLS, MINI_WINDOW, "product description account tools");
		   Thread.sleep(2000);
		   
		   Set<String> handle=driver.getWindowHandles();
		   if (handle.size()>2) {
			   switchToChildWindow();
			   Thread.sleep(2000);
			   String actualDesc=getText(PROD_DESC_MINI_PPP_WINDOW, "product description in PPP window");
			   if(actualDesc.contains(prodDesc)){
				   reporter.SuccessReport("verify the PPP window displayed for the selected product","PPP mini window displayed and the selected product displayed : \n ",actualDesc);
			   }
	    	}else{
			reporter.failureReport("verify the PPP window displayed for the selected product", "PPP window is not opened","");
		}
		   driver.close();
		   ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
		   driver.switchTo().window(tabs.get(1));
	}
	
	/**
	 * Method is to verify the description in mini popup window
	 * @throws Throwable
	 */
	
	public void selectDescriptionAndVerifyMiniPopupWindow() throws Throwable {
		String prodDesc=getText(PROD_DESC_ACCOUNT_TOOLS, "product description account tools");
		   clickUntil(PROD_DESC_ACCOUNT_TOOLS, MINI_WINDOW, "product description account tools","Product Description Link: "+prodDesc);
		   Thread.sleep(2000);
		   
		   Set<String> handle=driver.getWindowHandles();
		   if (handle.size()>2) {
			   switchToChildWindow();
			   Thread.sleep(2000);
			   String actualDesc=getText(PROD_DESC_MINI_PPP_WINDOW, "product description in PPP window");
			   if(actualDesc.contains(prodDesc)){
				   reporter.SuccessReport("verify the PPP window displayed for the selected product","PPP mini window displayed and the selected product in the product group table displayed correctly: \n ","Mini-PPP:  "+actualDesc);
			   }
	    	}else{
			reporter.failureReport("verify the PPP window displayed for the selected product", "PPP window is not opened","",driver);
		}
		   driver.close();
		   ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
		   driver.switchTo().window(tabs.get(1));
	}
	
	/**
	 * This method is to select under a product group and verify the selected product group is populated in the Right side of the page.
	 * @param productGroup
	 * @param productName
	 * @throws Throwable
	 */
	public void selectProductGroupAndVerify(String productGroup,String productName) throws Throwable{
		
		click(getCompanyStandardsProductGroup(productGroup, productName), "select product from product group","Link : "+productName);
		
		if(isElementPresent(getProductGrpNavigation(productGroup, productName), "NAVIGATED PRODUCT GROUP")){
			 reporter.SuccessReport("verify the selected product is displayed under the product group","Selected product is displayed correctly under the product group.",productName);
			  if(isElementPresent(ADD_ITEMS_RADIO_BUTTON, "add items to cart radio button")){
			     List<WebElement> myradioList=driver.findElements(ADD_ITEMS_RADIO_BUTTON);
			     List<WebElement> myList=driver.findElements(ITEM_DESCRIPTION);
			         for (int i = 0; i < myradioList.size(); i++) {
				         if(myradioList.get(i).isSelected()){
					          reporter.SuccessReport("verify the radio button checked or not","product is checked",myList.get(i).getText());
				   }
				}
			  }else
					if(isElementPresent(ADD_ITEMS_CHECKBOX, "Add items check box")){
						scrollToBottomWithCordinate("200");
					List<WebElement> myList1=driver.findElements(ADD_ITEMS_CHECKBOX);
					List<WebElement> myList=driver.findElements(ITEM_DESCRIPTION);
					for (int j = 0; j < myList1.size(); j++) {
						myList1.get(j).click();
						reporter.SuccessReport("verify the radio button checked or not","product is checked",myList.get(j).getText());
						Thread.sleep(6000);
					}
					
					click(ADD_TO_ORDER, "Add to oreder button");
					isElementPresent(ITEMS_ADDED_TO_CART_POPUP, "items added to cart",true);
					click(PROD_GRP_CLOSE_ICON, "Close icon");
					
				}
		}else{
			reporter.failureReport("verify the selected product group is displayed","Selected product is not displayed correctly under the product group.","",driver);
		}
		
	}		
	
public void selectProductGroupAndVerify1(String productGroup,String productName) throws Throwable{
		
		click(getCompanyStandardsProductGroup(productGroup, productName), "select product from product group","Link : "+productName);
		
		if(isElementPresent(getProductGrpNavigation(productGroup, productName), "NAVIGATED PRODUCT GROUP")){
			 reporter.SuccessReport("verify the selected product is displayed under the product group","Selected product is displayed correctly under the product group.",productName);
			  if(isElementPresent(ADD_ITEMS_RADIO_BUTTON, "add items to cart radio button")){
			     List<WebElement> myradioList=driver.findElements(ADD_ITEMS_RADIO_BUTTON);
			     List<WebElement> myList=driver.findElements(ITEM_DESCRIPTION);
			         for (int i = 0; i < myradioList.size(); i++) {
				         if(myradioList.get(i).isSelected()){
					          reporter.SuccessReport("verify the radio button checked or not","product is checked",myList.get(i).getText());
				   }
				}
			  }else
					if(isElementPresent(ADD_ITEMS_CHECKBOX, "Add items check box")){
						scrollToBottomWithCordinate("200");
					List<WebElement> myList1=driver.findElements(ADD_ITEMS_CHECKBOX);
					List<WebElement> myList=driver.findElements(ITEM_DESCRIPTION);
					for (int j = 0; j < myList1.size(); j++) {
						myList1.get(j).click();
						reporter.SuccessReport("verify the radio button checked or not","product is checked",myList.get(j).getText());
						Thread.sleep(6000);
					}
					
					click(ADD_TO_ORDER, "Add to oreder button");
					//isElementPresent(ITEMS_ADDED_TO_CART_POPUP, "items added to cart",true);
					//click(PROD_GRP_CLOSE_ICON, "Close icon");
					if(isVisibleOnly(VIEW_CART_PRODUCT_GROUP, "View cart Link")){
						click(VIEW_CART_PRODUCT_GROUP, "View cart Link","View cart Link");
						reporter.SuccessReport("verify View cart Link on Items added to cart Popup on Company standards", "View cart Link is visible and clicked","");
					}else{
						reporter.failureReport("verify View cart Link on Items added to cart Popup on Company standards", "View cart Link is not visible","",driver);
					}
				}
		}else{
			reporter.failureReport("verify the selected product group is displayed","Selected product is not displayed correctly under the product group.","",driver);
		}
		
	}		
	
	
	/**
	 * method to click on a product group select product and verify the product group table and items added to cart.
	 * @param productGroup
	 * @param productName
	 * @param columnNames
	 * @throws Throwable
	 */
	public void selectNewHireStandardAndverifyCart(String productGroup,String productName,String columnNames) throws Throwable{
		selectProductGroupAndVerify(productGroup, productName);
		verifyTheProductGroupTable(columnNames);
		click(getCartOnProductGroupPage(productName), "Select Add to cart on Product group page");
		if(isElementPresent(ITEMS_ADDED_TO_CART_POPUP, "items added to cart")){
			reporter.SuccessReport("verify items are added to acart as bundle","Items are added to cart",productName);
		}else{
			reporter.failureReport("verify items are added to acart as bundle","Items are not added to cart","",driver);
		}
		click(PROD_GRP_CLOSE_ICON, "Close icon");
	}
	
	/**
	 * This method is to verify the column names displayed for a product group
	 * selected.
	 * 
	 * @param columnNames
	 * @throws Throwable
	 */
	public void verifyTheProductGroupTable(String columnNames) throws Throwable {
		String result = null;
		String colName[] = columnNames.split(",");
		List<WebElement> myList = driver.findElements(productGroupTable);
		List<String> all_elements_text = new ArrayList<>();
		for (int i = 0; i < colName.length; i++) {
			all_elements_text.add(myList.get(i).getText());
			result = myList.get(i).getText();
			if (result.equals(colName[i])) {
				reporter.SuccessReport("verify the column names displayed correctly",
						"Column name displayed correctly : " , result);
			} else {
				reporter.failureReport("verify the column names displayed correctly",
						"Column name not displayed correctly. Expected  is : " + colName[i] + " .Actual is: " , result,driver);
			}
		}
	}

	/**
	 * Method is to verify the company standards link on the product detail page.
	 * @throws Throwable
	 */
	public void clickAddToCompanyStandardsLink() throws Throwable {

		//click(productsDisplayInfoObj.FIRST_PROD_NAME, "first product");
		if (isElementPresent(productsDisplayInfoObj.ADD_TO_COMPANY_STANDARDS_LINK,
				"Add to company standards link")) {
			click(productsDisplayInfoObj.ADD_TO_COMPANY_STANDARDS_LINK, "Add to company standards link");
			isElementPresent(productsDisplayInfoObj.COMPANY_STANDARDS_POPUP, "Company standards popup", true);
			// need to add print all the texts in popup
	   }
	}
	
	/**
	 * Method to click cancel
	 * @throws Throwable
	 */
	public void clickCancelOnSelectConfigurationSetPopup() throws Throwable {
		click(productsDisplayInfoObj.COMPANY_STANDARDS_CANCEL_BTN, "Company standards cancel button");
	}
	/**
	 * method to click on add 
	 * @throws Throwable
	 */
	public void clickAddButtonOnSelectConfigurationSetPopup() throws Throwable {
		click(productsDisplayInfoObj.COMPANY_STANDARDS_ADD_BTN, "Company standards add button","Company standards add button");
	}
	
	public void clickConfigurationSetsCheckboxs(String fieldName) throws Throwable {
		click(productsDisplayInfoObj.ConfigurationSetsCheckboxs(fieldName), "Configuration Sets Checkboxs "+fieldName, "CheckBox: "+fieldName,"CheckBox: "+fieldName);
	}
	
	/**
	 * 
	 * @throws Throwable
	 */
	public void verifyConfigurationSetsPopup() throws Throwable {
		Thread.sleep(3000);
		if(isVisibleOnly(productsDisplayInfoObj.CONFIG_SET_LABEL, "config sets label")) {
			reporter.SuccessReport("verify configure sets label", "Configure sets label exists", getText(productsDisplayInfoObj.CONFIG_SET_LABEL, "config set"));
		    List <WebElement> elements=driver.findElements(productsDisplayInfoObj.CHECKBOX_LABELS);
		    List<String> fields =new ArrayList<String>();
		    for(i=0;i<elements.size();i++) {
		    	fields.add(elements.get(i).getText());
		    	List<String> allfields =new ArrayList<String>();
		    	allfields.addAll(fields);
		    	reporter.SuccessReport("Configuration Set(s) Popup Over the Search Results Page", "Configuration Set(s) Popup Exists",elements.get(i).getText());
		    }
		    
		}else {
			reporter.failureReport("Configuration Set(s) Popup Over the Search Results Page", "Configuration Set(s) Popup does not Exists","",driver);
		}
	}
	/**
	 * Method is to click Add to order and select  View cart link on company standards screen.
	 * @throws Throwable
	 */
	public void clickAddToOrderOnCompanyStandardsScreen() throws Throwable{
		if(isElementPresent(ADD_ITEMS_RADIO_BUTTON, "add items to cart radio button")){
		     List<WebElement> myradioList=driver.findElements(ADD_ITEMS_RADIO_BUTTON);
		         for (int i = 0; i < myradioList.size(); i++) {
			         if(myradioList.get(i).isSelected()){
				          //reporter.SuccessReport("verify the radio button checked or not","products are added","");
			   }else {
				   reporter.failureReport("verify the radio button checked or not","products are not checked","");
			   }
			} click(ADD_TO_ORDER, "Add to order button","Add to Order");
		}else if(isElementPresent(ADD_ITEMS_CHECKBOX, "Add items check box")){
			List<WebElement> myList1=driver.findElements(ADD_ITEMS_CHECKBOX);
			for (int j = 0; j < myList1.size(); j++) {
				myList1.get(j).click();
				if(myList1.get(j).isSelected()){
			          //reporter.SuccessReport("verify the check box checked or not","Check box ("+j+") is checked ","");
		   }else {
			   reporter.failureReport("verify the checkbox checked or not","check box is not checked","");
		   }
				Thread.sleep(3000);
			}
			click(ADD_TO_ORDER, "Add to oreder button","ADD TO ORDER");
		}
		
		if(isVisibleOnly(VIEW_CART_PRODUCT_GROUP, "View cart Link")){
			click(VIEW_CART_PRODUCT_GROUP, "View cart Link","View cart Link");
			reporter.SuccessReport("verify View cart Link on Items added to cart Popup on Company standards", "View cart Link is visible and clicked","");
		}else{
			reporter.failureReport("verify View cart Link on Items added to cart Popup on Company standards", "View cart Link is not visible","",driver);
		}
   }
	/**
	 * 
	 * @param toolsMenuName
	 * @param dropDown
	 * @param productGroup
	 * @param productName
	 * @throws Throwable
	 */
	public void verifyAccountToolsFromSideMenuAndClickOnProductGrp(String toolsMenuName, String dropDown ,String productGroup,String productName) throws Throwable{
		Thread.sleep(20000);
		if(isElementPresent(InvoiceHistoryObj.COSE_ACCOUNT_TOOLS, "close account tools"))
		{
		click(InvoiceHistoryObj.COSE_ACCOUNT_TOOLS, "close account tools");
		}
		   click(ACCOUNT_TOOLS, "Account tools menu icon");
		   click(getAccountToolsMenu(toolsMenuName), "Account tools menu");
		   click(getAccountToolsDD(toolsMenuName, dropDown), "Select account tools");
		   click(getCompanyStandardsProductGroup(productGroup, productName), "select product from product group");
		   Thread.sleep(1000);
				       if(isElementPresent(ADD_ITEMS_CHECKBOX, "Add items check box")){
				List<WebElement> myList1=driver.findElements(ADD_ITEMS_CHECKBOX);
				for (int j = 0; j < myList1.size(); j++) {
					myList1.get(j).isDisplayed();
					reporter.SuccessReport("verify the checkbox button checked or not","Checkbox checked and products are added","");
				}
				click(ADD_TO_ORDER, "Add to oreder button");
				isElementPresent(ITEMS_ADDED_TO_CART_POPUP, "items added to cart",true);
				click(VIEW_CART_PRODUCT_GROUP, "View cart link");
				
		   }	
	}	
	public void VerifyIPSContractPage(String contractName)throws Throwable{
		clickUntil(CONTRACT_DD, getContractsFromDD(contractName), "contract drop down");
		click(getContractsFromDD(contractName),"Selected contract name");
		String Contracttitle = getText(title_Contracttitle, "Contracttitle");
		if(Contracttitle.contains(contractName)) {
			reporter.SuccessReport("Title", "IPS Contract Page Loaded", Contracttitle);
		}
		else {
			reporter.failureReport("Title", "IPS Contract Page not Loaded","",driver);
		}
	}
	/**
	 * 
	 * @param contractName
	 * @throws Throwable
	 */
	
	public void selectContractInCartPage(String contractName) throws Throwable{
		clickUntil(CONTRACT_DD, getContractsFromDD(contractName), "clicked on contract-all");
		click(getContractsFromDD(contractName),"Selected contract name");
		//click(CartObj.CART,"CART");
		Thread.sleep(2000);
		String title = getText(CONTRACT_VERIFY,"Contract Name title in cart").replace("# 1-SAMPLE - ABBR TITLE", "");
		if(contractName.contains(title)){
			reporter.SuccessReport("Verify the selected contract displayed in cart page ","contracts title page displayed successfully as : ",contractName );
		}else{
			reporter.failureReport("Verify the selected contract displayed in cart page ","contracts title page is not displayed successfully.Expceted is: ",contractName);
		}
		String selectedContract=getText(SELECTED_CONTRACT_PAGE, "Selected contract");
		if(selectedContract.contains(contractName)) {
			reporter.SuccessReport("Verify selected contract", "Selected contract is:", selectedContract, driver);
		}
			else {
				reporter.failureReport("Verify selected contract", "Selected contract is not as expected:", selectedContract, driver);
			}
			
		
	}
	public void selectContractInCartPageforTcQTH07(String contractName) throws Throwable{
		clickUntil(CONTRACT_DD, getContractsFromDD(contractName), "contract drop down");
		click(getContractsFromDD(contractName),"Selected contract name");
		//click(CartObj.CART,"CART");
		//Thread.sleep(2000);
		String title = getText(title_Contracttitle,"Contract Name title in cart");
		if(title!="") {
			reporter.SuccessReport("Contract Link", "Contract Link Exists and Clicked", title);
		}
		else {
			reporter.SuccessReport("Contract Link", "Contract Link not Exists","");
		}
		
	}
	/**
	 * 
	 * @param toolsMenuName
	 * @param dropDown
	 * @throws Throwable  
	 */
	public void verifyAccountToolsFromSideMenuAndClick(String toolsMenuName, String dropDown) throws Throwable{
		Thread.sleep(20000);
		if(isElementPresent(InvoiceHistoryObj.COSE_ACCOUNT_TOOLS, "close account tools"))
		{
		click(InvoiceHistoryObj.COSE_ACCOUNT_TOOLS, "close account tools");
		}
		   click(ACCOUNT_TOOLS, "Account tools menu icon");
		   click(getAccountToolsMenu(toolsMenuName), "Account tools menu");
		   click(getAccountToolsDD(toolsMenuName, dropDown), "Select account tools");
		   //click(getCompanyStandardsProductGroup(productGroup, productName), "select product from product group");
	}
	
	/**
	 * 
	 * @throws Throwable
	 */
	public void verifyProductWStandardsPage() throws Throwable{	
		if(isElementPresent(PRODUCTSGRP_HDR,"Verify products standard page")){
			reporter.SuccessReport("Product Standards Page","Product Standards Page Exist","" );
		}else
			   reporter.failureReport("Product Standards Page","Product Standards Page does not Exist" ,"",driver);
		}
	
	/**
	 * 
	 * @param productName
	 * @throws Throwable
	 */
	public void verifyClientAndClickOnProductGrp(String productName) throws Throwable{		
		 click(getClientfromProductStandards(productName), "select product from product group");
		 reporter.SuccessReport("Verify the selected contract ","Selected contract is displayed and clicked on: ",productName );
		   Thread.sleep(1000);
		   if(isElementPresent(DESCRIPTION_COLUMN, "Description Column Exist")){
			     List<WebElement> myradioList=driver.findElements(DESCRIPTION_COLUMN);
			         for (int i = 0; i < myradioList.size(); i++) {
				         if(myradioList.get(i).isDisplayed()){
					          reporter.SuccessReport("Description Column in Configuration Section on Product Standards Page","Description Column Exist","");
				   }else
					   reporter.failureReport("Description Column in Configuration Section on Product Standards Page","Description Column does Not Exists" ,"",driver);
			         }
				         
		   if(isElementPresent(ADD_ITEMS_RADIO_BUTTON, "add items to cart radio button")){
			     List<WebElement> myradioList1=driver.findElements(ADD_ITEMS_RADIO_BUTTON);
			         for (int j = 0; j < myradioList1.size(); j++) {
				         if(myradioList1.get(i).isSelected()){
					          reporter.SuccessReport("verify the radio button checked or not","products are added","");
				   }
				}
			  }
		   if(isElementPresent(ADD_ITEMS_CHECKBOX, "Add items check box")){
				List<WebElement> myList1=driver.findElements(ADD_ITEMS_CHECKBOX);
				for (int j = 0; j < myList1.size(); j++) {
					myList1.get(j).isDisplayed();
					reporter.SuccessReport("verify the checkbox button checked or not","Checkbox checked and products are added","");
				}
		   }
				click(ADD_TO_ORDER, "Add to oreder button");
				isElementPresent(ITEMS_ADDED_TO_CART_POPUP, "items added to cart",true);
				click(VIEW_CART_PRODUCT_GROUP, "View cart link");
		   }
		   
		}

	/**
	 * Method is to verify the search results page
	 * 
	 * @throws Throwable
	 */
	public void verifysearchResultsPage() throws Throwable {
		waitForVisibilityOfElement(SEARCH_RESULTS_PAGE,  "Search results");
		if (isElementPresent(SEARCH_RESULTS_PAGE, "Search results")) {
			reporter.SuccessReport("Verify search results page", "Search results page displayed", "Search results ");
		} else {
			reporter.failureReport("Verify search results page", "Search results page not verified successfully", "",driver);
		}
	}
	
	/**
	 * 
	 * @param contractName
	 * @throws Throwable
	 */
	public void selectContract(String contractName) throws Throwable{
		clickUntil(CONTRACT_DD, getContractsFromDD(contractName), "contract drop down");
		click(getContractsFromDD(contractName),"Selected contract name "+contractName);
		
		Thread.sleep(2000);
	}
	
	/**
	 * 
	 */
	public void verifyAccountToolForOrderMenuItem(String toolsMenuName, String dropDown) throws Throwable{
		Thread.sleep(20000);
		if(isElementPresent(InvoiceHistoryObj.COSE_ACCOUNT_TOOLS, "close account tools"))
		{
		click(InvoiceHistoryObj.COSE_ACCOUNT_TOOLS, "close account tools");
		}
		   click(ACCOUNT_TOOLS, "Account tools menu icon");
		   click(CommonObj.getAccountToolOrderMenuItem, "Account tools menu");
		   click(getAccountToolsDD(toolsMenuName, dropDown), "Select account tools");
	}
	
/**
 * 
 * @throws Throwable
 */
	public void clickallDesktopsLink() throws Throwable{
		if (isElementPresent(CommonObj.BROWSEALLDESKTOPS_LNK, "Desktops Link")) {
		   click(CommonObj.BROWSEALLDESKTOPS_LNK, "Browse all desktops links");
		}
	}

	/**
	 * Method is to get the compare list items in th esearch results page
	 * @return
	 * @throws Throwable
	 */
	public String verifyCompareList() throws Throwable{
		String list=null;
		if (isVisibleOnly(COMPARE_LIST_ITEMS, "Compare list")) {
			list=getText(COMPARE_LIST_ITEMS, "compare list items");
			reporter.SuccessReport("Items in the compare list", "Compare Your List label exists and items in the list are : ", "Items in the List: "+list);
		}else {
			reporter.failureReport("Items in the compare list", "Compare Your List label does not exists ", "Items in the List: "+list,driver);
		}
		return list;

	}
	
	public String verifyFrenchCompareList() throws Throwable{
		return getText(FRENCH_COMPARE_LIST_ITEMS, "compare list items");
	}
	
	/**
	 * 
	 * @throws Throwable
	 */
	public void checkMessageiconforAdobeProducts() throws Throwable{
		if (isElementPresent(CommonObj.MESSAGE_ICON, "Message icon")) {			
			mouseHover(CommonObj.MESSAGE_ICON, "Message Icon");
			   Thread.sleep(3000);
				WebElement element=driver.findElement(CommonObj.MESSAGE_ICON);
				String hover_data =element.getAttribute("onmouseover");
				 Thread.sleep(3000);
				 String toolTipMsg="The price displayed will be prorated in the Cart based on the remaining agreement period.";
			if((hover_data.replace("InsightCommon.showTooltip('", "").replace("',this);", "")).equals(toolTipMsg)) {
			reporter.SuccessReport("Verify Adobe Products has  Message Option on Product Standards Page", "Adobe Product has Tooltip message",toolTipMsg);			
		}else{
			reporter.failureReport("Verify Adobe Products has  Message Option on Product Standards Page", "Adobe Product  has No Message tooltip ", "",driver);
		}
		}else {
			reporter.failureReport("Verify Adobe Products has  Message Option on Product Standards Page", "Adobe Product  has No Message Options", "",driver);
		}
	}
	
	/**
	 * 
	 * @param productName
	 * @throws Throwable
	 */
	public void verifyClientAndClickOnProductGrpName(String productName) throws Throwable{		
		 click(getClientfromProductStandards(productName), "select product from product group");
		 reporter.SuccessReport("Verify the selected contract ","Selected contract is displayed and clicked on: ",productName );
		   Thread.sleep(1000);
		   if(isElementPresent(DESCRIPTION_COLUMN, "Description Column Exist")){
			     List<WebElement> myradioList=driver.findElements(DESCRIPTION_COLUMN);
			         for (int i = 0; i < myradioList.size(); i++) {
				         if(myradioList.get(i).isDisplayed()){
					          reporter.SuccessReport("Description Column in Configuration Section on Product Standards Page","Description Column Exist","");
				   }else
					   reporter.failureReport("Description Column in Configuration Section on Product Standards Page","Description Column does Not Exists" ,"",driver);
			         }
		   }
	}
	
	/**
	 * Method is to get the search results count 
	 * @return
	 */
	public int getSearchResultsCount() {
		List<WebElement> myList = driver.findElements(productsDisplayInfoObj.LIST_OF_ITEMS_SEARCH_RESULTS);
		return myList.size();
	}
	
	/**
	 * verify BreadCrumb In SearchResults Page
	 * @param productName
	 * @throws Throwable
	 */
	public void verifyBreadCrumbInSearchResultsPage(String productName) throws Throwable {
		if(isElementPresent(productsDisplayInfoObj.getBreadCrumb(productName), "Product in Breadcrumb") || isElementPresent(productsDisplayInfoObj.getFilterBreadCrumb(productName), "Product in Breadcrumb")) {
			reporter.SuccessReport("Verify Breadcrumb", "Breadcrumb in search results page:: "+productName+"","Breadcrumb: " +productName);
		}else {
			reporter.failureReport("Verify Breadcrumb", "Breadcrumb in search results page not found ", "Breadcrumb: "+productName,driver);
		}
	}
	
	
	/**
	 * Method is to verify the compared products count
	 * @return
	 * @throws Throwable 
	 */
	public void verifyComparedProductsExists(int count) throws Throwable {
		List<WebElement> elements=driver.findElements(productsDisplayInfoObj.COMPARE_LIST_ITEMS_COUNT);
		int comparedProducts=elements.size();
		if(comparedProducts==count) {
			reporter.SuccessReport("verify Compared  products", "Similar products exists",Integer.toString(comparedProducts));
		}else {
			reporter.failureReport("verify Compared products", "Similar products does not exists",Integer.toString(comparedProducts),driver);
		}
	}
	
	/**
	 * This method is to click on the shop all types button
	 * @throws Throwable 
	 * 
	 */
	public void clickShopAllTypesButton() throws Throwable {
		click(productsDisplayInfoObj.SHOP_ALL_TYPES_BTN, "Shop all types button");
	}
	
	/**
	 * Method is to get the product count
	 * @return
	 * @throws Throwable
	 */
	public String getProductCount() throws Throwable {
		String prodCount=null;
		if(isElementVisible(productsDisplayInfoObj.PRODUCT_COUNT, 3, "Product count")) {
			 prodCount=getText(productsDisplayInfoObj.PRODUCT_COUNT, "products count");
			reporter.SuccessReport("Available Products Counter in Search Results Page", "	Available Products Counter Exists and the count is : ", prodCount);
		}else {
			reporter.failureReport("Available Products Counter in Search Results Page", "	Available Products Counter does not Exists", prodCount,driver);
		}
		return prodCount;
	}
	
	/**
	 * Method is to verify selected filters
	 * @param filter
	 * @throws Throwable
	 */
	public void verifyFilterSelected(String filter) throws Throwable {
		if(isElementVisible(productsDisplayInfoObj.getFilterSelected(filter), 3, "Filter selected")) {
			reporter.SuccessReport("Verify filter selected", "Filter selected is :  ", filter);
		}else {
			reporter.failureReport("Verify filter selected", "Filter selected is not found  ", filter, driver);
		}
	}

	/**
	 * Method is to verify the search results 
	 * 
	 * @throws Throwable
	 */
	public void verifysearchResults(String searchText) throws Throwable {
		String ProdName=null;
		waitForVisibilityOfElement(SEARCH_RESULTS_PAGE,  "Search results");
		if (isElementPresent(SEARCH_RESULTS_PAGE, "Search results")) {
			 ProdName=getText(productsDisplayInfoObj.FIRST_PROD_NAME, "First Product");
			reporter.SuccessReport("Verify search results page", "Search results displayed for "+searchText, "Search Results: "+ProdName);
		} else {
			reporter.failureReport("Verify search results page", "Search results not displayed","Search Results: "+ ProdName,driver);
		}
	}
	/**
	 * Verify radio buttons selected in company standards screen
	 * @throws Throwable
	 */
	public void verifyRadioButtonsSelected() throws Throwable{
		if(isElementPresent(ADD_ITEMS_RADIO_BUTTON, "add items to cart radio button")){
		     List<WebElement> myradioList=driver.findElements(ADD_ITEMS_RADIO_BUTTON);
		         for (int i = 0; i < myradioList.size(); i++) {
			         if(myradioList.get(i).isSelected()){
				          reporter.SuccessReport("Add Column with Radio button Selected in product group Section on Product Standards Page","Add Column with Radio button Selected Exists","");
			   }else {
				   reporter.failureReport("verify the radio button checked or not","products are not checked ","",driver);
			   }
	       }
		}
   }

	/**
	 * Method to verify filter selected
	 * @param filter
	 * @throws Throwable
	 */
	public void verifyFilterBreadCrumb(String filter) throws Throwable {
		if(isVisibleOnly(productsDisplayInfoObj.getRemoveFilterName(filter), "Filter")) {
			reporter.SuccessReport("Filter verification", "Applied "+filter+" filter is verified and displayed", filter);
	}else {
		reporter.failureReport("Filter verification", "Applied "+filter+" filter is not displayed", filter, driver);
	  }
	}

	/*
	 * Method is to verify "Explore all the brands Insight has to offer."
	 */
	public void verifyExploreAllBrandsLabel() throws Throwable {
		if(isVisibleOnly(EXPLORE_ALL_BRANDS_LABEL, "Explore all brands")) {
			reporter.SuccessReport("Verify Explore all brands section", "Explore all the brands Insight has to offer.", "");
		}else {
			reporter.failureReport("Verify Explore all brands section", "Explore all the brands Insight has to offer. does not exist", "", driver);
		}
	}

	public void verifyNavigatedBreadCrumb(String searchText) throws Throwable {
		By BreadCrumb = productsDisplayInfoObj.getBreadCrumbs(searchText);

		if (isVisibleOnly(BreadCrumb, "Bread Crumb")) {
			reporter.SuccessReport("Verify the navigation", "Sucessfully Navigated to "+searchText ,searchText);
		} else {
			reporter.failureReport("Verify the navigation", "Navigation is not Sucessfully " , searchText,driver);
		}
	}

	
	/**
	 * 
	 * @param contract
	 * @throws Throwable
	 */
	public void verifydefaultcontractonAllcontractpopup(String contract) throws Throwable {
		if(isVisibleOnly(productsDisplayInfoObj.defaultContractRadioButton(contract), "contract")) {
			if(isCheckBoxSelected(productsDisplayInfoObj.defaultContractRadioButton(contract))) {
				reporter.SuccessReport("verify default selected contract", "Dfault contract is : ", contract);
			}
		}else {
			reporter.failureReport("verify contract present", contract+" is not default contract", "", driver);
		}
	}
	/**
	 * method  is to verify Open market and your price in contract all popup
	 * @param contract
	 * @throws Throwable
	 */
	public void verifyYourPriceInAllcontractPopup(String contract) throws Throwable {
		
		if(isElementPresent(productsDisplayInfoObj.defaultContractRadioButton(contract),"contract label")) {
			reporter.SuccessReport("Verify that "+contract+" exists", contract+ " Exists", contract);
		}else{
			reporter.failureReport("Verify that "+contract+" exists", contract+ " does not exists", "",driver);
		}
	}
	
	/**
	 * Method is to verify your price not available
	 * @param contract
	 * @throws Throwable
	 */
	public void verifyYourPriceDoesNotExists(String contract) throws Throwable {
		if(!isElementPresent(productsDisplayInfoObj.defaultContractRadioButton(contract),"contract label")) {
				reporter.SuccessReport("Verify that "+contract+" does not exists", contract+ " does not exists", "");
			}else{
				reporter.failureReport("Verify that "+contract+" exists", contract+ " Exists","",driver);
			}
	}
	
	/**
	 * This method is to select brand by alphabet for CA
	 * 
	 * @param url
	 * @throws Throwable
	 */
	public void selectBrandByAlphabetOrderSectionForCA(String url,String brand) throws Throwable {
		scrollToBottomWithCordinate("3990");
		click(getShopByBrandByAlphabetForCA(brand), "brand By Alphabets");
		Thread.sleep(2000);
		//verify_url(driver, url);
	}
	
	/**
	 * Method is to to get the Menu items in the SHOP ALL PRODUCTS/ BRANDS page for CA
	 * and verify them.
	 * 
	 * @param ShopAllMenus
	 * @throws Throwable
	 */
	public void verifyMenusInShopAllProductsPageForCA(String ShopAllMenus) throws Throwable {
		String result = null;
		// adding the menu items to list
		List<WebElement> myList = driver.findElements(SHOP_ALL_PRODUCTS_MENUS_LIST_CA);
		List<String> all_elements_text = new ArrayList<>();
		for (int i = 0; i < myList.size(); i++) {
			all_elements_text.add(myList.get(i).getText());
			result = myList.get(i).getText();
			String strArray[] = ShopAllMenus.split(","); // Getting test data as  list and  verifying
			if (result.equals(strArray[i])) {

				reporter.SuccessReport("Verify the results for menu items ",
						"Menus verification is sucessfull. Expected menu item is:" , result);
			} else {
				reporter.failureReport("Verify the results for menu items",
						"Expected menu item is  " + strArray[i] + "Actual menu item is: " , result,driver);
			}
		}
	}

	// POPULAR_PRODUCTS_LABEL_CA
	public void verifyPopularProductsLabel() throws Throwable {
		if (isVisibleOnly(POPULAR_PRODUCTS_LABEL_CA, "Popular products")) {
			reporter.SuccessReport("Verify that", "Sucessfully Popular products label is displayed " ,"Popular products");
		} else {
			reporter.failureReport("Verify that", "Popular products label not displayed " , "Popular products",driver);
		}
	}
	
}
 



