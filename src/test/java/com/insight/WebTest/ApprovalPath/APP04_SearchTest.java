package com.insight.WebTest.ApprovalPath;

import java.util.Hashtable;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.insight.Lib.ApprovalPathLib;
import com.insight.Lib.CMTLib;
import com.insight.Lib.CartLib;
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

public class APP04_SearchTest extends ApprovalPathLib {

	CMTLib cmtLib = new CMTLib();
	CommonLib commonLib = new CommonLib();
	CartLib cartLib = new CartLib();
	SearchLib searchLib = new SearchLib();
	ProductDetailLib prodDetailsLib = new ProductDetailLib();
	OrderLib orderLib = new OrderLib();
	ProductDisplayInfoLib pipLib = new ProductDisplayInfoLib();
	ShipBillPayLib sbpLib = new ShipBillPayLib();

	// #############################################################################################################
	// # Name of the Test : APP04_Search
	// # Migration Author : Cigniti Technologies
	// #
	// # Date of Migration : October 2019
	// # DESCRIPTION : This method is to delete the created approval path
	// # Parameters : StartRow ,EndRow , nextTestJoin
	// #
	// ###############################################################################################################
	@Parameters({ "StartRow", "EndRow", "nextTestJoin" })
	@Test
	public void TC_APP04(int StartRow, String EndRow, boolean nextTestJoin) throws Throwable {
		int counter = 0;
		try {
			int intStartRow = StartRow;
			int intEndRow = ReportControl.fnGetEndRowCunt(EndRow, "APP04_Search", TestData, "Approval_Path");
			for (int intCounter = intStartRow; intCounter <= intEndRow; intCounter++) {
				try {
					counter = intCounter;
					fnOpenTest();
					ReportStatus.fnDefaultReportStatus();
					ReportControl.intRowCount = intCounter;
					Hashtable<String, String> data = TestUtil.getDataByRowNo("APP04_Search", TestData, "Approval_Path", intCounter);
					TestEngineWeb.reporter.initTestCaseDescription("SearchTest");
								

					CommonLib commonLib = new CommonLib();
					CMTLib cmtLib = new CMTLib();
					RequisitionProcessingLib reqProcLib = new RequisitionProcessingLib();
					cmtLib.loginToCMTSearchWebGrpAndUser(data.get("Header"), data.get("WebGrp"),
							data.get("LnameEmailUname"), data.get("ContactName"));
					cmtLib.setPermissions(data.get("Menu_Name"), data.get("Set_Permission"));
					cmtLib.setPermissionsToDisable(data.get("Menu_Name"), data.get("Disable_Permission"));
					cmtLib.clickOnloginAs();
					switchToChildWindow();
					commonLib.spinnerImage();
					commonLib.clickOnAccountToolsAndClickOnProductGrp(data.get("Tools_Menu"),
							data.get("Tools_Menu_DD"));

					// Verify Verify Approval Management Page - is Loaded
					reqProcLib.verifyApprovalManagementPage();

					// System Displays All Approval paths and Details
					int count =VerifyApprovalPathAndApprovers();

					// Create Create New Approval Path - QTPTesting1
					CreateNewApprovalPathLink();

					String newApproverPathName1 = RandomApprovalPathName("QTP Testing");
					EnterNewApprovalPath(newApproverPathName1);
					// Select Approvers
					SelectApprover(null,2);

					// --- '>' icon
					//Add_Approver_Btn_Click();

					// Create Approval path
					ClickCreateApprovalPathButton();
					VerifyAppovalPathCreated(newApproverPathName1);
					// Create Create New Approval Path - QTPTesting1
					CreateNewApprovalPathLink();

					String newApproverPathName2 = RandomApprovalPathName("QTP Testing");
					EnterNewApprovalPath(newApproverPathName2);

					// Select Approvers
					SelectApprover(null,2);

					// --- '>' icon
					//Add_Approver_Btn_Click();
					ClickCreateApprovalPathButton();
					VerifyAppovalPathCreated(newApproverPathName2);
					

					// Enter Partial name and Search
					String[] strApprovalPathName = (newApproverPathName1).split("Testing");

					// Search with QTP - and click on search button
					//SearchUser(strApprovalPathName[0]);

					

					// Edit first approval path
					ClickEditLinkButton(newApproverPathName1);
					int a1 = NumberofApproversAddedtoRightSide();
					// Select Approvers and click Add
					SelectApprover(null,3);
					int a2 = NumberofApproversAddedtoRightSide();
					if(a2==a1+3) {
						reporter.SuccessReport("Approvers added", "Approvers added successfully", "", driver);
					}
					else {
						reporter.failureReport("Approvers added", "Approvers are not added successfully", "", driver);	
					}
					// Add button to add approver
					//Add_Approver_Btn_Click();

					ClickUpdateButton();

					// Search with QTP - and click on search button
					SearchUser(strApprovalPathName[0]);

					// Edit second approval path
					ClickEditLinkButton(newApproverPathName2);

					// Select Approvers and click Add
					SelectApprover(null,1);

					// Add button to add approver
					//Add_Approver_Btn_Click();

					ClickUpdateButton();

					VerifyAppovalPathCreated(newApproverPathName1);
					VerifyAppovalPathCreated(newApproverPathName2);
					
					
					// Edit first approval path
					ClickEditLinkButton(newApproverPathName1);
					int a3 = NumberofApproversAddedtoRightSide();
					// Select Approvers and click Add
					SelectApprover(null,2);
					int a4 = NumberofApproversAddedtoRightSide();
					if(a4==a3+2) {
						reporter.SuccessReport("Approvers added", "Approvers added successfully", "", driver);
					}
					else {
						reporter.failureReport("Approvers added", "Approvers are not added successfully", "", driver);	
					}
					// Add button to add approver
					//Add_Approver_Btn_Click();

					ClickUpdateButton();

					// Search with QTP - and click on search button
					SearchUser(strApprovalPathName[0]);

					// Edit second approval path
					ClickEditLinkButton(newApproverPathName2);

					// Select Approvers and click Add
					SelectApprover(null,3);

					// Add button to add approver
					//Add_Approver_Btn_Click();

					ClickUpdateButton();
					VerifyNumberOfApproversInApprovalManagement(newApproverPathName1,7);
					ClickOnViewAllOrRefreshIcon();
					VerifyApprovalPathAndApprovers();
					
					// Delete Created Approval path - QTPTesting with 1st random
					// number
					DeleteApprovers(newApproverPathName1);

					Thread.sleep(1000);

					// Confirm Delete
					ConfirmDelete(newApproverPathName1);

					Thread.sleep(2000);

					// Delete Created Approval path - QTPTesting with 2nd random
					// number
					DeleteApprovers(newApproverPathName2);

					Thread.sleep(1000);

					// Confirm Delete
					ConfirmDelete(newApproverPathName2);

					commonLib.clickLogOutLink(data.get("Logout"));

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
			ReportStatus.fnUpdateResultStatus("APP04_SearchTest", "TC_APP04", ReportStatus.strMethodName, 1, browser);
			throw new RuntimeException(e);
		}
        finally {
        	ReportControl.fnEnableJoin();
			ReportStatus.fnUpdateResultStatus("APP04_SearchTest", "TC_APP04", ReportStatus.strMethodName, counter, browser);
			fnCloseTest();
			ReportControl.fnNextTestJoin(nextTestJoin);
		}
	}

	}


