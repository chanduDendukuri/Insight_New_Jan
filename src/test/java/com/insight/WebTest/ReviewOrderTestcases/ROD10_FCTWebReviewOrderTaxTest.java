package com.insight.WebTest.ReviewOrderTestcases;

import com.insight.Lib.*;
import com.insight.accelerators.ReportControl;
import com.insight.accelerators.TestEngineWeb;
import com.insight.googledrive.ReportStatus;
import com.insight.utilities.TestUtil;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.util.Hashtable;

public class ROD10_FCTWebReviewOrderTaxTest extends OrderLib{

	ProductDisplayInfoLib prodInfoLib=new ProductDisplayInfoLib();
	CMTLib cmtLib=new CMTLib();
	SearchLib searchLib=new SearchLib();
	CommonLib commonLib=new CommonLib();
	CartLib cartLib=new CartLib();
	ProductDisplayInfoLib prodLib=new ProductDisplayInfoLib();
	CanadaLib canadaLib =new CanadaLib();

	// #############################################################################################################
	// #    Name of the Test         : ROD10_FCTWebReviewOrderTax
	// #    Migration Author         : Cigniti Technologies
	// #
	// #    Date of Migration        : September 2019
	// #    Description              : To Test Place Order basic
	// #    Parameters               : StartRow ,EndRow , nextTestJoin
	// #
	// #############################################################################################################
		@Parameters({ "StartRow", "EndRow", "nextTestJoin" })
		@Test
		public void TC_ROD10(int StartRow,String EndRow, boolean nextTestJoin) throws Throwable {
			int counter = 0;
			try {
				int intStartRow = StartRow;
				int intEndRow = ReportControl.fnGetEndRowCunt(EndRow, "ROD10_FCTWebReviewOrderTax", TestData, "Web_Review_Order");
				for (int intCounter = intStartRow; intCounter <= intEndRow; intCounter++) {
					try {
						fnOpenTest();
						ReportStatus.fnDefaultReportStatus();
						ReportControl.intRowCount = intCounter;
						Hashtable<String, String> data = TestUtil.getDataByRowNo("ROD10_FCTWebReviewOrderTax", TestData, "Web_Review_Order", intCounter);
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
						cmtLib.setPermissions(data.get("menuName"),data.get("userPermissions"));
						cmtLib.loginAsAdminCMT();
						
						// Login As to UAT web
						searchLib.searchInHomePage(data.get("SearchText"));
						searchLib.verifyBreadCrumbInSearchResultsPage(data.get("SearchText"));
						String partNumber=prodInfoLib.getPartNumberInSearchResultsPage();
						prodInfoLib.selectFirstProductAddToCartAndVerifyCart();
						prodInfoLib.verifyCartPageAndPartDetails();
						proceedToCheckout();
						cartLib.clickOnContinueButtonInAddInformtion();
						//******** Click continue on Line level Info, Ship and Bill pay sections ********************//
						clickContinueOnLineLevelInfo();
						canadaLib.verifySBP();
						clickContinueOnShippingAddress();
						shippingOptionsCarrierSelection();
						billingAddressContinueButton();
						selectPaymentInfoMethodCreditCard(data.get("cardNumber").toString(), data.get("cardName"), data.get("month"), data.get("year"),data.get("poNumebr"),data.get("POReleaseNumber"));

						// Verify Tax exemption message is displayed or not
						taxDeclerationCheckBoxON();
						clickOnReviewOrderButton();

						//Click on return to cart link
						clickOnReturnToCartLink();
						verifyCartHeaderLabel();

						//Add 1st part which requires EWR fee

						searchLib.searchInHomePage(data.get("SearchText1"));
						searchLib.removeTheFilterForInStockOnly(data.get("In_Stock_Only"));
						prodInfoLib.verifyTheManufacturerNumberInProductDetailsPage(data.get("SearchText1"));
						prodInfoLib.enterQuantityOnProductDetailsPage(data.get("Quantity"));
						commonLib.addToCartAndVerify();
						continueToCheckOutOnAddCart();
						canadaLib.verifyPlaceCartLabel();
						int itemnumber1=Integer.valueOf(data.get("Item_Number1"));
						cartLib.verifyItemInCartByInsightPart(data.get("SearchText1"));
						verifyCartPageAndPartDetails(itemnumber1);
						
						//Add 2nd part which requires EWR fee
						searchLib.searchInHomePage(data.get("SearchText2"));
						searchLib.removeTheFilterForInStockOnly(data.get("In_Stock_Only"));
						prodInfoLib.verifyTheManufacturerNumberInProductDetailsPage(data.get("SearchText2"));
						prodInfoLib.enterQuantityOnProductDetailsPage(data.get("Quantity"));
						commonLib.addToCartAndVerify();
						continueToCheckOutOnAddCart();
						canadaLib.verifyPlaceCartLabel();
						int itemnumber2=Integer.valueOf(data.get("Item_Number2"));
						cartLib.verifyItemInCartByInsightPart(data.get("SearchText2"));
						verifyCartPageAndPartDetails(itemnumber2);
						editproductQTY(data.get("SearchText2"),data.get("Quantity2"));
						proceedToCheckout();
						cartLib.clickOnContinueButtonInAddInformtion();
						//******** Click continue on Line level Info, Ship and Bill pay sections ********************//
						clickContinueOnLineLevelInfo();
						canadaLib.verifySBP();
						clickContinueOnShippingAddress();
						//enterAttentionField( data.get("Card_Name"));
						shippingOptionsCarrierSelection();
						billingAddressContinueButton();
						
						taxDeclerationCheckBoxOFF();
						clickOnReviewOrderButton();
						verifyPlaceOrderLabel();
						// Verify tax is greater than zero
						verifyTheTaxAfterUncheckingTaxExemptionCheckbox();
						editOrderInfo(data.get("sectionName"));
						canadaLib.verifySBP();
						// tax declaration ON
						taxDeclerationCheckBoxON();
						clickOnReviewOrderButton();
						// Verify tax equal to zero
						verifyEWRFeeAndTax();
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
				gTestStatus = false;
				ReportStatus.fnUpdateResultStatus("ROD", "ROD10", ReportStatus.strMethodName, 1, browser);
				throw new RuntimeException(e);
			}

			finally {
	        	ReportControl.fnEnableJoin();
				ReportStatus.fnUpdateResultStatus("ROD", "ROD10", ReportStatus.strMethodName, counter, browser);
				fnCloseTest();
				ReportControl.fnNextTestJoin(nextTestJoin);
			}
		}

}


