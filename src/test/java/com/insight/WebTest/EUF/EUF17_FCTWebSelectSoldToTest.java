package com.insight.WebTest.EUF;

import java.util.Hashtable;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.insight.Lib.CMTLib;
import com.insight.Lib.CanadaLib;
import com.insight.Lib.CartLib;
import com.insight.Lib.CommonLib;
import com.insight.Lib.EndUserFeaturesLib;
import com.insight.Lib.MarriottIntlCorpLib;
import com.insight.Lib.OrderLib;
import com.insight.Lib.ProductDetailLib;
import com.insight.Lib.ProductDisplayInfoLib;
import com.insight.Lib.SearchLib;
import com.insight.Lib.ShipBillPayLib;
import com.insight.accelerators.ReportControl;
import com.insight.accelerators.TestEngineWeb;
import com.insight.googledrive.ReportStatus;
import com.insight.utilities.TestUtil;

public class EUF17_FCTWebSelectSoldToTest extends EndUserFeaturesLib{
	CMTLib cmtLib = new CMTLib();
	CommonLib commonLib = new CommonLib();
	CartLib cartLib = new CartLib();
	SearchLib searchLib = new SearchLib();
	ProductDetailLib prodDetailsLib=new ProductDetailLib();
	OrderLib orderLib=new OrderLib();
	ProductDisplayInfoLib pipLib=new ProductDisplayInfoLib();
	ShipBillPayLib sbpLib=new ShipBillPayLib();
	CanadaLib canadaLib = new CanadaLib();
	MarriottIntlCorpLib marriottintlib=new MarriottIntlCorpLib();
	   
