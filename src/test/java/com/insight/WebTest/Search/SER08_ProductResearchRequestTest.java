package com.insight.WebTest.Search;

import java.util.Hashtable;

import com.insight.Lib.*;
import com.insight.accelerators.ReportControl;
import com.insight.accelerators.TestEngineWeb;
import com.insight.googledrive.ReportStatus;
import com.insight.utilities.TestUtil;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class SER08_ProductResearchRequestTest extends SearchLib {

	ProductDisplayInfoLib prodInfoLib = new ProductDisplayInfoLib();
	CMTLib cmtLib = new CMTLib();
	CommonLib commonLib=new CommonLib();

	// #############################################################################################################
	// #    Name of the Test         : SER08_ProductResearchRequest
	// #    Migration Author         : Cigniti Technologies
	// #
	// #    Date of Migration        : August 2019
	// #    DESCRIPTION              : This method is to perform search operations in the Product Research Request page.
	// #    Parameters               : StartRow ,EndRow , nextTestJoin
	// #
	// #############################################################################################################

	@Parameters({ "StartRow", "EndRow", "nextTestJoin" })
	@Test
	public void Tc_SER08(int StartRow, String EndRow, boolean nextTestJoin) throws Throwable {
		int counter=0;
		try {
			int intStartRow = StartRow;
			int intEndRow = ReportControl.fnGetEndRowCunt(EndRow, "SER08_ProductResearchRequest", TestData,
					"Web_Search");
			for (int intCounter = intStartRow; intCounter <= intEndRow; intCounter++) {
				try {
					// initilizing libraries and testdata
					ReportStatus.fnDefaultReportStatus();
					ReportControl.intRowCount = intCounter;
					Hashtable<String, String> data = TestUtil.getDataByRowNo("SER08_ProductResearchRequest",
							TestData, "Web_Search", intCounter);
					TestEngineWeb.reporter.initTestCaseDescription("ProductResearchRequest");
					
					// Test Steps execution
					fnOpenTest();

					// Logging into CMT tool
					cmtLib.loginToCMT(data.get("Login"));
					cmtLib.searchForWebGroup(data.get("WebGrp"));
					cmtLib.clickOnTheWebGroup(data.get("WebGrp_Name"));
					cmtLib.hoverOnManageWebGroupsAndSelectOptions(data.get("Manage_Web_Grp_Options"));
					cmtLib.searchForaUserAndSelect(data.get("LnameEmailUname"), data.get("ContactName"));
					cmtLib.loginAsAdminCMT();
					cmtLib.loginVerification(data.get("ContactName"));
                     // Search            
					searchInHomePage(data.get("SearchText"));
					verifyTheResultsForSearchTerm(data.get("SearchText"));
					prodInfoLib.clickProductResearchRequest();
					prodInfoLib.verifyProductResearchRequestPopupFields();
					prodInfoLib.clickSendWithoutFillingRequestProductAndVerify(data.get("SearchText"));
					prodInfoLib.clickProductResearchRequest();
					prodInfoLib.clickProductResearchRequestAndFillDetails(data.get("Name"), data.get("Email"),
							data.get("Country"), data.get("Quantity"), data.get("PartNo."), data.get("Mnfr_Name"),
							data.get("Prod_Desc"));
					
					commonLib.clickLogOutLink(data.get("Logout"));

					// go back to CMT tool
					cmtLib.navigateBackToCMT();
					cmtLib.hoverOnManageWebGroupsAndSelectOptions(data.get("Manage_Web_Grp_Options"));
					cmtLib.searchForaUserAndSelect(data.get("LnameEmailUname"), data.get("ContactName"));
					cmtLib.loginAsAdminCMT();
					cmtLib.loginVerification( data.get("ContactName"));
					searchInHomePage(data.get("SearchText"));
					verifyTheResultsForSearchTerm(data.get("SearchText"));
					prodInfoLib.clickProductResearchRequest();
					prodInfoLib.verifyProductResearchRequestPopupFields();
					prodInfoLib.clickProductResearchRequestAndFillDetails(data.get("Name"), data.get("Email"),
							data.get("Country"), data.get("Quantity"), data.get("PartNo."), data.get("Mnfr_Name"),
							data.get("Prod_Desc"));
					
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
		//	gErrorMessage = e.getMessage();
			gTestStatus = false;
			ReportStatus.fnUpdateResultStatus("ProductResearchRequest", "SER08", ReportStatus.strMethodName,1, browser);
			throw new RuntimeException(e);
		}

		finally {
        	ReportControl.fnEnableJoin();
			ReportStatus.fnUpdateResultStatus("ProductResearchRequest", "SER08", ReportStatus.strMethodName, counter, browser);
			fnCloseTest();
			ReportControl.fnNextTestJoin(nextTestJoin);
		}
	}

}
