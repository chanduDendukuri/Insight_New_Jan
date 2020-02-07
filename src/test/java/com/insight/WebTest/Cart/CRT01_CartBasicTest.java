package com.insight.WebTest.Cart;

import java.util.Hashtable;

import com.insight.Lib.*;
import org.apache.logging.log4j.core.config.Order;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.insight.accelerators.ReportControl;
import com.insight.accelerators.TestEngineWeb;
import com.insight.googledrive.ReportStatus;
import com.insight.utilities.TestUtil;

public class CRT01_CartBasicTest extends CartLib {
	CommonLib CommonLib = new CommonLib();
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
				//25/Jan/2020 -- lakshmi review comments	
					cmtLib.loginToCMT(data.get("Header"));
					
					cmtLib.searchForWebGroup(data.get("WebGrp"));
					cmtLib.clickOnTheWebGroup(data.get("WebGrp_Name"));
					//cmtLib.hoverOnManageWebGroupsAndSelectOptions(data.get("Manage_Web_Grp_Options"));
					cmtLib.hoverOnManageWebGroupsAndSelectOptions(data.get("Manage_Web_Grp_Options1"));
					cmtLib.searchForaUserAndSelect(data.get("LnameEmailUname"), data.get("ContactName"));
					cmtLib.loginAsAdminCMT();
					cmtLib.loginVerification(data.get("ContactName"));
					
					CommonLib.searchProduct(data.get("SearchItem1"));
					prodInfoLib.verifyTheManufacturerNumberInProductDetailsPage(data.get("SearchItem1"));
					search.increaseQuantity(data.get("quantity"));

					CommonLib.addToCartAndVerify();
					canadaLib.continueToCheckout();
//adding review commentsString s1=Boolean.toString(verifyCartPageAvailablity());
					String s1=Boolean.toString(verifyCartPageAvailablity());
					if(verifyCartPageAvailablity())
					{
						reporter.SuccessReport("Cart Landing Page", "Availability of Cart Landing Page is ",s1 );
					}
					else{
						reporter.failureReport("Cart Landing Page", "Availability of Cart Landing Page is ",s1,driver );
					}
					CommonLib.searchProduct(data.get("SearchItem2"));
					prodInfoLib.verifyTheManufacturerNumberInProductDetailsPage(data.get("SearchItem2"));
					prodInfoLib.clickOnWarrantiesTabOnProductDetailsPage();
					String manfa=prodInfoLib.getManfNumberFromWarrentiesPage(data.get("index"));
					prodInfoLib.clickOnAddToCartButtonInWarrentiesPage(data.get("index"));
					canadaLib.continueToCheckout();
					prodInfoLib.getProductManfNumber(manfa);
					prodInfoLib.enterQuantityForProductsInViewCartPage(data.get("Quantity"));
					CommonLib.clickOnUpdateLinkInViewCartPage(data.get("Quantity"));
//with zero
					prodInfoLib.enterQuantityForProductsInViewCartPage(data.get("quan"));
					if(!CommonLib.clickOnUpdateLinkInViewCartPage(data.get("quan"))){
						reporter.SuccessReport("Update link","Update link is not enabled with given input ",data.get("quan"));
					}
//characters
					prodInfoLib.enterQuantityForProductsInViewCartPage(data.get("quant"));
					if(!CommonLib.clickOnUpdateLinkInViewCartPage(data.get("quant"))){
						reporter.SuccessReport("Update link","Update link is not enabled with given input ",data.get("quant"));
					}

					prodInfoLib.getSummaryCartDetails();
					prodInfoLib.deleteSelectedProducts();
//Second time searching for same product after deleting
					CommonLib.searchProduct(data.get("SearchItem1"));
					prodInfoLib.verifyTheManufacturerNumberInProductDetailsPage(data.get("SearchItem1"));
					search.increaseQuantity(data.get("quantity"));

					CommonLib.addToCartAndVerify();
					canadaLib.continueToCheckout();
//adding review commentsString s1=Boolean.toString(verifyCartPageAvailablity());
					String s2=Boolean.toString(verifyCartPageAvailablity());
					if(verifyCartPageAvailablity())
					{
						reporter.SuccessReport("Cart Landing Page", "Availability of Cart Landing Page is ",s2 );
					}
					else{
						reporter.failureReport("Cart Landing Page", "Availability of Cart Landing Page is ",s2,driver );
					}
					prodInfoLib.deleteSelectedProducts();
					cmtLib.clickOnLogoutlink();

