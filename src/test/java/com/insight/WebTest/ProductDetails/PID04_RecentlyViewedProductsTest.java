package com.insight.WebTest.ProductDetails;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Hashtable;
import java.util.List;

import com.insight.Lib.*;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.insight.accelerators.ActionEngine;
import com.insight.accelerators.ReportControl;
import com.insight.accelerators.TestEngineWeb;
import com.insight.googledrive.ReportStatus;
import com.insight.utilities.TestUtil;

public class PID04_RecentlyViewedProductsTest extends ActionEngine{
	CommonLib commonLib = new CommonLib(); 
	CMTLib cmtLib = new CMTLib();
	CartLib cartLib = new CartLib();
	ProductDetailLib productdetLib = new ProductDetailLib();
	ProductDisplayInfoLib productDispinfoLib = new ProductDisplayInfoLib();
	SearchLib searchLib = new SearchLib();
	CommonCanadaLib ccp = new CommonCanadaLib();

	// #############################################################################################################
		// # Name of the Test : PID04_RecentlyViewedProducts
		// # Migration Author : Cigniti Technologies
		// #
		// # Date of Migration : OCT 2019
		// # DESCRIPTION : This method is to perform Basic Cart operations.
		// # Parameters : StartRow ,EndRow , nextTestJoin
		// #
		// ###############################################################################################################
		
