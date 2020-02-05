package com.insight.Lib;

import com.insight.ObjRepo.*;

import org.omg.PortableServer.THREAD_POLICY_ID;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.WeakHashMap;
import com.insight.ObjRepo.CommonCanadaPage;

public class CommonCanadaLib extends CommonCanadaPage {
    public boolean verifySelectedUser() throws Throwable {
        return isVisibleOnly(rbtnConvertAllTransactions, "radio button selected");
    }

    public boolean verifyDefaultScheduleReportNow(String reportName) throws Throwable {
        return isVisibleOnly(CanadaObj.getScheduleReport(reportName), "Default value of Schedule Report");
    }

    public void getListOfDeliveryMethodsOption() throws Throwable {
        List<WebElement> devMet = driver.findElements(DeliveryMethodsOption);
        for (int i = 0; i < devMet.size(); i++) {
            reporter.SuccessReport("Delivery method options are ", i + "  Delivery method option is ", devMet.get(i).getText());
        }
    }

    public void getListOfDeliveryFormatOption() throws Throwable {
        List<WebElement> devMet = driver.findElements(DeliveryFormatOption);
        for (int i = 0; i < devMet.size(); i++) {
            reporter.SuccessReport("Delivery Format options are ", i + "  Delivery Format option is ", devMet.get(i).getText());
        }
    }

    public void clickOnReportNameDD() throws Throwable {
        click(ReportName, "Click on Report Name ", "Report Name Drop down");
    }

    public void getListOfReportNameOption() throws Throwable {
        List<WebElement> devMet = driver.findElements(ReportNameOption);
        for (int i = 0; i < devMet.size(); i++) {
            reporter.SuccessReport("Delivery Report Name options are ", i + "  Delivery Report Name option is ", devMet.get(i).getText());
        }
    }

    public void clickOnDeliveryFormatDD() throws Throwable {
        click(DeliveryDateFormat, "Delivery format dd");
    }

    public void getDeliveryDateFormatDDOptions() throws Throwable {
        List<WebElement> devMet = driver.findElements(DeliveryDateFormatValue);
        for (int i = 0; i < devMet.size(); i++) {
            reporter.SuccessReport("Delivery Date format options are ", i + "  Delivery Date option  option is ", devMet.get(i).getText());
        }
    }

    public void clickOnDateRangeDD() throws Throwable {
        click(DateRange, "Date range dd");
    }

    public void getDateRangeDDOptions() throws Throwable {
        List<WebElement> devMet = driver.findElements(DateRangValue);
        for (int i = 0; i < devMet.size(); i++) {
            reporter.SuccessReport("Delivery Date Range options are ", i + "  Delivery Date Range  option is ", devMet.get(i).getText());
        }
    }

    public String getDefaultStartDate() throws Throwable {
        // return getText(defaultStartDate,"Strat date");
        return getAttributeValue(defaultStartDate, "value");
    }

    public String getDefaultEndDate() throws Throwable {
        // return getText(defaultStartDate,"Strat date");
        return getAttributeValue(defaultEndDate, "value");
    }

    public String currentMonthComparision() throws Throwable {
        LocalDate today = LocalDate.now();
        String Currentyear = today.toString().split("-")[0];
        String Currentmonth = today.toString().split("-")[1];
        if (Currentmonth.equalsIgnoreCase("01")) {
            Currentmonth = "Jan";
        }
        if (Currentmonth.equalsIgnoreCase("02")) {
            Currentmonth = "Feb";
            System.out.println(Currentmonth);
        }
        if (Currentmonth.equalsIgnoreCase("03")) {
            Currentmonth = "Mar";
            System.out.println(Currentmonth);
        }
        if (Currentmonth.equalsIgnoreCase("04")) {
            Currentmonth = "Apr";
            System.out.println(Currentmonth);
        }
        if (Currentmonth.equalsIgnoreCase("05")) {
            Currentmonth = "May";
            System.out.println(Currentmonth);
        }
        if (Currentmonth.equalsIgnoreCase("06")) {
            Currentmonth = "Jun";
            System.out.println(Currentmonth);
        }
        if (Currentmonth.equalsIgnoreCase("07")) {
            Currentmonth = "Jul";
            System.out.println(Currentmonth);
        }
        if (Currentmonth.equalsIgnoreCase("08")) {
            Currentmonth = "Aug";
            System.out.println(Currentmonth);
        }
        if (Currentmonth.equalsIgnoreCase("09")) {
            Currentmonth = "Sep";
            System.out.println(Currentmonth);
        }
        if (Currentmonth.equalsIgnoreCase("10")) {
            Currentmonth = "Oct";
            System.out.println(Currentmonth);
        }
        if (Currentmonth.equalsIgnoreCase("11")) {
            Currentmonth = "Nov";
            System.out.println(Currentmonth);
        }
        if (Currentmonth.equalsIgnoreCase("12")) {
            Currentmonth = "Dec";
            System.out.println(Currentmonth);
        }
        return Currentmonth;
    }

