package com.insight.WebTest.ProductInfo;

import com.insight.Lib.*;
import com.insight.accelerators.ReportControl;
import com.insight.accelerators.TestEngineWeb;
import com.insight.googledrive.ReportStatus;
import com.insight.utilities.TestUtil;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.util.Hashtable;

public class PIP03_PartDescriptionsTest extends ProductDisplayInfoLib{

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
		public void TC_PIP03(int StartRow,String EndRow, boolean nextTestJoin) throws Throwable {
			int counter = 0;
			try {
				int intStartRow = StartRow;
				int intEndRow = ReportControl.fnGetEndRowCunt(EndRow, "PIP03_PartDescriptions", TestData, "Web_Product_Info");
				for (int intCounter = intStartRow; intCounter <= intEndRow; intCounter++) {
					try {
						counter = intCounter;
						fnOpenTest();
						ReportStatus.fnDefaultReportStatus();
						ReportControl.intRowCount = intCounter;
						Hashtable<String, String> data = TestUtil.getDataByRowNo("PIP03_PartDescriptions", TestData,
								"Web_Product_Info", intCounter);
						TestEngineWeb.reporter.initTestCaseDescription("PartDescriptions");
						// Click on insight logo
						commonLib.clickOnInsightLogoOnHomePage();
						// search for a part or a product
						searchLib.searchInHomePage(data.get("SearchText1"));
						Thread.sleep(2000);
						searchLib.verifyBreadCrumbInSearchResultsPage(data.get("SearchText1"));
						String shortDesc=getFirstProdDescription();
						Thread.sleep(3000);
						cartLib.selectFirstProductDisplay();
						Thread.sleep(3000);

						// Verifying short description on product details page
						verifyShortDescriptionOnProductDetailsPage(shortDesc);

						// Verifying Long description on product details page
						verifyLongDescOnProductDetails();

						// Verifying short and Long description on Accessories tab
						clickOnAccessoriesTabInProductDetailsPage();
						verifyProductDescriptionOnAccessoriesTab();
						
						// Verify the Most Often Purchased Item(s)
						verifyProductDescForMostOftenPurchasedItems();
						verifyProductDescForPeopelWithSimilarInterestBought();
						verifyProductDescForPopularItems();
						

						// End of test


						// fnCloseTest();
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
					ReportStatus.fnUpdateResultStatus("PIP03_PartDescriptions", "TC_PIP03", ReportStatus.strMethodName, 1, browser);
					throw new RuntimeException(e);
				}
		        finally {
		        	ReportControl.fnEnableJoin();
					ReportStatus.fnUpdateResultStatus("PIP03_PartDescriptions", "TC_PIP03", ReportStatus.strMethodName, counter, browser);
					fnCloseTest();
					ReportControl.fnNextTestJoin(nextTestJoin);
				}
			}

		}

