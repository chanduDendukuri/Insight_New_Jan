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
	CanadaLib canadaLib=new CanadaLib();
	

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
					// Multifunction Printer
					searchInHomePage(data.get("SearchItem"));
					verifyTheResultsForSearchTerm(data.get("SearchItem"));
					canadaLib.verifySortOption(data.get("Sort_By")); // Best Match
					
					// Servers
					searchInHomePage(data.get("SearchText2"));
					verifyTheResultsForSearchTerm(data.get("SearchText2"));
					removeTheFilterForInStockOnly(data.get("In_Stock"));
					String initialCount=getProductCount();
					
					// Apply in stock filter
					filterSelectionInProductsSearchPage(data.get("StockFilter"));
					String stockFilterCount=getProductCount();
                    if(Integer.valueOf(stockFilterCount)<Integer.valueOf(initialCount)) {
                    	reporter.SuccessReport("Verify product count", "Search Results count less than initial count", "Count: "+stockFilterCount);
					}else {
						reporter.failureReport("Verify product count", "Search Results count not less than initial count", "Count: "+stockFilterCount, driver);
					}
					
					removeTheFilterForInStockOnly(data.get("In_Stock"));
					// servers verification bread crumb
					verifyBreadCrumbInSearchResultsPage(data.get("SearchText2"));
					String finalCount=getProductCount();
					
					if(initialCount.equals(finalCount)) {
						reporter.SuccessReport("Verify product count", "Search Results count same as initial count", "Count: "+finalCount);
					}else {
						reporter.failureReport("Verify product count", "Search Results count not same as initial count", "Count: "+finalCount, driver);
					}
					
					// select manufacturer - HP INC
					selectManufacturerFiter(data.get("Manufacturer1"),data.get("Mfr_Heading1"));
					verifyFilterBreadCrumb(data.get("Manufacturer1"));
					String mfrCount=getProductCount();
					if(Integer.valueOf(mfrCount)<Integer.valueOf(finalCount)) {
                    	reporter.SuccessReport("Verify product count", "Search Results count less than previous count", "Count: "+mfrCount);
					}else {
						reporter.failureReport("Verify product count", "Search Results count not less than previous count", "Count: "+mfrCount, driver);
					}
					// removeHP INC filter
					Thread.sleep(2000); 
					scrollUp();
					 Thread.sleep(3000);
					 removeTheFilter(data.get("Manufacturer1"));
					 String mfr1_RemovedCount=getProductCount();
					
					 if(mfr1_RemovedCount.equals(initialCount)) {
							reporter.SuccessReport("Verify product count", "Search Results count same as as the initial count", "Count: "+initialCount);
						}else {
							reporter.failureReport("Verify product count", "Search Results count not same as as the initial count", "Count: "+initialCount, driver);
						}
					
					// select manufacturer -LENOVO
					selectManufacturerFiter(data.get("Manufacturer2"),data.get("Mfr_Heading1"));
					verifyFilterBreadCrumb(data.get("Manufacturer2"));
					String Mfr2ProductsCount=getProductCount();
					
					if(Integer.valueOf(Mfr2ProductsCount)<Integer.valueOf(initialCount)) {
                    	reporter.SuccessReport("Verify product count", "Search Results count less than previous count", "Count: "+Mfr2ProductsCount);
					}else {
						reporter.failureReport("Verify product count", "Search Results count not less than previous count", "Count: "+Mfr2ProductsCount, driver);
				 }
					
					searchProductInProductDisplayPage(data.get("Keyword_Search"));  // core 
					verifyFilterBreadCrumb(data.get("Keyword_Search"));
					String keywordProductsCount=getProductCount();
					
					if(Integer.valueOf(keywordProductsCount)<Integer.valueOf(Mfr2ProductsCount)) {
                    	reporter.SuccessReport("Verify product count", "Search Results count less than previous count", "Count: "+keywordProductsCount);
					}else {
						reporter.failureReport("Verify product count", "Search Results count not less than previous count", "Count: "+keywordProductsCount, driver);
				   }
					
					// remove core keyword filter
					 removeTheFilter(data.get("Keyword_Search"));
					 String coreRemovedProductsCount=getProductCount();
					
					 if(coreRemovedProductsCount.equals(Mfr2ProductsCount)) {
							reporter.SuccessReport("Verify product count", "Search Results count same as as the Pervious count", "Count: "+coreRemovedProductsCount);
						}else {
							reporter.failureReport("Verify product count", "Search Results count not same as as the Pervious count", "Count: "+coreRemovedProductsCount, driver);
						}
					 // remove lenovo
					 removeTheFilter(data.get("Manufacturer2"));
					 String Mfr2_ReovedCount=getProductCount();
					 
					 if(Mfr2_ReovedCount.equals(initialCount)) {
							reporter.SuccessReport("Verify product count", "Search Results count same as as the Initial count", "Count: "+initialCount);
						}else {
							reporter.failureReport("Verify product count", "Search Results count not same as as the initial count", "Count: "+initialCount, driver);
						}
					 
					 searchInHomePage(data.get("Partnumber"));
					 prodInfoLib.verifyTheManufacturerNumberInProductDetailsPage(data.get("Partnumber"));
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
