package com.insight.WebTest.ProductInfo;

import com.insight.Lib.*;
import com.insight.accelerators.ReportControl;
import com.insight.accelerators.TestEngineWeb;
import com.insight.googledrive.ReportStatus;
import com.insight.utilities.TestUtil;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.util.Hashtable;

public class PIP05_ProductImageTest extends ProductDisplayInfoLib{

	CMTLib cmtLib = new CMTLib();
	CommonLib commonLib = new CommonLib();
	CartLib cartLib = new CartLib();
	SearchLib searchLib = new SearchLib();
	ProductDetailLib prodDetailsLib=new ProductDetailLib();
	OrderLib orderLib=new OrderLib();

	// #############################################################################################################
	// #     Name of the Test             : PIP05_ProductImage
	// #     Migration Author             : Cigniti Technologies
	// #
	// #     Date of Migration            : October 2019
	// #     DESCRIPTION                  : Purpose of this test method is to test Product Image
	// #     Parameters                   : StartRow ,EndRow , nextTestJoin
	// #
	// ###############################################################################################################

	@Parameters({ "StartRow", "EndRow", "nextTestJoin" })
		@Test
		public void TC_PIP05(int StartRow,String EndRow, boolean nextTestJoin) throws Throwable {
		int counter = 0;
		try {
			int intStartRow = StartRow;
			int intEndRow = ReportControl.fnGetEndRowCunt(EndRow, "PIP05_ProductImage", TestData, "Web_Product_Info");
			for (int intCounter = intStartRow; intCounter <= intEndRow; intCounter++) {
				try {
					counter = intCounter;
					fnOpenTest();
					ReportStatus.fnDefaultReportStatus();
					ReportControl.intRowCount = intCounter;
					Hashtable<String, String> data = TestUtil.getDataByRowNo("PIP05_ProductImage", TestData,
							"Web_Product_Info", intCounter);
					TestEngineWeb.reporter.initTestCaseDescription("ProductImage");

					    commonLib.clickOnInsightLogoOnHomePage();	
					
					   // search for first product
						searchLib.searchInHomePage(data.get("SearchText1"));
						searchLib.verifyBreadCrumbInSearchResultsPage(data.get("SearchText1"));
						String imageSrc=verifyProductImage();
						// cartLib.selectFirstProductDisplay();
						verifyFrontImageInProductDetailsPage();
						backToResultsProductDetailsPage();

						// search for second product
						searchLib.searchInHomePage(data.get("SearchText2"));
						searchLib.verifyBreadCrumbInSearchResultsPage(data.get("SearchText2"));
						Thread.sleep(3000);
						String imageSrc2=verifyProductImage();
						Thread.sleep(3000);
						backToResultsProductDetailsPage();
						
						// search for second product
						searchLib.searchInHomePage(data.get("SearchText3"));
						searchLib.verifyBreadCrumbInSearchResultsPage(data.get("SearchText3"));
						verifyProductImage();
						verifyLeftAngleImageInProductDetailsPage();

						// Verify image in recently viewed products
						prodDetailsLib.Verifyrecentlyviwedproductslabel();
						Thread.sleep(3000);
						scrollToBottom();
						verifyRecentlyViewedProductsImage(imageSrc);
						Thread.sleep(3000);
						verifyRecentlyViewedProductsImage(imageSrc2);

						// End of the test 
						System.out.println("Test completed");
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
					ReportStatus.fnUpdateResultStatus("PIP05_ProductImage", "TC_PIP05", ReportStatus.strMethodName, 1, browser);
					throw new RuntimeException(e);
				}
		        finally {
		        	ReportControl.fnEnableJoin();
					ReportStatus.fnUpdateResultStatus("PIP05_ProductImage", "TC_PIP05", ReportStatus.strMethodName, counter, browser);
					fnCloseTest();
					ReportControl.fnNextTestJoin(nextTestJoin);
				}
			}

		}

