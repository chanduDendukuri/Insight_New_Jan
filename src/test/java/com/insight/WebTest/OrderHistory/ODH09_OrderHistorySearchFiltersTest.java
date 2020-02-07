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

public class ODH09_OrderHistorySearchFiltersTest extends OrderHistoryLib {

	// #############################################################################################################
	// # Name of the Test :ODH09_OrderHistorySearchFilters
	// # Migration Author : Cigniti Technologies
	// #
	// # Date of Migration : OCT 2019
	// # DESCRIPTION 
	// # Parameters : StartRow ,EndRow , nextTestJoin
	// #
	// ###############################################################################################################
			@Parameters({ "StartRow", "EndRow", "nextTestJoin" })
			@Test
			public void Tc_ODH09(int StartRow,String EndRow, boolean nextTestJoin) throws Throwable {
				int counter = 0;
				try {
					int intStartRow = StartRow;
					int intEndRow = ReportControl.fnGetEndRowCunt(EndRow, "ODH09_OrderHistorySearchFilters",
							TestDataInsight, "Order_History");
					for (int intCounter = intStartRow; intCounter <= intEndRow; intCounter++) {
						try {
							counter = intCounter;
							fnOpenTest();
							ReportStatus.fnDefaultReportStatus();
							ReportControl.intRowCount = intCounter;
							Hashtable<String, String> data = TestUtil.getDataByRowNo("ODH09_OrderHistorySearchFilters",
									TestDataInsight, "Order_History", intCounter);
							TestEngineWeb.reporter.initTestCaseDescription("OrderHistorySearchFilters");	
			
			
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
							// ######## On hold status ################################
							selectOrderStatusDropdown(data.get("Order_Status_Onhold"));
							startDateCalender(data.get("Date"));
							clickAdvancedSearchButton();
							spinnerImageODH();
							//scrollToBottomWithCordinate("300");
							verifyOrderStatusResults(data.get("Order_Status_Onhold"));
							
							scrollUp();
							// ######## In progress status ################################
							selectOrderStatusDropdown(data.get("Order_Status_Inprogress"));
							//startDateCalender(previousYearToCurrentDate());
							//endDateCalender(currentDate());
							clickAdvancedSearchButton();
							spinnerImageODH();
							//scrollToBottomWithCordinate("300");
							verifyOrderStatusResults(data.get("Order_Status_Inprogress"));
							//clickClearcSearch();
							scrollUp();
							// ######## Partially shipped ################################
							selectOrderStatusDropdown(data.get("Order_Status_Partially_shipped"));
							//startDateCalender(previousYearToCurrentDate());
							//endDateCalender(previousYearPlusThreeMonthsToCurrentDate());
							clickAdvancedSearchButton();
							spinnerImageODH();
							//scrollToBottomWithCordinate("300");
							verifyOrderStatusResults(data.get("Order_Status_Partially_shipped"));
							//clickClearcSearch();
							scrollUp();
							// ######## Completed ################################
							selectOrderStatusDropdown(data.get("Order_Status_Completed"));
							//startDateCalender(previousYearPlusThreeMonthsToCurrentDate());
							//endDateCalender(currentDate());
							clickAdvancedSearchButton();
							spinnerImageODH();
							//scrollToBottomWithCordinate("300");
							verifyOrderStatusResults(data.get("Complete_Status"));
							//clickClearcSearch();
							scrollUp();
							selectOrderStatusDropdown("All order statuses");
							// ######## Non shippable ################################
							selectShippingTypeDropdown(data.get("Shipping_Type"));
							//startDateCalender(previousYearToCurrentDate());
							//endDateCalender(previousYearPlusThreeMonthsToCurrentDate());
							clickAdvancedSearchButton();
							spinnerImageODH();
							//scrollToBottomWithCordinate("300");
							verifyNonShippableQty(data.get("QTY"),data.get("QTY_Shipped"));
							//clickClearcSearch();
							scrollUp();
							// ######## invoiced orders ################################
							selectOrderStatusDropdown("All order statuses");
							selectOpenOrInvoicedTypeDropdown(data.get("Invoiced_order"));
							selectShippingTypeDropdown("All shipping types");
							//startDateCalender(previousYearPlusThreeMonthsToCurrentDate());
							//endDateCalender(currentDate());
							clickAdvancedSearchButton();
							spinnerImageODH();
							//scrollToBottomWithCordinate("300");
							verifyOrderStatusResults(data.get("Complete_Status"));
							clickClearcSearch();
							scrollUp();
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
					ReportStatus.fnUpdateResultStatus("ODH09_OrderHistorySearchFilters", "TC_ODH09", ReportStatus.strMethodName, 1, browser);
					throw new RuntimeException(e);
				}
		        finally {
		        	ReportControl.fnEnableJoin();
					ReportStatus.fnUpdateResultStatus("ODH09_OrderHistorySearchFilters", "TC_ODH09", ReportStatus.strMethodName, counter, browser);
					fnCloseTest();
					ReportControl.fnNextTestJoin(nextTestJoin);
				}
		
						}
		
}

