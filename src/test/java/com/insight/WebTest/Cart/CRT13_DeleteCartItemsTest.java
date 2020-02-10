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

public class CRT13_DeleteCartItemsTest extends CartLib {
	CommonLib commonLib = new CommonLib();
	CMTLib cmtLib = new CMTLib();
	SearchLib search = new SearchLib();
	ProductDisplayInfoLib prodInfoLib = new ProductDisplayInfoLib();
	EndUserFeaturesLib end = new EndUserFeaturesLib();
	SLPLib slp = new SLPLib();
	LineLevelInfoLib line = new LineLevelInfoLib();
	OrderLib order = new OrderLib();
	CanadaLib canadaLib = new CanadaLib();
	CartLib cartLib = new CartLib();
	SearchLib searchLib = new SearchLib();

	// #############################################################################################################
	// # Name of the Test : CRT13_DeleteCartItems
	// # Migration Author : Cigniti Technologies
	// #
	// # Date of Migration : August 2019
	// # DESCRIPTION : This method is to perform Basic Cart operations.
	// # Parameters : StartRow ,EndRow , nextTestJoin
	// #
	// ###############################################################################################################
	@Parameters({ "StartRow", "EndRow", "nextTestJoin" })
	@Test(enabled = true)
	public void Tc_CRT13(int StartRow, String EndRow, boolean nextTestJoin) throws Throwable {

		int counter = 0;
		try {
			int intStartRow = StartRow;
			int intEndRow = ReportControl.fnGetEndRowCunt(EndRow, "CRT13_DeleteCartItems", TestDataInsight, "Web_Cart");
			for (int intCounter = intStartRow; intCounter <= intEndRow; intCounter++) {
				try {
					counter = intCounter;
					fnOpenTest();
					ReportStatus.fnDefaultReportStatus();
					ReportControl.intRowCount = intCounter;
					Hashtable<String, String> data = TestUtil.getDataByRowNo("CRT13_DeleteCartItems", TestDataInsight,
							"Web_Cart", intCounter);
					TestEngineWeb.reporter.initTestCaseDescription("DeleteCartItems");
					//cmtLib.loginToCMTSearchWebGrpAndUser(data.get("header"), data.get("WebGrp"),
							//data.get("LnameEmailUname"), data.get("ContactName"));
					//System.out.println("testdata"+data.get("SearchItem").split(",")[0]);
					//System.out.println("testdata"+data.get("SearchItem").split(",")[1]);
					
					cmtLib.loginToCMT(data.get("header"));
					cmtLib.searchForWebGroup(data.get("WebGrp"));
					cmtLib.clickOnTheWebGroup(data.get("WebGrp_Name"));
					cmtLib.hoverOnManageWebGroupsAndSelectOptions(data.get("Manage_Web_Grp_Options"));
					cmtLib.searchForaUserAndSelect(data.get("LnameEmailUname"), data.get("ContactName"));
					cmtLib.setPermissions(data.get("menuName"), data.get("Enable_Purchasing_Popup"));
					cmtLib.clickOnloginAs();
					switchToChildWindow();
					cmtLib.loginVerification(data.get("ContactName"));
					
					commonLib.searchProduct(data.get("PartNumber"));
				    prodInfoLib.verifyTheManufacturerNumberInProductDetailsPage(data.get("PartNumber"));
					search.increaseQuantity(data.get("quantity"));
					commonLib.addToCartAndVerify();
				    canadaLib.continueToCheckout();
				    canadaLib.verifyPlaceCartLabel();
				    prodInfoLib.verifyCartPageAndPartDetailsForRecentlyItemDynamicaly(data.get("PartNumber"));
				    
				    cartLib.verifyQuickShopWithValidSinglePartNumber(data.get("SearchItem"), data.get("quantity"));
				    canadaLib.verifyPlaceCartLabel();
				    prodInfoLib.verifyCartPageAndPartDetailsForRecentlyItemDynamicaly(data.get("SearchItem").split(",")[0]);
				    prodInfoLib.verifyCartPageAndPartDetailsForRecentlyItemDynamicaly(data.get("SearchItem").split(",")[1]);
				    cartLib.deletePartInCart(data.get("PartNumber"));
				    cartLib.deletePartInCart(data.get("SearchItem1"));
				    cartLib.deletePartInCart(data.get("SearchItem2"));
				    
				    commonLib.searchProduct(data.get("SearchItem3"));
					prodInfoLib.verifyTheManufacturerNumberInProductDetailsPage(data.get("SearchItem3"));
					prodInfoLib.clickOnWarrantiesTabOnProductDetailsPage();
					
					
					String manfa=prodInfoLib.getManfNumberFromWarrentiesPage(data.get("index")).split("Insight Part #:")[1].trim();
					prodInfoLib.clickOnAddToCartButtonInWarrentiesPage(data.get("index"));
					canadaLib.continueToCheckout();
					canadaLib.verifyPlaceCartLabel();
					prodInfoLib.verifyCartPageAndPartDetailsForRecentlyItemDynamicaly(manfa);
					System.out.println("manfa"+manfa);
					//System.out.println("Manfactured+"+manfa.split("Insight Part #:")[1]);
					cartLib.deletePartInCart(manfa);
					commonLib.clickOnAccountToolsAndClickOnProductGrp(data.get("Tools_Menu"),
								data.get("Tools_Menu_DD"));
					searchLib.selectProductGroupAndVerify(data.get("Product_Group"), data.get("Product_Name"));
					commonLib.clickCart();
					canadaLib.verifyPlaceCartLabel();
					commonLib.verifyBundleIsAddedToCart();
					deleteBundle();
					commonLib.clickLogOutLink(data.get("Logout_Header"));
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
			//gErrorMessage = e.toString();
			gTestStatus = false;
			ReportStatus.fnUpdateResultStatus("DeleteCartItems", "Tc_CRT13", ReportStatus.strMethodName, 1, browser);
			throw new RuntimeException(e);
		} finally {
			ReportControl.fnEnableJoin();
			ReportStatus.fnUpdateResultStatus("DeleteCartItems", "Tc_CRT13", ReportStatus.strMethodName, counter,
					browser);
			fnCloseTest();
			ReportControl.fnNextTestJoin(nextTestJoin);
		}
	}
}