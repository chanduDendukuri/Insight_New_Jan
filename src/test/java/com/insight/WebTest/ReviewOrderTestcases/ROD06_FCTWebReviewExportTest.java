package com.insight.WebTest.ReviewOrderTestcases;

import com.insight.Lib.*;
import com.insight.accelerators.ReportControl;
import com.insight.accelerators.TestEngineWeb;
import com.insight.googledrive.ReportStatus;
import com.insight.utilities.TestUtil;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.util.Hashtable;

public class ROD06_FCTWebReviewExportTest extends OrderLib{

	ProductDisplayInfoLib prodInfoLib=new ProductDisplayInfoLib();
	CMTLib cmtLib=new CMTLib();
	SearchLib searchLib=new SearchLib();
	CommonLib commonLib=new CommonLib();
	CartLib cartLib=new CartLib();
	ProductDisplayInfoLib prodLib=new ProductDisplayInfoLib();
	CanadaLib canadaLib=new CanadaLib();

	// #############################################################################################################
	// #    Name of the Test         : ROD06_FCTWebReviewExport
	// #    Migration Author         : Cigniti Technologies
	// #
	// #    Date of Migration        : September 2019
	// #    Description              : To Test Place Order basic
	// #    Parameters               : StartRow ,EndRow , nextTestJoin
	// #
	// #############################################################################################################
		@Parameters({ "StartRow", "EndRow", "nextTestJoin" })
		@Test
		public void TC_ROD06(int StartRow,String EndRow, boolean nextTestJoin) throws Throwable {
			int counter = 0;
			try {
				int intStartRow = StartRow;
				int intEndRow = ReportControl.fnGetEndRowCunt(EndRow, "ROD06_FCTWebReviewExport", TestData, "Web_Review_Order");
				for (int intCounter = intStartRow; intCounter <= intEndRow; intCounter++) {
					try {
						fnOpenTest();
						ReportStatus.fnDefaultReportStatus();
						ReportControl.intRowCount = intCounter;
						Hashtable<String, String> data = TestUtil.getDataByRowNo("ROD06_FCTWebReviewExport", TestData, "Web_Review_Order", intCounter);
						TestEngineWeb.reporter
								.initTestCaseDescription("FCTWebReviewExport");
						reporter.SuccessReport("Iteration Number : ",
								"**************Iteration Number::  " + intCounter + " For:: " + data.get("LoginName") + " ::and:: "
										+ data.get("Password") + " To Validate::" + data.get("errorMessage") + "  **************","");


						cmtLib.loginToCMTSelectUserAndLoginAS(data.get("Header"), data.get("WebGrp"), data.get("WebGrp_Name"), data.get("Manage_Web_Grp_Options"), data.get("LnameEmailUname"), data.get("ContactName"));
						//Blade Servers
						searchLib.searchInHomePage(data.get("SearchText1"));
						searchLib.verifyTheResultsForSearchTerm(data.get("SearchText1"));
						// in-stock filter verification
						// searchLib.verifyFilterBreadCrumb(data.get("In_Stock_Only"));
						prodLib.selectFirstProductAddToCartAndVerifyCart();
						// ********  verify Export Excel ***************//
						cartLib.ClickExportCartAndVerify(data.get("Order_Utilities"),data.get("Sheet_Name"),data.get("Row_number"),data.get("Column_Headers"));
						// Quick shop
						cartLib.verifyQuickShopWithValidSinglePartNumber(data.get("QuickShop_Part"), data.get("Quantity"));
						// proceed To Checkout >> Fill Additional Information section >>>  Fill Line level Information >>> Fill Order and Item Info page - Review Order
						scrollUp();
						proceedToCheckout();
						Thread.sleep(3000);
						cartLib.clickOnContinueButtonInAddInformtion();
						clickContinueOnLineLevelInfo();
						canadaLib.verifySBP();
						clickContinueOnShippingAddress();
						//enterAttentionField( data.get("Card_Name"));
						shippingOptionsCarrierSelection();
						billingAddressContinueButton();
						selectPaymentInfoMethodCreditCard(data.get("Card_Number").toString(), data.get("Card_Name"), data.get("Month"), data.get("Year"),data.get("PO_Number"),data.get("POReleaseNumber"));
						clickOnReviewOrderButton();
						
						// ********  verify Export Excel ***************//   -- Verify  items added
						cartLib.ClickExportCartAndVerify(data.get("Order_Utilities"),data.get("Sheet_Name"),data.get("Row_number"),data.get("Column_Headers"));
						
						// SearchPart OR Product - Adobe
						searchLib.searchInHomePage(data.get("SearchText2"));
						searchLib.verifyTheResultsForSearchTerm(data.get("SearchText2"));
						// in-stock filter verification
						searchLib.verifyFilterBreadCrumb(data.get("In_Stock_Only"));
						prodLib.selectFirstProductAddToCartAndVerifyCart();
						
						// proceed To Checkout and click continue on Line Level information Section, shipping address,Shipping options, Billing address section >> Review Order
						proceedToCheckout();
						Thread.sleep(3000);
						clickOnAdditionalInfoContinueButton();
						clickContinueOnLineLevelInfo();
						canadaLib.verifySBP();
						shippingBillPayContinueButton();
						shippingOptionsCarrierSelection();
						shippingBillPayContinueButton();
						clickOnReviewOrderButton();

						// ******** verify Export Excel ***************//   -- Verify items added
					   	cartLib.ClickExportCartAndVerify(data.get("Order_Utilities"),data.get("Sheet_Name"),data.get("Row_number"),data.get("Column_Headers"));
						// Navigate to Account tools >> Company Standards
						clickOnSideMenuSelectAccountToolOptions(data.get("Tools_Menu"), data.get("Tools_Menu_DD"));
						commonLib.clickOnProductGrpInCompanyStandard( data.get("Product_Group"), data.get("Product_Name"));
						
						searchLib.clickAddToOrderOnCompanyStandardsScreen();

						// Verifying Bundle added to cart
						cartLib.verifyProductGroupBundleAddedToCart(data.get("Product_Name"));

						// proceed To Checkout >> Fill Additional Information section >>>  Fill Line level Information >>> Fill Order and Item Info page - Review Order
						proceedToCheckout();
						continueButtonOnAdditionalInformationSection();   // Click continue button on Add additional info
						clickContinueOnLLIAndShipBillPaySections(); // Click continue
						clickOnReviewOrderButton();       // Click Review order button

						// ********  verify Export Excel ***************//   -- Verify bundle items added
						cartLib.ClickExportCartAndVerify(data.get("Order_Utilities"),data.get("Sheet_Name"),data.get("Row_number"),data.get("Column_Headers"));
						// End of the test
						commonLib.clickLogOutLink(data.get("Logout"));
						
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
				ReportStatus.fnUpdateResultStatus("ROD", "ROD06", ReportStatus.strMethodName, 1, browser);
				throw new RuntimeException(e);
			}

			finally {
	        	ReportControl.fnEnableJoin();
				ReportStatus.fnUpdateResultStatus("ROD", "ROD06", ReportStatus.strMethodName, counter, browser);
				fnCloseTest();
				ReportControl.fnNextTestJoin(nextTestJoin);
			}
		}

}


