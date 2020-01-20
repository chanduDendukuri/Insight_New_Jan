package com.insight.WebTest.SLP;

import java.util.Hashtable;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.insight.Lib.CMTLib;
import com.insight.Lib.CanadaLib;
import com.insight.Lib.CartLib;
import com.insight.Lib.CommonLib;
import com.insight.Lib.OrderLib;
import com.insight.Lib.ProductDetailLib;
import com.insight.Lib.ProductDisplayInfoLib;
import com.insight.Lib.SLPLib;
import com.insight.Lib.SearchLib;
import com.insight.Lib.ShipBillPayLib;
import com.insight.accelerators.ReportControl;
import com.insight.accelerators.TestEngineWeb;
import com.insight.googledrive.ReportStatus;
import com.insight.utilities.TestUtil;

public class SLP03_SPLA_NoSPLA4uTest extends SLPLib{
	
	CMTLib cmtLib = new CMTLib();
	CommonLib commonLib = new CommonLib();
	CartLib cartLib = new CartLib();
	SearchLib searchLib = new SearchLib();
	ProductDetailLib prodDetailsLib=new ProductDetailLib();
	OrderLib orderLib=new OrderLib();
	ProductDisplayInfoLib pipLib=new ProductDisplayInfoLib();
	ShipBillPayLib sbpLib=new ShipBillPayLib();
	CanadaLib canadaLib=new CanadaLib();
	   
	    // #############################################################################################################
		// #       Name of the Test         :  SLP03_SPLA_NoSPLA4uTest
		// #       Migration Author         :  Cigniti Technologies
		// #
		// #       Date of Migration        : October 2019
		// #       DESCRIPTION              : This Test is used to Test SPLA_NoSPLA4uTest
		// #       Parameters               : StartRow ,EndRow , nextTestJoin
		// #
		// ###############################################################################################################
		@Parameters({ "StartRow", "EndRow", "nextTestJoin" })
		@Test
		public void TC_SLP03(int StartRow,String EndRow, boolean nextTestJoin) throws Throwable {
			int counter = 0;
			try {
				int intStartRow = StartRow;
				int intEndRow = ReportControl.fnGetEndRowCunt(EndRow, "SLP03_SPLA_NoSPLA4uTest", TestData, "SLP");
				for (int intCounter = intStartRow; intCounter <= intEndRow; intCounter++) {
					try {
						fnOpenTest();
						ReportStatus.fnDefaultReportStatus();
						ReportControl.intRowCount = intCounter;
						Hashtable<String, String> data = TestUtil.getDataByRowNo("SLP03_SPLA_NoSPLA4uTest", TestData, "SLP", intCounter);
						TestEngineWeb.reporter
								.initTestCaseDescription("SLPProrationMicrosoft");
						reporter.SuccessReport("Iteration Number : ",
								"**************Iteration Number::  " + intCounter + " For:: " + data.get("LoginName") + " ::and:: "
										+ data.get("Password") + " To Validate::" + data.get("errorMessage") + "  **************","");
						
						// Login to CMT
						cmtLib.loginToCMT(data.get("Header"));
						cmtLib.searchForWebGroup(data.get("WebGrp"));
						cmtLib.clickOnTheWebGroup(data.get("WebGrp_Name"));
						cmtLib.hoverOnManageWebGroupsAndSelectOptions(data.get("Manage_Web_Grp_Options"));
						cmtLib.searchForaUserAndSelect(data.get("LnameEmailUname"), data.get("ContactName"));
						//User Requires Approval;OFF";
						cmtLib.setPermissionsToDisable(data.get("Menu_Name"), data.get("Set_Permission"));
						cmtLib.loginAsAdminCMT();
						
						// Login Verification 
						cmtLib.loginVerification(data.get("ContactName"));
						// account tools >> Software License Agreements
						commonLib.clickOnAccountToolsAndClickOnProductGrp(data.get("Tools_Menu"), data.get("Tools_Menu_DD"));
						// Select Software  Lic Agreements
				     	canadaLib.selectSPLADetailsProductCheckBox(data.get("SPLA"));
						// verify search results and select first product
				     	searchLib.verifysearchResultsPage();
				        // select first product from search results
				     	String firstProdPrice1 = pipLib.getFirtProductListPrice();
				     	cartLib.selectFirstProductDisplay();
				     	pipLib.verifyThePriceInProdDetailsPage(firstProdPrice1); // Verifying price in product details page
				     	commonLib.addToCartAndVerify();
				     	orderLib.continueToCheckOutOnAddCart();
				     	cartLib.clickAndVerifyExportCart(data.get("Order_Utilities"));
				     	validateCartExportAndSheetName();
				     	commonLib.clickLogOutLink(data.get("Logout"));
				     	
				     	// Back to CMT
				     	cmtLib.navigateBackToCMT();
				     	//User Requires Approval;ON";
						cmtLib.setPermissions(data.get("Menu_Name"), data.get("Set_Permission"));
                        cmtLib.loginAsAdminCMT();
						
						// Login Verification 
						cmtLib.loginVerification(data.get("ContactName"));
						// account tools >> Software License Agreements
						commonLib.clickOnAccountToolsAndClickOnProductGrp(data.get("Tools_Menu"), data.get("Tools_Menu_DD"));
						// Select Software  Lic Agreements
				     	canadaLib.selectSPLADetailsProductCheckBox(data.get("SPLA"));
						// verify search results and select first product
				     	searchLib.verifysearchResultsPage();
				        // select first product from search results
				     	String firstProdPrice2 = pipLib.getFirtProductListPrice();
				     	cartLib.selectFirstProductDisplay();
				     	pipLib.verifyThePriceInProdDetailsPage(firstProdPrice2); // Verifying price in product details page
				     	commonLib.addToCartAndVerify();
				     	orderLib.continueToCheckOutOnAddCart();
				     	verifyProccedToCheckOutbuttonExists();
				     	verifyUserRequiresApprovelAlertMessage();
				     	commonLib.clickLogOutLink(data.get("Logout"));
				     	
				    	// Back to CMT
				     	cmtLib.navigateBackToCMT();
				     	//User Requires Approval;OFF";
						cmtLib.setPermissionsToDisable(data.get("Menu_Name"), data.get("Set_Permission"));
				     	
				     	
					} catch (Exception e) {
						ReportStatus.blnStatus = false;
						//gErrorMessage = e.getMessage();
						gTestStatus = false;
					}
					
				}
			} catch (Exception e) {
				e.printStackTrace();
				ReportStatus.blnStatus = false;
				//gErrorMessage = e.getMessage();
				gTestStatus = false;
				ReportStatus.fnUpdateResultStatus("SPLA_NoSPLA4uTest", "SLP03", ReportStatus.strMethodName, 1, browser);
				throw new RuntimeException(e);
			}

			finally {
            	ReportControl.fnEnableJoin();
            	ReportStatus.fnUpdateResultStatus("SPLA_NoSPLA4uTest", "SLP03", ReportStatus.strMethodName, counter, browser);
				fnCloseTest();
				ReportControl.fnNextTestJoin(nextTestJoin);
			}
		}

}
