package com.insight.Lib;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;

import com.insight.ObjRepo.CMTObj;
import com.insight.ObjRepo.CartObj;
import com.insight.ObjRepo.CommonObj;
import com.insight.ObjRepo.InvoiceHistoryObj;
import com.insight.ObjRepo.MarriottIntlCorpObj;
import com.insight.ObjRepo.OrderObj;
import com.insight.ObjRepo.ProductDetailObj;
import com.insight.ObjRepo.ShipBillPayObj;

public class ShipBillPayLib extends ShipBillPayObj {

	public void ApproveRequesition(String RefNum) throws Throwable {
		click(Requesitor(RefNum), "Click on requesitor");
		waitForVisibilityOfElement(ShipBillPayObj.APPROVAL_MANAGEMENT_LABEL, "Approval Management");
		click(APPROVEREQUISITOR_UPDATEBUTTON, "Approve Requisitor UPDATE BUTTON");// PAYMENTINFO_LABEL
		waitForVisibilityOfElement(ShipBillPayObj.PAYMENTINFO_LABEL, "Payment Information");
		String textinPaymentandcards = getText(STOREDCARDS, "Stored cards text");
		if (isElementPresent(STOREDCARDS, "payments and cards Msg")) {
			reporter.SuccessReport("Stored Cards :", textinPaymentandcards, "Stored Cards Disabled : 1");
		} else {
			reporter.failureReport("Payments And Cards Msg is not visible", "Payments And Cards Msg is not visible",
					"");
		}
	}
	public void ApproveRequesitionEndUser(String RefNum) throws Throwable {
		click(Requesitor(RefNum), "Click on requesitor");
		waitForVisibilityOfElement(ShipBillPayObj.APPROVAL_MANAGEMENT_LABEL, "Approval Management");
		click(APPROVEREQUISITOR_UPDATEBUTTON, "Approve Requisitor UPDATE BUTTON");// PAYMENTINFO_LABEL
		waitForVisibilityOfElement(ShipBillPayObj.PAYMENTINFO_LABEL, "Payment Information");
		String textinPaymentandcards = getText(STOREDCARDS, "Stored cards text");
		if (isElementPresent(STOREDCARDS, "payments and cards Msg")) {
			reporter.SuccessReport("Stored Cards :", textinPaymentandcards, "Stored Cards is Enabled");
		} else {
			reporter.failureReport("Payments And Cards Msg is not visible", "Payments And Cards Msg is not visible",
					"");
		}
	}
	
	public void verifyStoredCard() throws Throwable {
		if (isElementNotPresent(STOREDCARDS, "payments and cards Msg")) {
			reporter.SuccessReport("Verify Default stored card in Payment section on SBP Page", "Default stored card does not exist", "");
		} else {
			reporter.failureReport("Verify Default stored card in Payment section on SBP Page", "Default stored card exist",
					"",driver);
		}
	}

	/**
	 * This method is to verify shipping carrier after review order
	 * 
	 * @throws Throwable
	 */
	public void verifyShippingCarrierAFterReviewOrder(String shippingCarrier,String Shippingcarrie2) throws Throwable {
		if (isElementPresent(ShipBillPayObj.verifyShippingCarrier(shippingCarrier), "Shipping carrier")) {
			reporter.SuccessReport("Verify shipping carrier is present", "shipping carrier is present ",
					Shippingcarrie2);
		} else {
			reporter.failureReport("Verify shipping carrier is not present", "shipping carrier is not present ",
					Shippingcarrie2);
		}

	}

	/**
	 * This method is to click Order details button in receipt after plSce order
	 * 
	 * @throws Throwable
	 */
	public void clickOrderDetailsButtonInREceipt() throws Throwable {
		Thread.sleep(10000);
		waitForVisibilityOfElement(ShipBillPayObj.ORDER_DETAILS_BUTTON, "Order details button");
		click(ShipBillPayObj.ORDER_DETAILS_BUTTON, "Order details button");
	}

	public void PaymentandCardsTextverify(String toolsMenuName, String dropDown, String tabName) throws Throwable {
		Thread.sleep(20000);
		if(isElementPresent(InvoiceHistoryObj.COSE_ACCOUNT_TOOLS, "close account tools"))
		{
		click(InvoiceHistoryObj.COSE_ACCOUNT_TOOLS, "close account tools");
		}
		if(isElementPresent(CommonObj.CLOSEBUTTON_COOKIES,"close cookie")) {
			click(CommonObj.CLOSEBUTTON_COOKIES, "close cookie");
		}
		click(CommonObj.ACCOUNT_TOOLS, "Account tools menu icon");
		click(CommonObj.getAccountToolsMenu(toolsMenuName), "Account tools menu");
		click(CommonObj.getAccountToolsDD(toolsMenuName, dropDown), "Select account tools");// -----Personalization,User
																							// Profile
		click(CommonObj.getFavoritesTabs(tabName), "Payments And Cards Tab is clicked");
		String textinPaymentandcards = getText(PaymentandcardsText, "Payment cards text");
		if (isElementPresent(PaymentandcardsText, "payments and cards Msg")) {
			reporter.SuccessReport("Verify Payment cards Msg", "Payments And Cards msg", textinPaymentandcards);
		} else {
			reporter.failureReport("Payments And Cards Msg is not visible", "Payments And Cards Msg is not visible",
					"");
		}
	}

	public void verifyTextatPaymentinfoCreditcard() throws Throwable {
		click(OrderObj.PAYMENT_METHOD_DD, "payment method drop down");
		click(OrderObj.PAYMENT_METHOD_SELECTION, "payment method selection");
		String textinPaymentandcards = getText(PaymentTextinfoText, "Payment cards text");
		if (isElementPresent(PaymentTextinfoText, "Payment info Credit card Text")) {
			reporter.SuccessReport("Verify Payments info credit card msg", "Payments info credit card msg",
					textinPaymentandcards);
		} else {
			reporter.failureReport("Payments info credit card Msg is not visible",
					"Payments info Credit Card Msg is not visible", "");
		}
	}

	/**
	 * This method is to Verify Logged in US Web Group
	 * 
	 * @throws Throwable
	 */
	public void verifyWebGroupIsUS() throws Throwable {
		if (isElementPresent(ShipBillPayObj.USA_WEBGROUP, "US Webgroup")) {
			reporter.SuccessReport("Verify it is Logged in US Web Group", "User is Logged into US Web Group", "US WebGrp::Web Group – USA - Praxair");
		} else {
			reporter.failureReport("Verify it is Logged in US Web Group", "User is not Logged into US Web Group", "");
		}
	}

	/**
	 * This method is to click on Click here to change to US webGroup
	 * 
	 * @throws Throwable
	 */
	public void clickHere() throws Throwable {
		waitForVisibilityOfElement(ShipBillPayObj.CLICK_HERE, "Click here");
		click(ShipBillPayObj.CLICK_HERE, "Click here");
		waitForVisibilityOfElement(ShipBillPayObj.CONTINUE_BUTTON, "Continue button");
		click(ShipBillPayObj.CONTINUE_BUTTON, "Continue button");
	}

	/**
	 * This method is to Verify Logged in US Web Group
	 * 
	 * @throws Throwable
	 */
	public void verifyWEbsiteIsCannada() throws Throwable {
		if (isElementPresent(ShipBillPayObj.CANNADA_WEBSITE, "Cannada website")) {
			reporter.SuccessReport("Verify Canada Web Site Login", "User is Logged into Canada Insight", "Canada Webgrp::Web Group – Canada - Praxair");
		}
		if(getText(ShipBillPayObj.CANNADA_WEBSITE,"Canada").contains("Canada")){
			reporter.SuccessReport("Verify Canada Web Site Login", "User is Logged into Canada Insight", "Canada Webgrp::Web Group – Canada - Praxair");
		}
		else {
			reporter.failureReport("Verify Canada Web Site Login", "User is Not Logged into Canada Insight", "");
		}
	}

