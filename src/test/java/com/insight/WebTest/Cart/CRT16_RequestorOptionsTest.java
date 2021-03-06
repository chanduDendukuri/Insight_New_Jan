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

public class CRT16_RequestorOptionsTest extends CartLib{
	CommonLib commonLib = new CommonLib();
	CMTLib cmtLib = new CMTLib();
	CartLib cartLib = new CartLib();
	CanadaLib canadaLib=new CanadaLib();
	SearchLib search = new SearchLib();
	ProductDisplayInfoLib prodInfoLib = new ProductDisplayInfoLib();

	// #############################################################################################################
    // #    Name of the Test         : CRT16_RequestorOptions
    // #    Migration Author         : Cigniti Technologies
    // #
    // #    Date of Migration        : August 2019
    // #    DESCRIPTION              : This method is to perform Basic Cart  operations.
    // #    Parameters               : StartRow ,EndRow , nextTestJoin
    // #
    // ###############################################################################################################	
	@Parameters({"StartRow","EndRow","nextTestJoin"})
	@Test
	public void Tc_CRT16(int StartRow,String EndRow,boolean nextTestJoin) throws Throwable {

					int counter = 0;
					try {
						int intStartRow = StartRow;
						int intEndRow = ReportControl.fnGetEndRowCunt(EndRow, "CRT16_RequestorOptions", TestDataInsight, "Web_Cart");
						for (int intCounter = intStartRow; intCounter <= intEndRow; intCounter++) {
							try {
								counter = intCounter;
								fnOpenTest();
								ReportStatus.fnDefaultReportStatus();
								ReportControl.intRowCount = intCounter;
								Hashtable<String, String> data = TestUtil.getDataByRowNo("CRT16_RequestorOptions", TestDataInsight,
										"Web_Cart", intCounter);
								TestEngineWeb.reporter.initTestCaseDescription("RequestorOptions");
					
					//cmtLib.loginToCMTSearchWebGrpAndUser(data.get("Header"), data.get("WebGrp"), data.get("LnameEmailUname"), data.get("ContactName"));
					cmtLib.loginToCMT(data.get("Header"));
					cmtLib.searchForWebGroup(data.get("WebGrp"));
					cmtLib.clickOnTheWebGroup(data.get("WebGrp_Name"));
					cmtLib.hoverOnManageWebGroupsAndSelectOptions(data.get("Manage_Web_Grp_Options"));
					cmtLib.searchForaUserAndSelect(data.get("LnameEmailUname"), data.get("ContactName"));
					cmtLib.setPermissions(data.get("menuName"),data.get("Enable_Purchasing_Popup"));
					cmtLib.clickOnloginAs();
					switchToChildWindow();
					cmtLib.loginVerification(data.get("ContactName"));
					cmtLib.handleWelcomeToInsightBetaPopUp();
					commonLib.searchProduct(data.get("PartNumber"));
					
					prodInfoLib.verifyTheManufacturerNumberInProductDetailsPage(data.get("PartNumber"));
					search.increaseQuantity(data.get("quantity"));

					commonLib.addToCartAndVerify();
					
					canadaLib.continueToCheckout();
					canadaLib.verifyPlaceCartLabel();
					prodInfoLib.verifyCartPageAndPartDetailsForRecentlyItem();
					cartLib.verifySaveCartAsQuoteIsPresent();
					commonLib.clickLogOutLink(data.get("Logout_Header"));
					cmtLib.navigateBackToCMT();
								
				    cmtLib.hoverOverMasterGroupAndSelectChangeGrp();
				    cmtLib.searchForWebGroup(data.get("WebGrp"));
				    cmtLib.clickOnTheWebGroup(data.get("WebGrp_Name"));
				   
				    cmtLib.hoverOnManageWebGroupsAndSelectOptions(data.get("Manage_Web_Grp_Options"));
				    cmtLib.searchForaUserAndSelect(data.get("LnameEmailUname1"), data.get("ContactName1"));
				    cmtLib.clickOnloginAs();
				    switchToChildWindow();
				    cmtLib.loginVerification(data.get("ContactName1"));
				    commonLib.searchProduct(data.get("PartNumber"));
				    prodInfoLib.verifyTheManufacturerNumberInProductDetailsPage(data.get("PartNumber"));
					search.increaseQuantity(data.get("quantity"));

					commonLib.addToCartAndVerify();
					
				    canadaLib.continueToCheckout();
				    canadaLib.verifyPlaceCartLabel();
				    prodInfoLib.verifyCartPageAndPartDetailsForRecentlyItem();
					cartLib.verifySelectRwquestorGroupDropdownIsPresent();
					commonLib.clickLogOutLink(data.get("Logout_Header"));
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
			//gErrorMessage = e.toString();
			gTestStatus = false;
			ReportStatus.fnUpdateResultStatus("RequestorOptions", "Tc_CRT16", ReportStatus.strMethodName, 1, browser);
			throw new RuntimeException(e);
		}
finally {
ReportControl.fnEnableJoin();
ReportStatus.fnUpdateResultStatus("RequestorOptions", "Tc_CRT16", ReportStatus.strMethodName, counter, browser);
fnCloseTest();
ReportControl.fnNextTestJoin(nextTestJoin);
}
	}
}