	    // #############################################################################################################
		// #       Name of the Test         :  EUF17_FCTWebSelectSoldToTest
		// #       Migration Author         :  Cigniti Technologies
		// #
		// #       Date of Migration        : October 2019
		// #       DESCRIPTION              : This Test is used to Test FCTWebEndUserManageSoldtos
		// #       Parameters               : StartRow ,EndRow , nextTestJoin
		// #
		// ###############################################################################################################
		@Parameters({ "StartRow", "EndRow", "nextTestJoin" })
		@Test
		public void TC_EUF17(int StartRow,String EndRow, boolean nextTestJoin) throws Throwable {
			int counter = 0;
			try {
				int intStartRow = StartRow;
				int intEndRow = ReportControl.fnGetEndRowCunt(EndRow, "EUF17_FCTWebSelectSoldTo", TestDataInsight, "End_User");
				for (int intCounter = intStartRow; intCounter <= intEndRow; intCounter++) {
					try {
						fnOpenTest();
						ReportStatus.fnDefaultReportStatus();
						ReportControl.intRowCount = intCounter;
						Hashtable<String, String> data = TestUtil.getDataByRowNo("EUF17_FCTWebSelectSoldTo", TestDataInsight, "End_User", intCounter);
						TestEngineWeb.reporter.initTestCaseDescription("FCTWebSelectSoldToTest");
                        
						// Login to CMT
						cmtLib.loginToCMT(data.get("Header"));
						cmtLib.searchForWebGroup(data.get("WebGrp"));
						cmtLib.clickOnTheWebGroup(data.get("WebGrp_Name"));
						cmtLib.hoverOnManageWebGroupsAndSelectOptions(data.get("Manage_Web_Grp_Options"));
						cmtLib.searchForaUserAndSelect(data.get("LnameEmailUname"), data.get("ContactName"));
						// Click on Linked Accounts
					    cmtLib.clickCheckOutSettings(data.get("Linked_Accounts"));
						cmtLib.enterLinkedAccountSearch(data.get("Account_Number"));
						cmtLib.checkLinkedAccountCheckBox(data.get("Account_Number"));
						cmtLib.clickRadioDefaultAtLogin(data.get("Account_Number"));
						cmtLib.clickUpdateButtonOnLinkedAccountsScreen();
						// login As
						cmtLib.loginAsAdminCMT();
						String Accountname1=verifyAccountName();
						toClickOnAcoountDropdown();
						verifyAccountSearchBar();
						verifySoldtosInAccountDropdown(data.get("SoldtoCount1"));
						selectFirstSoldto();
						clickContinueButton();
						marriottintlib.VerifyWelcometoeProcurementpage();
						String Accountname2=verifyAccountName();
						Thread.sleep(5000);
						toClickOnAcoountDropdown();
						verifyAccountNamechanged(Accountname1,Accountname2);
						verifySoldtosInAccountDropdown(data.get("SoldtoCount1"));
						clickonAccountName(Accountname1);
						clickContinueButton();
						String Accountname3=verifyAccountName();
						toClickOnAcoountDropdown();
						verifyAccountNamechanged(Accountname3,Accountname2);
						verifySeeAllAvailableAccounts();
						verifyCurrentaccountPageinAccounttools();
						verifyAccountFavertesTab(data.get("Tabname1"));
						verifyAccountFavorites();
						//need to move favorites and update
						addSearchtoFavorites();
						clickUpdateButton();
						verifyupdateSuccessMessage();
						commonLib.clickLogOutLink("Logout");
						cmtLib.loginToCMT(data.get("Header"));
						cmtLib.searchForWebGroup(data.get("WebGrp"));
						cmtLib.clickOnTheWebGroup(data.get("WebGrp_Name"));
						cmtLib.hoverOnManageWebGroupsAndSelectOptions(data.get("Manage_Web_Grp_Options"));
						cmtLib.searchForaUserAndSelect(data.get("LnameEmailUname"), data.get("ContactName"));
						cmtLib.loginAsAdminCMT();
						Thread.sleep(5000);
						toClickOnAcoountDropdown();
						verifyFavoriteAccountssoldto(data.get("SoldtoCount2"));
						Thread.sleep(5000);
						commonLib.clickOnAccountToolsAndClickOnProductGrp(data.get("Tools_Menu"), data.get("Tools_Menu_DD"));
						verifyAccountFavertesTab(data.get("Tab"));
						verifyRemoveDefualtLink();
						verifyRemoveDefualtLinkandSelect();
						commonLib.clickLogOutLink("Logout");
						Thread.sleep(5000);
						cmtLib.navigateBackToCMT();				
						cmtLib.hoverOverMasterGroupAndSelectChangeGrp();
						cmtLib.searchForWebGroup(data.get("WebGrp"));
						Thread.sleep(5000);
						cmtLib.manageUsers();
						cmtLib.searchForaUserAndSelect(data.get("LnameEmailUname"), data.get("ContactName"));
						Thread.sleep(3000);
						cmtLib.loginAsAdminCMT();
						Thread.sleep(5000);
						clickOnTabInUserProfile(data.get("Tab"));
						verifyCurrentaccountPageinAccounttools();
						verifyDefualtLinkRemovedwarning();
						verifyAccountFavertesTab(data.get("Tabname1"));
						clearMyFavoriteAccountsList();
						clickUpdateButton();
						verifyupdateSuccessMessage();
						Thread.sleep(3000);
						commonLib.clickLogOutLink("Logout");
						Thread.sleep(5000);
						cmtLib.navigateBackToCMT();				
						cmtLib.hoverOverMasterGroupAndSelectChangeGrp();
						cmtLib.searchForWebGroup(data.get("WebGrp1"));
						Thread.sleep(5000);
						cmtLib.manageUsers();
						cmtLib.searchForaUserAndSelect(data.get("LnameEmailUname1"),data.get("ContactName1"));
						cmtLib.clickCheckOutSettings(data.get("Linked_Accounts"));
						verifyUserisnotlinkedtoanyAccount();
						cmtLib.loginAsAdminCMT();
						marriottintlib.VerifyWelcometoeProcurementpage();
						
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
				//gErrorMessage = e.getMessage();
				gTestStatus = false;
				ReportStatus.fnUpdateResultStatus("FCTWebSelectSoldToTest", "TC_EUF17", ReportStatus.strMethodName, 1, browser);
				throw new RuntimeException(e);
			}

			finally {
	        	ReportControl.fnEnableJoin();
	        	ReportStatus.fnUpdateResultStatus("FCTWebSelectSoldToTest", "TC_EUF17", ReportStatus.strMethodName, counter, browser);
				fnCloseTest();
				ReportControl.fnNextTestJoin(nextTestJoin);
			}
		}

}
