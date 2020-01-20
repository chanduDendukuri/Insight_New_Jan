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

public class CQT53_IPSOpenMarketExclusion  extends HomeLib {

	loginLib loginlib = new loginLib();
	// #############################################################################################################
	// # Name of the Test : CQT53_IPSOpenMarketExclusion
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
	public void TC_CQT53(int StartRow, String EndRow, boolean nextTestJoin) throws Throwable {
		int counter = 0;
		try {
				int intStartRow = StartRow;
				int intEndRow = ReportControl.fnGetEndRowCunt(EndRow, "CQT53_IPSOpenMarketExclusion",TestData_Smart,"Create_Quote");
				for (int intCounter = intStartRow; intCounter <= intEndRow; intCounter++) {

		try {
				counter = intCounter;
				fnOpenTest();
				ReportStatus.fnDefaultReportStatus();
				ReportControl.intRowCount = intCounter;
				Hashtable<String, String> data = TestUtil.getDataByRowNo("CQT53_IPSOpenMarketExclusion", TestData_Smart, "Create_Quote", intCounter);
				TestEngineWeb.reporter.initTestCaseDescription("IPSOpenMarketExclusion");
					navigateToApplication("SMART");
					loginlib.loginIntoSmartApplication(data.get("UserName"), data.get("Password"));
					clickOnCreateQuoteLink();
					enterSoldTo(data.get("SoldToValue"));
					// 0010600194
					AddMaterialOnLineItem(data.get("MaterialID1")); //F2CU040BTBLK
					AddMaterialOnLineItem(data.get("MaterialID2")); // F3W43AA
					AddMaterialOnLineItem(data.get("MaterialID3"));	// D2W67A8#ABA
					AddMaterialOnLineItem(data.get("MaterialID4"));	//  QY776AA
					clickOnCOntractIDinLineItemsList();					
					selectCOntractID(data.get("contactid"),data.get("Tab1"));//contracts					
					// Verify Enrollment Code//
					
					clickonRightArrowforLineItem();
					selectCOntractID(data.get("contactid"),data.get("Tab1"));					
					// Verify Enrollment Code//
					
					clickonRightArrowforLineItem();
					selectCOntractID(data.get("contactid"),data.get("Tab1"));
					// Verify Enrollment Code//
					clickonRightArrowforLineItem();
					selectCOntractID(data.get("contactid"),data.get("Tab1"));
					clickDoneButton();					 
					clickUpdateCosting();
					
					clickSideBarSmart();
					clickonSaveasQuote();
					enterCancelButtonInPoupHdr();
					String QuoteNum= GetQuoteNumber();	
					if(QuoteNum!=null) {
						reporter.SuccessReport("QuoteNumber:", "Quotenumber is displayed", "");
					}
					else {
						reporter.failureReport("QuoteNumber:", "Quotenumber is not displayed", "",driver);
					}
					ClickOnDisplayMode();
					clickonConXSystem("000040");
					copyAllContractstoAllLines();
					clickonRightArrowforLineItem();//Line2
					clickonRightArrowforLineItem();//Line3
					clickDoneButton();
					clickUpdateCosting();
					clickonConXSystem(data.get("LineItem1"));
					selectCOntractSubTabName(data.get("contactTabName1"));// Pricing		
					// Get data from the pricing tab
					List<String> Price = new ArrayList<>();
					int a= Integer.parseInt(data.get("Value"));
					Price = getPriceValueFromPricingTab(data.get("idValue"), data.get("expValue"),a);// Z0RC--0
                    System.out.println(Price.size());
                    String	Price1="";
                    String	Price2="";
                    String	Price3="";
                    String	Price4="";
                    
                    float price1value = 0, price2value =0,price3value =0,price4value = 0;
                    for(int b1 =0;b1<Price.size();b1++){
                    	if(b1==0){ //YMSM--0
                    		Price1 = Price.get(b1);
                    	 String P4 = Price1.replace(",", "");
                    	 price1value = Float.parseFloat(P4);
                         
                    	}
                    	if(b1==1){  // YP00--1
                    		Price2 = Price.get(b1);
                            String P5 = Price2.replace(",", "");
                            price2value = Float.parseFloat(P5);
                    	}
                    	if(b1==2){   // ZP00--2
                    		Price3 = Price.get(b1);
                            String P6 = Price3.replace(",", "");
                            price3value = Float.parseFloat(P6);
                    	}
                    	if(b1==3){   // ZORC--2
                    		Price4 = Price.get(b1);
                            String P7 = Price4.replace(",", "");
                            price4value = Float.parseFloat(P7);
                    	}
                    	
                    	
                    }
					selectCOntractID(data.get("contactid"),data.get("Tab1"));
					clickDoneButton();
					//Need to compare pricing
					VerifyZ0RCPlusYMSMequalstheYP00andZP00( price4value, price1value, price2value );
					VerifyZPOOShouldbeEqualToYPOO(price3value,price2value);
					VerifyContractPriceShouldbeEqualToYPOO(price2value,data.get("contractid"));
					
					clickSideBarSmart();
					clickClosthedocument(QuoteNum);
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
			ReportStatus.fnUpdateResultStatus("CQT53_IPSOpenMarketExclusion", "TC_053",
					ReportStatus.strMethodName, 1, browser);
			throw new RuntimeException(e);
		} finally {
			ReportControl.fnEnableJoin();
			ReportStatus.fnUpdateResultStatus("CQT53_IPSOpenMarketExclusion", "TC_053",
					ReportStatus.strMethodName, counter, browser);
			fnCloseTest();
			ReportControl.fnNextTestJoin(nextTestJoin);
		}
	}
}
