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

public class CQT29_IPSContractpricing_USCPriceMatchContract extends HomeLib {

	loginLib loginlib = new loginLib();
	// #############################################################################################################
	// # Name of the Test : CQT29_IPSContractpricing_USCPriceMatchContracta
	// # Migration Author : Cigniti Technologies
	// #
	// # Date of Migration : Nov 2019
	// # DESCRIPTION :
	// functionality in the products display page.
	// # Parameters : StartRow ,EndRow , nextTestJoin
	// #
	// ###############################################################################################################

	@Parameters({ "StartRow", "EndRow", "nextTestJoin" })
	@Test
	public void TC_CQT29(int StartRow, String EndRow, boolean nextTestJoin) throws Throwable {
		int counter = 0;
		try {
			int intStartRow = StartRow;
			int intEndRow = ReportControl.fnGetEndRowCunt(EndRow, "CQT29_IPSContractpricing_USCPriceMatchContract",TestData_Smart, "Create_Quote");
			for (int intCounter = intStartRow; intCounter <= intEndRow; intCounter++) {
				try {
					counter = intCounter;
					fnOpenTest();
					ReportStatus.fnDefaultReportStatus();
					ReportControl.intRowCount = intCounter;
					Hashtable<String, String> data = TestUtil.getDataByRowNo("CQT29_IPSContractpricing_USCPriceMatchContract", TestData_Smart, "Create_Quote", intCounter);
					TestEngineWeb.reporter.initTestCaseDescription("IPSContractpricing_USCPriceMatchContract");

					
					navigateToApplication("SMART");
					loginlib.loginIntoSmartApplication(data.get("UserName"), data.get("Password"));
					clickOnCreateQuoteLink();
					enterSoldTo(data.get("SoldToValue"));
					// 10529929
					AddMaterialOnLineItem(data.get("MaterialID1"));// CON-ISV1-VSSTD1A
					AddMaterialOnLineItem(data.get("MaterialID2"));// PWR-400W-AC
					AddMaterialOnLineItem(data.get("MaterialID3"));// WS-C2960X-24PS-L
					clickOnCOntractIDinLineItemsList();
					selectCOntractID(data.get("contactid"), data.get("contactTabName"));
					copyAllContractstoAllLines();
					clickDoneButton();
					clickUpdateCosting();
					driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
					clickOnCOntractIDinLineItemsList();
					selectCOntractSubTabName(data.get("contactTabName"));// Contracts
					// Need to verify reporting field6
					String strRepfld6contract= VerifyReportingField6(data.get("contactid"));
					float strRepfld6 = Float.valueOf(strRepfld6contract);
					String contractid2SellPrice = VerifyReportingField6SellPriceValue();
					float field6Sellprice = Float.valueOf(contractid2SellPrice);
					float sellprice1 =getSellPriceFromContract(data.get("contactid"));
					//float sellprice1 = Float.valueOf(contract1);
					selectCOntractSubTabName(data.get("contactTabName1"));// Pricing
					// Get data from the pricing tab
					List<String> Price = new ArrayList<>();
					int a = Integer.parseInt(data.get("Value"));
					Price = getPriceValueFromPricingTab(data.get("idValue"), data.get("expValue"), a);// ZP00--0
					System.out.println(Price.size());
					String Price1 = "";
					float price1value = 0;
					for(int b =0;b<Price.size();b++){
                    	if(b==0){ //ZP00--0
                    	 Price1 = Price.get(b);
                    	 String P1 = Price1.replace(",", "");
                         price1value = Float.parseFloat(P1);
                         
                    	}
					}
					clickDoneButton(); 
					if(price1value==field6Sellprice ){  //Verify contract price equals to ZP00
						reporter.SuccessReport("verify the Contract "+strRepfld6+" price equals to ZP00 value.", "Contract price equals to ZP00",
								"Contract Sell Price:"+field6Sellprice+"- ZP00:"+Price1);
					} else {
						reporter.failureReport("verify the Contract "+strRepfld6+" price equals to ZP00 value.","Contract price not equals to ZP00"
								,"Contract Sell Price:"+field6Sellprice+"- ZP00:"+Price1, driver);
					
					}
					clickSideBarSmart();
					clickonSaveasQuote();
				
				SelectAdherencetoflooroption("Client Satisfaction", "UFT Test");
				
				
					String QuoteNum1= GetQuoteNumber();
					clickOnCOntractIDinLineItemsList();
					selectCOntractSubTabName(data.get("contactTabName1"));// Pricing
					// Get data from the pricing tab
					List<String> Price12 = new ArrayList<>();
					int b = Integer.parseInt(data.get("Value"));
					Price12 = getPriceValueFromPricingTab(data.get("idValue"), data.get("expValue"), a);// ZP00--0
					System.out.println(Price12.size());
					String Price2 = "";
					float price2value=0;
					for(int b1 =0;b1<Price.size();b1++){
                    	if(b1==0){ //zp00--0
                    	 Price2 = Price12.get(b1);
                    	 String P2 = Price2.replace(",", "");
                         price2value = Float.parseFloat(P2);
                        
                    	}
					}
					clickDoneButton(); 
					if(price1value==price2value){
						reporter.SuccessReport("verify the  ZP00 value for line item 10", "Exists as Expected",
								"ZP00:"+Price1);
					} else {
						reporter.failureReport("verify the  ZP00 value for line item 10","Does not exists"
								,"ZP00:"+Price2, driver);
					}
					
					clickSideBarSmart();
					
					clickClosthedocument(QuoteNum1);
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
			ReportStatus.fnUpdateResultStatus("CQT29_IPSContractpricing_USCPriceMatchContracta", "TC_029",
					ReportStatus.strMethodName, 1, browser);
			throw new RuntimeException(e);
		} finally {
			ReportControl.fnEnableJoin();
			ReportStatus.fnUpdateResultStatus("CQT29_IPSContractpricing_USCPriceMatchContracta", "TC_029",
					ReportStatus.strMethodName, counter, browser);
			//fnCloseTest();
			ReportControl.fnNextTestJoin(nextTestJoin);
		}
	}

}
