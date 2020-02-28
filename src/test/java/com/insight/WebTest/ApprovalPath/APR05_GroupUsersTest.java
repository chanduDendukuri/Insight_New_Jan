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

public class APR05_GroupUsersTest extends ApprovalPathLib {

	CMTLib cmtLib = new CMTLib();
	CommonLib commonLib = new CommonLib();
	CartLib cartLib = new CartLib();
	SearchLib searchLib = new SearchLib();
	ProductDetailLib prodDetailsLib = new ProductDetailLib();
	OrderLib orderLib = new OrderLib();
	ProductDisplayInfoLib pipLib = new ProductDisplayInfoLib();
	ShipBillPayLib sbpLib = new ShipBillPayLib();

	// #############################################################################################################
	// # Name of the Test : APR05_GroupUsers
	// # Migration Author : Cigniti Technologies
	// #
	// # Date of Migration : November 2019
	// # DESCRIPTION : This method is to verify Group Users
	// # Parameters : StartRow ,EndRow , nextTestJoin
	// #
	// ###############################################################################################################
	@Parameters({ "StartRow", "EndRow", "nextTestJoin" })
	@Test
	public void TC_APR05(int StartRow, String EndRow, boolean nextTestJoin) throws Throwable {
		int counter = 0;
		try {
			int intStartRow = StartRow;
			int intEndRow = ReportControl.fnGetEndRowCunt(EndRow, "APR05_GroupUsers", TestData, "Approval_Path");
			for (int intCounter = intStartRow; intCounter <= intEndRow; intCounter++) {
				try {
					counter = intCounter;
					fnOpenTest();
					ReportStatus.fnDefaultReportStatus();
					ReportControl.intRowCount = intCounter;
					Hashtable<String, String> data = TestUtil.getDataByRowNo("APR05_GroupUsers", TestData,
							"Approval_Path", intCounter);
					TestEngineWeb.reporter.initTestCaseDescription("GroupUsers");

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
					// Verify Approval Management Page - is Loaded
					reqProcLib.verifyApprovalManagementPage();
					String ReqGroupName1 = data.get("RequestorGroup1");
					String ReqGroupName2 = data.get("RequestorGroup2");
					// Add Requestors to Requestor Group - TU_IUS Requestor
					// Group
					AddRequestorsRequestorGroup(ReqGroupName1);
					scrollUp();
					clickOnApprovalManagementTabs("Approval Path");
					
					
					/*
					 * AddRequestorsRequestorGroup(ReqGroupName2); scrollUp();
					 * clickOnApprovalManagementTabs("Approval Path");
					 */
					
					
					scrollUp();
					// Click on Reports
					ClickReports();
					// Click on Requestor Requestor Group Users
					ClickRequestorGroupUsersLink();
					clickonRefreshIconRequestorGroupuser();
					DisplayRequestors();
					// Click on the Next Page and verify page count 50
					VerifyPageCountInApprovalmgmt();
					ClickOnExporticon();
					Readdatfromexcel("requestorGroupUsers");
					// Change the paging to 20
					ChangePageCount(data.get("PageCount"));
					VerifyPageCountInApprovalmgmt();
					ClickNextPageAndVerify();
					// Change the paging to 20
					ChangePageCount(data.get("PageCount"));
					// click Requestor group link
					ClickTU_IUSRequestorGroupTieredLink("TU_IUS Requestor Group Tiered");
					
					
					// Verify Create/Edit Requestor Group Page
					Verify_Create_Edit_Requestor_GroupPage();
					scrollUp();
					String Requestor1 = "TU_IUSShared,TU_IUSShared";
					// Click on Manage Requestors
					clickManageRequestors();
					// Click Refresh Icon To display Requestors
					ClickRefreshIcon();
					// Select Requestors
					SelectRequestor(Requestor1,1);
					Add_Requestor_Btn_Click();
					ClickSaveChangesButton();
					UpdatedSuccessMsg();
					//Add_Requestor_Btn_Click();
					scrollUp();
					clickOnApprovalManagementTabs("General Settings");
					// Click on Reports
					ClickReports();
					
					
					/*
					 * // Click on Requestor Requestor Group Users ClickRequestorGroupUsersLink();
					 * //ClickOn Requestor Group link ClickTU_IUSRequestorGroupTieredLink(); //
					 * Verify Create/Edit Requestor Group Page
					 * Verify_Create_Edit_Requestor_GroupPage(); // Click on Manage Requestors
					 * clickManageRequestors(); // Click Refresh Icon To display Requestors
					 * ClickRefreshIcon(); SelectRequestor("TU_IUSShared,TU_IUSShared",1);
					 * ClickSaveChangesButton(); UpdatedSuccessMsg(); //Add_Requestor_Btn_Click();
					 * scrollUp(); clickOnApprovalManagementTabs("General Settings"); // Click on
					 * Reports ClickReports();
					 */
					
					
					// Click on Requestor Requestor Group Users
					ClickRequestorGroupUsersLink();
					
					String Requestor = GetNameOfLastRequestor();
					// Search by Last name
					SearchByLastName(Requestor);
					// Click Search
					Click_Search_Icon();
					scrollUp();
					clickOnApprovalManagementTabs("General Settings");
					// Click on Reports
					ClickReports();
					
					
					// Click on Requestor Requestor Group Users
					ClickRequestorGroupUsersLink();
					// click Requestor group link
					ClickTU_IUSRequestorGroupTieredLink("TU_IUS Requestor Group");
					// Verify Create/Edit Requestor Group Page
					Verify_Create_Edit_Requestor_GroupPage();
					scrollUp();
					// Click on Manage Requestors
					clickManageRequestors();
					// Click Refresh Icon To display Requestors
					ClickRefreshIcon();
					// Select Requestors
					SelectRequestor(Requestor1,1);
					 ClickSaveChangesButton();
					 scrollUp();
					 clickOnApprovalManagementTabs("General Settings");
						// Click on Reports
						ClickReports();
						
						
						
						// Click on Requestor Requestor Group Users
						ClickRequestorGroupUsersLink();
						// Displays Requestors
						//String reqName1 = DisplayRequestors();
						// Search by Last name
						SearchByLastName(Requestor1);
						// Click Search
						Click_Search_Icon();
					//verify user
						VerifyRequestorResults(Requestor1);
						//ClickOnBackToRefreshIcon();
						ClickRequestorGrpLink();
						
						ClickonEditlinkofRequestorGroMgmt("TU_IUS Requestor Group");
						// Verify Create/Edit Requestor Group Page
						Verify_Create_Edit_Requestor_GroupPage();
					// Click on Manage Requestors
					clickManageRequestors();
					SelectRequestorFromRightToLeft(Requestor1,1);
					 ClickSaveChangesButton();
					 UpdatedSuccessMsg();
					 scrollUp();
					ClickOnBackToRefreshIcon();
						// click Requestor group link
					ClickonEditlinkofRequestorGroMgmt("TU_IUS Requestor Group Tiered");
					// Verify Create/Edit Requestor Group Page
					Verify_Create_Edit_Requestor_GroupPage();
					scrollUp();
						// Click on Manage Requestors
						clickManageRequestors();	
						SelectRequestorFromRightToLeft(Requestor1,1);
						 ClickSaveChangesButton();
						 UpdatedSuccessMsg();
					
					
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
			ReportStatus.fnUpdateResultStatus("APR05_GroupUsers", "TC_APR05", ReportStatus.strMethodName, 1, browser);
			throw new RuntimeException(e);
		}
        finally {
        	ReportControl.fnEnableJoin();
			ReportStatus.fnUpdateResultStatus("APR05_GroupUsers", "TC_APR05", ReportStatus.strMethodName, counter, browser);
			fnCloseTest();
			ReportControl.fnNextTestJoin(nextTestJoin);
		}
	}

}
