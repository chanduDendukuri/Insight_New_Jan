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

public class CRT07_SaveCartTest extends CartLib {
	CommonLib commonLib = new CommonLib();
	CMTLib cmtLib = new CMTLib();
	CartLib cartLib = new CartLib();
	SearchLib searchLib = new SearchLib();
	CanadaLib canadaLib = new CanadaLib();
	ProductDisplayInfoLib prodInfoLib = new ProductDisplayInfoLib();
	SearchLib search = new SearchLib();
	// #############################################################################################################
	// # Name of the Test : CRT07_SaveCart
	// # Migration Author : Cigniti Technologies
	// #
	// # Date of Migration : August 2019
	// # DESCRIPTION : This method is to perform Basic Cart operations.
	// # Parameters : StartRow ,EndRow , nextTestJoin
	// #
	// ###############################################################################################################
	@Parameters({ "StartRow", "EndRow", "nextTestJoin" })
	@Test
	public void Tc_CRT07(int StartRow, String EndRow, boolean nextTestJoin) throws Throwable {

		int counter = 0;
		try {
			int intStartRow = StartRow;
			int intEndRow = ReportControl.fnGetEndRowCunt(EndRow, "CRT07_SaveCart", TestDataInsight, "Web_Cart");
			for (int intCounter = intStartRow; intCounter <= intEndRow; intCounter++) {
				try {
					counter = intCounter;
					fnOpenTest();
					ReportStatus.fnDefaultReportStatus();
					ReportControl.intRowCount = intCounter;
					Hashtable<String, String> data = TestUtil.getDataByRowNo("CRT07_SaveCart", TestDataInsight,
							"Web_Cart", intCounter);
					TestEngineWeb.reporter.initTestCaseDescription("SaveCart");

					
					cmtLib.loginToCMT(data.get("header"));
					cmtLib.searchForWebGroup(data.get("WebGrp"));
					cmtLib.clickOnTheWebGroup(data.get("WebGrp_Name"));
					cmtLib.hoverOnManageWebGroupsAndSelectOptions(data.get("Manage_Web_Grp_Options"));
					cmtLib.searchForaUserAndSelect(data.get("LnameEmailUname"), data.get("ContactName"));
					
					cmtLib.setPermissions(data.get("menuName"), data.get("Enable_Purchasing_Popup"));
					cmtLib.setPermissions(data.get("menuName"), data.get("userPermission"));
					cmtLib.setMultiplePermissionsToDisable(data.get("menuName"), data.get("User_Permissions"));
					String mainWindow = parentWindow();
					cmtLib.clickOnloginAs();
					switchToWindow(mainWindow);
					cmtLib.loginVerification(data.get("ContactName"));
					commonLib.clickOnAccountToolsAndClickOnProductGrp(data.get("Tools_Menu"),
							data.get("Tools_Menu_DD"));
					cartLib.deleteSavedCartFromAccountTools();
					commonLib.searchProduct(data.get("Search_Item"));
					searchLib.verifyBreadCrumbInSearchResultsPage(data.get("Search_Item"));
					/*Review comment*/	removeInStockItems();
					//String searchItem=prodInfoLib.getPartNumberInSearchResultsPage();
					String searchItem=prodInfoLib.getPartNumberExactlyInSearchResultsPage();
					commonLib.addFirstDisplyedItemToCartAndVerify();
					canadaLib.continueToCheckout();
					canadaLib.verifyPlaceCartLabel();
					prodInfoLib.verifyCartPageAndPartDetailsForRecentlyItemDynamically(searchItem);
					
					commonLib.searchProduct(data.get("Search_Item2"));
					String searchItem2=prodInfoLib.getPartNumberInSearchResultsPage();
					commonLib.addFirstDisplyedItemToCartAndVerify();
					canadaLib.continueToCheckout();
					canadaLib.verifyPlaceCartLabel();
					prodInfoLib.verifyCartPageAndPartDetailsForRecentlyItemDynamically(searchItem2);
					
					
					commonLib.searchProduct(data.get("Search_Item3"));
					prodInfoLib.verifyTheManufacturerNumberInProductDetailsPage(data.get("Search_Item3"));
					search.increaseQuantity(data.get("quantity"));
					commonLib.addToCartAndVerify();
					canadaLib.continueToCheckout();
					canadaLib.verifyPlaceCartLabel();
					prodInfoLib.verifyCartPageAndPartDetailsForRecentlyItemDynamically(data.get("Search_Item3"));
					
					String cartName = "QTPTestCart"+getRandomNumeric(5);
					cartLib.clickOnSaveCartContentAndSaveCart(cartName);
					cartLib.verifyCartIsEmpty();
					commonLib.clickCart();
					commonLib.verifyCartIsEMpty();
					
					cartLib.openSavedCartFromTools(cartName);
					cartLib.addToCartInSavedCart(cartName);
					canadaLib.verifyPlaceCartLabel();
					prodInfoLib.verifyCartPageAndPartDetailsForRecentlyItemDynamically(data.get("Search_Item3"));
					
					commonLib.clickAccountToolsFromSideMenuAndClickOnProductGrp(data.get("Tools_Menu"),
							data.get("Tools_Menu_DD1"), data.get("Product_Group"), data.get("Product_Name"));
					//commonLib.clickAccountToolsFromSideMenuAndClickOnProductGrp(data.get("Tools_Menu"),
							//data.get("Tools_Menu_DD"), data.get("Product_Group"), data.get("Product_Name"));
					searchLib.clickAddToOrderOnCompanyStandardsScreen();
					commonLib.clickCart();
					commonLib.verifyBundleIsAddedToCart();
					String cartName1 = "SavedCart"+getRandomNumeric(5);
					cartLib.clickOnSaveCartContentAndSaveCartAndClearCartOff(cartName1);
					
/*Comment*/			verifySavedCarts();
					commonLib.clickCart();
					canadaLib.verifyPlaceCartLabel();
					commonLib.emptyCartAndVerify();
					commonLib.clickOnAccountToolsAndClickOnProductGrp(data.get("Tools_Menu"),
							data.get("Tools_Menu_DD"));
					cartLib.deleteSavedCartFromAccountTools();
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

