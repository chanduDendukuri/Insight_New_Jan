package com.insight.WebTest.Cart;

import java.util.Hashtable;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.insight.Lib.CMTLib;
import com.insight.Lib.CanadaLib;
import com.insight.Lib.CartLib;
import com.insight.Lib.ChinaLib;
import com.insight.Lib.CommonLib;
import com.insight.Lib.OrderLib;
import com.insight.Lib.ProductDetailLib;
import com.insight.Lib.ProductDisplayInfoLib;
import com.insight.Lib.SearchLib;
import com.insight.Lib.ShipBillPayLib;
import com.insight.accelerators.ReportControl;
import com.insight.accelerators.TestEngineWeb;
import com.insight.googledrive.ReportStatus;
import com.insight.utilities.TestUtil;

public class CRT08_SaveCartIPSTest extends CartLib{
	CommonLib commonLib = new CommonLib();
	CMTLib cmtLib = new CMTLib();
	CartLib cartLib = new CartLib();
	ProductDisplayInfoLib prodInfoLib = new ProductDisplayInfoLib();
	SearchLib search = new SearchLib();
	CanadaLib canadaLib = new CanadaLib();
	 // #############################################################################################################
   // #    Name of the Test         : CRT08_SaveCartIPS
   // #    Migration Author         : Cigniti Technologies
   // #
   // #    Date of Migration        : August 2019
   // #    DESCRIPTION              : This method is to perform Basic Cart  operations.
   // #    Parameters               : StartRow ,EndRow , nextTestJoin
   // #
   // ###############################################################################################################	
	@Parameters({"StartRow","EndRow","nextTestJoin"})
	@Test
	public void TC_CRT08(int StartRow,String EndRow,boolean nextTestJoin) throws Throwable {
		int counter = 0;
					try {
						int intStartRow = StartRow;
						int intEndRow = ReportControl.fnGetEndRowCunt(EndRow, "CRT08_SaveCartIPS", TestDataInsight, "Web_Cart");
						for (int intCounter = intStartRow; intCounter <= intEndRow; intCounter++) {
							try {
								counter = intCounter;
								fnOpenTest();
								ReportStatus.fnDefaultReportStatus();
								ReportControl.intRowCount = intCounter;
								Hashtable<String, String> data = TestUtil.getDataByRowNo("CRT08_SaveCartIPS", TestDataInsight,
										"Web_Cart", intCounter);
								TestEngineWeb.reporter.initTestCaseDescription("SaveCartIPS");
					//cmtLib.loginToCMTSearchWebGrpAndUser(data.get("header"), data.get("WebGrp"), data.get("LnameEmailUname"), data.get("ContactName"));
					cmtLib.loginToCMT(data.get("header"));
					cmtLib.searchForWebGroup(data.get("WebGrp"));
					cmtLib.clickOnTheWebGroup(data.get("WebGrp_Name"));
					cmtLib.hoverOnManageWebGroupsAndSelectOptions(data.get("Manage_Web_Grp_Options"));
					cmtLib.searchForaUserAndSelect(data.get("LnameEmailUname"), data.get("ContactName"));
					
					cmtLib.setPermissions(data.get("menuName"), data.get("Enable_Purchasing_Popup"));
					cmtLib.setPermissions(data.get("menuName"), data.get("userPermission"));
					
					String mainWindow = parentWindow();
					cmtLib.clickOnloginAs();
					switchToWindow(mainWindow);	
					cmtLib.loginVerification(data.get("ContactName"));
					commonLib.clickOnAccountToolsAndClickOnProductGrp(data.get("Tools_Menu"),
							data.get("Tools_Menu_DD"));
					cartLib.deleteSavedCartFromAccountTools();
					commonLib.searchProduct(data.get("Search_Item"));
					search.verifyBreadCrumbInSearchResultsPage(data.get("Search_Item"));
					String searchItem=prodInfoLib.getPartNumberInSearchResultsPage();
					commonLib.addFirstDisplyedItemToCartAndVerify();
					canadaLib.continueToCheckout();
					canadaLib.verifyPlaceCartLabel();
					prodInfoLib.verifyCartPageAndPartDetailsForRecentlyItemDynamically(searchItem);
					
					String cartName = "QTPTestCart"+getRandomNumeric(5);
					cartLib.clickOnSaveCartContentAndSaveCart(cartName);
					cartLib.verifyCartIsEmpty();
					commonLib.clickCart();
					commonLib.verifyCartIsEMpty();
					
					search.selectContractInCartPage(data.get("Contract"));
					commonLib.searchProduct(data.get("Search_Item1"));
					search.verifyBreadCrumbInSearchResultsPage(data.get("Search_Item1"));
					String searchItem1=prodInfoLib.getPartNumberInSearchResultsPage();
					commonLib.addFirstDisplyedItemToCartAndVerify();
					canadaLib.continueToCheckout();
					canadaLib.verifyPlaceCartLabel();
					prodInfoLib.verifyCartPageAndPartDetailsForRecentlyItemDynamically(searchItem1);
					String cartName1 = "SavdCart"+getRandomNumeric(5);
					cartLib.clickOnSaveCartContentAndSaveCartAndClearCartOff(cartName1);
					
					commonLib.clickCart();
					commonLib.emptyCartAndVerify();
					cartLib.deleteSavedCartFromAccountTools();
					commonLib.clickLogOutLink(data.get("Logout_Header"));
					   System.out.println("Test completed");
		 				
							} catch (Exception e) {
								ReportStatus.blnStatus = false;
							//	gErrorMessage = e.getMessage();
								gTestStatus = false;
							}
						}
					} catch (Exception e) {
						e.printStackTrace();
						ReportStatus.blnStatus = false;
					//	gErrorMessage = e.toString();
						gTestStatus = false;
						ReportStatus.fnUpdateResultStatus("SaveCartIPS", "TC_CRT08", ReportStatus.strMethodName, 1, browser);
						throw new RuntimeException(e);
					}
	finally {
		ReportControl.fnEnableJoin();
		ReportStatus.fnUpdateResultStatus("SaveCartIPS", "TC_CRT08", ReportStatus.strMethodName, counter, browser);
		fnCloseTest();
		ReportControl.fnNextTestJoin(nextTestJoin);
	}
				}
				}