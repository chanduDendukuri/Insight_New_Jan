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

public class PID01_AddandDeleteToPersonalProductsListTest extends ActionEngine {
	CommonLib commonLib = new CommonLib();
	CMTLib cmtLib = new CMTLib();
	CartLib cartLib = new CartLib();
	ProductDetailLib productDetailLib = new ProductDetailLib();
	SearchLib searchLib=new SearchLib();

// #############################################################################################################
// # Name of the Test : PID01_AddandDeleteToPersonalProductsList
// # Migration Author : Cigniti Technologies
// #
// # Date of Migration : OCT 2019
// # DESCRIPTION : This method is to perform Basic Cart operations.
// # Parameters : StartRow ,EndRow , nextTestJoin
// #
// ###############################################################################################################

	@Parameters({ "StartRow", "EndRow", "nextTestJoin" })
	@Test
	public void Tc_PID01(int StartRow, String EndRow, boolean nextTestJoin) throws Throwable {
		int counter = 0;
		try {
			int intStartRow = StartRow;
			int intEndRow = ReportControl.fnGetEndRowCunt(EndRow, "PID01_AddandDeleteToPersonalProductsList",
					TestDataInsight, "Product_Detail");
			for (int intCounter = intStartRow; intCounter <= intEndRow; intCounter++) {
				try {
					counter = intCounter;
					fnOpenTest();
					ReportStatus.fnDefaultReportStatus();
					ReportControl.intRowCount = intCounter;
					Hashtable<String, String> data = TestUtil.getDataByRowNo("PID01_AddandDeleteToPersonalProductsList",
							TestDataInsight, "Product_Detail", intCounter);
					TestEngineWeb.reporter.initTestCaseDescription("AddandDeleteToPersonalProductsList");

					cmtLib.loginToCMTSearchWebGrpAndUser(data.get("Header"), data.get("WebGrp"),
							data.get("LnameEmailUname"), data.get("ContactName"));
					cmtLib.clickOnloginAs();
					switchToChildWindow();
					commonLib.searchProduct(data.get("Search_Item"));
					searchLib.verifyBreadCrumbInSearchResultsPage(data.get("Search_Item"));
					productDetailLib.getFirstProdDescription();
					cartLib.selectFirstProductDisplay();
					productDetailLib.getProductNameInProductDetailPage(data.get("Search_Item"));
					String partNumber = productDetailLib.getMFRNumberInProductInfopage();
					productDetailLib.selectProductAndAddToPersonalProductList();
					productDetailLib.verifyPersonalProductList(partNumber);
					productDetailLib.deletePersonalProductList(partNumber);
					productDetailLib.addItemsToProductListToPersonalProdcutList(data.get("Part_Number"));
					productDetailLib.deletePersonalProductList(data.get("Part_Number"));
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
			ReportStatus.fnUpdateResultStatus("PID01_AddandDeleteToPersonalProductsList", "TC_PID01",
					ReportStatus.strMethodName, 1, browser);
			throw new RuntimeException(e);
		} finally {
			ReportControl.fnEnableJoin();
			ReportStatus.fnUpdateResultStatus("PID01_AddandDeleteToPersonalProductsList", "TC_PID01",
					ReportStatus.strMethodName, counter, browser);
			fnCloseTest();
			ReportControl.fnNextTestJoin(nextTestJoin);
		}
	}

}
