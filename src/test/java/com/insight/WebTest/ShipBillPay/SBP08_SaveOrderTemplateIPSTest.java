package com.insight.WebTest.ShipBillPay;

import java.util.Hashtable;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.insight.Lib.CMTLib;
import com.insight.Lib.CanadaLib;
import com.insight.Lib.CartLib;
import com.insight.Lib.CommonLib;
import com.insight.Lib.OrderLib;
import com.insight.Lib.ShipBillPayLib;
import com.insight.accelerators.ReportControl;
import com.insight.accelerators.TestEngineWeb;
import com.insight.googledrive.ReportStatus;
import com.insight.utilities.TestUtil;

public class SBP08_SaveOrderTemplateIPSTest extends ShipBillPayLib{
	// #############################################################################################################
		// # Name of the Test : SBP08_SaveOrderTemplateIPS
		// # Migration Author : Cigniti Technologies
		// #
		// # Date of Migration : August 2019
		// # DESCRIPTION : This method is to perform Basic Cart operations.
		// # Parameters : StartRow ,EndRow , nextTestJoin
		// #
		// ###############################################################################################################

		@Parameters({ "StartRow", "EndRow", "nextTestJoin" })
		@Test
		public void Tc_SBP08(int StartRow, String EndRow, boolean nextTestJoin) throws Throwable {
			int counter=0;
			try {
			int intStartRow = StartRow;
			int intEndRow = ReportControl.fnGetEndRowCunt(EndRow, "SBP08_SaveOrderTemplateIPS", TestDataInsight,
					"Ship_Bill_Pay");
			for (int intCounter = intStartRow; intCounter <= intEndRow; intCounter++) {
				try {
				fnOpenTest();
				ReportStatus.fnDefaultReportStatus();
				ReportControl.intRowCount = intCounter;
				Hashtable<String, String> data = TestUtil.getDataByRowNo("SBP08_SaveOrderTemplateIPS", TestDataInsight,
						"Ship_Bill_Pay", intCounter);
				TestEngineWeb.reporter
						.initTestCaseDescription("SaveOrderTemplateIPS");
				reporter.SuccessReport("Iteration Number : ",
						"**************Iteration Number::  " + intCounter + " For:: " + data.get("LoginName") + " ::and:: "
								+ data.get("Password") + " To Validate::" + data.get("errorMessage") + "  **************",
						"");
				CommonLib commonLib = new CommonLib();
				CMTLib cmtLib = new CMTLib();
				CartLib cartLib = new CartLib();
				OrderLib orderLib = new OrderLib();
				ShipBillPayLib shipbLib = new ShipBillPayLib();
				CanadaLib canadaLib = new CanadaLib();
				
				// login
				cmtLib.loginToCMT(data.get("Header"));
				cmtLib.searchForWebGroup(data.get("WebGrp"));
				cmtLib.clickOnTheWebGroup(data.get("WebGrp_Name"));
				cmtLib.hoverOnManageWebGroupsAndSelectOptions(data.get("Manage_Web_Grp_Options"));
				cmtLib.searchForaUserAndSelect(data.get("LnameEmailUname"), data.get("ContactName"));
				cmtLib.setPermissions(data.get("Menu_Name"), data.get("Set_Permission"));
				cmtLib.loginAsAdminCMT();
				
				commonLib.clickOnAccountToolsAndClickOnProductGrp(data.get("toolsMenuName"),data.get("dropDown"));	
				deletesavedcartsortamplates();
				commonLib.searchProduct(data.get("Search_Item1"));
				verifyPartNumInProductDetailPage(data.get("Search_Item1"));
				VerifyQuantity();
				commonLib.addToCartAndVerify();
				// view cart
				canadaLib.continueToCheckout();
				cartLib.verifyCartBreadCrumb();
				// add product by quick shop
				shipbLib.AdditemsbyQuickshopAndUpdateQuantity(data.get("searchitem2"),data.get("Quantity"));
				cartLib.verifyItemInCart(data.get("searchitem2"));
				orderLib.proceedToCheckout();
				shipbLib.Addadtionalinformation(data.get("wG_HDL_Txt"), data.get("emailToEnter"), data.get("A"));
				clickCalander();
				String Date=currentDate();
				clickonTodayDate(Date);
				cartLib.clickOnContinueButtonInAddInformtion();
				shipbLib.enterReportingDetailsInLineLevelInfoSection(data.get("REPORTING FIELD_4"),
						data.get("REPORTING FIELD_5"),data.get("Wg_LNL_Txt"));
				Thread.sleep(3000);
				scrollBottom();
				scrollBottom();
				Thread.sleep(3000);
				clickExpand();
				verifyCopiedText();
				orderLib.clickContinueOnLineLevelInfo();
				shipbLib.clickstoredAddress(data.get("Text"));
				orderLib.shippingBillPayContinueButton();
				selectCarrierandGrounOption(data.get("Carrier"));
				shipbLib.clickstoredAddress(data.get("Text"));
				orderLib.billingAddressContinueButton(); 
				orderLib.selectPaymentInfoMethodCreditCard(data.get("cardNumber"), data.get("cardName"), data.get("month"),
						data.get("year"),data.get("PONumber"),data.get("POReleaseNumber"));
				orderLib.clickOnReviewOrderButton();
				String Tamplate="CartTemplate"+getRandomNumeric(4);
				//Template
				SaveasTemplete(Tamplate);
				scrollUp();
				commonLib.clickOnAccountToolsAndClickOnProductGrp(data.get("toolsMenuName"),data.get("dropDown"));	
				savecartContinueToCheckout(Tamplate);
				//cartLib.verifyItemInCart(data.get("searchitem1"));
				//cartLib.verifyItemInCart(data.get("searchitem2"));
				orderLib.proceedToCheckout();
				shipbLib.emailinfoExists(data.get("emailToEnter"));
				shipbLib.WG_HDL_TxtinfoExists(data.get("wG_HDL_Txt"));
				cartLib.clickOnContinueButtonInAddInformtion();
				shipbLib.reportingField4infoExists(data.get("REPORTING FIELD_4"));
				shipbLib.reportingField5infoExists(data.get("REPORTING FIELD_5"));
				shipbLib.reportingFieldWG_LNLinfoExists(data.get("Wg_LNL_Txt"));
				clickExpand();
				shipbLib.copyToall();
				verifyCopiedText();
				orderLib.clickContinueOnLineLevelInfo();
				VerifySoldtoAddress();
				orderLib.shippingBillPayContinueButton();
				selectCarrierandGrounOption(data.get("Carrier"));
				VerifySoldtoAddress();
				orderLib.billingAddressContinueButton(); 
				scrollUp();
				//Deletesavedcarts
				shipbLib.Deletesavedcarts(data.get("toolsMenuName"), data.get("dropDown"),Tamplate);
				commonLib.clickLogOutLink(data.get("Logout_Header"));
				

				} catch (Exception e) {
					ReportStatus.blnStatus = false;
					//gErrorMessage = e.getMessage();
					System.out.println(e.getMessage());
					gTestStatus = false;
				}
			
			}
		} catch (Exception e) {
			e.printStackTrace();
			ReportStatus.blnStatus = false;
			//gErrorMessage = e.getMessage();
			gTestStatus = false;
			ReportStatus.fnUpdateResultStatus("SaveOrderTemplateIPS", "SBP08", ReportStatus.strMethodName, 1, browser);
			throw new RuntimeException(e);
		}


			finally {
	        	ReportControl.fnEnableJoin();
				ReportStatus.fnUpdateResultStatus("SaveOrderTemplateIPS", "SBP08", ReportStatus.strMethodName, counter, browser);
				fnCloseTest();
				ReportControl.fnNextTestJoin(nextTestJoin);
			}
	}

}
