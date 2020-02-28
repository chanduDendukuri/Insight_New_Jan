package com.insight.WebTest.Canada;

import com.insight.Lib.CanadaLib;
import com.insight.Lib.*;
import com.insight.accelerators.ReportControl;
import com.insight.accelerators.TestEngineWeb;
import com.insight.googledrive.ReportStatus;
import com.insight.utilities.TestUtil;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.util.Hashtable;

public class CAN07_CartBasicTest extends CanadaLib{

	CommonLib commonLib = new CommonLib();
	CMTLib cmtLib = new CMTLib();
	CanadaLib canadaLib = new CanadaLib();
	SearchLib search = new SearchLib();
	ProductDisplayInfoLib prodInfoLib = new ProductDisplayInfoLib();
	EndUserFeaturesLib end = new EndUserFeaturesLib();
	SLPLib slp = new SLPLib();
	LineLevelInfoLib line = new LineLevelInfoLib();
	OrderLib order = new OrderLib();
	CommonCanadaLib ccp = new CommonCanadaLib();
	CartLib cartLib= new CartLib();
	// #############################################################################################################
	// #       Name of the Test         :  CAN07_CartBasicTest
	// #       Migration Author         :  Cigniti Technologies
	// #
	// #       Date of Migration        : October 2019
	// #       DESCRIPTION              : This method is to verify MenuSearch for canada
	// #       Parameters               : StartRow ,EndRow , nextTestJoin
	// #
	// ###############################################################################################################
	@Parameters({ "StartRow", "EndRow", "nextTestJoin" })
	@Test
	public void TC_CAN07(int StartRow,String EndRow, boolean nextTestJoin) throws Throwable {

		int counter = 0;
		try {
			int intStartRow = StartRow;
			int intEndRow = ReportControl.fnGetEndRowCunt(EndRow, "CAN07_CartBasic", TestDataInsight, "Canada");
			for (int intCounter = intStartRow; intCounter <= intEndRow; intCounter++) {
				try {
					counter = intCounter;
					fnOpenTest();
					ReportStatus.fnDefaultReportStatus();
					ReportControl.intRowCount = intCounter;
					Hashtable<String, String> data = TestUtil.getDataByRowNo("CAN07_CartBasic", TestDataInsight,
							"Canada", intCounter);
					TestEngineWeb.reporter.initTestCaseDescription("CartBasic");








					cmtLib.loginToCMT(data.get("Header"));

					cmtLib.searchForWebGroup(data.get("WebGrp"));
					cmtLib.clickOnTheWebGroup(data.get("WebGrp_Name"));

					cmtLib.hoverOnManageWebGroupsAndSelectOptions(data.get("Manage_Web_Grp_Options1"));
					cmtLib.searchForaUserAndSelect(data.get("LnameEmailUname"), data.get("ContactName"));
					cmtLib.loginAsAdminCMT();

					cmtLib.loginVerification(data.get("ContactName"));

					commonLib.searchProduct(data.get("SearchItem1"));
					prodInfoLib.verifyTheManufacturerNumberInProductDetailsPage(data.get("SearchItem1"));


					search.increaseQuantity(data.get("quantity"));





					commonLib.addToCartAndVerify();
					canadaLib.continueToCheckout();

					assertTrue(cartLib.verifyCartPageAvailablity(),"Cart Page loaded");



					commonLib.searchProduct(data.get("SearchItem2"));

					search.verifyBreadCrumbInSearchResultsPage(data.get("SearchItem2"));
					prodInfoLib.getPartNumberInSearchResultsPage();
					ccp.clickOnAddToCartButtonUnderProductDynamically(data.get("quantity"));
					String man3=cartLib.getPartNumber();
					cartLib.getPartNumber();

					canadaLib.continueToCheckout();


					prodInfoLib.enterQuantityForProductsInViewCartPage(data.get("Quantity"));
					reporter.SuccessReport("Update Quantity" ,"Quantity was update with ",data.get("Quantity"));
					commonLib.clickOnUpdateLinkInViewCartPage(data.get("Quantity"));

					prodInfoLib.enterQuantityForProductsInViewCartPage(data.get("quan"));
					assertTrue(!commonLib.clickOnUpdateLinkInViewCartPage(data.get("quan")),"Update button is not visible");

					prodInfoLib.enterQuantityForProductsInViewCartPage(data.get("quant"));
					assertTrue(!commonLib.clickOnUpdateLinkInViewCartPage(data.get("quant")),"Update button is not visible");
					ccp.clickOnDeleteIconBasedUpOnProductNumberSearch(data.get("SearchItem1"));
					ccp.clickOnDeleteIconBasedUpOnProductNumberSearch(man3);
					commonLib.verifyCartIsEMpty();
					commonLib.searchProduct(data.get("SearchItem2"));
					search.verifyBreadCrumbInSearchResultsPage(data.get("SearchItem2"));
					prodInfoLib.getPartNumberInSearchResultsPage();
					search.increaseQuantity(data.get("quantity"));

					ccp.clickOnAddToCartButtonUnderProductDynamically(data.get("quantity"));




					slp.verifyProccedToCheckOutbuttonExists();

					canadaLib.continueToCheckout();

					String s2=Boolean.toString(cartLib.verifyCartPageAvailablity());
					assertTrue(cartLib.verifyCartPageAvailablity(),"Cart Page loaded");
					ccp.clickOnEmptyCart();
					commonLib.verifyCartIsEMpty();
					commonLib.clickLogOutLink(data.get("Logout_Header"));
					cmtLib.navigateBackToCMT();
					cmtLib.clickOnLogout();

					cmtLib.loginToCMT(data.get("Header"));
					cmtLib.searchForWebGroup(data.get("WebGrp1"));
					cmtLib.clickOnTheWebGroup(data.get("WebGrp_Name1"));
					cmtLib.disbaleOverRidePaymentOption();
					end.clickUpdateButton();
					end.verifyupdateSuccessMessage();

					cmtLib.hoverOnManageWebGroupsAndSelectOptions(data.get("Manage_Web_Grp_Options1"));
					cmtLib.searchForaUserAndSelect(data.get("userName1"), data.get("ContactName1"));
					commonLib.clickRolesAndPermissionsAtUserLevel();
					cmtLib.setPermissionsToDisable(data.get("Menu_Name"), data.get("Set_Permission"));

					cmtLib.loginAsAdminCMT();
					cmtLib.loginVerification(data.get("ContactName1"));
					commonLib.searchProduct(data.get("SearchItem2"));
					search.verifyBreadCrumbInSearchResultsPage(data.get("SearchItem2"));
					prodInfoLib.getPartNumberInSearchResultsPage();
					ccp.clickOnAddToCartButtonUnderProductDynamically(data.get("quantity"));

					String man31=cartLib.getPartNumber();


					canadaLib.continueToCheckout();
					prodInfoLib.verifyCartPageAndPartDetailsForRecentlyItemDynamicaly(man31);

					String s4=Boolean.toString(cartLib.verifyCartPageAvailablity());
					if(cartLib.verifyCartPageAvailablity())
					{
						reporter.SuccessReport("Cart Landing Page", "Availability of Cart Landing Page is ",s4 );
					}
					else{
						reporter.failureReport("Cart Landing Page", "Availability of Cart Landing Page is ",s4,driver );
					}
					slp.verifyProccedToCheckOutbuttonExists();
					commonLib.clickLogOutLink(data.get("Logout_Header"));
					cmtLib.navigateBackToCMT();





					cmtLib.clickOnRolesAndPermissionsAndSetPermission(data.get("Menu_Name"), data.get("Set_Permission"));

					cmtLib.loginAsAdminCMT();
					cmtLib.loginVerification(data.get("ContactName1"));
					commonLib.searchProduct(data.get("SearchItem2"));
					search.verifyBreadCrumbInSearchResultsPage(data.get("SearchItem2"));
					prodInfoLib.getPartNumberInSearchResultsPage();
					ccp.clickOnAddToCartButtonUnderProductDynamically(data.get("quantity"));

					String man4=cartLib.getPartNumber();


					canadaLib.continueToCheckout();

					String s5=Boolean.toString(cartLib.verifyCartPageAvailablity());
					if(cartLib.verifyCartPageAvailablity())
					{
						reporter.SuccessReport("Cart Landing Page", "Availability of Cart Landing Page is ",s5 );
					}
					else{
						reporter.failureReport("Cart Landing Page", "Availability of Cart Landing Page is ",s5,driver );
					}
					Thread.sleep(3000);
					slp.verifyProceedToCheckOutButton();
					cmtLib.clickOnLogoutlink();
					System.out.println("Test completed");
				} catch (Exception e) {
					ReportStatus.blnStatus = false;
					gErrorMessage = e.getClass().getSimpleName();
					gTestStatus = false;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			ReportStatus.blnStatus = false;
			gErrorMessage = e.getClass().getSimpleName();;
			gTestStatus = false;
			ReportStatus.fnUpdateResultStatus("CartBasic", "TC_CAN05", ReportStatus.strMethodName, 1, browser);
			throw new RuntimeException(e);
		}
		finally {
			ReportControl.fnEnableJoin();
			ReportStatus.fnUpdateResultStatus("CartBasic", "TC_CAN05", ReportStatus.strMethodName, counter, browser);
			fnCloseTest();
			ReportControl.fnNextTestJoin(nextTestJoin);
		}
	}
}