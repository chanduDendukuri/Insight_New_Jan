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

public class SER03_SearchProductSplCharTest  extends HomeLib{
	
	loginLib loginlib=new loginLib();

	// #############################################################################################################
		// # Name of the Test : SER03_SearchProductSplChar
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
		public void TC_SER03(int StartRow, String EndRow, boolean nextTestJoin) throws Throwable {
			int counter = 0;
			try {
				int intStartRow = StartRow;
				int intEndRow = ReportControl.fnGetEndRowCunt(EndRow, "SER03_SearchProductSplChar", TestData_Smart, "Search");
				for (int intCounter = intStartRow; intCounter <= intEndRow; intCounter++) {
					try {
						counter = intCounter;
						fnOpenTest();
						ReportStatus.fnDefaultReportStatus();
						ReportControl.intRowCount = intCounter;
						Hashtable<String, String> data = TestUtil.getDataByRowNo("SER03_SearchProductSplChar", TestData_Smart,
								"Search", intCounter);
						TestEngineWeb.reporter.initTestCaseDescription("SearchProductSplChar");
						navigateToApplication("SMART");
						loginlib.loginIntoSmartApplication(data.get("UserName"), data.get("Password"));
						clickCreateQuoteButton();
						
						enterSoldTo(data.get("SoldToAcct"));
					selectsalesOrginPopUp(data.get("SalesOrg"));
					clickOnProductSearchButton();
					
					enterSearchValue(data.get("KeyWord1"));
					clickOnSearchButtonInSearchWindow();
					Thread.sleep(2000);
					verifyResultsofMaterailIdofKeyWordSearch(data.get("KeyWord1"));
					clickOnTabOfMaterailIDProductSearchPopUp(data.get("Tab1"));//Accessories
					verifyICScoloumninAccesoriesTab();
					clickOnTabOfMaterailIDProductSearchPopUp(data.get("Tab2"));//Tech Specs
					clickEmailTechSpecsButton();
					sendEamilinEmailTechSpecsPopUp(data.get("Email"));//qatester01@insight.com
					clickSendEmailButton();
					
					emailSentSuccessfull();
					
					clickOnProductSearchButton();
					clickonHomeButtonInProductSearchPopup();
					enterSearchValue(data.get("KeyWord2"));
					clickOnSearchButtonInSearchWindow();
					
					verifyResultsofMaterailIdofKeyWordSearch(data.get("KeyWord2"));
					clickCloseButtonProductSearch();
					
					clickClosthedocument(data.get("Doctype"));
					clickYesButtontocloseDocument();
					System.out.println("Testcase completed");
					
				}catch (Exception e) {
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
			//ReportStatus.fnUpdateResultStatus("SER03_SearchProductSplChar", "SER_03", ReportStatus.strMethodName, 1, browser);
			throw new RuntimeException(e);
		}
        finally {
			ReportControl.fnEnableJoin();
			ReportStatus.fnUpdateResultStatus("SER03_SearchProductSplChar", "SER_03", ReportStatus.strMethodName, counter, browser);
			fnCloseTest();
			ReportControl.fnNextTestJoin(nextTestJoin);
		}
	}

}
