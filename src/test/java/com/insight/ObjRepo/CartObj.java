package com.insight.ObjRepo;

import org.openqa.selenium.By;

public class CartObj extends CommonObj{

	public static By PRINT = By.xpath("//*[@class='columns shopping-cart__share text-right']/a/span[contains(.,'Print')]");
	
	public static By PRINT_POPUP = By.xpath("//*[@class='iw-dialog iw-dialog-contents xLarge']");
	public static By LOG_IN_LINK = By.xpath("//div[@class='o-grid__item  o-grid__item--shrink  u-push-auto@desktop']/nav/ul/li/a[text()='Login']");
	public static By POP_UP_EMAILID = By.id("cookie-email-id");	
	public static By POP_UP_SUBMIT_BUTTON = By.id("cookie-email-submit");
	public static By PRODUCT_LINK=By.xpath("//a[@data-gtm-event='cart-item-description-link']");
	public static By SEARCH_BUTTON_CMT_TOOL = By.id("eAdminCustomerSearchButton");	
	public static By MANAGE_USERS_BUTTON = By.id("manageUsersButton");
	public static By LNAME_EMAIL_USERNAME = By.id("userSearchText");
	public static By USERNAME_SEARCH_BUTTON = By.id("userSearchResultsButton");
	public static By USER_CONTACT_NAME = By.xpath("//table[@id='userSearchResultsTable']/tbody/tr/td[@id='userName']/a");
	public static By TEXT_FIELD_IN_POP_UP = By.id("emailId");
	public static By SUBMIT_BUTTON_IN_POP_UP = By.xpath("//a[@id='resetPasswordSubmit']");
	public static By MORE_AVAILABLE_PRICES=By.xpath("//a[text()='More Prices Available'] | (//a[text()='More Prices Available'])[1]");
	public static By ADD_TO_CART_IN_ALL_CONTRACT_PRICES=By.xpath("//h4[text()='All Contract Prices For']/parent::div//a[text()='Add to Cart']");
    public static By US_COMMIDITIES=By.xpath("//div[@class='large-12 columns js-modal-pick-contract']//label[contains(.,'U.S. COMMUNITIES')]");
	
    public static By moreAvilablePrices(Integer index) {
		return By.xpath("//div[@id='search-item-"+index+"']//a[text()='More Prices Available']");
	}
    
    public static By breadCrum(String text) {
    	return By.xpath("//div[@class='filter-item js-filter-item']//a[contains(text(),'"+text+"')]");
    }
    		
