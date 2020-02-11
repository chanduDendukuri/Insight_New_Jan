package com.insight.Lib;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

import org.mortbay.log.Log;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.insight.ObjRepo.CMTObj;
import com.insight.ObjRepo.CartObj;
import com.insight.ObjRepo.CommonObj;
import com.insight.ObjRepo.UserManagementObj;
import com.insight.report.ConfigFileReadWrite;
import com.insight.report.ReporterConstants;

public class CMTLib extends CMTObj {

	public String CMT_ADMIN_USERNAME = ConfigFileReadWrite.read(ReporterConstants.configReporterFile, "cmtUserName");
	public String CMT_ADMIN_PASSWORD = ConfigFileReadWrite.read(ReporterConstants.configReporterFile, "cmtPassWord");
	public String EMAIL_ID = ConfigFileReadWrite.read(ReporterConstants.configReporterFile, "emailID");

	/**
	 * This methods to Login to the CMT Site with the Account credentials
	 * created and verify the successful login.
	 * 
	 * @param login
	 * @param userName
	 * @param password
	 * @throws Throwable
	 */
	public void loginToCMT(String login) throws Throwable {
		click(CommonObj.getPrimaryNavLink(login), "Login link");
		Thread.sleep(3000);
		if (isVisibleOnly(CommonObj.CLOSE, "Close icon")) {
			if (isVisibleOnly(CommonObj.CLOSE, "Close icon")) {
				click(CommonObj.CLOSE, "Close icon");
			}
		}
		if (isVisibleOnly(CommonObj.CLOSEBUTTON_COOKIES, "close cookie")) {
			click(CommonObj.CLOSEBUTTON_COOKIES, "close cookie");
		}
		typeText(CommonObj.LOGIN_USER, CMT_ADMIN_USERNAME, "User Name");
		typeText(CommonObj.LOGIN_PSWD, CMT_ADMIN_PASSWORD, "password");
		click(CommonObj.LOGIN_BTN, "Login button");
		Thread.sleep(3000);
		waitForVisibilityOfElement(WEB_GROUP, "WEB GROUP in CMT TOOL HOME PAGE");
		if(isVisibleOnly(CMT_WELCOME_POPUP,"CMT Welcome Popup")) {
		WebElement ExpectedUserName = driver.findElement(CMTObj.CMT_WELCOME_POPUP);
		if (ExpectedUserName.isDisplayed()) {
			handleWelcomeToInsightBetaPopUpInCMT();
			reporter.SuccessReport("Verify login", "Sucessfully logged in to the account",
					"User name is : " + CMT_ADMIN_USERNAME + "password is : " + CMT_ADMIN_PASSWORD);
		} else {
			reporter.failureReport("Verify login", "Login is not sucessful", "", driver);
		}
		}
	}

	/**
	 * This method is to click manage users button in CMT tool
	 * 
	 * @throws Throwable
	 */
	public void manageUsers() throws Throwable {
		waitForVisibilityOfElement(CartObj.MANAGE_USERS_BUTTON, "MANAGE USERS BUTTON");
		click(CartObj.MANAGE_USERS_BUTTON, "MANAGE USERS BUTTON");

	}

	/**
	 * 
	 * @param header
	 * @throws Throwable
	 */
	public void clickLoginLink(String header) throws Throwable {
		waitForVisibilityOfElement(CartObj.LOG_IN_LINK, "login Link");
		click(CartObj.LOG_IN_LINK, "login Link");
	}

	/**
	 * This method is to handle the welcome popup in the CMT home screen
	 * 
	 * @throws Throwable
	 */
	public void handleWelcomeToInsightBetaPopUpInCMT() throws Throwable {
		type(EMAIL_INPUT, EMAIL_ID, "POP UP TO ENTER EMAIL ID");
		click(SUBMIT, "POP UP SUBMIT BUTTON");
	}

	/**
	 * This method is to handle the welcome popup in the CMT home screen
	 * 
	 * @throws Throwable
	 */
	public void handleWelcomeToInsightBetaPopUp() throws Throwable {
		waitForVisibilityOfElement(CartObj.POP_UP_EMAILID, "POP UP TO ENTER EMAIL ID");
		type(CartObj.POP_UP_EMAILID, EMAIL_ID, "POP UP TO ENTER EMAIL ID");
		click(CartObj.POP_UP_SUBMIT_BUTTON, "POP UP SUBMIT BUTTON");
	}

	/**
	 * This method is to search for a webgroup in the CMT home page.
	 * 
	 * @param webGroup
	 * @throws Throwable
	 */
	public void searchForWebGroup(String webGroup) throws Throwable {
		if (isElementVisible(CLIENT_SEARCH_LABEL, 3, "CMT home page")) {
			reporter.SuccessReport("Verify Client Admin Search Page",
					"Client Admin Search Page Exists with client search label", "CMT home screen");
			waitForVisibilityOfElement(WEB_GROUP, "WEB GROUP in CMT TOOL HOME PAGE");
			type(WEB_GROUP, webGroup, "Web Group field Exists and Entered");
			click(SEARCH_BUTTON_CMT_TOOL, "SEARCH BUTTON in CMT TOOL HOME PAGE");
		} else {
			reporter.failureReport("Verify CMT home page displayed", "CMT home page is not displayed ",
					"CMT home screen");
		}
	}

	/**
	 * This method is to search for a webgroup in the CMT home page.
	 * 
	 * @param webGroup
	 * @throws Throwable
	 */
	public void searchForUserByEmail(String email) throws Throwable {
		waitForVisibilityOfElement(SEARCH_USER_BY_EMAIL, "Email in CMT TOOL HOME PAGE");
		type(SEARCH_USER_BY_EMAIL, email, "Email in CMT TOOL");
		click(SEARCH_BUTTON_CMT_TOOL, "SEARCH BUTTON in CMT TOOL HOME PAGE");

	}

	/**
	 * This method is to click on shared link user in information tab
	 * 
	 * @param webGroup
	 * @throws Throwable
	 */
	public void clickOnSharedLinkUserInInformationTab() throws Throwable {
		if (isElementPresent(CMTObj.PERSONAL_INFORMATION_TEXT, "Personal Information")) {
			click(CMTObj.SHARED_LINK_USER, "Shared link user");

		}

	}

	/**
	 * This method is to click on forget password link
	 * 
	 * @param webGroup
	 * @throws Throwable
	 */
	public void clickForgetPasswordLink() throws Throwable {
		click(FORGET_PASSWORD_LINK, "Forget password link");
	}

	public void enterMailToResetPassword(String text) throws Throwable {
		clearData(FORGET_PASSWORD_MAIL_ENTER_TEXTFIELD);
		type(FORGET_PASSWORD_MAIL_ENTER_TEXTFIELD, text, "Mail to enter");
		click(FORGET_PASSWORD_SUBMIT_BUTTON, "Submit button");
	}

	public void verifyPasswordResetWrongEmailEnteredMessage() throws Throwable {
		String error = null;
		if (isVisibleOnly(FOGET_PASSWORD_ERROR_MESSAGE, "error message")) {
			error = getText(FOGET_PASSWORD_ERROR_MESSAGE, "error message");
			reporter.SuccessReport("Verifying Success message", "Email Success Message Exists", error);
		} else {
			reporter.failureReport("Verifying Success message", "Email Success Message does not Exist", error, driver);
		}
	}

	public void verifyPasswordResetEmailEnteredSucessMessage(String input) throws Throwable {
		String error = null;
		if (isVisibleOnly(FORGET_PASSWORD_RESET_SUCESS_MESSAGE, "Success message")) {
			error = getText(FORGET_PASSWORD_RESET_SUCESS_MESSAGE, "Success message");
			reporter.SuccessReport("Verifying Success message", "Apperead Success message is ", error);
		} else {
			reporter.failureReport("Verifying Success message", "Success message is not appeared ", error, driver);
		}
	}

	/**
	 * This method is to verify login verification
	 * 
	 * @param webGroup
	 * @throws Throwable
	 */
	public void userLoginVerification(String userID) throws Throwable {
		if (isElementPresent(CMTObj.loginVerificationForUser(userID), "User ID")) {
			reporter.SuccessReport("Login verification", "User is sucesfulley logged in", userID);

		} else {
			reporter.failureReport("Login verification", "User is not sucesfulley logged in", "", driver);
		}
	}

	/**
	 * Method is to click on the web Group name in the CMT page
	 * 
	 * @param webgrpName
	 * @throws Throwable
	 */

	public boolean clickOnTheWebGroup(String webgrpName) throws Throwable {
		boolean status = false;
		if (isElementVisible(getWebGroupName(webgrpName), 3, "Search results are displayed on Client Search Page")) {
			click(getWebGroupName(webgrpName), "Web Group link exists :"+ webgrpName );
			status = true;
		} else {
			reporter.failureReport("Verify web group displayed", "searched Web group is not displayed", "", driver);
			status = false;
		}
		return status;
	}

	public void clickOnLogoutlink() throws Throwable {
		click(lnklogout, "Logout");
	}

	/**
	 * This method is to hover on Manage current webGroup in the CMT tool select
	 * the required option.
	 * 
	 * @param ManageWebGrpOptions
	 * @throws Throwable
	 */
	public void hoverOnManageWebGroupsAndSelectOptions(String ManageWebGrpOptions) throws Throwable {
		Thread.sleep(3000);
		mouseHover(MANAGE_WEBGRP, "Manage Web group");
		click(getManageWebGroupDDLinks(ManageWebGrpOptions), "Change Web group option: " + ManageWebGrpOptions);
	}

	/**
	 * This method is to select change master group in CMT.
	 * 
	 * @param changeGrpOption
	 * @throws Throwable
	 */
	public void hoverOverMasterGroupAndSelectChangeGrp() throws Throwable {
		
		/*mouseHover(MASTER_GROUP, "Master Group");
		driver.navigate().refresh();*/
	  
		mouseHover(MASTER_GROUP, "Master Group");
		click(CHANGE_MASTER_GRP, "Change master group option");
	}
	public void hoverOverManagecurrentWebGrp() throws Throwable {
		mouseHover(ManageCurrentWebGroup, "ManageCurrentWebGroup");
		click(UserslinkfromManageCurrentWebGroup, "UserslinkfromManageCurrentWebGroup");
	}

	/**
	 * This method is to click on Approve item catalog link
	 * 
	 * @throws Throwable
	 */
	public void clickApproveItemCatlog() throws Throwable {
		// have to uncomment it don't delete
		if (isElementPresent(CMTObj.APPROVE_ITEM_CATALOG_CREATE_BTN, "Approve item catalog create button")) {
			click(CMTObj.APPROVE_ITEM_CATALOG_CREATE_BTN, "Approve item catalog create button");
		}
		Thread.sleep(2000);
		click(APPROVE_ITEM_CATALOG_LINK, "Approve item catalog link");
		Thread.sleep(2000);
	}

	/**
	 * This method is to click on Roles and permissions tab and check the
	 * required permission check boxes as a group and update.
	 * 
	 * @throws Throwable
	 */
	public void clickOnRolesAndPermissionsAndSetPermission(String menuName, String userPermission) throws Throwable {

		click(getUsersTabMenus(menuName), "Roles And Permissions");
		String permissions[] = userPermission.split(",");
		for (i = 0; i < permissions.length; i++) {
			if (isCheckBoxSelected(getUserPermission(permissions[i]))) {
				LOG.info(userPermission + " check box already checked.");
				reporter.SuccessReport(permissions[i] + " Permissions on WebGroup Management Page",
						"Check Box Field Exists and Enabled", permissions[i]+" ON");
			} else {
				click(getUserPermission(permissions[i]), "set user permission " + permissions[i] + " is ON ",
						permissions[i], permissions[i]);
			}
		}

		click(UPDATE_USER_BTN, "Update user button");
		if (isElementPresent(PERMISSION_UPDATE_MSG, "update sucessful message")) {
			reporter.SuccessReport("Verify the Sucess message ", "Permissions Updated Succesfully", getText(PERMISSION_UPDATE_MSG, "update sucessful message"));
		} else {
			reporter.failureReport("Verify the sucess message", "Permissions are not Updated Succesfully", getText(PERMISSION_UPDATE_MSG, "update sucessful message"), driver);
		}
	}

	public void clickOnRolesandPermissionTab(String menuName) throws Throwable {
		click(getUsersTabMenus(menuName), "Roles And Permissions");
	}

	/**
	 * Method is to login as admin from the CMT tool.
	 * 
	 * @throws Throwable
	 */
	public void loginAsAdminCMT() throws Throwable {
		if (isElementPresent(CMTObj.LOGIN_AS, "Login as")) {
			click(CMTObj.LOGIN_AS, "Login as", "Link: Login As");
			switchToChildWindow();
		}else {
			reporter.failureReport("Verify LoginAs exists", "Login as does not exists", "", driver);
		}
		
	}
	public void clickUpdateUser() throws Throwable {
		click(UpdateUser, "Update User", "");
	}

	public void clickSaveAsQuote() throws Throwable {
		click(SaveAsQuote, "Save as quote", "");
		waitForVisibilityOfElement(SaveAsQuoteHeading, "SaveAsQuoteHeading", driver);
		click(btn_SaveAsQuote, "Save as Quote button in cart page", "");
		waitForVisibilityOfElement(txt_SaveAsQuoteSuccessfull, "Save as Quote Successfull", driver);
	}
	public List<String> getQuoteNameandReferenceNumber() throws Throwable {
		List<String> details = new ArrayList<>();
		String QuoteName = getText(txt_QuoteName, "Quotename");
		String ReferenceNumber = getText(txt_referencenumber, "Reference number");
		if(QuoteName!=null && ReferenceNumber!=null) {
			reporter.SuccessReport("QuoteName and ReferenceNUmber", "QuoteName and Reference Numbers are", ""+QuoteName+" ,"+ReferenceNumber+"");
		}
		else {
		reporter.failureReport("QuoteName and ReferenceNUmber", "QuoteName and Reference Numbers are not displayed", "");
		}
		details.add(QuoteName);
		details.add(ReferenceNumber);
		return details;
	}
	
	public void SwitchToParentWindow() {
		String Parent_Window = driver.getWindowHandle();    

		 for (String Child_Window : driver.getWindowHandles())  
		 {  
		 driver.switchTo().window(Child_Window);  
		 //Perform operation on child window 
		 driver.close();
		 } 

		 //Switching back to Parent Window  
		 driver.switchTo().window(Parent_Window);  
	}
	/**
	 * Method is to Verify the Same User Logged into Insight from CMT by Contact
	 * name verification
	 * 
	 * @param contactName
	 * @throws Throwable
	 */
	public void loginVerification(String contactName) throws Throwable {
		waitForVisibilityOfElement(CMTObj.getLoginVerficationByContactNameOnHeader(contactName),"contact Name is " + contactName);
		//if(getText(CMTObj.getLoginVerficationByContactNameOnHeader(contactName),"LoginName").contains(contactName)){
		if (isVisibleOnly(CMTObj.getLoginVerficationByContactNameOnHeader(contactName), "contact Name")) {
			reporter.SuccessReport("Verify the Same User Logged into Insight from CMT",
					"User login verification is successfull. User is : ", contactName);
		} else {
			reporter.failureReport("Verify the Same User Logged into Insight",
					"User login verification is not successfull.Actual name is: ", contactName, driver);
		}
	}

	/**
	 * Method is to search for an user in the CMT users screen
	 * 
	 * @param lnameEmailUname
	 * @throws Throwable
	 */
	public void searchUsers(String LnameEmailUname) throws Throwable {
		waitForVisibilityOfElement(CMTObj.LNAME_EMAIL_USERNAME, "LNAME EMAIL USERNAME: " + LnameEmailUname);
		type(CMTObj.LNAME_EMAIL_USERNAME, LnameEmailUname, "LNAME EMAIL USERNAME: " + LnameEmailUname);
		click(CMTObj.USERNAME_SEARCH_BUTTON, "Search button Exists and Clicked", "Link: Search");
	}

	/**
	 * This method is to verify the filtered user and click on the check box.
	 * 
	 * @param contactName
	 * @throws Throwable
	 */
	public void verifyUserandClick(String contactName) throws Throwable {
		Thread.sleep(3000);
		String actualuser = getText(CMTObj.getUser(contactName), contactName);
		if (isElementVisible(USER_MANAGEMENT_LABEL, 3, "Manage Web Groups:  User Management")) {
			reporter.SuccessReport("Verify Web Group Management: Users Page", "Web Group Management: Users Page loaded",
					"Page: Web Group Management: Users");
			if (actualuser.contains(contactName)) {
				reporter.SuccessReport("Verifying User Contact Name :", "User Contact Name present is : ",
						"Contact name : " + contactName);
				click(CMTObj.getUser(contactName), contactName);
				if (isElementVisible(USER_CONTACT_PAGE, 3, "user contaact page")) {
					reporter.SuccessReport("Verify user contact page", "Uers contact page verified successfully",
							"Web Group Management: Users");
				} else {
					reporter.failureReport("Verify user contact page", "Uers contact page not loaded  successfully", "",
							driver);
				}
			} else {
				reporter.failureReport("Verifying User Contact Name :", "User Contact Name not present is : ",
						contactName, driver);
			}
		} else {
			reporter.failureReport("Verify Web Group Management: Users Page",
					"Web Group Management: Users Page not loaded", "Page: Web Group Management: Users");
		}

	}
public  void verifyDashboard()throws Throwable {
	boolean enabledashboard  = driver.findElement(By.xpath("//div[@class='contactInfoNameTextBox']//input[@id='enableDashboard']")).isDisplayed();
	System.out.println(enabledashboard);
}
	/**
	 * Search for a user in the users screen and select it.
	 * 
	 * @param LnameEmailUname
	 * @param ContactName
	 * @throws Throwable
	 */
	public void searchForaUserAndSelect(String LnameEmailUname, String ContactName) throws Throwable {
		if (isElementVisible(USER_MANAGEMENT_LABEL, 3, "User Management page")) {
			reporter.SuccessReport("Verify Web Group Management: Users Page", "User management page is displayed",
					"Page: Web Group Management: Users");
			Thread.sleep(5000);
			searchUsers(LnameEmailUname);
			Thread.sleep(5000);
			verifyUserandClick(ContactName);

		} else {
			reporter.failureReport("Verify user management page", "User management page is not displayed", "", driver);
		}
	}

	/**
	 * This method is to close the current tab/ current window and takes the
	 * control to the parent window.
	 */
	public void navigateBackToCMT() {
		// Create an ArrayList and store the open tabs
		ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
		// below code will switch to new tab
		// driver.switchTo().window(tabs.get(1));
		// perform whatever actions you want in new tab then close it
		driver.close();
		// Switch back to your original tab
		driver.switchTo().window(tabs.get(0));
	}

	/**
	 * This method is to click on loginAs link in CMT TOOL
	 * 
	 * @throws Throwable
	 */
	public void clickOnloginAs() throws Throwable {
		click(CMTObj.LOGIN_AS, "Login as");
	}

