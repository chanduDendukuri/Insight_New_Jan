package com.insight.WebTest.EUF;

import java.util.Hashtable;

import com.insight.Lib.*;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.insight.accelerators.ReportControl;
import com.insight.accelerators.TestEngineWeb;
import com.insight.googledrive.ReportStatus;
import com.insight.utilities.TestUtil;

public class EUF01_FCTWebEndUserManageSoldtosTest extends EndUserFeaturesLib{

	
	CMTLib cmtLib = new CMTLib();
	CommonLib commonLib = new CommonLib();
	CartLib cartLib = new CartLib();
	SearchLib searchLib = new SearchLib();
	ProductDetailLib prodDetailsLib=new ProductDetailLib();
	OrderLib orderLib=new OrderLib();
	ProductDisplayInfoLib pipLib=new ProductDisplayInfoLib();
	ShipBillPayLib sbpLib=new ShipBillPayLib();
	CanadaLib canadaLib = new CanadaLib();
	InvoiceHistoryLib ivhLib=new InvoiceHistoryLib();
	CommonCanadaLib ccp = new CommonCanadaLib();
	   
	    // #############################################################################################################
		// #       Name of the Test         :  EUF01_FCTWebEndUserManageSoldtos
		// #       Migration Author         :  Cigniti Technologies
		// #
		// #       Date of Migration        : October 2019
		// #       DESCRIPTION              : This Test is used to Test FCTWebEndUserManageSoldtos
		// #       Parameters               : StartRow ,EndRow , nextTestJoin
		// #
		// ###############################################################################################################
		@Parameters({ "StartRow", "EndRow", "nextTestJoin" })
		@Test
		public void TC_EUF01(int StartRow,String EndRow, boolean nextTestJoin) throws Throwable {
			int counter = 0;
			try {
				int intStartRow = StartRow;
				int intEndRow = ReportControl.fnGetEndRowCunt(EndRow, "EUF01_FCTWebEndUserManageSoldtosTest", TestDataInsight, "End_User");
				for (int intCounter = intStartRow; intCounter <= intEndRow; intCounter++) {
					try {
						fnOpenTest();
						ReportStatus.fnDefaultReportStatus();
						ReportControl.intRowCount = intCounter;
						Hashtable<String, String> data = TestUtil.getDataByRowNo("EUF01_FCTWebEndUserManageSoldtosTest", TestDataInsight, "End_User", intCounter);
						TestEngineWeb.reporter.initTestCaseDescription("FCTWebEndUserManageSoldtos");
                        
						cmtLib.loginToCMTSearchWebGrpAndUser(data.get("Header"), data.get("WebGrp"),
								data.get("LnameEmailUname"), data.get("ContactName"));
						// Click on Linked Accounts
						cmtLib.clickCheckOutSettings(data.get("Linked_Accounts"));
						verifyNumberOfCheckboxesSelected();
						cmtLib.clickDefaultRadioButtonInLinkedAccounts();
						cmtLib.clickUpdateButtonOnLinkedAccountsScreen();
						cmtLib.clickOnloginAs();
						switchToChildWindow();
						//ivhLib.closeAccountTools();
						/*canadaLib.clickOnSideMenuSelectAccountToolOptions(data.get("Tools_Menu"),
								data.get("Tools_Menu_DD"));*/
						commonLib.clickOnAccountToolsAndClickOnProductGrp(data.get("Tools_Menu"),
								data.get("Tools_Menu_DD"));
						clickOnTabInUserProfile(data.get("Tab_Name"));
						verifyLoggedInAs(data.get("LnameEmailUname"));
						verifyWebGroup(data.get("Web_Group_Name"));
						verifyCurrentAccount();
						String value =ccp.getDefaultAccountNumber();
//getCurrentAccount
						verifyRemoveDefualtLink();
						verifyResultsDisplayedPerPage(data.get("Results_Per_Page"));
						verifyResultsPerPagrDDOptions(data.get("Results_Per_Page_Options"));
						selectResultsPerPageDD(data.get("Results_Per_Page_20"));
						selectResultsPerPageDD(data.get("Results_Per_Page_50"));
						selectResultsPerPageDD(data.get("Results_Per_Page_100"));
						clickOnPageNumber(data.get("Page_Number_3"));
						clickOnPageNumber(data.get("Page_Number_5"));
/*West*/				searchForAvailableAccount(data.get("Account_Name"));
						getResultsFromCurrentAccountPage();
						String val=ccp.getResultsValueFromCurrentAccountPage();
						clickSwitchAccountLink();
						clickContinueButton();
						//assertTrue(ccp.isVisibilityOfWelcomeMessage(),"Welcome message is available");
					//	cmtLib.navigateBackToCMT();
						//ivhLib.closeAccountTools();
						ccp.verifyLoggedInAs(data.get("ContactName"));
						commonLib.clickOnAccountToolsAndClickOnProductGrp(data.get("Tools_Menu"),
								data.get("Tools_Menu_DD"));
						clickOnTabInUserProfile(data.get("Tab_Name"));

						assertTrue(ccp.getAccountNumber().contains(val),"Switched to Same account");
						commonLib.clickLogOutLink(data.get("Logout_Header"));
/*Second Login AS*/		cmtLib.navigateBackToCMT();
						cmtLib.clickOnloginAs();
						switchToChildWindow();
						//ivhLib.closeAccountTools();
//BOS
						commonLib.clickOnAccountToolsAndClickOnProductGrp(data.get("Tools_Menu"),
								data.get("Tools_Menu_DD"));
						//scrollToBottomWithCordinate("-500");
						clickOnTabInUserProfile(data.get("Tab_Name"));
						searchForAvailableAccount(data.get("Account_Name1"));
						getResultsFromCurrentAccountPage();

//MAR
						searchForAvailableAccount(data.get("Account_Name2"));
						getResultsFromCurrentAccountPage();
//Empty

						searchForAvailableAccount(data.get("Account_Name5"));
						//searchForAvailableAccount(" ");
						getResultsFromCurrentAccountPage();
						scrollToBottomWithCordinate("-650");

//XYZ
						searchForAvailableAccount(data.get("Account_Name3"));
						getResultsFromCurrentAccountPage();
//Hi
						scrollToBottomWithCordinate("-450");
						searchForAvailableAccount(data.get("Account_Name4"));
						getResultsFromCurrentAccountPage();
						Thread.sleep(3000);
						scrollToBottomWithCordinate("-2000");
						clickonRemoveDefualtLink();
						Thread.sleep(3000);
						ccp.getRemoveSuccessMessage();

						commonLib.clickLogOutLink(data.get("Logout_Header")); //fnCloseTest();

						cmtLib.navigateBackToCMT();
						cmtLib.clickOnloginAs();
						switchToChildWindow();
						//ivhLib.closeAccountTools();

						commonLib.clickOnAccountToolsAndClickOnProductGrp(data.get("Tools_Menu"),
								data.get("Tools_Menu_DD"));
						scrollToBottomWithCordinate("-500");
						clickOnTabInUserProfile(data.get("Tab_Name"));

						ccp.clickOnSwitchToAccountForSelectedAccountRefNum(value);
						clickContinueButton();
						Thread.sleep(3000);
						commonLib.clickLogOutLink(data.get("Logout_Header"));
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
				ReportStatus.fnUpdateResultStatus("FCTWebEndUserManageSoldtos", "TC_EUF01", ReportStatus.strMethodName, 1, browser);
				throw new RuntimeException(e);
			}

			finally {
	        	ReportControl.fnEnableJoin();
	        	ReportStatus.fnUpdateResultStatus("FCTWebEndUserManageSoldtos", "TC_EUF01", ReportStatus.strMethodName, counter, browser);
				fnCloseTest();
				ReportControl.fnNextTestJoin(nextTestJoin);
			}
		}

}