	/**
	 * This method is to Verify Sub total price is CAD in cart
	 * 
	 * @throws Throwable
	 */
	public void verifyPriceIsCAD(String actualCurrency) throws Throwable {
		if (isElementPresent(ShipBillPayObj.SUB_TOTAL, "Sub totoal")) {
			String currency = getText(ShipBillPayObj.SUB_TOTAL_CURRENCY, "Sub total currency");
			if (currency.equalsIgnoreCase(actualCurrency)) {
				reporter.SuccessReport("Verify Subtotal Before Taxes in the Cart",
						"Subtotal Before Taxes is Showing as Canadian Dollars in the Cart", "");
			} else {
				reporter.failureReport("Verify Subtotal Before Taxes in the Cart",
						"USubtotal Before Taxes is Not Showing as Canadian Dollars in the Cart", "");
			}
		}

	}

	public void addAddtionalInfo(String name, String phone, String email) throws Throwable {
		if (isElementPresent(OrderObj.ORDER_ITEM_INFO_LABEl, "order and inforamtion page")
				&& isElementPresent(ADD_ADDITIONAL_INFO, "ADD additional info")) {
			type(NAME, name, "Name: " + name);
			type(PHONE, phone, "Phone: " + phone);
			type(EMAIL, email, "Email: " + email);
		}
	}

	public void getSummaryAmountsInCart(String subtotal, String total) throws Throwable {

		if (isElementPresent(getAmountsInSummary(subtotal), "Subtotal")) {
			String subTotalAmount = getText(getAmountsInSummary(subtotal), "Sub total amount");
			reporter.SuccessReport("Verify Subtotal Before Taxes in the Cart", "Subtotal Before Taxes: ",
					subTotalAmount);
		} else {
			reporter.failureReport("Verify Subtotal Before Taxes in the Cart",
					"subtotal Before Taxes Does Not Exist in the Cart", "");

		}

		if (isElementPresent(SUMMARY_AMOUNT, "shipping Estimate")) {
			String shippingEstimateAmount = getText(SUMMARY_AMOUNT, "shipping Estimate");
			reporter.SuccessReport("Verify Shipping Estimate in the Cart", "Shipping Estimate: ",
					shippingEstimateAmount);
		} else {
			reporter.failureReport("Verify Shipping Estimate in the Cart",
					"Shipping Estimate Does Not Exist in the Cart", "");
		}

		if (isElementPresent(getAmountsInSummary(total), "Total Amount")) {
			String totalAmount = getText(getAmountsInSummary(total), "Total Amount");
			reporter.SuccessReport("Verify Total Amount in the Cart", "Total Amount: ", totalAmount);
		} else {
			reporter.failureReport("Verify Total Amount in the Cart", "Total Amount Does Not Exist in the Cart", "");
		}

	}
	
	public String getShippingEstimateAmount() throws Throwable {
		String shippingEstimateAmount = getText(SUMMARY_AMOUNT, "shipping Estimate");
		return shippingEstimateAmount;
	}

	public void verifyBillmycarrier() throws Throwable {
		if (isCheckBoxSelected(BILLMYCARRIER_CHECKBOX)) {
			reporter.failureReport("Bill my carrier Check Box is cliked Already",
					"Bill my carrier Check box is clicked Already", "");
		} else {
			reporter.SuccessReport("Bill my carrier Check Box is Not clicked",
					"Bill my carrier Check box is Not Clicked", "");
		}
	}

	public void Verifyshippingcarrier(String carrierName) throws Throwable {
		if (isElementPresent(shippingcarrier(carrierName), "Shipping carrier is present")) {

			reporter.SuccessReport("Shipping carrier is Present  ", "Shipping carrier is : ", carrierName);

		} else {
			reporter.failureReport("Shipping carrier is Not Presnt", "Shipping Carrier is Not Present", "");
		}
	}

	public void SelectcarrierAtBillmycarrier(String DefCarrier) throws Throwable {
		if (isCheckBoxSelected(BILLMYCARRIER_CHECKBOX)) {
			reporter.SuccessReport("verify Billmy carrier checkbox::", "Bill my carrier account CheckBox Exist and Checked", "");
			if (isElementPresent(BILLMYCARRIEROPTION(DefCarrier), "BILLMYCARRIER Option is Clicked")) {
				reporter.SuccessReport("Billmy carrier  Presnt", "Bill your carrier account Options are Exist and Verified", ""+DefCarrier+"");
			} else {
				reporter.failureReport("Billmy carrier Option is Not Presnt", "Billmy Carrier Option is Not Present",
						"");
			}
		} else {
			reporter.failureReport("Billmy carrier is Not Presnt", "Billmy Carrier is Not Present", "");
		}
		//click(GROUND_CARRIER,"Shipping Carrier::Ground");
	}

	public void verifyCarrierAccountinplaceorder() throws Throwable {
		if (isElementPresent(CARRIER_ACCOUNT, "Carrier Account Number is Present")) {
			reporter.SuccessReport("Carrier Account Number is Presnt", "Carrier Account Number is Present", "");
		} else {
			reporter.failureReport("Carrier Account Number is Not Presnt", "Carrier Account Number is Not Present", "");
		}
	}

	public void VeifyChoosenCarrierMsg() throws Throwable {
	String MSG=getText(CHOOSENCARRIERNOTMATCH_MSG,"Carrier Msg");
		if (isElementPresent(CHOOSENCARRIERNOTMATCH_MSG, "Choosen carrier msg is visible")) {
			reporter.SuccessReport("Choosen carrier msg is Presnt", "Choosen carrier msg is is Present", MSG);
		} else {
			reporter.failureReport("Choosen carrier msg is Not Presnt", "Choosen carrier msg is Not Present", "");

		}
	}

	public void Verifycarrieroptionsdiabled(String disabledcarrier) throws Throwable {
		waitForVisibilityOfElement(CARRIERS_DROPDOWN, "carriers Dropdown is visible");
		if (isElementPresent(CARRIERS_DROPDOWN, "Carrier Dropdown is visible")) {
			click(CARRIERS_DROPDOWN, "CARRIER Dropdown is clicked");
			click(FEDX_CARRIER, "FedEx Carrier is Selected");
			reporter.SuccessReport("Carrier::", "FedEx Carrier is Selected", "FedEx");
		} else {
			reporter.failureReport("Carriers Dropdown is Not Presnt", "Carriers Dropdown is Not Present", "");
		}
		if (isElementPresent(DISABLED_OPTIONS_SHIPPINGOTIONS(disabledcarrier), "Carriers Are Disabled")) {
			reporter.SuccessReport("Carriers Are Disabled", "Carriers Are Disabled", "");
		} else {
			reporter.failureReport("Carriers Are Not Disabled", "Carriers Are Not Disabled", "");
		}
	}

	public void SelectstoredCardinRequistion(String toolsMenuName, String dropDown, String Value, String RefNum)
			throws Throwable {
		Thread.sleep(20000);
		if(isElementPresent(InvoiceHistoryObj.COSE_ACCOUNT_TOOLS, "close account tools"))
		{
		click(InvoiceHistoryObj.COSE_ACCOUNT_TOOLS, "close account tools");
		}
		click(CommonObj.ACCOUNT_TOOLS, "Account tools menu icon");
		click(CommonObj.getAccountToolsMenu(toolsMenuName), "Account tools menu");
		click(CommonObj.getAccountToolsDD(toolsMenuName, dropDown), "Select account tools");
		click(ShipBillPayObj.Requesitor(RefNum), "Click on requesitor");
		click(ShipBillPayObj.APPROVEREQUISITOR_UPDATEBUTTON, "Update Button is clicked");
		selectByValue(ShipBillPayObj.STOREDCARDS_DROPDOWN_ENDUSER, Value, "Select one Storedcard");

	}

