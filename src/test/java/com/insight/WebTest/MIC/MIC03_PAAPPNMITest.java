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

public class MIC03_PAAPPNMITest extends MarriottIntlCorpLib{
	
	CommonLib commonLib = new CommonLib();
	CMTLib cmtLib = new CMTLib();					
	OrderLib orderLib = new OrderLib();
	ShipBillPayLib shipbLib = new ShipBillPayLib();
	CartLib cartLib = new CartLib();

	// #############################################################################################################
	// # Name of the Test : MIC06_SharedMgdServicesOrder
	// # Migration Author : Cigniti Technologies
	// #
	// # Date of Migration : OCT 2019
	// # DESCRIPTION : This method is to perform Basic Cart operations.
	// # Parameters : StartRow ,EndRow , nextTestJoin
	// #
	// ###############################################################################################################
	@Parameters({ "StartRow", "EndRow", "nextTestJoin" })
	@Test
	public void Tc_MIC03(int StartRow, String EndRow, boolean nextTestJoin) throws Throwable {
	
					int counter = 0;
					try {
						int intStartRow = StartRow;
						int intEndRow = ReportControl.fnGetEndRowCunt(EndRow, "MIC03_PAAPPNMI", TestDataInsight,
								"MarriottIntl_Corp");
						for (int intCounter = intStartRow; intCounter <= intEndRow; intCounter++) {
							try {
								counter = intCounter;
								fnOpenTest();
								ReportStatus.fnDefaultReportStatus();
								ReportControl.intRowCount = intCounter;
								Hashtable<String, String> data = TestUtil.getDataByRowNo("MIC03_PAAPPNMI", TestDataInsight,
										"MarriottIntl_Corp", intCounter);
								TestEngineWeb.reporter.initTestCaseDescription("PAAPPNMI");
					cmtLib.loginToCMTSearchWebGrpAndUser(data.get("Header"), data.get("WebGrp"), data.get("LnameEmailUname"),data.get("Contact_Name"));
					cmtLib.clickOnRolesAndPermissionsAndSetPermission(data.get("Menu_Name"), data.get("Set_Permission"));// Enable
					cmtLib.loginAsAdminCMT();
					handleinsightpopup();
					// METHOD TO SEARCH AND SWITCH ACCOUNT
					SearchAndswitchtoaccount(data.get("Account_Name"));
					handleinsightpopup();
					// WELCOME TO E PROCUREMNET PAGE
					VerifyWelcometoeProcurementpage();
					// COMPANY STATNDARDS LINK
					CompanystandardslinkandProductGrp(data.get("CPG"), data.get("SelectCP"));
					handleinsightpopup();
					// verify qunatitiy price partnum
					Verifypartnum(data.get("Verifypart"));
					setQuantityAddProductMIC03(data.get("VerifyQty"),data.get("Verifypart"));
					clickOnViewCart();	
					Thread.sleep(3000);
					Verifypartnum(data.get("Verifypart"));
					cartLib.verifyItemInCart(data.get("Verifypart"));
					Thread.sleep(3000);
					SelectCPPFRomAccounttools(data.get("Tools_Menu"),data.get("Tools_Menu_DD"),data.get("CPG2"), data.get("SelectCP2"));
					Verifypartnum(data.get("Verifypart2"));
					setQuantityAddProductMIC03(data.get("VerifyQty2"), data.get("Verifypart2"));
					Thread.sleep(5000);
					clickOnViewCart();
					cartLib.verifyItemInCart(data.get("Verifypart2"));
					verifyPartInCartQuickShop(data.get("QuickShop_Part"));
					orderLib.proceedToCheckout();
					addAdditionalInfoOfProduct(data.get("Brand_Identifier"), data.get("Requester_Name"),
							data.get("EndUser_PeopleSoftNumber"), data.get("Notes"), data.get("Customer_Reference_Number"),
							data.get("PC_User_Name"), data.get("PC_End_User_Div_Unit_Dept"), data.get("End_User_Email"),
							data.get("Approving_Manager_Email"), data.get("Non_IRFA_PC"));
					addAdditionalInfo(data.get("Requester_Name"),data.get("Ship_Phone"),data.get("End_User_Email"));					
					addShippingInfo(data.get("Ship_Attention"), data.get("Ship_Suite"), data.get("Ship_Phone"));
					shippingOptionContinueButton();
					addBillingInfo(data.get("Bill_Attention"), data.get("Bill_Suite"), data.get("Bill_Phone"));
					Thread.sleep(5000);
					shipbLib.ClickRviewrequesition();
					Thread.sleep(5000);
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
			ReportStatus.fnUpdateResultStatus("PAAPPNMI", "Tc_MIC03", ReportStatus.strMethodName, 1, browser);
			throw new RuntimeException(e);
		} finally {
			ReportControl.fnEnableJoin();
			ReportStatus.fnUpdateResultStatus("PAAPPNMI", "Tc_MIC03", ReportStatus.strMethodName, counter, browser);
			fnCloseTest();
			ReportControl.fnNextTestJoin(nextTestJoin);
		}
	}
	}
