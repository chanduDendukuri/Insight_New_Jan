package com.insight.WebTest.MIC;

import java.util.Hashtable;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.insight.Lib.CMTLib;
import com.insight.Lib.CanadaLib;
import com.insight.Lib.CartLib;
import com.insight.Lib.CommonLib;
import com.insight.Lib.MarriottIntlCorpLib;
import com.insight.Lib.OrderLib;
import com.insight.Lib.ShipBillPayLib;
import com.insight.accelerators.ReportControl;
import com.insight.accelerators.TestEngineWeb;
import com.insight.googledrive.ReportStatus;
import com.insight.utilities.TestUtil;

public class MIC07_PAAprovalMIGobalTest extends MarriottIntlCorpLib {

	CommonLib commonLib = new CommonLib();
	CMTLib cmtLib = new CMTLib();
	CartLib cartLib = new CartLib();
	OrderLib orderLib = new OrderLib();
	ShipBillPayLib shipbLib = new ShipBillPayLib();
	CanadaLib canadaLib = new CanadaLib(); 

	
	// #############################################################################################################
	// # Name of the Test : MIC07_PAAprovalMIGobal
	// # Migration Author : Cigniti Technologies
	// #
	// # Date of Migration : OCT 2019
	// # DESCRIPTION : This method is to perform Basic Cart operations.
	// # Parameters : StartRow ,EndRow , nextTestJoin
	// #
	// ###############################################################################################################
	
	@Parameters({ "StartRow", "EndRow", "nextTestJoin" })
	@Test
	public void Tc_MIC07(int StartRow, String EndRow, boolean nextTestJoin) throws Throwable {
		
			
			int counter = 0;
			try {
				int intStartRow = StartRow;
				int intEndRow = ReportControl.fnGetEndRowCunt(EndRow, "MIC07_PAAprovalMIGobal", TestDataInsight,
						"MarriottIntl_Corp");
				for (int intCounter = intStartRow; intCounter <= intEndRow; intCounter++) {
					try {
						counter = intCounter;
						fnOpenTest();
						ReportStatus.fnDefaultReportStatus();
						ReportControl.intRowCount = intCounter;
						Hashtable<String, String> data = TestUtil.getDataByRowNo("MIC07_PAAprovalMIGobal", TestDataInsight,
								"MarriottIntl_Corp", intCounter);
						TestEngineWeb.reporter.initTestCaseDescription("PAAprovalMIGobal");
			cmtLib.loginToCMTSearchWebGrpAndUser(data.get("Header"), data.get("WebGrp"), data.get("LnameEmailUname"),
					data.get("Contact_Name"));
			cmtLib.clickOnRolesAndPermissionsAndSetPermission(data.get("Menu_Name"), data.get("Set_Permission"));
			cmtLib.loginAsAdminCMT();
			handleinsightpopup();
			// METHOD TO SEARCH AND SWITCH ACCOUNT
			SearchAndswitchtoaccount(data.get("AccountName"));
			Thread.sleep(10000);
			handleinsightpopup();
			// WELCOME TO E PROCUREMNET PAGE
			VerifyWelcometoeProcurementpage();
			// COMPANY STATNDARDS LINK
			CompanystandardslinkandProductGrpWithbtag(data.get("productGroup"), data.get("productName"),data.get("FieldOnly"));
			handleinsightpopup();
			// verify quantity,price,part number
			Verifypartnum(data.get("partnum1"));
			VerifypartPrice(data.get("partprice"));
			Addcheckbox(data.get("partnum1"));
			Setquantity(data.get("partnum1"),data.get("value"));
			Thread.sleep(5000);
			clickOnViewCart();
			handleinsightpopup();
			Thread.sleep(5000);
			Verifypartnum(data.get("partnum1"));
			shipbLib.AdditemsbyQuickshop(data.get("PartNum2"));
			Thread.sleep(2000);
			Verifypartnum(data.get("PartNum2"));
			commonLib.searchProduct(data.get("PartNum3"));
			commonLib.addToCartAndVerify();
			canadaLib.continueToCheckout();
			Thread.sleep(2000);
			Verifypartnum(data.get("PartNum3"));
			orderLib.proceedToCheckout();
			Thread.sleep(2000);
			addAdditionalInfoOfProduct(data.get("Brand_Identifier"), data.get("Requester_Name"),
					data.get("EndUser_PeopleSoftNumber"), data.get("Notes"), data.get("Customer_Reference_Number"),
					data.get("PC_User_Name"), data.get("PC_End_User_Div_Unit_Dept"), data.get("End_User_Email"),
					data.get("Approving_Manager_Email"), data.get("Non_IRFA_PC"));
			addAdditionalInfo(data.get("Name"),data.get("Phone"),data.get("Email"));
			addShippingInfo(data.get("Ship_Attention"), data.get("Ship_Suite"), data.get("Ship_Phone"));
			shippingOptionContinueButton();
			addBillingInfo(data.get("Bill_Attention"), data.get("Bill_Suite"), data.get("Bill_Phone"));
			Thread.sleep(5000);
			termsInPaymentInfo();
			shipbLib.ClickRviewrequesition();
			Thread.sleep(3000);
			VerifyBrandidentifier(data.get("Brand_Identifier"));
			verifyrequestorname(data.get("Requester_Name"));
			verifypcusername(data.get("PC_User_Name"));
			Verifynotes(data.get("Notes"));
			Verifycustomerreference(data.get("Customer_Reference_Number"));
			VerifyEnduserText(data.get("EndUser_PeopleSoftNumber"));
			verifyapprovingmanageremail(data.get("Approving_Manager_Email"));
			verifyEnduseremail(data.get("End_User_Email"));
			verifynonirfapc( data.get("Non_IRFA_PC"));
			verifyenduserdiv( data.get("PC_End_User_Div_Unit_Dept"));
			verifyShippingattention(data.get("Ship_Attention"));
			verifybillingattention(data.get("Bill_Attention"));
			verifyPayementInfo(data.get("PAYMENT_TYPE"));
			Thread.sleep(3000);
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
	ReportStatus.fnUpdateResultStatus("PAAprovalMIGobal", "Tc_MIC07", ReportStatus.strMethodName, 1, browser);
	throw new RuntimeException(e);
} finally {
	ReportControl.fnEnableJoin();
	ReportStatus.fnUpdateResultStatus("PAAprovalMIGobal", "Tc_MIC07", ReportStatus.strMethodName, counter, browser);
	fnCloseTest();
	ReportControl.fnNextTestJoin(nextTestJoin);
}
}
}