	public void UserProfCardVerification(String toolsMenuName, String dropDown, String tabName) throws Throwable {
		Thread.sleep(20000);
		if(isElementPresent(InvoiceHistoryObj.COSE_ACCOUNT_TOOLS, "close account tools"))
		{
		click(InvoiceHistoryObj.COSE_ACCOUNT_TOOLS, "close account tools");
		}
		if(isElementPresent(CommonObj.CLOSEBUTTON_COOKIES,"close cookie")) {
			click(CommonObj.CLOSEBUTTON_COOKIES, "close cookie");
		}
		click(CommonObj.ACCOUNT_TOOLS, "Account tools menu icon");
		click(CommonObj.getAccountToolsMenu(toolsMenuName), "Account tools menu");
		click(CommonObj.getAccountToolsDD(toolsMenuName, dropDown), "Select account tools");
		click(CommonObj.getFavoritesTabs(tabName), "Payments And Cards Tab is clicked");
		String textinPaymentandcards = getText(STOREDCARDS_ENDUSER, "In Payments and cards stored card Text");
		if (isElementPresent(STOREDCARDS_ENDUSER, "Stored card Exists")) {
			reporter.SuccessReport("Verify stored card Verification Success", "Expected QTP Stored Card 1 Exists and Selected QTP Stored Card 1",
					textinPaymentandcards);
		} else {
			reporter.failureReport("Stored card Verification not Success", "Stored card Verification not Success", "");
		}
	}

	public void ReviewrequisitionnumPage() throws Throwable {
		ClickRviewrequesition();
		clickUntil(PLACE_REQUISITION_BUTTON, OrderObj.RECEIPT_LABEL, "Place requisition button is clicked");
		Thread.sleep(3000);
		if (isElementPresent(OrderObj.RECEIPT_LABEL, "Recipt Page is Opened")) {
			waitForVisibilityOfElement(REFERENCE_NUMBER, "Reference Number");
			reporter.SuccessReport("Reference Number ", "Referenace Number is Visible", "");
		} else {
			reporter.failureReport("Reference Number is Not created", "Reference Number is Not created", "");
		}
	}

	/**
	 * This method is to get RefNum after placing order
	 * 
	 * @throws Throwable
	 */

	public String getReferenceNum() throws Throwable {
		String Refnum = getText(REFERENCE_NUMBER, "Refernce Number:");
		String RefNum = Refnum.replace("(In process)", " ").trim();
		reporter.SuccessReport("Verify ref number ", "Reference Number: ", RefNum);
		return RefNum;

	}

	/**
	 * This method is to add products By Quick shop
	 * 
	 * @throws Throwable
	 */
	public void AdditemsbyQuickshop(String searchItem) throws Throwable {
		waitForVisibilityOfElement(CartObj.QUICK_SHOP_ITEM_FIELD, "QUICK SHOP ITEM FIELD");
		type(CartObj.QUICK_SHOP_ITEM_FIELD, searchItem, "QUICK SHOP ITEM FIELD");
		Thread.sleep(3000);
		click(CartObj.ADD_BUTTON_IN_QUICK_SHOP, "ADD BUTTON IN QUICK SHOP");
		Thread.sleep(3000);
		waitForVisibilityOfElement(CartObj.CART_LABEL_ON_CART_PAGE, "Item added to cart");
		scrollUp();
		Thread.sleep(3000);
	}

	/**
	 * This method is to add Additional information After proceed to checkout
	 * 
	 * @throws Throwable
	 */

	public void Addadtionalinformation(String wG_HDL_Txt, String emailToEnter, String A) throws Throwable {
		CartLib cartLib = new CartLib();
		if (isElementPresent(OrderObj.ORDER_ITEM_INFO_LABEl, "order and inforamtion page")) {
			typeText(OrderObj.WG_HDL_Txt, wG_HDL_Txt, "wG_HDL_Txt tet box");
			cartLib.enterValidAddtionalEmail(emailToEnter);
			selectByVisibleText(WG_HDL_Lst, A, "WG_HDL_Lst");
		} else {
			reporter.failureReport("Additional information is Not able to Enter",
					"Additional information is Not able to Enter", "");
		}

	}

	/**
	 * This method is to add products By Quick shop
	 * 
	 * @throws Throwable
	 */
	public void clickstoredAddress(String Text) throws Throwable {
		click(STOREDADDRESS_LINK, "stored Address Link");
		click(SEARCH_FIELD,"Searc Field");
		type(SEARCH_FIELD,Text,"Search Field");
		click(search_Button,"Search Button");
		click(RADIOBUTTON_DEFUALTADDRESS,"Click Radio Button");
		if (driver.findElement(RADIOBUTTON_DEFUALTADDRESS).isSelected()) {
			reporter.SuccessReport("Stored address is selected", "Stored Address is selected", Text);
		} else {
			reporter.failureReport("Stored address is Not selected", "Stored address is Not selected", "");
		}
		getText(SHIPPING_ADDRES,"Shiping address Company :"+Text).trim();
		click(CONTINUE_BUTTONSTOREDADDRESS, "continue Button of Stored Address");

	}

	public void SaveCartandView(String cartName, String toolsMenuName, String dropDown) throws Throwable {
		isElementPresent(CartObj.SAVE_CART_CONTENTS, "Save cart contents");
		clickUntil(CartObj.SAVE_CART_CONTENTS, CartObj.SAVE_CART_CONTENTS_POPUP, "Save cart contents");
		waitForVisibilityOfElement(CartObj.SAVE_CART_CONTENTS_POPUP, "SAVE CART CONTENTS POPUP");
		click(SAVE_CART_INPUT_FIELD, "Save cart Textfield");
		type(SAVE_CART_INPUT_FIELD, cartName, "cart name");
		click(CartObj.SAVE_BUTTON, "Save button");
		waitForVisibilityOfElement(CartObj.CART_SAVED_SUCESS_MESSAGE, "cart save sucess message");
		if (isElementPresent(CartObj.CART_SAVED_SUCESS_MESSAGE, "Save cart sucess message")) {
			reporter.SuccessReport("Save cart sucess message ",
					"Your cart has been successfully saved message is displayed", "");
		} else {
			reporter.failureReport("Save cart error message ",
					"Your cart has been successfully saved message is not displayed", "");

		}
		click(CartObj.CONTINUE, "Continue button");
		waitForVisibilityOfElement(ShipBillPayObj.WELCOMEMSG_DASHBOARD, "Welcome to Insight");
		click(CommonObj.getAccountToolsMenu(toolsMenuName), "Account tools menu");
		click(CommonObj.getAccountToolsDD(toolsMenuName, dropDown), "Select account tools");
		// SAVEDCART
		waitForVisibilityOfElement(SAVEDCART(cartName), "SAVED CART::" + cartName + "");
		click(ADDTOCART_SAVEDCARTS(cartName), "Add to cart");
		// ADDEDTOCART_POPUP
		waitForVisibilityOfElement(ADDEDTOCART_POPUP, "ADDED TO CART");
		click(CHECKOUT_SAVEDCARTS, "Continue To Checkout");
		waitForVisibilityOfElement(CartObj.CART_LABEL_ON_CART_PAGE, "Cart Page");

	}
	/**
	 * Method is to fill the Reporting Fields on the Line level info section
	 * @param reportingField4
	 * @param reportingField5
	 * @param reportingField6
	 * @throws Throwable
	 */
	public void enterReportingDetailsInLineLevelInfoSection(String reportingField4,String reportingField5,String Wg_LNL_Txt) throws Throwable{
		if(isElementPresent(OrderObj.REPORTING_FIELD_4, "Reporting Field 4")){
		typeText(OrderObj.REPORTING_FIELD_4, reportingField4, "Reporting Field 4");
		typeText(OrderObj.REPORTING_FIELD_5, reportingField5, "Reporting Field 5");
		typeText(WG_LNL, Wg_LNL_Txt, "RP_LNL_Txt text box::"+Wg_LNL_Txt+"");
		click(CopyToALL,"Copt To ALL Exixts and Clicked");
		}else{
			reporter.failureReport("Verify reporting fields displayed in the Line level information section","Reporting fields are not displayed Line level information","");
		}
	}

	public void VerifyStoredAddress(String storedaddress) throws Throwable {
		click(STOREDADDRESS_LINK, "Stored Address Link");
		if (isVisibleOnly(STOREDADDRESS(storedaddress), "Stored Address is Visible")) {
			reporter.SuccessReport("Verify default stored address", "Defualt Stored Address is displayed ",
					storedaddress);
		} else {
			reporter.failureReport("Verify default stored address", "Defualt Stored Address is Not displayed",
					"Defualt Stored Address is not displayed");

		}
		Thread.sleep(2000);
		click(CANCELBUTTON_STOREDADDRESS, "Cancle Button");
	}
	
