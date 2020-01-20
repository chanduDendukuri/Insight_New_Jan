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

public class CQT44_IPSContractPricingSoftwareMSRPminusContractTest extends HomeLib{
	
	loginLib loginlib=new loginLib();

	// #############################################################################################################
		// # Name of the Test : CQT44_IPSContractPricingSoftwareMSRPminusContract
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
		public void TC_CQT44(int StartRow, String EndRow, boolean nextTestJoin) throws Throwable {
			int counter = 0;
			try {
				int intStartRow = StartRow;
				int intEndRow = ReportControl.fnGetEndRowCunt(EndRow, "CQT44_IPSContractPricingSoftwareMSRPminusContract", TestData_Smart, "Create_Quote");
				for (int intCounter = intStartRow; intCounter <= intEndRow; intCounter++) {
					try {
					counter = intCounter;
					fnOpenTest();
					ReportStatus.fnDefaultReportStatus();
					ReportControl.intRowCount = intCounter;
					Hashtable<String, String> data = TestUtil.getDataByRowNo(
							"CQT44_IPSContractPricingSoftwareMSRPminusContract", TestData_Smart, "Create_Quote",
							intCounter);
					TestEngineWeb.reporter.initTestCaseDescription("IPSContractPricingSoftwareMSRPminusContract");
					MethodfromSmartLoginTillSalesOrgselection(data.get("UserName"), data.get("Password"),
							data.get("SoldToAcct"), data.get("SalesOrg"));
					//Add Materails
					Addmaterail(data.get("Material1"));
					Addmaterail(data.get("Material2"));
					Addmaterail(data.get("Material3"));
					//Click On Contract
					clickonConXSystem(data.get("ItemNum"));// 000010
					clickOnContractId(data.get("contactid"));
					clickOnCopyContarctToallLineItems();
					clickYesButtontocloseDocument();
					clickDoneButton();
					//Update Costing 
					clickUpdateCosting();
					
					clickonSaveasQuote();
					enterCancelButtonInPoupHdr();
					//validate Quote Number
					String QuoteNum = GetQuoteNumber();
					if(QuoteNum!=null) {
						reporter.SuccessReport("QuoteNumber:", "Quotenumber is displayed", "");
					}
					else {
						reporter.failureReport("QuoteNumber:", "Quotenumber is not displayed", "",driver);
					}
					ClickOnDisplayMode();
					clickonConXSystem(data.get("ItemNum"));// 000010
					clickOnTabsInLineItemDetailsPopUp(data.get("Tab"));// Pricing
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
                    	if(b==0){ //ZPML--0
                    	 Price1 = Price.get(b);
                    	 String P1 = Price1.replace(",", "");
                         price1value = Float.parseFloat(P1);
                         
                    	}
                    	if(b==1){  // ZP00--1
                            Price2 = Price.get(b);
                            String P2 = Price2.replace(",", "");
                            price2value = Float.parseFloat(P2);
                    	}
                    	if(b==2){   // ZDML--2
                            Price3 = Price.get(b);
                            String P3 = Price3.replace(",", "");
                            price3value = Float.parseFloat(P3);
                    	}
                    	if(b==3){    // YP00--3
                           Price4 = Price.get(b);
                           String P4 = Price4.replace(",", "");
                           price4value = Float.parseFloat(P4);
                    	}
                    	
                    }
					
					clickOnTabsInLineItemDetailsPopUp(data.get("Tab2"));//Contracts
					String ContractId=getContractId(data.get("contactid"));
					VerifyContractPriceShouldbeEqualToYPOO(ContractId,price4value);
					clickDoneButton();
					VerifyZPMLMinusZDMLShouldbeEqualToYP00(price1value,price3value,price4value);
					VerifyZPMLMinusZDMLShouldbeEqualToZP00(price1value,price3value,price2value);
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
			// ReportStatus.fnUpdateResultStatus("IPSContractPricingSoftwareMSRPminusContract",
			// "CQT_44", ReportStatus.strMethodName, 1, browser);
			throw new RuntimeException(e);
		} finally {
			ReportControl.fnEnableJoin();
			ReportStatus.fnUpdateResultStatus("IPSContractPricingSoftwareMSRPminusContract", "CQT_44",
					ReportStatus.strMethodName, counter, browser);
			fnCloseTest();
			ReportControl.fnNextTestJoin(nextTestJoin);
		}
	}

}
