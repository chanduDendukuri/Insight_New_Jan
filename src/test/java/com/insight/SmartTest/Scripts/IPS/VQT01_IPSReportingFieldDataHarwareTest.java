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

public class VQT01_IPSReportingFieldDataHarwareTest extends HomeLib{

	loginLib loginlib=new loginLib();

	// #############################################################################################################
		// # Name of the Test : VQT01_IPSReportingFieldDataHarware
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
		public void TC_VQT01(int StartRow, String EndRow, boolean nextTestJoin) throws Throwable {
			int counter = 0;
			try {
				int intStartRow = StartRow;
				int intEndRow = ReportControl.fnGetEndRowCunt(EndRow, "VQT01_IPSReportingFieldDataHarware", TestData_Smart, "View_Quote");
				for (int intCounter = intStartRow; intCounter <= intEndRow; intCounter++) {
					try {
						counter = intCounter;
						fnOpenTest();
						ReportStatus.fnDefaultReportStatus();
						ReportControl.intRowCount = intCounter;
						Hashtable<String, String> data = TestUtil.getDataByRowNo("VQT01_IPSReportingFieldDataHarware", TestData_Smart,
								"View_Quote", intCounter);
						TestEngineWeb.reporter.initTestCaseDescription("IPSReportingFieldDataHarware");
						navigateToApplication("SMART");
					loginlib.loginIntoSmartApplication(data.get("UserName"),data.get("Password"));
					searchSalesDocNum(data.get("SalesDocNum"));//0220701229
					verifyResultsofmaterail();
					VerifyXconsymbolispresentforallthematerials(data.get("ItemNum1"));
					VerifyXconsymbolispresentforallthematerials(data.get("ItemNum2"));
					VerifyXconsymbolispresentforallthematerials(data.get("ItemNum3"));
					VerifyXconsymbolispresentforallthematerials(data.get("ItemNum4"));
					
					clickonConXSystem(data.get("ItemNum1"));//000010
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
					//Change Mode
					ClickOnDisplayMode();;
					
					clickonConXSystem(data.get("ItemNum1"));//000010
					
					ArrayList<String> al5 = verifytesults();
					clickonNextLineItemArrowsymbolinPopUp();
					ArrayList<String> al6 = verifytesults();
					clickonNextLineItemArrowsymbolinPopUp();
					ArrayList<String> al7 = verifytesults();
					clickonNextLineItemArrowsymbolinPopUp();
					ArrayList<String> al8 = verifytesults();
					if(al5.equals(al6) && al6.equals(al7) && al7.equals(al8) && al8.equals(al5)) {
						reporter.SuccessReport("Item details in change mode:", "Diversity Partner,Reporting Fields and contract ID Exist", "");
					}
					else {
						reporter.failureReport("Item details in Change mode:", "Diversity Partner,Reporting Fields and contract ID Exist", "",driver);
					}
					
					clickDoneButton();
					
					clickClosthedocument(data.get("SalesDocNum"));
					
					
				}  catch (Exception e) {
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
			//ReportStatus.fnUpdateResultStatus("VQT01_IPSReportingFieldDataHarware", "VQT_01", ReportStatus.strMethodName, 1, browser);
			throw new RuntimeException(e);
		}
        finally {
			ReportControl.fnEnableJoin();
			ReportStatus.fnUpdateResultStatus("VQT01_IPSReportingFieldDataHarware", "VQT_01", ReportStatus.strMethodName, counter, browser);
			fnCloseTest();
			ReportControl.fnNextTestJoin(nextTestJoin);
		}
	}


}