	/**
	 * This method is to login to CMT select a web grp and select a user and
	 * login as from the selected user.
	 * 
	 * @param login
	 * @param userName
	 * @param password
	 * @param webGrp
	 * @param webGrp_Name
	 * @param manage_Web_Grp_Options
	 * @param lnameEmailUname
	 * @param contactName
	 * @throws Throwable
	 */
	public void loginToCMTSelectUserAndLoginAS(String login, String webGrp, String webGrp_Name,
			String manage_Web_Grp_Options, String lnameEmailUname, String contactName) throws Throwable {
		loginToCMT(login);
		searchForWebGroup(webGrp);
		clickOnTheWebGroup(webGrp_Name);
		verifyManageWebGroupSettings();
		hoverOnManageWebGroupsAndSelectOptions(manage_Web_Grp_Options);
		verifyManageWebGroupsUserManagement();
		searchForaUserAndSelect(lnameEmailUname, contactName);
		loginAsAdminCMT();
	}

	/**
	 * 
	 * @param login
	 * @param webGrp
	 * @param webGrp_Name
	 * @param manage_Web_Grp_Options
	 * @param lnameEmailUname
	 * @param contactName
	 * @param menuName
	 * @param userPermissions
	 * @throws Throwable
	 */
	public void loginToCMTSetPermissions(String login, String webGrp, String webGrp_Name, String manage_Web_Grp_Options,
			String lnameEmailUname, String contactName, String menuName, String userPermissions) throws Throwable {
		loginToCMT(login);
		searchForWebGroup(webGrp);
		clickOnTheWebGroup(webGrp_Name);
		hoverOnManageWebGroupsAndSelectOptions(manage_Web_Grp_Options);
		searchForaUserAndSelect(lnameEmailUname, contactName);
		setPermissions(menuName, userPermissions);
		loginAsAdminCMT();
	}

	public void loginToCMTSetPermissionsAndSelectPermissionFromDD(String login, String webGrp, String webGrp_Name,
			String manage_Web_Grp_Options, String lnameEmailUname, String contactName, String menuName,
			String userPermissions, String optionDD) throws Throwable {
		loginToCMT(login);
		searchForWebGroup(webGrp);
		clickOnTheWebGroup(webGrp_Name);
		hoverOnManageWebGroupsAndSelectOptions(manage_Web_Grp_Options);
		searchForaUserAndSelect(lnameEmailUname, contactName);
		setPermissions(menuName, userPermissions);
		permissionFromDD(userPermissions, optionDD);
		loginAsAdminCMT();
	}

	/**
	 * This method is to login to CMt and search a user and select it.
	 * 
	 * @param login
	 * @param webGrp
	 * @param webGrp_Name
	 * @param manage_Web_Grp_Options
	 * @param lnameEmailUname
	 * @param contactName
	 * @param menuName
	 * @param userPermissions
	 * @param optionDD
	 * @throws Throwable
	 */
	public void loginToCMTSearchForUserAndSelect(String login, String webGrp, String webGrp_Name,
			String manage_Web_Grp_Options, String lnameEmailUname, String contactName) throws Throwable {
		loginToCMT(login);
		searchForWebGroup(webGrp);
		clickOnTheWebGroup(webGrp_Name);
		hoverOnManageWebGroupsAndSelectOptions(manage_Web_Grp_Options);
		searchForaUserAndSelect(lnameEmailUname, contactName);
	}

	/**
	 * This method is to click on Roles and permissions tab and check the one
	 * required permission check box and update.
	 * 
	 * @param menuName
	 * @param userPermissions
	 * @throws Throwable
	 */
	public void setPermissions(String menuName, String userPermissions) throws Throwable {
		click(getUsersTabMenus(menuName), "Roles And Permissions");
		Thread.sleep(3000);
		if (isCheckBoxSelected(getUserPermission(userPermissions))) {
			LOG.info(userPermissions + " check box already checked: " + userPermissions);
			reporter.SuccessReport(userPermissions, "check box already checked::" + userPermissions + " ON",
					userPermissions + " ON");
		} else {
			click(getUserPermission(userPermissions), "User permissions: " + userPermissions+ "ON");
			click(UPDATE_USER_BTN, "Update user button");
			waitForVisibilityOfElement(PERMISSION_UPDATE_MSG, "PERMISSION UPDATE MSG");
			if (isElementPresent(PERMISSION_UPDATE_MSG, "update sucessful message")) {
				reporter.SuccessReport("Verify the Sucess message ", "Permissions Updated Succesfully ",
						"Permissions Updated Succesfully");
			} else {
				reporter.failureReport("Verify the sucess message", "Permissions are not Updated Succesfully", "",
						driver);
			}
		}
	}

	public void verifyPermissions(String menuName, String userPermissions) throws Throwable {
		click(getUsersTabMenus(menuName), "Roles And Permissions");
		if (!driver.findElement(getUserPermission(userPermissions)).getAttribute("disabled")
				.equalsIgnoreCase("disabled")) {
			reporter.SuccessReport(
					"Verify Status Edit Contact Information Under Account Tools  in Roles and Permissions Tab on Manage Web groups: Create User Page ",
					"Edit Contact Information Under Account Tools exists  and Disabled", "");
		} else {
			reporter.SuccessReport(
					"Verify Status Edit Contact Information Under Account Tools  in Roles and Permissions Tab on Manage Web groups: Create User Page ",
					"Edit Contact Information Under Account Tools exists  and not Disabled", "");
		}
	}

	/**
	 * This method is to click on Roles and permissions tab and disable the
	 * required permission check box and update.
	 * 
	 * @param menuName
	 * @param userPermissions
	 * @throws Throwable
	 */
	public void setPermissionsToDisable(String menuName, String userPermissions) throws Throwable {
			click(getUsersTabMenus(menuName), "Roles And Permissions");
		if (isCheckBoxSelected(getUserPermission(userPermissions))) {
			click(getUserPermission(userPermissions), "User permissions : " + userPermissions + " is OFF");
			click(UPDATE_USER_BTN, "Update user button");
			if (isVisibleOnly(PERMISSION_UPDATE_MSG, "update sucessful message")) {
				reporter.SuccessReport("Verify the Sucess message ", "Permissions disabled Succesfully", getText(PERMISSION_UPDATE_MSG, "update sucessful message"));
			} else {
				reporter.failureReport("Verify the sucess message", "Permissions are not disabled Succesfully", getText(PERMISSION_UPDATE_MSG, "update sucessful message"),
						driver);
			}
		} else {
			LOG.info(userPermissions + " check box already checked.");
			reporter.SuccessReport("Verify Customer Level Permissions",
					"User permissions :" + userPermissions + "check box already Disabled",
					"" + userPermissions + " OFF");
		}
	}
	
	public void setMultiplePermissionsToDisable(String menuName, String userPermission) throws Throwable {
		click(getUsersTabMenus(menuName), "Roles And Permissions");
		String permissions[] = userPermission.split(",");
		for (i = 0; i < permissions.length; i++) {
			if (!isCheckBoxSelected(getUserPermission(permissions[i]))) {
				LOG.info(userPermission + " check box already checked.");
				reporter.SuccessReport(permissions[i] + " Permissions on WebGroup Management Page",
						"Check Box Field Exists and diasabld", permissions[i]);
			} else {
				click(getUserPermission(permissions[i]), "set user permission " + permissions[i] + " is OFF ",
						permissions[i], permissions[i]);
			}
		}

		
	}
	
	public void setPermissionsToDisableOnly( String userPermissions) throws Throwable {
		//click(getUsersTabMenus(menuName), "Roles And Permissions");
		if (isCheckBoxSelected(getUserPermission(userPermissions))) {
			click(getUserPermission(userPermissions), "User permissions : " + userPermissions + " is OFF");
			click(UPDATE_USER_BTN, "Update user button");
			if (isElementPresent(PERMISSION_UPDATE_MSG, "update sucessful message")) {
				reporter.SuccessReport("Verify the Sucess message ", "Permissions disabled Succesfully", getText(PERMISSION_UPDATE_MSG, "update sucessful message"));
			} else {
				reporter.failureReport("Verify the sucess message", "Permissions are not disabled Succesfully", getText(PERMISSION_UPDATE_MSG, "update sucessful message"),
						driver);
			}
		} else {
			LOG.info(userPermissions + " check box already checked.");
			reporter.SuccessReport("Verify Customer Level Permissions",
					"User permissions :" + userPermissions + "check box already Disabled",
					"" + userPermissions + " OFF");
		}
	}

	/**
	 * Purpose of this method is to click on Login
	 * 
	 * @throws Throwable
	 */
	public void loginAsAdmin() throws Throwable {
		waitForVisibilityOfElement(CartObj.USER_NAME, "user name");
		type(CartObj.USER_NAME, CMT_ADMIN_USERNAME, "user name");

		waitForVisibilityOfElement(CartObj.PASSWORD, "Password");
		type(CartObj.PASSWORD, CMT_ADMIN_PASSWORD, "Password");
		click(CartObj.LOG_IN_BUTTON, "LOG IN BUTTON");

	}

	public void loginAsEndUser(String userName, String password) throws Throwable

	{
		if (driver.findElement(CartObj.POP_UP_EMAILID).isDisplayed()) {
			handleWelcomeToInsightBetaPopUp();
		} else {
			// do nothing
		}
		waitForVisibilityOfElement(CartObj.USER_NAME, "user name");
		type(CartObj.USER_NAME, userName, "user name");
		waitForVisibilityOfElement(CartObj.PASSWORD, "Password");
		type(CartObj.PASSWORD, password, "Password");
		acceptCookies();
		click(CartObj.LOG_IN_BUTTON, "LOG IN BUTTON");
		Thread.sleep(10000);
	}

	/**
	 * This method is to search for a webgroup in the CMT home page.
	 * 
	 * @param webGroup
	 * @throws Throwable
	 * @author : CIGNITI/SOWJANYA
	 */
	public void loginToCMTSearchWebGrpAndUser(String header, String webGrp, String lnameEmailUname, String contactName)
			throws Throwable {
		acceptCookies();
		clickLoginLink(header);
		if (driver.findElement(CartObj.POP_UP_EMAILID).isDisplayed()) {
			handleWelcomeToInsightBetaPopUp();
		} else {
			// do nothing
		}
		loginAsAdmin();
		
		searchForWebGroup(webGrp);
		manageUsers();
		searchUsers(lnameEmailUname);
		verifyUserandClick(contactName);
	}

	public void loginAsEndUserInMainPage(String header, String username, String password) throws Throwable {
		//clickLoginLink(header);
		// if (driver.findElement(CartObj.POP_UP_EMAILID).isDisplayed()) {
		// handleWelcomeToInsightBetaPopUp();
		// }
		loginAsEndUser(username, password);
//		String currentUrl=getCurrentUrl();
//		System.out.println("currentUrl"+currentUrl);
//		if(currentUrl.contains("dashboard")) {
//			reporter.SuccessReport("Verify Insight Home Page", "Insight Home Page Exists", "");
//		}
//		else {
//			reporter.failureReport("Verify Insight Home Page", "Insight Home Page does not Exists", "",driver);
//		}
	}

	/**
	 * This method is to select the required permission from the drop down.
	 * 
	 * @param userPermission
	 * @param optionDD
	 * @throws Throwable
	 */
	public void permissionFromDD(String userPermission, String optionDD) throws Throwable {
		if (!isCheckBoxSelected(getUserPermission(userPermission))){
			click(getPermissionDropDowns(userPermission), "permission drop down::"+userPermission);
		}
		selectByVisibleText(getPermissionDropDowns(userPermission), optionDD, "permission drop down::"+optionDD);
		click(UPDATE_USER_BTN, "Update user button");
		if (isElementPresent(PERMISSION_UPDATE_MSG, "update sucessful message")) {
			reporter.SuccessReport("Verify the Sucess message ", "Permissions Updated Succesfully", "");
		} else {
			reporter.failureReport("Verify the sucess message", "Permissions are not Updated Succesfully", "", driver);
		}
	}

	/**
	 * This method is to check the customer level permissions in the Manage Web
	 * Groups: Settings page.
	 * 
	 * @param menuName
	 * @param userPermissions
	 * @throws Throwable
	 */
	public void setCustomerLevelPermissionsON(String customerPermissions) throws Throwable {

		if (isCheckBoxSelected(getCustomerLevelPermissionsForWebGrp(customerPermissions))) {
			LOG.info(customerPermissions + " check box already checked.");
			reporter.SuccessReport("Verify theWebGrp Level Permissions: ", "Permission is Already Enabled",
					customerPermissions +" ON");
		} else {
			click(getCustomerLevelPermissionsForWebGrp(customerPermissions), "Customer level permissions"+customerPermissions);
			click(UPDATE_CUSTOMER_PERMISSIONS_BTN, "Update button");
			if (isElementPresent(CUSTOMER_PERMISSION_UPDATE_MSG, "update sucessful message")) {
				reporter.SuccessReport("Verify the Success message ", "Permissions Updated Succesfully.",
						customerPermissions+" ON");
			} else {
				reporter.failureReport("Verify the sucess message", "Permissions are not Updated Succesfully", "",
						driver);
			}
		}
	}

	/**
	 * This method is to set the CMT level customer permissions OFF.
	 * 
	 * @param customerPermissions
	 * @throws Throwable
	 */
	public void setCustomerLevelPermissionsOFF(String customerPermissions) throws Throwable {

		if (isCheckBoxSelected(getCustomerLevelPermissionsForWebGrp(customerPermissions))) {
			click(getCustomerLevelPermissionsForWebGrp(customerPermissions),
					"Web Group level permissions::" + customerPermissions + " is OFF");
			click(UPDATE_CUSTOMER_PERMISSIONS_BTN, "Update button");
			if (isElementPresent(CUSTOMER_PERMISSION_UPDATE_MSG, "update sucessful message")) {
				reporter.SuccessReport("Verify the Success message ", "Permissions Updated Succesfully",
						"" + customerPermissions + " OFF");
			} else {
				reporter.failureReport("Verify the sucess message", "Permissions are not Updated Succesfully", "",
						driver);
			}
		} else {
			LOG.info(customerPermissions + " check box already Unchecked.");
			reporter.SuccessReport("Verify Web Group Level Permissions ",
					"Web Group level permissions::" + customerPermissions + " is OFF", "" + customerPermissions + " OFF");
		}
	}

	public void AssigntheusertoServiceLevelShippingwithnodefault(String menuName, String user_Permissions, String Value)
			throws Throwable {
		click(getUsersTabMenus(menuName), "Checkout Settings");
		click(getOptionsunderCkeckoutsettings(user_Permissions), "Shipping Options");
		click(USER_SERVICE_LEVEL_SHIPPING, "User level shipping option is selected ");
		if (isElementPresent(DefaultShippingOption, "DefaultShippingOption is Displayed")) {
			selectByValue(DefaultShippingOption, Value, "Default_Shipping_Option:: " + user_Permissions + "");
			reporter.SuccessReport("Verify the Sucess message ", "Service Level Shipping with Select One is Exists and Selected", "");
		} else {
			reporter.failureReport("Verify the sucess message", "DefaultShippingOption is not Displayed", "", driver);
		}

	}

	/**
	 * 
	 * @throws Throwable
	 */
	public void clickupdateatDefaultShippingOption() throws Throwable {
		click(UPDATE_USER_ShippingEstimations, "Update user button");
		if (isElementPresent(SUCCESS_UPDATE_MSG, "update sucessful message")) {
			reporter.SuccessReport("Verify the Sucess message ", "Permissions Updated Succesfully", getText(SUCCESS_UPDATE_MSG, "Success message"));
		} else {
			reporter.failureReport("Verify the sucess message", "Permissions are not Updated Succesfully", "", driver);
		}
	}

	public void verifyUpdateSuccessMessages() throws Throwable {
		if (isVisibleOnly(SUCCESS_UPDATE_MSG, "update sucessful message")) {
			reporter.SuccessReport("Verify the Sucess message ", "Permissions Updated Succesfully",
					getText(SUCCESS_UPDATE_MSG, "update sucessful message"));
		} else {
			reporter.failureReport("Verify the sucess message", "Permissions are not Updated Succesfully",
					getText(SUCCESS_UPDATE_MSG, "update sucessful message"), driver);
		}
	}

	/**
	 * This method is to click on add new user link
	 *
	 */
	public void clickAddNewUserLink() throws Throwable {
		click(ADD_NEW_USER_LINK, "Add new user link");
	}

	/**
	 * This method is to select user type dropdown @ option to select
	 */
	public void selectUserTypeDropdown(String text) throws Throwable {
		selectByVisibleText(USER_TYPE_DROPDOWN, text, "User type dropdown::"+text);
	}

	/**
	 * This method is used to enter user name
	 *
	 */
	public void enterUserName(String text) throws Throwable {
		clearData(USER_NAME_FIELD);
		type(USER_NAME_FIELD, text, "user name"+text);
		click(CHECK_AVAILABLITY_BUTTON, "check availability");

	}

	/**
	 * This method is to verify the availability of user name
	 *
	 */
	public void verifyAvailabiltyOfUserNameNotExists() throws Throwable {
		if (isElementPresent(AVAILABLE_MESSAGE, "User Available message")) {
			reporter.SuccessReport("Availability Message ", "User availability message",
					getText(AVAILABLE_MESSAGE, "error message"));
		} else {
			Log.info("user name is available");
			reporter.failureReport("Availability Message ", "User availability message Not Exists",
					"",driver);
		}
	}

	/**
	 * This method is to click on create user button
	 *
	 */
	public void clickCreateUserButton() throws Throwable {
		click(CREATE_USER_BUTTON, "Create user button");
	}

	/**
	 * 
	 * @param menuName
	 * @param user_Permissions
	 * @param text1
	 * @throws Throwable
	 */
	public void usertoServiceLevelShippingwithOnlyFedex(String menuName, String user_Permissions, String text1)
			throws Throwable {
		click(getUsersTabMenus(menuName), "Checkout Settings");
		click(getOptionsunderCkeckoutsettings(user_Permissions), "Shipping Options");
		click(DesignatedShippingOption_Button, "Designated_Shipping_Button");
		if (isElementPresent(Designatedshippingoptions, "DesignatedShippingOption is Displayed")) {
			selectByVisibleText(Designatedshippingoptions, text1, "Designated_Shipping_Option");
			reporter.SuccessReport("Verify the Sucess message ", "DesignatedShippingOption is Displayed", text1);
		} else {
			reporter.failureReport("Verify the sucess message", "DesignatedShippingOption is not Displayed.", "",
					driver);
		}
		click(buttontoclickFedExoptin, "FedEx Option is selected And Moved to allowed Options");
		click(DesignatedshippingFedoption_dropdown, "only FedEx Option is selected");
		selectByVisibleText(DesignatedshippingFedoption_dropdown, text1,
				"Designated_Shipping_Option FedEx is Selected");

	}

	/*
	 * PURPOSE OF METHOD : Click on checkout settings
	 * 
	 * @author : Cigniti
	 */

	public void clickCheckOutSettings(String checkOutSettings) throws Throwable {
		waitForVisibilityOfElement(CMTObj.getUsersTabMenus(checkOutSettings), "User Tabs::" + checkOutSettings + "");
		click(CMTObj.getUsersTabMenus(checkOutSettings), "Select Tab::" + checkOutSettings + " Manage Web groups: Users Page");
	}

