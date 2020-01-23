package com.insight.WebTest.Search;

import java.util.Hashtable;

import com.insight.Lib.*;
import com.insight.accelerators.ReportControl;
import com.insight.accelerators.TestEngineWeb;
import com.insight.googledrive.ReportStatus;
import com.insight.utilities.TestUtil;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class SER11_SearchSuggestionsTest extends SearchLib {

	ProductDisplayInfoLib prodInfoLib = new ProductDisplayInfoLib();
	CMTLib cmtLib = new CMTLib();
	CommonLib commonLib=new CommonLib();

	    // #############################################################################################################
		// #    Name of the Test         : SER11_SearchSuggestions
		// #    Migration Author         : Cigniti Technologies
	    // #
		// #    Date of Migration        : August 2019
		// #    Description              : This method is used to Test Search Suggestions.
		// #    Parameters               : StartRow ,EndRow , nextTestJoin
		// #
		// #############################################################################################################

	@Parameters({ "StartRow", "EndRow", "nextTestJoin" })
	@Test
	public void Tc_SER11(int StartRow, String EndRow, boolean nextTestJoin) throws Throwable {
		int counter=0;
		try {
			int intStartRow = StartRow;
			int intEndRow = ReportControl.fnGetEndRowCunt(EndRow, "SER11_SearchSuggestions", TestData, "Web_Search");
			for (int intCounter = intStartRow; intCounter <= intEndRow; intCounter++) {
				try {
					// initilizing libraries and testdata
					ReportStatus.fnDefaultReportStatus();
					ReportControl.intRowCount = intCounter;
					Hashtable<String, String> data = TestUtil.getDataByRowNo("SER11_SearchSuggestions",
							TestData, "Web_Search", intCounter);
					TestEngineWeb.reporter.initTestCaseDescription("SearchSuggestions");
					
					// Test Steps execution
					fnOpenTest();

					searchInHeaderSelectFromSuggestions(data.get("SearchText"));
					verifyBreadCrumbInSearchResultsPage(data.get("SearchText"));

					// login to CMT
					cmtLib.loginToCMTSelectUserAndLoginAS(data.get("Login"), data.get("WebGrp"),
							data.get("WebGrp_Name"), data.get("Manage_Web_Grp_Options"), data.get("LnameEmailUname"),
							data.get("ContactName"));
					cmtLib.loginVerification(data.get("ContactName"));
					// Navigate back to UAT
					// Enable Show search Suggestions and verify
					
					selectAccountTools(data.get("FavoritesTabName"), data.get("FavoritesTabName1"));
					// Enable search Suggestions
					enableSearchSuggestions();
					updateSuggessions();
					searchInHeaderSelectFromSuggestions(data.get("SearchText"));
					verifyBreadCrumbInSearchResultsPage(data.get("SearchText"));
					
					// Disable Show search Suggestions and verify
					
					selectAccountTools(data.get("FavoritesTabName"), data.get("FavoritesTabName1"));
					// Enable search Suggestions
					disableSearchSuggestions();
					updateSuggessions();
					verifySearchSuggestionsareNotDisplayed(data.get("SearchText"));
					// Enable search Suggestions
					selectAccountTools(data.get("FavoritesTabName"), data.get("FavoritesTabName1"));
					enableSearchSuggestions();
					commonLib.clickLogOutLink(data.get("Logout"));

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
			ReportStatus.fnUpdateResultStatus("SearchSuggestions", "SER11", ReportStatus.strMethodName,
					1, browser);
			throw new RuntimeException(e);
		}

		finally {
        	ReportControl.fnEnableJoin();
			ReportStatus.fnUpdateResultStatus("SearchSuggestions", "SER11", ReportStatus.strMethodName, counter, browser);
			fnCloseTest();
			ReportControl.fnNextTestJoin(nextTestJoin);
		}
	}

}
