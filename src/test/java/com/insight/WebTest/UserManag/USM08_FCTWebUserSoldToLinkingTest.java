package com.insight.WebTest.UserManag;

import java.util.Hashtable;
import java.util.List;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import com.insight.Lib.CMTLib;
import com.insight.Lib.CanadaLib;
import com.insight.Lib.CartLib;
import com.insight.Lib.CommonLib;
import com.insight.Lib.OrderLib;
import com.insight.Lib.ProductDetailLib;
import com.insight.Lib.ProductDisplayInfoLib;
import com.insight.Lib.SearchLib;
import com.insight.Lib.ShipBillPayLib;
import com.insight.Lib.UserManagementLib;
import com.insight.accelerators.ReportControl;
import com.insight.accelerators.TestEngineWeb;
import com.insight.googledrive.ReportStatus;
import com.insight.utilities.TestUtil;

public class USM08_FCTWebUserSoldToLinkingTest extends UserManagementLib{
	
	CMTLib cmtLib = new CMTLib();
	CommonLib commonLib = new CommonLib();
	CartLib cartLib = new CartLib();
	SearchLib searchLib = new SearchLib();
	ProductDetailLib prodDetailsLib=new ProductDetailLib();
	OrderLib orderLib=new OrderLib();
	ProductDisplayInfoLib pipLib=new ProductDisplayInfoLib();
	ShipBillPayLib sbpLib=new ShipBillPayLib();
	CanadaLib canadaLib = new CanadaLib();
	UserManagementLib userMgt=new UserManagementLib();
	
