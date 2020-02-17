package com.insight.WebTest.OrderPlacementTests;

import com.insight.Lib.*;
import com.insight.accelerators.ReportControl;
import com.insight.accelerators.TestEngineWeb;
import com.insight.googledrive.ReportStatus;
import com.insight.utilities.TestUtil;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.util.Hashtable;

public class ODP08_ConvertQuoteBundlesTest extends OrderLib{

	ProductDisplayInfoLib prodInfoLib = new ProductDisplayInfoLib();
	CMTLib cmtLib = new CMTLib();
	SearchLib searchLib = new SearchLib();
	CommonLib commonLib = new CommonLib();
	CartLib cartLib = new CartLib();
	ProductDisplayInfoLib prodLib = new ProductDisplayInfoLib();
	OrderLib orderLib =new OrderLib();

	// #############################################################################################################
	// #    Name of the Test         : ODP08_ConvertQuoteBundles
	// #    Migration Author         : Cigniti Technologies
	// #
	// #    Date of Migration        : September 2019
	// #    Description              : To Test Place Order  Confirmations
	// #    Parameters               : StartRow ,EndRow , nextTestJoin
	// #
	// #############################################################################################################

	@Parameters({ "StartRow", "EndRow", "nextTestJoin" })
		@Test
		public void TC_ODP08(int StartRow,String EndRow, boolean nextTestJoin) throws Throwable {
		int counter = 0;
		try {
			int intStartRow = StartRow;
			int intEndRow = ReportControl.fnGetEndRowCunt(EndRow, "ODP08_ConvertQuoteBundles",
					TestData, "Web_Order_Placement");
			for (int intCounter = intStartRow; intCounter <= intEndRow; intCounter++) {
				try {
					counter = intCounter;
					fnOpenTest();
					ReportStatus.fnDefaultReportStatus();
					ReportControl.intRowCount = intCounter;
					Hashtable<String, String> data = TestUtil.getDataByRowNo("ODP08_ConvertQuoteBundles",
							TestData, "Web_Order_Placement", intCounter);
					TestEngineWeb.reporter.initTestCaseDescription("ConvertQuoteBundles");
			
						// Login to CMT enable view_quotes;ON";
						cmtLib.loginToCMT(data.get("Header"));
						cmtLib.searchForWebGroup(data.get("WebGrp"));
						cmtLib.clickOnTheWebGroup(data.get("WebGrp_Name"));
						cmtLib.hoverOnManageWebGroupsAndSelectOptions(data.get("Manage_Web_Grp_Options"));
						cmtLib.searchForaUserAndSelect(data.get("LnameEmailUname"), data.get("ContactName"));
						cmtLib.setPermissions(data.get("Menu_Name"), data.get("Set_Permission"));
						cmtLib.setPermissions(data.get("Menu_Name"), data.get("Set_Permission3"));

						// Login As to Web UAT
						cmtLib.loginAsAdminCMT();

						// Login Verification
						cmtLib.loginVerification(data.get("ContactName"));

						// Navigate to Account tools >> Company Standards >> Select bundle of products and add to order
						commonLib.clickAccountToolsFromSideMenuAndClickOnProductGrp(data.get("Tools_Menu"), data.get("Tools_Menu_DD"), data.get("Product_Group"), data.get("Product_Name"));
						searchLib.clickAddToOrderOnCompanyStandardsScreen();
						cartLib.verifyProductGroupBundleAddedToCart(data.get("Product_Name"));

						// Create Quote
						createQuote(data.get("Quote_Name"));
						String refNumber=getQuoteReferenceNumber();
						verifyProductBundleTableLoadedInSaveQuoteScreen();

						// Navigate to Quote History
						commonLib.clickOnAccountToolsAndClickOnProductGrp(data.get("Tools_Menu1"), data.get("Tools_Menu_DD1"));
						searchByInQuoteHistory(refNumber,data.get("DD_Option"));
						convertQuote();
						cartLib.verifyCartBreadCrumb();

						// Click on Proceed to checkout
						proceedToCheckout();
						// Click continue on Additional information Section
						continueButtonOnAdditionalInformationSection();

						// Click continue on LL info , shipping and billing sections
						clickContinueOnLLIAndShipBillPaySections();

						// Fill payment Info
						selectPaymentInfoMethodCreditCard(data.get("Card_Number").toString(), data.get("Card_Name"),data.get("Month"), data.get("Year"),data.get("PO_Number"),data.get("POReleaseNumber"));

						// Review Order
						clickOnReviewOrderButton();

						// Place Order
						String summaryAmount=cartLib.getSummaryAmountInCart();
						// Verify order and Date
						placeOrderAndVerifyReceiptOrderAndDate(summaryAmount);

						// Verify Receipt
						verifyReceiptVerbiage();
						clickOrderDetailsLinkOnReceiptPage();

						//Logout
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
			//	gErrorMessage = e.toString();
				gTestStatus = false;
				ReportStatus.fnUpdateResultStatus("ODP08_ConvertQuoteBundles", "TC_ODP08", ReportStatus.strMethodName, 1, browser);
				throw new RuntimeException(e);
			}
	        finally {
	        	ReportControl.fnEnableJoin();
				ReportStatus.fnUpdateResultStatus("ODP08_ConvertQuoteBundles", "TC_ODP08", ReportStatus.strMethodName, counter, browser);
				fnCloseTest();
				ReportControl.fnNextTestJoin(nextTestJoin);
			}
		}

	



}


