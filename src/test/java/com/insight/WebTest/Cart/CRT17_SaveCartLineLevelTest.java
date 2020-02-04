package com.insight.WebTest.Cart;

import java.util.Hashtable;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.insight.Lib.CMTLib;
import com.insight.Lib.CanadaLib;
import com.insight.Lib.CartLib;
import com.insight.Lib.ChinaLib;
import com.insight.Lib.CommonLib;
import com.insight.Lib.LineLevelInfoLib;
import com.insight.Lib.OrderLib;
import com.insight.Lib.ProductDetailLib;
import com.insight.Lib.ProductDisplayInfoLib;
import com.insight.Lib.SearchLib;
import com.insight.Lib.ShipBillPayLib;
import com.insight.accelerators.ReportControl;
import com.insight.accelerators.TestEngineWeb;
import com.insight.googledrive.ReportStatus;
import com.insight.utilities.TestUtil;

public class CRT17_SaveCartLineLevelTest extends CartLib {
	CommonLib commonLib = new CommonLib();
	CMTLib cmtLib = new CMTLib();
	CartLib cartLib = new CartLib();
	CanadaLib canadaLib = new CanadaLib();
	SearchLib searchLib = new SearchLib();
	ProductDisplayInfoLib prodInfoLib = new ProductDisplayInfoLib();
	LineLevelInfoLib lineLevelLib=new LineLevelInfoLib();
	// #############################################################################################################
	// # Name of the Test : CRT17_SaveCartLineLevel
	// # Migration Author : Cigniti Technologies
	// #
	// # Date of Migration : August 2019
	// # DESCRIPTION : This method is to perform Basic Cart operations.
	// # Parameters : StartRow ,EndRow , nextTestJoin
	// #
	// ###############################################################################################################
	@Parameters({ "StartRow", "EndRow", "nextTestJoin" })
	@Test
	public void Tc_CRT17(int StartRow, String EndRow, boolean nextTestJoin) throws Throwable {

		int counter = 0;
		try {
			int intStartRow = StartRow;
			int intEndRow = ReportControl.fnGetEndRowCunt(EndRow, "CRT17_SaveCartLineLevel", TestDataInsight, "Web_Cart");
			for (int intCounter = intStartRow; intCounter <= intEndRow; intCounter++) {
				try {
					counter = intCounter;
					fnOpenTest();
					ReportStatus.fnDefaultReportStatus();
					ReportControl.intRowCount = intCounter;
					Hashtable<String, String> data = TestUtil.getDataByRowNo("CRT17_SaveCartLineLevel", TestDataInsight,
							"Web_Cart", intCounter);
					TestEngineWeb.reporter.initTestCaseDescription("SaveCartLineLevel");

					OrderLib orderLib = new OrderLib();
					//cmtLib.loginToCMTSearchWebGrpAndUser(data.get("Header"), data.get("WebGrp"),
							//data.get("LnameEmailUname"), data.get("ContactName"));
					cmtLib.loginToCMT(data.get("Header"));
					cmtLib.searchForWebGroup(data.get("WebGrp"));
					cmtLib.clickOnTheWebGroup(data.get("WebGrp_Name"));
					cmtLib.hoverOnManageWebGroupsAndSelectOptions(data.get("Manage_Web_Grp_Options"));
					cmtLib.searchForaUserAndSelect(data.get("LnameEmailUname"), data.get("ContactName"));
					cmtLib.clickOnRolesAndPermissionsAndSetPermission(data.get("Menu_Name"), data.get("Set_Permission"));
					cmtLib.setPermissions(data.get("Menu_Name"), data.get("Enable_Purchasing_Popup"));
					cmtLib.clickOnloginAs();
					switchToChildWindow();
					cmtLib.loginVerification(data.get("ContactName"));
					commonLib.clickOnAccountToolsAndClickOnProductGrp(data.get("Tools_Menu"),
							data.get("Tools_Menu_DD"));
					cartLib.deleteSavedCartFromAccountTools();
					commonLib.searchProduct(data.get("PartNumber"));
					searchLib.verifyBreadCrumbInSearchResultsPage(data.get("PartNumber"));
					prodInfoLib.getPartNumberInSearchResultsPage();
					commonLib.addFirstDisplyedItemToCartAndVerify();
					
					canadaLib.continueToCheckout();
					canadaLib.verifyPlaceCartLabel();
					prodInfoLib.verifyCartPageAndPartDetailsForRecentlyItem();
					orderLib.proceedToCheckout();
					lineLevelLib.verifyOrderAndItemInfoBreadCrumb();
					cartLib.addAdditionalInformationInCheckOut(data.get("Url"), data.get("RP_HDL_Txt"));
					cartLib.addLineLevelInformationInCheckOut(data.get("RP_LNL_Txt"));
					canadaLib.verifySBP();
					cartLib.clearPhoneFieldInCheckOut();
					cartLib.shippingBillPayInCheckOut(data.get("Card_Number").toString(), data.get("Card_Name"),
							data.get("Month"), data.get("Year"), data.get("PONumber"),data.get("POReleaseNumber"));
					cartLib.verifyRpHdlTxt(data.get("RP_HDL_Txt"));
					//cartLib.verifyRpLnllTxt(data.get("RP_LNL_Txt"));
					String cartName = "QTPSaveCartLineLevel"+getRandomNumeric(4);
					cartLib.clickOnSaveCartContentAndSaveCart(cartName);
					
					commonLib.clickCart();
					canadaLib.verifyPlaceCartLabel();
					commonLib.verifyCartIsEMpty();
					cartLib.openSavedCartFromTools(cartName);
					cartLib.addToCartInSavedCart(cartName);
					canadaLib.verifyPlaceCartLabel();
					orderLib.proceedToCheckout();
					lineLevelLib.verifyOrderAndItemInfoBreadCrumb();
					//cartLib.verifyRpHdlTxtisNotPresent(data.get("RP_HDL_Txt"));
					//cartLib.verifyRpLnllTxtisNotPresent(data.get("RP_LNL_Txt"));
					scrollUp();
					cartLib.openSavedCartFromTools(cartName);
					cartLib.deleteCartFromAccountTools(cartName);
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
		//	gErrorMessage = e.toString();
			gTestStatus = false;
			ReportStatus.fnUpdateResultStatus("SaveCartLineLevel", "Tc_CRT17", ReportStatus.strMethodName, 1, browser);
			throw new RuntimeException(e);
		} finally {
			ReportControl.fnEnableJoin();
			ReportStatus.fnUpdateResultStatus("SaveCartLineLevel", "Tc_CRT17", ReportStatus.strMethodName, counter,
					browser);
			fnCloseTest();
			ReportControl.fnNextTestJoin(nextTestJoin);
		}
	}
}
