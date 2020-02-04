package com.insight.Lib;

import com.insight.ObjRepo.CommonCanadaPage;

import  com.insight.ObjRepo.CanadaObj;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.WeakHashMap;

public class CommonCanadaLib extends CommonCanadaPage {
    public boolean verifySelectedUser() throws Throwable{
        return isVisibleOnly(rbtnConvertAllTransactions,"radio button selected");
    }
    public boolean verifyDefaultScheduleReportNow(String reportName) throws Throwable{
        return isVisibleOnly(CanadaObj.getScheduleReport(reportName),"Default value of Schedule Report");
    }

    public void getListOfDeliveryMethodsOption() throws Throwable{
        List<WebElement> devMet=driver.findElements(DeliveryMethodsOption);
        for(int i=0;i<devMet.size();i++){
            reporter.SuccessReport("Delivery method options are ",i +"  Delivery method option is ",devMet.get(i).getText());
        }
    }
    public void getListOfDeliveryFormatOption() throws Throwable{
        List<WebElement> devMet=driver.findElements(DeliveryFormatOption);
        for(int i=0;i<devMet.size();i++){
            reporter.SuccessReport("Delivery Format options are ",i +"  Delivery Format option is ",devMet.get(i).getText());
        }
    }
    public void clickOnReportNameDD() throws Throwable{
        click(ReportName,"Click on Report Name ","Report Name Drop down");
    }
    public void getListOfReportNameOption() throws Throwable{
        List<WebElement> devMet=driver.findElements(ReportNameOption);
        for(int i=0;i<devMet.size();i++){
            reporter.SuccessReport("Delivery Report Name options are ",i +"  Delivery Report Name option is ",devMet.get(i).getText());
        }
    }
    public void clickOnDeliveryFormatDD() throws Throwable{
        click(DeliveryDateFormat,"Delivery format dd");
    }
    public void getDeliveryDateFormatDDOptions() throws Throwable{
        List<WebElement> devMet=driver.findElements(DeliveryDateFormatValue);
        for(int i=0;i<devMet.size();i++){
            reporter.SuccessReport("Delivery Date format options are ",i +"  Delivery Date option  option is ",devMet.get(i).getText());
        }
    }
    public void clickOnDateRangeDD() throws Throwable{
        click(DateRange,"Date range dd");
    }
    public void getDateRangeDDOptions() throws Throwable{
        List<WebElement> devMet=driver.findElements(DateRangValue);
        for(int i=0;i<devMet.size();i++){
            reporter.SuccessReport("Delivery Date Range options are ",i +"  Delivery Date Range  option is ",devMet.get(i).getText());
        }
    }

    public String getDefaultStartDate() throws Throwable{
      // return getText(defaultStartDate,"Strat date");
       return getAttributeValue(defaultStartDate,"value");
    }
    public String getDefaultEndDate() throws Throwable{
        // return getText(defaultStartDate,"Strat date");
        return getAttributeValue(defaultEndDate,"value");
    }
    public String currentMonthComparision() throws Throwable{
        LocalDate today = LocalDate.now();
        String Currentyear = today.toString().split("-")[0];
        String Currentmonth = today.toString().split("-")[1];
        if(Currentmonth.equalsIgnoreCase("01")){
            Currentmonth="Jan";
        }
        if(Currentmonth.equalsIgnoreCase("02"))
        {
            Currentmonth="Feb";
            System.out.println(Currentmonth);
        }
        if(Currentmonth.equalsIgnoreCase("03"))
        {
            Currentmonth="Mar";
            System.out.println(Currentmonth);
        }
        if(Currentmonth.equalsIgnoreCase("04"))
        {
            Currentmonth="Apr";
            System.out.println(Currentmonth);
        }
        if(Currentmonth.equalsIgnoreCase("05"))
        {
            Currentmonth="May";
            System.out.println(Currentmonth);
        }
        if(Currentmonth.equalsIgnoreCase("06"))
        {
            Currentmonth="Jun";
            System.out.println(Currentmonth);
        }
        if(Currentmonth.equalsIgnoreCase("07"))
        {
            Currentmonth="Jul";
            System.out.println(Currentmonth);
        }
        if(Currentmonth.equalsIgnoreCase("08"))
        {
            Currentmonth="Aug";
            System.out.println(Currentmonth);
        }
        if(Currentmonth.equalsIgnoreCase("09"))
        {
            Currentmonth="Sep";
            System.out.println(Currentmonth);
        }
        if(Currentmonth.equalsIgnoreCase("10"))
        {
            Currentmonth="Oct";
            System.out.println(Currentmonth);
        }
        if(Currentmonth.equalsIgnoreCase("11"))
        {
            Currentmonth="Nov";
            System.out.println(Currentmonth);
        }
        if(Currentmonth.equalsIgnoreCase("12"))
        {
            Currentmonth="Dec";
            System.out.println(Currentmonth);
        }
        return Currentmonth;
    }

    public boolean verifyInvoiceDateDefaultCheck() throws Throwable{
      return   isCheckBoxSelected(inVoiceDate);
    }
    public boolean verifySMART_CHECK() throws Throwable{
      return   isCheckBoxSelected(SMART_CHECK);
    }

    public void addAvailableItemsToAllowItems() throws Throwable{
        List<WebElement> a = driver.findElements(AvailbileFieldsList);
        List<WebElement> b = driver.findElements(AllowedFieldsList);
        reporter.SuccessReport("Available Fields count","Available field count" ,Integer.toString(a.size()));
        reporter.SuccessReport("Allowed Fields count","Allowed field count" ,Integer.toString(b.size()));

        click(AvailbileFieldsList,"Available list");
        sendKeys(Keys.CONTROL+"a","");
        /*sendKeys(Keys.CONTROL + "a","");*/
        click(addLeftToRight,"Add items from left to right");
        List<WebElement> c = driver.findElements(AllowedFieldsList);
        if(c.size()-b.size() ==a.size())
        {
            reporter.SuccessReport("Available items to allow items", " all the items are added to allowed" ,"true");
        }
    }
    public void selectHPINCRadioButton() throws Throwable{
        click(lblHPINC,"HP INC lable","HP INC");
    }
    public boolean verifyHPLICBreadCrumbAvailability() throws Throwable{
        return isVisibleOnly(HPINCBreadCramb,"HP INC BreadCrumb");
    }
}