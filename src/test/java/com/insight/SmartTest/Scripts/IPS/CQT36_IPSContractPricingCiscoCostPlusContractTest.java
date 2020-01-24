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

public class CQT36_IPSContractPricingCiscoCostPlusContractTest extends HomeLib{
	loginLib loginlib=new loginLib();

	// #############################################################################################################
		// # Name of the Test : CQT36_IPSContractPricingCiscoCostPlusContract
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
		public void TC_CQT36(int StartRow, String EndRow, boolean nextTestJoin) throws Throwable {
			int counter = 0;
			try {
				int intStartRow = StartRow;
				int intEndRow = ReportControl.fnGetEndRowCunt(EndRow, "CQT36_IPSContractPricingCiscoCostPlusContract", TestData_Smart, "Create_Quote");
				for (int intCounter = intStartRow; intCounter <= intEndRow; intCounter++) {
					try {
					counter = intCounter;
					fnOpenTest();
					ReportStatus.fnDefaultReportStatus();
					ReportControl.intRowCount = intCounter;
					Hashtable<String, String> data = TestUtil.getDataByRowNo("CQT36_IPSContractPricingCiscoCostPlusContract", TestData_Smart, "Create_Quote", intCounter);
					TestEngineWeb.reporter.initTestCaseDescription("IPSQuoteWithYCGE");
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
					clickOnTabsInLineItemDetailsPopUp(data.get("ContractsTab"));//Contracts
					clickOnContractId(data.get("ContractId"));
					clickOnCopyContarctToallLineItems();
					clickYesButtontocloseDocument();
					clickDoneButton();
					clickUpdateCosting();
					
					//clickSideBarSmart();
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
					String Quantity = getQuantityfromItemDetails();
					float qty = Float.parseFloat(Quantity);
					
					List<String> Rate = new ArrayList<>();
					int a = Integer.parseInt(data.get("Value2"));
					Rate = getRateValueFromPricingTab(data.get("idValue"), data.get("ExpRate"), a);
					System.out.println(Rate.size());
					 String	Rate1="";
					 String	Rate2="";
					 String	Rate3="";
	                   
	                    float Rate1value = 0,Rate2value =0,Rate3value=0;
	                    for(int b =0;b<Rate.size();b++){
	                    	if(b==0){ //ZP00--0
	                    		Rate1 = Rate.get(b);
	                    	 String R1 = Rate1.replace(",", "");
	                    	 Rate1value = Float.parseFloat(R1);
	                         
	                    	}
	                    	if(b==1){  // Z0RC--1
	                    		Rate2 = Rate.get(b);
	                            String R2 = Rate2.replace(",", "");
	                            Rate2value = Float.parseFloat(R2);
	                    	}
	                    	if(b==2){   // YP00--2
	                    		Rate3 = Rate.get(b);
	                            String R3 = Rate3.replace(",", "");
	                            Rate3value = Float.parseFloat(R3);
	                    	}
	                    	
	                    }
	                    ClickonRefreshbuttonInItemdetails();
	                    clickDoneButton();
	                    clickonConXSystem(data.get("LineItem1"));//000010
						clickOnTabsInLineItemDetailsPopUp(data.get("Tab3"));//Pricing
					// Get data from the pricing tab
					List<String> Price = new ArrayList<>();
					int a1= Integer.parseInt(data.get("Value1"));
					Price = getPriceValueFromPricingTab(data.get("idValue"), data.get("expValue"),a1);// YMSM--0
                    System.out.println(Price.size());
                    String	Price1="";
                   
                    float price1value = 0;
                    for(int b1 =0;b1<Price.size();b1++){
                    	if(b1==0){ //YMSM--0
                    	 Price1 = Price.get(b1);
                    	 String P1 = Price1.replace(",", "");
                         price1value = Float.parseFloat(P1);
                         
                    	}
                    }	
					// Need to compare pricing
					clickDoneButton();
					float expectedvalue = getexpectedvalue(Rate2value,price1value,qty);
					VerifyZP00equalToExpectedval(Rate1value,expectedvalue);
					VerifyYP00equalToExpectedval(Rate3value,expectedvalue);
					clickSideBarSmart();
					clickClosthedocument(QuoteNum);
					System.out.println("Testcase completed");
					
				} catch (Exception e) {
					ReportStatus.blnStatus = false;
					//gErrorMessage = e.getMessage();
					gTestStatus = false;
				}
			}
		} 
			catch (Exception e) {
			e.printStackTrace();
			ReportStatus.blnStatus = false;
			//gErrorMessage = e.toString();
			gTestStatus = false;
			//ReportStatus.fnUpdateResultStatus("IPSContractPricingCiscoCostPlusContract", "CQT_36", ReportStatus.strMethodName, 1, browser);
			throw new RuntimeException(e);
		}
        finally {
			ReportControl.fnEnableJoin();
			ReportStatus.fnUpdateResultStatus("IPSContractPricingCiscoCostPlusContract", "CQT_36", ReportStatus.strMethodName, counter, browser);
			fnCloseTest();
			ReportControl.fnNextTestJoin(nextTestJoin);
		}
	}
}	

