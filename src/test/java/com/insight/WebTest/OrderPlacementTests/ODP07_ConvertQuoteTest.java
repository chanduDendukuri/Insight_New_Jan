package com.insight.WebTest.OrderPlacementTests;

import com.insight.Lib.*;
import com.insight.accelerators.ReportControl;
import com.insight.accelerators.TestEngineWeb;
import com.insight.googledrive.ReportStatus;
import com.insight.utilities.TestUtil;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.util.Hashtable;
import java.util.List;

public class ODP07_ConvertQuoteTest extends OrderLib{

	ProductDisplayInfoLib prodInfoLib = new ProductDisplayInfoLib();
	CMTLib cmtLib = new CMTLib();
	SearchLib searchLib = new SearchLib();
	CommonLib commonLib = new CommonLib();
	CartLib cartLib = new CartLib();
	OrderLib orderLib =new OrderLib();
	CanadaLib canadaLib=new CanadaLib();
	ProductDetailLib prodLib=new ProductDetailLib();
	LineLevelInfoLib lnlLib=new LineLevelInfoLib();

	// #############################################################################################################
	// #    Name of the Test         : ODP07_ConvertQuote
	// #    Migration Author         : Cigniti Technologies
	// #
	// #    Date of Migration        : September 2019
	// #    Description              : To Test Place Order  Confirmations
	// #    Parameters               : StartRow ,EndRow , nextTestJoin
	// #
	// #############################################################################################################