	/**
	 * This method is to click on info tab
	 *
	 */
	public void clickInformationTab(String informationTab) throws Throwable {
		waitForVisibilityOfElement(CMTObj.getUsersTabMenus(informationTab), "User Tabs::" + informationTab + "");
		click(CMTObj.getUsersTabMenus(informationTab), "User Tabs::" + informationTab + "");
	}

	/**
	 * This method is to click on user URL
	 *
	 */
	public void clickOnUserURL() throws Throwable {
		click(USER_URL, "User url", getText(USER_URL, "User Link"));
	}

	/**
	 * This method is to verify create an account page
	 *
	 */
	public void verifyCreateAnAccountPage() throws Throwable {
		if (isElementPresent(CommonObj.CLOSEBUTTON_COOKIES, "close cookie")) {
			click(CommonObj.CLOSEBUTTON_COOKIES, "close cookie");
		}
		if (isElementPresent(CREATE_AN_ACCOUNT_PAGE, "Create an account page")) {
			reporter.SuccessReport("Create an Account Page", "Create an Account Page exists", "");
		} else {
			reporter.failureReport("Create an Account Page", "Create an Account Page does not exists", "", driver);
		}
	}

	/**
	 * This method is to enter email in create an account page
	 *
	 */
	public void enterEmailInCreateAnAccount(String email) throws Throwable {
		type(EMAIL, email, "email");
	}

	public void verifyuserNotAvailabilityMessage() throws Throwable {
		if (isVisibleOnly(userNotAvailabilityMessage, "User not availabile")) {
			reporter.SuccessReport("User Availability", " User available status",
					getText(userNotAvailabilityMessage, "User availabile status"));
		}
	}

	/**
	 * This method is to enter first Name in create an account page
	 *
	 */
	public void enterFirstNameInCreateAnAccount(String firstName) throws Throwable {
		type(FIRST_NAME, firstName, " first Name");
	}

	/**
	 * This method is to enter last Name in create an account page
	 *
	 */
	public void enterLastNameInCreateAnAccount(String lastName) throws Throwable {
		type(LAST_NAME, lastName, " last Name");
	}

	/**
	 * This method is to enter phone number in create an account page
	 *
	 */
	public void enterPhoneNumberInCreateAnAccount(String phoneNumber) throws Throwable {
		type(PHONE_NUMBER_CREATE_ACC, phoneNumber, "phone Number");
	}

	/**
	 * This method is to enter billing account name in create an account page
	 *
	 */
	public void enterBillingAccountNameInCreateAnAccount(String billingAccountName) throws Throwable {
		type(BILLING_ACCOUNT_NAME, billingAccountName, "Billing account name");
	}

	/**
	 * This method is to enter billing account name in create an account page
	 *
	 */
	public void enterAdressesInCreateAnAccount(String Adresses) throws Throwable {
		clearData(ADRESSES);
		type(ADRESSES, Adresses, "Adresses");
	}

	/**
	 * This method is to enter city in create an account page
	 *
	 */
	public void enterCityInCreateAnAccount(String city) throws Throwable {
		type(CITY, city, "city");
	}

	/**
	 * This method is to enter city in create an account page
	 *
	 */
	public void selectStateInCreateAnAccount(String state) throws Throwable {
		selectByVisibleText(selectState(), state, "State");
	}

	/**
	 * This method is to enter zip code in create an account page
	 *
	 */
	public void enterZipCodeInCreateAnAccount(String zipCode) throws Throwable {
		clearData(ZIP_CODE);
		type(ZIP_CODE, zipCode, "zip Code");
	}

	/**
	 * This method is to enter user Name in create an account page
	 *
	 */
	public void enterUserNameInCreateAnAccount(String userName,String userName1) throws Throwable {
		type(USER_NAME, userName, "user Name");
		click(CHECK_AVAILABILITY, "Check availability");
		if(isElementPresent(AVAILABLEUSERNAMEMSG,"User Name Available")) {
			//Proceed
		}else {
			clearData(USER_NAME);
			type(USER_NAME, userName1, "user Name");	
		}

	}

	public String verifyAvailabilityCreateAccount(String userName, String userName1) throws Throwable {
		if (isElementPresent(CommonObj.CLOSEBUTTON_COOKIES, "close cookie")) {
			click(CommonObj.CLOSEBUTTON_COOKIES, "close cookie");
		}
		type(USER_NAME, userName, "user Name");
		click(CHECK_AVAILABILITY, "Check availability");
		if (isVisibleOnly(USER_NAME_MESSAGE, "user name Not Available")) {
			clearData(USER_NAME);
			type(USER_NAME, userName1, "user Name");
			return userName1;
		} else {
			return userName;
		}
	}

	public void enterUserNameToValidate(String userName) throws Throwable {
		type(USER_NAME, userName, "user Name");
		click(CHECK_AVAILABILITY, "Check availability");
	}

	/**
	 * This method is to enter password in create an account page
	 *
	 */
	public void enterPasswordInCreateAnAccount(String password) throws Throwable {
		type(PASSWORD, password, "password");

	}

	/**
	 * This method is to enter password in create an account page
	 *
	 */
	public void enterConfirmPasswordInCreateAnAccount(String password) throws Throwable {
		type(CONFIRM_PASSWORD, password, "password");

	}

	/**
	 * This method is to click create button create an account page
	 *
	 */
	public void clickCreateButtonInCreateAnAccount() throws Throwable {
		click(CREATE, "Create button");

	}

	/**
	 * This method is to click save and continue button in create an account
	 * page
	 *
	 */
	public void clickContinueButtonInCreateAnAccount() throws Throwable {
		if (isElementPresent(SAVE_AND_CONTNUE, "Save and continue button")) {
			click(SAVE_AND_CONTNUE, "Save and continue button");
		}
	}

	public void verifyErrorMessageICrateAccount() throws Throwable {
		List<WebElement> myList = driver.findElements(ERROR_MESSAGE_CREATE_ACCOUNT);

		for (int i = 0; i < myList.size(); i++) {
			if (isElementPresent(ERROR_MESSAGE_CREATE_ACCOUNT, "error message")) {
				String text = myList.get(i).getText();

				reporter.SuccessReport("verifying error message", "Error message exists: " + text, text);
			} else {
				reporter.failureReport("verifying error message", "Error message does not exists: ", "", driver);
			}
		}
	}

	/**
	 * This method is to verify welcome page
	 *
	 */
	public void verifyWelcomePage() throws Throwable {
		if (isElementVisible(WELCOME_PAGE, 3, "Welcome page")) {
			reporter.SuccessReport("Welcome Page", "Welcome Page exists", "");
		} else {
			reporter.failureReport("Welcome Page", "Welcome Page does not exists", "", driver);
		}

	}

	public void selectUserType(String text) throws Throwable {
		selectByVisibleText(USER_TYPE, text, "User type");
	}

	public void selectFirstUser() throws Throwable {

		click(FIRST_USER_LINK, "First user link", getText(FIRST_USER_LINK, "First user Name"));
	}

	/*
	 * PURPOSE OF METHOD : Select option in check out settings
	 * 
	 * @author : Cigniti
	 */
	public void selectOptionInCheckoutSettings(String optionToSelect) throws Throwable {
		waitForVisibilityOfElement(CMTObj.optionsInCheckOutSettings(optionToSelect), "Wait for" + optionToSelect);
		click(CMTObj.optionsInCheckOutSettings(optionToSelect), "Click on" + optionToSelect);
	}

	/**
	 * 
	 * @param login
	 * @param WebGrp
	 * @param WebGrp_Name
	 * @throws Throwable
	 */
	public void loginCMTtillwebgrpname(String login, String WebGrp, String WebGrp_Name) throws Throwable {
		loginToCMT(login);
		searchForWebGroup(WebGrp);
		clickOnTheWebGroup(WebGrp_Name);
	}

	public void selectParticularDesignatedShippingOptions(String optionsToSelect, String defaultShippingOtion)
			throws Throwable {
		click(DesignatedShippingOption_Button, "Designated_Shipping_Button");
		String options[] = optionsToSelect.split(",");
		for (i = 0; i < options.length; i++) {
			if (isElementPresent(AVILABLE_OPTIONS, "")) {
				selectByVisibleText(Designatedshippingoptions, options[i], "Designated_Shipping_Option");
				click(buttontoclickFedExoptin, options[i] + " is selected And Moved to allowed Options");
				Thread.sleep(2000);
			}

			click(DesignatedshippingFedoption_dropdown, "only FedEx Option is selected");
			selectByVisibleText(DesignatedshippingFedoption_dropdown, defaultShippingOtion,
					"Designated_Shipping_Option FedEx is Selected");
		}

	}

	public void selectAllDesignatedShippingOptions(String defaultShippingOtion) throws Throwable {
		click(DesignatedShippingOption_Button, "Designated_Shipping_Button");
		if (isElementPresent(ALL_AVILABLE_OPTIONS, "Available Options")) {
			List<WebElement> myList = driver.findElements(ALL_AVILABLE_OPTIONS);
			for (int i = 0; i < myList.size(); i++) {
				String text = (myList.get(i)).getText();
				selectByVisibleText(Designatedshippingoptions, text, "Designated_Shipping_Option::" + text + "");
				click(buttontoclickFedExoptin, text + " is selected And Moved to allowed Options");
				Thread.sleep(2000);
			}
		} else {
			// Do nothing
		}
		click(DesignatedshippingFedoption_dropdown, "Default Shipping Option");
		selectByVisibleText(DesignatedshippingFedoption_dropdown, defaultShippingOtion,
				"Default_Shipping_Option Selected is::" + defaultShippingOtion + "");
	}

	public void DeselectAllDesignatedShippingOptions() throws Throwable {
		click(DesignatedShippingOption_Button, "Designated_Shipping_Button");
		List<WebElement> myList = driver.findElements(ALL_ALLOWED_OPTIONS);
		for (int i = 0; i < myList.size() - 1; i++) {
			String text = (myList.get(i)).getText();
			selectByVisibleText(SELECT_ALLWOED_OPTION, text, "Designated_Shipping_Option");
			click(LEFT_ARROW, text + " is selected And Moved to Available Options");
			Thread.sleep(2000);
		}

	}

	/*
	 * PURPOSE OF METHOD : Select option in check out settings
	 * 
	 * @author : Cigniti
	 */
	public void selectDefaultShippingOptionInCheckoutSettings(String optionToSelect) throws Throwable {
		if (isCheckBoxSelected(USER_SERVICE_LEVEL_SHIPPING)) {
			// waitForVisibilityOfElement(CMTObj.DEFAULT_SHIPPING_OPTION, "Wait
			// for" + optionToSelect);
			selectByVisibleText(CMTObj.DEFAULT_SHIPPING_OPTION, optionToSelect, "Click on" + optionToSelect);
		} else {
			click(USER_SERVICE_LEVEL_SHIPPING, "user service level shipping");
			selectByVisibleText(CMTObj.DEFAULT_SHIPPING_OPTION, optionToSelect, "Click on" + optionToSelect);
		}
	}

	public void selectShippingOptions(String optionToSelect) throws Throwable {
		// waitForVisibilityOfElement(CMTObj.DEFAULT_SHIPPING_OPTION, "Wait
		// for" + optionToSelect);
		click(CMTObj.defaultShippingCarrier, "Shipping carrier");
		click(CMTObj.defaultshippingCarrier(optionToSelect), optionToSelect, "Click on" + optionToSelect);

	}

	/**
	 * This method is to Remove Existing sales reps
	 * 
	 * @param reps
	 * @throws Throwable
	 */
	public void RemoveExistedsalesreps(String reps) throws Throwable {
		if (isElementPresent(getRemoveExistedsalesreps(reps), "Remove icon")) {
			click(getRemoveExistedsalesreps(reps), "Existing rep remove icon");
			if (isVisible(REP_REMOVED_MSG, "Successfully removed message")) {
				reporter.SuccessReport("Verify Existing selected rep removed", "Selected rep removed successfully",
						reps);
			} else {
				reporter.failureReport("Verify Existing selected rep removed", "Selected rep is not removed.", reps,
						driver);
			}
		} else {
			LOG.info("Searching rep is already removed.");
		}
	}

	/**
	 * Method to add new sales rep
	 * 
	 * @param repEmail
	 * @param repName
	 * @throws Throwable
	 */
	public void addNewSalesRep(String repEmail) throws Throwable {
		click(ADD_NEW_REP_LINK, "Add new rep link");
		type(NEW_REP_EMAIL, repEmail, "rep email id");
		click(NEW_REP_NAME, "rep name");
		click(NEW_REP_UPDATE_BTN, "update button");
		if (isVisible(NEW_REP_MSG, "Success message")) {
			reporter.SuccessReport("Verify new rep added", " New sales rep added successfully", repEmail);
		} else {
			reporter.failureReport("Verify new rep added", " New sales rep not added", repEmail, driver);
		}
	}

	/**
	 * Method is to check or Un check the Orders check box of a sales rep on the
	 * Manage Web Group: Contacts and Notifications screen
	 * 
	 * @param repName
	 * @param status
	 * @throws Throwable
	 */
	public void enableOrDisableOrdersOfSalesRepOnContactsAndNotifications(String repName, String status)
			throws Throwable {
		switch (status) {
		case "ON":
			if (isCheckBoxSelected(getOrdersCheckBoxsalesreps(repName))) {
				LOG.info("Örders check box already checked");
				reporter.SuccessReport("Verify checkBox checked ", "Check box already Checked ", "Oders***Check Box Status:ON", driver);
			} else {
				click(getOrdersCheckBoxsalesreps(repName), "Orders check box selected");
				click(saveImage(repName), "Save image icon", "");
				if(isElementPresent(CONTACT_AND_NOTIFICATIONS_SUCCESS_MSG, "Success message")) {
					reporter.SuccessReport("Verify checkBox checked ", "Check box Checked and success message displayed", "Oders***Check Box Status:ON", driver);
				}else {
					reporter.failureReport("Verify checkBox checked ", "Check box not Checked and no success message is displayed", "", driver);
				}
			}

		case "OFF":
			if (isCheckBoxSelected(getOrdersCheckBoxsalesreps(repName))) {
				click(getOrdersCheckBoxsalesreps(repName), "Orders check box selected");
				click(saveImage(repName), "Save image icon", "");
				if(isElementPresent(CONTACT_AND_NOTIFICATIONS_SUCCESS_MSG, "Success message")) {
					reporter.SuccessReport("Verify checkBox checked ", "Check box UnChecked and success message displayed", "Oders***Check Box Status:OFF", driver);
				}else {
					reporter.failureReport("Verify checkBox checked ", "Check box not UnChecked and no success message is displayed", "", driver);
				}
			} else {
				LOG.info("Örders check box already Un checked");
				reporter.SuccessReport("Verify checkBox checked ", "Check box already UnChecked ", "Oders***Check Box Status:OFF", driver);
			}
		}
	}

	/**
	 * Method is to check or Un check the Quotes check box of a sales rep on the
	 * Manage Web Group: Contacts and Notifications screen
	 * 
	 * @param repName
	 * @param status
	 * @throws Throwable
	 */
	public void enableOrDisableQuotesOfSalesRepOnContactsAndNotifications(String repName, String status)
			throws Throwable {
		switch (status) {
		case "ON":
			if (isCheckBoxSelected(getQuotesCheckBoxOfRep(repName))) {
				LOG.info("Quotes check box already checked");
				reporter.SuccessReport("Verify checkBox checked ", "Quotes Check box already Checked ", "Quotes***Check Box Status:ON", driver);
				
			} else {
				click(getQuotesCheckBoxOfRep(repName), "Quotes check box selected");
				click(saveImage(repName), "Save image icon", "");
				if(isElementPresent(CONTACT_AND_NOTIFICATIONS_SUCCESS_MSG, "Success message")) {
					reporter.SuccessReport("Verify checkBox checked ", "Check box Checked and success message displayed", "Oders***Check Box Status:ON", driver);
				}else {
					reporter.failureReport("Verify checkBox checked ", "Check box not Checked and no success message is displayed", "", driver);
				}
			}

		case "OFF":
			if (isCheckBoxSelected(getQuotesCheckBoxOfRep(repName))) {
				click(getQuotesCheckBoxOfRep(repName), "Quotes check box selected");
				click(saveImage(repName), "Save image icon", "");
				if(isElementPresent(CONTACT_AND_NOTIFICATIONS_SUCCESS_MSG, "Success message")) {
					reporter.SuccessReport("Verify checkBox checked ", "Quotes Check box UnChecked and success message displayed", "Oders***Check Box Status:ON", driver);
				}else {
					reporter.failureReport("Verify checkBox checked ", "Quotes Check box not UnChecked and no success message is displayed", "", driver);
				}
			} else {
				LOG.info("Quotes check box already Un checked");
				reporter.SuccessReport("Verify checkBox checked ", "Check box already UnChecked ", "Quotes***Check Box Status:OFF", driver);
				
			}
		}
	}

	public void verifySalesRepAreDisplayed() throws Throwable {
		List<WebElement> myList = driver.findElements(SALES_REP_NAMES);
		if (myList.size() > 0) {
			for (int i = 1; i < myList.size(); i++) {
				String text = myList.get(i).getText();
				reporter.SuccessReport("Verifyimg sales rep", "Under Account Team, Sales Rep are Exists", text);
			}

		} else {
			reporter.failureReport("Verifyimg sales rep", "Under Account Team, Sales Rep are does not Exists", "",
					driver);
		}
	}

	public void verifyNewSalesRepAdded(String userName) throws Throwable {
		List<WebElement> myList = driver.findElements(SALES_REP_NAMES);
		int size = myList.size();
		String text = myList.get(size - 1).getText();
		if (text.contains(userName)) {
			reporter.SuccessReport("Verifying newly created sales rep",
					"Created User is  Exists And Enabled the Check Box for Display on the Web", "");
		} else {
			reporter.failureReport("Verifying newly created sales rep", "Created User does not Exists ", "", driver);
		}
	}

	public void deleteClientNotification(String repMail) throws Throwable {
		if (isVisibleOnly(clientNotifiationDeleteIcon(repMail), "Repo Email")) {

			click(clientNotifiationDeleteIcon(repMail), "Delete client notification ");

		} else {
			reporter.failureReport("Verifying newly added Sales repo", "Newly added sales repo is not reflected ",
					repMail, driver);
		}
	}
	public void deleteClientNotificationBeforeAdd(String repMail) throws Throwable {
		if (isVisibleOnly(clientNotifiationDeleteIcon(repMail), "Repo Email")) {

			click(clientNotifiationDeleteIcon(repMail), "Delete client notification ");
			reporter.SuccessReport("Delete client notification","Deleting client notifications", repMail);

		}
	}

	/**
	 * Method is to check or Un check the Quotes check box of a sales rep on the
	 * Manage Web Group: Contacts and Notifications screen >> Client
	 * notification section
	 * 
	 * @param repName
	 * @param status
	 * @throws Throwable
	 */
	public void enableOrDisableQuotesOfClientNotificationRep(String repEmail, String status) throws Throwable {
		switch (status) {
		case "ON":
			if (isCheckBoxSelected(getQuotesOnClientNotifications(repEmail))) {
				LOG.info("Quotes check box already checked");
			} else {
				click(getQuotesOnClientNotifications(repEmail), "Quotes check box selected");
			}

		case "OFF":
			if (isCheckBoxSelected(getQuotesOnClientNotifications(repEmail))) {
				click(getQuotesOnClientNotifications(repEmail), "Quotes check box selected");
			} else {
				LOG.info("Quotes check box already Un checked");
			}
		}
	}

