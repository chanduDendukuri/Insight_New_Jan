package com.insight.WebTest.ApprovalPath;

import java.util.Hashtable;
import java.util.List;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.insight.Lib.ApprovalPathLib;
import com.insight.Lib.CMTLib;
import com.insight.Lib.CartLib;
import com.insight.Lib.CommonLib;
import com.insight.Lib.InvoiceHistoryLib;
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

public class APP01_CreateApprovalPathTest extends ApprovalPathLib {

	CMTLib cmtLib = new CMTLib();
	CommonLib commonLib = new CommonLib();
	CartLib cartLib = new CartLib();
	SearchLib searchLib = new SearchLib();
	ProductDetailLib prodDetailsLib = new ProductDetailLib();
	OrderLib orderLib = new OrderLib();
	ProductDisplayInfoLib pipLib = new ProductDisplayInfoLib();
	ShipBillPayLib sbpLib = new ShipBillPayLib();
	InvoiceHistoryLib ivhLib=new InvoiceHistoryLib();

	// #############################################################################################################
	// # Name of the Test : APP01_CreateApprovalPath
	// # Migration Author : Cigniti Technologies
	// #
	// # Date of Migration : October 2019
	// # DESCRIPTION : This method is to delete the created approval path
	// # Parameters : StartRow ,EndRow , nextTestJoin
	// #
	// ###############################################################################################################
	@Parameters({ "StartRow", "EndRow", "nextTestJoin" })
	@Test
	public void TC_APP01(int StartRow, String EndRow, boolean nextTestJoin) throws Throwable {
		int counter = 0;
		try {			
			int intStartRow = StartRow;
			int intEndRow = ReportControl.fnGetEndRowCunt(EndRow, "APP01_CreateApprovalPath", TestData,
					"Approval_Path");
			for (int intCounter = intStartRow; intCounter <= intEndRow; intCounter++) {
				try {
					counter = intCounter;
					fnOpenTest();
					ReportStatus.fnDefaultReportStatus();
					ReportControl.intRowCount = intCounter;
					Hashtable<String, String> data = TestUtil.getDataByRowNo("APP01_CreateApprovalPath", TestData, "Approval_Path", intCounter);
					TestEngineWeb.reporter.initTestCaseDescription("CreateApprovalPath");
										
					CommonLib commonLib = new CommonLib();
					CMTLib cmtLib = new CMTLib();
					RequisitionProcessingLib reqProcLib = new RequisitionProcessingLib();
					Thread.sleep(2000);
					cmtLib.loginToCMTSearchWebGrpAndUser(data.get("Header"), data.get("WebGrp"),
							data.get("LnameEmailUname"), data.get("ContactName"));
					cmtLib.setPermissions(data.get("Menu_Name"), data.get("Set_Permission"));
					cmtLib.setPermissionsToDisable(data.get("Menu_Name"), data.get("Disable_Permission"));
					cmtLib.clickOnloginAs();
					Thread.sleep(2000);
					switchToChildWindow();
					commonLib.spinnerImage();
					cmtLib.loginVerification("TU_IUSAdmin TU_IUSAdmin");
					//ivhLib.closeAccountTools();
					commonLib.clickOnAccountToolsAndClickOnProductGrp(data.get("Tools_Menu"),
							data.get("Tools_Menu_DD"));

					// Verify Verify Approval Management Page - is Loaded
					reqProcLib.verifyApprovalManagementPage();

					// Get the existing Approval path name
					String FirstApproverPath = GetFirstApprovalPath();

					// Create Create New Approval Path
					CreateNewApprovalPathLink();
					// Click approval path button
					ClickCreateApprovalPathButton();
					acceptAlert();
					// Get the Last name of Existing User to search in latter
					// steps
					EnterNewApprovalPath(FirstApproverPath);
					// select approval path
					//String approverName = CreateNewApprovalClick();
					SelectApprover(null,2);
					// Click approval path button
					ClickCreateApprovalPathButton();
					
					String alerttext  = getmessageofAlertandaccept();
					if(alerttext.equals("Approval path with this name already exist")) {
						reporter.SuccessReport("POPUP", "POPUP Exists", alerttext);
					}
					else {
						reporter.failureReport("POPUP", "POPUP not Exists", "");
					}
					String newApproverPathName = RandomApprovalPathName("QTP Testing");
					EnterNewApprovalPath(newApproverPathName);
					// select approval path
					SelectApprover(null,2);
					//SelectApprover(null,1);
					//CreateNewApprovalClick();
					//CreateNewApprovalClick();
					//CreateNewApprovalClick();
					// Click approval path button1
					ClickCreateApprovalPathButton();
					// Get the created approver from search list
					VerifyAppovalPathCreated(newApproverPathName);

					// Get the Existing User Last name to Search
					ApproverSearchTextBox("Automation");
					// Search button click
					SearchClick();
					VerifyAppovalPathCreated(newApproverPathName);
					// Click on Edit link for the created Approver
					ClickEditLinkButton(newApproverPathName);
					// select approval path
					/*
					 * CreateNewApprovalClick(); CreateNewApprovalClick(); CreateNewApprovalClick();
					 */
					
					
					// Select Approvers and click Add
					List<String> appName = SelectApprover(null,3);
					//SelectApproverAPP01(null,3);
					
					

					// Verify Approvers are Added to Approver path Sequence
					//VerifyApproversAdded(appName);
					
					
					// Get the Existing User Last name to Search
					ApproverSearchTextBox1("Automation");
				     NumberofApproversAddedtoRightSide();
					ClickCancelButton();
					//ClickUpdateButton();

					// Delete Created Approval path
					DeleteApprovers(newApproverPathName);

					// Confirm Delete
					ConfirmDelete(newApproverPathName);
					
					// Get the Existing User Last name to Search
					ApproverSearchTextBox(FirstApproverPath);
					// Search button click
					SearchClick();
					
					// Verify Approval path is Retuned
					VerifyNumberOfApproversInApprovalManagement(FirstApproverPath, 0);

					commonLib.clickLogOutLink(data.get("Logout"));

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
			//gErrorMessage = e.toString();
			gTestStatus = false;
			ReportStatus.fnUpdateResultStatus("APP01_CreateApprovalPath", "TC_APP01", ReportStatus.strMethodName, 1, browser);
			throw new RuntimeException(e);
		}
        finally {
        	ReportControl.fnEnableJoin();
			ReportStatus.fnUpdateResultStatus("APP01_CreateApprovalPath", "TC_APP01", ReportStatus.strMethodName, counter, browser);
			fnCloseTest();
			ReportControl.fnNextTestJoin(nextTestJoin);
		}
	}

}
