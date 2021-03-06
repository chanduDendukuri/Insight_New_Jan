package com.insight.WebTest.Canada;

import java.util.Hashtable;

import com.insight.Lib.*;
import com.insight.accelerators.ReportControl;
import com.insight.accelerators.TestEngineWeb;
import com.insight.googledrive.ReportStatus;
import com.insight.utilities.TestUtil;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class CAN01_ShipBillPayTaxTest extends CanadaLib{


	CMTLib cmtLib = new CMTLib();
	CommonLib commonLib = new CommonLib();
	CartLib cartLib = new CartLib();
	SearchLib searchLib = new SearchLib();
	ProductDetailLib prodDetailsLib=new ProductDetailLib();
	OrderLib orderLib=new OrderLib();
	ProductDisplayInfoLib pipLib=new ProductDisplayInfoLib();
	ShipBillPayLib sbpLib=new ShipBillPayLib();
	   
	    // #############################################################################################################
		// #       Name of the Test         :  CAN01_ShipBillPayTaxTest
		// #       Migration Author         :  Cigniti Technologies
		// #
		// #       Date of Migration        : October 2019
		// #       DESCRIPTION              : This method is to verify MenuSearch for canada
		// #       Parameters               : StartRow ,EndRow , nextTestJoin
		// #
		// ###############################################################################################################
		@Parameters({ "StartRow", "EndRow", "nextTestJoin" })
		@Test
		public void TC_CAN01(int StartRow,String EndRow, boolean nextTestJoin) throws Throwable {
		

						int counter = 0;
						try {
							int intStartRow = StartRow;
							int intEndRow = ReportControl.fnGetEndRowCunt(EndRow, "CAN01_ShipBillPayTax", TestDataInsight, "Canada");
							for (int intCounter = intStartRow; intCounter <= intEndRow; intCounter++) {
								try {
									counter = intCounter;
									fnOpenTest();
									ReportStatus.fnDefaultReportStatus();
									ReportControl.intRowCount = intCounter;
									Hashtable<String, String> data = TestUtil.getDataByRowNo("CAN01_ShipBillPayTax", TestDataInsight,
											"Canada", intCounter);
									TestEngineWeb.reporter.initTestCaseDescription("ShipBillPayTax");				


						CommonLib commonLib = new CommonLib();
						CMTLib cmtLib = new CMTLib();
						CartLib cartLib = new CartLib();
						OrderLib orderLib = new OrderLib();
						ShipBillPayLib shipbLib = new ShipBillPayLib();
						CanadaLib canadaLib = new CanadaLib();
						ProductDisplayInfoLib prodinfo=new ProductDisplayInfoLib();
						ProductDetailLib productdetail=new ProductDetailLib();

					
						cmtLib.loginToCMT(data.get("Header"));
						cmtLib.searchForWebGroup(data.get("WebGrp"));
						cmtLib.clickOnTheWebGroup(data.get("Webgrpname"));
						cmtLib.verifyManageWebGroupSettings();
						cmtLib.hoverOnManageWebGroupsAndSelectOptions(data.get("ManageWebGrpOptions"));
						cmtLib.verifyManageWebGroupsUserManagement();
						cmtLib.searchForaUserAndSelect(data.get("lnameEmailUname"), data.get("contactName"));
						cmtLib.setPermissions(data.get("Menu_Name"), data.get("Set_Permission"));
						//cmtLib.setPermissions(data.get("Menu_Name"),data.get("Enable_Purchasing_Popup"));
						cmtLib.clickOnloginAs();
						switchToChildWindow();
						cmtLib.loginVerification("User - "+data.get("contactName"));
						//cmtLib.loginVerificationByGetText("User - "+data.get("ContactName"));
						//cmtLib.loginVerification("Canada Test");
						
						
						
						/*
					 * cmtLib.clickOnloginAs(); cmtLib.loginVerification(data.get("contactName"));
					 * switchToChildWindow();
					 */
									shipbLib.verifyWEbsiteIsCannada();
						canadaLib.verifyCanadaWebgroup();
						// Adding first product to cart
						commonLib.searchProduct(data.get("Search_Item"));
						searchLib.verifyTheResultsForSearchTerm(data.get("Search_Item"));
						searchLib.removeTheFilterForInStockOnly("filter");
						//commonLib.addFirstDisplyedItemToCartAndVerify();
						prodinfo.getPartNumberInSearchResultsPage();
						searchLib.increaseQuantity(data.get("Quantity"));
						commonLib.addToCartAndVerifyInSearchPage();
//						commonLib.continueToShopping();
//						commonLib.clickCart();
						canadaLib.continueToCheckout();
						cartLib.verifyCartPageAvailablity();
						prodinfo.verifyCartPageAndPartDetails();
						
						// Adding second product to cart
						commonLib.searchProduct(data.get("Search_Item1"));
						//should add code to verify product details
						//productdetail.
						prodinfo.verifyTheManufacturerNumberInProductDetailsPage(data.get("Search_Item1"));
						commonLib.updateCartQuantityInProductDetailsPage(data.get("Quantity"));
						prodinfo.addToCartInProductDetailsPage();
						//searchLib.removeTheFilterForInStockOnly(data.get("filter"));
						//commonLib.addFirstDisplyedItemToCartAndVerify();
						
						canadaLib.continueToCheckout();
						cartLib.verifyCartPageAvailablity();
						prodinfo.verifyCartPageAndPartDetailsForRecentlyItem();

						shipbLib.verifyPriceIsCAD(data.get("CANDAIAN_DOLLAR"));
						shipbLib.getSummaryAmountsInCart(data.get("SubTotal"), data.get("Total"));
						// proceed to check out
						orderLib.proceedToCheckout();
						cartLib.clickOnContinueButtonInAddInformtion();
						canadaLib.verifySBP();
						orderLib.shippingBillPayContinueButton();
						orderLib.shippingOptionsCarrierSelection(); // Click continue on shipping options
						orderLib.billingAddressContinueButton();
						orderLib.termsInPaymentInfo(data.get("PONumber"), data.get("POReleaseNumber"));
						orderLib.verifyPlaceOrderLabel();
						String PSTAMOUNT = canadaLib.getPSTInSummary(data.get("PST"));
						String GSTAMOUNT = canadaLib.getGSTInSummary(data.get("GST"));
						shipbLib.verifyPriceIsCAD(data.get("CANDAIAN_DOLLAR"));
						shipbLib.getSummaryAmountsInCart(data.get("SubTotal"), data.get("Total"));
						canadaLib.clickReturnToCart();
						commonLib.spinnerImage();
						canadaLib.verifyPlaceCartLabel();
						commonLib.updateCartQuantity(data.get("quantity"));
						// proceed to check out
						orderLib.proceedToCheckout();
						cartLib.clickOnContinueButtonInAddInformtion();
						canadaLib.verifySBP();
						orderLib.shippingBillPayContinueButton();
						orderLib.shippingOptionsCarrierSelection(); // Click continue on shipping options
						orderLib.shippingBillPayContinueButton();

						canadaLib.verifyGSTAndPSTInCartPage(data.get("PST"), data.get("GST"));
						orderLib.termsInPaymentInfo(data.get("PONumber"), data.get("POReleaseNumber"));
						canadaLib.verifyGSTAndPSTInCartPage(data.get("PST"), data.get("GST"));
						String PSTAMOUNT1 = canadaLib.getPSTInSummary(data.get("PST"));
						String GSTAMOUNT1 = canadaLib.getGSTInSummary(data.get("GST"));
						canadaLib.verifyGSTAmonunts(GSTAMOUNT, GSTAMOUNT1);
						shipbLib.verifyPriceIsCAD(data.get("CANDAIAN_DOLLAR"));
						shipbLib.getSummaryAmountsInCart(data.get("SubTotal"), data.get("Total"));
						String summaryAmount = cartLib.getSummaryAmountInCart();
						orderLib.placeOrderAndVerifyReceiptOrderAndDate(summaryAmount);
						shipbLib.clickOrderDetailsButtonInREceipt();
						canadaLib.verifyGSTAndPSTInCartPage(data.get("PST"), data.get("GST"));
						String PSTAMOUNT2 = canadaLib.getPSTInSummary(data.get("PST"));
						String GSTAMOUNT2 = canadaLib.getGSTInSummary(data.get("GST"));
						canadaLib.verifyGSTAmonunts(GSTAMOUNT, GSTAMOUNT2);
						shipbLib.verifyPriceIsCAD(data.get("CANDAIAN_DOLLAR"));
						shipbLib.getSummaryAmountsInCart(data.get("SubTotal"), data.get("Total"));
						commonLib.clickLogOutLink(data.get("Logout_Header"));
						
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
							//gErrorMessage = e.toString();
							gTestStatus = false;
							ReportStatus.fnUpdateResultStatus("ShipBillPayTax", "TC_CAN01", ReportStatus.strMethodName, 1, browser);
							throw new RuntimeException(e);
						}
				        finally {
				        	ReportControl.fnEnableJoin();
							ReportStatus.fnUpdateResultStatus("ShipBillPayTax", "TC_CAN01", ReportStatus.strMethodName, counter, browser);
							fnCloseTest();
							ReportControl.fnNextTestJoin(nextTestJoin);
						}
					}
	}

