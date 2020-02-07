package com.insight.WebTest.Cart;

import java.util.Hashtable;

import com.insight.Lib.*;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

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
	CommonCanadaLib ccp = new CommonCanadaLib();
ProductDisplayInfoLib prodinfo= new ProductDisplayInfoLib();
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

					cmtLib.loginToCMT(data.get("Header"));
					cmtLib.searchForWebGroup(data.get("WebGrp"));
					cmtLib.clickOnTheWebGroup(data.get("WebGrp_Name"));
					cmtLib.hoverOnManageWebGroupsAndSelectOptions(data.get("manage_Web_Grp_Options"));
					cmtLib.searchForaUserAndSelect(data.get("LnameEmailUname"), data.get("ContactName"));
					//cmtLib.setPermissions(data.get("Menu_Name"), data.get("Enable_Purchasing_Popup"));
					cmtLib.clickOnRolesAndPermissionsAndSetPermission(data.get("Menu_Name"), data.get("Set_Permission"));
					// Disable -- Enable Custom Catalog
					cmtLib.setPermissionsToDisable(data.get("Menu_Name"), data.get("Set_Permission1"));
					cmtLib.clickOnloginAs();
					switchToChildWindow();
					cmtLib.loginVerification(data.get("ContactName"));
					searchLib.verifyContractAllDisplayed();
					commonLib.searchProduct(data.get("Search_Item"));
					searchLib.verifyTheResultsForSearchTerm(data.get("Search_Item"));
					String a =prodinfo.getPartNumberInSearchResultsPage();
					commonLib.addFirstDisplyedItemToCartAndVerify();
					String partNumber1 = cartLib.getPartNumber();
					System.out.println("partNumber1"+partNumber1);
					canadaLib.continueToCheckout();
					cartLib.verifyCartPageAvailablity();
					//prodinfo.verifyCartPageAndPartDetails();
					//cartLib.verifyItemInCart(partNumber1);
					prodinfo.verifyCartPageAndPartDetailsForRecentlyItemDynamically(data.get("Search_Item"));

					cartLib.verifyQuickShopWithValidSinglePartNumber(data.get("Search_Item2"), data.get("quantity"));
					cartLib.verifyCartPageAvailablity();
					prodinfo.verifyCartPageAndPartDetailsForRecentlyItemDynamically(data.get("Search_Item2"));
					//prodinfo.deleteSelectedProducts();
					ccp.clickOnEmptyCart();
					commonLib.verifyCartIsEMpty();

					searchLib.selectNewcontract(data.get("Contract_name"));
//Verify Contract Page
					commonLib.searchProduct(data.get("Search_Item3"));
					searchLib.verifyTheResultsForSearchTerm(data.get("Search_Item3"));
					String b =prodinfo.getPartNumberInSearchResultsPage();
					commonLib.addFirstDisplyedItemToCartAndVerify();
					String partNumber3 = cartLib.getPartNumber();
					System.out.println("partNumber3"+partNumber3);
					canadaLib.continueToCheckout();
					cartLib.verifyCartPageAvailablity();
					prodinfo.verifyCartPageAndPartDetails();
					prodinfo.verifyCartPageAndPartDetailsForRecentlyItemDynamically(data.get("SearchItem3"));

					cartLib.verifyQuickShopWithValidSinglePartNumber(data.get("Search_Item2"), data.get("quantity"));
					cartLib.verifyCartPageAvailablity();
					prodinfo.verifyCartPageAndPartDetailsForRecentlyItemDynamically(data.get("Search_Item2"));
					//prodinfo.deleteSelectedProducts();
					ccp.clickOnEmptyCart();
					commonLib.verifyCartIsEMpty();

					cmtLib.clickOnLogoutlink();
					cmtLib.navigateBackToCMT();
					cmtLib.verifyWebGroupsManagementUsers();
					cmtLib.clickOnRolesAndPermissionsAndSetPermission(data.get("Menu_Name"), data.get("Set_Permission1"));

					cmtLib.clickOnloginAs();
					switchToChildWindow();
					cmtLib.loginVerification(data.get("ContactName"));
					searchLib.verifyContractAllDisplayed();
					commonLib.searchProduct(data.get("Search_Item"));
					searchLib.verifyTheResultsForSearchTerm(data.get("Search_Item"));
					String c =prodinfo.getPartNumberInSearchResultsPage();
					commonLib.addFirstDisplyedItemToCartAndVerify();
					String partNumber4 = cartLib.getPartNumber();
					String[] partn = partNumber4.split("#: ");
					String partNum =partn[1] ;
					System.out.println("partNumber1"+partNumber1);
					canadaLib.continueToCheckout();
					cartLib.verifyCartPageAvailablity();
					prodinfo.verifyCartPageAndPartDetails();
					//cartLib.verifyItemInCart(partNumber1);
					prodinfo.verifyCartPageAndPartDetailsForRecentlyItemDynamically(data.get("Search_Item"));

					cartLib.verifyQuickShopWithValidSinglePartNumber(data.get("QuickSearch"), data.get("Quantity"));

					cartLib.verifyCartPageAvailablity();
					prodinfo.verifyCartPageAndPartDetailsForRecentlyItemDynamically(data.get("QuickSearch"));

					cmtLib.clickOnLogoutlink();
//Click on add button in quick shop, Master cpy

//8**********************************************************************************************************************
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
