package com.insight.ObjRepo;

import org.openqa.selenium.By;

import com.insight.accelerators.ActionEngine;

public class ShipBillPayObj extends ActionEngine{
	
	//storedcards
	public static By PaymentandcardsText=By.xpath("//h3[contains(text(),'Stored Procurement Cards')]/following-sibling::p");
    public static By PaymentTextinfoText=By.xpath("//p[@class='payment-info--loginas__warning']");
    public static By Requesitor(String RefNum){
      return By.xpath("//a[contains(text(),'"+RefNum+"')]");
    }
    public static By STOREDCARDS=By.xpath("//select[@id='insightPaymentStoreCards']//option");
    public static By APPROVEREQUISITOR_UPDATEBUTTON=By.xpath("//div[@id='rsUpdatebuttons']//a/span[contains(text(),'UPDATE ')]");
    public static By verifyShippingCarrier(String shippingCarrier) {
    	return By.xpath("//label[text()='Shipping carrier:']//parent::div//p[text()='"+shippingCarrier+"']");
    }
    public static By PAYMENTINFO_LABEL=By.xpath("//div[@id='bodyContent']//h2[contains(text(),'Payment Information')]");
    public static By ORDER_DETAILS_BUTTON=By.xpath("//strong[text()='Order details']//parent::a");
    public static By REVIEW_REQUISTION_BUTTON=By.xpath("//button[@type='submit' and contains(text(),'Review requisition')]");
    public static By PLACE_REQUISITION_BUTTON=By.xpath("//section[@class='cart cart']/following::div[@class='cart-summary-container']//button[contains(text(),'Place requisition')]");
    public static By REFERENCE_NUMBER=By.xpath("//div[@class='row expanded align-justify']//label[contains(text(),'Reference number:')]/p");
    public static By APPROVAL_MANAGEMENT_LABEL=By.xpath("//div[@id='searchRequisition']//div//h1[contains(text(),'Approval Management')]");
    public static By ORDERSBUTTON=By.xpath("//div[@id='offCanvasNavPlaceholder']//ul[@class='metismenu']//a[contains(text(),'Orders')]");
    public static By STOREDCARDS_DD=By.xpath("//select[@id='insightPaymentStoreCards']");
  //US webgroup
    public static By USA_WEBGROUP=By.xpath("//span[text()='Web Group – USA - Praxair']");
    public static By CLICK_HERE=By.xpath("//span[text()='Click Here']//parent::a");
    public static By CONTINUE_BUTTON=By.xpath("//button[text()='Continue']");
    public static By CANNADA_WEBSITE=By.xpath("//span[text()='Web Group – Canada - Praxair']");
    //cart
    public static By SUB_TOTAL=By.xpath("//div[@class='columns small-12 large-3 print-5 print-offset-7']//div[text()='Subtotal']//parent::div");
    public static By SUB_TOTAL_CURRENCY=By.xpath("//div[@class='columns small-12 large-3 print-5 print-offset-7']//div[text()='Subtotal']//parent::div//span[@class='iw-currency__code']");
    
    //Additinal Info
    public static By ADD_ADDITIONAL_INFO=By.xpath("//h3[text()='Additional information']");
    public static By NAME=By.xpath("//input[@name='userContact.name']");
    public static By PHONE=By.xpath("//input[@name='userContact.phone']");
    public static By EMAIL=By.xpath("//input[@name='userContact.email']");
    //Summary in cart
    public static By getAmountsInSummary(String amountType) {
    	return By.xpath("//section/following::div[@class='columns small-12 large-3 print-5 print-offset-7']//div[text()='"+amountType+"']//parent::div//span[@class='iw-currency__amount']");
    
    }
   //3rd party carrier
   public static By SUMMARY_AMOUNT=By.xpath("//section/following::div[@class='columns small-12 large-3 print-5 print-offset-7']//span[text()='Shipping estimate']");

