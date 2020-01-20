package com.insight.WebTest.SEWP;

import java.util.Hashtable;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.insight.Lib.CartLib;
import com.insight.Lib.MarriottIntlCorpLib;
import com.insight.Lib.SearchLib;
import com.insight.Lib.SewpLib;
import com.insight.accelerators.ReportControl;
import com.insight.accelerators.TestEngineWeb;
import com.insight.googledrive.ReportStatus;
import com.insight.utilities.TestUtil;

public class SWP02_SEWPIPSContractTest extends SewpLib{
	        // #############################################################################################################
			// # Name of the Test :SWP02_SEWPIPSContractTest
			// # Migration Author : Cigniti Technologies
			// #
			// # Date of Migration : August 2019
			// # DESCRIPTION : This method is to perform Basic Cart operations.
			// # Parameters : StartRow ,EndRow , nextTestJoin
			// #
			// ###############################################################################################################
			

	@Parameters({ "StartRow", "EndRow", "nextTestJoin" })
	@Test
	public void SWP02_SEWPIPSContract(int StartRow, String EndRow, boolean nextTestJoin) throws Throwable {
		try {
		int intStartRow = StartRow;
		int intEndRow = ReportControl.fnGetEndRowCunt(EndRow, "SWP02_SEWPIPSContractTest", TestDataInsight,
				"SEWP");
		for (int intCounter = intStartRow; intCounter <= intEndRow; intCounter++) {
			try {
				fnOpenTest();
			ReportStatus.fnDefaultReportStatus();
			ReportControl.intRowCount = intCounter;
			Hashtable<String, String> data = TestUtil.getDataByRowNo("SWP02_SEWPIPSContractTest", TestDataInsight, "SEWP",
					intCounter);
			TestEngineWeb.reporter.initTestCaseDescription(
					"SEWPIPSContractTest");
			reporter.SuccessReport("Iteration Number : ",
					"**************Iteration Number::  " + intCounter + " For:: " + data.get("LoginName")
							+ " ::and:: " + data.get("Password") + " To Validate::" + data.get("errorMessage")
							+ "  **************",
					"");
			SearchLib searchLib = new SearchLib();
			CartLib cartLib=new CartLib();
			MarriottIntlCorpLib marriottLib=new MarriottIntlCorpLib();
			fnOpenTest();
			navigateTo(data.get("URL"));
			Thread.sleep(3000);
			CreateAnAccount(data.get("TC"));
			marriottLib.handleinsightpopup();
			Thread.sleep(3000);
			enterBillingInfo(data.get("Organization"), data.get("Agency"), data.get("SubAgency"));
			marriottLib.handleinsightpopup();
			enterLoginInfo(data.get("LastName"), data.get("PhoneNumber"), data.get("Address1"), data.get("City"),
					data.get("State"), data.get("Title"), data.get("ZipCode"));
			saveAndCreateAccount();
			Thread.sleep(3000);
			marriottLib.handleinsightpopup();
			Thread.sleep(3000);
			searchLib.verifyContractAllDisplayed();
			Thread.sleep(3000);
			searchLib.selectContract(data.get("Contract"));
			searchProduct(data.get("SEWPPart"));
			Thread.sleep(3000);
			// Verify SEWP Part details
			verifyMfr(data.get("SEWPPart"));
			verifyInsight(data.get("SEWPPart"));
			clickOnAddtoCart();
			Thread.sleep(3000);
			marriottLib.handleinsightpopup();
			Thread.sleep(3000);
			cartLib.verifyItemInCart(data.get("SEWPPart"));
		    Thread.sleep(3000);
		    cartLib.verifyContractNameInCart(data.get("Contract"));
			orderLib.proceedToCheckout();
			Thread.sleep(3000);
			enterReportingDetailsInLineLevelInfoSection(data.get("REPORTINGFIELD_4"), data.get("REPORTINGFIELD_5"),data.get("REPORTINGFIELD_6"));
			orderLib.shippingBillPayContinueButton(); // Click continue on shipping address
			orderLib.shippingOptionsCarrierSelection(); // Click continue on shipping options
			clickBillingAddressContinueButton();
			Thread.sleep(3000);
			orderLib.selectPaymentInfoMethodCreditCard(data.get("Card_Number"), data.get("Card_Name"),data.get("Month"), data.get("Year"),data.get("PO_Number"),data.get("POReleaseNumber"));
			orderLib.clickOnReviewOrderButton();
			cartLib.verifyItemInCart(data.get("SEWPPart"));
		    Thread.sleep(3000);
		    cartLib.verifyContractNameInCart(data.get("Contract"));
			Thread.sleep(3000);
			// Place Order
			String summaryAmountInLogin = cartLib.getSummaryAmountInCart();
			orderLib.placeOrderAndVerifyReceiptOrderAndDate(summaryAmountInLogin);			
			String RefNumber= orderLib.getTextfromReferenceNumber().trim();
			Thread.sleep(3000);
		       //Verifying order details
			orderLib.clickOrderDetailsLinkOnReceiptPage();
			cartLib.verifyContractNameInCart(data.get("Contract"));
			Thread.sleep(3000);
			searchLib.verifyAccountToolsFromSideMenuAndClick(data.get("toolsMenuName"),data.get("dropDown"));			
			clickonorderNumLinkinRecentorders(RefNumber);
			Thread.sleep(3000);
			verifyPartNumberInOrderdetails(data.get("SEWPPart"));
			
			} catch (Exception e) {
				ReportStatus.blnStatus = false;
				//gErrorMessage = e.getMessage();
				System.out.println(e.getMessage());
				gTestStatus = false;
			}
			ReportControl.fnEnableJoin();
			ReportStatus.fnUpdateResultStatus("SEWPIPSContractTest", "SWP02", ReportStatus.strMethodName,
					intCounter, browser);
			fnCloseTest();
		}
	} catch (Exception e) {
		e.printStackTrace();
		ReportStatus.blnStatus = false;
		//gErrorMessage = e.getMessage();
		gTestStatus = false;
		ReportStatus.fnUpdateResultStatus("SEWPIPSContractTest", "SWP02", ReportStatus.strMethodName, 1, browser);
		throw new RuntimeException(e);
	}

	ReportControl.fnNextTestJoin(nextTestJoin);
}

}