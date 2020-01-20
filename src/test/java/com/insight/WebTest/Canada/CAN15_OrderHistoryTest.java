package com.insight.WebTest.Canada;


	import java.util.Hashtable;

	import org.testng.annotations.Parameters;
	import org.testng.annotations.Test;

	import com.insight.Lib.CMTLib;
	import com.insight.Lib.CanadaLib;

	import com.insight.accelerators.ActionEngine;
	import com.insight.accelerators.ReportControl;
	import com.insight.accelerators.TestEngineWeb;
	import com.insight.googledrive.ReportStatus;
	import com.insight.utilities.TestUtil;

	public class CAN15_OrderHistoryTest extends ActionEngine  {

		

		// #############################################################################################################
		// # Name of the Test : CAN13_CartBundles
		// # Migration Author : Cigniti Technologies
		// #
		// # Date of Migration : August 2019
		// # DESCRIPTION : This method is to perform Basic Cart operations.
		// # Parameters : StartRow ,EndRow , nextTestJoin
		// #
		// ###############################################################################################################
		@Parameters({ "StartRow", "EndRow", "nextTestJoin" })
		@Test
		public void Tc_CAN15(int StartRow, String EndRow, boolean nextTestJoin) throws Throwable {
		
				int counter = 0;
				try {
					int intStartRow = StartRow;
					int intEndRow = ReportControl.fnGetEndRowCunt(EndRow, "CAN15_OrderHistory", TestDataInsight, "Canada");
					for (int intCounter = intStartRow; intCounter <= intEndRow; intCounter++) {
						try {
							counter = intCounter;
							fnOpenTest();
							ReportStatus.fnDefaultReportStatus();
							ReportControl.intRowCount = intCounter;
							Hashtable<String, String> data = TestUtil.getDataByRowNo("CAN15_OrderHistory", TestDataInsight,
									"Canada", intCounter);
							TestEngineWeb.reporter.initTestCaseDescription("OrderHistory");
				
				CMTLib cmtLib = new CMTLib();	
				CanadaLib canadaLib=new CanadaLib();
				cmtLib.loginToCMTSearchWebGrpAndUser(data.get("Header"), data.get("WebGrp"), data.get("LnameEmailUname"),
						data.get("ContactName"));
				cmtLib.clickOnRolesAndPermissionsAndSetPermission(data.get("Menu_Name"), data.get("Set_Permission"));
				cmtLib.loginAsAdminCMT();
				canadaLib.clickOnSideMenuSelectAccountToolOptions(data.get("Tools_Menu"),  data.get("Tools_Menu_DD"));	
				canadaLib.advanceSearchInOrderHistory(data.get("SelectOrder"), data.get("OrderNumber"));
				canadaLib.advanceSearchInOrderHistory(data.get("SelectReference"), data.get("ReferenceNumber"));
				canadaLib.advanceSearchInOrderHistory(data.get("SelectPurchaseOrder"), data.get("PurchaseNumber"));
				canadaLib.clickOnOrderNumberToViewDetails(data.get("OrderNumber"));
				canadaLib.verifyDetailsInOrderPage(data.get("OrderNumber"),data.get("PurchaseNumber"),data.get("ReferenceNumber"),data.get("EWR"));
				System.out.println("Test completed");
				
			} catch (Exception e) {
				ReportStatus.blnStatus = false;
			//	gErrorMessage = e.getMessage();
				gTestStatus = false;
			}
		}
	} catch (Exception e) {
		e.printStackTrace();
		ReportStatus.blnStatus = false;
	//	gErrorMessage = e.toString();
		gTestStatus = false;
		ReportStatus.fnUpdateResultStatus("OrderHistory", "TC_CAN15", ReportStatus.strMethodName, 1, browser);
		throw new RuntimeException(e);
	}
    finally {
    	ReportControl.fnEnableJoin();
		ReportStatus.fnUpdateResultStatus("OrderHistory", "TC_CAN15", ReportStatus.strMethodName, counter, browser);
		fnCloseTest();
		ReportControl.fnNextTestJoin(nextTestJoin);
	}
}
}