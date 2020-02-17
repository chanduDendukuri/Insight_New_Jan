package com.insight.WebTest.Canada;

import java.util.Hashtable;

import com.insight.Lib.*;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.insight.accelerators.ReportControl;
import com.insight.accelerators.TestEngineWeb;
import com.insight.googledrive.ReportStatus;
import com.insight.utilities.TestUtil;

public class CAN14_NoDiscoverCardTest extends CanadaLib {

    CMTLib cmtLib = new CMTLib();
    CommonLib commonLib = new CommonLib();
    CartLib cartLib = new CartLib();
    SearchLib searchLib = new SearchLib();
    ProductDetailLib prodDetailsLib = new ProductDetailLib();
    OrderLib orderLib = new OrderLib();
    ProductDisplayInfoLib pipLib = new ProductDisplayInfoLib();
    SewpLib sewpLib = new SewpLib();
    ShipBillPayLib shipbLib = new ShipBillPayLib();
    MarriottIntlCorpLib mic = new MarriottIntlCorpLib();
    CommonCanadaLib ccp = new CommonCanadaLib();

    // #############################################################################################################
    // #       Name of the Test         :  CAN14_NoDiscoverCardTest
    // #       Migration Author         :  Cigniti Technologies
    // #
    // #       Date of Migration        : October 2019
    // #       DESCRIPTION              :
    // #       Parameters               : StartRow ,EndRow , nextTestJoin
    // #
    // ###############################################################################################################

