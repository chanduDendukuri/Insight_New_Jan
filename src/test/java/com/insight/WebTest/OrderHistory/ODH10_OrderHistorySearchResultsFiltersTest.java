package com.insight.WebTest.OrderHistory;

import java.util.Hashtable;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.insight.Lib.CMTLib;
import com.insight.Lib.CanadaLib;
import com.insight.Lib.CommonLib;
import com.insight.Lib.InvoiceHistoryLib;
import com.insight.Lib.OrderHistoryLib;
import com.insight.accelerators.ReportControl;
import com.insight.accelerators.TestEngineWeb;
import com.insight.googledrive.ReportStatus;
import com.insight.utilities.TestUtil;

public class ODH10_OrderHistorySearchResultsFiltersTest extends OrderHistoryLib{

	// #############################################################################################################
	// # Name of the Test :ODH10_OrderHistorySearchResultsFilters
	// # Migration Author : Cigniti Technologies
	// #
	// # Date of Migration : OCT 2019
	// # DESCRIPTION 
	// # Parameters : StartRow ,EndRow , nextTestJoin
	// #
	// ###############################################################################################################
			@Parameters({ "StartRow", "EndRow", "nextTestJoin" })
			@Test
			public void TC_ODH10(int StartRow,String EndRow, boolean nextTestJoin) throws Throwable {
				int counter = 0;
				try {
					int intStartRow = StartRow;
					int intEndRow = ReportControl.fnGetEndRowCunt(EndRow, "ODH10_OrderHistorySearchResultsFilters",
							TestDataInsight, "Order_History");
					for (int intCounter = intStartRow; intCounter <= intEndRow; intCounter++) {
						try {
							counter = intCounter;
							fnOpenTest();
							ReportStatus.fnDefaultReportStatus();
							ReportControl.intRowCount = intCounter;
							Hashtable<String, String> data = TestUtil.getDataByRowNo("ODH10_OrderHistorySearchResultsFilters",
									TestDataInsight, "Order_History", intCounter);
							TestEngineWeb.reporter.initTestCaseDescription("OrderHistorySearchResultsFilters");	
			
							CMTLib cmtLib = new CMTLib();
							CanadaLib canadaLib = new CanadaLib();
							CommonLib commonLib = new CommonLib();
							InvoiceHistoryLib ivhLib=new InvoiceHistoryLib();


							cmtLib.loginToCMTSearchWebGrpAndUser(data.get("Header"), data.get("WebGrp"),
									data.get("LnameEmailUname"), data.get("ContactName"));
							// ######## GGP Level ################################
							cmtLib.setPermissions(data.get("Menu_Name"), data.get("Set_Permission"));
							cmtLib.permissionFromDD(data.get("Set_Permission"), data.get("Permission_Dropdown_Option"));
							cmtLib.clickOnloginAs();
							switchToChildWindow();
							cmtLib.loginVerification(data.get("ContactName"));
							//ivhLib.closeAccountTools();
							canadaLib.clickOnSideMenuSelectAccountToolOptions(data.get("Tools_Menu"),
									data.get("Tools_Menu_DD"));
							verifyOrderHistoryPage();
							clickOnAdvancedSearch();
							// ######## select Accounts under the RP ################################
							selectAccountSelectionDropdown(data.get("ACCOUNT_RP"));
							startDateCalender(data.get("Date"));
							clickAdvancedSearchButton();
							spinnerImageODH();
							scrollToBottomWithCordinate("300");
							verifySearchResultsAreDisplayed();
							// ######## Results per page 20 ################################
							selectDisplayPerPage(data.get("Results_Per_Page"));
							verifySearchResultsMoreThanFive();
							// ######## Sort by order number and sort order ascending ################################
							selectSortResults(data.get("Sort_Order_Number"));
							selectSortOrder(data.get("Ascending"));
							verifySearchResultsAreInAscending();
							//######## Sort by order date and sort order Descending ################################
							selectSortResults(data.get("Sort_Order_Date"));
							selectSortOrder(data.get("Descending"));
							verifyDatesOrder();
							//######## Sort by PO and sort order Descending ################################
							//selectSortResults(data.get("Sort_PO_Number"));
							
							//######## Sort by order status and sort order ascending ################################
							//selectSortResults(data.get("Sort_Order_Status"));
							//selectSortOrder(data.get("Ascending"));
							
							//######## Sort by PO and sort order ascending ################################
							//selectSortResults(data.get("Sort_PO_Number"));
							
							clickClearcSearch();
							scrollUp();
							//End Of The Test
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
					ReportStatus.fnUpdateResultStatus("ODH10_OrderHistorySearchResultsFilters", "TC_ODH10", ReportStatus.strMethodName, 1, browser);
					throw new RuntimeException(e);
				}
		        finally {
		        	ReportControl.fnEnableJoin();
					ReportStatus.fnUpdateResultStatus("ODH10_OrderHistorySearchResultsFilters", "TC_ODH10", ReportStatus.strMethodName, counter, browser);
					fnCloseTest();
					ReportControl.fnNextTestJoin(nextTestJoin);
				}
		
						}
		
}

