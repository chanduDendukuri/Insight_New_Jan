package com.insight.WebTest.UserManag;

import java.util.Arrays;
import java.util.Hashtable;
import java.util.List;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.insight.Lib.CMTLib;
import com.insight.Lib.CanadaLib;
import com.insight.Lib.CartLib;
import com.insight.Lib.CommonLib;
import com.insight.Lib.InvoiceHistoryLib;
import com.insight.Lib.MarriottIntlCorpLib;
import com.insight.Lib.OrderLib;
import com.insight.Lib.SearchLib;
import com.insight.Lib.ShipBillPayLib;
import com.insight.Lib.UserManagementLib;
import com.insight.accelerators.ReportControl;
import com.insight.accelerators.TestEngineWeb;
import com.insight.googledrive.ReportStatus;
import com.insight.utilities.TestUtil;

public class USM03_FCTWebCreateCoPassUserIPSTest  extends UserManagementLib {

	// #############################################################################################################
			// # Name of the Test :USM01_FCTWEBClientUserSearch
			// # Migration Author : Cigniti Technologies
			// #
			// # Date of Migration : OCT 2019
			// # DESCRIPTION : This method is to perform Quote History search with date
			// operations.
			// # Parameters : StartRow ,EndRow , nextTestJoin
			// #
			// ###############################################################################################################
			@Parameters({ "StartRow", "EndRow", "nextTestJoin" })
			@Test
			public void TC_USM03FCTWebCreateCoPassUserIPS(int StartRow, String EndRow, boolean nextTestJoin) throws Throwable {
				
				int counter = 0;
				try {
					int intStartRow = StartRow;
					int intEndRow = ReportControl.fnGetEndRowCunt(EndRow, "USM03_FCTWebCreateCoPassUserIPS", TestDataInsight, "UserManagement");
					for (int intCounter = intStartRow; intCounter <= intEndRow; intCounter++) {
						try {
							
							counter = intCounter;
							fnOpenTest();
							ReportStatus.fnDefaultReportStatus();
							ReportControl.intRowCount = intCounter;
							Hashtable<String, String> data = TestUtil.getDataByRowNo("USM03_FCTWebCreateCoPassUserIPS", TestDataInsight, "UserManagement", intCounter);
							TestEngineWeb.reporter.initTestCaseDescription("FCTWebCreateCoPassUserIP");
										 
							CMTLib cmtLib = new CMTLib();
							SearchLib searchLib = new SearchLib();
							OrderLib orderLib=new OrderLib();
							CanadaLib canadaLib=new CanadaLib();
							CartLib cartLib=new CartLib();
							MarriottIntlCorpLib mic=new MarriottIntlCorpLib();
							InvoiceHistoryLib invoiceHistoryLib = new InvoiceHistoryLib();
							MarriottIntlCorpLib marriottIntlCorpLib=new MarriottIntlCorpLib();
							CommonLib commonLib = new CommonLib();
							ShipBillPayLib sbp=new ShipBillPayLib();
							//Login
							cmtLib.clickLoginLink(data.get("Header"));
							cmtLib.handleWelcomeToInsightBetaPopUp();
							cmtLib.loginAsAdmin();
							Thread.sleep(3000);
							cmtLib.searchForWebGroup(data.get("WebGrp"));
							cmtLib.manageUsers();
							cmtLib.clickAddNewUserLink();
							Thread.sleep(3000);
							cmtLib.selectUserTypeDropdown(data.get("User_Type"));
							cmtLib.clickCreateUserButton();
							cmtLib.verifyErrorMessage();
							cmtLib.verifyAvailabiltyOfUserName(data.get("UserName"));
							cmtLib.checkAvailability();	
							Thread.sleep(3000);
							String userName=getRandomNumeric(4);
							cmtLib.enterUserName("QTPTest"+userName);
							String userName1=getRandomNumeric(4);
							cmtLib.verifyAvailabiltyOfUserName("QTPTest"+userName1);
							cmtLib.clickCreateUserButton();
							Thread.sleep(3000);
							List<String> permissions = Arrays.asList(data.get("Set_Permission").split(","));
							for (int i= 0 ; i<permissions.size();i++){
								cmtLib.setPermissions(data.get("Menu_Name"),permissions.get(i));
							}
							cmtLib.clickCheckOutSettings(data.get("Linked_Accounts"));
							cmtLib.enterLinkedAccountSearch(data.get("Account_Number"));
							cmtLib.checkLinkedAccountCheckBox(data.get("Account_Number"));
							cmtLib.clickRadioDefaultAtLogin(data.get("Account_Number"));
							cmtLib.clickUpdateButtonOnLinkedAccountsScreen();
							Thread.sleep(3000);
							cmtLib.clickCheckOutSettings(data.get("Check_out_Settings"));
							// navigate to checkout settings >>  payment options
							cmtLib.selectOptionInCheckoutSettings(data.get("Payment_Options"));
							cmtLib.clearPaymentOptionsInCheckoutSettings();
							Thread.sleep(3000);
							cmtLib.selectpaymentOptionsInCheckOutSettings(data.get("Options"));
							Thread.sleep(3000);
							cmtLib.selectDefaultPaymentOption(data.get("Default_Payment_Option"));					
							cmtLib.logoutSite();
							//commonLib.clickLogOutLink(data.get("Header"));
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
					ReportStatus.fnUpdateResultStatus("FCTWebCreateCoPassUserIP", "USM03_FCTWebCreateCoPassUserIPS", ReportStatus.strMethodName, 1, browser);
					throw new RuntimeException(e);
				}
	            finally {
					ReportControl.fnEnableJoin();
					ReportStatus.fnUpdateResultStatus("FCTWebCreateCoPassUserIP", "USM03_FCTWebCreateCoPassUserIPS", ReportStatus.strMethodName, counter, browser);
					fnCloseTest();
					ReportControl.fnNextTestJoin(nextTestJoin);
				}
			}
}

