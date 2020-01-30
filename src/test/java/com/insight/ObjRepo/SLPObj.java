package com.insight.ObjRepo;

import org.openqa.selenium.By;

import com.insight.accelerators.ActionEngine;

public class SLPObj extends ActionEngine{
	
	public static By PRICE_PRODUCTDETAILPAGE=By.xpath("//div[@id='js-product-detail-pricing-target']//p[@class='prod-price']");
	
    public static By Priceincart(String Partnum) {
		return By.xpath("//a[contains(@href,'"+Partnum+"')]/parent::div/following-sibling::div//span[contains(text(),'Unit price')]/following-sibling::span/span[@class='iw-currency__amount']");

	   }
    
    public static By APPROVE_RADIO_BTN=By.xpath("//input[@id='appRadioButton']");
   
    /** to get the success message after the requisition approval**/
    public static By getRequisitionApprovedMsg(String refNum){
          return By.xpath("//div[@id='hiddenMessageAlert'and contains(text(),'Requisition "+refNum+" has been Approved.')]");
    }

    public static By deploy_date(String Partnum)
    {
    	return By.xpath("//a[contains(@href,'"+Partnum+"')]/ancestor::div[@class='cart__item']//div[@class='proration__deploy-details--date']");
       }
    public static By licensetype(String Partnum) {
    	return By.xpath("//a[contains(@href,'"+Partnum+"')]/ancestor::div[@class='cart__item']//div[contains(text(),'License type:')]/parent::div/div/div[@class='proration__deploy-details--date'][contains(text(),'New')]");

      }
    
    public static By unpaidLicenseType(String partNum) {
    	return By.xpath("//a[contains(@href,'"+partNum+"')]/ancestor::div[@class='cart__item']//div[contains(text(),'License type:')]/parent::div/div/div[@class='proration__deploy-details--date'][contains(text(),'Unpaid')]");
    }
    
    public static By PLACE_REQUISITION=By.xpath("//section[@class='cart cart']/following::div[@class='cart-summary-container']//button[contains(text(),'Place requisition')]");
	
    public static By copytoall(String PartNum){
    	return By.xpath("//a[contains(@href,'"+PartNum+"')]/ancestor::div[@class='cart__item']//div[contains(text(),'License type:')]/parent::div/div/span//span[contains(text(),'Copy to all')]");
    }
    public static By Change(String PartNum){
    	return By.xpath("//a[contains(@href,'"+PartNum+"')]/ancestor::div[@class='cart__item']//div[contains(text(),'License type:')]/parent::div/div/span//span[contains(text(),'Change')]");
    }
    public static By DEPLOYDATE_POPUP=By.xpath("//h3[contains(text(),'Deploy date')]");
    public static By DEPLOYDATE_PLACEORDERPAGE=By.xpath("//div[@class='proration__deploy-details--date']");
    public static By UNPAIDLISENCE=By.xpath("//input[@id='unpaidLicense']");
    public static By DEPLOY_DATEINPOPUP=By.xpath("//div[@class='date-picker__date-display']");
    public static By PRORATIONPOPUP_DATA=By.xpath("//section[@class='proration-modal']//tr/td[@class='proration-modal__table-body']");
    public static By PartNuminpopupdata(String PartNum){
    	return By.xpath("//section[@class='proration-modal']//tr/td[contains(text(),'"+PartNum+"')]");
    }
    public static By APPLYBUTTON=By.xpath("//button[contains(text(),'Apply') and @type='button']");
    public static By Prorationtext(String PartNum){
    	return By.xpath("//a[contains(@href,'"+PartNum+"')]/ancestor::div[@class='cart__item']//div/p[@class='proration__text']");
    }
    public static By MONTH_DROPDOWN=By.xpath("//div[@class='react-datepicker__month-read-view']");
    public static By YEAR_DROPDOWN=By.xpath("//div[@class='react-datepicker__year-read-view']");
    public static By Month(String month){
    	return By.xpath(" //div[@class='react-datepicker__month-option' and contains(text(),'"+month+"')]");
    }
    public static By Year(String year){
    	return By.xpath("//div[@class='react-datepicker__year-option' and contains(text(),'"+year+"')]");
    }
    public static By Day(String day){
    	return By.xpath("//div[contains(@class,'react-datepicker__day') and contains(text(),'"+day+"')]");
    }
    public static By SELECTEDYEAR=By.xpath("//span[@class='react-datepicker__year-read-view--selected-year']");
    public static By SELECTED_MONTH=By.xpath("//span[@class='react-datepicker__month-read-view--selected-month']");
    public static By SELECTED_DAY=By.xpath("//div[@class='react-datepicker__day react-datepicker__day--selected react-datepicker__day--today']");
    public static By getQuoteFormTable(String quoteName){
        return By.xpath("//table[@class='footable clean full footable-loaded default']//tbody//tr//td[contains(.,'"+quoteName+"')]");

  }
   public static By getReferenceNumber(String quoteReferenceNum){
        return By.xpath("//table[@class='footable clean full footable-loaded default']//tbody//tr//td//a[contains(.,'"+quoteReferenceNum+"')]");
  }
   public static By ENTER_NEWCARDBUTTON=By.xpath("//a[contains(text(),'Enter New Card')]");
    // cart page messages
    public static By USER_REQUIRES_APPROVAL_MSG=By.xpath("//div[@class='row expanded is-collapse-child shopping-cart__messages']//span[@class='columns iw-message__text'][contains(text(),'You require approval to place orders, however this licensing program does not allow approval routing. Please contact spprograms@insight.com for assistance.')]");
    public static By NON_CITRIX_ITEMS_REMOVE_MESSAGE=By.xpath("//span[@class='columns iw-message__text'][contains(text(),'In order to report usage please remove items that do not apply to the selected service provider.')]");
    public static By CITRIX_ITEMS_IN_CART=By.xpath("//h4[@class='cart__item-heading'][contains(text(),'CITRIX CSP')]");
    
