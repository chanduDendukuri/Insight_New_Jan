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
import com.insight.Lib.SLPLib;
import com.insight.Lib.ShipBillPayLib;
import com.insight.accelerators.ReportControl;
import com.insight.accelerators.TestEngineWeb;
import com.insight.googledrive.ReportStatus;
import com.insight.utilities.TestUtil;

public class CAN11_SLPProrationMicrosoftTest extends CanadaLib {
	
	CommonLib commonLib = new CommonLib();
	CMTLib cmtLib = new CMTLib();
	CartLib cartLib = new CartLib();
	OrderLib orderLib = new OrderLib();
	CanadaLib canadaLib = new CanadaLib();
	ShipBillPayLib shipbLib = new ShipBillPayLib();
	ProductDisplayInfoLib prodinfo=new ProductDisplayInfoLib();
	ProductDetailLib prodDetailLib=new ProductDetailLib();
	SLPLib slpLib=new SLPLib();

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
					Hashtable<String, String> data = TestUtil.getDataByRowNo("CAN11_SLPProrationMicrosoft",
							TestDataInsight, "Canada", intCounter);
					TestEngineWeb.reporter.initTestCaseDescription("SLPProrationMicrosoft");
					
					cmtLib.loginToCMT(data.get("Header"));
					cmtLib.searchForWebGroup(data.get("WebGrp"));
					cmtLib.clickOnTheWebGroup(data.get("WebGrp_Name"));
					cmtLib.hoverOnManageWebGroupsAndSelectOptions(data.get("Manage_Web_Grp_Options"));
					cmtLib.searchForaUserAndSelect(data.get("LnameEmailUname"), data.get("ContactName"));
					cmtLib.loginAsAdminCMT();
					// Login verification
					cmtLib.loginVerification("User - "+data.get("ContactName"));
					canadaLib.CandaHomePageVerification();
					
					Thread.sleep(2000);
					// SelectSoftwareLicAgrements
					commonLib.clickOnAccountToolsAndClickOnProductGrp(data.get("Tools_Menu"),
							data.get("Tools_Menu_DD"));
					// Select Software  Lic Agreements
			     	canadaLib.selectSPLADetailsProductCheckBox(data.get("SPLA"));
					// verify search results and select first product
			     	slpLib.verifysearchResultsPageForSLP();
					// search product
					commonLib.searchProduct(data.get("Productname"));
					Thread.sleep(2000);
					// Verify product details
					prodinfo.verifyTheManufacturerNumberInProductDetailsPage(data.get("Productname"));
					//prodinfo.verifyProductDescAndPartNumberInCompanyStandards();
					prodDetailLib.Getproductdetails();
					// Add Quantity 
					prodinfo.enterQuantityOnProductDetailsPage(data.get("Quantity"));
					String price = slpLib.getpricefromProductdetailpage().replace("CAD", "").replace("$", "").replace(",", ""); 
					Double Actualprice = Double.valueOf(price);
					commonLib.addToCartAndVerify();
					
					String partNumber1 = cartLib.getPartNumber();
					canadaLib.continueToCheckout();
					Thread.sleep(2000);
					//Add Cart page Verification and product details
					cartLib.verifyCartPageAvailablity();
					prodinfo.verifyCartPageAndPartDetailsForRecentlyItem();
					String subTotal=shipbLib.getTotalAmountInCart(data.get("SubTotal"));
				    Float subTotalAmount = Float.parseFloat(subTotal.replace("$", "").replace(",", ""));
				     // verifying Prorated Price of product in cart
				   slpLib.verifyProrationincartpage(data.get("Productname"), Actualprice);
				    // verifying Prorated Price with the subtotal price in cart price
				   slpLib.verifyProrationPrice(subTotalAmount, Actualprice);
					// proceed to checkout
					orderLib.proceedToCheckout();
					// ******** Click continue on Line level Info, Ship and Bill pay sections ********************//
					orderLib.clickContinueOnLineLevelInfo();
					// Verify Order and Item Info 
					 canadaLib.verifySBP();
					 orderLib.shippingBillPayContinueButton();// shipping address continue button
                     orderLib.billingAddressContinueButton();  // Billing address continue button
                     orderLib.selectPaymentInfoMethodCreditCard(data.get("cardNumber"), data.get("cardName"), data.get("month"), data.get("year"),data.get("poNumebr"),data.get("POReleaseNumber"));
				     orderLib.clickOnReviewOrderButton();
					
					//  Need to check with ref with Report
					String summaryAmount = cartLib.getSummaryAmountInCart();
					//shipbLib.getSummaryAmountsInCart(data.get("SubTotal"), data.get("Total"));
					
					String totalAmt=shipbLib.getTotalAmountInCart(data.get("Total"));
					Double totalAmount = Double.valueOf(totalAmt.replace("$", "").replace(",", ""));
					slpLib.verifyProrationPrice(Actualprice, totalAmount);
					// place requisition
					orderLib.placeOrderAndVerifyReceiptOrderAndDate(summaryAmount);
					//Verify Receipt page
					shipbLib.getReferenceNum();
					//Verify Receipt
					orderLib.verifyReceiptVerbiage();
					orderLib.clickOrderDetailsLinkOnReceiptPage();
					
					// Verify prorated price in receipt page
					String totalAmt_receiptPage=shipbLib.getTotalAmountInCart(data.get("Total"));
					Double totalAmount_receiptPage = Double.valueOf(totalAmt_receiptPage.replace("$", "").replace(",", ""));
					slpLib.verifyProrationPrice(Actualprice, totalAmount_receiptPage);
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
