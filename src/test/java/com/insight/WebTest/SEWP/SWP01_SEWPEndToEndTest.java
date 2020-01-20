package com.insight.WebTest.SEWP;

import java.util.Hashtable;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.insight.Lib.CMTLib;
import com.insight.Lib.CanadaLib;
import com.insight.Lib.CartLib;
import com.insight.Lib.CommonLib;
import com.insight.Lib.MarriottIntlCorpLib;
import com.insight.Lib.OrderLib;
import com.insight.Lib.SearchLib;
import com.insight.Lib.SewpLib;
import com.insight.ObjRepo.CartObj;
import com.insight.accelerators.ReportControl;
import com.insight.accelerators.TestEngineWeb;
import com.insight.googledrive.ReportStatus;
import com.insight.utilities.TestUtil;

public class SWP01_SEWPEndToEndTest extends SewpLib {
	
	// #############################################################################################################
		// # Name of the Test :SWP01_SEWPEndToEnd
		// # Migration Author : Cigniti Technologies
		// #
		// # Date of Migration : August 2019
		// # DESCRIPTION : This method is to perform Basic Cart operations.
		// # Parameters : StartRow ,EndRow , nextTestJoin
		// #
		// ###############################################################################################################
		
		@Parameters({ "StartRow", "EndRow", "nextTestJoin" })
		@Test
		public void SWP01_SEWPEndToEnd(int StartRow, String EndRow, boolean nextTestJoin) throws Throwable {
			try {
			int intStartRow = StartRow;
			int intEndRow = ReportControl.fnGetEndRowCunt(EndRow, "SWP01_SEWPEndToEndTest", TestDataInsight,"SEWP");
			for (int intCounter = intStartRow; intCounter <= intEndRow; intCounter++) {
				try {
				fnOpenTest();
				ReportStatus.fnDefaultReportStatus();
				ReportControl.intRowCount = intCounter;
				Hashtable<String, String> data = TestUtil.getDataByRowNo("SWP01_SEWPEndToEndTest", TestDataInsight,"SEWP", intCounter);
				TestEngineWeb.reporter
						.initTestCaseDescription("SEWPEndToEnd");
				reporter.SuccessReport("Iteration Number : ",
						"**************Iteration Number::  " + intCounter + " For:: " + data.get("LoginName") + " ::and:: "
								+ data.get("Password") + " To Validate::" + data.get("errorMessage") + "  **************",
						"NA");
				CommonLib commonLib = new CommonLib();
				CMTLib cmtLib = new CMTLib();
				CartLib cartLib = new CartLib();
				OrderLib orderLib = new OrderLib();
				CanadaLib canadaLib = new CanadaLib();
				SearchLib searchLib=new SearchLib();
				MarriottIntlCorpLib marriottlib=new MarriottIntlCorpLib();
				// Test Steps execution
				fnOpenTest();
				navigateTo(data.get("URL"));
				searchProductforSWEP(data.get("SEWPPart"));
				verifyMfr(data.get("SEWPPart"));
				verifyInsight(data.get("SEWPPart"));
				commonLib.contractOnProductDetailPage();
				Verifyupdatequantity();
				commonLib.addToCartAndVerify();
				commonLib.continueToShopping();
				//clickOnProduct();
			    clickMorePricesAndViewContractsinProductsQA();
			    cartLib.verifyItemInCart(data.get("SEWPPart"));
			    cartLib.verifyItemInCart(data.get("SEWPPart"));
			    Thread.sleep(3000);
			    cartLib.verifyContractNameInCart(data.get("Contract"));
			    cartLib.verifyContractNameInCart(data.get("Contract2"));
			    clickProceedCheckout();
			    Thread.sleep(2000);
				CreateAnAccount(data.get("TC"));
				enterBillingInfo(data.get("Organization"),data.get("Agency"),data.get("SubAgency"));
				enterLoginInfo(data.get("LastName"),data.get("PhoneNumber"),data.get("Address1"),data.get("City"),data.get("State"),data.get("Title"),data.get("ZipCode"));	
				saveAndCreateAccount();
				 Thread.sleep(2000);
				enterReportingDetailsInLineLevelInfoSection(data.get("REPORTINGFIELD_4"),data.get("REPORTINGFIELD_5"),data.get("REPORTINGFIELD_6"));
				orderLib.shippingBillPayContinueButton();// Click continue on shipping address
				orderLib.shippingOptionsCarrierSelection();// Click continue on shipping options
				clickBillingAddressContinueButton();
				orderLib.selectPaymentInfoMethodCreditCard(data.get("Card_Number"), data.get("Card_Name"),data.get("Month"), data.get("Year"),data.get("PO_Number"),data.get("POReleaseNumber"));
				orderLib.clickOnReviewOrderButton();
				cartLib.verifyContractNameInCart(data.get("Contract"));
				cartLib.verifyContractNameInCart(data.get("Contract2"));
				// Place Order
				String summaryAmountInLogin = cartLib.getSummaryAmountInCart();
				orderLib.placeOrderAndVerifyReceiptOrderAndDate(summaryAmountInLogin);			
				String RefNumber= orderLib.getTextfromReferenceNumber().trim();
			       //Verifying order details
				orderLib.clickOrderDetailsLinkOnReceiptPage();
				Thread.sleep(5000);
				searchLib.verifyAccountToolsFromSideMenuAndClick(data.get("toolsMenuName"),data.get("dropDown"));
				clickonorderNumLinkinRecentorders(RefNumber);
				verifyPartNumberInOrderdetails(data.get("SEWPPart"));
				fnCloseTest();
				} catch (Exception e) {
					ReportStatus.blnStatus = false;
					//gErrorMessage = e.getMessage();
					System.out.println(e.getMessage());
					gTestStatus = false;
				}
				ReportControl.fnEnableJoin();
				ReportStatus.fnUpdateResultStatus("SEWPEndToEnd", "SWP01", ReportStatus.strMethodName,
						intCounter, browser);
				
			}
		} catch (Exception e) {
			e.printStackTrace();
			ReportStatus.blnStatus = false;
			//gErrorMessage = e.getMessage();
			gTestStatus = false;
			ReportStatus.fnUpdateResultStatus("SEWPEndToEnd", "SWP01", ReportStatus.strMethodName, 1, browser);
			throw new RuntimeException(e);
		}

		ReportControl.fnNextTestJoin(nextTestJoin);
	}

}