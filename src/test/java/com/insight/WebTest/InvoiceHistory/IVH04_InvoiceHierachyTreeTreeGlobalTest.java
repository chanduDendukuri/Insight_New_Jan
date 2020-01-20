package com.insight.WebTest.InvoiceHistory;

import java.util.Hashtable;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.insight.Lib.CMTLib;
import com.insight.Lib.CanadaLib;
import com.insight.Lib.CommonLib;
import com.insight.Lib.InvoiceHistoryLib;
import com.insight.accelerators.ReportControl;
import com.insight.accelerators.TestEngineWeb;
import com.insight.googledrive.ReportStatus;
import com.insight.utilities.TestUtil;

public class IVH04_InvoiceHierachyTreeTreeGlobalTest extends InvoiceHistoryLib {
	// #############################################################################################################
	// # Name of the Test :IVH04_InvoiceHierachyTreeTreeGlobal
	// # Migration Author : Cigniti Technologies
	// #
	// # Date of Migration : OCT 2019
	// #
	// # Parameters : StartRow ,EndRow , nextTestJoin
	// #
	// ###############################################################################################################
	@Parameters({ "StartRow", "EndRow", "nextTestJoin" })
	@Test
	public void TC_IVH04(int StartRow, String EndRow, boolean nextTestJoin) throws Throwable {

		int counter = 0;
		try {
			int intStartRow = StartRow;
			int intEndRow = ReportControl.fnGetEndRowCunt(EndRow, "IVH04_InvoiceHierachyTreeTreeGlobal",
					TestDataInsight, "Invoice_History");
			for (int intCounter = intStartRow; intCounter <= intEndRow; intCounter++) {
				try {
					counter = intCounter;
					fnOpenTest();
					ReportStatus.fnDefaultReportStatus();
					ReportControl.intRowCount = intCounter;
					Hashtable<String, String> data = TestUtil.getDataByRowNo("IVH04_InvoiceHierachyTreeTreeGlobal",
							TestDataInsight, "Invoice_History", intCounter);
					TestEngineWeb.reporter.initTestCaseDescription("InvoiceHierachyTreeTreeGlobal");

					CMTLib cmtLib = new CMTLib();
					CanadaLib canadaLib = new CanadaLib();
					InvoiceHistoryLib invoiceHistoryLib = new InvoiceHistoryLib();
					CommonLib commonLib = new CommonLib();

					cmtLib.loginToCMTSearchWebGrpAndUser(data.get("Header"), data.get("WebGrp"),
							data.get("LnameEmailUname"), data.get("ContactName"));

					cmtLib.setPermissions(data.get("Menu_Name"), data.get("Set_Permission"));
					cmtLib.permissionFromDD(data.get("Set_Permission"), data.get("Permission_Dropdown_Option"));
					cmtLib.setPermissionsToDisable(data.get("Menu_Name"), data.get("Set_Permission1"));

					cmtLib.clickOnloginAs();
					switchToChildWindow();

					cmtLib.loginVerification(data.get("ContactName"));
					canadaLib.clickOnSideMenuSelectAccountToolOptions(data.get("Tools_Menu"),
							data.get("Tools_Menu_DD"));
					canadaLib.verifyInvoiceHistoryPageOpened();
					invoiceHistoryLib.clickOnAdvancedSearch();
					scrollUp();
					clickShowAccountHirerachy();
					verifyAccountHirearchyPopUp();
					verifyTree();
					closeHierarchyPopUp();

					canadaLib.clickOnSideMenuSelectAccountToolOptions(data.get("Tools_Menu"),
							data.get("Tools_Menu_DD"));
					canadaLib.verifyInvoiceHistoryPageOpened();
					invoiceHistoryLib.clickOnAdvancedSearch();
					scrollUp();
					verifySelectedOptionInAccountSelectionDD(data.get("Default_Option"));
					canadaLib.clickOnSideMenuSelectAccountToolOptions(data.get("Tools_Menu"),
							data.get("Tools_Menu_DD"));
					canadaLib.verifyInvoiceHistoryPageOpened();
					invoiceHistoryLib.clickOnAdvancedSearch();
					scrollUp();
					verifySelectedOptionInAccountSelectionDD(data.get("Default_Option"));
					closeAccountHirearchyDropdow();
					setAccountHirerachydropdown(data.get("GGP"));
					datePickerStartDateCalender(data.get("Date"));
					invoiceHistoryLib.datePickerEndDateCalender(data.get("End_Date"));
					clickOnAdvancedSearchSearchButton();
					verifySearchResultsAreDisplayed();
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
			ReportStatus.fnUpdateResultStatus("InvoiceHierachyTreeTreeGlobal", "TC_IVH04", ReportStatus.strMethodName,
					1, browser);
			throw new RuntimeException(e);
		} finally {
			ReportControl.fnEnableJoin();
			ReportStatus.fnUpdateResultStatus("InvoiceHierachyTreeTreeGlobal", "TC_IVH04", ReportStatus.strMethodName,
					counter, browser);
			fnCloseTest();
			ReportControl.fnNextTestJoin(nextTestJoin);
		}
	}
}