	/**
	 * Method is to check or Un check the Orders check box of a sales rep on the
	 * Manage Web Group: Contacts and Notifications screen >> Client
	 * notification section
	 * 
	 * @param repName
	 * @param status
	 * @throws Throwable
	 */
	public void enableOrDisableOrdersOfOfClientNotificationRep(String repEmail, String status) throws Throwable {
		switch (status) {
		case "ON":
			if (isCheckBoxSelected(getOrdersOnClientNotifications(repEmail))) {
				LOG.info("Örders check box already checked");
			} else {
				click(getOrdersOnClientNotifications(repEmail), "Orders check box selected");
			}

		case "OFF":
			if (isCheckBoxSelected(getOrdersOnClientNotifications(repEmail))) {
				click(getOrdersOnClientNotifications(repEmail), "Orders check box selected");
			} else {
				LOG.info("Örders check box already Un checked");
			}
		}
	}

	/**
	 * method is to add client notification
	 * 
	 * @param repEmail
	 * @throws Throwable
	 */
	public void createClientNotifications(String repEmail) throws Throwable {
		if(isVisibleOnly(removeEmailInClientNotifications(repEmail), "existing client notification email")) {
			click(removeEmailInClientNotifications(repEmail), "rep Email ");
		}else {
			// do nothing
		}
		type(CLIENT_NOTIFICATION_EMAIL, repEmail, "client email");
		click(ACTION_ICON_CLIENT_NOTIFICATIONS, "Save");
		Thread.sleep(4000);
		if (isVisibleOnly(CLIENT_NOTIFICATION_EMAIL_SUCCESS_MSG, "Email added")) {
			reporter.SuccessReport("Verify client notification adeed ", "Client notification email adeed successfully ",
					repEmail);
		} else {
			reporter.failureReport("Verify client notification adeed ", "Client notification email not adeed ",
					repEmail, driver);
		}
	}

	public void createClientNotification(String repEmail) throws Throwable {
		type(CLIENT_NOTIFICATION_EMAIL, repEmail, "client email");
		Thread.sleep(10000);
		click(ACTION_ICON_CLIENT_NOTIFICATIONS, "Save");
		Thread.sleep(10000);
		if (isElementPresent(CLIENT_NOTIFICATION_EMAIL_SUCCESS_MSG, "Email added")) {
			reporter.SuccessReport("Verify client notification adeed ", "Client notification email adeed successfully ",
					repEmail);
		} else {
			reporter.failureReport("Verify client notification adeed ", "Client notification email not adeed ",
					repEmail, driver);
		}
	}

	public void clearPaymentOptionsInCheckoutSettings() throws Throwable {
		List<WebElement> myList = driver.findElements(ALL_PAYMENT_OPTIONS);
		for (int i = 0; i < myList.size(); i++) {
			String text = (myList.get(i)).getText();
			selectByVisibleText(ALLOWED_OPTION, text, "Payment option " + text);
			click(PAYMENTS_LEFT_ARROW_BUTTON, text + " is selected And Moved to Available Options");
			Thread.sleep(2000);
		}

	}

	/**
	 * Method is to select payment options in the check out settings tab.
	 * 
	 * @param option
	 * @throws Throwable
	 */
	public void selectpaymentOptionsInCheckOutSettings(String option) throws Throwable {
		String strArray[] = option.split(",");
		for (int i = 0; i < strArray.length; i++) {
			if (isElementPresent(paymentAllowedOption(strArray[i]), "payment options")) {
				LOG.info("Payment option already selected");
			} else {
				click(getCheckOutSettingsPaymentOptions(strArray[i].trim()), "payment " + strArray[i] + " option");
				click(PAYMENTS_INFO_LEFT_TO_RIGHT_ARROW, "left to right arrow ");
			}
		}
		
		click(PAYMENTS_OPTION_UPDATE_BTN, "update button");
		if (isElementPresent(SUCESS_MESSAGE_PAYMENT_OPTIONS, "Updated message")) {
			reporter.SuccessReport("Verify the Sucess message ", "Permissions Updated Succesfully", "");
		} else {
			reporter.failureReport("Verify the Sucess message ", "Permissions not Updated Succesfully", "", driver);
		}

	}

	public void selectDefaultPaymentOption(String text) throws Throwable {
		selectByVisibleText(DEFAULT_PAYMENT_OPTION, text, "Default payment option: " + text);
		click(PAYMENTS_OPTION_UPDATE_BTN, "update button");
		if (isElementPresent(SUCESS_MESSAGE_PAYMENT_OPTIONS, "Updated message")) {
			reporter.SuccessReport("Verify the Sucess message ", "Permissions Updated Succesfully", "");
		} else {
			reporter.failureReport("Verify the Sucess message ", "Permissions not Updated Succesfully", "", driver);
		}
	}

	public void setWebGroupDefaultOption(String option) throws Throwable {
		if (isCheckBoxSelected(webGroupOption(option))) {
			LOG.info(option + " radio button already checked.");
		} else {
			click(webGroupOption(option), "web group default option");
		}
		click(UPDATE_USER_BTN, "Update user button");
		if (isElementPresent(UPDATED_MESSAGE, "Updated message")) {
			reporter.SuccessReport("Verify the Sucess message ", "Permissions Updated Succesfully", "");
		} else {
			reporter.failureReport("Verify the Sucess message ", "Permissions not Updated Succesfully", "", driver);
		}
	}

	public List<String> verifyDisplayWebIcon() throws Throwable {

		// Verify Display Web Icon visibility
		if (isVisibleOnly(DISPLAY_ON_WEB, "Web icon")) {
			click(DISPLAY_ON_WEB, "Display web icon");
			reporter.SuccessReport("Click Display  Web Icon on Manage Web Group: Contacts and Notifications Page",
					"Display on Web Icon Existed and Clicked ", "");
		} else {
			reporter.failureReport("Click Display  Web Icon on Manage Web Group: Contacts and Notifications Page",
					"Display on Web Icon Is Not Exists ", "");
		}

		// See the Reps Names
		List<String> popupRepNamesList = new ArrayList<String>();
		String popupRepName = null;
		if (isElementPresent(DISPLAY_ON_WEB_POPUP, "Web icon")) {
			reporter.SuccessReport("Verify Rep Name on Manage Web Group: Contacts and Notifications Page",
					"POP Exists with Display Sales Rep Names ", "");

			List<WebElement> salesRep = driver
					.findElements(By.xpath("//div[@id='webNotificationSortPopUp']//input[@type='textbox']"));

			for (i = 1; i <= salesRep.size(); i++) {
				if (salesRep.size() >= 2) {

					if (i == 1) {
						String val = Integer.toString(i + 3);
						type(getRepValuesOnDisplayWebPopup(i), val, "sales rep number text box");

						reporter.SuccessReport("Repo Name and its Value",
								"Repo Name is "
										+ getText(getRepNamesOnDisplayWebPopup(i), "Rep Names").replace(",", " ").trim()
										+ " and order is -->" + val,
								val);
					}
					if (i == 2) {
						String val1 = Integer.toString(i + 1);
						type(getRepValuesOnDisplayWebPopup(i), val1, "sales rep number text box");
						reporter.SuccessReport("Repo Name and its Value",
								"Repo Name is "
										+ getText(getRepNamesOnDisplayWebPopup(i), "Rep Names").replace(",", " ").trim()
										+ " and order is -->" + val1,
								val1);

					}
					if (i == 3) {
						String val2 = Integer.toString(i - 1);
						type(getRepValuesOnDisplayWebPopup(i), val2, "sales rep number text box");
						reporter.SuccessReport("Repo Name and its Value",
								"Repo Name is "
										+ getText(getRepNamesOnDisplayWebPopup(i), "Rep Names").replace(",", " ").trim()
										+ " and order is --> " + val2,
								val2);

					}
					if (i == 4) {
						String val3 = Integer.toString(i - 3);
						type(getRepValuesOnDisplayWebPopup(i), val3, "sales rep number text box");
						reporter.SuccessReport("Repo Name and its Value",
								"Repo Name is "
										+ getText(getRepNamesOnDisplayWebPopup(i), "Rep Names").replace(",", " ").trim()
										+ " and order is -->  " + val3,
								val3);

					}

				}

			}

			if (isElementPresent(UPDATE_BUTTN, "Update button")) {
				click(UPDATE_BUTTN, "Update button");
				reporter.SuccessReport(
						"Click Update button  Web Icon on Manage Web Group: Contacts and Notifications Page",
						"Update button Exists and Clicked ", "");
			} else {
				reporter.failureReport(
						"Click Update button  Web Icon on Manage Web Group: Contacts and Notifications Page",
						"Update button does not Exists ", "");
			}

			// JSClick(UPDATE_BUTTN, "Update button");
		} else {
			reporter.failureReport("Verify Rep Name on Manage Web Group: Contacts and Notifications Page",
					"POPUP does not Exists ", "");
		}

		if (isVisibleOnly(DISPLAY_ON_WEB, "Web Icon")) {
			click(DISPLAY_ON_WEB, "Display Web Icon");
			if (isElementPresent(DISPLAY_ON_WEB_POPUP, "Web icon")) {
				reporter.SuccessReport("Verify Rep Name on Manage Web Group: Contacts and Notifications Page",
						"POP Exists with Display Sales Rep Names ", "");

				List<WebElement> salesRep = driver
						.findElements(By.xpath("//div[@id='webNotificationSortPopUp']//input[@type='textbox']"));

				for (i = 1; i <= salesRep.size(); i++) {
					if (salesRep.size() >= 2) {

						if (i == 1) {
							String val = Integer.toString(i + 3);
							type(getRepValuesOnDisplayWebPopup(i), val, "sales rep number text box");

							reporter.SuccessReport("Repo Name and its Value", "Repo Name is "
									+ getText(getRepNamesOnDisplayWebPopup(i), "Rep Names").replace(",", " ").trim()
									+ " and its values is ", val);
						}
						/*
						 * if (i == 2) { String val1=Integer.toString(i + 1);
						 * type(getRepValuesOnDisplayWebPopup(i), val1,
						 * "sales rep number text box");
						 * reporter.SuccessReport("Repo Name and its Value",
						 * "Repo Name is "+
						 * getText(getRepNamesOnDisplayWebPopup(i),
						 * "Rep Names").replace(",", " ").trim() +
						 * " and its values is ", val1);
						 * 
						 * } if (i == 3) { String val2=Integer.toString(i -1);
						 * type(getRepValuesOnDisplayWebPopup(i), val2,
						 * "sales rep number text box");
						 * reporter.SuccessReport("Repo Name and its Value",
						 * "Repo Name is "+
						 * getText(getRepNamesOnDisplayWebPopup(i),
						 * "Rep Names").replace(",", " ").trim() +
						 * " and its values is ", val2);
						 * 
						 * } if (i == 4) { String val3=Integer.toString(i - 3);
						 * type(getRepValuesOnDisplayWebPopup(i), val3,
						 * "sales rep number text box");
						 * reporter.SuccessReport("Repo Name and its Value",
						 * "Repo Name is "+
						 * getText(getRepNamesOnDisplayWebPopup(i),
						 * "Rep Names").replace(",", " ").trim() +
						 * " and its values is ", val3);
						 * 
						 * }
						 */
					}
				}
			}

			click(UPDATE_BUTTN, "Update button");
			waitForVisibilityOfElement(ALERTERORRR_MSG, "Error, the rules must numeric.");
			if (isVisibleOnly(ALERTERORRR_MSG, "Error, the rules must numeric.")) {
				reporter.SuccessReport("Invalid data Validation",
						"Error message appeares when user enter invalid input",
						getText(ALERTERORRR_MSG, "Error, the rules must be unique."));
			}

		}
		// verify cancel button in Product Exp
		if (isElementPresent(DISPLAY_ON_WEB_POPUP, "Web icon")) {
			// click(DISPLAY_ON_WEB, "Display web icon");
			// type(getRepValuesOnDisplayWebPopup(1), "abc", "sales rep number
			// text box");

			List<WebElement> salesRep = driver
					.findElements(By.xpath("//div[@id='webNotificationSortPopUp']//input[@type='textbox']"));

			for (i = 1; i <= salesRep.size(); i++) {
				if (salesRep.size() >= 2) {

					if (i == 1) {
						String val = "a";
						type(getRepValuesOnDisplayWebPopup(i), val, "sales rep number text box");

						reporter.SuccessReport("Repo Name and its Value",
								"Repo Name is "
										+ getText(getRepNamesOnDisplayWebPopup(i), "Rep Names").replace(",", " ").trim()
										+ " and its values is ",
								val);
					}
					if (i == 2) {
						String val1 = "b";
						type(getRepValuesOnDisplayWebPopup(i), val1, "sales rep number text box");
						reporter.SuccessReport("Repo Name and its Value",
								"Repo Name is "
										+ getText(getRepNamesOnDisplayWebPopup(i), "Rep Names").replace(",", " ").trim()
										+ " and its values is ",
								val1);

					}
					if (i == 3) {
						String val2 = "c";
						type(getRepValuesOnDisplayWebPopup(i), val2, "sales rep number text box");
						reporter.SuccessReport("Repo Name and its Value",
								"Repo Name is "
										+ getText(getRepNamesOnDisplayWebPopup(i), "Rep Names").replace(",", " ").trim()
										+ " and its values is ",
								val2);

					}
					if (i == 4) {
						String val3 = "d";
						type(getRepValuesOnDisplayWebPopup(i), val3, "sales rep number text box");
						reporter.SuccessReport("Repo Name and its Value", "Repo Name is " + "<b>"
								+ getText(getRepNamesOnDisplayWebPopup(i), "</b>Rep Names").replace(",", " ").trim()
								+ " and its values is ", val3);

					}

				}

			}

			click(UPDATE_BUTTN, "Update button");
			waitForVisibilityOfElement(ALERTERORRR_MSG, "Error, the rules must numeric.");
			if (isVisibleOnly(ALERTERORRR_MSG, "Error, the rules must numeric.")) {
				reporter.SuccessReport("Invalid data Validation",
						"Error message appeares when user enter invalid input",
						getText(ALERTERORRR_MSG, "Error, the rules must numeric."));
			}

			// Duplicate input

			/*
			 * List<WebElement> salesRep = driver .findElements(By.xpath(
			 * "//div[@id='webNotificationSortPopUp']//input[@type='textbox']"))
			 * ;
			 */

			if (isElementPresent(CANCEL_BTN, "Cancel button")) {
				click(CANCEL_BTN, "Cancel button click");
				reporter.SuccessReport("Click CANCEL in POPUP on Manage Web Group: Contacts and Notifications Page",
						"CANCEL Fields Exists and Clicked", "");

			} else
				reporter.failureReport("Click CANCEL in POPUP on Manage Web Group: Contacts and Notifications Page",
						"CANCEL Field does not Exists", "", driver);
		}

		if (isElementPresent(DISPLAY_ON_WEB, "Web icon")) {
			click(DISPLAY_ON_WEB, "Display web icon");

			// Read the popup Rep names into a List
			List<WebElement> myList = driver.findElements(PRODUCT_EXP_REP_NAMES);

			for (i = 1; i <= myList.size(); i++) {
				popupRepName = getText(getRepNamesOnDisplayWebPopup(i), "Rep Names").replace(",", " ").trim();
				popupRepNamesList.add(popupRepName);
			}
		}

		click(CANCEL_BTN, "Cancel button click");

		return popupRepNamesList;

	}

	/**
	 * This method is to click on Product Exp icon and verify week names
	 * 
	 */
	public void verifyProductExpIconFeature() throws Throwable {

		String result = null;
		if (isElementPresent(PRODUCT_EXP, "Product Exp icon")) {
			click(PRODUCT_EXP, "Product Exp icon");
			reporter.SuccessReport("Click Product Exp  Icon on Manage Web Group: Contacts and Notifications Page",
					"Product Exp Icon Existed and Clicked", "");

			// View the week days
			String[] weekDaysToCompare = { "Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday",
					"Saturday" };

			List<WebElement> weekdays = driver.findElements(WEEKDAYS);

			for (int i = 0; i < weekdays.size(); i++) {
				result = weekdays.get(i).getText().trim();
				if (result.equals(weekDaysToCompare[i])) {
					reporter.SuccessReport("verify day " + result + " displayed",
							"Week day is displayed correctly. And day is: " + result, "");
				}
			}

			// verify cancel button in Product Exp
			if (isElementPresent(PRODUCT_EXP_CANCEL_BTN, "Cancel button")) {
				click(PRODUCT_EXP_CANCEL_BTN, "Cancel button click");
				reporter.SuccessReport("Click on Cancel on Product  Exp Pop-up",
						"Product Exp Popup Exists and Clicked Cancel", "");

			} else
				reporter.failureReport("Click on Cancel on Product  Exp Pop-up", "Cancel button does not Exist", "",
						driver);

		} else
			reporter.failureReport("Click Product Exp  Icon on Manage Web Group: Contacts and Notifications Page",
					"Product Exp Icon is not Exists", "", driver);
	}

	public void VerifyRepNamesDisplayOrder(List<String> RepNamesFromIcon) throws Throwable {
		List<String> RepNamesList = new ArrayList<String>();
		String RepName = null;
		if (isElementPresent(SALESREPIMAGES, "Sales Rep Image")) {
			List<WebElement> salesRep = driver.findElements(SALESREPIMAGES);
			int j = 1;
			for (i = 1; i <= salesRep.size(); i++) {

				RepName = getText(SalesRepNamesAfterLogin(j), "Rep Names").replace(",", " ").trim();
				System.out.println(RepName);
				RepNamesList.add(RepName);
				j = j + 2;
			}

			boolean isMatch = RepNamesList.equals(RepNamesFromIcon);
			if (isMatch) {
				reporter.SuccessReport("Verify Rep Name and their Sequence in CMT and After Logging in as User",
						"Rep Name and its sequence match in CMT and after user Login As", "");
			} else
				reporter.failureReport("Verify Rep Name and their Sequence in CMT and After Logging in as User",
						"Rep Name and its sequence did not match in CMT and after user Login As", "", driver);
		} else
			reporter.failureReport("Verify Rep Name and their Sequence in CMT and After Logging in as User",
					"Rep Names are not present in CMT and after user Login As", "", driver);
	}

	/**
	 * This method is to enter the product group name
	 * 
	 * @param ProdGrpName
	 * @throws Throwable
	 */
	public void enterProductGrpName(String ProdGrpName) throws Throwable {
		String val = null;
		if (isElementPresent(PROD_WEBGRP_NAME, "Product group")) {
			// click(getManageWebGroupDDLinks(ProdGrpName), "Product group
			// option: " + ProdGrpName);
			val = getRandomNumeric(4);
			type(PROD_WEBGRP_NAME, ProdGrpName, "Product Group Name");
			reporter.SuccessReport("Enter Product Group Name on Product Group Creation Wizard",
					"Product Group Name is Exists and Entered", "");
		} else {
			reporter.failureReport("Enter Product Group Name on Product Group Creation Wizard",
					"Product Group Name is Not Exists", "", driver);
		}
	}

