package com.insight.WebTest.Cart;

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
import com.insight.accelerators.ReportControl;
import com.insight.accelerators.TestEngineWeb;
import com.insight.googledrive.ReportStatus;
import com.insight.utilities.TestUtil;

public class CRT24_WebGuidedPurchaseTest extends CartLib {
	CommonLib commonLib = new CommonLib();
	CMTLib cmtLib = new CMTLib();
	CartLib cartLib = new CartLib();
	SearchLib searchLib = new SearchLib();
	OrderLib OrderLib = new OrderLib();

	// #############################################################################################################
	// # Name of the Test : CRT25_QuickCheckOutTest
	// # Migration Author : Cigniti Technologies
	// #
	// # Date of Migration : August 2019
	// # DESCRIPTION : This method is to perform Basic Cart operations.
	// # Parameters : StartRow ,EndRow , nextTestJoin
	// #
	// ###############################################################################################################
	@Parameters({ "StartRow", "EndRow", "nextTestJoin" })
	@Test
	public void TC_CRT24(int StartRow, String EndRow, boolean nextTestJoin) throws Throwable {

		int counter = 0;
		try {
			int intStartRow = StartRow;
			int intEndRow = ReportControl.fnGetEndRowCunt(EndRow, "CRT24_WebGuidedPurchase", TestDataInsight,
					"Web_Cart");
			for (int intCounter = intStartRow; intCounter <= intEndRow; intCounter++) {
				try {
					counter = intCounter;
					fnOpenTest();
					ReportStatus.fnDefaultReportStatus();
					ReportControl.intRowCount = intCounter;
					Hashtable<String, String> data = TestUtil.getDataByRowNo("CRT24_WebGuidedPurchase", TestDataInsight,
							"Web_Cart", intCounter);
					TestEngineWeb.reporter.initTestCaseDescription("WebGuidedPurchase");

					cmtLib.loginToCMTSearchWebGrpAndUser(data.get("Header"), data.get("WebGrp"),
							data.get("LnameEmailUname"), data.get("ContactName"));
					// cmtLib.setPermissions(data.get("menuName"),data.get("Enable_Purchasing_Popup"));
					cmtLib.clickOnloginAs();
					switchToChildWindow();
					cartLib.verifyWelcomeHeader();
					cartLib.clickAndVerifyGetStartedLink();
					cartLib.clickOnAccesseriosLnk(data.get("link"));
					Thread.sleep(2000);
					cartLib.clickOnDellLink(data.get("link1"));
					// Get part id for the first product
					int Num = Integer.parseInt(data.get("size"));
					cartLib.clickOnChooseThisItemLnk(Num);
					cartLib.clickOnProductLink(data.get("ProductLink"));
					Thread.sleep(3000);
					String PartNum = cartLib.getPartNumAndswitchToParentWindow();
					Thread.sleep(3000);
					// Get part id for the second product
					cartLib.clickOnProductLink(data.get("ProductLink2"));
					Thread.sleep(3000);
					String PartNum1 = cartLib.getPartNumAndswitchToParentWindow();
					Thread.sleep(3000);
					cartLib.clickOnNextButton();
					// Click on continue shopping Link
					scrollUp();
					cartLib.clickOnchooseLink(data.get("ContinueLink"));
					cartLib.clickOnlaptopsLnk();
					int Num1 = Integer.parseInt(data.get("size1"));
					cartLib.clickOnChooseThisItemLnk(Num1);
					cartLib.clickOnProductLink(data.get("ProductLink3"));
					// Get part id for the third product
					String PartNum2 = cartLib.getPartNumAndswitchToParentWindow();
					System.out.println("PartNum2"+ PartNum2);
					Thread.sleep(3000);
					//cartLib.clickOnChooseThisItemLnk(Num);
					Thread.sleep(3000);
					// Click on continue shopping Link
					cartLib.clickOnNextButton();
					scrollUp();
					cartLib.clickOnchooseLink(data.get("Proceedlink"));
					// Verify landed to cart page or not
					OrderLib.verifyCartHeaderLabel();
					// Comparing part numbers which are added to cart
					cartLib.verifyItemInCart(PartNum);
					cartLib.verifyItemInCart(PartNum1);
					cartLib.verifyItemInCart(PartNum2);

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
			ReportStatus.fnUpdateResultStatus("WebGuidedPurchase", "TC_CRT24", ReportStatus.strMethodName, 1, browser);
			throw new RuntimeException(e);
		} finally {
			ReportControl.fnEnableJoin();
			ReportStatus.fnUpdateResultStatus("WebGuidedPurchase", "TC_CRT24", ReportStatus.strMethodName, counter,
					browser);
			fnCloseTest();
			ReportControl.fnNextTestJoin(nextTestJoin);
		}
	}
}