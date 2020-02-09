package com.insight.Lib;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.WebElement;

import com.insight.ObjRepo.CanadaObj;
import com.insight.ObjRepo.CartObj;
import com.insight.ObjRepo.CommonObj;
import com.insight.ObjRepo.InvoiceHistoryObj;
import com.insight.ObjRepo.OrderObj;
import com.insight.ObjRepo.ShipBillPayObj;
import com.insight.ObjRepo.productsDisplayInfoObj;
import com.insight.accelerators.ActionEngine;
import com.thoughtworks.selenium.webdriven.commands.GetText;

import static com.insight.ObjRepo.CartObj.lblCartLebel;

public class CartLib extends ActionEngine {

	CommonLib commonLib = new CommonLib();
	CMTLib cmtLib = new CMTLib();
	OrderObj orderObj = new OrderObj();
	ShipBillPayLib shipbLib = new ShipBillPayLib();
	InvoiceHistoryLib ivhLib=new InvoiceHistoryLib();
	CanadaLib canadaLib=new CanadaLib();
	LineLevelInfoLib lnlLib=new LineLevelInfoLib();
	String openMarketPrice;
	

	/**
	 * PURPOSE: This method is to verify Quick Shop With Valid Single PartNumber in
	 * shopping cart page.
	 * 
	 * @param searchItem
	 * @throws Throwable
	 * @customization author : CIGNITI
	 */
	public void increaseNumberOfDisplayedProduct() throws Throwable {
		waitForVisibilityOfElement(CartObj.NUMBER_PICKER_IN_PRODUCT_DISPLAY, "NUMBER PICKER IN PRODUCT DISPLAY");
		click(CartObj.NUMBER_PICKER_IN_PRODUCT_DISPLAY, "NUMBER PICKER IN PRODUCT DISPLAY");
	}

	/**
	 * PURPOSE: This method is to verify Quick Shop With Valid Single PartNumber in
	 * shopping cart page.
	 * 
	 * @param searchItem
	 * @throws Throwable
	 * @customization author : CIGNITI
	 */
	public void clickOnPrint() throws Throwable {
		waitForVisibilityOfElement(CartObj.PRINT, "PRINT LINK");
		click(CartObj.PRINT, "PRINT LINK");
	}

	/**
	 * This method is to click on More available prices
	 * 
	 * @param
	 * @throws Throwable
	 * @author : CIGNITI
	 */
	public void clickMorePricesAvilable(int index) throws Throwable {
		isElementPresent(CartObj.moreAvilablePrices(index), "More AVilable Prices");
		click(CartObj.moreAvilablePrices(index), "More AVilable Prices");
	}

	/**
	 * This method is to click on More available prices in product info
	 * 
	 * @param
	 * @throws Throwable
	 * @author : CIGNITI
	 */
	public void clickMorePricesAvilableInProductInfo() throws Throwable {
		isElementPresent(CartObj.MORE_AVAILABLE_PRICE_IN_PRODUCT_INFO, "More Available Prices");
		click(CartObj.MORE_AVAILABLE_PRICE_IN_PRODUCT_INFO, "More Available Prices");
	}

	/**
	 * This method is to verify deafult contarct search results page
	 * 
	 * @param
	 * @throws Throwable
	 * @author : CIGNITI
	 */
	public void verifyDefaultContract() throws Throwable {
		if (isElementPresent(CartObj.DEFAULT_CONTARCT, "Default contarct")) {
			reporter.SuccessReport("Verify Default Contract in the Search Results Page",
					"Default Contract: US COMMUNITIES IT PRODUCTS & SERVICES", "");
		} else {
			reporter.failureReport("Verify Default Contract in the Search Results Page",
					"Default Contract is not: US COMMUNITIES IT PRODUCTS & SERVICES", "", driver);
		}
	}
	
	public void verifyDefaultContractinProductDisplay() throws Throwable {
		if (isElementPresent(CartObj.DEFAULT_CONTRACT_PRODUCT_DISPLAY, "Default contarct")) {
			reporter.SuccessReport("Verify Default Contract in the Search Results Page",
					"Default Contract: US COMMUNITIES IT PRODUCTS & SERVICES", "");
		} else {
			reporter.failureReport("Verify Default Contract in the Search Results Page",
					"Default Contract is not: US COMMUNITIES IT PRODUCTS & SERVICES", "", driver);
		}
	}

	/**
	 * This method is to verify default contarct in cart page
	 * 
	 * @param
	 * @throws Throwable
	 * @author : CIGNITI
	 */
	public void verifyDefaultContractInCart() throws Throwable {
		if (isElementPresent(CartObj.DEAFULT_CONTARCT_IN_CART, "Default contarct")) {
			reporter.SuccessReport("Verify Default Contract in the cart Page",
					"Default Contract: US COMMUNITIES IT PRODUCTS & SERVICES", "");
		} else {
			reporter.failureReport("Verify Default Contract in the cart Page",
					"Default Contract is not: US COMMUNITIES IT PRODUCTS & SERVICES", "", driver);
		}
	}

	/**
	 * This method is to select desired price
	 * 
	 * @param
	 * @throws Throwable
	 * @author : CIGNITI
	 */
	public void clickOnOpenMarketPrice() throws Throwable {
		isElementPresent(productsDisplayInfoObj.OPEN_MARKET, "open market price");
		click(productsDisplayInfoObj.OPEN_MARKET, "open market price");
	}

	/**
	 * This method is to select desired price
	 * 
	 * @param
	 * @throws Throwable
	 * @author : CIGNITI
	 */
	public void clickOnUSCommuditiesPrice() throws Throwable {
		isElementPresent(CartObj.US_COMMIDITIES, "Us commodities price");
		click(CartObj.US_COMMIDITIES, "Us commodities price");
	}

	/**
	 * This method is to click Add to cart in avilable prices list
	 * 
	 * @param
	 * @throws Throwable
	 * @author : CIGNITI
	 */
	public void clickOnAddToCartInAllContractPrices() throws Throwable {
		isElementPresent(CartObj.ADD_TO_CART_IN_ALL_CONTRACT_PRICES, "Add to cart");
		click(CartObj.ADD_TO_CART_IN_ALL_CONTRACT_PRICES, "Add to cart");
		waitForVisibilityOfElement(CartObj.ADD_TO_CART_SUCCESS_MESSAGE, "ADD TO CART SUCCESS MESSAGE");
		isElementPresent(CartObj.ADD_TO_CART_SUCCESS_MESSAGE, "ADD TO CART SUCCESS MESSAGE", true);
	}
	public void verifyBreadCrum(String text) throws Throwable {
		if(isElementPresent(CartObj.breadCrum(text), "Bread crum")) {
			reporter.SuccessReport("Verify bread crum in search results page", "Breadcrum verification", text, driver);
		}
		else {
			reporter.failureReport("Verify bread crum in search results page", "Breadcrum verification ietm not present", "", driver);
		}
	}

	/**
	 * This method is to click on save cart contents and save the cart
	 * 
	 * @param
	 * @throws Throwable
	 * @author : CIGNITI
	 */
	public void clickOnSaveCartContentAndSaveCart(String cartName) throws Throwable {
		Thread.sleep(10000);
		commonLib.spinnerImage();
		scrollToBottomWithCordinate("500");
		isElementPresent(CartObj.SAVE_CART_CONTENTS, "Save cart contents");
		click(CartObj.SAVE_CART_CONTENTS, "Save cart contents");
		waitForVisibilityOfElement(CartObj.SAVE_CART_CONTENTS_POPUP, "SAVE CART CONTENTS POPUP");

		Thread.sleep(5000);
		clearData(CartObj.SAVE_CART_INPUT_FIELD);
		
		type(CartObj.SAVE_CART_INPUT_FIELD, cartName, "cart name");
		if(isCheckBoxSelected(CartObj.CLEAR_MY_DRAFT_SAVED)) {
			reporter.SuccessReport("Clear my cart after save Checkbox", "Clear my cart after save Checkbox exist", "Clear my cart after save: on");
		}
		else {
			click(CartObj.CLEAR_MY_DRAFT_SAVED,"Clear my drafts check box");
			reporter.SuccessReport("Clear my cart after save Checkbox", "Clear my cart after save Checkbox exist", "Clear my cart after save: on");
		}
		click(CartObj.SAVE_BUTTON, "Save button");
		waitForVisibilityOfElement(CartObj.CART_SAVED_SUCESS_MESSAGE, "cart save sucess message");
		if (isElementPresent(CartObj.CART_SAVED_SUCESS_MESSAGE, "Save cart sucess message")) {
			reporter.SuccessReport("Save cart sucess message ",
					"Your cart has been successfully saved message is displayed", "");
		} else {
			reporter.failureReport("Save cart error message ",
					"Your cart has been successfully saved message is not displayed", "", driver);

		}
		click(CartObj.CONTINUE, "Continue button");
	}

	public void clickOnSaveCartContentAndSaveCartAndClearCartOff(String cartName) throws Throwable {
		Thread.sleep(10000);
		commonLib.spinnerImage();
		scrollToBottomWithCordinate("500");
		isElementPresent(CartObj.SAVE_CART_CONTENTS, "Save cart contents");
		click(CartObj.SAVE_CART_CONTENTS, "Save cart contents");
		waitForVisibilityOfElement(CartObj.SAVE_CART_CONTENTS_POPUP, "SAVE CART CONTENTS POPUP");

		Thread.sleep(5000);
		clearData(CartObj.SAVE_CART_INPUT_FIELD);
		
		type(CartObj.SAVE_CART_INPUT_FIELD, cartName, "cart name");
		if(!isCheckBoxSelected(CartObj.CLEAR_MY_DRAFT_SAVED)) {
			reporter.SuccessReport("Clear my cart after save Checkbox", "Clear my cart after save Checkbox exist", "Clear my cart after save: off");
		}
		else {
			click(CartObj.CLEAR_MY_DRAFT_SAVED,"Clear my drafts check box");
			reporter.SuccessReport("Clear my cart after save Checkbox", "Clear my cart after save Checkbox exist", "Clear my cart after save: off");
		}
		click(CartObj.SAVE_BUTTON, "Save button");
		waitForVisibilityOfElement(CartObj.CART_SAVED_SUCESS_MESSAGE, "cart save sucess message");
		if (isElementPresent(CartObj.CART_SAVED_SUCESS_MESSAGE, "Save cart sucess message")) {
			reporter.SuccessReport("Save cart sucess message ",
					"Your cart has been successfully saved message is displayed", "");
		} else {
			reporter.failureReport("Save cart error message ",
					"Your cart has been successfully saved message is not displayed", "", driver);

		}
		click(CartObj.CONTINUE, "Continue button");
	}

	/*
	 * PURPOSE OF METHOD : verify save cart as quote is present
	 * 
	 * @author : CIGNITI
	 */
	public void verifySaveCartAsQuoteIsPresent() throws Throwable {
		waitForVisibilityOfElement(CartObj.SAVE_AS_QUOTE, "cart save as quote");
		if (isElementPresent(CartObj.SAVE_AS_QUOTE, "cart save as quote")) {
			reporter.SuccessReport("cart save as quote ", "cart save as quote is present ", "");
		} else {
			reporter.failureReport("cart save as quote ", "cart save as quote is not present", "", driver);
		}
	}

	/*
	 * PURPOSE OF METHOD : click on quick checkout and verify shipping adresses and
	 * contact details
	 * 
	 * @author : CIGNITI
	 */
	public void clickQuickCheckOutandVerify(String shippingCompany, String shippingCarrier, String NotificationMail,
			String BillingAddresses, String PaymentType) throws Throwable {
		waitForVisibilityOfElement(CartObj.QUICK_CHECKOUT, "quick check out");
		click(CartObj.QUICK_CHECKOUT, "quick check out");
		Thread.sleep(10000);
		waitForVisibilityOfElement(CartObj.PLACE_ORDER_PAGE_TEXT, "place order page");
		if(isElementPresent(CartObj.PLACE_ORDER_PAGE_TEXT, "place order page")) {
			reporter.SuccessReport("Place order page", "place order page is loaded", "page Details:Place order");
		}
		else {
			reporter.SuccessReport("Place order page", "place order page is not loaded", "");
		}
		
		if (isElementPresent(CartObj.validationsInPlaceOrderPage(shippingCompany), "Shipping Company")) {
			reporter.SuccessReport("Shipping Company", "Shipping company is present", "Shipping Address - "+shippingCompany);

		} else {
			reporter.failureReport("Shipping Company", "Shipping company is not present", shippingCompany, driver);
		}
		if (isElementPresent(CartObj.validationsInPlaceOrderPage(shippingCarrier), "Shipping Carrier")) {
			reporter.SuccessReport("Shipping Carrier", "Shipping Carrier is present", "Shipping Options -" +shippingCarrier);

		} else {
			reporter.failureReport("Shipping Carrier", "Shipping Carrier is not present", shippingCarrier, driver);
		}

		if (isElementPresent(CartObj.validationsInPlaceOrderPage(NotificationMail), "Notification Mail")) {
			reporter.SuccessReport("Notification Mail", "Notification Mail is present", "Notification email(s):" +NotificationMail);

		} else {
			reporter.failureReport("Notification Mail", "Notification Mail is not present", NotificationMail, driver);
		}

		if (isElementPresent(CartObj.validationsInPlaceOrderPage(BillingAddresses), "Billing Addresses")) {
			reporter.SuccessReport("Billing Addresses", "Billing Addresses is present", "Billing Address - "+BillingAddresses);

		} else {
			reporter.failureReport("NBilling Addresses", "Billing Addresses is not present", BillingAddresses, driver);
		}

		if (isElementPresent(CartObj.validationsInPlaceOrderPage(PaymentType), "Payment Type")) {
			reporter.SuccessReport("Payment Type", "Payment Type is present", "Billing Address - "+PaymentType);

		} else {
			reporter.failureReport("Payment Type", "Payment Type" + PaymentType + "is not present", "", driver);
		}

		click(CartObj.RETURN_TO_CART, "Return to cart");

	}

