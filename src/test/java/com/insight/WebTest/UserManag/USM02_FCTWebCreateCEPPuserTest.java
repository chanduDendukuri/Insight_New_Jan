package com.insight.WebTest.UserManag;

import java.util.Hashtable;

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

public class USM02_FCTWebCreateCEPPuserTest extends UserManagementLib {

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
			public void TC_USM02FCTWebCreateCEPPuser(int StartRow, String EndRow, boolean nextTestJoin) throws Throwable {
				int counter = 0;
				try {
					int intStartRow = StartRow;
					int intEndRow = ReportControl.fnGetEndRowCunt(EndRow, "TC_USM02FCTWebCreateCEPPuser", TestDataInsight,
							"UserManagement");
					for (int intCounter = intStartRow; intCounter <= intEndRow; intCounter++) {
						try {
							counter = intCounter;
							fnOpenTest();
							ReportStatus.fnDefaultReportStatus();
							ReportControl.intRowCount = intCounter;
							Hashtable<String, String> data = TestUtil.getDataByRowNo("TC_USM02FCTWebCreateCEPPuser",TestDataInsight, "UserManagement", intCounter);
							TestEngineWeb.reporter.initTestCaseDescription("FCTWebCreateCEPPuser");
							reporter.SuccessReport("Iteration Number : ",
									"**************Iteration Number::  " + intCounter + " For:: " + data.get("LoginName") + " ::and:: "
											+ data.get("Password") + " To Validate::" + data.get("errorMessage") + "  **************",
									"");
	 
							CMTLib cmtLib = new CMTLib();
							SearchLib searchLib = new SearchLib();
							OrderLib orderLib=new OrderLib();
							CanadaLib canadaLib=new CanadaLib();
							CartLib cartLib=new CartLib();
							MarriottIntlCorpLib mic=new MarriottIntlCorpLib();
							CommonLib commonLib = new CommonLib();
							ShipBillPayLib sbp=new ShipBillPayLib();
							//Login
							cmtLib.loginToCMT(data.get("Header"));
							cmtLib.searchForWebGroup(data.get("WebGrp"));
							cmtLib.manageUsers();
							cmtLib.hoverOnManageWebGroupsAndSelectOptions(data.get("Manage_Web_Grp_Options"));
							cmtLib.hoverOnManageWebGroupsAndSelectOptions(data.get("Manage_Web_Grp_Options1"));
							//New User Link
							cmtLib.clickAddNewUserLink();
							//User Name
							cmtLib.enterUserName(data.get("ExistingUserName"));
							cmtLib.checkAvailability();
							cmtLib.verifyAvailabiltyOfUserNameExists();
                            String Username="QTPTest"+getRandomNumeric(4);							
							cmtLib.enterUserName(Username);
							cmtLib.checkAvailability();
							cmtLib.verifyAvailabiltyOfUserNameNotExists();
							cmtLib.clickCreateUserButton();	
							cmtLib.clickOnRolesAndPermissionsTab(data.get("Menu_Name"));
							String[] permissions = data.get("Set_Permission1").split(",");
							for (i = 0; i < permissions.length; i++) {
								cmtLib.verifySetPermissions( permissions[i]);
							}
							cmtLib.verifyDDPermission(data.get("Permision4"),data.get("Option"));
							cmtLib.verifyDDPermission(data.get("Permision5"),data.get("Option"));
							cmtLib.verifyDDPermission(data.get("Permision6"),data.get("Option"));
							//cmtLib.updateUser();
							cmtLib.clickInformationTab(data.get("Information_Tab"));
							cmtLib.clickOnUserURL();
							//refreshPage();
							//cmtLib.verifyCreateAnAccountPage();
							//commonLib.clickLogOutLink(data.get("Logout_Header"));
							//navigateTo("https://uat1.insight.com/insightweb/endUser/createAccount?authKey=wSNU%2F3UlR5o%3D");
							mic.handleinsightpopup();
							//refreshPage();
							cmtLib.enterAdressesInCreateAnAccount(data.get("Adressess2"));
							cmtLib.enterCityInCreateAnAccount(data.get("City2"));
							cmtLib.selectStateInCreateAnAccount(data.get("State2"));
							cmtLib.enterZipCodeInCreateAnAccount(data.get("ZipCode2"));
							cmtLib.clickCreateButtonInCreateAnAccount();
							//Verify Error Msg
							verifyErorrMsgOfFirstName();
							verifyErorrMsgOfLastName();
							verifyErorrMsgOfPhoneNumber();
							//User Name
							String Number=getRandomNumeric(4);
							String userName="QTPTest"+Number;
							cmtLib.verifyAvailabilityCreateAccount(userName,data.get("CreateacUserName5"));
							String password="QTPTest"+Number;
							cmtLib.enterPasswordInCreateAnAccount(password);
							cmtLib.enterConfirmPasswordInCreateAnAccount(password);
							String email="QTPTest"+Number+"@test.com";
							cmtLib.enterEmailInCreateAnAccount(email);
							String firstName="QTPTest"+Number;
							cmtLib.enterFirstNameInCreateAnAccount(firstName);
							String lastName="QTPTest"+Number;
							cmtLib.enterLastNameInCreateAnAccount(lastName);
							cmtLib.enterPhoneNumberInCreateAnAccount(data.get("Phone_Number"));
							//Address
							cmtLib.enterAdressesInCreateAnAccount(data.get("Adressess1"));
							cmtLib.enterCityInCreateAnAccount(data.get("City"));
							cmtLib.selectStateInCreateAnAccount(data.get("State"));
							cmtLib.enterZipCodeInCreateAnAccount(data.get("ZipCode"));
							String billingAccountName="QTPTest"+Number;
							cmtLib.enterBillingAccountNameInCreateAnAccount(billingAccountName);
							cmtLib.clickCreateButtonInCreateAnAccount();
							cmtLib.clickContinueButtonInCreateAnAccount();
							mic.handleinsightpopup();
							cmtLib.verifyWelcomePage();
							searchLib.searchInHomePage(data.get("SearchItem"));
							commonLib.addToCartAndVerify();
							orderLib.continueToCheckOutOnAddCart();
							Thread.sleep(3000);
							cartLib.verifyItemInCart(data.get("SearchItem"));
							mic.proceedToCheckout();
							Thread.sleep(3000);
							cartLib.clickOnContinueButtonInAddInformtion();
							canadaLib.verifySBP();
							orderLib.shippingBillPayContinueButton();
							orderLib.shippingOptionsCarrierSelection();
							orderLib.billingAddressContinueButton();
							orderLib.selectPaymentInfoMethodCreditCard(data.get("cardNumber"),data.get("cardName"),data.get("month"),data.get("year"),data.get("poNumber"),data.get("POReleaseNumber"));
							orderLib.clickOnReviewOrderButton();
							String summaryAmount = cartLib.getSummaryAmountInCart();
							orderLib.placeOrderAndVerifyReceiptOrderAndDate(summaryAmount);
							sbp.clickOrderDetailsButtonInREceipt();
							Thread.sleep(3000);
							sbp.verifyShippingCarrierAFterReviewOrder(data.get("shippingCarrier"),data.get("shippingCarrier"));
							commonLib.clickLogOutLink(data.get("Logout_Header"));
							
							cmtLib.loginToCMT(data.get("Header"));
							cmtLib.searchForWebGroup(data.get("WebGrp"));
							cmtLib.manageUsers();
							cmtLib.hoverOnManageWebGroupsAndSelectOptions(data.get("Manage_Web_Grp_Options1"));
							cmtLib.searchForaUserAndSelect(data.get("LnameEmailUname"), data.get("ContactName"));
							cmtLib.clickOnRolesAndPermissionsTab(data.get("Menu_Name"));
							String[] permissions2 = data.get("Set_Permission1").split(",");
							for (i = 0; i < permissions2.length; i++) {
								cmtLib.verifySetPermissions( permissions2[i]);
							}
							cmtLib.verifyDDPermission(data.get("Persision4"),data.get("Option"));
							cmtLib.verifyDDPermission(data.get("Persision5"),data.get("Option"));
							cmtLib.verifyDDPermission(data.get("Persision6"),data.get("Option"));
							//cmtLib.updateUser();
							cmtLib.hoverOnManageWebGroupsAndSelectOptions(data.get("Manage_Web_Grp_Options"));
							
							
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
					//ReportStatus.fnUpdateResultStatus("USM01FCTWEBClientUserSearch", "TC_USM01FCTWEBClientUserSearch", ReportStatus.strMethodName, 1, browser);
					throw new RuntimeException(e);
				}
	            finally {
					ReportControl.fnEnableJoin();
					ReportStatus.fnUpdateResultStatus("USM02FCTWebCreateCEPPuser", "TC_USM02FCTWebCreateCEPPuser", ReportStatus.strMethodName, counter, browser);
					fnCloseTest();
					ReportControl.fnNextTestJoin(nextTestJoin);
				}
			}
}