    public boolean verifyInvoiceDateDefaultCheck() throws Throwable {
        return isCheckBoxSelected(inVoiceDate);
    }

    public boolean verifySMART_CHECK() throws Throwable {
        return isCheckBoxSelected(SMART_CHECK);
    }

    public void addAvailableItemsToAllowItems() throws Throwable {
        List<WebElement> a = driver.findElements(AvailbileFieldsList);
        List<WebElement> b = driver.findElements(AllowedFieldsList);
        reporter.SuccessReport("Available Fields count", "Available field count", Integer.toString(a.size()));
        reporter.SuccessReport("Allowed Fields count", "Allowed field count", Integer.toString(b.size()));

        click(AvailbileFieldsList, "Available list");
        sendKeys(Keys.CONTROL + "a", "");
        /*sendKeys(Keys.CONTROL + "a","");*/
        click(addLeftToRight, "Add items from left to right");
        List<WebElement> c = driver.findElements(AllowedFieldsList);
        if (c.size() - b.size() == a.size()) {
            reporter.SuccessReport("Available items to allow items", " all the items are added to allowed", "true");
        }
    }

    public void selectHPINCRadioButton() throws Throwable {
        click(lblHPINC, "HP INC lable", "HP INC");
    }

    public boolean verifyHPLICBreadCrumbAvailability() throws Throwable {
        return isVisibleOnly(HPINCBreadCramb, "HP INC BreadCrumb");
    }

    public boolean validateNames(String expectedName, String actualName) throws Throwable {
       /* reporter.SuccessReport("String Searched::","", expectedName);
        reporter.SuccessReport("Result Found::","", actualName);*/
        String[] names = expectedName.split(" ");
        for(int index = 0; index < names.length; index++)
        {
            if(!actualName.toLowerCase().contains(names[index].toLowerCase()))
            {
                reporter.failureReport("Recently Viewd Products","Searched String is "+expectedName+
                        " mismatched with result found "+actualName,"false",driver);
                return false;
            }else{
                reporter.SuccessReport("Recently Viewd Products","Searched String ::"+expectedName+
                        " Exists Recently Viewed Product",expectedName+ "and " + actualName + " Both are matched" );

            }
        }
        return true;
    }

    public void clickOnAccountToolsAndClickOnProductGrp(String toolsMenuName) throws Throwable {
        Thread.sleep(20000);
        if (isVisibleOnly(CommonObj.CLOSEBUTTON_COOKIES, "close cookie")) {
            click(CommonObj.CLOSEBUTTON_COOKIES, "close cookie");
        }
        if (isElementPresent(InvoiceHistoryLib.COSE_ACCOUNT_TOOLS, "close account tools")) {
            click(InvoiceHistoryLib.COSE_ACCOUNT_TOOLS, "close account tools");
        }

    }

    public void getListOfCardTypesFoavailable() throws Throwable {
        click(CanadaObj.PaymentTypedpdn, "Payment Type drp");
        List<WebElement> card = driver.findElements(CanadaObj.PaymentTypedpdnOptions);
        for (int i = 0; i < card.size(); i++) {
            if (card.get(i).getText().equalsIgnoreCase("Discover Credit Card")) {
                reporter.SuccessReport("Discover Credit card", "Availability of Discover Credit card ", "true");
            } else {
                reporter.failureReport("Discover card", "Availability of Discover credit card is  ", " available card is " + card.get(i).getText(), driver);
            }

        }
    }

    public String verifyQuickShopErrorMsg() throws Throwable {
        String ErrorMessage = null;
        if (isElementPresent(QUICKSHOP_ERROR_MSG, "Error Msg Present")) {
            ErrorMessage = getText(QUICKSHOP_ERROR_MSG, "Error Msg Present");
            reporter.failureReport("Verify quick shop Error Message", " quick shop Error Message is present", ErrorMessage, driver);
        } else {

            reporter.SuccessReport("Verify quick shop Error Message", "quick shop Error Message is not present", "true", driver);
        }
        return ErrorMessage;
    }

    public boolean visibilityOfReferenceNumber() throws Throwable {
        return isVisibleOnly(lblReferenceNumberInOrderHistoryPage, "Reference Number in Order history page");
    }

    public String getReferenceNumberFromOrderHistoryPage() throws Throwable {
        return getText(lblReferenceNumberInOrderHistoryPage, "Reference Number in Order history page");
    }