	/*
	 * PURPOSE OF METHOD : click on favorite shipping address and select an adresses
	 * 
	 * @author : CIGNITI
	 */
	public void clickOnFavouriteShippingAddressesandSelectanAddresses(String shippingAddresses) throws Throwable {
		waitForVisibilityOfElement(CartObj.FAVOURITE_SHIPPING_ADDRESSES_DROPDOWN, "Favourite shipping addresses");
		//click(CartObj.FAVOURITE_SHIPPING_ADDRESSES_DROPDOWN, "Favourite shipping addresses");
		clickUntil(CartObj.FAVOURITE_SHIPPING_ADDRESSES_DROPDOWN,CartObj.selectFavouriteShippingAdresses(shippingAddresses), "Favourite shipping addresses");
		click(CartObj.selectFavouriteShippingAdresses(shippingAddresses), "Shipping Adresses");
		Thread.sleep(5000);
	}

	/*
	 * PURPOSE OF METHOD : click on quick checkout
	 * 
	 * @author : CIGNITI
	 */
	public void clickOnQuickCheckout() throws Throwable {
		waitForVisibilityOfElement(CartObj.QUICK_CHECKOUT, "quick check out");
		if(isVisible(CartObj.QUICK_CHECKOUT, "quick check out")) {
			click(CartObj.QUICK_CHECKOUT, "quick check out");

		}
		else {
			reporter.failureReport("Quick checkout in cart page", "Quick check out button is not visible", "", driver);
		}
	}

	/*
	 * PURPOSE OF METHOD : validate Shipping address in quick ckeck out page
	 * 
	 * @author : CIGNITI
	 */
	public void validateShippingAddressesInQickCheckOut(String shippingCompany) throws Throwable {
		if (isElementPresent(CartObj.PLACE_ORDER_PAGE_TEXT, "place order page")) {
			reporter.SuccessReport("Place order ", "Place order page is opened ", "");
		} else {
			reporter.failureReport("Place order ", "Place order page is not opened ", "", driver);
		}
		if (isElementPresent(CartObj.validationsInPlaceOrderPage(shippingCompany), "Shipping Company")) {
			reporter.SuccessReport("Shipping Company", "Shipping company is present", "Shipping Address - "+shippingCompany);

		} else {
			reporter.failureReport("Shipping Company", "Shipping company" + shippingCompany + "is not present",
					shippingCompany, driver);
		}
	}

	/*
	 * PURPOSE OF METHOD : verify save cart as quote is present
	 * 
	 * @author : CIGNITI
	 */
	public void verifySelectRwquestorGroupDropdownIsPresent() throws Throwable {
		waitForVisibilityOfElement(CartObj.SELECT_REQUEST_GROUP, "SELECT REQUEST GROUP DROPDOWN");
		if (isElementPresent(CartObj.SELECT_REQUEST_GROUP, "SELECT REQUEST GROUP DROPDOWN")) {
			reporter.SuccessReport("SELECT REQUEST GROUP DROPDOWN ", "SELECT REQUEST GROUP DROPDOWN IS PRESENT", "");
		} else {
			reporter.failureReport("SELECT REQUEST GROUP DROPDOWN ", "SELECT REQUEST GROUP DROPDOWN IS NOT PRESENT", "",
					driver);
		}
	}

	/*
	 * PURPOSE OF METHOD : open saved cart from account tools
	 * 
	 * @author : CIGNITI
	 */
	public void openSavedCartFromTools(String cartName) throws Throwable {
		//waitForVisibilityOfElement(CartObj.ACCOUNT_TOOLS, "ACCOUNT TOOLS");
		scrollUp();
		Thread.sleep(10000);
		if(isElementPresent(InvoiceHistoryLib.COSE_ACCOUNT_TOOLS, "close account tools")) {
			click(InvoiceHistoryLib.COSE_ACCOUNT_TOOLS, "close account tools");
		}
		
		click(CartObj.ACCOUNT_TOOLS, "ACCOUNT TOOLS");
		click(CartObj.TOOLS, "TOOLS");
		
		click(CartObj.SAVEDCART, "SAVED CART");
		if(isElementPresent(CartObj.SAVED_CART_CONTENTS_HEADER, "Saved cart or orfer templates")) {
			reporter.SuccessReport("Saved carts/ ORDER TEMPLATES", "page is saved carts", "Saved carts/ ORDER TEMPLATES");
		}
		else {
			reporter.failureReport("Saved carts/ ORDER TEMPLATES", "page is not saved carts","", driver);
		}
		//isElementPresent(CartObj.SAVED_CART_TEXT, "Saved cart");
		click(CartObj.loadCart(cartName), "Load cart");
		if (isElementPresent(CartObj.CURRIENCES, "cart is loaded")) {
			reporter.SuccessReport("Click on load cart ", "Saved cart exists and selected", "");
		} else {
			reporter.failureReport("Click on load cart ", "Saved cart does not exist", "", driver);

		}

	}

	/**
	 * This method is to click add to cart in saved cart
	 * 
	 * @throws Throwable
	 */
	public void addToCartInSavedCart(String cartName) throws Throwable {
		click(CartObj.addToCartInSavedCart(cartName), "Add to cart in saved cart");
		waitForVisibilityOfElement(CartObj.CONTINUE_TO_CHECKOUT, "Continue to check out");
		if(isVisible(CartObj.CONTINUE_TO_CHECKOUT, "Continue to check out")) {
			click(CartObj.CONTINUE_TO_CHECKOUT, "Continue to check out");
		}
		else {
			reporter.failureReport("Continue to checkout button in saved carts/ order templates page", "Continue to checkout button is not visible", "", driver);
		}
		
	}

	/**
	 * This method is to click on continue button on add info
	 * 
	 * @throws Throwable
	 */
	public void clickOnContinueButtonInAddInformtion() throws Throwable {
		if (isElementPresent(OrderObj.ORDER_ITEM_INFO_LABEl, "order and inforamtion page")) {
			reporter.SuccessReport("Verify Line Level/Ship Bill & Pay/Line Level/Place Requisition/Place Order Page", "Order and item information Page loaded", "PageDetails : Order and item information");
			click(OrderObj.CONTINUE_BTN, "Additional information section Continue button");
			Thread.sleep(2000);
		}else {
			reporter.failureReport("Verify Line Level/Ship Bill & Pay/Line Level/Place Requisition/Place Order Page", "Order and item information Page not loaded", "", driver);
		}
	}

	/**
	 * This method is to click on Continue to check out
	 * 
	 * @throws Throwable
	 */
	public void clickCheckoutDefaults() throws Throwable {
		waitForVisibilityOfElement(CartObj.CHECKOUT_DEFAULTS, "Continue to check out");
		click(CartObj.CHECKOUT_DEFAULTS, "Checkout defaults");
	}

	/**
	 * This method is to verify notification email field in account tools is not
	 * present
	 * 
	 * @throws Throwable
	 */
	public void verifyShipmentNotificationInCheckoutDefaultsIsNotPresent() throws Throwable {

		if (isElementPresent(CartObj.SHIPMENT_NOTIFICATION, "ASN field")) {
			reporter.failureReport("Verify ASN Field on  Account Management - Account Tools Page", "ASN Field is Exist",
					"", driver);
		} else {
			reporter.SuccessReport("Verify ASN Field on  Account Management - Account Tools Page",
					"ASN Field does not Exist", "");
		}
	}

	/**
	 * This method is to verify notification email field in account tools is present
	 * 
	 * @throws Throwable
	 */
	public void verifyShipmentNotificationInCheckoutDefaults() throws Throwable {

		if (isElementPresent(CartObj.SHIPMENT_NOTIFICATION, "ASN field")) {
			reporter.SuccessReport("Verify ASN Field on  Account Management - Account Tools Page", "ASN Field is Exist",
					"");
		} else {
			reporter.failureReport("Verify ASN Field on  Account Management - Account Tools Page",
					"ASN Field does not Exist", "", driver);
		}
	}
	public void verifyPreviousShipmentNotificationInCheckoutDefaults(String previousemail) throws Throwable {

		if (isVisibleOnly(CartObj.SHIPMENT_NOTIFICATION, "ASN field")) {
		String Email=getText(CartObj.SHIPMENT_NOTIFICATION,"Shipment Notification");
		if(Email.contains(previousemail)) {
			reporter.SuccessReport("Verify Defualt mail in ASN Field  Account Management - Account Tools Page", "In ASN Field is Defualt Email Exist as Expected",
					Email);
		} else {
			reporter.failureReport("erify Defualt mail in ASN Field   Account Management - Account Tools Page",
					"ASN Field Email does not Exist", "", driver);
		}
		}
	}

	public void enterMailIdToNotificationFieldAndVerifySuccessMessage(String mail) throws Throwable {
		clearData(CartObj.SHIPMENT_NOTIFICATION);
		type(CartObj.SHIPMENT_NOTIFICATION, mail, "ASN field");
		click(CartObj.UPDATE_BUTTON, "Update button");
		Thread.sleep(3000);
		String errorMessage = getText(CartObj.SUCESS_MESSAGE_NOTE, "Success meassage");
		if (isElementPresent(CartObj.SUCESS_MESSAGE_NOTE, "Success meassage")) {

			reporter.SuccessReport("Verify Set Three Emails Separated by Semi-Colons in Shipment Notification Recipients Field in Checkout Defaults",
					"Expected Three Emails Separated by Semi-Colons in Shipment Notification Recipients Field in Checkout Defaults Exists and Value Entered::"+mail,
					errorMessage);
		} else {
			reporter.failureReport("Verify Shipment Notification Recipients Field error message",
					"Shipment Notification Recipients Field Error Message in Checkout Defaults - Account Tools", "",
					driver);
		}

	}

	public void enterMailIdToNotificationFieldAndVerifyErrorMessageNote(String mail) throws Throwable {
		clearData(CartObj.SHIPMENT_NOTIFICATION);
		type(CartObj.SHIPMENT_NOTIFICATION, mail, "ASN field");
		click(CartObj.UPDATE_BUTTON, "Update button");
		String messageNote = getText(CartObj.ERROR_MESSAGE, "Error meassage");
		if (isElementPresent(CartObj.ERROR_MESSAGE, "Error meassage")) {

			reporter.SuccessReport("Verify the Shipment Notification Recipients Field Error Message in Checkout Defaults - Account Tools",
					"Shipment Notification Recipients Field Error Message in Checkout Defaults - Account Tools Exists and Value Returned"
							,
							messageNote);
		} else {
			reporter.failureReport("Verify Shipment Notification Recipients Field error message",
					"Shipment Notification Recipients Field Error Message in Checkout Defaults - Account Tools", "",
					driver);
		}

	}

	/**
	 * This method is to verify notification email field in shiiping checkout
	 * 
	 * @throws Throwable
	 */
	public void verifyShipmentNotificationInCheckoutIsNotPresent() throws Throwable {

		if (isElementPresent(CartObj.NOTIFICATION_EMAIL, "ASN field")) {
			reporter.failureReport("Verify Shipment Notification  Confirm via email Ship Bill Page",
					"Shipment Notification  Confirm via email is Exists", "", driver);
		} else {
			reporter.SuccessReport("Verify Shipment Notification  Confirm via email Ship Bill Page",
					"Shipment Notification  Confirm via email Not Exists as Expected", "");
		}
	}

	public void verifyEmailAsInFormat(String emailToVerify) throws Throwable {
		if(isVisibleOnly(CartObj.verifyEmail(emailToVerify), "Email " + emailToVerify)) {
			String Email1=driver.findElement(CartObj.verifyEmail(emailToVerify)).getAttribute("value");
			reporter.SuccessReport("Verify Shipment Notification Email Format on Ship bill page",
					"Shipment Notification email1 Exists as expected", Email1);	
		}
		else {
			reporter.failureReport("Verify Shipment Notification Email " + emailToVerify + " Format on Ship bill page",
					"Shipment Notification email is not as expected", "", driver);
		}
	}

	/**
	 * This method is to click on Add additional notification email
	 * 
	 * @throws Throwable
	 */
	public void clickAddAdditionalNotificationEmail() throws Throwable {
		click(CartObj.ADD_ADDITIONAL_NOTIFICATION_EMAIL, "Add additional notification email");
	}