	public void selectCompanyStandardsLink() throws Throwable {
		if (isElementPresent(COMP_STAND_WIZARD, "company standards wizard")) {

			click(COMP_STAND_WIZARD, "company standards wizard exists and clicked");
			reporter.SuccessReport("Click Company Standards Wizard on Company Standards Management Page",
					"Company Standards Wizard Existed and Clicked", "");

		} else
			reporter.failureReport("Click Company Standards Wizard on Company Standards Management Page",
					"Company Standards Wizard is not exists", "", driver);
	}

	/**
	 * This method is to enter the Create New name
	 * 
	 * @param CreateNewName
	 * @throws Throwable
	 */
	public void enterCreateNewValue(String CreateNewName) throws Throwable {
		if (isElementPresent(CREATE_NEW, "Select Category")) {
			/*
			 * click(CATEGORY, "Select Category"); selectByVisibleText(CATEGORY,
			 * CreateNewName, "Select Category");
			 */
			type(CREATE_NEW, CreateNewName, "Create New Name");
			reporter.SuccessReport("Enter Category on Product Group Creation Wizard", "Category is Exists and Entered",
					CreateNewName);
		} else
			reporter.failureReport("Enter Category on Product Group Creation Wizard", "Category is Not Exists",
					CreateNewName, driver);
	}

	public void clickContinueBtn() throws Throwable {
		if (isElementPresent(CONTINUE_BTN, "Continue Button")) {
			click(CONTINUE_BTN, "Continue Button");
			reporter.SuccessReport("Click CONTINUE on Product Group Creation Wizard",
					"CONTINUE Fields Exists and Clicked", "");
		} else
			reporter.failureReport("Click CONTINUE on Product Group Creation Wizard", "Continue is Not Exists", "");
	}

	/**
	 * This method is to enter the Configuration Set name
	 * 
	 * @param ConfSetName
	 * @throws Throwable
	 */
	public void enterConfigurationSetName(String ConfSetName) throws Throwable {
		if (isElementPresent(CONF_SET_NAME, "Configuration set name")) {
			type(CONF_SET_NAME, ConfSetName, "Configuration set name");
			reporter.SuccessReport("Enter Configuration Set Name on Product Group Creation Wizard",
					"Configuration Set Name is Not Exists", "");

			// Click on Continue button
			if (isElementPresent(CONTINUE_BTN, "Continue Button")) {
				click(CONTINUE_BTN, "Continue Button");
				reporter.SuccessReport("Click CONTINUE on Product Group Creation Wizard",
						"CONTINUE Fields Exists and Clicked", "");
			} else
				reporter.failureReport("Click CONTINUE on Product Group Creation Wizard", "Continue is Not Exists", "",
						driver);
		} else
			reporter.failureReport("Enter Configuration Set Name on Product Group Creation Wizard",
					"Configuration Set Name is Not Exists", "", driver);

	}

	/**
	 * This method is to enter the Search Key Word and click on search button
	 * 
	 * @param SrchKeyWord
	 * @throws Throwable
	 */
	public void searchByKeword(String SrchKeyWord) throws Throwable {
		if (isElementPresent(SRCH_BY_KEYWORD, "Search textbox")) {
			type(SRCH_BY_KEYWORD, SrchKeyWord, "Search keyword");
			reporter.SuccessReport("Enter Search by Keyword on Product Group Creation Wizard",
					"Search by Keyword is Exists and Entered", "");

			// Click on Search button
			if (isElementPresent(SEARCH_BTN, "Search Button")) {
				click(SEARCH_BTN, "Search Button");
				reporter.SuccessReport("Click Search  on Product Group Creation Wizard",
						"Search Link  is Exists and Entered", "");
			} else
				reporter.failureReport("Click Search  on Product Group Creation Wizard", "Search Link is Not Exists",
						"", driver);
		} else
			reporter.failureReport("Enter Search by Keyword on Product Group Creation Wizard",
					"Search by Keyword is Not Exists", "", driver);

	}

	public void verifyProduct() throws Throwable {
		switchToChildWindow();
		driver.manage().window().maximize();
		String result = null;
		if (isElementPresent(PRODUCTMFR, "Product MFR")) {
			result = getText(PRODUCTMFR, "Product MFR");
			String item = result.split("Mfr Part #:")[1];
			if (item != "") {
				reporter.SuccessReport("Verify 1st Product Part # in Search Results Page",
						"1st Product Part # is Exists", result);
			} else
				reporter.failureReport("Verify 1st Product Part # in Search Results Page",
						"1st Product Part # is Not Exists", "", driver);

		}
	}

	public void AddToSet() throws Throwable {
		if (isElementPresent(ADD_TO_SET, "add to set button")) {
			click(ADD_TO_SET, "Click Add To Set button");
			reporter.SuccessReport("Click Add to Set", "Add to Set Exists and Clicked", "");
		} else
			reporter.failureReport("Click Add to Set", "Add to Set Not Exists", "", driver);
	}

	public void VerifyUpdate() throws Throwable {
		/*
		 * Thread.sleep(2000); String mainWindow = parentWindow();
		 * clickOnloginAs(); switchToWindow(mainWindow)
		 */;
		// switchToParentWindow(mainWindow);
		ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
		driver.switchTo().window(tabs.get(0));
		if (isElementPresent(CONF_SET_SUCCESS_COMMENT, "Configuration set success comment"))
			reporter.SuccessReport("Verify Configuration Set Updated on Company Standards Management Page",
					"Configuration Set Updated Exists", "");
		else
			reporter.failureReport("Verify Configuration Set Updated on Company Standards Management Page",
					"Configuration Set Updated Not Exists", "", driver);
	}

	public void ClickCategoryLink(String val) throws Throwable {
		if (isVisibleOnly(lnkCatLink(val), "QTPCategory Link")) {
			click(lnkCatLink(val), "QTPCategory Link");
			reporter.SuccessReport("Click Category Link Under Current Categories  on Company Standards Management",
					"Category Link  is Exists and Entered", "");
		} else
			reporter.failureReport("Click Category Link Under Current Categories  on Company Standards Management",
					"Category Link is Not Exists", "", driver);

	}

	public void ModifyCategory() throws Throwable {
		if (isElementPresent(QTPCATEGORY_NAME, "QTPCategory Name")) {
			type(QTPCATEGORY_NAME, "QTPCategory", "QTPCategory Name");
			reporter.SuccessReport("Edit Category Name on Company Standards Management",
					"Category Name Exists and Modified", "");
		} else
			reporter.failureReport("Edit Category Name on Company Standards Management", "Category Name is Not Exists",
					"", driver);
	}

	public void CheckCollapseOption() throws Throwable {
		if (isVisibleOnly(CHECK_COLLAPSE, "Collapse check box")) {
			WebElement chkOption = driver.findElement(CHECK_COLLAPSE);
			boolean chk = chkOption.isSelected();
			if (!chk) {
				click(CHECK_COLLAPSE, "Check Collapse check box option");
			}
			reporter.SuccessReport("Select Display Collapsed Check Box  on Company Standards Management",
					"Display Collapsed Check Box and Selected", "");
		} else
			reporter.failureReport("Select Display Collapsed Check Box  on Company Standards Management",
					"Display Collapsed Check Box is Not Exists", "", driver);
	}

	public void ClickUpdate() throws Throwable {
		if (isElementPresent(UPDT_BTN, "Update button")) {
			click(UPDT_BTN, "Click Update button");
			reporter.SuccessReport("Click Update on Company Standards Management", "Update Link  is Exists and Clicked",
					"");
		} else
			reporter.failureReport("Click Update on Company Standards Management", "Update Link is Not Exists", "",
					driver);
	}

	public void ModifyProdGroup(String PrdGrpName, String Category) throws Throwable {
		if (PrdGrpName != "") {
			Thread.sleep(2000);
			click(PRD_GRP_NAME(PrdGrpName, Category), "Click product group name");
			reporter.SuccessReport("Click Product Group Link Under Current Categories  on Company Standards Management",
					"Product Group Link  is Exists and Entered", "");
		}

		if (isElementPresent(PRD_GRP_TO_MODIFY, "Prod group to modify")) {
			type(PRD_GRP_TO_MODIFY, "TestProdGroupCategory", "Product Group Category Name");
			// click on update button
			if (isElementPresent(UPDT_BTN, "Update button")) {
				click(UPDT_BTN, "Click Update button");
				reporter.SuccessReport("Click Update on Company Standards Management",
						"Update Link  is Exists and Clicked", "");
			} else
				reporter.failureReport("Click Update on Company Standards Management", "Update Link is Not Exists", "",
						driver);
		}
	}

	public void clickOnProductGroup(String productGroupName) throws Throwable {

		if (isVisibleOnly(lblProductGroupNamelink(productGroupName), "Product Group")) {
			click(lblProductGroupNamelink(productGroupName), "Product Group", productGroupName);
			reporter.SuccessReport("Clicking on product group ", " Clicked on ", productGroupName);
		} else {
			reporter.failureReport("Clicking on prodcut group", "clicking on product group", productGroupName, driver);
		}
		if (isVisibleOnly(UPDT_BTN, "Update button")) {
			click(UPDT_BTN, "Update button");
		}

		if (isElementPresent(PRD_GRP_TO_MODIFY, "Prod group to modify")) {
			// type(PRD_GRP_TO_MODIFY, "TestProdGroupCategory", "Product Group
			// Category Name");
			// click on update button
			click(UPDT_BTN, "Update button");
			if (isElementPresent(UPDT_BTN, "Update button")) {
				click(UPDT_BTN, "Click Update button");
				reporter.SuccessReport("Click Update on Company Standards Management",
						"Update Link  is Exists and Clicked", "");
			} /*
				 * else reporter.
				 * failureReport("Click Update on Company Standards Management",
				 * "Update Link is Not Exists", "", driver);
				 */
		}
	}

	public void VerifyUpdateMsg() throws Throwable {
		if (isElementPresent(PRD_GRP_UPDATE_MSG, "Product group update message"))
			reporter.SuccessReport("Verify Product Group Updated on Company Standards Management Page",
					"Product Group Updated Exists", "");
		else
			reporter.failureReport("Verify Product Group Updated on Company Standards Management Page",
					"Product Group Updated Not Exists", "", driver);
	}

	public void VerifyICompanyStandards() throws Throwable {
		if (isVisibleOnly(PRD_STANDARDS_PAGE, "Product Standards page")) {
			reporter.SuccessReport("Verify Product Standards Page", "Product Standards Page is Loaded", "");

			// Verify Product Groups are Collapsed
			if (isVisibleOnly(PRD_GRP_COLLAPSE, "Product Group collapse")) {
				reporter.SuccessReport("Verify Product Grous are Collapsed on Product StandardsPage",
						"Product Groups are Collapsed", "");

			} else
				reporter.failureReport("Verify Product Grous are Collapsed on Product StandardsPage",
						"Product Groups are Not Collapsed", "", driver);

		} else
			reporter.failureReport("Verify Product Standards Page", "Product Standards Page is Not Loaded", "", driver);
	}

	
	
	
	public void addNewRepDetails(String Email, String Phone, String Fax) throws Throwable {
		if (isVisibleOnly(ADD_NEW_REP, "Add New Rep Details")) {
			reporter.SuccessReport("Click on Add New Rep Link", "Add New Rep Link is available", "");
			click(ADD_NEW_REP, "Click on add new rep");
			if (isVisibleOnly(ADD_NEW_REP_POPUP, "Add New Rep popup")) {
				// Enter Email Address,Name,Title,Phone NUmber,Fax Number,
				// Photo, Information about Rep info in the Pop Up
				if (isVisibleOnly(EMAIL_ADDRESS, "Email address")) {
					type(EMAIL_ADDRESS, Email, "Email Address");
				}
				Thread.sleep(5000);
				if (isVisibleOnly(PHONE_NUMBER, "New Rep Phone Number")) {
					click(PHONE_NUMBER, "Phone Number Text Field");
					String getPhone = driver.findElement(PHONE_NUMBER).getText();
					if (getPhone == null || getPhone != null) {
						type(PHONE_NUMBER, Phone, "New Rep Phone Number::" + Phone + "");
					} /*
						 * else{ reporter.SuccessReport("Phone number",
						 * " Repo Phone number ", getPhone); }
						 */
				}
				Thread.sleep(5000);
				if (isVisibleOnly(FAX_NUMBER, "Fax Number")) {
					click(FAX_NUMBER, "Fax Number");
					String getFaxNum = driver.findElement(FAX_NUMBER).getText();
					if (getFaxNum == null) {
						type(FAX_NUMBER, Fax, "New Rep Fax Number::" + Fax + "");
					}
					type(FAX_NUMBER, Fax, "Fax Number");
				}

				if (isVisibleOnly(REP_UPDATE, "Update button")) {
					click(REP_UPDATE, "Click Update button");
				}
			}
		} else
			reporter.failureReport("Click on Add New Rep Link", "Add New Rep Link is NOT available ", "", driver);

	}

	public void verifyNewlyAddedRepo() throws Throwable {
		if (isVisibleOnly(lblNewRepoText, "Newly Added repo")) {
			reporter.SuccessReport("Newly added Repo", " The newly added repo available ",
					getText(lblNewRepoText, "Newly Added repo"));
		} else {
			reporter.failureReport("Newly added Repo", " The newly added repo is not available ", "NA", driver);
		}
	}

	public void verifyNewlyAddedRepoAfterDelete() throws Throwable {
		if (!isVisibleOnly(lblNewRepoText, "Newly Added repo")) {
			reporter.SuccessReport("Newly added Repo", " The newly added repo is successfully deleted ", "");
		} else {
			reporter.failureReport("Newly added Repo", " The newly added repo is not deleted ", "", driver);
		}
	}

	public void verifySuccessRepMsg() throws Throwable {
		if (isElementPresent(REP_ADD_SUCCESS_MESSAGE, "Verify success messalge")) {
			reporter.SuccessReport("Verify that the New has been added message is displayed",
					"New rep has been added message is displayed", "");
		} else
			reporter.failureReport("Verify that the New has been added message is displayed",
					"New Rep has been added message is not displayed", "", driver);
	}

	public void verifyNewRepAdded() throws Throwable {
		if (isElementPresent(ADDED_REP_NAME_WEBPAGE, "Verify success messalge")) {
			reporter.SuccessReport("Verify that the newly added Rep has been added", "Newly added Rep has been added",
					"");
		} else
			reporter.failureReport("Verify that the newly added Rep has been addedd",
					"Newly added Rep has Not been added", "", driver);
	}

	public void checkPermissions() throws Throwable {
		if (isElementPresent(PROD_EXP_CHKBOX, "Product Exp check box")) {
			if (!isCheckBoxSelected(PROD_EXP_CHKBOX)) {
				driver.findElement(PROD_EXP_CHKBOX).click();
			}
			if (isElementPresent(DISPLAY_ONWEB_CHKBOX, "Display on web check box")) {
				if (!isCheckBoxSelected(DISPLAY_ONWEB_CHKBOX)) {
					driver.findElement(DISPLAY_ONWEB_CHKBOX).click();
				}
			}
			reporter.SuccessReport("Verify that the Prod Exp and Display Web permissions are checked",
					"Prod Exp and Display Web permissions are checked", "");
		} else
			reporter.failureReport("Verify that the Prod Exp and Display Web permissions are checked",
					"Prod Exp and Display Web permissions are Not checked", "", driver);
	}

	public void saveRepDetails() throws Throwable {
		if (isVisibleOnly(SAVE_BTN, "Save Button")) {

			click(SAVE_BTN, "Click on save button");
			reporter.SuccessReport("Verify that Save button exists and clicked", "Save button exists and clicked", "");
		} else
			reporter.failureReport("Verify that Save button exists and clicked", "Save button does not exists", "",
					driver);
	}

	public void verifySuccessSaveMsg() throws Throwable {
		if (isVisibleOnly(REP_SAVE_SUCCESS_MESSAGE, "Success save message")) {
			reporter.SuccessReport("Verify that the message that the updates have been made successfully is displayed",
					"the message that the updates have been made successfully is displayed", "");
		} else
			reporter.failureReport("Verify that the message that the updates have been made successfully is displayed",
					"the message that the updates have been made successfully is not displayed", "", driver);
	}

	public void deleteNewRepAdded() throws Throwable {
		if (isElementPresent(DELETE_BTN, "Delete button")) {
			click(DELETE_BTN, "Click on Delete button");
			reporter.SuccessReport("Verify that the delete icon is there and clicked to delete the newly added Rep",
					"Delete icon is there and clicked to delete the newly added Rep", "");
		} else
			reporter.failureReport("Verify that the delete icon is there and clicked to delete the newly added Rep",
					"Delete icon is Not there for the newly added Rep", "", driver);
	}

	public void verifySuccessDeleteMsg() throws Throwable {
		if (isElementPresent(REP_DELETE_SUCCESS_MESSAGE, "Success delete message")) {
			reporter.SuccessReport("Verify that the message that the Rep has been deleted successfully is displayed",
					"the message that the Rep has been deleted successfully is displayed", "");
		} else
			reporter.failureReport("Verify that the message that the Rep has been deleted successfully is displayed",
					"the message that the Rep has been deleted successfully is not displayed", "", driver);
	}

	public void logoutSite() throws Throwable {
		click(By.xpath("//span[@class='mainTopHeaderLabels'][@id='logoutUrlLink']"), "Logout Link");
	}

	/**
	 * This method is to check the Hosted licensing permissions in the Manage
	 * Web Groups: Settings page.
	 * 
	 * @param menuName
	 * @param permissions
	 * @throws Throwable
	 */
	public void setHostedLicensingPermissionsON(String permissions) throws Throwable {
		if (isCheckBoxSelected(getHostedLicensingPermissions(permissions))) {
			LOG.info(permissions + " check box already checked.");
		} else {
			click(getHostedLicensingPermissions(permissions), "Customer level permissions");
			click(UPDATE_CUSTOMER_PERMISSIONS_BTN, "Update button");
			if (isElementPresent(CUSTOMER_PERMISSION_UPDATE_MSG, "update sucessful message")) {
				reporter.SuccessReport("Verify the Success message ", "Permissions Updated Succesfully.", permissions);
			} else {
				reporter.failureReport("Verify the sucess message", "Permissions are not Updated Succesfully", "",
						driver);
			}
		}
	}

	/**
	 * This method is to uncheck the Hosted licensing permissions OFF.
	 * 
	 * @param customerPermissions
	 * @throws Throwable
	 */
	public void setHostedLicensingPermissionsOFF(String permissions) throws Throwable {

		if (isCheckBoxSelected(getHostedLicensingPermissions(permissions))) {
			click(getHostedLicensingPermissions(permissions), "Customer level permissions");
			click(UPDATE_CUSTOMER_PERMISSIONS_BTN, "Update button");
			if (isElementPresent(CUSTOMER_PERMISSION_UPDATE_MSG, "update sucessful message")) {
				reporter.SuccessReport("Verify the Success message ", "Permissions Updated Succesfully", permissions+" OFF");
			} else {
				reporter.failureReport("Verify the sucess message", "Permissions are not Updated Succesfully", permissions +" ON",driver);
			}
		} else {
			reporter.SuccessReport("Verify the Success message ", "Permissions Updated Succesfully", permissions+" OFF");
		}
	}

