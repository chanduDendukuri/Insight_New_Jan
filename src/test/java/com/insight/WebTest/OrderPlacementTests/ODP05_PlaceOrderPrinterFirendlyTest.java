package com.insight.WebTest.OrderPlacementTests;

import com.insight.Lib.*;
import com.insight.accelerators.ReportControl;
import com.insight.accelerators.TestEngineWeb;
import com.insight.googledrive.ReportStatus;
import com.insight.utilities.TestUtil;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.util.Hashtable;
import java.util.List;

public class ODP05_PlaceOrderPrinterFirendlyTest extends OrderLib{

	ProductDisplayInfoLib prodInfoLib = new ProductDisplayInfoLib();
	CMTLib cmtLib = new CMTLib();
	SearchLib searchLib = new SearchLib();
	CommonLib commonLib = new CommonLib();
	CartLib cartLib = new CartLib();
	ProductDisplayInfoLib prodLib = new ProductDisplayInfoLib();
	OrderLib orderLib =new OrderLib();

	// #############################################################################################################
	// #    Name of the Test         : ODP05_PlaceOrderPrinterFirendly
	// #    Migration Author         : Cigniti Technologies
	// #
	// #    Date of Migration        : September 2019
	// #    Description              : To Test Place Order  Confirmations
	// #    Parameters               : StartRow ,EndRow , nextTestJoin
	// #
	// #############################################################################################################