	/**
	 * This method is to enter additional notification email-inavlid and verify
	 * error message
	 * 
	 * @throws Throwable
	 */
	public void enterInvalidAddtionalNotificationEmailAndVerifyErrorMessage(String emailToEnter) throws Throwable {
		// clearData(CartObj.ADDITIONAL_NOTIFICATION_EMAIL);
		type(CartObj.ADDITIONAL_NOTIFICATION_EMAIL, emailToEnter, "additional notification email");
		click(CartObj.ADD_ADDITIONAL_NOTIFICATION_EMAIL, "Add additional notification email");
		Thread.sleep(5000);
		if (isVisibleOnly(CartObj.ERROR_MESSAGE_INVALID_EMAIL, "Error message")) {
			reporter.SuccessReport("Verify error message for invalid mail", "Error message", getText(CartObj.ERROR_MESSAGE_INVALID_EMAIL, "Error message"));
		}
		clearData(CartObj.clearNotificationEmail(emailToEnter));
	}

	/**
	 * This method is to enter additional notification email
	 * 
	 * @throws Throwable
	 */
	public void enterValidAddtionalEmail(String emailToEnter) throws Throwable {
		// clearData(CartObj.ADDITIONAL_NOTIFICATION_EMAIL);
		type(CartObj.ADDITIONAL_NOTIFICATION_EMAIL, emailToEnter, "additional notification email");

	}

	/**
	 * This method is to fill the Additional information in the Order and item
	 * information page.
	 * https://loginas-uat1.insight.com/insightweb/editLineLevelInfo
	 * 
	 * @param url
	 * @param rP_HDL_Txt
	 * @throws Throwable
	 */
	public void addAdditionalInformationInCheckOut(String url, String rP_HDL_Txt) throws Throwable {
		verify_url(driver, url);
		if (isElementPresent(OrderObj.ORDER_ITEM_INFO_LABEl, "order and inforamtion page")
				&& isElementPresent(OrderObj.RP_HDL_Txt, "RP_HDL_Txt")) {
			type(OrderObj.RP_HDL_Txt, rP_HDL_Txt, "Smart Tracker name:");
			click(OrderObj.CONTINUE_BTN, "additional information::Continue button");
		}
		else {
			reporter.failureReport("order and inforamtion page", "order and inforamtion page is not loaded", "Header level smart tracker is not entered", driver);
		}
	}

	/**
	 * This method is to fill the add Line Level Information in the Order and item
	 * information page.
	 * 
	 * @param rP_LNL_Txt
	 * @throws Throwable
	 */
	public void addLineLevelInformationInCheckOut(String rP_LNL_Txt) throws Throwable {
		lnlLib.verifyOrderAndItemInfoBreadCrumb();
		if (isElementPresent(OrderObj.LINE_LEVEL_INFO, "Line level information link")) {
			click(OrderObj.LINE_LEVEL_INFO, "Line Level Information");
			if (isElementPresent(OrderObj.SMART_TRACKER_LABEL, "Smart tracker in LL info section")) {
				type(OrderObj.RP_LNL_Txt, rP_LNL_Txt, "Smart Tracker name:");
				click(OrderObj.LLI_CONTINUE_BTN, "Continue button");
			}
		}
	}

	/**
	 * This method is to clear phone field in checkout page
	 * 
	 * @throws Throwable
	 * 
	 */
	public void clearPhoneFieldInCheckOut() throws Throwable {
		if (isElementPresent(CartObj.PHONE_FIELD, "Phone field")) {
			clearData(CartObj.PHONE_FIELD);
			reporter.SuccessReport("Clear Phone number","Existing date was cleared","true");
			click(OrderObj.CONTINUE_BTN, "Continue in shipping addresses section");
			Thread.sleep(2000);
		}
	}

	/**
	 * This method is to fill payment info
	 * 
	 * @throws Throwable
	 * 
	 */

	public void shippingBillPayInCheckOut(String cardNumber, String cardName, String month, String year,
			String PONumber,String PORealeseNumber) throws Throwable {
		click(OrderObj.CONTINUE_BTN, "Continue button of Shipping Options"); // clicking continue in Shipping address
		Thread.sleep(2000);
		click(OrderObj.CONTINUE_BTN, "Continue button of Billing Address");// clicking continue in Shipping options
		Thread.sleep(2000);
		click(OrderObj.PAYMENT_METHOD_DD, "payment method drop down");
		click(OrderObj.PAYMENT_METHOD_SELECTION, "payment method selection");
		type(OrderObj.CARD_NUMBER_TEXTBX, cardNumber, "Card number"); // Entering Card details in payment info
		type(OrderObj.CARD_NAME_TEXTBOX, cardName, "Card name");
		click(OrderObj.EXPIRATION_MONTH, "Expiration month");
		selectByValue(OrderObj.EXPIRATION_MONTH, month, "Expiration month");
		click(OrderObj.EXPIRATION_YEAR, "Expiration year");
		selectByValue(OrderObj.EXPIRATION_YEAR, year, "Expiration year");
		Thread.sleep(2000);
		type(OrderObj.PO_NUMBER, PONumber, "PO number");
		Thread.sleep(2000);
		 if(isElementPresent(OrderObj.PO_REALESE_NUMBER,"PO Realese Number")){
			  typeText(OrderObj.PO_REALESE_NUMBER, PORealeseNumber, "PO number");
		  }
		click(OrderObj.REVIEW_ORDER_BTN, "review order button of payment Info");
		// expectedSummaryTotalAmount=getText(SUMMARY_TOTAL_AMOUNT,
		// "summaryTotalAmount");

	}

	public void verifyNotificationEmailInShippingAdresses(String email) throws Throwable {
		if (isVisibleOnly(CartObj.verifyNotificationEmailInShippingAdresses(email), "Verifying email")) {
			reporter.SuccessReport("Verify the Notification emails on Place Order page", "Notification emails on Place Order page Exists and Value Returned", email);
		}
	}

	/**
	 * This method is to verify RP_HDL_Txt text
	 * 
	 * @throws Throwable
	 * 
	 */
	public void verifyRpHdlTxt(String rpHdlText) throws Throwable {
		if (isElementPresent(CartObj.verifyRpHdlText(rpHdlText), "rpHdl Text")) {
			reporter.SuccessReport("Verify header level smart tracker ", "" + rpHdlText + " is displayed", rpHdlText);
		} else {
			reporter.failureReport("Verify header level smart tracker", "" + rpHdlText + " is not displayed", rpHdlText, driver);

		}
	}

	/**
	 * This method is to verify RP_LNL_Txt text
	 * 
	 * @throws Throwable
	 * 
	 */
	public void verifyRpLnllTxt(String rpLnlText) throws Throwable {
		//JSScroll(CartObj.verifyRpLnlText(rpLnlText), "rpHdl Text");
		scrollToBottomWithCordinate("1250");
		if (isElementPresent(CartObj.verifyRpLnlText(rpLnlText), "rpHdl Text")) {
			reporter.SuccessReport("Verify line level smart tracker ", "" + rpLnlText + " is displayed", rpLnlText);
		} else {
			reporter.failureReport("Verify line level smart tracker", "" + rpLnlText + " is not displayed", rpLnlText, driver);

		}
		scrollUp();
	}

	/**
	 * Method is to select carrier option in the ship bill page
	 * 
	 * @throws Throwable
	 */
	public void shippingOptionsCarrierSelectionInCheckOut(String carrier) throws Throwable {
		click(OrderObj.CONTINUE_BTN, "Continue button of Shipping Options");
		if (isElementPresent(OrderObj.SHIPPING_CARRIER_REQUIRED_MSG, "A shipping carrier is required message")) {
			click(OrderObj.SELECT_CARRIER_DD, "carrier Drop down");
			click(OrderObj.selectCarrier(carrier), "select carrier::"+carrier+"");

		} else {
			// do nothing
		}
	}

	/**
	 * Method is to verify carriers in checkout
	 * 
	 * @throws Throwable
	 */
	public void verifyCarriersInCheckOut(String carrier,String Carriers) throws Throwable {
		if (isElementPresent(OrderObj.SELECT_CARRIER_DD, "carrier Drop down")) {
		String Text=getText(OrderObj.SELECT_CARRIER_DD,"carrier Drop down");
		click(OrderObj.SELECT_CARRIER_DD, "carrier Drop down");
			String carriers[] = carrier.split(",");
			for (i = 0; i < carriers.length; i++) {
				if (isVisibleOnly(OrderObj.selectCarrier(carriers[i]),carrier)) {
				} else {
					reporter.failureReport("verify carrier options::", carriers[i] + " is not present", carriers[i],
							driver);
				}
			}
			reporter.SuccessReport("verify carrier options::", "Selected Options::"+Text+"", "Available carriers"+carrier);
		} 
      else if (isVisibleOnly(OrderObj.SELECT_CARRIER_DD, "carrier Drop down")) {
			  click(OrderObj.SELECT_CARRIER_DD, "carrier Drop down");
			  String carriers[] =Carriers.split(","); 
			  for (i = 0; i < carriers.length; i++) { 
		         if(isElementPresent(OrderObj.selectCarrier(carriers[i]), Carriers)) {
 			  }
			  else { 
				  reporter.failureReport("verify carrier options", carriers[i] + " is not present", carriers[i], driver); }
			  }
			  reporter.SuccessReport("verify carrier options","No carrier preference","Expected Carrier Options:"+ carriers[i]);	
			}
      }
	

	public void selectCarrier(String carrier) throws Throwable {
		click(OrderObj.SELCET_CARRIER, "carrier Drop down");
		if (isElementPresent(ShipBillPayObj.selectCarrierDD(carrier), "shipping carrier Dropdown")) {
			click(ShipBillPayObj.selectCarrierDD(carrier), "carrier Drop down");

		}
	}

	/**
	 * Method is to get shipping method cost
	 * 
	 * @throws Throwable
	 */
	public String getShippingMethodCost(String shippingCarrier) throws Throwable {
		String shippingCost = getText(OrderObj.shippingCarrierCharges(shippingCarrier), "Shipping charges");
		return shippingCost;
	}

	/**
	 * Method is to select shipping method
	 * 
	 * @throws Throwable
	 */

	public void selectShippingMeethod(String shippingCarrier) throws Throwable {
		click(OrderObj.shippingCarrier(shippingCarrier), "shipping carrier: " + shippingCarrier);
	}

	/**
	 * Purpose of this method is to delete Cart From Account Tools
	 * 
	 * @param cartName
	 * @throws Throwable
	 */
	public void deleteCartFromAccountTools(String cartName) throws Throwable {
		Thread.sleep(20000);
		
		if(isElementPresent(InvoiceHistoryObj.COSE_ACCOUNT_TOOLS, "close account tools"))
		{
		click(InvoiceHistoryObj.COSE_ACCOUNT_TOOLS, "close account tools");
		}
		
		click(CartObj.ACCOUNT_TOOLS, "ACCOUNT TOOLS");
		click(CartObj.TOOLS, "TOOLS");
		
		click(CartObj.SAVEDCART, "SAVED CART");
		Thread.sleep(5000);
		if(isElementPresent(CartObj.SAVED_CART_CONTENTS_HEADER, "Saved cart or orfer templates")) {
			reporter.SuccessReport("Saved carts/ ORDER TEMPLATES", "page is saved carts", "Saved carts/ ORDER TEMPLATES");
		}
		else {
			reporter.failureReport("Saved carts/ ORDER TEMPLATES", "page is not saved carts","", driver);
		}
		click((CartObj.deleteButton(cartName)), "Delete cart");
		waitForVisibilityOfElement(CartObj.YES_BUTTON_INCONFORMATION_POP_UP, "Yes in conformation pop up");
		click(CartObj.YES_BUTTON_INCONFORMATION_POP_UP, "Yes in conformation pop up");
		reporter.SuccessReport("Delete cart meassage ", "Cart is sucessfully deleted", cartName);
		//waitForVisibilityOfElement(CartObj.DELETE_CART_MEASSAGE, "ACCOUNT TOOLS");
//		if (isElementPresent(CartObj.DELETE_CART_MEASSAGE, "Delete cart sucess meassage")) {
//			reporter.SuccessReport("Delete cart meassage ", "Cart is sucessfully deleted", cartName);
//		} else {
//			reporter.failureReport("Delete cart meassage ", "Cart is sucessfully not deleted", "", driver);
//
//		}
	}
	
