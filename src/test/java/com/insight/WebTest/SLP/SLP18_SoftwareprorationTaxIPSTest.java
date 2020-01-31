package com.insight.WebTest.SLP;

import java.util.Hashtable;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.insight.Lib.CMTLib;
import com.insight.Lib.CanadaLib;
import com.insight.Lib.CartLib;
import com.insight.Lib.CommonLib;
import com.insight.Lib.OrderLib;
import com.insight.Lib.ProductDisplayInfoLib;
import com.insight.Lib.SLPLib;
import com.insight.accelerators.ReportControl;
import com.insight.accelerators.TestEngineWeb;
import com.insight.googledrive.ReportStatus;
import com.insight.utilities.TestUtil;

public class SLP18_SoftwareprorationTaxIPSTest extends SLPLib {
	
	// #############################################################################################################
	// # Name of the Test     : SLP18_SoftwareprorationTaxIPS
	// # Migration Author     : Cigniti Technologies
	// #
	// # Date of Migration    : OCT 2019
	// # DESCRIPTION          : Purpose of this test method is to verify the compare
	//                          functionality in the products display page.
	// # Parameters           : StartRow ,EndRow , nextTestJoin
	// #
	// ###############################################################################################################

	@Parameters({ "StartRow", "EndRow", "nextTestJoin" })
	@Test
	public void SLP18_SoftwareprorationTaxIPS(int StartRow, String EndRow, boolean nextTestJoin) throws Throwable {
		int counter = 0;
		try {
		int intStartRow = StartRow;
		int intEndRow = ReportControl.fnGetEndRowCunt(EndRow, "SLP18_SoftwareprorationTaxIPS", TestData, "SLP");
		for (int intCounter = intStartRow; intCounter <= intEndRow; intCounter++) {

			// initializing libraries and testdata
			ReportStatus.fnDefaultReportStatus();
			ReportControl.intRowCount = intCounter;
			Hashtable<String, String> data = TestUtil.getDataByRowNo("SLP18_SoftwareprorationTaxIPS", TestData, "SLP",
					intCounter);
			TestEngineWeb.reporter.initTestCaseDescription("SoftwareprorationTaxIPS");
		      reporter.SuccessReport("Iteration Number : ",
				"**************Iteration Number::  " + intCounter + " For:: " + data.get("LoginName") + " ::and:: "
						+ data.get("Password") + " To Validate::" + data.get("errorMessage") + "  **************","");
			
			
			// Test Steps execution
			try {
				fnOpenTest();
				CMTLib cmtLib = new CMTLib();
				CommonLib commonLib = new CommonLib();
				OrderLib orderLib = new OrderLib();
				CartLib cartLib = new CartLib();
				ProductDisplayInfoLib pipLib=new ProductDisplayInfoLib();
				CanadaLib canadaLib=new CanadaLib();
				
				cmtLib.loginToCMT(data.get("Header"));
				cmtLib.searchForWebGroup(data.get("WebGrp"));
				cmtLib.clickOnTheWebGroup(data.get("WebGrp_Name"));
				cmtLib.hoverOnManageWebGroupsAndSelectOptions(data.get("ManageWebGrpOptions"));
				cmtLib.searchForaUserAndSelect(data.get("LnameEmailUname"), data.get("ContactName"));
				cmtLib.setPermissions(data.get("Menu_Name"), data.get("Set_Permission"));
				cmtLib.loginAsAdminCMT();
				
				// Login Verification 
				cmtLib.loginVerification(data.get("ContactName"));
				commonLib.searchProduct(data.get("PartNum"));
				pipLib.verifyTheManufacturerNumberInProductDetailsPage(data.get("PartNum"));
				pipLib.enterQuantityOnProductDetailsPage(data.get("Quantity"));
				String price=getpricefromProductdetailpage();
				Double Actualprice = Double.parseDouble(price.replace("$", "").replace("USD", ""));
				commonLib.addToCartAndVerify();
				orderLib.continueToCheckOutOnAddCart();
				canadaLib.verifyPlaceCartLabel();
				verifyProrationincartpage(data.get("PartNum"), Actualprice);
				orderLib.proceedToCheckout();
				
				 //orderLib.clickOnAdditionalInfoContinueButton();
				 orderLib.clickContinueOnLineLevelInfo();   // Click continue on Line level Info
				 canadaLib.verifySBP();
				 orderLib.clickContinueOnShippingAddress();
				//orderLib.shippingOptionsCarrierSelection();
				 orderLib.billingAddressContinueButton(); // Billing address continue button
				 orderLib.termsInPaymentInfo(data.get("PONumber"),data.get("POReleaseNumber"));
				 verifyProrationinplaceorderpage(data.get("Subtotal"), Actualprice);
				 String amount = cartLib.getSummaryAmountInCart();
				 orderLib.placeOrderAndVerifyReceiptOrderAndDate(amount);
				 verifyProrationinrecieptpage(Actualprice);
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
		ReportStatus.fnUpdateResultStatus("SoftwareprorationTaxIPS", "SLP18", ReportStatus.strMethodName, 1, browser);
		throw new RuntimeException(e);
	}

	finally {
    	ReportControl.fnEnableJoin();
    	ReportStatus.fnUpdateResultStatus("SoftwareprorationTaxIPS", "SLP18", ReportStatus.strMethodName, counter, browser);
		fnCloseTest();
		ReportControl.fnNextTestJoin(nextTestJoin);
	}

}
}

