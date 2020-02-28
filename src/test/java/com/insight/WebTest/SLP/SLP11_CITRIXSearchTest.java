package com.insight.WebTest.SLP;

import java.util.Hashtable;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.insight.Lib.CMTLib;
import com.insight.Lib.CanadaLib;
import com.insight.Lib.CartLib;
import com.insight.Lib.CommonLib;
import com.insight.Lib.OrderLib;
import com.insight.Lib.ProductDetailLib;
import com.insight.Lib.ProductDisplayInfoLib;
import com.insight.Lib.SLPLib;
import com.insight.Lib.SearchLib;
import com.insight.Lib.ShipBillPayLib;
import com.insight.accelerators.ReportControl;
import com.insight.accelerators.TestEngineWeb;
import com.insight.googledrive.ReportStatus;
import com.insight.utilities.TestUtil;

public class SLP11_CITRIXSearchTest extends SLPLib{

	
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
		// #       Name of the Test         :  SLP11_CITRIXSearchTest
		// #       Migration Author         :  Cigniti Technologies
		// #
		// #       Date of Migration        : October 2019
		// #       DESCRIPTION              : This Test is used to Test CITRIX SearchTest
		// #       Parameters               : StartRow ,EndRow , nextTestJoin
		// #
		// ###############################################################################################################
		@Parameters({ "StartRow", "EndRow", "nextTestJoin" })
		@Test
		public void TC_SLP11(int StartRow,String EndRow, boolean nextTestJoin) throws Throwable {
			int counter = 0;
			try {
				int intStartRow = StartRow;
				int intEndRow = ReportControl.fnGetEndRowCunt(EndRow, "SLP11_CITRIXSearch", TestData, "SLP");
				for (int intCounter = intStartRow; intCounter <= intEndRow; intCounter++) {
					try {
						fnOpenTest();
						ReportStatus.fnDefaultReportStatus();
						ReportControl.intRowCount = intCounter;
						Hashtable<String, String> data = TestUtil.getDataByRowNo("SLP11_CITRIXSearch", TestData, "SLP", intCounter);
						TestEngineWeb.reporter
								.initTestCaseDescription("CITRIXSearch");
						reporter.SuccessReport("Iteration Number : ",
								"**************Iteration Number::  " + intCounter + " For:: " + data.get("LoginName") + " ::and:: "
										+ data.get("Password") + " To Validate::" + data.get("errorMessage") + "  **************","");
						
						// Login to CMT
						cmtLib.loginToCMT(data.get("Header"));
						cmtLib.searchForWebGroup(data.get("WebGrp"));
						cmtLib.clickOnTheWebGroup(data.get("WebGrp_Name"));
						// Select Hosted Licensing Administration
						cmtLib.hoverOnManageWebGroupsAndSelectOptions(data.get("Manage_Web_Grp_Options1"));
						// Clear usage
						cmtLib.AddMonthInHostedLicensingAdministrationPage(data.get("Month1"), data.get("Year1"), data.get("Type"),data.get("SoldTO"),data.get("SalesOrg"));
						// Select Users
						cmtLib.hoverOnManageWebGroupsAndSelectOptions(data.get("Manage_Web_Grp_Options2"));
						cmtLib.searchForaUserAndSelect(data.get("LnameEmailUname"), data.get("ContactName"));
						// enable_purchase_popup;ON";
						cmtLib.setPermissions(data.get("Menu_Name"), data.get("Set_Permission"));
						cmtLib.loginAsAdminCMT();
						// Login verification
						cmtLib.loginVerification(data.get("ContactName"));
						
						// account tools >> Software License Agreements
						commonLib.clickOnAccountToolsMenuIcon(data.get("Tools_Menu"), data.get("Tools_Menu_DD"));
						canadaLib.verifySPLAPage();
						// Select Software  Lic Agreements
				     	canadaLib.selectSPLADetailsProductCheckBox(data.get("Soft_Agrement"));
						// verify search results and select first product
				     	 verifysearchResultsPageForSLP();
				     	// search for a part / product and ad to cart  >> 4007110-CSP
				     	searchLib.searchInHomePage(data.get("SearchText1"));
				     	searchLib.removeTheFilterForInStockOnly(data.get("In_Stock_Only"));
						pipLib.verifyTheManufacturerNumberInProductDetailsPage(data.get("SearchText1"));
						pipLib.enterQuantityOnProductDetailsPage(data.get("Quantity"));
						commonLib.addToCartAndVerify();
						orderLib.continueToCheckOutOnAddCart();
						verifyCITRIXItemInCart(data.get("SearchText1"));
				     	
					/*
					 * Thread.sleep(3000); int itemnumber=Integer.valueOf(data.get("Item_Number"));
					 * verifyCartPageAndPartDetails(itemnumber-1);
					 */
						pipLib. verifyCartPageAndPartDetailsForRecentlyItemDynamically(data.get("SearchText1"));
				     	
				        // search for product and add to cart  : Workstations
						searchLib.searchInHomePage(data.get("SearchText3"));
						searchLib.verifyTheResultsForSearchTerm(data.get("SearchText3"));
						pipLib.getPartNumberInSearchResultsPage();
						cartLib.selectFirstProductDisplay();
						String mfrNumber=prodDetailsLib.getMFRNumberInProductInfopage();
						String insigtPart=prodDetailsLib.getInsightPartNumberInProductInfopage();
						commonLib.addToCartAndVerify();
						orderLib.continueToCheckOutOnAddCart();
						canadaLib.verifyPlaceCartLabel();
						cartLib.verifyItemInCartByInsightPart(mfrNumber);
						pipLib. verifyCartPageAndPartDetailsForRecentlyItemDynamically(mfrNumber);
						// Verify CITRIX part in cart
						verifyCITRIXItemInCart(data.get("SearchText1"));
						verifyCitrixItemsInCart();
						
						// verifying the Non Service Provider Items Removal Message in cart
				     	verifyNonServiceProviderItemsRemovalMessage();
				     	
				     	///	Remove non Citrix Items from the Cart
				     	deleteParticularItemInCart(insigtPart);
				     	// Usage period warning message
				       canadaLib.verifyReportingUsagePeriodWarningMessage();
				       // Verify Usage Period
						String reportingPeriod=canadaLib.verifyReportingUsagePeriod();
						//Proceed to checkout
						 orderLib.proceedToCheckout();
						 orderLib.clickOnAdditionalInfoContinueButton();
						 orderLib.clickContinueOnLineLevelInfo();   // Click continue on Line level Info
						 canadaLib.verifySBP();
						 orderLib.clickContinueOnShippingAddress();
						 orderLib.shippingOptionsCarrierSelection();
						 orderLib.billingAddressContinueButton(); // Billing address continue button
						orderLib.selectPaymentInfoMethodCreditCard(data.get("Card_Number").toString(), data.get("Card_Name"),data.get("Month"), data.get("Year"),data.get("PO_Number"),data.get("POReleaseNumber"));  // VISA card
						orderLib.clickOnReviewOrderButton();
						//Place Order
						String summaryAmount=cartLib.getSummaryAmountInCart();
						orderLib.placeOrderAndVerifyReceiptOrderAndDate(summaryAmount);
						
						//Verify Receipt
						orderLib.verifyReceiptVerbiage();
						String reportingPeriodonReceiptPage=verifyReportingUsagePeriodOnReceiptPage();
						assertTextStringMatching(reportingPeriod, reportingPeriodonReceiptPage);
						orderLib.clickOrderDetailsLinkOnReceiptPage();
						commonLib.clickLogOutLink(data.get("Logout"));
						
					} catch (Exception e) {
						ReportStatus.blnStatus = false;
						gTestStatus = false;
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
				ReportStatus.blnStatus = false;
				gTestStatus = false;
				ReportStatus.fnUpdateResultStatus("CITRIXSearch", "SLP11", ReportStatus.strMethodName, 1, browser);
				throw new RuntimeException(e);
			}

			finally {
            	ReportControl.fnEnableJoin();
            	ReportStatus.fnUpdateResultStatus("CITRIXSearch", "SLP11", ReportStatus.strMethodName, counter, browser);
				fnCloseTest();
				ReportControl.fnNextTestJoin(nextTestJoin);
			}
		}
	}



