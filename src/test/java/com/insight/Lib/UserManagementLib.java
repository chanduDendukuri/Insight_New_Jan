package com.insight.Lib;

import java.io.File;
import java.io.FileInputStream;
import java.io.FilenameFilter;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import com.insight.ObjRepo.CMTObj;
import com.insight.ObjRepo.UserManagementObj;

public class UserManagementLib extends UserManagementObj {	
	
	CMTLib cmtLib = new CMTLib();
	/**
	 * Method is used to verify user is logged to canada webgrp
	 */
	public void verifyNoUsersErrMsg(String invalidUser) throws Throwable {
				
		  cmtLib.searchUsers(invalidUser);
		if (isElementPresent(NOUSRS_ERRMSG, "No User results")) {
			reporter.SuccessReport("Verify that no users are returned on User Management Page", "No users are returned and error message is displayed", "");
		} else {
			reporter.failureReport("Verify that no users are returned on User Management Page", " users are returned and no error message is displayed", "",driver);
		}
	}

	/**
	 * Method is used to verify user is logged to canada webgrp
	 */
	public void verifyWithValidUserName(String user) throws Throwable {
		 cmtLib.searchUsers(user);
		if (isElementPresent(USERS_TOT, "No User results")) {
			
			String users=getText(USERS_TOT,"");
			
			reporter.SuccessReport("Verify that users having the given search input are returned", "Users having search input returned are "+users+"", "");
		} else {
			reporter.failureReport("Verify that users having the given search input are returned", "Users having search input  has not returnedreturned", "",driver);
		}
	}	
	
	
	/**
	 * Method is used to verify user is logged to canada webgroup
	 */
	public void verifyPagination(String intEndPaging) throws Throwable {
		  selectByVisibleText(RECORDS,intEndPaging,"");
		   if (isElementPresent(getPagination(intEndPaging), "Pagnation")) {
			 
			reporter.SuccessReport("Verify "+intEndPaging+" users are displayed ", intEndPaging+" users are displayed", "");
		 } else {
			reporter.failureReport("Verify "+intEndPaging+" users are displayed ", intEndPaging+" users are  not displayed", "",driver);
		}
	}
	
	
	/**
	 * Method is used to verify user is logged to canada webgrp
	 */
	public void verifyEmailUserName(String emailUser) throws Throwable {
		
	    cmtLib.searchUsers(emailUser);
		if (isElementPresent(getEmailUser(emailUser), "Email User")) {					
			reporter.SuccessReport("Verify that user matching the email address are returned", "User matching the email address returned - "+emailUser+"", "");
		} 
		}
	
	/**
	 * Method is used to verify user is logged to canada webgrp
	 */
	public void verifyWithUserId(String userId) throws Throwable {
		
	    cmtLib.searchUsers(userId);
		if (isElementPresent(getEmailUser(userId), "Email User")) {					
			reporter.SuccessReport("Verify that user matching the UserId are returned", "User matching the UserId returned - "+userId+"", "");
		} else {
			reporter.failureReport("Verify that user matching the UserId are returned", "User matching the UserId is not returned ", "");
		} 
		}
	
	/**
	 * Method is used to verify user is logged to canada webgrp
	 */
	public void clickOnExportToExcel() throws Throwable {
		Thread.sleep(3000);
		if (isElementPresent(EXPORT, "Exprt To Excel ")) {	
			click(EXPORT, "Exprt To Excel ");
			reporter.SuccessReport("Click on Export To Excel button on User Management Page", "Export To Excel button was  found and clicked ", "");
		} else {
			reporter.failureReport("Click on Export To Excel button on User Management Page", "Export To Excel button was not found ", "");
		} 
		}
	
