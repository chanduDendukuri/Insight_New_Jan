package com.insight.ObjRepo;

import org.openqa.selenium.By;

import com.insight.accelerators.ActionEngine;

public class UserManagementObj extends ActionEngine{
	
	
	public static By NOUSRS_ERRMSG = By.xpath("//div[@id='userSearchEmptyResultsText'][contains(.,'No results were found based on your search criteria')]");
	
	
	public  static By  getPagination(String pages){
		return By.xpath("//span[@id='showingEnd'][contains(.,'"+pages+"')]");
	}
	
	public static By USERS_TOT = By.xpath("//span[@id='showingTotal']");
	
	
	public  static By  getEmailUser(String emailUser){
		return By.xpath("//table[@id='userSearchResultsTable']//tr[2]//td[contains(.,'"+emailUser+"')]//div");
	}
	public static By EXPORT = By.xpath("//a[@title='Export To Excel']");
	
	public static By STATUS = By.xpath("//select[@id='userStatus']");
	public static By RECORDS = By.xpath("//select[@id='shownRecords']");
	
	public static By USER_TYPE = By.xpath("//select[@id='userUserType']");
	
	public static By USER_WEBGROUP = By.xpath("//select[@id='userWebGroupRelationship']");
	
	public static By PAGINATION (String pageNo){
		return By.xpath("//strong//a[contains(text(),'"+pageNo+"')]");
	}
			
	public  static By  getUserType(String userType){
		return By.xpath("(//td[@id='UserType'][contains(.,'"+userType+"')])[1]");
	 }
	public static By HEADER_LOGO=By.xpath("//div[@class='c-header-logo']");
	public static By Error_MSG=By.xpath("//div[@id='ErrorMsg']");
	public static By Reporting_parent_Inbvoicing=By.xpath("//select[@id='1134_permissionLevel']/option[@selected='selected' and contains(text(),'Reporting Parent')]");
	public static By Reporting_parent_ORDERTRACKING=By.xpath("//select[@id='1139_permissionLevel']/option[@selected='selected' and contains(text(),'Reporting Parent')]");
	public static By FERROR_MSG=By.xpath("//div[@class='input-error' and contains(text(),'Please enter First Name')]");
	public static By LERROR_MSG=By.xpath("//div[@class='input-error' and contains(text(),'Please enter Last Name')]");
	public static By PhoneError_MSG=By.xpath("//div[@class='input-error' and contains(text(),'Please enter valid Phone Number')]");
	public static By LINKEDACCOUNTS_Defualtacc=By.xpath("//div[@id='defaultSoldToDetailsDivId']");
	public static By PREVIOUSPAGE=By.xpath("//a[contains(text(),' |<')]");

}
	
