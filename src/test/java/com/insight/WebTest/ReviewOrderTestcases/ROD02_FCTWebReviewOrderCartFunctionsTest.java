package com.insight.WebTest.ReviewOrderTestcases;

import com.insight.Lib.*;
import com.insight.accelerators.ReportControl;
import com.insight.accelerators.TestEngineWeb;
import com.insight.googledrive.ReportStatus;
import com.insight.utilities.TestUtil;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.util.Hashtable;

public class ROD02_FCTWebReviewOrderCartFunctionsTest extends OrderLib{

	ProductDisplayInfoLib prodInfoLib=new ProductDisplayInfoLib();
	CMTLib cmtLib=new CMTLib();
	SearchLib searchLib=new SearchLib();
	CommonLib commonLib=new CommonLib();
	CartLib cartLib=new CartLib();
	ProductDisplayInfoLib prodLib=new ProductDisplayInfoLib();
	CanadaLib canadaLib=new CanadaLib();


	// #############################################################################################################
	// #    Name of the Test         : ROD02_FCTWebReviewOrderCartFunctions
	// #    Migration Author         : Cigniti Technologies
	// #
	// #    Date of Migration        : September 2019
	// #    Description              : To Test Place Order basic
	// #    Parameters               : StartRow ,EndRow , nextTestJoin
	// #
	// #############################################################################################################
		@Parameters({ "StartRow", "EndRow", "nextTestJoin" })
		@Test
		public void TC_ROD02(int StartRow,String EndRow, boolean nextTestJoin) throws Throwable {
			int counter = 0;
			try {
				int intStartRow = StartRow;
				int intEndRow = ReportControl.fnGetEndRowCunt(EndRow, "ROD02_FCTWebReviewOrderCartFunctions", TestData, "Web_Review_Order");
				for (int intCounter = intStartRow; intCounter <= intEndRow; intCounter++) {
					try {
						fnOpenTest();
						ReportStatus.fnDefaultReportStatus();
						ReportControl.intRowCount = intCounter;
						Hashtable<String, String> data = TestUtil.getDataByRowNo("ROD02_FCTWebReviewOrderCartFunctions", TestData, "Web_Review_Order", intCounter);
						TestEngineWeb.reporter
								.initTestCaseDescription("FCTWebReviewOrderCartFunctions");
						reporter.SuccessReport("Iteration Number : ",
								"**************Iteration Number::  " + intCounter + " For:: " + data.get("LoginName") + " ::and:: "
										+ data.get("Password") + " To Validate::" + data.get("errorMessage") + "  **************","");

						// Login to CMT tool and enable permission : Enable Crosssell
						cmtLib.loginToCMTSetPermissions(data.get("Header"), data.get("WebGrp"), data.get("WebGrp_Name"), data.get("Manage_Web_Grp_Options"), data.get("LnameEmailUname"), data.get("ContactName"),data.get("Menu_Name"), data.get("Set_Permission"));

						// Login As To UAT Web
						searchLib.searchInHomePage(data.get("SearchText1"));
						// Stock only
						searchLib.removeTheFilterForInStockOnly(data.get("In_Stock_Only"));
						prodInfoLib.verifyTheManufacturerNumberInProductDetailsPage(data.get("SearchText1"));
						prodInfoLib.enterQuantityOnProductDetailsPage(data.get("Quantity"));
						commonLib.addToCartAndVerify();
						continueToCheckOutOnAddCart();
						canadaLib.verifyPlaceCartLabel();
						cartLib.verifyItemAddedInCartByMfrNumber(data.get("SearchText1"));
						prodInfoLib.verifyCartPageAndPartDetails();
						addWarrantyInCartPage();

						// Searching for second item  -- 20HD0068US
						searchLib.searchInHomePage(data.get("SearchText2"));
						// Stock only
						searchLib.removeTheFilterForInStockOnly(data.get("In_Stock_Only"));
						prodInfoLib.verifyTheManufacturerNumberInProductDetailsPage(data.get("SearchText2"));
						prodInfoLib.enterQuantityOnProductDetailsPage(data.get("Quantity"));
						commonLib.addToCartAndVerify();
						continueToCheckOutOnAddCart();
						canadaLib.verifyPlaceCartLabel();
						cartLib.verifyItemAddedInCartByMfrNumber(data.get("SearchText1"));
						int itemnumber1=Integer.valueOf(data.get("Item_Number1"));
						verifyCartPageAndPartDetails(itemnumber1);
						
						// Think pads
						searchLib.searchInHomePage(data.get("SearchText3"));
						searchLib.verifyTheResultsForSearchTerm(data.get("SearchText3"));
						// in-stock filter verification
						searchLib.verifyFilterBreadCrumb(data.get("In_Stock_Only"));
						prodInfoLib.getFirstProdDescription();
						prodInfoLib.selectFirstProductAddToCartAndVerifyCart();
						int itemnumber2=Integer.valueOf(data.get("Item_Number2"));
						verifyCartPageAndPartDetails(itemnumber2);
						proceedToCheckout();
						cartLib.clickOnContinueButtonInAddInformtion();
						clickContinueOnLineLevelInfo();
						clickContinueOnShippingAddress();
						enterAttentionField( data.get("Card_Name"));
						shippingOptionsCarrierSelection();
						billingAddressContinueButton();
						selectPaymentInfoMethodCreditCard(data.get("Card_Number").toString(), data.get("Card_Name"), data.get("Month"), data.get("Year"),data.get("PONumber"),data.get("POReleaseNumber"));
						clickOnReviewOrderButton();
						verifyPlaceOrderLabel();
						// LogOut
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
				ReportStatus.fnUpdateResultStatus("ROD", "ROD02", ReportStatus.strMethodName, 1, browser);
				throw new RuntimeException(e);
			}
			finally {
	        	ReportControl.fnEnableJoin();
				ReportStatus.fnUpdateResultStatus("ROD", "ROD02", ReportStatus.strMethodName, counter, browser);
				fnCloseTest();
				ReportControl.fnNextTestJoin(nextTestJoin);
			}
		}

}