	public void deleteSavedCartFromAccountTools() throws Throwable {
		if(isElementPresent(CartObj.SAVED_CART_CONTENTS_HEADER, "Saved cart or orfer templates")) {
			reporter.SuccessReport("Saved carts/ ORDER TEMPLATES", "page is saved carts", "Saved carts/ ORDER TEMPLATES");
		}
		else {
			reporter.failureReport("Saved carts/ ORDER TEMPLATES", "page is not saved carts","", driver);
		}
		if(isElementPresent(CartObj.NO_SAVED_CART_MESSAGE, "No Saved carts or order templates exists")) {
			reporter.SuccessReport("AccountTools/Saved carts", "No Saved carts or order templates exists", "No Saved carts or order templates exists");
		}
		else {
			List<WebElement> myList = driver.findElements(CartObj.DELETE_CART);
			System.out.println("myList"+myList);			
			List<WebElement> myList1 = driver.findElements(CartObj.CART_NAME);
			int size=myList.size();
			System.out.println("listsize1"+size);
			//for (int i = 0; i <= size-1; i++) {
			for (int i = 0; i <= size-1; i++) {
				String cartName = myList1.get(i).getText();
				myList.get(i).click();
				waitForVisibilityOfElement(CartObj.YES_BUTTON_INCONFORMATION_POP_UP, "Yes in conformation pop up");
				click(CartObj.YES_BUTTON_INCONFORMATION_POP_UP, "Yes in conformation pop up","Saved Carts: "+ cartName);
				Thread.sleep(5000);
				
				//waitForVisibilityOfElement(CartObj.DELETE_CART_MEASSAGE, "ACCOUNT TOOLS");
				reporter.SuccessReport("Delete cart meassage ", "Save Cart name Exist and Deleted", "Saved Carts: "+cartName);
				Thread.sleep(10000);
				
				
			}
			
			if(isElementPresent(CartObj.NO_SAVED_CART_MESSAGE, "No Saved carts or order templates exists")) {
				reporter.SuccessReport("AccountTools/Saved carts", "No Saved carts or order templates exists", "No Saved carts or order templates exists");
			}
		}
	}
	
	public void verifySavedCarts() throws Throwable {
		scrollUp();
		if(isElementPresent(InvoiceHistoryObj.COSE_ACCOUNT_TOOLS, "close account tools"))
		{
		click(InvoiceHistoryObj.COSE_ACCOUNT_TOOLS, "close account tools");
		}
		
		click(CartObj.ACCOUNT_TOOLS, "ACCOUNT TOOLS");
		click(CartObj.TOOLS, "TOOLS");
		
		click(CartObj.SAVEDCART, "SAVED CART");
		Thread.sleep(5000);
		if(isElementPresent(CartObj.SAVED_CART_CONTENTS_HEADER, "Saved cart or orfer templates")) {
			reporter.SuccessReport("Saved carts/ ORDER TEMPLATES", "page is saved carts", "Saved carts/ ORDER TEMPLATES");
		}
		else {
			reporter.failureReport("Saved carts/ ORDER TEMPLATES", "page is not saved carts","", driver);
		}
		if(isElementPresent(CartObj.CART_NAME, "Saved cart")) {
			List<WebElement> myList = driver.findElements(CartObj.CART_NAME);

			for (int i = 0; i <=myList.size()-1; i++) {
				//myList.get(i).click();

				reporter.SuccessReport("Verify saved cart", "Saved Carts  Exist ", "Saved Carts: "+myList.get(i).getText());
				Thread.sleep(5000);
			}
		}
		else {
			reporter.failureReport("Verify saved cart", "Saved Carts does not Exist ", "");
			}
		
	}

	/*
	 * PURPOSE OF METHOD : Verify cart is empty
	 * 
	 * @author :
	 */

	public void verifyCartIsEmpty() throws Throwable {
		waitForVisibilityOfElement(CartObj.CART_ITEMS, "CART ITEMS");
		if (isElementPresent(CartObj.CART_ITEMS, "cart items")) {
			reporter.SuccessReport("cart message ", "Cart is empty", "");
		} else {
			click(CartObj.CART, "CART");
			commonLib.emptyCartAndVerify();
			reporter.failureReport("Delete cart meassage ", "Cart is not empty", "", driver);

		}
	}
	public void deletePartInCart(String partNumber) throws Throwable {
		if(isVisible(CartObj.deleteSpecificPartNumber(partNumber), "Delete part in cart")){
			click(CartObj.deleteSpecificPartNumber(partNumber), "Delete part in cart "+partNumber);
		}
		else {
			reporter.failureReport("Delete part in cart", "Required part number is not visible in cart", partNumber, driver);
		}
		commonLib.spinnerImage();
	}
	public void deleteBundle() throws Throwable {
		if(isVisible(CartObj.DELETE_BUNDLE, "Delete budnle-1")) {
			click(CartObj.DELETE_BUNDLE, "Delete budnle-1");
			String text=getText(CartObj.BUNDLE_NAME, "Bundle name");
			if(!isVisibleOnly(CartObj.DELETE_BUNDLE, "Delete budnle-1")) {
				reporter.SuccessReport("Delete bundle-1 in cart", "Bundle-1 is sucessfully deleted", text+": "+"Bundle-1", driver);
			}
		}
		else {
			reporter.failureReport("Delete bundle-1 in cart", "Bundle-1 in cart is not visible", "Bundle-1", driver);
		}
	}
    public void removeInStockItems() throws Throwable {
		if(isElementPresent(CartObj.REMOVE_IN_STOCK_ITEMS, "Remove in stock items")) {
			click(CartObj.REMOVE_IN_STOCK_ITEMS, "Remove in stock items");
			
		}
		else {
			reporter.SuccessReport("In stocks items", "In stock items are not visible", "", driver);
		}
	}
    
    public void verifyInStockItems() throws Throwable {
		if(isElementPresent(CartObj.REMOVE_IN_STOCK_ITEMS, "Remove in stock items")) {
			reporter.SuccessReport("Verifying in stock items", "In stock items are present", "", driver);
			
		}
		else {
			reporter.failureReport("Verifying in stock items", "In stock items are not present", "", driver);
		}
	}
    
	/**
	 * This method is to compare two prices
	 * 
	 * @param open
	 *            market price and us commodity price
	 * @throws Throwable
	 * @author : CIGNITI
	 */
	public void VerifyOpenMarketPriceAndUSCommodityPrice(String openMarketPrice, String USCommodityPrice)
			throws Throwable {
		if (openMarketPrice.contains(USCommodityPrice)) {
			reporter.failureReport("Verify the Open Market Price And US Commodit Price",
					" Open Market Price And US Commodit Price are same ", "", driver);
		} else {
			reporter.SuccessReport("Verify the Open Market Price And US Commodit Price",
					"  Open Market Price And US Commodit Price are not same ", "");
		}

	}

	public void VerifyLoginPriceAndNonLoginPrice(String priceInLogin, String priceWithoutLogin) throws Throwable {
		if (priceInLogin.equalsIgnoreCase(priceWithoutLogin)) {
			reporter.failureReport("Verify the login price and non login price",
					" Login price and non login prices are same", "", driver);
		} else {
			reporter.SuccessReport("Verify the login price and non login price",
					"  Login price and non login prices are different ", "");
		}

	}

	public void verifySummaryPriceInLoginAndNonLogin(String priceInLogin, String priceWithoutLogin) throws Throwable {
		if (priceInLogin.equalsIgnoreCase(priceWithoutLogin)) {
			reporter.failureReport("Verify the login price and non login price in summary",
					" Login price and non login prices are same", "", driver);
		} else {
			reporter.SuccessReport("Verify the login price and non login price in summary",
					"  Login price and non login prices are different ", "");
		}

	}

	public String getSummaryAmountInCart() throws Throwable {
		Thread.sleep(5000);
		String summaryAmount = getText(CartObj.SUMMARY_TOTAL, "summaryTotalAmount");
		return summaryAmount;
	}
	public String getCurrencyCode() throws Throwable {
		String currencyCode=getText(CartObj.CURRENCY_CODE, "Currency type");
		
		return currencyCode;
		
	}

	public String getShippingEstimateInCart() throws Throwable {
		String shipingCharges = getText(CartObj.SHIPPING_ESTIMATE, "SHipping Charges");
		return shipingCharges;

	}

	/**
	 * This method is to verify RP_HDL_Txt text
	 * 
	 * @throws Throwable
	 * 
	 */
	public void verifyRpHdlTxtisNotPresent(String rpHdlText) throws Throwable {
		if (isElementPresent(CartObj.verifyRpHdlText(rpHdlText), "rpHdl Text")) {
			reporter.failureReport("Verify smart tracker ", "" + rpHdlText + " is displayed", "", driver);
		} else {
			reporter.SuccessReport("Verify smart tracker", "" + rpHdlText + " is not displayed", "");

		}
	}

	/**
	 * This method is to verify RP_LNL_Txt text
	 * 
	 * @throws Throwable
	 * 
	 */
	public void verifyRpLnllTxtisNotPresent(String rpLnlText) throws Throwable {
		if (isElementPresent(CartObj.verifyRpHdlText(rpLnlText), "rpHdl Text")) {
			reporter.failureReport("Verify smart tracker ", "" + rpLnlText + " is displayed", rpLnlText, driver);
		} else {
			reporter.SuccessReport("Verify smart tracker", "" + rpLnlText + " is not displayed", rpLnlText);

		}
	}

	/**
	 * PURPOSE: This method is to verify Quick Shop With Valid Single PartNumber in
	 * shopping cart page.
	 * 
	 * @param searchItem
	 * @throws Throwable
	 * @customization author : CIGNITI
	 */
	public void verifyPrintPopUp() throws Throwable {
		waitForVisibilityOfElement(CartObj.VIEW_PRINTABLE_POPUP, "PRINT POPUP");
		isElementPresent(CartObj.INSIGHT_LOGO_IN_PRINT_POPUP, "INSIGHT LOGO IN PRINT POPUP");
		isElementPresent(CartObj.QUANTITY_IN_PRINT_POPUP, "QUANTITY IN PRINT POPUP");
		isElementPresent(CartObj.UNIT_PRICE_IN_PRINT_POPUP, "UNIT PRICE IN PRINT POPUP");
	}

	/**
	 * This method is to search for a web group in the CMT home page.
	 * 
	 * @param webGroup
	 * @throws Throwable
	 * @author : CIGNITI
	 */
	public String getTotalPrice() throws Throwable {
		isElementPresent(CartObj.TOTAL_PRICE_IN_POPUP, "TOTAL PRICE OF ITEM IN POPUP");
		String totalPrice = getText(CartObj.TOTAL_PRICE_IN_POPUP, "TOTAL PRICE OF ITEM IN POPUP");
		totalPrice = totalPrice.split(":")[1];

		return totalPrice;

	}
	public String getTotalPriceInSearchResults() throws Throwable {
		isElementPresent(CartObj.TOTAL_PRICE_IN_SEARCH_RESULTS, "TOTAL PRICE OF ITEM ");
		String totalPrice = getText(CartObj.TOTAL_PRICE_IN_SEARCH_RESULTS, "TOTAL PRICE OF ITEM ");
		//totalPrice = totalPrice.split("$")[1];

		return totalPrice;
	}
	/**
	 * This method is to search for a web group in the CMT home page.
	 * 
	 * @param webGroup
	 * @throws Throwable
	 * @author : CIGNITI
	 */
	public void cartBasicsIPS_verifyPermissionAtUserLevel() throws Throwable {

		clickMorePricesAvilableInProductInfo();
		clickOnOpenMarketPrice();
		clickOnAddToCartInAllContractPrices();

		commonLib.closePopUp();

	}

	/**
	 * This method is to Add a second part
	 * 
	 * @param webGroup
	 * @throws Throwable
	 * @author : CIGNITI
	 */
	public void cartBasicsIPS_verifyPermissionAtUserLevelAddingSecondPart(String menuName, String userPermission,
			String searchItem, String quantity, String logoutHeader) throws Throwable {

		commonLib.clickRolesAndPermissionsAtUserLevel();
		cmtLib.setPermissionsToDisable(menuName, userPermission);
		String mainWindow = parentWindow();
		cmtLib.clickOnloginAs();
		switchToWindow(mainWindow);
		commonLib.searchProduct(searchItem);
		clickMorePricesAvilable(0);
		clickOnUSCommuditiesPrice();
		clickOnAddToCartInAllContractPrices();
		String usCommodityPrice = getTotalPrice();
		VerifyOpenMarketPriceAndUSCommodityPrice(openMarketPrice, usCommodityPrice);
		commonLib.closePopUp();
		commonLib.clickCart();
		commonLib.updateCartQuantity(quantity);
		commonLib.clickLogOutLink(logoutHeader);
		switchToWindow(mainWindow);
	}

	/**
	 * PURPOSE: This method is to verify Quick Shop With Valid Single PartNumber in
	 * shopping cart page.
	 * 
	 * @param searchItem
	 * @throws Throwable
	 * @customization author : CIGNITI/SOWJANYA
	 */
	public void clickAndVerifyViewPrintablePopUp(String orderUtilities) throws Throwable {
		commonLib.clickCart();
		cmtLib.handleWelcomeToInsightBetaPopUp();
		waitForVisibilityOfElement(CartObj.getShoppingCartOrderUtilities(orderUtilities), "PRINT LINK");
		click(CartObj.getShoppingCartOrderUtilities(orderUtilities), "PRINT LINK");
		verifyPrintPopUp();
		// clickPrintInPopUp();
		// NEED TO DO VALIDATION IN PRINT PDF
		closePrintPopUp();
	}

