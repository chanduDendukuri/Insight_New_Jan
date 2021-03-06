package com.insight.WebTest.QuoteHistory;

import java.util.Hashtable;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.insight.Lib.CMTLib;
import com.insight.Lib.CanadaLib;
import com.insight.Lib.CartLib;
import com.insight.Lib.CommonLib;
import com.insight.Lib.InvoiceHistoryLib;
import com.insight.Lib.MarriottIntlCorpLib;
import com.insight.Lib.OrderLib;
import com.insight.Lib.ProductDisplayInfoLib;
import com.insight.Lib.QuoteHistoryLib;
import com.insight.Lib.SearchLib;
import com.insight.accelerators.ReportControl;
import com.insight.accelerators.TestEngineWeb;
import com.insight.googledrive.ReportStatus;
import com.insight.utilities.TestUtil;

public class QTH03_QuoteHistoryViewSearchResultsTest extends QuoteHistoryLib {

	// #############################################################################################################
			// # Name of the Test :QuoteHistoryViewSearchResultsTest
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
			public void TC_QTH03(int StartRow, String EndRow, boolean nextTestJoin) throws Throwable {
				
				int counter = 0;
				try {
										
							int intStartRow = StartRow;
							int intEndRow = ReportControl.fnGetEndRowCunt(EndRow, "TC_QTH03QuoteHistoryViewSearchResults", TestDataInsight, "Quote_History");
							for (int intCounter = intStartRow; intCounter <= intEndRow; intCounter++) {
								try {
									
									counter = intCounter;
									fnOpenTest();
									ReportStatus.fnDefaultReportStatus();
									ReportControl.intRowCount = intCounter;
									Hashtable<String, String> data = TestUtil.getDataByRowNo("TC_QTH03QuoteHistoryViewSearchResults", TestDataInsight, "Quote_History", intCounter);
									TestEngineWeb.reporter.initTestCaseDescription("QuoteHistoryViewSearchResults");

	 
							CMTLib cmtLib = new CMTLib();
							SearchLib searchLib = new SearchLib();
							OrderLib orderLib=new OrderLib();
							CanadaLib canadaLib=new CanadaLib();
							CartLib cartLib=new CartLib();
							InvoiceHistoryLib invoiceHistoryLib = new InvoiceHistoryLib();
							CommonLib commonLib = new CommonLib();
							ProductDisplayInfoLib prodinfo=new ProductDisplayInfoLib();
							
							cmtLib.loginToCMT(data.get("Header"));
							cmtLib.verifyClientSearchTitle();
							cmtLib.searchForWebGroup(data.get("WebGrp1"));
							cmtLib.clickOnTheWebGroup(data.get("Webgroupname"));
							cmtLib.verifyManageWebGroupSettings();
							cmtLib.hoverOnManageWebGroupsAndSelectOptions(data.get("ManageWebGrpOptions"));
							cmtLib.verifyManageWebGroupsUserManagement();
							cmtLib.searchForaUserAndSelect(data.get("LnameEmailUname1"), data.get("ContactName1"));
							
							cmtLib.setPermissions(data.get("Menu_Name"), data.get("Set_Permission1"));
							//cmtLib.setPermissionsToDisable(data.get("Menu_Name"), data.get("Set_Permission2"));
							// Login As to Web UAT
							cmtLib.verifyWebGroupsManagementUsers();
						    cmtLib.setPermissionsToDisable(data.get("Menu_Name"), data.get("Set_Permission2"));
						    cmtLib.verifyWebGroupsManagementUsers();
						    cmtLib.setPermissionsToDisable(data.get("Menu_Name"), data.get("Set_Permission3"));
						    cmtLib.clickOnloginAs();
							switchToChildWindow();
							cmtLib.loginVerification("User - "+data.get("ContactName1"));
							commonLib.searchProduct(data.get("SearchItem1"));
							searchLib.verifyTheResultsForSearchTerm(data.get("SearchItem1"));
							prodinfo.getPartNumberInSearchResultsPage();
							commonLib.addToCartAndVerifyInSearchPage();
							orderLib.continueToCheckOutOnAddCart();
							cartLib.verifyCartPageAvailablity();
							orderLib.createQuote(data.get("Quote_Name1"));
							orderLib.getQuoteReferenceName();
							orderLib.getQuoteReferenceNumber();
							canadaLib.clickOnSideMenuSelectAccountToolOptions(data.get("Tools_Menu"),
									data.get("Tools_Menu_DD"));
							verifyQuoteHistoryPageOpened();		
							invoiceHistoryLib.verifyQuickSearch();
							invoiceHistoryLib.verifyAdvancedSearch();
							//commonLib.clickLogOutLink(data.get("Logout_Header"));
							
							/*cmtLib.loginToCMT(data.get("Header"));
							cmtLib.verifyClientSearchTitle();
							cmtLib.searchForWebGroup(data.get("WebGrp1"));
							cmtLib.clickOnTheWebGroup(data.get("Webgroupname"));
							cmtLib.verifyManageWebGroupSettings();
							cmtLib.hoverOnManageWebGroupsAndSelectOptions(data.get("ManageWebGrpOptions"));
							cmtLib.verifyManageWebGroupsUserManagement();
							cmtLib.searchForaUserAndSelect(data.get("LnameEmailUname1"), data.get("ContactName1"));
							
							cmtLib.setPermissions(data.get("Menu_Name"), data.get("Set_Permission1"));
							cmtLib.setPermissions(data.get("Menu_Name"), data.get("Set_Permission2"));
							cmtLib.setPermissions(data.get("Menu_Name"), data.get("Set_Permission3"));
							//cmtLib.setPermissionsToDisable(data.get("Menu_Name"), data.get("Set_Permission2"));
							// Login As to Web UAT
							cmtLib.verifyWebGroupsManagementUsers();
						    cmtLib.setPermissionsToDisable(data.get("Menu_Name"), data.get("Set_Permission2"));
						    cmtLib.verifyWebGroupsManagementUsers();
						    cmtLib.clickOnloginAs();
							switchToChildWindow();
							cmtLib.loginVerification("User - "+data.get("ContactName1"));
							canadaLib.clickOnSideMenuSelectAccountToolOptions(data.get("Tools_Menu"),
									data.get("Tools_Menu_DD"));
							verifyQuoteHistoryPageOpened();	*/
							invoiceHistoryLib.clickOnAdvancedSearch();
							//invoiceHistoryLib.datePickerStartDateCalender(data.get("FromDate"));
							//invoiceHistoryLib.datePickerEndDateCalender(data.get("ToDate"));
							invoiceHistoryLib.clickOnSearchUnderAdvancedSearch();
							invoiceHistoryLib.verifySearchResultsAreDisplayed();
							clickExportToExcelButton();
							String rowNumber = "1";
							
							verifyExportFileInQuoteHistory(data.get("SheetName"),rowNumber,data.get("ColumnHeaders"));
							quoteNumberLink();
							verifyQuoteDetails();
							getDelailsOnQuotePage();
							verifyConvertQuoteButtonEnable();
							verifyPrint();
							verifyEmailIcon();
							verifyEditThisQuoteIcon();
							verifyDeleteThisQuoteIcon();
							commonLib.clickLogOutLink(data.get("Logout_Header"));
							System.out.println("Test Completed");
					
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
							ReportStatus.fnUpdateResultStatus("QuoteHistoryViewSearchResults", "TC_QTH03", ReportStatus.strMethodName, 1, browser);
							throw new RuntimeException(e);
						}
			            finally {
			            	ReportControl.fnEnableJoin();
							ReportStatus.fnUpdateResultStatus("QuoteHistoryViewSearchResults", "TC_QTH03", ReportStatus.strMethodName, counter, browser);
							fnCloseTest();
							ReportControl.fnNextTestJoin(nextTestJoin);
						}
					}

}
