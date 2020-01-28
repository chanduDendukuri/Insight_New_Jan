package com.insight.WebTest.MIC;

import java.util.Hashtable;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.insight.Lib.CMTLib;
import com.insight.Lib.CartLib;
import com.insight.Lib.CommonLib;
import com.insight.Lib.MarriottIntlCorpLib;
import com.insight.Lib.ShipBillPayLib;
import com.insight.accelerators.ReportControl;
import com.insight.accelerators.TestEngineWeb;
import com.insight.googledrive.ReportStatus;
import com.insight.utilities.TestUtil;

public class MIC04_PAAPPQuoteTest extends MarriottIntlCorpLib{//MIC04_PAAPPQuote
	
	CommonLib commonLib = new CommonLib();
	CMTLib cmtLib = new CMTLib();
	CartLib cartLib = new CartLib();
	ShipBillPayLib shipbLib = new ShipBillPayLib();
	
	    // #############################################################################################################
		// # Name of the Test : MIC04_PAAPPQuote
		// # Migration Author : Cigniti Technologies
		// #
		// # Date of Migration : OCT 2019
		// # DESCRIPTION : This method is to perform Basic Cart operations.
		// # Parameters : StartRow ,EndRow , nextTestJoin
		// #
		// ###############################################################################################################
		@Parameters({ "StartRow", "EndRow", "nextTestJoin" })
		@Test
		public void Tc_MIC04(int StartRow, String EndRow, boolean nextTestJoin) throws Throwable {
	
				
				int counter = 0;
				try {
					int intStartRow = StartRow;
					int intEndRow = ReportControl.fnGetEndRowCunt(EndRow, "MIC04_PAAPPQuote", TestDataInsight,
							"MarriottIntl_Corp");
					for (int intCounter = intStartRow; intCounter <= intEndRow; intCounter++) {
						try {
							counter = intCounter;
							fnOpenTest();
							ReportStatus.fnDefaultReportStatus();
							ReportControl.intRowCount = intCounter;
							Hashtable<String, String> data = TestUtil.getDataByRowNo("MIC04_PAAPPQuote", TestDataInsight,
									"MarriottIntl_Corp", intCounter);
							TestEngineWeb.reporter.initTestCaseDescription("PAAPPQuote");
				cmtLib.loginToCMTSearchWebGrpAndUser(data.get("Header"), data.get("WebGrp"), data.get("LnameEmailUname"),
						data.get("Contact_Name"));
				cmtLib.clickOnRolesAndPermissionsAndSetPermission(data.get("Menu_Name"), data.get("Set_Permission"));
				cmtLib.loginAsAdminCMT();
				handleinsightpopup();
				// METHOD TO SEARCH AND SWITCH ACCOUNT
				SearchAndswitchtoaccount(data.get("AccountName"));
				Thread.sleep(4000);
				handleinsightpopup();
				// WELCOME TO E PROCUREMNET PAGE
				VerifyWelcometoeProcurementpage();
				// COMPANY STATNDARDS LINK
				CompanystandardslinkandProductGrp(data.get("productGroup1"), data.get("productName1"));
				handleinsightpopup();
				// verify qunatitiy price partnum
				Verifypartnum(data.get("partnum1"));
				VerifypartPrice(data.get("partprice1"));
				Addradiobutton(data.get("partnum1"));
				Setquantity(data.get("partnum1"),data.get("value"));
				// cart
				handleinsightpopup();
				// VERIFY ITEM IN CART
				Thread.sleep(5000);
				clickOnViewCart();
				//commonLib.spinnerImage();
				handleinsightpopup();
				Thread.sleep(4000);
				//cartLib.verifyItemInCart(data.get("partnum1"));
				Verifypartnum(data.get("partnum1"));
				Thread.sleep(4000);
				// company standrads
				SelectCPPFRomAccounttools(data.get("toolsMenuName"), data.get("dropDown"), data.get("productGroup2"),
						data.get("productName2"));
				handleinsightpopup();
				// verify qunatitiy price partnum
				Verifypartnum(data.get("partnum2"));
				VerifypartPrice(data.get("partprice2"));
				Addcheckbox(data.get("partnum2"));
				Setquantity( data.get("partnum2"),data.get("value"));
				// click add to order
				// cart
				handleinsightpopup();
				// VERIFY ITEM IN CART
				Thread.sleep(5000);
				clickOnViewCart();
				Thread.sleep(4000);
				Verifypartnum(data.get("partnum2"));
				Thread.sleep(4000);
				shipbLib.AdditemsbyQuickshop(data.get("PartNum3"));
				Thread.sleep(4000);
				cartLib.verifyItemInCart(data.get("PartNum3"));
				SaveAsQuote();
				typeattention(data.get("AccountName"));
				VerifyQuoteNameinsaveasquotepage();
				VerifyQuoteNotesinsaveasquotepage();
				Toclicksaveasquoteincompanystandardspage();
				// CLICK QUOTE
				handleinsightpopup();
				VerifySuccessmsgofQuoteandRefNum();
				VerifyQuoteName();
				VerifyQuoteNotes();
				commonLib.clickLogOutLink(data.get("Logout_Header"));
				Thread.sleep(5000);
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
		ReportStatus.fnUpdateResultStatus("PAAPPQuote", "Tc_MIC04", ReportStatus.strMethodName, 1, browser);
		throw new RuntimeException(e);
	} finally {
		ReportControl.fnEnableJoin();
		ReportStatus.fnUpdateResultStatus("PAAPPQuote", "Tc_MIC04", ReportStatus.strMethodName, counter, browser);
		fnCloseTest();
		ReportControl.fnNextTestJoin(nextTestJoin);
	}
}
}

