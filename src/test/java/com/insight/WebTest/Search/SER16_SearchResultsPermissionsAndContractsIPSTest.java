package com.insight.WebTest.Search;

import java.util.Hashtable;

import com.insight.Lib.*;
import com.insight.accelerators.ReportControl;
import com.insight.accelerators.TestEngineWeb;
import com.insight.googledrive.ReportStatus;
import com.insight.utilities.TestUtil;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class SER16_SearchResultsPermissionsAndContractsIPSTest extends SearchLib {

	ProductDisplayInfoLib prodInfoLib = new ProductDisplayInfoLib();
	CMTLib cmtLib = new CMTLib();
	CommonLib commonLib = new CommonLib();

	    // #############################################################################################################
	 	// #    Name of the Test         : SER16_SearchResults-PermissionsAndContractsIPS
	 	// #    Migration Author         : Cigniti Technologies
	    // #
	 	// #    Date of Migration        : August 2019
	 	// #    Description              : Web - Search - Logged in IPS - default permissions
	 	// #    Parameters               : StartRow ,EndRow , nextTestJoin
	 	// #
	 	// ############################################################################################################# 

	@Parameters({ "StartRow", "EndRow", "nextTestJoin" })
	@Test
	public void Tc_SER16(int StartRow, String EndRow, boolean nextTestJoin) throws Throwable {
		int counter=0;
		try {
			int intStartRow = StartRow;
			int intEndRow = ReportControl.fnGetEndRowCunt(EndRow, "SER16_SearchResultsPermissionsAndContractsIPS",
					TestData, "Web_Search");
			for (int intCounter = intStartRow; intCounter <= intEndRow; intCounter++) {
				try {
					// initilizing libraries and testdata
					ReportStatus.fnDefaultReportStatus();
					ReportControl.intRowCount = intCounter;
					Hashtable<String, String> data = TestUtil.getDataByRowNo(
							"SER16_SearchResultsPermissionsAndContractsIPS", TestData, "Web_Search",
							intCounter);
					TestEngineWeb.reporter.initTestCaseDescription("SearchResultsPermissionsAndContractsIPS");
					
					// Test Steps execution
					fnOpenTest();

					cmtLib.loginToCMT(data.get("Login"));
					cmtLib.searchForWebGroup(data.get("WebGrp"));
					cmtLib.clickOnTheWebGroup(data.get("WebGrp_Name"));
					cmtLib.hoverOnManageWebGroupsAndSelectOptions(data.get("Manage_Web_Grp_Options"));
					cmtLib.searchForaUserAndSelect(data.get("LnameEmailUname"), data.get("ContactName"));
					String[] permissions1=data.get("Set_Permission1").split(",");
					for (i = 0; i < permissions1.length; i++) { 
					cmtLib.setPermissions(data.get("Menu_Name"),permissions1[i] );
					}
					// Login to CMT
					cmtLib.loginAsAdminCMT();
					cmtLib.loginVerification(data.get("ContactName"));

					// Back to UAT and verify the above enabled settings
					verifyContractAllDisplayed();
					searchInHomePage(data.get("SearchText"));
					verifyTheResultsForSearchTerm(data.get("SearchText"));
					clickMorePricesAndViewContracts();
					Thread.sleep(3000);
					VerifyDefaultUSDContractPrice();
					selectNewcontract(data.get("Contract_Name"));
					searchInHomePage(data.get("SearchText"));
					verifyTheResultsForSearchTerm(data.get("SearchText"));
					prodInfoLib.verifyContractNameInProdDetailsPageAndAddToCart(data.get("Contract_Name"),
							data.get("Quantity"));
					commonLib.clickCart();
					Thread.sleep(3000);
					prodInfoLib.verifyContractInCartScreen(data.get("Contract_Name"));
					cmtLib.navigateBackToCMT();

					// Navigate to CMT and Enable "Remove US Communities as
					// Default Contract" setting
					cmtLib.setPermissions(data.get("Menu_Name"), data.get("Set_Permission2"));
					cmtLib.permissionFromDD(data.get("Set_Permission3"), data.get("Permission_Drop_Down"));

					// Navigate to UAT and verify the USC price is not displayed
					// as default for products
					cmtLib.loginAsAdminCMT();
					verifyContractAllDisplayed();
					searchInHomePage(data.get("SearchText"));
					verifyTheResultsForSearchTerm(data.get("SearchText"));
					Thread.sleep(3000);
					verifyUSCcontractNotPresent();
					clickMorePricesAndViewContracts();
					cmtLib.navigateBackToCMT();

					// Navigate back to CMT and Disable Open Market
					cmtLib.setPermissionsToDisable(data.get("Menu_Name"), data.get("Set_Permission"));

					// Verify the changes effected.
					cmtLib.loginAsAdminCMT();
					verifyContractAllDisplayed();
					searchInHomePage(data.get("SearchText"));
					verifyTheResultsForSearchTerm(data.get("SearchText"));
					verifyUSCcontractNotPresent();

					// Navigate to CMT, enable Open Market - Your Price and
					// disable fed_view_contracts
					cmtLib.navigateBackToCMT();
					cmtLib.setPermissions(data.get("Menu_Name"), data.get("Set_Permission"));
					cmtLib.permissionFromDD(data.get("Set_Permission3"), data.get("Permission_Drop_Down"));
					cmtLib.setPermissionsToDisable(data.get("Menu_Name"), data.get("Set_Permission4"));

					// Verify that "Your Price" for all parts in search results
					// page
					cmtLib.loginAsAdminCMT();
					searchInHomePage(data.get("SearchText"));
					verifyTheResultsForSearchTerm(data.get("SearchText"));
					prodInfoLib.verifyYourPriceExists();

					// Navigate to CMT, fed_open_market;on and
					// "fed_view_contracts;on"
					cmtLib.navigateBackToCMT();
					String[] permissions = data.get("Set_Permission5").split(",");
					for (i = 0; i < permissions.length; i++) {
						cmtLib.setPermissions(data.get("Menu_Name"), permissions[i]);
					}
					cmtLib.loginAsAdminCMT();
					verifyContractAllDisplayed();
					selectNewcontract(data.get("Contract_Name"));

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
			ReportStatus.fnUpdateResultStatus("SearchResultsPermissionsAndContractsIPS", "SER16", ReportStatus.strMethodName,
					1, browser);
			throw new RuntimeException(e);
		}

		finally {
        	ReportControl.fnEnableJoin();
			ReportStatus.fnUpdateResultStatus("SearchResultsPermissionsAndContractsIPS", "SER16", ReportStatus.strMethodName, counter, browser);
			fnCloseTest();
			ReportControl.fnNextTestJoin(nextTestJoin);
		}
	}

}