	public void selectUSShippingAddressesAccount() throws Throwable {
		click(STOREDADDRESS_LINK, "stored Address Link");
		click(US_ADDRESSES,"US addresses");
		click(CONTINUE_BUTTON_STORED_ADDRESSES,"Continue button");
	}
	public void Searchforstoredaddress(String Userstoredaddress) throws Throwable {
		waitForVisibilityOfElement(EDITBUTTON_SHIPPINGADDRES, "Edit Button");
		clickUntil(EDITBUTTON_SHIPPINGADDRES, STOREDADDRESS_LINK, "Edit Button of Shippingadress");
		click(STOREDADDRESS_LINK, "stored Address Link");
		waitForVisibilityOfElement(SEARCHFIELD_STOREDADDRESS, "Stored Address Search Field");
		type(SEARCHFIELD_STOREDADDRESS, Userstoredaddress, "Stored Address Search field");
		click(SEARCH_BUTTON, "Search Button");
		waitForVisibilityOfElement(STOREDADDRESS(Userstoredaddress), "" + Userstoredaddress + "");
		if (isElementPresent(STOREDADDRESS(Userstoredaddress), "Stored Address Account Name")) {
			reporter.SuccessReport("Verify stored address", "Stored Address Account Name is displayed",
					Userstoredaddress);
		} else {
			reporter.failureReport("Stored Address Account Name is Not displayed", "Stored Address is not displayed",
					"");

		}
	}
	/**
	 * Method is to add stored address
	 * @param Userstoredaddress
	 * @throws Throwable
	 */
	public void selectStoredAddress(String Userstoredaddress) throws Throwable {
		click(STOREDADDRESS_LINK, "stored Address Link");
		waitForVisibilityOfElement(SEARCHFIELD_STOREDADDRESS, "Stored Address Search Field");
		if(isElementPresent(SEARCHFIELD_STOREDADDRESS, "Stored Address Search Field")) {
			reporter.SuccessReport("Verify stored address page", "Stored address link is displayed and verified", "");
			type(SEARCHFIELD_STOREDADDRESS, Userstoredaddress, "Stored Address Search field");
			click(SEARCH_BUTTON, "Search Button");
		}else {
			reporter.failureReport("Verify stored address page", "Stored address page not displayed", "NA",driver);
		}
	}

	public void ClickcancelButtonStoredAddress() throws Throwable {
		click(CANCELBUTTON_STOREDADDRESS, "Cancel Button");
	}

	public void clickContinueOnStoredAddresssScreen() throws Throwable {
		click(CONTINUE_BUTTON_STOREDADDRESS, "Continue on Stored address screen", "Continue on Stored address screen");
	}
	public void RemoveDefualtShippingAddress() throws Throwable {
		waitForVisibilityOfElement(REMOVE_DEFAULTADDRESS, "Remove defualt Address");
		if (isElementPresent(REMOVE_DEFAULTADDRESS, "Defualt Address")) {
			click(REMOVE_DEFAULTADDRESS, "Defualt Address is Removed");
		} else {
			reporter.SuccessReport("Defualt Address is Already Removed", "Defualt Address is Already Removed", "");
		}
	}

	public void AddNewshippingAddressWithcountry(String link, String companyName, String street, String city,
			String zipcode, String state, String Country,String attention) throws Throwable {
		Thread.sleep(2000);
		if(isElementPresent(OrderObj.headerlinkCheck(link),"Header link check")){
		clickUntil(OrderObj.ADDLINK,OrderObj.COMPANY_NAME_LBL,"Add link");
		typeText(OrderObj.COMPANY_NAME_LBL,companyName,"company Name::"+companyName+"");
		typeText(OrderObj.STREETLINE_LBL,street,"Street Name::"+street+"");
		typeText(OrderObj.CITY_LBL,city,"City name::"+city+"");
		typeText(OrderObj.ZIPCODE,zipcode,"ZIP/Postal code::"+zipcode+"");
		selectByVisibleText(OrderObj.SELECT_COUNTRY,Country,"Country::"+Country+"");
		selectByVisibleText(OrderObj.SELECT_STATE,state,"STATE::"+state+""); 
		waitForVisibilityOfElement(OrderObj.SHIP_ATTENTION, "Ship Attention");
		if (isElementPresent(OrderObj.SHIP_ATTENTION, "Ship Attention")) {
			click(OrderObj.SHIP_ATTENTION, "Ship Attention");
			type(OrderObj.SHIP_ATTENTION, attention, "Ship Attention");
		click(OrderObj.SHIPPING_ADDRESS_CONTINUE_BTN,"Continue button");
		if(isElementPresent(OrderObj.ADDRESS_VALIDATION_WINDOW_HDR, "Address validation popup")){
			click(OrderObj.SAVE_ADDRESS_BTN, "Save address button");
			Thread.sleep(2000);
			}
		}
	}
	}
	public void VerifyCreatedAddress(String Company) throws Throwable {
		isVisibleOnly(OrderObj.SHIPPING_ADDRESS, "Shipping Address");
		if (isElementPresent(CREATEDADDRES(Company), "Created Address")) {
			String Addres=getText(SHIPPING_ADDRES,"Shiping address").trim();
			//String Addres1=getText(SHIPPING_ADDRESES,"Shiping address").trim();
			//String Addres2=getText(SHIPPING_ADDRESESSECONDLINE,"Shiping address").trim();
			reporter.SuccessReport("Verify New address ", "New Is Verified successfully", Company+ Addres);
		} else {
			reporter.failureReport("New Address Is Not Created", "New Address Is Not Created", "");
		}
	}
	public void VerifyDefualtSoldtoAddress(String Company) throws Throwable {
		Thread.sleep(4000);
		if (isVisibleOnly(CREATEDADDRES(Company), "Sold-To Shipping Address")) {
			Thread.sleep(4000);
			String Addres=getText(SHIPPING_ADDRES,"Shiping address").trim();
			//String Addres1=getText(SHIPPING_ADDRESES,"Shiping address").trim();
			//String Addres2=getText(SHIPPING_ADDRESESSECONDLINE,"Shiping address").trim();
			reporter.SuccessReport("Verify Sold-To Shipping Address", "Sold-To Shipping Address", "ADDRESS ::"+Company +Addres);
		} else {
			reporter.failureReport("Verify Sold-To Shipping Address", "Sold-To Shipping Address", "");
		}
	}

	public void SelectAllLinkedaddresses(String Linkuseraddresses) throws Throwable {
		if (isElementPresent(LINKEDACCOUNTSDROPDOWN, "LINKEDACCOUNTSDROPDOWN")) {
			selectByVisibleText(LINKEDACCOUNTSDROPDOWN, Linkuseraddresses, "Link User To addresses");
		} else {
			reporter.failureReport("Unable to Link user to Available Adresses",
					"Unable to Link user to Available Adresses", "");
		}
		click(UPDATEBUTTON_SHPPINGADDRESS, "Update Button Permissions");
		waitForVisibilityOfElement(SUCCESSMSG_SHIPPINGADDRESS, "Shipping Address Option Updated Msg");
	}
	
	public void verifyNoDefaultAddress() throws Throwable {
		
		if (isElementPresent(DEFUALT_ADDRESS, "No Defualt Address")) {
			reporter.SuccessReport("verify Defualt Address::",
					"No Defualt Address", "No Default Shipping Address is set.Please select below.");	
		} else {
			reporter.failureReport("verify Defualt Address::",
					"Unable to Verify No Defualt Address Adresses", "");
		}
		click(UPDATEBUTTON_SHPPINGADDRESS, "Update Button Permissions");
		waitForVisibilityOfElement(SUCCESSMSG_SHIPPINGADDRESS, "Shipping Address Option Updated Msg");
	}//DEFUALTEMAIL_ADDTIONALNOTIFICATIONS

