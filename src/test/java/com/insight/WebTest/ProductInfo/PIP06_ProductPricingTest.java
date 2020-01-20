package com.insight.WebTest.ProductInfo;

import com.insight.Lib.*;
import com.insight.accelerators.ReportControl;
import com.insight.accelerators.TestEngineWeb;
import com.insight.googledrive.ReportStatus;
import com.insight.utilities.TestUtil;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.util.Hashtable;

public class PIP06_ProductPricingTest extends ProductDisplayInfoLib{

	CMTLib cmtLib = new CMTLib();
	CommonLib commonLib = new CommonLib();
	CartLib cartLib = new CartLib();
	SearchLib searchLib = new SearchLib();
	ProductDetailLib prodDetailsLib=new ProductDetailLib();
	OrderLib orderLib=new OrderLib();
	CommonLib CommonLib = new CommonLib();


	// #############################################################################################################
	// #       Name of the Test               : PIP06_ProductPricing
	// #       Migration Author               : Cigniti Technologies
	// #
	// #       Date of Migration              : October 2019
	// #       DESCRIPTION                    : Purpose of this test method is to test Product Pricing
	// #       Parameters                     : StartRow ,EndRow , nextTestJoin
	// #
	// ###############################################################################################################

	@Parameters({ "StartRow", "EndRow", "nextTestJoin" })
		@Test
		public void TC_PIP06(int StartRow,String EndRow, boolean nextTestJoin) throws Throwable {
		int counter = 0;
		try {
			int intStartRow = StartRow;
			int intEndRow = ReportControl.fnGetEndRowCunt(EndRow, "PIP06_ProductPricing", TestData, "Web_Product_Info");
			for (int intCounter = intStartRow; intCounter <= intEndRow; intCounter++) {
				try {
					counter = intCounter;
					fnOpenTest();
					ReportStatus.fnDefaultReportStatus();
					ReportControl.intRowCount = intCounter;
					Hashtable<String, String> data = TestUtil.getDataByRowNo("PIP06_ProductPricing", TestData,
							"Web_Product_Info", intCounter);
					TestEngineWeb.reporter.initTestCaseDescription("ProductPricing");

						// search for first product
						searchLib.searchInHomePage(data.get("SearchText2"));
						searchLib.verifyBreadCrumbInSearchResultsPage(data.get("SearchText2"));
						String description=getFirstProdDescription();
						String firstProdPrice = getFirtProductListPrice();
						String firstProdQty = getFirstProdQuantity();
						verifyTheProductPricesInSearchResultsPage();
						scrollUp();
						Thread.sleep(3000);
						cartLib.selectFirstProductDisplay();
						// Verifying  description on product details page
						verifyShortDescriptionOnProductDetailsPage(description);
						prodDetailsLib.getMFRNumberInProductInfopage();
						
						verifyThePriceInProdDetailsPage(firstProdPrice); // Verifying price in product details page
						verifyQuantityInProdDetailsPage(firstProdQty);   // Verifying quantity in product details page
						Thread.sleep(3000);
						commonLib.addToCartAndVerify();
						orderLib.continueToCheckOutOnAddCart();

						// Login  as internal user
						cmtLib.loginToCMT(data.get("Header"));
						cmtLib.searchForWebGroup(data.get("WebGrp"));
						cmtLib.clickOnTheWebGroup(data.get("WebGrp_Name"));
						cmtLib.hoverOnManageWebGroupsAndSelectOptions(data.get("Manage_Web_Grp_Options"));
						cmtLib.searchForaUserAndSelect(data.get("LnameEmailUname"), data.get("ContactName"));

						// Login as to UAT
						cmtLib.loginAsAdminCMT();
						Thread.sleep(3000);
						cmtLib.loginVerification(data.get("ContactName"));

						// search for first product
						searchLib.searchInHomePage(data.get("SearchText2"));
						searchLib.verifyBreadCrumbInSearchResultsPage(data.get("SearchText2"));
						Thread.sleep(3000);
						verifyYourPriceExists();
						String desc=getFirstProdDescription();
						cartLib.selectFirstProductDisplay();
						verifyYourPriceInProductDetailsPage();
						verifyShortDescriptionOnProductDetailsPage(desc);

						clickOnWarrantiesTabOnProductDetailsPage();
						verifyPriceInWarrantiesTab();
                        commonLib.clickLogOutLink(data.get("Logout"));
						
						cmtLib.navigateBackToCMT();
						// Work with a different Master Group in Web Group Management Page
						cmtLib.hoverOverMasterGroupAndSelectChangeGrp();
						cmtLib.searchForWebGroup(data.get("WebGrp1"));
						cmtLib.clickOnTheWebGroup(data.get("WebGrp_Name1"));
						cmtLib.hoverOnManageWebGroupsAndSelectOptions(data.get("Manage_Web_Grp_Options"));
						cmtLib.searchForaUserAndSelect(data.get("LnameEmailUname1"), data.get("ContactName1"));
					/*
					 * cmtLib.setPermissions(data.get("Menu_Name"), data.get("Set_Permission1"));
					 * cmtLib.setPermissions(data.get("Menu_Name"), data.get("Set_Permission2"));
					 * cmtLib.setPermissions(data.get("Menu_Name"), data.get("Set_Permission3"));
					 */
						cmtLib.loginAsAdminCMT();
						cmtLib.loginVerification(data.get("ContactName1"));

						// search text
						searchLib.searchInHomePage(data.get("SearchText2"));
						searchLib.verifyBreadCrumbInSearchResultsPage(data.get("SearchText2"));
						// Select new contract  - Open Market
						selectNewcontract(data.get("Contract_Name1"));
						// Verify contract page
						verifyWelcomePage();
						// search for first product
						searchLib.searchInHomePage(data.get("SearchText2"));
						searchLib.verifyBreadCrumbInSearchResultsPage(data.get("SearchText2"));
						verifyOpenMarketPriceExists();
						cartLib.selectFirstProductDisplay();
						Thread.sleep(3000);
						//verifyOpenMarketPriceInProductDetailsPage();

						// Select new contract
						searchLib.selectNewcontract(data.get("Contract_Name2"));
						searchLib.searchInHomePage(data.get("SearchText1"));
						searchLib.verifyBreadCrumbInSearchResultsPage(data.get("SearchText1"));
						verifyUSDContractPricePresentInSearchResults();
						commonLib.clickLogOutLink(data.get("Logout"));
						// End of test
						
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
					ReportStatus.fnUpdateResultStatus("PIP06_ProductPricingTest", "TC_PIP06", ReportStatus.strMethodName, 1, browser);
					throw new RuntimeException(e);
				}
		        finally {
		        	ReportControl.fnEnableJoin();
					ReportStatus.fnUpdateResultStatus("PIP06_ProductPricingTest", "TC_PIP06", ReportStatus.strMethodName, counter, browser);
					fnCloseTest();
					ReportControl.fnNextTestJoin(nextTestJoin);
				}
			}

		}




