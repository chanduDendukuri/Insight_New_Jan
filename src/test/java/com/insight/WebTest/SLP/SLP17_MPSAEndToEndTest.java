package com.insight.WebTest.SLP;

import java.util.Hashtable;
import java.util.List;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.insight.Lib.CMTLib;
import com.insight.Lib.CanadaLib;
import com.insight.Lib.CartLib;
import com.insight.Lib.CommonLib;
import com.insight.Lib.InvoiceHistoryLib;
import com.insight.Lib.MarriottIntlCorpLib;
import com.insight.Lib.OrderHistoryLib;
import com.insight.Lib.OrderLib;
import com.insight.Lib.RequisitionProcessingLib;
import com.insight.Lib.SLPLib;
import com.insight.Lib.SearchLib;
import com.insight.Lib.ShipBillPayLib;
import com.insight.accelerators.ReportControl;
import com.insight.accelerators.TestEngineWeb;
import com.insight.googledrive.ReportStatus;
import com.insight.utilities.TestUtil;

public class SLP17_MPSAEndToEndTest extends SLPLib{
	
	// #############################################################################################################
	// # Name of the Test : SLP17_MPSAEndToEnd
	// # Migration Author : Cigniti Technologies
	// #
	// # Date of Migration : OCT 2019
	// # DESCRIPTION : Purpose of this test method is to verify the compare
	// functionality in the products display page.
	// # Parameters : StartRow ,EndRow , nextTestJoin
	// #
	// ###############################################################################################################

