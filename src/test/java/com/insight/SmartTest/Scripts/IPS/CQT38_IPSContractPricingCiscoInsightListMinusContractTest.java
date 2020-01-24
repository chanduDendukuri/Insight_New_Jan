package com.insight.SmartTest.Scripts.IPS;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.insight.SmartTest.Lib.HomeLib;
import com.insight.SmartTest.Lib.loginLib;
import com.insight.accelerators.ReportControl;
import com.insight.accelerators.TestEngineWeb;
import com.insight.googledrive.ReportStatus;
import com.insight.utilities.TestUtil;

public class CQT38_IPSContractPricingCiscoInsightListMinusContractTest extends HomeLib{
	
	loginLib loginlib=new loginLib();

	// #############################################################################################################
		// # Name of the Test : CQT38_IPSContractPricingCiscoInsightListMinusContract
		// # Migration Author : Cigniti Technologies
		// #
		// # Date of Migration : OCT 2019
		// # DESCRIPTION : Purpose of this test method is to verify the compare
		// functionality in the products display page.
		// # Parameters : StartRow ,EndRow , nextTestJoin
		// #
		// ###############################################################################################################

		@Parameters({ "StartRow", "EndRow", "nextTestJoin" })
		@Test
		public void TC_CQT38(int StartRow, String EndRow, boolean nextTestJoin) throws Throwable {
			int counter = 0;
			try {
				int intStartRow = StartRow;
				int intEndRow = ReportControl.fnGetEndRowCunt(EndRow, "CQT38_IPSContractPricingCiscoInsightListMinusContract", TestData_Smart, "Create_Quote");
				for (int intCounter = intStartRow; intCounter <= intEndRow; intCounter++) {
					try {
					counter = intCounter;
					fnOpenTest();
					ReportStatus.fnDefaultReportStatus();
					ReportControl.intRowCount = intCounter;
					Hashtable<String, String> data = TestUtil.getDataByRowNo("CQT38_IPSContractPricingCiscoInsightListMinusContract", TestData_Smart, "Create_Quote", intCounter);
					TestEngineWeb.reporter.initTestCaseDescription("IPSContractPricingCiscoInsightListMinusContract");
					MethodfromSmartLoginTillSalesOrgselection(data.get("UserName"),data.get("Password"),data.get("SoldToAcct"),data.get("SalesOrg"));
					clickAcquireEstimateBtn();
					enterEstimateNumber(data.get("EstimateID"));
					clickonConXSystem(data.get("LineItem1"));//000010
					clickonRightArrowforLineItem();
					clickOnTabsInLineItemDetailsPopUp(data.get("Tab1"));//VC
					getDurationTime(data.get("LineItem2"),data.get("Duration1"));
					clickonRightArrowforLineItem();
					clickonRightArrowforLineItem();
					getDurationTime(data.get("LineItem3"),data.get("Duration2"));
					clickonRightArrowforLineItem();
					clickonRightArrowforLineItem();
					getDurationTime(data.get("LineItem4"),data.get("Duration3"));
					clickOnTabsInLineItemDetailsPopUp(data.get("Tab2"));//Contracts
					clickOnContractId(data.get("contactid"));
					clickOnCopyContarctToallLineItems();
					clickYesButtontocloseDocument();
					clickDoneButton();
					clickUpdateCosting();
					
					clickonSaveasQuote();
					enterCancelButtonInPoupHdr();
					String QuoteNum= GetQuoteNumber();
					if(QuoteNum!=null) {
						reporter.SuccessReport("QuoteNumber:", "Quotenumber is displayed", "");
					}
					else {
						reporter.failureReport("QuoteNumber:", "Quotenumber is not displayed", "",driver);
					}
					clickonConXSystem(data.get("LineItem1"));//000010
					clickOnTabsInLineItemDetailsPopUp(data.get("Tab3"));//Pricing
					clickOnTabsInLineItemDetailsPopUp(data.get("Tab3"));//Pricing
			
					// Get data from the pricing tab
					List<String> Price = new ArrayList<>();
					int a= Integer.parseInt(data.get("Value"));
					Price = getPriceValueFromPricingTab(data.get("idValue"), data.get("expValue"),a);// ZPLS--0
					
                    System.out.println(Price.size());
                    String	Price1="";
                    String	Price2="";
                    String	Price3="";
                    String	Price4="";
                    float price1value = 0, price2value =0,price3value =0,price4value = 0;
                    for(int b =0;b<Price.size();b++){
                    	if(b==0){ //ZPLS--0
                    	 Price1 = Price.get(b);
                    	 String P1 = Price1.replace(",", "");
                         price1value = Float.parseFloat(P1);
                    	}
                    	if(b==1){  // ZP00--1
                            Price2 = Price.get(b);
                            String P2 = Price2.replace(",", "");
                            price2value = Float.parseFloat(P2);
                    	}
                    	if(b==2){   // YP00--2
                            Price3 = Price.get(b);
                            String P3 = Price3.replace(",", "");
                            price3value = Float.parseFloat(P3);
                    	}
                    	if(b==3){    // YDLP--3
                           Price4 = Price.get(b);
                           price4value = Float.parseFloat(Price4);
                    	}
                    }
                    
                   
                    //comparision of retrieved price values
                    VerifyZPLSMinusYDLPShouldbeEqualToZP00(price1value,price4value,price2value);
					VerifyZPLSMinusYDLPShouldbeEqualToYP00(price1value,price4value,price3value);
					 clickDoneButton();
					 ClickOnDisplayMode();
					 ClickOnConverToOrder();
					SwipeUpapplication();
					SwipeUpapplication();
					typePONumber(data.get("PONumber"));
					//SearchButtonPONumber();
					clickOKinPopUp();
					//clickSideBarSmart();
					clickonSaveasOrder();
					clickSaveorderwithoutAttachment();
					CloseButtonofOutputform();
					String QuoteNum1= GetQuoteNumber();
					if(QuoteNum1!=null) {
						reporter.SuccessReport("QuoteNumber:", "Quote NUmber is displayed", "");
					}
					else {
						reporter.failureReport("QuoteNUmber:", "Quote Number is not displayed", "", driver);
					}
					clickSideBarSmart();
					clickClosthedocument(QuoteNum1);
					clickYesButtontocloseDocument();				
					System.out.println("Test completed");
				} catch (Exception e) {
					ReportStatus.blnStatus = false;
					//gErrorMessage = e.getMessage();
					gTestStatus = false;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			ReportStatus.blnStatus = false;
			//gErrorMessage = e.toString();
			gTestStatus = false;
			//ReportStatus.fnUpdateResultStatus("CQT38_IPSContractPricingCiscoInsightListMinusContract", "CQT_38", ReportStatus.strMethodName, 1, browser);
			throw new RuntimeException(e);
		}
        finally {
			ReportControl.fnEnableJoin();
			ReportStatus.fnUpdateResultStatus("CQT38_IPSContractPricingCiscoInsightListMinusContract", "CQT_38", ReportStatus.strMethodName, counter, browser);
			fnCloseTest();
			ReportControl.fnNextTestJoin(nextTestJoin);
		}
	}


}
