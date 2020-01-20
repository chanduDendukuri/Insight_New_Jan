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

public class SBP01_StoredCardsLoginAsTest extends ShipBillPayLib {
	
	// #############################################################################################################
		// # Name of the Test : SBP01_StoredCardsLoginAs
		// # Migration Author : Cigniti Technologies
		// #
		// # Date of Migration : August 2019
		// # DESCRIPTION : This method is to perform Basic Cart operations.
		// # Parameters : StartRow ,EndRow , nextTestJoin
		// #
		// ###############################################################################################################

		@Parameters({ "StartRow", "EndRow", "nextTestJoin" })
		@Test
		public void Tc_SBP01(int StartRow, String EndRow, boolean nextTestJoin) throws Throwable {
			int counter=0;
			try {
			int intStartRow = StartRow;
			int intEndRow = ReportControl.fnGetEndRowCunt(EndRow, "SBP01_StoredCardsLoginAs", TestDataInsight,
					"Ship_Bill_Pay");
			for (int intCounter = intStartRow; intCounter <= intEndRow; intCounter++) {
				try {
			
				fnOpenTest();
				ReportStatus.fnDefaultReportStatus();
				ReportControl.intRowCount = intCounter;
				Hashtable<String, String> data = TestUtil.getDataByRowNo("SBP01_StoredCardsLoginAs", TestDataInsight,
						"Ship_Bill_Pay", intCounter);
				TestEngineWeb.reporter
						.initTestCaseDescription("StoredCardsLoginAs");
				reporter.SuccessReport("Iteration Number : ",
						"**************Iteration Number::  " + intCounter + " For:: " + data.get("LoginName") + " ::and:: "
								+ data.get("Password") + " To Validate::" + data.get("errorMessage") + "  **************",
						"");
				CommonLib commonLib = new CommonLib();
				CMTLib cmtLib = new CMTLib();
				CartLib cartLib = new CartLib();
				SearchLib searchLib=new SearchLib();
				OrderLib orderLib = new OrderLib();
				ShipBillPayLib shipbLib = new ShipBillPayLib();
				CanadaLib canadaLib = new CanadaLib(); 
				//Login
				cmtLib.loginToCMT(data.get("Header"));
				cmtLib.searchForWebGroup(data.get("WebGrp"));
				cmtLib.clickOnTheWebGroup(data.get("WebGrp_Name"));
				cmtLib.setCustomerLevelPermissionsOFF(data.get("Customer_Permissions_OFF"));
				// reference num creation---TU_StoredcardtestUser
				cmtLib.hoverOnManageWebGroupsAndSelectOptions(data.get("ManageWebGrpOptions"));
				cmtLib.searchForaUserAndSelect(data.get("LnameEmailUname"), data.get("ContactName"));
				commonLib.clickCheckOutSettings(data.get("Check_out_Settings"));
				commonLib.selectOptionInCheckoutSettings(data.get("Shipping_Options"));
				commonLib.selectDefaultShippingOptionInCheckoutSettings(data.get("Default_Shipping_Option"));
				commonLib.clickOnUpdateButtonInUserSettings();
				cmtLib.loginAsAdminCMT();
				Thread.sleep(2000);
				shipbLib.PaymentandCardsTextverify(data.get("toolsMenuName2"), data.get("dropDown2"), data.get("tabName2"));
				canadaLib.clickOnSideMenuSelectAccountToolOptions(data.get("toolsMenuName1"),data.get("dropDown1"));	
				//set date in calander
				clickExpandSearchButtonInApprovalmanagementPage();
				clenderApproval(data.get("Month"),data.get("Year"));
				//Click on search button 
				clickSearchButtonInApprovalmanagementPage();
				shipbLib.ApproveRequesition(data.get("ReferenceNumber"));
				commonLib.searchProduct(data.get("SearchItem1"));
				searchLib.verifyBreadCrumbInSearchResultsPage(data.get("SearchItem1"));
				commonLib.addFirstDisplyedItemToCartAndVerify();
				canadaLib.continueToCheckout();
				Thread.sleep(2000);
				orderLib.proceedToCheckout();
				Thread.sleep(2000);
				// continue button for all shipping sections
				orderLib.clickOnAdditionalInfoContinueButton();
				orderLib.clickContinueOnLineLevelInfo(); // Click continue on Line level Info
				orderLib.shippingBillPayContinueButton(); // Click continue on shipping address
				orderLib.shippingOptionsCarrierSelection(); // Click continue on shipping options
				orderLib.billingAddressContinueButton(); // Billing address continue button
				verifyTextatPaymentinfoCreditcard();
				orderLib.selectPaymentInfoMethodCreditCard(data.get("cardNumber"), data.get("cardName"), data.get("month"),
						data.get("year"),data.get("PONumber"),data.get("POReleaseNumber"));
				orderLib.clickOnReviewOrderButton();
				String summaryAmount1 = cartLib.getSummaryAmountInCart();
				orderLib.placeOrderAndVerifyReceiptOrderAndDate(summaryAmount1);
				Thread.sleep(2000);
				commonLib.clickLogOutLink(data.get("Logout_Header"));
				cmtLib.navigateBackToCMT();
				// End user Login
				navigateTo("https://uat1.insight.com/en_US/home.html");
				cmtLogout();
				//cmtLib.handleWelcomeToInsightBetaPopUp();
				cmtLib.clickLoginLink(data.get("Header"));
				cmtLib.loginAsEndUser(data.get("userName"), data.get("password"));
				shipbLib.UserProfCardVerification(data.get("toolsMenuName2"), data.get("dropDown2"), data.get("tabName2"));
				canadaLib.clickOnSideMenuSelectAccountToolOptions(data.get("toolsMenuName1"),data.get("dropDown1"));
				//set date in calander
				clickExpandSearchButtonInApprovalmanagementPage();
				clenderApproval(data.get("Month"),data.get("Year"));
				//Click on search button 
				clickSearchButtonInApprovalmanagementPage();
				shipbLib.ApproveRequesitionEndUser(data.get("ReferenceNumber"));
				selectedStoredCards(data.get("storedcard"));
				commonLib.searchProduct(data.get("SearchItem1"));
				searchLib.verifyBreadCrumbInSearchResultsPage(data.get("SearchItem1"));
				commonLib.addFirstDisplyedItemToCartAndVerify();
				canadaLib.continueToCheckout();
				Thread.sleep(2000);
				orderLib.proceedToCheckout();
				Thread.sleep(2000);
				// continue button for all shipping sections
				orderLib.clickOnAdditionalInfoContinueButton();
				orderLib.clickContinueOnLineLevelInfo(); // Click continue on Line level Info
				orderLib.shippingBillPayContinueButton(); // Click continue on shipping address
				orderLib.shippingOptionsCarrierSelection(); // Click continue on shipping options
				orderLib.billingAddressContinueButton(); // Billing address continue button
				clickCreditCard();
				verifyCardNum();
				verifyCardName();
				verifyCardDate();
				orderLib.clickOnReviewOrderButton();//************3742/2/2024/Joe Tester
				verifyCardNum();
				verifyCardName();
				verifyCardDate();
				String summaryAmount2 = cartLib.getSummaryAmountInCart();
				orderLib.placeOrderAndVerifyReceiptOrderAndDate(summaryAmount2);
				Thread.sleep(2000);
				commonLib.clickLogOutLink(data.get("Logout_Header"));
				
				
				} catch (Exception e) {
					ReportStatus.blnStatus = false;
				//	gErrorMessage = e.getMessage();
					System.out.println(e.getMessage());
					gTestStatus = false;
				}
			
			}
		} catch (Exception e) {
			e.printStackTrace();
			ReportStatus.blnStatus = false;
		//	gErrorMessage = e.getMessage();
			gTestStatus = false;
			ReportStatus.fnUpdateResultStatus("StoredCardsLoginAs", "SBP01", ReportStatus.strMethodName, 1, browser);
			throw new RuntimeException(e);
		}

			finally {
	        	ReportControl.fnEnableJoin();
				ReportStatus.fnUpdateResultStatus("StoredCardsLoginAs", "SBP01", ReportStatus.strMethodName, counter, browser);
				fnCloseTest();
				ReportControl.fnNextTestJoin(nextTestJoin);
			}
	}

}