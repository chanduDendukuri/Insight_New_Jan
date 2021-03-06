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

public class SLP13_CITRIX_ZeroUsage extends SLPLib{
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
		// #       Name of the Test         :  SLP13_CITRIX-ZeroUsage
		// #       Migration Author         :  Cigniti Technologies
		// #
		// #       Date of Migration        : October 2019
		// #       DESCRIPTION              : This Test is used to test CITRIX No CITRIX4u Test
		// #       Parameters               : StartRow ,EndRow , nextTestJoin
		// #
		// ###############################################################################################################
		@Parameters({ "StartRow", "EndRow", "nextTestJoin" })
		@Test
		public void TC_SLP13(int StartRow,String EndRow, boolean nextTestJoin) throws Throwable {
			int counter = 0;
			try {
				int intStartRow = StartRow;
				int intEndRow = ReportControl.fnGetEndRowCunt(EndRow, "SLP13_CITRIX-ZeroUsage", TestData, "SLP");
				for (int intCounter = intStartRow; intCounter <= intEndRow; intCounter++) {
					try {
						fnOpenTest();
						ReportStatus.fnDefaultReportStatus();
						ReportControl.intRowCount = intCounter;
						Hashtable<String, String> data = TestUtil.getDataByRowNo("SLP13_CITRIX-ZeroUsage", TestData, "SLP", intCounter);
						TestEngineWeb.reporter
								.initTestCaseDescription("CITRIX-ZeroUsage");
						reporter.SuccessReport("Iteration Number : ",
								"**************Iteration Number::  " + intCounter + " For:: " + data.get("LoginName") + " ::and:: "
										+ data.get("Password") + " To Validate::" + data.get("errorMessage") + "  **************","");
						
						// Login to CMT
						cmtLib.loginToCMT(data.get("Header"));
						cmtLib.searchForWebGroup(data.get("WebGrp"));
						cmtLib.clickOnTheWebGroup(data.get("WebGrp_Name"));
						cmtLib.setHostedLicensingPermissionsOFF(data.get("permissions"));
						cmtLib.hoverOnManageWebGroupsAndSelectOptions(data.get("Manage_Web_Grp_Options"));
						// Clear usage
						cmtLib.AddMonthInHostedLicensingAdministrationPage(data.get("Month1"), data.get("Year1"), data.get("Type"),data.get("SoldTO"),data.get("SalesOrg"));
                        cmtLib.hoverOnManageWebGroupsAndSelectOptions(data.get("Manage_Web_Grp_Options1"));
						
						cmtLib.searchForaUserAndSelect(data.get("LnameEmailUname"), data.get("ContactName"));
						// "user_requires_approval;off";
						cmtLib.setPermissionsToDisable(data.get("Menu_Name"), data.get("Set_Permission"));
						cmtLib.loginAsAdminCMT();
						// Login verification
						cmtLib.loginVerification(data.get("ContactName"));
						
						// account tools >> Software License Agreements
						commonLib.clickOnAccountToolsMenuIcon(data.get("Tools_Menu"), data.get("Tools_Menu_DD"));
						// Select Software  Lic Agreements
				     	canadaLib.selectSPLADetailsProductCheckBox(data.get("Software_Agrement"));
						// verify search results and select first product
				     	 verifysearchResultsPageForSLP();
						
						// Search for part or product and add to cart : ROYSPLACXAPREM-CSP
				     	searchLib.searchInHomePage(data.get("PartNum1"));
				     	pipLib.enterQuantityOnProductDetailsPage(data.get("Quantity"));
				     	commonLib.addToCartAndVerify();
				     	orderLib.continueToCheckOutOnAddCart();
				     	orderLib.verifyCartHeaderLabel();
				     	//Verify part item added in cart page
				     	cartLib.verifyItemInCart(data.get("PartNum1"));
					/*
					 * int itemnumber=Integer.valueOf(data.get("Item_Number"));
					 * verifyCartPageAndPartDetails(itemnumber-1);
					 */
				     	pipLib. verifyCartPageAndPartDetailsForRecentlyItemDynamically(data.get("PartNum1"));
				        
				     	// account tools >> Software License Agreements
				     	orderLib.clickOnSideMenuSelectAccountToolOptions(data.get("Tools_Menu"), data.get("Tools_Menu_DD"));						
				     	canadaLib.verifySPLAPage();
				     	retrieveLastUsageReport(data.get("Software_Agrement"));
						orderLib.verifyCartHeaderLabel();
						verifyCitrixItemsInCart();
						String subTotal=sbpLib.getTotalAmountInCart(data.get("SubTotal_label"));
						Double subTotalAmount = Double.parseDouble(subTotal.replace("$", ""));
						verifyAmount(subTotalAmount);
					
						canadaLib.clickOnReportZeroUsageLinkOnCart();
						
						cartLib.verifyOnlyOneItemInCartPage();
						// Verify Only Zero Usage Part in the Cart CAD $0.00"
						String subtotalAmt=cartLib.getSummaryAmountInCart();
						Float subTotalAmount1 = Float.parseFloat(subtotalAmt.replace("$", ""));
						 verifySubTotalAmount(subTotalAmount1);
						
						 String itemPrice=orderLib.getCartProductTotalPriceForRecentlyAddedItem();
						 Float cartZeroUsageItem = Float.parseFloat(itemPrice.replace("$", ""));
						 if(cartZeroUsageItem==0){
								reporter.SuccessReport("Only Zero Usage Part in the Cart", " Zero Usage part Exists.", "Price USD $0.00");
							}else{
								reporter.failureReport("Only Zero Usage Part in the Cart ", " Zero Usage part does not exists", "");
							}
						 
						 // Verify usage period on cart
						String cartUsagePeriod=canadaLib.verifyReportingUsagePeriod();
						
						//Proceed to checkout
						 orderLib.proceedToCheckout();
						 orderLib.clickOnAdditionalInfoContinueButton();
						 orderLib.clickContinueOnLineLevelInfo();
						 canadaLib.verifySBP();
						 orderLib.clickContinueOnShippingAddress();
						 orderLib.shippingOptionsCarrierSelection();
						 orderLib.billingAddressContinueButton();
						 
						 orderLib.addNewCardInPayment(data.get("cardNumber"), data.get("cardName"), data.get("month"), data.get("year"),data.get("poNumebr"),data.get("POReleaseNumber"));
						 orderLib.clickOnReviewOrderButton();  // Click Review order button
						 
						 // Verify usage period on place order page
						  String poUsagePeriod=verifyReportingUsagePeriodOnReceiptPage();
						  assertTextStringMatching(cartUsagePeriod, poUsagePeriod);
						 
						 // Place Order
						 String amount = cartLib.getSummaryAmountInCart();
						orderLib.placeOrderAndVerifyReceiptOrderAndDate(amount);
						verifyReportingUsagePeriodOnReceiptPage();	
						//Verify Receipt
						orderLib.verifyReceiptVerbiage();
						
						// account tools >> Software License Agreements
						orderLib.clickOnSideMenuSelectAccountToolOptions(data.get("Tools_Menu"), data.get("Tools_Menu_DD"));
						canadaLib.verifySPLAPage();
						verifyAllReportingPeriodsCurrent();
						//SelectSoftwareLicAgrements
						commonLib.clickOnInsightLogoOnHomePage();
						// account tools >> Software License Agreements
						orderLib.clickOnSideMenuSelectAccountToolOptions(data.get("Tools_Menu"), data.get("Tools_Menu_DD"));
						canadaLib.selectSPLADetailsProductCheckBox(data.get("Software_Agrement"));
						// verify search results and select first product
				     	 verifysearchResultsPageForSLP();						
						
						// Search for part or product and add to cart : RPTCNSVPXCSPSE200C 
				     	searchLib.searchInHomePage(data.get("PartNum1"));
				     	pipLib.enterQuantityOnProductDetailsPage(data.get("Quantity"));
				     	commonLib.addToCartAndVerify();
				     	orderLib.continueToCheckOutOnAddCart();
				     	orderLib.verifyCartHeaderLabel();
				     	//Verify part item added in cart page
				     	cartLib.verifyItemInCart(data.get("PartNum1"));
				    	pipLib. verifyCartPageAndPartDetailsForRecentlyItemDynamically(data.get("PartNum1"));
				     	verifyAllReportingPeriodsCurrentinCartPage();
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
			ReportStatus.fnUpdateResultStatus("CITRIX-ZeroUsage", "SLP13", ReportStatus.strMethodName, 1, browser);
			throw new RuntimeException(e);
		}
			finally {
            	ReportControl.fnEnableJoin();
            	ReportStatus.fnUpdateResultStatus("CITRIX-ZeroUsage", "SLP13", ReportStatus.strMethodName, counter, browser);
				fnCloseTest();
				ReportControl.fnNextTestJoin(nextTestJoin);
			}
		}
}
		