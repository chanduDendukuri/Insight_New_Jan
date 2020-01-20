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

public class CRT06_QuickSearchIPSTest extends CartLib {
	CommonLib commonLib = new CommonLib();
	CMTLib cmtLib = new CMTLib();
	CartLib cartLib = new CartLib();
	CanadaLib canadaLib = new CanadaLib();
	SearchLib searchLib = new SearchLib();

	// #############################################################################################################
	// # Name of the Test : CRT06_QuickSearchIPS
	// # Migration Author : Cigniti Technologies
	// #
	// # Date of Migration : August 2019
	// # DESCRIPTION : This method is to perform Basic Cart operations.
	// # Parameters : StartRow ,EndRow , nextTestJoin
	// #
	// ###############################################################################################################
	@Parameters({ "StartRow", "EndRow", "nextTestJoin" })
	@Test
	public void Tc_CRT06(int StartRow, String EndRow, boolean nextTestJoin) throws Throwable {

		int counter = 0;
		try {
			int intStartRow = StartRow;
			int intEndRow = ReportControl.fnGetEndRowCunt(EndRow, "CRT06_QuickSearchIPS", TestDataInsight, "Web_Cart");
			for (int intCounter = intStartRow; intCounter <= intEndRow; intCounter++) {
				try {
					counter = intCounter;
					fnOpenTest();
					ReportStatus.fnDefaultReportStatus();
					ReportControl.intRowCount = intCounter;
					Hashtable<String, String> data = TestUtil.getDataByRowNo("CRT06_QuickSearchIPS", TestDataInsight,
							"Web_Cart", intCounter);
					TestEngineWeb.reporter.initTestCaseDescription("QuickSearchIPS");
					cmtLib.loginToCMTSearchWebGrpAndUser(data.get("Header"), data.get("WebGrp"),
							data.get("LnameEmailUname"), data.get("ContactName"));

					cmtLib.setPermissions(data.get("Menu_Name"), data.get("Set_Permission"));
					cmtLib.setPermissionsToDisable(data.get("Menu_Name"), data.get("Set_Permission1"));
					cmtLib.clickOnloginAs();
					switchToChildWindow();
					searchLib.verifyContractAllDisplayed();
					commonLib.searchProduct(data.get("Search_Item"));
					commonLib.addToCartAndVerify();
//					commonLib.continueToShopping();
//					commonLib.clickCart();
					canadaLib.continueToCheckout();
					cartLib.verifyItemInCart(data.get("Search_Item"));
					cartLib.verifyQuickShopWithValidSinglePartNumber(data.get("Search_Item2"), data.get("quantity"));
					commonLib.clickCart();
					commonLib.emptyCartAndVerify();
					searchLib.selectNewcontract(data.get("Contract_name"));
					commonLib.searchProduct(data.get("Search_Item3"));
					commonLib.addToCartAndVerify();

//					commonLib.continueToShopping();
//					commonLib.clickCart();
					canadaLib.continueToCheckout();
					cartLib.verifyItemInCart(data.get("Search_Item3"));
					cartLib.verifyContractNameInCart(data.get("Contract_name"));
					cartLib.verifyQuickShopWithValidSinglePartNumber(data.get("Search_Item2"), data.get("quantity"));
					commonLib.clickCart();
					commonLib.emptyCartAndVerify();
					commonLib.clickLogOutLink(data.get("Logout_Header"));
					cmtLib.loginToCMTSearchWebGrpAndUser(data.get("Header"), data.get("WebGrp"),
							data.get("LnameEmailUname"), data.get("ContactName"));
					cmtLib.setPermissions(data.get("Menu_Name"), data.get("Set_Permission1"));
					cmtLib.clickOnloginAs();
					switchToChildWindow();
					commonLib.searchProduct(data.get("Search_Item"));
					commonLib.addToCartAndVerify();
					canadaLib.continueToCheckout();
					cartLib.verifyItemInCart(data.get("Search_Item"));
					cartLib.verifyQuickShopWithValidSinglePartNumber(data.get("Search_Item2"), data.get("quantity"));
					commonLib.clickCart();
					commonLib.emptyCartAndVerify();
					commonLib.clickLogOutLink(data.get("Logout_Header"));
					cmtLib.loginToCMTSearchWebGrpAndUser(data.get("Header"), data.get("WebGrp"),
							data.get("LnameEmailUname"), data.get("ContactName"));
					cmtLib.setPermissionsToDisable(data.get("Menu_Name"), data.get("Set_Permission"));
					cmtLib.clickOnloginAs();
					switchToChildWindow();
					commonLib.searchProduct(data.get("Search_Item"));
					commonLib.addToCartAndVerify();
					canadaLib.continueToCheckout();
					cartLib.verifyItemInCart(data.get("Search_Item"));
					cartLib.verifyQuickShopIsDisable();
					System.out.println("Test completed");

				} catch (Exception e) {
					ReportStatus.blnStatus = false;
				//	gErrorMessage = e.getMessage();
					gTestStatus = false;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			ReportStatus.blnStatus = false;
		//	gErrorMessage = e.toString();
			gTestStatus = false;
			ReportStatus.fnUpdateResultStatus("QuickSearchIPS", "Tc_CRT06", ReportStatus.strMethodName, 1, browser);
			throw new RuntimeException(e);
		} finally {
			ReportControl.fnEnableJoin();
			ReportStatus.fnUpdateResultStatus("QuickSearchIPS", "Tc_CRT06", ReportStatus.strMethodName, counter,
					browser);
			fnCloseTest();
			ReportControl.fnNextTestJoin(nextTestJoin);
		}
	}
}
