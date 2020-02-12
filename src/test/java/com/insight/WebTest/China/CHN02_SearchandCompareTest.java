package com.insight.WebTest.China;

import java.util.Hashtable;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.insight.Lib.CMTLib;
import com.insight.Lib.CanadaLib;
import com.insight.Lib.CartLib;
import com.insight.Lib.ChinaLib;
import com.insight.Lib.CommonLib;
import com.insight.Lib.OrderLib;
import com.insight.Lib.ProductDetailLib;
import com.insight.Lib.ProductDisplayInfoLib;
import com.insight.Lib.SearchLib;
import com.insight.Lib.ShipBillPayLib;
import com.insight.accelerators.ReportControl;
import com.insight.accelerators.TestEngineWeb;
import com.insight.googledrive.ReportStatus;
import com.insight.utilities.TestUtil;

public class CHN02_SearchandCompareTest extends ChinaLib{

	CMTLib cmtLib = new CMTLib();
	CommonLib commonLib = new CommonLib();
	CartLib cartLib = new CartLib();
	SearchLib searchLib = new SearchLib();
	ProductDetailLib prodDetailsLib=new ProductDetailLib();
	OrderLib orderLib=new OrderLib();
	ProductDisplayInfoLib pipLib=new ProductDisplayInfoLib();
	ShipBillPayLib sbpLib=new ShipBillPayLib();
	CanadaLib canadaLib=new CanadaLib();
	   
	    // #############################################################################################################
		// #       Name of the Test         :  CHN02_SearchandCompareTest
		// #       Migration Author         :  Cigniti Technologies
		// #
		// #       Date of Migration        : October 2019
		// #       DESCRIPTION              : This Test is used to search and compare test in canada
		// #       Parameters               : StartRow ,EndRow , nextTestJoin
		// #
		// ###############################################################################################################
		@Parameters({ "StartRow", "EndRow", "nextTestJoin" })
		@Test
		public void TC_CHN02(int StartRow,String EndRow, boolean nextTestJoin) throws Throwable {
			int counter = 0;
			try {
				int intStartRow = StartRow;
				int intEndRow = ReportControl.fnGetEndRowCunt(EndRow, "CHN02_SearchandCompare", TestData, "China");
				for (int intCounter = intStartRow; intCounter <= intEndRow; intCounter++) {
					try {
						fnOpenTest();
						ReportStatus.fnDefaultReportStatus();
						ReportControl.intRowCount = intCounter;
						Hashtable<String, String> data = TestUtil.getDataByRowNo("CHN02_SearchandCompare", TestData, "China", intCounter);
						TestEngineWeb.reporter
								.initTestCaseDescription("SearchandCompare");
						reporter.SuccessReport("Iteration Number : ",
								"**************Iteration Number::  " + intCounter + " For:: " + data.get("LoginName") + " ::and:: "
										+ data.get("Password") + " To Validate::" + data.get("errorMessage") + "  **************","");

						// select language and country
						selectLanguageOnHomePage(data.get("Country"), data.get("Language"));
						canadaLib.verifyCountry("cn");
						navigateTo(data.get("URL"));
						verify_url(WebDriver, data.get("URL"));
						// Search for a product
						searchLib.searchInHomePage(data.get("SearchText"));
						searchLib.verifyBreadCrumbInSearchResultsPage(data.get("SearchText"));
						Thread.sleep(3000);
						// Verify Best Match option
						canadaLib.verifySortOption(data.get("Sort_Option"));
						// select Filter HP INC
						scrollUp();
						searchLib.filterSelectionInProductsSearchPage(data.get("Manufacturer"));
						Thread.sleep(3000);
						verifyManufacturerOnSearchResultsPage(data.get("Manufacturer"));
						Thread.sleep(3000);
						String firstProdPrice=pipLib.getFirtProductListPrice();
                        cartLib.selectFirstProductDisplay();
                        pipLib.verifyThePriceInProdDetailsPage(firstProdPrice); // Verifying price in product details page
						// back to results
                        pipLib.backToResultsProductDetailsPage();
                        Thread.sleep(3000);
                     // Adding products to compare list
            			int itemscount = Integer.valueOf(data.get("Items_Count")); // 3 items to compare list
        				searchLib.clickOnAddToMyCompareListLink(itemscount);
        				//Verify  products added to list
        				Thread.sleep(3000);
        				String compareNum=searchLib.verifyCompareList();
        				// click compare items link
        				searchLib.clickOnComparedItemsLink();
        				 Thread.sleep(3000);
        				// verify products added in compare list
        				verifyCompareProductsPage(itemscount);
        				// Verify new part added
        				Thread.sleep(3000);
        				addAnotherPartInCompareProductsPage(data.get("Part_Number"));
        				verifyPartNumberAddedInCompareProductListPage(data.get("Part_Number"));
        				// Verify number of products displayed
        				verifyCompareProductsPage(itemscount+1);
        				// Remove first product
        				Thread.sleep(3000);
        				searchLib.clickOnCloseIconInCompareProductsPage();
        				// verify product count after removal
        				Thread.sleep(3000);
        				// Verify number of products displayed
        				verifyCompareProductsPage(itemscount);
        				// add to cart 
        				pipLib.addToCart();
        				orderLib.continueToCheckOutOnAddCart();
					} catch (Exception e) {
						ReportStatus.blnStatus = false;
					//	gErrorMessage = e.getMessage();
						gTestStatus = false;
					}
					
				}
			} catch (Exception e) {
				e.printStackTrace();
				ReportStatus.blnStatus = false;
			//	gErrorMessage = e.getMessage();
				gTestStatus = false;
				ReportStatus.fnUpdateResultStatus("SearchandCompare", "CHN02", ReportStatus.strMethodName, 1, browser);
				throw new RuntimeException(e);
			}
			finally {
	        	ReportControl.fnEnableJoin();
				ReportStatus.fnUpdateResultStatus("SearchandCompare", "SearchandCompare", ReportStatus.strMethodName, counter, browser);
				fnCloseTest();
				ReportControl.fnNextTestJoin(nextTestJoin);
			}
			
		}

}
