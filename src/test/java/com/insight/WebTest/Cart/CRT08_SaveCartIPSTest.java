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
					cmtLib.loginToCMTSearchWebGrpAndUser(data.get("header"), data.get("WebGrp"), data.get("LnameEmailUname"), data.get("ContactName"));
					cmtLib.setPermissions(data.get("menuName"),data.get("userPermission"));
					cmtLib.setPermissions(data.get("menuName"),data.get("Enable_Purchasing_Popup"));
					String mainWindow = parentWindow();
					cmtLib.clickOnloginAs();
					switchToWindow(mainWindow);	
					cartLib.verifyCartIsEmpty();
					commonLib.searchProduct(data.get("Search_Item"));
					commonLib.addFirstDisplyedItemToCartAndVerify();
					commonLib.continueToShopping();
					commonLib.searchProduct(data.get("Search_Item"));
					cartLib.clickMorePricesAvilable(1);
					cartLib.clickOnOpenMarketPrice();
					cartLib.clickOnAddToCartInAllContractPrices();
					commonLib.clickCart();
					String cartName=getRandomString(5)+'@';
					cartLib.clickOnSaveCartContentAndSaveCart(cartName);
					commonLib.clickCart();
					commonLib.verifyCartIsEMpty();
					cartLib.openSavedCartFromTools(cartName);
					cartLib.deleteCartFromAccountTools(cartName);
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