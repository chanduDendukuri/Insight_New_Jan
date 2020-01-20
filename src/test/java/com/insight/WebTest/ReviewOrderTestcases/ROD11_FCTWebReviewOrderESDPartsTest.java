package com.insight.WebTest.ReviewOrderTestcases;

import com.insight.Lib.*;
import com.insight.accelerators.ReportControl;
import com.insight.accelerators.TestEngineWeb;
import com.insight.googledrive.ReportStatus;
import com.insight.utilities.TestUtil;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.Hashtable;
import java.util.List;

public class ROD11_FCTWebReviewOrderESDPartsTest extends OrderLib{

	ProductDisplayInfoLib prodInfoLib=new ProductDisplayInfoLib();
	CMTLib cmtLib=new CMTLib();
	SearchLib searchLib=new SearchLib();
	CommonLib commonLib=new CommonLib();
	CartLib cartLib=new CartLib();
	ProductDisplayInfoLib prodLib=new ProductDisplayInfoLib();
	ShipBillPayLib sbpLib=new ShipBillPayLib();
	LineLevelInfoLib lineLevelLib=new LineLevelInfoLib();

	// #############################################################################################################
	// #    Name of the Test         : ROD07_FCTWebReviewExportIPS
	// #    Migration Author         : Cigniti Technologies
	// #
	// #    Date of Migration        : September 2019
	// #    Description              : To Test Place Order basic
	// #    Parameters               : StartRow ,EndRow , nextTestJoin
	// #
	// #############################################################################################################
		@Parameters({ "StartRow", "EndRow", "nextTestJoin" })
		@Test
		public void TC_ROD11(int StartRow,String EndRow, boolean nextTestJoin) throws Throwable {
			int counter = 0;
			try {
				int intStartRow = StartRow;
				int intEndRow = ReportControl.fnGetEndRowCunt(EndRow, "ROD11_FCTWebReviewOrderESDParts", TestData, "Web_Review_Order");
				for (int intCounter = intStartRow; intCounter <= intEndRow; intCounter++) {
					try {
						fnOpenTest();
						ReportStatus.fnDefaultReportStatus();
						ReportControl.intRowCount = intCounter;
						Hashtable<String, String> data = TestUtil.getDataByRowNo("ROD11_FCTWebReviewOrderESDParts", TestData, "Web_Review_Order", intCounter);
						TestEngineWeb.reporter
								.initTestCaseDescription("FCTWebReviewOrderESDParts");
						reporter.SuccessReport("Iteration Number : ",
								"**************Iteration Number::  " + intCounter + " For:: " + data.get("LoginName") + " ::and:: "
										+ data.get("Password") + " To Validate::" + data.get("errorMessage") + "  **************",
								"");


						// Login to CMT enable Display Additional Notes during the transaction process,Allow File Upload during Checkout,Display Invoice Notes during the transaction process settings at web group level.
						cmtLib.loginToCMT(data.get("Header"));
						cmtLib.searchForWebGroup( data.get("WebGrp"));
						cmtLib.clickOnTheWebGroup(data.get("WebGrp_Name"));
						cmtLib.hoverOnManageWebGroupsAndSelectOptions(data.get("manage_Web_Grp_Options"));
						cmtLib.searchForaUserAndSelect(data.get("lnameEmailUname"),data.get("ContactName"));
						cmtLib.loginAsAdminCMT();

						// Login As to UAT web
						searchLib.searchInHomePage(data.get("SearchText"));
						cartLib.selectFirstProductDisplay();

						// Add a item >>  proceed To Checkout >> place order >> Review Order
						commonLib.addToCartAndVerify();
						continueToCheckOutOnAddCart();
						Thread.sleep(3000);
						proceedToCheckout();
						continueButtonOnAdditionalInformationSection();
						sbpLib.selectStoredAddress(data.get("Address"));
						String companyName=clickStoredAddressRadioButton();
						sbpLib.clickContinueOnStoredAddresssScreen();
						verifyStoredAddressAdded(companyName);
						clickContinueOnShippingAddress();
						selectShippingOptionsCarrier(); // Shipping options continue button
						shippingBillPayContinueButton();  //billing address
						// payment info
						termsInPaymentInfo(data.get("PONumber"));
						// verify tax on place order page
						verifyTheTaxOnPlaceOrderPage();
						searchLib.searchInHomePage(data.get("SearchText1"));
						commonLib.addToCartAndVerify();
						continueToCheckOutOnAddCart();
						proceedToCheckout();
						lineLevelLib.verifyOrderAndItemInfoBreadCrumb();
						continueButtonOnAdditionalInformationSection();
						clickContinueOnLineLevelInfo();
						shippingBillPay(data.get("Card_Number").toString(), data.get("Card_Name"), data.get("Month"), data.get("Year"), data.get("PONumber1"),data.get("POReleaseNumber"));
						// verify tax on place order page
						verifyTheTaxOnPlaceOrderPage();
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
				ReportStatus.fnUpdateResultStatus("ROD", "ROD11", ReportStatus.strMethodName, 1, browser);
				throw new RuntimeException(e);
			}

			finally {
	        	ReportControl.fnEnableJoin();
				ReportStatus.fnUpdateResultStatus("ROD", "ROD11", ReportStatus.strMethodName, counter, browser);
				fnCloseTest();
				ReportControl.fnNextTestJoin(nextTestJoin);
			}
		}

}


