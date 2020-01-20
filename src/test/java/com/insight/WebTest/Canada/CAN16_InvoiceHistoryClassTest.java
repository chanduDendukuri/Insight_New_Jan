package com.insight.WebTest.Canada;

import java.util.Hashtable;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.insight.Lib.CMTLib;
import com.insight.Lib.CanadaLib;
import com.insight.Lib.InvoiceHistoryLib;
import com.insight.accelerators.ActionEngine;
import com.insight.accelerators.ReportControl;
import com.insight.accelerators.TestEngineWeb;
import com.insight.googledrive.ReportStatus;
import com.insight.utilities.TestUtil;

public class CAN16_InvoiceHistoryClassTest extends ActionEngine  {

	

	// #############################################################################################################
	// # Name of the Test :CAN16_InvoiceHistory
	// # Migration Author : Cigniti Technologies
	// #
	// # Date of Migration : August 2019
	// # DESCRIPTION : This method is to perform Basic Cart operations.
	// # Parameters : StartRow ,EndRow , nextTestJoin
	// #
	// ###############################################################################################################
	@Parameters({ "StartRow", "EndRow", "nextTestJoin" })
	@Test
	public void Tc_CAN16(int StartRow, String EndRow, boolean nextTestJoin) throws Throwable {
		
			

					int counter = 0;
					try {
						int intStartRow = StartRow;
						int intEndRow = ReportControl.fnGetEndRowCunt(EndRow, "CAN16_InvoiceHistory", TestDataInsight, "Canada");
						for (int intCounter = intStartRow; intCounter <= intEndRow; intCounter++) {
							try {
								counter = intCounter;
								fnOpenTest();
								ReportStatus.fnDefaultReportStatus();
								ReportControl.intRowCount = intCounter;
								Hashtable<String, String> data = TestUtil.getDataByRowNo("CAN16_InvoiceHistory", TestDataInsight,
										"Canada", intCounter);
								TestEngineWeb.reporter.initTestCaseDescription("InvoiceHistory");				


			CMTLib cmtLib = new CMTLib();		
			CanadaLib canadaLib=new CanadaLib();
			InvoiceHistoryLib invoiceHistoryLib=new InvoiceHistoryLib();
			cmtLib.loginToCMTSearchWebGrpAndUser(data.get("Header"), data.get("WebGrp"), data.get("LnameEmailUname"),
					data.get("ContactName"));

			cmtLib.clickOnRolesAndPermissionsAndSetPermission(data.get("Menu_Name"), data.get("Set_Permission"));
			cmtLib.loginAsAdminCMT();
			canadaLib.clickOnSideMenuSelectAccountToolOptions(data.get("Tools_Menu"),  data.get("Tools_Menu_DD"));	
			canadaLib.verifyInvoiceHistoryPageOpened();
			canadaLib.clickOnInvoiceHistory();
			invoiceHistoryLib.quickSearchAndVerifySearchResults(data.get("SelectOrder"), data.get("OrderNumber"));
			invoiceHistoryLib.quickSearchAndVerifySearchResults(data.get("SelectReference"), data.get("ReferenceNumber"));
			invoiceHistoryLib.quickSearchAndVerifySearchResults(data.get("SelectPurchaseOrder"), data.get("PurchaseNumber"));
			invoiceHistoryLib.quickSearchAndVerifySearchResults(data.get("SelectInvoice"), data.get("InvoiceNumber"));
			invoiceHistoryLib.quickSearchAndVerifySearchResults(data.get("SelectAssetTag"), data.get("AssetTag"));
			invoiceHistoryLib.quickSearchAndVerifySearchResults(data.get("SelectSerial"), data.get("SerialNumber"));
			invoiceHistoryLib.clickOnAdvancedSearch();
			// calender date
			invoiceHistoryLib.datePickerStartDateCalender(data.get("From_Date"));
			invoiceHistoryLib.datePickerEndDateCalender(data.get("End_Date"));
			invoiceHistoryLib.clickOnAdvancedSearchSearchButton();
			invoiceHistoryLib.verifySearchResultsAreDisplayed();
			String invoiceNumber=canadaLib.getInvoiceNumber();
			invoiceHistoryLib.clickInvoicePreviewLink();
			invoiceHistoryLib.verifyInvoicePreviewPopUp();
			invoiceHistoryLib.verifyInvoicePreviewDetails(data.get("Invoice_details"));
			invoiceHistoryLib.verifyInvoicePreviewShipToAndBillTo();
			invoiceHistoryLib.verifyInvoicePreviewHeaderDetails(data.get("Header_Details"));
			invoiceHistoryLib.verifyInvoicePreviewFootDetails(data.get("Foot_Details"));
			invoiceHistoryLib.clickOnInvoiceFullPreview();
			canadaLib.verifyInvoiceHistoryPageOpened();
			canadaLib.printIcon();			
			invoiceHistoryLib.verifyPrintPopUp();
			 canadaLib.closeIcon();
			 canadaLib.clickOnDownloadLink();
			 canadaLib.verifyDownloadedFile(invoiceNumber);
			
				System.out.println("Test completed");
				
							} catch (Exception e) {
								ReportStatus.blnStatus = false;
							//	gErrorMessage = e.getMessage();
								gTestStatus = false;
							}
						}
					} catch (Exception e) {
						e.printStackTrace();
						ReportStatus.blnStatus = false;
					//	gErrorMessage = e.toString();
						gTestStatus = false;
						ReportStatus.fnUpdateResultStatus("InvoiceHistory", "Tc_CAN16", ReportStatus.strMethodName, 1, browser);
						throw new RuntimeException(e);
					}
				    finally {
				    	ReportControl.fnEnableJoin();
						ReportStatus.fnUpdateResultStatus("InvoiceHistory", "TC_CAN16", ReportStatus.strMethodName, counter, browser);
						fnCloseTest();
						ReportControl.fnNextTestJoin(nextTestJoin);
					}
				}
				}
