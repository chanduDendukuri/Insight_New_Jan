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

public class ODH08_OrderHistoryOrderDetailsTest extends OrderHistoryLib{
	
		    // #############################################################################################################
			// # Name of the Test :ODH08_OrderHistoryOrderDetails
			// # Migration Author : Cigniti Technologies
			// #
			// # Date of Migration : OCT 2019
			// # DESCRIPTION : This method is to perform Basic Cart operations.
			// # Parameters : StartRow ,EndRow , nextTestJoin
			// #
			// ###############################################################################################################
			@Parameters({ "StartRow", "EndRow", "nextTestJoin" })
			@Test
			public void ODH08(int StartRow, String EndRow, boolean nextTestJoin) throws Throwable {
				int counter = 0;
				try {
					int intStartRow = StartRow;
					int intEndRow = ReportControl.fnGetEndRowCunt(EndRow, "ODH08_OrderHistoryOrderDetails",
							TestDataInsight, "Order_History");
					for (int intCounter = intStartRow; intCounter <= intEndRow; intCounter++) {
						try {
							counter = intCounter;
							fnOpenTest();
							ReportStatus.fnDefaultReportStatus();
							ReportControl.intRowCount = intCounter;
							Hashtable<String, String> data = TestUtil.getDataByRowNo("ODH08_OrderHistoryOrderDetails",
									TestDataInsight, "Order_History", intCounter);
							TestEngineWeb.reporter.initTestCaseDescription("OrderHistoryOrderDetails");
				
							CMTLib cmtLib = new CMTLib();
							CanadaLib canadaLib = new CanadaLib();
							InvoiceHistoryLib invoiceHistoryLib = new InvoiceHistoryLib();
							CommonLib commonLib = new CommonLib();
							
							cmtLib.loginToCMTSearchWebGrpAndUser(data.get("Header"), data.get("WebGrp"),
									data.get("LnameEmailUname"), data.get("ContactName"));
							// ######## GGP Level ################################
							cmtLib.setPermissions(data.get("Menu_Name"), data.get("Set_Permission"));
							cmtLib.setPermissionsToDisable(data.get("Menu_Name"), data.get("Set_Permission1"));
							cmtLib.setPermissionsToDisable(data.get("Menu_Name"), data.get("Set_Permission2"));
							cmtLib.permissionFromDD(data.get("Set_Permission"), data.get("Permission_Dropdown_Option"));
							cmtLib.clickOnloginAs();
							switchToChildWindow();
							cmtLib.loginVerification(data.get("ContactName"));
							canadaLib.clickOnSideMenuSelectAccountToolOptions(data.get("Tools_Menu"),
									data.get("Tools_Menu_DD"));
							verifyOrderHistoryPage();
							clickOnAdvancedSearch();
							startDateCalender(data.get("Date"));
							clickAdvancedSearchButton();
							commonLib.spinnerImage();
							scrollToBottomWithCordinate("200");
							verifySearchResultsAreDisplayed();
							clickOrderNumber();
							invoiceHistoryLib.verifyOrderDetailsPage();
							verifyPrintLink();
							clickSendToColleauge();
							sendToColleauge(data.get("YourName"),data.get("YourEmail"),data.get("RecipientEmail"),data.get("YourComments"));
							clickAddTrackingNotification();
							verifyAddAdditionalNotificationEmail();
							clickOrderAgain();
							commonLib.spinnerImage();
							canadaLib.verifyPlaceCartLabel();
							
							commonLib.clickLogOutLink(data.get("Logout_Header"));
														
							// Test completed
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
					ReportStatus.fnUpdateResultStatus("ODH08_OrderHistoryOrderDetails", "TC_ODH08", ReportStatus.strMethodName, 1, browser);
					throw new RuntimeException(e);
				}
		        finally {
		        	ReportControl.fnEnableJoin();
					ReportStatus.fnUpdateResultStatus("ODH08_OrderHistoryOrderDetails", "TC_ODH08", ReportStatus.strMethodName, counter, browser);
					fnCloseTest();
					ReportControl.fnNextTestJoin(nextTestJoin);
				}
			}

		}
