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
					
					clickDoneButton();					 
					clickUpdateCosting();
					
					clickSideBarSmart();
					clickonSaveasQuote();
					ValidateError();
					
					String QuoteNum= GetQuoteNumber();	
					if(QuoteNum!=null) {
						reporter.SuccessReport("QuoteNumber:", "Quotenumber is displayed", "");
					}
					else {
						reporter.failureReport("QuoteNumber:", "Quotenumber is not displayed", "",driver);
					}
					
					clickonConXSystem("000040");
					selectCOntractID(data.get("contactid"),data.get("Tab1"));
					clickDoneButton();
					clickUpdateCosting();
					clickonSaveasQuote();
					enterCancelButtonInPoupHdr();
					String QuoteNum1= GetQuoteNumber();	
					if(QuoteNum1!=null) {
						reporter.SuccessReport("QuoteNumber:", "Quotenumber is displayed", "");
					}
					else {
						reporter.failureReport("QuoteNumber:", "Quotenumber is not displayed", "",driver);
					}
					clickClosthedocument(QuoteNum1);
					
					
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
