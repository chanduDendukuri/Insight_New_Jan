package com.insight.WebTest.ShipBillPay;

import java.util.Hashtable;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.insight.Lib.CMTLib;
import com.insight.Lib.CanadaLib;
import com.insight.Lib.CartLib;
import com.insight.Lib.CommonLib;
import com.insight.Lib.OrderLib;
import com.insight.Lib.ShipBillPayLib;
import com.insight.accelerators.ReportControl;
import com.insight.accelerators.TestEngineWeb;
import com.insight.googledrive.ReportStatus;
import com.insight.utilities.TestUtil;

public class SBP09_SharedUserWGSwitchTest extends ShipBillPayLib{
	// #############################################################################################################
		// # Name of the Test : SBP09_SharedUserWGSwitch
		// # Migration Author : Cigniti Technologies
		// #
		// # Date of Migration : August 2019
		// # DESCRIPTION : This method is to perform Basic Cart operations.
		// # Parameters : StartRow ,EndRow , nextTestJoin
		// #
		// ###############################################################################################################
		@Parameters({ "StartRow", "EndRow", "nextTestJoin" })
		@Test
		public void Tc_SBP09(int StartRow, String EndRow, boolean nextTestJoin) throws Throwable {
			int counter=0;
			try {
			int intStartRow = StartRow;
			int intEndRow = ReportControl.fnGetEndRowCunt(EndRow, "SBP09_SharedUserWGSwitch", TestDataInsight,
					"Ship_Bill_Pay");
			for (int intCounter = intStartRow; intCounter <= intEndRow; intCounter++) {
				try {
				fnOpenTest();
				ReportStatus.fnDefaultReportStatus();
				ReportControl.intRowCount = intCounter;
				Hashtable<String, String> data = TestUtil.getDataByRowNo("SBP09_SharedUserWGSwitch", TestDataInsight,
						"Ship_Bill_Pay", intCounter);
				TestEngineWeb.reporter
						.initTestCaseDescription("SharedUserWGSwitch");
				reporter.SuccessReport("Iteration Number : ",
						"**************Iteration Number::  " + intCounter + " For:: " + data.get("LoginName") + " ::and:: "
								+ data.get("Password") + " To Validate::" + data.get("errorMessage") + "  **************",
						"");
				CommonLib commonLib = new CommonLib();
				CanadaLib canadaLib = new CanadaLib();
				CMTLib cmtLib = new CMTLib();
				CartLib cartLib = new CartLib();
				OrderLib orderLib = new OrderLib();
				ShipBillPayLib shipbLib = new ShipBillPayLib();

				cmtLib.loginToCMTSearchWebGrpAndUser(data.get("Header"), data.get("WebGrp"), data.get("LnameEmailUname"),
						data.get("ContactName"));
				cmtLib.clickOnloginAs();
				switchToChildWindow();
				Thread.sleep(3000);
				cmtLib.handleWelcomeToInsightBetaPopUp();
				shipbLib.verifyWebGroupIsUS();
				Thread.sleep(3000);
				shipbLib.clickHere();
				Thread.sleep(3000);
				cmtLib.handleWelcomeToInsightBetaPopUp();
				shipbLib.verifyWEbsiteIsCannada();
				Thread.sleep(3000);
				commonLib.searchProduct(data.get("Search_Item"));
				commonLib.addFirstDisplyedItemToCartAndVerify();
				 String partNumber1=cartLib.getPartNumber();
				 Thread.sleep(3000);
				 canadaLib.continueToCheckout();
				 Thread.sleep(3000);
				 cartLib.verifyItemInCart(partNumber1);
				shipbLib.verifyPriceIsCAD(data.get("CANDAIAN_DOLLAR"));
				shipbLib.getSummaryAmountsInCart(data.get("SubTotal"), data.get("Total"));
				orderLib.proceedToCheckout();
				Thread.sleep(3000);
				shipbLib.addAddtionalInfo(data.get("Name"), data.get("Phone"), data.get("Email"));
				cartLib.clickOnContinueButtonInAddInformtion();
				commonLib.clickLogOutLink(data.get("Logout_Header"));
				

				} catch (Exception e) {
					ReportStatus.blnStatus = false;
					//gErrorMessage = e.getMessage();
					System.out.println(e.getMessage());
					gTestStatus = false;
				}
				
			}
		} catch (Exception e) {
			e.printStackTrace();
			ReportStatus.blnStatus = false;
			//gErrorMessage = e.getMessage();
			gTestStatus = false;
			ReportStatus.fnUpdateResultStatus("SharedUserWGSwitch", "SBP09", ReportStatus.strMethodName, 1, browser);
			throw new RuntimeException(e);
		}

			finally {
	        	ReportControl.fnEnableJoin();
				ReportStatus.fnUpdateResultStatus("SharedUserWGSwitch", "SBP09", ReportStatus.strMethodName, counter, browser);
				fnCloseTest();
				ReportControl.fnNextTestJoin(nextTestJoin);
			}
	}

}