package com.insight.WebTest.OrderPlacementTests;

import com.insight.Lib.*;
import com.insight.accelerators.ReportControl;
import com.insight.accelerators.TestEngineWeb;
import com.insight.googledrive.ReportStatus;
import com.insight.utilities.TestUtil;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.util.Hashtable;

public class ODP06_CreditCardOverridePaymentTest extends OrderLib{

	ProductDisplayInfoLib prodInfoLib = new ProductDisplayInfoLib();
	CMTLib cmtLib = new CMTLib();
	SearchLib searchLib = new SearchLib();
	CommonLib commonLib = new CommonLib();
	CartLib cartLib = new CartLib();
	OrderLib orderLib =new OrderLib();
	CanadaLib canadaLib=new CanadaLib();
	ProductDetailLib prodDetailsLib=new ProductDetailLib();
	LineLevelInfoLib lnlLib=new LineLevelInfoLib();

	// #############################################################################################################
	// #    Name of the Test         : ODP09_ConvertQuoteIPS
	// #    Migration Author         : Cigniti Technologies
	// #
	// #    Date of Migration        : September 2019
	// #    Description              : To Test Place Order  Confirmations
	// #    Parameters               : StartRow ,EndRow , nextTestJoin
	// #
	// #############################################################################################################

	@Parameters({ "StartRow", "EndRow", "nextTestJoin" })
		@Test
		public void TC_ODP06(int StartRow,String EndRow, boolean nextTestJoin) throws Throwable {
		int counter = 0;
		try {
			int intStartRow = StartRow;
			int intEndRow = ReportControl.fnGetEndRowCunt(EndRow, "ODP06_CreditCardOverridePayment",
					TestData, "Web_Order_Placement");
			for (int intCounter = intStartRow; intCounter <= intEndRow; intCounter++) {
				try {
					counter = intCounter;
					fnOpenTest();
					ReportStatus.fnDefaultReportStatus();
					ReportControl.intRowCount = intCounter;
					Hashtable<String, String> data = TestUtil.getDataByRowNo("ODP06_CreditCardOverridePayment",
							TestData, "Web_Order_Placement", intCounter);
					TestEngineWeb.reporter.initTestCaseDescription("CreditCardOverridePayment");

						// Login to CMT Enable Override Payment Options at web group level.
						cmtLib.loginToCMT(data.get("Header"));
						cmtLib.searchForWebGroup(data.get("WebGrp"));
						cmtLib.clickOnTheWebGroup(data.get("WebGrp_Name"));

						// check Override Payments Options in web Group Level
						cmtLib.setCustomerLevelPermissionsON(data.get("Customer_Permissions_ON"));

						// select Users and Login As
						cmtLib.hoverOnManageWebGroupsAndSelectOptions(data.get("Manage_Web_Grp_Options"));
						cmtLib.searchForaUserAndSelect(data.get("LnameEmailUname"), data.get("ContactName"));
						// user_requires_approval;off";
						cmtLib.setPermissionsToDisable(data.get("Menu_Name"), data.get("Set_Permission"));

						// Login As to Web UAT
						cmtLib.loginAsAdminCMT();

						// Login Verification
						cmtLib.loginVerification(data.get("ContactName"));

						// Select First Product and Add to cart
						searchLib.searchInHomePage(data.get("SearchText1"));
						searchLib.verifyBreadCrumbInSearchResultsPage(data.get("SearchText1"));
						cartLib.selectFirstProductDisplay();
						String mfrNumber=prodDetailsLib.getInsightPartNumberInProductInfopage();
						// Cart verification
						commonLib.addToCartAndVerify();
						orderLib.continueToCheckOutOnAddCart();
						canadaLib.verifyPlaceCartLabel();
						commonLib.spinnerImage();
						cartLib.verifyItemInCartByInsightPart(mfrNumber);
						// proceed to checkout
						proceedToCheckout();
					   continueButtonOnAdditionalInformationSection();  // Continue on additional info
						// Click continue on LL info , shipping and billing sections
						clickContinueOnLLIAndShipBillPaySections();
						// Fill payment Info
						selectPaymentInfoMethodCreditCard(data.get("Card_Number_Error_VISA").toString(), data.get("Card_Name"),data.get("Month"), data.get("Year"),data.get("PO_Number"),data.get("POReleaseNumber"));
						verifyVISACardTypedErrorMessage();
						enterCreditCard(data.get("Card_Number1").toString(), data.get("Card_Name"),data.get("Month"), data.get("Year"),data.get("PO_Number"),data.get("POReleaseNumber"));
						clickOnReviewOrderButton();
						//Place Order
						String summaryAmount=cartLib.getSummaryAmountInCart();
						placeOrderAndVerifyReceiptOrderAndDate(summaryAmount);
						//Verify Receipt
						verifyReceiptVerbiage();

						// go back to CMT tool
						cmtLib.navigateBackToCMT();

						//approval_process_setup;ON";;
						cmtLib.setPermissions(data.get("Menu_Name"), data.get("Set_Permission1"));

						// Login As to Web UAT
						cmtLib.loginAsAdminCMT();

						// Click on Approval Management
						commonLib.clickOnAccountToolsAndClickOnProductGrp(data.get("Tools_Menu"), data.get("Tools_Menu_DD"));

						clickonApprovalManagementTabs(data.get("Approval_Management_Tabs"));
						clickOnTheEditLinkOfRequestorGroupNameEditLink(data.get("Requestor_Group"));
						clickOnTheRequestorGroupNameTabs(data.get("Requestor_Group_Tab"));  // clicking on checkout settings
						selectTheGroupPaymentOptions(data.get("Payment_Option"));
						clickOnTheRequestorGroupNameTabs(data.get("Requestor_Group_Tab2"));// Clicking on Approval path settings
						checkPaymentMethodCheckBox();   // Check payments method check box in Approval path settings

						// Select First Product and Add to cart
						searchLib.searchInHomePage(data.get("SearchText1"));
						searchLib.verifyBreadCrumbInSearchResultsPage(data.get("SearchText1"));
						cartLib.selectFirstProductDisplay();
						String mfrNumber1=prodDetailsLib.getInsightPartNumberInProductInfopage();
						// Cart verification
						commonLib.addToCartAndVerify();
						orderLib.continueToCheckOutOnAddCart();
						canadaLib.verifyPlaceCartLabel();
						cartLib.verifyItemInCartByInsightPart(mfrNumber1);

						// proceed to checkout
						proceedToCheckout();
						lnlLib.verifyOrderAndItemInfoBreadCrumb();
						continueButtonOnAdditionalInformationSection();  // Continue on additional info section
						clickContinueOnLLIAndShipBillPaySections(); // continue on Line level info, Ship bill pay sections

						// Fill payment Info
						selectPaymentInfoMethodCreditCard(data.get("Card_Number_Error_DISCOVER").toString(), data.get("Card_Name"),data.get("Month"), data.get("Year"),data.get("PO_Number"),data.get("POReleaseNumber"));
						verifyDiscoverCardErrorMessage();
						selectPaymentInfoMethodCreditCard(data.get("Card_Number1").toString(), data.get("Card_Name"),data.get("Month"), data.get("Year"),data.get("PO_Number"),data.get("POReleaseNumber"));
						clickOnReviewOrderButton();
						String summaryAmount1=cartLib.getSummaryAmountInCart();
						placeOrderAndVerifyReceiptOrderAndDate(summaryAmount1);

						// navigate back to CMt
						cmtLib.navigateBackToCMT();
						cmtLib.hoverOnManageWebGroupsAndSelectOptions(data.get("Manage_Web_Grp_Options"));
						cmtLib.searchForaUserAndSelect(data.get("LnameEmailUname1"), data.get("ContactName1"));
						
						cmtLib.clickCheckOutSettings(data.get("checkOut_Settings"));
						// navigate to checkout settings >>  payment options
						cmtLib.selectOptionInCheckoutSettings(data.get("Payment_Options"));
						cmtLib.clearPaymentOptionsInCheckoutSettings();
						cmtLib.selectpaymentOptionsInCheckOutSettings(data.get("Options"));

						// Login As to Web UAT
						cmtLib.loginAsAdminCMT();
						cmtLib.loginVerification(data.get("ContactName1"));
						
						// Select First Product and Add to cart
						searchLib.searchInHomePage(data.get("SearchText1"));
						searchLib.verifyBreadCrumbInSearchResultsPage(data.get("SearchText1"));
						cartLib.selectFirstProductDisplay();
						String mfrNumber2=prodDetailsLib.getInsightPartNumberInProductInfopage();
						// Cart verification
						commonLib.addToCartAndVerify();
						orderLib.continueToCheckOutOnAddCart();
						canadaLib.verifyPlaceCartLabel();
						cartLib.verifyItemInCartByInsightPart(mfrNumber2);

						// proceed to checkout
						proceedToCheckout();
						continueButtonOnAdditionalInformationSection();  // Continue on additional info section
						clickContinueOnLLIAndShipBillPaySections(); // continue on Line level info, Ship bill pay sections
						// Fill payment Info
						selectPaymentInfoMethodCreditCard(data.get("Card_Number1").toString(), data.get("Card_Name"),data.get("Month"), data.get("Year"),data.get("PO_Number"),data.get("POReleaseNumber"));
						clickOnReviewOrderButton();
						//Place Order
						String summaryAmount2=cartLib.getSummaryAmountInCart();
						placeOrderAndVerifyReceiptOrderAndDate(summaryAmount2);
						//Verify Receipt
						verifyReceiptVerbiage();
						
						// navigate back to CMT
						cmtLib.navigateBackToCMT();
						Thread.sleep(6000);
						scrollUp();
						cmtLib.hoverOnManageWebGroupsAndSelectOptions(data.get("Manage_Web_Grp_Options_Settings"));
						// Uncheck Override Payments Options in web Group Level
						cmtLib.setCustomerLevelPermissionsOFF(data.get("Customer_Permissions_OFF"));
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
				ReportStatus.fnUpdateResultStatus("ODP06_CreditCardOverridePayment", "TC_ODP06", ReportStatus.strMethodName, 1, browser);
				throw new RuntimeException(e);
			}
	        finally {
	        	ReportControl.fnEnableJoin();
				ReportStatus.fnUpdateResultStatus("ODP06_CreditCardOverridePayment", "TC_ODP06", ReportStatus.strMethodName, counter, browser);
				fnCloseTest();
				ReportControl.fnNextTestJoin(nextTestJoin);
			}
		}

	}