    public static By STOREDCARDS_ENDUSER=By.xpath("//div[@id='cardDes_1919']");
    public static By STOREDCARDS_DROPDOWN_ENDUSER=By.xpath("//select[@id='insightPaymentStoreCards']");
    public static By BILLMYCARRIER_CHECKBOX=By.xpath("//input[@name='billMyCarrier']");
    public By shippingcarrier(String text){
		return By.xpath("//label[contains(text(),'Shipping carrier:')]/following-sibling::p[contains(text(),'"+text+"')]");
		}
    public static By BILLMYCARRIER_DROPDOWN=By.xpath("//div[contains(text(),'Choose a carrier')]");
    public  By BILLMYCARRIEROPTION(String defcarrier){
        return By.xpath("//div[@class='Select-value']/span[contains(text(),'"+defcarrier+"')]");//UPS|6E28E4
        }
    public static By DEFUALTCARRIER_INPUT=By.xpath("//input[@id='defaultCarrAccTextID']");
    public static By CARRIER_ACCOUNT=By.xpath("//label[contains(text(),'Carrier account:')]");
    public static By CHOOSENCARRIERNOTMATCH_MSG=By.xpath("//span[contains(text(),'The chosen carrier account number does not match')]");
    public static By CARRIERS_DROPDOWN=By.xpath("//div[@class='Select Select__shipping-carrier Select--single is-searchable has-value']");
    public static By FEDX_CARRIER     =By.xpath("//div[@class='Select-menu-outer']//div[@class='Select-option'][contains(text(),'FedEx')]");
    
    
    public static By DISABLED_OPTIONS_SHIPPINGOTIONS(String carrier){
    	return By.xpath("//input[@disabled]//parent::label//Strong[text()='"+carrier+"']");
    }
    
   //SaveOrderTemplateIPS
     public static By WG_HDL_Lst=By.xpath("//span[contains(text(),'WG_HDL_Lst')]/following-sibling::select[@class='form__field form__select ']");
     public By STOREDADDRESS(String storedaddress){
         return By.xpath("//p[@class='no-margin-bot']/strong[contains(text(),'"+storedaddress+"')]");//QTPIPSPricing
     }
     
    
   public static By RADIOBUTTON_DEFUALTADDRESS=By.xpath("//label[contains(text(),'Address')]/preceding::div/input[@name='defaultAddress']");
   public static By RADIOBUTTON=By.xpath("//label[contains(text(),'')]/preceding::div/input[@name='defaultAddress']");
   public static By CONTINUE_BUTTONSTOREDADDRESS=By.xpath("//button[@class='button expanded no-margin-bot' and contains(text(),'Continue')]");
   public static By WELCOMEMSG_DASHBOARD=By.xpath("//div[@class='welcome-content']/h1");
   
   public static By CANCELBUTTON_STOREDADDRESS=By.xpath("//button[@type='button' and contains(text(),'Cancel')]"); 
   public static By CONTINUE_BUTTON_STOREDADDRESS=By.xpath("//button[@type='button' and contains(text(),'Continue')]");
   public static By SEARCHFIELD_STOREDADDRESS=By.xpath("//div[@class='input-group']//input[@id='storedAddress-search']");
   public static By SEARCH_BUTTON=By.xpath("//div[@class='input-group-button']//button[@type='button']");
   public static By CHECKBOXES_SHIPPINGADDRES=By.xpath("//div[@id='ship_tableDiv']//td[@class='linkedField']//input[@type='checkbox']");
   public static By SUCCESSMSG_SHIPPINGADDRESS=By.xpath("//div[@id='ship_successDiv']");
   public static By UPDATEBUTTON_SHPPINGADDRESS=By.xpath("//div[@class='buttons']//a//span[contains(text(),'Update')]");
   public static By REMOVE_DEFAULTADDRESS=By.xpath("//a[@id='removeDefaultAddress' and contains(text(),'Remove Default')]");
   public static By SHOWALLDROPDOWN=By.xpath("//select[@id='ship_pagingSelect']");
  public static By CREATEDADDRES(String Company){
  	return By.xpath("//label[contains(text(),'Company:')]/following-sibling::p[contains(text(),'"+Company+"')]");
  }
  public static By LINKEDACCOUNTSDROPDOWN=By.xpath("//select[@id='ship_linking']");
  public static By DEFUALTADRESSES=By.xpath("//td[contains(text(),'  insight ')]/following-sibling::td//input[@id='21297179_ship_defaultAddress']");
  public static By EDITBUTTON_SHIPPINGADDRES=By.xpath("//h3[contains(text(),'Shipping address')]/following-sibling::a[contains(text(),'Edit')]");
  public static By DEFUALT_ADDRESS=By.xpath("//div[@id='defaultBillingAddrDiv']");
  public static By DEFUALTEMAIL_ADDTIONALNOTIFICATIONS=By.xpath("//input[@id='addEmailAddress']");
  //credit card override
   public static By VISA_CARD_ERROR_MESSAGE=By.xpath("//div[text()='VISA card type is not supported']");

