package com.insight.WebTest.Canada;

import java.util.Hashtable;

import com.insight.Lib.*;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.insight.accelerators.ReportControl;
import com.insight.accelerators.TestEngineWeb;
import com.insight.googledrive.ReportStatus;
import com.insight.utilities.TestUtil;

public class CAN05_MenuSearchTest extends CanadaLib {

    CMTLib cmtLib = new CMTLib();
    CommonLib commonLib = new CommonLib();
    CartLib cartLib = new CartLib();
    SearchLib searchLib = new SearchLib();
    ProductDetailLib prodDetailsLib = new ProductDetailLib();
    OrderLib orderLib = new OrderLib();
    ProductDisplayInfoLib pipLib = new ProductDisplayInfoLib();
    ShipBillPayLib sbpLib = new ShipBillPayLib();
    CommonCanadaLib ccp = new CommonCanadaLib();

    // #############################################################################################################
    // #       Name of the Test         :  CAN05_MenuSearch
    // #       Migration Author         :  Cigniti Technologies
    // #
    // #       Date of Migration        : October 2019
    // #       DESCRIPTION              : This method is to verify MenuSearch for canada
    // #       Parameters               : StartRow ,EndRow , nextTestJoin
    // #
    // ###############################################################################################################
    @Parameters({"StartRow", "EndRow", "nextTestJoin"})
    @Test
    public void Tc_CAN05(int StartRow, String EndRow, boolean nextTestJoin) throws Throwable {

        int counter = 0;
        try {
            int intStartRow = StartRow;
            int intEndRow = ReportControl.fnGetEndRowCunt(EndRow, "CAN05_MenuSearch", TestDataInsight, "Canada");
            for (int intCounter = intStartRow; intCounter <= intEndRow; intCounter++) {
                try {
                    counter = intCounter;
                    fnOpenTest();
                    ReportStatus.fnDefaultReportStatus();
                    ReportControl.intRowCount = intCounter;
                    Hashtable<String, String> data = TestUtil.getDataByRowNo("CAN05_MenuSearch", TestDataInsight,
                            "Canada", intCounter);
                    TestEngineWeb.reporter.initTestCaseDescription("MenuSearch");
                    navigateToApplication("CANADA");
                    // Perform Menu Search For Non Logged in   >> Shop all products
                    searchLib.clickonShopAllButtonsInHeaderList(data.get("HeaderName"), data.get("ShopAll"));
					assertTrue(ccp.verifySearchByAllProd(), "Verify Search all products");
					searchLib.verifyPopularProductsLabel();
                    searchLib.verifyMenusInShopAllProductsPageForCA(data.get("Menus"));
                    scrollToBottomWithCordinate("500");
                   /* assertTrue(ccp.verifySearchByAllProd(), "Verify Search all products");
                    searchLib.verifyPopularProductsLabel();*/
                    // Shop all brands
                    searchLib.clickonShopAllButtonsInHeaderList(data.get("HeaderName"), data.get("ShopAllBrands"));
                    scrollToBottomWithCordinate("500");
                    assertTrue(ccp.verifyTopBrands(), "Top brands is available");


                    searchLib.selectTopBrandsInShopAllBrandsPage(data.get("BrandLogo"), data.get("Url1"));
                    // Shop all brands  >> select by alphabetical order
                    searchLib.clickonShopAllButtonsInHeaderList(data.get("HeaderName"), data.get("ShopAllBrands"));
                    searchLib.selectBrandByAlphabetOrderSectionForCA(data.get("Url2"), data.get("Brand"));

                    assertTrue(ccp.verifyBlackBerryLogo(), "BlackBerry page is loaded");
                    cmtLib.loginToCMT(data.get("Header"));
                    cmtLib.searchForWebGroup(data.get("WebGrp"));
                    cmtLib.clickOnTheWebGroup(data.get("WebGrp_Name"));
                    cmtLib.hoverOnManageWebGroupsAndSelectOptions(data.get("Manage_Web_Grp_Options"));
                    cmtLib.searchForaUserAndSelect(data.get("LnameEmailUname"), data.get("ContactName"));
                    cmtLib.loginAsAdminCMT();
                    cmtLib.loginVerification(data.get("ContactName"));
                    searchLib.clickonShopAllButtonsInHeaderList(data.get("HeaderName"), data.get("ShopAll"));
					assertTrue(ccp.verifySearchByAllProd(), "Verify Search all products");
					searchLib.verifyPopularProductsLabel();
                    searchLib.verifyMenusInShopAllProductsPageForCA(data.get("Menus"));
                    scrollToBottomWithCordinate("500");
                    // Shop all brands
                    searchLib.clickonShopAllButtonsInHeaderList(data.get("HeaderName"), data.get("ShopAllBrands"));
                    scrollToBottomWithCordinate("500");
                    assertTrue(ccp.verifyTopBrands(), "Top brands is available");
                    searchLib.selectTopBrandsInShopAllBrandsPage(data.get("BrandLogo"), data.get("Url1"));
                    // Shop all brands  >> select by alphabetical order
                    searchLib.clickonShopAllButtonsInHeaderList(data.get("HeaderName"), data.get("ShopAllBrands"));
                    searchLib.selectBrandByAlphabetOrderSectionForCA(data.get("Url2"), data.get("Brand"));
                    assertTrue(ccp.verifyBlackBerryLogo(), "BlackBerry page is loaded");
                    // End of  test
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
            ReportStatus.fnUpdateResultStatus("CAN05_MenuSearch", "Tc_CAN05", ReportStatus.strMethodName, 1, browser);
            throw new RuntimeException(e);
        } finally {
            ReportControl.fnEnableJoin();
            ReportStatus.fnUpdateResultStatus("CAN05_MenuSearch", "Tc_CAN05", ReportStatus.strMethodName, counter, browser);
            fnCloseTest();
            ReportControl.fnNextTestJoin(nextTestJoin);
        }
    }
}


