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

public class CQT28_IPSContractpricing_ReferenceYIPRContract extends HomeLib {

	loginLib loginlib = new loginLib();
	// #############################################################################################################
	// # Name of the Test : CQT28_IPSContractpricing_ReferenceYIPRContract
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
	public void TC_CQT28(int StartRow, String EndRow, boolean nextTestJoin) throws Throwable {
		int counter = 0;
		try {
			int intStartRow = StartRow;
			int intEndRow = ReportControl.fnGetEndRowCunt(EndRow, "CQT28_IPSContractpricing_ReferenceYIPRContract",TestData_Smart, "Create_Quote");
			for (int intCounter = intStartRow; intCounter <= intEndRow; intCounter++) {
				try {
					counter = intCounter;
					fnOpenTest();
					ReportStatus.fnDefaultReportStatus();
					ReportControl.intRowCount = intCounter;
					Hashtable<String, String> data = TestUtil.getDataByRowNo("CQT28_IPSContractpricing_ReferenceYIPRContract", TestData_Smart, "Create_Quote", intCounter);
					TestEngineWeb.reporter.initTestCaseDescription("IPSContractpricing_ReferenceYIPRContract");

					navigateToApplication("SMART");
					loginlib.loginIntoSmartApplication(data.get("UserName"), data.get("Password"));
					clickOnCreateQuoteLink();
					enterSoldTo(data.get("SoldToValue"));
					// 10529929
					AddMaterialOnLineItem(data.get("MaterialID"));//PWR-400W-AC
					//AddMaterialOnLineItem(data.get("MaterialID2"));//WS-C2960X-24PS-L
					//AddMaterialOnLineItem(data.get("MaterialID3"));//CON-NSNT-C881WACC
					clickOnCOntractIDinLineItemsList();
					selectCOntractID(data.get("contactid"), data.get("contactTabName"));
					copyAllContractstoAllLines();
					clickDoneButton();
					clickUpdateCosting();
					
					clickSideBarSmart();
					clickonSaveasQuote();
					
					SelectAdherencetoflooroption("Client Satisfaction", "UFT Test");
					
					
					String QuoteNum1 = GetQuoteNumber();
					if(QuoteNum1!=null) {
						reporter.SuccessReport("QuoteNumber:", "Quotenumber is displayed", "");
					}
					else {
						reporter.failureReport("QuoteNumber:", "Quotenumber is not displayed", "",driver);
					}
					clickOnCOntractIDinLineItemsList();
					selectCOntractSubTabName(data.get("contractTab1"));// Pricing

					// Get data from the pricing tab
					List<String> Price = new ArrayList<>();
					int a= Integer.parseInt(data.get("Value"));

					Price = getPriceValueFromPricingTab(data.get("idValue"), data.get("expValue"),a);// ZPFX--0
					 System.out.println(Price.size());
	                    String	Price1="";
	                    String	Price2="";
	                    String	Price3="";
	                    String	Price4="";
	                    String rateCaptured="";
	                    float price1value = 0, price2value =0,price3value =0,price4value = 0;
	                    for(int b =0;b<Price.size();b++){
	                    	if(b==0){ //ZPFX--0
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
	                    	if(b==3){    // YIPR--3
	                           Price4 = Price.get(b);
	                           String P4 = Price4.replace(",", "");
	                           price4value = Float.parseFloat(P4);
	                           rateCaptured= getRateValueinPricingTable("YIPR");
	                    	}
	                    	
	                    }
                   

				float rateCaptured1 = Float.parseFloat(rateCaptured);
                    float Rate= Float.valueOf(rateCaptured);
                    
					// Need to compare pricing
                    verifyZPOOequalstheZPFXandYIPRPercent(price2value, price1value, rateCaptured1);

					if (price3value==price2value) { // compare ZPOO equals to YPOO
				reporter.SuccessReport("Verify that YP00 equals to ZP00 value.","ZP00:"+Price2+"YP00: "+Price3, "");
				} else {
				reporter.failureReport("Verify that YP00 equals to ZP00 value.","ZP00:"+Price2+"YP00: "+Price3 + "both are not same","", driver);
				}
					selectCOntractSubTabName(data.get("contractTab2"));// Contracts
					VerifyContractPriceShouldbeEqualToYPOO(price3value, data.get("contactid"));	
					clickDoneButton();
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
			ReportStatus.fnUpdateResultStatus("CQT28_IPSContractpricing_ReferenceYIPRContract", "TC_028",
					ReportStatus.strMethodName, 1, browser);
			throw new RuntimeException(e);
		} finally {
			ReportControl.fnEnableJoin();
			ReportStatus.fnUpdateResultStatus("CQT28_IPSContractpricing_ReferenceYIPRContract", "TC_028",
					ReportStatus.strMethodName, counter, browser);
			fnCloseTest();
			ReportControl.fnNextTestJoin(nextTestJoin);
		}
	}

}