   public static By CREDITCARDOPTION(String Creditcard){
		 return By.xpath("//div[@class='Select-menu-outer']/div/div[@aria-label='"+Creditcard+"']");
	 }
	public static By PAYMENT_METHOD_DD=By.xpath("//div[@class='Select Select__Payment-method Select--single has-value']");
	public static By OPTIONSINCLUDED_REQUESTORGRP=By.xpath("//select[@id='GrpPaymntSelected']");
	public static By  ALLOWEDOPTIONS_CHECKOUTSETTINGS=By.xpath("//select[@id='payAllowedSel']");
	public static By DEFUALTPAYMENTTYPE=By.xpath("//select[@id='defaultPayTypeSelID']");
	public static By SUCCESMSG_PAYMENTOPTIONS=By.xpath("//div[@id='checkoutSettUpdateMsg' and contains(text(),'Payment options updated successfully')]");
	public static By SELECT_CARRIER_DD=By.xpath("//div[@class='Select-value']//span[contains(text(),'FedEx')]");
	public static By GROUND_CAREER=By.xpath("//label[@class='form__label--inline']//input/following-sibling::span/strong[contains(text(),'Ground')]");
	public By SAVEDCART(String cartName){
		 return By.xpath("//h3[contains(text(),'"+cartName+"')]");
	}
	public  By ADDTOCART_SAVEDCARTS(String cartName){
	return By.xpath("//h3[contains(text(),'"+cartName+"')]/parent::div//following-sibling::div/button[contains(text(),'Add to cart')]");
	}
	public  By Deletesavedcart(String cartName){
		return By.xpath("//h3[contains(text(),'"+cartName+"')]/parent::div//following-sibling::div/button[contains(text(),'Delete')]");
		}
	public static By DIALOGUEBOX_DELETECART=By.xpath("//div[@class='c-modal__container']");
	public static By YESBUTTON_DELETECART=By.xpath("//div[@class='c-modal__container']//div/button[contains(text(),'Yes')]");
	public static By  ADDEDTOCART_POPUP=By.xpath("//h2[contains(text(),'Added to cart')]");
	public static By CHECKOUT_SAVEDCARTS=By.xpath("//button[contains(text(),'Continue to checkout')]");
	public static By SHIPINGCAREER=By.xpath("//label[contains(text(),'Shipping carrier:')]/following-sibling::p[contains(text(),'FedEx (Ground)')]");
	public static By  WG_LNL_Txt=By.xpath("//label//span[contains(text(),'WG_LNL_Txt')]/following-sibling::input");
	public static By WG_LNL=By.xpath("(//input[@name='smartTracker.st-593839'])[1]");
	public static By CopyToALL=By.xpath("(//a[contains(text(),'Copy to all')])[1]");
	public static By WG_LNL_TEXT=By.xpath("(//input[@value='QTP1'])[2]");

	public static By SAVE_CART_INPUT_FIELD=By.xpath("//span[contains(text(),'Please enter a name for your cart ')]/following-sibling::input");
	//Header menu
	public static By STATE_AND_LOCAL_GOVT=By.xpath("//a[text()='State & local government']");
	public static By STATE_AND_LOCAL_GOVT_DROPDOWN=By.xpath("//a[text()='State / Local']");
	public static By selectDropdownOption(String option) {
		return By.xpath("//a[text()='"+option+"']");
	}
	public static By contractDropdown(String contract) {
		return By.xpath("//a[text()='"+contract+"']");
	}
	
	
	public static By selectCountry(String country) {
		return By.xpath("//a[text()='"+country+"']");
	}
	
	public static By SELECT_CONTRACT=By.xpath("//a[@id='contract-detail-drop-current']");
	public static By contractOption(String contractOption) {
		return By.xpath("//a[text()='"+contractOption+"']");
	}
	public static By BROWSE_PRODUCTS=By.xpath("//a[text()='Browse products']");
	public static By verifyDisplayedProductContract(String contract) {
		return By.xpath("//p[contains(text(),'"+contract+"')]");
	}
	public static By CHANGE=By.xpath("//a[text()='change']");
	
	public static By CONTINUE_BUTTON_STORED_ADDRESSES=By.xpath("//footer//button[text()='Continue']");
	public static By STOREDADDRESS_LINK=By.xpath("//span[contains(text(),'Stored addresses')]");
	public static By US_ADDRESSES=By.xpath("//label[@class='stored-address__label' and text()='insight']//parent::div//parent::div//input");
	public static By SELECT_A_CARRIER=By.xpath("//div[contains(text(),'Select a shipping carrier')]");
	public static By selectCarrierDD(String carrier) {
		return By.xpath("//*[contains(text(),'"+carrier+"')]");
	}
	
	public static By selectShippingMethod(String shippingMethod) {
		return By.xpath("//label[@class='form__label--inline']//input/following-sibling::span/strong[contains(text(),'"+shippingMethod+"')]");
	}
	