	/**
	 * PURPOSE: This method is to verify Quick Shop With Valid Single PartNumber in
	 * shopping cart page.
	 * 
	 * @param searchItem
	 * @throws Throwable
	 * @customization author : CIGNITI
	 */
	public void clickAndVerifySendToAColleagueErrorMSG(String orderUtilities) throws Throwable {
		//commonLib.clickCart();
		//cmtLib.handleWelcomeToInsightBetaPopUp();
		waitForVisibilityOfElement(CartObj.getShoppingCartOrderUtilities(orderUtilities), "Send to a colleague");
		click(CartObj.getShoppingCartOrderUtilities(orderUtilities), "Send to a colleague");
		waitForVisibilityOfElement(CartObj.SEND_TO_A_COLLEGUE_POPUP, "SEND TO A COLLEGUE POPUP");
		waitForVisibilityOfElement(CartObj.SEND_BUTTON_IN_SEND_TO_A_COLLEGUE_POPUP,
				"SEND BUTTON IN SEND TO A COLLEGUE POPUP");
		click(CartObj.SEND_BUTTON_IN_SEND_TO_A_COLLEGUE_POPUP, "SEND BUTTON IN SEND TO A COLLEGUE POPUP");
		verifyErrorMessagesInSendToAColleaguePopUp();
		//click(CartObj.CLOSE_SEND_TO_A_COLLEGUE_POPUP, "CLOSE SEND TO A COLLEGUE POPUP");
	}

	/**
	 * PURPOSE: This method is to verify Quick Shop With Valid Single PartNumber in
	 * shopping cart page.
	 * 
	 * @param searchItem
	 * @throws Throwable
	 * @customization author : CIGNITI/SOWJANYA
	 */
	public void verifyErrorMessagesInSendToAColleaguePopUp() throws Throwable {
		if(isElementPresent(CartObj.YOUR_NAME_ERROR_MESSAGE, "YOUR NAME ERROR MESSAGE")) {
			String errorMessage=getText(CartObj.YOUR_NAME_ERROR_MESSAGE,"YOUR NAME ERROR MESSAGE");
			reporter.SuccessReport("Verifying error message", "YOUR NAME ERROR MESSAGE", errorMessage, driver);
		}
		else {
			reporter.failureReport("Verifying error message", "YOUR NAME ERROR MESSAGE does not exist", "", driver);
		}
		if(isElementPresent(CartObj.YOUR_EMAIL_ERROR_MESSAGE, "YOUR EMAIL ERROR MESSAGE")) {
			String errorMessage=getText(CartObj.YOUR_EMAIL_ERROR_MESSAGE, "YOUR EMAIL ERROR MESSAGE");
			reporter.SuccessReport("Verifying error message", "YOUR EMAIL ERROR MESSAGE", errorMessage, driver);
		}
		else {
			reporter.failureReport("Verifying error message", "YOUR EMAIL ERROR MESSAGE does not exist", "", driver);
		}
		if(isElementPresent(CartObj.RECIPIENT_EMAIL_ERROR_MESSAGE, "RECIPIENT EMAIL ERROR MESSAGE")) {
			String errorMessage=getText(CartObj.RECIPIENT_EMAIL_ERROR_MESSAGE, "RECIPIENT EMAIL ERROR MESSAGE");
			reporter.SuccessReport("Verifying error message", "RECIPIENT EMAIL ERROR MESSAGE", errorMessage, driver);
		}
		else {
			reporter.failureReport("Verifying error message", "RECIPIENT EMAIL ERROR MESSAGE does not exist", "", driver);
		}
	}
	
	public void verifyErrorMessagesInSendToAColleaguePopUpForEmail() throws Throwable {
		
		if(isElementPresent(CartObj.YOUR_EMAIL_ERROR_MESSAGE, "YOUR EMAIL ERROR MESSAGE")) {
			String errorMessage=getText(CartObj.YOUR_EMAIL_ERROR_MESSAGE, "YOUR EMAIL ERROR MESSAGE");
			reporter.SuccessReport("Verifying error message", "YOUR EMAIL ERROR MESSAGE", errorMessage, driver);
		}
		else {
			reporter.failureReport("Verifying error message", "YOUR EMAIL ERROR MESSAGE does not exist", "", driver);
		}
		if(isElementPresent(CartObj.RECIPIENT_EMAIL_ERROR_MESSAGE, "RECIPIENT EMAIL ERROR MESSAGE")) {
			String errorMessage=getText(CartObj.RECIPIENT_EMAIL_ERROR_MESSAGE, "RECIPIENT EMAIL ERROR MESSAGE");
			reporter.SuccessReport("Verifying error message", "RECIPIENT EMAIL ERROR MESSAGE", errorMessage, driver);
		}
		else {
			reporter.failureReport("Verifying error message", "RECIPIENT EMAIL ERROR MESSAGE does not exist", "", driver);
		}
	}

	/**
	 * PURPOSE:
	 * 
	 * @param searchItem
	 * @throws Throwable
	 * @customization author : CIGNITI
	 */
	public void verifySendToAColleague(String orderUtilities, String yourName, String yourEmail, String recipientEmail,
			String yourComments) throws Throwable {
		//waitForVisibilityOfElement(CartObj.getShoppingCartOrderUtilities(orderUtilities), "Send to a colleague");
		//click(CartObj.getShoppingCartOrderUtilities(orderUtilities), "Send to a colleague");

		waitForVisibilityOfElement(CartObj.YOUR_NAME_TEXT_FIELD, "YOUR NAME TEXT FIELD");
		clickOnly(CartObj.YOUR_NAME_TEXT_FIELD, "YOUR NAME TEXT FIELD");
		typeUntil(CartObj.YOUR_NAME_TEXT_FIELD, yourName, "YOUR NAME TEXT FIELD");

		waitForVisibilityOfElement(CartObj.YOUR_EMAIL_TEXT_FIELD, "YOUR EMAIL TEXT FIELD");
		clickOnly(CartObj.YOUR_EMAIL_TEXT_FIELD, "YOUR EMAIL TEXT FIELD");
		typeUntil(CartObj.YOUR_EMAIL_TEXT_FIELD, yourEmail, "YOUR EMAIL TEXT FIELD");

		waitForVisibilityOfElement(CartObj.RECIPIENT_EMAIL_TEXT_FIELD, "RECIPIENT EMAIL TEXT FIELD");
		clickOnly(CartObj.RECIPIENT_EMAIL_TEXT_FIELD, "RECIPIENT EMAIL TEXT FIELD");
		typeUntil(CartObj.RECIPIENT_EMAIL_TEXT_FIELD, recipientEmail, "RECIPIENT EMAIL TEXT FIELD");

		waitForVisibilityOfElement(CartObj.YOUR_COMMENTS_TEXT_FIELD, "YOUR COMMENTS TEXT FIELD");
		clickOnly(CartObj.YOUR_COMMENTS_TEXT_FIELD, "YOUR COMMENTS TEXT FIELD");
		typeUntil(CartObj.YOUR_COMMENTS_TEXT_FIELD, yourComments, "YOUR COMMENTS TEXT FIELD");

		waitForVisibilityOfElement(CartObj.SEND_BUTTON_IN_SEND_TO_A_COLLEGUE_POPUP,
				"SEND BUTTON IN SEND TO A COLLEGUE POPUP");
		click(CartObj.SEND_BUTTON_IN_SEND_TO_A_COLLEGUE_POPUP, "SEND BUTTON IN SEND TO A COLLEGUE POPUP");

		

	}
	
	public void verifySendToAColleagueSucessMessage() throws Throwable {
		waitForVisibilityOfElement(CartObj.MAIL_SEND_TO_A_COLLEGUE_SUCCESS_MSG, "SUCCESS MSG");
		isElementPresent(CartObj.MAIL_SEND_TO_A_COLLEGUE_SUCCESS_MSG, "SUCCESS MSG", true);
	}
	

	/**
	 * PURPOSE:
	 * 
	 * @param searchItem
	 * @throws Throwable
	 * @customization author : CIGNITI
	 */
	public void clickOnCompanyStandards() throws Throwable {
		waitForVisibilityOfElement(CartObj.COMPANY_STANDARDS, "COMPANY STANDARDS");
		click(CartObj.COMPANY_STANDARDS, "COMPANY STANDARDS");
	}

	/**
	 * PURPOSE: This method is to verify Quick Shop With Valid Single PartNumber in
	 * shopping cart page.
	 * 
	 * @param searchItem
	 * @throws Throwable
	 * @customization author : CIGNITI/SOWJANYA
	 */
	public void clickAndVerifySendToAColleagueErrorMSG_IPS(String orderUtilities) throws Throwable {
		commonLib.clickCart();
		Thread.sleep(10000);
		commonLib.spinnerImage();
		waitForVisibilityOfElement(CartObj.getShoppingCartOrderUtilities(orderUtilities), "Send to a colleague");
		click(CartObj.getShoppingCartOrderUtilities(orderUtilities), "Send to a colleague");
		waitForVisibilityOfElement(CartObj.SEND_TO_A_COLLEGUE_POPUP, "SEND TO A COLLEGUE POPUP");
		waitForVisibilityOfElement(CartObj.SEND_BUTTON_IN_SEND_TO_A_COLLEGUE_POPUP,
				"SEND BUTTON IN SEND TO A COLLEGUE POPUP");
		click(CartObj.SEND_BUTTON_IN_SEND_TO_A_COLLEGUE_POPUP, "SEND BUTTON IN SEND TO A COLLEGUE POPUP");
		verifyErrorMessagesInSendToAColleaguePopUp();
		//click(CartObj.CLOSE_SEND_TO_A_COLLEGUE_POPUP, "CLOSE SEND TO A COLLEGUE POPUP");
	}

	/**
	 * PURPOSE: This method is to verify Quick Shop With Valid Single PartNumber in
	 * shopping cart page.
	 * 
	 * @param searchItem
	 * @throws Throwable
	 * @customization author : CIGNITI
	 */
	public void clickAndVerifyExportCart(String orderUtilities) throws Throwable {
		commonLib.clickCart();
		waitForVisibilityOfElement(CartObj.getShoppingCartOrderUtilities(orderUtilities), "Export as a file");
		mouseClick(CartObj.getShoppingCartOrderUtilities(orderUtilities), "Export as a file");

	}

	/**
	 * This method is to click on the Export cart and validate th eexported excel
	 * 
	 * @param orderUtilities
	 * @throws Throwable
	 */
	public void ClickExportCartAndVerify(String orderUtilities,String sheetName,String rownum,String headers) throws Throwable {
		scrollUp();
		if(isVisibleOnly(CartObj.getShoppingCartOrderUtilities(orderUtilities), "order utilities")) {
			reporter.SuccessReport("Verify order utilities exists", "order utilities exits", "Order Utilities List");
			mouseClick(CartObj.getShoppingCartOrderUtilities(orderUtilities), "Export as a file");
			verifyExportFile(sheetName,rownum,headers);
		}else {
			reporter.failureReport("Verify order utilities exists", "order utilities does not exits", "", driver);
		}
		

	}

	/**
	 * PURPOSE: This method is to verify Quick Shop With Valid Single PartNumber in
	 * shopping cart page.
	 * 
	 * @param searchItem
	 * @throws Throwable
	 * @customization author : CIGNITI
	 */
	public void verifyQuickShopWithValidSinglePartNumber(String searchItem, String quantity) throws Throwable {
		waitForVisibilityOfElement(CartObj.QUICK_SHOP_ITEM_FIELD, "QUICK SHOP ITEM FIELD");
		clearData(CartObj.QUICK_SHOP_ITEM_FIELD);
		type(CartObj.QUICK_SHOP_ITEM_FIELD, searchItem, "QUICK SHOP ITEM FIELD");
		clearData(CartObj.QUICK_SHOP_QUANTITY_FIELD);
		type(CartObj.QUICK_SHOP_QUANTITY_FIELD, quantity, "QUICK SHOP QUANTITY FIELD");
		Thread.sleep(3000);
		//JSClick(CartObj.ADD_BUTTON_IN_QUICK_SHOP, "ADD BUTTON IN QUICK SHOP");
		clickUntil(CartObj.ADD_BUTTON_IN_QUICK_SHOP, CommonObj.SPINNER_IMAGE, "Add quick shop button");
		commonLib.spinnerImage();
		//verifyItemInCart(searchItem);
		scrollUp();

	}
	public void clickOnProductLinkInCartPage() throws Throwable {
		if(isVisible(CartObj.PRODUCT_LINK,"Product Link")) {
			click(CartObj.PRODUCT_LINK,"Product Link");
		}
		else {
			reporter.failureReport("Product link in cart page", "Product link in cart page is not visible", "", driver);
		}
		
	}
	public void verifyQuickShopIsDisable() throws Throwable {

		if (isElementNotPresent(CartObj.QUICK_SHOP_SECTION, "quick shop section")) {
			reporter.SuccessReport("verifying quick shop section :: ", "is  not present", "");
		} else {

			reporter.failureReport("verifying quick shop section :: ", " is present", "", driver);

		}
	}

	/**
	 * PURPOSE: This method is to verify whether item is added to cart or not in
	 * shopping cart page.
	 * 
	 * @param searchItem
	 * @throws Throwable
	 * @customization author : CIGNITI
	 */
	public void verifyItemInCart(String itemInCart) throws Throwable {
		canadaLib.verifyPlaceCartLabel();
		waitForVisibilityOfElement(CartObj.getItemInCart(itemInCart), "Item in cart");
		if (driver.findElement(CartObj.getItemInCart(itemInCart)).isDisplayed()) {
			reporter.SuccessReport("verifying item added to cart :: ", " item added to cart is verified and it is same as product details page.ITEM IS :", itemInCart);
		} else {
			reporter.failureReport("verifying item added to cart :: ", "ITEM " + itemInCart + "is not ADDED TO CART",
					itemInCart, driver);

		}
	}