	public void enterEmail(String Email)throws Throwable{
		clearData(DEFUALTEMAIL_ADDTIONALNOTIFICATIONS);
		typeText(DEFUALTEMAIL_ADDTIONALNOTIFICATIONS,Email,"Email Address Field Exist and Entered::"+Email+"");
		click(UPDATEBUTTON_SHPPINGADDRESS, "Update Button Email Address in Additional Notification");
	}
	public void SelectdefualtAddress() throws Throwable {

		if (isCheckBoxSelected(DEFUALTADRESSES)) {
			reporter.SuccessReport("Verify Defualt Address is Selected::", "Default Address Exist and Seleted First Radio Option", "Defaulted Account: insight");
		} else {
			click(DEFUALTADRESSES, "Defualt address");
			reporter.SuccessReport("Verify Defualt Address is Selected::", "Default Address Exist and Seleted First Radio Option", "Defaulted Account: insight");
		}
		click(UPDATEBUTTON_SHPPINGADDRESS, "Update Button");
		waitForVisibilityOfElement(SUCCESSMSG_SHIPPINGADDRESS, "Shipping Address Option Updated Msg");

	}

	/**
	 * 
	 * @param cardNumber
	 * @param cardName
	 * @param month
	 * @param year
	 * @param poNumebr
	 * @throws Throwable
	 */
	public void VisaCardErrorPayment(String cardNumber, String cardName, String month, String year, String poNumebr)
			throws Throwable {
		click(PAYMENT_METHOD_DD, "payment method drop down");
		if(isVisibleOnly(OrderObj.PAYMENT_METHOD_VERIFICATION_TERMS,"Terms")) {
			reporter.failureReport("Verify payment options:", "Terms Exists in payment Options", "Terms");
			if(isVisibleOnly(OrderObj.PAYMENT_METHOD_VERIFICATION_procurementscard,"Procurement Card")) {
				reporter.failureReport("Verify payment options:", "Procurementcard  Option exits", "");	
			}
		}else {
			 reporter.SuccessReport("Verify payment options:", "Only Credit card exists as Payments Option", "Credit Card");	
		type(OrderObj.CARD_NUMBER_TEXTBX, cardNumber, "Card number"); // Entering
																		// details
		// in payment
		// info
		type(OrderObj.CARD_NAME_TEXTBOX, cardName, "Card name");
		click(OrderObj.EXPIRATION_MONTH, "Expiration month");
		selectByValue(OrderObj.EXPIRATION_MONTH, month, "Expiration month");
		click(OrderObj.EXPIRATION_YEAR, "Expiration year");
		selectByValue(OrderObj.EXPIRATION_YEAR, year, "Expiration year");
		click(OrderObj.REVIEW_ORDER_BTN, "review order button of payment Info");
		if (isElementPresent(VISA_CARD_ERROR_MESSAGE, "Visa card error message")) {
			reporter.SuccessReport("Verify visa card error message", "Visa card type is not supported", "");
		} else {
			reporter.failureReport("Verify visa card error message", "Visa card type is supported", "");
		}
		}
	}

	public void emexCardPayment(String cardNumber, String cardName, String month, String year, String poNumebr)
			throws Throwable {
		clearData(OrderObj.CARD_NUMBER_TEXTBX);
		type(OrderObj.CARD_NUMBER_TEXTBX, cardNumber, "Card number"); // Entering
																		// details
		// in payment
		// info
		clearData(OrderObj.CARD_NAME_TEXTBOX);
		type(OrderObj.CARD_NAME_TEXTBOX, cardName, "Card name");
		click(OrderObj.EXPIRATION_MONTH, "Expiration month");
		selectByValue(OrderObj.EXPIRATION_MONTH, month, "Expiration month");
		click(OrderObj.EXPIRATION_YEAR, "Expiration year");
		selectByValue(OrderObj.EXPIRATION_YEAR, year, "Expiration year");
		click(OrderObj.REVIEW_ORDER_BTN, "review order button of payment Info");

	}

	public void VerifypaymentOptions(String Creditcard) throws Throwable {

		if (isElementPresent(PAYMENT_METHOD_DD, "payment DD")) {
			click(PAYMENT_METHOD_DD, "payment method drop down");
			waitForVisibilityOfElement(CREDITCARDOPTION(Creditcard), "Payment Options");
			String textinPaymentandcards = getText(CREDITCARDOPTION(Creditcard), "Payment Option");
			if (isElementPresent(CREDITCARDOPTION(Creditcard), "payment DD")) {
				reporter.SuccessReport("Verify payment options", "Payemnt Option::", textinPaymentandcards);
			} else {
				reporter.failureReport("Payemnt Option", "payemnt Option is not visible", "");
			}
		}

		else {
			reporter.failureReport("Payment Method dropdown", "Payment Method dropdown is not visible", "");
		}
	}
	

	/**
	 * This method is used to click on state and central government in header menu
	 * @param option
	 * @throws Throwable
	 */
	public void clickOnStateAndCentralGovernment(String contract) throws Throwable {
		waitForVisibilityOfElement(STATE_AND_LOCAL_GOVT, "State & local government Link");
		click(STATE_AND_LOCAL_GOVT, "State & local government Link");
		click(STATE_AND_LOCAL_GOVT_DROPDOWN, "State Contracts DropDown");
		click(selectDropdownOption(contract),"Select "+contract+" ");
	}
	
	/**
	 * This method is used to click on state and central government in header menu
	 * @param option
	 * @throws Throwable
	 */
	public void selectContract(String contract,String country,String contractOption) throws Throwable {
        click(contractDropdown(contract),contract+" dropdown");
        click(selectCountry(country),country);
        click(SELECT_CONTRACT,"Select a contract");
        click(contractOption(contractOption),contractOption);
	}
	/**
	 * This method is used to click on contract dropdown
	 * @param option
	 * @throws Throwable
	 */
	public void selectContractOption(String contractOption,String contractOption1) throws Throwable {
		 click(contractOption(contractOption),contractOption);
		 click(contractOption(contractOption1),contractOption);
		 Thread.sleep(3000);
		 JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript("window.scrollTo(250, 0)");
		 
	}
	/**
	 * This method is used to verify login page
	 * @param option
	 * @throws Throwable
	 */
	public void verifyHomepgaeLogin(String loginHeader) throws Throwable {
		if(isElementPresent(CommonObj.getPrimaryNavLink(loginHeader), "Login link")) {
			reporter.SuccessReport("Verifying home page", "User is navigated to login page", "login");
		}
		else {
			reporter.failureReport("Verifying home page", "User is not navigated to login page", "login");
		}
	}
	/**
	 * This method is used to click on browse products
	 * @param option
	 * @throws Throwable
	 */
	public void clickonBrowseProducts() throws Throwable {
		click(BROWSE_PRODUCTS,"Browse Products Button");
	}
	
	/**
	 * This method is used to verify products displayed
	 * @param option
	 * @throws Throwable
	 */
	public void verifyProducts(String contractOption) throws Throwable {
		waitForVisibilityOfElement(CHANGE,"Change");
		List<WebElement> mylist=driver.findElements(verifyDisplayedProductContract(contractOption));
		
		if(isElementPresent(verifyDisplayedProductContract(contractOption), "Contract")) {
			reporter.SuccessReport("Verifying products displayed","are of contract "+contractOption , contractOption);
		}
		else {
			reporter.failureReport("Verifying products displayed","are not of contract "+contractOption , contractOption);
		}
		
	}
	/**
	 * This method is used to click on change
	 * @param option
	 * @throws Throwable
	 */
	public void clickonchange() throws Throwable {
		click(CHANGE,"Change");
	}
	/**
	 * This methosd is used to click on shop in header menu
	 * @param option
	 * @throws Throwable
	 */
	public void clickOnShop(String header) throws Throwable {
		waitForVisibilityOfElement(CommonObj.getSecondaryHeaderMenu(header), "Header menu");
		click(CommonObj.getSecondaryHeaderMenu(header), "Secondary header link :"+header);
	}
	

