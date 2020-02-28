package com.insight.WebTest.Canada;

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
import com.insight.Lib.SearchLib;
import com.insight.Lib.ShipBillPayLib;
import com.insight.accelerators.ReportControl;
import com.insight.accelerators.TestEngineWeb;
import com.insight.googledrive.ReportStatus;
import com.insight.utilities.TestUtil;

public class CAN04_ShipBillPayFreightTest extends CanadaLib{
	CMTLib cmtLib = new CMTLib();
	CommonLib commonLib = new CommonLib();
	CartLib cartLib = new CartLib();
	SearchLib searchLib = new SearchLib();
	ProductDetailLib prodDetailsLib=new ProductDetailLib();
	OrderLib orderLib=new OrderLib();
	ProductDisplayInfoLib pipLib=new ProductDisplayInfoLib();
	ShipBillPayLib sbpLib=new ShipBillPayLib();
	   
	    // #############################################################################################################
		// #       Name of the Test         :  CAN04_ShipBillPayFreightTest
		// #       Migration Author         :  Cigniti Technologies
		// #
		// #       Date of Migration        : October 2019
		// #       DESCRIPTION              : This method is to verify MenuSearch for canada
		// #       Parameters               : StartRow ,EndRow , nextTestJoin
		// #
		// ###############################################################################################################
		@Parameters({ "StartRow", "EndRow", "nextTestJoin" })
		@Test
		public void TC_CAN04(int StartRow,String EndRow, boolean nextTestJoin) throws Throwable {
		
						int counter = 0;
						try {
							int intStartRow = StartRow;
							int intEndRow = ReportControl.fnGetEndRowCunt(EndRow, "CAN04_ShipBillPayFreight", TestDataInsight, "Canada");
							for (int intCounter = intStartRow; intCounter <= intEndRow; intCounter++) {
								try {
									counter = intCounter;
									fnOpenTest();
									ReportStatus.fnDefaultReportStatus();
									ReportControl.intRowCount = intCounter;
									Hashtable<String, String> data = TestUtil.getDataByRowNo("CAN04_ShipBillPayFreight", TestDataInsight,
											"Canada", intCounter);
									TestEngineWeb.reporter.initTestCaseDescription("ShipBillPayFreight");		

						CommonLib commonLib = new CommonLib();
						CMTLib cmtLib = new CMTLib();
						CartLib cartLib = new CartLib();
						OrderLib orderLib = new OrderLib();
						ShipBillPayLib shipbLib = new ShipBillPayLib();
						CanadaLib canadaLib = new CanadaLib();
						ProductDisplayInfoLib prodinfo = new ProductDisplayInfoLib();
//Common Across all the testcases Starts from here
									cmtLib.loginToCMT(data.get("Header"));
									cmtLib.searchForWebGroup(data.get("WebGrp"));
									cmtLib.clickOnTheWebGroup(data.get("MgContactName"));
									cmtLib.verifyManageWebGroupSettings();
									cmtLib.hoverOnManageWebGroupsAndSelectOptions(data.get("ManageWebGrpOptions"));
									cmtLib.verifyManageWebGroupsUserManagement();
									cmtLib.searchForaUserAndSelect(data.get("LnameEmailUname"), data.get("ContactName"));
									cmtLib.setPermissions(data.get("Menu_Name"), data.get("Set_Permission"));
									//cmtLib.setPermissions(data.get("Menu_Name"),data.get("Enable_Purchasing_Popup"));
									cmtLib.clickOnloginAs();
									switchToChildWindow();
									cmtLib.loginVerification(data.get("ContactName"));
									shipbLib.verifyWEbsiteIsCannada();
									canadaLib.verifyCanadaWebgroup();

//End here

						// Adding first product to cart
									commonLib.searchProduct(data.get("Search_Item"));
									prodinfo.verifyTheManufacturerNumberInProductDetailsPage(data.get("Search_Item"));
									commonLib.updateCartQuantityInProductDetailsPage(data.get("Quantity"));
									//prodinfo.addToCartInProductDetailsPage();
									commonLib.addToCartAndVerify();

						canadaLib.continueToCheckout();
						cartLib.verifyCartPageAvailablity();
						//prodinfo.verifyCartPageAndPartDetailsForRecentlyItem();
						prodinfo.verifyCartPageAndPartDetailsForRecentlyItemDynamically(data.get("Search_Item"));


						commonLib.searchProduct(data.get("Search_Item1"));
						prodinfo.getPartNumberInSearchResultsPage();
						commonLib.addFirstDisplyedItemToCartAndVerify();
						String partNumber1 = cartLib.getPartNumber();
						System.out.println("partNumber1"+partNumber1);
//						commonLib.continueToShopping();
//						commonLib.clickCart();
						canadaLib.continueToCheckout();
									cartLib.verifyCartPageAvailablity();
									//prodinfo.verifyCartPageAndPartDetails();
									prodinfo.verifyCartPageAndPartDetailsForRecentlyItemDynamically(data.get("Search_Item1"));

									cartLib.verifyItemInCart(partNumber1);


// **********************************End of Second search value ***************************************
// ++++++++++++++++++++++++++++++++++End of Third search value *+++++++++++++++++++++++++++++++++++++*

									commonLib.searchProduct(data.get("Search_Item2"));
									prodinfo.getPartNumberInSearchResultsPage();
									commonLib.addFirstDisplyedItemToCartAndVerify();
									String partNumber2 = cartLib.getPartNumber();
									System.out.println("partNumber2"+partNumber1);
//						commonLib.continueToShopping();
//						commonLib.clickCart();
									canadaLib.continueToCheckout();
									cartLib.verifyCartPageAvailablity();
									prodinfo.verifyCartPageAndPartDetailsForRecentlyItemDynamically(partNumber2);
									//prodinfo.verifyCartPageAndPartDetails();
									cartLib.verifyItemInCart(partNumber2);
									//prodinfo.verifyCartPageAndPartDetailsForRecentlyItemDynamically(data.get("Search_Item2"));

									// proceed to check out
//$$$$$$$$$$$$$$$$$$$$$$$$End of 3rd prod $$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$
									orderLib.proceedToCheckout();
									cartLib.clickOnContinueButtonInAddInformtion();
									orderLib.clickContinueOnLineLevelInfo();
									canadaLib.verifySBP();
									//Lakshmi
									orderLib.clickContinueOnShippingAddress();
									//Lakshmi
									orderLib.getgroundOptionInShipmentOptionsPage();
									//orderLib.clickContinueOnShippingAddress();
									reporter.SuccessReport("Air Values in shipment options ","Air data in shipment options is ",orderLib.getAirValueFromShipbillOptions());
									reporter.SuccessReport("Ground Values in shipment options ","Ground data in shipment options is ",orderLib.getGroundValueFromShipbillOptions());
									canadaLib.verifyGroundIsDefaultShippingOption();

									orderLib.shippingBillPayContinueButton();
									orderLib.billingAddressContinueButton();
									orderLib.shippingOptionsCarrierSelection();
									orderLib.termsInPaymentInfo(data.get("PONumber"), data.get("POReleaseNumber"));
									orderLib.verifyPlaceOrderLabel();
									orderLib.availabilityOfCanadaGroundInSummaryTotal();
									String summaryAmount1 = cartLib.getSummaryAmountInCart();
									orderLib.placeOrderAndVerifyReceiptOrderAndDate(summaryAmount1);
									commonLib.clickLogOutLink(data.get("Logout_Header"));//fnCloseTest();
									System.out.println("Test completed");
					
								} catch (Exception e) {
									ReportStatus.blnStatus = false;
								//	gErrorMessage = e.getMessage();
									gTestStatus = false;
								}
							}
						} catch (Exception e) {
							e.printStackTrace();
							ReportStatus.blnStatus = false;
							//gErrorMessage = e.toString();
							gTestStatus = false;
							ReportStatus.fnUpdateResultStatus("ShipBillPayFreight", "TC_CAN04", ReportStatus.strMethodName, 1, browser);
							throw new RuntimeException(e);
						}
				        finally {
				        	ReportControl.fnEnableJoin();
							ReportStatus.fnUpdateResultStatus("ShipBillPayFreight", "TC_CAN04", ReportStatus.strMethodName, counter, browser);
							fnCloseTest();
							ReportControl.fnNextTestJoin(nextTestJoin);
						}
					}
	}
