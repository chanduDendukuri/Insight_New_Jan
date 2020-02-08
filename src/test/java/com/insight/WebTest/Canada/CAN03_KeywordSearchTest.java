package com.insight.WebTest.Canada;

import java.util.Hashtable;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.insight.Lib.CMTLib;
import com.insight.Lib.CanadaLib;
import com.insight.Lib.CartLib;
import com.insight.Lib.CommonLib;
import com.insight.Lib.OrderHistoryLib;
import com.insight.Lib.OrderLib;
import com.insight.Lib.ProductDetailLib;
import com.insight.Lib.ProductDisplayInfoLib;
import com.insight.Lib.SearchLib;
import com.insight.Lib.SewpLib;
import com.insight.accelerators.ReportControl;
import com.insight.accelerators.TestEngineWeb;
import com.insight.googledrive.ReportStatus;
import com.insight.utilities.TestUtil;

public class CAN03_KeywordSearchTest extends CanadaLib{

	CMTLib cmtLib = new CMTLib();
	CanadaLib canadaLib=new CanadaLib();
	CommonLib commonLib = new CommonLib();
	CartLib cartLib = new CartLib();
	SearchLib searchLib = new SearchLib();
	ProductDetailLib prodDetailsLib=new ProductDetailLib();
	OrderLib orderLib=new OrderLib();
	ProductDisplayInfoLib pipLib=new ProductDisplayInfoLib();
	ProductDetailLib productDetailLib = new ProductDetailLib();
	OrderHistoryLib orderHistortLib=new OrderHistoryLib();
	ProductDisplayInfoLib prodinfo=new ProductDisplayInfoLib();
	ProductDetailLib prodDetailLib=new ProductDetailLib();
	SewpLib sewpLib=new SewpLib();
	
