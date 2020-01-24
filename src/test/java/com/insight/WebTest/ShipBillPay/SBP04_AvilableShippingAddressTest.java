package com.insight.WebTest.ShipBillPay;

import java.util.Hashtable;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.insight.Lib.CMTLib;
import com.insight.Lib.CanadaLib;
import com.insight.Lib.CartLib;
import com.insight.Lib.CommonLib;
import com.insight.Lib.OrderLib;
import com.insight.Lib.SearchLib;
import com.insight.Lib.ShipBillPayLib;
import com.insight.accelerators.ReportControl;
import com.insight.accelerators.TestEngineWeb;
import com.insight.googledrive.ReportStatus;
import com.insight.utilities.TestUtil;

public class SBP04_AvilableShippingAddressTest extends ShipBillPayLib{
	// #############################################################################################################
		// # Name of the Test : SBP04_AvilableShippingAddress
		// # Migration Author : Cigniti Technologies
		// #
		// # Date of Migration : August 2019
		// # DESCRIPTION : This method is to perform Basic Cart operations.
		// # Parameters : StartRow ,EndRow , nextTestJoin
		// #
		// ###############################################################################################################
		@Parameters({ "StartRow", "EndRow", "nextTestJoin" })
		@Test
		public void Tc_SBP04(int StartRow, String EndRow, boolean nextTestJoin) throws Throwable {
			int counter=0;
			try {
			int intStartRow = StartRow;
			int intEndRow = ReportControl.fnGetEndRowCunt(EndRow, "SBP04_AvilableShippingAddress", TestDataInsight,
					"Ship_Bill_Pay");
			for (int intCounter = intStartRow; intCounter <= intEndRow; intCounter++) {
				try {
				fnOpenTest();
				ReportStatus.fnDefaultReportStatus();
				ReportControl.intRowCount = intCounter;
				Hashtable<String, String> data = TestUtil.getDataByRowNo("SBP04_AvilableShippingAddress", TestDataInsight,
						"Ship_Bill_Pay", intCounter);
				TestEngineWeb.reporter
						.initTestCaseDescription("AvilableShippingAddress");
				reporter.SuccessReport("Iteration Number : ",
						"**************Iteration Number::  " + intCounter + " For:: " + data.get("LoginName") + " ::and:: "
								+ data.get("Password") + " To Validate::" + data.get("errorMessage") + "  **************",
						"");
				CommonLib commonLib = new CommonLib();
				CMTLib cmtLib = new CMTLib();
				CartLib cartLib = new CartLib();
				OrderLib orderLib = new OrderLib();
				ShipBillPayLib shipbLib = new ShipBillPayLib();
				CanadaLib canadaLib = new CanadaLib();
				SearchLib searchLib= new SearchLib();
				// login-1
				cmtLib.loginToCMT(data.get("Header"));
				cmtLib.searchForWebGroup(data.get("WebGrp"));
				cmtLib.clickOnTheWebGroup(data.get("WebGrp_Name"));
				cmtLib.hoverOnManageWebGroupsAndSelectOptions(data.get("Manage_Web_Grp_Options"));
				cmtLib.searchForaUserAndSelect(data.get("LnameEmailUname"), data.get("ContactName"));
				// --------RoleandPermission "change_ship_to;ON"
				cmtLib.setPermissions(data.get("Menu_Name"), data.get("Set_Permission"));
				cmtLib.setPermissions(data.get("Menu_Name"), data.get("Set_Permission5"));
				cmtLib.setPermissions(data.get("Menu_Name"), data.get("Set_Permission6"));
				cmtLib.setPermissionsToDisableWithoutReport(data.get("Set_Permission2"));
				// Uncheck all shipping options
				cmtLib.clickCheckOutSettings(data.get("Check_out_Settings"));
				cmtLib.selectOptionInCheckoutSettings(data.get("Shipping Addresses"));
				shipbLib.SelectAllLinkedaddresses(data.get("Linkuseraddresses"));
				verifyNoDefaultAddress();
				cmtLib.selectOptionInCheckoutSettings(data.get("Additional_Notifiaction"));
				enterEmail(data.get("Email"));
				cmtLib.loginAsAdminCMT();
				// add product to cart
				commonLib.searchProduct(data.get("Search_Item"));
				searchLib.verifyBreadCrumbInSearchResultsPage(data.get("Search_Item"));
				commonLib.addFirstDisplyedItemToCartAndVerify();
				canadaLib.continueToCheckout();
				// proceed to checkout
				orderLib.proceedToCheckout();
				cartLib.clickOnContinueButtonInAddInformtion();
				orderLib.clickContinueOnLineLevelInfo();
				Thread.sleep(5000);
				// verify shipping address
				VerifyDefualtSoldtoAddress(data.get("SoldToAddress"));
				clickstoredAddressandVerify(data.get("storedaddress"),data.get("storedaddress"));
				orderLib.shippingBillPayContinueButton();
				commonLib.clickLogOutLink(data.get("Logout_Header"));
				// login-2
				cmtLib.navigateBackToCMT();
				cmtLib.hoverOverMasterGroupAndSelectChangeGrp();
				cmtLib.searchForWebGroup(data.get("WebGrp"));
				cmtLib.clickOnTheWebGroup(data.get("WebGrp_Name"));
				cmtLib.hoverOnManageWebGroupsAndSelectOptions(data.get("Manage_Web_Grp_Options"));
				cmtLib.searchForaUserAndSelect(data.get("LnameEmailUname"), data.get("ContactName"));
				cmtLib.clickCheckOutSettings(data.get("Check_out_Settings"));
				cmtLib.selectOptionInCheckoutSettings(data.get("Shipping Addresses"));
				shipbLib.SelectAllLinkedaddresses(data.get("Linkuseraddresses"));
				verifyNoDefaultAddress();
				// shipping options
				// Check all shipping options CheckUnCheckCheckBoxes("ON");
				shipbLib.SelectAllLinkedaddresses(data.get("Linkuseraddresses1"));
				shipbLib.SelectdefualtAddress();
				cmtLib.loginAsAdminCMT();
				// add to cart
				commonLib.searchProduct(data.get("Search_Item"));
				searchLib.verifyBreadCrumbInSearchResultsPage(data.get("Search_Item"));
				commonLib.addFirstDisplyedItemToCartAndVerify();
				canadaLib.continueToCheckout();
				orderLib.proceedToCheckout();
				cartLib.clickOnContinueButtonInAddInformtion();
				orderLib.clickContinueOnLineLevelInfo();
				VerifyDefualtSoldtoAddress(data.get("SoldToAddress2"));
				clickstoredAddressandVerify(data.get("Defualtaddress"),data.get("Defualtaddress"));
				orderLib.shippingBillPayContinueButton();
				commonLib.clickLogOutLink(data.get("Logout_Header"));
				// login-3
				cmtLib.navigateBackToCMT();
				cmtLib.hoverOverMasterGroupAndSelectChangeGrp();
				cmtLib.searchForWebGroup(data.get("WebGrp"));
				cmtLib.clickOnTheWebGroup(data.get("WebGrp_Name"));
				cmtLib.hoverOnManageWebGroupsAndSelectOptions(data.get("Manage_Web_Grp_Options"));
				cmtLib.searchForaUserAndSelect(data.get("LnameEmailUname"), data.get("ContactName"));
				cmtLib.setPermissions(data.get("Menu_Name"), data.get("Set_Permission2"));
				cmtLib.setPermissions(data.get("Menu_Name"), data.get("Set_Permission3"));
				cmtLib.setPermissions(data.get("Menu_Name"), data.get("Set_Permission4"));
				cmtLib.loginAsAdminCMT();
				// add to cart
				commonLib.searchProduct(data.get("Search_Item"));
				searchLib.verifyBreadCrumbInSearchResultsPage(data.get("Search_Item"));
				commonLib.addFirstDisplyedItemToCartAndVerify();
				canadaLib.continueToCheckout();
				orderLib.proceedToCheckout();
				cartLib.clickOnContinueButtonInAddInformtion();
				orderLib.clickContinueOnLineLevelInfo();
				String companyname3="IUS Created Shipping Adress Canada"+"_"+getRandomNumeric(4);
				shipbLib.AddNewshippingAddressWithcountry(data.get("link"),companyname3, data.get("street"),
						data.get("city"), data.get("zipcode"), data.get("state"), data.get("Country"),data.get("Attention"));
				Thread.sleep(5000);
				Thread.sleep(3000);
				scrollUp();
				clickEdit();
				// Search for the Account Name
				shipbLib.VerifyCreatedAddress(companyname3);
				clickstoredAddressandCancle(companyname3);
				commonLib.clickLogOutLink(data.get("Logout_Header"));
				// login-4
				cmtLib.navigateBackToCMT();
				cmtLib.hoverOverMasterGroupAndSelectChangeGrp();
				cmtLib.searchForWebGroup(data.get("WebGrp"));
				cmtLib.clickOnTheWebGroup(data.get("WebGrp_Name"));
				cmtLib.hoverOnManageWebGroupsAndSelectOptions(data.get("Manage_Web_Grp_Options"));
				cmtLib.searchForaUserAndSelect(data.get("LnameEmailUname"), data.get("ContactName"));
				cmtLib.setPermissions(data.get("Menu_Name"), data.get("Set_Permission3"));
				cmtLib.loginAsAdminCMT();
				// add to cart
				commonLib.searchProduct(data.get("Search_Item"));
				searchLib.verifyBreadCrumbInSearchResultsPage(data.get("Search_Item"));
				commonLib.addFirstDisplyedItemToCartAndVerify();
				canadaLib.continueToCheckout();
				orderLib.proceedToCheckout();
				cartLib.clickOnContinueButtonInAddInformtion();
				orderLib.clickContinueOnLineLevelInfo();
				String companyname="IUS Created Shipping Adress Canada"+"_"+getRandomNumeric(4);
				shipbLib.AddNewshippingAddressWithcountry(data.get("link"),companyname, data.get("street2"),
						data.get("city2"), data.get("zipcode2"), data.get("state2"), data.get("Country2"),data.get("Attention"));
				Thread.sleep(5000);
				scrollUp();
				clickEdit();
				shipbLib.VerifyCreatedAddress(companyname);
				clickstoredAddressandCancle(companyname);
				String companyname1="IUS Created Shipping Adress Canada"+"_"+getRandomNumeric(4);
				shipbLib.AddNewshippingAddressWithcountry(data.get("link"),companyname1, data.get("street2"),
						data.get("city2"), data.get("zipcode2"), data.get("state2"), data.get("Country2"),data.get("Attention"));
				Thread.sleep(5000);
				scrollUp();
				clickEdit();
				shipbLib.VerifyCreatedAddress(companyname1);
				clickstoredAddressandCancle(companyname1);
				commonLib.clickLogOutLink(data.get("Logout_Header"));
				// permissions unchek
				cmtLib.navigateBackToCMT();
				cmtLib.hoverOverMasterGroupAndSelectChangeGrp();
				cmtLib.searchForWebGroup(data.get("WebGrp"));
				cmtLib.clickOnTheWebGroup(data.get("WebGrp_Name"));
				cmtLib.setCustomerLevelPermissionsON(data.get("WebGrpPermission"));
				cmtLib.hoverOnManageWebGroupsAndSelectOptions(data.get("Manage_Web_Grp_Options"));
				cmtLib.searchForaUserAndSelect(data.get("LnameEmailUname"), data.get("ContactName"));
				cmtLib.setPermissionsToDisable(data.get("Menu_Name"), data.get("Set_Permission2"));
				cmtLib.clickCheckOutSettings(data.get("Check_out_Settings"));
				cmtLib.selectOptionInCheckoutSettings(data.get("Shipping Addresses"));
				shipbLib.SelectAllLinkedaddresses(data.get("Linkuseraddresses1"));
				

				} catch (Exception e) {
					ReportStatus.blnStatus = false;
					//gErrorMessage = e.getMessage();
					System.out.println(e.getMessage());
					gTestStatus = false;
				}
				ReportControl.fnEnableJoin();
				ReportStatus.fnUpdateResultStatus("AvilableShippingAddress", "SBP04", ReportStatus.strMethodName, intCounter,
						browser);
			}
		} catch (Exception e) {
			e.printStackTrace();
			ReportStatus.blnStatus = false;
			//gErrorMessage = e.getMessage();
			gTestStatus = false;
			ReportStatus.fnUpdateResultStatus("AvilableShippingAddress", "SBP04", ReportStatus.strMethodName, 1, browser);
			throw new RuntimeException(e);
		}

			finally {
	        	ReportControl.fnEnableJoin();
				ReportStatus.fnUpdateResultStatus("AvilableShippingAddress", "SBP04", ReportStatus.strMethodName, counter, browser);
				fnCloseTest();
				ReportControl.fnNextTestJoin(nextTestJoin);
			}
	}

}