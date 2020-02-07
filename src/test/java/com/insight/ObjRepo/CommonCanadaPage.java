package com.insight.ObjRepo;

import org.openqa.selenium.By;
import com.insight.accelerators.ActionEngine;

public class CommonCanadaPage extends ActionEngine{

    public static By rbtnConvertAllTransactions = By.xpath("//*[@value='SUPPLY']");
    public static By DeliveryMethodsOption = By.xpath("//select[@id='ddlDeliveryMethod']");
    public static By DeliveryFormatOption = By.xpath("//select[@id='ddlDeliveryFormat']//option");
    public static By ReportName = By.xpath("//select[@id='ddlReportNameType']");
    public static By ReportNameOption = By.xpath("//select[@id='ddlReportNameType']//option");
    public static By DeliveryDateFormat = By.xpath("//select[@id='deliveryDateFormat']");
    public static By DeliveryDateFormatValue = By.xpath("//select[@id='deliveryDateFormat']//option");
    public static By DateRange = By.xpath("//select[@id='ddlQuickDates']");
    public static By DateRangValue = By.xpath("//select[@id='ddlQuickDates']/option");
    public static By defaultStartDate = By.xpath("//*[@id='ordFullStartDate']");
    public static By defaultEndDate = By.xpath("//*[@id='ordFullEndDate']");
    public static By inVoiceDate = By.xpath("//*[@name='orderInvRadioID' and @aria-label='invoiceDate']");
    public static By SMART_CHECK = By.xpath("//input[@name='smartTrackerId']");
    public static By AvailbileFieldsList = By.xpath("//*[@id='AdHocAvailableSel']//option");
    public static By AllowedFieldsList = By.xpath("//*[@id='AdHocAllowedSel']//option");
    public static By addLeftToRight = By.xpath("//*[@id='manageReq_LtoR']");

    public static By lblHPINC=By.xpath("//*[@aria-label='Filter By Manufacturers']//div[@id='js-mfr-filter-items']//label//span[@data-gtm-info='HP INC']");
    public static By HPINCBreadCramb=By.xpath("//*[@class='filter-item js-filter-item']//a[contains(text(),'HP INC')]");
    public static By QUICKSHOP_ERROR_MSG = By.xpath("//span[@class='columns iw-message__text' and contains(text(),'Please enter')]");
    public static By lblReferenceNumberInOrderHistoryPage = By.xpath("//*[text()='Reference number']//following-sibling::span");

    public static By WelcomeMessageAtAccountToolPage = By.xpath("//*[@class='welcomePageUserInfoDivStyle']//strong");
    public static By noResultsAvailable=By.xpath("//*[text()='No results were found based on your search criteria']");
    public static By accountResults=By.xpath("//*[@id='searchAcctResults'] //tbody//tr//td[@class='footable-visible footable-first-column']");
    public static By accountReferenceNumberResults=By.xpath("//*[@id='searchAcctResults'] //tbody//tr//td[2]");
    public static By llAccountNumber = By.xpath("//*[@class='medium-4 columns currentAcctLvl']//p//following-sibling::div[1]");
    public static By usFlag = By.xpath("//*[text()='TU_CANoDCTest']//span");
    public static By CAFlag = By.xpath("//*[text()='TU_CASPLAAccount']//span");
    public static By CAFlagSelected = By.xpath("//*[text()='TU_CASPLAAccount']//span//..//following-sibling::span[text()='Current']");
    public static By dd_WebGrp = By.xpath("//div[@class='c-header__middle']//button[@id='webGroupDropdown']");
    public static By countryLogos = By.cssSelector("span[class='c-header-account-menu__flag'] svg");
    public static By countryNames = By.xpath("//*[@class='o-list-bare__item  c-header-account-menu__item']//button");
    public static By PLACE_ORDER_PAGE_TEXT=By.xpath("//h1[text()='Place order']");
    public static By discoveryCardInfo=By.xpath("//*[@class='hide-for-print icon-cards icon-cards--DISC']//span");
    public static By removeSuccessMessage=By.xpath("//*[@class='medium-4 columns defaultAcctLvl']//div");
    public static By getAccountReferenceNumberResult=By.xpath("//td[@class='footable-visible footable-first-column']//following-sibling::td[1]");
    public static By SWITCH_ACCOUNT_LINK=By.xpath("//td[@class='footable-visible footable-first-column']//following-sibling::td[3]");
    public static By accRefNumberFromDefaultAccount= By.xpath("//div[@class='medium-4 columns currentAcctLvl']//div[1]");
    public static By TU_CASPLAAccountlink= By.xpath("//*[@class='o-list-bare__item  c-header-account-menu__item']//button[text()='TU_CASPLAAccount']");
    public static By TU_CANoDCTestlnk= By.xpath("//*[@class='o-list-bare__item  c-header-account-menu__item']//button[text()='TU_CANoDCTest']");
    public static By WEBGRPCHANGE_POPUP = By.xpath("//h2[contains(text(),'You are about to change your web group')]");
    public static By CONTINUEBUTTON_WEBGRPCHANGE = By.xpath("//button[contains(text(),'Continue') and @type='button']");
    public static By closeWelcomeInsightPopup = By.xpath("//a[@class='close-reveal-modal' and @aria-label='Close']");
    public static By lblHostedLicensingAdminPage = By.xpath("//h1[text()='Hosted Licensing Administration']");
    public static By lblReturnTOSoftwareLicenseAggrements = By.xpath("//*[text()='Return to My Software License Agreements']");
    public static By lblManifacturerNumberFormProductScreen = By.xpath("//*[@class='product-specs top-horizontal']//td[2]");
    public static By lblProductStandards = By.xpath("//div[@id='csProductGroupsHeading']");
    public static By addToCartIcon = By.xpath("//*[contains(@id,'cartIcon')]");
    public static By viewCartlnk = By.xpath("//span[text()='View Cart >>']");
    public static By lblCartLebel=By.xpath("//*[@class='shopping-cart__header-title']");











}