	@Parameters({ "StartRow", "EndRow", "nextTestJoin" })
	@Test
	public void SLP17_MPSAEndToEnd(int StartRow, String EndRow, boolean nextTestJoin) throws Throwable {
		int counter = 0;
		try {
		int intStartRow = StartRow;
		int intEndRow = ReportControl.fnGetEndRowCunt(EndRow, "SLP17_MPSAEndToEnd", TestData, "SLP");
		for (int intCounter = intStartRow; intCounter <= intEndRow; intCounter++) {

			// initilizing libraries and testdata
			ReportStatus.fnDefaultReportStatus();
			ReportControl.intRowCount = intCounter;
			Hashtable<String, String> data = TestUtil.getDataByRowNo("SLP17_MPSAEndToEnd", TestData, "SLP",
					intCounter);
			TestEngineWeb.reporter.initTestCaseDescription("MPSAEndToEnd");
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
				SearchLib searchLib = new SearchLib();
				ShipBillPayLib shipbLib = new ShipBillPayLib();
				RequisitionProcessingLib ReqLib = new RequisitionProcessingLib();
				CanadaLib canadaLib=new CanadaLib();
				OrderHistoryLib orderHistortLib=new OrderHistoryLib();
				InvoiceHistoryLib invoiceHistoryLib = new InvoiceHistoryLib();
				
				cmtLib.loginToCMT(data.get("Header"));
				cmtLib.searchForWebGroup(data.get("WebGrp"));
				cmtLib.clickOnTheWebGroup(data.get("WebGrp_Name"));
				cmtLib.hoverOnManageWebGroupsAndSelectOptions(data.get("ManageWebGrpOptions"));
				cmtLib.searchForaUserAndSelect(data.get("LnameEmailUname1"), data.get("ContactName1"));
				String[] permissions = data.get("Set_Permission").split(",");
				for (i = 0; i < permissions.length; i++) {
					cmtLib.setPermissions(data.get("Menu_Name"), permissions[i]);
				}
				cmtLib.loginAsAdminCMT();
				Thread.sleep(3000);
				
				// Login Verification 
				cmtLib.loginVerification(data.get("ContactName1"));
				commonLib.clickAccountToolsFromSideMenuAndClickOnProductGrp(data.get("Tools_Menu1"),
						data.get("Tools_Menu_DD1"), data.get("Product_Group"), data.get("Product_Name"));//Tools,Company Standards,TUMPSATestCat,tuMPSATestGrp
				searchLib.clickAddToOrderOnCompanyStandardsScreen();
				commonLib.verifyBundleIsAddedToCart();
				// Verify prorated cart
				verifyProratedCart();
				
				// Verify Deploy popup details for part 1
		     	String date1=getDeploydateOnCart(data.get("PartNum1"));
		     	List<String> prodDesc1 = orderLib.getProductDescriptionOfCartProduct();
				verifyandClickchangeLink(data.get("PartNum1"));
				Clickonunpaidlicense();
				verifydeployedatewithcurrentdate();
				verifycartDetailsWithDeployPopUpDetails(date1, prodDesc1.get(0), data.get("PartNum1"));
				calenderforUnpaidLicense(data.get("Date1"));
		     	clickapply();
		     	Thread.sleep(5000);
				//  verify deploy date on cart
		     	verifydeployeDateinCartPageAfterDateChange(data.get("PartNum1"), data.get("Date1"));
		     	clickOnCopyToAllLink(data.get("PartNum1"));
		     	
		     	// First part verification
		     	String date_1=getDeploydateOnCart(data.get("PartNum1"));
		     	verifyDateAppliedToAllPartAfterCopyAll(date_1, data.get("Date1"));
				
		     	// Second part deploy date verification
		     	String date2=getDeploydateOnCart(data.get("PartNum2"));
		     	verifyDateAppliedToAllPartAfterCopyAll(date2, data.get("Date1"));
		     	
		     	verifyUpdatedUnPaidLisenceOnCartPage(data.get("PartNum2"));
		     	verifyUpdatedUnPaidLisenceOnCartPage(data.get("PartNum1"));
		     	
		     	clickSaveasQuote();
                // verifying deploy date on quotes screen
                verifyProductDeployDate(data.get("Date1"),data.get("Unpaid"));
				
                clickSaveAsQuoteButtonOnQuoteScreen();
                String refNumber = orderLib.getQuoteReferenceNumber();
                clickQuoteHistoryLink();
		        Thread.sleep(5000);
		        orderLib.searchByInQuoteHistory(refNumber,data.get("DD_Option"));
				// verify quote details on prorated product in Quote details
				verifyMSPAProductsProratedOnQuoteDetailsScreen();
				orderLib.convertQuote();
				cartLib.verifyCartBreadCrumb();
				 
				verifyUpdatedUnPaidLisenceOnCartPage(data.get("PartNum1"));
		     	verifyUpdatedUnPaidLisenceOnCartPage(data.get("PartNum2"));
		     	
		        // First part verification
		     	String cartDate1=getDeploydateOnCart(data.get("PartNum1"));
		     	verifyDateAppliedToAllPartAfterCopyAll(cartDate1, data.get("Date1"));
				
		     	// Second part deploy date verification
		     	String cartDate2=getDeploydateOnCart(data.get("PartNum2"));
		     	verifyDateAppliedToAllPartAfterCopyAll(cartDate2, data.get("Date1"));
				
		     	// Proceed to checkout
		     	orderLib.proceedToCheckout();
		     	orderLib.clickOnAdditionalInfoContinueButton();
		     	orderLib.clickContinueOnLineLevelInfo();
		     	 canadaLib.verifySBP();
				 orderLib.clickContinueOnShippingAddress();
			     //orderLib.shippingOptionsCarrierSelection();
				 orderLib.billingAddressContinueButton();
				 orderLib.selectPaymentInfoMethodCreditCard(data.get("Card_Number").toString(),data.get("Card_Name"), data.get("Month"),
			             data.get("Year"), data.get("PONumber"),data.get("POReleaseNumber"));
				 orderLib.clickOnReviewRequisitionButton();                       
                 orderLib.verifyPlaceOrderLabel();
                 // Verify deploy date
                 verifyDeploydateOnPlaceOrderPage(data.get("PartNum2"), data.get("Date1"));
                 verifyDeploydateOnPlaceOrderPage(data.get("PartNum1"), data.get("Date1"));
				
                 verifyUpdatedUnPaidLisenceOnCartPage(data.get("PartNum2"));
                 verifyUpdatedUnPaidLisenceOnCartPage(data.get("PartNum1"));
                 String summaryAmount = cartLib.getSummaryAmountInCart();
                 // Place requisition
                 shipbLib.ReviewrequisitionnumPage();
                 verifyReceiptPageOrderDetails(summaryAmount);
                 String RefNumber= orderLib.getTextfromReferenceNumber();
                 orderLib.clickOrderDetailsLinkOnReceiptPage();
                 
                 // Logout 
                 commonLib.clickLogOutLink(data.get("Logout"));
				 cmtLib.navigateBackToCMT();
				 cmtLib.hoverOnManageWebGroupsAndSelectOptions(data.get("ManageWebGrpOptions"));
				 cmtLib.searchForaUserAndSelect(data.get("LnameEmailUname2"), data.get("ContactName2"));
				 cmtLib.loginAsAdminCMT();
				
				 commonLib.clickOnInsightLogoOnHomePage();
				 commonLib.clickOnAccountToolsAndClickOnProductGrp(data.get("Tools_Menu2"), data.get("Tools_Menu_DD3"));
				 selectReferenceNumFromRequisitionSearchResults(RefNumber);
				 verifyProductDeployDate(data.get("Date1"),data.get("Unpaid"));
				 
				 selectApproveRadioButtonOnApprovalManagementPage();
				 ReqLib.clickUpdateInApprovalManagmentPage();
				 clickonEnterNewcard();
				 ReqLib.enterNewcarDetails(data.get("cardtype"), data.get("cardNum"), data.get("cardName"));
				 ReqLib.continuebutton();
				 ReqLib.verifyApproveRequisitionStatus();
				 commonLib.clickOnInsightLogoOnHomePage();
				 // Order history
				 commonLib.clickOnAccountToolsAndClickOnProductGrp(data.get("Tools_Menu2"), data.get("Tools_Menu_DD4"));
				 orderHistortLib.selectQuickSearchDropdown(data.get("DD_Option"),RefNumber);
				 orderHistortLib.verifySearchResultsAreDisplayed();
				 orderHistortLib.getFirstOrderNumber();
				 orderHistortLib.clickOrderNumber();
				 invoiceHistoryLib.verifyOrderDetailsPage();
				// Logout
     			commonLib.clickLogOutLink(data.get("Logout"));
				
			} catch (Exception e) {
				ReportStatus.blnStatus = false;
				//gErrorMessage = e.getMessage();
				gTestStatus = false;
				throw new RuntimeException(e);
			}
			
		}
	} catch (Exception e) {
		e.printStackTrace();
		ReportStatus.blnStatus = false;
		//gErrorMessage = e.getMessage();
		gTestStatus = false;
		ReportStatus.fnUpdateResultStatus("MPSAEndToEnd", "SLP17", ReportStatus.strMethodName, 1, browser);
		throw new RuntimeException(e);
	}
	finally {
    	ReportControl.fnEnableJoin();
    	ReportStatus.fnUpdateResultStatus("MPSAEndToEnd", "SLP17", ReportStatus.strMethodName, counter, browser);
		fnCloseTest();
		ReportControl.fnNextTestJoin(nextTestJoin);
	}
	}
}