    public static By Insightpartnumber = By.xpath("//div[@class='cart__item']//a//following-sibling::p[1]");
    public static By MfrPartNumber = By.xpath("//div[@class='cart__item']//a//following-sibling::p[2]");
    public static By MORE_AVAILABLE_PRICE_IN_PRODUCT_INFO=By.xpath("//div[@class='prod-description-bottom']//a[text()='More Prices Available']");
    public static By CART_LABEL_ON_CART_PAGE=By.xpath("//h1[@class='shopping-cart__header-title'][text()='Cart']");
    public static By DEFAULT_CONTARCT=By.xpath("//div[@id='search-item-0']//p[@class='sewp-prices' and  contains(text(),'U.S. COMMUNITIE...')]");
    public static By DEFAULT_CONTRACT_PRODUCT_DISPLAY=By.xpath("//p[@class='sewp-prices' and contains(text(),'U.S')]");
    public static By DEAFULT_CONTARCT_IN_CART=By.xpath("//div[text()='Contract: ']//parent::div//strong[contains(text(),'U.S')]");	
	//UAT1
	public static By SEARCH_BUTTON = By.id("searchbutton");
	public static By INSIGHT_NUMBER_IN_PRODUCT_DISPLAY = By.xpath("//span[contains(.,' Insight ')]/parent::td");
	public static By ADD_TO_CART_IN_PRODUCT_DISPLAY = By.xpath("//*[@id='tpl-product-detail-order-target']/div/div[contains(.,'Add to Cart')]|(//*[text()='Add to Cart'])[2]") ; 
	public static By NUMBER_PICKER_IN_PRODUCT_DISPLAY = By.xpath("//input[@id='product-detail-order-number-picker']/parent::div/button[@class='number-picker-up ion-arrow-up-b']");    
	public static By ADD_TO_CART_SUCCESS_MESSAGE = By.xpath("//div[text()='Added to Your Cart']");		
	public static By CONTINUE_TO_SHOPPING = By.xpath("//a[@id='buy-add-item-modal-continue']");		
	public static By ADD_TO_CART_BUTTON_FOR_FIRST_DISPLAYED_ITEM=By.xpath("//*[@id='search-item-0']//a[text()='Add to Cart']" );
	public static By ADD_TO_CART_BUTTON_FOR_SECOND_DISPLAYED_ITEM=By.xpath("//*[@id='search-item-1']//a[text()='Add to Cart']");
	public static By ADD_WARRANTY = By.xpath("//section[@id='addon-warranties']/p/a[contains(.,'Add Warranty')]");
	public static By CLOSE_POPUP = By.xpath("//div[@id='buy-modal']/a");
	public static By CART = By.xpath("//div[@class='o-grid__item  o-grid__item--shrink  u-push-auto@desktop']/nav/ul/li[6]/a");
	public static By QUANTITY = By.xpath("//label[text()='Item quantity']/following-sibling::input | //*[@id='product-detail-order-number-picker']");
	public static By UPDATE = By.xpath("//a[text()='Update']");
	public static By DELETE = By.xpath("//*[@class='ion-trash-a cart__trash-icon']");
	public static By EMPTY_CART = By.xpath("//a[text()='Empty cart']");
	public static By EMPTY_CART_MESSAGE = By.xpath("//*[text()='Your shopping cart is empty.']");
	public static By ENABLE_CLOUD_BUYING = By.xpath("//div[@class='webPermLabel']/a[contains(.,'Enable Cloud Buying')]/parent::div/preceding-sibling::div/input");
	public static By MANAGE_CURRENT_WEB_GROUP = By.xpath("//div[text()='Manage Current Web Group']");
	public static By ROLES_AND_PERMISSIONS_USER_LEVEL = By.xpath("//div[@id='CNUTabMenu']/ul/li[@id='permissionTab']");
	public static By PERMISSIONS_UPDATED_SUCCESSFULLY_MESSAGE = By.id("rolePermissionsUpdateMessage");
	public static By BUYING_NOT_ENBLED_MESSAGE = By.xpath("//*[contains(text(),'Buying is not enabled')]");
	public static By PROCEED_TO_CHECKOUT = By.xpath("//*[@id='CartContainer']/div/div[2]/div[3]/div[2]/div/div[2]/div[1]/div[3]/div[1]/div[2]/div/div/button");
	public static By SEARCH_ITEM = By.xpath("//*[@id='search-item-0']/div/div[4]/div/div/div[2]/div/div[3]/div/div[2]/a");
	public static By TOOLS = By.xpath("//*[@id='offCanvasNavPlaceholder']/main/ul/li[4]/a");
	public static By COMPANY_STANDARDS = By.xpath("//*[@id='offCanvasNavPlaceholder']/main/ul/li[4]/ul/li[1]/a");
	public static By COMPANY_STANDARDS_PRODUCTS = By.xpath("//a[text()='Products']");
	public static By MFR_NUMBER = By.xpath("//*[@id='js-product-detail-pricing-target']/table/tbody/tr/td/span[contains(text(),'Mfr')]/parent::td/text()");
	public static By QUANTITY_DISABLED=By.xpath("//input[@class='cart-item__qty--readonly cart-item__qty align-self-middle text-center']");
	
	/* *******************************************************************************
	 * >>>>>>>>>>>>>>>>>>>>>>>>>>>>>QUICK SHOP LOCATORS<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
	 * *******************************************************************************
	 */

	
	public static By QUICK_SHOP_ITEM_FIELD = By.xpath("//div[@class='hide-for-print']/following::input[@class='quick-shop__materialID no-margin-bot']");
	public static By QUICKSHOP_ITEM_QUANTITYFIELD=By.xpath("//div[@class='hide-for-print']/following::input[@class='quick-shop__quantity text-center no-margin-bot']");
	public static By QUICK_SHOP_QUANTITY_FIELD = By.xpath("//div[@class='hide-for-print']/following::input[@class='quick-shop__quantity text-center no-margin-bot']");
	public static By ADD_BUTTON_IN_QUICK_SHOP = By.xpath("//div[@class='hide-for-print']/following::button[text()='Add']");
	public static By QUICK_SHOP_ERROR_MESSAGE = By.xpath("//div[@class='hide-for-print']/following::span[contains(text(),'Please enter a valid part number')]");

	public static By QUICK_SHOP_SECTION=By.xpath("//div[@class='hide-for-print']//h4[text()='Quick shop']");


	/* *******************************************************************************
	 * >>>>>>>>>>>>>>>>>>>>>>>>>>>>>SHOPPING CART LOCATORS<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
	 * *******************************************************************************
	 */

	public static By getItemInCart(String itemInCart){
	 return By.xpath("//div[@class='cart__item']//div//p[contains(.,'Mfr Part #: " +itemInCart + "')]");
	 
	}
	
	public static By getItemIncartByInsightPartNumber(String itemInCart){
		return By.xpath("//div[@class='cart__item']//div//p[contains(.,'Insight Part #: "+itemInCart+"')]");
	}
	
