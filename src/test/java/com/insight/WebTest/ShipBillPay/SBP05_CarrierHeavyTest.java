package com.insight.WebTest.ShipBillPay;

import java.util.Hashtable;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.insight.Lib.CMTLib;
import com.insight.Lib.CanadaLib;
import com.insight.Lib.CartLib;
import com.insight.Lib.CommonLib;
import com.insight.Lib.OrderLib;
import com.insight.Lib.ShipBillPayLib;
import com.insight.accelerators.ReportControl;
import com.insight.accelerators.TestEngineWeb;
import com.insight.googledrive.ReportStatus;
import com.insight.utilities.TestUtil;

public class SBP05_CarrierHeavyTest extends ShipBillPayLib{
	
	// #############################################################################################################
		// # Name of the Test : SBP05_CarrierHeavy
		// # Migration Author : Cigniti Technologies
		// #
		// # Date of Migration : August 2019
		// # DESCRIPTION : This method is to perform Basic Cart operations.
		// # Parameters : StartRow ,EndRow , nextTestJoin
		// #
		// ###############################################################################################################
		@Parameters({ "StartRow", "EndRow", "nextTestJoin" })
		@Test
		public void Tc_SBP05(int StartRow, String EndRow, boolean nextTestJoin) throws Throwable {
			int counter=0;
			try {
			int intStartRow = StartRow;
			int intEndRow = ReportControl.fnGetEndRowCunt(EndRow, "SBP05_CarrierHeavy", TestDataInsight, "Ship_Bill_Pay");
			for (int intCounter = intStartRow; intCounter <= intEndRow; intCounter++) {
				
			try {
				fnOpenTest();
				ReportStatus.fnDefaultReportStatus();
				ReportControl.intRowCount = intCounter;
				Hashtable<String, String> data = TestUtil.getDataByRowNo("SBP05_CarrierHeavy", TestDataInsight,
						"Ship_Bill_Pay", intCounter);
				TestEngineWeb.reporter
						.initTestCaseDescription("CarrierHeavy");
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
				cmtLib.loginToCMT(data.get("Header"));
				cmtLib.searchForWebGroup(data.get("WebGrp"));
				cmtLib.clickOnTheWebGroup(data.get("WebGrp_Name"));
				//Customer level permission
				cmtLib.setCustomerLevelPermissionsOFF(data.get("Customer_Permissions_OFF"));
				cmtLib.hoverOnManageWebGroupsAndSelectOptions(data.get("Manage_Web_Grp_Options"));
				cmtLib.searchForaUserAndSelect(data.get("LnameEmailUname"), data.get("ContactName"));
				//set permission user level
				String[] permissions = data.get("Set_Permission").split(",");
				for (i = 0; i < permissions.length; i++) {
					cmtLib.setPermissions(data.get("Menu_Name"), permissions[i]);
				}
				commonLib.clickCheckOutSettings(data.get("Check_out_Settings"));
				commonLib.selectOptionInCheckoutSettings(data.get("Shipping_Options"));
				commonLib.selectDefaultShippingOptionInCheckoutSettings(data.get("Default_Shipping_Option"));
				commonLib.clickOnUpdateButtonInUserSettings();
				cmtLib.clickOnloginAs();
				switchToChildWindow();
				commonLib.searchProduct(data.get("Search_Item"));
				commonLib.addToCartAndVerify();
				canadaLib.continueToCheckout();
				commonLib.updateCartQuantity(data.get("Quantity"));
				orderLib.proceedToCheckout();
				cartLib.clickOnContinueButtonInAddInformtion();
				orderLib.clickContinueOnLineLevelInfo();
				orderLib.shippingBillPayContinueButton();
				//No carrier Preference
				cartLib.verifyCarriersInCheckOut(data.get("Carriers"),data.get("Carrier2"));
				cartLib.DefualtCarrierOption();
				orderLib.shippingOptionsCarrierSelection();
				orderLib.billingAddressContinueButton();
				orderLib.selectPaymentMethod(data.get("Payment_method"));
				shipbLib.verifyShippingCarrierAFterReviewOrder(data.get("Carrier_Verify"));
				String summaryAmount = cartLib.getSummaryAmountInCart();
				orderLib.placeOrderAndVerifyReceiptOrderAndDate(summaryAmount);
				shipbLib.clickOrderDetailsButtonInREceipt();
				shipbLib.verifyShippingCarrierAFterReviewOrder(data.get("Carrier_Verify2"));
				commonLib.clickLogOutLink(data.get("Logout_Header"));
				//Login 
				cmtLib.navigateBackToCMT();
				commonLib.clickCheckOutSettings(data.get("Check_out_Settings"));
				commonLib.selectOptionInCheckoutSettings(data.get("Shipping_Options"));
				//CESV 
				//String[] carriers2 = data.get("Carrier2").split(",");
				//for (i = 0; i < permissions.length; i++) {
					//cmtLib.verifySetPermissions( carriers2[i]);
				//}
				cmtLib.selectParticularDesignatedShippingOption(data.get("UPS"));
				commonLib.clickOnUpdateButtonInUserSettings();
				cmtLib.clickOnloginAs();
				switchToChildWindow();
				commonLib.searchProduct(data.get("Search_Item1"));
				clickStockOnly();
				commonLib.addToCartAndVerify();
				canadaLib.continueToCheckout();
				orderLib.proceedToCheckout();
				cartLib.clickOnContinueButtonInAddInformtion();
				orderLib.clickContinueOnLineLevelInfo();
				orderLib.shippingBillPayContinueButton();
				Thread.sleep(4000);
				cartLib.verifyCarriers(data.get("Carriers1"),data.get("UPS"));
				cartLib.selectCarrier(data.get("PGL"));
				orderLib.shippingOptionsCarrierSelection();
				orderLib.billingAddressContinueButton();
				orderLib.selectPaymentMethod(data.get("Payment_method"));
				shipbLib.verifyShippingCarrierAFterReviewOrder(data.get("Shiping_Carrier_Verify3"));
				String summaryAmount1 = cartLib.getSummaryAmountInCart();
				orderLib.placeOrderAndVerifyReceiptOrderAndDate(summaryAmount1);
				shipbLib.clickOrderDetailsButtonInREceipt();
				shipbLib.verifyShippingCarrierAFterReviewOrder(data.get("Shiping_Carrier_Verify3"));
				commonLib.clickLogOutLink(data.get("Logout_Header"));
				cmtLib.navigateBackToCMT();
				commonLib.clickCheckOutSettings(data.get("Check_out_Settings"));
				commonLib.selectOptionInCheckoutSettings(data.get("Shipping_Options"));
				cmtLib.DeselectAllDesignatedShippingOptions();
				commonLib.selectDefaultShippingOptionInCheckoutSettings(data.get("Shiping_Carrier_Checkout"));
				commonLib.clickOnUpdateButtonInUserSettings();

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
		ReportStatus.fnUpdateResultStatus("CarrierHeavy", "SBP05", ReportStatus.strMethodName, 1, browser);
		throw new RuntimeException(e);
	}

			finally {
	        	ReportControl.fnEnableJoin();
				ReportStatus.fnUpdateResultStatus("CarrierHeavy", "SBP05", ReportStatus.strMethodName, counter, browser);
				fnCloseTest();
				ReportControl.fnNextTestJoin(nextTestJoin);
			}
}

}