					//cmtLib.loginToCMTSearchWebGrpAndUser(data.get("Header"), data.get("WebGrp"),data.get("LnameEmailUname"), data.get("ContactName"));

					cmtLib.loginToCMT(data.get("Header"));
					cmtLib.searchForWebGroup(data.get("WebGrp"));
					cmtLib.clickOnTheWebGroup(data.get("WebGrp_Name"));
					cmtLib.disbaleOverRidePaymentOption();
					end.clickUpdateButton();
					end.verifyupdateSuccessMessage();

					cmtLib.hoverOnManageWebGroupsAndSelectOptions(data.get("Manage_Web_Grp_Options1"));
					cmtLib.searchForaUserAndSelect(data.get("userName"), data.get("contract"));
					CommonLib.clickRolesAndPermissionsAtUserLevel();
					cmtLib.setPermissionsToDisable(data.get("menuName"), data.get("userPermission2"));
					cmtLib.setPermissionsToDisableOnly(data.get("userPermission3"));
					cmtLib.loginAsAdminCMT();
					cmtLib.loginVerification(data.get("contract"));
					CommonLib.searchProduct(data.get("SearchItem1"));
					prodInfoLib.verifyTheManufacturerNumberInProductDetailsPage(data.get("SearchItem1"));
					search.increaseQuantity(data.get("quantity"));

					CommonLib.addToCartAndVerify();
					canadaLib.continueToCheckout();
//adding review commentsString s1=Boolean.toString(verifyCartPageAvailablity());
					String s4=Boolean.toString(verifyCartPageAvailablity());
					if(verifyCartPageAvailablity())
					{
						reporter.SuccessReport("Cart Landing Page", "Availability of Cart Landing Page is ",s4 );
					}
					else{
						reporter.failureReport("Cart Landing Page", "Availability of Cart Landing Page is ",s4,driver );
					}
					slp.verifyProccedToCheckOutbuttonExists();
					cmtLib.clickOnLogoutlink();


					cmtLib.loginToCMT(data.get("Header"));

					cmtLib.searchForWebGroup(data.get("WebGrp"));
					cmtLib.clickOnTheWebGroup(data.get("WebGrp_Name"));
					//cmtLib.hoverOnManageWebGroupsAndSelectOptions(data.get("Manage_Web_Grp_Options"));
					cmtLib.hoverOnManageWebGroupsAndSelectOptions(data.get("Manage_Web_Grp_Options1"));
					cmtLib.searchForaUserAndSelect(data.get("userName"), data.get("userName"));
					cmtLib.clickOnRolesAndPermissionsAndSetPermission(data.get("menuName"), data.get("userPermission3"));
					cmtLib.loginAsAdminCMT();
					cmtLib.loginVerification(data.get("contract"));
					CommonLib.searchProduct(data.get("SearchItem1"));
					prodInfoLib.verifyTheManufacturerNumberInProductDetailsPage(data.get("SearchItem1"));
					search.increaseQuantity(data.get("quantity"));

					CommonLib.addToCartAndVerify();
					canadaLib.continueToCheckout();
//adding review commentsString s1=Boolean.toString(verifyCartPageAvailablity());
					Thread.sleep(10000);
					prodInfoLib.verifyCartPageAndPartDetailsForRecentlyItemDynamically(data.get("SearchItem1"));
					String s5=Boolean.toString(verifyCartPageAvailablity());
					if(verifyCartPageAvailablity())
					{
						reporter.SuccessReport("Cart Landing Page", "Availability of Cart Landing Page is ",s5 );
					}
					else{
						reporter.failureReport("Cart Landing Page", "Availability of Cart Landing Page is ",s5,driver );
					}


					line.proceedToCheckout();
					order.clickOnAdditionalInfoContinueButton();
					line.clickOnLinelevelInfoOptionalLink();
					order.clickContinueOnLineLevelInfo();
					canadaLib.verifySBP();
					scrollToBottomWithCordinate("-2000");
					canadaLib.clickOnSideMenuSelectAccountToolOptions(data.get("Tools_Menu"),
							data.get("Manage_Web_Grp_Options"));
					assertTrue(ccp.verifyCompanyStandard(),"Product standard page is available");
					ccp.addToOderInProductStandardsPage();
					assertTrue(cartLib.verifyCartPageAvailablity(),"View Cart page loaded");
					cmtLib.clickOnLogoutlink();

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