	public void verifyItemInCartByInsightPart(String itemInCart) throws Throwable {
		Thread.sleep(5000);
		waitForVisibilityOfElement(CartObj.getItemIncartByInsightPartNumber(itemInCart), "Item in cart");
		if (isElementPresent(CartObj.getItemIncartByInsightPartNumber(itemInCart), "part number")) {
			reporter.SuccessReport("verifying item added to cart :: ", " Item added to cart Mfr number # is :", itemInCart);
		} else {
			reporter.failureReport("verifying item added to cart :: ", "ITEM " + itemInCart + "is not ADDED TO CART",
					itemInCart, driver);

		}
	}

	/**
	 * 
	 * @param contractName
	 * @throws Throwable
	 */
	public void verifyContractNameInCart(String contractName) throws Throwable {
		if (driver.findElement(CartObj.getContractNameInCart(contractName)).isDisplayed()) {
			reporter.SuccessReport("verifying item added to cart :: ", " with contract name-", "Contract : "+contractName);
		} else {
			reporter.failureReport("verifying item is not added to cart :: ", " with contract name-", contractName,
					driver);

		}
	}

	/**
	 * PURPOSE: This method is to verify QuickShop With Invalid PartNumber in
	 * shopping cart page.
	 * 
	 * @param searchItem
	 * @throws Throwable
	 * @customization author : CIGNITI
	 */
	public void verifyQuickShopWithInvalidPartNumber(String searchItem) throws Throwable {
		waitForVisibilityOfElement(CartObj.QUICK_SHOP_ITEM_FIELD, "QUICK SHOP ITEM FIELD");
		clearData(CartObj.QUICK_SHOP_ITEM_FIELD);
		type(CartObj.QUICK_SHOP_ITEM_FIELD, searchItem, "QUICK SHOP ITEM FIELD");
		click(CartObj.ADD_BUTTON_IN_QUICK_SHOP, "ADD BUTTON IN QUICK SHOP");
		if (isElementPresent(CartObj.INAVLID_PART_NO_QUICK_SHOP_ERROR_MESSAGE, "error message")) {
			reporter.SuccessReport("Error Message for invalid Part #s on the Cart Page",
					" Error Message for invalid Part #s Exists", "");
		} else {
			reporter.failureReport("Error Message for invalid Part #s on the Cart Page",
					" Error Message for invalid Part #s does not  Exists", "", driver);
		}
	}

	public String getPartNumber() throws Throwable {

		String[] partNumberArray = getText(CartObj.PART_NUMBER_IN_ADDED_TO_YOUR_CART_POPUP,
				"MFR_NUMBER_PRODUCT_DETAILS_PAGE").replace("\"", "").replace(" ", "").trim().split(":");
		String partNumber = partNumberArray[1].trim();
		return partNumber;
	}

	/**
	 * PURPOSE: This method is to verify verify QuickShop Error Message in shopping
	 * cart page.
	 * 
	 * @param searchItem
	 * @throws Throwable
	 * @customization author : CIGNITI
	 */
	public void verifyQuickShopErrorMessage() throws Throwable {
		if (driver.findElement(CartObj.QUICK_SHOP_ERROR_MESSAGE).isDisplayed()) {
			reporter.SuccessReport("verifying QUICK SHOP ERROR MESSAGE :: ", " QUICK SHOP ERROR MESSAGE is  visible",
					"");
		} else {
			reporter.failureReport("verifying QUICK SHOP ERROR MESSAGE :: ", " QUICK SHOP ERROR MESSAGE is not visible",
					"", driver);

		}

	}

	/**
	 * This method is to validate the columns in the exported cart excel file.
	 * 
	 * @throws Throwable
	 */
	public void validateCartExport() throws Throwable {

		File src = new File("C:\\Users\\E002542\\Downloads\\exportCart.xls"); // CHANGE PATH ACCORDINGLY
		if (src.exists()) {
			System.out.println("file found");
			reporter.SuccessReport("Verify the file existance ", "File Found", "");
		} else {
			System.out.println("file not found");
			reporter.failureReport("Verify the file existance ", "File not Found", "");
		}
		Workbook wb = WorkbookFactory.create(src);
		Sheet sh = wb.getSheet("send_cart");
		int rownum = sh.getLastRowNum();
		System.out.println(rownum);
		for (int i = 1; i < rownum; i++) {
			Row r = sh.getRow(i);
			short cell = r.getLastCellNum();
			for (int k = 0; k < cell; k++) {
				Cell c = r.getCell(k);
				String val = c.getStringCellValue();

				System.out.println(val);
				reporter.SuccessReport("Getting the values from columns ", "The column value is : ", val);

			}
		}

	}

	/**
	 * This method is to close Print PopUp
	 * 
	 * @throws Exception
	 */
	public void closePrintPopUp() throws Throwable {
		waitForVisibilityOfElement(CartObj.CLOSE_PRINT_POPUP, "CLOSE PRINT POPUP");
        click(CartObj.CLOSE_PRINT_POPUP, "CLOSE PRINT POPUP");

	}

	/**
	 * This method TO VERIFY PRINT POP UP
	 * 
	 * @throws Exception
	 */
	public void clickPrintInPopUp() throws Throwable {
		waitForVisibilityOfElement(CartObj.PRINT_SYMBOL_IN_PRINT_POPUP, "PRINT SYMBOL IN PRINT POPUP");
		click(CartObj.PRINT_SYMBOL_IN_PRINT_POPUP, "PRINT SYMBOL IN PRINT POPUP");

	}

	/**
	 * Method is to click on the first product and click on add to cart.
	 * 
	 * @throws Throwable
	 */
	public void selectFirstProductDisplay() throws Throwable {
		waitForVisibilityOfElement(productsDisplayInfoObj.FIRST_PROD_NAME, "First product in search results page");
		String ProdName=getText(productsDisplayInfoObj.FIRST_PROD_NAME, "First Product");
		Thread.sleep(3000);
		clickUntil(productsDisplayInfoObj.FIRST_PROD_NAME,productsDisplayInfoObj.BACK_TO_RESULTS, "First product in search results page displayed and clicked "+ProdName,"Product name : "+ProdName);
		waitForVisibilityOfElement(productsDisplayInfoObj.BACK_TO_RESULTS, "Back to results");
	}
public void verifyProductdetails() throws Throwable {
	String produtname = getText(productsDisplayInfoObj.FirstprodName, "First product in search results page");
	String PartNumber  = getText(productsDisplayInfoObj.PartNumberInprodutdetailsPage,"PartNumber");
	if(produtname!=""&&PartNumber!="") {
		reporter.SuccessReport("Search Results:", "Product Details are Displayed", produtname);
		reporter.SuccessReport("Search Results:", "Product Details are Displayed", PartNumber);
	}
	else {
		reporter.failureReport("Search Results:", "Product Details are not Displayed", "");
	}
}
	/**
	 * This method is to verify the added product group is displayed in the cart
	 * screen.
	 * 
	 * @param productName
	 * @throws Throwable
	 */
	public void verifyProductGroupBundleAddedToCart(String productName) throws Throwable {
		canadaLib.verifyPlaceCartLabel();
		String actualprodGroupName = getText(CartObj.PROD_GROUP_NAME_IN_CART, "Product group name in cart");
		if (actualprodGroupName.equals(productName) && isElementPresent(CartObj.BUNDLEONE, "Bundle one in cart")) {
			reporter.SuccessReport("Verify product group displayed in the cart screen",
					"correct product group is displayed as bundle : ", "BUNDLE-1: "+actualprodGroupName);
		} else {
			reporter.failureReport("Verify product group displayed in the cart screen",
					"correct product group is not displayed : " + actualprodGroupName + " .Expected is: ", productName,
					driver);
		}
	}

	/**
	 * This method is to verify the Manufacture Number/part number/ Item added In
	 * the Cart.
	 * 
	 * @param mfrNumber
	 * @throws Throwable
	 */
	public void verifyItemAddedInCartByMfrNumber(String mfrNumber) throws Throwable {

		waitForVisibilityOfElement(CartObj.PART_NUMBER, "Part Number in cart");
		String MfrNum = getText(CartObj.PART_NUMBER, "Mfr number in cart");
		String[] actualMfrNumber = MfrNum.replace("\" ", "").replace(" ", "").trim().split(":");
		if (actualMfrNumber[1].equals(mfrNumber)) {
			reporter.SuccessReport("Verify the part added in the cart", "Item added to cart verfication is successfull",
					mfrNumber);
		} else {
			reporter.failureReport("Verify the part added in the cart",
					"Item added to cart verfication is not successfull.. Expected : " + mfrNumber + "Actual : "
							+ actualMfrNumber[1],
					"", driver);
		}
		Thread.sleep(3000);
	}

	/**
	 * This method is to verify the page landed to NVIDIA Page
	 * 
	 * @param mfrNumber
	 * @throws Throwable
	 */
	public void verifyWelcomeHeader() throws Throwable {
		if (waitForVisibilityOfElement(CartObj.WELCOME_MESSAGE, "Welcome header Message displayed")) {
			reporter.SuccessReport("Verify wether user navigates to Welcome page or not",
					"User successfully navigated to Welcome NVIDIA page", "");
		} else
			reporter.failureReport("Verify wether user navigates to Welcome page or not",
					"User not navigated to Welcome NVIDIA page", "", driver);
	}
	/*
	 * This method is to verify the page landed to NVIDIA Page and click on Get
	 * started Link
	 * 
	 * @param mfrNumber
	 * 
	 * @throws Throwable
	 */

	public void clickAndVerifyGetStartedLink() throws Throwable {
		if (isElementPresent(CartObj.GETSTARTED_IMG, "Get started Image displayed")) {
			if (click(CartObj.GETSTARTED_IMG, "Get started Image link"))
				reporter.SuccessReport("Verify the Get Started Image and click", "Images exists and clicked", "");
			else {
				reporter.failureReport("Verify the Get Started Image and click", "Unable to click on Image get started",
						"");
			}
		} else
			reporter.failureReport("Verify the Get Started Image and click", "Images does not exist", "", driver);

	}

	/**
	 * This method is to select accessories link
	 * 
	 * @param mfrNumber
	 * @throws Throwable
	 */
	public void clickOnAccesseriosLnk(String link) throws Throwable {
		if (isElementPresent(CartObj.NVIDIALIST_HDR, "NVIDIA Product List")) {
			click(CartObj.ACCESSORIES_LNK, "Accesserios Link");
			scrollUp();
			clickUntil(CartObj.listofAccessories(link), CartObj.DELL_CURRENTTAB, "Dell Accessories Link");
		}
	}

	/**
	 * This method is to select category link
	 * 
	 * @param mfrNumber
	 * @throws Throwable
	 */
	public void clickOnDellLink(String link) throws Throwable {
		click(CartObj.listofAccessories(link), "Dell Power Adapter");
		Thread.sleep(10000);
	}

	/**
	 * This method is to select laptops category link
	 * 
	 * @param mfrNumber
	 * @throws Throwable
	 */
	public void clickOnlaptopsLnk() throws Throwable {
		if (isElementPresent(CartObj.NVIDIALIST_HDR, "NVIDIA Product List")) {
			click(CartObj.LENOVOLAPTOPS_LNK, "Lenovo Laptops Link");
			Thread.sleep(10000);
		}
	}

	/**
	 * This method is to iterate by clicking on choose this link
	 * 
	 * @param mfrNumber
	 * @throws Throwable
	 */
	public void clickOnChooseThisItemLnk(int size) throws Throwable {
		for (int i = 0; i <= size; i++) {
			click(CartObj.chooseThisItem_lnk(i), "Choose this item Link for the selected product");
			System.out.println(i);
		}
	}

	/**
	 * This method is to select product link
	 * 
	 * @param mfrNumber
	 * @throws Throwable
	 */

	public void clickOnProductLink(String ProductLink) throws Throwable {
		String text=getText(CartObj.product_link(ProductLink), "Product link");
		click(CartObj.product_link(ProductLink), "Product Link "+text);
		Thread.sleep(10000);
	}

	/**
	 * This method is to click on Next button
	 * 
	 * @param mfrNumber
	 * @throws Throwable
	 */
	public void clickOnNextButton() throws Throwable {
		click(CartObj.NEXT_BTN, "Next Button");
	}

	/**
	 * This method is to click on main category links
	 * 
	 * @param mfrNumber
	 * @throws Throwable
	 */
	public void clickOnchooseLink(String link) throws Throwable {
		click(CartObj.listofAccessories(link), "Continue Shopping/Proceed to checkout Link");
	}

	/**
	 * This method is to verify the list of part items added to cart
	 * 
	 * @param mfrNumber
	 * @throws Throwable
	 */
	public List<String> getAddedPartNumberFromCart() throws Throwable {
		List<WebElement> myList = driver.findElements(OrderObj.PART_NUM);
		List<String> addedCartItems = new ArrayList<String>();
		for (int i = 0; i <= myList.size(); i++) {
			addedCartItems.add(myList.get(i).getText());
		}
		return addedCartItems;
	}

