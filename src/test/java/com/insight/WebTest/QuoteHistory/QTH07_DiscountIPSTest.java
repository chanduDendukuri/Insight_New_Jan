package com.insight.WebTest.QuoteHistory;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.insight.Lib.CMTLib;
import com.insight.Lib.CanadaLib;
import com.insight.Lib.CartLib;
import com.insight.Lib.CommonLib;
import com.insight.Lib.OrderLib;
import com.insight.Lib.QuoteHistoryLib;
import com.insight.Lib.SearchLib;
import com.insight.accelerators.ReportControl;
import com.insight.accelerators.TestEngineWeb;
import com.insight.googledrive.ReportStatus;
import com.insight.utilities.TestUtil;

public class QTH07_DiscountIPSTest extends QuoteHistoryLib {
	// #############################################################################################################
	// # Name of the Test :DiscountIPS
	// # Migration Author : Cigniti Technologies
	// #
	// # Date of Migration : OCT 2019
	// # DESCRIPTION : This method is to perform Quote History search with date
	// operations.
	// # Parameters : StartRow ,EndRow , nextTestJoin
	// #
	// ###############################################################################################################
	@Parameters({ "StartRow", "EndRow", "nextTestJoin" })
	@Test
	public void TC_QTH07(int StartRow, String EndRow, boolean nextTestJoin) throws Throwable {
		
		int counter = 0;
		try {
								
					int intStartRow = StartRow;
					int intEndRow = ReportControl.fnGetEndRowCunt(EndRow, "QTH07_DiscountIPS", TestDataInsight, "Quote_History");
					for (int intCounter = intStartRow; intCounter <= intEndRow; intCounter++) {
						try {
							
							counter = intCounter;
							fnOpenTest();
							ReportStatus.fnDefaultReportStatus();
							ReportControl.intRowCount = intCounter;
							Hashtable<String, String> data = TestUtil.getDataByRowNo("QTH07_DiscountIPS", TestDataInsight, "Quote_History", intCounter);
							TestEngineWeb.reporter.initTestCaseDescription("DiscountIPS");

							List<String> quotedetails = new ArrayList<>();
							List<String> orderdetails = new ArrayList<>();
					CMTLib cmtLib = new CMTLib();
					CommonLib commonLib = new CommonLib();
					SearchLib searchLib = new SearchLib();
					CartLib cartLib = new CartLib();
					OrderLib orderLib = new OrderLib();
					CanadaLib canadaLib=new CanadaLib();
					cmtLib.loginToCMTSearchWebGrpAndUser(data.get("Header"), data.get("WebGrp"),
							data.get("LnameEmailUname"), data.get("ContactName"));

					// Enable Quotes Check Box
					cmtLib.setPermissions(data.get("Menu_Name"), data.get("Set_Permission"));
					cmtLib.setPermissions(data.get("Menu_Name"), data.get("Set_Permission1"));
					cmtLib.setPermissions(data.get("Menu_Name"), data.get("Set_Permission2"));
					cmtLib.setPermissionsToDisable(data.get("Menu_Name"), data.get("Set_Permission2"));
					// Disable -- quote_track_only_my_quotes;OFF
					//cmtLib.setPermissionsToDisable(data.get("Menu_Name"), data.get("Disable_Permission"));

					// Enable purchasing popup
					//cmtLib.setPermissions(data.get("Menu_Name"), data.get("Set_Permission_Msg"));

					cmtLib.clickOnloginAs();
					switchToChildWindow();
					searchLib.selectContractInCartPage(data.get("Contract"));

					// Search For the Product and Add to Cart
					searchLib.searchInHomePage(data.get("SearchItem"));

					// Click on First Product
					cartLib.selectFirstProductDisplay();

					// Verify Discount and Standard Price
					String StandardPrice = verifyStandardPrice();
					verifyDiscount();

					// Get the Regular Price
					String discountPrice = getDiscountPrice();

					validateStandardDiscountPrice(discountPrice, StandardPrice);

					// Click Add To Order

					commonLib.addToCartAndVerify();
					orderLib.continueToCheckOutOnAddCart();

					// Verify Cart Page is Loaded
					orderLib.verifyCartHeaderLabel();

					// Select Contract - 3
					searchLib.selectContractInCartPageforTcQTH07(data.get("Contract3"));

					// Search item
					searchLib.searchInHomePage(data.get("SearchItem3"));
					// Click on First Product
					cartLib.selectFirstProductDisplay();

					// Click Add To Order
					commonLib.addToCartAndVerify();
					orderLib.continueToCheckOutOnAddCart();
					cmtLib.clickSaveAsQuote();	
					 
					quotedetails = cmtLib.getQuoteNameandReferenceNumber();
					
					commonLib.clickOnAccountToolsAndClickOnProductGrp(data.get("Tools_Menu1"),
							data.get("Tools_Menu_DD1"));
					verifyQuoteHistoryPageOpened();
					quoteNumberInTable(quotedetails.get(0));ClickFirstQuoteNumber();
					verifyMSRPPrice();
					verifyDiscountPrice();
					orderLib.convertQuote();
					cartLib.verifyCartBreadCrumb();

					// Proceed to checkout
					orderLib.proceedToCheckout();
					EntervalidEmail(data.get("Email"));
					orderLib.clickContinueOnLLIAndShipBillPaySections();
					orderLib.addNewCardInPayment(data.get("cardNumber"), data.get("cardName"), data.get("month"),
							data.get("year"), data.get("poNumber"),data.get("POReleaseNumber"));
					orderLib.clickOnReviewOrderButton();
					
					orderdetails=orderLib.placeOrderAndVerifyReceiptOrderAndDate("$9,279.30");
					commonLib.clickOnAccountToolsAndClickOnProductGrp(data.get("Tools_Menu1"),
							data.get("Tools_Menu_DD2"));
					String[] RN = (orderdetails.get(0)).split(" ");
					selectadvancedOrdersearchoption("Reference number",RN[0]);
					
					commonLib.clickLogOutLink(data.get("Logout_Header"));
					
					
					
					
					
					

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
			ReportStatus.fnUpdateResultStatus("DiscountIPS", "TC_QTH07", ReportStatus.strMethodName, 1, browser);
			throw new RuntimeException(e);
		}
        finally {
        	ReportControl.fnEnableJoin();
			ReportStatus.fnUpdateResultStatus("DiscountIPS", "TC_QTH07", ReportStatus.strMethodName, counter, browser);
			fnCloseTest();
			ReportControl.fnNextTestJoin(nextTestJoin);
		}
	}

}