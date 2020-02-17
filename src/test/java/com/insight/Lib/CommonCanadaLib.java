package com.insight.Lib;

import com.insight.ObjRepo.*;

import freemarker.cache.WebappTemplateLoader;
import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.core.pattern.ThrowablePatternConverter;
import org.omg.PortableServer.THREAD_POLICY_ID;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;

import java.io.File;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.WeakHashMap;
import com.insight.ObjRepo.CommonCanadaPage;
import com.insight.ObjRepo.*;

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
        for (int index = 0; index < names.length; index++) {
            if (!actualName.toLowerCase().contains(names[index].toLowerCase())) {
                reporter.failureReport("Recently Viewd Products", "Searched String is " + expectedName +
                        " mismatched with result found " + actualName, "false", driver);
                return false;
            } else {
                reporter.SuccessReport("Recently Viewd Products", "Searched String ::" + expectedName +
                        " Exists Recently Viewed Product", expectedName + "and " + actualName + " Both are matched");

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

    public String getAccountNumber() throws Throwable {
        return getText(llAccountNumber, "Account number");
    }

    public void verifyLoggedInAs(String loggedInUser) throws Throwable {
        String text = getText(WelcomeMessageAtAccountToolPage, "You are logged in as");
        String str = loggedInUser;
        String[] arrOfStr = str.split(" ");
        String username = arrOfStr[0];
        if (text.toUpperCase().contains(username.toUpperCase())) {
            reporter.SuccessReport("System displays User Name on Current Account Tab in Account Management -Account Tools Page", "User Name on Current Account Tab is Exists", loggedInUser);
        } else {
            reporter.failureReport("System displays User Name on Current Account Tab in Account Management -Account Tools Page", "User Name on Current Account Tab is Not Exists", loggedInUser, driver);

        }
    }

    public boolean verifyCanadaFlag() throws Throwable {
        return isVisibleOnly(CAFlag, "Canada Flag");
    }

    public boolean verifyCanadaFlagAvailability() throws Throwable {
        click(dd_WebGrp, "WebGrp dropdown", "");
        boolean a = isVisibleOnly(CAFlagSelected, "Canada Flag");
        click(dd_WebGrp, "WebGrp dropdown", "");
        return a;
    }

    public void verifyCompanyLogosAvailability() throws Throwable {
        click(dd_WebGrp, "WebGrp dropdown", "WebGroup ");
        List<WebElement> logos = driver.findElements(countryLogos);
        List<WebElement> logoName = driver.findElements(countryNames);
        for (int i = 0; i < logos.size(); i++) {
            assertTrue(logos.get(i).isDisplayed(), "Country name is " + logoName.get(i).getText());
        }
        click(dd_WebGrp, "WebGrp dropdown", "WebGroup ");
    }

    public boolean verifyPlaceOrderHeader() throws Throwable {
        return isVisibleOnly(PLACE_ORDER_PAGE_TEXT, "Place order header");
    }

    public boolean verifyDiscoveryCardAvailability() throws Throwable {
        return isVisibleOnly(discoveryCardInfo, "DiscoveryCard Availablility ");
    }

    public String getDiscoveryCardInformation() throws Throwable {
        return getText(discoveryCardInfo, "DiscoveryCard Availablility ");
    }

    public void getRemoveSuccessMessage() throws Throwable {
        getText(removeSuccessMessage, "Default account removed ");
    }

    public void clickOnSwitchToAccountForSelectedAccountRefNum(String acc) throws Throwable {
        List<WebElement> accn = driver.findElements(getAccountReferenceNumberResult);
        List<WebElement> swichBu = driver.findElements(SWITCH_ACCOUNT_LINK);

        for (int i = 0; i < accn.size(); i++) {
            if (accn.get(i).getText().equalsIgnoreCase(acc)) {
                swichBu.get(i).click();
                reporter.SuccessReport("Default Account number", "You are selecting default Account link", swichBu.get(i).getText());
                break;
            }
        }
    }

    public String getDefaultAccountNumber() throws Throwable {
        String val = getText(accRefNumberFromDefaultAccount, "Reference Number");
        String refNum = val.toString().split(": ")[1];
        return refNum;
    }

    public void clickOnUSAccountUnderWebGroup() throws Throwable {
        click(dd_WebGrp, "WebGrp dropdown", "WebGroup ");
        click(TU_CANoDCTestlnk, "TU_CANoDCTestlnk", "TU_CANoDCTestlnk");
        waitForVisibilityOfElement(WEBGRPCHANGE_POPUP, "Switch To Webgrp PopUp");
        click(CONTINUEBUTTON_WEBGRPCHANGE, "Continue");
    }

    public void clickOnCloseButtonOnWelcomeInsightPopup() throws Throwable {
        if (isVisibleOnly(closeWelcomeInsightPopup, "Popup Welcome insight popup")) {
            click(closeWelcomeInsightPopup, "Welcome insight popup", "Welcome insight popup");
        }
    }

    public boolean HostedLicensingAdminPageVerification() throws Throwable {
        return isVisibleOnly(lblHostedLicensingAdminPage, "Hosted Licensing Admin Page");
    }

    public boolean verifyReturnTOSoftwareLicenseAggrements() throws Throwable {
        return isVisibleOnly(lblReturnTOSoftwareLicenseAggrements, "ReturnTOSoftwareLicenseAggrements");
    }

    public String getManfNumberFromProductSearchScreen() throws Throwable {
        return getText(lblManifacturerNumberFormProductScreen, "Manufacturer number");
    }

    public boolean verifyCompanyStandard() throws Throwable {
        return isVisibleOnly(lblProductStandards, "Product Standards");
    }

    public void addToOderInProductStandardsPage() throws Throwable {
        List<WebElement> cart = driver.findElements(addToCartIcon);
        for (int i = 0; i < cart.size(); i++) {
            cart.get(i).click();
            if (isVisibleOnly(viewCartlnk, "View Cart link")) {
                reporter.SuccessReport("Cart Icon", "Clicking on Cart icon", "is True");

                click(viewCartlnk, "View Cart link");
                break;
            }
        }
    }

    public void clickOnEmptyCart() throws Throwable {
        Thread.sleep(2000);
        //  if(isVisibleOnly(emptyCartLink,"Empty cart link")){
        //click(emptyCartLink,"Empty Cart Link");
        clickUntil(emptyCartLink, CartObj.EMPTY_CART_MESSAGE, "Empty Cart Link");
        //}
    }

    public boolean verifyingQuickSearch() throws Throwable {
        return isVisibleOnly(CartObj.QUICK_SHOP_ITEM_FIELD, "Quick Shop Item");
    }

    public void verifyTheResultsForSearchTerm(String productName) throws Throwable {

        List<WebElement> pro = driver.findElements(CommonObj.RESULT_FOR_SEARCH);
        for (int i = 0; i < pro.size(); i++) {
            String res = pro.get(i).getText().replace("\"", "");
            if (res.equals(productName)) {
                reporter.SuccessReport("Verify the results for search term in products display page ",
                        "Verification is sucessfull.search term / Bread crumb is: ", "Bread crumb : " + res);
            } else {
                reporter.failureReport("Verify the results for search term in products display page",
                        "Expected search result is  " + productName + "Actual is: ", res);
            }

        }

    }

    public void clickOnStoredAddressesLink() throws Throwable {
        if (isVisibleOnly(StoredAddresses, "Stored Addresses")) {
            click(StoredAddresses, "Stored Address Link");
        }
    }

    public String getStoredAddresses() throws Throwable {
        return getText(getStoredAddresses, "Storead Address");
    }

    public boolean verifySroredAddresswithSearchResults() throws Throwable {
        boolean Status = false;
        List<WebElement> sAddress = driver.findElements(getSroredAddressFromResultsGrid);
        for (int i = 0; i < sAddress.size(); i++) {
            if (sAddress.get(i).getText().contains("CA")) {
                Status = true;
                reporter.SuccessReport("Verifying stored address ", "Verifying stored address with expected", "Clicked on " + sAddress.get(i).getText());
                click(rbtnDefaultAddress, "Default address from results Grid");
                click(btnContinue, "continue button");
                break;
            } else {
                Status = false;
            }
        }
        return Status;
    }

    public boolean verifyAddToCartLabelAvailable() throws Throwable {
        return isVisibleOnly(productsDisplayInfoObj.ADDED_TO_CART_LABEL, "Add to cart label");
    }

    /* public void verifyExportFile(String fileName,String sheetName, String rowNumber, String columnHeaders) throws Throwable {
         Thread.sleep(10000);
         String sfile = System.getProperty("user.dir") + "\\" + "DownloadedFiles" + "\\" + fileName+".xls";
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
 */
    public void clickOnAddToCartButtonUnderWarrentyDynamically() throws Throwable {
        List<WebElement> atc = driver.findElements(addTocartButtonInWarrenty);
        for (int i = 0; i < atc.size(); i++) {
            atc.get(i).click();
            reporter.SuccessReport("Clicked on ADd to cart button", "Clicked on ADd to cart button", "Clicked on ADd to cart button");
            break;
        }
    }
    public void clickOnAddToCartButtonUnderWarrenty() throws Throwable {
        click(addTocartButtonInWarrenty1, "Add to cart button");
            //reporter.SuccessReport("Clicked on ADd to cart button", "Clicked on ADd to cart button", "Clicked on ADd to cart button");
            
        
    }
    
    public void getPriceinWarrenty() throws Throwable {
    	if(isElementPresent(priceInWarrenty, "Price in warrenty tab")) {
    		List<WebElement> atc = driver.findElements(priceInWarrenty);
    		//String price=getText(priceInWarrenty, "Price in warrenty tab");
    		for (int i = 0; i < atc.size(); i++) {
                String price=atc.get(i).getText();
    		reporter.SuccessReport("Price in warrenty tab", "Price in warrenty tab exists", "Warrenty price is: "+price, driver);
    		break;
    		}
    	}
    	else {
    		reporter.failureReport("Price in warrenty tab", "Price in warrenty tab does not exists", "", driver);
    	}
    	
    }
//btnAddToCartIst
    public void verifyExportFile(String sheetName, String rowNumber, String columnHeaders, File fileN) throws Throwable {
       Thread.sleep(60000);

        // String content = Files.readString(Paths.get(fileN));
        // String content = FileUtils.readFileToString(new File(String.valueOf(fileN)), StandardCharsets.UTF_8);
        String content = String.valueOf(fileN);
        File file = new File(content);
        if (file.exists()) {
            List<String> downloadedExcelContent = CommonLib.readRowFromExcel(content, sheetName, Integer.parseInt(rowNumber));
            List<String> acutalContent = Arrays.asList(columnHeaders.split(","));
            System.out.println("Compare content" + downloadedExcelContent.equals(acutalContent));
            if (downloadedExcelContent.equals(acutalContent)) {
                reporter.SuccessReport(""+columnHeaders, "columns are avilable in Excel File", "columns: " + columnHeaders);
            } else {
                reporter.failureReport(""+columnHeaders, columnHeaders + " are not avilable", "", driver);
            }
        } else {
            reporter.failureReport("ExportCart Excel File", "File dose not exists", "", driver);
        }
        System.out.println("File Deletion :" + file.delete());
        if (file.exists()) {
            file.delete();
            reporter.SuccessReport("ExportCart Excel File", "File closed", "");
        } else {
            // do nothing
        }
    }

    public String FileNameWithdateSplit(String fileName) throws Throwable {
        DateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        //Date datev = new Date();

        Date datev1 = new Date();
        String strDate = sdf.format(datev1);

        String yyyy = strDate.split("/")[0].replace("/", "");
        String MM = strDate.split("/")[1].replace("/", "");
        String dd = strDate.split("/")[2].replace("/", "");
        String HH = strDate.split("")[3].replace("/", "_");
        String mm = strDate.split("")[3].replace("/", "_");
        String file = fileName + "_" + yyyy + MM + dd + "_" + mm + "*";
        return file;
    }

    public void clickOnExportToExcellink() throws Throwable {
        click(exportToXLSlink, "Export to Excel Link", "Export to Excel Link");
    }

    public void clickOnIUSAMandatoryCTOlink() throws Throwable {
        if (isVisibleOnly(IUSAMandatoryCTOlink, "IUSAMandatoryCTO link ")) {
            click(IUSAMandatoryCTOlink, "IUSAMandatoryCTOlink ", "IUSAMandatoryCTOlink");
        } else {
            reporter.failureReport("Availability of IUSAMandatoryCTOlink ", " IUSAMandatoryCTOlink is  available", "false", driver);
        }
    }

    public void clickOnAddToOrderButton() throws Throwable {
        if (isVisibleOnly(AddtoOrder, "Add to Order")) {
            click(AddtoOrder, "Add to Order Button", "Add to order Button");
        } else {
            reporter.failureReport("Availability of Add to Order  ", " Add to Order button is  available", "false", driver);

        }
    }

    public void clickOnViewToCartlink() throws Throwable {
        if (isVisibleOnly(viewCartlnk, "View Cart link")) {
            reporter.SuccessReport("Cart Icon", "Clicking on Cart icon", "is True");

            click(viewCartlnk, "View Cart link");

        } else {
            reporter.SuccessReport("Cart Icon", "Clicking on Cart icon", "is false", driver);

        }
    }

    public static File getLatestFilefromDir() {
        File dir = new File(System.getProperty("user.dir") + "\\" + "DownloadedFiles" + "\\");
        File[] files = dir.listFiles();
        if (files == null || files.length == 0) {
            return null;
        }

        File lastModifiedFile = files[0];
        for (int i = 1; i < files.length; i++) {
            if (lastModifiedFile.lastModified() < files[i].lastModified()) {
                lastModifiedFile = files[i];
            }
        }
        return lastModifiedFile;
    }

    public boolean verifyBlackBerryLogo() throws Throwable {
        return isVisibleOnly(blackBerrylogo, "Black berry Logo");
    }

    public boolean verifySearchByAllProd() throws Throwable {

        return isVisibleOnly(searchByProductByCategory,"Search By Product by Category");
}
    public boolean verifyTopBrands() throws Throwable {

        return isVisibleOnly(lblTopBrands,"Top Brands");
}

    public void clickOnAddToCartButtonUnderProductDynamically(String quanity) throws Throwable {
        List<WebElement> atc = driver.findElements(btnAddToCartIst);
        List<WebElement> qtn = driver.findElements(txtQuantityValue);

        for (int i = 0; i < atc.size(); i++) {
            qtn.get(i).clear();
            qtn.get(i).sendKeys(quanity);
            reporter.SuccessReport("Successfully entered quantity", "Entered Quantity", quanity);

            atc.get(i).click();
            reporter.SuccessReport("Clicked on ADd to cart button", "Clicked on ADd to cart button", "Clicked on ADd to cart button");
            break;
        }
    }
//
public void getListOfCardTypes() throws Throwable{
    click(PaymentTypedpdn,"Payment Type drp");
    List<WebElement> card = driver.findElements(PaymentTypedpdnOptions);
    for(int i=0;i<card.size();i++){
        if(card.get(i).getText().equalsIgnoreCase("Discover Credit Card")){
            reporter.failureReport("Discover Credit card","Availability of Discover Credit card ", "true",driver);
        }else{
            reporter.SuccessReport("Discover card","Availability of Discover credit card is  "," available card is "+card.get(i).getText());
        }

    }
}

public void getListOfAvailableBreadCrumbs() throws Throwable{
        List<WebElement> bcrumb = driver.findElements(BreadCrumbdynamic);
        for (int i=0;i<bcrumb.size();i++){
             // String breadcrumb =bcrumb.get(i).getText();
            reporter.SuccessReport("BreadCrumbsverification","VVerification of Breadcrumbs "," available breadcrumbs are"+bcrumb.get(i).getText());


        }


}
    public void clickOnPrintIcon() throws Throwable{
        if(isVisibleOnly(printLinkiCon,"Print Icon")){
            click(printLinkiCon,"Print icon");
        }
    }
    public boolean availabilityOfPrintPage() throws Throwable{
        return isVisibleOnly(availabilityOfPrintPage,"Print Page Opened");
    }
    public void closePrintPage() throws Throwable{
        if(availabilityOfPrintPage()){
            click(closePrintPage,"Close Print Page");
        }
    }
    public String getTotalPriceFromSearchProductsPage() throws Throwable{
        return getText(summaryTotalInSearchPage,"Total Price");
    }

    public void getProductDescriptionInViewCartPage() throws Throwable{
        List<WebElement> DescVal= driver.findElements(CART_PROD_DESC_RECENTLYADDEDTEM_loop);
        for(int i=0 ; i<DescVal.size();i++){
            reporter.SuccessReport("Product Description ","Item added to cart under contract",DescVal.get(i).getText());
        }

    }
    public void getAllConractDetails() throws Throwable{
        List<WebElement> DescVal= driver.findElements(AllContractDetails);
        for(int i=0 ; i<DescVal.size();i++){
            reporter.SuccessReport("Product Description ","Item added to cart under contract",DescVal.get(i).getText());
        }

    }
    public boolean availabilityOfPanasonicHeader() throws Throwable{
        return isVisibleOnly(newURLHeader,"State local government verification ");
    }
    public String getHeaderValue() throws Throwable{
        return getText(newURLHeader,"State local government verification ");
    }

}