	/**
	 * Method to verify download excel file
	 * 
	 * @throws Throwable
	 */
	public void verifyDownloadedUsersExcelFile(String filePath) throws Throwable {
		Thread.sleep(10000);
		//File root = new File("C:\\Users\\e004303\\Downloads");
		String sfile = System.getProperty("user.dir") + "\\" + "DownloadedFiles" + "\\" + filePath+".xls";
		System.out.println("sfile"+sfile);
		File root = new File(sfile);
		FilenameFilter beginswithm = new FilenameFilter() {
			public boolean accept(File directory, String filename) {
				return filename.startsWith(filePath);
			}
		};
		File[] files = root.listFiles(beginswithm);

		if (files[0] != null) {
			// PATH
			reporter.SuccessReport("File Download Dialog With Open Button ", "File Download Dialog Exists and Selected Open Button", "");
			// load file
			FileInputStream fis = new FileInputStream(files[0]);
			// Load workbook
			HSSFWorkbook wb =new HSSFWorkbook(fis);			
			HSSFSheet sh1 =wb.getSheet("Sheet0");
			int rows = (sh1.getLastRowNum())-1;	
			fis.close();
			reporter.SuccessReport("All Users are included in the Export to Excel File ", "No. of Users:"+rows+"", "");
			reporter.SuccessReport("Verify that t Export to Excel File ", "Excel File is Closed", "");
          
		}
		else {
			reporter.failureReport("File Download Dialog With Open Button ", "File Download Dialog does not  Exists ", "",driver);
		}
	}
	
	
	/**
	 * Method is used to verify user status
	 */
	public void verifyUserStatus() throws Throwable {
		
		String option="";
		if (isElementPresent(STATUS, "Status ")) {	
		 option =getSelectedDropdownOption(STATUS);
			reporter.SuccessReport("Verify User Status", "User status is "+option+"", "");
		} else {
			reporter.failureReport("Verify User Status", "User status is not "+option+"", "",driver);
		} 
		}

	/**
	 * Method is used to verify user status
	 */
	public void selectUserStatus(String userStatus) throws Throwable {

		 
		if (isElementPresent(STATUS, "Status ")) {	
			  selectByVisibleText(STATUS,userStatus,"");
			  cmtLib.searchUsers("");
			reporter.SuccessReport("Verify User Status", "User status is "+userStatus+"", "");
		} else {
			reporter.failureReport("Verify User Status", "User status is not "+userStatus+"", "",driver);
		} 
		}

	/**
	 * Method is used to verify user status
	 */
	public void selectPagination(String pageNo) throws Throwable {
	if(isElementPresent(PAGINATION(pageNo), "Pagination")){
		click(PAGINATION(pageNo),"Pagination");
		reporter.SuccessReport("Click on page 3 to verify results", "Page 3 results displayed","");
	}
		else{
		reporter.failureReport("Click on page 3 to verify results", "No Pagination","");
	}
}
	
	
	
	/**
	 * Method is used to verify UserType
	 */
	public void selectUsertypeUserStatus(String userType ,String userStatus) throws Throwable {		
		
		if (isElementPresent(USER_TYPE, "User Type ")) {	
		selectByVisibleText(STATUS,userStatus,"");
	    selectByVisibleText(USER_TYPE,userType,"");
	    cmtLib.searchUsers("");
		reporter.SuccessReport("Only "+userType+" users are returned in First Page", "Only "+userType+" and "+userStatus+" users are there","");
		} else {
			reporter.failureReport("Only "+userType+" users are returned in First Page", "Only "+userType+" and "+userStatus+"  users are not there","");
		}	
			}
	
	
	/**
	 * Method is used to verify UserType
	 */
	public void selectUsertype(String userType ) throws Throwable {			
		if (isElementPresent(USER_TYPE, "User Type ")) {	
	    selectByVisibleText(USER_TYPE,userType,"User type");
	    cmtLib.searchUsers("");
			reporter.SuccessReport("Only "+userType+" users are returned in First Page", "Only "+userType+" users are there","");
		} else {
			reporter.failureReport("Only "+userType+" users are returned in First Page", "Only "+userType+" users are not there","");
		}	
			}
	
	
	/**
	 * Method is used to verify UserType
	 */
	public void selectWebGroup(String webGroup ) throws Throwable {		
		
		if (isElementPresent(USER_WEBGROUP, "Web Group Relationship ")) {	
	    selectByVisibleText(USER_WEBGROUP,webGroup,"");
	    cmtLib.searchUsers("");
			reporter.SuccessReport("Only "+webGroup+" users are returned in First Page", "Only "+webGroup+" users are there","");
		} else {
			reporter.failureReport("Only "+webGroup+" users are returned in First Page", "Only "+webGroup+" users are not there","");
		}	
			}
	
	
	/**
	 * Method is used to verify WelcomePage
	 */
	public void verifyWelcomePage() throws Throwable {		
		if (isVisibleOnly(HEADER_LOGO, "WelcomePage")) {	
			reporter.SuccessReport("Shared User WelCome Page", "Welcome Home Page exists","");
		} else {
			reporter.failureReport("Shared User WelCome Page", "WelCome Page does not exist","");
		}	
			}
	