	 public static By STOREDCARD=By.xpath("//select[@id='insightPaymentStoreCards']");
	 public static By NO_SHIPPING_CARRIER=By.xpath("//span[contains(text(),'No carrier preference')]");
     public static By GROUND_CARRIER=By.xpath("//input[@type='radio']/parent::label/span/strong[contains(text(),'Ground')]");
	 public static By EXPANDSEARCH_APPROVALMANAGEMENT=By.xpath("//div[@id='expndsearch1']/a");
	 public static By CALENDER_IMG=By.xpath("//input[@id='Start_dt']/following-sibling::img");
	 public static By MONTH_CALENDER=By.xpath("//div[@class='ui-datepicker-title']/span[@class='ui-datepicker-month']");
     public static By YEAR_CALENDER=By.xpath("//div[@class='ui-datepicker-title']/span[@class='ui-datepicker-year']");
	 public static By PREVBUTTON_CLAENDER=By.xpath("//a[@title='Prev']");
	 public static By DATE=By.xpath("//a[@class='ui-state-default' and text()='1']");
	 public static By SEARCH=By.xpath("(//a[@id='simpsearch1']/span)[1]");
     public static By MONTHWITHTEXT=By.xpath("//div[@class='ui-datepicker-title']/span[@class='ui-datepicker-month' and contains(text(),'January')]");
     public static By YEARWITHTEXT=By.xpath("//div[@class='ui-datepicker-title']/span[@class='ui-datepicker-month' and contains(text(),'2019')]");

     public static By STOREDCARDNUM = By.xpath("//p[@id='iw-checkout__paymentsfc-stored-card-type']");
     public static By STOREDCARDNAME= By.xpath("//p[@id='iw-checkout__paymentsfc-card-holder-name']");
     public static By EXPIRATIONDATE=By.xpath("//label[@for='iw-checkout__paymentsfc-expired']/p");
     public static By THIRDPARTYCARRIER=By.xpath("//div[contains(text(),'Third party carrier is required')]");
     public static By LOGOUT=By.xpath("//a[contains(text(),'Logout')]");
     public static By partNum(String PartNum) {
 		return By.xpath("//td[contains(text(),'"+PartNum+"')]");
 		
     } 	

     public static By CALANDER = By.xpath("//span[@class='ion ion-calendar ion--right date-picker__icon']");
     public static By SEARCH_FIELD = By.xpath("//input[@id='storedAddress-search']");
     public static By search_Button=By.xpath("//span[@class='ion-search']/parent::button");
     public static By New_address=By.xpath("//p[@id='iw-checkout__address-section-selected-address']");
 	public static By SAVE_AS_TEMPATE=By.xpath("//div[@class='columns small-12 large-3 print-5 print-offset-7']//span/button[contains(text(),'Save order template')]");
 	public static By SAVE_AS_TEMPATE_INPUT=By.xpath("//label[@for='Saved Name']/input");
public static By CLEAR_CHECKBOX=By.xpath("//input[@name='clearCheckbox']");
public static By SUCCES_MSG=By.xpath("//p[@class='save-for-later__modal-success']");
public static By CONTINUE_BTN=By.xpath("//button[contains(text(),'Continue')]");
public static By Date(String date) {
return By.xpath("//div[contains(text(),'"+date+"')]");
}
public static By EXPAND_LNL=By.xpath("//span[@class='ion ion-ios-plus-outline']");
public static By CITY_LBL = By.name("city");
public static By ZIPCODE = By.name("zipCode");
public static By SELECT_STATE= By.name("state");
public static By SELECT_COUNTRY=By.name("country");
public static By SHIPPINGADDRESS_CONTINUE_BTN=By.xpath("//button[@class='button expanded section__button no-margin-bot']");
public static By ADDRESSVALIDATION_WINDOW_HDR= By.xpath("//h3[@class='iw-modal__heading']");
public static By SAVEADDRESS_BTN = By.xpath("//div[@class='column small-6 medium-shrink']/button");
public static By EDIT_LINK=By.xpath("//h3[contains(text(),'Shipping address')]/following-sibling::a");
public static By SHIPPING_ADDRES=By.xpath("//p[@id='iw-checkout__address-section-selected-address']");
public static By SHIPPING_ADDRESES=By.xpath("//p[@id='iw-checkout__address-section-selected-address']/br[1]");
public static By SHIPPING_ADDRESESSECONDLINE=By.xpath("//p[@id='iw-checkout__address-section-selected-address']/br[2]");
public static By STOCKONLY_SEARCHRESULTS=By.xpath("//div[@class='filter-item js-filter-item']/a[@class='button call-to-action']");



}



