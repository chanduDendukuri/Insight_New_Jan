package com.insight.WebTest.Reporting;

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
import com.insight.Lib.InvoiceHistoryLib;
import com.insight.Lib.MarriottIntlCorpLib;
import com.insight.Lib.OrderLib;
import com.insight.Lib.ProductDetailLib;
import com.insight.Lib.ProductDisplayInfoLib;
import com.insight.Lib.ReportingLib;
import com.insight.Lib.SearchLib;
import com.insight.Lib.SewpLib;
import com.insight.Lib.ShipBillPayLib;
import com.insight.accelerators.ReportControl;
import com.insight.accelerators.TestEngineWeb;
import com.insight.googledrive.ReportStatus;
import com.insight.utilities.TestUtil;

public class REP08_SoftwareLicenseDetailDefaultSettingsTest extends ReportingLib{
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
	public void TC_REP08(int StartRow, String EndRow, boolean nextTestJoin) throws Throwable {
		
		
		int counter = 0;
		try {
			int intStartRow = StartRow;
			int intEndRow = ReportControl.fnGetEndRowCunt(EndRow, "TC_REP08SoftwareLicenseDetailDefaultSettings", TestDataInsight, "Reporting");
			for (int intCounter = intStartRow; intCounter <= intEndRow; intCounter++) {
				try {
					counter = intCounter;
					fnOpenTest();
					ReportStatus.fnDefaultReportStatus();
					ReportControl.intRowCount = intCounter;
					Hashtable<String, String> data = TestUtil.getDataByRowNo("TC_REP08SoftwareLicenseDetailDefaultSettings", TestDataInsight,
							"Reporting", intCounter);
					TestEngineWeb.reporter.initTestCaseDescription("SoftwareLicenseDetailDefaultSettings");
	

			//	CommonLib commonLib = new CommonLib();
				CMTLib cmtLib = new CMTLib();
				CanadaLib canadaLib = new CanadaLib();				
				InvoiceHistoryLib invoiceHistoryLib = new InvoiceHistoryLib();
				CommonCanadaLib ccp = new CommonCanadaLib();
				
				
				cmtLib.loginToCMTSearchWebGrpAndUser(data.get("Header"), data.get("WebGrp"),
						data.get("LnameEmailUname"), data.get("ContactName"));
				cmtLib.setPermissionsToDisable(data.get("Menu_Name"), data.get("Set_Permission1"));
				cmtLib.setPermissions(data.get("Menu_Name"), data.get("Set_Permission2"));
				cmtLib.setPermissions(data.get("Menu_Name"), data.get("Set_Permission3"));
				cmtLib.permissionFromDD(data.get("Set_Permission3"), data.get("Permission_Dropdown_Option"));
				cmtLib.loginAsAdminCMT();
				canadaLib.clickOnSideMenuSelectAccountToolOptions(data.get("Tools_Menu"),
						data.get("Tools_Menu_DD"));
				canadaLib.clickOnReportOptions(data.get("ReportOption"));
				canadaLib.verifyReportsPage();
				verifySelectReportOptions();
				canadaLib.verifySelectReport(data.get("SelectReport"));
				verifyScheduleReport();
				verifyScheduleReportOption(data.get("Soption"));
				List<String> optionList = Arrays.asList(data.get("ScheduleOptions").split(","));
				verifyScheduleReportOptions(optionList);
				canadaLib.verifyFilterbyCurrency(data.get("Currency"));
				canadaLib.verifyDeliveryOption();
				List<String> deliveryOptionsList = Arrays.asList(data.get("DeliveryOptions").split(","));
				verifyDeliveryMethodOptions(deliveryOptionsList);
				verifyDefaultAccountSelection(data.get("DefaultAccountSelOpt"));
				verifytheLinkedSoldTosText();
				verifyDefualtCurrancyUSD();
				verifyStartDate("01");
				EndDateVerification();
				verifyDefaultCurrentDate(data.get("CurrentDate"));
				List<String> dateOptionsList = Arrays.asList(data.get("ScheduleDates").split(","));
				verifyScheduleReportOptionsdates(dateOptionsList);
				canadaLib.clickOnDeliveryMethod(data.get("DeliveryMethod"));
				canadaLib.clickOnDeliveryFormat(data.get("DeliverFormat"));
				canadaLib.clickOnRun();
				Thread.sleep(60000);
				String List="Manufacturer,Product Description,Enrollment Number,Vendor Master,Pool Description,Invoiced Quantity,Total Sales,Currency,Points,Total Points";
				ccp.verifyExportFile("Page1","2",List,ccp.getLatestFilefromDir());
				commonLib.clickLogOutLink(data.get("Logout_Header"));
				
				//Logout
				
				cmtLib.navigateBackToCMT();
				cmtLib.hoverOverMasterGroupAndSelectChangeGrp();
				cmtLib.searchForWebGroup(data.get("WebGrp1"));
				cmtLib.manageUsers();
				cmtLib.searchForaUserAndSelect(data.get("LnameEmailUname1"), data.get("ContactName1"));
				cmtLib.setPermissionsToDisable(data.get("Menu_Name"), data.get("Set_Permission1"));
				cmtLib.setPermissions(data.get("Menu_Name"), data.get("Set_Permission2"));
				cmtLib.setPermissions(data.get("Menu_Name"), data.get("Set_Permission3"));
				cmtLib.permissionFromDD(data.get("Set_Permission3"), data.get("Permission_Dropdown_Option"));
				cmtLib.loginAsAdminCMT();

				canadaLib.clickOnSideMenuSelectAccountToolOptions(data.get("Tools_Menu"),
						data.get("Tools_Menu_DD"));
				canadaLib.clickOnReportOptions(data.get("ReportOption"));
				canadaLib.verifyReportsPage();
				canadaLib.verifySelectReport(data.get("SelectReport"));
				verifytheLinkedSoldTosText();
				verifyStartDate("01");
				EndDateVerification();
				verifyScheduleReport();
				verifyScheduleReportOptions(optionList);
				canadaLib.verifyFilterbyCurrency(data.get("Currency"));
				canadaLib.verifyDeliveryOption();
				Thread.sleep(10000);
				verifyDeliveryMethodOptions(deliveryOptionsList);
				verifyDefaultCurrentDate(data.get("CurrentDate"));
				Thread.sleep(10000);
				verifyScheduleReportOptionsdates(dateOptionsList);
				canadaLib.clickOnDeliveryMethod(data.get("DeliveryMethod"));
				canadaLib.clickOnDeliveryFormat(data.get("DeliverFormat"));
				canadaLib.clickOnRun(); 
				Thread.sleep(60000);
				String List2="Manufacturer,Product Description,Enrollment Number,Vendor Master,Pool Description,Invoiced Quantity,Total Sales,Currency,Points,Total Points";
				ccp.verifyExportFile("Page1","2",List2,ccp.getLatestFilefromDir());
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
			ReportStatus.fnUpdateResultStatus("SoftwareLicenseDetailDefaultSettings", "TC_REP08", ReportStatus.strMethodName, 1, browser);
			throw new RuntimeException(e);
		}
        finally {
        	ReportControl.fnEnableJoin();
			ReportStatus.fnUpdateResultStatus("SoftwareLicenseDetailDefaultSettings", "TC_REP08", ReportStatus.strMethodName, counter, browser);
			fnCloseTest();
			ReportControl.fnNextTestJoin(nextTestJoin);
		}
	}
}

