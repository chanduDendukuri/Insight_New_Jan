package com.insight.WebTest.ReviewOrderTestcases;

import com.insight.Lib.*;
import com.insight.accelerators.ReportControl;
import com.insight.accelerators.TestEngineWeb;
import com.insight.googledrive.ReportStatus;
import com.insight.utilities.TestUtil;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.util.Hashtable;

public class ROD01_FCTWebReviewOrderAdditionalOrderInfoTest extends OrderLib{

	ProductDisplayInfoLib prodInfoLib=new ProductDisplayInfoLib();
	CMTLib cmtLib=new CMTLib();
	SearchLib searchLib=new SearchLib();
	CommonLib commonLib=new CommonLib();
	CartLib cartLib=new CartLib();
	ProductDisplayInfoLib prodLib=new ProductDisplayInfoLib();
	LineLevelInfoLib lineLevel=new LineLevelInfoLib();
	CanadaLib canadaLib=new CanadaLib();

	// #############################################################################################################
	// #    Name of the Test         : ROD01_FCTWebReviewOrderAdditionalOrderInfo
	// #    Migration Author         : Cigniti Technologies
	// #
	// #    Date of Migration        : September 2019
	// #    Description              : To Test Place Order basic
	// #    Parameters               : StartRow ,EndRow , nextTestJoin
	// #
	// #############################################################################################################
		@Parameters({ "StartRow", "EndRow", "nextTestJoin" })
		@Test
		public void TC_ROD01(int StartRow,String EndRow, boolean nextTestJoin) throws Throwable {
			int counter =0;
			try {
				int intStartRow = StartRow;
				int intEndRow = ReportControl.fnGetEndRowCunt(EndRow, "ROD01_FCTWebReviewOrderAdditionalOrderInfo", TestData, "Web_Review_Order");
				for (int intCounter = intStartRow; intCounter <= intEndRow; intCounter++) {
					try {
						fnOpenTest();
						ReportStatus.fnDefaultReportStatus();
						ReportControl.intRowCount = intCounter;
						Hashtable<String, String> data = TestUtil.getDataByRowNo("ROD01_FCTWebReviewOrderAdditionalOrderInfo", TestData, "Web_Review_Order", intCounter);
						TestEngineWeb.reporter
								.initTestCaseDescription("FCTWebReviewOrderAdditionalOrderInfo");
						reporter.SuccessReport("Iteration Number : ",
								"**************Iteration Number::  " + intCounter + " For:: " + data.get("LoginName") + " ::and:: "
										+ data.get("Password") + " To Validate::" + data.get("errorMessage") + "  **************","");

						//********************Login to CMT enable Display Additional Notes during the transaction process,************************************************//
						//****** Allow File Upload during Checkout,Display Invoice Notes during the transaction process settings at web group level***********************//
						cmtLib.loginToCMT(data.get("Header"));
						cmtLib.searchForWebGroup( data.get("WebGrp"));
						cmtLib.clickOnTheWebGroup(data.get("WebGrp_Name"));
						String[] permissions=data.get("Customer_Permissions").split(",");
						for(i=0;i<permissions.length;i++){
							cmtLib.setCustomerLevelPermissionsON(permissions[i]);
						}
						cmtLib. hoverOnManageWebGroupsAndSelectOptions(data.get("Manage_Web_Grp_Options"));
						cmtLib.searchForaUserAndSelect(data.get("lnameEmailUname"),data.get("ContactName"));
						cmtLib.loginAsAdminCMT();
						// Back to UAT
						searchLib.searchInHomePage(data.get("IPP_API_Part"));
						// Add a item & warranty to cart >>  proceed To Checkout >> place order >> Verify the review order details,Receipt Order And Date
						searchLib.removeTheFilterForInStockOnly(data.get("In_Stock_Only"));
						prodInfoLib.verifyTheManufacturerNumberInProductDetailsPage(data.get("IPP_API_Part"));
						prodInfoLib.enterQuantityOnProductDetailsPage(data.get("Quantity"));
						commonLib.addToCartAndVerify();
						continueToCheckOutOnAddCart();
						canadaLib.verifyPlaceCartLabel();
						cartLib.verifyItemAddedInCartByMfrNumber(data.get("IPP_API_Part"));
						prodInfoLib.verifyCartPageAndPartDetails();
						addWarrantyInCartPage();
						proceedToCheckout();
						// Enter Smart trackers info
						addRPandWGinfoInAddAdditionalInfo(data.get("RP_HDL_Txt"), data.get("WG_HDL_Txt"), data.get("Additional_Notes"), data.get("Invoice_Notes"));
						clickOnLinelevelInfoOptionalLink();
						enterRP_WP_Info_lineLevel(data.get("RP_LNL_Txt"), data.get("WG_LNL_Txt"));
						canadaLib.verifySBP();
						clickContinueOnShippingAddress();
						enterAttentionField( data.get("Card_Name"));
						shippingOptionsCarrierSelection();
						billingAddressContinueButton();
						//**************************** Enter payment Info *****************************************//
						selectPaymentInfoMethodCreditCard(data.get("Card_Number").toString(), data.get("Card_Name"), data.get("Month"), data.get("Year"),data.get("PO_Number"),data.get("POReleaseNumber"));
						clickOnReviewOrderButton();
						verifyPlaceOrderLabel();
						verifyReviewOrderPageDetails(data.get("Additional_Notes"), data.get("Invoice_Notes"));
						verifyRP_HDL_TxtOnPlaceOrderPage(data.get("RP_HDL_Txt"));
						verifyWG_HDL_TxtOnPlaceOrderPage(data.get("WG_HDL_Txt"));
						verifyRP_LNL_TxtOnPlaceOrderPage(data.get("IPP_API_Part"), data.get("RP_LNL_Txt"));
						verifyWG_LNL_TxtOnPlaceOrderPage(data.get("IPP_API_Part"), data.get("WG_LNL_Txt"));
						String summaryAmount1=cartLib.getSummaryAmountInCart();
						placeOrderAndVerifyReceiptOrderAndDate(summaryAmount1);
						
						// Click company standards and add a bundle of items
						commonLib.clickAccountToolsFromSideMenuAndClickOnProductGrp(data.get("Tools_Menu"), data.get("Tools_Menu_DD"),data.get("Product_Group"),data.get("Product_Name"));
						commonLib.addToOrderAndViewCart();
						cartLib.verifyProductGroupBundleAddedToCart(data.get("Product_Name"));
						lineLevel.verifyBundleIsAddedToCart(data.get("Bundle_Name"));
						proceedToCheckout();
						addRPandWGinfoInAddAdditionalInfo(data.get("RP_HDL_Txt2"), data.get("WG_HDL_Txt2"), data.get("Additional_Notes2"), data.get("Invoice_Notes2"));
						clickOnLinelevelInfoOptionalLink();
						lineLevel.verifyBundleIsAddedToCart(data.get("Bundle_Name"));
						enterRP_WP_Info_lineLevel(data.get("RP_LNL_Txt2"), data.get("WG_LNL_Txt2"));
						canadaLib.verifySBP();
						clickContinueOnShippingAddress();
						enterAttentionField( data.get("Card_Name"));
						shippingOptionsCarrierSelection();
						billingAddressContinueButton();
						selectPaymentInfoMethodCreditCard(data.get("Card_Number").toString(), data.get("Card_Name"), data.get("Month"), data.get("Year"),data.get("PO_Number"),data.get("POReleaseNumber"));
						clickOnReviewOrderButton();
						verifyPlaceOrderLabel();
						verifyReviewOrderPageDetails(data.get("Additional_Notes2"), data.get("Invoice_Notes2"));
						verifyRP_HDL_TxtOnPlaceOrderPage(data.get("RP_HDL_Txt2"));
						verifyWG_HDL_TxtOnPlaceOrderPage(data.get("WG_HDL_Txt2"));
						verifyRP_LNL_TxtOnPlaceOrderPageForBundles(data.get("Bundle_Name"), data.get("RP_LNL_Txt2"));
						verifyWG_LNL_TxtOnPlaceOrderPageForBundles(data.get("Bundle_Name"), data.get("WG_LNL_Txt2"));
						
						String summaryAmount2=cartLib.getSummaryAmountInCart();
						placeOrderAndVerifyReceiptOrderAndDate(summaryAmount2);
						commonLib.clickLogOutLink(data.get("Logout"));
						
						System.out.println("Test completed");
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
				ReportStatus.fnUpdateResultStatus("ROD", "ROD01", ReportStatus.strMethodName, 1, browser);
				throw new RuntimeException(e);
			}


			finally {
	        	ReportControl.fnEnableJoin();
				ReportStatus.fnUpdateResultStatus("ROD", "ROD01", ReportStatus.strMethodName, counter, browser);
				fnCloseTest();
				ReportControl.fnNextTestJoin(nextTestJoin);
			}
		}

}


