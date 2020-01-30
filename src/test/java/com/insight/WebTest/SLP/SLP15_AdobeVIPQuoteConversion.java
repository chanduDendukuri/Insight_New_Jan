package com.insight.WebTest.SLP;

import java.util.Hashtable;
import java.util.List;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.insight.Lib.CMTLib;
import com.insight.Lib.CanadaLib;
import com.insight.Lib.CartLib;
import com.insight.Lib.CommonLib;
import com.insight.Lib.OrderLib;
import com.insight.Lib.ProductDetailLib;
import com.insight.Lib.ProductDisplayInfoLib;
import com.insight.Lib.QuoteHistoryLib;
import com.insight.Lib.SLPLib;
import com.insight.Lib.SearchLib;
import com.insight.Lib.ShipBillPayLib;
import com.insight.accelerators.ReportControl;
import com.insight.accelerators.TestEngineWeb;
import com.insight.googledrive.ReportStatus;
import com.insight.utilities.TestUtil;

public class SLP15_AdobeVIPQuoteConversion extends SLPLib{
	CMTLib cmtLib = new CMTLib();
	CommonLib commonLib = new CommonLib();
	CartLib cartLib = new CartLib();
	SearchLib searchLib = new SearchLib();
	ProductDetailLib prodDetailsLib=new ProductDetailLib();
	OrderLib orderLib=new OrderLib();
	ProductDisplayInfoLib pipLib=new ProductDisplayInfoLib();
	ShipBillPayLib sbpLib=new ShipBillPayLib();
	CanadaLib canadaLib=new CanadaLib();
	QuoteHistoryLib quoteLib= new QuoteHistoryLib();
	
	   
	    // #############################################################################################################
		// #       Name of the Test         :  SLP15_AdobeVIPQuoteConversion
		// #       Migration Author         :  Cigniti Technologies
		// #
		// #       Date of Migration        : October 2019
		// #       DESCRIPTION              : This Test is used to test VIP quote conversion
		// #       Parameters               : StartRow ,EndRow , nextTestJoin
		// #
		// ###############################################################################################################
		@Parameters({ "StartRow", "EndRow", "nextTestJoin" })
		@Test
		public void TC_SLP15(int StartRow,String EndRow, boolean nextTestJoin) throws Throwable {
			int counter = 0;
		
			try {
				int intStartRow = StartRow;
				int intEndRow = ReportControl.fnGetEndRowCunt(EndRow, "SLP15_AdobeVIPQuoteConversion", TestData, "SLP");
				for (int intCounter = intStartRow; intCounter <= intEndRow; intCounter++) {
					try {
						fnOpenTest();
						ReportStatus.fnDefaultReportStatus();
						ReportControl.intRowCount = intCounter;
						Hashtable<String, String> data = TestUtil.getDataByRowNo("SLP15_AdobeVIPQuoteConversion", TestData, "SLP", intCounter);
						TestEngineWeb.reporter
								.initTestCaseDescription("SLPProrationMicrosoft");
						reporter.SuccessReport("Iteration Number : ",
								"**************Iteration Number::  " + intCounter + " For:: " + data.get("LoginName") + " ::and:: "
										+ data.get("Password") + " To Validate::" + data.get("errorMessage") + "  **************","");
						
						// Login to CMT
						cmtLib.loginToCMT(data.get("Header"));
						cmtLib.searchForWebGroup(data.get("WebGrp"));
						cmtLib.clickOnTheWebGroup(data.get("WebGrp_Name"));
						cmtLib.hoverOnManageWebGroupsAndSelectOptions(data.get("Manage_Web_Grp_Options"));
						cmtLib.searchForaUserAndSelect(data.get("LnameEmailUname"), data.get("ContactName"));
						// "enable_standard_reports;ON";
						cmtLib.setPermissions(data.get("Menu_Name"), data.get("Set_Permission"));
						cmtLib.loginAsAdminCMT();
						// Login verification
						cmtLib.loginVerification(data.get("ContactName"));
						
						// Search for part or product and add to cart :65291080BA03A12
				     	searchLib.searchInHomePage(data.get("PartNum1"));
				     	pipLib.verifyTheManufacturerNumberInProductDetailsPage(data.get("PartNum1"));
				     	pipLib.enterQuantityOnProductDetailsPage(data.get("Quantity"));
				     	commonLib.addToCartAndVerify();
				     	orderLib.continueToCheckOutOnAddCart();
				       
				     	// Search for part or product and add to cart : 65234098BA03A12
				     	searchLib.searchInHomePage(data.get("PartNum2"));
				     	pipLib.verifyTheManufacturerNumberInProductDetailsPage(data.get("PartNum2"));
				     	pipLib.enterQuantityOnProductDetailsPage(data.get("Quantity"));
				     	commonLib.addToCartAndVerify();
				     	orderLib.continueToCheckOutOnAddCart();
				     	canadaLib.verifyPlaceCartLabel();
				       
				     	// Search for part or product and add to cart : L9K19UT#ABA
				     	searchLib.searchInHomePage(data.get("PartNum3"));
				     	pipLib.verifyTheManufacturerNumberInProductDetailsPage(data.get("PartNum3"));
				     	pipLib.enterQuantityOnProductDetailsPage(data.get("Quantity"));
				     	commonLib.addToCartAndVerify();
				     	orderLib.continueToCheckOutOnAddCart();
				     	canadaLib.verifyPlaceCartLabel();
				     	
				        // Verify Deploy popup details for part 1
				     	String date1=getDeploydateOnCart(data.get("PartNum1"));
				     	List<String> prodDesc1 = orderLib.getProductDescriptionOfCartProduct();
				     	verifyandClickchangeLink(data.get("PartNum1"));
				     	verifydeployedatewithcurrentdate();
				     	verifycartDetailsWithDeployPopUpDetails(date1, prodDesc1.get(1), data.get("PartNum1"));
				     	calenderforUnpaidLicense(data.get("Date1"));
				     	clickapply();
				     	Thread.sleep(2000);
				     	// Copy to all 
				     	verifyandClickcopytoallifexists(data.get("PartNum1"));
				     	String date2=getDeploydateOnCart(data.get("PartNum2"));
				     	verifyDateAppliedToAllPartAfterCopyAll(date2, data.get("Date1"));
				     	
				     	orderLib.proceedToCheckout();
				     	int i=1;
				     	int j=2;
				     	enterPAvalue(data.get("PA"),i);//PA_fieldinplaceorderpage
				     	enterPAvalue(data.get("PA1"),j);
				     	orderLib.clickContinueOnLineLevelInfo();
				        orderLib.clickOnReturnToCartLink();     
				        canadaLib.verifyPlaceCartLabel();
				        String dateUpdated=getDeploydateOnCart(data.get("PartNum2"));
				        verifyDateAppliedToAllPartAfterCopyAll(dateUpdated, data.get("Date1"));
				        String subTotalAmount=  sbpLib.getTotalAmountInCart(data.get("Subtotal")).replace("$", "").replace(",", "");
				     	Double subTotal=Double.valueOf(subTotalAmount);
				     	if(subTotal!=0) {
				     		reporter.SuccessReport("Find SubtotalCurrency Code and Amount in Cart Summary on Content & resources ", "Subtotal Currency Code and Amount Exists", "Currency Code and Amount: USD $"+subTotal);
				     	}else {
				     		reporter.failureReport("Find SubtotalCurrency Code and Amount in Cart Summary on Content & resources ", "Subtotal Currency Code and Amount does not Exists", "Currency Code and Amount: USD $"+subTotal,driver);
				     	}
				        
                        clickSaveasQuote();
                        // verifying deploy date on quotes screen
                        verifyProductDeployDate(data.get("Date1"));
                        // verifying PA fields 
                        verifyPAFieldsOnQuotesScreen(data.get("PA"));
                        verifyPAFieldsOnQuotesScreen(data.get("PA1"));
                        // MFR requirements
                        verifyManufacturerRequirementsOnQuoteScreen();
				        String quotesubTotal=getSubTotalOnQuotesScreen(data.get("SubTotal1")).replace("$", "").replace(",", "");;
				        verifySubTotalAmountsOnQuoteAndCartScreen(subTotalAmount, quotesubTotal);
				        clickSaveAsQuoteButtonOnQuoteScreen();
                        String refNumber = orderLib.getQuoteReferenceNumber();
				     	
				        // verifying deploy date on quotes screen
                        verifyProductDeployDate(data.get("Date1"));
                        // verifying PA fields 
                        verifyPAFieldsOnQuotesScreen(data.get("PA"));
                        verifyPAFieldsOnQuotesScreen(data.get("PA1"));
                        // MFR requirements
                        verifyManufacturerRequirementsOnQuoteScreen();
                        String quotesubTotal1=getSubTotalOnQuotesScreen(data.get("SubTotal1")).replace("$", "").replace(",", "");;
				        verifySubTotalAmountsOnQuoteAndCartScreen(subTotalAmount, quotesubTotal1);
				        clickQuoteHistoryLink();
				        Thread.sleep(5000);
				        orderLib.searchByInQuoteHistory(refNumber,data.get("DD_Option"));
				        orderLib.convertQuote();
						cartLib.verifyCartBreadCrumb();
						getDeploydateOnCart(data.get("PartNum2"));
						getDeploydateOnCart(data.get("PartNum1"));
                        
						 orderLib.proceedToCheckout();
						 //orderLib.continueButtonOnAdditionalInformationSection();
						 orderLib.clickContinueOnLineLevelInfo();
						 canadaLib.verifySBP();
						 orderLib.clickContinueOnShippingAddress();
					     orderLib.shippingOptionsCarrierSelection();
						 orderLib.billingAddressContinueButton();
                        
                         orderLib.selectPaymentInfoMethodCreditCard(data.get("Card_Number").toString(), data.get("Card_Name"), data.get("Month"),
			             data.get("Year"), data.get("PONumber"),data.get("POReleaseNumber"));
                        orderLib.clickOnReviewOrderButton();                       
                        orderLib.verifyPlaceOrderLabel();
                      
                        //Verifying PA fields in Place order page
                        verifyPAOnReceiptPage(data.get("PA")); 
                        verifyPAOnReceiptPage(data.get("PA1")); 
                        // Verify deploy date
                        verifyDeploydateOnPlaceOrderPage(data.get("PartNum2"), data.get("Date2"));
                        verifyDeploydateOnPlaceOrderPage(data.get("PartNum1"), data.get("Date1"));
                        int item=Integer.valueOf(data.get("item_num"));
                        verifyManufacturerRequirementsOnPlaceOrderScreen(item);
                        verifyManufacturerRequirementsOnPlaceOrderScreen(item+1);
                        String subTotalAmountOnPO=  sbpLib.getTotalAmountInCart(data.get("Subtotal")).replace("$", "").replace(",", "");
				     	 verifySubTotalAmountsOnQuoteAndCartScreen(subTotalAmountOnPO, quotesubTotal);
                        //Place Order
						String summaryAmountInLogin=cartLib.getSummaryAmountInCart();
						 orderLib.placeOrderAndVerifyReceiptOrderAndDate(summaryAmountInLogin);
						String RefNumber= orderLib.getTextfromReferenceNumber();
                        orderLib.clickOrderDetailsLinkOnReceiptPage();
                     // Logout
            			commonLib.clickLogOutLink(data.get("Logout"));

            			
					} catch (Exception e) {
						ReportStatus.blnStatus = false;
						//gErrorMessage = e.getMessage();
						gTestStatus = false;
						throw new RuntimeException(e);
					}
					
				}
			} catch (Exception e) {
				e.printStackTrace();
				ReportStatus.blnStatus = false;
				//gErrorMessage = e.getMessage();
				gTestStatus = false;
				ReportStatus.fnUpdateResultStatus("AdobeVIPQuoteConversion", "SLP15", ReportStatus.strMethodName, 1, browser);
				throw new RuntimeException(e);
			}
			finally {
	        	ReportControl.fnEnableJoin();
	        	ReportStatus.fnUpdateResultStatus("AdobeVIPQuoteConversion", "SLP15", ReportStatus.strMethodName, counter, browser);
				fnCloseTest();
				ReportControl.fnNextTestJoin(nextTestJoin);
			}
			}
	}
		