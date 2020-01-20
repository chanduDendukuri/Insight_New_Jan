package com.insight.WebTest.Search;

import java.util.Hashtable;

import com.insight.Lib.*;
import com.insight.accelerators.ReportControl;
import com.insight.accelerators.TestEngineWeb;
import com.insight.googledrive.ReportStatus;
import com.insight.utilities.TestUtil;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class SER10_SearchResultsTest extends SearchLib {

	ProductDisplayInfoLib prodInfoLib = new ProductDisplayInfoLib();
	CMTLib cmtLib = new CMTLib();
	

	    // #############################################################################################################
		// #    Name of the Test         : SER10_SearchResults
		// #    Migration Author         : Cigniti Technologies
		// #
		// #    Date of Migration        : August 2019
		// #    DESCRIPTION              : This method is to perform search operations in the Search results screen using filters.
		// #    Parameters               : StartRow ,EndRow , nextTestJoin
		// #
		// ###############################################################################################################

	@Parameters({ "StartRow", "EndRow", "nextTestJoin" })
	@Test
	public void Tc_SER10(int StartRow, String EndRow, boolean nextTestJoin) throws Throwable {
		int counter=0;
		try {
			int intStartRow = StartRow;
			int intEndRow = ReportControl.fnGetEndRowCunt(EndRow, "SER10_SearchResults", TestData, "Web_Search");
			for (int intCounter = intStartRow; intCounter <= intEndRow; intCounter++) {
				try {
					// initializing libraries and testdata
					ReportStatus.fnDefaultReportStatus();
					ReportControl.intRowCount = intCounter;
					Hashtable<String, String> data = TestUtil.getDataByRowNo("SER10_SearchResults", TestData,"Web_Search", intCounter);
					TestEngineWeb.reporter.initTestCaseDescription("SearchResults");
				
					// Test Steps execution
					fnOpenTest();
					searchInHomePage(data.get("SearchText"));
					verifyTheResultsForSearchTerm(data.get("SearchText"));
					searchInHomePage(data.get("SearchText2"));
					verifyTheResultsForSearchTerm(data.get("SearchText2"));
					int searchCount =getSearchResultsCount();
					filterSelectionInProductsSearchPage(data.get("StockFilter"));
					int in_stockCount=getSearchResultsCount();
					assertTrue(searchCount>=in_stockCount, "Stock filter Search Results count less than Pervious count");
					///	Remove the In-stock option from Breadcrumb
					removeTheFilterForInStockOnly(data.get("In_Stock"));
					int in_stock_Removed=getSearchResultsCount();
					assertTrue(searchCount==in_stock_Removed, "Search Results count same as initial count");
					
					// Narrow Down by  Manufacture
					filterSelectionInProductsSearchPage(data.get("MnfrFilter"));
					int mfr_Count=getSearchResultsCount();
					assertTrue(searchCount>=mfr_Count, "manufacturer filter Search Results count less than Pervious count");
					
					// Remove manufacturer option from Breadcrumb
					removeTheFilter(data.get("MnfrFilter"));
					
					///	Narrow Down by  Keyword
					searchProductInProductDisplayPage(data.get("Search_Item"));
					int keyword_search_Count=getSearchResultsCount();
					assertTrue(searchCount>=keyword_search_Count, "keyword Search Results count less than Pervious count");
					
					// Remove keyword search from Breadcrumb and compare results
					removeTheFilter(data.get("Search_Item"));
					int keywordSearch_Removed=getSearchResultsCount();
					assertTrue(searchCount==keywordSearch_Removed, "Search Results count same as initial count");
					
					// prodInfoLib.enterPriceDetailsFilters(data.get("Min_Price"), data.get("Max_Price"));

					/*
					 * searchProductInProductDisplayPage(data.get("ProductName"));
					 * removeTheFilter(data.get("ProductName"));
					 */

					/*
					 * searchInHomePage(data.get("SearchText_adobe"));
					 * verifyTheResultsForSearchTerm(data.get("SearchText_adobe"));
					 * prodInfoLib.selectFirstProductAndReturnBack();
					 * prodInfoLib.selectFirstProductImageAndReturnBack();
					 * prodInfoLib.verifyTheSearchResultsDisplayed();
					 * prodInfoLib.selectSortByOptions(data.get("Sort_By"));
					 */
					
					/// 	Search - special character search validation - While logged out, search on a part that has a "?" in it. Added as part of US324
					Thread.sleep(2000);
					searchInHomePage(data.get("Search_Number"));
					prodInfoLib.verifyTheManufacturerNumberInProductDetailsPage(data.get("Search_Number"));
				}

				catch (Exception e) {
					ReportStatus.blnStatus = false;
					//gErrorMessage = e.getMessage();
					gTestStatus = false;
				}
				
			}
		} catch (Exception e) {
			e.printStackTrace();
			ReportStatus.blnStatus = false;
			//gErrorMessage = e.getMessage();
			gTestStatus = false;
			ReportStatus.fnUpdateResultStatus("SearchResults", "SER10", ReportStatus.strMethodName,
					1, browser);
			throw new RuntimeException(e);
		}

		finally {
        	ReportControl.fnEnableJoin();
			ReportStatus.fnUpdateResultStatus("SearchResults", "SER10", ReportStatus.strMethodName, counter, browser);
			fnCloseTest();
			ReportControl.fnNextTestJoin(nextTestJoin);
		}
	}

}
