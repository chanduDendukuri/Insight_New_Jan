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
                    // Select Software Lic Agreements
                    selectSPLADetailsProductCheckBox(data.get("SPLA"));
                    // Search for a product and add to cart
                   assertTrue(ccp.verifyReturnTOSoftwareLicenseAggrements(),"Search Results :: My Software licencing aggreement ");
                   // searchLib.verifysearchResultsPage();
                    searchLib.searchInHomePage(data.get("SearchItem1"));
                    String manNum=ccp.getManfNumberFromProductSearchScreen();
                    commonLib.updateCartQuantity(data.get("quantity"));
                    commonLib.addToCartAndVerify();
                    orderLib.continueToCheckOutOnAddCart();
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

                    selectReportUsageButtonsInMySoftwareLicenseAgreements(data.get("Btnlabel"));
                    clickOnReportZeroUsageLinkOnCart();

                    // Verify Only Zero Usage Part in the Cart CAD $0.00"
                    String summaryAmount = cartLib.getSummaryAmountInCart();
                        assertTextStringContains(summaryAmount, data.get("Price"));
                    // Verify usage period on cart
                    verifyReportingUsagePeriod();
                    //Proceed to checkout
                    orderLib.proceedToCheckout();
                    orderLib.shippingBillPayContinueButton();  // Click continue on  shipping address
                    orderLib.shippingBillPayContinueButton();  // Billing address continue button
                    orderLib.addNewCardInPayment(data.get("cardNumber"), data.get("cardName"), data.get("month"), data.get("year"), data.get("poNumebr"), data.get("POReleaseNumber"));
                    orderLib.clickOnReviewOrderButton();  // Click Review order button
                    // Place Order
                    String amount = cartLib.getSummaryAmountInCart();
                    orderLib.placeOrderAndVerifyReceiptOrderAndDate(amount);

                    // account tools >> Software License Agreements
                    commonLib.clickOnAccountToolsAndClickOnProductGrp(data.get("Tools_Menu"), data.get("Tools_Menu_DD"));
                    // Select Software  Lic Agreements
                    selectSPLADetailsProductCheckBox(data.get("SPLA"));
                    // verify search results and select first product
                    searchLib.verifysearchResultsPage();
                    pipLib.selectFirstProductAddToCartAndVerifyCart();
                    verifyReportingUsagePeriod();
                    // Logout
                    commonLib.clickLogOutLink(data.get("Logout"));

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

