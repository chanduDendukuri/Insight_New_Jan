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
						// Add a item  >>  proceed To Checkout >> place order >> Review Order
						commonLib.addToCartAndVerify();
						continueToCheckOutOnAddCart();

						proceedToCheckout();
						addAdditionalInformation(data.get("Url"), data.get("RP_HDL_Txt"), data.get("WG_HDL_Txt"), data.get("Additional_Notes"), data.get("Invoice_Notes"));
						clickContinueOnLineLevelInfo();
						shippingBillPayContinueButton();
						shippingBillPayContinueButton();
						shippingBillPayContinueButton();
						AddNewshippingAddress(data.get("link1"),data.get("companyName"),data.get("street"),data.get("city"),data.get("zipcode"),data.get("state"));
						Thread.sleep(2000);
						addNewCardInPayment(data.get("cardNumber"),data.get("cardName"),data.get("month"),data.get("year"),data.get("poNumebr"),data.get("POReleaseNumber"));

						// Verify Tax exemption message is displayed or not
						taxDeclerationCheckBoxON();
						clickOnReviewOrderButton();

						//Click on return to cart link
						clickOnReturnToCartLink();
						verifyCartHeaderLabel();

						//Add 1st part which requires EWR fee

						searchLib.searchInHomePage(data.get("SearchText1"));
						cartLib.selectFirstProductDisplay();
						commonLib.addToCartAndVerify();
						continueToCheckOutOnAddCart();

						//Add 2nd part which requires EWR fee
						searchLib.searchInHomePage(data.get("SearchText2"));
						commonLib.addToCartAndVerify();
						continueToCheckOutOnAddCart();
						verifyCartHeaderLabel();

						//verify part items added to cart and update quantity

						cartLib.verifyItemInCart(data.get("SearchText1"));
						editproductQTY(data.get("partNumber"),data.get("qntyNo"));
						cartLib.verifyItemInCart(data.get("SearchText2"));
						editproductQTY(data.get("partNumber1"),data.get("qntyNo1"));
						proceedToCheckout();
						addAdditionalInformation(data.get("Url"), data.get("RP_HDL_Txt"), data.get("WG_HDL_Txt"), data.get("Additional_Notes"), data.get("Invoice_Notes"));
						addLineLevelInfo();
						shippingBillPayContinueButton();
						shippingBillPayContinueButton();
						shippingBillPayContinueButton();
						addNewCardInPaymentInfoSection(data.get("cardNumber").toString(),data.get("cardName"), data.get("month"), data.get("year"),data.get("poNumebr"),data.get("POReleaseNumber"));
						//selectPaymentInfoMethodCreditCard(data.get("cardNumber").toString(),data.get("cardName"), data.get("month"), data.get("year"),data.get("poNumebr"),data.get("POReleaseNumber"));
						taxDeclerationCheckBoxOFF();
						clickOnReviewOrderButton();
						verifyPlaceOrderLabel();
						// Verify tax is greater than zero
						verifyTheTaxAfterUncheckingTaxExemptionCheckbox();
						editOrderInfo(data.get("sectionName"));

						//check the tax check box in payment Info
						taxDeclerationCheckBoxON();
						clickOnReviewOrderButton();
						// Verify tax equal to zero
						verifyEWRFeeAndTax();
						// End of the test
						
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