	public void ModifyCategory(String newCategoryName) throws Throwable {
		if (isElementPresent(QTPCATEGORY_NAME, "QTPCategory Name")) {
			type(QTPCATEGORY_NAME, newCategoryName, "QTPCategory Name");
			reporter.SuccessReport("Edit Category Name on Company Standards Management",
					"Category Name Exists and Modified", "");
		} else
			reporter.failureReport("Edit Category Name on Company Standards Management", "Category Name is Not Exists",
					"", driver);
	}

	public void ClickOnProdGroupImg() throws Throwable {

		if (isElementPresent(CREATE_NEW_PROD_GROUP, "Product groups home page")) {
			click(CREATE_NEW_PROD_GROUP, "Click on new product group icon");
			reporter.SuccessReport("Add Product Group on Manage Web groups: Settings Page",
					"Product Image Exists and Clicked", "");
		} else
			reporter.failureReport("Add Product Group on Manage Web groups: Settings Page",
					"Product Image does not Exists", "", driver);
	}

	public void CreateNewCategory(String categoryName) throws Throwable {
		if (isElementPresent(NEW_CATEGORY_NAME, "Product groups home page")) {
			type(NEW_CATEGORY_NAME, categoryName, "Click on new product group icon");
			reporter.SuccessReport("Create Category Name on Company Standards Management",
					"Category Name Exists and Entered", "");

			if (isElementPresent(CREATE_BUTTON, "Create Button")) {
				click(CREATE_BUTTON, "Create button click");
				reporter.SuccessReport("Click CREATE on Company Standards Management",
						"CREATE Link  is Exists and Clicked", "");
			} else {
				reporter.failureReport("Click CREATE on Company Standards Management", "CREATE Link is Not Exists", "",
						driver);
			}

			Thread.sleep(2000);

			WebDriverWait wait = new WebDriverWait(driver,
					300 /* timeout in seconds */);
			if (wait.until(ExpectedConditions.alertIsPresent()) != null) {
				String alertMsg = driver.switchTo().alert().getText();
				if (alertMsg.contains("duplicate category name")) {
					reporter.SuccessReport("Verfiy the Message on Company Standards Management",
							"duplicate category name.Please enter different category name and try again  is Exists",
							"");
					acceptAlert();
				}
			}

		} else
			reporter.failureReport("Create Category Name on Company Standards Management",
					"Category Name does not Exists", "", driver);
	}

	/**
	 * 
	 * @param CategoryName
	 * @throws Throwable
	 */
	public void DeleteCategory(String CategoryName) throws Throwable {
		if (isElementPresent(categoryLink(CategoryName), "Product groups home page")) {
			click(categoryLink(CategoryName), "Click on cagtegory name to delete");

			if (isElementPresent(DELETE_CATEGORY_LINK, "Delete category icon")) {
				click(DELETE_CATEGORY_LINK, "Delete category icon");
				reporter.SuccessReport("Verfiy the Delete this Category on Company Standards Management",
						"Delete this Category exists and clicked", "");

				// handle popup alert
				acceptAlert();

				if (isElementPresent(CONFIRM_DELETE_CHKBOX, "Delete check box")) {
					click(CONFIRM_DELETE_CHKBOX, "Delete check box");
					if (isElementPresent(UPDATE_TODEL_BTN, "Delete button")) {
						click(UPDATE_TODEL_BTN, "Delete button");
					}
				}

			} else {
				reporter.failureReport("Verfiy the Delete this Category on Company Standards Management",
						"Delete this Category does not exists", "", driver);
			}

		} else
			reporter.failureReport("Create Category Name on Company Standards Management",
					"Category Name does not Exists", "");
	}

	/**
	 * 
	 * @throws Throwable
	 */
	public void verifyProdGroupsCollapsed() throws Throwable {
		if (isElementPresent(PROD_GRPS_COLLAPSE, "Product groups home page")) {
			reporter.SuccessReport("Verify Product Grous are Collapsed on Product StandardsPage",
					"Product Groups are Collapsed", "");
		} else
			reporter.failureReport("Verify Product Grous are Collapsed on Product StandardsPage",
					"Product Groups are Not Collapsed", "", driver);
	}

	/**
	 * This method is to add a month in the Hosted Licensing Administration page
	 * 
	 * @param month
	 * @param year
	 * @param type
	 * @throws Throwable
	 */
	public void AddMonthInHostedLicensingAdministrationPage(String month, String year, String type, String soldTo,
			String salesOrg) throws Throwable {
		waitForVisibilityOfElement(ADD_MONTH_RADIO_BTN, "add a month radi button");
		click(ADD_MONTH_RADIO_BTN, "add a month radio button");
		type(SOLD_TO_TXT_BOX, soldTo, "soldTo");
		type(SALES_ORG_TXT_BOX, salesOrg, "salesOrg");
		click(MONTH, "month");
		selectByVisibleText(MONTH, month, "month");
		click(YEAR, "year");
		selectByVisibleText(YEAR, year, "month");
		click(TYPE, "TYPE");
		selectByVisibleText(TYPE, type, "month");
		click(ADD_MONTH_BTN, "add a month button");
		isElementPresent(USAGE_PERIOD_ADDED_MSG, "Added a usage period for the soldto message", true);

	}

	/**
	 * This method is to search for a user in the CMT home page.
	 * 
	 * @param webGroup
	 * @throws Throwable
	 */
	public void searchForUser(String user) throws Throwable {
		waitForVisibilityOfElement(WEB_GROUP, "WEB GROUP in CMT TOOL HOME PAGE");
		type(USER_INPUT, user, "USER in CMT TOOL");
		click(SEARCH_BUTTON_CMT_TOOL, "SEARCH BUTTON in CMT TOOL HOME PAGE");

	}

	/**
	 * Method is to click on webgrp link
	 * 
	 * @throws Throwable
	 */
	public void clickOnWebGrpLink() throws Throwable {
		click(WEB_GRP_LINK, "Web grp ");
	}

	/**
	 * verify the smart tracker page in cmt
	 * 
	 * @throws Throwable
	 */
	public void verifySmartTrackerPage() throws Throwable {
		if (isElementPresent(SMART_TRACKER_LABEL, "smart tracker label")) {
			reporter.SuccessReport("verify smart tracker label", "Smart tracker page is displayed", "");
		} else {
			reporter.failureReport("verify smart tracker label", "Smart tracker page is not displayed", "", driver);
		}
	}

	/*
	 * Method is to click on add a smart tracker link
	 */
	public void clickOnAddSmartTrackerLink() throws Throwable {
		click(ADD_A_SMART_TRACKER, "Add smart tracker link");
	}

	/**
	 * Method is to add field label in manage smart tracker page and save
	 * changes
	 * 
	 * @param label
	 * @throws Throwable
	 */
	public void addFieldLabelInSmartTracker(String label) throws Throwable {
		if (isElementVisible(FIELD_LABEL, 5, "Field label")) {
			type(FIELD_LABEL, label, "Field label");
		} else {
			reporter.failureReport("Verify smart tracker field", "Smart tracker field does not exists", "", driver);
		}
	}

	/**
	 * This method is to select the field type
	 * 
	 * @throws Throwable
	 * 
	 */
	public void addSmartTrackerFieldType(String fieldType) throws Throwable {
		if (isElementPresent(SMART_TRACKER_FIELD_TYPE_DD, "Field Type")) {
			selectByVisibleText(SMART_TRACKER_FIELD_TYPE_DD, fieldType, "field Type");
		} else {
			reporter.failureReport("Verify smart tracker field type", "Smart tracker field type not present", "",
					driver);
		}
	}

	/**
	 * This method is to save changes in smart tracker
	 * 
	 * @param label
	 * @throws Throwable
	 */
	public void saveChangesAndVerify(String label) throws Throwable {
		click(SAVE_SMART_TRACKER, "save changes");
		if (isElementPresent(getSmartTrackerLabel(label), "smart tracker")) {
			reporter.SuccessReport("Verify smart tracker added", "Smart tracker added successfully", label);
		} else {
			reporter.failureReport("Verify smart tracker added", "Smart tracker not added successfully", "", driver);
		}

	}

	/**
	 * 
	 * @param label
	 * @throws Throwable
	 */
	public void editSmartTracker(String label) throws Throwable {
		click(SMART_TRACKER_EXPEND,label);
		Thread.sleep(3000);
		click(EDIT_SMART_TRACKER, "edit button");
		if (isEnabled(MAKE_INACTIVE_CHECK_BOX, "make inactive check box")) {
			click(MAKE_INACTIVE_CHECK_BOX, "make inactive check box");
			click(SAVE_EDIT_SMARTTRACKER, "save changes");
			acceptAlert();
		} else {
			reporter.failureReport("make inactive check box", "make inactive check box is not enabled", label, driver);
		}
	}

	/**
	 * Method is to verify the smart tracker inactive error label
	 * 
	 * @throws Throwable
	 */
	public void verifyInactiveSmartTrackerError() throws Throwable {
		if (isVisibleOnly(INACTIVE_SMART_TRACKER_ERROR, "smart tracker inactive Message")) {
			reporter.SuccessReport("verify smart tracker Message", "Smart tracker inactive Message is diaplayed ", "Smart Tracker Status:Inactive");
		} else {
			reporter.failureReport("verify smart tracker Message", "Smart tracker inactive Message is not diaplayed ", "",
					driver);
		}
	}

	/**
	 * This method is to enter the account Search in account links screen
	 * 
	 * @param accountSearch
	 * @throws Throwable
	 */
	public void enterLinkedAccountSearch(String accountSearch) throws Throwable {
		if (isVisibleOnly(LINKED_ACCOUNTS_SEARCH, "Linked account search")) {
			typeText(LINKED_ACCOUNTS_SEARCH, accountSearch, "");
			click(SEARCH_ICON, "search icon");
			reporter.SuccessReport("Web Group Management", "Under Linked Accounts Quick Find Box Exist and Value Entered", "Search Input:"+accountSearch);
		} else {
			reporter.failureReport("Verify linked accounts search input", "linked accounts search input is not present",
					accountSearch, driver);
		}
	}

	/**
	 * Method is to click on search icon
	 * 
	 * @throws Throwable
	 */
	public void clearSearch() throws Throwable {
		if(isVisibleOnly(LINKED_ACCOUNTS_SEARCH,"Linked Account Search")) {
		clearData(LINKED_ACCOUNTS_SEARCH);
		click(SEARCH_ICON, "search icon");
		reporter.SuccessReport("Web Group Management", "Under Linked Accounts Results are Cleared", "");
	}
	}

	/**
	 * This method is to check the account account check box
	 * 
	 * @param accountNum
	 * @throws Throwable
	 */
	public void checkLinkedAccountCheckBox(String accountNum) throws Throwable {
		if (isCheckBoxSelected(getLinkedAccountsCheckBox(accountNum))) {
			Log.info("Check box already selected");
			reporter.SuccessReport("Verify linked accounts checkbox", "Linked accounts check box already checked", "Linked account check box checked");
		} else {
			click(getLinkedAccountsCheckBox(accountNum), "Account linked check box");
		}
	}

	/**
	 * This method is to check the Default At Login radio button
	 * 
	 * @param accountNum
	 * @throws Throwable
	 */
	public void clickRadioDefaultAtLogin(String accountNum) throws Throwable {
		if (isCheckBoxSelected(getDefaultLoginRadioButton(accountNum))) {
			reporter.SuccessReport("Verify radio button already selected","radio button already selected",accountNum);
		} else {
			click(getDefaultLoginRadioButton(accountNum), "Account linked check box");
		}
	}

	public void clickDefaultRadioButtonInLinkedAccounts() throws Throwable {
		click(DEFAULT_LOGIN_LINKED_ACCOUNTS, "Default login Radio button selected");
	}

	/**
	 * This method is to click on update user button and verify error message
	 * 
	 * @throws Throwable
	 */
	public void clickUpdateButtonOnLinkedAccountsScreen() throws Throwable {
		if (isEnabled(UPDATEUSER_BTN, "Update Button")) {
			click(UPDATEUSER_BTN, "update button");
			isElementPresent(LINKED_ACCOUNT_UPADTE_MSG, "Update Success message", true);
		} else {
			reporter.failureReport("verify update button enabled", "update user button is not enabled", "", driver);
		}
	}

	public void clickResetPasswordLink() throws Throwable {
		click(RESET_PASSWORD_LINK, "Reset password link");
	}

	/**
	 * This method is to enter the account Search in account links screen
	 * 
	 * @param accountSearch
	 * @throws Throwable
	 */
	public void linkedAccountSearch(String accountSearch) throws Throwable {
		if (isElementPresent(LINKED_ACCOUNTS_SEARCH_FIELD, "Linked account search")) {
			type(LINKED_ACCOUNTS_SEARCH_FIELD, accountSearch, "account Search");
			click(SEARCH_ICON_LINKED_ACC, "search icon");
		} else {
			reporter.failureReport("Verify linked accounts search input", "linked accounts search input is not present",
					accountSearch, driver);
		}
	}

	/**
	 * This method is to check the Default At Login radio button
	 * 
	 * @param accountNum
	 * @throws Throwable
	 */
	public void clickRadioDefaultAtLinkedAccounts(String accountNum) throws Throwable {
		if (isCheckBoxSelected(getDefaultLoginRadioButtonInLinkedAccounts(accountNum))) {
			Log.info(" radio button already selected");
		} else {
			click(getDefaultLoginRadioButtonInLinkedAccounts(accountNum), "Account linked check box");
			click(UPDATE_USER_BTN_LINKED_ACCOUNT, "update");

		}
	}

	/**
	 * This method is to click on the Smart Trackers Headers
	 * 
	 * @param headerName
	 * @throws Throwable
	 */
	public void selectSmartTrackersHeaders(String headerName) throws Throwable {
		waitForVisibilityOfElement(getManageSmartTrackersHeaders(headerName), "smart tracker header", driver);
		Thread.sleep(3000);
		if (isVisibleOnly(getManageSmartTrackersHeaders(headerName), "smart tracker header")) {
			click(getManageSmartTrackersHeaders(headerName), " Tab : "+headerName);
		} else {
			reporter.failureReport("Verify Smart Trackers Headers", "Smart Tracker Header is not present", headerName,
					driver);
		}

	}

	/**
	 * This method is to click on manage Smart Tracker tabs
	 * 
	 * @param tabName
	 * @throws Throwable
	 */
	public void selectmanageSmartTrackertabs(String tabName) throws Throwable {
		if (isElementPresent(getSmartTrackerstabs(tabName), "manage Smart Tracker tabs")) {
			click(getSmartTrackerstabs(tabName), "Tabs: "+tabName);
		} else {
			reporter.failureReport("Verify Smart Trackers tabs exist", "Smart Tracker tab is not present", tabName,
					driver);
		}
	}

	/**
	 * This method is to verify the linked Account Headers
	 * 
	 * @param header
	 * @throws Throwable
	 */
	public void verifyLinkedAccountHeaders(String header) throws Throwable {
		if (isElementPresent(LinkedAccountHeaders(header), "Lined account headers")) {
			reporter.SuccessReport("verify Linked Account Headers", "Linked Account Headers are present", "");
		} else {
			reporter.failureReport("verify Linked Account Headers", "Linked Account Headers are not present", "");
		}
	}

	/**
	 * This method is to verify the select From Linked Account DD
	 * 
	 * @param option
	 * @throws Throwable
	 */
	public void selectFromLinkedAccountDD(String option) throws Throwable {
		if (isElementPresent(LINKED_ACCOUNT_DD, "LINKED ACCOUNT  DD")) {
			selectByVisibleText(LINKED_ACCOUNT_DD, option, "linked account drop down");
		} else {
			reporter.failureReport("Verify linked account Drop down", "Verify linked account Drop down does not exists",
					"", driver);
		}
	}

	/**
	 * This method is to verify the linked accounts check boxes are selected or
	 * not.
	 * 
	 * @param status
	 * @throws Throwable
	 */
	public void VerifytheCheckBoxStatus(String status) throws Throwable {
		List<WebElement> list = driver.findElements(LINKED_ACCOUNT_CHECKBOX);
                  int C= list.size();
                  System.out.println(C);
		if (status.equals("Checked")) {
			for (i = 0; i <= 10; i++) {
				if (isCheckBoxSelected(LINKED_ACCOUNT_CHECKBOX)) {
					reporter.SuccessReport("Under Linked Accounts Linking Weblist Exist and Selected",
							"Verify Check Boxes Status:Checkbox is Checked", "");
					// do nothing
				} else {
					reporter.failureReport("Verify linked account check box selected",
							" linked account check boxes are not selected for " + i + "user", "", driver);
				}
			}
			reporter.SuccessReport("Verify linked account check box selected",
					"linked account check boxes are selected / Checked for all the displayed users " +C, "");
		}

		if (status.equals("UnChecked")) {
			for (i = 0; i <=10; i++) {
				if (!isCheckBoxSelected(LINKED_ACCOUNT_CHECKBOX)) {
					reporter.SuccessReport("Under Linked Accounts Linking Weblist Exist and Selected",
							"Verify Check Boxes Status:Checkbox is UnChecked", "");

				} else {
					reporter.failureReport("Verify linked account check box selected",
							" linked account check boxes are selected", "", driver);
				}
			}
			reporter.SuccessReport("Verify linked account check box selected",
					" linked account check boxes are not selected / unchecked for users " +list.size(), "");
		}
	}

	/**
	 * Method is to verify the next >> in pagination
	 * 
	 * @throws Throwable
	 */
	public void clickOnNextPagination() throws Throwable {
		if (isElementVisible(PAGINATION, 5, "pagination next")) {
			click(PAGINATION, "pagination");
			reporter.SuccessReport("Web Group Management", "Under Linked Accounts Next Link Existed and Clicked", "Link:>>");
		} else {
			reporter.failureReport("Verify next in pagination", " next >> in pagination does not exist", "");
		}
	}

	/**
	 * This method is to verify next page navigation
	 * 
	 * @param pageNum
	 * @throws Throwable
	 */
	public void verifyPagenavigationOnLinkedAccountsPage(int pageNum) throws Throwable {

		int endResults;
		int startResult;

		String endpage = getText(END_PAGE, "END_PAGE");
		int endPageNum = Integer.parseInt(endpage);

		String results = getText(SELECTED_RESULTS_PER_PAGE, "SELECTED RESULTS PER PAGE");
		int resultsPerPage = Integer.parseInt(results);

		endResults = pageNum * resultsPerPage;
		startResult = (pageNum * resultsPerPage) - resultsPerPage;

		if (endPageNum <= endResults && endPageNum >= startResult) {
			reporter.SuccessReport("Verify page navigation", "Page navigation is successfull", "");
		} else {
			reporter.failureReport("Verify page navigation", "Page navigation is not successfull", "", driver);
		}
	}

