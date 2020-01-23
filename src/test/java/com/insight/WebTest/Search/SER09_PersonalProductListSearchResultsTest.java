package com.insight.WebTest.Search;

import java.util.Hashtable;

import com.insight.Lib.*;
import com.insight.accelerators.ReportControl;
import com.insight.accelerators.TestEngineWeb;
import com.insight.googledrive.ReportStatus;
import com.insight.utilities.TestUtil;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class SER09_PersonalProductListSearchResultsTest extends SearchLib {

	ProductDisplayInfoLib prodInfoLib = new ProductDisplayInfoLib();
	ProductDetailLib prodDetailsLib=new ProductDetailLib();
	CMTLib cmtLib = new CMTLib();
	CartLib cartLib=new CartLib();
	OrderLib orderLib=new OrderLib();
	CommonLib commonLib=new CommonLib();
 
	    // ############################################################################################################
		// #    Name of the Test         : SER09_PersonalProductListSearchResults
		// #    Migration Author         : Cigniti Technologies
	    // #
		// #    Date of Migration        : August 2019
		// #    Description              : This Test is used to Test  Product Center Search Results
		// #    Parameters               : StartRow ,EndRow , nextTestJoin
		// #
		// #############################################################################################################

	@Parameters({ "StartRow", "EndRow", "nextTestJoin" })
	@Test
	public void Tc_SER09(int StartRow, String EndRow, boolean nextTestJoin) throws Throwable {
		int counter=0;
		try {
			int intStartRow = StartRow;
			int intEndRow = ReportControl.fnGetEndRowCunt(EndRow, "SER09_PersonalProductListSearchResults", TestData,
					"Web_Search");
			for (int intCounter = intStartRow; intCounter <= intEndRow; intCounter++) {
				try {
					// initilizing libraries and testdata
					ReportStatus.fnDefaultReportStatus();
					ReportControl.intRowCount = intCounter;
					Hashtable<String, String> data = TestUtil.getDataByRowNo(
							"SER09_PersonalProductListSearchResults", TestData, "Web_Search", intCounter);
					TestEngineWeb.reporter.initTestCaseDescription("PersonalProductListSearchResults");

					// Test Steps execution
					fnOpenTest();
					// search Workstations
					searchInHomePage(data.get("ProductType"));
					verifyBreadCrumbInSearchResultsPage(data.get("ProductType"));
					// Add to Personal product list link should not display.
					cartLib.selectFirstProductDisplay();
					prodInfoLib.verifyPersonalProductListLinkNotPresent();

					cmtLib.loginToCMT(data.get("Login"));
					cmtLib.searchForWebGroup(data.get("WebGrp"));
					cmtLib.clickOnTheWebGroup(data.get("WebGrp_Name"));
					cmtLib.hoverOnManageWebGroupsAndSelectOptions(data.get("Manage_Web_Grp_Options"));
					cmtLib.searchForaUserAndSelect(data.get("LnameEmailUname"), data.get("ContactName"));
					// 
					cmtLib.setPermissions(data.get("Menu_Name"), data.get("Set_Permission1"));
					cmtLib.setPermissionsToDisable(data.get("Menu_Name"), data.get("Set_Permission2"));
					// Login to CMT
					cmtLib.loginAsAdminCMT();
					cmtLib.loginVerification(data.get("ContactName"));
					
					// search Workstations
					searchInHomePage(data.get("ProductType"));
					verifyBreadCrumbInSearchResultsPage(data.get("ProductType"));
					
					String prodDesc=prodInfoLib.getFirstProdDescription();
					Thread.sleep(3000);
					cartLib.selectFirstProductDisplay();
					Thread.sleep(3000);
					// Verifying short description on product details page
					prodInfoLib.verifyShortDescriptionOnProductDetailsPage(prodDesc);
					String partNumber=prodDetailsLib.getMFRNumberInProductInfopage();
					if(partNumber!=null) {
						reporter.SuccessReport("Verify Products Details Page", "Product Details page Exists", "part Number : "+partNumber);
					}else {
						reporter.failureReport("Verify Products Details Page", "Product Details page Exists", "part Number : "+partNumber, driver);
					}
					prodInfoLib.selectProductAndverifyPersonalProductListLinkPresent();
					prodInfoLib.ClickAddedItemsToPersonalProductList();
					prodInfoLib.verifyManufacturerPartInPersonalListPage(partNumber);
					//cartLib.selectFirstProductDisplay();
					prodInfoLib.addItemsToProductList(data.get("Part_Number"));
					prodInfoLib.addToCartAndVerify(data.get("Part_Number"));
					orderLib.clickOnSideMenuSelectAccountToolOptions(data.get("Tools_Menu"), data.get("Tools_Menu_DD"));
					prodInfoLib.deleteItemFromPersonalizedList();
					prodInfoLib.deleteItemFromPersonalizedList();
					prodInfoLib.verifyPersonalizedListEmpyMessagePresent();
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
			ReportStatus.fnUpdateResultStatus("PersonalProductListSearchResults", "SER09", ReportStatus.strMethodName,
					1, browser);
			throw new RuntimeException(e);
		}

		finally {
        	ReportControl.fnEnableJoin();
			ReportStatus.fnUpdateResultStatus("PersonalProductListSearchResults", "SER09", ReportStatus.strMethodName, counter, browser);
			fnCloseTest();
			ReportControl.fnNextTestJoin(nextTestJoin);
		}
	}

}
