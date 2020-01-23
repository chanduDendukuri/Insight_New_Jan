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

public class CQT57_IPSQuoteWithYCGETest extends HomeLib{
	
	loginLib loginlib=new loginLib();

	// #############################################################################################################
		// # Name of the Test : CQT57_IPSQuoteWithYCGE
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
	public void TC_CQT57(int StartRow,String EndRow, boolean nextTestJoin) throws Throwable {
		int counter = 0;
		try {
			int intStartRow = StartRow;
			int intEndRow = ReportControl.fnGetEndRowCunt(EndRow, "CQT57_IPSQuoteWithYCGE", TestData_Smart, "Create_Quote");
			for (int intCounter = intStartRow; intCounter <= intEndRow; intCounter++) {
				try {
					counter = intCounter;
					fnOpenTest();
					ReportStatus.fnDefaultReportStatus();
					ReportControl.intRowCount = intCounter;
					Hashtable<String, String> data = TestUtil.getDataByRowNo("CQT57_IPSQuoteWithYCGE", TestData_Smart, "Create_Quote", intCounter);
					TestEngineWeb.reporter.initTestCaseDescription("IPSQuoteWithYCGE");
					MethodfromSmartLoginTillSalesOrgselection(data.get("UserName"), data.get("Password"),
							data.get("SoldToAcct"), data.get("SalesOrg"));
					Addmaterail(data.get("Material1"));
					Addmaterail(data.get("Material2"));
					clickonConXSystem(data.get("ItemNum1"));// 000010
					clickOnContractId(data.get("contactid"));
					clickOnCopyContarctToallLineItems();
					clickYesButtontocloseDocument();
					clickDoneButton();
					clickUpdateCosting();
					
					
					String RepCost1=getRepCostofLineItem(data.get("ItemNum1"));
					String RepCost2=getRepCostofLineItem(data.get("ItemNum2"));
					clickonConXSystem(data.get("ItemNum1"));// 000010
					clickOnTabsInLineItemDetailsPopUp(data.get("Tab"));// Pricing
					// Get data from the pricing tab
					List<String> Price = new ArrayList<>();
					int a= Integer.parseInt(data.get("Value"));
					Price = getPriceValueFromPricingTab(data.get("idValue"), data.get("expValue"),a);// 
                    System.out.println(Price.size());
                  
                    String	Price1="";
                    String	Price2="";
                  
                    
                    float price1value = 0, price2value =0;
                    for(int b1 =0;b1<Price.size();b1++){
                    	if(b1==0){ //YCGE--0
                    		Price1 = Price.get(b1);
                    	 String P4 = Price1.replace(",", "");
                    	 price1value = Float.parseFloat(P4);
                         
                    	}
                    	if(b1==1){  // ZOMV--1
                    		Price2 = Price.get(b1);
                            String P5 = Price2.replace(",", "");
                            price2value = Float.parseFloat(P5);
                    	}
                    	
                    	
                    }
					clickonNextLineItemArrowsymbolinPopUp();
					clickOnTabsInLineItemDetailsPopUp(data.get("Tab"));// Pricing
					// Get data from the pricing tab
					List<String> price11 = new ArrayList<>();
					int b= Integer.parseInt(data.get("Value"));
					price11 = getPriceValueFromPricingTab(data.get("idValue"), data.get("expValue"),a);// ZPLS--0
                    System.out.println(price11.size());
                    String	price1="";
                    String	price2="";
                    float price3value =0,price4value=0;
                    for(int i =0;i<price11.size();i++){
                    	if(i==0){ //YCGE1--0
                     	price1 = price11.get(i);
                     	String P4 = price1.replace(",", "");
                   	 price3value = Float.parseFloat(P4);
                    	}
                    	if(i==1){  // ZMOV1--1
                    		price2 = price11.get(i);
                         	String P4 = price2.replace(",", "");
                       	 price4value = Float.parseFloat(P4);
                    	}
                    }
					clickDoneButton();
					VerifyYCGEPlusZMOVShouldbeEqualToRepCost(price1value, price2value,RepCost1, data.get("ItemNum1"));
					VerifyYCGEPlusZMOVShouldbeEqualToRepCost(price3value, price4value,RepCost2, data.get("ItemNum2"));
					
					
					
					clickonSaveasQuote();
					
					enterCancelButtonInPoupHdr();
					
					String QuoteNum = GetQuoteNumber();
					if(QuoteNum!=null) {
						reporter.SuccessReport("QuoteNumber:", "Quotenumber is displayed", "");
					}
					else {
						reporter.failureReport("QuoteNumber:", "Quotenumber is not displayed", "",driver);
					}
					
					clickSideBarSmart();
					clickClosthedocument(QuoteNum);
					
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
			//ReportStatus.fnUpdateResultStatus("IPSQuoteWithYCGE", "CQT_57", ReportStatus.strMethodName, 1, browser);
			throw new RuntimeException(e);
		}
        finally {
			ReportControl.fnEnableJoin();
			ReportStatus.fnUpdateResultStatus("IPSQuoteWithYCGE", "CQT_57", ReportStatus.strMethodName, counter, browser);
			fnCloseTest();
			ReportControl.fnNextTestJoin(nextTestJoin);
		}
	}


}
