package com.insight.WebTest.Cart;

import java.util.Hashtable;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.insight.Lib.CMTLib;
import com.insight.Lib.CanadaLib;
import com.insight.Lib.CartLib;
import com.insight.Lib.ChinaLib;
import com.insight.Lib.CommonLib;
import com.insight.Lib.InvoiceHistoryLib;
import com.insight.Lib.OrderLib;
import com.insight.Lib.ProductDetailLib;
import com.insight.Lib.ProductDisplayInfoLib;
import com.insight.Lib.SearchLib;
import com.insight.Lib.ShipBillPayLib;
import com.insight.accelerators.ReportControl;
import com.insight.accelerators.TestEngineWeb;
import com.insight.googledrive.ReportStatus;
import com.insight.utilities.TestUtil;

public class CRT12_TopNavTest extends CartLib {
	CommonLib commonLib = new CommonLib();
	CMTLib cmtLib = new CMTLib();
	CartLib cartLib = new CartLib();
	SearchLib searchLib = new SearchLib();
	OrderLib orderLib = new OrderLib();
	InvoiceHistoryLib invoiceHistoryLib = new InvoiceHistoryLib();
	CanadaLib canadaLib = new CanadaLib();

	// #############################################################################################################
	// # Name of the Test : CRT12_TopNav
	// # Migration Author : Cigniti Technologies
	// #
	// # Date of Migration : August 2019
	// # DESCRIPTION : This method is to perform Basic Cart operations.
	// # Parameters : StartRow ,EndRow , nextTestJoin
	// #
	// ###############################################################################################################
	@Parameters({ "StartRow", "EndRow", "nextTestJoin" })
	@Test
	public void Tc_CRT12(int StartRow, String EndRow, boolean nextTestJoin) throws Throwable {

		int counter = 0;
		try {
			int intStartRow = StartRow;
			int intEndRow = ReportControl.fnGetEndRowCunt(EndRow, "CRT12_TopNav", TestDataInsight, "Web_Cart");
			for (int intCounter = intStartRow; intCounter <= intEndRow; intCounter++) {
				try {
					counter = intCounter;
					fnOpenTest();
					ReportStatus.fnDefaultReportStatus();
					ReportControl.intRowCount = intCounter;
					Hashtable<String, String> data = TestUtil.getDataByRowNo("CRT12_TopNav", TestDataInsight,
							"Web_Cart", intCounter);
					TestEngineWeb.reporter.initTestCaseDescription("TopNav");

					cmtLib.loginToCMTSearchWebGrpAndUser(data.get("Header"), data.get("WebGrp"),
							data.get("LnameEmailUname"), data.get("ContactName"));
					cmtLib.setPermissions(data.get("menuName"), data.get("Enable_Purchasing_Popup"));
					cmtLib.clickOnloginAs();
					switchToChildWindow();
					invoiceHistoryLib.closeAccountTools();
					commonLib.clickAccountToolsFromSideMenuAndClickOnProductGrp(data.get("Tools_Menu"),
							data.get("Tools_Menu_DD"), data.get("Product_Group"), data.get("Product_Name"));
					searchLib.clickAddToOrderOnCompanyStandardsScreen();
					//commonLib.clickCart();
					commonLib.verifyBundleIsAddedToCart();
					commonLib.searchProduct(data.get("PartNumber"));
					commonLib.addToCartAndVerify();
					orderLib.continueToCheckOutAddWarrantyAndVerifyTheCart(data.get("PartNumber"));
					commonLib.spinnerImage();
					commonLib.searchProduct(data.get("Search_Product"));
					commonLib.addFirstDisplyedItemToCartAndVerify();
					String partNumber1 = cartLib.getPartNumber();
					//commonLib.clickCart();
					canadaLib.continueToCheckout();
					cartLib.verifyItemInCart(partNumber1);
					commonLib.searchProduct(data.get("Search_Product"));
					commonLib.addSecondDisplyedItemToCartAndVerify();
					String partNumber2 = cartLib.getPartNumber();
					canadaLib.continueToCheckout();
					cartLib.verifyItemInCart(partNumber2);
					commonLib.emptyCartAndVerify();
					commonLib.clickLogOutLink(data.get("Logout_Header"));
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
			ReportStatus.fnUpdateResultStatus("TopNav", "Tc_CRT12", ReportStatus.strMethodName, 1, browser);
			throw new RuntimeException(e);
		} finally {
			ReportControl.fnEnableJoin();
			ReportStatus.fnUpdateResultStatus("TopNav", "Tc_CRT12", ReportStatus.strMethodName, counter, browser);
			fnCloseTest();
			ReportControl.fnNextTestJoin(nextTestJoin);
		}
	}
}