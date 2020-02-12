package com.insight.WebTest.Canada;

import com.insight.Lib.CanadaLib;
import com.insight.Lib.*;
import com.insight.accelerators.ReportControl;
import com.insight.accelerators.TestEngineWeb;
import com.insight.googledrive.ReportStatus;
import com.insight.utilities.TestUtil;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.util.Hashtable;

public class CAN07_CartBasicTest extends CanadaLib{

	CommonLib CommonLib = new CommonLib();
	CMTLib cmtLib = new CMTLib();
	CanadaLib canadaLib = new CanadaLib();
	SearchLib search = new SearchLib();
	ProductDisplayInfoLib prodInfoLib = new ProductDisplayInfoLib();
	EndUserFeaturesLib end = new EndUserFeaturesLib();
	SLPLib slp = new SLPLib();
	LineLevelInfoLib line = new LineLevelInfoLib();
	OrderLib order = new OrderLib();
	CommonCanadaLib ccp = new CommonCanadaLib();
	CartLib cartLib= new CartLib();
	    // #############################################################################################################
		// #       Name of the Test         :  CAN07_CartBasicTest
		// #       Migration Author         :  Cigniti Technologies
		// #
		// #       Date of Migration        : October 2019
		// #       DESCRIPTION              : This method is to verify MenuSearch for canada
		// #       Parameters               : StartRow ,EndRow , nextTestJoin
		// #
		// ###############################################################################################################
		@Parameters({ "StartRow", "EndRow", "nextTestJoin" })
		@Test
		public void TC_CAN07(int StartRow,String EndRow, boolean nextTestJoin) throws Throwable {
			
						int counter = 0;
						try {
							int intStartRow = StartRow;
							int intEndRow = ReportControl.fnGetEndRowCunt(EndRow, "CAN07_CartBasic", TestDataInsight, "Canada");
							for (int intCounter = intStartRow; intCounter <= intEndRow; intCounter++) {
								try {
									counter = intCounter;
									fnOpenTest();
									ReportStatus.fnDefaultReportStatus();
									ReportControl.intRowCount = intCounter;
									Hashtable<String, String> data = TestUtil.getDataByRowNo("CAN07_CartBasic", TestDataInsight,
											"Canada", intCounter);
									TestEngineWeb.reporter.initTestCaseDescription("CartBasic");				

					/*	CommonLib commonLib = new CommonLib();
						CMTLib cmtLib = new CMTLib();
						CartLib cartLib = new CartLib();
						OrderLib orderLib = new OrderLib();
						ShipBillPayLib shipbLib = new ShipBillPayLib();
						CanadaLib canadaLib = new CanadaLib();
*/
									cmtLib.loginToCMT(data.get("Header"));

									cmtLib.searchForWebGroup(data.get("WebGrp"));
									cmtLib.clickOnTheWebGroup(data.get("WebGrp_Name"));
									//cmtLib.hoverOnManageWebGroupsAndSelectOptions(data.get("Manage_Web_Grp_Options"));
									cmtLib.hoverOnManageWebGroupsAndSelectOptions(data.get("Manage_Web_Grp_Options1"));
									cmtLib.searchForaUserAndSelect(data.get("LnameEmailUname"), data.get("ContactName"));
									cmtLib.loginAsAdminCMT();
									//switchToChildWindow();
									cmtLib.loginVerification(data.get("ContactName"));

									CommonLib.searchProduct(data.get("SearchItem1"));
									prodInfoLib.verifyTheManufacturerNumberInProductDetailsPage(data.get("SearchItem1"));
									//cartLib.getPartNumber();
									//prodInfoLib.verifyTheManufacturerNumberInProductDetailsPage(data.get("SearchItem1"));
									search.increaseQuantity(data.get("quantity"));

									CommonLib.addToCartAndVerify();
									//canadaLib.continueToCheckout();


									//CommonLib.addToCartAndVerify();
									canadaLib.continueToCheckout();
//adding review commentsString s1=Boolean.toString(verifyCartPageAvailablity());
									assertTrue(cartLib.verifyCartPageAvailablity(),"Cart Page loaded");
	//--------------------------------------------		Prod 1 -------------------------------------------------//

									CommonLib.searchProduct(data.get("SearchItem2"));
									//prodInfoLib.verifyTheManufacturerNumberInProductDetailsPage(data.get("SearchItem2"));
//Added new code from here please look again
									search.verifyBreadCrumbInSearchResultsPage(data.get("SearchItem2"));

									ccp.clickOnAddToCartButtonUnderProductDynamically(data.get("quantity"));
									cartLib.getPartNumber();
									//CommonLib.addToCartAndVerify();
									canadaLib.continueToCheckout();
//adding review commentsString s1=Boolean.toString(verifyCartPageAvailablity());
	//QUantity  = 2 								assertTrue(cartLib.verifyCartPageAvailablity(),"Cart Page loaded");
									prodInfoLib.enterQuantityForProductsInViewCartPage(data.get("Quantity"));
									reporter.SuccessReport("Update Quantity" ,"Quantity was update with ",data.get("Quantity"));
									CommonLib.clickOnUpdateLinkInViewCartPage(data.get("Quantity"));
//with ABC
									prodInfoLib.enterQuantityForProductsInViewCartPage(data.get("quan"));
									assertTrue(!CommonLib.clickOnUpdateLinkInViewCartPage(data.get("quan")),"Update button is not visible");
//With 0
									prodInfoLib.enterQuantityForProductsInViewCartPage(data.get("quant"));
									assertTrue(!CommonLib.clickOnUpdateLinkInViewCartPage(data.get("quant")),"Update button is not visible");
									prodInfoLib.getSummaryCartDetails();
									prodInfoLib.deleteSelectedProducts();
//Second time searching for same product after deleting
									CommonLib.searchProduct(data.get("SearchItem2"));
									prodInfoLib.verifyTheManufacturerNumberInProductDetailsPage(data.get("SearchItem2"));
									search.increaseQuantity(data.get("quantity"));

									CommonLib.addToCartAndVerify();
									canadaLib.continueToCheckout();
//adding review commentsString s1=Boolean.toString(verifyCartPageAvailablity());
									String s2=Boolean.toString(cartLib.verifyCartPageAvailablity());
									assertTrue(cartLib.verifyCartPageAvailablity(),"Cart Page loaded");

									//prodInfoLib.deleteSelectedProducts();
									ccp.clickOnEmptyCart();
		//EMpty card verification to be check
									cmtLib.clickOnLogoutlink();

									//cmtLib.loginToCMTSearchWebGrpAndUser(data.get("Header"), data.get("WebGrp"),data.get("LnameEmailUname"), data.get("ContactName"));

									cmtLib.loginToCMT(data.get("Header"));
									cmtLib.searchForWebGroup(data.get("WebGrp1"));
									cmtLib.clickOnTheWebGroup(data.get("WebGrp_Name1"));
									cmtLib.disbaleOverRidePaymentOption();
									end.clickUpdateButton();
									end.verifyupdateSuccessMessage();

									cmtLib.hoverOnManageWebGroupsAndSelectOptions(data.get("Manage_Web_Grp_Options1"));
									cmtLib.searchForaUserAndSelect(data.get("userName1"), data.get("ContactName1"));
									CommonLib.clickRolesAndPermissionsAtUserLevel();
									cmtLib.setPermissionsToDisable(data.get("menuName"), data.get("userPermission2"));//Enable Buying Enable Duplicate Order - OFF
									//cmtLib.setPermissionsToDisableOnly(data.get("userPermission3"));
									cmtLib.loginAsAdminCMT();
									cmtLib.loginVerification(data.get("ContactName1"));
									CommonLib.searchProduct(data.get("SearchItem2"));//Thin Clients
									prodInfoLib.verifyTheManufacturerNumberInProductDetailsPage(data.get("SearchItem2"));
									ccp.clickOnAddToCartButtonUnderProductDynamically(data.get("quantity"));

									String man3=cartLib.getPartNumber();

									//CommonLib.addToCartAndVerify();
									canadaLib.continueToCheckout();
//adding review commentsString s1=Boolean.toString(verifyCartPageAvailablity());
									String s4=Boolean.toString(cartLib.verifyCartPageAvailablity());
									if(cartLib.verifyCartPageAvailablity())
									{
										reporter.SuccessReport("Cart Landing Page", "Availability of Cart Landing Page is ",s4 );
									}
									else{
										reporter.failureReport("Cart Landing Page", "Availability of Cart Landing Page is ",s4,driver );
									}
									slp.verifyProccedToCheckOutbuttonExists();
									cmtLib.clickOnLogoutlink();


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
				ReportStatus.fnUpdateResultStatus("CartBasic", "TC_CAN05", ReportStatus.strMethodName, 1, browser);
				throw new RuntimeException(e);
			}
	        finally {
	        	ReportControl.fnEnableJoin();
				ReportStatus.fnUpdateResultStatus("CartBasic", "TC_CAN05", ReportStatus.strMethodName, counter, browser);
				fnCloseTest();
				ReportControl.fnNextTestJoin(nextTestJoin);
			}
		}
}