	    // #############################################################################################################
		// #       Name of the Test         :  SER03_KeywordSearch
		// #       Migration Author         :  Cigniti Technologies
		// #
		// #       Date of Migration        : October 2019
		// #       DESCRIPTION              : This method is to verify Keyword Search for canada
		// #       Parameters               : StartRow ,EndRow , nextTestJoin
		// #
		// ###############################################################################################################
		@Parameters({ "StartRow", "EndRow", "nextTestJoin" })
		@Test
		public <verifyTheResultsForSearchTerm> void TC_CAN03(int StartRow, String EndRow, boolean nextTestJoin) throws Throwable {
			int counter = 0;
			try {
				int intStartRow = StartRow;
				int intEndRow = ReportControl.fnGetEndRowCunt(EndRow, "CAN03_KeywordSearch", TestDataInsight, "Canada");
				for (int intCounter = intStartRow; intCounter <= intEndRow; intCounter++) {
					try {
						fnOpenTest();
						ReportStatus.fnDefaultReportStatus();
						ReportControl.intRowCount = intCounter;
						Hashtable<String, String> data = TestUtil.getDataByRowNo("CAN03_KeywordSearch", TestDataInsight, "Canada",intCounter);
						TestEngineWeb.reporter.initTestCaseDescription("KeywordSearch" );
						
						navigateToApplication("CANADA");;
						
						//	Search on HomePage
						searchLib.searchInHomePage(data.get("SearchText1"));
						searchLib.verifyBreadCrumbInSearchResultsPage(data.get("SearchText1"));
						//verifySortOption(data.get("Sort_Option"));
						Thread.sleep(3000);
						canadaLib.verifySortOption(data.get("sortOption"));

						
						///	Search On Product List Page
						searchLib.searchInHomePage(data.get("SearchText2"));
						searchLib.verifyBreadCrumbInSearchResultsPage(data.get("SearchText2"));
						//verifySortOption(data.get("Sort_Option"));
						Thread.sleep(3000);
						canadaLib.verifySortOption(data.get("sortOption")); // Best Match
					
						//	Search On Product List Page
						searchLib.searchInHomePage(data.get("SearchText3"));
						searchLib.verifyBreadCrumbInSearchResultsPage(data.get("SearchText3"));
						//verifySortOption(data.get("Sort_Option"));
						Thread.sleep(3000);
						canadaLib.verifySortOption(data.get("sortOption"));

						// Login to CMT
						cmtLib.loginToCMT(data.get("Header"));
						cmtLib.searchForWebGroup(data.get("WebGrp"));
						cmtLib.clickOnTheWebGroup(data.get("WebGrp_Name"));
						cmtLib.hoverOnManageWebGroupsAndSelectOptions(data.get("Manage_Web_Grp_Options"));
						cmtLib.searchForaUserAndSelect(data.get("LnameEmailUname"), data.get("contactName"));
						
						// Disable -- custom_catalog;OFF";
					    cmtLib.setPermissionsToDisable(data.get("Menu_Name"), data.get("Set_Permission1"));
					    // Enable Shop by brand
					    cmtLib.setPermissions(data.get("Menu_Name"), data.get("Set_Permission2"));
					    
					    // Login as to UAT
						cmtLib.loginAsAdminCMT();
						//07-02-> Add Login Verification
						cmtLib.loginVerification("User - "+data.get("contactName"));
						String url = driver.getCurrentUrl();
						assertTrue(url.contains("CA"),"You are logged in to Canada URL");
						// Canada Home Page Verification
						///	Search with Microsite pages ex: HP, IBM
						searchLib.searchInHomePage(data.get("SearchText4"));
						searchLib.verifyBreadCrumbInSearchResultsPage(data.get("SearchText4"));
						//searchLib.verifysearchResultsPage();
						//pipLib.selectFirstProductAddToCartAndVerifyCart();
						
						
						///	Search  category ex: WorkStations
						searchLib.searchInHomePage(data.get("SearchText5"));
						searchLib.verifyBreadCrumbInSearchResultsPage(data.get("SearchText5"));
						//sewpLib.clickOnAddtoCart();
						//sewpLib.clickContinueToCheckout();
						//searchLib.verifysearchResultsPage();
						searchLib.searchInHomePage(data.get("SearchText6"));
						/*orderHistortLib.verifySearchResultsAreDisplayed();
						String desc=pipLib.getFirstProdDescription();
						Thread.sleep(3000);
						// Verifying description on product details page 
						pipLib.verifyShortDescriptionOnProductDetailsPage(desc);*/
						prodinfo.verifyTheManufacturerNumberInProductDetailsPage(data.get("SearchText6"));
						// 07-02 Add Product Details verification
						//prodinfo.verifyProductDescAndPartNumberInCompanyStandards();
						prodDetailLib.Getproductdetails();
					
						///	Search Partial Product  ID
						searchLib.searchInHomePage(data.get("SearchText7"));
						searchLib.verifyBreadCrumbInSearchResultsPage(data.get("SearchText7"));
						///	Search Invalid Partial Product  ID
						searchLib.searchInHomePage(data.get("SearchText8"));
						verifyNoResultsFoundMessgeInProductSearchResults();
						
						// 07-02 Add Logout 
						commonLib.clickLogOutLink(data.get("Logout"));
						System.out.println("Test completed");
					} catch (Exception e) {
						ReportStatus.blnStatus = false;
						//gErrorMessage = e.getMessage();
						gTestStatus = false;
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
				ReportStatus.blnStatus = false;
			//	gErrorMessage = e.toString();
				gTestStatus = false;
				ReportStatus.fnUpdateResultStatus("ShipBillPayTax", "TC_CAN01", ReportStatus.strMethodName, 1, browser);
				throw new RuntimeException(e);
			}
	        finally {
	        	ReportControl.fnEnableJoin();
				ReportStatus.fnUpdateResultStatus("ShipBillPayTax", "TC_CAN01", ReportStatus.strMethodName, counter, browser);
				fnCloseTest();
				ReportControl.fnNextTestJoin(nextTestJoin);
			}
		}
}


