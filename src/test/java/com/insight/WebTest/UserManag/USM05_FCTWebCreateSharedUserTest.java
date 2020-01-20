package com.insight.WebTest.UserManag;

import java.util.Hashtable;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.insight.Lib.CMTLib;
import com.insight.Lib.CanadaLib;
import com.insight.Lib.CartLib;
import com.insight.Lib.CommonLib;
import com.insight.Lib.MarriottIntlCorpLib;
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

public class USM05_FCTWebCreateSharedUserTest extends UserManagementLib{
	
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
MarriottIntlCorpLib marriottlib=new MarriottIntlCorpLib();
    // #############################################################################################################
	// #       Name of the Test         : USM05_FCTWebCreateSharedUser
	// #       Migration Author         :  Cigniti Technologies
	// #
	// #       Date of Migration        : October 2019
	// #       DESCRIPTION              : This Test is used to Test FCT Web User Sold To Linking
	// #       Parameters               : StartRow ,EndRow , nextTestJoin
	// #
	// ###############################################################################################################
	@Parameters({ "StartRow", "EndRow", "nextTestJoin" })
	@Test
	public void TC_USM05(int StartRow,String EndRow, boolean nextTestJoin) throws Throwable {
		int counter = 0;
			try {
				int intStartRow = StartRow;
				int intEndRow = ReportControl.fnGetEndRowCunt(EndRow, "USM05_FCTWebCreateSharedUserTest", TestDataInsight, "UserManagement");
				for (int intCounter = intStartRow; intCounter <= intEndRow; intCounter++) {
					try {
						counter = intCounter;
						fnOpenTest();
						ReportStatus.fnDefaultReportStatus();
						ReportControl.intRowCount = intCounter;
						Hashtable<String, String> data = TestUtil.getDataByRowNo("USM05_FCTWebCreateSharedUserTest", TestDataInsight, "UserManagement", intCounter);
						TestEngineWeb.reporter.initTestCaseDescription("FCTWebCreateSharedUser");
						
						// Login to CMT
						cmtLib.loginToCMT(data.get("Header"));
						cmtLib.searchForWebGroup(data.get("WebGrp"));
						cmtLib.clickOnTheWebGroup(data.get("WebGrp_Name"));
						cmtLib.hoverOnManageWebGroupsAndSelectOptions(data.get("Manage_Web_Grp_Options"));
						//New User Link
						cmtLib.clickAddNewUserLink();
						//create User
						cmtLib.selectUserTypeDropdown(data.get("User_Type"));
						cmtLib.enterFirstNameInCreateAnAccount(data.get("FirstName"));
						cmtLib.enterLastNameInCreateAnAccount(data.get("LastName"));
						String emailAddress1="Hello World";
						cmtLib.enterEmailAddressInAddAnAccount(emailAddress1);
						cmtLib.selectEmailFormat(data.get("EmailFormat"));
						cmtLib.enterNotes(data.get("Notes"));
						cmtLib.clickCreateUserButton();	
						Thread.sleep(2000);
						verifyErrorMsg();
						String emailAddress="TU_IPSADMIN"+getRandomNumeric(4)+"@MAILINATOR.COM";
						cmtLib.enterEmailAddressInAddAnAccount(emailAddress);
						cmtLib.clickCreateUserButton();	
						Thread.sleep(2000);
						cmtLib.clickBackToUserSearch();
						cmtLib.searchUsers(emailAddress);
						Thread.sleep(2000);
						cmtLib.contactNameSearchResultVerificationofCreatedUser(data.get("ContactName"));
						cmtLib.verifyUserandClick(data.get("ContactName"));
						String[] permissions = data.get("Set_Permission").split(",");
						for (i = 0; i < permissions.length; i++) {
							cmtLib.setPermissions(data.get("Menu_Name"), permissions[i]);
						}
						Thread.sleep(2000);
						cmtLib.clickInformationTab(data.get("Information_Tab"));
						cmtLib.clickOnSharedUserUrl();
						marriottlib.handleinsightpopup();
						verifyWelcomePage();
					} catch (Exception e) {
						ReportStatus.blnStatus = false;
						gErrorMessage = e.getMessage();
						gTestStatus = false;
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
				ReportStatus.blnStatus = false;
				gErrorMessage = e.toString();
				gTestStatus = false;
				ReportStatus.fnUpdateResultStatus("FCTWebCreateSharedUser", "TC_USM05", ReportStatus.strMethodName, 1, browser);
				throw new RuntimeException(e);
			}
            finally {
            	ReportControl.fnEnableJoin();
				ReportStatus.fnUpdateResultStatus("FCTWebCreateSharedUser", "TC_USM05", ReportStatus.strMethodName, counter, browser);
				fnCloseTest();
				ReportControl.fnNextTestJoin(nextTestJoin);
			}
		}

}