	/**
	 * This method is to verify part number and switch to parent Window
	 * 
	 * @param mfrNumber
	 * @throws Throwable
	 */
	public String getPartNumAndswitchToParentWindow() throws Throwable {
		String[] mfrNum = null;
		Set<String> handle = driver.getWindowHandles();
		if (handle.size() > 2) {
			switchToChildWindow();
			waitForVisibilityOfElement(CartObj.PDP_INSIGHT_IMG,
					"Insight Image is loaded in product description Window");
			mfrNum = getText(CartObj.CART_IDINPRODUCT_PAGE, "Part Number in Product description Page")
					.replace("Mfr Part #", "").replace("UNSPSC", "").replace(" ", "").split(":");
			if (mfrNum[0].isEmpty()) {
				reporter.failureReport("verify the Part Number for the selected product",
						"PDP window is not opened and unable to verify part number", "", driver);
			} else
				reporter.SuccessReport("verify the Part Number for the selected product",
						"PDP window opened and successfully verified the part number", "");
			Thread.sleep(3000);
			driver.close();
			ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
			driver.switchTo().window(tabs.get(1));
			Thread.sleep(3000);
		}
		return mfrNum[0];
	}

	public void verifyShippingestimator() throws Throwable {
		waitForVisibilityOfElement(CartObj.Shipping_EStimator, "Shipping Estimator is Present");
		if (isElementPresent(CartObj.Shipping_EStimator, "Shipping Estimator is Present")) {
			reporter.SuccessReport("Shipping Estimator is present", "Shipping Estimator is displayed", "");
		} else {
			reporter.failureReport("Shipping Estimator is not present", "Shipping Estimator is not displayed", "",
					driver);
		}
	}
	
	public void verifyShippingestimatorIsNotPresent() throws Throwable {
		//waitForVisibilityOfElement(CartObj.Shipping_EStimator, "Shipping Estimator is Present");
		if (isElementNotPresent(CartObj.Shipping_EStimator, "Shipping Estimator is Present")) {
			reporter.SuccessReport("Shipping Estimator is not present", "Shipping Estimator is not displayed", "");
		} else {
			reporter.failureReport("Shipping Estimator is present", "Shipping Estimator is displayed", "",
					driver);
		}
	}

	public void verifyShippingestimatorshippingCarrier(String Postal_code, String upsCarrier, String fedexCarrier)
			throws Throwable {
		Thread.sleep(10000);
		commonLib.spinnerImage();
		waitForVisibilityOfElement(CartObj.Shipping_EStimator, "Shipping Estimator is Present");
		typeText(CartObj.Shipping_Estimator_Textfield, Postal_code, "postalcode");
		click(CartObj.Shipping_Estimator_Applybutton, "Click Apply Button of Shipping Estimator ");
		click(CartObj.Shipping_Estimator_seeallcarriers, "Succesfully Clicked on See all Carriers");
		List<WebElement> myList = driver.findElements(CartObj.verifyshippingCarrier(upsCarrier));
		for (int i = 0; i < myList.size(); i++) {

			if (myList.get(i).isDisplayed()) {

				reporter.SuccessReport("UPS shpping carriers ", "" + myList.get(i).getText() + "are displayed", "");
			} else {
				reporter.failureReport("UPS shpping carriers ", "" + myList.get(i).getText() + "are not displayed", "",
						driver);
			}
		}
		List<WebElement> myList1 = driver.findElements(CartObj.verifyshippingCarrier(fedexCarrier));
		for (int i = 0; i < myList1.size(); i++) {

			if (myList1.get(i).isDisplayed()) {

				reporter.SuccessReport("FedEx shpping carriers ", "" + myList1.get(i).getText() + "are displayed", "");
			} else {
				reporter.failureReport("FedEx shpping carriers ", "" + myList1.get(i).getText() + "are not displayed",
						"", driver);
			}
		}
		click(CartObj.Close_seeallcarriers, "Succesfully see all carriers option is closed");

	}

	public void VerifyonlyFedExoptions(String Postal_code, String fedexCarrier) throws Throwable {
		Thread.sleep(10000);
		commonLib.spinnerImage();
		waitForVisibilityOfElement(CartObj.Shipping_EStimator, "Shipping Estimator is Present");
		typeText(CartObj.Shipping_Estimator_Textfield, Postal_code, "postalcode");
		click(CartObj.Shipping_Estimator_Applybutton, "Click Apply Button of Shipping Estimator ");
		List<WebElement> myList = driver.findElements(CartObj.verifyshippingCarrier(fedexCarrier));
		for (int i = 0; i < myList.size(); i++) {

			if (myList.get(i).isDisplayed()) {

				reporter.SuccessReport("FedEx shpping carriers ", "" + myList.get(i).getText() + "are displayed", "");
			} else {
				reporter.failureReport("FedEx shpping carriers ", "" + myList.get(i).getText() + "are not displayed",
						"", driver);
			}
		}
		click(CartObj.Close_seeallcarriers, "Succesfully see all carriers option is closed");

	}

	public void clickotherthanUSDandFedEx(String Postal_code) throws Throwable {
		waitForVisibilityOfElement(CartObj.Shipping_EStimator, "Shipping Estimator is Present");
		typeText(CartObj.Shipping_Estimator_Textfield, Postal_code, "postalcode");
		click(CartObj.Shipping_Estimator_Applybutton, "Click Apply Button of Shipping Estimator ");
		click(CartObj.otherthanUSDandFedEx, "clicked on Option which is not USD or FedEX");
		click(CartObj.submit_Button, "The cart shipping estimate is updated in the cart");

	}

	////////////////////////// cart inventory///////////
	/**
	 * 
	 * @param toolsMenuName
	 * @param dropDown
	 * @param productGroup
	 * @param productName
	 * @param Text_COI
	 * @throws Throwable
	 */
	public String verifyCOIpart(String toolsMenuName, String dropDown, String productGroup, String productName,
			String Text_COI) throws Throwable {
		Thread.sleep(20000);
		if(isElementPresent(InvoiceHistoryObj.COSE_ACCOUNT_TOOLS, "close account tools"))
		{
		click(InvoiceHistoryObj.COSE_ACCOUNT_TOOLS, "close account tools");
		}
		else{
			//Do Nothing
		}
		
		click(CommonObj.ACCOUNT_TOOLS_PRODUCTDETAIL_PAGE, "Account tools menu icon");
		click(CommonObj.getAccountToolsMenuProductDetailPage(toolsMenuName), "Account tools menu");
		click(CommonObj.getAccountToolsDDProductDetailPage(toolsMenuName, dropDown), "Select account tools");// ---Tools,Customer-Owned-Inventory
		isElementPresent(CartObj.Current_product_groups, " Current Product Groups page is opened");
		click(CommonObj.getCompanyStandardsProductGroup(productGroup, productName),
				"select product from product group");
		String description=getText(CartObj.DESCRIPTION, "Description");
		String stock=getText(CartObj.STOCK, "Stock");

		reporter.SuccessReport("Check the Stock in Configuration Section on Product Standards Page", "Product Stock is Exists", "Part: "+description+"Stock: "+stock);
		if(!isCheckBoxSelected(CartObj.CHECK_BOX)) {
			click(CartObj.CHECK_BOX, "Check box");
		}
		else {
			reporter.failureReport("Checkbox status", "check box is not visible", "", driver);
		}
		Thread.sleep(2000);
		
		click(CommonObj.ADD_TO_ORDER, "ADD To Order Button is Clicked");
		Thread.sleep(3000);
		if(isVisibleOnly(CommonObj.VIEW_CART_PRODUCT_GROUP, "View cart Link")){
			click(CommonObj.VIEW_CART_PRODUCT_GROUP, "View cart Link","View cart Link");
			reporter.SuccessReport("verify View cart Link on Items added to cart Popup on Company standards", "View cart Link is visible and clicked","");
		}else{
			reporter.failureReport("verify View cart Link on Items added to cart Popup on Company standards", "View cart Link is not visible","");
		}
		
		return(stock);
		
	}
	
	public Integer getCOIQuantityFromStock(String stock){
        String[] subStrings1 = stock.split("\\|");
        for(String subString1 : subStrings1)
        {
        	String[] subStrings2 = subString1.trim().split("\\:");
        	
        	if(subStrings2[0].trim().equals("COI"))
            {
                //System.out.println("COI is"+subStrings2[1].trim());
                
                return Integer.parseInt(subStrings2[1].trim());
            }
        }
		
		
		return 0;
		
	}
	
	public Integer getCSIQuantityFromStock(String stock){
		
        String[] subStrings1 = stock.split("\\|");
        for(String subString1 : subStrings1)
        {
        	String[] subStrings2 = subString1.trim().split("\\:");
        	
        	if(subStrings2[0].trim().equals("CSI"))
            {
                //System.out.println("COI is"+subStrings2[1].trim());
                
                return Integer.parseInt(subStrings2[1].trim());
            }
        }
		
		
		
		return 0;
		
	}
	public Integer getStockFromTools(String stock) {
		String[] subStrings1 = stock.split("\\|");
		for(String subString1 : subStrings1)
        {
        	String[] subStrings2 = subString1.trim().split("\\s+");
        	
        	if(subStrings2[1].trim().equals("In-stock"))
            {
                //System.out.println("COI is"+subStrings2[1].trim());
                
                return Integer.parseInt(subStrings2[0].trim());
            }
        }
		
		
		
		return 0;
	}
	public void coiInCartPage(Integer coiQuantity) throws Throwable {
		String partNumber=getText(CartObj.Cart_Prod_Insight_Part_Number,"part number");
		String coi=getText(CartObj.COI_IN_CART,"COI in cart page");
		System.out.println("coi"+coi);
		if(partNumber!=null && coiQuantity>0) {
			reporter.SuccessReport("Check Product with COI Value in the Cart Page", "Product with COI Value are Exists and As Expectedin the Cart", "Part Number:"+partNumber +"COI: "+coi);
		}
		else {
			reporter.SuccessReport("Check Product with COI Value in the Cart Page", "Product with COI Value are not Exists","", driver);
		}
		
	}
	
	public void csiInCartPage(Integer csiQuantity) throws Throwable {
		String partNumber=getText(CartObj.Cart_Prod_Insight_Part_Number,"part number");
		String csi=getText(CartObj.CSI_IN_CART,"CSI in cart page");
		if(partNumber!=null && csiQuantity>0) {
			reporter.SuccessReport("Check Product with CSI Value in the Cart Page", "Product with CSI Value are Exists and As Expectedin the Cart", "Part Number:"+partNumber +"CSI: "+csi);
		}
		else {
			reporter.SuccessReport("Check Product with CSI Value in the Cart Page", "Product with CSI Value are not Exists","", driver);
		}
		
	}
	
	public void stockInCartPage(Integer stockInTools) throws Throwable {
		String stock=getText(CartObj.CART_PROD_STOCK_RECENTLYADDEDTEM,"CartProductStockForRecentlyAddedItem");
		String partNumber=getText(CartObj.Cart_Prod_Insight_Part_Number,"part number");
		if(partNumber!=null && stockInTools>0) {
			reporter.SuccessReport("Check Product with stock Value in the Cart Page", "Product with stock Value are Exists and As Expectedin the Cart", "Part Number:"+partNumber +"stock: "+stock);
		}
		else {
			reporter.SuccessReport("Check Product with stock Value in the Cart Page", "Product with stock Value are not Exists","", driver);
		}
		
	}
	
	/**
	 * 
	 * @param toolsMenuName
	 * @param dropDown
	 * @param productGroup
	 * @param productName	 * @param Text_COI
	 * @throws Throwable
	 */
	public void verifyCSIpart(String toolsMenuName, String dropDown, String productGroup, String productName,
			String Text_COI) throws Throwable {
		Thread.sleep(20000);
		if(isElementPresent(InvoiceHistoryObj.COSE_ACCOUNT_TOOLS, "close account tools"))
		{
		click(InvoiceHistoryObj.COSE_ACCOUNT_TOOLS, "close account tools");
		}
		else{
			//Do Nothing
		}
		
		click(CommonObj.ACCOUNT_TOOLS, "Account tools menu icon");
		click(CommonObj.getAccountToolsMenu(toolsMenuName), "Account tools menu");
		click(CommonObj.getAccountToolsDD(toolsMenuName, dropDown), "Select account tools");// ---Tools,Customer-Owned-Inventory
		isElementPresent(CartObj.Current_product_groups, " Current Product Groups page is opened");
		click(CommonObj.getCompanyStandardsProductGroup(productGroup, productName),
				"select product from product group");
		List<WebElement> myList = driver.findElements(CartObj.verificationText(Text_COI));
		for (int i = 0; i < myList.size(); i++) {

			if (myList.get(i).isDisplayed()) {

				reporter.SuccessReport("Products With ", "" + myList.get(i).getText() + "are displayed", "");
			} else {
				reporter.failureReport("Product With COI", "" + myList.get(i).getText() + "are not displayed", "",
						driver);
			}
		}
		List<WebElement> myList1 = driver.findElements(CartObj.ADD_Checkbox_forCOIproducts);
		int l1 = myList1.size();
		for (int i = 0; i < myList1.size(); i++) {
			if (myList1.get(i).isDisplayed()) {
				myList1.get(i).click();
				reporter.SuccessReport("Add Check Box is Clicked", "Add Check Box is Clicked", "");
			} else {
				reporter.failureReport("Add Check Box is Not Clicked ", "Add Check Box Not is Clicked", "", driver);
			}
		}
		click(CommonObj.ADD_TO_ORDER, "ADD To Order Button is Clicked");
		Thread.sleep(3000);
		click(CartObj.closeicon_addtocart, "Add to cart Popup is Closed");
		commonLib.clickCart();
		List<WebElement> myList2 = driver.findElements(CartObj.Productname_at_cart);
		int l2 = myList2.size();
		if (l1 == l2) {
			reporter.SuccessReport("Products with CSI added to CART", "Products with CSI added to CART", "");
		} else {
			reporter.failureReport("Products with CSI not added to CART", "Products with CSI not added to CART", "",
					driver);
		}
		click(CartObj.EMPTY_CART, "Cart is Empty");
	}