	    // #############################################################################################################
		// #       Name of the Test         : USM08_FCTWebUserSoldToLinking
		// #       Migration Author         :  Cigniti Technologies
		// #
		// #       Date of Migration        : October 2019
		// #       DESCRIPTION              : This Test is used to Test FCT Web User Sold To Linking
		// #       Parameters               : StartRow ,EndRow , nextTestJoin
		// #
		// ###############################################################################################################
		@Parameters({ "StartRow", "EndRow", "nextTestJoin" })
		@Test
		public void TC_USM08(int StartRow,String EndRow, boolean nextTestJoin) throws Throwable {
			int counter = 0;
				try {
					int intStartRow = StartRow;
					int intEndRow = ReportControl.fnGetEndRowCunt(EndRow, "USM08_FCTWebUserSoldToLinking", TestDataInsight, "UserManagement");
					for (int intCounter = intStartRow; intCounter <= intEndRow; intCounter++) {
						try {
							
							counter = intCounter;
							fnOpenTest();
							ReportStatus.fnDefaultReportStatus();
							ReportControl.intRowCount = intCounter;
							Hashtable<String, String> data = TestUtil.getDataByRowNo("USM08_FCTWebUserSoldToLinking", TestDataInsight, "UserManagement", intCounter);
							TestEngineWeb.reporter.initTestCaseDescription("FCTWebUserSoldToLinking");

							// Login to CMT
							cmtLib.loginToCMT(data.get("Header"));
							cmtLib.searchForWebGroup(data.get("WebGrp"));
							cmtLib.clickOnTheWebGroup(data.get("WebGrp_Name"));
							cmtLib.hoverOnManageWebGroupsAndSelectOptions(data.get("Manage_Web_Grp_Options"));
							cmtLib.searchForaUserAndSelect(data.get("LnameEmailUname"), data.get("ContactName"));

							// Click on Linked Accounts
							cmtLib.clickCheckOutSettings(data.get("Linked_Accounts"));
							cmtLib.VerifytheLinkedAccountsText();
							
							// Select >>  Link User to All Available Accounts
							cmtLib.selectFromLinkedAccountDD(data.get("Option1"));
							cmtLib.VerifytheCheckBoxStatus("Checked");
							
							// Select >>   Unlink User from All Accounts
							cmtLib.selectFromLinkedAccountDD(data.get("Option2"));
							cmtLib.VerifytheCheckBoxStatus("UnChecked");
							
							// Select 100 per Page AND VERIFY
							userMgt.verifyPagination(data.get("Ending_Page"));
							
							// Click Next
							cmtLib.clickOnNextPagination();
							cmtLib.verifyPageStartsWithatLinkedAccounts("1101");
							// Click on 2nd sold to
							cmtLib.clickLinkedAccountCheckBox(data.get("Account_Number1"));//3
							// search by ABE
							cmtLib.enterLinkedAccountSearch(data.get("Account_Search1"));
							
							List<String> accountName1=cmtLib.getAccountNameInLinkedAccounts();
							cmtLib.verifyAccountNameStartsWith(accountName1, data.get("Account_Search1"));
							
							// Select >>  Link User to All Available Accounts
							cmtLib.selectFromLinkedAccountDD(data.get("Option1"));
							cmtLib.VerifytheCheckBoxStatus("Checked");
							// select last default login >> 10
							cmtLib.clickOnDefaultAccountLoginByIndex(data.get("Account_Number2"));
							cmtLib.clickUpdateButtonOnLinkedAccountsScreen();
							//Remove Default 
							cmtLib.removeDefaultLink();
							cmtLib.verifyNoDefaultAccountISPresent();
							// search by AB
							cmtLib.enterLinkedAccountSearch(data.get("Account_Search2"));
							List<String> accountName2=cmtLib.getAccountNameInLinkedAccounts();
							cmtLib.verifyAccountNameStartsWith(accountName2, data.get("Account_Search2"));
							// Clear the Results
							cmtLib.clearSearch();
							// select page - 3
							userMgt.selectPagination(data.get("Paging_Num2"));
							cmtLib.verifyPageStartsWithatLinkedAccounts("101");
							//2.//Verify page is changed
							// Select >>  Link User to All Available Accounts
							cmtLib.selectFromLinkedAccountDD(data.get("Option1"));
							// Select >>  Unlink User from All Accounts
							cmtLib.selectFromLinkedAccountDD(data.get("Option2"));
							cmtLib.VerifytheCheckBoxStatus("UnChecked");
							// Click on 2nd sold to
							cmtLib.clickLinkedAccountCheckBox("4");
							cmtLib.VerifyLinkedAccountCheckBoxisClicked("4");
							cmtLib.clickLinkedAccountUnCheckBox("4");
							cmtLib.VerifyLinkedAccountCheckBoxisClicked("4");
							// select page - 2
							userMgt.selectPaginationPrevious("2");//2
							cmtLib.verifyPageStartsWithatLinkedAccounts("51");
							cmtLib.VerifytheCheckBoxStatus("UnChecked");
							// Select >>  Link User to All Available Accounts
							cmtLib.selectFromLinkedAccountDD(data.get("Option1"));
							// search by ABE
							cmtLib.enterLinkedAccountSearch(data.get("Account_Search1"));
							List<String> accountName4=cmtLib.getAccountNameInLinkedAccounts();
							cmtLib.verifyAccountNameStartsWith(accountName4, data.get("Account_Search1"));
							cmtLib.clickOnDefaultAccountLoginByIndex(data.get("Account_Number1"));
							cmtLib.clickUpdateButtonOnLinkedAccountsScreen();
							// Verify default account
							cmtLib.verifyDefaultAccountAddress();
							cmtLib.logoutSite();
							
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
					ReportStatus.fnUpdateResultStatus("FCTWebUserSoldToLinking", "TC_USM08", ReportStatus.strMethodName, 1, browser);
					throw new RuntimeException(e);
				}
	            finally {
	            	ReportControl.fnEnableJoin();
					ReportStatus.fnUpdateResultStatus("FCTWebUserSoldToLinking", "TC_USM08", ReportStatus.strMethodName, counter, browser);
					fnCloseTest();
					ReportControl.fnNextTestJoin(nextTestJoin);
				}
			}

}