	/**
	 * 
	 * @param option
	 * @throws Throwable
	 */
	public void selectTheGroupPaymentOptions(String Paymentoption) throws Throwable {
		waitForVisibilityOfElement(OPTIONSINCLUDED_REQUESTORGRP, "Payment Options in requestor group");
		selectByVisibleText(OPTIONSINCLUDED_REQUESTORGRP, Paymentoption, "Payment Options are Already Included in the Avilable Options::" + Paymentoption + "");
		click(OrderObj.SAVE_CHANGES_BTN, "save changes button");
		if (isElementPresent(OrderObj.SHIPPING_PAYMENTS_SUCCESS_MSG, "Success message")) {
			reporter.SuccessReport("Verify success message", "Shipping payments options success message is displayed",
					"");
		} else {
			reporter.failureReport("Verify success message", "Success message is not displayed", "");
		}

	}

	public void SelectAllowedoptionspaymentcheckoutsettings(String Paymentoption) throws Throwable {
		waitForVisibilityOfElement(ALLOWEDOPTIONS_CHECKOUTSETTINGS, "Allowed Options");
		if(isVisibleOnly(PROCUREMENTCARD_OPTION,"Procurement Card Option")) {
		selectByVisibleText(ALLOWEDOPTIONS_CHECKOUTSETTINGS, Paymentoption, "Payment option::" + Paymentoption + "");
		click(CMTObj.LEFT_ARROW, Paymentoption + " is selected And Moved to Available Options");
		click(UPDATEBUTTON_SHPPINGADDRESS, "update Button");
		waitForVisibilityOfElement(SUCCESMSG_PAYMENTOPTIONS, "Payment options updated Success Msg");
		reporter.SuccessReport("Verify Payment Option", "Payment Option added to Avialble Options", "");
		}else {
			reporter.SuccessReport("Verify Payment Option", "Payment Option Alredy added to Avialble Options", Paymentoption);
		}
	}

	/**
	 * This method is to Select shipping carrier
	 * 
	 * @throws Throwable
	 */
	public void Selectshippingcarrier() throws Throwable {
		OrderLib orderLib = new OrderLib();
		if (isElementPresent(SELECT_CARRIER_DD, "shipping carrier Dropdown")) {
			click(SELECT_CARRIER_DD, "carrier Drop down");
			if (driver.findElement(GROUND_CAREER).isSelected()) {
				reporter.SuccessReport("FedEx Ground Career is selected", "FedEx Ground Career is selected", "FedEx Ground - USD $30.46");
			} else {
				reporter.failureReport("FedEx Ground Career is Not selected", "FedEx Ground Career is Not selected",
						"");
			}
		} else {
			reporter.failureReport("FedEx Ground Career is Not selected", "FedEx Ground Career is Not selected", "");
		}
		orderLib.selectShippingOptionsCarrierSection();
	}

	public void verifyshippingcareer() throws Throwable {
		// SHIPINGCAREER
		if (isElementPresent(SHIPINGCAREER, "Shipping carrer")) {
			reporter.SuccessReport("Shipping carrer", "Shipping carrer is verified", "");
		} else {
			reporter.failureReport("Shipping carrer", "Shipping carrer is Not verified", "");
		}
	}

	/**
	 * 
	 * @param rP_LNL_Txt
	 * @throws Throwable
	 */
	public void addLineLevelInfoSmartTracker(String Wg_LNL_Txt) throws Throwable {
		if (isElementPresent(OrderObj.LINE_LEVEL_INFO, "Line level information link")) {
			click(OrderObj.LINE_LEVEL_INFO, "Line Level Information");
			if (isElementPresent(OrderObj.SMART_TRACKER_LABEL, "Smart tracker in LL info section")) {
				type(WG_LNL_Txt, Wg_LNL_Txt, "RP_LNL_Txt text box");
				click(OrderObj.LLI_CONTINUE_BTN, "Continue button");
			}
		}
	}
	/**
	 * 
	 * @param total
	 * @throws Throwable
	 */
	public String getTotalAmountInCart(String total) throws Throwable {
		String totalAmount = getText(getAmountsInSummary(total), "Total Amount");
		return totalAmount;
	}
	
	public void Deletesavedcarts(String toolsMenuName,String dropDown,String cartName)throws Throwable {
		Thread.sleep(20000);
		if(isElementPresent(InvoiceHistoryObj.COSE_ACCOUNT_TOOLS, "close account tools"))
		{
		click(InvoiceHistoryObj.COSE_ACCOUNT_TOOLS, "close account tools");
		}
		click(CommonObj.ACCOUNT_TOOLS, "Account tools menu icon");
		click(CommonObj.getAccountToolsMenu(toolsMenuName), "Account tools menu");
		click(CommonObj.getAccountToolsDD(toolsMenuName, dropDown), "Select account tools");
		// SAVEDCART
		waitForVisibilityOfElement(SAVEDCART(cartName), "SAVED CART::" + cartName + "");
		click(Deletesavedcart(cartName), "Add to cart");
		// ADDEDTOCART_POPUP
		waitForVisibilityOfElement(DIALOGUEBOX_DELETECART, "Delete Cart");
		click(YESBUTTON_DELETECART, "Continue To Checkout");
		
	}
	public void ClickRviewrequesition()throws Throwable {
		clickUntil(REVIEW_REQUISTION_BUTTON,PLACE_REQUISITION_BUTTON, "Review requisition button ic clicked");
	}
	/**
	 * This method is to Select shipping carrier
	 * 
	 * @throws Throwable
	 */
	public void selectshippingcarrierAsRequired(String carrier,String shippingMethod) throws Throwable {
		OrderLib orderLib = new OrderLib();
		click(SELECT_A_CARRIER, "select a carrier");
		if (isElementPresent(selectCarrierDD(carrier), "shipping carrier Dropdown")) {
			click(selectCarrierDD(carrier), "carrier Drop down");
			
		}	
		click(selectShippingMethod(shippingMethod),"Shipping method");
		orderLib.shippingBillPayContinueButton();
	}
	public void VeifyNoCarrier() throws Throwable {
		if (isElementPresent(NO_SHIPPING_CARRIER, "Shipping Carrier")) {
			reporter.SuccessReport("Verify Shipping Carrier", "Shipping carrier", "No carrier preference");
		} else {
			reporter.failureReport("Verify Shipping Carrier", "Choosen carrier msg is Not Present", "");

		}
	}
	/**
	 * Method is to fill the card details in the payment info section
	 * @param PONumber
	 * @throws Throwable
	 */
	public void selectPaymentInfoMethodCreditCard(String PONumber,String PORealeseNumber) throws Throwable{
			  if(isElementPresent(OrderObj.PO_NUMBER,"PO Number")){
			  type(OrderObj.PO_NUMBER, PONumber, "PO number");
			  }
			  if(isElementPresent(OrderObj.PO_REALESE_NUMBER,"PO Realese Number")){
				  typeText(OrderObj.PO_REALESE_NUMBER, PORealeseNumber, "PO number");
			  }
		}
	
	public void clenderApproval(String MONTH,String YEAR)throws Throwable{
		click(CALENDER_IMG,"Calender for Start Date");
		String Month=getText(MONTH_CALENDER,"Pervious MONTH of Calaneder");
		System.out.println(Month);
		String Year=getText(YEAR_CALENDER,"Pervious YEAR of Calaneder");
		System.out.println(Year);
		if((MONTH.equals(Month))&&(YEAR.equals(Year))) {
			click(DATE,"1 JAN 2019");
			reporter.SuccessReport("Calander Date", "1/1/2019 was found and Selected", "Date 1/1/2019");
		}
		else if(!(MONTH.equals(Month))&&(YEAR.equals(Year))){
			Thread.sleep(3000);
			for(int i=0;i<=8;i++){
			    click(PREVBUTTON_CLAENDER,"Previus Button");
			    if(i==8) {
			    	break;
			    }
			}
			click(DATE,"1 January 2019");
			reporter.SuccessReport("Calander Date", "1/1/2019 was found and Selected", "Date 1/1/2019");
		}
		else if(((MONTH.equals(Month))&&!(YEAR.equals(Year)))) {
			clickUntil(PREVBUTTON_CLAENDER,YEARWITHTEXT,"Previous Button in Calander");
			click(DATE,"1 January 2019");
			reporter.SuccessReport("Calander Date", "1/1/2019 was found and Selected", "Date 1/1/2019");
			
		}else {
			reporter.failureReport("Calander Date", "Unable to choose the date in calander", "");
		}
	}
    public void clickSearchButtonInApprovalmanagementPage()	throws Throwable{
	click(SEARCH,"SEARCH BUTTON");
}
    public void clickExpandSearchButtonInApprovalmanagementPage()	throws Throwable{
    	click(EXPANDSEARCH_APPROVALMANAGEMENT,"Expand SEARCH BUTTON in Approval managemnet Page");
    }//STOREDCARDS_DD
    
