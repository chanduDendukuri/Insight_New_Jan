package com.insight.WebTest.Reporting;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Hashtable;
import java.util.List;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.insight.Lib.CMTLib;
import com.insight.Lib.CanadaLib;
import com.insight.Lib.CartLib;
import com.insight.Lib.CommonCanadaLib;
import com.insight.Lib.CommonLib;
import com.insight.Lib.MarriottIntlCorpLib;
import com.insight.Lib.OrderLib;
import com.insight.Lib.ProductDetailLib;
import com.insight.Lib.ProductDisplayInfoLib;
import com.insight.Lib.SearchLib;
import com.insight.Lib.SewpLib;
import com.insight.Lib.ShipBillPayLib;
import com.insight.accelerators.ReportControl;
import com.insight.accelerators.TestEngineWeb;
import com.insight.googledrive.ReportStatus;
import com.insight.utilities.TestUtil;

public class REP03_AdHocReportDefaultSettingsTest extends CanadaLib{
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
	
	@Parameters({ "StartRow", "EndRow", "nextTestJoin" })
	@Test
	public void TC_REP03(int StartRow, String EndRow, boolean nextTestJoin) throws Throwable {
		
		
		int counter = 0;
		try {
			int intStartRow = StartRow;
			int intEndRow = ReportControl.fnGetEndRowCunt(EndRow, "TC_REP03AdHocReportDefaultSettings", TestDataInsight, "Reporting");
			for (int intCounter = intStartRow; intCounter <= intEndRow; intCounter++) {
				try {
					counter = intCounter;
					fnOpenTest();
					ReportStatus.fnDefaultReportStatus();
					ReportControl.intRowCount = intCounter;
					Hashtable<String, String> data = TestUtil.getDataByRowNo("TC_REP03AdHocReportDefaultSettings", TestDataInsight,
							"Reporting", intCounter);
					TestEngineWeb.reporter.initTestCaseDescription("AdHocReportDefaultSettings");
	
	
			//	CommonLib commonLib = new CommonLib();
				CMTLib cmtLib = new CMTLib();
			//	OrderLib orderLib = new OrderLib();
				CanadaLib canadaLib=new CanadaLib();
				CommonCanadaLib ccp = new CommonCanadaLib();
				
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
				clickOnRun();
				String a = data.get("ReportOption");
				List<String> excelOptions= Arrays.asList(data.get("ExcelOptions").split(","));
				canadaLib.openDirectoryToVerifyFileExist(a);
				verifyDownloadedReportExcelFile(excelOptions,data.get("ReportOption"));
                     commonLib.clickLogOutLink(data.get("Logout_Header"));
	                 System.out.println("Test completed");
	
				////////////////////////////////////old code////////////////////////////////
				cmtLib.loginToCMTSearchWebGrpAndUser(data.get("Header"), data.get("WebGrp"), data.get("LnameEmailUname"),data.get("ContactName"));
				cmtLib.clickOnRolesAndPermissionsAndSetPermission(data.get("Menu_Name"), data.get("Set_Permission"));
				cmtLib.loginAsAdminCMT();
				mic.clickAccountToolsFromSideMenu(data.get("Tools_Menu"),data.get("Tools_Menu_DD"));	
				clickOnReportOptions(data.get("ReportOption"));
				verifyReportsPage();
				verifySelectReport(data.get("SelectReport"));
				verifyAccountSelections(data.get("AccountSelections"));
				verifyFilterbyCurrency(data.get("Currency"));
				verifyFilterOption();
				verifyScheduleReport(data.get("ScheduleOption"));
				verifyDeliveryOption();
				clickOnAccountSelections(data.get("AccountSelections"));
				verifyQuickDateOption(data.get("QuickDateOptions"));
				verifyCustomDate();
				verifyFilterOrder();
				verifySmartcheck();
				verifyAllFields();
				clickOnDeliveryMethod(data.get("DeliveryMethod"));
				clickOnDeliveryFormat(data.get("DeliveryFormat"));
				clickOnRun();	
				List<String> excelOptions1= Arrays.asList(data.get("ExcelOptions").split(","));
			    canadaLib.verifyDownloadedReportExcelFile(excelOptions1,data.get("ReportOption"));
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
			//gErrorMessage = e.toString();
			gTestStatus = false;
			ReportStatus.fnUpdateResultStatus("AdHocReportDefaultSettings", "TC_REP03", ReportStatus.strMethodName, 1, browser);
			throw new RuntimeException(e);
		}
        finally {
        	ReportControl.fnEnableJoin();
			ReportStatus.fnUpdateResultStatus("AdHocReportDefaultSettings", "TC_REP03", ReportStatus.strMethodName, counter, browser);
			fnCloseTest();
			ReportControl.fnNextTestJoin(nextTestJoin);
		}
	}
}