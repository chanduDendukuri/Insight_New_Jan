package com.insight.WebTest.LNL;

import java.util.Hashtable;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.insight.Lib.CMTLib;
import com.insight.Lib.CanadaLib;
import com.insight.Lib.CartLib;
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

public class LNL04_CopyLineLevelDataIPSTest  extends LineLevelInfoLib{
	
	CMTLib cmtLib = new CMTLib();
	CommonLib commonLib = new CommonLib();
	CartLib cartLib = new CartLib();
	SearchLib searchLib = new SearchLib();
	ProductDetailLib prodDetailsLib=new ProductDetailLib();
	OrderLib orderLib=new OrderLib();
	ProductDisplayInfoLib pipLib=new ProductDisplayInfoLib();
	ShipBillPayLib sbpLib=new ShipBillPayLib();
	CanadaLib canadaLib=new CanadaLib();
	ProductDetailLib prodLib=new ProductDetailLib();
	   
	    // #############################################################################################################
		// #       Name of the Test         : LNL04_CopyLineLevelDataIPSTest
		// #       Migration Author         : Cigniti Technologies
		// #
		// #       Date of Migration        : October 2019
		// #       DESCRIPTION              : This function is used to generate the Copy LineLevel Data_IPS
		// #       Parameters               : StartRow ,EndRow , nextTestJoin
		// #
		// ###############################################################################################################
		@Parameters({ "StartRow", "EndRow", "nextTestJoin" })
		@Test
		public void TC_LNL04(int StartRow,String EndRow, boolean nextTestJoin) throws Throwable {
			int counter = 0;
			try {
				int intStartRow = StartRow;
				int intEndRow = ReportControl.fnGetEndRowCunt(EndRow, "LNL04_CopyLineLevelDataIPS", TestData, "WEB_LineLevelInfo");
				for (int intCounter = intStartRow; intCounter <= intEndRow; intCounter++) {
					try {
						fnOpenTest();
						ReportStatus.fnDefaultReportStatus();
						ReportControl.intRowCount = intCounter;
						Hashtable<String, String> data = TestUtil.getDataByRowNo("LNL04_CopyLineLevelDataIPS", TestData, "WEB_LineLevelInfo", intCounter);
						TestEngineWeb.reporter.initTestCaseDescription("CopyLineLevelDataIPS");
                    
						// Login to CMT
						cmtLib.loginToCMT(data.get("Header"));
						cmtLib.searchForWebGroup(data.get("WebGrp"));
						cmtLib.clickOnTheWebGroup(data.get("WebGrp_Name"));
						cmtLib.hoverOnManageWebGroupsAndSelectOptions(data.get("Manage_Web_Grp_Options"));
						cmtLib.searchForaUserAndSelect(data.get("LnameEmailUname"), data.get("ContactName"));
						// Enable purchasing popup
						cmtLib.setPermissions(data.get("Menu_Name"), data.get("Set_Permission"));
						// login As
						cmtLib.loginAsAdminCMT();
						
						// Search for part or product  >> LENOVO >>
						searchLib.searchInHomePage(data.get("SearchText1"));
						searchLib.verifyTheResultsForSearchTerm(data.get("SearchText1"));
						cartLib.selectFirstProductDisplay();
						commonLib.contractOnProductDetailPage();
						String mfrNumber1=prodLib.getInsightPartNumberInProductInfopage();
						commonLib.addToCartAndVerify();
						orderLib.continueToCheckOutOnAddCart();
						canadaLib.verifyPlaceCartLabel();
						cartLib.verifyItemInCartByInsightPart(mfrNumber1);
						
					    // Search for another product >> Workstations >> 
						searchLib.searchInHomePage(data.get("SearchText2"));
						searchLib.verifyTheResultsForSearchTerm(data.get("SearchText2"));
						cartLib.selectFirstProductDisplay();
						commonLib.contractOnProductDetailPage();
						String mfrNumber2=prodLib.getInsightPartNumberInProductInfopage();
						commonLib.addToCartAndVerify();
						orderLib.continueToCheckOutOnAddCart();
						canadaLib.verifyPlaceCartLabel();
						cartLib.verifyItemInCartByInsightPart(mfrNumber2);
						
						// Select new contract
						searchLib.selectNewcontract(data.get("Contract_Name1"));
						// Search for another product >> LENOVO >>
						searchLib.searchInHomePage(data.get("SearchText1"));
						searchLib.verifyTheResultsForSearchTerm(data.get("SearchText1"));
						cartLib.selectFirstProductDisplay();
						String mfrNumber3=prodLib.getInsightPartNumberInProductInfopage();
						commonLib.addToCartAndVerify();
						orderLib.continueToCheckOutOnAddCart();
						canadaLib.verifyPlaceCartLabel();
						cartLib.verifyItemInCartByInsightPart(mfrNumber3);
						// Verify selected contract in cart page
						cartLib.verifyContractNameInCart(data.get("Contract_Name1"));
						cartLib.verifyContractNameInCart(data.get("Contract_Name"));   
						
						// Select new contract  - Open Market 
						searchLib.selectContract(data.get("Contract_Name2"));
						// Verify contract page
						pipLib.verifyWelcomePage();
						// Search for another product >> thinkpads
						searchLib.searchInHomePage(data.get("SearchText3"));
						searchLib.verifyTheResultsForSearchTerm(data.get("SearchText3"));
						cartLib.selectFirstProductDisplay();
						String mfrNumber4=prodLib.getInsightPartNumberInProductInfopage();
						commonLib.addToCartAndVerify();
						orderLib.continueToCheckOutOnAddCart();
						canadaLib.verifyPlaceCartLabel();
						cartLib.verifyItemInCartByInsightPart(mfrNumber4);
						
						// Verify selected 3 contracts in cart page
						cartLib.verifyContractNameInCart(data.get("OpenMarket"));
						cartLib.verifyContractNameInCart(data.get("Contract_Name1"));
						cartLib.verifyContractNameInCart(data.get("Contract_Name"));  
						orderLib.proceedToCheckout();  // Proceed to checkout
						orderLib.enterReportingDetailsInLineLevelInfo(data.get("REPORTING_FIELD_4"), data.get("REPORTING_FIELD_5"), data.get("REPORTING_FIELD_6"));
						selectDiversityPartner(data.get("Diversity_Partner1"),mfrNumber3);
						clickOnLinelevelInfoOptionalLink();
						orderLib.clickContinueOnLineLevelInfo(); // click continue on LLI 
						
						orderLib.shippingBillPay(data.get("Card_Number").toString(), data.get("Card_Name"),data.get("Month"), data.get("Year"),data.get("PO_Number"),data.get("POReleaseNumber"));
						// Contract Specific Information verification
						verifyContractSpecificInfoOnPlaceOrderPage();
						// click on edit LLI
						editLinelevelInfoOnPlaceOrderPage();
						// Verify OII bread crumb
						verifyOrderAndItemInfoBreadCrumb();
						// click on LLI optional
						clickOnLineLevelOptionalLinkByPartNum(mfrNumber1);
						//selectDiversityPartner(data.get("Diversity_Partner2"),mfrNumber3);
						clickCopyToAllLink(mfrNumber1);
						
						//verifyContractSpecificInfoOnPlaceOrderPage();
						/*clickOnLineLevelOptionalLinkByPartNum(mfrNumber2);
						verifyDiversityPartnerexists(data.get("Diversity_Partner2"),mfrNumber2);*/
						clickClearLink(mfrNumber1);
						//scrollUp();
						//clickClearLink(mfrNumber3);
						String reportingfield4= getReportingField4();
						String reportingfield5= getReportingField5();
						String reportingfield6= getReportingField6();
					   // Verifying reporting fields are empty
						assertTextStringMatching(reportingfield4, "");
						assertTextStringMatching(reportingfield5, "");
						assertTextStringMatching(reportingfield6, "");
						orderLib.enterReportingDetailsInLineLevelInfo(data.get("REPORTING_FIELD_4"), data.get("REPORTING_FIELD_5"), data.get("REPORTING_FIELD_6"));
						
						// select second options from DD 
						selectDiversityPartner(data.get("Diversity_Partner1"),mfrNumber1);
						clickCopyToAllLink(mfrNumber1);
						//clickOnLineLevelOptionalLinkByPartNum(mfrNumber2);
						verifyDiversityPartnerexists(data.get("Diversity_Partner1"),mfrNumber2);
						clickOnLineLevelOptionalLinkByPartNum(mfrNumber3);
						verifyDiversityPartnerexists(data.get("Diversity_Partner1"),mfrNumber3);
						clickClearLink(mfrNumber3);
						verifyDiversityPartnerexists("Select a partner",mfrNumber3);
						// filling all reporting fields in part 1
						orderLib.enterReportingDetailsInLineLevelInfo(data.get("REPORTING_FIELD_4"), data.get("REPORTING_FIELD_5"), data.get("REPORTING_FIELD_6"));
						
						
						// click on 3rd part and check out all the fields copied 
						// clear all in 3rd part - all parts fields will be cleared
						// make sure data is cleared
						// go back to 1st one and make sure all the fields are cleared
						// fill all the reporting fields in 1st part
						
						orderLib.clickContinueOnLineLevelInfo(); // click continue on LLI 
						
						// verify the reporting fields are exists
						verifyContractSpecificInfoOnPlaceOrderPage();
						
						commonLib.clickLogOutLink(data.get("Logout"));
						
					} catch (Exception e) {
						ReportStatus.blnStatus = false;
					//	gErrorMessage = e.getMessage();
						gTestStatus = false;
					}
					
				}
			} catch (Exception e) {
				e.printStackTrace();
				ReportStatus.blnStatus = false;
			//	gErrorMessage = e.getMessage();
				gTestStatus = false;
				ReportStatus.fnUpdateResultStatus("CopyLineLevelDataIPS", "TC_LNL04", ReportStatus.strMethodName, 1, browser);
				throw new RuntimeException(e);
			}

			finally {
	        	ReportControl.fnEnableJoin();
				ReportStatus.fnUpdateResultStatus("CopyLineLevelDataIPS", "TC_LNL04", ReportStatus.strMethodName, counter, browser);
				//fnCloseTest();
				ReportControl.fnNextTestJoin(nextTestJoin);
			}
		}


}
