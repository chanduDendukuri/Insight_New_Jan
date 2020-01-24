package com.insight.WebTest.MIC;

import java.util.Hashtable;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.insight.Lib.CMTLib;
import com.insight.Lib.CartLib;
import com.insight.Lib.CommonLib;
import com.insight.Lib.MarriottIntlCorpLib;
import com.insight.accelerators.ReportControl;
import com.insight.accelerators.TestEngineWeb;
import com.insight.googledrive.ReportStatus;
import com.insight.utilities.TestUtil;

public class MIC05_MGSPropAssocQuoteTest extends MarriottIntlCorpLib {

	CommonLib commonLib = new CommonLib();
	CMTLib cmtLib = new CMTLib();
	CartLib cartLib = new CartLib();
	
	// #############################################################################################################
	// # Name of the Test : MIC05_MGSPropAssocQuote
	// # Migration Author : Cigniti Technologies
	// #
	// # Date of Migration : OCT 2019
	// # DESCRIPTION : This method is to perform Basic Cart operations.
	// # Parameters : StartRow ,EndRow , nextTestJoin
	// #
	// ###############################################################################################################
	
	@Parameters({ "StartRow", "EndRow", "nextTestJoin" })
	@Test
	public void Tc_MIC05(int StartRow, String EndRow, boolean nextTestJoin) throws Throwable {

			
			int counter = 0;
			try {
				int intStartRow = StartRow;
				int intEndRow = ReportControl.fnGetEndRowCunt(EndRow, "MIC05_MGSPropAssocQuote", TestDataInsight,
						"MarriottIntl_Corp");
				for (int intCounter = intStartRow; intCounter <= intEndRow; intCounter++) {
					try {
						counter = intCounter;
						fnOpenTest();
						ReportStatus.fnDefaultReportStatus();
						ReportControl.intRowCount = intCounter;
						Hashtable<String, String> data = TestUtil.getDataByRowNo("MIC05_MGSPropAssocQuote", TestDataInsight,
								"MarriottIntl_Corp", intCounter);
						TestEngineWeb.reporter.initTestCaseDescription("MGSPropAssocQuote");
			cmtLib.loginToCMTSearchWebGrpAndUser(data.get("Header"), data.get("WebGrp"), data.get("LnameEmailUname"),
					data.get("Contact_Name"));
			cmtLib.clickOnRolesAndPermissionsAndSetPermission(data.get("Menu_Name"), data.get("Set_Permission"));// Enable
			//Login																										// Purchasing
			cmtLib.loginAsAdminCMT();
			handleinsightpopup();
			// METHOD TO SEARCH AND SWITCH ACCOUNT
			SearchAndswitchtoaccount(data.get("AccountName"));
			Thread.sleep(5000);
			handleinsightpopup();
			// WELCOME TO E PROCUREMNET PAGE
			VerifyWelcometoeProcurementpage();
			// COMPANY STATNDARDS LINK
			CompanystandardslinkandProductGrp(data.get("productGroup"), data.get("productName"));
			Thread.sleep(4000);
			handleinsightpopup();
			// verify qunatitiy price partnum
			Verifypartnum(data.get("partnum"));
			VerifypartPrice(data.get("partprice"));
			Addradiobutton(data.get("partnum"));
			Setquantity(data.get("partnum"),data.get("value"));
			Thread.sleep(5000);
			Closeiconofaddtocartatproductgrps();
			// click add to order
			// cart
			handleinsightpopup();
			// VERIFY ITEM IN CART
			Clickoncart();
			Thread.sleep(4000);
			Verifypartnum(data.get("partnum"));
			VerifyItemPriceincart(data.get("price"));
			VerifyItemQuantityincart(data.get("price"));
			SaveAsQuote();
			Thread.sleep(4000);
			typeattention(data.get("AccountName"));
			VerifyQuoteNameinsaveasquotepage();
			VerifyQuoteNotesinsaveasquotepage();
			Toclicksaveasquoteincompanystandardspage();
			Thread.sleep(4000);
			// CLICK QUOTE
			handleinsightpopup();
			VerifySuccessmsgofQuoteandRefNum();
			VerifyQuoteName();
			VerifyQuoteNotes();
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
//gErrorMessage = e.toString();
gTestStatus = false;
ReportStatus.fnUpdateResultStatus("MGSPropAssocQuote", "Tc_MIC04", ReportStatus.strMethodName, 1, browser);
throw new RuntimeException(e);
} finally {
ReportControl.fnEnableJoin();
ReportStatus.fnUpdateResultStatus("MGSPropAssocQuote", "Tc_MIC04", ReportStatus.strMethodName, counter, browser);
fnCloseTest();
ReportControl.fnNextTestJoin(nextTestJoin);
}
}
}