	public static By getWebGroupNumber(String webGroupNumber){
		return By.xpath("//*[@id='webGroupId']/a[contains(text(),'"+webGroupNumber+"')]");
		
	}	
	
	public static By getUser(String contactName){
		return By.xpath("//table[@id='userSearchResultsTable']/tbody/tr/td[@id='userName']/a[contains(text(),'" + contactName + "')]");
		
	}	
	
	public static By getContractNameInCart(String contractName) {
		return By.xpath("//div[@class='columns cart__contract-header']//strong[text()='"+contractName+"']");
	}
	
	public static By CONTRACT_IN_CART=By.xpath("//div[@class='columns cart__contract-header']//strong");
	
	/* *******************************************************************************
	 * >>>>>>>>>>>>>>>>>LOCATORS IN VIEW PRINTABLE POPUP<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
	 * *******************************************************************************
	 */
	public static By VIEW_PRINTABLE_POPUP = By.xpath("//*[@class='iw-dialog iw-dialog-contents xLarge']");
	public static By CLOSE_PRINT_POPUP = By.xpath("//*[@class='cart-print-header undefined']/header/div/span");
	public static By INSIGHT_LOGO_IN_PRINT_POPUP = By.xpath("//*[@class='cart-print-header undefined']/header/a/img");
	public static By YOUR_CART_IN_PRINT_POPUP = By.xpath("/html/body/div[13]/div/div[2]/div/div/div[2]/div[3]/div[1]/section/div[1]/h3");
	public static By UNIT_PRICE_IN_PRINT_POPUP = By.xpath("/html/body/div[13]/div/div[2]/div/div/div[2]/div[3]/div[1]/section/div[2]/div[1]/div/div[3]");
	public static By QUANTITY_IN_PRINT_POPUP = By.xpath("/html/body/div[13]/div/div[2]/div/div/div[2]/div[3]/div[1]/section/div[2]/div[1]/div/div[4]");
	public static By PRINT_SYMBOL_IN_PRINT_POPUP = By.xpath("/html/body/div[13]/div/div[2]/div/section/header/div/div/span[1]");
	
	/* *******************************************************************************
	 * >>>>>>>>>>>>>>>>>LOCATORS IN SEND TO A COLLEGUE POPUP<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
	 * *******************************************************************************
	 */
	public static By SEND_TO_A_COLLEGUE_POPUP = By.xpath("//*[@class='iw-dialog iw-dialog-contents medium']");
	public static By SEND_BUTTON_IN_SEND_TO_A_COLLEGUE_POPUP = By.xpath("//button[text()='Send']");
	public static By YOUR_NAME_ERROR_MESSAGE = By.xpath("//div[contains(text(),'name is required')]");
	public static By YOUR_EMAIL_ERROR_MESSAGE = By.xpath("//div[contains(text(),'email address is required')]");
	public static String RECIPIENT_EMAIL = "Recipient's email address is required";
	public static By RECIPIENT_EMAIL_ERROR_MESSAGE = By.xpath("//div[contains(text(),'Recipient')]");
	public static By CLOSE_SEND_TO_A_COLLEGUE_POPUP = By.xpath("//*[@class='iw-dialog__icon--close ion-ios-close-empty']");
	public static By YOUR_NAME_TEXT_FIELD = By.xpath("//*[text()='Your name']/parent::div/following-sibling::div/input");
	public static By YOUR_EMAIL_TEXT_FIELD = By.xpath("//*[text()='Your email']/parent::div/following-sibling::div/input");
	public static By RECIPIENT_EMAIL_TEXT_FIELD = By.xpath("//div[contains(text(),'Recipient')]/parent::div/input");
	public static By YOUR_COMMENTS_TEXT_FIELD = By.xpath("//*[text()='Your comments']/parent::div/following-sibling::div/textarea");
	public static By MAIL_SEND_TO_A_COLLEGUE_SUCCESS_MSG = By.xpath("//*[contains(text(),'Email successfully sent to colleague(s)')]");
	public static By SHOPPINGCART_ICON_CTO_PRODUCT_GROUP = By.xpath("//*[text()='IUSA Mandatory CTO']/parent::td/parent::tr/parent::tbody/parent::table/following-sibling::div/input[@name='addToCart']");
	public static By ITEMS_ADDED_TO_CART_POPUP = By.xpath("//*[@id='addToCartDialog']/div/div/b[contains(.,'Items added to Cart')]");
	public static By CLOSE_ITEMS_ADDED_TO_CART_POPUP = By.xpath("//*[@class='insightCustomDialog']/div/span");
	
	public static By getShoppingCartOrderUtilities(String orderUtilities){
		return  By.xpath("//div[@class='columns shopping-cart__share text-right']/a/span[contains(.,'"+orderUtilities+"')]");
		
	}	
	