	public void selectedStoredCards(String StoredCard) throws Throwable {
			selectByVisibleText(STOREDCARDS_DD, StoredCard, "Select::"+StoredCard+"");
	}
	public void verifyCarrier(String DefCarrier) throws Throwable {
		if (isVisibleOnly(DEFUALTCARRIER_INPUT, "carrier Option")) {
			typeText(DEFUALTCARRIER_INPUT,DefCarrier,"Defualt carrier Account Textfield");
				reporter.SuccessReport("Defualt carrier", "Default Carrier Account Field is Exists and Entered", DefCarrier);
			} else {
				reporter.failureReport("Defualt carrier", "Billmy Carrier Option is Not Present",
						"");
			}
	}
	public void clickCreditCard()throws Throwable {
		if(isElementPresent(OrderObj.PAYMENT_METHOD_DD, "payment DD")){
			click(OrderObj.PAYMENT_METHOD_DD, "payment method drop down");
			click(OrderObj.PAYMENT_METHOD_SELECTION, "payment method selection::Credit Card");
		}
	}
	public void verifyCardNum() throws Throwable {
		if (isVisibleOnly(STOREDCARDNUM, "Card Number")) {
			String CardNum=getText(STOREDCARDNUM,"Card Number").trim();
				reporter.SuccessReport("Credit Card Info::", "Stored Card Number Exisits", CardNum);
			} else {
				reporter.failureReport("Credit Card Info::", "Stored Card Number Does not Exists",
						"");
			}
	}
		public void verifyCardName() throws Throwable {
			if (isVisibleOnly(STOREDCARDNAME, "Card Name")) {
				String CardNam=getText(STOREDCARDNAME,"Card Number").trim();
					reporter.SuccessReport("Credit Card Info::", "Stored Card Name Exists", CardNam);
				} else {
					reporter.failureReport("Credit Card Info::", "Stored Card Name Does not Exists",
							"");
				}
	}
		public void verifyCardDate() throws Throwable {
			if (isVisibleOnly(EXPIRATIONDATE, "Card Name")) {
				String Carddate=getText(EXPIRATIONDATE,"Card Number").trim();
					reporter.SuccessReport("Credit Card Info::", "Stored Card Expiry Date Exists", Carddate);
				} else {
					reporter.failureReport("Credit Card Info::", "Stored Card Expiry Does not Exists",
							"");
				}
	}
		public void verifyThiredPartyCarrierMsg() throws Throwable {
			if (isVisibleOnly(THIRDPARTYCARRIER, "ThirdParty carried Msg")) {
				String MSG=getText(THIRDPARTYCARRIER,"ThirdParty carried Msg").trim();
					reporter.SuccessReport("Verify ThirdParty carried Msg::", "ThirdParty carried Msg exists", MSG);
				} else {
					reporter.failureReport("ThirdParty carried Msg::", "ThirdParty carried Msg Does not Exists",
							"");
				}
	}
		public void cmtLogout() throws Throwable {
			if (isVisibleOnly(LOGOUT, "Logout")) {
					click(LOGOUT,"Logout CMT");
	}
		}
		public void deleteCart(String cartname)throws Throwable {
			if(isVisibleOnly(CartObj.deleteButton(cartname),"Delete Cart")) {
			waitForVisibilityOfElement(CartObj.deleteButton(cartname),"SavedCart::"+cartname+"");
			click((CartObj.deleteButton(cartname)), "Delete cart::"+cartname+"");
			waitForVisibilityOfElement(CartObj.YES_BUTTON_INCONFORMATION_POP_UP, "Yes in conformation pop up");
			click(CartObj.YES_BUTTON_INCONFORMATION_POP_UP, "Yes in conformation pop up");
			waitForVisibilityOfElement(CartObj.DELETE_CART_MEASSAGE, "ACCOUNT TOOLS");
			if (isElementPresent(CartObj.DELETE_CART_MEASSAGE, "Delete cart sucess meassage")) {
				reporter.SuccessReport("Delete cart meassage ", "Cart is sucessfully deleted", "");
			} else {
				reporter.failureReport("Delete cart meassage ", "Cart is sucessfully not deleted", "", driver);

			}
			}else {
				reporter.SuccessReport("verify saved carts and delete", "saved carts already deleted", "");
			}
		}
			public void verifyPartNumInProductDetailPage(String Partnum) throws Throwable {
				if (isVisibleOnly(partNum(Partnum), "PartNum")) {
						reporter.SuccessReport("verify PartNum In ProductDetail Page::", "PartNum  Exists", Partnum);
					} else {
						reporter.failureReport("verify PartNum In ProductDetail Page::", "PartNum Does not Exists",
								"");
					}
		}
			/**
			 * This method is to add products By Quick shop
			 * 
			 * @throws Throwable
			 */
			public void AdditemsbyQuickshopAndUpdateQuantity(String searchItem,String Quantity) throws Throwable {
				waitForVisibilityOfElement(CartObj.QUICK_SHOP_ITEM_FIELD, "QUICK SHOP ITEM FIELD");
				typeText(CartObj.QUICK_SHOP_ITEM_FIELD, searchItem, "QUICK SHOP ITEM FIELD");
				Thread.sleep(3000);
				clearData(CartObj.QUICKSHOP_ITEM_QUANTITYFIELD);
				typeText(CartObj.QUICKSHOP_ITEM_QUANTITYFIELD, Quantity, "Upadte Quantity::"+Quantity+"");
				Thread.sleep(3000);
				click(CartObj.ADD_BUTTON_IN_QUICK_SHOP, "ADD BUTTON IN QUICK SHOP");
				Thread.sleep(3000);
				waitForVisibilityOfElement(CartObj.CART_LABEL_ON_CART_PAGE, "Item added to cart");
				scrollUp();
				Thread.sleep(3000);
			}
			public void selectFedExCarrier() throws Throwable {
				waitForVisibilityOfElement(CARRIERS_DROPDOWN, "carriers Dropdown is visible");
				if (isElementPresent(CARRIERS_DROPDOWN, "Carrier Dropdown is visible")) {
					click(CARRIERS_DROPDOWN, "CARRIER Dropdown is clicked");
					click(FEDX_CARRIER, "FedEx Carrier is Selected");
					reporter.SuccessReport("Carrier::", "FedEx Carrier is Selected", "FedEx");
				} else {
					reporter.failureReport("Carriers Dropdown is Not Presnt", "Carriers Dropdown is Not Present", "");
				}
				click(GROUND_CARRIER,"Shipping Carrier::Ground");
	}
			/**
			 * This method is to update quantity
			 * 
			 * @throws Throwable
			 */
			public void VerifyQuantity() throws Throwable {
				Thread.sleep(3000);
				waitForVisibilityOfElement(ProductDetailObj.NUMBERPICKER, "Number Picker");
				if (isVisibleOnly(ProductDetailObj.NUMBERPICKER, "Quantity")) {
					reporter.SuccessReport("Quantity", "Quantity Exists", "Quantitiy::1");

				} else {
					reporter.failureReport("Quantity", "Quantity Not Exists", "");
				}
			}

