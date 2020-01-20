package com.insight.SmartTest.Scripts.IPS;

import java.util.ArrayList;
import java.util.Hashtable;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.insight.SmartTest.Lib.HomeLib;
import com.insight.SmartTest.Lib.loginLib;
import com.insight.accelerators.ReportControl;
import com.insight.accelerators.TestEngineWeb;
import com.insight.googledrive.ReportStatus;
import com.insight.utilities.TestUtil;

public class VQT02_IPSFreeGroundFreightTest extends HomeLib{

	loginLib loginlib=new loginLib();

	// #############################################################################################################
		// # Name of the Test : VQT02_IPSFreeGroundFreight
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
		public void TC_VQT02(int StartRow, String EndRow, boolean nextTestJoin) throws Throwable {
			int counter = 0;
			try {
				int intStartRow = StartRow;
				int intEndRow = ReportControl.fnGetEndRowCunt(EndRow, "VQT02_IPSFreeGroundFreight", TestData_Smart, "View_Quote");
				for (int intCounter = intStartRow; intCounter <= intEndRow; intCounter++) {
					try {
						counter = intCounter;
						fnOpenTest();
						ReportStatus.fnDefaultReportStatus();
						ReportControl.intRowCount = intCounter;
						Hashtable<String, String> data = TestUtil.getDataByRowNo("VQT02_IPSFreeGroundFreight", TestData_Smart,
								"View_Quote", intCounter);
						TestEngineWeb.reporter.initTestCaseDescription("IPSFreeGroundFreight");
						navigateToApplication("SMART");
					loginlib.loginIntoSmartApplication(data.get("UserName"),data.get("Password"));
					searchSalesDocNum(data.get("SalesDocNum"));//0220701229
					verifyResultsofmaterail();
					VerifyXconsymbolispresentforallthematerials(data.get("ItemNum1"));
					VerifyXconsymbolispresentforallthematerials(data.get("ItemNum2"));
					VerifyXconsymbolispresentforallthematerials(data.get("ItemNum3"));
					VerifyXconsymbolispresentforallthematerials(data.get("ItemNum4"));
					clickonConXSystem(data.get("ItemNum"));//000010
					ArrayList<String> al1 = verifytesults();
					clickonNextLineItemArrowsymbolinPopUp();
					ArrayList<String> al2 = verifytesults();
					clickonNextLineItemArrowsymbolinPopUp();
					ArrayList<String> al3 = verifytesults();
					clickonNextLineItemArrowsymbolinPopUp();
					ArrayList<String> al4 = verifytesults();
					if(al1.equals(al2) && al2.equals(al3) && al3.equals(al4) && al4.equals(al1)) {
						reporter.SuccessReport("Item details:", "Diversity Partner,Reporting Fields and contract ID Exist", "");
					}
					else {
						reporter.failureReport("Item details:", "Diversity Partner,Reporting Fields and contract ID Exist", "",driver);
					}
					
					
					clickDoneButton();
					clickAdvancedHeader();
					clickAdvancedHeaderTab(data.get("Tab1"));
					verifySelectedoptioninShippingconditions(data.get("Option"));
					clickOnSummaryTabs(data.get("Tab2"));
					VerifySummaryDatainTable(data.get("type"),data.get("Data"));//Freight..0.00
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
			//ReportStatus.fnUpdateResultStatus("VQT02_IPSFreeGroundFreight", "VQT_02", ReportStatus.strMethodName, 1, browser);
			throw new RuntimeException(e);
		}
        finally {
			ReportControl.fnEnableJoin();
			ReportStatus.fnUpdateResultStatus("VQT02_IPSFreeGroundFreight", "VQT_02", ReportStatus.strMethodName, counter, browser);
			fnCloseTest();
			ReportControl.fnNextTestJoin(nextTestJoin);
		}
	}

}

