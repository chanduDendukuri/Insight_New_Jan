package com.insight.WebTest.Search;

import java.util.Hashtable;

import com.insight.Lib.*;
import com.insight.accelerators.ReportControl;
import com.insight.accelerators.TestEngineWeb;
import com.insight.googledrive.ReportStatus;
import com.insight.utilities.TestUtil;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class SER07_MenuSearchTest extends SearchLib {

	ProductDisplayInfoLib prodInfoLib = new ProductDisplayInfoLib();
	CMTLib cmtLib = new CMTLib();

	        // #############################################################################################################
			// #   Name of the Test               : SER07_MenuSearch
			// #   Migration Author               : Cigniti Technologies
			// #
			// #   Date of Migration              : August 2019
			// #   DESCRIPTION                    : This method is to navigate to the Shop All Products / Shop All Brands and
		    // #                                      select the particular category and verify navigations and menus.
			// #   Parameters                     : StartRow ,EndRow , nextTestJoin
			// #
			// ###############################################################################################################	

	@Parameters({ "StartRow", "EndRow", "nextTestJoin" })
	@Test
	public void Tc_SER07(int StartRow, String EndRow, boolean nextTestJoin) throws Throwable {
		int counter=0;
		try {
			int intStartRow = StartRow;
			int intEndRow = ReportControl.fnGetEndRowCunt(EndRow, "SER07_MenuSearch", TestData, "Web_Search");
			for (int intCounter = intStartRow; intCounter <= intEndRow; intCounter++) {
				try {

					ReportStatus.fnDefaultReportStatus();
					ReportControl.intRowCount = intCounter;
					Hashtable<String, String> data = TestUtil.getDataByRowNo("SER07_MenuSearch", TestData,
							"Web_Search", intCounter);
					TestEngineWeb.reporter.initTestCaseDescription("MenuSearch");
					reporter.SuccessReport("Iteration Number : ",
							"**************Iteration Number::  " + intCounter + " For:: " + data.get("LoginName")
									+ " ::and:: " + data.get("Password") + " To Validate::" + data.get("errorMessage")
									+ "  **************","");

					fnOpenTest();
					clickonShopAllButtonsInHeaderList(data.get("HeaderName"), data.get("ShopAllBrands"));
					selectTopBrandsInShopAllBrandsPage(data.get("Brand1"), data.get("Url"));
					
					clickonShopAllButtonsInHeaderList(data.get("HeaderName"), data.get("ShopAllBrands"));
					//explore label verification
					verifyExploreAllBrandsLabel();
					selectTopBrandsInShopAllBrandsPage(data.get("Brand2"),data.get("Url2"));
					
					clickOnSecondaryDDAndSelectListitem(data.get("HeaderName"), data.get("Header_List"));// technology deals
					prodInfoLib.verifyInventoryBlowOutInTechnologyDealsPage();
					
					clickOnSecondaryDDAndSelectListitem(data.get("HeaderName"), data.get("Header_List2"));// software
					// verify software page
					verifyNavigatedBreadCrumb(data.get("Header_List2"));
					
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
			ReportStatus.fnUpdateResultStatus("MenuSearch", "SER07", ReportStatus.strMethodName,
					1, browser);
			throw new RuntimeException(e);
		}

		finally {
        	ReportControl.fnEnableJoin();
			ReportStatus.fnUpdateResultStatus("MenuSearch", "SER07", ReportStatus.strMethodName, counter, browser);
			fnCloseTest();
			ReportControl.fnNextTestJoin(nextTestJoin);
		}
	}

}
