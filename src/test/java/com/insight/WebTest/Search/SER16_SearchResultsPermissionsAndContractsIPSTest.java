package com.insight.WebTest.Search;

import java.util.Hashtable;

import com.insight.Lib.*;
import com.insight.accelerators.ReportControl;
import com.insight.accelerators.TestEngineWeb;
import com.insight.googledrive.ReportStatus;
import com.insight.utilities.TestUtil;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class SER16_SearchResultsPermissionsAndContractsIPSTest extends SearchLib {

	ProductDisplayInfoLib prodInfoLib = new ProductDisplayInfoLib();
	CMTLib cmtLib = new CMTLib();
	CommonLib commonLib = new CommonLib();
	CartLib cartLib=new CartLib();
	OrderLib orderLib=new OrderLib();

	    // #############################################################################################################
	 	// #    Name of the Test         : SER16_SearchResults-PermissionsAndContractsIPS
	 	// #    Migration Author         : Cigniti Technologies
	    // #
	 	// #    Date of Migration        : August 2019
	 	// #    Description              : Web - Search - Logged in IPS - default permissions
	 	// #    Parameters               : StartRow ,EndRow , nextTestJoin
	 	// #
	 	// ############################################################################################################# 

	@Parameters({ "StartRow", "EndRow", "nextTestJoin" })
	@Test
	public void Tc_SER16(int StartRow, String EndRow, boolean nextTestJoin) throws Throwable {
		int counter=0;
		try {
			int intStartRow = StartRow;
			int intEndRow = ReportControl.fnGetEndRowCunt(EndRow, "SER16_SearchResultsPermissionsAndContractsIPS",
					TestData, "Web_Search");
			for (int intCounter = intStartRow; intCounter <= intEndRow; intCounter++) {
				try {
					// initializing libraries and testdata
					ReportStatus.fnDefaultReportStatus();
					ReportControl.intRowCount = intCounter;
					Hashtable<String, String> data = TestUtil.getDataByRowNo(
							"SER16_SearchResultsPermissionsAndContractsIPS", TestData, "Web_Search",
							intCounter);
					TestEngineWeb.reporter.initTestCaseDescription("SearchResultsPermissionsAndContractsIPS");
					
					// Test Steps execution
					fnOpenTest();
					cmtLib.loginToCMT(data.get("Login"));
					cmtLib.searchForWebGroup(data.get("WebGrp"));
					cmtLib.clickOnTheWebGroup(data.get("WebGrp_Name"));
					cmtLib.hoverOnManageWebGroupsAndSelectOptions(data.get("Manage_Web_Grp_Options"));
					cmtLib.searchForaUserAndSelect(data.get("LnameEmailUname"), data.get("ContactName"));
					String[] permissions1=data.get("Set_Permission1").split(",");
					for (i = 0; i < permissions1.length; i++) { 
					cmtLib.setPermissions(data.get("Menu_Name"),data.get("Set_Permission1") );
					}
					cmtLib.permissionFromDD(data.get("Set_Permission3"), data.get("Permission_Drop_Down"));
					// Remove us comm default OFF
					cmtLib.setPermissionsToDisable(data.get("Menu_Name"), data.get("Set_Permission2"));
					// Login to CMT
					cmtLib.loginAsAdminCMT();
					cmtLib.loginVerification(data.get("ContactName"));

					// Back to UAT and verify the above enabled settings
					verifyContractAllDisplayed();
					// workstations
					searchInHomePage(data.get("SearchText"));
					verifyTheResultsForSearchTerm(data.get("SearchText"));
					prodInfoLib.getPartNumberInSearchResultsPage();
					clickOnMorePrices();
					allContractPricesPopup();
					verifyDefaultUSContractInAllContractPricesPopup("checked");
					// STATE OF MINNESOTA - PC HARDWARE, & SERVICES-
					selectContractOnAllContractPricesPopup(data.get("Contarct_Name1"));
					increaseQuantity(data.get("Contract_Quantity"));
					cartLib.clickOnAddToCartInAllContractPrices();
					orderLib.continueToCheckOutOnAddCart();
					// contract verification in cart page
					prodInfoLib.verifyContractInCartScreen(data.get("Contarct_Name1"));
					prodInfoLib.verifyCartPageAndPartDetails();
					prodInfoLib.verifyContractInCartScreen(data.get("Contarct_Name1"));
					commonLib.clickLogOutLink(data.get("Logout"));
					
					// Remove us comm default ON
					// navigate back to cmt
					cmtLib.navigateBackToCMT();
					cmtLib.searchForaUserAndSelect(data.get("LnameEmailUname"), data.get("ContactName"));
					for (i = 0; i < permissions1.length; i++) { 
						cmtLib.setPermissions(data.get("Menu_Name"),permissions1[i] );
						}
					cmtLib.permissionFromDD(data.get("Set_Permission3"), data.get("Permission_Drop_Down2"));
					cmtLib.setPermissions(data.get("Menu_Name"), data.get("Set_Permission2"));
					
					// Login to CMT
					cmtLib.loginAsAdminCMT();
					cmtLib.loginVerification(data.get("ContactName"));

					// Back to UAT and verify the above enabled settings
					verifyContractAllDisplayed();
					// Workstation
					searchInHomePage(data.get("SearchText"));
					verifyTheResultsForSearchTerm(data.get("SearchText"));
					prodInfoLib.getPartNumberInSearchResultsPage();
					prodInfoLib.contractNameOfFirstproduct();
					// more prices
					clickOnMorePrices();
					
					verifyDefaultUSContractInAllContractPricesPopup("unchecked");
					allContractPricesPopup();
					// your price in popup should print seperately   --- pending lakshmi  ##########################
					
					
					verifydefaultcontractonAllcontractpopup(data.get("Contarct_Name1"));
					commonLib.clickLogOutLink(data.get("Logout"));
					
					
					// navigate back to cmt
					cmtLib.navigateBackToCMT();
					cmtLib.searchForaUserAndSelect(data.get("LnameEmailUname"), data.get("ContactName"));
					// Open market - OFF
					cmtLib.setPermissionsToDisable(data.get("Menu_Name"), data.get("Permission"));
					//cmtLib.setPermissions(data.get("Menu_Name"), data.get("Set_Permission1"));
					// Login to CMT
					cmtLib.loginAsAdminCMT();
					cmtLib.loginVerification(data.get("ContactName"));
					
					// Back to UAT and verify the above enabled settings
					verifyContractAllDisplayed();
					// workstation
					searchInHomePage(data.get("SearchText"));
					verifyTheResultsForSearchTerm(data.get("SearchText"));
					prodInfoLib.getPartNumberInSearchResultsPage();
					prodInfoLib.contractNameOfFirstproduct();
					// more prices
					clickOnMorePrices();
					
					// verify Your price is not available in popup -- pending       ##########################
					commonLib.clickLogOutLink(data.get("Logout"));
					
					
					// navigate back to cmt
					cmtLib.navigateBackToCMT();
					cmtLib.searchForaUserAndSelect(data.get("LnameEmailUname"), data.get("ContactName"));
					// open mark on
					cmtLib.setPermissions(data.get("Menu_Name"), data.get("Permission"));
					cmtLib.setPermissionsToDisable(data.get("Menu_Name"), data.get("Set_Permission4"));
					
					// Login to CMT
					cmtLib.loginAsAdminCMT();
					cmtLib.loginVerification(data.get("ContactName"));

					// Back to UAT and verify the above enabled settings
					// Workstation
					searchInHomePage(data.get("SearchText"));
					verifyTheResultsForSearchTerm(data.get("SearchText"));
					prodInfoLib.getPartNumberInSearchResultsPage();
					
					// open market price label and part number verification in search results page for all the products in search results page
					
					
					commonLib.clickLogOutLink(data.get("Logout"));
					
					// navigate back to cmt
					cmtLib.navigateBackToCMT();
					cmtLib.searchForaUserAndSelect(data.get("LnameEmailUname"), data.get("ContactName"));
					// open mark , contract agencies ON
					cmtLib.setPermissions(data.get("Menu_Name"), data.get("Permission"));
					cmtLib.setPermissions(data.get("Menu_Name"), data.get("Set_Permission4"));
					

				}

				catch (Exception e) {
					ReportStatus.blnStatus = false;
					//gErrorMessage = e.getMessage();
					gTestStatus = false;
				}
				
			}
		} catch (Exception e) {
			e.printStackTrace();
			ReportStatus.blnStatus = false;
			//gErrorMessage = e.getMessage();
			gTestStatus = false;
			ReportStatus.fnUpdateResultStatus("SearchResultsPermissionsAndContractsIPS", "SER16", ReportStatus.strMethodName,
					1, browser);
			throw new RuntimeException(e);
		}

		finally {
        	ReportControl.fnEnableJoin();
			ReportStatus.fnUpdateResultStatus("SearchResultsPermissionsAndContractsIPS", "SER16", ReportStatus.strMethodName, counter, browser);
			fnCloseTest();
			ReportControl.fnNextTestJoin(nextTestJoin);
		}
	}

}
