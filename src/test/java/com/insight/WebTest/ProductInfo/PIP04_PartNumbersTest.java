package com.insight.WebTest.ProductInfo;

import com.insight.Lib.*;
import com.insight.accelerators.ReportControl;
import com.insight.accelerators.TestEngineWeb;
import com.insight.googledrive.ReportStatus;
import com.insight.utilities.TestUtil;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.util.Hashtable;

public class PIP04_PartNumbersTest extends ProductDisplayInfoLib{

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
		public void TC_PIP04(int StartRow,String EndRow, boolean nextTestJoin) throws Throwable {
			int counter = 0;
			try {
				int intStartRow = StartRow;
				int intEndRow = ReportControl.fnGetEndRowCunt(EndRow, "PIP04_PartNumbers", TestData, "Web_Product_Info");
				for (int intCounter = intStartRow; intCounter <= intEndRow; intCounter++) {
					try {
						counter = intCounter;
						fnOpenTest();
						ReportStatus.fnDefaultReportStatus();
						ReportControl.intRowCount = intCounter;
						Hashtable<String, String> data = TestUtil.getDataByRowNo("PIP04_PartNumbers", TestData,
								"Web_Product_Info", intCounter);
						TestEngineWeb.reporter.initTestCaseDescription("PartNumbers");

						commonLib.clickOnInsightLogoOnHomePage();
						// Login to CMT
						cmtLib.loginToCMT(data.get("Header"));
						cmtLib.searchForWebGroup(data.get("WebGrp"));
						cmtLib.clickOnTheWebGroup(data.get("WebGrp_Name"));
						cmtLib.hoverOnManageWebGroupsAndSelectOptions(data.get("Manage_Web_Grp_Options"));
						cmtLib.searchForaUserAndSelect(data.get("LnameEmailUname"), data.get("ContactName"));

						// Login as to UAT
						cmtLib.loginAsAdminCMT();
						cmtLib.loginVerification(data.get("ContactName"));

						// search for a product
						searchLib.searchInHomePage(data.get("SearchText"));
						Thread.sleep(4000);
						searchLib.removeTheFilterForInStockOnly(data.get("In_Stock"));
						// verifying manufacturer number in product details page
						verifyTheManufacturerNumberInProductDetailsPage(data.get("SearchText"));
						// verifying manufacturer number in overview tab product details page.
						verifyManufacturerNumberInOverviewTab(data.get("SearchText"));

						// verify accessories part number in product details page
						clickOnAccessoriesTabInProductDetailsPage();
						verifyMfrpartInAccessorirs();
						
						// verify warranties part number in product details page
						clickOnWarrantiesTabOnProductDetailsPage();
						verifyMfrpartInWarrantiesTab();

						commonLib.clickOnInsightLogoOnHomePage();
						// navigate to company standards and select product group
						commonLib.clickOnAccountToolsMenuName(data.get("toolsMenuName"));
						commonLib.clickOnAccountToolsMenuDropDown(data.get("toolsMenuName"), data.get("dropDown"));
						commonLib.clickOnProductGrpInCompanyStandard(data.get("productGroup"), data.get("productName"));

						// Verify the part number and description in company standards screen
						verifyProductDescAndPartNumberInCompanyStandards();
						// Logout
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
					ReportStatus.fnUpdateResultStatus("PIP04_PartNumbers", "TC_PIP04", ReportStatus.strMethodName, 1, browser);
					throw new RuntimeException(e);
				}
		        finally {
		        	ReportControl.fnEnableJoin();
					ReportStatus.fnUpdateResultStatus("PIP04_PartNumbers", "TC_PIP04", ReportStatus.strMethodName, counter, browser);
					fnCloseTest();
					ReportControl.fnNextTestJoin(nextTestJoin);
				}
			}

		}

