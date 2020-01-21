package com.insight.SmartTest.Scripts.IPS;

import java.util.Hashtable;
import java.util.concurrent.TimeUnit;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.insight.SmartTest.Lib.HomeLib;
import com.insight.SmartTest.Lib.loginLib;
import com.insight.accelerators.ReportControl;
import com.insight.accelerators.TestEngineWeb;
import com.insight.googledrive.ReportStatus;
import com.insight.utilities.TestUtil;

public class CQT45_IPSContractPricingRoundingWithMultipleQtysTest extends HomeLib {
	
	loginLib loginlib = new loginLib();

	// #############################################################################################################
	// # Name of the Test : CQT45_IPSContractPricingRoundingWithMultipleQtys
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
	public void TC_CQT45(int StartRow, String EndRow, boolean nextTestJoin)
			throws Throwable {
		int counter = 0;
		try {
			int intStartRow = StartRow;
			int intEndRow = ReportControl.fnGetEndRowCunt(EndRow, "CQT45_IPSContractPricingRoundingWithMultipleQtys",
					TestData_Smart, "Create_Quote");
			for (int intCounter = intStartRow; intCounter <= intEndRow; intCounter++) {
				try {
					counter = intCounter;
					fnOpenTest();
					ReportStatus.fnDefaultReportStatus();
					ReportControl.intRowCount = intCounter;
					Hashtable<String, String> data = TestUtil.getDataByRowNo(
							"CQT45_IPSContractPricingRoundingWithMultipleQtys", TestData_Smart, "Create_Quote",
							intCounter);
					TestEngineWeb.reporter.initTestCaseDescription("IPSContractPricingRoundingWithMultipleQtys");
					MethodfromSmartLoginTillSalesOrgselection(data.get("UserName"), data.get("Password"),
							data.get("SoldToAcct"), data.get("SalesOrg"));
					//Add material
					Addmaterail(data.get("Material1"));
					Addmaterail(data.get("Material2"));
					Addmaterail(data.get("Material3"));
					Addmaterail(data.get("Material4"));
					
					//Add Quantity
					AddQuantity(data.get("Quantity"), data.get("LineItem1"));
					AddQuantity(data.get("Quantity"), data.get("LineItem2"));
					AddQuantity(data.get("Quantity"), data.get("LineItem3"));
					AddQuantity(data.get("Quantity"), data.get("LineItem4"));
					//click on contract of LineItems
					clickonConXSystem(data.get("LineItem1"));// 000010
					clickOnContractId(data.get("sw_contactid1"));
					clickonNextLineItemArrowsymbolinPopUp();
					clickOnContractId(data.get("sw_contactid2"));
					clickonNextLineItemArrowsymbolinPopUp();
					clickOnContractId(data.get("sw_contactid3"));
					clickOnTabsInLineItemDetailsPopUp(data.get("Tab"));//General
					selectReasonForRejectioninItemdetailsPopup(data.get("Reason"));
					clickonNextLineItemArrowsymbolinPopUp();
					clickOnTabsInLineItemDetailsPopUp(data.get("Tab1"));//Contract
					clickOnContractId(data.get("sw_contactid4"));
					clickDoneButton();
					clickUpdateCosting();
					String Pricevalue = getPriceofLineItem("000030");
					if(Pricevalue.equals("99,999.99")){
						reporter.SuccessReport("LineItem000030 Price value: ", "Price in Line 3 is 99,999.99", "");
					}
					else {
						reporter.failureReport("LineItem000030 Price value: ", "Price in Line 3 is not 99,999.99", "", driver);
					}
					setPrice(data.get("LineItem3"),data.get("price"));
					clickUpdateCosting();
					//verify Price
					String expprice1 = getextPricevalue("000010", "extPrice") ;
					String expprice2 = getextPricevalue("000020", "extPrice") ;
					String expprice3= getextPricevalue("000030", "extPrice") ;
					String expprice4 = getextPricevalue("000040", "extPrice") ;
					String price1=getPriceofLineItem(data.get("LineItem1"));
					verifyPriceofLineitem(price1,expprice1,data.get("LineItem1"));
					String price2=getPriceofLineItem(data.get("LineItem2"));
					verifyPriceofLineitem(price2,expprice2,data.get("LineItem2"));
					String price3=getPriceofLineItem(data.get("LineItem3"));
					verifyPriceofLineitem(price3,expprice3,data.get("LineItem3"));
					String price4=getPriceofLineItem(data.get("LineItem4"));
					verifyPriceofLineitem(price4,expprice4,data.get("LineItem4"));
					String TotalOrderRevenuevalue = getTotalOrderrevenue();
					verifyTotalOrderRevenue(TotalOrderRevenuevalue);
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
			// ReportStatus.fnUpdateResultStatus("IPSContractPricingRoundingWithMultipleQtys",
			// "CQT_45", ReportStatus.strMethodName, 1, browser);
			throw new RuntimeException(e);
		} finally {
			ReportControl.fnEnableJoin();
			ReportStatus.fnUpdateResultStatus("IPSContractPricingRoundingWithMultipleQtys", "CQT_45",
					ReportStatus.strMethodName, counter, browser);
			fnCloseTest();
			ReportControl.fnNextTestJoin(nextTestJoin);
		}
	}

}
