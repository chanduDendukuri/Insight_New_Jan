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
	CanadaLib canadaLib=new CanadaLib();

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
						searchLib.verifyBreadCrumbInSearchResultsPage(data.get("SearchText"));
						String partNumber=prodInfoLib.getPartNumberInSearchResultsPage();
						prodInfoLib.selectFirstProductAddToCartAndVerifyCart();
						Thread.sleep(3000);
						proceedToCheckout();
						continueButtonOnAdditionalInformationSection();
						sbpLib.selectStoredAddress(data.get("Address"));
						String companyName=clickStoredAddressRadioButton();
						sbpLib.clickContinueOnStoredAddresssScreen();
						verifyStoredAddressAdded(companyName);
						clickContinueOnShippingAddress();
						selectShippingOptionsCarrier(); // Shipping options continue button
						billingAddressContinueButton();  //billing address
						// payment info
						termsInPaymentInfo(data.get("PONumber"),data.get("POReleaseNumber"));
						// verify tax on place order page
						String taxBefore=verifyTheTaxOnPlaceOrderPage();
						searchLib.searchInHomePage(data.get("SearchText1"));
						searchLib.removeTheFilterForInStockOnly(data.get("In_Stock_Only"));
						prodInfoLib.verifyTheManufacturerNumberInProductDetailsPage(data.get("SearchText1"));
						prodInfoLib.enterQuantityOnProductDetailsPage(data.get("Quantity"));
						commonLib.addToCartAndVerify();
						continueToCheckOutOnAddCart();
						canadaLib.verifyPlaceCartLabel();
						cartLib.verifyItemInCartByInsightPart(data.get("SearchText1"));
						proceedToCheckout();
						lineLevelLib.verifyOrderAndItemInfoBreadCrumb();
						continueButtonOnAdditionalInformationSection();
						clickContinueOnLineLevelInfo();
						canadaLib.verifySBP();
						clickContinueOnShippingAddress();
						shippingOptionsCarrierSelection();
						billingAddressContinueButton();
						clickOnReviewOrderButton();
						// verify tax on place order page
						String taxAfter=verifyTheTaxOnPlaceOrderPage();
						float tax1=Float.valueOf(taxBefore);
						float tax2=Integer.valueOf(taxAfter);
						verifyTaxEstimatesAreEqual(tax1, tax2);
						
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


