package com.insight.WebTest.OrderPlacementTests;

import com.insight.Lib.*;
import com.insight.accelerators.ReportControl;
import com.insight.accelerators.TestEngineWeb;
import com.insight.googledrive.ReportStatus;
import com.insight.utilities.TestUtil;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.util.Hashtable;

public class ODP15_OrderswithMultipleBOMsRequisitionTest extends OrderLib{

	ProductDisplayInfoLib prodInfoLib = new ProductDisplayInfoLib();
	CMTLib cmtLib = new CMTLib();
	SearchLib searchLib = new SearchLib();
	CommonLib commonLib = new CommonLib();
	CartLib cartLib = new CartLib();
	ProductDisplayInfoLib prodLib = new ProductDisplayInfoLib();
	OrderLib orderLib =new OrderLib();
	CanadaLib canadaLib= new CanadaLib();

	// #############################################################################################################
	// #    Name of the Test         : ODP15_OrderswithMultipleBOMsRequisition
	// #    Migration Author         : Cigniti Technologies
	// #
	// #    Date of Migration        : September 2019
	// #    Description              : To Test Place Order  Confirmations
	// #    Parameters               : StartRow ,EndRow , nextTestJoin
	// #
	// #############################################################################################################

	@Parameters({ "StartRow", "EndRow", "nextTestJoin" })
		@Test
		public void TC_ODP15(int StartRow,String EndRow, boolean nextTestJoin) throws Throwable {
		int counter = 0;
		try {
			int intStartRow = StartRow;
			int intEndRow = ReportControl.fnGetEndRowCunt(EndRow, "ODP15_OrderswithMultipleBOMsRequisition",
					TestData, "Web_Order_Placement");
			for (int intCounter = intStartRow; intCounter <= intEndRow; intCounter++) {
				try {
					counter = intCounter;
					fnOpenTest();
					ReportStatus.fnDefaultReportStatus();
					ReportControl.intRowCount = intCounter;
					Hashtable<String, String> data = TestUtil.getDataByRowNo("ODP15_OrderswithMultipleBOMsRequisition",
							TestData, "Web_Order_Placement", intCounter);
					TestEngineWeb.reporter.initTestCaseDescription("OrderswithMultipleBOMsRequisition");

						// Login to CMT enable Display Additional Notes during the transaction process,Allow File Upload during Checkout,Display Invoice Notes during the transaction process settings at web group level.
						cmtLib.loginToCMT(data.get("Header"));
						cmtLib.searchForWebGroup(data.get("WebGrp"));
						cmtLib.clickOnTheWebGroup(data.get("WebGrp_Name"));
						cmtLib.hoverOnManageWebGroupsAndSelectOptions(data.get("Manage_Web_Grp_Options"));
						cmtLib.searchForaUserAndSelect(data.get("LnameEmailUname"), data.get("ContactName"));
						cmtLib.loginAsAdminCMT();

						// Select client in product standards page
						searchLib.verifyProductWStandardsPage();
						searchLib.verifyClientAndClickOnProductGrp(data.get("productName"));
						orderLib.verifyCartHeaderLabel();
						// Verify added bundle exists in cart screen
						cartLib.verifyProductGroupBundleAddedToCart(data.get("productName"));
						proceedToCheckout();
						clickOnAdditionalInfoContinueButton();
						// Click continue on shipping address
						canadaLib.verifySBP();
						shippingBillPayContinueButton();
						shippingOptionsCarrierSelection();
						billingAddressContinueButton();
						addNewCardInPayment(data.get("cardNumber"), data.get("cardName"), data.get("month"), data.get("year"),
								data.get("poNumebr"),data.get("POReleaseNumber"));
						clickOnReviewRequisitionButton();
						verifyPlaceOrderLabel();
						clickOnPlaceRequisitionButton();
						// Verify Receipt
						verifyReceiptVerbiage();
						String RefNumber = orderLib.getTextfromReferenceNumber();
						commonLib.clickLogOutLink(data.get("header1"));
						// Login with 2nd user
						cmtLib.loginToCMT(data.get("Header"));
						cmtLib.searchForWebGroup(data.get("WebGrp"));
						cmtLib.clickOnTheWebGroup(data.get("WebGrp_Name"));
						cmtLib.hoverOnManageWebGroupsAndSelectOptions(data.get("Manage_Web_Grp_Options"));
						cmtLib.searchForaUserAndSelect(data.get("LnameEmailUname1"), data.get("ContactName1"));
						cmtLib.loginAsAdminCMT();

						searchLib.verifyProductWStandardsPage();
						searchLib.verifyAccountToolsFromSideMenuAndClick(data.get("toolsMenuName"), data.get("dropDown"));
						verifyandClickonRefLink(RefNumber);
						verifyApprovalManagmentHeaderandClickonUpdateLink();
						commonLib.clickLogOutLink(data.get("header1"));
						// Login with 1st user
						cmtLib.loginToCMT(data.get("Header"));
						cmtLib.searchForWebGroup(data.get("WebGrp"));
						cmtLib.clickOnTheWebGroup(data.get("WebGrp_Name"));
						cmtLib.hoverOnManageWebGroupsAndSelectOptions(data.get("Manage_Web_Grp_Options"));
						cmtLib.searchForaUserAndSelect(data.get("LnameEmailUname"), data.get("ContactName"));
						cmtLib.loginAsAdminCMT();
						searchLib.verifyAccountToolsFromSideMenuAndClick(data.get("toolsMenuName"), data.get("dropDown1"));
						clickonorderNumLinkinRecentorders(RefNumber);
						verifyPartNumberInOrderdetails(data.get("item1"),data.get("Product Des"));
						// Logout 
						commonLib.clickLogOutLink(data.get("Logout"));
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
				ReportStatus.fnUpdateResultStatus("ODP15_OrderswithMultipleBOMsRequisition", "TC_ODP15", ReportStatus.strMethodName, 1, browser);
				throw new RuntimeException(e);
			}
	        finally {
	        	ReportControl.fnEnableJoin();
				ReportStatus.fnUpdateResultStatus("ODP15_OrderswithMultipleBOMsRequisition", "TC_ODP15", ReportStatus.strMethodName, counter, browser);
				fnCloseTest();
				ReportControl.fnNextTestJoin(nextTestJoin);
			}
		}

	}


