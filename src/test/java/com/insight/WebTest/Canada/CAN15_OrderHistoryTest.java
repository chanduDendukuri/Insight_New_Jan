package com.insight.WebTest.Canada;


import java.util.Hashtable;

import com.insight.Lib.*;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.insight.accelerators.ActionEngine;
import com.insight.accelerators.ReportControl;
import com.insight.accelerators.TestEngineWeb;
import com.insight.googledrive.ReportStatus;
import com.insight.utilities.TestUtil;

public class CAN15_OrderHistoryTest extends ActionEngine {


    // #############################################################################################################
    // # Name of the Test : CAN13_CartBundles
    // # Migration Author : Cigniti Technologies
    // #
    // # Date of Migration : August 2019
    // # DESCRIPTION : This method is to perform Basic Cart operations.
    // # Parameters : StartRow ,EndRow , nextTestJoin
    // #
    // ###############################################################################################################
    @Parameters({"StartRow", "EndRow", "nextTestJoin"})
    @Test
    public void Tc_CAN15(int StartRow, String EndRow, boolean nextTestJoin) throws Throwable {

        int counter = 0;
        try {
            int intStartRow = StartRow;
            int intEndRow = ReportControl.fnGetEndRowCunt(EndRow, "CAN15_OrderHistory", TestDataInsight, "Canada");
            for (int intCounter = intStartRow; intCounter <= intEndRow; intCounter++) {
                try {
                    counter = intCounter;
                    fnOpenTest();
                    ReportStatus.fnDefaultReportStatus();
                    ReportControl.intRowCount = intCounter;
                    Hashtable<String, String> data = TestUtil.getDataByRowNo("CAN15_OrderHistory", TestDataInsight,
                            "Canada", intCounter);
                    TestEngineWeb.reporter.initTestCaseDescription("OrderHistory");

                    CMTLib cmtLib = new CMTLib();
                    CanadaLib canadaLib = new CanadaLib();
                    ShipBillPayLib shipbLib = new ShipBillPayLib();
                    InvoiceHistoryLib invoice = new InvoiceHistoryLib();
                    CommonLib commonLib = new CommonLib();
                    cmtLib.loginToCMT(data.get("Header"));
                    cmtLib.searchForWebGroup(data.get("WebGrp"));
                    cmtLib.clickOnTheWebGroup(data.get("MgContactName"));
                    cmtLib.verifyManageWebGroupSettings();
                    cmtLib.hoverOnManageWebGroupsAndSelectOptions(data.get("ManageWebGrpOptions"));
                    cmtLib.verifyManageWebGroupsUserManagement();
                    cmtLib.searchForaUserAndSelect(data.get("LnameEmailUname"), data.get("ContactName"));
                    //cmtLib.setPermissions(data.get("Menu_Name"), data.get("Set_Permission"));
                    cmtLib.clickOnPermissionAndRolesMenu(data.get("Menu_Name"));
                    cmtLib.permissionForDD(data.get("permission"), data.get("Permission_Drop_Down"));
                    String[] permissions1 = data.get("Set_Permission").split(",");
                    for (i = 0; i < permissions1.length; i++) {
                        //cmtLib.setPermissions(data.get("Menu_Name"),permissions1[i]);
                        cmtLib.setPermissionsToDisable(data.get("Menu_Name"), permissions1[i]);
                    }

                    // Remove us comm default OFF
                    //cmtLib.setPermissionsToDisable(data.get("Menu_Name"), data.get("Set_Permission2"));

                    //cmtLib.setPermissions(data.get("Menu_Name"),data.get("Enable_Purchasing_Popup"));
                    cmtLib.clickOnloginAs();
                    switchToChildWindow();
                    cmtLib.loginVerification(data.get("ContactName"));
                    shipbLib.verifyWEbsiteIsCannada();
                    canadaLib.verifyCanadaWebgroup();
                    canadaLib.clickOnSideMenuSelectAccountToolOptions(data.get("Tools_Menu"), data.get("Tools_Menu_DD"));
                    invoice.verifyRecentHistoryHeader();
                    canadaLib.advanceSearchInOrderHistory(data.get("SelectOrder"), data.get("OrderNumber"));
                    scrollToBottomWithCordinate("-200");
                    canadaLib.verifyAccountNumberDetailsInResultsGrid();
                    //verification required
                    canadaLib.clickOnSideMenuSelectAccountToolOptions(data.get("Tools_Menu"), data.get("Tools_Menu_DD"));
                    invoice.verifyRecentHistoryHeader();
                    canadaLib.advanceSearchInOrderHistory(data.get("SelectReference"), data.get("ReferenceNumber"));
                    canadaLib.verifyAccountNumberDetailsInResultsGrid();
                    scrollToBottomWithCordinate("-200");
                    canadaLib.clickOnSideMenuSelectAccountToolOptions(data.get("Tools_Menu"), data.get("Tools_Menu_DD"));
                    invoice.verifyRecentHistoryHeader();
                    canadaLib.advanceSearchInOrderHistory(data.get("SelectPurchaseOrder"), data.get("PurchaseNumber"));
                    canadaLib.verifyAccountNumberDetailsInResultsGrid();
                    canadaLib.clickOnAccountNumberDetailsInResultsGrid();
                    canadaLib.verifyOrderDetailsHeader();
                    canadaLib.getEwrVlaue();
                    //canadaLib.verifyDetailsInOrderPage(data.get("OrderNumber"), data.get("PurchaseNumber"), data.get("ReferenceNumber"), data.get("EWR"));
                    //
                    if (canadaLib.clickOnAssetAndSerialNumber()) {
                        if (canadaLib.verifyAssetSerialNuberValues()) {
                            canadaLib.getAssetAndSerialNumberValuesOnPopups();
                            canadaLib.closeAssetSerialNumberPopup();
                        }else {
                            reporter.failureReport("Link available", "Asset link availability is ", "Not available", driver);
                        }
                    }
                    scrollToBottomWithCordinate("-200");
                    canadaLib.clickOnSideMenuSelectAccountToolOptions(data.get("Tools_Menu"), data.get("Tools_Menu_DD"));
                    invoice.verifyRecentHistoryHeader();
                    invoice.clickOnAdvancedSearchSearchButton();
                    scrollToBottomWithCordinate("160");

                    invoice.selectStartandEndDateInRecentHistory(data.get("Date"));
                    invoice.selectEndDateInRecentHistory(data.get("End_Date"));
                   // invoice.datePickerEndDateCalender(data.get("End_Date"));
                    canadaLib.clickOnSearchButtonInRecentOrders();
                    cmtLib.getOrderNumbersFromRecentOrders();
/*

				canadaLib.clickOnOrderNumberToViewDetails(data.get("OrderNumber"));
*/
                    commonLib.clickLogOutLink(data.get("Logout_Header"));//fnCloseTest();

                    System.out.println("Test completed");

                } catch (Exception e) {
                    ReportStatus.blnStatus = false;
                    //	gErrorMessage = e.getMessage();
                    gTestStatus = false;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            ReportStatus.blnStatus = false;
            //	gErrorMessage = e.toString();
            gTestStatus = false;
            ReportStatus.fnUpdateResultStatus("OrderHistory", "TC_CAN15", ReportStatus.strMethodName, 1, browser);
            throw new RuntimeException(e);
        } finally {
            ReportControl.fnEnableJoin();
            ReportStatus.fnUpdateResultStatus("OrderHistory", "TC_CAN15", ReportStatus.strMethodName, counter, browser);
            fnCloseTest();
            ReportControl.fnNextTestJoin(nextTestJoin);
        }
    }
}