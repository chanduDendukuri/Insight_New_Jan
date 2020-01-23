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
import com.insight.Lib.ProductDetailLib;
import com.insight.Lib.SearchLib;
import com.insight.Lib.ShipBillPayLib;
import com.insight.accelerators.ReportControl;
import com.insight.accelerators.TestEngineWeb;
import com.insight.googledrive.ReportStatus;

import com.insight.utilities.TestUtil;

public class MIC02_MIGlobalSoftwareTest extends MarriottIntlCorpLib {

	CommonLib commonLib = new CommonLib();
	CMTLib cmtLib = new CMTLib();
	OrderLib orderLib = new OrderLib();
	ShipBillPayLib shipbLib = new ShipBillPayLib();
	ProductDetailLib productdetLib = new ProductDetailLib();
	ShipBillPayLib shipBillpayLib=new ShipBillPayLib();
	SearchLib searchLib=new SearchLib();
	CartLib cartLib = new CartLib();
	CanadaLib canadaLib = new CanadaLib(); 

	
	// #############################################################################################################
	// # Name of the Test : MIC02_MIGlobalSoftware
	// # Migration Author : Cigniti Technologies
	// #
	// # Date of Migration : OCT 2019
	// # DESCRIPTION : This method is to perform Basic Cart operations.
	// # Parameters : StartRow ,EndRow , nextTestJoin
	// #
	// ###############################################################################################################

 @Parameters({ "StartRow", "EndRow", "nextTestJoin" })
 @Test
 public void Tc_MIC02(int StartRow, String EndRow, boolean nextTestJoin) throws Throwable {
	

			int counter = 0;
			try {
				int intStartRow = StartRow;
				int intEndRow = ReportControl.fnGetEndRowCunt(EndRow, "MIC02_MIGlobalSoftware", TestDataInsight,
						"MarriottIntl_Corp");
				for (int intCounter = intStartRow; intCounter <= intEndRow; intCounter++) {
					try {
						counter = intCounter;
						fnOpenTest();
						ReportStatus.fnDefaultReportStatus();
						ReportControl.intRowCount = intCounter;
						Hashtable<String, String> data = TestUtil.getDataByRowNo("MIC02_MIGlobalSoftware", TestDataInsight,
								"MarriottIntl_Corp", intCounter);
						TestEngineWeb.reporter.initTestCaseDescription("MIGlobalSoftware");
			cmtLib.loginToCMTSearchWebGrpAndUser(data.get("Header"), data.get("WebGrp"), data.get("LnameEmailUname"),data.get("ContactName"));
			cmtLib.clickOnRolesAndPermissionsAndSetPermission(data.get("Menu_Name"), data.get("Set_Permission"));//Enable Purchasing Popup
			cmtLib.loginAsAdminCMT();
			Thread.sleep(5000);
			commonLib.searchProduct(data.get("Search_Item"));//20L5004HUS
			productdetLib.getProductNameInProductDetailPage(data.get("Search_Item"));
			productdetLib.getMFRNumberInProductInfopage();
			commonLib.addToCartAndVerify();
			canadaLib.continueToCheckout();
			clickAccountToolsFromSideMenu(data.get("Tools_Menu"),data.get("Tools_Menu_DD"));			
			CompanystandardsSelectProductGrp(data.get("CPG"), data.get("SelectCP"));
			Verifypartnum(data.get("Verifypart"));
			setQuantityAddProduct(data.get("VerifyQty"), data.get("Verifypart"));
			Thread.sleep(5000);
			clickOnViewCart();
			scrollToBottomWithCordinate("300");
			shipbLib.AdditemsbyQuickshop(data.get("QuickShop_Part"));
			Thread.sleep(2000);
			Verifypartnum(data.get("QuickShop_Part"));
			Thread.sleep(2000);
			orderLib.proceedToCheckout();
			addAdditionalInfoOfProduct(data.get("Brand_Identifier"), data.get("Requester_Name"),
					data.get("EndUser_PeopleSoftNumber"), data.get("Notes"), data.get("Customer_Reference_Number"),
					data.get("PC_User_Name"), data.get("PC_End_User_Div_Unit_Dept"), data.get("End_User_Email"),
					data.get("Approving_Manager_Email"), data.get("Non_IRFA_PC"));
			addShippingInfo(data.get("Ship_Attention"), data.get("Ship_Suite"), data.get("Ship_Phone"));
			shippingOptionContinueButton();
			addBillingInfo(data.get("Bill_Attention"), data.get("Bill_Suite"), data.get("Bill_Phone"));
			Thread.sleep(5000);
			termsInPaymentInfo();
			orderLib.clickOnReviewOrderButton();
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
		ReportStatus.fnUpdateResultStatus("MIGlobalSoftware", "Tc_MIC02", ReportStatus.strMethodName, 1, browser);
		throw new RuntimeException(e);
	} finally {
		ReportControl.fnEnableJoin();
		ReportStatus.fnUpdateResultStatus("MIGlobalSoftware", "Tc_MIC02", ReportStatus.strMethodName, counter, browser);
		fnCloseTest();
		ReportControl.fnNextTestJoin(nextTestJoin);
	}
}
}