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

import com.insight.Lib.ReportingLib;
import com.insight.Lib.SearchLib;
import com.insight.accelerators.ReportControl;
import com.insight.accelerators.TestEngineWeb;
import com.insight.googledrive.ReportStatus;
import com.insight.utilities.TestUtil;

public class REP01_InvoicedOrdersDefaultSettingsTest extends ReportingLib {

	// #############################################################################################################
			// # Name of the Test :TC_QTH03QuoteHistoryViewSearchResults
			// # Migration Author : Cigniti Technologies
			// #
			// # Date of Migration : OCT 2019
			// # DESCRIPTION : This method is to perform Quote History search with date
			// operations.
			// # Parameters : StartRow ,EndRow , nextTestJoin
			// #
			// ###############################################################################################################
			@Parameters({ "StartRow", "EndRow", "nextTestJoin" })
			@Test
			public void TC_REP01InvoicedOrdersDefaultSettings(int StartRow, String EndRow, boolean nextTestJoin) throws Throwable {
				try {
					int intStartRow = StartRow;
					int intEndRow = ReportControl.fnGetEndRowCunt(EndRow, "TC_REP01InvoicedOrdersDefaultSettings", TestDataInsight,
							"Reporting");
					for (int intCounter = intStartRow; intCounter <= intEndRow; intCounter++) {
						try {
							fnOpenTest();
							ReportStatus.fnDefaultReportStatus();
							ReportControl.intRowCount = intCounter;
							Hashtable<String, String> data = TestUtil.getDataByRowNo("TC_REP01InvoicedOrdersDefaultSettings",TestDataInsight, "Reporting", intCounter);
							TestEngineWeb.reporter.initTestCaseDescription("InvoicedOrdersDefaultSettings");
							reporter.SuccessReport("Iteration Number : ",
									"**************Iteration Number::  " + intCounter + " For:: " + data.get("LoginName") + " ::and:: "
											+ data.get("Password") + " To Validate::" + data.get("errorMessage") + "  **************",
									"");
	 
							CMTLib cmtLib = new CMTLib();
							CanadaLib canadaLib=new CanadaLib();
							MarriottIntlCorpLib marriottIntlCorpLib=new MarriottIntlCorpLib();
							CommonLib commonLib = new CommonLib();
							CommonCanadaLib ccp = new CommonCanadaLib();

							
							cmtLib.loginToCMTSearchWebGrpAndUser(data.get("Header"), data.get("WebGrp"), data.get("LnameEmailUname"),data.get("ContactName"));
							cmtLib.setPermissionsToDisable(data.get("Menu_Name"), data.get("Set_Permission1"));
							cmtLib.setPermissionsToDisable(data.get("Menu_Name"), data.get("Set_Permission2"));
							cmtLib.setPermissions(data.get("Menu_Name"), data.get("Set_Permission3"));
							cmtLib.permissionFromDD(data.get("Set_Permission3"), data.get("Permission_Dropdown_Option"));
							cmtLib.loginAsAdminCMT();
							Thread.sleep(5000);
							canadaLib.clickOnSideMenuSelectAccountToolOptions(data.get("Tools_Menu"),data.get("Tools_Menu_DD"));	
							canadaLib.clickOnReportOptions(data.get("ReportOption"));
							canadaLib.verifyReportsPage();
							canadaLib.verifySelectReport(data.get("SelectReport"));
							verifyScheduleReport();							
							List<String> optionList= Arrays.asList(data.get("ScheduleOptions").split(","));
							verifyScheduleReportOptions(optionList);
							Thread.sleep(5000);
							canadaLib.verifyFilterbyCurrency(data.get("Currency"));
							canadaLib.verifyDeliveryOption();
							List<String> deliveryOptionsList= Arrays.asList(data.get("DeliveryOptions").split(","));
							verifyDeliveryMethodOptions(deliveryOptionsList);
							verifyDefaultAccountSelection(data.get("DefaultAccountSelOpt"));
							verifyDefaultCurrentDate(data.get("CurrentDate"));
							List<String> dateOptionsList= Arrays.asList(data.get("ScheduleDates").split(","));
							verifyScheduleReportOptionsdates(dateOptionsList);
							verifyDefualtCurrancyUSD();
							verifyStartDate(data.get("DayOne"));
							EndDateVerification();
							canadaLib.clickOnDeliveryMethod(data.get("DeliveryMethod"));
							clickOnDeliveryReport(data.get("DeliveryReport"));
							String customName=getRandomString(7);
							clickOnCustomName(customName);
							canadaLib.clickOnDeliveryFormat(data.get("DeliverFormat"));
							canadaLib.clickOnRun();
							commonLib.spinnerImage();
							Thread.sleep(50000);
							verifyCustomeName(customName);
							String List="Operations Center,Region,Account Number,Account Name,Sales Rep Name,Service Sales Rep Name,Billing Account,Billing Name,Order Type,Reference Number,Web Group,PO Number,PO Release No.,Order Number,Order Date,Invoice Number,Invoice Date,Order Line No,Invoice Line Number,Customer Material,Manufacturer Material,Material Desc,Country of Usage,Invoiced Quantity,Serial Number,Asset Number,Manufacturer,Unit Price,MSRP,Ext Price,Total Price,Tax,PST/QST,GST/HST,Freight Line,Freight Total,EWR Fee,Currency,Product Category,Product Subcategory,Carrier,Ship Method,Ship Date,Shipping Account Number,Shipping Name,Shipping Attention,Shipping Street,Shipping City,Shipping State / Province,Shipping Postal Code,Shipping Country";
							ccp.verifyExportFile("Page1","2",List,ccp.getLatestFilefromDir());
							commonLib.clickLogOutLink(data.get("Logout_Header"));	
							Thread.sleep(5000);
							cmtLib.navigateBackToCMT();				
							cmtLib.hoverOverMasterGroupAndSelectChangeGrp();
							cmtLib.searchForWebGroup(data.get("WebGrp"));
							Thread.sleep(5000);
							cmtLib.manageUsers();
							cmtLib.searchForaUserAndSelect(data.get("LnameEmailUname"), data.get("ContactName"));
							cmtLib.setPermissionsToDisable(data.get("Menu_Name"), data.get("Set_Permission1"));
							cmtLib.setPermissionsToDisable(data.get("Menu_Name"), data.get("Set_Permission2"));
							cmtLib.setPermissions(data.get("Menu_Name"), data.get("Set_Permission3"));
							cmtLib.permissionFromDD(data.get("Set_Permission3"), data.get("Permission_Dropdown_Option"));
							cmtLib.loginAsAdminCMT();
							marriottIntlCorpLib.clickAccountToolsFromSideMenu(data.get("Tools_Menu"),data.get("Tools_Menu_DD"));
							canadaLib.clickOnReportOptions(data.get("ReportOption1"));
							canadaLib.verifyReportsPage();
							verifySelectReportOptions() ;
							canadaLib.verifySelectReport(data.get("SelectReport1"));
							verifyScheduleReport();							
							List<String> optionList1= Arrays.asList(data.get("ScheduleOptions").split(","));
							verifyScheduleReportOptions(optionList1);
							List<String> dateOptionsList1= Arrays.asList(data.get("ScheduleDates").split(","));
							verifyScheduleReportOptionsdates(dateOptionsList1);
							canadaLib.verifyFilterbyCurrency(data.get("Currency"));
							canadaLib.verifyDeliveryOption();
							List<String> deliveryOptionsList1= Arrays.asList(data.get("DeliveryOptions").split(","));
							verifyDeliveryMethodOptions(deliveryOptionsList1);
							verifyDefaultAccountSelection(data.get("DefaultAccountSelOpt"));
							verifyDefaultCurrentDate(data.get("CurrentDate"));
							verifyDefualtCurrancyUSD();
							verifyStartDate(data.get("DayOne"));
							EndDateVerification();
							canadaLib.clickOnDeliveryMethod(data.get("DeliveryMethod"));
							clickOnDeliveryReport(data.get("DeliveryReport"));							
							String customName1=getRandomString(7);
							clickOnCustomName(customName1);
							canadaLib.clickOnDeliveryFormat(data.get("DeliverFormat"));
							canadaLib.clickOnRun();
							commonLib.spinnerImage();
							Thread.sleep(50000);
							verifyCustomeName(customName1);
							String List1="Ops Center,Region,Invoice Date,PO Number,PO Release No.,Invoice #,Order Date,Shipping Location,Shipping State / Province,Shipping Country,Shipping Date,Invoice Total,Currency";
							ccp.verifyExportFile("Page1","2",List1,ccp.getLatestFilefromDir());
							commonLib.clickLogOutLink(data.get("Logout_Header"));
							Thread.sleep(5000);
							cmtLib.navigateBackToCMT();				
							cmtLib.hoverOverMasterGroupAndSelectChangeGrp();
							cmtLib.searchForWebGroup(data.get("WebGrp2"));
							Thread.sleep(5000);
							cmtLib.manageUsers();
							cmtLib.searchForaUserAndSelect(data.get("LnameEmailUname2"), data.get("ContactName2"));
							cmtLib.setPermissionsToDisable(data.get("Menu_Name"), data.get("Set_Permission1"));
							cmtLib.setPermissionsToDisable(data.get("Menu_Name"), data.get("Set_Permission2"));
							cmtLib.permissionFromDD(data.get("Set_Permission3"), data.get("Permission_Dropdown_Option"));
							cmtLib.loginAsAdminCMT();
							Thread.sleep(5000);
							canadaLib.clickOnSideMenuSelectAccountToolOptions(data.get("Tools_Menu"),data.get("Tools_Menu_DD"));	
							canadaLib.clickOnReportOptions(data.get("ReportOption"));
							canadaLib.verifyReportsPage();
							canadaLib.verifySelectReport(data.get("SelectReport"));
							verifyScheduleReport();
							List<String> optionList2= Arrays.asList(data.get("ScheduleOptions").split(","));
							verifyScheduleReportOptions(optionList2);
							List<String> dateOptionsList2= Arrays.asList(data.get("ScheduleDates").split(","));
							verifyScheduleReportOptionsdates(dateOptionsList2);
							canadaLib.verifyFilterbyCurrency(data.get("Currency"));
							canadaLib.verifyDeliveryOption();
							List<String> deliveryOptionsList2= Arrays.asList(data.get("DeliveryOptions").split(","));
							verifyDeliveryMethodOptions(deliveryOptionsList2);
							verifyDefaultAccountSelection(data.get("DefaultAccountSelOpt"));
							verifyDefaultCurrentDate(data.get("CurrentDate"));
							clickSmartTracker();
							canadaLib.clickOnDeliveryMethod(data.get("DeliveryMethod"));
							clickOnDeliveryReport(data.get("DeliveryReport"));							
							String customName2=getRandomString(7);
							clickOnCustomName(customName2);
							canadaLib.clickOnDeliveryFormat(data.get("DeliverFormat"));
							canadaLib.clickOnRun();
							Thread.sleep(50000);
							String List2="Operations Center,Region,Account Number,Account Name,Sales Rep Name,Service Sales Rep Name,Billing Account,Billing Name,Order Type,Reference Number,Web Group,PO Number,PO Release No.,Order Number,Order Date,Invoice Number,Invoice Date,Order Line No,Invoice Line Number,Customer Material,Manufacturer Material,Material Desc,Country of Usage,Invoiced Quantity,Serial Number,Asset Number,Manufacturer,Unit Price,MSRP,Ext Price,Total Price,Tax,PST/QST,GST/HST,Freight Line,Freight Total,EWR Fee,Currency,Product Category,Product Subcategory,Carrier,Ship Method,Ship Date,Shipping Account Number,Shipping Name,Shipping Attention,Shipping Street,Shipping City,Shipping State / Province,Shipping Postal Code,Shipping Country";
							ccp.verifyExportFile("Page1","2",List2,ccp.getLatestFilefromDir());
							commonLib.clickLogOutLink(data.get("Logout_Header"));


						} catch (Exception e) {
							ReportStatus.blnStatus = false;
							//gErrorMessage = e.getMessage();
							gTestStatus = false;
						}
						ReportControl.fnEnableJoin();
						ReportStatus.fnUpdateResultStatus("REP", "TC_REP01", ReportStatus.strMethodName,
								intCounter, browser);
						fnCloseTest();
					}
				} catch (Exception e) {
					e.printStackTrace();
					ReportStatus.blnStatus = false;
					//gErrorMessage = e.getMessage();
					gTestStatus = false;
					ReportStatus.fnUpdateResultStatus("REP", "TC_REP01", ReportStatus.strMethodName, 1,
							browser);
					throw new RuntimeException(e);
				}

				ReportControl.fnNextTestJoin(nextTestJoin);
			}

}
