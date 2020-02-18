package com.insight.WebTest.Canada;

import java.util.Hashtable;

import com.insight.Lib.*;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import com.insight.accelerators.ReportControl;
import com.insight.accelerators.TestEngineWeb;
import com.insight.googledrive.ReportStatus;
import com.insight.utilities.TestUtil;

public class CAN18_SearchIncludingFrenchTest extends  CanadaLib{
	
	ChinaLib chinaLib=new ChinaLib();
	SearchLib searchLib = new SearchLib();
	ProductDisplayInfoLib pipLib=new ProductDisplayInfoLib();
	CartLib cartLib=new CartLib();
	CommonLib commonLib=new CommonLib();
	OrderLib orderLib=new OrderLib();
	ProductDetailLib prodLib=new ProductDetailLib();
	CanadaLib canadaLib = new CanadaLib();
	CommonCanadaLib ccp = new CommonCanadaLib();
	
	// #############################################################################################################
	// #       Name of the Test         :  CAN18_SearchIncludingFrench
	// #       Migration Author         :  Cigniti Technologies
	// #
	// #       Date of Migration        : November 2019
	// #       DESCRIPTION              : To Test basic Search Including French
	// #       Parameters               : StartRow ,EndRow , nextTestJoin
	// #
	// ###############################################################################################################
	@Parameters({ "StartRow", "EndRow", "nextTestJoin" })
	@Test
	public void TC_CAN18(int StartRow,String EndRow, boolean nextTestJoin) throws Throwable {

		int counter = 0;
		try {
			int intStartRow = StartRow;
			int intEndRow = ReportControl.fnGetEndRowCunt(EndRow, "CAN18_SearchIncludingFrench", TestDataInsight, "Canada");
			for (int intCounter = intStartRow; intCounter <= intEndRow; intCounter++) {
				try {
					counter = intCounter;
					fnOpenTest();
					ReportStatus.fnDefaultReportStatus();
					ReportControl.intRowCount = intCounter;
					Hashtable<String, String> data = TestUtil.getDataByRowNo("CAN18_SearchIncludingFrench", TestDataInsight,
							"Canada", intCounter);
					TestEngineWeb.reporter.initTestCaseDescription("SearchIncludingFrench");
					chinaLib.selectLanguageOnHomePage(data.get("Country"), data.get("Language"));
					chinaLib.clickOnConnectionslink();
					navigateToApplication("CANADA_FR");
					assertTrue(driver.getCurrentUrl().contains("CA"),"Launched url is CANADA");



		// Search for a product
					searchLib.searchInHomePage(data.get("SearchText"));
					searchLib.verifyBreadCrumbInSearchResultsPage(data.get("SearchText"));
					//searchLib.verifyBreadCrumbInSearchResultsPage("Workstations");
					Thread.sleep(3000);
					// Verify Best Match option
					canadaLib.verifySortOption(data.get("Sort_Option"));
					// select Filter HP INC
					scrollToBottomWithCordinate("520");
					ccp.selectHPINCRadioButton();

					//searchLib.filterSelectionInProductsSearchPage(data.get("Manufacturer"));
					Thread.sleep(3000);
					scrollToBottomWithCordinate("-12000");
					assertTrue(ccp.verifyHPLICBreadCrumbAvailability(),"Availability of HP LIC bread crumb");
					//chinaLib.verifyManufacturerOnSearchResultsPage(data.get("Manufacturer"));
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
					chinaLib.verifyCompareProductsPage(itemscount);
					// Verify new part added
					Thread.sleep(3000);
					chinaLib.addAnotherPartInCompareProductsPage(data.get("Part_Number"));
					//chinaLib.addAnotherPartInCompareProductsPage("30B4S2YU01");
					chinaLib.verifyPartNumberAddedOnFrenchCompareProductListPage(data.get("Part_Number"));
					// Verify number of products displayed
					chinaLib.verifyCompareProductsPage(itemscount+1);
					// Remove first product
					Thread.sleep(3000);
					searchLib.clickOnCloseIconInCompareProductsPage();
					// verify product count after removal
					Thread.sleep(3000);
					// Verify number of products displayed
					chinaLib.verifyCompareProductsPage(itemscount);
					// add to cart
					pipLib.addToCart();
					orderLib.continueToCheckOutOnAddCart();
		//*******************************************************************************************

			} catch (Exception e) {
				ReportStatus.blnStatus = false;
				//	gErrorMessage = e.getMessage();
				gTestStatus = false;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			ReportStatus.blnStatus = false;
		//	gErrorMessage = e.toString();
			gTestStatus = false;
			ReportStatus.fnUpdateResultStatus("SearchIncludingFrench", "TC_CAN18", ReportStatus.strMethodName, 1, browser);
			throw new RuntimeException(e);
		}
		finally {
			ReportControl.fnEnableJoin();
			ReportStatus.fnUpdateResultStatus("SearchIncludingFrench", "TC_CAN18", ReportStatus.strMethodName, counter, browser);
			fnCloseTest();
			ReportControl.fnNextTestJoin(nextTestJoin);
		}
	}
}
