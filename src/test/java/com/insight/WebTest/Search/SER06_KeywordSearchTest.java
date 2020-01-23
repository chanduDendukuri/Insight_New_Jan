package com.insight.WebTest.Search;

import java.util.Hashtable;

import com.insight.Lib.*;
import com.insight.accelerators.ReportControl;
import com.insight.accelerators.TestEngineWeb;
import com.insight.googledrive.ReportStatus;
import com.insight.utilities.TestUtil;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class SER06_KeywordSearchTest extends SearchLib {

	ProductDisplayInfoLib prodInfoLib = new ProductDisplayInfoLib();
	CMTLib cmtLib = new CMTLib();
	CanadaLib canadaLib=new CanadaLib();
	CommonLib commonLib=new CommonLib();

	    // #############################################################################################################
		// #   Name of the Test               : SER06_KeywordSearch
		// #   Migration Author               : Cigniti Technologies
		// #
		// #   Date of Migration              : August 2019
		// #   DESCRIPTION                    : Purpose of this test case is to perform keyword search and verify the filters operation in the Product details page.
		// #   Parameters                     : StartRow ,EndRow , nextTestJoin
		// #
		// ###############################################################################################################

	@Parameters({ "StartRow", "EndRow", "nextTestJoin" })
	@Test
	public void TC_SER06(int StartRow, String EndRow, boolean nextTestJoin) throws Throwable {
		int counter=0;
		try {
			int intStartRow = StartRow;
			int intEndRow = ReportControl.fnGetEndRowCunt(EndRow, "SER06_KeywordSearch", TestData, "Web_Search");
			for (int intCounter = intStartRow; intCounter <= intEndRow; intCounter++) {
				try {
					// initilizing libraries and testdata
					ReportStatus.fnDefaultReportStatus();
					ReportControl.intRowCount = intCounter;
					Hashtable<String, String> data = TestUtil.getDataByRowNo("SER06_KeywordSearch", TestData,
							"Web_Search", intCounter);
					TestEngineWeb.reporter.initTestCaseDescription("KeywordSearch");
				
					// Test Steps execution

					fnOpenTest();
					
					// lexmark printers
					searchInHomePage(data.get("SearchText1"));
					verifyTheResultsForSearchTerm(data.get("SearchText1"));
					canadaLib.verifySortOption(data.get("Sort_Option")); // Best Match
					
					// Xerox Multifunction Printers
					searchInHomePage(data.get("SearchText2"));
					verifyTheResultsForSearchTerm(data.get("SearchText2"));
					canadaLib.verifySortOption(data.get("Sort_Option"));
					
					// LENOVO
					searchInHomePage(data.get("SearchText3"));
					verifyTheResultsForSearchTerm(data.get("SearchText3"));
					canadaLib.verifySortOption(data.get("Sort_Option"));
					// Logging into CMT tool
					cmtLib.loginToCMT(data.get("Login"));
					cmtLib.searchForWebGroup(data.get("WebGrp"));
					cmtLib.clickOnTheWebGroup(data.get("WebGrp_Name"));
					cmtLib.hoverOnManageWebGroupsAndSelectOptions(data.get("ManageWebGrpOptions"));
					cmtLib.hoverOnManageWebGroupsAndSelectOptions(data.get("Users"));
					cmtLib.searchForaUserAndSelect(data.get("LnameEmailUname"), data.get("ContactName"));
					cmtLib.loginAsAdminCMT();
					cmtLib.loginVerification(data.get("ContactName"));
					
					// Desktops
					searchInHomePage(data.get("SearchText4"));
					//clickShopAllTypesButton();
					verifyTheResultsForSearchTerm(data.get("SearchText4"));
					// in-stock filter verification
					verifyFilterBreadCrumb(data.get("In_Stock_Only"));
					// Approve items only
					scrollBottom();
					scrollBottom();
					filterSelectionInProductsSearchPage(data.get("Approved_Items"));
					verifyBreadCrumbInSearchResultsPage(data.get("Approved_Items_Remove_Filter"));
					verifyTheResultsForSearchTerm(data.get("SearchText4"));
					removeTheFilter(data.get("Approved_Items_Remove_Filter"));
					verifyTheResultsForSearchTerm(data.get("SearchText4"));
					
					// High performance
					searchInHomePage(data.get("SearchText5"));
					verifyTheResultsForSearchTerm(data.get("SearchText5"));
					// in stock verification
					verifyFilterBreadCrumb(data.get("In_Stock_Only"));
					// Approve items only
					scrollBottom();
					scrollBottom();
				    filterSelectionInProductsSearchPage(data.get("Approved_Items"));
					removeTheFilter(data.get("Approved_Items_Remove_Filter"));
					verifyTheResultsForSearchTerm(data.get("SearchText4"));
					
					// Printers
					searchInHomePage(data.get("SearchText6"));
					verifyTheResultsForSearchTerm(data.get("SearchText6"));
					
					// Stock only
					removeTheFilter(data.get("filter"));
					getProductCount();
					filterSelectionInProductsSearchPage(data.get("filter"));
					getProductCount();
					//removeTheFilter(data.get("Approved_Items"));
					// Manufacturers :HONEYWELL
					filterSelectionInProductsSearchPage(data.get("Manufacturer"));
					// Min price and Max price
					prodInfoLib.enterPriceDetailsFilters(data.get("Min_Price"), data.get("Max_Price"));
					// Remove price filter
					removeTheFilter(data.get("price_Filter"));
					verifyFilterSelected(data.get("Manufacturer"));
					// LogOut
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
			//gErrorMessage = e.getMessage();
			gTestStatus = false;
			ReportStatus.fnUpdateResultStatus("KeywordSearch", "SER06", ReportStatus.strMethodName,
					1, browser);
			throw new RuntimeException(e);
		}

		finally {
        	ReportControl.fnEnableJoin();
			ReportStatus.fnUpdateResultStatus("KeywordSearch", "SER06", ReportStatus.strMethodName, counter, browser);
			fnCloseTest();
			ReportControl.fnNextTestJoin(nextTestJoin);
		}
	}

}
