package com.insight.WebTest.RequisitionProcessing;

import java.util.Hashtable;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.insight.Lib.CMTLib;
import com.insight.Lib.CanadaLib;
import com.insight.Lib.CartLib;
import com.insight.Lib.ChinaLib;
import com.insight.Lib.CommonLib;
import com.insight.Lib.OrderHistoryLib;
import com.insight.Lib.OrderLib;
import com.insight.Lib.ProductDetailLib;
import com.insight.Lib.ProductDisplayInfoLib;
import com.insight.Lib.RequisitionProcessingLib;
import com.insight.Lib.SearchLib;
import com.insight.Lib.ShipBillPayLib;
import com.insight.accelerators.ReportControl;
import com.insight.accelerators.TestEngineWeb;
import com.insight.googledrive.ReportStatus;
import com.insight.utilities.TestUtil;

public class REQ01_EndToEndTest extends ChinaLib{
	CommonLib commonLib = new CommonLib();
	CMTLib cmtLib = new CMTLib();
	SearchLib searchLib = new SearchLib();
	OrderLib orderLib = new OrderLib();
	RequisitionProcessingLib ReqLib = new RequisitionProcessingLib();
	ProductDisplayInfoLib prodinfo=new ProductDisplayInfoLib();
	CartLib cartLib = new CartLib();
	OrderHistoryLib orderhistory=new OrderHistoryLib();
	// #############################################################################################################
		// # Name of the Test : REQ01_EndToEnd
		// # Migration Author : Cigniti Technologies
		// #
		// # Date of Migration : October 2019
		// # Description : This Test is used to Test Requisition end to end flow
		// # Parameters : StartRow ,EndRow , nextTestJoin
		// #
		// #############################################################################################################
        @Parameters({ "StartRow", "EndRow", "nextTestJoin" })
		@Test
		public void TC_REQ01(int StartRow,String EndRow, boolean nextTestJoin) throws Throwable {
        	int counter = 0;
			try {
				int intStartRow = StartRow;
				int intEndRow = ReportControl.fnGetEndRowCunt(EndRow, "REQ01_EndToEnd",
						TestData, "Requisition_Processing");
				for (int intCounter = intStartRow; intCounter <= intEndRow; intCounter++) {
					try {
						counter = intCounter;
						fnOpenTest();
						ReportStatus.fnDefaultReportStatus();
						ReportControl.intRowCount = intCounter;
						Hashtable<String, String> data = TestUtil.getDataByRowNo("REQ01_EndToEnd",
								TestData, "Requisition_Processing", intCounter);
						TestEngineWeb.reporter.initTestCaseDescription("EndToEndTest");

						// select language and country
						cmtLib.loginToCMT(data.get("Header"));
						cmtLib.searchForWebGroup(data.get("WebGrp"));
						cmtLib.clickOnTheWebGroup(data.get("WebGrp_Name"));
						cmtLib.hoverOnManageWebGroupsAndSelectOptions(data.get("Manage_Web_Grp_Options"));
						// LogIN with 1st User
						cmtLib.searchForaUserAndSelect(data.get("LnameEmailUname"), data.get("ContactName")); // Req1
						cmtLib.setPermissions(data.get("menuName"), data.get("userPermissions"));
						cmtLib.loginAsAdminCMT();

						searchLib.searchInHomePage(data.get("SearchItem"));
						searchLib.verifyBreadCrumbInSearchResultsPage(data.get("SearchItem"));
						
						prodinfo.getPartNumberInSearchResultsPage();

						// Add a item to cart >> proceed To Checkout >> place order >>
						// Verify the review order details,Receipt Order And Date
						commonLib.addFirstDisplyedItemToCartAndVerify();
						orderLib.continueToCheckOutOnAddCart();
						cartLib.verifyCartPageAvailablity();
						// Add 2nd product to the cart
						searchLib.searchInHomePage(data.get("SearchItem1"));
						searchLib.verifyBreadCrumbInSearchResultsPage(data.get("SearchItem1"));
						prodinfo.getPartNumberInSearchResultsPage();
						commonLib.addFirstDisplyedItemToCartAndVerify();
						orderLib.continueToCheckOutOnAddCart();
						cartLib.verifyCartPageAvailablity();
						// Select web requestor group name from dropdown
						ReqLib.selectRequestorGroupName(data.get("ReqName"));
						orderLib.proceedToCheckout();

						orderLib.continueButtonOnAdditionalInformationSection();
						orderLib.clickContinueOnLineLevelInfo(); // Click continue on Line
						/////
						
						// level Info
						ReqLib.clearPhoneNumber();
						orderLib.shippingBillPayContinueButton(); // Click continue on
																	// shipping address
						orderLib.shippingOptionsCarrierSelection(); // Click continue on
																	// shipping options
						orderLib.shippingBillPayContinueButton(); // Billing address
																	// continue button
						orderLib.selectPaymentInfoMethodCreditCard(data.get("Card_Number").toString(), data.get("Card_Name"),
								data.get("Month"), data.get("Year"),data.get("PONumber"),data.get("POReleaseNumber"));
						orderLib.clickOnReviewRequisitionButton();
						orderLib.verifyPlaceOrderLabel();
						orderLib.clickOnPlaceRequisitionButton();
					
						// Verify Receipt
						Thread.sleep(4000);
						orderLib.verifyReceiptVerbiage();
						String RefNumber = orderLib.getTextfromReferenceNumber();
						orderLib.getOrderDate();
						commonLib.clickLogOutLink(data.get("header1"));

						// LogIN with 2nd User
						cmtLib.navigateBackToCMT();
						
						cmtLib.hoverOnManageWebGroupsAndSelectOptions(data.get("Manage_Web_Grp_Options"));
						cmtLib.searchForaUserAndSelect(data.get("LnameEmailUname1"), data.get("ContactName1")); // app1
						cmtLib.loginAsAdminCMT();
						searchLib.verifyAccountToolForOrderMenuItem(data.get("toolsMenuName"), data.get("dropDown"));
						orderLib.verifyandClickonRefLink(RefNumber);
						orderLib.verifyApprovalManagmentandClickUpdate();
						commonLib.clickLogOutLink(data.get("header1"));
						// LogIN with 3rd User
						cmtLib.navigateBackToCMT();
						cmtLib.hoverOnManageWebGroupsAndSelectOptions(data.get("Manage_Web_Grp_Options"));
						cmtLib.searchForaUserAndSelect(data.get("LnameEmailUname2"), data.get("ContactName2")); // app2
						cmtLib.loginAsAdminCMT();
						searchLib.verifyAccountToolForOrderMenuItem(data.get("toolsMenuName"), data.get("dropDown"));
						orderLib.verifyandClickonRefLink(RefNumber);
						orderLib.verifyApprovalManagmentandClickUpdate();
						commonLib.clickLogOutLink(data.get("header1"));
						// LogIN with 2nd User in order to verify approved reference link
						cmtLib.navigateBackToCMT();
						cmtLib.hoverOnManageWebGroupsAndSelectOptions(data.get("Manage_Web_Grp_Options"));
						cmtLib.searchForaUserAndSelect(data.get("LnameEmailUname1"), data.get("ContactName1")); // app1
						cmtLib.loginAsAdminCMT();
						searchLib.verifyAccountToolForOrderMenuItem(data.get("toolsMenuName"), data.get("dropDown"));
						ReqLib.verifyorderNumLinkinReqHistoryPage(RefNumber);
						commonLib.clickLogOutLink(data.get("header1"));

						// LogIN with 5th User
						cmtLib.navigateBackToCMT();
						cmtLib.hoverOnManageWebGroupsAndSelectOptions(data.get("Manage_Web_Grp_Options"));
						cmtLib.searchForaUserAndSelect(data.get("LnameEmailUname3"), data.get("ContactName3")); // app4
						cmtLib.loginAsAdminCMT();
						searchLib.verifyAccountToolForOrderMenuItem(data.get("toolsMenuName"), data.get("dropDown"));
						orderLib.verifyandClickonRefLink(RefNumber);
						commonLib.clickLogOutLink(data.get("header1"));
						
						
						cmtLib.navigateBackToCMT();
						cmtLib.hoverOnManageWebGroupsAndSelectOptions(data.get("Manage_Web_Grp_Options"));
						cmtLib.searchForaUserAndSelect(data.get("LnameEmailUname3"), data.get("ContactName3")); // app4
						cmtLib.loginAsAdminCMT();
						searchLib.verifyAccountToolForOrderMenuItem(data.get("toolsMenuName"), data.get("dropDown"));
						orderLib.verifyandClickonRefLink(RefNumber);					
						ReqLib.enterPOandPORelease(data.get("PONum"), data.get("PORelese"));
						// get PO and POrelase numbers
						String PO = data.get("PONum");
						String PORelease = data.get("PORelese");
						ReqLib.enterPOandPOReleaseandUpdate();
						commonLib.clickLogOutLink(data.get("header1"));
						///////
						/////
						// LogIN with 1st User
						cmtLib.navigateBackToCMT();
						cmtLib.hoverOnManageWebGroupsAndSelectOptions(data.get("Manage_Web_Grp_Options"));
						cmtLib.searchForaUserAndSelect(data.get("LnameEmailUname"), data.get("ContactName"));
						cmtLib.verifyWebGroupsManagementUsers();
						
						// req1
						cmtLib.loginAsAdminCMT();
						searchLib.verifyAccountToolForOrderMenuItem(data.get("toolsMenuName"), data.get("dropDown1")); // Order
						orderhistory.verifyOrderHistoryPage();	
						orderhistory.selectQuickSearchDropdown(data.get("sortby"), RefNumber);
						//orderhistory.verifySearchResultsAreDisplayed();
						orderhistory.clickOnFirstOrderHistoryResult();
						
						// history
						
						/////
						
						// Verifying PO Numbers
						ReqLib.verifyPOandPORelease(RefNumber, PO, PORelease);
						// Logout
						commonLib.clickLogOutLink(data.get("header1"));
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
				ReportStatus.fnUpdateResultStatus("REQ01_EndToEndTest", "TC_REQ01", ReportStatus.strMethodName, 1, browser);
				throw new RuntimeException(e);
			}
	        finally {
	        	ReportControl.fnEnableJoin();
				ReportStatus.fnUpdateResultStatus("REQ01_EndToEndTest", "TC_REQ01", ReportStatus.strMethodName, counter, browser);
				fnCloseTest();
				ReportControl.fnNextTestJoin(nextTestJoin);
			}
	
					}
	
}


