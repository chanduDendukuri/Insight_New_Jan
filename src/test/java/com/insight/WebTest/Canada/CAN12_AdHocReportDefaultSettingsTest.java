package com.insight.WebTest.Canada;

import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.Date;
import java.util.Hashtable;
import java.util.List;

import com.insight.Lib.*;
import com.insight.ObjRepo.CommonCanadaPage;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.insight.accelerators.ReportControl;
import com.insight.accelerators.TestEngineWeb;
import com.insight.googledrive.ReportStatus;
import com.insight.utilities.TestUtil;

public class CAN12_AdHocReportDefaultSettingsTest extends CanadaLib{
	
	CMTLib cmtLib = new CMTLib();
	CommonLib commonLib = new CommonLib();
	CartLib cartLib = new CartLib();
	SearchLib searchLib = new SearchLib();
	ProductDetailLib prodDetailsLib=new ProductDetailLib();
	OrderLib orderLib=new OrderLib();
	ProductDisplayInfoLib pipLib=new ProductDisplayInfoLib();
	SewpLib sewpLib=new SewpLib();
	ShipBillPayLib shipbLib=new ShipBillPayLib();
	MarriottIntlCorpLib mic=new MarriottIntlCorpLib();
	CommonCanadaLib ccp = new CommonCanadaLib();
	CanadaLib canadaLib = new CanadaLib();
	ReportingLib report = new ReportingLib();

	@Parameters({ "StartRow", "EndRow", "nextTestJoin" })
	@Test
	public void TC_CAN12(int StartRow, String EndRow, boolean nextTestJoin) throws Throwable {
	
				int counter = 0;
				try {
					int intStartRow = StartRow;
					int intEndRow = ReportControl.fnGetEndRowCunt(EndRow, "TC_CAN12AdHocReportDefaultSettings", TestDataInsight, "Canada");
					for (int intCounter = intStartRow; intCounter <= intEndRow; intCounter++) {
						try {
							counter = intCounter;
							fnOpenTest();
							ReportStatus.fnDefaultReportStatus();
							ReportControl.intRowCount = intCounter;
							Hashtable<String, String> data = TestUtil.getDataByRowNo("TC_CAN12AdHocReportDefaultSettings", TestDataInsight,
									"Canada", intCounter);
							TestEngineWeb.reporter.initTestCaseDescription("AdHocReportDefaultSettings");
			//	CommonLib commonLib = new CommonLib();
				CMTLib cmtLib = new CMTLib();
				CanadaLib canadaLib = new CanadaLib();
				CommonCanadaLib commonCanadaLib = new CommonCanadaLib();
							cmtLib.loginToCMT(data.get("Header"));
							cmtLib.searchForWebGroup(data.get("WebGrp"));
							cmtLib.clickOnTheWebGroup(data.get("MgContactName"));
							cmtLib.verifyManageWebGroupSettings();
							cmtLib.hoverOnManageWebGroupsAndSelectOptions(data.get("ManageWebGrpOptions"));
							cmtLib.verifyManageWebGroupsUserManagement();
							cmtLib.searchForaUserAndSelect(data.get("LnameEmailUname"), data.get("ContactName"));
							//cmtLib.setPermissions(data.get("Menu_Name"), data.get("Set_Permission"));
							cmtLib.clickOnPermissionAndRolesMenu(data.get("Menu_Name"));
							cmtLib.permissionForDD(data.get("Set_Permission"), data.get("Permission_Drop_Down"));
							cmtLib.clickOnloginAs();
							switchToChildWindow();
							cmtLib.loginVerification(data.get("ContactName"));
							shipbLib.verifyWEbsiteIsCannada();
							canadaLib.verifyCanadaWebgroup();
							//canadaLib.clickOnSideMenuSelectAccountToolOptions(data.get("Tools_Menu"), data.get("Tools_Menu_DD"));

							commonLib.clickOnAccountToolsAndClickOnProductGrp(data.get("Tools_Menu"),
									data.get("Tools_Menu_DD"));
							//verifyReportsPage();
							clickOnReportOptions(data.get("ReportOption"));
							verifyReportsPage();
				verifySelectReport(data.get("SelectReport"));
				verifyAccountSelections(data.get("AccountSelections"));
				verifyFilterbyCurrency(data.get("Currency"));
			//	verifyFilterOption();
							boolean checkBoxSelected =ccp.verifySelectedUser();
							assertTrue(checkBoxSelected,"Convert all transactions to  check box was selected");
							assertTrue(ccp.verifyDefaultScheduleReportNow(data.get("ScheduleOption")),"Default value is Scheduled Option ");
							verifyDeliveryOption();
							clickOnDeliveryMethod(data.get("DeliveryMethod"));
							ccp.getListOfDeliveryMethodsOption();
							clickOnDeliveryFormat(data.get("DeliveryFormat"));
							ccp.getListOfDeliveryFormatOption();
							ccp.clickOnReportNameDD();
							ccp.getListOfReportNameOption();
							ccp.clickOnDeliveryFormatDD();
							ccp.getDeliveryDateFormatDDOptions();
							clickOnAccountSelections(data.get("AccountSelections"));
							verifyQuickDateOption(data.get("QuickDateOptions"));
							ccp.clickOnDateRangeDD();
							ccp.getDateRangeDDOptions();

							String StartDate = ccp.getDefaultStartDate();
							String day = StartDate.split("-")[0];
							String month = StartDate.split("-")[1];
							String year = StartDate.split("-")[2];
							String endDate=ccp.getDefaultEndDate();
							String eday = endDate.split("-")[0];
							String emonth = endDate.split("-")[1];
							String eyear = endDate.split("-")[2];
							LocalDate today = LocalDate.now();
							String Currentyear = today.toString().split("-")[0];
							String currentMonth=ccp.currentMonthComparision();
							String date = today.toString().split("-")[2];
							assertTrue(day.equals("01") && year.equals(Currentyear) && month.equalsIgnoreCase(currentMonth),"Default Start date is Starting of the month " );
							assertTrue(eday.equals(date) && year.equals(Currentyear) && month.equalsIgnoreCase(currentMonth),"Default End date is Current date" );
							assertTrue(ccp.verifyInvoiceDateDefaultCheck(),"By default Invoice date check box was selected");
							assertTrue(!ccp.verifySMART_CHECK(),"By default SmartCheck was not selected");
							verifyFilterOrder();
							ccp.addAvailableItemsToAllowItems();

					/*		ccp.clickOnReportNameDD();
							//ccp.getListOfReportNameOption();
							report.clickOnDeliveryReport("Custom Report Name");
							String customName="AdHoc";
							report.clickOnCustomName(customName);*/
							DateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
							Date datev = new Date();

							clickOnRun();
							//String file=ccp.FileNameWithdateSplit(customName);
							Thread.sleep(60000);
							ccp.verifyExportFile("Page1","3","Operations Center,Region,Account Number",ccp.getLatestFilefromDir());
							//cartLib.verifyExportFile("Page1","1","Ad-Hoc");


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
		//gErrorMessage = e.toString();
		gTestStatus = false;
		ReportStatus.fnUpdateResultStatus("AdHocReportDefaultSettings", "TC_CAN12", ReportStatus.strMethodName, 1, browser);
		throw new RuntimeException(e);
	}
    finally {
    	ReportControl.fnEnableJoin();
		ReportStatus.fnUpdateResultStatus("AdHocReportDefaultSettings", "TC_CAN12", ReportStatus.strMethodName, counter, browser);
		fnCloseTest();
		ReportControl.fnNextTestJoin(nextTestJoin);
	}
}
}
