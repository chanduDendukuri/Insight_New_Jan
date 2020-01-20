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

public class EUF11_FCTWebEndUserHeadintheCloudsTest extends EndUserFeaturesLib{
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
		// #       Name of the Test         :  EUF11_FCTWebEndUserHeadintheClouds
		// #       Migration Author         :  Cigniti Technologies
		// #
		// #       Date of Migration        : October 2019
		// #       DESCRIPTION              : This Test is used to Test FCTWebEndUserManageSoldtos
		// #       Parameters               : StartRow ,EndRow , nextTestJoin
		// #
		// ###############################################################################################################
		@Parameters({ "StartRow", "EndRow", "nextTestJoin" })
		@Test
		public void TC_EUF11(int StartRow,String EndRow, boolean nextTestJoin) throws Throwable {
			int counter = 0;
			try {
				int intStartRow = StartRow;
				int intEndRow = ReportControl.fnGetEndRowCunt(EndRow, "EUF11_FCTWebEndUserHeadintheClouds", TestDataInsight, "End_User");
				for (int intCounter = intStartRow; intCounter <= intEndRow; intCounter++) {
					try {
						fnOpenTest();
						ReportStatus.fnDefaultReportStatus();
						ReportControl.intRowCount = intCounter;
						Hashtable<String, String> data = TestUtil.getDataByRowNo("EUF11_FCTWebEndUserHeadintheClouds", TestDataInsight, "End_User", intCounter);
						TestEngineWeb.reporter.initTestCaseDescription("FCTWebEndUserHeadintheClouds");
                        
						cmtLib.loginToCMTSearchWebGrpAndUser(data.get("Header"), data.get("WebGrp"),
								data.get("LnameEmailUname"), data.get("ContactName"));
						
						cmtLib.setPermissions(data.get("Menu_Name"), data.get("Set_Permission"));
						cmtLib.clickOnloginAs();
						switchToChildWindow();
						verifyAccountToolsManageCloudDropdownOption(data.get("Tools_Menu"),data.get("Tools_Menu_DD"));
						cmtLib.navigateBackToCMT();
						cmtLib.setPermissionsToDisable(data.get("Menu_Name"), data.get("Set_Permission"));
						cmtLib.clickOnloginAs();
						switchToChildWindow();
						verifyAccountToolsManageCloudDropdownOptionIsNotPresent(data.get("Tools_Menu"),data.get("Tools_Menu_DD"));
						commonLib.clickLogOutLink(data.get("Logout_Header"));
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
				ReportStatus.fnUpdateResultStatus("FCTWebEndUserHeadintheClouds", "TC_EUF11", ReportStatus.strMethodName, 1, browser);
				throw new RuntimeException(e);
			}

			finally {
	        	ReportControl.fnEnableJoin();
	        	ReportStatus.fnUpdateResultStatus("FCTWebEndUserHeadintheClouds", "TC_EUF11", ReportStatus.strMethodName, counter, browser);
				fnCloseTest();
				ReportControl.fnNextTestJoin(nextTestJoin);
			}
		}

}
