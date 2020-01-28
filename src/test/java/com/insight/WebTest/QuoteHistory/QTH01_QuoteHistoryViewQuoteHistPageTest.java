package com.insight.WebTest.QuoteHistory;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.insight.Lib.CMTLib;
import com.insight.Lib.CanadaLib;
import com.insight.Lib.CartLib;
import com.insight.Lib.CommonLib;
import com.insight.Lib.InvoiceHistoryLib;
import com.insight.Lib.MarriottIntlCorpLib;
import com.insight.Lib.OrderLib;
import com.insight.Lib.QuoteHistoryLib;
import com.insight.Lib.SearchLib;
import com.insight.accelerators.ReportControl;
import com.insight.accelerators.TestEngineWeb;
import com.insight.googledrive.ReportStatus;
import com.insight.utilities.TestUtil;

public class QTH01_QuoteHistoryViewQuoteHistPageTest extends QuoteHistoryLib {
	// #############################################################################################################
		// # Name of the Test :TC_QTH01QuoteHistoryViewQuoteHistPage
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
		public void TC_QTH01(int StartRow, String EndRow, boolean nextTestJoin) throws Throwable {
			int counter = 0;
			try {
									
						int intStartRow = StartRow;
						int intEndRow = ReportControl.fnGetEndRowCunt(EndRow, "TC_QTH01QuoteHistoryViewQuoteHistPage", TestDataInsight, "Quote_History");
						for (int intCounter = intStartRow; intCounter <= intEndRow; intCounter++) {
							try {
								
								counter = intCounter;
								fnOpenTest();
								ReportStatus.fnDefaultReportStatus();
								ReportControl.intRowCount = intCounter;
								Hashtable<String, String> data = TestUtil.getDataByRowNo("TC_QTH01QuoteHistoryViewQuoteHistPage", TestDataInsight, "Quote_History", intCounter);
								TestEngineWeb.reporter.initTestCaseDescription("QuoteHistoryViewQuoteHistPage");

 List<String> quotedetails = new ArrayList<>();
						CMTLib cmtLib = new CMTLib();
						SearchLib searchLib = new SearchLib();
						OrderLib orderLib=new OrderLib();
						CanadaLib canadaLib=new CanadaLib();
						QuoteHistoryLib QHL = new QuoteHistoryLib();
						CartLib cartLib=new CartLib();
						InvoiceHistoryLib invoiceHistoryLib = new InvoiceHistoryLib();
						MarriottIntlCorpLib marriottIntlCorpLib=new MarriottIntlCorpLib();
						CommonLib commonLib = new CommonLib();
						cmtLib.loginToCMTSearchWebGrpAndUser(data.get("Header"), data.get("WebGrp"),
								data.get("LnameEmailUname"), data.get("ContactName"));
						cmtLib.setPermissions(data.get("Menu_Name"), data.get("Set_Permission1"));
						cmtLib.setPermissionsToDisable(data.get("Menu_Name"), data.get("Set_Permission2"));
						cmtLib.clickUpdateUser();
						// Login As to Web UAT
						cmtLib.loginAsAdminCMT();
						cmtLib.loginVerification(data.get("ContactName"));
						// Select First Product and Add to cart
						searchLib.searchInHomePage(data.get("SearchItem"));
						cartLib.selectFirstProductDisplay();
						commonLib.addToCartAndVerify();
						orderLib.continueToCheckOutOnAddCart();	
						cmtLib.clickSaveAsQuote();	
						cmtLib.getQuoteNameandReferenceNumber();
						canadaLib.clickOnSideMenuSelectAccountToolOptions(data.get("Tools_Menu"),
								data.get("Tools_Menu_DD"));
						verifyQuoteHistoryPageOpened();
						invoiceHistoryLib.clickOnAdvancedSearch();					// Perform Quote Search						
						clickOnAdvancedSearchSearchButton();
						verifyNoRecords();
						commonLib.clickLogOutLink(data.get("Logout_Header"));	
						cmtLib.navigateBackToCMT();
						cmtLib.setPermissions(data.get("Menu_Name"), data.get("Set_Permission2"));
						cmtLib.setPermissions(data.get("Menu_Name"), data.get("Set_Permission3"));
						cmtLib.clickUpdateUser();
						cmtLib.setPermissionsToDisable(data.get("Menu_Name"), data.get("Set_Permission2"));
						
						cmtLib.clickUpdateUser();
						cmtLib.loginAsAdminCMT();
						cmtLib.loginVerification(data.get("ContactName"));
						canadaLib.clickOnSideMenuSelectAccountToolOptions(data.get("Tools_Menu"),
								data.get("Tools_Menu_DD"));
						verifyQuoteHistoryPageOpened();
						invoiceHistoryLib.clickOnAdvancedSearch();					// Perform Quote Search						
						clickOnAdvancedSearchSearchButton();
						verifyNoRecords();
						searchLib.searchInHomePage(data.get("SearchItem"));
						cartLib.selectFirstProductDisplay();
						commonLib.addToCartAndVerify();
						orderLib.continueToCheckOutOnAddCart();	
						cmtLib.clickSaveAsQuote();	
						quotedetails = cmtLib.getQuoteNameandReferenceNumber();
						
						canadaLib.clickOnSideMenuSelectAccountToolOptions(data.get("Tools_Menu"),
								data.get("Tools_Menu_DD"));	 
						verifyQuoteHistoryPageOpened();
						 
						quoteNumberInTable(quotedetails.get(0));	
						ClickFirstQuoteNumber();
						commonLib.clickLogOutLink(data.get("Logout_Header"));
						cmtLib.navigateBackToCMT();
						cmtLib.hoverOverMasterGroupAndSelectChangeGrp();
						cmtLib.searchForWebGroup(data.get("WebGrp2"));
						commonLib.clickOnWebGroupNumber(data.get("WebGrp2"));
						cmtLib.verifyManageWebGroupSettings();
						cmtLib.hoverOverManagecurrentWebGrp();
						cmtLib.searchUsers(data.get("LnameEmailUname2"));
						cmtLib.verifyUserandClick(data.get("ContactName2"));
						cmtLib.verifyDashboard();
						cmtLib.clickUpdateUser();
						cmtLib.setPermissions(data.get("Menu_Name"), data.get("Set_Permission2"));
						cmtLib.setPermissions(data.get("Menu_Name"), data.get("Set_Permission3"));
						cmtLib.clickUpdateUser();
						cmtLib.setPermissionsToDisable(data.get("Menu_Name"), data.get("Set_Permission2"));
						
						cmtLib.clickUpdateUser();
						cmtLib.loginAsAdminCMT();
						
						cmtLib.loginVerification(data.get("ContactName"));
						marriottIntlCorpLib.getandVerifyWebGroupName(data.get("webgrpName"));
						canadaLib.clickOnSideMenuSelectAccountToolOptions(data.get("Tools_Menu"),
								data.get("Tools_Menu_DD"));
						
						verifyQuoteHistoryPageOpened();
						invoiceHistoryLib.clickOnAdvancedSearch();	
						QHL.SelectAccountdropdownoption(data.get("AccountOption"));
						clickOnAdvancedSearchSearchButton();
						VerifyQuoteDetailsunderQuoteSearch();
						SelectWebGroupfromdd("Briana");
						canadaLib.clickOnSideMenuSelectAccountToolOptions(data.get("Tools_Menu"),
								data.get("Tools_Menu_DD"));
						invoiceHistoryLib.clickOnAdvancedSearch();
						QHL.SelectAccountdropdownoption(data.get("AccountOption"));
						clickOnAdvancedSearchSearchButton();
						VerifyQuoteDetailsunderQuoteSearch();
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
				ReportStatus.fnUpdateResultStatus("QTH01QuoteHistoryViewQuoteHistPage", "TC_USM08", ReportStatus.strMethodName, 1, browser);
				throw new RuntimeException(e);
			}
            finally {
            	ReportControl.fnEnableJoin();
				ReportStatus.fnUpdateResultStatus("QTH01QuoteHistoryViewQuoteHistPage", "TC_USM08", ReportStatus.strMethodName, counter, browser);
				fnCloseTest();
				ReportControl.fnNextTestJoin(nextTestJoin);
			}
		}

}
