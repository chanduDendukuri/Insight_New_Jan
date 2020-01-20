package com.insight.WebTest.EUF;

import java.util.Hashtable;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.insight.Lib.CMTLib;
import com.insight.Lib.CanadaLib;
import com.insight.Lib.CartLib;
import com.insight.Lib.CommonLib;
import com.insight.Lib.EndUserFeaturesLib;
import com.insight.Lib.OrderLib;
import com.insight.Lib.ProductDetailLib;
import com.insight.Lib.ProductDisplayInfoLib;
import com.insight.Lib.SearchLib;
import com.insight.Lib.ShipBillPayLib;
import com.insight.accelerators.ReportControl;
import com.insight.accelerators.TestEngineWeb;
import com.insight.googledrive.ReportStatus;
import com.insight.utilities.TestUtil;

public class EUF09_FCTWebEndDisplayMyContactNotificationsTest extends EndUserFeaturesLib {
	CMTLib cmtLib = new CMTLib();
	CommonLib commonLib = new CommonLib();
	CartLib cartLib = new CartLib();
	SearchLib searchLib = new SearchLib();
	ProductDetailLib prodDetailsLib=new ProductDetailLib();
	OrderLib orderLib=new OrderLib();
	ProductDisplayInfoLib pipLib=new ProductDisplayInfoLib();
	ShipBillPayLib sbpLib=new ShipBillPayLib();
	CanadaLib canadaLib = new CanadaLib();
	   
	    // #############################################################################################################
		// #       Name of the Test         :  EUF09_FCTWebEndDisplayMyContactNotifications
		// #       Migration Author         :  Cigniti Technologies
		// #
		// #       Date of Migration        : October 2019
		// #       DESCRIPTION              : This Test is used to Test FCTWebEndUserManageSoldtos
		// #       Parameters               : StartRow ,EndRow , nextTestJoin
		// #
		// ###############################################################################################################
		@Parameters({ "StartRow", "EndRow", "nextTestJoin" })
		@Test
		public void TC_EUF09(int StartRow,String EndRow, boolean nextTestJoin) throws Throwable {
			int counter = 0;
			try {
				int intStartRow = StartRow;
				int intEndRow = ReportControl.fnGetEndRowCunt(EndRow, "EUF09_FCTWebEndDisplayMyContactNotifications", TestDataInsight, "End_User");
				for (int intCounter = intStartRow; intCounter <= intEndRow; intCounter++) {
					try {
						fnOpenTest();
						ReportStatus.fnDefaultReportStatus();
						ReportControl.intRowCount = intCounter;
						Hashtable<String, String> data = TestUtil.getDataByRowNo("EUF09_FCTWebEndDisplayMyContactNotifications", TestDataInsight, "End_User", intCounter);
						TestEngineWeb.reporter.initTestCaseDescription("FCTWebEndDisplayMyContactNotifications");
                        
						cmtLib.loginToCMT(data.get("Header"));
						cmtLib.searchForWebGroup(data.get("WebGrp"));
						cmtLib.clickOnTheWebGroup(data.get("WebGrp_Name"));
						// select Contacts and Notifications
						cmtLib.hoverOnManageWebGroupsAndSelectOptions(data.get("Manage_Web_Grp_Options"));
						cmtLib.verifySalesRepAreDisplayed();
						cmtLib.addNewSalesRep(data.get("Rep_Email"));
						cmtLib.verifyNewSalesRepAdded(data.get("Rep_Name"));
						// create client notifications
						cmtLib.createClientNotification(data.get("Rep_Email"));
						// Login as user selected
						cmtLib.hoverOnManageWebGroupsAndSelectOptions(data.get("Manage_Web_Grp_Options2"));
						cmtLib.searchForaUserAndSelect(data.get("LnameEmailUname"), data.get("ContactName"));
						cmtLib.clickOnloginAs();
						switchToChildWindow();
						canadaLib.clickOnSideMenuSelectAccountToolOptions(data.get("Tools_Menu"),
								data.get("Tools_Menu_DD"));
						cmtLib.loginVerification(data.get("ContactName"));
						
						commonLib.clickLogOutLink(data.get("Logout_Header"));
						cmtLib.navigateBackToCMT();
						cmtLib.hoverOnManageWebGroupsAndSelectOptions(data.get("Manage_Web_Grp_Options"));
						cmtLib.deleteClientNotification(data.get("Rep_Email"));
						cmtLib.RemoveExistedsalesreps(data.get("Rep_Name"));
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
				ReportStatus.fnUpdateResultStatus("FCTWebEndDisplayMyContactNotifications", "TC_EUF09", ReportStatus.strMethodName, 1, browser);
				throw new RuntimeException(e);
			}

			finally {
	        	ReportControl.fnEnableJoin();
	        	ReportStatus.fnUpdateResultStatus("FCTWebEndDisplayMyContactNotifications", "TC_EUF09", ReportStatus.strMethodName, counter, browser);
				fnCloseTest();
				ReportControl.fnNextTestJoin(nextTestJoin);
			}
		}

}
