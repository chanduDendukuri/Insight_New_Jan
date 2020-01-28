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
import com.insight.Lib.RequisitionProcessingLib;
import com.insight.Lib.SLPLib;
import com.insight.Lib.SearchLib;
import com.insight.Lib.ShipBillPayLib;
import com.insight.accelerators.ReportControl;
import com.insight.accelerators.TestEngineWeb;
import com.insight.googledrive.ReportStatus;
import com.insight.utilities.TestUtil;

public class SLP02_SLPProrationMicrosoftTest extends SLPLib{
	CMTLib cmtLib = new CMTLib();
	CommonLib commonLib = new CommonLib();
	CartLib cartLib = new CartLib();
	SearchLib searchLib = new SearchLib();
	ProductDetailLib prodDetailsLib=new ProductDetailLib();
	OrderLib orderLib=new OrderLib();
	ProductDisplayInfoLib pipLib=new ProductDisplayInfoLib();
	ShipBillPayLib sbpLib=new ShipBillPayLib();
	CanadaLib canadaLib=new CanadaLib();
	RequisitionProcessingLib ReqLib = new RequisitionProcessingLib();
	   
	    // #############################################################################################################
		// #       Name of the Test         :  SLP02_SLPProrationMicrosoft
		// #       Migration Author         :  Cigniti Technologies
		// #
		// #       Date of Migration        : October 2019
		// #       DESCRIPTION              : This Test is used to Test SLP Proration Microsoft
		// #       Parameters               : StartRow ,EndRow , nextTestJoin
		// #
		// ###############################################################################################################
		@Parameters({ "StartRow", "EndRow", "nextTestJoin" })
		@Test
		public void TC_SLP02(int StartRow,String EndRow, boolean nextTestJoin) throws Throwable  {
			int counter = 0;
			try {
				int intStartRow = StartRow;
				int intEndRow = ReportControl.fnGetEndRowCunt(EndRow, "SLP02_SLPProrationMicrosoft", TestData, "SLP");
				for (int intCounter = intStartRow; intCounter <= intEndRow; intCounter++) {
					try {
						fnOpenTest();
						ReportStatus.fnDefaultReportStatus();
						ReportControl.intRowCount = intCounter;
						Hashtable<String, String> data = TestUtil.getDataByRowNo("SLP02_SLPProrationMicrosoft", TestData, "SLP", intCounter);
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
						//User Requires Approval;ON";
						cmtLib.setPermissions(data.get("Menu_Name"), data.get("Set_Permission"));
						cmtLib.loginAsAdminCMT();
						
						// account tools >> Software License Agreements
						commonLib.clickOnAccountToolsAndClickOnProductGrp(data.get("Tools_Menu"), data.get("Tools_Menu_DD"));
						// Select Software  Lic Agreements
				     	canadaLib.selectSPLADetailsProductCheckBox(data.get("SPLA"));
						// verify search results and select first product
				     	searchLib.verifysearchResultsPage();
				     	
				       // Search for part or product and add to cart : part : 394-00559-SLP
				     	searchLib.searchInHomePage(data.get("SearchText"));
				        // Stock only
						searchLib.removeTheFilterForInStockOnly(data.get("In_Stock_Only"));
						String price = getpricefromProductdetailpage(); 
						Float Actualprice = Float.parseFloat(price.replace("USD $", ""));
						pipLib.verifyTheManufacturerNumberInProductDetailsPage(data.get("SearchText"));
						pipLib.enterQuantityOnProductDetailsPage(data.get("Quantity"));
				     	commonLib.addToCartAndVerify();
				     	orderLib.continueToCheckOutOnAddCart();
				    	canadaLib.verifyPlaceCartLabel();
				     					     	
				    	String subTotal=sbpLib.getTotalAmountInCart(data.get("SubTotal_label"));
					    Float subTotalAmount = Float.parseFloat(subTotal.replace("$", ""));
					     // verifying Prorated Price of product in cart
					   verifyProrationincartpage(data.get("SearchText"), Actualprice);
					    // verifying Prorated Price with the subtotal price in cart price
					   verifyProrationPrice(subTotalAmount, Actualprice);
				     	
					    //Proceed to checkout
						 orderLib.proceedToCheckout();
						 orderLib.clickOnAdditionalInfoContinueButton();
						 orderLib.clickContinueOnLineLevelInfo();   // Click continue on Line level Info
						 canadaLib.verifySBP();
						 orderLib.shippingBillPayContinueButton();
                         orderLib.billingAddressContinueButton();
				     	 orderLib.selectPaymentInfoMethodCreditCard(data.get("cardNumber"), data.get("cardName"), data.get("month"), data.get("year"),data.get("poNumebr"),data.get("POReleaseNumber"));
				     	orderLib.clickOnReviewRequisitionButton(); 
				     	String totalAmt=sbpLib.getTotalAmountInCart(data.get("TotalAmount_label"));
						Double totalAmount = Double.valueOf(totalAmt.replace("$", ""));
						verifyProrationPrice(Actualprice, totalAmount);
				     	
						String summaryAmount = cartLib.getSummaryAmountInCart();
						clickPlaceRequisition();
						//Verify Receipt page
						String ReferenceNumber = sbpLib.getReferenceNum();
						verifyReceiptPageOrderDetails(summaryAmount);
						orderLib.verifyReceiptVerbiage();
						commonLib.clickLogOutLink(data.get("LogOut"));
				     	
						// navigate back to CMT
						cmtLib.navigateBackToCMT();
						cmtLib.hoverOverMasterGroupAndSelectChangeGrp();
						cmtLib.searchForWebGroup(data.get("WebGrp"));
						cmtLib.clickOnTheWebGroup(data.get("WebGrp_Name"));
						cmtLib.hoverOnManageWebGroupsAndSelectOptions(data.get("Manage_Web_Grp_Options"));
						cmtLib.searchForaUserAndSelect(data.get("LnameEmailUname1"), data.get("ContactName1"));
						cmtLib.loginAsAdminCMT(); // login to Uat1
						
						commonLib.clickOnInsightLogoOnHomePage();
						// Select my requisition history from account tools
						commonLib.clickOnAccountToolsAndClickOnProductGrp(data.get("Tools_Menu1"), data.get("Tools_Menu_DD1"));
						selectReferenceNumFromRequisitionSearchResults(ReferenceNumber);
						selectApproveRadioButtonOnApprovalManagementPage();
						
						
						ReqLib.clickUpdateInApprovalManagmentPage();
						clickonEnterNewcard();
						ReqLib.enterNewcarDetails(data.get("cardtype"), data.get("cardNum"), data.get("cardName"));
						ReqLib.continuebutton();
						ReqLib.verifyApproveRequisitionStatus();
						
						// updateRequisitionAndVerify(ReferenceNumber);
						commonLib.clickLogOutLink(data.get("LogOut"));  // Logout End of test
						
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
				ReportStatus.fnUpdateResultStatus("SLPProrationMicrosoft", "SLP02", ReportStatus.strMethodName, 1, browser);
				throw e; 
			}

			finally {
            	ReportControl.fnEnableJoin();
            	ReportStatus.fnUpdateResultStatus("SLPProrationMicrosoft", "SLP02", ReportStatus.strMethodName, counter, browser);
				fnCloseTest();
				ReportControl.fnNextTestJoin(nextTestJoin);
			}
		}
}
