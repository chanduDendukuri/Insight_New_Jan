package com.insight.WebTest.Cart;

import java.util.Hashtable;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.insight.Lib.CMTLib;
import com.insight.Lib.CanadaLib;
import com.insight.Lib.CartLib;
import com.insight.Lib.CommonLib;
import com.insight.accelerators.ReportControl;
import com.insight.accelerators.TestEngineWeb;
import com.insight.googledrive.ReportStatus;
import com.insight.utilities.TestUtil;

public class CRT01_CartBasicTest extends CartLib {
	CommonLib CommonLib = new CommonLib();
	CMTLib cmtLib = new CMTLib();
	CanadaLib canadaLib = new CanadaLib();
	// #############################################################################################################
	// # Name of the Test : CRT01_CartBasic
	// # Migration Author : Cigniti Technologies
	// #
	// # Date of Migration : August 2019
	// # DESCRIPTION : This method is to perform Basic Cart operations.
	// # Parameters : StartRow ,EndRow , nextTestJoin
	// #
	// ###############################################################################################################

	@Parameters({ "StartRow", "EndRow", "nextTestJoin" })
	@Test

	public void Tc_CRT01(int StartRow, String EndRow, boolean nextTestJoin) throws Throwable {

		int counter = 0;
		try {
			int intStartRow = StartRow;
			int intEndRow = ReportControl.fnGetEndRowCunt(EndRow, "CRT01_CartBasic", TestDataInsight, "Web_Cart");
			for (int intCounter = intStartRow; intCounter <= intEndRow; intCounter++) {
				try {
					counter = intCounter;
					fnOpenTest();
					ReportStatus.fnDefaultReportStatus();
					ReportControl.intRowCount = intCounter;
					Hashtable<String, String> data = TestUtil.getDataByRowNo("CRT01_CartBasic", TestDataInsight,
							"Web_Cart", intCounter);
					TestEngineWeb.reporter.initTestCaseDescription("CartBasic");

					CommonLib.searchProduct(data.get("SearchItem1"));
					CommonLib.addToCartAndVerify();
					CommonLib.continueToShopping();
					CommonLib.searchProduct(data.get("SearchItem2"));
					CommonLib.addFirstDisplyedItemToCartAndVerify();
					CommonLib.clickCart();
					//canadaLib.continueToCheckout();
					cmtLib.handleWelcomeToInsightBetaPopUp();
					CommonLib.updateCartQuantity(data.get("quantity"));
					CommonLib.deleteItemFromCart();
					CommonLib.searchProduct(data.get("SearchItem2"));
					CommonLib.addSecondDisplyedItemToCartAndVerify();
					CommonLib.clickCart();
					//canadaLib.continueToCheckout();
					CommonLib.emptyCartAndVerify();
					cmtLib.loginToCMTSearchWebGrpAndUser(data.get("header"), data.get("WebGrp"),
							data.get("LnameEmailUname"), data.get("ContactName"));
					CommonLib.clickRolesAndPermissionsAtUserLevel();
					cmtLib.setPermissions(data.get("menuName"), data.get("userPermission"));
					cmtLib.setPermissions(data.get("menuName"), data.get("userPermission1"));
					CommonLib.cartBasics_verifyPermissionAtUserLevel(data.get("SearchItem2"),
							data.get("userPermission"), data.get("menuName"));
					cmtLib.navigateBackToCMT();
					cmtLib.setPermissions(data.get("menuName"), data.get("userPermission"));
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
			ReportStatus.fnUpdateResultStatus("CRT01_CartBasic", "Tc_CRT01", ReportStatus.strMethodName, 1, browser);
			throw new RuntimeException(e);
		} finally {
			ReportControl.fnEnableJoin();
			ReportStatus.fnUpdateResultStatus("CRT01_CartBasic", "Tc_CRT01", ReportStatus.strMethodName, counter,
					browser);
			fnCloseTest();
			ReportControl.fnNextTestJoin(nextTestJoin);
		}
	}
}
