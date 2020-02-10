package com.insight.WebTest.Canada;

import java.util.Hashtable;

import com.insight.Lib.*;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.insight.accelerators.ReportControl;
import com.insight.accelerators.TestEngineWeb;
import com.insight.googledrive.ReportStatus;
import com.insight.utilities.TestUtil;

public class CAN10_SPLAZeroUsageTest extends CanadaLib {

    CMTLib cmtLib = new CMTLib();
    CommonLib commonLib = new CommonLib();
    CartLib cartLib = new CartLib();
    SearchLib searchLib = new SearchLib();
    ProductDetailLib prodDetailsLib = new ProductDetailLib();
    OrderLib orderLib = new OrderLib();
    ProductDisplayInfoLib pipLib = new ProductDisplayInfoLib();
    ShipBillPayLib sbpLib = new ShipBillPayLib();
    MarriottIntlCorpLib micLib = new MarriottIntlCorpLib();
    CommonCanadaLib ccp = new CommonCanadaLib();
    SLPLib slpLib=new SLPLib();

    // #############################################################################################################
    // #       Name of the Test         :  CAN10_SPLAZeroUsage
    // #       Migration Author         :  Cigniti Technologies
    // #
    // #       Date of Migration        : October 2019
    // #       DESCRIPTION              : This Test is used to Test Load of the Cart After Selecting SPLA Products
    // #       Parameters               : StartRow ,EndRow , nextTestJoin
    // #
    // ###############################################################################################################
    @Parameters({"StartRow", "EndRow", "nextTestJoin"})
    @Test
    public void TC_CAN10(int StartRow, String EndRow, boolean nextTestJoin) throws Throwable {

        int counter = 0;
        try {
            int intStartRow = StartRow;
            int intEndRow = ReportControl.fnGetEndRowCunt(EndRow, "CAN10_SPLAZeroUsage", TestDataInsight, "Canada");
            for (int intCounter = intStartRow; intCounter <= intEndRow; intCounter++) {
                try {
                    counter = intCounter;
                    fnOpenTest();
                    ReportStatus.fnDefaultReportStatus();
                    ReportControl.intRowCount = intCounter;
                    Hashtable<String, String> data = TestUtil.getDataByRowNo("CAN10_SPLAZeroUsage", TestDataInsight,
                            "Canada", intCounter);
                    TestEngineWeb.reporter.initTestCaseDescription("SPLAZeroUsage");

                    // Login to CMT
                    cmtLib.loginToCMT(data.get("Header"));
                    cmtLib.searchForWebGroup(data.get("WebGrp"));
                    cmtLib.clickOnTheWebGroup(data.get("WebGrp_Name"));
                    /// allow_unlimited_spla_ordering;off";
                 //   commonLib.clickRolesAndPermissionsAtUserLevel();
                    cmtLib.setHostedLicensingPermissionsOFF(data.get("Set_Permission"));
                    cmtLib.hoverOnManageWebGroupsAndSelectOptions(data.get("Manage_Web_Grp_Options1"));
                    // Clear usage
                    assertTrue(ccp.HostedLicensingAdminPageVerification(), "Successfully launched Hosted Licensing Admin Page");
                    cmtLib.AddMonthInHostedLicensingAdministrationPage(data.get("Month1"), data.get("Year1"), data.get("Type"), data.get("SoldTO"), data.get("SalesOrg"));
                    cmtLib.hoverOnManageWebGroupsAndSelectOptions(data.get("Manage_Web_Grp_Options2"));
                    cmtLib.searchForaUserAndSelect(data.get("LnameEmailUname"), data.get("ContactName"));

					cmtLib.setPermissionsToDisable(data.get("Menu_Name"), data.get("Set_Permission1"));
					// Login as to UAT
                    cmtLib.loginAsAdminCMT();
                    // Login Verification
                    cmtLib.loginVerification(data.get("ContactName"));
                    CandaHomePageVerification();

                    // account tools >> Software License Agreements
                    commonLib.clickOnAccountToolsAndClickOnProductGrp(data.get("Tools_Menu"), data.get("Tools_Menu_DD"));
                    verifySPLAPage();
                    // Select Software Lic Agreements
                    selectSPLADetailsProductCheckBox(data.get("SPLA"));
                    // verify search results 
			     	slpLib.verifysearchResultsPageForSLP();
                    // Search for a product and add to cart
                   assertTrue(ccp.verifyReturnTOSoftwareLicenseAggrements(),"Search Results :: My Software licencing aggreement ");
                   // searchLib.verifysearchResultsPage();
                    searchLib.searchInHomePage(data.get("SearchItem1"));
                    pipLib.verifyTheManufacturerNumberInProductDetailsPage(data.get("SearchText"));
			    	pipLib.enterQuantityOnProductDetailsPage(data.get("Quantity"));
			     	commonLib.addToCartAndVerify();
			     	orderLib.continueToCheckOutOnAddCart();
			    	verifyPlaceCartLabel();
			     	cartLib.verifyItemInCartByInsightPart(data.get("SearchItem1"));
                    pipLib. verifyCartPageAndPartDetailsForRecentlyItemDynamically(data.get("SearchItem1"));
                    //cartLib.verifyItemInCartByInsightPart(data.get("SearchItem1"));

                    // Search for a product and add to cart
                    searchLib.searchInHomePage(data.get("SearchItem2"));
                    String manNum1=ccp.getManfNumberFromProductSearchScreen();
                    commonLib.updateCartQuantity(data.get("quantity"));

                    commonLib.addToCartAndVerify();
                    orderLib.continueToCheckOutOnAddCart();
                    pipLib. verifyCartPageAndPartDetailsForRecentlyItemDynamically(data.get("SearchItem2"));

                   // cartLib.verifyItemInCartByInsightPart(data.get("SearchItem2"));
                    scrollToBottomWithCordinate("-2000");
                    // account tools >> Software License Agreements
                    commonLib.clickOnAccountToolsAndClickOnProductGrp(data.get("Tools_Menu"), data.get("Tools_Menu_DD"));
                    verifySPLAPage();
                    slpLib.retrieveLastUsageReport(data.get("SPLA"));
                    String subTotal=sbpLib.getTotalAmountInCart(data.get("SubTotal_label"));
					Double subTotalAmount = Double.parseDouble(subTotal.replace("$", ""));
					slpLib.verifyAmount(subTotalAmount);
                  
					clickOnReportZeroUsageLinkOnCart();
					// Verify Only Zero Usage Part in the Cart CAD $0.00"
					String subtotalAmt=cartLib.getSummaryAmountInCart();
					Float subTotalAmount1 = Float.parseFloat(subtotalAmt.replace("$", ""));
					slpLib.verifySubTotalAmount(subTotalAmount1);
					
					// verify reporting usage period warning message
					slpLib.verifyReportingPeriodWarning();
					// Verify usage period on cart
					 String cartUsagePeriod=verifyReportingUsagePeriod();
					//Proceed to checkout
					 orderLib.proceedToCheckout();
					 orderLib.clickOnAdditionalInfoContinueButton();
					 orderLib.clickContinueOnLineLevelInfo();   // Click continue on Line level Info
					 verifySBP();
					 orderLib.clickContinueOnShippingAddress();  // Click continue on  shipping address 
					 orderLib.billingAddressContinueButton(); // Billing address continue button
					 orderLib.addNewCardInPayment(data.get("cardNumber"), data.get("cardName"), data.get("month"), data.get("year"), data.get("poNumebr"), data.get("POReleaseNumber"));
					 orderLib.clickOnReviewOrderButton();  // Click Review order button
					
					// Verify usage period on place order page
					  String poUsagePeriod=slpLib.verifyReportingUsagePeriodOnReceiptPage();
					  slpLib.verifyUsagePeriodsMatching(poUsagePeriod, cartUsagePeriod);
					 
					 // Place Order
					 String amount = cartLib.getSummaryAmountInCart();
					orderLib.placeOrderAndVerifyReceiptOrderAndDate(amount);
				 
					// Verify usage period on receipt page
					 String receiptUsagePeriod=slpLib.verifyReportingUsagePeriodOnReceiptPage();
					 slpLib.verifyUsagePeriodsMatching(receiptUsagePeriod, cartUsagePeriod);
					
					 // account tools >> Software License Agreements
					commonLib.clickOnAccountToolsAndClickOnProductGrp(data.get("Tools_Menu"), data.get("Tools_Menu_DD"));
					verifySPLAPage();
					slpLib.verifyAllReportingPeriodsCurrent();
					selectSPLADetailsProductCheckBox(data.get("SPLA"));
					// verify search results 
					slpLib.verifysearchResultsPageForSLP();
					
					// search for a part / product and add to cart
			     	searchLib.searchInHomePage(data.get("SearchItem1"));
			    	pipLib.verifyTheManufacturerNumberInProductDetailsPage(data.get("SearchItem1"));
			    	pipLib.enterQuantityOnProductDetailsPage(data.get("Quantity"));
			     	commonLib.addToCartAndVerify();
			     	orderLib.continueToCheckOutOnAddCart();
			    	verifyPlaceCartLabel();
			    	pipLib. verifyCartPageAndPartDetailsForRecentlyItemDynamically(data.get("SearchItem1"));
			    	slpLib.verifyAllReportingPeriodsCurrentinCartPage();
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
            //gErrorMessage = e.toString();
            gTestStatus = false;
            ReportStatus.fnUpdateResultStatus("SPLAZeroUsage", "TC_CAN10", ReportStatus.strMethodName, 1, browser);
            throw new RuntimeException(e);
        } finally {
            ReportControl.fnEnableJoin();
            ReportStatus.fnUpdateResultStatus("SPLAZeroUsage", "TC_CAN10", ReportStatus.strMethodName, counter, browser);
            fnCloseTest();
            ReportControl.fnNextTestJoin(nextTestJoin);
        }
    }
}

