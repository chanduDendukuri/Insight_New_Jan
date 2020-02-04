package com.insight.WebTest.LNL;

import java.util.Hashtable;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.insight.Lib.CMTLib;
import com.insight.Lib.CanadaLib;
import com.insight.Lib.CartLib;
import com.insight.Lib.CommonLib;
import com.insight.Lib.LineLevelInfoLib;
import com.insight.Lib.OrderLib;
import com.insight.Lib.ProductDetailLib;
import com.insight.Lib.ProductDisplayInfoLib;
import com.insight.Lib.SearchLib;
import com.insight.Lib.ShipBillPayLib;
import com.insight.accelerators.ReportControl;
import com.insight.accelerators.TestEngineWeb;
import com.insight.googledrive.ReportStatus;
import com.insight.utilities.TestUtil;

public class LNL02_RequireOverrideTest extends LineLevelInfoLib{

	CMTLib cmtLib = new CMTLib();
	CommonLib commonLib = new CommonLib();
	CartLib cartLib = new CartLib();
	SearchLib searchLib = new SearchLib();
	ProductDetailLib prodDetailsLib=new ProductDetailLib();
	OrderLib orderLib=new OrderLib();
	ProductDisplayInfoLib pipLib=new ProductDisplayInfoLib();
	ShipBillPayLib sbpLib=new ShipBillPayLib();
	CanadaLib canadaLib=new CanadaLib();
	   
	    // #############################################################################################################
		// #       Name of the Test         :  LNL02_RequireOverrideTest
		// #       Migration Author         :  Cigniti Technologies
		// #
		// #       Date of Migration        : October 2019
		// #       DESCRIPTION              : Test to RP Option Smart Trackers that are flagged as "Required on Web" are Required
		// #       Parameters               : StartRow ,EndRow , nextTestJoin
		// #
		// ###############################################################################################################
		@Parameters({ "StartRow", "EndRow", "nextTestJoin" })
		@Test
		public void TC_LNL02(int StartRow,String EndRow, boolean nextTestJoin) throws Throwable {
			int counter = 0;
			try {
				int intStartRow = StartRow;
				int intEndRow = ReportControl.fnGetEndRowCunt(EndRow, "LNL02_RequireOverride", TestData, "WEB_LineLevelInfo");
				for (int intCounter = intStartRow; intCounter <= intEndRow; intCounter++) {
					try {
						fnOpenTest();
						ReportStatus.fnDefaultReportStatus();
						ReportControl.intRowCount = intCounter;
						Hashtable<String, String> data = TestUtil.getDataByRowNo("LNL02_RequireOverride", TestData, "WEB_LineLevelInfo", intCounter);
						TestEngineWeb.reporter.initTestCaseDescription("RequireOverrideTest");
						
						// Login to CMT
						cmtLib.loginToCMT(data.get("Header"));
						cmtLib.searchForWebGroup(data.get("WebGrp"));
						cmtLib.clickOnTheWebGroup(data.get("WebGrp_Name"));
						cmtLib.hoverOnManageWebGroupsAndSelectOptions(data.get("Manage_Web_Grp_Options"));
						cmtLib.searchForaUserAndSelect(data.get("LnameEmailUname"), data.get("ContactName"));
						// login As
						cmtLib.loginAsAdminCMT();
						// search for a product
						searchLib.searchInHomePage(data.get("SearchText"));
						searchLib.verifyBreadCrumbInSearchResultsPage(data.get("SearchText"));
						String partNumber=pipLib.getPartNumberInSearchResultsPage();
						pipLib.selectFirstProductAddToCartAndVerifyCart();
						pipLib.verifyCartPageAndPartDetails();
						scrollToBottom();
						scrollBottom();
						orderLib.proceedToCheckout();
						cartLib.addAdditionalInformationInCheckOut(data.get("Url"), data.get("RP_HDL_Txt"));
						cartLib.addLineLevelInformationInCheckOut(data.get("RP_LNL_Txt"));
						canadaLib.verifySBP();
						orderLib.enterAttentionField(data.get("Card_Name"));
						orderLib.clearPhnumberInShippinAddress();
						orderLib.clickContinueOnShippingAddress();  // continue button on Shipping address
						orderLib.shippingOptionsCarrierSelection();  // carrier selection or continue in shipping options
						orderLib.billingAddressContinueButton();  // Continue on billing address section
						orderLib.selectPaymentInfoMethodCreditCard(data.get("Card_Number").toString(), data.get("Card_Name"),data.get("Month"), data.get("Year"),data.get("PO_Number"),data.get("POReleaseNumber"));
						orderLib.clickOnReviewOrderButton();
						orderLib.verifyPlaceOrderLabel();
						// Verify RP_HDL_Txt and RP_LNL_Txt text on PO page
						verifyRP_HDL_TxtOnPlaceOrderPage(data.get("RP_HDL_Txt"));
						verifyRP_LNL_TxtOnPlaceOrderPage(data.get("RP_LNL_Txt"));
						//Place Order
						String summaryAmountInLogin=cartLib.getSummaryAmountInCart();
						orderLib.placeOrderAndVerifyReceiptOrderAndDate(summaryAmountInLogin);
						//Verify Receipt
						orderLib.verifyReceiptVerbiage();
						orderLib.clickOrderDetailsLinkOnReceiptPage();
						// Verify RP_HDL_Txt and RP_LNL_Txt text on receipt page
						verifyRP_HDL_TxtOnReceiptPage(data.get("RP_HDL_Txt"));
						verifyRP_LNL_TxtOnReceiptPage(data.get("RP_LNL_Txt"));
						// logout 
						commonLib.clickLogOutLink(data.get("Logout"));
						
					} catch (Exception e) {
						ReportStatus.blnStatus = false;
						//gErrorMessage = e.getMessage();
						gTestStatus = false;
					}
					
				}
			} catch (Exception e) {
				e.printStackTrace();
				ReportStatus.blnStatus = false;
				//gErrorMessage = e.getMessage();
				gTestStatus = false;
				ReportStatus.fnUpdateResultStatus("RequireOverride", "TC_LNL02", ReportStatus.strMethodName, 1, browser);
				throw new RuntimeException(e);
			}

			 finally {
		        	ReportControl.fnEnableJoin();
					ReportStatus.fnUpdateResultStatus("RequireOverride", "TC_LNL02", ReportStatus.strMethodName, counter, browser);
					fnCloseTest();
					ReportControl.fnNextTestJoin(nextTestJoin);
				}
		}

}