	public void verifyErrorMsg() throws Throwable {		
		if (isElementPresent(Error_MSG, "Error Message")) {
			String Error=getText(Error_MSG,"Error Msg");
			reporter.SuccessReport("Verify Error Msg::", "Error Message exists",Error);
		} else {
			reporter.failureReport("Verify Error Msg::", "Error Message does not exist","");
		}	
			}
	public void verifyUserPermissionInvoice_DD() throws Throwable {
		if (isVisibleOnly(Reporting_parent_Inbvoicing, "User Permission")) {
			reporter.SuccessReport("Verify User Permission", "Select Reporting Parent in Enable Invoicing Under Account History exists and Selected", "");
		} else {
			reporter.failureReport("Verify User Permission", " Select Reporting Parent in Enable Invoicing Under Account History exists But Not Selected", "",driver);
		}
	}
	public void verifyUserPermissionOrderTracking_DD() throws Throwable {
		if (isVisibleOnly(Reporting_parent_ORDERTRACKING, "User Permission")) {
			reporter.SuccessReport("Verify User Permission", "Select Reporting Parent in Enable Order Tracking Under Account History exists and Selected", "");
		} else {
			reporter.failureReport("Verify User Permission", " Select Reporting Parent in Enable Order Tracking Under Account History exists But Not Selected", "",driver);
		}
	}
	
	public void verifyErorrMsgOfFirstName()throws Throwable {
		if(isVisibleOnly(FERROR_MSG,"Error Msg")){
		reporter.SuccessReport("Verify Error Msg", "Error Message Exists", getText(FERROR_MSG,"Error Msg"));
		}
		else {
			reporter.failureReport("Verify Error Msg", "Error Message Not Exists", getText(FERROR_MSG,"Error Msg"));
	
		}
	}
	
	public void verifyErorrMsgOfLastName()throws Throwable {
		if(isVisibleOnly(FERROR_MSG,"Error Msg")){
		reporter.SuccessReport("Verify Error Msg", "Error Message Exists", getText(FERROR_MSG,"Error Msg"));
		}
		else {
			reporter.failureReport("Verify Error Msg", "Error Message Not Exists", getText(FERROR_MSG,"Error Msg"));
	
		}
	}
	public void verifyErorrMsgOfPhoneNumber()throws Throwable {
		if(isVisibleOnly(FERROR_MSG,"Error Msg")){
		reporter.SuccessReport("Verify Error Msg", "Error Message Exists", getText(FERROR_MSG,"Error Msg"));
		}
		else {
			reporter.failureReport("Verify Error Msg", "Error Message Not Exists", getText(FERROR_MSG,"Error Msg"));
	
		}
	}//LINKEDACCOUNTS_Defualtac
	//LINKEDACCOUNTS_Defualtacc
	
	public void verifydefualtLinkedAcc()throws Throwable {
		if(isVisibleOnly(LINKEDACCOUNTS_Defualtacc,"Error Msg")){
		reporter.SuccessReport("Verify Default Linked to Account# in the Linked Accounts Tab on Manage Web groups: Create User Page", "Default Linked to Account# in Linked Accounts Tab", "Account#"+getText(LINKEDACCOUNTS_Defualtacc,"Defualt Account"));
		}
		else {
			reporter.failureReport("Verify Default Linked to Account# in the Linked Accounts Tab on Manage Web groups: Create User Page", "Default Linked to Account# in Linked Accounts Tab Not Exists", getText(LINKEDACCOUNTS_Defualtacc,"Defualt Account"));
	
		}
	}
	
	
	
}