	@Parameters({ "StartRow", "EndRow", "nextTestJoin" })
		@Test
		public void TC_ODP05(int StartRow,String EndRow, boolean nextTestJoin) throws Throwable {
		int counter = 0;
		try {
			int intStartRow = StartRow;
			int intEndRow = ReportControl.fnGetEndRowCunt(EndRow, "ODP05_PlaceOrderPrinterFirendly",
					TestData, "Web_Order_Placement");
			for (int intCounter = intStartRow; intCounter <= intEndRow; intCounter++) {
				try {
					counter = intCounter;
					fnOpenTest();
					ReportStatus.fnDefaultReportStatus();
					ReportControl.intRowCount = intCounter;
					Hashtable<String, String> data = TestUtil.getDataByRowNo("ODP05_PlaceOrderPrinterFirendly",
							TestData, "Web_Order_Placement", intCounter);
					TestEngineWeb.reporter.initTestCaseDescription("PlaceOrderPrinterFirendly");

			

                      // Login to CMT and disable Allow File Upload during Checkout,Override Payment Options
						cmtLib.loginToCMT(data.get("Header"));
						cmtLib.searchForWebGroup(data.get("WebGrp"));
						cmtLib.clickOnTheWebGroup(data.get("WebGrp_Name"));

						// Uncheck Override Payments Options in web Group Level
						cmtLib.setCustomerLevelPermissionsOFF(data.get("Customer_Permissions_OFF"));

						// select Users and Login As
						cmtLib.hoverOnManageWebGroupsAndSelectOptions(data.get("Manage_Web_Grp_Options"));
						cmtLib.searchForaUserAndSelect(data.get("LnameEmailUname"), data.get("ContactName"));
						cmtLib.loginAsAdminCMT();

						// Select First Product and Add to cart
						searchLib.searchInHomePage(data.get("SearchText"));
						commonLib.addFirstDisplyedItemToCartAndVerify();
						continueToCheckOutOnAddCart();

						// Select warranty Product to cart
						searchLib.searchInHomePage(data.get("WarrPartNumber"));
						commonLib.addToCartAndVerify();
						continueToCheckOutOnAddCart();
						//verify part item added in cart
						cartLib.verifyItemInCart(data.get("WarrPartNumber"));

						// verify print popup
						List<String> prodDesc=getProductDescriptionOfCartProduct();
						List<String> quantity=getCartProductQuantity();
						List<String> stock=getCartProductStock();
						List<String> totalPrice=getCartProductTotalPrice();
						List<String> unitPrice=getCartProductUnitPrice();

						clickPrintIconOnCartPage(data.get("Order_Utilities"));
						VerifyPrintPopup(prodDesc,quantity,stock,totalPrice,unitPrice);
						cartLib.closePrintPopUp();
						proceedToCheckout();
						continueButtonOnAdditionalInformationSection();  // Click continue on Additional information Section
						clickContinueOnLineLevelInfo(); // Click continue on Line Level information Section
						shippingBillPayContinueButton();  // continue button on Shipping address
						shippingOptionsCarrierSelection();  // carrier selection or continue in shipping options
						shippingBillPayContinueButton();  // Continue on billing address section

						selectPaymentInfoMethodCreditCard(data.get("Card_Number").toString(), data.get("Card_Name"),data.get("Month"), data.get("Year"),data.get("PO_Number"),data.get("POReleaseNumber"));  // American Express card

						clickOnReviewOrderButton();
						//Place Order
						String summaryAmount=cartLib.getSummaryAmountInCart();
						placeOrderAndVerifyReceiptOrderAndDate(summaryAmount);
						//Verify Receipt
						verifyReceiptVerbiage();
						//selecting bundle from company standards page
						commonLib.clickAccountToolsFromSideMenuAndClickOnProductGrp(data.get("toolsMenuName"),data.get("dropDown") ,data.get("productGroup"),data.get("productName"));
						searchLib.clickAddToOrderOnCompanyStandardsScreen();
						orderLib.verifyCartHeaderLabel();
						//verify print popup

						// verify print popup
						List<String> prodDesc1=getProductDescriptionOfCartProduct();
						List<String> quantity1=getCartProductQuantity();
						List<String> stock1=getCartProductStock();
						List<String> totalPrice1=getCartProductTotalPrice();
						List<String> unitPrice1=getCartProductUnitPrice();

						clickPrintIconOnCartPage(data.get("Order_Utilities"));
						VerifyPrintPopup(prodDesc1,quantity1,stock1,totalPrice1,unitPrice1);
						cartLib.closePrintPopUp();
						proceedToCheckout();
						continueButtonOnAdditionalInformationSection();  // Click continue on Additional information Section
						clickContinueOnLineLevelInfo(); // Click continue on Line Level information Section
						shippingBillPayContinueButton();  // continue button on Shipping address
						shippingOptionsCarrierSelection();  // carrier selection or continue in shipping options
						shippingBillPayContinueButton();  // Continue on billing address section

						selectPaymentInfoMethodCreditCard(data.get("Card_Number1").toString(), data.get("Card_Name"),data.get("Month"), data.get("Year"),data.get("PO_Number"),data.get("POReleaseNumber"));  // American Express card

						clickOnReviewOrderButton();
						//Place Order
						String summaryAmount1=cartLib.getSummaryAmountInCart();
						placeOrderAndVerifyReceiptOrderAndDate(summaryAmount1);
						//Verify Receipt
						verifyReceiptVerbiage();
						//Logout
						cmtLib.navigateBackToCMT();
						cmtLib.hoverOverMasterGroupAndSelectChangeGrp();
						//Login with 2nd User
						cmtLib.searchForWebGroup(data.get("WebGrp1"));
						cmtLib.clickOnTheWebGroup(data.get("WebGrp_Name1"));
						cmtLib.hoverOnManageWebGroupsAndSelectOptions(data.get("Manage_Web_Grp_Options"));
						cmtLib.searchForaUserAndSelect(data.get("LnameEmailUname1"), data.get("ContactName1"));
						cmtLib.loginAsAdminCMT();

						// Select warranty Product to cart
						searchLib.searchInHomePage(data.get("SearchText2"));
						commonLib.addFirstDisplyedItemToCartAndVerify();
						continueToCheckOutOnAddCart();
						//Verify print popup
						// verify print popup
						List<String> prodDesc2=getProductDescriptionOfCartProduct();
						List<String> quantity2=getCartProductQuantity();
						List<String> stock2=getCartProductStock();
						List<String> totalPrice2=getCartProductTotalPrice();
						List<String> unitPrice2=getCartProductUnitPrice();

						clickPrintIconOnCartPage(data.get("Order_Utilities"));
						VerifyPrintPopup(prodDesc2,quantity2,stock2,totalPrice2,unitPrice2);
						cartLib.closePrintPopUp();
						proceedToCheckout();
						continueButtonOnAdditionalInformationSection();  // Click continue on Additional information Section
						clickContinueOnLineLevelInfo(); // Click continue on Line Level information Section
						shippingBillPayContinueButton();  // continue button on Shipping address
						shippingOptionsCarrierSelection();  // carrier selection or continue in shipping options
						shippingBillPayContinueButton();  // Continue on billing address section

						selectPaymentInfoMethodCreditCard(data.get("Card_Number").toString(), data.get("Card_Name"),data.get("Month"), data.get("Year"), data.get("poNumebr"),data.get("POReleaseNumber"));  // American Express card

						clickOnReviewOrderButton();
						//Place Order
						String summaryAmount2=cartLib.getSummaryAmountInCart();
						placeOrderAndVerifyReceiptOrderAndDate(summaryAmount2);
						//Verify Receipt
						verifyReceiptVerbiage();
						//Logout
						cmtLib.navigateBackToCMT();
						cmtLib.hoverOverMasterGroupAndSelectChangeGrp();
						//Login with 3rd User
						cmtLib.searchForWebGroup(data.get("WebGrp2"));
						cmtLib.clickOnTheWebGroup(data.get("WebGrp_Name2"));
						cmtLib.hoverOnManageWebGroupsAndSelectOptions(data.get("Manage_Web_Grp_Options"));
						cmtLib.searchForaUserAndSelect(data.get("LnameEmailUname2"), data.get("ContactName2"));
						cmtLib.loginAsAdminCMT();

						// Select warranty Product to cart
						searchLib.searchInHomePage(data.get("SearchText3"));
						commonLib.addFirstDisplyedItemToCartAndVerify();
						continueToCheckOutOnAddCart();
						//Verify print popup

						List<String> prodDesc3=getProductDescriptionOfCartProduct();
						List<String> quantity3=getCartProductQuantity();
						List<String> stock3=getCartProductStock();
						List<String> totalPrice3=getCartProductTotalPrice();
						List<String> unitPrice3=getCartProductUnitPrice();

						clickPrintIconOnCartPage(data.get("Order_Utilities"));
						VerifyPrintPopup(prodDesc3,quantity3,stock3,totalPrice3,unitPrice3);
						cartLib.closePrintPopUp();
						proceedToCheckout();
						cartLib.addAdditionalInformationInCheckOut(data.get("Url"), data.get("RP_HDL_Txt"));
						continueButtonOnAdditionalInformationSection();  // Click continue on Additional information Section
						addLineLevelInfoSmartTracker(data.get("rP_LNL_Txt"));
						clearPhnumberInShippinAddress();
						shippingBillPayContinueButton();  // continue button on Shipping address
						shippingOptionsCarrierSelection();  // carrier selection or continue in shipping options
						shippingBillPayContinueButton();  // Continue on billing address section

						//Adding Visa card
						selectPaymentInfoMethodCreditCard(data.get("Card_Number2").toString(), data.get("Card_Name"),data.get("Month"), data.get("Year"), data.get("poNumebr"),data.get("POReleaseNumber"));
						clickOnReviewOrderButton();
						verifyPlaceOrderLabel();
						//Verify print popup Window
						List<String> prodDesc4=getProductDescriptionOfCartProduct();
						List<String> quantity4=getCartProductQuantity();
						List<String> stock4=getCartProductStock();
						List<String> totalPrice4=getCartProductTotalPrice();
						List<String> unitPrice4=getCartProductUnitPrice();

						clickPrintIconOnCartPage(data.get("Order_Utilities"));
						VerifyPrintPopup(prodDesc4,quantity4,stock4,totalPrice4,unitPrice4);
						cartLib.closePrintPopUp();
						//Place Order
						String summaryAmount3=cartLib.getSummaryAmountInCart();
						placeOrderAndVerifyReceiptOrderAndDate(summaryAmount3);
						//Verify Receipt
						verifyReceiptVerbiage();
						//commonLib.clickLogOutLink(data.get("Logout"));

						// fnCloseTest();
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
				ReportStatus.fnUpdateResultStatus("ODP05_PlaceOrderPrinterFirendly", "TC_ODP05", ReportStatus.strMethodName, 1, browser);
				throw new RuntimeException(e);
			}
	        finally {
	        	ReportControl.fnEnableJoin();
				ReportStatus.fnUpdateResultStatus("ODP05_PlaceOrderPrinterFirendly", "TC_ODP05", ReportStatus.strMethodName, counter, browser);
				fnCloseTest();
				ReportControl.fnNextTestJoin(nextTestJoin);
			}
		}

	}