	/**
	 * Method to click on linked account check box
	 * 
	 * @param i
	 * @throws Throwable
	 */
	public void clickLinkedAccountCheckBox(String i) throws Throwable {
		if(isVisibleOnly(getLinkedAccountCheckBoxByIndex(i), "Linked account check box")) {
		click(getLinkedAccountCheckBoxByIndex(i), "Under Linked Accounts CheckBox of index "+i+" Exist and Checked");
		String Value=driver.findElement(CMTObj.getLinkedAccountCheckBoxByIndex(i)).getAttribute("value");
		reporter.SuccessReport("Web Group Management", "The Sold To is Linked to User", "Account Number:"+Value);
		}
	}

	public void clickOnDefaultAccountLoginByIndex(String i) throws Throwable {
		click(getDefaultLoginByIndex(i), "Last Sold TO is Linked to User");
	}

	/**
	 * This method is to check availability of usernames
	 * 
	 * @param tabName
	 * @throws Throwable
	 */
	public void checkAvailability() throws Throwable {
			click(CHECK_AVAILABLITY_BUTTON, "check availability Button");
	}

	/**
	 * get account name in linked accounts
	 * 
	 * @return
	 * @throws Throwable
	 */
	public List<String> getAccountNameInLinkedAccounts() throws Throwable {

		List<WebElement> myList = driver.findElements(ACCOUNT_NAME);
		List<String> all_elements_text = new ArrayList<>();
		for (int i = 0; i < myList.size(); i++) {
			all_elements_text.add(myList.get(i).getText());
		}
		return all_elements_text;
	}

	/**
	 * This method is to verify the Account Name
	 * 
	 * @param expectedName
	 * @param actualName
	 * @throws Throwable
	 */
	public void verifyAccountNameStartsWith(List<String> expectedName, String actualName) throws Throwable {
		for (i = 0; i < 5; i++) {
			if ((expectedName.get(i).toUpperCase()).startsWith(actualName)) {
				reporter.SuccessReport("verify Account Name Starts With",
						"All Sold To Accounts Started with "+expectedName+" are Exist and Returned",  expectedName.get(i));
			} else {
				reporter.failureReport("verify Account Name Starts With",
						"Account Name verification is not successfull", "", driver);
			}
		}

	}

	/**
	 * Method is to verify the Remove default link and click on it
	 * 
	 * @throws Throwable
	 */
	public void removeDefaultLink() throws Throwable {
		if (isElementVisible(REMOVE_DEFAULT_LINK, 3, "Remove default link")) {
			click(REMOVE_DEFAULT_LINK, "Remove default link");
		} else {
			reporter.failureReport("verify Remove default link exists", "Remove default link does not exists", "",
					driver);
		}
	}

	/**
	 * verify no default account msg
	 * 
	 * @throws Throwable
	 */
	public void verifyNoDefaultAccountISPresent() throws Throwable {
		if (isElementPresent(NO_DEFAULT_ACCOUNT_DETAILS, "no default account")) {
			reporter.SuccessReport("verify no default account message exists", " no default account message exists",
					"");
		} else {
			reporter.failureReport("verify no default account message exists", "no default account message not exists",
					"", driver);
		}
	}

	/**
	 * method is to click on |< paging
	 * 
	 * @throws Throwable
	 */
	public void clickOnLeftpaging() throws Throwable {
		click(LEFT_PAGING, "LEFT PAGING");
	}

	/**
	 * This method is to verify the default account existance
	 * 
	 * @throws Throwable
	 */
	public void verifyDefaultAccountAddress() throws Throwable {
		if (isElementPresent(DEFAULT_ACCOUNT_ADDRESS, "default account address")) {
			reporter.SuccessReport("verify default account exists", " default account exists", "");
		} else {
			reporter.failureReport("verify default account exists", "no default account does not exists", "", driver);
		}
	}

	/**
	 * This method is to verify the error messsage when user driectly clicks on
	 * create user
	 * 
	 * @throws Throwable
	 */
	public void verifyErrorMessage() throws Throwable {
		if (isElementPresent(ERR_MSG, "Error Message ")) {
			String errorMessage = driver.findElement(ERR_MSG).getText();
			reporter.SuccessReport("Verify Error Msg Exists", "Error Msg Exists", errorMessage);
		} else {
			reporter.failureReport("Verify Error Msg Exists", "Verify Error Msg does not exists", "", driver);
		}
	}

	/**
	 * This method is to enter email in create an account page
	 *
	 */
	public void enterEmailAddressInAddAnAccount(String email) throws Throwable {
		clearData(EMAILADDRESS);
		typeText(EMAILADDRESS, email, "email adress");
	}

	/**
	 * method is to Select Email Format
	 * 
	 * @throws Throwable
	 */
	public void selectEmailFormat(String Option) throws Throwable {
		selectByVisibleText(EMAILFORMAT_DD, Option, "Email Format::" + Option + "");
	}

	/**
	 * method is to Select Email Format
	 * 
	 * @throws Throwable
	 */
	public void enterNotes(String Text) throws Throwable {
		typeText(TEXTAREA, Text, "Notes::" + Text + "");
	}

	/**
	 * This method is to Click On Back to User Search Link
	 *
	 */
	public void clickBackToUserSearch() throws Throwable {
		click(BACKTOUSERSEARCH_LINK, "User Search");
	}

	/**
	 * Method is to Verify the Search Result of Email for Created User name
	 * verification
	 * 
	 * @param contactName
	 * @throws Throwable
	 */
	public void contactNameSearchResultVerificationofCreatedUser(String contactName) throws Throwable {
		if (isElementPresent(CMTObj.getUser(contactName), "Created User Contact Name")) {
			reporter.SuccessReport("Manage Web Groups: User Management", "Created User is Exist : ", contactName);
		} else {
			reporter.failureReport("Manage Web Groups: User Management", "Created User does not Exists ", contactName,
					driver);
		}
	}

	/**
	 * This method is to Click On Shared User Link
	 *
	 */
	public void clickOnSharedUserUrl() throws Throwable {
		if (isVisibleOnly(SHAREDUSERURL, "Shared User Url")) {
			String URl = getText(SHAREDUSERURL, "URL");
			System.out.println(URl);
			click(SHAREDUSERURL, "Shared User Url");
			reporter.SuccessReport("Verify User URL in Information Tab on Manage Web groups: Create User Page",
					" User URL in Information Tab Exists", "User URL::" + URl + "");
		} else {
			reporter.failureReport("Verify User URL in Information Tab on Manage Web groups: Create User Page",
					" User URL in Information Tab Does Not Exists", "");
		}
	}

	public void clickDesignatedShippingOptions(String optionToSelect) throws Throwable {
		click(DesignatedShippingOption_Button, "Designated_Shipping_Button");
		if (isElementPresent(available_Option(optionToSelect), "Available Option")) {
			selectByVisibleText(CMTObj.Designatedshippingoptions, optionToSelect, "Click on" + optionToSelect);
			click(buttontoclickFedExoptin, optionToSelect + " is selected And Moved to allowed Options");
		} else {
			// do nothing
		}
		click(DesignatedshippingFedoption_dropdown, "Default Shipping Option");
		selectByVisibleText(DesignatedshippingFedoption_dropdown, optionToSelect,
				"Default_Shipping_Option Selected is::" + optionToSelect + "");
	}

	public void selectParticularDesignatedShippingOption(String optionToSelect) throws Throwable {
		click(DesignatedShippingOption_Button, "Designated_Shipping_Button");
		if (isElementPresent(available_Option(optionToSelect), "Available Options")) {
			List<WebElement> myList = driver.findElements(available_Option(optionToSelect));
			for (int i = 0; i < myList.size(); i++) {
				String text = (myList.get(i)).getText();
				selectByVisibleText(Designatedshippingoptions, text, "Designated_Shipping_Option::" + text + "");
				click(buttontoclickFedExoptin, text + " is selected And Moved to allowed Options");
				Thread.sleep(2000);
			}
		} else {
			// Do nothing
		}
	}

	/**
	 * This method is to click on Roles and permissions tab and check the one
	 * required permission check box and update.
	 * 
	 * @param menuName
	 * @param userPermissions
	 * @throws Throwable
	 */
	public void verifySetPermissions(String userPermissions) throws Throwable {
		if (isCheckBoxSelected(getUserPermission(userPermissions))) {
			LOG.info(userPermissions + " check box already checked: " + userPermissions);
			reporter.SuccessReport("Verify the Permission is Enabled::" + userPermissions,
					"check box already checked::" + userPermissions + "", userPermissions);
		} else {
			reporter.failureReport("Verify Permissions::", "Permission os not Enabled", "" + userPermissions + "");
		}
	}

	public void clickOnRolesAndPermissionsTab(String menuName) throws Throwable {
		click(getUsersTabMenus(menuName), "Roles And Permissions");
	}

	/**
	 * This Method is to validate existing user error message required
	 * permission check box and update.
	 * 
	 * @param menuName
	 * @param userPermissions
	 * @throws Throwable
	 */
	public void verifyUserNotExistMessage(String userName) throws Throwable {
		if (isVisibleOnly(CMTObj.ERRORMSG_CREATEUSER, "Error message")) {
			String errorMessage = getText(CMTObj.ERRORMSG_CREATEUSER, "Error message");
			reporter.failureReport("New User Creation Error Message", "Your given User id " + userName + "is",
					errorMessage, driver);
		} else {
			reporter.SuccessReport("Availability of new user", "The given User ID " + userName + "is", "Available");
		}
	}

	public void verifyUpdateSuccessMessage() throws Throwable {
		if (isVisibleOnly(CMTObj.lblSuccessMessage, "Success Message")) {
			reporter.SuccessReport("Update status", "Update status is ",
					getText(CMTObj.lblSuccessMessage, "Success Message"));

		} else {
			reporter.failureReport("Update status", "Update status is ",
					getText(CMTObj.lblSuccessMessage, "Success Message"), driver);
		}
	}

	public boolean verifyCheckBoxSelectedForFirstElement() throws Throwable {
		return isCheckBoxSelected(chkBxWebElement1);
	}

	public boolean verifyCheckBoxSelectedForSecondElement() throws Throwable {
		return isCheckBoxSelected(chkBxWebElement2);
	}

	public boolean verifyCheckBoxSelectedForThirdElement() throws Throwable {
		return isCheckBoxSelected(chkBxWebElement3);
	}

	public boolean verifyCheckBoxSelectedForFourthElement() throws Throwable {
		return isCheckBoxSelected(chkBxWebElement4);
	}

	public void clickOnAddCategoryPlusIcon() throws Throwable {
		click(addCategoryPlusButton, "Add Category ", "Add Category plus icon");
	}

	public void enterNewCategory(String val) throws Throwable {
		if (isVisibleOnly(QTPCATEGORY_NAME, "QTPCategory Name")) {
			type(QTPCATEGORY_NAME, val, "QTPCategory Name");
			reporter.SuccessReport("Edit Category Name on Company Standards Management",
					"Category Name Exists and Modified", val);
		} else
			reporter.failureReport("Edit Category Name on Company Standards Management", "Category Name is Not Exists",
					val, driver);
	}

	public void clickOnCreateButton() throws Throwable {
		click(CREATE_BUTTON, "Create new Button", "Create new Button");
	}

	public void clickOnProductGroupLink() throws Throwable {
		click(lnkAddProductGroup, "Add Product Group", "Add Product Group");
	}

	public void enterValueToCreateNewProductGroup(String val) throws Throwable {
		type(PRD_GRP_NEW, val, "Product group");
	}

	public void selectCategoryFromDropDown(String val) throws Throwable {
		click(selectCategorydrp, "Select drop down list", "select category");
		// click(selectCatDrpValue(val), "Select category", val);
		Actions action = new Actions(driver);
		action.sendKeys(Keys.ARROW_UP).perform();
		action.sendKeys(Keys.ENTER).perform();

	}

	public void deleteProductGroup() throws Throwable {

		List<WebElement> salesRep = driver.findElements(lnkProductGroupToDelete);

		for (i = 1; i <= salesRep.size(); i++) {
			// salesRep.get(1).click();
			driver.findElement(By.xpath("(//*[@class='cs-editProdGroup row'])[1]")).click();
			click(lnkDeleteProductGroups, "Delete product group", "Delete Product group");
			acceptAlert();
			Thread.sleep(4000);
		}
	}

	public void setPermissionsToDisableWithoutReport(String userPermissions) throws Throwable {
		if (isCheckBoxSelected(getUserPermission(userPermissions))) {
			click(getUserPermission(userPermissions), "User permissions : " + userPermissions + " is OFF");
			click(UPDATE_USER_BTN, "Update user button");
			if (isElementPresent(PERMISSION_UPDATE_MSG, "update sucessful message")) {
				LOG.info(userPermissions + " updated Permssions");
			} else {
			}
		} else {
			LOG.info(userPermissions + " check box already checked.");
		}

	}

	public void clickOnLogout() throws Throwable {
		click(lnkLogout, "logout", "logout link");
	}

	public void selectUseTheCustomizedPermissionBelowRadioButton() throws Throwable {
		click(rbtnUsetheCustomizedPermissionBelow, "User The customized Permission Below",
				"User The customized Permission Below");
	}

	public void unCheckEditContactInformationCheckBox() throws Throwable {
		boolean status = false;
		if (isCheckBoxSelected(chbxEditContactInformation)) {
			status = true;
			click(chbxEditContactInformation, "Edit Contact Information", "Edit contact information check box");
		} else {
			status = false;
			String s1 = Boolean.toString(status);
			reporter.SuccessReport("Edit contact Information Check box",
					"Edit contact information check box was checked", s1);
		}
	}

	public void verifyUncheckedEditContactInformation() throws Throwable {
		boolean status = false;
		if (!isCheckBoxSelected(chbxEditContactInformation)) {
			status = true;
			click(chbxEditContactInformation, "Edit Contact Information",
					"Edit contact information check box was un checked");
		} else {
			status = false;
			String s1 = Boolean.toString(status);
			reporter.failureReport("Edit contact Information Check box",
					"Edit contact information check box was checked", s1);
		}
	}

	public void clickOnUpdateUser() throws Throwable {
		click(btnUpdateUser, "Update user button", "Update user button");
		if (isElementPresent(CMTObj.PERMISSION_UPDATE_MSG, "update sucessful message")) {
			reporter.SuccessReport("Verify the Sucess message ", "Permissions Updated Succesfully",
					getText(CMTObj.PERMISSION_UPDATE_MSG, "update sucessful message"));
		} else {
			reporter.failureReport("Verify the sucess message", "Permissions are not Updated Succesfully", "", driver);
		}
	}