	public static By getShoppingCartIcon(String ctoProductGroup){
		return By.xpath("//*[text()='" + ctoProductGroup + "']/parent::td/parent::tr/parent::tbody/parent::table/following-sibling::div/input[@name='addToCart']");
		
	}	
	//cart save
	public static By SAVE_CART_CONTENTS=By.xpath("//div[@class='columns small-12 large-3 print-5 print-offset-7']//button[text()='Save cart contents']");
	public static By SAVE_CART_CONTENTS_POPUP=By.xpath("//h3[text()='Save Cart Contents']");
	public static By SAVE_ORDER_TEMPLATE=By.xpath("//div[@class='columns small-12 large-3 print-5 print-offset-7']//button[text()='Save order template']");
	public static By SAVE_ORDER_TEMPLATE_POPUP=By.xpath("//div[@class='iw-modal']//h3[contains(text(),'Save Order Template')]");
	public static By SAVE_BUTTON=By.xpath("//button[text()='Save']");
	public static By SAVE_CART_ERROR_MESSAGE=By.xpath("//div[@class='form__field-msg form__field-msg--error']");
	public static By SAVE_CART_INPUT_FIELD=By.xpath("//span[@class='form__label__text']/following-sibling::input");
	public static By CART_SAVED_SUCESS_MESSAGE=By.xpath("//p[text()='Your cart has been successfully saved.']");
	public static By CONTINUE=By.xpath("//button[text()='Continue']");
	public static By SAVED_CART_TEXT=By.xpath("//h2[text()='Saved carts']");
	public static By LOAD_CART=By.xpath("//button[@class='c-button  c-button--inline-link c-saved-cart__btn']");
	public static By SAVED_CART_CANCEL_BTN=By.xpath("//button[@type='button'][contains(text(),'Cancel')]");
	public static By CLEAR_MY_DRAFT_SAVED=By.xpath("//input[@name='clearCheckbox']");
	public static By SAVED_CART_CONTENTS_HEADER=By.xpath("//h1[text()='Saved carts/Order templates']");
	public static By loadCart(String cartName) {
		return By.xpath("//h3[text()='"+cartName+"']//parent::div//button[@class='c-button  c-button--inline-link c-saved-cart__btn']");
	}
	public static By CURRIENCES=By.xpath("//div[@data-label='Unit price']//span/span");
	public static By DELETE_BUTTON=By.xpath("//button[@class='c-button  c-button--link c-button--small']");
	public static By deleteButton(String cartName) {
		return By.xpath("//h3[text()='"+cartName+"']//parent::div//parent::div//button[@class='c-button  c-button--link c-button--small']");
	}
	public static By DELETE_CART=By.xpath("//div[@class='o-grid  o-grid--center  c-saved-cart__row']//div//button[@class='c-button  c-button--link c-button--small']");
	public static By DELETEBTN=By.xpath("//div//button[@class='c-button  c-button--link c-button--small']");
	public static By CART_NAME=By.xpath("//h3[@class='c-saved-cart__heading']");
	public static By YES_BUTTON_INCONFORMATION_POP_UP=By.xpath("//button[@class='c-button  c-button--primary']");
	public static By DELETE_CART_MEASSAGE=By.xpath("//div[@class='c-no-saves']//strong");
	public static By NO_SAVED_CART_MESSAGE=By.xpath("//strong[text()='No saved carts or order templates exist.']");
	public static By addToCartInSavedCart(String cartName) {
		return By.xpath("//h3[text()='"+cartName+"']//parent::div//parent::div//button[text()='Add to cart']");
	}
	public static By CONTINUE_TO_CHECKOUT=By.xpath("//button[text()='Continue to checkout']");
	
	//cart
	public static By CART_ITEMS=By.xpath("//a[@aria-label='Shopping cart, 0 items']");
	public static By PROD_GROUP_NAME_IN_CART=By.xpath("//div[@class='cart__table-bundle-row']//h4");
	public static By BUNDLEONE=By.xpath("//div[@class='cart__item-bundle' and contains(.,'BUNDLE-1')]");
	public static By BUNDLE=By.xpath("//div[@class='cart__item-bundle']");
	public static By BUNDLE_NAME=By.xpath("//div[@class='cart__item-bundle']//parent::div//h4");
	public static By PART_NUMBER=By.xpath("//a[@data-gtm-event='cart-item-description-link']//following-sibling::p[contains(text(),'Mfr Part #')]");
	//cart quote
	public static By SAVE_AS_QUOTE=By.xpath("//div[@class='columns small-12 large-3 print-5 print-offset-7']//a[text()='Save as quote']");
	//summary
	public static By SELECT_REQUEST_GROUP=By.xpath("//div[@class='columns small-12 large-3 print-5 print-offset-7']//div[text()='Select Requestor Group...']");
	//quick checkout
	public static By QUICK_CHECKOUT=By.xpath("//div[@class='columns small-12 large-3 print-5 print-offset-7']//button[@class='button expanded cart-summary__button']");
	public static By PLACE_ORDER_PAGE_TEXT=By.xpath("//h1[text()='Place order']");
	
