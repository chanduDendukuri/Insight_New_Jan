package com.insight.WebTest.LNL;

import java.util.Hashtable;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.insight.Lib.CMTLib;
import com.insight.Lib.CanadaLib;
import com.insight.Lib.CartLib;
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

public class LNL01_InactiveSTTest extends LineLevelInfoLib{
	
	CMTLib cmtLib = new CMTLib();
	CommonLib commonLib = new CommonLib();
	CartLib cartLib = new CartLib();
	SearchLib searchLib = new SearchLib();
	ProductDetailLib prodDetailsLib=new ProductDetailLib();
	OrderLib orderLib=new OrderLib();
	ProductDisplayInfoLib pipLib=new ProductDisplayInfoLib();
	ShipBillPayLib sbpLib=new ShipBillPayLib();
	CanadaLib canadaLib=new CanadaLib();
	LineLevelInfoLib lnlLib=new LineLevelInfoLib();
	   
	    // #############################################################################################################
		// #       Name of the Test         : LNL01_InactiveSTTest
		// #       Migration Author         : Cigniti Technologies
		// #
		// #       Date of Migration        : October 2019
		// #       DESCRIPTION              : Test to Ensure inactive smart trackers do no display
		// #       Parameters               : StartRow ,EndRow , nextTestJoin
		// #
		// ###############################################################################################################
		@Parameters({ "StartRow", "EndRow", "nextTestJoin" })
		@Test
		public void TC_LNL01(int StartRow,String EndRow, boolean nextTestJoin) throws Throwable {
			int counter = 0;
			try {
				int intStartRow = StartRow;
				int intEndRow = ReportControl.fnGetEndRowCunt(EndRow, "LNL01_InactiveST", TestData, "WEB_LineLevelInfo");
				for (int intCounter = intStartRow; intCounter <= intEndRow; intCounter++) {
					try {
						fnOpenTest();
						ReportStatus.fnDefaultReportStatus();
						ReportControl.intRowCount = intCounter;
						Hashtable<String, String> data = TestUtil.getDataByRowNo("LNL01_InactiveST", TestData, "WEB_LineLevelInfo", intCounter);
						TestEngineWeb.reporter.initTestCaseDescription("InactiveST");
                    
						// Create a new account 
						canadaLib.clickCreateAccountOnCanadaSearchPage();
						cmtLib.handleWelcomeToInsightBetaPopUp();
						String userName=enterEmailInCreateAccount().replace("@mail.com", "");
						enterpasswordInCreateAccount(userName);
						canadaLib.clickCustomCheckBox();
						canadaLib.clickOnNext();
						selectCountryDisplayed(data.get("Country"));  // United States Of America
						canadaLib.selectJobTitle(data.get("Jobtitle"));
						canadaLib.selectOption();
						canadaLib.clickCreateButton();
						// Search for part or product
						searchLib.searchInHomePage(data.get("SearchText"));
						searchLib.verifyBreadCrumbInSearchResultsPage(data.get("SearchText"));
						String partNumber=pipLib.getPartNumberInSearchResultsPage();
						pipLib.selectFirstProductAddToCartAndVerifyCart();
						orderLib.proceedToCheckout();
						verifyOrderAndItemInfoBreadCrumb();
						// Enter add additional info
						
						String contactName=canadaLib.enterNameOnAdditionalInfo(userName);
						canadaLib.enterPhoneOnAdditionalInfo(data.get("Phone"));
						orderLib.continueButtonOnAdditionalInformationSection();
						canadaLib.verifySBP();
						// enter Shipping address
						// street1 >> 3480 Lotus Dr  // city >> PLANO // state >> Texas // zipCode >> 75075
						// Company name ****** // ****** name should be same as email geneerated
						canadaLib.addShippingAddress(userName, userName, data.get("Street1"), data.get("City"), data.get("State"), data.get("Zipcode"));  
						orderLib.clickContinueOnShippingAddress(); // continue on shipping address
						// Save address  
						acceptShippingAddressVerification();
						orderLib.shippingOptionsCarrierSelection(); // continue on shipping options
						checkSameAsShippingAddress();
						orderLib.billingAddressContinueButton(); // continue on Billing  options
						orderLib.selectPaymentInfoMethodCreditCard(data.get("Card_Number").toString(), data.get("Card_Name"),data.get("Month"), data.get("Year"),data.get("PO_Number"),data.get("POReleaseNumber"));
						orderLib.clickOnReviewOrderButton();
						cartLib.getSummaryAmountInCart();
					    canadaLib.clickPlaceOrderButton();
					    orderLib.verifyReceiptVerbiage();
						
						/// Login 
						cmtLib.clickLoginLink(data.get("Header"));
						cmtLib.loginAsAdmin();
						// search for user
						cmtLib.searchForUser(userName);
						// Click on Web Group Link 
						cmtLib.clickOnWebGrpLink();
						cmtLib.hoverOnManageWebGroupsAndSelectOptions(data.get("Manage_Web_Grp_Options1"));  // SmartTrackers
						cmtLib.verifySmartTrackerPage();
						cmtLib.selectmanageSmartTrackertabs(data.get("tab_Name1"));
						//cmtLib.selectSmartTrackersHeaders(data.get("Header1"));
						// Add a smart tracker
						cmtLib.clickOnAddSmartTrackerLink();
						String date_field1 = "QTP"+getCurrentDateTime("dd-mmm-yyyy HH:MM:SS.ss");
						cmtLib.addFieldLabelInSmartTracker(date_field1);
						cmtLib.saveChangesAndVerify(date_field1);
						// inActive smart tracker
						cmtLib.editSmartTracker(date_field1);
						cmtLib.verifyInactiveSmartTrackerError();
						String date_field2 = "QTP"+getCurrentDateTime("dd-mmm-yyyy HH:MM:SS.ss");
						
						// click on Line level tab
						cmtLib.selectSmartTrackersHeaders(data.get("Header"));
						cmtLib.clickOnAddSmartTrackerLink();
						cmtLib.addFieldLabelInSmartTracker(date_field2);
						cmtLib.addSmartTrackerFieldType(data.get("Field_Type"));  // Date - type
						cmtLib.saveChangesAndVerify(date_field2);
						
						// inActive smart tracker
						cmtLib.editSmartTracker(date_field2);
						cmtLib.verifyInactiveSmartTrackerError();
						
						// click on Manage Reporting Parent SmartTrackers
						refreshPage();
						String date_field3 = "QTP"+getCurrentDateTime("dd-mmm-yyyy HH:MM:SS.ss");
						cmtLib.selectmanageSmartTrackertabs(data.get("tab_Name"));
						//cmtLib.selectSmartTrackersHeaders(data.get("Header1"));
						cmtLib.clickOnAddSmartTrackerLink();
						cmtLib.addFieldLabelInSmartTracker(date_field3);
						cmtLib.addSmartTrackerFieldType(data.get("Field_Type"));  // Date - type
						cmtLib.saveChangesAndVerify(date_field3);
						
						// inActive smart tracker
						cmtLib.editSmartTracker(date_field3);
						cmtLib.verifyInactiveSmartTrackerError();
					  
						String date_field_LL = "QTP"+getCurrentDateTime("dd-mmm-yyyy HH:MM:SS.ss");
						// click on Line level tab
						cmtLib.selectSmartTrackersHeaders(data.get("Header"));
						cmtLib.clickOnAddSmartTrackerLink();
						cmtLib.addFieldLabelInSmartTracker(date_field_LL);
						cmtLib.addSmartTrackerFieldType(data.get("Field_Type"));  // Date - type
						cmtLib.saveChangesAndVerify(date_field_LL);
						
						// inActive smart tracker
						cmtLib.editSmartTracker(date_field_LL);
						cmtLib.verifyInactiveSmartTrackerError();
						Thread.sleep(3000);
						cmtLib.hoverOverMasterGroupAndSelectChangeGrp();
						// search for user
						cmtLib.searchForUser(userName);
						// Click on Web Group Link 
						cmtLib.clickOnWebGrpLink();
						cmtLib.hoverOnManageWebGroupsAndSelectOptions(data.get("Manage_Web_Grp_Options"));  // Users
						cmtLib.searchForaUserAndSelect(userName,contactName);  // contact 
						// login As
						cmtLib.loginAsAdminCMT();
						
						// search for item and add to cart
						searchLib.searchInHomePage(data.get("SearchText"));
						cartLib.selectFirstProductDisplay();
						String partNumber1=prodDetailsLib.getMFRNumberInProductInfopage();
						commonLib.addToCartAndVerify();
						orderLib.continueToCheckOutOnAddCart();
						cartLib.verifyItemInCart(partNumber1);
						
						// Proceed to check out
						proceedToCheckout();
						lnlLib.verifyLineLevelInfoExists("NotAvailable");
						
						orderLib.clickContinueOnShippingAddress(); // Click continue on shipping address Section
						orderLib.selectShippingOptionsCarrier(); // Click continue on Shipping options Section
						orderLib.billingAddressContinueButton(); //Click continue on Billing address Section
						orderLib.selectPaymentInfoMethodCreditCard(data.get("Card_Number").toString(), data.get("Card_Name"),data.get("Month"), data.get("Year"),data.get("PO_Number"),data.get("POReleaseNumber"));  // VISA card
						orderLib.clickOnReviewOrderButton();
						
						// logout
						commonLib.clickLogOutLink(data.get("Logout"));
						
					} catch (Exception e) {
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
				ReportStatus.fnUpdateResultStatus("InactiveST", "TC_LNL01", ReportStatus.strMethodName, 1, browser);
				throw new RuntimeException(e);
			}

			 finally {
		        	ReportControl.fnEnableJoin();
					ReportStatus.fnUpdateResultStatus("InactiveST", "TC_LNL01", ReportStatus.strMethodName, counter, browser);
					fnCloseTest();
					ReportControl.fnNextTestJoin(nextTestJoin);
				}
		}

}
