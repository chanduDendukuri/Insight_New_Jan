package com.insight.WebTest.OrderPlacementTests;

import com.insight.Lib.*;
import com.insight.accelerators.ReportControl;
import com.insight.accelerators.TestEngineWeb;
import com.insight.googledrive.ReportStatus;
import com.insight.utilities.TestUtil;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.util.Hashtable;

public class ODP11_SmartTrackersPlaceOrderTest extends OrderLib{

	ProductDetailLib proddetailLib = new ProductDetailLib();
	CMTLib cmtLib = new CMTLib();
	SearchLib searchLib = new SearchLib();
	CommonLib commonLib = new CommonLib();
	CartLib cartLib = new CartLib();
	ProductDisplayInfoLib prodLib = new ProductDisplayInfoLib();
	OrderLib orderLib =new OrderLib();
	CanadaLib canadaLib= new CanadaLib();
	

	// #############################################################################################################
	// #    Name of the Test         : ODP11_SmartTrackersPlaceOrder
	// #    Migration Author         : Cigniti Technologies
	// #
	// #    Date of Migration        : September 2019
	// #    Description              : To Test Place Order  Confirmations
	// #    Parameters               : StartRow ,EndRow , nextTestJoin
	// #
	// #############################################################################################################

	@Parameters({ "StartRow", "EndRow", "nextTestJoin" })
		@Test
		public void TC_ODP11(int StartRow,String EndRow, boolean nextTestJoin) throws Throwable {
		int counter = 0;
		try {
			int intStartRow = StartRow;
			int intEndRow = ReportControl.fnGetEndRowCunt(EndRow, "ODP11_SmartTrackersPlaceOrder",
					TestData, "Web_Order_Placement");
			for (int intCounter = intStartRow; intCounter <= intEndRow; intCounter++) {
				try {
					counter = intCounter;
					fnOpenTest();
					ReportStatus.fnDefaultReportStatus();
					ReportControl.intRowCount = intCounter;
					Hashtable<String, String> data = TestUtil.getDataByRowNo("ODP11_SmartTrackersPlaceOrder",
							TestData, "Web_Order_Placement", intCounter);
					TestEngineWeb.reporter.initTestCaseDescription("SmartTrackersPlaceOrder");
			
						// Login to CMT enable Display Additional Notes during the transaction process,Allow File Upload during Checkout,Display Invoice Notes during the transaction process settings at web group level.
						cmtLib.loginToCMT(data.get("Header"));
						cmtLib.searchForWebGroup(data.get("WebGrp"));
						cmtLib.clickOnTheWebGroup(data.get("WebGrp_Name"));
						cmtLib.setCustomerLevelPermissionsOFF(data.get("Customer_Permissions_OFF"));
						cmtLib.hoverOnManageWebGroupsAndSelectOptions(data.get("Manage_Web_Grp_Options"));
						cmtLib.searchForaUserAndSelect(data.get("LnameEmailUname"), data.get("ContactName"));
						cmtLib.loginAsAdminCMT();
						//Select product and add to cart
						searchLib.searchInHomePage(data.get("SearchText"));
						searchLib.verifyBreadCrumbInSearchResultsPage(data.get("SearchText"));
						cartLib.selectFirstProductDisplay();
						String partNum=proddetailLib.getMFRNumberInProductInfopage();
						commonLib.addToCartAndVerify();
						continueToCheckOutOnAddCart();
						orderLib.verifyCartHeaderLabel();
						proceedToCheckout();
						addAdditionalInformation(data.get("Url"), data.get("RP_HDL_Txt"), data.get("WG_HDL_Txt"), data.get("Additional_Notes"), data.get("Invoice_Notes"));
						addLineLevelInfoSmartTracker(data.get("rP_LNL_Txt"));
						canadaLib.verifySBP();
						shippingBillPayContinueButton();
						shippingOptionsCarrierSelection();
						billingAddressContinueButton();
						selectPaymentInfoMethodCreditCard(data.get("cardNumber"),data.get("cardName"),data.get("month"),data.get("year"),data.get("PO_Number"),data.get("POReleaseNumber"));
						// Verify PO NUmber is empty
						verifyPONumberisEmpty();
						clickOnReviewOrderButton();
						verifyPlaceOrderLabel();
						// Verify RP_HDL_Txt and RP_LNL_Txt text on PO page
						verifyRP_HDL_TxtOnPlaceOrderPage(data.get("RP_HDL_Txt"));
						verifyRP_LNL_TxtOnPlaceOrderPage(partNum,data.get("rP_LNL_Txt"));
						//Place Order
						String summaryAmountInLogin=cartLib.getSummaryAmountInCart();
						placeOrderAndVerifyReceiptOrderAndDate(summaryAmountInLogin);
						String RefNumber= orderLib.getTextfromReferenceNumber();
						//Verifying order details
					//	scrollToBottomWithCordinate("110");
						clickOrderDetailsLinkOnReceiptPage();
						searchLib.verifyAccountToolsFromSideMenuAndClick(data.get("toolsMenuName"),data.get("dropDown"));
					    scrollToBottomWithCordinate("110");
						clickonorderNumLinkinRecentorders(RefNumber);
						//Order details Page verification
						getLineItemInfoValues();
						verifytabsinOrderDetailsPage(data.get("TabName"));//order details
						verifySmartTrackerHeaderInOrderDetails(data.get("rP_LNL_Txt"));
						verifytabsinOrderDetailsPage(data.get("TabName1"));// customer details
						verifySmartTrackerHeaderInCustomerDetails(data.get("RP_HDL_Txt"));

						getHeaderLevelItemInfo();
					/*getHeaderLevelItemsInforDynamically("Txt"); //data.get("linetxt");
					getHeaderLevelItemsInforDynamically("Lst");*///data.get("lineLst");
						// Logout
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
				//gErrorMessage = e.toString();
				gTestStatus = false;
				ReportStatus.fnUpdateResultStatus("ODP11_SmartTrackersPlaceOrder", "TC_ODP11", ReportStatus.strMethodName, 1, browser);
				throw new RuntimeException(e);
			}
	        finally {
	        	ReportControl.fnEnableJoin();
				ReportStatus.fnUpdateResultStatus("ODP11_SmartTrackersPlaceOrder", "TC_ODP11", ReportStatus.strMethodName, counter, browser);
				fnCloseTest();
				ReportControl.fnNextTestJoin(nextTestJoin);
			}
		}

	}




