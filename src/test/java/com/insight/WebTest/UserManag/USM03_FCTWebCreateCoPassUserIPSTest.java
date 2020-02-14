package com.insight.WebTest.UserManag;

import java.util.Hashtable;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.insight.Lib.CMTLib;
import com.insight.Lib.CommonLib;
import com.insight.Lib.EndUserFeaturesLib;
import com.insight.Lib.MarriottIntlCorpLib;
import com.insight.Lib.ProductDetailLib;
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
							MarriottIntlCorpLib mic=new MarriottIntlCorpLib();
							ProductDetailLib productdetLib = new ProductDetailLib();
							CommonLib commonLib = new CommonLib(); 
							EndUserFeaturesLib eufLib=new EndUserFeaturesLib();
							ShipBillPayLib shipbilllib=new ShipBillPayLib();
							
							//Login
							cmtLib.loginToCMT(data.get("Header"));
							cmtLib.searchForWebGroup(data.get("WebGrp"));
							cmtLib.manageUsers();
							cmtLib.hoverOnManageWebGroupsAndSelectOptions(data.get("Manage_Web_Grp_Options"));
							//New User Link
							cmtLib.clickAddNewUserLink();
							cmtLib.selectUserTypeDropdown(data.get("User_Type"));
							cmtLib.clickCreateUserButton();
							cmtLib.verifyErrorMessage();
							cmtLib.enterUserName(data.get("Name1"));
							cmtLib.checkAvailability();
							cmtLib.verifyAvailabiltyOfUserNameExists();
							String userName="QTPTest"+getRandomNumeric(4);
							cmtLib.enterUserName(userName);
							cmtLib.clickCreateUserButton();
							//Permissions
							cmtLib.clickOnRolesAndPermissionsTab(data.get("Menu_Name"));
							String[] permissions = data.get("Set_Permission").split(",");
							for (i = 0; i < permissions.length; i++) {
								cmtLib.verifySetPermissions( permissions[i]);
							}
							cmtLib.verifyDDPermission(data.get("Permision4"),data.get("Option"));
							cmtLib.verifyDDPermission(data.get("Permision5"),data.get("Option"));
							//cmtLib.verifyDDPermission(data.get("Permision6"),data.get("Option"));
							cmtLib.verifyDDPermission(data.get("Permision7"),data.get("Option2"));
							cmtLib.setPermissionsToDisable(data.get("Menu_Name"),data.get("DisablePermission"));
							cmtLib.clickCheckOutSettings(data.get("Check_out_Settings"));
							cmtLib.selectOptionInCheckoutSettings(data.get("Payment_Options"));
							shipbilllib.verifyOptioninAllowedOptions(data.get("OptionTerms"));
							shipbilllib.verifyOptioninAllowedOptions(data.get("OptionCreditCard"));
							shipbilllib.verifyOptioninAllowedOptions(data.get("Options1"));
							
							commonLib.selectOptionInCheckoutSettings(data.get("Shipping_Options"));
							cmtLib.verifyShippingOptions();
							cmtLib.NoOptionOtherThanSLSinShippingOption(data.get("SLS"));
							//Billing Address
							cmtLib.selectOptionInCheckoutSettings(data.get("Billing_address"));
							shipbilllib.verifyNoDefaultAddressBillingAddress();
							cmtLib.DEFUALTCHECKBOX();
							//Shipping Address
							cmtLib.selectOptionInCheckoutSettings(data.get("Shipping_address"));
							cmtLib.defualtShippingAddressCheckBox();
							commonLib.verifyDefualtShippingSelectedOption();
							//Payment
							cmtLib.selectDefaultPaymentOption(data.get("Default_Payment_Option1"));
							//Linked Account
							cmtLib.clickCheckOutSettings(data.get("Manage_Web_Grp_Options1"));
							verifydefualtLinkedAcc(data.get("Account_Number"));
							cmtLib.clickInformationTab(data.get("Information_Tab"));
							cmtLib.clickOnUserURL();
							cmtLib.verifyCreateAnAccountPage();
							String Number=getRandomNumeric(4);
							String email="QTPTest"+Number+"@test.com";
							cmtLib.enterEmailInCreateAnAccount(email);
							String firstName="QTPTest"+Number;
							cmtLib.enterFirstNameInCreateAnAccount(firstName);
							String lastName="QTPTest"+Number;
							cmtLib.enterLastNameInCreateAnAccount(lastName);
							cmtLib.enterPhoneNumberInCreateAnAccount(data.get("Phone_Number"));
							String userName1="QTPTest"+Number;
							String userName2="QTPTest"+Number;
							cmtLib.enterUserNameInCreateAnAccount(userName1,userName2);
							String password="QTPTest"+Number;
							cmtLib.enterPasswordInCreateAnAccount(password);
							cmtLib.enterConfirmPasswordInCreateAnAccount(password);//Phone_Number
							cmtLib.clickCreateButtonInCreateAnAccount();
							cmtLib.clickContinueButtonInCreateAnAccount();
							mic.handleinsightpopup();
							cmtLib.verifyWelcomePage();
							productdetLib.verifytheLoginUser(firstName+" "+lastName);
							commonLib.clickLogOutLink(data.get("Logout_Header"));
							cmtLib.loginToCMT(data.get("Header"));
							cmtLib.searchForWebGroup(data.get("WebGrp"));
							cmtLib.manageUsers();
							cmtLib.hoverOnManageWebGroupsAndSelectOptions(data.get("Manage_Web_Grp_Options"));
							cmtLib.searchForaUserAndSelect(email,firstName+" "+lastName);
							cmtLib.clickOnRolesAndPermissionsTab(data.get("Menu_Name"));
							String[] permissions2 = data.get("Set_Permission").split(",");
							for (i = 0; i < permissions2.length; i++) {
								cmtLib.verifySetPermissions( permissions2[i]);
							}
							cmtLib.verifySetPermissionsDisabled("");
							cmtLib.verifyDDPermission(data.get("Permision4"),data.get("Option"));
							cmtLib.verifyDDPermission(data.get("Permision5"),data.get("Option"));
							//cmtLib.verifyDDPermission(data.get("Permision6"),data.get("Option"));
							cmtLib.verifyDDPermission(data.get("Permision7"),data.get("Option2"));
							cmtLib.clickCheckOutSettings(data.get("Manage_Web_Grp_Options1"));
							verifydefualtLinkedAcc(data.get("Account_Number"));
							cmtLib.clickCheckOutSettings(data.get("Check_out_Settings"));
							cmtLib.selectOptionInCheckoutSettings(data.get("Payment_Options"));
							Thread.sleep(3000);
							shipbilllib.verifyProcurmentOptioninAllowedOptions();
							cmtLib.logoutSite();
							
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