    public boolean isVisibilityOfWelcomeMessage() throws Throwable {
        return isVisibleOnly(WelcomeMessageAtAccountToolPage, "Welcome Message" + getText(WelcomeMessageAtAccountToolPage, "Welcome Message"));
    }

    public String getResultsValueFromCurrentAccountPage() throws Throwable {

        String val = null;
        if (!isVisibleOnly(noResultsAvailable, "No Results found")) {
            int a = 0;
            if (isVisibleOnly(accountReferenceNumberResults, "Accounts Results")) {
                List<WebElement> acountList = driver.findElements(accountReferenceNumberResults);
                val = acountList.get(i).getText();
            } else {
                reporter.SuccessReport("No Results found ", "No Results found message appeared ", getText(noResultsAvailable, "No Results found"));
            }


        }
        return val;
    }
public String getAccountNumber() throws Throwable{
        return getText(llAccountNumber,"Account number");
}
    public void verifyLoggedInAs(String loggedInUser) throws Throwable {
        String text=getText(WelcomeMessageAtAccountToolPage, "You are logged in as");
        String str = loggedInUser;
        String[] arrOfStr = str.split(" ");
        String username= arrOfStr[0];
        if(text.toUpperCase().contains(username.toUpperCase())) {
            reporter.SuccessReport("System displays User Name on Current Account Tab in Account Management -Account Tools Page", "User Name on Current Account Tab is Exists",loggedInUser);
        }
        else {
            reporter.failureReport("System displays User Name on Current Account Tab in Account Management -Account Tools Page", "User Name on Current Account Tab is Not Exists",loggedInUser,driver);

        }
    }

    public boolean verifyCanadaFlag() throws Throwable{
        return isVisibleOnly(CAFlag,"Canada Flag");
    }

    public boolean verifyCanadaFlagAvailability() throws Throwable{
        click(dd_WebGrp, "WebGrp dropdown", "");
        boolean a= isVisibleOnly(CAFlagSelected,"Canada Flag");
        click(dd_WebGrp, "WebGrp dropdown", "");
        return a;
    }
    public void verifyCompanyLogosAvailability() throws Throwable{
        click(dd_WebGrp, "WebGrp dropdown", "WebGroup ");
       List<WebElement> logos=driver.findElements(countryLogos);
       List<WebElement> logoName=driver.findElements(countryNames);
       for(int i=0;i<logos.size();i++){
          assertTrue( logos.get(i).isDisplayed(),"Country name is " + logoName.get(i).getText());
       }
        click(dd_WebGrp, "WebGrp dropdown", "WebGroup ");
    }
    public boolean verifyPlaceOrderHeader() throws Throwable{
        return isVisibleOnly(PLACE_ORDER_PAGE_TEXT,"Place order header");
    }
    public boolean verifyDiscoveryCardAvailability() throws Throwable{
        return isVisibleOnly(discoveryCardInfo,"DiscoveryCard Availablility ");
    }
    public String getDiscoveryCardInformation() throws Throwable{
  return  getText(discoveryCardInfo,"DiscoveryCard Availablility ");
    }
    public void getRemoveSuccessMessage() throws Throwable{
        getText(removeSuccessMessage,"Default account removed ");
    }

    public void clickOnSwitchToAccountForSelectedAccountRefNum(String acc) throws Throwable{
        List<WebElement> accn = driver.findElements(getAccountReferenceNumberResult);
        List<WebElement> swichBu = driver.findElements(SWITCH_ACCOUNT_LINK);

       for (int i=0 ; i<accn.size();i++){
           if(accn.get(i).getText().equalsIgnoreCase(acc))
           {
            swichBu.get(i).click();
           }
       }
    }

    public String getDefaultAccountNumber() throws Throwable{
        String val =getText(accRefNumberFromDefaultAccount,"Reference Number");
        String refNum = val.toString().split(": ")[1];
  return refNum;
    }
    public void clickOnUSAccountUnderWebGroup() throws Throwable{
        click(dd_WebGrp, "WebGrp dropdown", "WebGroup ");
        click(TU_CANoDCTestlnk,"TU_CANoDCTestlnk","TU_CANoDCTestlnk");
        waitForVisibilityOfElement(WEBGRPCHANGE_POPUP, "Switch To Webgrp PopUp");
        click(CONTINUEBUTTON_WEBGRPCHANGE, "Continue");
    }
    public void clickOnCloseButtonOnWelcomeInsightPopup() throws Throwable{
        if(isVisibleOnly(closeWelcomeInsightPopup,"Popup Welcome insight popup")){
            click(closeWelcomeInsightPopup,"Welcome insight popup","Welcome insight popup");
        }
    }
}

