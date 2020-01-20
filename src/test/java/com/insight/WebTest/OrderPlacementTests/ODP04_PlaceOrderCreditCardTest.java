package com.insight.WebTest.OrderPlacementTests;

import com.google.gdata.data.spreadsheet.Data;
import com.insight.Lib.*;
import com.insight.accelerators.ReportControl;
import com.insight.accelerators.TestEngineWeb;
import com.insight.googledrive.ReportStatus;
import com.insight.utilities.TestUtil;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.util.Hashtable;

public class ODP04_PlaceOrderCreditCardTest extends OrderLib{

	ProductDisplayInfoLib prodInfoLib = new ProductDisplayInfoLib();
	CMTLib cmtLib = new CMTLib();
	SearchLib searchLib = new SearchLib();
	CommonLib commonLib = new CommonLib();
	CartLib cartLib = new CartLib();
	ProductDisplayInfoLib prodLib = new ProductDisplayInfoLib();
	OrderLib orderLib =new OrderLib();

	// #############################################################################################################
	// #    Name of the Test         : ODP04_PlaceOrderCreditCard
	// #    Migration Author         : Cigniti Technologies
	// #
	// #    Date of Migration        : September 2019
	// #    Description              : To Test Place Order  Confirmations
	// #    Parameters               : StartRow ,EndRow , nextTestJoin
	// #
	// #############################################################################################################

	@Parameters({ "StartRow", "EndRow", "nextTestJoin" })
		@Test
		public void TC_ODP04(int StartRow,String EndRow, boolean nextTestJoin) throws Throwable {
		int counter = 0;
		try {
			int intStartRow = StartRow;
			int intEndRow = ReportControl.fnGetEndRowCunt(EndRow, "ODP04_PlaceOrderCreditCard",
					TestData, "Web_Order_Placement");
			Hashtable<String, String> data1 = TestUtil.getDataByRowNo("ODP04_PlaceOrderCreditCard", TestData,"Web_Order_Placement", intStartRow);  
			fnOpenTest();
			
			// Login to CMT and disable Allow File Upload during Checkout,Override Payment Options
			cmtLib.loginToCMT(data1.get("Header"));
			cmtLib.searchForWebGroup(data1.get("WebGrp"));
			cmtLib.clickOnTheWebGroup(data1.get("WebGrp_Name"));
			
			//  Uncheck Override Payments Options in web Group Level	 	
			cmtLib.setCustomerLevelPermissionsOFF(data1.get("Customer_Permissions_OFF"));
			
			// select Users and Login As
			cmtLib.hoverOnManageWebGroupsAndSelectOptions(data1.get("Manage_Web_Grp_Options"));
			cmtLib.searchForaUserAndSelect(data1.get("LnameEmailUname"), data1.get("ContactName"));
			cmtLib.loginAsAdminCMT();
			
			for (int intCounter = intStartRow; intCounter <= intEndRow; intCounter++) {
				try {
					counter = intCounter;
					
					ReportStatus.fnDefaultReportStatus();
					ReportControl.intRowCount = intCounter;
					Hashtable<String, String> data = TestUtil.getDataByRowNo("ODP04_PlaceOrderCreditCard",TestData, "Web_Order_Placement", intCounter);
					TestEngineWeb.reporter.initTestCaseDescription("PlaceOrderCreditCard");

                        // Select First Product and Add to cart
						searchLib.searchInHomePage(data.get("SearchText"));
						commonLib.addToCartAndVerify();
						continueToCheckOutOnAddCart();
						cartLib.verifyItemInCart(data.get("SearchText"));
						proceedToCheckout();
						continueButtonOnAdditionalInformationSection();  // Click continue on Additional information Section
						clickContinueOnLineLevelInfo(); // Click continue on Line Level information Section
						shippingBillPayContinueButton(); // Click continue on shipping address Section
						shippingBillPayContinueButton(); // Click continue on Shipping options Section
						shippingBillPayContinueButton(); //Click continue on Billing address Section
						selectPaymentInfoMethodCreditCard(data.get("Card_Number").toString(), data.get("Card_Name"),data.get("Month"), data.get("Year"),data.get("PO_Number"),data.get("POReleaseNumber"));  // VISA card
						clickOnReviewOrderButton();
						//Place Order
						String summaryAmount=cartLib.getSummaryAmountInCart();
						placeOrderAndVerifyReceiptOrderAndDate(summaryAmount);

						//Verify Receipt
						verifyReceiptVerbiage();
						clickOrderDetailsLinkOnReceiptPage();

						// verifying payment info
						verifyPaymentInformationOnReceiptPage(data.get("Section_Name3"),data.get("Month"),data.get("Year"),data.get("Card_Name"),data.get("Ending_Card_Numbers"),data.get("Card_Type"));

						// Verifying the part in cart
						cartLib.verifyItemInCart(data.get("SearchText"));
					} 
				
				   catch (Exception e) {
						ReportStatus.blnStatus = false;
						//gErrorMessage = e.getMessage();
						gTestStatus = false;
					}
				}
			commonLib.clickLogOutLink(data1.get("Logout"));
			// End of test 
			     
			} catch (Exception e) {
				e.printStackTrace();
				ReportStatus.blnStatus = false;
				//gErrorMessage = e.toString();
				gTestStatus = false;
				ReportStatus.fnUpdateResultStatus("ODP04_PlaceOrderCreditCard", "TC_ODP04", ReportStatus.strMethodName, 1, browser);
				throw new RuntimeException(e);
			}
	        finally {
	        	ReportControl.fnEnableJoin();
				ReportStatus.fnUpdateResultStatus("ODP04_PlaceOrderCreditCard", "TC_ODP04", ReportStatus.strMethodName, counter, browser);
				fnCloseTest();
				ReportControl.fnNextTestJoin(nextTestJoin);
			}
		}

	}