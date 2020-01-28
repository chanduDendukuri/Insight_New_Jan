package com.insight.WebTest.ReviewOrderTestcases;

import com.insight.Lib.*;
import com.insight.accelerators.ReportControl;
import com.insight.accelerators.TestEngineWeb;
import com.insight.googledrive.ReportStatus;
import com.insight.utilities.TestUtil;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import java.util.Hashtable;


public class ROD07_FCTWebReviewExportIPSTest extends OrderLib{

	//ProductDisplayInfoLib prodInfoLib=new ProductDisplayInfoLib();
	CMTLib cmtLib=new CMTLib();
	SearchLib searchLib=new SearchLib();
	CommonLib commonLib=new CommonLib();
	CartLib cartLib=new CartLib();
	ProductDisplayInfoLib prodLib=new ProductDisplayInfoLib();
	ProductDetailLib prodDetails = new ProductDetailLib();
	CanadaLib canadaLib=new CanadaLib();

	// #############################################################################################################
	// #    Name of the Test         : ROD07_FCTWebReviewExportIPS
	// #    Migration Author         : Cigniti Technologies
	// #
	// #    Date of Migration        : September 2019
	// #    Description              : To Test Place Order basic
	// #    Parameters               : StartRow ,EndRow , nextTestJoin
	// #
	// #############################################################################################################
		@Parameters({ "StartRow", "EndRow", "nextTestJoin" })
		@Test
		public void TC_ROD07(int StartRow,String EndRow, boolean nextTestJoin) throws Throwable {
			int counter=0;
			try {
				int intStartRow = StartRow;
				int intEndRow = ReportControl.fnGetEndRowCunt(EndRow, "ROD07_FCTWebReviewExportIPS", TestData, "Web_Review_Order");
				for (int intCounter = intStartRow; intCounter <= intEndRow; intCounter++) {
					try {
						fnOpenTest();
						ReportStatus.fnDefaultReportStatus();
						ReportControl.intRowCount = intCounter;
						Hashtable<String, String> data = TestUtil.getDataByRowNo("ROD07_FCTWebReviewExportIPS", TestData, "Web_Review_Order", intCounter);
						TestEngineWeb.reporter
								.initTestCaseDescription("FCTWebReviewExportIPS");
						reporter.SuccessReport("Iteration Number : ",
								"**************Iteration Number::  " + intCounter + " For:: " + data.get("LoginName") + " ::and:: "
										+ data.get("Password") + " To Validate::" + data.get("errorMessage") + "  **************","");



						// Login to CMT and disable override_payment_options;off"
						cmtLib.loginToCMT(data.get("Header"));
						cmtLib.searchForWebGroup( data.get("WebGrp"));
						cmtLib.clickOnTheWebGroup(data.get("WebGrp_Name"));
						cmtLib.setCustomerLevelPermissionsOFF(data.get("Customer_Permissions"));
						cmtLib. hoverOnManageWebGroupsAndSelectOptions(data.get("Manage_Web_Grp_Options"));
						cmtLib.searchForaUserAndSelect(data.get("LnameEmailUname"),data.get("ContactName"));
						// enable_purchase_popup;ON";
						cmtLib.setPermissions(data.get("Menu_Name"), data.get("Set_Permission"));
						// Login As to UAT Web
						cmtLib.loginAsAdminCMT();

						searchLib.selectNewcontract(data.get("Contract_Name1"));
						prodDetails.verifyContractDetails();
						searchLib.searchInHomePage(data.get("SearchText"));
						searchLib.verifyBreadCrumbInSearchResultsPage(data.get("SearchText"));
						prodLib.selectFirstProductAddToCartAndVerifyCart();
						commonLib.updateCartQuantity(data.get("Quantity"));
						// Selecting second contract
						searchLib.selectNewcontract(data.get("Contract_Name2")); 
						prodDetails.verifyContractDetails();
						searchLib.searchInHomePage(data.get("SearchText"));
						searchLib.verifyBreadCrumbInSearchResultsPage(data.get("SearchText"));
						prodLib.selectFirstProductAddToCartAndVerifyCart();
						commonLib.updateCartQuantity(data.get("Quantity"));

						proceedToCheckout();
						enterReportingDetailsInLineLevelInfoSection(data.get("REPORTING FIELD_4"), data.get("REPORTING FIELD_5"), data.get("REPORTING FIELD_6"));
						canadaLib.verifySBP();
						clickContinueOnShippingAddress();   // Click continue on Shipping address
						shippingOptionsCarrierSelection(); // carrier options continue button
						billingAddressContinueButton();   // Billing address continue button
						selectPaymentInfoMethodCreditCard(data.get("Card_Number").toString(), data.get("Card_Name"), data.get("Month"), data.get("Year"),data.get("PO_Number"),data.get("POReleaseNumber"));
						clickOnReviewOrderButton(); // Click Review order button on payment info
						// Click on Export as a file and verify 
						cartLib.ClickExportCartAndVerify(data.get("Order_Utilities"),data.get("Sheet_Name"),data.get("Row_number"),data.get("Column_Headers"));
						commonLib.clickLogOutLink(data.get("Logout"));
						// End of test 
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
				ReportStatus.fnUpdateResultStatus("ROD", "ROD07", ReportStatus.strMethodName, 1, browser);
				throw new RuntimeException(e);
			}

			finally {
	        	ReportControl.fnEnableJoin();
				ReportStatus.fnUpdateResultStatus("ROD", "ROD07", ReportStatus.strMethodName, counter, browser);
				fnCloseTest();
				ReportControl.fnNextTestJoin(nextTestJoin);
			}
		}

}


