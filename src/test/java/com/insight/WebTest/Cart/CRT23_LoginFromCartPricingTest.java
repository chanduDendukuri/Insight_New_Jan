package com.insight.WebTest.Cart;

import java.util.Hashtable;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.insight.Lib.CMTLib;
import com.insight.Lib.CanadaLib;
import com.insight.Lib.CartLib;
import com.insight.Lib.ChinaLib;
import com.insight.Lib.CommonLib;
import com.insight.Lib.LineLevelInfoLib;
import com.insight.Lib.OrderLib;
import com.insight.Lib.ProductDetailLib;
import com.insight.Lib.ProductDisplayInfoLib;
import com.insight.Lib.SearchLib;
import com.insight.Lib.ShipBillPayLib;
import com.insight.accelerators.ReportControl;
import com.insight.accelerators.TestEngineWeb;
import com.insight.googledrive.ReportStatus;
import com.insight.utilities.TestUtil;

public class CRT23_LoginFromCartPricingTest extends CartLib{
	CommonLib commonLib = new CommonLib();
	CMTLib cmtLib = new CMTLib();
	CartLib cartLib = new CartLib();
	CanadaLib canadaLib = new CanadaLib();
	SearchLib searchLib = new SearchLib();
	ProductDisplayInfoLib prodInfoLib = new ProductDisplayInfoLib();
	LineLevelInfoLib lineLevelLib=new LineLevelInfoLib();
	// #############################################################################################################
    // #    Name of the Test         : CRT23_LoginFromCartPricing
    // #    Migration Author         : Cigniti Technologies
    // #
    // #    Date of Migration        : August 2019
    // #    DESCRIPTION              : This method is to perform Basic Cart  operations.
    // #    Parameters               : StartRow ,EndRow , nextTestJoin
    // #
    // ###############################################################################################################	
	@Parameters({"StartRow","EndRow","nextTestJoin"})
	@Test
	public void Tc_CRT23(int StartRow,String EndRow,boolean nextTestJoin) throws Throwable {
		
					int counter = 0;
					try {
						int intStartRow = StartRow;
						int intEndRow = ReportControl.fnGetEndRowCunt(EndRow, "CRT23_LoginFromCartPricing", TestDataInsight,
								"Web_Cart");
						for (int intCounter = intStartRow; intCounter <= intEndRow; intCounter++) {
							try {
								counter = intCounter;
								fnOpenTest();
								ReportStatus.fnDefaultReportStatus();
								ReportControl.intRowCount = intCounter;
								Hashtable<String, String> data = TestUtil.getDataByRowNo("CRT23_LoginFromCartPricing", TestDataInsight,
										"Web_Cart", intCounter);
								TestEngineWeb.reporter.initTestCaseDescription("LoginFromCartPricing");
					//cmtLib.loginAsEndUserInMainPage(data.get("Header"),data.get("User_Name"),data.get("Password"));
					commonLib.searchProduct(data.get("Search_Item"));
					searchLib.verifyBreadCrumbInSearchResultsPage(data.get("Search_Item"));
					prodInfoLib.getPartNumberInSearchResultsPage();
					commonLib.addFirstDisplyedItemToCartAndVerify();
					//commonLib.addToCartAndVerify();
					//String priceInLogin=cartLib.getTotalPriceInSearchResults();
					String priceInLogin=prodInfoLib.getFirtProductListPrice();
					canadaLib.continueToCheckout();
					commonLib.clickCart();
					//cartLib.verifyItemInCart(data.get("Search_Item"));
					canadaLib.verifyPlaceCartLabel();
					prodInfoLib.verifyCartPageAndPartDetailsForRecentlyItem();
					String summaryAmountInLogin=cartLib.getSummaryAmountInCart();
					if(priceInLogin.equalsIgnoreCase(summaryAmountInLogin)) {
						
					}
					commonLib.emptyCartAndVerify();
					commonLib.clickLogOutLink(data.get("Logout_Header"));
					commonLib.searchProduct(data.get("Search_Item"));
					commonLib.addToCartAndVerify();
					String priceWithoutLogin=cartLib.getTotalPrice();
					//commonLib.clickCart();
					canadaLib.continueToCheckout();
					cartLib.verifyItemInCart(data.get("Search_Item"));
					String summaryAmountWithoutLogin=cartLib.getSummaryAmountInCart();
					System.out.println("summaryAmountInLogin"+summaryAmountInLogin);
					System.out.println("summaryAmountWithoutLogin"+summaryAmountWithoutLogin);
					cartLib.VerifyLoginPriceAndNonLoginPrice(priceInLogin,priceWithoutLogin);
					cartLib.verifySummaryPriceInLoginAndNonLogin(summaryAmountInLogin,summaryAmountWithoutLogin);
					System.out.println("Test completed");

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
						ReportStatus.fnUpdateResultStatus("LoginFromCartPricing", "Tc_CRT23", ReportStatus.strMethodName, 1, browser);
						throw new RuntimeException(e);
					} finally {
						ReportControl.fnEnableJoin();
						ReportStatus.fnUpdateResultStatus("LoginFromCartPricing", "Tc_CRT23", ReportStatus.strMethodName, counter,
								browser);
						fnCloseTest();
						ReportControl.fnNextTestJoin(nextTestJoin);
					}
				}
			}
