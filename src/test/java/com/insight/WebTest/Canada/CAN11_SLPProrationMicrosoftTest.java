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
import com.insight.Lib.ShipBillPayLib;
import com.insight.accelerators.ReportControl;
import com.insight.accelerators.TestEngineWeb;
import com.insight.googledrive.ReportStatus;
import com.insight.utilities.TestUtil;

public class CAN11_SLPProrationMicrosoftTest extends CanadaLib {

	// #############################################################################################################
	// # Name of the Test : CAN11_SLPProrationMicrosoft
	// # Migration Author : Cigniti Technologies
	// #
	// # Date of Migration : October 2019
	// # DESCRIPTION : This method is to verify Proration price for canada
	// # Parameters : StartRow ,EndRow , nextTestJoin
	// #
	// ###############################################################################################################
	@Parameters({ "StartRow", "EndRow", "nextTestJoin" })
	@Test
	public void TC_CAN11(int StartRow, String EndRow, boolean nextTestJoin) throws Throwable {
	
					
					int counter = 0;
					try {
						int intStartRow = StartRow;
						int intEndRow = ReportControl.fnGetEndRowCunt(EndRow, "CAN11_SLPProrationMicrosoft", TestDataInsight, "Canada");
						for (int intCounter = intStartRow; intCounter <= intEndRow; intCounter++) {
							try {
								counter = intCounter;
								fnOpenTest();
								ReportStatus.fnDefaultReportStatus();
								ReportControl.intRowCount = intCounter;
								Hashtable<String, String> data = TestUtil.getDataByRowNo("CAN11_SLPProrationMicrosoft", TestDataInsight,
										"Canada", intCounter);
								TestEngineWeb.reporter.initTestCaseDescription("SLPProrationMicrosoft");
					CommonLib commonLib = new CommonLib();
					CMTLib cmtLib = new CMTLib();
					CartLib cartLib = new CartLib();
					OrderLib orderLib = new OrderLib();
					CanadaLib canadaLib = new CanadaLib();
					ShipBillPayLib shipbLib = new ShipBillPayLib();
					ProductDisplayInfoLib prodinfo=new ProductDisplayInfoLib();
					ProductDetailLib prodDetailLib=new ProductDetailLib();

					//07-02 Updated latest flow
					//cmtLib.loginToCMTSearchWebGrpAndUser(data.get("Header"), data.get("WebGrp"),
					//		data.get("LnameEmailUname"), data.get("ContactName"));
					cmtLib.loginToCMT(data.get("Header"));
					cmtLib.searchForWebGroup(data.get("WebGrp"));
					cmtLib.clickOnTheWebGroup(data.get("WebGrp_Name"));
					cmtLib.hoverOnManageWebGroupsAndSelectOptions(data.get("Manage_Web_Grp_Options"));
					cmtLib.searchForaUserAndSelect(data.get("LnameEmailUname"), data.get("contactName"));

					cmtLib.clickOnloginAs();
					switchToChildWindow();
					//07-02-> Add Login Verification
					cmtLib.loginVerification("User - "+data.get("contactName"));
					canadaLib.CandaHomePageVerification();
					
					Thread.sleep(2000);
					// SelectSoftwareLicAgrements
					commonLib.clickOnAccountToolsAndClickOnProductGrp(data.get("Tools_Menu"),
							data.get("Tools_Menu_DD"));
					//07-02-> Add Page Verification of Microsoft Lic Agreement
					canadaLib.verifySPLAPage();
					canadaLib.selectProdCheckbox();
					// search product
					commonLib.searchProduct(data.get("Productname"));
					// commonLib.spinnerImage();
					Thread.sleep(2000);
					//07-02-> Verify product details
					prodinfo.verifyTheManufacturerNumberInProductDetailsPage(data.get("Productname"));
					//prodinfo.verifyProductDescAndPartNumberInCompanyStandards();
					prodDetailLib.Getproductdetails();
					// 07-02 Add Quantity 
					prodinfo.enterQuantityOnProductDetailsPage(data.get("Quantity"));
					commonLib.addToCartAndVerify();
					canadaLib.verifyProductPrice();
					String partNumber1 = cartLib.getPartNumber();
					canadaLib.continueToCheckout();
					Thread.sleep(2000);
					// 07-02 Add Cart page Verification and product details
					cartLib.verifyCartPageAvailablity();
					prodinfo.verifyCartPageAndPartDetailsForRecentlyItem();
					canadaLib.verifyProratedPrice();
					// proceed to checkout
					orderLib.proceedToCheckout();
					// ******** Click continue on Line level Info, Ship and Bill pay sections ********************//
					orderLib.clickContinueOnLineLevelInfo();
					//07-02 Verify Order and Item Info 
					canadaLib.verifySBP();
					orderLib.shippingBillPayContinueButton();
					Thread.sleep(1000);
					orderLib.shippingBillPayContinueButton();
					Thread.sleep(2000);
					// Select Terms- payment method and click Review Order
					// 07-02 Need to verify PONumber value in TestData
					orderLib.termsInPaymentInfo(data.get("PONumber"),data.get("POReleaseNumber"));
					// 07-02 Need to check with ref with Report
					String summaryAmount = cartLib.getSummaryAmountInCart();
					shipbLib.getSummaryAmountsInCart(data.get("SubTotal"), data.get("Total"));
					//orderLib.clickOnReviewOrderButton();  // Click Review order button
					orderLib.verifyPlaceOrderLabel();
					// Place Order
					// 07-02 Add SubTotal Amount
					orderLib.placeOrderAndVerifyReceiptOrderAndDate(summaryAmount);
					shipbLib.getSummaryAmountsInCart(data.get("SubTotal"), data.get("Total"));

					//orderLib.selectPaymentMethod(data.get("Payment_method"));
					// Verify Ship Bill Page
					Thread.sleep(2000);
					canadaLib.veriyProratedPriceinSBP();
					// verify subtotal(prorated price), verify receipt, order and date
					shipbLib.clickOrderDetailsButtonInREceipt();
					shipbLib.getSummaryAmountsInCart(data.get("SubTotal"), data.get("Total"));
					commonLib.clickLogOutLink(data.get("Logout_Header"));
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
			ReportStatus.fnUpdateResultStatus("SLPProrationMicrosoft", "TC_CAN11", ReportStatus.strMethodName, 1, browser);
			throw new RuntimeException(e);
		}
        finally {
        	ReportControl.fnEnableJoin();
			ReportStatus.fnUpdateResultStatus("SLPProrationMicrosoft", "TC_CAN11", ReportStatus.strMethodName, counter, browser);
			fnCloseTest();
			ReportControl.fnNextTestJoin(nextTestJoin);
		}
	}
}
