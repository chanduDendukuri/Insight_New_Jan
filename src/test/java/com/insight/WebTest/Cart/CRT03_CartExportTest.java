package com.insight.WebTest.Cart;

import java.util.Hashtable;

import com.insight.Lib.*;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.insight.accelerators.ReportControl;
import com.insight.accelerators.TestEngineWeb;
import com.insight.googledrive.ReportStatus;
import com.insight.utilities.TestUtil;

public class CRT03_CartExportTest extends CartLib{
	CommonLib commonLib = new CommonLib();	
	CartLib cartLib = new CartLib();
	CMTLib cmtLib = new CMTLib();
	OrderLib orderLib=new OrderLib();
	SearchLib searchLib=new SearchLib();
	ProductDisplayInfoLib prodinfo =  new ProductDisplayInfoLib();
	CommonCanadaLib ccp =  new CommonCanadaLib();
	CanadaLib canadaLib =  new CanadaLib();

	 // #############################################################################################################
    // #    Name of the Test         : CRT03_CartExport
    // #    Migration Author         : Cigniti Technologies
    // #
    // #    Date of Migration        : August 2019
    // #    DESCRIPTION              : This method is to perform search operations in the Product Research Request page.
    // #    Parameters               : StartRow ,EndRow , nextTestJoin
    // #
    // ###############################################################################################################	
    @Parameters({"StartRow","EndRow","nextTestJoin"})
	@Test
	public void Tc_CRT03(int StartRow,String EndRow,boolean nextTestJoin) throws Throwable {
		

					int counter = 0;
					try {
						int intStartRow = StartRow;
						int intEndRow = ReportControl.fnGetEndRowCunt(EndRow, "CRT03_CartExport", TestDataInsight, "Web_Cart");
						for (int intCounter = intStartRow; intCounter <= intEndRow; intCounter++) {
							try {
								counter = intCounter;
								fnOpenTest();
								ReportStatus.fnDefaultReportStatus();
								ReportControl.intRowCount = intCounter;
								Hashtable<String, String> data = TestUtil.getDataByRowNo("CRT03_CartExport", TestDataInsight,
										"Web_Cart", intCounter);
								TestEngineWeb.reporter.initTestCaseDescription("CartExport");
		//***********************************************New CODE********************************************************
								cmtLib.loginToCMT(data.get("header"));
								cmtLib.searchForWebGroup(data.get("WebGrp"));
								cmtLib.clickOnTheWebGroup(data.get("WebGrp_Name"));
								//cmtLib.hoverOnManageWebGroupsAndSelectOptions(data.get("Manage_Web_Grp_Options"));
								cmtLib.hoverOnManageWebGroupsAndSelectOptions(data.get("Manage_Web_Grp_Options1"));
								cmtLib.searchForaUserAndSelect(data.get("LnameEmailUname"), data.get("ContactName"));
								cmtLib.loginAsAdminCMT();
								cmtLib.loginVerification(data.get("ContactName"));
								commonLib.searchProduct(data.get("SearchItem1"));
								//String aa =prodinfo.getPartNumberInSearchResultsPage();

								prodinfo.clickOnWarrantiesTabOnProductDetailsPage();
								ccp.clickOnAddToCartButtonUnderWarrentyDynamically();
								String man1=cartLib.getPartNumber();

								assertTrue(ccp.verifyAddToCartLabelAvailable(),"View Cart page loaded");
								canadaLib.continueToCheckout();
								//orderLib.continueToCheckOutAddWarrantyAndVerifyWarrentyInCart(data.get("SearchItem1"),data.get("Warrenty_Part_Number"));
								prodinfo.verifyCartPageAndPartDetailsForRecentlyItemDynamically(man1);

								commonLib.searchProduct(data.get("SearchItem2"));
							//	prodinfo.getPartNumberInSearchResultsPage();
								prodinfo.clickOnWarrantiesTabOnProductDetailsPage();
								/*String manf=prodinfo.getManfNumberFromWarrentiesPage(data.get("index"));
								//String manf=prodinfo.getManfNumberFromWarrentiesPage(data.get("index"));
								String[] partn10 = manf.split(" | ");
								String partNum1 =partn10[3] ;
*/
								ccp.clickOnAddToCartButtonUnderWarrentyDynamically();
								String man=cartLib.getPartNumber();
								assertTrue(ccp.verifyAddToCartLabelAvailable(),"View Cart page loaded");
								canadaLib.continueToCheckout();
								//orderLib.continueToCheckOutAddWarrantyAndVerifyWarrentyInCart(data.get("SearchItem2"),data.get("Warrenty_Part_Number"));
								prodinfo.verifyCartPageAndPartDetailsForRecentlyItemDynamically(man);

								commonLib.searchProduct(data.get("SearchItem3"));
								//prodinfo.getPartNumberInSearchResultsPage();
								prodinfo.clickOnWarrantiesTabOnProductDetailsPage();
								ccp.clickOnAddToCartButtonUnderWarrentyDynamically();
								String man2=cartLib.getPartNumber();
								assertTrue(ccp.verifyAddToCartLabelAvailable(),"View Cart page loaded");
								canadaLib.continueToCheckout();
								//orderLib.continueToCheckOutAddWarrantyAndVerifyWarrentyInCart(data.get("SearchItem3"),data.get("Warrenty_Part_Number"));
								prodinfo.verifyCartPageAndPartDetailsForRecentlyItemDynamically(man2);





								ccp.clickOnExportToExcellink();
								cartLib.verifyExportFile(data.get("Sheet_Name"),data.get("Row_number"),data.get("Column_Headers"));

								driver.navigate().refresh();
								ccp.clickOnEmptyCart();
								commonLib.verifyCartIsEMpty();
								canadaLib.clickOnSideMenuSelectAccountToolOptions(data.get("Tools_Menu"),
										data.get("Tools_Menu_DD1"));
								assertTrue(ccp.verifyCompanyStandard(),"Product standard page is available");
								ccp.clickOnIUSAMandatoryCTOlink();
								ccp.clickOnAddToOrderButton();
								ccp.clickOnViewToCartlink();
								cartLib.verifyCartPageAvailablity();
								ccp.clickOnExportToExcellink();
								cartLib.verifyExportFile(data.get("Sheet_Name"),data.get("Row_number"),data.get("Column_Headers"));
								cmtLib.clickOnLogoutlink();
					Thread.sleep(5000);
				} catch (Exception e) {
					ReportStatus.blnStatus = false;
					//gErrorMessage = e.getMessage();
					gTestStatus = false;
				}
				ReportControl.fnEnableJoin();
				ReportStatus.fnUpdateResultStatus("CartExport", "CRT03", ReportStatus.strMethodName, intCounter, browser);
				fnCloseTest();
			}
		} catch (Exception e) {
			e.printStackTrace();
			ReportStatus.blnStatus = false;
			//gErrorMessage = e.getMessage();
			gTestStatus = false;
			ReportStatus.fnUpdateResultStatus("CartExport", "CRT03", ReportStatus.strMethodName, 1, browser);
			throw new RuntimeException(e);
		}

					  finally {
					    	ReportControl.fnEnableJoin();
							ReportStatus.fnUpdateResultStatus("CartBasicIPS", "Tc_CRT02", ReportStatus.strMethodName, counter, browser);
							fnCloseTest();
							ReportControl.fnNextTestJoin(nextTestJoin);
						}
	}
}
	
