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
	SearchLib searchLib=new SearchLib();
	ProductDisplayInfoLib prodInfoLib=new ProductDisplayInfoLib();
	ProductDetailLib prodLib=new ProductDetailLib();
	OrderLib orderLib=new OrderLib();
	ShipBillPayLib sbpLib=new ShipBillPayLib();

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
					searchLib.verifyBreadCrumbInSearchResultsPage(data.get("SearchItem1"));
					cartLib.selectFirstProductDisplay();
					String mfrNumber1=prodLib.getInsightPartNumberInProductInfopage();
					// Cart verification
					commonLib.addToCartAndVerify();
					orderLib.continueToCheckOutOnAddCart();
					canadaLib.verifyPlaceCartLabel();
					commonLib.spinnerImage();
					cartLib.verifyItemInCartByInsightPart(mfrNumber1);
					prodInfoLib.verifyCartPageAndPartDetails();
					cartLib.verifyShippingestimatorIsNotPresent();
					commonLib.clickLogOutLink("Logout");   ///  --   1st logout
					
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
					searchLib.verifyBreadCrumbInSearchResultsPage(data.get("SearchItem1"));
					cartLib.selectFirstProductDisplay();
					String mfrNumber2=prodLib.getInsightPartNumberInProductInfopage();
					// Cart verification
					commonLib.addToCartAndVerify();
					orderLib.continueToCheckOutOnAddCart();
					canadaLib.verifyPlaceCartLabel();
					cartLib.verifyShippingestimator();
					cartLib.verifyShippingestimatorshippingCarrier(data.get("postalcode"), data.get("upsCarrier"),
							data.get("fedexCarrier"));
					String shippingEstimateBefore=getShippingEstimateInCart().replace("$", "");
					cartLib.clickotherthanUSDandFedEx(data.get("postalcode"));
					String shippingEstimateAfter=getShippingEstimateInCart().replace("$", "");
					if(Float.valueOf(shippingEstimateBefore)<Float.valueOf(shippingEstimateAfter)) {
						reporter.SuccessReport("Shipping Estimate Updated on Cart Page", "Shipping Estimate is Updated", "Shipping Estimate before and after Select Option: *Shipping estimateUSD $"+shippingEstimateBefore+ "and *Shipping estimateUSD $"+shippingEstimateAfter, driver);
					}else {
						reporter.failureReport("Shipping Estimate Update on Cart Page", "Shipping Estimate is not Updated", "", driver);
					}
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
					searchLib.verifyBreadCrumbInSearchResultsPage(data.get("SearchItem1"));
					cartLib.selectFirstProductDisplay();
					String mfrNumber3=prodLib.getInsightPartNumberInProductInfopage();
					// Cart verification
					commonLib.addToCartAndVerify();
					orderLib.continueToCheckOutOnAddCart();
					cartLib.verifyShippingestimator();
					cartLib.VerifyonlyFedExoptions(data.get("postalcode2"), data.get("fedexCarrier"));
					commonLib.clickLogOutLink("Logout");    // -- 3rd logout 
					
					
					cmtLib.navigateBackToCMT();
					/*cmtLib.hoverOverMasterGroupAndSelectChangeGrp();
					cmtLib.searchForWebGroup(data.get("WebGrp"));
					cmtLib.clickOnTheWebGroup(data.get("WebGrp_Name"));
					cmtLib.hoverOnManageWebGroupsAndSelectOptions(data.get("ManageWebGrpOptions"));
					cmtLib.searchForaUserAndSelect(data.get("LnameEmailUname"), data.get("ContactName"));*/
					cmtLib.AssigntheusertoServiceLevelShippingwithnodefault(data.get("menuName"),
							data.get("user_Permissions"), data.get("indexvalue"));
					cmtLib.clickupdateatDefaultShippingOption();
					
					cmtLib.loginAsAdminCMT();
					Thread.sleep(3000);
					commonLib.searchProduct(data.get("SearchItem2"));
					searchLib.verifyBreadCrumbInSearchResultsPage(data.get("SearchItem2"));
					cartLib.selectFirstProductDisplay();
					String mfrNumber4=prodLib.getInsightPartNumberInProductInfopage();
					// Cart verification
					commonLib.addToCartAndVerify();
					orderLib.continueToCheckOutOnAddCart();
					canadaLib.verifyPlaceCartLabel();
					/*cmtLib.navigateBackToCMT();
					cmtLib.hoverOverMasterGroupAndSelectChangeGrp();
					cmtLib.searchForWebGroup(data.get("WebGrp"));
					cmtLib.clickOnTheWebGroup(data.get("WebGrp_Name"));
					cmtLib.setCustomerLevelPermissionsOFF(data.get("customerPermissions"));
					   System.out.println("Test completed");*/
		 				
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