package com.insight.WebTest.Cart;

import java.util.Hashtable;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.insight.Lib.CMTLib;
import com.insight.Lib.CanadaLib;
import com.insight.Lib.CartLib;
import com.insight.Lib.ChinaLib;
import com.insight.Lib.CommonLib;
import com.insight.Lib.EndUserFeaturesLib;
import com.insight.Lib.LineLevelInfoLib;
import com.insight.Lib.OrderLib;
import com.insight.Lib.ProductDetailLib;
import com.insight.Lib.ProductDisplayInfoLib;
import com.insight.Lib.SLPLib;
import com.insight.Lib.SearchLib;
import com.insight.Lib.ShipBillPayLib;
import com.insight.accelerators.ReportControl;
import com.insight.accelerators.TestEngineWeb;
import com.insight.googledrive.ReportStatus;
import com.insight.utilities.TestUtil;

public class CRT05_QuickSearchTest extends CartLib {
	CommonLib commonLib = new CommonLib();
	CartLib cartLib = new CartLib();
	CMTLib cmtLib = new CMTLib();
	SearchLib search = new SearchLib();
	ProductDisplayInfoLib prodInfoLib = new ProductDisplayInfoLib();
	EndUserFeaturesLib end = new EndUserFeaturesLib();
	SLPLib slp = new SLPLib();
	LineLevelInfoLib line = new LineLevelInfoLib();
	OrderLib order = new OrderLib();
	CanadaLib canadaLib = new CanadaLib();
	// #############################################################################################################
	// # Name of the Test : CRT05_QuickSearch
	// # Migration Customization Author : Cigniti Technologies
	// #
	// # Date of Migration : August 2019
	// # DESCRIPTION : This method is to perform Quick Search operations in the
	// shopping cart page.
	// # Parameters : StartRow ,EndRow , nextTestJoin
	// #
	// ###############################################################################################################
	@Parameters({ "StartRow", "EndRow", "nextTestJoin" })
	@Test()
	public void Tc_CRT05(int StartRow, String EndRow, boolean nextTestJoin) throws Throwable {

		int counter = 0;
		try {
			int intStartRow = StartRow;
			int intEndRow = ReportControl.fnGetEndRowCunt(EndRow, "CRT05_QuickSearch", TestDataInsight, "Web_Cart");
			for (int intCounter = intStartRow; intCounter <= intEndRow; intCounter++) {
				try {
					counter = intCounter;
					fnOpenTest();
					ReportStatus.fnDefaultReportStatus();
					ReportControl.intRowCount = intCounter;
					Hashtable<String, String> data = TestUtil.getDataByRowNo("CRT05_QuickSearch", TestDataInsight,
							"Web_Cart", intCounter);
					TestEngineWeb.reporter.initTestCaseDescription("QuickSearch");
					//cmtLib.loginToCMTSearchWebGrpAndUser(data.get("header"), data.get("WebGrp"),
							//data.get("LnameEmailUname"), data.get("ContactName"));
					cmtLib.loginToCMT(data.get("header"));
					cmtLib.searchForWebGroup(data.get("WebGrp"));
					cmtLib.clickOnTheWebGroup(data.get("WebGrp_Name"));
					cmtLib.hoverOnManageWebGroupsAndSelectOptions(data.get("Manage_Web_Grp_Options"));
					cmtLib.searchForaUserAndSelect(data.get("LnameEmailUname"), data.get("ContactName"));
					cmtLib.setPermissions(data.get("Menu_Name"), data.get("Enable_Purchasing_Popup"));
					cmtLib.clickOnloginAs();
					switchToChildWindow();
					cmtLib.loginVerification(data.get("ContactName"));
					commonLib.searchProduct(data.get("SearchItem1"));
					prodInfoLib.verifyTheManufacturerNumberInProductDetailsPage(data.get("SearchItem1"));
					prodInfoLib.clickOnWarrantiesTabOnProductDetailsPage();
					String manfa=prodInfoLib.getManfNumberFromWarrentiesPage(data.get("index"));
					prodInfoLib.clickOnAddToCartButtonInWarrentiesPage(data.get("index"));
					canadaLib.continueToCheckout();
					canadaLib.verifyPlaceCartLabel();
					prodInfoLib.verifyCartPageAndPartDetailsForRecentlyItemDynamically(manfa);
					//prodInfoLib.getProductManfNumber(manfa);
					//prodInfoLib.enterQuantityForProductsInViewCartPage(data.get("Quantity"));
//					commonLib.clickOnUpdateLinkInViewCartPage(data.get("Quantity"));
					
					//cartLib.selectFirstProductDisplay();
					clickOnProductLinkInCartPage();
					prodInfoLib.verifyTheManufacturerNumberInProductDetailsPage(manfa);
					commonLib.clickCart();
					canadaLib.verifyPlaceCartLabel();
					cartLib.verifyQuickShopWithValidSinglePartNumber(data.get("SearchItem2"), data.get("quantity"));
					canadaLib.verifyPlaceCartLabel();
					prodInfoLib.verifyCartPageAndPartDetailsForRecentlyItemDynamically(data.get("SearchItem2"));
					
					cartLib.verifyQuickShopWithInvalidPartNumber(data.get("SearchItem3"));
					cartLib.verifyQuickShopWithValidSinglePartNumber(data.get("SearchItem2"), data.get("quantity"));
					String quantity = getCartQuantity(data.get("SearchItem2"));
					if (Integer.parseInt(quantity)>Integer.parseInt(data.get("quantity"))) {
						reporter.SuccessReport("Quantity is increased on the Cart Page",
								"Quantity Exists and increased", "");
					} else {
						reporter.failureReport("Quantity is increased on the Cart Page",
								"Quantity Exists and not increased", "");
					}
					cartLib.verifyQuickShopWithValidSinglePartNumber(data.get("SearchItem4"), data.get("quantity1"));
					canadaLib.verifyPlaceCartLabel();
					//prodInfoLib.verifyCartPageAndPartDetailsForRecentlyItemDynamically(data.get("Search_Item2"));
					String quantity1 = getCartQuantity(data.get("Search_Item2"));
					if (Integer.parseInt(quantity1)>Integer.parseInt(quantity)) {
						reporter.SuccessReport("Quantity is increased on the Cart Page",
								"Quantity Exists and increased", "");
					} else {
						reporter.failureReport("Quantity is increased on the Cart Page",
								"Quantity Exists and not increased", "");
					}
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
			ReportStatus.fnUpdateResultStatus("QuickSearch", "Tc_CRT05", ReportStatus.strMethodName, 1, browser);
			throw new RuntimeException(e);
		} finally {
			ReportControl.fnEnableJoin();
			ReportStatus.fnUpdateResultStatus("QuickSearch", "Tc_CRT05", ReportStatus.strMethodName, counter, browser);
			fnCloseTest();
			ReportControl.fnNextTestJoin(nextTestJoin);
		}
	}
}
