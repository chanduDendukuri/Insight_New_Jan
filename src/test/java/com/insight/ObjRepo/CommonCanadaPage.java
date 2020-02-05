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






}