	public static By validationsInPlaceOrderPage(String textToVerify) {
		return By.xpath("//p[text()='"+textToVerify+"']");
		
	}
	public static By deleteSpecificPartNumber(String partNumber) {
		return By.xpath("//p[text()='"+partNumber+"']//ancestor::div[@class='cart__item']//span[@class='ion-trash-a cart__trash-icon']");
	}
	public static By DELETE_BUNDLE=By.xpath("//div[text()='BUNDLE-1']//ancestor::div[@class='cart__table-bundle-row']//span[@class='ion-trash-a cart__trash-icon']");
	public static By RETURN_TO_CART=By.xpath("//a[@class='shopping-cart__header-link']");
	public static By FAVOURITE_SHIPPING_ADDRESSES_DROPDOWN=By.xpath("//div[@class='columns small-12 large-3 print-5 print-offset-7']//div[text()='Select a favorite shipping address']");
	public static By selectFavouriteShippingAdresses(String shippingAddresses) {
		return By.xpath("//*[text()='"+shippingAddresses+"']");
	}
	//shipping in checkout
	public static By NOTIFICATION_EMAIL=By.xpath("//input[@type='email']");
	public static By verifyEmail(String email) {
		return By.xpath("//input[@value='"+email+"']");
	}
	public static By ADD_ADDITIONAL_NOTIFICATION_EMAIL=By.xpath("//a[text()='Add additional notification email']");
	public static By ADDITIONAL_NOTIFICATION_EMAIL=By.xpath("//input[@type='email' and @value='']");
	public static By clearNotificationEmail(String email) {
		return By.xpath("//input[@type='email' and @value='"+email+"']");
	}
	
	public static By ERROR_MESSAGE_INVALID_EMAIL=By.xpath("//div[@class='form__field-msg form__field-msg--error' and contains(text(),'Please')]");
	public static By verifyNotificationEmailInShippingAdresses(String notificationEmail) {
		return By.xpath("//p[text()='"+notificationEmail+"']");
	}
	//checkout
	public static By PHONE_FIELD=By.xpath("//input[@name='existingAddressAttention.phone']");
	public static By verifyRpHdlText(String textToVerify) {
		return By.xpath("//span[text()='RP_HDL_Txt']//parent::label//parent::div/p[text()='"+textToVerify+"']");
	}
	
	public static By verifyRpLnlText(String textToVerify) {
		return By.xpath("//label[text()='RP_LNL_Txt:']/p[text()='"+textToVerify+"']");
	}
	
	public static By PART_NUMBER_IN_ADDED_TO_YOUR_CART_POPUP=By.xpath("//div[@id='buy-modal']//div//ul//li[contains(.,'Mfr #:')]");
	public static By SUMMARY_TOTAL=By.xpath("//section/following::div[@class='columns small-12 large-3 print-5 print-offset-7']//div[text()='Total']//parent::div//span[@class='iw-currency__amount']");
	public static By CURRENCY_CODE=By.xpath("//section/following::div[@class='columns small-12 large-3 print-5 print-offset-7']//div[text()='Total']//parent::div//span[@class='iw-currency__code']");
	public static By REMOVE_IN_STOCK_ITEMS=By.xpath("//a[text()='In Stock Only']");
	public static By lblCartLebel=By.xpath("//*[@class='shopping-cart__header-title']");

    ///NViDiA Screen
    
	public static By WELCOME_MESSAGE = By.xpath("//div[@id='welcomePageHeading']/h1[contains(text(),'Welcome to your Punchout Site')]");
    public static By GETSTARTED_IMG = By.xpath("//div[@class='htmlcomponent section']/following::p/a/img");
    public static By ACCESSORIES_LNK = By.xpath("//a[@id='selectItemButton_8']/span[contains(text(),'Accessories')]");
    public static By LENOVOLAPTOPS_LNK = By.xpath(" //a[@id='selectItemButton_4']/span[contains(text(),'Lenovo Laptops')]");
    public static By listofAccessories(String link) {
         return By.xpath("//div[@class='buttons']/a[@name='gwpOptions']/span[contains(text(),'"+link+"')]");
    }
    public static By chooseThisItem_lnk(int size) {
    	return By.xpath("//a[@id='selectItemButton_"+size+"']");
    }
    public static By NEXT_BTN = By.xpath("//div[@id='actionButtons']/a/following::a/span[contains(text(),'NEXT >>')]");
    public static By CART_IDINPRODUCT_PAGE = By.xpath("//div[@class='mfrUnspscDiv']");
    public static By NVIDIALIST_HDR = By.xpath("//div[@id='PageTitle'][contains(text(),'NVidia Product List')]");
    public static By product_link(String productlink) {
    	return By.xpath("//div[@class='productHyperlink']/a[contains(text(),'"+productlink+"')]");
    }
    public static By DELL_CURRENTTAB = By.xpath("//li[@class='current']/p[contains(text(),'Dell Accessories')]");
    public static By PDP_INSIGHT_IMG = By.xpath("//div[@class='miniPPPBrandingLogoContainer']");

   