    public static By getdeleteIconIncartBypartNumber(String partNum){
    	return By.xpath("//p[contains(.,'Insight Part #: "+partNum+"')]/ancestor::div[@class='row expanded align-top is-collapse-child cart__table-row text-center']//span[@class='ion-trash-a cart__trash-icon']");
    }
    
    public static By REPORTING_USAGE_ON_RECEIPT_PAGE =By.xpath("//div[@class='row is-collapse-child expanded usage-reporting usage-reporting--summary']//span[contains(text(),'Report usage for:')]/following-sibling::span//strong");
    public static By RETRIEVE_LAST_USAGE_REPORT=By.xpath("//span[contains(text(),'Retrieve Last Usage Report')]");
    public static By REPORTING_PERIOD_WARNING_MSG=By.xpath("//span[@class='columns usage-reporting__warning-message']");
    public static By ENROLLMENT=By.xpath("//div[@class='usage-reporting__footer']");
    public static By ENROLLMENT_ON_RECEIPT_PAGE=By.xpath("//div[@class='column small-12 medium-shrink medium-text-right']");

    public static By copytoallLink(String PartNum){
    	return By.xpath("//p[@class='cart__item-part cart__font-size--sm'][contains(.,'"+PartNum+"')]/following::div[11]/span//span[contains(text(),'Copy to all')]");
    }
    
    public static By retriveLastUsageReport(String softwareAgreement) {
		return By.xpath("//tr//td//strong[contains(text(),'"+softwareAgreement+"')]/following::td//div[@class='buttons inline left']//a//span[contains(.,'Retrieve Last Usage Report')]");
    	
    }
    
    public static By getCopyAllLink(String PartNum) {
    	return By.xpath("//p[@class='cart__item-part cart__font-size--sm'][contains(.,'"+PartNum+"')]/following::div//span[@class='prortation__link-text prortation__link-text--copy']");
    }
    public static By PA_FIELD=By.xpath("//input[@name='licenseInformation.AUTHORIZATION']");
    public static By PA_fieldincartpage(int i){
    	return By.xpath("(//input[@name='licenseInformation.AUTHORIZATION'])["+i+"]");
    }
    public static By PA_fieldinplaceorderpage(int i){
    	return By.xpath("//label[@class='form__label--readonly'][contains(text(),'PA #:')]["+i+"]");
    }
    public static By DEPLOYEDDATE_CARTPAGE=By.xpath("//div[@class='proration__deploy-details--date']");
    public static By change_field(String partNum){
    	return By.xpath("//p[@class='cart__item-part cart__font-size--sm'][contains(.,'"+partNum+"')]/following::div//span[@class='prortation__link-text prortation__link-text--change'][contains(text(),'Change')]");
    }
    
    public static By getDeployDateOnCartPage(String partNum) {
    	return By.xpath("//p[@class='cart__item-part cart__font-size--sm'][contains(.,'"+partNum+"')]/following::div//span//div[@class='proration__deploy-details--date']");
    }

    public static By ALL_REPORTING_PERIODS_CURRENT_LABEL_SLPA_PAGE=By.xpath("//span[@class='usagePeriod'][contains(text(),'All Reporting Periods Current')]");
    public static By ALL_REPORTING_PERIODS_CURRENT_LABEL_CART_PAGE=By.xpath("//div[@class='usage-reporting__detail row row__gutter--tiny collapse expanded']//span//strong[contains(text(),'All reporting periods current.')]");
    
    public static By RETURN_TO_SLP=By.xpath("//a[contains(text(),'Return to My Software License Agreements')]");

// Deploy date popup
    public static By DEPLOY_DATE_POPUP_MFR_PART=By.xpath("//table[@class='proration-modal__table']//tbody//td[2]");
    public static By DEPLOY_DATE_POPUP_DESCRIPTION=By.xpath("//table[@class='proration-modal__table']//tbody//td[3]");

    // PA #on receipt page 
    public static By getPAOnReceipt(String pa){
    	return By.xpath("//label[@class='form__label--readonly'][contains(.,'PA #:')]//p[contains(text(),'"+pa+"')]");
    }
    
    public static By getDeployDateAndLicenceTypeOnPlaceOrderPage(String input,String partNum) {
    	return By.xpath("//div[@class='proration__deploy-details--date'][contains(text(),'"+input+"')]/ancestor::div//div[@class='cart__item']//div//p[contains(.,'Insight Part #: "+partNum+"')]");
    }
   
    
    // Quotes
    public static By getDescriptionOnQuotePage(String description) {
    	return By.xpath("//a[@id='A1'][contains(text(),'"+description+"')]");
    }
    
    public static By getDeployDateOnQuotePage(int i,String date) {
    	return By.xpath("(//span[contains(text(),'"+date+"')])["+i+"]");
    }
    
    public static By getPAField(String pa) {
    	return By.xpath("//div[contains(text(),'PA #: "+pa+"')]");
    }
     public static By manufacturerRequirements(int i) {
    	 return By.xpath("(//div[@class='editManufacturerRequirements'])["+i+"]");
     }
     
     public static By getSummaryAmountOnQuoteScreen(String label) {
    	 return By.xpath("//td[contains(text(),'"+ label +"')]/following-sibling::td//span[@id='subtotalvalDiv']");
     }
     
     public static By QUOTEHISTORY_LINK=By.xpath("//a[@class='displayQuoteHistory']");
    
     public static By mfrRequirementsOnPlaceOrderPage(int i,int j) {
    	 return By.xpath("(//section[@class='line-level__section'])["+i+"]//div//label["+j+"]");
     }
}
