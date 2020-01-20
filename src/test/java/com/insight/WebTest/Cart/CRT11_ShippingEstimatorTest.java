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

public class CRT11_ShippingEstimatorTest extends CartLib {
	CommonLib commonLib = new CommonLib();
	CMTLib cmtLib = new CMTLib();
	CartLib cartLib = new CartLib();
	CanadaLib canadaLib = new CanadaLib();

	// #############################################################################################################
	// # Name of the Test : CRT11_ShippingEstimator
	// # Migration Author : Cigniti Technologies
	// #
	// # Date of Migration : August 2019
	// # DESCRIPTION : This method is to perform Basic Cart operations.
	// # Parameters : StartRow ,EndRow , nextTestJoin
	// #
	// ###############################################################################################################

	@Parameters({ "StartRow", "EndRow", "nextTestJoin" })
	@Test
	public void Tc_CRT11(int StartRow, String EndRow, boolean nextTestJoin) throws Throwable {
	
		int counter = 0;
		try {
			int intStartRow = StartRow;
			int intEndRow = ReportControl.fnGetEndRowCunt(EndRow, "CRT11_ShippingEstimator", TestDataInsight,
					"Web_Cart");
			for (int intCounter = intStartRow; intCounter <= intEndRow; intCounter++) {
				try {
					counter = intCounter;
					fnOpenTest();
					ReportStatus.fnDefaultReportStatus();
					ReportControl.intRowCount = intCounter;
					Hashtable<String, String> data = TestUtil.getDataByRowNo("CRT11_ShippingEstimator", TestDataInsight,
							"Web_Cart", intCounter);
					TestEngineWeb.reporter.initTestCaseDescription("ShippingEstimator");

					cmtLib.loginToCMT(data.get("header"));
					cmtLib.searchForWebGroup(data.get("WebGrp"));
					cmtLib.clickOnTheWebGroup(data.get("WebGrp_Name"));
					cmtLib.setCustomerLevelPermissionsOFF(data.get("customerPermissions"));
					cmtLib.hoverOnManageWebGroupsAndSelectOptions(data.get("ManageWebGrpOptions"));
					cmtLib.searchForaUserAndSelect(data.get("LnameEmailUname"), data.get("ContactName"));
					cmtLib.loginAsAdminCMT();
					commonLib.searchProduct(data.get("SearchItem1"));
					commonLib.addFirstDisplyedItemToCartAndVerify();
					canadaLib.continueToCheckout();
					cartLib.verifyShippingestimatorIsNotPresent();
					commonLib.clickLogOutLink("Logout");
					cmtLib.navigateBackToCMT();
					cmtLib.hoverOverMasterGroupAndSelectChangeGrp();
					cmtLib.searchForWebGroup(data.get("WebGrp"));
					cmtLib.clickOnTheWebGroup(data.get("WebGrp_Name"));
					cmtLib.setCustomerLevelPermissionsON(data.get("customerPermissions"));
					cmtLib.hoverOnManageWebGroupsAndSelectOptions(data.get("ManageWebGrpOptions"));
					cmtLib.searchForaUserAndSelect(data.get("LnameEmailUname"), data.get("ContactName"));
					cmtLib.AssigntheusertoServiceLevelShippingwithnodefault(data.get("menuName"),
							data.get("user_Permissions"), data.get("indexvalue"));
					cmtLib.clickupdateatDefaultShippingOption();
					cmtLib.loginAsAdminCMT();
					commonLib.searchProduct(data.get("SearchItem1"));
					commonLib.addFirstDisplyedItemToCartAndVerify();
					canadaLib.continueToCheckout();
					cartLib.verifyShippingestimator();
					cartLib.verifyShippingestimatorshippingCarrier(data.get("postalcode"), data.get("upsCarrier"),
							data.get("fedexCarrier"));
					cartLib.clickotherthanUSDandFedEx(data.get("postalcode"));
					commonLib.clickLogOutLink("Logout");
					cmtLib.navigateBackToCMT();
					cmtLib.hoverOverMasterGroupAndSelectChangeGrp();
					cmtLib.searchForWebGroup(data.get("WebGrp"));
					cmtLib.clickOnTheWebGroup(data.get("WebGrp_Name"));
					cmtLib.setCustomerLevelPermissionsON(data.get("customerPermissions"));
					cmtLib.hoverOnManageWebGroupsAndSelectOptions(data.get("ManageWebGrpOptions"));
					cmtLib.searchForaUserAndSelect(data.get("LnameEmailUname"), data.get("ContactName"));
					cmtLib.usertoServiceLevelShippingwithOnlyFedex(data.get("menuName"), data.get("user_Permissions"),
							data.get("text1"));
					cmtLib.clickupdateatDefaultShippingOption();
					cmtLib.loginAsAdminCMT();
					commonLib.searchProduct(data.get("SearchItem1"));
					commonLib.addFirstDisplyedItemToCartAndVerify();
					canadaLib.continueToCheckout();
					cartLib.verifyShippingestimator();
					cartLib.VerifyonlyFedExoptions(data.get("postalcode"), data.get("fedexCarrier"));
					commonLib.clickLogOutLink("Logout");
					cmtLib.navigateBackToCMT();
					cmtLib.hoverOverMasterGroupAndSelectChangeGrp();
					cmtLib.searchForWebGroup(data.get("WebGrp"));
					cmtLib.clickOnTheWebGroup(data.get("WebGrp_Name"));
					cmtLib.hoverOnManageWebGroupsAndSelectOptions(data.get("ManageWebGrpOptions"));
					cmtLib.searchForaUserAndSelect(data.get("LnameEmailUname"), data.get("ContactName"));
					cmtLib.AssigntheusertoServiceLevelShippingwithnodefault(data.get("menuName"),
							data.get("user_Permissions"), data.get("indexvalue"));
					cmtLib.clickupdateatDefaultShippingOption();
					cmtLib.loginAsAdminCMT();
					Thread.sleep(3000);
					commonLib.searchProduct(data.get("SearchItem1"));
					commonLib.addFirstDisplyedItemToCartAndVerify();
					cmtLib.navigateBackToCMT();
					cmtLib.hoverOverMasterGroupAndSelectChangeGrp();
					cmtLib.searchForWebGroup(data.get("WebGrp"));
					cmtLib.clickOnTheWebGroup(data.get("WebGrp_Name"));
					cmtLib.setCustomerLevelPermissionsOFF(data.get("customerPermissions"));
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
						ReportStatus.fnUpdateResultStatus("ShippingEstimator", "Tc_CRT11", ReportStatus.strMethodName, 1, browser);
						throw new RuntimeException(e);
					}
	finally {
		ReportControl.fnEnableJoin();
		ReportStatus.fnUpdateResultStatus("ShippingEstimator", "Tc_CRT11", ReportStatus.strMethodName, counter, browser);
		fnCloseTest();
		ReportControl.fnNextTestJoin(nextTestJoin);
	}
				}
				}