    @Parameters({"StartRow", "EndRow", "nextTestJoin"})
    @Test
    public void Tc_CAN14(int StartRow, String EndRow, boolean nextTestJoin) throws Throwable {

        int counter = 0;
        try {
            int intStartRow = StartRow;
            int intEndRow = ReportControl.fnGetEndRowCunt(EndRow, "TC14_CAN14NoDiscoverCard", TestDataInsight, "Canada");
            for (int intCounter = intStartRow; intCounter <= intEndRow; intCounter++) {
                try {
                    counter = intCounter;
                    fnOpenTest();
                    ReportStatus.fnDefaultReportStatus();
                    ReportControl.intRowCount = intCounter;
                    Hashtable<String, String> data = TestUtil.getDataByRowNo("TC14_CAN14NoDiscoverCard", TestDataInsight,
                            "Canada", intCounter);
                    TestEngineWeb.reporter.initTestCaseDescription("NoDiscoverCard");
                    CommonLib commonLib = new CommonLib();
                    CMTLib cmtLib = new CMTLib();
                    CartLib cartLib = new CartLib();
                    OrderLib orderLib = new OrderLib();
                    CanadaLib canadaLib = new CanadaLib();
                    RequisitionProcessingLib req = new RequisitionProcessingLib();
                    ProductDisplayInfoLib prodinfo = new ProductDisplayInfoLib();

                    navigateTo("https://ca-uat1.insight.com/insightweb/login");
                    canadaLib.CandaHomePageVerification();
                    cmtLib.loginAsEndUser(data.get("EndUSER"), data.get("Password"));
                    Thread.sleep(9000);
//Canada verification

                   // shipbLib.verifyWEbsiteIsCannada();
                    ccp.verifyCompanyLogosAvailability();
                    assertTrue(ccp.verifyCanadaFlagAvailability(),"Logged with Canada user");
                  //  canadaLib.verifyCanadaWebgroup();

                    //canadaLib.getWeGrpDDValues();

                    shipbLib.PaymentandCardsTextverify(data.get("Tools_Menu"), data.get("Tools_Menu_DD"), data.get("tabName2"));
                    //clickOnEnterACard(data.get("Creditcard"));
                    scrollToBottomWithCordinate("300");

                    clickOnEnterNewCard();
                    clickOnCancelButton();
                    scrollToBottomWithCordinate("-100");
                    //canadaLib.clickOnSideMenuSelectAccountToolOptions("Orders","My Requisition History" );
                    commonLib.clickOnAccountToolsAndClickOnProductGrp("Orders", "My Requisition History");
                    //canadaLib.clickOnSideMenuSelectAccountToolOptions(data.get("Tools_Menu1"),data.get("Tools_Menu_DD1"));
                    req.clickExpandSearchIcon();

                    selectStartDateFromCal("1 December 2018");
                    scrollToBottomWithCordinate("160");
                    selectEndDateFromCal("1 December 2019");
                    //selectStartDateFromCal( data.get("date"));
                    enterReferenceNumber("59151231");
                    //enterReferenceNumber(data.get("Refno"));

                    clickOnSearchButtonInRequestionSearch();
                    if (visibilityOfReferenceNoInRequestionSearch()) {
                        clickOnReferenceNoLink();
                        verifyApprovalManagementHeader();
                        scrollToBottomWithCordinate("800");
                        clickOnUpdateButton();
                        scrollToBottomWithCordinate("220");
                        clickOnEnterNewCard();

                        getListOfCardTypes();
                    } else {
                        reporter.failureReport("No Records found", "No Records found", "", driver);
                    }
//****************************************************  code ************************************************************************************************

                    //commonLib.searchProduct(data.get("Search_Item"));
                    commonLib.searchProduct("HP Workstations");
                    //searchLib.verifyTheResultsForSearchTerm(data.get("Search_Item"));
                    searchLib.verifyTheResultsForSearchTerm("HP Workstations");
                    searchLib.removeTheFilterForInStockOnly("filter");
                    prodinfo.getPartNumberInSearchResultsPage();
                    commonLib.addFirstDisplyedItemToCartAndVerify();

                    String partNumber1 = cartLib.getPartNumber();
                    System.out.println("partNumber1" + partNumber1);
//						//commonLib.continueToShopping();
//						commonLib.clickCart();
                    canadaLib.continueToCheckout();
                    cartLib.verifyCartPageAvailablity();
                    prodinfo.verifyCartPageAndPartDetails();
                    cartLib.verifyItemInCart(partNumber1);
                    orderLib.proceedToCheckout();
                    orderLib.clickOnAdditionalInfoContinueButton();
                    canadaLib.verifySBP();
                    orderLib.shippingBillPayContinueButton();
                    orderLib.shippingOptionsCarrierSelection();
                    // Click continue on shipping options


                    orderLib.billingAddressContinueButton();
                    //orderLib.termsInPaymentInfo(data.get("PONumber"), data.get("POReleaseNumber"));
                    //orderLib.termsInPaymentInfo("7989517711", "8886366331");
					/*	orderLib.selectPaymentInfoMethodCreditCard(data.get("cardNumber"), data.get("cardName"), data.get("month"),
								data.get("year"),data.get("PO_Number"),data.get("POReleaseNumber"));*/
                    orderLib.selectPaymentInfoMethodCreditCard("6011111111111117", "Chandu Dendukuri", "12",
                            "2020", "01919", "123");
                    orderLib.getNoCardErrorMessage();

                    //mic.SwitchWebGroup(data.get("webGroup"));
                    ccp.clickOnUSAccountUnderWebGroup();
                    if (driver.getCurrentUrl().contains("uat1")) {
                        reporter.SuccessReport("URL Swithing", "URL is switched from canada to US", driver.getCurrentUrl());
                    } else {
                        reporter.failureReport("URL Swithing", "URL is switched from canada to US", driver.getCurrentUrl(), driver);
                    }

   // Handle login popup
                    assertTrue(driver.getCurrentUrl().contains("US"),"US URL launched");
//Web group ValidationH
                  //  canadaLib.verifyCanadaWebgroup();
                    cmtLib.handlebetaPopup();
                    shipbLib.PaymentandCardsTextverify(data.get("Tools_Menu"), data.get("Tools_Menu_DD"), data.get("tabName2"));
                    //clickOnEnterACard(data.get("Creditcard"));
                    cmtLib.handlebetaPopup();
                    clickOnEnterNewCard();
scrollToBottomWithCordinate("200");
//verification disco();
                       ccp.getListOfCardTypes();
                       scrollToBottomWithCordinate("-500");
                    ccp.clickOnAccountToolsAndClickOnProductGrp("My Company");
                   // ccp.clickOnAccountToolsAndClickOnProductGrp(data.get("Tools_Menu1"));
                    cmtLib.handlebetaPopup();
                    commonLib.clickOnAccountToolsAndClickOnProductGrp("Orders", "My Requisition History");
                    cmtLib.handlebetaPopup();
                    commonLib.searchProduct("HP Workstations");
                    //searchLib.verifyTheResultsForSearchTerm(data.get("Search_Item"));
                    searchLib.verifyTheResultsForSearchTerm("HP Workstations");
                    searchLib.removeTheFilterForInStockOnly("filter");
                    prodinfo.getPartNumberInSearchResultsPage();
                    commonLib.addFirstDisplyedItemToCartAndVerify();

                    String partNumber2 = cartLib.getPartNumber();
                    System.out.println("partNumber1" + partNumber2);
//						//commonLib.continueToShopping();
//						commonLib.clickCart();
                    canadaLib.continueToCheckout();
                    cartLib.verifyCartPageAvailablity();
                    prodinfo.verifyCartPageAndPartDetails();
                    cartLib.verifyItemInCart(partNumber2);

                    orderLib.proceedToCheckout();
                    cartLib.clickOnContinueButtonInAddInformtion();
                    canadaLib.verifySBP();
                    orderLib.shippingBillPayContinueButton();
                    orderLib.shippingOptionsCarrierSelection();
                    // Click continue on shipping options
                    orderLib.billingAddressContinueButton();
                    orderLib.selectPaymentInfoMethodCreditCard("6011111111111117", "Chandu Dendukuri", "12",
                            "2020", "01919", "123");
                    orderLib.clickOnReviewOrderButton();
                    assertTrue(ccp.verifyPlaceOrderHeader(),"Place order header is visible");
                    assertTrue(ccp.verifyDiscoveryCardAvailability(),"Available Credit card name " + ccp.getDiscoveryCardInformation()) ;

//verify place order
//Discovery card availability nneeds to be verifed in shipbill page

                    commonLib.clickLogOutLink(data.get("Logout_Header"));
                    System.out.println("Test completed");

                } catch (Exception e) {
                    ReportStatus.blnStatus = false;
                   // reporter.failureReport("Exception Occurred","Exception ",e.getClass().getSimpleName(),driver);
                    gErrorMessage = e.getClass().getSimpleName();
                    gTestStatus = false;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            ReportStatus.blnStatus = false;
            	gErrorMessage = e.getClass().getSimpleName();
            gTestStatus = false;
           // reporter.failureReport("Exception Occurred","Exception ",e.getClass().getSimpleName(),driver);

            ReportStatus.fnUpdateResultStatus("NoDiscoverCard", "Tc_CAN14", ReportStatus.strMethodName, 1, browser);
            throw new RuntimeException(e);
        } finally {
            ReportControl.fnEnableJoin();
            ReportStatus.fnUpdateResultStatus("NoDiscoverCard", "Tc_CAN14", ReportStatus.strMethodName, counter, browser);
            fnCloseTest();
            ReportControl.fnNextTestJoin(nextTestJoin);
        }
    }
}