    public static By SHIPPING_ESTIMATE=By.xpath("//section/following::div[@class='columns small-12 large-3 print-5 print-offset-7']//span[text()='Shipping estimate']//parent::div//parent::div//span[@class='iw-currency__amount']");
    

///////////////////////////////////////SHIPPING ESTIMATOR///////////////////
public static By Shipping_EStimator=By.xpath("//div[@class='columns small-12 large-3 print-5 print-offset-7']//h4[contains(text(),'Shipping estimator')]");

public static By Shipping_Estimator_Textfield=By.xpath("//div[@class='columns small-12 large-3 print-5 print-offset-7']//input[@id='shippingEstimator-zipcode']");
public static By Shipping_Estimator_Applybutton=By.xpath("//div[@class='columns small-12 large-3 print-5 print-offset-7']//button[contains(text(),'Apply')]");
public static By Shipping_Estimator_seeallcarriers=By.xpath("/html/body/div[13]/div/div[2]/div/div/section/div/a");

public static By verifyshippingCarrier(String carrierName){
return By.xpath("//p[text()='Please select a shipping carrier from the list below:']//parent::div//ul//li//*[contains(.,'"+carrierName+"')]");
}

public static By otherthanUSDandFedEx=By.xpath("/html/body/div[13]/div/div[2]/div/div/section/div/ul/li[1]/label/span[1]");
public static By submit_Button=By.xpath("/html/body/div[13]/div/div[2]/div/footer/div/div/button");
public static By Close_seeallcarriers=By.xpath("/html/body/div[13]/div/div[2]/div/header/div[2]/span");

//user profile in personalization in account tools
public static By CHECKOUT_DEFAULTS=By.xpath("//a[@data-tab='chkoutDefaults']");
public static By SHIPMENT_NOTIFICATION=By.xpath("//textarea[@id='shipmentNotificationRecipients']");
public static By UPDATE_BUTTON=By.xpath("//input[@id='updateChkoutDef']");
public static By ERROR_MESSAGE=By.xpath("//div[@class='input-error alert']");
public static By MESSAGE_NOTE=By.xpath("//span[@class='alert-info-note']");
public static By SUCESS_MESSAGE_NOTE=By.xpath("//div[@class='alert successGreen']");

////////////////////////////////////CARTINVENTORY////////////////////////
public static By Current_product_groups =By.xpath("//div[@id='csProductGroupsHeading']");
public static By closeicon_addtocart=By.xpath("//span[@class='insightLightBoxClose frameworkIcons Deep_Close_Icon']");
public static By ProductName_with_COI(String Productname)
{
return By.xpath("//table[@id='prodGroupTable']//a[contains(text(),'"+Productname+"')]  ");

}
public static  By verificationText(String product)
{
return By.xpath("//td[@class='stock width70']//a[contains(text(),'"+product+"')]");
}
public static By DESCRIPTION=By.xpath("//table[@id='prodGroupTable']//div//a");
public static By STOCK=By.xpath("//table[@id='prodGroupTable']//tr//td[2]//a");
public static By CHECK_BOX=By.xpath("//table[@id='prodGroupTable']//tr//td[5]//input");
public static By COI_IN_CART=By.xpath("//div[@class='columns medium-flex-child-grow cart__table-col--item']//div//div//div[@class='columns flex-child-auto medium-flex-child-shrink cart__table-col cart__table-col--coi'] | //div[@class='columns medium-flex-child-grow cart__table-col--item']//div//div//div[@class='columns flex-child-auto medium-flex-child-shrink cart__table-col cart__table-col--coi']//span");
public static By CSI_IN_CART=By.xpath("//div[@class='columns medium-flex-child-grow cart__table-col--item']//div//div//div[@class='columns flex-child-auto medium-flex-child-shrink cart__table-col cart__table-col--csi']");

public static By ADD_Checkbox_forCOIproducts=By.xpath("//div[@id='selectedProductGroupContent']//td[contains(text(),'COI Price:')]/following-sibling::td//input[@type='checkbox']");
public static By Productname_at_cart=By.xpath("//h4[@class='cart__item-heading']");

// CART PRINT FUNCTIONALITY

