package com.insight.WebTest.Canada;


	import java.util.Hashtable;

	import org.testng.annotations.Parameters;
	import org.testng.annotations.Test;

	import com.insight.Lib.CMTLib;
	import com.insight.Lib.CanadaLib;
import com.insight.Lib.CartLib;
import com.insight.Lib.CommonLib;
import com.insight.Lib.OrderLib;
import com.insight.Lib.ProductDetailLib;
import com.insight.Lib.ProductDisplayInfoLib;
import com.insight.Lib.SearchLib;
import com.insight.Lib.SewpLib;
import com.insight.accelerators.ActionEngine;
	import com.insight.accelerators.ReportControl;
	import com.insight.accelerators.TestEngineWeb;
	import com.insight.googledrive.ReportStatus;
	import com.insight.utilities.TestUtil;

	public class CAN17_ShippingPricesLtdandStdUserTest extends CanadaLib  {
		
		CMTLib cmtLib = new CMTLib();
		CommonLib commonLib = new CommonLib();
		CartLib cartLib = new CartLib();
		SearchLib searchLib = new SearchLib();
		ProductDetailLib prodDetailsLib=new ProductDetailLib();
		OrderLib orderLib=new OrderLib();
		ProductDisplayInfoLib pipLib=new ProductDisplayInfoLib();
		SewpLib sewpLib=new SewpLib();

		// #############################################################################################################
		// # Name of the Test : CAN17_ShippingPricesLtdandStdUser
		// # Migration Author : Cigniti Technologies
		// #
		// # Date of Migration : October 2019
		// # DESCRIPTION : This method is to perform Basic Cart operations.
		// # Parameters : StartRow ,EndRow , nextTestJoin
		// #
		// ###############################################################################################################
		@Parameters({ "StartRow", "EndRow", "nextTestJoin" })
		@Test
		public void Tc_CAN17(int StartRow, String EndRow, boolean nextTestJoin) throws Throwable {
		
					int counter = 0;
					try {
						int intStartRow = StartRow;
						int intEndRow = ReportControl.fnGetEndRowCunt(EndRow, "CAN17_ShippingPricesLtdandStdUser", TestDataInsight, "Canada");
						for (int intCounter = intStartRow; intCounter <= intEndRow; intCounter++) {
							try {
								counter = intCounter;
								fnOpenTest();
								ReportStatus.fnDefaultReportStatus();
								ReportControl.intRowCount = intCounter;
								Hashtable<String, String> data = TestUtil.getDataByRowNo("CAN17_ShippingPricesLtdandStdUser", TestDataInsight,
										"Canada", intCounter);
								TestEngineWeb.reporter.initTestCaseDescription("ShippingPricesLtdandStdUser");				


					navigateToApplication("CANADA");
					searchLib.searchInHomePage(data.get("SearchItem"));
					Thread.sleep(2000);
					verifyProductPriceWithGivenPrice(data.get("verifyPrice"));
					Thread.sleep(2000);
					sewpLib.clickOnAddtoCart();
					sewpLib.clickContinueToCheckout();
					cmtLib.handleWelcomeToInsightBetaPopUp();
					clickCreateAccountOnCanadaSearchPage();
					String randomName=getRandomNumeric(4);
					enterMailDetails(randomName);
					verifyCountryDisplayed(data.get("Country"));
					selectJobTitle(data.get("Jobtitle"));
					selectOption();
					clickCreateButton();					
					addAdditionalInfo(randomName,data.get("ShipPhone"));
					verifyLoggedinPriceInCart(data.get("total"),data.get("loggedPrice"));
					addShippingAddress(data.get("Company"),randomName,data.get("Address1"),data.get("City"),data.get("State"),data.get("Zipcode"));
					
					orderLib.shippingBillPayContinueButton();
					saveAddress();
					verifyShippingMethod(data.get("Air"));
					verifyShippingMethod(data.get("Ground"));
					orderLib.shippingBillPayContinueButton();
					addShippingAddress(data.get("BillingCompany"),randomName,data.get("BillingAddress"),data.get("BillingCity"),data.get("BillingState"),data.get("BillingZipcode"));
					orderLib.shippingBillPayContinueButton();
					saveAddress();
					orderLib.enterCreditCard(data.get("Card_Number").toString(), data.get("Card_Name"),data.get("Month"), data.get("Year"),data.get("PONumber"),data.get("POReleaseNumber"));
					orderLib.clickOnReviewOrderButton();
				    cartLib.getSummaryAmountInCart();
				    clickPlaceOrderButton();
					getReferenceNo();
					
					searchLib.searchInHomePage(data.get("SearchItem"));
					Thread.sleep(2000);
					verifyProductPriceWithGivenPrice(data.get("verifyPrice"));
					Thread.sleep(2000);
					sewpLib.clickOnAddtoCart();
					sewpLib.clickContinueToCheckout();
					cmtLib.loginAsEndUser(data.get("userName"),data.get("Password"));
					verifyProductPriceWithGivenPrice(data.get("verifyPrice"));					
					orderLib.shippingBillPayContinueButton();
					verifyShippingMethod(data.get("Air"));
					verifyShippingMethod(data.get("Ground"));

					orderLib.shippingBillPayContinueButton(); // Click continue on shipping address Section
					orderLib.shippingOptionsCarrierSelection();  // carrier selection or continue in shipping options
					orderLib.shippingBillPayContinueButton(); //Click continue on Billing address Section
					orderLib.selectPaymentInfoMethodCreditCard(data.get("Card_Number").toString(), data.get("Card_Name"),data.get("Month"), data.get("Year"),data.get("PO_Number"),data.get("POReleaseNumber"));
				   
					// Review Order 
					orderLib.clickOnReviewOrderButton();
					 clickPlaceOrderButton();
					clickOnSideMenuSelectAccountToolOptions(data.get("Tools_Menu"),data.get("Tools_Menu_DD"));					
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
						ReportStatus.fnUpdateResultStatus("ShippingPricesLtdandStdUser", "TC_CAN17", ReportStatus.strMethodName, 1, browser);
						throw new RuntimeException(e);
					}
				    finally {
				    	ReportControl.fnEnableJoin();
						ReportStatus.fnUpdateResultStatus("ShippingPricesLtdandStdUser", "TC_CAN17", ReportStatus.strMethodName, counter, browser);
						fnCloseTest();
						ReportControl.fnNextTestJoin(nextTestJoin);
					}
				}
				}

