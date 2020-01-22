package com.insight.WebTest.Search;

import java.util.Hashtable;

import com.insight.Lib.*;
import com.insight.accelerators.ReportControl;
import com.insight.accelerators.TestEngineWeb;
import com.insight.googledrive.ReportStatus;
import com.insight.utilities.TestUtil;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class SER04_CompanyStandardsTest extends SearchLib {

	ProductDisplayInfoLib prodInfoLib = new ProductDisplayInfoLib();
	CMTLib cmtLib = new CMTLib();
	CommonLib commonLib=new CommonLib();
	OrderLib orderLib=new OrderLib();
	CartLib cartLib=new CartLib();

	    // #############################################################################################################
		// #    Name of the Test         : SER04_CompanyStandards
		// #    Migration Author         : Cigniti Technologies
	    // #
		// #    Date of Migration        : August 2019
		// #    Description              : This method is used to Test Company Standards.
		// #    Parameters               : StartRow ,EndRow , nextTestJoin
		// #
		// #############################################################################################################
	@Parameters({ "StartRow", "EndRow", "nextTestJoin" })
	@Test
	public void Tc_SER04(int StartRow, String EndRow, boolean nextTestJoin) throws Throwable {
		int counter=0;
		try {
			int intStartRow = StartRow;
			int intEndRow = ReportControl.fnGetEndRowCunt(EndRow, "SER04_CompanyStandards", TestData, "Web_Search");
			for (int intCounter = intStartRow; intCounter <= intEndRow; intCounter++) {
				try {
					// initializing libraries and testdata
					ReportStatus.fnDefaultReportStatus();
					ReportControl.intRowCount = intCounter;
					Hashtable<String, String> data = TestUtil.getDataByRowNo("SER04_CompanyStandards",
							TestData, "Web_Search", intCounter);
					TestEngineWeb.reporter.initTestCaseDescription("CompanyStandards");
					reporter.SuccessReport("Iteration Number : ","**************Iteration Number::  " + intCounter + " For:: " + data.get("LoginName")
									+ " ::and:: " + data.get("Password") + " To Validate::" + data.get("errorMessage")
									+ "  **************","");

					// Test Steps execution
					fnOpenTest();

					// Logging into CMT tool
					// Login to CMT enable Open Market and Contracts/Agencies
					// are enabled by default

					cmtLib.loginToCMT(data.get("Login"));
					cmtLib.searchForWebGroup(data.get("WebGrp"));
					cmtLib.clickOnTheWebGroup(data.get("WebGrp_Name"));
					cmtLib.hoverOnManageWebGroupsAndSelectOptions(data.get("Manage_Web_Grp_Options"));
					cmtLib.hoverOnManageWebGroupsAndSelectOptions(data.get("Manage_Web_Grp_Options1"));
					cmtLib.searchForaUserAndSelect(data.get("LnameEmailUname"), data.get("ContactName"));
					cmtLib.loginAsAdminCMT();
					cmtLib.loginVerification(data.get("ContactName"));

					// Company standards 
					orderLib.clickOnSideMenuSelectAccountToolOptions(data.get("Tools_Menu"), data.get("Tools_Menu_DD"));
					commonLib.clickOnProductGrpInCompanyStandard( data.get("Product_Group"), data.get("Product_Name"));
					// verify table columns
					verifyTheProductGroupTable(data.get("Product_Grp_Columns"));
					// Select first description and verify mini popup window
					selectDescriptionAndVerifyMiniPopupWindow();
					// Click on Required link
					commonLib.clickOnProductGrpInCompanyStandard( data.get("Product_Group2"), data.get("Product_Name2"));
					// verify table columns
					verifyTheProductGroupTable(data.get("Product_Grp_Columns"));
					// Add to order
					clickAddToOrderOnCompanyStandardsScreen();
					// verify cart page loaded
					cartLib.verifyCartBreadCrumb();
					cartLib.verifyProductGroupBundleAddedToCart(data.get("Product_Name2"));
					String summaryAmount=cartLib.getSummaryAmountInCart();
					if(summaryAmount!=null) {
						reporter.SuccessReport("Parts are Added to Cart on Product Standards Page ", "Parts are Added to Cart", "Cart : TotalUSD "+summaryAmount);
					}else {
						reporter.failureReport("Parts are Added to Cart on Product Standards Page ", "Parts are Added not added to Cart", "Cart : TotalUSD "+summaryAmount);
					}
					
					// Company standards >> IUSA Mandatory CTO Link 
					orderLib.clickOnSideMenuSelectAccountToolOptions(data.get("Tools_Menu"), data.get("Tools_Menu_DD"));
					commonLib.clickOnProductGrpInCompanyStandard( data.get("Product_Group3"), data.get("Product_Name3"));
					// verify table columns
					verifyTheProductGroupTable(data.get("Product_Grp_Columns"));
					verifyRadioButtonsSelected();
					Thread.sleep(3000);
					// SystemN Multiple CTO Link
					commonLib.clickOnProductGrpInCompanyStandard( data.get("Product_Group3"), data.get("Product_Name4"));
					// verify table columns
					verifyTheProductGroupTable(data.get("Product_Grp_Columns"));
					clickAddToOrderOnCompanyStandardsScreen();
					// verify cart page loaded
					cartLib.verifyCartBreadCrumb();
					
					String summaryAmount2=cartLib.getSummaryAmountInCart();
					if(summaryAmount2!=null) {
						reporter.SuccessReport("Parts are Added to Cart on Product Standards Page ", "Parts are Added to Cart", "Cart : TotalUSD "+summaryAmount2);
					}else {
						reporter.failureReport("Parts are Added to Cart on Product Standards Page ", "Parts are Added not added to Cart", "Cart : TotalUSD "+summaryAmount2);
					}
					// search part number : 516814-B21-AX -- 516814-B21-AX
					searchInHomePage(data.get("part_Number"));
					prodInfoLib.verifyTheManufacturerNumberInProductDetailsPage(data.get("part_Number"));
					String[] checkbox=data.get("Checkboxes").split(",");
					clickAddToCompanyStandardsLink();
					Thread.sleep(3000);
					verifyConfigurationSetsPopup();
					clickCancelOnSelectConfigurationSetPopup();
					Thread.sleep(3000);
					clickAddToCompanyStandardsLink();
					verifyConfigurationSetsPopup();
					for(i=0;i<checkbox.length;i++) {
						clickConfigurationSetsCheckboxs(checkbox[i]);
					}
					Thread.sleep(3000);
					clickAddButtonOnSelectConfigurationSetPopup();
					commonLib.clickLogOutLink(data.get("Logout"));
					

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
			ReportStatus.fnUpdateResultStatus("CompanyStandards", "SER04", ReportStatus.strMethodName,
					1, browser);
			throw new RuntimeException(e);
		}

		finally {
        	ReportControl.fnEnableJoin();
			ReportStatus.fnUpdateResultStatus("CompanyStandards", "SER04", ReportStatus.strMethodName, counter, browser);
			//fnCloseTest();
			ReportControl.fnNextTestJoin(nextTestJoin);
		}
	}

}
