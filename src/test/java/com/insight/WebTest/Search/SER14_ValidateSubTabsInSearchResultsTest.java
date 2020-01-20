package com.insight.WebTest.Search;

import java.util.Hashtable;

import com.insight.Lib.*;
import com.insight.accelerators.ReportControl;
import com.insight.accelerators.TestEngineWeb;
import com.insight.googledrive.ReportStatus;
import com.insight.utilities.TestUtil;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class SER14_ValidateSubTabsInSearchResultsTest extends SearchLib {

	ProductDisplayInfoLib prodInfoLib = new ProductDisplayInfoLib();
	CMTLib cmtLib = new CMTLib();

	    // #############################################################################################################
		// #    Name of the Test         : SER14_ValidateSubTabsInSearchResults
		// #    Migration Author         : Cigniti Technologies
	    // #
		// #    Date of Migration        : August 2019
		// #    Description              : This method To Test Menu Search.
		// #    Parameters               : StartRow ,EndRow , nextTestJoin
		// #
		// #############################################################################################################

	@Parameters({ "StartRow", "EndRow", "nextTestJoin" })
	@Test
	public void Tc_SER14(int StartRow, String EndRow, boolean nextTestJoin) throws Throwable {
		int counter =0;
		try {
			int intStartRow = StartRow;
			int intEndRow = ReportControl.fnGetEndRowCunt(EndRow, "SER14_ValidateSubTabsInSearchResults", TestData,
					"Web_Search");
			for (int intCounter = intStartRow; intCounter <= intEndRow; intCounter++) {
				try {
					// initilizing libraries and testdata
					ReportStatus.fnDefaultReportStatus();
					ReportControl.intRowCount = intCounter;
					Hashtable<String, String> data = TestUtil.getDataByRowNo("SER14_ValidateSubTabsInSearchResults",
							TestData, "Web_Search", intCounter);
					TestEngineWeb.reporter.initTestCaseDescription("ValidateSubTabsInSearchResults");
					

					// Test Steps execution
					fnOpenTest();

					// Login to CMT tool
					cmtLib.loginToCMT(data.get("Login"));
					cmtLib.searchForWebGroup(data.get("WebGrp"));
					cmtLib.clickOnTheWebGroup(data.get("WebGrp_Name"));
					cmtLib.hoverOnManageWebGroupsAndSelectOptions(data.get("Manage_Web_Grp_Options"));
					cmtLib.searchForaUserAndSelect(data.get("LnameEmailUname"), data.get("ContactName"));
					// Disable whyinsight -OFF,"services;OFF",enable_manage;OFF,shop_by_brand;OFF.
					String[] permissions=data.get("Set_Permission").split(",");
					for (i = 0; i < permissions.length; i++) {
					cmtLib.setPermissionsToDisable(data.get("Menu_Name"), permissions[i]);
					}
					// Login AS to UAT
					cmtLib.loginAsAdminCMT();
					verifyMenusDisabledOnHomePage(data.get("HeaderName"), data.get("headerlist"),data.get("ShopAllBrands"));

					// Navigate Back to CMT tool
					cmtLib.navigateBackToCMT();
					// Enable whyinsight,services,enable_manage,shop_by_brand
					cmtLib.clickOnRolesAndPermissionsAndSetPermission(data.get("Menu_Name"),data.get("Set_Permission"));
					cmtLib.loginAsAdminCMT();

					// login As to UAT
					verifyMenuEnabledOnHomeScreen(data.get("HeaderName"), data.get("headerlist"),data.get("ShopAllBrands"));

				}

				catch (Exception e) {
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
			ReportStatus.fnUpdateResultStatus("ValidateSubTabsInSearchResults", "SER14", ReportStatus.strMethodName,
					1, browser);
			throw new RuntimeException(e);
		}

		finally {
        	ReportControl.fnEnableJoin();
			ReportStatus.fnUpdateResultStatus("ValidateSubTabsInSearchResults", "SER14", ReportStatus.strMethodName, counter, browser);
			fnCloseTest();
			ReportControl.fnNextTestJoin(nextTestJoin);
		}
	}

}
