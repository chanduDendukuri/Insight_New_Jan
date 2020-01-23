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
	ChinaLib chinaLib=new ChinaLib();

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
					// search Monitors
					searchInHomePage( data.get("ProductName1"));
					verifyBreadCrumbInSearchResultsPage(data.get("ProductName1"));
					verifysearchResults(data.get("ProductName1"));
					String productName=prodInfoLib.clickOnCompareSimilarLink(data.get("Product_Number"));
					verifyTheProductNameInCompareSimilarProductsPage(productName);
					// Similar products verification
					verifySimilarProductsExists();


					// search Thinkpads
					searchInHomePage( data.get("ProductName2"));
					verifyBreadCrumbInSearchResultsPage(data.get("ProductName2"));
					int itemscount = Integer.valueOf(data.get("Items_Count"));

					// 2 Items added to compare list
					clickOnAddToMyCompareListLink(itemscount);
					Thread.sleep(3000);
					    //Verify  products added to list
					    verifyCompareList();

					    // click compare items link
					    clickOnComparedItemsLink();
					    Thread.sleep(3000);
					    // verify products added in compare list
					    chinaLib.verifyCompareProductsPage(itemscount);
					   
					    // Adding another part
					    chinaLib.addAnotherPartInCompareProductsPage(data.get("Part_Number"));
					    chinaLib.verifyPartNumberAddedInCompareProductListPage(data.get("Part_Number"));
					    // Verify number of products displayed
					    chinaLib.verifyCompareProductsPage(itemscount+1);
					   
					    // search Workstations
					searchInHomePage( data.get("ProductName3"));
					verifyBreadCrumbInSearchResultsPage(data.get("ProductName3"));
					   
					//Verify  products added to list
					    verifyCompareList();
					    // Add one more product to compare list
					    clickOnAddToMyCompareListLink(itemscount-1);
					    clickOnComparedItemsLink();
					    // verify products added in compare list
					    chinaLib.verifyCompareProductsPage(itemscount+2);
					   
					    clickOnCloseIconInCompareProductsPage();
					    chinaLib.verifyCompareProductsPage(itemscount+1);
					   
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
