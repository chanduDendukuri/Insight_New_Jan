package com.insight.WebTest.MIC;

import java.util.Hashtable;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.insight.Lib.CMTLib;
import com.insight.Lib.CartLib;
import com.insight.Lib.CommonLib;
import com.insight.Lib.MarriottIntlCorpLib;
import com.insight.Lib.OrderLib;
import com.insight.Lib.ShipBillPayLib;
import com.insight.accelerators.ReportControl;
import com.insight.accelerators.TestEngineWeb;
import com.insight.googledrive.ReportStatus;
import com.insight.utilities.TestUtil;

public class MIC08_OrderShareMgdMarriottINTLITest extends MarriottIntlCorpLib {

	CommonLib commonLib = new CommonLib();
	CMTLib cmtLib = new CMTLib();
	CartLib cartLib = new CartLib();
	OrderLib orderLib = new OrderLib();
	ShipBillPayLib shipbLib = new ShipBillPayLib();

	// #############################################################################################################
	// # Name of the Test : MIC08_OrderShareMgdMarriottINTLI
	// # Migration Author : Cigniti Technologies
	// #
	// # Date of Migration : OCT 2019
	// # DESCRIPTION : This method is to perform Basic Cart operations.
	// # Parameters : StartRow ,EndRow , nextTestJoin
	// #
	// ###############################################################################################################

	@Parameters({ "StartRow", "EndRow", "nextTestJoin" })
	@Test
	public void Tc_MIC08(int StartRow, String EndRow, boolean nextTestJoin) throws Throwable {

		int counter = 0;
		try {
			int intStartRow = StartRow;
			int intEndRow = ReportControl.fnGetEndRowCunt(EndRow, "MIC08_OrderShareMgdMarriottINTLI", TestDataInsight,
					"MarriottIntl_Corp");
			for (int intCounter = intStartRow; intCounter <= intEndRow; intCounter++) {
				try {
					counter = intCounter;
					fnOpenTest();
					ReportStatus.fnDefaultReportStatus();
					ReportControl.intRowCount = intCounter;
					Hashtable<String, String> data = TestUtil.getDataByRowNo("MIC08_OrderShareMgdMarriottINTLI",
							TestDataInsight, "MarriottIntl_Corp", intCounter);
					TestEngineWeb.reporter.initTestCaseDescription("OrderShareMgdMarriottINTLI");
					cmtLib.loginToCMTSearchWebGrpAndUser(data.get("Header"), data.get("WebGrp"),
							data.get("LnameEmailUname"), data.get("Contact_Name"));
					cmtLib.clickOnRolesAndPermissionsAndSetPermission(data.get("Menu_Name"),
							data.get("Set_Permission"));
					cmtLib.loginAsAdminCMT();
					Thread.sleep(10000);
					handleinsightpopup();
					// METHOD TO SEARCH AND SWITCH ACCOUNT
					SearchAndswitchtoaccount(data.get("AccountName"));
					Thread.sleep(10000);
					handleinsightpopup();
					// WELCOME TO E PROCUREMNET PAGE
					VerifyWelcometoeProcurementpage();
					// COMPANY STATNDARDS LINK
					CompanystandardslinkandProductGrp(data.get("productGroup"), data.get("productName"));
					handleinsightpopup();
					// verify quantity,price,part number
					Verifypartnum(data.get("partnum"));
					VerifypartPrice(data.get("partprice"));
					Addcheckbox(data.get("partnum"));
					Setquantity(data.get("partnum"),data.get("value"));
					Thread.sleep(10000);
					clickOnViewCart();
					handleinsightpopup();
					Thread.sleep(10000);
					scrollToBottomWithCordinate("300");
					cartLib.verifyItemInCart(data.get("partnum"));
					shipbLib.AdditemsbyQuickshop(data.get("PartNum2"));
					Thread.sleep(2000);
					scrollToBottomWithCordinate("1000");
					Thread.sleep(2000);
					cartLib.verifyItemInCart(data.get("PartNum2"));
					Thread.sleep(2000);
					scrollToBottomWithCordinate("300");
					shipbLib.AdditemsbyQuickshop(data.get("PartNum3"));
					Thread.sleep(3000);
					scrollToBottomWithCordinate("300");
					Thread.sleep(2000);
					cartLib.verifyItemInCart(data.get("PartNum3"));
					orderLib.proceedToCheckout();
					Additionalinfo(data.get("Approving_Manager_Email"), data.get("DOMAIN"), data.get("REQUESTOR_EMAIL"),
							data.get("ENDUSERMARSHA"), data.get("ENDUSERDIVSELECT"), data.get("ENDUSEREID"),
							data.get("ENDUSERNAME"), data.get("Notes"));
					SelectApprovalackonwledgement(data.get("Option"));
					addAdditionalInfo(data.get("Name"), data.get("Phone"), data.get("Email"));
					addShippingInfo(data.get("Ship_Attention"), data.get("Ship_Suite"), data.get("Ship_Phone"));
					shippingOptionContinueButton();
					addBillingInfo(data.get("Bill_Attention"), data.get("Bill_Suite"), data.get("Bill_Phone"));
					orderLib.clickOnReviewOrderButton();
					Thread.sleep(2000);
					VerifyBrandidentifier(data.get("Brand_Identifier"));
					verifyapprovingmanageremail(data.get("Approving_Manager_Email"));
					verifyrequestoremail(data.get("REQUESTOR_EMAIL"));
					verifyendusermarsha(data.get("ENDUSERMARSHA"));
					verifyenduserdiv(data.get("ENDUSERDIVSELECT"));
					verifyendusereid(data.get("ENDUSEREID"));
					verifyendusername(data.get("ENDUSERNAME"));
					Verifynotes(data.get("Notes"));
					verifyShippingattention(data.get("Ship_Attention"));
					verifybillingattention(data.get("Bill_Attention"));
					verifyPayementInfo(data.get("PAYMENT_TYPE"));
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
			ReportStatus.fnUpdateResultStatus("OrderShareMgdMarriottINTLI", "Tc_MIC08", ReportStatus.strMethodName, 1,
					browser);
			throw new RuntimeException(e);
		} finally {
			ReportControl.fnEnableJoin();
			ReportStatus.fnUpdateResultStatus("OrderShareMgdMarriottINTLI", "Tc_MIC08", ReportStatus.strMethodName,
					counter, browser);
			fnCloseTest();
			ReportControl.fnNextTestJoin(nextTestJoin);
		}
	}
}