	/**
	 * 
	 * @param toolsMenuName
	 * @param dropDown
	 * @param productGroup
	 * @param productName
	 * @param Text_COI
	 * @throws Throwable
	 */
	public void verifyReservedProducts(String toolsMenuName, String dropDown, String productGroup, String productName,
			String Text_COI) throws Throwable {
		Thread.sleep(20000);
		if(isElementPresent(InvoiceHistoryObj.COSE_ACCOUNT_TOOLS, "close account tools"))
		{
		click(InvoiceHistoryObj.COSE_ACCOUNT_TOOLS, "close account tools");
		}
		else{
			//Do Nothing
		}
		
		click(CommonObj.ACCOUNT_TOOLS, "Account tools menu icon");
		click(CommonObj.getAccountToolsMenu(toolsMenuName), "Account tools menu");
		click(CommonObj.getAccountToolsDD(toolsMenuName, dropDown), "Select account tools");// ---Tools,Customer-Owned-Inventory
		Thread.sleep(2000);
		isElementPresent(CartObj.Current_product_groups, " Current Product Groups page is opened");
		click(CommonObj.getCompanyStandardsProductGroup(productGroup, productName),
				"select product from product group");
		List<WebElement> myList = driver.findElements(CartObj.verificationText(Text_COI));
		for (int i = 0; i < myList.size(); i++) {

			if (myList.get(i).isDisplayed()) {

				reporter.SuccessReport("Products With ", "" + myList.get(i).getText() + "are displayed", "");
			} else {
				reporter.failureReport("Product With COI", "" + myList.get(i).getText() + "are not displayed", "",
						driver);
			}
		}
		List<WebElement> myList1 = driver.findElements(CartObj.ADD_Checkbox_forCOIproducts);
		int l1 = myList1.size();
		for (int i = 0; i < myList1.size(); i++) {
			if (myList1.get(i).isDisplayed()) {
				myList1.get(i).click();
				reporter.SuccessReport("Add Check Box is Clicked", "Add Check Box is Clicked", "");
			} else {
				reporter.failureReport("Add Check Box is Not Clicked ", "Add Check Box Not is Clicked", "", driver);
			}
		}
		click(CommonObj.ADD_TO_ORDER, "ADD To Order Button is Clicked");
		Thread.sleep(3000);
		click(CartObj.closeicon_addtocart, "Add to cart Popup is Closed");
		commonLib.clickCart();
		List<WebElement> myList2 = driver.findElements(CartObj.Productname_at_cart);
		int l2 = myList2.size();
		if (l1 == l2) {
			reporter.SuccessReport("Products with Reserved option added to CART",
					"Products with Reserved option added to CART", "");
		} else {
			reporter.failureReport("Products with Reserved option not added to CART",
					"Products with Reserved option not added to CART", "", driver);
		}
		click(CartObj.EMPTY_CART, "Cart is Empty");
	}

	/**
	 * Method is to verify the cart bread crumb.
	 * 
	 * @throws Throwable
	 */
	public void verifyCartBreadCrumb() throws Throwable {
		if (isElementVisible(CartObj.CART_LABEL_ON_CART_PAGE,60, "cart page")) {
			
			reporter.SuccessReport("Verify cart page", "User successfully navigated to cart page", "PageDetails : Cart");
		} 
		else if(isElementNotPresent(CartObj.CART_LABEL_ON_CART_PAGE, "cart page")){
			 refreshPage();
			 waitForVisibilityOfElement(CartObj.CART_LABEL_ON_CART_PAGE, "cart page");
		}
			 else {
		
			reporter.failureReport("Verify cart page", "Cart page is not displayed", "", driver);
		}
	}
public void getpartnumberIncartpage() throws Throwable {
	getText(CartObj.Insightpartnumber, "Insightpartnumber");
	getText(CartObj.MfrPartNumber, "Insightpartnumber");
	
}
	/**
	 * This method is to verify the contract is present in the cart page.
	 * 
	 * @throws Throwable
	 */
	public void verifyTheItemIsAddedUnderContractInCartPage() throws Throwable {
		String contractName = getText(CartObj.CONTRACT_IN_CART, "contract name");
		if (contractName.isEmpty() || contractName == null) {
			reporter.failureReport("Verify contract present in cart page", "Contract in Cart page is not displayed", "",
					driver);
		} else {
			reporter.SuccessReport("Verify contract present in cart page",
					"Contract in Cart page is displayed as : " + contractName, "Contract : "+contractName);
		}
	}

	public void verifyTheExportedCart(List<String> downloadedExcelContent, List<String> acutalContent)
			throws Throwable {
		if (downloadedExcelContent.equals(acutalContent)) {
			reporter.SuccessReport("Verify export cart content", "Export cart content verification is successfull",
					acutalContent.get(i));
		} else {
			reporter.failureReport("Verify export cart content", "Export cart content verification is successfull",
					acutalContent.get(i), driver);
		}

	}

	/**
	 * Method is to verify the quantity given in the product group displayed in the
	 * cart page
	 * 
	 * @param quantity
	 * @param expectedQty
	 * @throws Throwable
	 */
	public void verifyProductGroupQuantityInCart(List<String> quantity, String expectedQty) throws Throwable {
		for (i = 0; i < quantity.size(); i++) {
			if (quantity.get(i).equals(expectedQty)) {
				reporter.SuccessReport("Verify product Quantity ", "Product Quantity verification is successfull for ("+i+") product", "Quantity is: "+expectedQty);
			} else {
				reporter.failureReport("Verify product Quantity ", "Product Quantity verification failed. Actual is: "+quantity.get(i),
						"", driver);
			}
		}

	}

	/**
	 * Method is to verify the columns in the Export as excel file.
	 * @param sheetName
	 * @param rowNumber
	 * @param columnHeaders
	 * @throws Throwable
	 */
	public void verifyExportFile(String sheetName, String rowNumber, String columnHeaders) throws Throwable {
		Thread.sleep(10000);
		String sfile = System.getProperty("user.dir") + "\\" + "DownloadedFiles" + "\\" + "exportCart.xls";
		File file = new File(sfile);
		if (file.exists()) {
		List<String> downloadedExcelContent = CommonLib.readRowFromExcel(sfile, sheetName, Integer.parseInt(rowNumber));
		List<String> acutalContent = Arrays.asList(columnHeaders.split(","));
		System.out.println("Compare content" + downloadedExcelContent.equals(acutalContent));
		if (downloadedExcelContent.equals(acutalContent)) {
			reporter.SuccessReport(columnHeaders,  "columns are avilable in exportCart.xls", "columns: "+columnHeaders);
		} else {
			reporter.failureReport(columnHeaders, columnHeaders+ " are not avilable", "", driver);
		 }
		}else {
			reporter.failureReport("ExportCart Excel File", "File dose not exists", "", driver);
		}
		System.out.println("File Deletion :" + file.delete());
		if (file.exists()) {
			file.delete();
			reporter.SuccessReport("ExportCart Excel File", "File closed", "");
		}else {
			// do nothing
		}
	}

	public String getCartQuantity(String partNumber) throws Throwable {
		String quantity = getAttributeByValue(CartObj.quantityInCart(partNumber), "Quantity in cart");
		return quantity;
	}

	/**
	 * This method is to verify RP_LNL_Txt text
	 * 
	 * @throws Throwable
	 * 
	 */
	public void verifyOnlyOneItemInCartPage() throws Throwable {
		List<WebElement> trashicon = driver.findElements(CartObj.TRASH_ICON);
		if (trashicon.size() == 1) {
				reporter.SuccessReport("Only Zero Usage Part in the Cart",
						"Only one part in cart", "");

			}
		else {
		reporter.failureReport("Only Zero Usage Part in the Cart",
				"More than one part in cart", "", driver);
	  }
	}

	
	public String getTextProductdetailPageAndVerifyCSICOI() throws Throwable {
		String Text = getText(CartObj.CSICOI_PRODUCTDEATILPG,"Product with CSI/COI Stock:");
		String CSIorCOIText[]=Text.split("\\|");
		System.out.println(CSIorCOIText[1]);
		return CSIorCOIText[1];
		
	}
	public void COICSIPrice() throws Throwable {
		getText(CartObj.CSI_COI_PRODUCT_PRICE, "Product with COI/CSI Price: ");
		
	}
	public void verifyCOICSI(String TEXT,String COI,String CSI) throws Throwable {			
		if(TEXT.contains(COI)) {
			reporter.SuccessReport("Product Deatil Page::","Product with COI/CSI Price in the Product Details Page", ""+TEXT+"");
		}
		else if(TEXT.contains(CSI)){
			reporter.SuccessReport("Product Deatil Page::","Product Contains CSI", "CSI");
		}else {
			reporter.failureReport("Product Deatil Page::","Product Does not contains COI or CSI", "");
		}	
	}
	
	/**
	 * Method is to click on the account tools side menu and select options
	 * 
	 * @param toolsMenuName
	 * @param dropDown
	 * @throws Throwable
	 */
	public void clickOnSideMenuSelectAccountToolOptionsInProductDetailsPage(String toolsMenuName, String dropDown) throws Throwable {
		Thread.sleep(10000);
		if(isElementPresent(InvoiceHistoryLib.COSE_ACCOUNT_TOOLS, "close account tools")) {
			click(InvoiceHistoryLib.COSE_ACCOUNT_TOOLS, "close account tools");
		}
		click(CommonObj.ACCOUNT_TOOLS_PRODUCTDETAIL_PAGE, "Account tools menu icon");
		click(CanadaObj.getAccountToolsMenu(toolsMenuName), "Account tools menu");
		click(CommonObj.getAccountToolsDD(toolsMenuName, dropDown), "Select account tools");
	}
	
	public void DefualtCarrierOption() throws Throwable {
		if (isVisibleOnly(OrderObj.SELECT_CARRIER_DD, "carrier Drop down")) {
		String Text=getText(OrderObj.SELECT_CARRIER_DD,"Default Carrier Option::");
		System.out.println(Text);
		}
	}
	/**
	 * Method is to verify carriers in checkout
	 * 
	 * @throws Throwable
	 */
	public void verifyCarriers(String carrier,String UPS) throws Throwable {
		if (isVisibleOnly(OrderObj.SELECTARRIER, "carrier Drop down")) {
		String Text=getText(OrderObj.SELECTARRIER,"carrier Drop down");
		click(OrderObj.SELECTARRIER, "carrier Drop down");
			String carriers[] = carrier.split(",");
			for (i = 0; i < carriers.length; i++) {
				if (isVisibleOnly(OrderObj.verifyCarrier(carriers[i]),carrier)) {
				} else {
					reporter.failureReport("verify carrier options::", carriers[i] + " is not present", carriers[i],
							driver);
				}
			}
			reporter.SuccessReport("verify carrier options::", "Selected Options::"+carrier+"", "Available carriers::"+carrier);
			if(isElementNotPresent(OrderObj.verifyCarrier(UPS),"Verify UPS")){
			reporter.SuccessReport("verify carrier options::", " Expected Carrier Exist","UPS  Does not Exits in Available Carriers List");
		}else {
			reporter.SuccessReport("verify carrier options::", " Expected Carrier Exist","UPS Exits in Available Carriers List");
		}
	}
		else {
			reporter.SuccessReport("verify carrier options::", " Expected Carrier Exist","UPS Exits in Available Carriers List");

		}

	}		
	public boolean verifyCartPageAvailablity() throws Throwable{
		return isVisibleOnly(lblCartLebel,"Cart Header");
		}
	
	public void verifySLPAProductOnCart(String itemInCart) throws Throwable {
		waitForVisibilityOfElement(CartObj.getItemIncartByInsightPartNumber(itemInCart), "Item in cart");
		if (isElementPresent(CartObj.getItemIncartByInsightPartNumber(itemInCart), "part number")) {
			reporter.SuccessReport("Verify SPLA Product on CART Page ", " SPLA Product Exists and Verified", "Insight part: "+itemInCart);
		} else {
			reporter.failureReport("Verify SPLA Product on CART Page", "ITEM SLPA " + itemInCart + "is not ADDED TO CART",
					itemInCart, driver);

		}
	}
	public void clickMorePricesAvilableInSearchResultPage() throws Throwable {
		isElementPresent(CartObj.MORE_AVAILABLE_PRICES, "More AVilable Prices");
		click(CartObj.MORE_AVAILABLE_PRICES, "More AVilable Prices", "More AVilable Prices");

	}

}