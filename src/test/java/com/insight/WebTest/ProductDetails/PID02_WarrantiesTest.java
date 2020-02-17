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

public class PID02_WarrantiesTest extends ActionEngine{
	CommonLib commonLib = new CommonLib();
	CMTLib cmtLib = new CMTLib();
	CartLib cartLib = new CartLib();
	OrderLib orderLib=new OrderLib();
	ProductDetailLib productdetLib = new ProductDetailLib();
	ProductDisplayInfoLib productDisplayInfoLib=new ProductDisplayInfoLib();
	SearchLib searchLib=new SearchLib();
	CanadaLib canadaLib = new CanadaLib();
	// #############################################################################################################
		// # Name of the Test : PID02_Warranties
		// # Migration Author : Cigniti Technologies
		// #
		// # Date of Migration : OCT 2019
		// # DESCRIPTION : This method is to perform Basic Cart operations.
		// # Parameters : StartRow ,EndRow , nextTestJoin
		// #
		// ###############################################################################################################
		
		@Parameters({ "StartRow", "EndRow", "nextTestJoin" })
		@Test
		public void Tc_PID02(int StartRow, String EndRow, boolean nextTestJoin)	throws Throwable {
		int counter = 0;
		try {
			int intStartRow = StartRow;
			int intEndRow = ReportControl.fnGetEndRowCunt(EndRow, "PID02_Warranties", TestDataInsight,
					"Product_Detail");
			for (int intCounter = intStartRow; intCounter <= intEndRow; intCounter++) {
				try {
					counter = intCounter;
					fnOpenTest();
					ReportStatus.fnDefaultReportStatus();
					ReportControl.intRowCount = intCounter;
					Hashtable<String, String> data = TestUtil.getDataByRowNo("PID02_Warranties", TestDataInsight,
							"Product_Detail", intCounter);
					TestEngineWeb.reporter.initTestCaseDescription("WarrantiesTest");
					
                   //Login
					cmtLib.loginToCMTSearchWebGrpAndUser(data.get("Header"), data.get("WebGrp"),
							data.get("LnameEmailUname"), data.get("Contact_Name"));
					cmtLib.setPermissions(data.get("Menu_Name"), data.get("Set_Permission"));
					cmtLib.clickOnloginAs();
					switchToChildWindow();
					commonLib.searchProduct(data.get("SearchItem1"));
					productdetLib.getProductNameInProductDetailPage(data.get("SearchItem1"));
					productdetLib.getMFRNumberInProductInfopage();
					productdetLib.clickOnWarrenties();
					Thread.sleep(5000);
					Thread.sleep(5000);
					String MfrNum=productdetLib.clickOnWarrentiesTabAddToCart();
					canadaLib.continueToCheckout();
					cartLib.verifyItemInCart(MfrNum);
					commonLib.searchProduct(data.get("SearchItem2"));
					productdetLib.getProductNameInProductDetailPage(data.get("SearchItem1"));
					productdetLib.getMFRNumberInProductInfopage();
					commonLib.addToCartAndVerify();
					canadaLib.continueToCheckout();
					productdetLib.clickonAddWarentyincartPage();
					commonLib.clickLogOutLink(data.get("Logout_Header"));
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
			ReportStatus.fnUpdateResultStatus("PID02_WarrantiesTest", "TC_PID02", ReportStatus.strMethodName, 1, browser);
			throw new RuntimeException(e);
		}
        finally {
        	ReportControl.fnEnableJoin();
			ReportStatus.fnUpdateResultStatus("PID02_WarrantiesTest", "TC_PID02", ReportStatus.strMethodName, counter, browser);
			fnCloseTest();
			ReportControl.fnNextTestJoin(nextTestJoin);
		}
	}

}