   public static By PRINT_PREVIEW_PRINT_ICON=By.xpath("//section[@class='cart-print-header undefined']//span[@class='cart-print-header__icon--print ion-ios-printer-outline']");
   public static By ITEM_DESC=By.xpath("//div[@class='iw-modal cart-print-preview']//div[contains(text(),'Item')]/following::div//a//h4[@class='cart__item-heading']");
   public static By INSIGHT_PART=By.xpath("//div[@class='iw-modal cart-print-preview']//div[contains(text(),'Unit price')]/following::div//p[contains(.,'Insight Part #')]");
   public static By UNIT_PRICE_PRINT_POPUP=By.xpath("//div[@class='iw-modal cart-print-preview']//div[@class='columns flex-child-auto medium-flex-child-shrink cart__table-col cart__table-col--price medium-text-right small-negative-left-margin']//span[@class='iw-currency__amount']");
   public static By TOTAL_PRICE_PRINT_POPUP=By.xpath("//div[@class='iw-modal cart-print-preview']//div[@class='columns flex-child-auto medium-flex-child-shrink cart__table-col cart__table-col--total medium-text-right']//span[@class='iw-currency__amount']");
   public static By STOCK_PRINT_POPUP =By.xpath("//div[@class='iw-modal cart-print-preview']//span[contains(text(),' Stock: ')]");
   public static By QUANTITY_PRINT_POPUP=By.xpath(" //div[@class='iw-modal cart-print-preview']//input[@aria-label='Item quantity']");
   public static By ADDRESS_PRINT_POPUP=By.xpath("//div[@class='row cart-print-header__address']//h5/following-sibling::span");
   public static By CLOSE_ICON_PRINT_POPUP=By.xpath(" //section[@class='cart-print-header undefined']//span[@class='cart-print-header__icon--close ion-ios-close-empty']");
   
   //order details page
   public static By productDes=By.xpath("//h3[@class='item-details__desc']");
   public static By productPartNum=By.xpath("//h3[@class='item-details__desc']/following::span[@class='item-details__part']");
   // Verify Cart 
   public static By currencytype=By.xpath("//*[@class='iw-currency__code']");
   public static By CART_PROD_DESC=By.xpath("//div[@class='columns flex-child-auto cart__table-col--desc text-left']/a/h4");
   public static By CART_PROD_QTY=By.xpath("//label[text()='Item quantity']/following-sibling::input[@id='iw-checkout__cart-item-quantity' or @aria-label='Item quantity']");
   public static By CART_PROD_STOCK=By.xpath("//label[text()='Item quantity']/following-sibling::input/following::span/span[contains(text(),' Stock: ')]");
   public static By CART_PROD_TOTAL_PRICE=By.xpath("//div[@class='columns flex-child-auto medium-flex-child-shrink cart__table-col cart__table-col--total medium-text-right']//span[@class='iw-currency__amount']");
   public static By CART_PROD_UNIT_PRICE=By.xpath(" //div[@class='columns flex-child-auto medium-flex-child-shrink cart__table-col cart__table-col--price medium-text-right small-negative-left-margin']//span[@class='iw-currency__amount'] | //div[contains(@class,'columns flex-child-auto medium-flex-child-shrink cart__table-col cart__table-col--price medium')]//span[@class='iw-currency__amount']");
   public static By CART_CONTRACT_DETAILS=By.xpath("//div[@class='row row__gutter--tiny collapse']//div//strong");
   public static By getPartNuminOrderdetails= By.xpath("//h3[@class='item-details__desc']/following::span[@class='item-details__part']");
 
   public static By NUMBER_PICKER_IN_PRODUCTQA_DISPLAY_PAGE = By.xpath("//input[@id='product-detail-order-number-picker']/parent::div/button[@class='number-picker-up ion-arrow-up-b']"); 
  public static By TRASH_ICON = By.xpath("//span[@class='ion-trash-a cart__trash-icon']");
  public static By QUANTITY_IN_CART=By.xpath("//label[text()='Item quantity']/following-sibling::input[@class=' cart-item__qty align-self-middle text-center']");
  public static By quantityInCart(String partNumber) {
	  return By.xpath("//p[text()='"+partNumber+"']//parent::div//parent::div//label[text()='Item quantity']/following-sibling::input[@class=' cart-item__qty align-self-middle text-center']");
  }
  public static By INAVLID_PART_NO_QUICK_SHOP_ERROR_MESSAGE=By.xpath("//div[@class='columns small-12 large-3 print-5 print-offset-7']//span[contains(text(),'Please enter a valid part number')]");
  public static By CSICOI_PRODUCTDEATILPG = By.xpath("//p[@class='prod-stock']");
  public static By CSI_COI_PRODUCT_PRICE=By.xpath("//p[@class='ips-price-contract']");
  
