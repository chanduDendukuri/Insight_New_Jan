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

public class CQT41_IPSContractPricingSoftwareCostplusContractTest extends HomeLib{
	loginLib loginlib=new loginLib();

	// #############################################################################################################
		// # Name of the Test : CQT41_IPSContractPricingSoftwareCostplusContract
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
		public void TC_CQT41(int StartRow, String EndRow, boolean nextTestJoin) throws Throwable {
			int counter = 0;
			try {
				int intStartRow = StartRow;
				int intEndRow = ReportControl.fnGetEndRowCunt(EndRow, "CQT41_IPSContractPricingSoftwareCostplusContract", TestData_Smart, "Create_Quote");
				for (int intCounter = intStartRow; intCounter <= intEndRow; intCounter++) {
					try {
					counter = intCounter;
					fnOpenTest();
					ReportStatus.fnDefaultReportStatus();
					ReportControl.intRowCount = intCounter;
					Hashtable<String, String> data = TestUtil.getDataByRowNo("CQT41_IPSContractPricingSoftwareCostplusContract", TestData_Smart, "Create_Quote", intCounter);
					TestEngineWeb.reporter.initTestCaseDescription("IPSContractPricingSoftwareCostplusContract");
					MethodfromSmartLoginTillSalesOrgselection(data.get("UserName"),data.get("Password"),data.get("SoldToAcct"),data.get("SalesOrg"));
					Addmaterail(data.get("Material1"));
					Addmaterail(data.get("Material2"));
					Addmaterail(data.get("Material3"));
					clickonConXSystem(data.get("ItemNum1"));//000010
					clickOnContractId(data.get("contactid"));
					clickOnCopyContarctToallLineItems();
					clickYesButtontocloseDocument();
					clickonNextLineItemArrowsymbolinPopUp();
					clickOnContractId(data.get("contactid"));
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
					clickonConXSystem(data.get("ItemNum1"));//000010
					clickOnTabsInLineItemDetailsPopUp(data.get("Tab1"));//Pricing
					//Get data from the pricing tab

					// Get data from the pricing tab
					List<String> Price = new ArrayList<>();
					int a= Integer.parseInt(data.get("Value"));
					Price = getPriceValueFromPricingTab(data.get("idValue"), data.get("expValue"),a);
                    System.out.println(Price.size());
                    String	Price1="";
                    String	Price2="";
                    String	Price3="";
                    String	Price4="";
                    
                    float price1value = 0, price2value =0,price3value =0,price4value = 0;
                    for(int b =0;b<Price.size();b++){
                    	if(b==0){ //ZP00
                    	 Price1 = Price.get(b);
                    	 String P1 = Price1.replace(",", "");
                         price1value = Float.parseFloat(P1);
                         
                    	}
                    	if(b==1){  // Z0RC--1
                            Price2 = Price.get(b);
                            String P2 = Price2.replace(",", "");
                            price2value = Float.parseFloat(P2);
                    	}
                    	if(b==2){   // YP00--2
                            Price3 = Price.get(b);
                            String P3 = Price3.replace(",", "");
                            price3value = Float.parseFloat(P3);
                    	}
                    	if(b==3){    // YMSM--3
                           Price4 = Price.get(b);
                           String P4 = Price4.replace(",", "");
                           price4value = Float.parseFloat(P4);
                    	}
                    	
                    }
					clickOnTabsInLineItemDetailsPopUp(data.get("Tab2"));//Contracts
					String ContractId=getContractId(data.get("contactid"));
					getSellPriceFromContract(ContractId);
					VerifyContractPriceShouldbeEqualToYPOO(price3value,ContractId);
					clickDoneButton();
					//Need to compare pricings
					VerifyZ0RCPlusYMSMShouldbeEqualToZP00(price2value,price4value,price1value);
					VerifyZPOOShouldbeEqualToYPOO(price1value,price3value);
					clickSideBarSmart();
					clickClosthedocument(QuoteNum);
					//clickYesButtontocloseDocument();				
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
			//ReportStatus.fnUpdateResultStatus("IPSContractPricingSoftwareCostplusContract", "CQT_41", ReportStatus.strMethodName, 1, browser);
			throw new RuntimeException(e);
		}
        finally {
			ReportControl.fnEnableJoin();
			ReportStatus.fnUpdateResultStatus("IPSContractPricingSoftwareCostplusContract", "CQT_41", ReportStatus.strMethodName, counter, browser);
			fnCloseTest();
			ReportControl.fnNextTestJoin(nextTestJoin);
		}
	}


}