package com.insight.WebTest.Cart;

import java.util.Hashtable;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.insight.Lib.CMTLib;
import com.insight.Lib.CanadaLib;
import com.insight.Lib.CartLib;
import com.insight.Lib.CommonLib;
import com.insight.Lib.OrderLib;
import com.insight.Lib.ProductDisplayInfoLib;
import com.insight.accelerators.ReportControl;
import com.insight.accelerators.TestEngineWeb;
import com.insight.googledrive.ReportStatus;
import com.insight.utilities.TestUtil;

public class CRT22_CartInventoryTest extends CartLib {
	CommonLib commonLib = new CommonLib();
	CMTLib cmtLib = new CMTLib();
	CartLib cartLib = new CartLib();
	ProductDisplayInfoLib prodInfoLib = new ProductDisplayInfoLib();
	CanadaLib canadaLib = new CanadaLib();
	OrderLib orderLib = new OrderLib();
	// #############################################################################################################
	// # Name of the Test : CRT22_CartInventory
	// # Migration Author : Cigniti Technologies
	// #
	// # Date of Migration : August 2019
	// # DESCRIPTION : This method is to perform Basic Cart operations.
	// # Parameters : StartRow ,EndRow , nextTestJoin
	// #
	// ###############################################################################################################
	@Parameters({ "StartRow", "EndRow", "nextTestJoin" })
	@Test
	public void Tc_CRT22(int StartRow, String EndRow, boolean nextTestJoin) throws Throwable {

		int counter = 0;
		try {
			int intStartRow = StartRow;
			int intEndRow = ReportControl.fnGetEndRowCunt(EndRow, "CRT22_CartInventory", TestDataInsight, "Web_Cart");
			for (int intCounter = intStartRow; intCounter <= intEndRow; intCounter++) {
				try {
					counter = intCounter;
					fnOpenTest();
					ReportStatus.fnDefaultReportStatus();
					ReportControl.intRowCount = intCounter;
					Hashtable<String, String> data = TestUtil.getDataByRowNo("CRT22_CartInventory", TestDataInsight,
							"Web_Cart", intCounter);
					TestEngineWeb.reporter.initTestCaseDescription("CartInventory");
					cmtLib.loginToCMT(data.get("Header"));
					cmtLib.searchForWebGroup(data.get("WebGrp"));
					cmtLib.clickOnTheWebGroup(data.get("WebGrp_Name"));
					cmtLib.hoverOnManageWebGroupsAndSelectOptions(data.get("ManageWebGrpOptions"));
					cmtLib.searchForaUserAndSelect(data.get("LnameEmailUname"), data.get("ContactName"));
					cmtLib.loginAsAdminCMT();
					cmtLib.loginVerification(data.get("ContactName"));
					commonLib.searchProduct(data.get("searchItem"));
					prodInfoLib.verifyTheManufacturerNumberInProductDetailsPage(data.get("searchItem"));
					COICSIPrice();
					String Text=getTextProductdetailPageAndVerifyCSICOI();
					verifyCOICSI(Text,data.get("COIText"),data.get("CSIText"));
					commonLib.searchProduct(data.get("searchItem1"));
					COICSIPrice();
					String Text1=getTextProductdetailPageAndVerifyCSICOI();
					verifyCOICSI(Text1,data.get("COIText"),data.get("CSIText"));
					cartLib.verifyCOIpart(data.get("toolsMenuName"), data.get("dropDown"), data.get("productGroup"),
							data.get("productName"), data.get("COIText"));
					//cartLib.verifyCSIpart(data.get("toolsMenuName"), data.get("dropDown"), data.get("productGroup"),
							//data.get("productName"), data.get("CSIText"));
					//commonLib.clickCart();
					canadaLib.verifyPlaceCartLabel();
					prodInfoLib.verifyCartPageAndPartDetailsForRecentlyItem();
					coiInCartPage();
					orderLib.stockInCartPage();
					commonLib.clickLogOutLink("Logout");
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
			ReportStatus.fnUpdateResultStatus("CartInventory", "Tc_CRT22", ReportStatus.strMethodName, 1, browser);
			throw new RuntimeException(e);
		} finally {
			ReportControl.fnEnableJoin();
			ReportStatus.fnUpdateResultStatus("CartInventory", "Tc_CRT22", ReportStatus.strMethodName, counter,
					browser);
			fnCloseTest();
			ReportControl.fnNextTestJoin(nextTestJoin);
		}
	}
}