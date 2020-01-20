package com.insight.WebTest.ProductInfo;

import com.insight.Lib.*;
import com.insight.accelerators.ReportControl;
import com.insight.accelerators.TestEngineWeb;
import com.insight.googledrive.ReportStatus;
import com.insight.utilities.TestUtil;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.util.Hashtable;

public class PIP01_AvailabilityTest extends ProductDisplayInfoLib{

	CMTLib cmtLib = new CMTLib();
	CommonLib commonLib = new CommonLib();
	CartLib cartLib = new CartLib();
	SearchLib searchLib = new SearchLib();
	ProductDetailLib prodDetailsLib=new ProductDetailLib();
	OrderLib orderLib=new OrderLib();
	   
	    // #############################################################################################################
		// #       Name of the Test         :  CAN05_MenuSearch
		// #       Migration Author         :  Cigniti Technologies
		// #
		// #       Date of Migration        : October 2019
		// #       DESCRIPTION              : This method is to verify MenuSearch for canada
		// #       Parameters               : StartRow ,EndRow , nextTestJoin
		// #
		// ###############################################################################################################
		@Parameters({ "StartRow", "EndRow", "nextTestJoin" })
		@Test
		public void TC_PIP01(int StartRow,String EndRow, boolean nextTestJoin) throws Throwable {
		int counter = 0;
		try {
			int intStartRow = StartRow;
			int intEndRow = ReportControl.fnGetEndRowCunt(EndRow, "PIP01_Availability", TestData, "Web_Product_Info");
			for (int intCounter = intStartRow; intCounter <= intEndRow; intCounter++) {
				try {
					counter = intCounter;
					fnOpenTest();
					ReportStatus.fnDefaultReportStatus();
					ReportControl.intRowCount = intCounter;
					Hashtable<String, String> data = TestUtil.getDataByRowNo("PIP01_Availability", TestData,
							"Web_Product_Info", intCounter);
					TestEngineWeb.reporter.initTestCaseDescription("Availability");

					cmtLib.loginToCMT(data.get("Header"));
					cmtLib.searchForWebGroup(data.get("WebGrp"));
					cmtLib.clickOnTheWebGroup(data.get("WebGrp_Name"));
					cmtLib.hoverOnManageWebGroupsAndSelectOptions(data.get("Manage_Web_Grp_Options"));
					cmtLib.searchForaUserAndSelect(data.get("LnameEmailUname"), data.get("ContactName"));

					// Login as to UAT
					cmtLib.loginAsAdminCMT();
					cmtLib.loginVerification(data.get("ContactName"));

					// search for a product and verify the stock number in the
					// search
					// results and product details page > Workstations
					searchLib.searchInHomePage(data.get("SearchText1"));
					searchLib.verifyBreadCrumbInSearchResultsPage(data.get("SearchText1"));
					verifyProductStockNumberInSearchResultsPage();
					String StockNumber1 = getStockNumebrOfFirstProductInSearchResults();
					cartLib.selectFirstProductDisplay();
					verifyStockNumberInProductDetailsPage(StockNumber1);
                    Thread.sleep(3000);
					// Search for another product
					searchLib.searchInHomePage(data.get("SearchText2"));
					searchLib.verifyBreadCrumbInSearchResultsPage(data.get("SearchText2"));
					Thread.sleep(3000);
					verifyProductStockNumberInSearchResultsPage();
					String StockNumber2 = getStockNumebrOfFirstProductInSearchResults();
					Thread.sleep(3000);
					cartLib.selectFirstProductDisplay();
					Thread.sleep(3000);
					verifyStockNumberInProductDetailsPage(StockNumber2);

					commonLib.clickOnInsightLogoOnHomePage();
					// navigate to company standards and select product group
					commonLib.clickOnAccountToolsMenuName(data.get("toolsMenuName"));
					commonLib.clickOnAccountToolsMenuDropDown(data.get("toolsMenuName"), data.get("dropDown"));
					commonLib.clickOnProductGrpInCompanyStandard(data.get("productGroup"), data.get("productName"));

					// Verifying stock availability in company standards
					verifyStockNumberInCompanyStandardsProductGroup();

					// Log out
					commonLib.clickLogOutLink(data.get("Logout"));
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
				ReportStatus.fnUpdateResultStatus("PIP01_Availability", "TC_PIP01", ReportStatus.strMethodName, 1, browser);
				throw new RuntimeException(e);
			}
	        finally {
	        	ReportControl.fnEnableJoin();
				ReportStatus.fnUpdateResultStatus("PIP01_Availability", "TC_PIP01", ReportStatus.strMethodName, counter, browser);
				fnCloseTest();
				ReportControl.fnNextTestJoin(nextTestJoin);
			}
		}

	}

