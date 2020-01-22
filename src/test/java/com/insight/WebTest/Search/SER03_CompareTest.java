package com.insight.WebTest.Search;

import java.util.Hashtable;

import com.insight.Lib.*;
import com.insight.accelerators.ReportControl;
import com.insight.accelerators.TestEngineWeb;
import com.insight.googledrive.ReportStatus;
import com.insight.utilities.TestUtil;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class SER03_CompareTest extends SearchLib {

	ProductDisplayInfoLib prodInfoLib = new ProductDisplayInfoLib();
	CMTLib cmtLib = new CMTLib();
	CartLib cartLib=new CartLib();
	OrderLib orderLib=new OrderLib();

	    //#############################################################################################################
		//#		Name of the Test			:	   SER03_Compare
		//#		Migration Author 	        : 	   Cigniti Technologies
		//#		
		//#		Date of Migration			:      August 2019
		//#		DESCRIPTION 				:	   Purpose of this test method is to verify the compare functionality in the products display page.
		//#		Parameters                  :      StartRow ,EndRow , nextTestJoin
		//#      												
		//###############################################################################################################

	@Parameters({ "StartRow", "EndRow", "nextTestJoin" })
	@Test
	public void TC_SER03(int StartRow, String EndRow, boolean nextTestJoin) throws Throwable {
		int counter=0;
		try {
			int intStartRow = StartRow;
			int intEndRow = ReportControl.fnGetEndRowCunt(EndRow, "SER03_Compare", TestData, "Web_Search");
			for (int intCounter = intStartRow; intCounter <= intEndRow; intCounter++) {
				try {
					// initializing libraries and test data
					ReportStatus.fnDefaultReportStatus();
					ReportControl.intRowCount = intCounter;
					Hashtable<String, String> data = TestUtil.getDataByRowNo("SER03_Compare", TestData,"Web_Search", intCounter);
					TestEngineWeb.reporter.initTestCaseDescription("Compare");

					// Test Steps execution

					fnOpenTest();
					/*
					 * navigateToProductSearchResultsAndSearchProduct(data.get("HeaderName"),
					 * data.get("HeaderList"), data.get("ProductType"), data.get("ProductName"));
					 */
					// search Monitors
					searchInHomePage( data.get("ProductName1"));
					verifyBreadCrumbInSearchResultsPage(data.get("ProductName1"));
					String productName=prodInfoLib.clickOnCompareSimilarLink(data.get("Product_Number"));
					verifyTheProductNameInCompareSimilarProductsPage(productName);
					// Similar products verification
					verifySimilarProductsExists();
					navigateToBackPage();
					
					// search Thinkpads
					searchInHomePage( data.get("ProductName2"));
					verifyBreadCrumbInSearchResultsPage(data.get("ProductName2"));
					int itemscount = Integer.valueOf(data.get("Items_Count"));
					
					// 3 Items added to compare list 
					clickOnAddToMyCompareListLinkandVerify(itemscount);
					
					// search Workstations
					searchInHomePage( data.get("ProductName3"));
					verifyBreadCrumbInSearchResultsPage(data.get("ProductName3"));
					int itemNum = Integer.valueOf(data.get("Items_Count"));
					clickOnAddToMyCompareListLinkIndividually(itemNum-2);
					String compareNum=verifyCompareList();
					int compareItemsNum=Integer.parseInt(compareNum);
					Thread.sleep(3000);
					clickOnComparedItemsLink();
					verifyComparedProductsExists(compareItemsNum);
					clickOnCloseIconInCompareProductsPage();
					Thread.sleep(3000);
					verifyComparedProductsExists(compareItemsNum-1);
					// Add to cart
					prodInfoLib.addToCart();
					orderLib.continueToCheckOutOnAddCart();
				
				} catch (Exception e) {
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
			ReportStatus.fnUpdateResultStatus("Compare", "SER03", ReportStatus.strMethodName,1, browser);
			throw new RuntimeException(e);
		}

		finally {
        	ReportControl.fnEnableJoin();
			ReportStatus.fnUpdateResultStatus("Compare", "SER03", ReportStatus.strMethodName, counter, browser);
			fnCloseTest();
			ReportControl.fnNextTestJoin(nextTestJoin);
		}
	}

}
