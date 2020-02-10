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

public class CRT02_CartBasicIPSTest extends CartLib{
	CommonLib commonLib = new CommonLib();
	CartLib cartLib = new CartLib();
	CMTLib cmtLib = new CMTLib();
	ShipBillPayLib shipbLib=new ShipBillPayLib();
	CanadaLib canadaLib = new CanadaLib();
	SearchLib search = new SearchLib();
	ProductDisplayInfoLib prodInfoLib = new ProductDisplayInfoLib();
	OrderLib orderLib=new OrderLib();




	// #############################################################################################################
	// #    Name of the Test         : CRT02_CartBasicIPS
	// #    Migration Author         : Cigniti Technologies
	// #
	// #    Date of Migration        : August 2019
	// #    DESCRIPTION              : This method is to perform Basic Cart  operations using IPS USER.
	// #    Parameters               : StartRow ,EndRow , nextTestJoin
	// #
	// ###############################################################################################################

	@Parameters({"StartRow","EndRow","nextTestJoin"})
	@Test
	public void Tc_CRT02(int StartRow,String EndRow,boolean nextTestJoin) throws Throwable {

		int counter = 0;
		try {
			int intStartRow = StartRow;
			int intEndRow = ReportControl.fnGetEndRowCunt(EndRow, "CRT02_CartBasicIPS", TestDataInsight, "Web_Cart");
			for (int intCounter = intStartRow; intCounter <= intEndRow; intCounter++) {
				try {
					counter = intCounter;
					fnOpenTest();
					ReportStatus.fnDefaultReportStatus();
					ReportControl.intRowCount = intCounter;
					Hashtable<String, String> data = TestUtil.getDataByRowNo("CRT02_CartBasicIPS", TestDataInsight,
							"Web_Cart", intCounter);
					TestEngineWeb.reporter.initTestCaseDescription("CartBasicIPS");

					cmtLib.loginToCMT(data.get("header"));
					cmtLib.searchForWebGroup(data.get("WebGrp"));
					cmtLib.clickOnTheWebGroup(data.get("WebGrp_Name"));
					cmtLib.hoverOnManageWebGroupsAndSelectOptions(data.get("Manage_Web_Grp_Options"));
					cmtLib.searchForaUserAndSelect(data.get("LnameEmailUname"), data.get("contactName"));

					//cmtLib.loginToCMTSearchWebGrpAndUser(data.get("header"), data.get("WebGrp"), data.get("LnameEmailUname"), data.get("ContactName"));

					cmtLib.setPermissionsToDisable(data.get("Menu_Name"),data.get("User_Permission"));
					cmtLib.setPermissions(data.get("Menu_Name"),data.get("Enable_Purchasing_Popup"));
					cmtLib.clickOnloginAs();
					switchToChildWindow();
					cmtLib.loginVerification("User - "+data.get("contactName"));
					//cartLib.verifyCartIsEmpty();
					commonLib.searchProduct(data.get("Search_Item"));
					//commonLib.addFirstDisplyedItemToCartAndVerify();
					prodInfoLib.verifyTheManufacturerNumberInProductDetailsPage(data.get("Search_Item"));
					search.increaseQuantity(data.get("quantity"));

					//String partNumber1=cartLib.getPartNumber();

					commonLib.addToCartAndVerify();
					canadaLib.continueToCheckout();

					String totalAmountMarketPriceoff=shipbLib.getTotalAmountInCart(data.get("Total"));
					commonLib.clickLogOutLink(data.get("Logout_Header"));

					cmtLib.navigateBackToCMT();
					cmtLib.setPermissions(data.get("Menu_Name"),data.get("User_Permission"));
					cmtLib.clickOnloginAs();
					switchToChildWindow();
					cmtLib.loginVerification("User - "+data.get("contactName"));

					commonLib.searchProduct(data.get("Search_Item"));
					prodInfoLib.verifyTheManufacturerNumberInProductDetailsPage(data.get("Search_Item"));
					clickMorePricesAvilableInProductInfo();
					clickOnOpenMarketPrice();
					clickOnAddToCartInAllContractPrices();
					//cartLib.cartBasicsIPS_verifyPermissionAtUserLevel();


					/*String partNumber2=cartLib.getPartNumber();

					commonLib.addToCartAndVerify();
					canadaLib.continueToCheckout();*/


					/*commonLib.searchProduct(partNumber1);
					commonLib.clickCart();
					String totalAmountMarketPriceOn=shipbLib.getTotalAmountInCart(data.get("Total"));
					if(!totalAmountMarketPriceoff.equals(totalAmountMarketPriceOn))
					{
						reporter.SuccessReport("Us commdity price and open market price", "are not equal","");
					}
					else {
						reporter.failureReport("Us commdity price and open market price", "are equal","");
					}*/

					commonLib.clickLogOutLink(data.get("Logout_Header"));
					cmtLib.navigateBackToCMT();
					cmtLib.clickOnLogout();
					//commonLib.clickLogOutLink(data.get("Logout_Header"));
					cmtLib.loginToCMT(data.get("header"));
					cmtLib.searchForWebGroup(data.get("WebGrp2"));
					cmtLib.clickOnTheWebGroup(data.get("WebGrp_Name2"));
					cmtLib.hoverOnManageWebGroupsAndSelectOptions(data.get("Manage_Web_Grp_Options"));
					cmtLib.searchForaUserAndSelect(data.get("LnameEmailUname2"), data.get("ContactName2"));


					/*cmtLib.hoverOverMasterGroupAndSelectChangeGrp();
				    cmtLib.searchForWebGroup(data.get("WebGrp2"));
				    cmtLib.manageUsers();
				    cmtLib.searchUsers(data.get("LnameEmailUname2"));
				    cmtLib.verifyUserandClick(data.get("ContactName2"));*/

					cmtLib.clickOnloginAs();
					switchToChildWindow();
					cmtLib.loginVerification("User - "+data.get("ContactName2"));

					search.searchInHomePage(data.get("Search_Item1"));
					search.verifyBreadCrumbInSearchResultsPage(data.get("Search_Item1"));
					Thread.sleep(10000);
					cartLib.verifyDefaultContractinProductDisplay();

					//commonLib.addToCartAndVerify();

					//canadaLib.continueToCheckout();
					//cartLib.verifyDefaultContractInCart();
					search.searchInHomePage(data.get("SearchItem2"));
					search.verifyBreadCrumbInSearchResultsPage(data.get("SearchItem2"));
					prodInfoLib.selectFirstProductAddToCartAndVerifyCart();
					Thread.sleep(5000);
					cartLib.verifyDefaultContractInCart();

					search.searchInHomePage(data.get("Search_Item1"));
					search.verifyBreadCrumbInSearchResultsPage(data.get("Search_Item1"));

					cartLib.clickMorePricesAvilableInSearchResultPage();
					//search.clickOnMorePrices();
					search.allContractPricesPopup();
					search.verifyDefaultUSContractInAllContractPricesPopup("checked");
					// STATE OF MINNESOTA - PC HARDWARE, & SERVICES-
					//search.selectContractOnAllContractPricesPopup(data.get("Contarct_Name1"));

					//cartLib.clickOnOpenMarketPrice();
					//cartLib.clickOnAddToCartInAllContractPrices();
					search.increaseQuantity(data.get("quantity2"));
					cartLib.clickOnAddToCartInAllContractPrices();
					orderLib.continueToCheckOutOnAddCart();
					// contract verification in cart page
					prodInfoLib.verifyContractInCartScreen(data.get("Contarct_Name1"));
					prodInfoLib.verifyCartPageAndPartDetails();
					search.selectNewcontract(data.get("Contarct_Name2"));

					search.searchInHomePage(data.get("Search_Item1"));
					search.verifyBreadCrumbInSearchResultsPage(data.get("Search_Item1"));
					prodInfoLib.selectFirstProductAddToCartAndVerifyCart();
					//cartLib.verifyDefaultContractInCart();
					prodInfoLib.verifyContractInCartScreen(data.get("Contarct_Name2"));
					prodInfoLib.verifyContract2InCartScreen(data.get("Contarct_Name1"));

					commonLib.emptyCartAndVerify();

					commonLib.clickLogOutLink(data.get("Logout_Header"));
					navigateTo(data.get("URL"));

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
			ReportStatus.fnUpdateResultStatus("CartBasicIPS", "Tc_CRT02", ReportStatus.strMethodName, 1, browser);
			throw new RuntimeException(e);
		}
		finally {
			ReportControl.fnEnableJoin();
			ReportStatus.fnUpdateResultStatus("CartBasicIPS", "Tc_CRT02", ReportStatus.strMethodName, counter, browser);
			fnCloseTest();
			ReportControl.fnNextTestJoin(nextTestJoin);
		}
	}
}


