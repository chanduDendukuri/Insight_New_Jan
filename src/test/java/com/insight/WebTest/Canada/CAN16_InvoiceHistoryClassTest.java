package com.insight.WebTest.Canada;

import java.util.Hashtable;

import com.insight.Lib.*;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

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

//%%%%%%%%%%%%%%%%%%%%%%%%%latest%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
								CMTLib cmtLib = new CMTLib();
								CanadaLib canadaLib = new CanadaLib();
								ShipBillPayLib shipbLib = new ShipBillPayLib();
								InvoiceHistoryLib invoice = new InvoiceHistoryLib();
								CommonLib commonLib = new CommonLib();
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
								commonLib.clickOnAccountToolsAndClickOnProductGrp(data.get("Tools_Menu"), data.get("Tools_Menu_DD") );
								invoice.verifyInvoiceHistoryLabel();
								invoice.quickSearchAndVerifySearchResults(data.get("SelectOrder"), data.get("OrderNumber"));
								invoice.quickSearchAndVerifySearchResults(data.get("SelectReference"), data.get("ReferenceNumber"));
								invoice.quickSearchAndVerifySearchResults(data.get("SelectPurchaseOrder"), data.get("PurchaseNumber"));
								invoice.quickSearchAndVerifySearchResults(data.get("SelectInvoice"), data.get("InvoiceNumber"));
								invoice.quickSearchAndVerifySearchResults(data.get("SelectAssetTag"), data.get("AssetTag"));
								invoice.quickSearchAndVerifySearchResults(data.get("SelectSerial"), data.get("SerialNumber"));
								invoice.clickOnAdvancedSearch();
								invoice.clickOnAdvancedSearchSearchButton();
								scrollToBottomWithCordinate("160");
								canadaLib.selectStartDateFromInvoiceHistoryCalenaer(data.get("From_Date"));
								canadaLib.selectEndDateFromInvoiceHistoryCalenaer(data.get("End_Date"));
								//invoice.selectEndDateInRecentHistory(data.get("End_Date"));
								// invoice.datePickerEndDateCalender(data.get("End_Date"));
								canadaLib.clickOnSearchButtonInRecentOrders();
								canadaLib.getInvoiceNumbersFromResults();
								String a=canadaLib.clickOnInvoiceNumbersFromResults();
								Thread.sleep(14000);
								canadaLib.openDirectoryToVerifyFileExist(a);
								commonLib.clickLogOutLink(data.get("Logout_Header"));//fnCloseTest();




			 //canadaLib.verifyDownloadedFile(invoiceNumber);

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
