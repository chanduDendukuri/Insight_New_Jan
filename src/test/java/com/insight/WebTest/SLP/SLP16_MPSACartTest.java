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
import com.insight.Lib.ProductDisplayInfoLib;
import com.insight.Lib.SLPLib;
import com.insight.Lib.SearchLib;
import com.insight.Lib.ShipBillPayLib;
import com.insight.accelerators.ReportControl;
import com.insight.googledrive.ReportStatus;
import com.insight.utilities.TestUtil;

public class SLP16_MPSACartTest extends SLPLib{
	
	    // #############################################################################################################
		// # Name of the Test : SLP16_MPSACart
		// # Migration Author : Cigniti Technologies
		// #
		// # Date of Migration : OCT 2019
		// # DESCRIPTION : Purpose of this test method is to verify the compare
		// functionality in the products display page.
		// # Parameters : StartRow ,EndRow , nextTestJoin
		// #
		// ###############################################################################################################

		@Parameters({ "StartRow", "EndRow", "nextTestJoin" })
		@Test
		public void SLP16_MPSACart(int StartRow, String EndRow, boolean nextTestJoin) throws Throwable {
			int counter = 0;
			try {
			int intStartRow = StartRow;
			int intEndRow = ReportControl.fnGetEndRowCunt(EndRow, "SLP16_MPSACart", TestData, "SLP");
			for (int intCounter = intStartRow; intCounter <= intEndRow; intCounter++) {

				// initilizing libraries and testdata
				ReportStatus.fnDefaultReportStatus();
				ReportControl.intRowCount = intCounter;
				Hashtable<String, String> data = TestUtil.getDataByRowNo("SLP16_MPSACart", TestData, "SLP",
						intCounter);
				// Test Steps execution
				try {
					fnOpenTest();
					CMTLib cmtLib = new CMTLib();
					CommonLib commonLib = new CommonLib();
					OrderLib orderLib = new OrderLib();
					CartLib cartLib = new CartLib();
					CanadaLib canadaLib=new CanadaLib();
					SearchLib searchLib = new SearchLib();
					ShipBillPayLib shipbLib = new ShipBillPayLib();
					ProductDisplayInfoLib pipLib=new ProductDisplayInfoLib();
					
					cmtLib.loginToCMT(data.get("Header"));
					cmtLib.searchForWebGroup(data.get("WebGrp"));
					cmtLib.clickOnTheWebGroup(data.get("WebGrp_Name"));
					cmtLib.hoverOnManageWebGroupsAndSelectOptions(data.get("ManageWebGrpOptions"));
					cmtLib.searchForaUserAndSelect(data.get("LnameEmailUname"), data.get("ContactName"));
					cmtLib.setPermissions(data.get("Menu_Name"), data.get("Set_Permission"));
					cmtLib.loginAsAdminCMT();
					// Login verification
					cmtLib.loginVerification(data.get("ContactName"));
					Thread.sleep(3000);
					//navigate to SLP
					canadaLib.clickOnSideMenuSelectAccountToolOptions(data.get("Tools_Menu"),data.get("Tools_Menu_DD"));
					Thread.sleep(2000);
					canadaLib.selectSPLADetailsProductCheckBox(data.get("SPLA"));
					Thread.sleep(4000);
					// verify search results and select first product
			     	verifysearchResultsPageForSLP();
			     	
			     	// Search for part or product and add to cart : part : 
			     	searchLib.searchInHomePage(data.get("PartNum1"));
			     	pipLib.verifyTheManufacturerNumberInProductDetailsPage(data.get("PartNum1"));
			     	pipLib.enterQuantityOnProductDetailsPage(data.get("Quantity"));
			     	commonLib.addToCartAndVerify();
			     	orderLib.continueToCheckOutOnAddCart();
			     	canadaLib.verifyPlaceCartLabel();
			     	
			     	Thread.sleep(3000);
					verifydeploydate(data.get("PartNum1"));
					verifylicensetypenotexists(data.get("PartNum1"));
					verifycopytoallnotexists(data.get("PartNum1"));
					Thread.sleep(3000);
					
					//navigate to SLP
					/*canadaLib.clickOnSideMenuSelectAccountToolOptions(data.get("Tools_Menu"),data.get("Tools_Menu_DD"));
					canadaLib.selectSPLADetailsProductCheckBox(data.get("SPLA"));
					// verify search results and select first product
			     	searchLib.verifysearchResultsPage();*/
			     	// Search for part or product and add to cart : part : 
			     	
					searchLib.searchInHomePage(data.get("PartNum2"));
			     	pipLib.verifyTheManufacturerNumberInProductDetailsPage(data.get("PartNum2"));
			     	pipLib.enterQuantityOnProductDetailsPage(data.get("Quantity"));
			     	commonLib.addToCartAndVerify();
			     	orderLib.continueToCheckOutOnAddCart();
			     	canadaLib.verifyPlaceCartLabel();
			     	Thread.sleep(3000);
					verifydeploydate(data.get("PartNum2"));
					verifylicensetype(data.get("PartNum2"));
					Thread.sleep(3000);
					/*//navigate to SLP
					canadaLib.clickOnSideMenuSelectAccountToolOptions(data.get("Tools_Menu"),  data.get("Tools_Menu_DD"));
					canadaLib.selectSPLADetailsProductCheckBox(data.get("SPLA"));
					// verify search results and select first product
			     	searchLib.verifysearchResultsPage();
			     	// Search for part or product and add to cart : part : AAA-04091
*/			     	
					
					searchLib.searchInHomePage(data.get("PartNum3"));
			     	pipLib.verifyTheManufacturerNumberInProductDetailsPage(data.get("PartNum3"));
			     	pipLib.enterQuantityOnProductDetailsPage(data.get("Quantity"));
			     	commonLib.addToCartAndVerify();
			     	orderLib.continueToCheckOutOnAddCart();
			     	canadaLib.verifyPlaceCartLabel();
			     	String date1=getDeploydateOnCart(data.get("PartNum3"));
			     	List<String> prodDesc1 = orderLib.getProductDescriptionOfCartProduct();
			     	Thread.sleep(3000);
			     	String Lisence=getlicense(data.get("PartNum3"));
			     	verifydeploydate(data.get("PartNum3"));
			     	verifylicensetype(data.get("PartNum3"));
			     	verifychangeexists(data.get("PartNum3"));
			     	Clickonunpaidlicense();
			     	verifydeployedatewithcurrentdate();
			     	verifycartDetailsWithDeployPopUpDetails(date1, prodDesc1.get(2), data.get("PartNum3"));
			     	// Enter date
			     	calenderforUnpaidLicense(data.get("Date"));
			     	//verifyPartNuminPopUpData(data.get("PartNum2"));
			     	clickapply();
			     	Thread.sleep(3000);
			        // Copy to all 
			     	clickOnCopyToAllLink(data.get("PartNum3"));
			     	Thread.sleep(4000);
			     	String part3Date=getDeploydateOnCart(data.get("PartNum3"));
			     	String part2Date=getDeploydateOnCart(data.get("PartNum2"));
			     	// verify it for 3rd part 
			     	verifyDateAppliedToAllPartAfterCopyAll(part3Date, data.get("Date1"));
			     	verifyDateAppliedToAllPartAfterCopyAll(part2Date, data.get("Date1"));
			     	verifyUpdatedUnPaidLisenceOnCartPage(data.get("PartNum2"));
			     	verifyUpdatedUnPaidLisenceOnCartPage(data.get("PartNum3"));
			     	
			     	scrollUp();
					orderLib.proceedToCheckout();
					orderLib.continueButtonOnAdditionalInformationSection();
					orderLib.clickContinueOnLineLevelInfo();
					canadaLib.verifySBP();
					 orderLib.clickContinueOnShippingAddress();
				     //orderLib.shippingOptionsCarrierSelection();
					 orderLib.billingAddressContinueButton();
					 orderLib.selectPaymentInfoMethodCreditCard(data.get("Card_Number").toString(), data.get("Card_Name"), data.get("Month"),
	        					data.get("Year"), data.get("PONumber"),data.get("POReleaseNumber"));
					 shipbLib.ClickRviewrequesition();
					 orderLib.verifyPlaceOrderLabel();
	                
	                 verifyDeploydateOnPlaceOrderPage(data.get("PartNum2"), part2Date);
	                 verifyDeploydateOnPlaceOrderPage(data.get("PartNum3"), part3Date);
	                 //verifyLicenceOnPlaceOrderPage(data.get("PartNum2"), data.get("UnPaid"));
	                 verifyUpdatedUnPaidLisenceOnCartPage(data.get("PartNum2"));
	                 verifyUpdatedUnPaidLisenceOnCartPage(data.get("PartNum3"));
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
			ReportStatus.fnUpdateResultStatus("MPSACart", "SLP16", ReportStatus.strMethodName, 1, browser);
			throw new RuntimeException(e);
		}
		finally {
        	ReportControl.fnEnableJoin();
        	ReportStatus.fnUpdateResultStatus("MPSACart", "SLP16", ReportStatus.strMethodName, counter, browser);
			fnCloseTest();
			ReportControl.fnNextTestJoin(nextTestJoin);
		}
		}
}
	

