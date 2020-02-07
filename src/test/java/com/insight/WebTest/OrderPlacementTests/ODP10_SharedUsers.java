package com.insight.WebTest.OrderPlacementTests;

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
import com.insight.accelerators.ReportControl;
import com.insight.accelerators.TestEngineWeb;
import com.insight.googledrive.ReportStatus;
import com.insight.utilities.TestUtil;

public class ODP10_SharedUsers extends OrderLib{

	ProductDisplayInfoLib prodInfoLib = new ProductDisplayInfoLib();
	CMTLib cmtLib = new CMTLib();
	SearchLib searchLib = new SearchLib();
	CommonLib commonLib = new CommonLib();
	CartLib cartLib = new CartLib();
	ProductDisplayInfoLib prodLib = new ProductDisplayInfoLib();
	OrderLib orderLib =new OrderLib();
	CanadaLib canadaLib= new CanadaLib();
	ProductDetailLib proddetailLib = new ProductDetailLib();

	// #############################################################################################################
	// #    Name of the Test         : ODP10_SharedUsers
	// #    Migration Author         : Cigniti Technologies
	// #
	// #    Date of Migration        : September 2019
	// #    Description              : To Test Place Order  Confirmations
	// #    Parameters               : StartRow ,EndRow , nextTestJoin
	// #
	// #############################################################################################################

	@Parameters({ "StartRow", "EndRow", "nextTestJoin" })
		@Test
		public void TC_ODP10(int StartRow,String EndRow, boolean nextTestJoin) throws Throwable {
		int counter = 0;
		try {
			int intStartRow = StartRow;
			int intEndRow = ReportControl.fnGetEndRowCunt(EndRow, "ODP10_SharedUser",
					TestData, "Web_Order_Placement");
			for (int intCounter = intStartRow; intCounter <= intEndRow; intCounter++) {
				try {
					counter = intCounter;
					fnOpenTest();
					ReportStatus.fnDefaultReportStatus();
					ReportControl.intRowCount = intCounter;
					Hashtable<String, String> data = TestUtil.getDataByRowNo("ODP10_SharedUser",
							TestData, "Web_Order_Placement", intCounter);
					TestEngineWeb.reporter.initTestCaseDescription("ODP10_SharedUser");

						// Login to CMT enable Display Additional Notes during the transaction process,Allow File Upload during Checkout,Display Invoice Notes during the transaction process settings at web group level.
						
					cmtLib.loginToCMT(data.get("Header"));
					cmtLib.searchForWebGroup(data.get("WebGrp"));
					cmtLib.clickOnTheWebGroup(data.get("WebGrp_Name"));
					cmtLib.hoverOnManageWebGroupsAndSelectOptions(data.get("Manage_Web_Grp_Options"));
					//cmtLib.setPermissions(data.get("menuName"), data.get("userPermission"));
					cmtLib.searchForaUserAndSelect(data.get("LnameEmailUname"), data.get("ContactName"));
					cmtLib.clickOnRolesAndPermissionsAndSetPermission(data.get("menuName"), data.get("userPermission"));
					cmtLib.loginAsAdminCMT();
						searchLib.searchInHomePage(data.get("SearchText"));
						searchLib.verifyBreadCrumbInSearchResultsPage(data.get("SearchText"));
						cartLib.selectFirstProductDisplay();
						commonLib.addToCartAndVerify();
						continueToCheckOutOnAddCart();
						orderLib.verifyCartHeaderLabel();
						proceedToCheckout();
						addAdditionalInformationByNameFields(data.get("url"), data.get("name"), data.get("phonembr"),
						data.get("email"));
						clickOnAdditionalInfoContinueButton();
						clickContinueOnLineLevelInfo();
						canadaLib.verifySBP();
						shippingBillPayContinueButton();
						scrollBottom();
						shippingOptionsCarrierSelection();
						billingAddressContinueButton();
						selectPaymentInfoMethodCreditCard(data.get("cardNumber"),data.get("cardName"),data.get("month"),data.get("year"),data.get("PO_Number"),data.get("POReleaseNumber"));
						clickOnReviewRequisitionButton();
						verifyPlaceOrderLabel();
						clickOnPlaceRequisitionButton();
						//VerifyOrderPlaceByFields();
						// Verify Receipt
						verifyReceiptVerbiage();

						String RefNumber = orderLib.getTextfromReferenceNumber();
						// Logout
						commonLib.clickLogOutLink(data.get("header1"));
						// Login with 2nd user admin
						cmtLib.navigateBackToCMT();
						cmtLib.hoverOnManageWebGroupsAndSelectOptions(data.get("Manage_Web_Grp_Options"));
						cmtLib.searchForaUserAndSelect(data.get("LnameEmailUname1"), data.get("ContactName1"));
						cmtLib.clickOnRolesAndPermissionsAndSetPermission(data.get("menuName"), data.get("userPermission1"));
						cmtLib.loginAsAdminCMT();
						searchLib.verifyAccountToolForOrderMenuItem(data.get("toolsMenuName"), data.get("dropDown"));
						verifyandClickonRefLink(RefNumber);
						//Verify emaild id and phone number fields
						VerifyOrderPlaceByFields(data.get("name"),data.get("email"));
						verifyApprovalManagmentandClickUpdate();
						commonLib.clickLogOutLink(data.get("header1"));
						// Login with 3rd user Approver
						cmtLib.navigateBackToCMT();
						cmtLib.hoverOnManageWebGroupsAndSelectOptions(data.get("Manage_Web_Grp_Options"));
						cmtLib.searchForaUserAndSelect(data.get("LnameEmailUname2"), data.get("ContactName2"));
						cmtLib.clickOnRolesAndPermissionsAndSetPermission(data.get("menuName"), data.get("userPermission1"));
						cmtLib.loginAsAdminCMT();
						searchLib.verifyAccountToolForOrderMenuItem(data.get("toolsMenuName"), data.get("dropDown"));
						verifyandClickonRefLink(RefNumber);
						//Verify emaild id and phone number fields
						VerifyOrderPlaceByFields(data.get("name"),data.get("email"));
						verifyOrderNumberandClickonUpdateLink();
						verifyOrderNumberinManagementPage(RefNumber);
						searchLib.verifyAccountToolForOrderMenuItem(data.get("toolsMenuName"), data.get("dropDown1"));
						refreshPage();
						clickonorderNumLinkinRecentorders(RefNumber);
						verifytabsinOrderDetailsPage(data.get("TabName1"));// customer details
						VerifycontactFieldsInCustomerDetails(data.get("name"),data.get("email"));
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
				ReportStatus.fnUpdateResultStatus("ODP10_SharedUser", "TC_ODP10", ReportStatus.strMethodName, 1, browser);
				throw new RuntimeException(e);
			}
	        finally {
	        	ReportControl.fnEnableJoin();
				ReportStatus.fnUpdateResultStatus("ODP10_SharedUser", "TC_ODP10", ReportStatus.strMethodName, counter, browser);
				fnCloseTest();
				ReportControl.fnNextTestJoin(nextTestJoin);
			}
		}

}