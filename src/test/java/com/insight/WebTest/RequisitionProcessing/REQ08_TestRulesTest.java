package com.insight.WebTest.RequisitionProcessing;

import java.util.Hashtable;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.insight.Lib.CMTLib;
import com.insight.Lib.CanadaLib;
import com.insight.Lib.CartLib;
import com.insight.Lib.ChinaLib;
import com.insight.Lib.CommonLib;
import com.insight.Lib.OrderLib;
import com.insight.Lib.ProductDetailLib;
import com.insight.Lib.ProductDisplayInfoLib;
import com.insight.Lib.RequisitionProcessingLib;
import com.insight.Lib.SearchLib;
import com.insight.Lib.ShipBillPayLib;
import com.insight.accelerators.ReportControl;
import com.insight.accelerators.TestEngineWeb;
import com.insight.googledrive.ReportStatus;
import com.insight.utilities.TestUtil;

public class REQ08_TestRulesTest extends ChinaLib{
	CommonLib commonLib = new CommonLib();
	CMTLib cmtLib = new CMTLib();
	RequisitionProcessingLib reqProcLib = new RequisitionProcessingLib();
	OrderLib orderLib = new OrderLib();

	   
	// #############################################################################################################
		// # Name of the Test : REQ08_TestRules
		// # Migration Author : Cigniti Technologies
		// #
		// # Date of Migration : September 2019
		// # Description : To Test Place Order basic
		// # Parameters : StartRow ,EndRow , nextTestJoin
		// #
		// #############################################################################################################
			@Parameters({ "StartRow", "EndRow", "nextTestJoin" })
		@Test
		public void TC_REQ08(int StartRow,String EndRow, boolean nextTestJoin) throws Throwable {
				int counter = 0;
				try {
					int intStartRow = StartRow;
					int intEndRow = ReportControl.fnGetEndRowCunt(EndRow, "REQ08_TestRules",
							TestData, "Requisition_Processing");
					for (int intCounter = intStartRow; intCounter <= intEndRow; intCounter++) {
						try {
							counter = intCounter;
							fnOpenTest();
							ReportStatus.fnDefaultReportStatus();
							ReportControl.intRowCount = intCounter;
							Hashtable<String, String> data = TestUtil.getDataByRowNo("REQ08_TestRules",
									TestData, "Requisition_Processing", intCounter);
							TestEngineWeb.reporter.initTestCaseDescription("TestRules");

						// select language and country
						cmtLib.loginToCMTSearchWebGrpAndUser(data.get("Header"), data.get("WebGrp"), data.get("LnameEmailUname"),
								data.get("ContactName"));
						cmtLib.setPermissions(data.get("Menu_Name"), data.get("Set_Permission"));
						cmtLib.setPermissionsToDisable(data.get("Menu_Name"), data.get("Disable_Permission"));
						cmtLib.clickOnloginAs();
						switchToChildWindow();
						commonLib.spinnerImage();
						commonLib.clickOnAccountToolsAndClickOnProductGrp(data.get("Tools_Menu"), data.get("Tools_Menu_DD"));

						// Verify Verify Approval Management Page - is Loaded
						reqProcLib.verifyApprovalManagementPage();

						// Click on - Requestor Group Users
						orderLib.clickonApprovalManagementTabs(data.get("Approval_Management_Tabs"));

						// Click on TestRules Edit link button
						reqProcLib.clickTestRulesLink();

						commonLib.spinnerImage();

						// clicking on Optional Rules
						reqProcLib.clickOptionalRules();

						// create rules
						reqProcLib.createRule(data.get("Cart_Type_Standard"), data.get("Min_Amt_0"), data.get("Max_Amt_99"),
								data.get("Result_Path1"), "1");
						Thread.sleep(1000);
						reqProcLib.createRule(data.get("Cart_Type_NonStandard"), data.get("Min_Amt_100"), data.get("Max_Amt_199"),
								data.get("Result_Path2"), "2");
						Thread.sleep(1000);
						reqProcLib.createRule(data.get("Cart_Type_Any"), data.get("Min_Amt_200"), data.get("Max_Amt_299"),
								data.get("Result_Path3"), "3");

						// Test Approval Rules
						reqProcLib.clickTestApprovalRulesLink();

						// Verify Rules
						reqProcLib.verifyRules(data.get("Amount_0"), data.get("Cart_Type_Standard"), data.get("Result_Path1"),
								false);
						reqProcLib.verifyRules(data.get("Amount_110"), data.get("Cart_Type_NonStandard"), data.get("Result_Path2"),
								false);
						reqProcLib.verifyRules(data.get("Amount_210"), data.get("Cart_Type_Standard"), data.get("Result_Path3"),
								false);
						reqProcLib.verifyRules(data.get("Amount_210"), data.get("Cart_Type_NonStandard"), data.get("Result_Path3"),
								false);
						reqProcLib.verifyRules(data.get("Amount_110"), data.get("Cart_Type_Standard"), data.get("Result_Path1"),
								false);

						// close test approvals window
						reqProcLib.closeTestApprovalRulesWindow();

						// Delete Created Rules
						reqProcLib.clickDeleteRule("First");
						reqProcLib.clickDeleteRule("Second");
						reqProcLib.clickDeleteRule("Third");

						// back to Requestor Group Search
						reqProcLib.backtoReqSearch();
						Thread.sleep(5000);

						// Click on TestRules Edit link button
						reqProcLib.clickSmartTrackerRulesLink();

						Thread.sleep(2000);

						// clicking on Optional Rules
						reqProcLib.clickOptionalRules();

						// select with rule listing factor dropdown
						reqProcLib.selectWithRuleListingFactor(data.get("CreateRule_WithListOption"));

						// click on ADD ROUTE button
						reqProcLib.clickAdRouteButton();

						// select HDLList after ADD ROUTE click
						reqProcLib.selectHDLListOption(data.get("SmartTracker_HDLLIstOption"));

						// click ADD RULE button
						reqProcLib.addRuleWithList(data.get("List1_Option"), data.get("Cart_Type_Standard"), data.get("Min_Amt_0"),
								data.get("Max_Amt_99"), data.get("Result_Path1"), "1");

						// Test Approval Rules 
						reqProcLib.clickTestApprovalRulesLink();

						// Verify Rules
						reqProcLib.verifyRules(data.get("Amount_0"), data.get("Cart_Type_Standard"), data.get("Result_Path1"),
								false);
						reqProcLib.verifyRules(data.get("Amount_110"), data.get("Cart_Type_NonStandard"),
								data.get("Result_NoApprovalPath"), true);
						reqProcLib.verifyRules(data.get("Amount_210"), data.get("Cart_Type_Standard"), data.get("Result_Path3"),
								false);
						reqProcLib.verifyRules(data.get("Amount_210"), data.get("Cart_Type_NonStandard"), data.get("Result_Path3"),
								false);
						reqProcLib.verifyRules(data.get("Amount_110"), data.get("Cart_Type_Standard"),
								data.get("Result_NoApprovalPath"), true);

						// close test approvals window
						reqProcLib.closeTestApprovalRulesWindow();

						// Delete Created Rules
						reqProcLib.clickDeleteRule("WithRule");

						// Delete Routing List choice
						reqProcLib.deleteRoutingOption();
						commonLib.clickLogOutLink(data.get("Logout"));
						
						// fnCloseTest();
						System.out.println("Test completed");
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
				ReportStatus.fnUpdateResultStatus("REQ08_TestRulesTest", "TC_REQ08", ReportStatus.strMethodName, 1, browser);
				throw new RuntimeException(e);
			}
	        finally {
	        	ReportControl.fnEnableJoin();
				ReportStatus.fnUpdateResultStatus("REQ08_TestRulesTest", "TC_REQ08", ReportStatus.strMethodName, counter, browser);
				fnCloseTest();
				ReportControl.fnNextTestJoin(nextTestJoin);
			}
	
					}
	
}