	@Parameters({ "StartRow", "EndRow", "nextTestJoin" })
		@Test
		public void TC_ODP07(int StartRow,String EndRow, boolean nextTestJoin) throws Throwable {
		int counter = 0;
		try {
			int intStartRow = StartRow;
			int intEndRow = ReportControl.fnGetEndRowCunt(EndRow, "ODP07_ConverQuote",
					TestData, "Web_Order_Placement");
			for (int intCounter = intStartRow; intCounter <= intEndRow; intCounter++) {
				try {
					counter = intCounter;
					fnOpenTest();
					ReportStatus.fnDefaultReportStatus();
					ReportControl.intRowCount = intCounter;
					Hashtable<String, String> data = TestUtil.getDataByRowNo("ODP07_ConverQuote",
							TestData, "Web_Order_Placement", intCounter);
					TestEngineWeb.reporter.initTestCaseDescription("ConverQuote");
			
						cmtLib.loginToCMT(data.get("Header"));
						cmtLib.searchForWebGroup(data.get("WebGrp"));
						cmtLib.clickOnTheWebGroup(data.get("WebGrp_Name"));
						//Customer Level Permissions:override_payment_options - off
						cmtLib.setCustomerLevelPermissionsOFF(data.get("Customer_Permissions_OFF"));
						cmtLib.enterWGCustom800NumberAndDisplayOnWeb(data.get("WG_Custom_800_Number"));
						cmtLib.hoverOnManageWebGroupsAndSelectOptions(data.get("Manage_Web_Grp_Options"));
						cmtLib.searchForaUserAndSelect(data.get("LnameEmailUname"), data.get("ContactName"));
						cmtLib.setPermissions(data.get("Menu_Name"), data.get("Set_Permission1"));
						cmtLib.setPermissionsToDisable(data.get("Menu_Name"), data.get("Set_Permission2"));

						// Login As to Web UAT
						cmtLib.loginAsAdminCMT();

						// Select First Product and Add to cart
						searchLib.searchInHomePage(data.get("SearchText"));
						searchLib.verifyBreadCrumbInSearchResultsPage(data.get("SearchText"));
						cartLib.selectFirstProductDisplay();
						String mfrNumber1=prodLib.getInsightPartNumberInProductInfopage();
						// Cart verification
						commonLib.addToCartAndVerify();
						orderLib.continueToCheckOutOnAddCart();
						canadaLib.verifyPlaceCartLabel();
						cartLib.verifyItemInCartByInsightPart(mfrNumber1);

						// Create Quote
						createQuote(data.get("Quote_Name"));
						verifyTaxInSaveAsQuotePage();   // Verify Tax in save as quote page
						String refNumber=getQuoteReferenceNumber();
						selectOrderUtilitiesOnSaveAsQuotesScreen(data.get("Order_Utilities"));
						verifyWG800NumberOnSaveAsQuoteScreen(data.get("WG_Custom_800_Number"));
						String taxAmount=verifyTaxInSaveAsQuotePage().replace("$", "");
						
						commonLib.clickOnAccountToolsAndClickOnProductGrp(data.get("Tools_Menu"), data.get("Tools_Menu_DD"));
						searchByInQuoteHistory(refNumber,data.get("DD_Option"));
						convertQuote();
						cartLib.verifyCartBreadCrumb();
						verifyTheQuantityIsdisabled();

						commonLib.clickOnAccountToolsAndClickOnProductGrp(data.get("Tools_Menu"), data.get("Tools_Menu_DD"));
						searchByInQuoteHistory(refNumber,data.get("DD_Option"));
						editQuote();
						cartLib.verifyCartBreadCrumb();
						commonLib.updateCartQuantity(data.get("Quantity"));

						//Proceed to checkout
						proceedToCheckout();
						lnlLib.verifyOrderAndItemInfoBreadCrumb();
						continueButtonOnAdditionalInformationSection();  // Click continue on Additional information Section
						canadaLib.verifySBP();
						clickContinueOnShippingAddress(); // Click continue on shipping address Section
						shippingOptionsCarrierSelection();  // carrier selection or continue in shipping options
						billingAddressContinueButton(); //Click continue on Billing address Section
						selectPaymentInfoMethodCreditCard(data.get("Card_Number").toString(), data.get("Card_Name"),data.get("Month"), data.get("Year"),data.get("PO_Number"),data.get("POReleaseNumber"));

						// Review Order
						clickOnReviewOrderButton();

						/// Verify Updated Qty on Place Order Page
						verifyTheQuantityOfCartProductOnReceiptPage(data.get("Quantity"));

						//Place Order
						String summaryAmount=cartLib.getSummaryAmountInCart();
						placeOrderAndVerifyReceiptOrderAndDate(summaryAmount);

						//Verify Receipt
						verifyReceiptVerbiage();
						clickPrintOnReceiptpage();
						verifyPhoneNumberOnPrintPopupOfReceipPage(data.get("WG_Custom_800_Number"));
						cartLib.closePrintPopUp();
						clickOrderDetailsLinkOnReceiptPage();

						// verify Ship Bill details
						verifyShippingAddressOnReceiptPage(data.get("Section_Name1")); // Verifying shipping address
						verifyBillingAddressOnReceiptPage(data.get("Section_Name2"));  // verifying billing address

						// Verify Updated Qty on Receipt Page
						verifyTheQuantityOfCartProductOnReceiptPage(data.get("Quantity"));
						getTaxInReceipt();
						//verifyTheTaxForSearchTerm(taxAmount);
						/*String result = getText(ADDLICENCE_TAX_AMOUNT, "Tax displayed after adding LICENCE").replace("$", "");
						Float actualtax=Float.valueOf(result);
						if (actualtax==(Float.valueOf(taxAmount)*Integer.valueOf(data.get("Quantity")))) {
							reporter.SuccessReport("Verify the TAX on receipt page","Tax in Order Recipet for Quote Converion",
									"Tax in Order Recipet for Quote Converion:Tax estimateUSD $"+actualtax);
						} else {
							reporter.failureReport("Verify the TAX on receipt page","Tax in Order Recipet for Quote Converion is not same","Actual tax: "+actualtax+"  Expected tax: "+taxAmount,driver);
						}*/
						commonLib.clickLogOutLink(data.get("Logout"));

						// fnCloseTest();
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
				ReportStatus.fnUpdateResultStatus("ODP07_ConverQuote", "TC_ODP07", ReportStatus.strMethodName, 1, browser);
				throw new RuntimeException(e);
			}
	        finally {
	        	ReportControl.fnEnableJoin();
				ReportStatus.fnUpdateResultStatus("ODP07_ConverQuote", "TC_ODP07", ReportStatus.strMethodName, counter, browser);
				fnCloseTest();
				ReportControl.fnNextTestJoin(nextTestJoin);
			}
		}

	}
