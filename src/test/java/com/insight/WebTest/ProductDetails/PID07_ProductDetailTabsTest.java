package com.insight.WebTest.ProductDetails;

import java.util.Hashtable;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.insight.Lib.CMTLib;
import com.insight.Lib.CartLib;
import com.insight.Lib.CommonLib;
import com.insight.Lib.MarriottIntlCorpLib;
import com.insight.Lib.ProductDetailLib;
import com.insight.Lib.SearchLib;
import com.insight.accelerators.ActionEngine;
import com.insight.accelerators.ReportControl;
import com.insight.accelerators.TestEngineWeb;
import com.insight.googledrive.ReportStatus;
import com.insight.utilities.TestUtil;

public class PID07_ProductDetailTabsTest extends ActionEngine{
	CommonLib commonLib = new CommonLib(); 
	CMTLib cmtLib = new CMTLib();
	CartLib cartLib = new CartLib();
	ProductDetailLib productDetailLib = new ProductDetailLib();
	SearchLib searchLib=new SearchLib();

	// #############################################################################################################
		// # Name of the Test : PID07_ProductDetailTabs
		// # Migration Author : Cigniti Technologies
		// #
		// # Date of Migration : OCT 2019
		// # DESCRIPTION : This method is to perform Basic Cart operations.
		// # Parameters : StartRow ,EndRow , nextTestJoin
		// #
		// ###############################################################################################################
		
		@Parameters({ "StartRow", "EndRow", "nextTestJoin" })
		@Test
		public void Tc_PID07(int StartRow, String EndRow, boolean nextTestJoin)	throws Throwable {
			int counter = 0;
			try {
				int intStartRow = StartRow;
				int intEndRow = ReportControl.fnGetEndRowCunt(EndRow, "PID07_ProductDetailTabs",
						TestDataInsight, "Product_Detail");
				for (int intCounter = intStartRow; intCounter <= intEndRow; intCounter++) {
					try {
					counter = intCounter;
					fnOpenTest();
					ReportStatus.fnDefaultReportStatus();
					ReportControl.intRowCount = intCounter;
					Hashtable<String, String> data = TestUtil.getDataByRowNo("PID07_ProductDetailTabs",
							TestDataInsight, "Product_Detail", intCounter);
					TestEngineWeb.reporter.initTestCaseDescription("ProductDetailTabsTest");
				CommonLib commonLib = new CommonLib();
				ProductDetailLib productdetLib = new ProductDetailLib();
				MarriottIntlCorpLib micLib=new MarriottIntlCorpLib();
				
				commonLib.searchProduct(data.get("Search_Item2"));
				productdetLib.getProductNameInProductDetailPage(data.get("Search_Item2"));
				productdetLib.getMFRNumberInProductInfopage();
				productdetLib.OverviewTab();
				productdetLib.clickOnWarrenties();
				Thread.sleep(5000);
				Thread.sleep(5000);
				String MfrNum=productdetLib.clickOnWarrentiesAddToCart();
				commonLib.continueToShopping();
				productdetLib.clickOnAccessories();
				String accessories=productdetLib.clickOnAccessoriesAddToCart();
				commonLib.continueToShopping();
				// Verify Specifications
				productdetLib.clickOnSpecification();
				productdetLib.verifySpecifications(data.get("Tab1"));
				productdetLib.verifySpecifications(data.get("Tab2"));
				productdetLib.verifySpecifications(data.get("Tab3"));
				productdetLib.verifySpecifications(data.get("Tab4"));
				Thread.sleep(3000);
				scrollUp();
				commonLib.clickCart();
				micLib.handleinsightpopup();
				cartLib.verifyItemInCart(accessories);
				cartLib.verifyItemInCart(MfrNum);
				Thread.sleep(3000);
			    //End of The Test
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
		ReportStatus.fnUpdateResultStatus("PID05_RecommendedProductsTest", "TC_PID07", ReportStatus.strMethodName, 1, browser);
		throw new RuntimeException(e);
	}
    finally {
    	ReportControl.fnEnableJoin();
		ReportStatus.fnUpdateResultStatus("PID05_RecommendedProductsTest", "TC_PID07", ReportStatus.strMethodName, counter, browser);
		fnCloseTest();
		ReportControl.fnNextTestJoin(nextTestJoin);
	}
}

}

