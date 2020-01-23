package com.insight.WebTest.ProductDetails;

import java.util.Hashtable;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.insight.Lib.CMTLib;
import com.insight.Lib.CanadaLib;
import com.insight.Lib.CartLib;
import com.insight.Lib.ChinaLib;
import com.insight.Lib.CommonLib;
import com.insight.Lib.OrderLib;
import com.insight.Lib.ProductDetailLib;
import com.insight.Lib.ProductDisplayInfoLib;
import com.insight.Lib.SearchLib;
import com.insight.Lib.ShipBillPayLib;
import com.insight.accelerators.ActionEngine;
import com.insight.accelerators.ReportControl;
import com.insight.accelerators.TestEngineWeb;
import com.insight.googledrive.ReportStatus;
import com.insight.utilities.TestUtil;

public class PID06_StockLocationsTest extends ActionEngine{
	CommonLib commonLib = new CommonLib(); 
	CMTLib cmtLib = new CMTLib();
	CartLib cartLib = new CartLib();
	ProductDetailLib productDetailLib = new ProductDetailLib();
	SearchLib searchLib=new SearchLib();
	
	// #############################################################################################################
		// # Name of the Test : PID06_StockLocations
		// # Migration Author : Cigniti Technologies
		// #
		// # Date of Migration : OCT 2019
		// # DESCRIPTION : This method is to perform Basic Cart operations.
		// # Parameters : StartRow ,EndRow , nextTestJoin
		// #
		// ###############################################################################################################

		@Parameters({ "StartRow", "EndRow", "nextTestJoin" })
		@Test
		public void Tc_PID06(int StartRow, String EndRow, boolean nextTestJoin)	throws Throwable {
			int counter = 0;
			try {
				int intStartRow = StartRow;
				int intEndRow = ReportControl.fnGetEndRowCunt(EndRow, "PID06_StockLocations",
						TestDataInsight, "Product_Detail");
				for (int intCounter = intStartRow; intCounter <= intEndRow; intCounter++) {
					try {
					counter = intCounter;
					fnOpenTest();
					ReportStatus.fnDefaultReportStatus();
					ReportControl.intRowCount = intCounter;
					Hashtable<String, String> data = TestUtil.getDataByRowNo("PID06_StockLocations",
							TestDataInsight, "Product_Detail", intCounter);
					TestEngineWeb.reporter.initTestCaseDescription("StockLocationsTest");

		
				commonLib.searchProduct(data.get("Search_Item"));
				searchLib.verifyBreadCrumbInSearchResultsPage(data.get("Search_Item"));
				productDetailLib.verifyAvailability();
				productDetailLib.getFirstProdDescription();
				cartLib.selectFirstProductDisplay();
				productDetailLib.verifyAvailabilityInProductDetailPage();
				commonLib.searchProduct(data.get("Search_Item1"));
				searchLib.verifyBreadCrumbInSearchResultsPage(data.get("Search_Item1"));
				productDetailLib.narrowDownFilters(data.get("Category"), data.get("Option"));
				commonLib.spinnerImage();
				productDetailLib.selectUnlimetedAvailabilityProduct();
				productDetailLib.verifyAvailabilityInProductDetailPage();
				cmtLib.loginToCMTSearchWebGrpAndUser(data.get("Header"), data.get("WebGrp"), data.get("LnameEmailUname"),
						data.get("Contact_Name"));
				cmtLib.setWebGroupDefaultOption(data.get("WebGrp_Option"));
				cmtLib.setPermissions(data.get("Menu_Name"), data.get("Set_Permission"));
				cmtLib.clickOnloginAs();
				switchToChildWindow();
				productDetailLib.selectAccountToolsFromSideMenuAndClickOnProductGrp(data.get("Tools_Menu"),
						data.get("Tools_Menu_DD"), data.get("Product_Group"), data.get("Product_Name"));

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
		ReportStatus.fnUpdateResultStatus("PID06_StockLocationsTest", "TC_PID06", ReportStatus.strMethodName, 1, browser);
		throw new RuntimeException(e);
	}
    finally {
    	ReportControl.fnEnableJoin();
		ReportStatus.fnUpdateResultStatus("PID06_StockLocationsTest", "TC_PID06", ReportStatus.strMethodName, counter, browser);
		fnCloseTest();
		ReportControl.fnNextTestJoin(nextTestJoin);
	}
}

}

