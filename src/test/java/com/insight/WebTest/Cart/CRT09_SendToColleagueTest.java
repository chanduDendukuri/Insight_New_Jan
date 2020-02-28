package com.insight.WebTest.Cart;

import java.util.Hashtable;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.insight.Lib.CMTLib;
import com.insight.Lib.CanadaLib;
import com.insight.Lib.CartLib;
import com.insight.Lib.ChinaLib;
import com.insight.Lib.CommonLib;
import com.insight.Lib.OrderLib;
import com.insight.Lib.ProductDetailLib;
import com.insight.Lib.ProductDisplayInfoLib;
import com.insight.Lib.SearchLib;
import com.insight.Lib.ShipBillPayLib;
import com.insight.accelerators.ReportControl;
import com.insight.accelerators.TestEngineWeb;
import com.insight.googledrive.ReportStatus;
import com.insight.utilities.TestUtil;

public class CRT09_SendToColleagueTest extends CartLib {
	CommonLib commonLib = new CommonLib();
	CartLib cartLib = new CartLib();
	SearchLib searchLib = new SearchLib();
	CanadaLib canadaLib = new CanadaLib();
	ProductDisplayInfoLib prodInfoLib = new ProductDisplayInfoLib();
	SearchLib search = new SearchLib();
	CMTLib cmtLib = new CMTLib();
	// #############################################################################################################
	// # Name of the Test : CRT09_SendToColleague
	// # Migration Author : Cigniti Technologies
	// #
	// # Date of Migration : August 2019
	// # DESCRIPTION : This method is to verify send to collegue order utility
	// in shopping cart page.
	// # Parameters : StartRow ,EndRow , nextTestJoin
	// #
	// ###############################################################################################################
	@Parameters({ "StartRow", "EndRow", "nextTestJoin" })
	@Test(enabled = true)
	public void Tc_CRT09(int StartRow, String EndRow, boolean nextTestJoin) throws Throwable {

		int counter = 0;
		try {
			int intStartRow = StartRow;
			int intEndRow = ReportControl.fnGetEndRowCunt(EndRow, "CRT09_SendToColleague", TestDataInsight, "Web_Cart");
			for (int intCounter = intStartRow; intCounter <= intEndRow; intCounter++) {
				try {
					counter = intCounter;
					fnOpenTest();
					ReportStatus.fnDefaultReportStatus();
					ReportControl.intRowCount = intCounter;
					Hashtable<String, String> data = TestUtil.getDataByRowNo("CRT09_SendToColleague", TestDataInsight,
							"Web_Cart", intCounter);
					TestEngineWeb.reporter.initTestCaseDescription("SendToColleague");
					cmtLib.loginToCMT(data.get("header"));
					cmtLib.searchForWebGroup(data.get("WebGrp"));
					cmtLib.clickOnTheWebGroup(data.get("WebGrp_Name"));
					cmtLib.hoverOnManageWebGroupsAndSelectOptions(data.get("Manage_Web_Grp_Options"));
					cmtLib.searchForaUserAndSelect(data.get("LnameEmailUname"), data.get("ContactName"));
					String mainWindow = parentWindow();
					cmtLib.clickOnloginAs();
					switchToWindow(mainWindow);	
					cmtLib.loginVerification(data.get("ContactName"));
					commonLib.searchProduct(data.get("SearchItem1"));
					prodInfoLib.verifyTheManufacturerNumberInProductDetailsPage(data.get("SearchItem1"));
					commonLib.addToCartAndVerify();
					canadaLib.continueToCheckout();
					canadaLib.verifyPlaceCartLabel();
					prodInfoLib.verifyCartPageAndPartDetailsForRecentlyItemDynamically(data.get("SearchItem1"));

					commonLib.searchProduct(data.get("SearchItem2"));
					prodInfoLib.verifyTheManufacturerNumberInProductDetailsPage(data.get("SearchItem2"));
					commonLib.addToCartAndVerify();
					canadaLib.continueToCheckout();
					canadaLib.verifyPlaceCartLabel();
					prodInfoLib.verifyCartPageAndPartDetailsForRecentlyItemDynamically(data.get("SearchItem2"));

					commonLib.searchProduct(data.get("SearchItem3"));
					prodInfoLib.verifyTheManufacturerNumberInProductDetailsPage(data.get("SearchItem3"));
					commonLib.addToCartAndVerify();
					canadaLib.continueToCheckout();
					canadaLib.verifyPlaceCartLabel();
					prodInfoLib.verifyCartPageAndPartDetailsForRecentlyItemDynamically(data.get("SearchItem3"));

					cartLib.clickAndVerifySendToAColleagueErrorMSG(data.get("OrderUtilities"));
					cartLib.verifySendToAColleague(data.get("OrderUtilities"), data.get("YourName"),
							data.get("YourEmail1"), data.get("YourEmail1"), data.get("YourComments"));
					verifyErrorMessagesInSendToAColleaguePopUpForEmail();
					cartLib.verifySendToAColleague(data.get("OrderUtilities"), data.get("YourName"),
							data.get("YourEmail"), data.get("RecipientEmail"), data.get("YourComments"));
					verifySendToAColleagueSucessMessage();
					commonLib.clickLogOutLink(data.get("Logout_Header"));
					System.out.println("Test completed");

				} catch (Exception e) {
					ReportStatus.blnStatus = false;
					// gErrorMessage = e.getMessage();
					gTestStatus = false;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			ReportStatus.blnStatus = false;
			// gErrorMessage = e.toString();
			gTestStatus = false;
			ReportStatus.fnUpdateResultStatus("SendToColleague", "Tc_CRT09", ReportStatus.strMethodName, 1, browser);
			throw new RuntimeException(e);
		} finally {
			ReportControl.fnEnableJoin();
			ReportStatus.fnUpdateResultStatus("SendToColleague", "Tc_CRT09", ReportStatus.strMethodName, counter,
					browser);
			fnCloseTest();
			ReportControl.fnNextTestJoin(nextTestJoin);
		}
	}
}