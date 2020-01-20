package com.insight.SmartTest.Scripts.IUS;

import java.util.Hashtable;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.insight.SmartTest.Lib.HomeLib;
import com.insight.SmartTest.Lib.loginLib;
import com.insight.accelerators.ReportControl;
import com.insight.accelerators.TestEngineWeb;
import com.insight.googledrive.ReportStatus;
import com.insight.utilities.TestUtil;

public class SER01_AccountSearchTest extends HomeLib{
	
	loginLib loginlib=new loginLib();

	// #############################################################################################################
		// # Name of the Test : SER01_AccountSearch
		// # Migration Author : Cigniti Technologies
		// #
		// # Date of Migration : OCT 2019
		// # DESCRIPTION : Purpose of this test method is to verify the compare
		// functionality in the products display page.
		// # Parameters : StartRow ,EndRow , nextTestJoin
		// #
		// ###############################################################################################################

		@Parameters({ "StartRow", "EndRow", "nextTestJoin" })
		@Test
		public void TC_SER01(int StartRow, String EndRow, boolean nextTestJoin) throws Throwable {
			int counter = 0;
			try {
				int intStartRow = StartRow;
				int intEndRow = ReportControl.fnGetEndRowCunt(EndRow, "SER01_AccountSearch", TestData_Smart, "Search");
				for (int intCounter = intStartRow; intCounter <= intEndRow; intCounter++) {
					try {
						counter = intCounter;
						fnOpenTest();
						ReportStatus.fnDefaultReportStatus();
						ReportControl.intRowCount = intCounter;
						Hashtable<String, String> data = TestUtil.getDataByRowNo("SER01_AccountSearch", TestData_Smart,
								"Search", intCounter);
						TestEngineWeb.reporter.initTestCaseDescription("SearchProductSplChar");
						navigateToApplication("SMART");
					
					loginlib.loginIntoSmartApplication(data.get("UserName"),data.get("Password"));
					accountsearch();
					//clickOnCheckoxinAccountSearch(data.get("type1"));//SoldTo
					textfieldsinAccountSearchpage(data.get("TextField1"),data.get("SearchTerm"));//Last Name / Name 1---- 
					searchinAccountSearchPage();
					verifyResultsofLastname(data.get("Lname"));
					clearTextfieldsinAccountSearchpage(data.get("TextField1"));//Last Name / Name 1
					clickOnCheckoxinAccountSearch(data.get("type1"));//SoldTo
					//Ship To
					clickOnCheckoxinAccountSearch(data.get("type2"));//ShipTo
					textfieldsinAccountSearchpage(data.get("TextField2"),data.get("Text2"));//Street---4 EXECUTIVE PARK DR
					searchinAccountSearchPage();
					verifyResultsofStreet(data.get("Street"));//Street---4 EXECUTIVE PARK DR
				    //Clear Fields
					clearTextfieldsinAccountSearchpage(data.get("TextField2"));
					//Search with Contact
					clickOnCheckoxinAccountSearch(data.get("type2"));//SoldTo
					clickOnCheckoxinAccountSearch(data.get("type3"));//Contact
					textfieldsinAccountSearchpage(data.get("TextField3"),data.get("Text3"));//First Name / Name 2
					textfieldsinAccountSearchpage(data.get("TextField1"),data.get("Text1"));//Last Name / Name 1
					searchinAccountSearchPage();
					verifyResultsofLastname(data.get("Lname1"));
					verifyResultsofFirstname(data.get("Fname"));
					clearTextfieldsinAccountSearchpage(data.get("TextField3"));
					clearTextfieldsinAccountSearchpage(data.get("TextField1"));
					//clickOnCheckoxinAccountSearch(data.get("type3"));//contact
					textfieldsinAccountSearchpage(data.get("TextField4"),data.get("Email"));//Email
					searchinAccountSearchPage();
					verifyResultsofEmail(data.get("Email"));
					clearTextfieldsinAccountSearchpage(data.get("TextField4"));
					AccountSearchCloseButton();
					System.out.println("Testcase completed");
					
					
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
			//ReportStatus.fnUpdateResultStatus("SER01_AccountSearch", "SER_01", ReportStatus.strMethodName, 1, browser);
			throw new RuntimeException(e);
		}
        finally {
			ReportControl.fnEnableJoin();
			ReportStatus.fnUpdateResultStatus("SER01_AccountSearch", "SER_01", ReportStatus.strMethodName, counter, browser);
			fnCloseTest();
			ReportControl.fnNextTestJoin(nextTestJoin);
		}
	}

}