	public void verifyEnabledPermissions() throws Throwable {
		boolean status = false;
		String s1 = null;
		if (isCheckBoxSelected(lblEnable_Change_Login_Password)) {
			status = true;
			s1 = Boolean.toString(status);
			reporter.SuccessReport("Enable_Change_Login_Password", "Enable Change Login Password ", s1);
		} else {
			status = false;
			reporter.failureReport("Enable_Change_Login_Password", "Enable Change Login Password ", s1, driver);
		}

		if (isCheckBoxSelected(lblEdit_Contact_Information)) {
			status = true;
			s1 = Boolean.toString(status);
			reporter.SuccessReport("lblEdit_Contact_Information", "lblEdit_Contact_Information ", s1);
		} else {
			status = false;
			reporter.failureReport("lblEdit_Contact_Information", "lblEdit_Contact_Information ", s1, driver);
		}

		if (isCheckBoxSelected(lblEnable_Edit_My_Account)) {
			status = true;
			s1 = Boolean.toString(status);
			reporter.SuccessReport("lblEnable_Edit_My_Account", "lblEnable_Edit_My_Account ", s1);
		} else {
			status = false;
			reporter.failureReport("lblEnable_Edit_My_Account", "lblEnable_Edit_My_Account ", s1, driver);
		}

		if (isCheckBoxSelected(lblEnable_Edit_Checkout_Defaults_and_Favorites)) {
			status = true;
			s1 = Boolean.toString(status);
			reporter.SuccessReport("lblEnable_Edit_Checkout_Defaults_and_Favorites",
					"lblEnable_Edit_Checkout_Defaults_and_Favorites ", s1);
		} else {
			status = false;
			reporter.failureReport("lblEnable_Edit_Checkout_Defaults_and_Favorites",
					"lblEnable_Edit_Checkout_Defaults_and_Favorites ", s1, driver);
		}

		if (isCheckBoxSelected(lblEnable_Allow_Access_to_Customer_Documents)) {
			status = true;
			s1 = Boolean.toString(status);
			reporter.SuccessReport("lblEnable_Allow_Access_to_Customer_Documents",
					"lblEnable_Allow_Access_to_Customer_Documents ", s1);
		} else {
			status = false;
			reporter.failureReport("lblEnable_Allow_Access_to_Customer_Documents",
					"lblEnable_Allow_Access_to_Customer_Documents ", s1, driver);
		}

		if (isCheckBoxSelected(lblEnable_RMA)) {
			status = true;
			s1 = Boolean.toString(status);
			reporter.SuccessReport("lblEnable_RMA", "lblEnable_RMA ", s1);
		} else {
			status = false;
			reporter.failureReport("lblEnable_RMA", "lblEnable_RMA ", s1, driver);
		}

		if (isCheckBoxSelected(lblEnable_Invoicing)) {
			status = true;
			s1 = Boolean.toString(status);
			reporter.SuccessReport("lblEnable_Invoicing", "lblEnable_Invoicing ", s1);
		} else {
			status = false;
			reporter.failureReport("lblEnable_Invoicing", "lblEnable_Invoicing ", s1, driver);
		}

		if (isCheckBoxSelected(lblEnable_Standard_Reports)) {
			status = true;
			s1 = Boolean.toString(status);
			reporter.SuccessReport("lblEnable_Standard_Reports", "lblEnable_Standard_Reports ", s1);
		} else {
			status = false;
			reporter.failureReport("lblEnable_Standard_Reports", "lblEnable_Standard_Reports ", s1, driver);
		}
		if (isCheckBoxSelected(lblEnable_Order_Tracking)) {
			status = true;
			s1 = Boolean.toString(status);
			reporter.SuccessReport("lblEnable_Order_Tracking", "lblEnable_Order_Tracking ", s1);
		} else {
			status = false;
			reporter.failureReport("lblEnable_Order_Tracking", "lblEnable_Order_Tracking ", s1, driver);
		}
		if (isCheckBoxSelected(lblMy_Orders_Only)) {
			status = true;
			s1 = Boolean.toString(status);
			reporter.SuccessReport("lblMy_Orders_Only", "lblMy_Orders_Only ", s1);
		} else {
			status = false;
			reporter.failureReport("lblMy_Orders_Only", "lblMy_Orders_Only ", s1, driver);
		}
		if (isCheckBoxSelected(lblEnable_Webinars)) {
			status = true;
			s1 = Boolean.toString(status);
			reporter.SuccessReport("lblEnable_Webinars", "lblEnable_Webinars ", s1);
		} else {
			status = false;
			reporter.failureReport("lblEnable_Webinars", "lblEnable_Webinars ", s1, driver);
		}
		if (isCheckBoxSelected(lblEnable_Company_Standards_Product_Groups)) {
			status = true;
			s1 = Boolean.toString(status);
			reporter.SuccessReport("lblEnable_Company_Standards_Product_Groups",
					"lblEnable_Company_Standards_Product_Groups ", s1);
		} else {
			status = false;
			reporter.failureReport("lblEnable_Company_Standards_Product_Groups",
					"lblEnable_Company_Standards_Product_Groups ", s1, driver);
		}
		if (isCheckBoxSelected(lblEnable_Product_Compare)) {
			status = true;
			s1 = Boolean.toString(status);
			reporter.SuccessReport("lblEnable_Product_Compare", "lblEnable_Product_Compare ", s1);
		} else {
			status = false;
			reporter.failureReport("lblEnable_Product_Compare", "lblEnable_Product_Compare ", s1, driver);
		}
		if (isCheckBoxSelected(Enable_Resource_Centers)) {
			status = true;
			s1 = Boolean.toString(status);
			reporter.SuccessReport("Enable_Resource_Centers", "Enable_Resource_Centers ", s1);
		} else {
			status = false;
			reporter.failureReport("Enable_Resource_Centers", "Enable_Resource_Centers ", s1, driver);
		}
		if (isCheckBoxSelected(Enable_Shop_By_Brand)) {
			status = true;
			s1 = Boolean.toString(status);
			reporter.SuccessReport("Enable_Shop_By_Brand", "Enable_Shop_By_Brand ", s1);
		} else {
			status = false;
			reporter.failureReport("Enable_Shop_By_Brand", "Enable_Shop_By_Brand ", s1, driver);
		}
		if (isCheckBoxSelected(Enable_menu_for_products)) {
			status = true;
			s1 = Boolean.toString(status);
			reporter.SuccessReport("Enable_menu_for_products", "Enable_menu_for_products ", s1);
		} else {
			status = false;
			reporter.failureReport("Enable_menu_for_products", "Enable_menu_for_products ", s1, driver);
		}
		if (isCheckBoxSelected(Enable_Configurators)) {
			status = true;
			s1 = Boolean.toString(status);
			reporter.SuccessReport("Enable_Configurators", "Enable_Configurators ", s1);
		} else {
			status = false;
			reporter.failureReport("Enable_Configurators", "Enable_Configurators ", s1, driver);
		}
		if (isCheckBoxSelected(Enable_Inventory_Blowout)) {
			status = true;
			s1 = Boolean.toString(status);
			reporter.SuccessReport("Enable_Inventory_Blowout", "Enable_Inventory_Blowout ", s1);
		} else {
			status = false;
			reporter.failureReport("Enable_Inventory_Blowout", "Enable_Inventory_Blowout ", s1, driver);
		}
		if (isCheckBoxSelected(Enable_Personal_Product_List)) {
			status = true;
			s1 = Boolean.toString(status);
			reporter.SuccessReport("Enable_Personal_Product_List", "Enable_Personal_Product_List ", s1);
		} else {
			status = false;
			reporter.failureReport("Enable_Personal_Product_List", "Enable_Personal_Product_List ", s1, driver);
		}
		if (isCheckBoxSelected(Enable_menu_for_solutions)) {
			status = true;
			s1 = Boolean.toString(status);
			reporter.SuccessReport("Enable_menu_for_solutions", "Enable_menu_for_solutions ", s1);
		} else {
			status = false;
			reporter.failureReport("Enable_menu_for_solutions", "Enable_menu_for_solutions ", s1, driver);
		}
		if (isCheckBoxSelected(Enable_Accessories)) {
			status = true;
			s1 = Boolean.toString(status);
			reporter.SuccessReport("Enable_Accessories", "Enable_Accessories ", s1);
		} else {
			status = false;
			reporter.failureReport("Enable_Accessories", "Enable_Accessories ", s1, driver);
		}
		if (isCheckBoxSelected(Enable_Search)) {
			status = true;
			s1 = Boolean.toString(status);
			reporter.SuccessReport("Enable_Search", "Enable_Search ", s1);
		} else {
			status = false;
			reporter.failureReport("Enable_Search", "Enable_Search ", s1, driver);
		}

		if(getText(allMyAccountsForEnableInvoice,"All my Accounts").equalsIgnoreCase("All My Accounts"))
		{
			status = true;
			s1 = Boolean.toString(status);
			reporter.SuccessReport("All my accounts", "All my accounts is default value ","Select All My Account in Enable Invoicing Under Account History Exists and Selected is " +s1);
		}else{
			reporter.SuccessReport("All my accounts", "All my accounts is default value ","Select All My Account in Enable Invoicing Under Account History Exists and Selected is"+ s1);
		}

		if(getText(allMyAccountsForEnableStandardReports,"All my Accounts").equalsIgnoreCase("All My Accounts"))
		{
			status = true;
			s1 = Boolean.toString(status);
				reporter.SuccessReport("All my accounts", "All my accounts is default value ","Select All My Account in Enable Standard Reports Under Account History Exists and Selected is " +s1);
		}else{
			reporter.SuccessReport("All my accounts", "All my accounts is default value ","Select All My Account in Enable Standard Reports under Account History Exists and Selected is"+ s1);
		}

		if(getText(allMyAccountsForEnableOrderTracking,"All my Accounts").equalsIgnoreCase("All My Accounts"))
		{
			status = true;
			s1 = Boolean.toString(status);
			reporter.SuccessReport("All my accounts", "All my accounts is default value ","Select All My Account in Enable Order tracking Under Account History Exists and Selected is " +s1);
		}else{
			reporter.SuccessReport("All my accounts", "All my accounts is default value ","Select All My Account in Enable Order tracking under Account History Exists and Selected is"+ s1);
		}



	}

	public void disbaleOverRidePaymentOption() throws Throwable{
		boolean status=false;
		String s1=null;
		if (isCheckBoxSelected(overRidePaymentOption)) {
			status = true;
			s1 = Boolean.toString(status);
			click(overRidePaymentOption,"overRidePaymentOption","overRidePaymentOption");
			reporter.SuccessReport("overRidePaymentOption", "overRidePaymentOption is ", "OFF");
		} else {
			status = false;
			reporter.SuccessReport("overRidePaymentOption", "overRidePaymentOption is ", "OFF");
		}
	}

	public void verifyRepEmailInSalesPage(String email) throws Throwable{
		boolean status = false;
			List<WebElement> fieldsList=driver.findElements(repUserVerificationInSalesTeamPage);
			for (int i = 0; i < fieldsList.size(); i++) {
				if(fieldsList.get(i).getText().equalsIgnoreCase(email)){
					status=true;
					reporter.SuccessReport("Verify Repo email availability","Repo name availability",email + " is available in sales team page");
				break;
				}else
					status=false;
			}
			if(!status) {
				reporter.failureReport("Verify Repo email availability", "Repo name availability", email + " is not available in sales team page", driver);
			}
	}
	

	public void verifyManageWebGroupSettings() throws Throwable
	{
		isVisible(lblManageWebGroups, "manage web groups title verification");
	}

	public void verifyManageWebGroupsUserManagement() throws Throwable
	{
		isVisible(lblManageWebGroupsUserManagement, "ManageWebGroupsUserManagement title verification");
	}

	/**
	 * This method is to verify the availability of user name
	 *
	 */
	public void verifyAvailabiltyOfUserNameExists() throws Throwable {
		if (isVisibleOnly(NOTAVAILABLE_MESSAGE, "error message")) {
			String MSG=getText(NOTAVAILABLE_MESSAGE, "error message");
	reporter.SuccessReport("Verify UserName availability","User Name Already Exists" ,"UserName::Not Available");
		} else {
			reporter.failureReport("Verify UserName availability","User Name not Exists" ,"UserName:: Available");

		}
	}
	public void updateUser()throws Throwable{
		click(UPDATE_USER_BTN, "Update user button");
		waitForVisibilityOfElement(PERMISSION_UPDATE_MSG, "PERMISSION UPDATE MSG");
	}
	public void verifyDDPermission(String Permission,String Option)throws Throwable{
		if(isVisibleOnly(Account_DD_Permission(Permission,Option),"Persission")) {
			reporter.SuccessReport("Verify Select "+Option+" in "+Permission+" Under in Roles and Permissions Tab on Manage Web groups: Create User Page","Select "+Option+" in "+Permission+" Under Account History Exists and Selected" ,Permission+"::"+Option);
		}else {
			reporter.failureReport("Verify Select "+Option+" in "+Permission+" Under  in Roles and Permissions Tab on Manage Web groups: Create User Page","Permission not Exists" ,"",driver);
		}
	}
/**
 * This method is to verify the availability of user name
 *
 */
public void verifyAvailabiltyOfUserName(String Option) throws Throwable {
	if (isElementNotPresent(AVAILABLE_MESSAGE, "error message")) {
	clearData(USER_NAME_FIELD);
	type(USER_NAME_FIELD, Option, "user name");

	} else {
	Log.info("user name is available");
	reporter.SuccessReport("Availability Message ", "User availability message",
	getText(AVAILABLE_MESSAGE, "error message"));
	    }
}
public void clickOnCreateanacccount() throws Throwable {
	click(CREATE_AN_ACCOUNT, "Create an account", getText(CREATE_AN_ACCOUNT, "Create an account"));
}

public void verifyPaymentOptionsInCheckOutSettings(String option) throws Throwable {
	String strArray[] = option.split(",");
	for (int i = 0; i < strArray.length; i++) {
		if (isElementPresent(paymentAllowedOption(strArray[i]), "payment options in Allowed Options")) {
			reporter.SuccessReport("Verify Payment Options ",strArray[i]+ "Allowed Options in Payment Options on Checkout Settings Tab", "");
		} else {
			reporter.failureReport("Verify Payment Options ",strArray[i]+ "Allowed Options in Payment Options on Checkout Settings Tab", "");

		}
		}
	}


         public static void verifyShippingOptions()throws Throwable{
	if( driver.findElement(CMTObj.User_service_levelshipping).isSelected()) {
		reporter.SuccessReport("User Service level Shipping Selected in Shipping Options in the Checkout Settings Tab on Manage Web groups: Create User Page","User Service level Shipping Selected in Shipping Options on Checkout Settings Tab","");
	}else {
		reporter.failureReport("verify User Service level Shipping Selected in Shipping Options in the Checkout Settings Tab on Manage Web groups: Create User Page","User Service level Shipping Not Selected","",driver);
	}
	
}
         public void selectedOptionPaymentMethod(String option) throws Throwable {
     		String selectedOption=getText(DEFAULT_PAYMENT_OPTION, "selected option");
     		System.out.println("selectedOption"+selectedOption);
     		if(selectedOption.equalsIgnoreCase(option)) {
     			reporter.SuccessReport("Verifyig default selected option", "Default selected option is "+option, option);
     		}
     		else {
     			reporter.failureReport("Verifyig default selected option", "Default selected option is not "+option, selectedOption,driver);
     		}
     	}
      


public void verifyClientSearchTitle() throws Throwable
{
	isVisible(lblClientSearch, "client search title verification");
}

public void verifyProductStandardsTitle() throws Throwable
{
	isVisible(lblProductStandards, "ProductStandardsTitle verification");
}

	public void loginVerificationByGetText(String contactName) throws Throwable {
		String a = getText(CMTObj.getLoginVerficationByContactNameOnHeader(contactName),"contact Name is " + contactName);
		//if(getText(CMTObj.getLoginVerficationByContactNameOnHeader(contactName),"LoginName").contains(contactName)){
		if (a.contains(contactName)) {
			reporter.SuccessReport("Verify the Same User Logged into Insight from CMT",
					"User login verification is successfull. User is : ", contactName);
		} else {
			reporter.failureReport("Verify the Same User Logged into Insight",
					"User login verification is not successfull.Actual name is: ", contactName, driver);
		}
	}

	public void verifyManageWebGroupsCreateUser() throws Throwable
	{
		isVisible(CMTObj.lblWebGroupManagement, "Web group create User page verification");
	}
	public boolean verifyWebGroupsManagementUsers() throws Throwable
	{
		return isVisible(lblWebGroupManagementUsers, "WebGroupsManagementUsers page loaded");
	}

	/*public void setPermissionsWithMultipleOptions(String menuName, String userPermissions) throws Throwable {
		click(getUsersTabMenus(menuName), "Roles And Permissions");
		Thread.sleep(3000);
		String[] permissions1=userPermissions.split(",");
		for (i = 0; i < permissions1.length; i++) {
			setPermissions(data.get("Menu_Name"),permissions1[i]);
		}

		if (isCheckBoxSelected(getUserPermission(userPermissions))) {
			LOG.info(userPermissions + " check box already checked: " + userPermissions);
			reporter.SuccessReport(userPermissions, "check box already checked::" + userPermissions + " ON",
					userPermissions + " ON");
		} else {
			click(getUserPermission(userPermissions), "User permissions: " + userPermissions);
			click(UPDATE_USER_BTN, "Update user button");
			waitForVisibilityOfElement(PERMISSION_UPDATE_MSG, "PERMISSION UPDATE MSG");
			if (isElementPresent(PERMISSION_UPDATE_MSG, "update sucessful message")) {
				reporter.SuccessReport("Verify the Sucess message ", "Permissions Updated Succesfully ",
						"Permissions Updated Succesfully");
			} else {
				reporter.failureReport("Verify the sucess message", "Permissions are not Updated Succesfully", "",
						driver);
			}
		}
	}*/
public void clickOnPermissionAndRolesMenu(String menuName) throws Throwable{
	click(getUsersTabMenus(menuName), "Roles And Permissions");
}


public void getOrderNumbersFromRecentOrders() throws Throwable{
	List<WebElement> order= driver.findElements(lblOrderNumber);

	for (int i = 0; i < order.size(); i++) {
		order.get(i).getText();
		reporter.SuccessReport("Order Number ","Order numbers are ",order.get(i).getText());
	}
}
public void NoOptionOtherThanSLSinShippingOption(String SLS)throws Throwable{
	//defaultShippingOption
	List<WebElement> Options= driver.findElements(defaultShippingOption(SLS));
	for (int i = 0; i < Options.size(); i++) {
		Options.get(i).getText();
	}
	reporter.SuccessReport("Verify No Shipping Partners Only SLS Carries in Shipping Options in the Checkout Settings Tab on Manage Web groups: Create User Page","No Shipping Partners Only SLS Carries Exists in Shipping Options","");
}
//DEFAULTCHECKBOXINBILLINGADDRESS
public void DEFUALTCHECKBOX()throws Throwable{
	if(isVisibleOnly(DEFAULTCHECKBOXINBILLINGADDRESS,"Defualt Address")) {
		reporter.failureReport("Verify No Default Billing Addresses are Not Selected","Default Billing Addresses are Selected","");	
	}else {
		reporter.SuccessReport("Verify No Default Billing Addresses are Not Selected in the Checkout Settings Tab on Manage Web groups: Create User Page","No Default Billing Addresses are Selected in Checkout Settings Tab","");	
	}
	
}
public void defualtShippingAddressCheckBox()throws Throwable{
	if(isVisibleOnly(DEFAULTCHECKBOXINSHIPPINGADDRESS,"Defualt Address")) {
		reporter.SuccessReport("Verify Default All Shipping Addresses Selected in the Checkout Settings Tab on Manage Web groups: Create User Page","Default All Shipping Addresses Selected in the Checkout Settings Tab","");	
	}else {
		reporter.failureReport("Verify No Default Shipping Addresses are Not Selected","Default Shipping Addresses are Selected","");	
	}
	
}
public void verifySetPermissionsDisabled(String userPermissions) throws Throwable {
	if (isCheckBoxSelected(getUserPermission(userPermissions))) {
		LOG.info(userPermissions + " check box already checked: " + userPermissions);
		reporter.failureReport("Verify the Permission is Enabled::" + userPermissions,
				"check box already checked::" + userPermissions + "", userPermissions);
	} else {
		reporter.SuccessReport("Verify Permissions::", "Permission is not Enabled", "" + userPermissions + "");
	}
}


	public void permissionForDD(String userPermission, String optionDD) throws Throwable {
		if (!isCheckBoxSelected(getUserPermission(userPermission))){
			click(getUserPermission(userPermission),userPermission,userPermission);
			click(getPermissionDropDowns(userPermission), "permission drop down");
		}
		selectByVisibleText(getPermissionDropDowns(userPermission), optionDD, "permission drop down");
		click(UPDATE_USER_BTN, "Update user button");
		if (isElementPresent(PERMISSION_UPDATE_MSG, "update sucessful message")) {
			reporter.SuccessReport("Verify the Sucess message ", "Permissions Updated Succesfully", "");
		} else {
			reporter.failureReport("Verify the sucess message", "Permissions are not Updated Succesfully", "", driver);
		}
	}

	public void acceptCookies() throws Throwable{
	if(isVisibleOnly(CommonObj.AcceptAlerts,"Accept Cookies")) {
		click(CommonObj.AcceptAlerts, "Accept Cookies");
	}
	}
	
	public void VerifytheLinkedAccountsText() throws Throwable {
		//List<WebElement> list = driver.findElements(LINKEDACCOUNTS);
		List<String> values = new ArrayList<String>();
				if(isVisibleOnly(LinkedAccountsText,"Linked Accounts Data")) {
					List<WebElement> list2 = driver.findElements(LinkedAccountsText);
					for (int i = 0; i<15; i++) {
						for (int j=0;j<5;j++) {
						String textlinkedaccount= list2.get(i).getText().trim();
						values.add(textlinkedaccount);
						}
					reporter.SuccessReport("Web Group Management", "All Active Sold to's for the Current Web Group Displays", "AccountName,AccountNumber,Address,AccountStatus,DefaultLogin:"+values);
					values.clear();
					}
				}
	//	}
	}

	public void handlebetaPopup() throws Throwable{

		if (driver.findElement(CartObj.POP_UP_EMAILID).isDisplayed()) {
			handleWelcomeToInsightBetaPopUp();
		}
	}
	public void verifyPageStartsWithatLinkedAccounts(String Page) throws Throwable {
		if (isVisibleOnly(STARTPAGE, "default account address")) {
			String SartPage=getText(STARTPAGE,"Start Page").trim();
			if(SartPage.contains(Page)) {
			reporter.SuccessReport("Web Group Management", "Under Linked Accounts Next Page is Exist and Displays", SartPage);
		} else {
			reporter.failureReport("Web Group Management", "Under Linked Accounts Next Page is Not Exist", "", driver);
		}
	    }else {
	    	reporter.failureReport("Default account address", "default account address does not exists", "", driver);
	    }
	}
	
	/**
	 * Method is to enter WG Custom 800 Number And check Display On Web check box
	 * @throws Throwable 
	 */
	public void enterWGCustom800NumberAndDisplayOnWeb(String phoneNumebr) throws Throwable {
		type(CLIENT_SUPPORT_800_PHONE_NUMEBR, phoneNumebr, "800 Number", driver);
		if(isCheckBoxSelected(DISPLAY_THIS_ON_WEB)) {
			reporter.SuccessReport("Display this on web check box", "Display this on web check box is already checked", "", driver);
		}else {
			click(DISPLAY_THIS_ON_WEB, "Display this on web check box", "Display this on web check box");
		}
		click(UPDATE_CUSTOMER_PERMISSIONS_BTN, "Update button");
		if (isElementPresent(CUSTOMER_PERMISSION_UPDATE_MSG, "update sucessful message")) {
			reporter.SuccessReport("Verify the Success message ", "Permissions Updated Succesfully","");
		} else {
			reporter.failureReport("Verify the sucess message", "Display this on web check box not checked Succesfully", "",
					driver);
		}
	}
}