		@Parameters({ "StartRow", "EndRow", "nextTestJoin" })
		@Test
		public void Tc_PID04(int StartRow, String EndRow, boolean nextTestJoin)	throws Throwable {
			int counter = 0;
			try {
				int intStartRow = StartRow;
				int intEndRow = ReportControl.fnGetEndRowCunt(EndRow, "PID04_RecentlyViewedProducts", TestDataInsight,
						"Product_Detail");
				for (int intCounter = intStartRow; intCounter <= intEndRow; intCounter++) {
					try {
					counter = intCounter;
					fnOpenTest();
					ReportStatus.fnDefaultReportStatus();
					ReportControl.intRowCount = intCounter;
					Hashtable<String, String> data = TestUtil.getDataByRowNo("PID04_RecentlyViewedProducts",
							TestDataInsight, "Product_Detail", intCounter);
					TestEngineWeb.reporter.initTestCaseDescription("RecentlyViewedProductsTest");
					List<String> x = new ArrayList<String>();
					List<String> y = new ArrayList<String>();

					commonLib.searchProduct(data.get("Search_Item1"));//HP Workstation

					searchLib.verifyBreadCrumbInSearchResultsPage(data.get("Search_Item1"));
					cartLib.selectFirstProductDisplay();
					String Searchitem1=productdetLib.getProductNameInProductDetailPage(data.get("Search_Item1"));
					productdetLib.VerifyrecentlyviwedproductslabelNotVisisble();
					x.add(data.get("Search_Item1"));
					//search item 2
					commonLib.searchProduct(data.get("Search_Item2"));//adobe Acrobat license
					searchLib.verifyBreadCrumbInSearchResultsPage(data.get("Search_Item2"));
					cartLib.selectFirstProductDisplay();
					String Searchitem2=productdetLib.getProductNameInProductDetailPage(data.get("Search_Item2")).trim();
					ArrayList<String> z =productdetLib.Verifyrecentlyvieweditems();
					x.add(data.get("Search_Item2"));
					if(x.size() == z.size()) {
						for (int i = 0; i < x.size(); i++) {
							ccp.validateNames(x.get(i).trim(),z.get(i).trim());
						}
					}
					//search item 3
					commonLib.searchProduct(data.get("Search_Item3"));//Multifunction Printers
					searchLib.verifyBreadCrumbInSearchResultsPage(data.get("Search_Item3"));
					cartLib.selectFirstProductDisplay();
					String Searchitem3=productdetLib.getProductNameInProductDetailPage(data.get("Search_Item3"));
					productdetLib.Verifyrecentlyvieweditems();
						x.add(Searchitem3);
						for(int i =0 ;i<x.size();i++){
							//System.out.println(x.get(i).);
							System.out.println(x);

						}
					//	x.containsAll(Searchitem1,Searchitem1,Searchitem3);

					productdetLib.Clickonviewdetails(Searchitem2);
					productdetLib.Clickonviewdetails(Searchitem1);
					//search item 4
					commonLib.searchProduct(data.get("Search_Item4"));//Belkin Cable
					searchLib.verifyBreadCrumbInSearchResultsPage(data.get("Search_Item4"));
					cartLib.selectFirstProductDisplay();
					productdetLib.Verifyrecentlyvieweditems();
					//search item 5
					commonLib.searchProduct(data.get("Search_Item5"));//Cisco LED Flat Pannel
					searchLib.verifyBreadCrumbInSearchResultsPage(data.get("Search_Item5"));
					cartLib.selectFirstProductDisplay();
					productdetLib.Verifyrecentlyvieweditems();
					//search item 6
					commonLib.searchProduct(data.get("Search_Item6"));//Ricoh Printer
					searchLib.verifyBreadCrumbInSearchResultsPage(data.get("Search_Item6"));
					cartLib.selectFirstProductDisplay();
					productdetLib.Verifyrecentlyvieweditems();
					//search item 7
					commonLib.searchProduct(data.get("Search_Item7"));//Computer Cables
					searchLib.verifyBreadCrumbInSearchResultsPage(data.get("Search_Item7"));
					cartLib.selectFirstProductDisplay();
					productdetLib.Verifyrecentlyvieweditems();
					// Login
					cmtLib.loginToCMTSearchWebGrpAndUser(data.get("Header"), data.get("WebGrp"),
							data.get("LnameEmailUname"), data.get("Contact_Name"));
					cmtLib.loginAsAdminCMT();
					commonLib.searchProduct(data.get("Search_Item4"));
					String MfrNum = productdetLib.getMfrpartnumofFirstproduct();
					cartLib.selectFirstProductDisplay();
					Thread.sleep(4000);
					productDispinfoLib.verifyTheManufacturerNumberInProductDetailsPage(MfrNum);
					commonLib.addToCartAndVerify();
					commonLib.clickLogOutLink(data.get("Logout_Header"));
					cmtLib.navigateBackToCMT();
					cmtLib.hoverOverMasterGroupAndSelectChangeGrp();
					cmtLib.searchForWebGroup(data.get("WebGrp"));
					cmtLib.clickOnTheWebGroup(data.get("WebGrp_Name"));
					// Click Custom Catalog
					cmtLib.hoverOnManageWebGroupsAndSelectOptions(data.get("CustomCatalog"));
					productdetLib.ClickonDeleteButtonofcustomcatalog();
					productdetLib.Createcustomcatalog();
					productdetLib.ClickonCustomcatalog(data.get("manufacturers"));
					productdetLib.Clickonmanufacturers(data.get("manufacturers1"));
					productdetLib.Clickonmanufacturers(data.get("manufacturers2"));
					productdetLib.Clickonmanufacturers(data.get("manufacturers3"));
					// Custom Catalog changes and update
					productdetLib.SelectOptionFromExculdeManufacturers(data.get("optionOKI"));
					cmtLib.hoverOnManageWebGroupsAndSelectOptions(data.get("ManageWebGrpOptions"));
					cmtLib.searchForaUserAndSelect(data.get("LnameEmailUname"), data.get("Contact_Name"));
					cmtLib.setPermissions(data.get("Menu_Name"), data.get("Set_Permission"));
					cmtLib.loginAsAdminCMT();
					commonLib.searchProduct(data.get("Search_Item4"));
					cartLib.selectFirstProductDisplay();
					commonLib.searchProduct(data.get("Search_Item5"));
					cartLib.selectFirstProductDisplay();
					// verify recently viewed----
					productdetLib.Verifyrecentlyvieweditems();
					commonLib.clickLogOutLink(data.get("Logout_Header"));
					cmtLib.navigateBackToCMT();
					cmtLib.hoverOverMasterGroupAndSelectChangeGrp();
					cmtLib.searchForWebGroup(data.get("WebGrp"));
					cmtLib.clickOnTheWebGroup(data.get("WebGrp_Name"));
					cmtLib.hoverOnManageWebGroupsAndSelectOptions(data.get("CustomCatalog"));
					// delete Custom Catalog
					productdetLib.ClickonDeleteButtonofcustomcatalog();
					cmtLib.hoverOnManageWebGroupsAndSelectOptions(data.get("ManageWebGrpOptions"));
					cmtLib.searchForaUserAndSelect(data.get("LnameEmailUname"), data.get("Contact_Name"));
					cmtLib.setPermissionsToDisable(data.get("Menu_Name"), data.get("Set_Permission"));
					// End Of The Test
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
			//gErrorMessage = e.toString();
			gTestStatus = false;
			ReportStatus.fnUpdateResultStatus("PID04_RecentlyViewedProductsTest", "TC_PID04", ReportStatus.strMethodName, 1, browser);
			throw new RuntimeException(e);
		}
        finally {
        	ReportControl.fnEnableJoin();
			ReportStatus.fnUpdateResultStatus("PID04_RecentlyViewedProductsTest", "TC_PID04", ReportStatus.strMethodName, counter, browser);
			fnCloseTest();
			ReportControl.fnNextTestJoin(nextTestJoin);
		}
	}

}
