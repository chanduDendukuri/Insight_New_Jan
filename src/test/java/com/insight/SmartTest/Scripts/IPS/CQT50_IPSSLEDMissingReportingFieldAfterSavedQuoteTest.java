package com.insight.SmartTest.Scripts.IPS;

import java.util.Hashtable;
import java.util.concurrent.TimeUnit;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.insight.SmartTest.Lib.HomeLib;
import com.insight.SmartTest.Lib.loginLib;
import com.insight.accelerators.ReportControl;
import com.insight.accelerators.TestEngineWeb;
import com.insight.googledrive.ReportStatus;
import com.insight.utilities.TestUtil;

public class CQT50_IPSSLEDMissingReportingFieldAfterSavedQuoteTest extends HomeLib{
	loginLib loginlib=new loginLib();

	// #############################################################################################################
		// # Name of the Test : CQT50_IPSSLEDMissingReportingFieldAfterSavedQuote
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
		public void TC_CQT50(int StartRow, String EndRow, boolean nextTestJoin) throws Throwable {
			int counter = 0;
			try {
				int intStartRow = StartRow;
				int intEndRow = ReportControl.fnGetEndRowCunt(EndRow, "CQT50_IPSSLEDMissingReportingFieldAfterSavedQuote", TestData_Smart, "Create_Quote");
				for (int intCounter = intStartRow; intCounter <= intEndRow; intCounter++) {
					try {
					counter = intCounter;
					fnOpenTest();
					ReportStatus.fnDefaultReportStatus();
					ReportControl.intRowCount = intCounter;
					Hashtable<String, String> data = TestUtil.getDataByRowNo(
							"CQT50_IPSSLEDMissingReportingFieldAfterSavedQuote", TestData_Smart, "Create_Quote",
							intCounter);
					TestEngineWeb.reporter.initTestCaseDescription("IPSSLEDMissingReportingFieldAfterSavedQuote");
					MethodfromSmartLoginTillSalesOrgselection(data.get("UserName"), data.get("Password"),
							data.get("SoldToAcct"), data.get("SalesOrg"));
					
					Addmaterail(data.get("Material1"));// RBC7
					
					Addmaterail(data.get("Material2"));// 4XA0E97775
					
					clickonConXSystem(data.get("ItemNum"));// 000010
					clickOnContractId(data.get("contactid"));// 1510000830
					clickOnCopyContarctToallLineItems();
					clickYesButtontocloseDocument();
					clickDoneButton();
					clickUpdateCosting();
					
					//clickSideBarSmart();
					
					clickonSaveasQuote();
					enterCancelButtonInPoupHdr();
					ClickOnDisplayMode();
					
					ClickOnConverToOrder();
					
					clickonConXSystem(data.get("ItemNum"));// 000010
					enterTestinReportingField4(data.get("Reprotingfield4text"));
					enterTestinReportingField0(data.get("Reprotingfield0text"));
					enterTestinReportingField3(data.get("Reprotingfield3text"));
					clickonCopyreportingfieldstoallthelines();
					clickYesButtontocloseDocument();
					clickOnCopyContarctToallLineItems();
					clickYesButtontocloseDocument();
					clickDoneButton();
					clickUpdateCosting();
					selectPaymentTerms(data.get("PaymentTerms"));// 2001 Net 30
						SwipeUpapplication();											// days
					typePONumber(data.get("PONumber"));// 12345
					okPopUp();
					clickonSaveasOrder();
					clickSaveorderwithoutAttachment();
					
					getOrderNum();
					clickCancel();
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
			//ReportStatus.fnUpdateResultStatus("IPSSLEDMissingReportingFieldAfterSavedQuote", "CQT_50", ReportStatus.strMethodName, 1, browser);
			throw new RuntimeException(e);
		}
        finally {
			ReportControl.fnEnableJoin();
			ReportStatus.fnUpdateResultStatus("IPSSLEDMissingReportingFieldAfterSavedQuote", "CQT_50", ReportStatus.strMethodName, counter, browser);
			fnCloseTest();
			ReportControl.fnNextTestJoin(nextTestJoin);
		}
	}


}

