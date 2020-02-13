package com.insight.WebTest.Cart;

import java.util.Hashtable;
import java.util.List;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.insight.Lib.CMTLib;
import com.insight.Lib.CanadaLib;
import com.insight.Lib.CartLib;
import com.insight.Lib.ChinaLib;
import com.insight.Lib.CommonCanadaLib;
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

public class CRT04_CartPrintFriendlyTest extends CartLib {
	CommonLib commonLib = new CommonLib();
	CartLib cartLib = new CartLib();
	SearchLib search = new SearchLib();
	ProductDisplayInfoLib prodInfoLib = new ProductDisplayInfoLib();
	 ProductDetailLib prodDetailsLib = new ProductDetailLib();
	EndUserFeaturesLib end = new EndUserFeaturesLib();
	SLPLib slp = new SLPLib();
	LineLevelInfoLib line = new LineLevelInfoLib();
	OrderLib order = new OrderLib();
	CanadaLib canadaLib = new CanadaLib();
	CMTLib cmtLib = new CMTLib();
	CommonCanadaLib ccp =  new CommonCanadaLib();

	// #############################################################################################################
	// # Name of the Test : CRT04_CartPrintFriendly
	// # Migration Author : Cigniti Technologies
	// #
	// # Date of Migration : August 2019
	// # DESCRIPTION : This method is to perform search operations in the Product
	// Research Request page.
	// # Parameters : StartRow ,EndRow , nextTestJoin
	// #
	// ###############################################################################################################
	@Parameters({ "StartRow", "EndRow", "nextTestJoin" })
	@Test(enabled = true)
	public void Tc_CRT04(int StartRow, String EndRow, boolean nextTestJoin) throws Throwable {

		int counter = 0;
		try {
			int intStartRow = StartRow;
			int intEndRow = ReportControl.fnGetEndRowCunt(EndRow, "CRT04_CartPrintFriendly", TestDataInsight,
					"Web_Cart");
			for (int intCounter = intStartRow; intCounter <= intEndRow; intCounter++) {
				try {
					counter = intCounter;
					fnOpenTest();
					ReportStatus.fnDefaultReportStatus();
					ReportControl.intRowCount = intCounter;
					Hashtable<String, String> data = TestUtil.getDataByRowNo("CRT04_CartPrintFriendly", TestDataInsight,
							"Web_Cart", intCounter);
					TestEngineWeb.reporter.initTestCaseDescription("CartPrintFriendly");
					cmtLib.loginToCMT(data.get("header"));
					cmtLib.searchForWebGroup(data.get("WebGrp"));
					cmtLib.clickOnTheWebGroup(data.get("WebGrp_Name"));
					cmtLib.hoverOnManageWebGroupsAndSelectOptions(data.get("Manage_Web_Grp_Options"));
					cmtLib.searchForaUserAndSelect(data.get("LnameEmailUname"), data.get("ContactName"));
					cmtLib.setPermissions(data.get("menuName1"), data.get("Enable_Purchasing_Popup"));
					cmtLib.AssigntheusertoServiceLevelShippingwithnodefault(data.get("menuName2"),
							data.get("user_Permissions"), data.get("indexvalue"));
					cmtLib.clickOnloginAs();
					switchToChildWindow();
					// Login verification
					cmtLib.loginVerification("User - "+data.get("ContactName"));
					
					// Add first part
					commonLib.searchProduct(data.get("SearchItem1"));
					search.removeTheFilterForInStockOnly(data.get("In_Stock_Only"));
					String mfrNumber1=prodDetailsLib.getInsightPartNumberInProductInfopage();
					prodInfoLib.enterQuantityOnProductDetailsPage(data.get("Quantity"));
					commonLib.addToCartAndVerify();
					order.continueToCheckOutOnAddCart();
					canadaLib.verifyPlaceCartLabel();
					
					// adding second part
					commonLib.searchProduct(data.get("SearchItem2"));
					search.removeTheFilterForInStockOnly(data.get("In_Stock_Only"));
					String mfrNumber2=prodDetailsLib.getInsightPartNumberInProductInfopage();
					prodInfoLib.clickOnWarrantiesTabOnProductDetailsPage();
					//ccp.getPriceinWarrenty();
					ccp.clickOnAddToCartButtonUnderWarrentyDynamically();
					String warrantyPartNumber=cartLib.getPartNumber();
					commonLib.addToCartAndVerify();
					order.continueToCheckOutOnAddCart();
					canadaLib.verifyPlaceCartLabel();
					
					// adding third part
					commonLib.searchProduct(data.get("SearchItem3"));
					String mfrNumber3=prodDetailsLib.getInsightPartNumberInProductInfopage();
					commonLib.addToCartAndVerify();
					order.continueToCheckOutOnAddCart();
					canadaLib.verifyPlaceCartLabel();
					//commonLib.closePopUp();
					//cartLib.clickAndVerifyViewPrintablePopUp(data.get("OrderUtilities"));
					
					// verify print popup
					List<String> prodDesc1=order.getProductDescriptionOfCartProduct();
					List<String> quantity1=order.getCartProductQuantity();
					List<String> stock1=order.getCartProductStock();
					List<String> totalPrice1=order.getCartProductTotalPrice();
					List<String> unitPrice1=order.getCartProductUnitPrice();

					order.clickPrintIconOnCartPage(data.get("OrderUtilities"));
					order.VerifyPrintPopup(prodDesc1,quantity1,stock1,totalPrice1,unitPrice1);
					order.verifyWarrantiesOnPrintPopup(data.get("SearchItem2"));
					verifyPrintAndCloseIconexists();
					// clickPrintInPopUp();
					cartLib.closePrintPopUp();
					// verify first part added on cart
					prodInfoLib.verifyCartPageAndPartDetailsForRecentlyItemDynamically(data.get("SearchItem1"));
					String warranty=order.addWarrantyInCartPage();
					order.clickPrintIconOnCartPage(data.get("OrderUtilities"));
					String printWarranty=order.verifyWarrantiesOnPrintPopup(data.get("SearchItem1"));
					if(warranty.equals(printWarranty)) {
						reporter.SuccessReport("View Printable POPUP", "Warranty info Exist", "Warranty info : "+printWarranty, driver);
					}else {
						reporter.failureReport("View Printable POPUP", "Warranty info does not Exist", "", driver);
					}
					cartLib.closePrintPopUp();
					// Empty cart
					commonLib.emptyCartAndVerify();
					
					// company stanards
					//selecting bundle from company standards page
					commonLib.clickAccountToolsFromSideMenuAndClickOnProductGrp(data.get("toolsMenuName"),data.get("dropDown") ,data.get("productGroup"),data.get("productName"));
					search.clickAddToOrderOnCompanyStandardsScreen();
					order.verifyCartHeaderLabel();
					order.clickPrintIconOnCartPage(data.get("OrderUtilities"));
					order.verifyBundleOnPrintPopup(data.get("productName"));
					cartLib.closePrintPopUp();
					commonLib.clickLogOutLink(data.get("Logout"));
					
					
				} catch (Exception e) {
					ReportStatus.blnStatus = false;
					// gErrorMessage = e.getMessage();
					gTestStatus = false;
				}
				ReportControl.fnEnableJoin();
				ReportStatus.fnUpdateResultStatus("CartPrintFriendly", "CRT04", ReportStatus.strMethodName, intCounter,
						browser);
				fnCloseTest();
			}
		} catch (Exception e) {
			e.printStackTrace();
			ReportStatus.blnStatus = false;
			// gErrorMessage = e.getMessage();
			gTestStatus = false;
			ReportStatus.fnUpdateResultStatus("CartPrintFriendly", "CRT04", ReportStatus.strMethodName, 1, browser);
			throw new RuntimeException(e);
		}

		finally {
			ReportControl.fnEnableJoin();
			ReportStatus.fnUpdateResultStatus("CartPrintFriendly", "CRT04", ReportStatus.strMethodName, counter,
					browser);
			fnCloseTest();
			ReportControl.fnNextTestJoin(nextTestJoin);
		}
	}
}