			/**
			 * Method is used to get the previous year date to current date
			 * 
			 * @throws Throwable
			 */
			public String currentDate() {
				LocalDate today = LocalDate.now();
				String newDate = today.format(DateTimeFormatter.ofPattern("d-MMMM-uuuu"));
				String date[]=newDate.split("-");
				System.out.println("newDate" + date[0]);
				return date[0];

			}

			
			public void clickCalander() throws Throwable {
          click(CALANDER,"Calaender in ADDitional Info");
			}
			public void verifyCopiedText()throws Throwable{
				if(isVisibleOnly(WG_LNL_TEXT,"WG_LNL_TEXT Copied")) {
					reporter.SuccessReport("Verify Copied Text", "WG_LNL_TEXT Copied", "Smart Tracker WG_LNL_Text::QTP1");
				}else {
					reporter.SuccessReport("Verify Copied Text", "WG_LNL_TEXT Not Copied", "WG_LNL_Text::QTP1");

				}
			}
			public void verifyNewAddress()throws Throwable{
				if(isVisibleOnly(New_address,"New address")) {
					reporter.SuccessReport("Verify New Address", "Address changed to Tempe", "910 W CARVER RD TEMPE, AZ 85284-5265 US");
				}else {
					reporter.SuccessReport("Verify New Addresst", "Address not Changed", "");

				}
			}
			public void SaveasTemplete(String Tamplate)throws Throwable{
				click(SAVE_AS_TEMPATE,"Save Order Templete");
				typeText(SAVE_AS_TEMPATE_INPUT,Tamplate,"Save Order Tamplate as::"+Tamplate+"");
				click(CLEAR_CHECKBOX,"Clear My Cart After Save::OFF");
				click(CartObj.SAVE_BUTTON, "Save button");
				String Msg=getText(SUCCES_MSG, "Succes Message");
				System.out.println(Msg);
				click(CONTINUE_BTN,"Continue Button");
			}
			
			
			public void savecartContinueToCheckout(String cartName)throws Throwable{
			// SAVEDCART
			waitForVisibilityOfElement(SAVEDCART(cartName), "SAVED CART::" + cartName + "");
			click(ADDTOCART_SAVEDCARTS(cartName), "Add to cart");
			// ADDEDTOCART_POPUP
			waitForVisibilityOfElement(ADDEDTOCART_POPUP, "ADDED TO CART");
			click(CHECKOUT_SAVEDCARTS, "Continue To Checkout");
			waitForVisibilityOfElement(CartObj.CART_LABEL_ON_CART_PAGE, "Cart Page");

		}
     public void clickonTodayDate(String date)throws Throwable{
			click(Date(date),"Today::"+date+"");
			}
		public void clickExpand()throws Throwable{
			clickUntil(EXPAND_LNL,WG_LNL_TEXT,"Line Level Section");
		}
		/**
		 * This method is to add products By Quick shop
		 * 
		 * @throws Throwable
		 */
		public void clickstoredAddressandVerify(String Text,String storedaddress) throws Throwable {
			click(STOREDADDRESS_LINK, "Link::STORED ADDRESS");
			if (isVisibleOnly(STOREDADDRESS(storedaddress), "Stored Address is Visible")) {
				reporter.SuccessReport("Verify default stored address", "Defualt Stored Address is displayed ",
						storedaddress);
			} else {
				reporter.failureReport("Verify default stored address", "Defualt Stored Address is Not displayed",
						"Defualt Stored Address is not displayed");

			}
			click(SEARCH_FIELD,"Link:SEARCH AVAILABLE ADDRESSES");
			type(SEARCH_FIELD,Text,"Search Field");
			click(search_Button,"Search Button");
			Thread.sleep(3000);
			if(isVisibleOnly(RADIOBUTTON,"Search Result")) {
			if (driver.findElement(RADIOBUTTON).isSelected()) {
				reporter.SuccessReport("Stored address is selected", "Stored Address is visible in Results and Radio Button is selected", "Search By Account Type"+storedaddress);
			} else {
				reporter.SuccessReport("Stored address is Not selected", "Stored address is Not selected", "");
			}
			}else {
				//do nothing
			}
			click(CANCELBUTTON_STOREDADDRESS, "Cancle Button on search shipping address page");

		}
		
		public void clickEdit()throws Throwable{
			click(EDIT_LINK,"Edit Link Shipping Address");
		}
		/**
		 * This method is to add products By Quick shop
		 * 
		 * @throws Throwable
		 */
		public void clickstoredAddressandCancle(String Text) throws Throwable {
			click(STOREDADDRESS_LINK, "Link::STORED ADDRESS");
			click(SEARCH_FIELD,"Link:SEARCH AVAILABLE ADDRESSES");
			type(SEARCH_FIELD,Text,"Search Field");
			click(search_Button,"Search Button");
			Thread.sleep(3000);
			click(CANCELBUTTON_STOREDADDRESS, "Cancle Button on search shipping address page");
		}
		public void clickStockOnly()throws Throwable{
			click(STOCKONLY_SEARCHRESULTS,"Stock Only");
		}
		public void selectCarrier(String carrier) throws Throwable {
			clickUntil(OrderObj.SELECTARRIER,OrderObj.verifyCarrier(carrier), "carrier Drop down");
			if (isElementPresent(OrderObj.verifyCarrier(carrier), "shipping carrier in Dropdown"+carrier)) {
				click(OrderObj.verifyCarrier(carrier), "From Carrier Drop down"+carrier);
			}
			
		}
		public void shippingOptionsCarrierSelection() throws Throwable{
			click(CONTINUE_BTN, "Continue button of Shipping Options");
			if(isElementPresent(OrderObj.SHIPPING_CARRIER_REQUIRED_MSG, "A shipping carrier is required message")){
				 click(OrderObj.CARRIER_PRICE_RADIO_BTN, "carrier price - days");
				 click(CONTINUE_BTN, "Continue button of Shipping Options");
			 }else{
				 
				 //do nothing
			 }
		}
		
		public void verifyProcurmentOptioninAllowedOptions() throws Throwable {
			if(isVisibleOnly(PROCUREMENTCARD_OPTION,"Procurement Card Option")) {
			reporter.SuccessReport("Verify Procurement Card is Selected in Allowed Options in Payment Options in the Checkout Settings Tab on Manage Web groups: Create User Page", "Procurement Card in Allowed Options in Payment Options on Checkout Settings Tab Exists", "Procurement Card");
			}else {
				reporter.failureReport("Verify Payment Option", "Payment Option is Not in Allowed Options", "Procurement Card");
			}
		}
		public void verifyOptioninAllowedOptions(String Option) throws Throwable {
			if(isVisibleOnly(OPTION(Option),"Procurement Card Option")) {
			reporter.SuccessReport("Verify "+Option+" is Selected in Allowed Options in Payment Options in the Checkout Settings Tab on Manage Web groups: Create User Page", ""+Option+"  in Allowed Options in Payment Options on Checkout Settings Tab Exists", Option);
			}else {
				reporter.failureReport("Verify Payment Option", "Payment Option is Not in Allowed Options", "");
			}
		}
		public void verifyNoDefaultAddressBillingAddress() throws Throwable {
			if (isVisibleOnly(DEFUALT_ADDRESS, "No Defualt Address")) {
				reporter.SuccessReport("verifyNo Default Billing Addresses Linked to Account# in the Checkout Settings Tab on Manage Web groups: Create User Page::",
						"No Default Billing Addresses Linked to Account# in Checkout Settings Tab", "");	
			} else {
				reporter.failureReport("verify Defualt Address::",
						"Unable to Verify No Defualt Address Adresses", "");
			}
		}
		
		public void VerifySoldtoAddress(String Company) throws Throwable {
			Thread.sleep(4000);
			if (isVisibleOnly(CREATEDADDRES(Company), "Sold-To Shipping Address")) {
				Thread.sleep(4000);
				getText(SHIPPING_ADDRES,"Shiping address").trim();
			}
		}
		public void selectCarrierandGrounOption(String carrier) throws Throwable {
			clickUntil(OrderObj.SELECT_CARRIER_DD,OrderObj.verifyCarrier(carrier), "carrier Drop down");
			if (isElementPresent(OrderObj.verifyCarrier(carrier), "shipping carrier in Dropdown"+carrier)) {
				click(OrderObj.verifyCarrier(carrier), "From Carrier Drop down"+carrier);
			}
			if(driver.findElement(GROUND_CAREER).isSelected()) {
				reporter.SuccessReport("Verify "+carrier+" is Selected" ,"Select a Carrier Option Exist and Verified", carrier+" Ground - USD $30.46");	
			}else {
				click(GROUND_CAREER,"Ground- USD $30.46Option Exists and Selected");
			}
			click(CONTINUE_BTN, "Continue button of Shipping Options");
		}
}