  public static By CART_PROD_DESC_RECENTLYADDEDTEM=By.xpath("(//div[@class='columns flex-child-auto cart__table-col--desc text-left']/a/h4)[2] | (//div[@class='columns flex-child-auto cart__table-col--desc text-left']/a/h4)");
  public static By CART_PROD_TOTAL_PRICE_RECENTLYADDEDTEM=By.xpath("(//div[@class='columns flex-child-auto medium-flex-child-shrink cart__table-col cart__table-col--total medium-text-right']//span[@class='iw-currency__amount'])[2] | (//div[@class='columns flex-child-auto medium-flex-child-shrink cart__table-col cart__table-col--total medium-text-right']//span[@class='iw-currency__amount'])");
  public static By CART_PROD_UNIT_PRICE_RECENTLYADDEDTEM=By.xpath(" (//div[@class='columns flex-child-auto medium-flex-child-shrink cart__table-col cart__table-col--price medium-text-right small-negative-left-margin']//span[@class='iw-currency__amount'])[2] |  (//div[@class='columns flex-child-auto medium-flex-child-shrink cart__table-col cart__table-col--price medium-text-right small-negative-left-margin']//span[@class='iw-currency__amount'])");
  public static By CART_PROD_QTY_RECENTLYADDEDTEM=By.xpath("(//label[text()='Item quantity']/following-sibling::input[@id='iw-checkout__cart-item-quantity'])[2] | (//label[text()='Item quantity']/following-sibling::input[@id='iw-checkout__cart-item-quantity'])");
  public static By CART_PROD_STOCK_RECENTLYADDEDTEM=By.xpath("(//label[text()='Item quantity']/following-sibling::input/following::span/span[contains(text(),' Stock: ')])[2] | (//label[text()='Item quantity']/following-sibling::input/following::span/span[contains(text(),' Stock: ')])");
  public static By ADD_TO_CART_IN_PRODUCT_SEARCHPAGE = By.xpath("//*[@id='tpl-product-detail-order-target']/div/div[contains(.,'Add to Cart')]|(//*[text()='Add to Cart'])[1]") ;

	public static By CART_PROD_DESC_RECENTLYADDEDTEM_loop=By.xpath("//div[@class='columns flex-child-auto cart__table-col--desc text-left']/a/h4");
	public static By CART_PROD_TOTAL_PRICE_RECENTLYADDEDTEM_loop=By.xpath("//div[@class='columns flex-child-auto medium-flex-child-shrink cart__table-col cart__table-col--total medium-text-right']//span[@class='iw-currency__amount']");
	public static By CART_PROD_UNIT_PRICE_RECENTLYADDEDTEM_loop=By.xpath(" //div[@class='columns flex-child-auto medium-flex-child-shrink cart__table-col cart__table-col--price medium-text-right small-negative-left-margin']//span[@class='iw-currency__amount'] | //div[@class='columns flex-child-auto cart__table-col--desc text-left']//following-sibling::div//span[text()='Unit price']//following-sibling::span[@class='iw-currency']");
	public static By CART_PROD_UNIT_PRICE_RECENTLYADDEDTEM_loop1=By.xpath(" //div[contains(@class,'columns flex-child-auto medium-flex-child-shrink cart__table-col cart__table-col--price medium')]//span[@class='iw-currency__amount'] | //div[@class='columns flex-child-auto cart__table-col--desc text-left']//following-sibling::div//span[text()='Unit price']//following-sibling::span[@class='iw-currency'] | //div[contains(@class,'columns flex-child-auto medium-flex-child-shrink cart__table-col cart__table-col--price medium-text')]//span[@class='iw-currency__amount']");
	
	public static By CART_PROD_QTY_RECENTLYADDEDTEM_loop=By.xpath("//label[text()='Item quantity']/following-sibling::input[@id='iw-checkout__cart-item-quantity'] | //label[text()='Item quantity']/following-sibling::input");

	public static By CART_PROD_STOCK_RECENTLYADDEDTEM_loop=By.xpath("//label[text()='Item quantity']/following-sibling::input/following::span/span[contains(text(),' Stock: ')] or contains(text(),'non')] | //*[contains(@class,'cart__stock-text')] ");
	//public static By CART_PROD_STOCK_RECENTLYADDEDTEM_loop=By.xpath("//label[text()='Item quantity']/following-sibling::input/following::span[contains(text(),' Stock: ') or contains(text(),'non')]");
	public static By Cart_Prod_Insight_Part_Number=By.xpath("//*[@class='cart__item-part cart__font-size--sm' and contains(text(),'Insight')]");


	public static By lblTotalAmountFromCartSearchResults= By.xpath("//*[@class='item-body item-body--bundle']//div//div[@class='columns small-12 large-1 print-2']//div[2]//span[@class='iw-currency item-body__value']");

}
    

