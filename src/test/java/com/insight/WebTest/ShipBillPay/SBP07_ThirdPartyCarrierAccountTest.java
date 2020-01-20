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

public class SBP07_ThirdPartyCarrierAccountTest extends ShipBillPayLib{
	// #############################################################################################################
		// # Name of the Test : SBP07_ThirdPartyCarrierAccount
		// # Migration Author : Cigniti Technologies
		// #
		// # Date of Migration : August 2019
		// # DESCRIPTION : This method is to perform Basic Cart operations.
		// # Parameters : StartRow ,EndRow , nextTestJoin
		// #
		// ###############################################################################################################
		@Parameters({ "StartRow", "EndRow", "nextTestJoin" })
		@Test
		public void Tc_SBP07(int StartRow, String EndRow, boolean nextTestJoin) throws Throwable {
			int counter=0;
			try {
			int intStartRow = StartRow;
			int intEndRow = ReportControl.fnGetEndRowCunt(EndRow, "SBP07_ThirdPartyCarrierAccount", TestDataInsight,
					"Ship_Bill_Pay");
			for (int intCounter = intStartRow; intCounter <= intEndRow; intCounter++) {
				try {
				fnOpenTest();
				ReportStatus.fnDefaultReportStatus();
				ReportControl.intRowCount = intCounter;
				Hashtable<String, String> data = TestUtil.getDataByRowNo("SBP07_ThirdPartyCarrierAccount", TestDataInsight,
						"Ship_Bill_Pay", intCounter);
				TestEngineWeb.reporter
						.initTestCaseDescription("ThirdPartyCarrierAccount");
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
				SearchLib searchLib=new SearchLib();
				//Login
				cmtLib.loginToCMT(data.get("Header"));
				cmtLib.searchForWebGroup(data.get("WebGrp"));
				cmtLib.clickOnTheWebGroup(data.get("WebGrp_Name"));
				cmtLib.setCustomerLevelPermissionsOFF(data.get("Customer_Permissions_OFF"));
				cmtLib.hoverOnManageWebGroupsAndSelectOptions(data.get("Manage_Web_Grp_Options"));
				cmtLib.searchForaUserAndSelect(data.get("LnameEmailUname"), data.get("ContactName"));
				// set roles and permissions
				cmtLib.setPermissions(data.get("Menu_Name"), data.get("Set_Permission"));
				cmtLib.setPermissions(data.get("Menu_Name"), data.get("SetPermission3_Login2"));
				cmtLib.setPermissions(data.get("Menu_Name"), data.get("SetPermission2_Login2"));
				cmtLib.setPermissions(data.get("Menu_Name"), data.get("SetPermission1_Login2"));
				cmtLib.setPermissionsToDisable(data.get("Menu_Name"), data.get("SetPermission2_Login2"));
				cmtLib.setPermissionsToDisable(data.get("Menu_Name"), data.get("SetPermission1_Login2"));
				// checkout settings
				commonLib.clickCheckOutSettings(data.get("Check_out_Settings"));
				commonLib.selectOptionInCheckoutSettings(data.get("Shipping_Options"));
				commonLib.selectDefaultShippingOptionInCheckoutSettings(data.get("Default_Shipping_Option"));
				commonLib.clickOnUpdateButtonInUserSettings();
				cmtLib.loginAsAdminCMT();
				Thread.sleep(3000);
				// add item to cart
				commonLib.searchProduct(data.get("Search_Item"));
				commonLib.addFirstDisplyedItemToCartAndVerify();
				canadaLib.continueToCheckout();
				Thread.sleep(3000);
				orderLib.proceedToCheckout();
				// click continue in place order page
				cartLib.clickOnContinueButtonInAddInformtion();
				orderLib.clickContinueOnLineLevelInfo();
				orderLib.shippingBillPayContinueButton();
				shipbLib.VeifyNoCarrier();
				orderLib.shippingOptionsCarrierSelection();
				orderLib.shippingBillPayContinueButton();// billing address continue
				selectPaymentInfoMethodCreditCard(data.get("PONumber"),data.get("POReleaseNumber"));
				orderLib.clickOnReviewOrderButton();
				Thread.sleep(3000);
				shipbLib.Verifyshippingcarrier(data.get("carrierName"));
				commonLib.clickLogOutLink(data.get("Logout_Header"));
				//2nd Order
				cmtLib.navigateBackToCMT();
				cmtLib.hoverOverMasterGroupAndSelectChangeGrp();
				cmtLib.searchForWebGroup(data.get("WebGrp"));
				cmtLib.clickOnTheWebGroup(data.get("WebGrp_Name"));
				cmtLib.hoverOnManageWebGroupsAndSelectOptions(data.get("Manage_Web_Grp_Options"));
				cmtLib.searchForaUserAndSelect(data.get("LnameEmailUname"), data.get("ContactName"));
				// set roles and permissions
				cmtLib.setPermissions(data.get("Menu_Name"), data.get("SetPermission3_Login2"));
				cmtLib.setPermissions(data.get("Menu_Name"), data.get("SetPermission1_Login2"));
				cmtLib.setPermissions(data.get("Menu_Name"), data.get("SetPermission2_Login2"));
				// checkout settings
				commonLib.clickCheckOutSettings(data.get("Check_out_Settings"));
				commonLib.selectOptionInCheckoutSettings(data.get("Shipping_Options"));
				cmtLib.clickDesignatedShippingOptions(data.get("Carrier"));
				Thread.sleep(3000);
				//verification
				verifyCarrier(data.get("DefualtCarrier"));
				cmtLib.clickupdateatDefaultShippingOption();
				cmtLib.loginAsAdminCMT();
				Thread.sleep(3000);
				commonLib.searchProduct(data.get("Search_Item"));
				searchLib.verifyBreadCrumbInSearchResultsPage(data.get("Search_Item"));
				commonLib.addFirstDisplyedItemToCartAndVerify();
				canadaLib.continueToCheckout();
				orderLib.proceedToCheckout();
				cartLib.clickOnContinueButtonInAddInformtion();
				orderLib.clickContinueOnLineLevelInfo();
				orderLib.shippingBillPayContinueButton();
				// SelectcarrierAtBillmycarrier
				shipbLib.SelectcarrierAtBillmycarrier(data.get("DefCarrier"));
				// continue button shipping Options
				orderLib.shippingOptionsCarrierSelection();
				// account num verify
				shipbLib.verifyCarrierAccountinplaceorder();
				orderLib.billingAddressContinueButton();// billing address continue
				selectPaymentInfoMethodCreditCard(data.get("PONumber"),data.get("POReleaseNumber"));
				orderLib.clickOnReviewOrderButton();
				Thread.sleep(3000);
				// logout
				commonLib.clickLogOutLink(data.get("Logout_Header"));
				cmtLib.navigateBackToCMT();
				cmtLib.hoverOverMasterGroupAndSelectChangeGrp();
				cmtLib.searchForWebGroup(data.get("WebGrp"));
				cmtLib.clickOnTheWebGroup(data.get("WebGrp_Name"));
				cmtLib.setCustomerLevelPermissionsOFF(data.get("Customer_Permissions_OFF"));
				cmtLib.hoverOnManageWebGroupsAndSelectOptions(data.get("Manage_Web_Grp_Options"));
				cmtLib.searchForaUserAndSelect(data.get("LnameEmailUname"), data.get("ContactName"));
				commonLib.clickCheckOutSettings(data.get("Check_out_Settings"));
				commonLib.selectOptionInCheckoutSettings(data.get("Shipping_Options"));
				commonLib.selectDefaultShippingOptionInCheckoutSettings(data.get("Default_Shipping_Option"));
				commonLib.clickOnUpdateButtonInUserSettings();
				cmtLib.loginAsAdminCMT();
				Thread.sleep(3000);
				commonLib.searchProduct(data.get("Search_Item"));
				commonLib.addFirstDisplyedItemToCartAndVerify();
				canadaLib.continueToCheckout();
				orderLib.proceedToCheckout();
				Thread.sleep(3000);
				// click continue in place order page
				cartLib.clickOnContinueButtonInAddInformtion();
				orderLib.clickContinueOnLineLevelInfo();
				orderLib.shippingBillPayContinueButton();
				shipbLib.VeifyChoosenCarrierMsg();
				Thread.sleep(3000);
				shipbLib.Verifycarrieroptionsdiabled(data.get("disabledcarrier"));
				orderLib.selectShippingOptionsCarrierSection();
				verifyThiredPartyCarrierMsg();
			    commonLib.clickLogOutLink(data.get("Logout_Header"));
				

				} catch (Exception e) {
					ReportStatus.blnStatus = false;
					//gErrorMessage = e.getMessage();
					System.out.println(e.getMessage());
					gTestStatus = false;
				}
				
			}
		} catch (Exception e) {
			e.printStackTrace();
			ReportStatus.blnStatus = false;
			//gErrorMessage = e.getMessage();
			gTestStatus = false;
			ReportStatus.fnUpdateResultStatus("ThirdPartyCarrierAccount", "SBP07", ReportStatus.strMethodName, 1, browser);
			throw new RuntimeException(e);
		}

			finally {
	        	ReportControl.fnEnableJoin();
				ReportStatus.fnUpdateResultStatus("ThirdPartyCarrierAccount", "SBP07", ReportStatus.strMethodName, counter, browser);
				fnCloseTest();
				ReportControl.fnNextTestJoin(nextTestJoin);
			}
	}

}
