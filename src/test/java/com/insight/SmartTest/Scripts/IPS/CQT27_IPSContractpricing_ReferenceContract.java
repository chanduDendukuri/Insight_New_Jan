package com.insight.SmartTest.Scripts.IPS;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.insight.SmartTest.Lib.HomeLib;
import com.insight.SmartTest.Lib.loginLib;
import com.insight.accelerators.ReportControl;
import com.insight.accelerators.TestEngineWeb;
import com.insight.googledrive.ReportStatus;
import com.insight.utilities.TestUtil;

public class CQT27_IPSContractpricing_ReferenceContract extends HomeLib {

	loginLib loginlib = new loginLib();
	// #############################################################################################################
	// # Name of the Test : CQT27_IPSContractpricing_ReferenceContract
	// # Migration Author : Cigniti Technologies
	// #
	// # Date of Migration : Nov 2019
	// # DESCRIPTION :
	// functionality in the products display page.
	// # Parameters : StartRow ,EndRow , nextTestJoin
	// #
	// ###############################################################################################################

	@Parameters({ "StartRow", "EndRow", "nextTestJoin" })
	@Test
	public void TC_CQT27(int StartRow, String EndRow, boolean nextTestJoin) throws Throwable {
		int counter = 0;
		try {
			int intStartRow = StartRow;
			int intEndRow = ReportControl.fnGetEndRowCunt(EndRow, "CQT27_IPSContractpricing_ReferenceContract",TestData_Smart, "Create_Quote");
			for (int intCounter = intStartRow; intCounter <= intEndRow; intCounter++) {
				try {
					counter = intCounter;
					fnOpenTest();
					ReportStatus.fnDefaultReportStatus();
					ReportControl.intRowCount = intCounter;
					Hashtable<String, String> data = TestUtil.getDataByRowNo("CQT27_IPSContractpricing_ReferenceContract", TestData_Smart, "Create_Quote", intCounter);
					TestEngineWeb.reporter.initTestCaseDescription("IPSContractpricing_ReferenceContract");

					
					navigateToApplication("SMART");
					loginlib.loginIntoSmartApplication(data.get("UserName"), data.get("Password"));
					clickOnCreateQuoteLink();
					enterSoldTo(data.get("SoldToValue"));
					// 10529929
					AddMaterialOnLineItem(data.get("MaterialID1"));// C7974A
					AddMaterialOnLineItem(data.get("MaterialID1"));// C7974A
					ClickOnXsymbolunderCon();
					selectCOntractSubTabName(data.get("contactTabName"));// Contracts
					clickOnContractId(data.get("contractid2"));
					clickDoneButton();
					
					clickOnLItem00020CON("000020", "con");
					selectCOntractSubTabName(data.get("contactTabName"));// Contracts
					clickOnContractId(data.get("contractid2"));
					clickDoneButton();
					
					clickUpdateCosting();
					ClickOnsideViewBar();
					clickonSaveasQuote();
					SelectAdherencetoflooroption("Client Satisfaction","uft TEST");
					String QuoteNumber = GetQuoteNumber();
					if(QuoteNumber!=null) {
						reporter.SuccessReport("QuoteNumber:", "Quotenumber is displayed", "");
					}
					else {
						reporter.failureReport("QuoteNumber:", "Quotenumber is not displayed", "",driver);
					}
					comparePriceValuesInLineitems(data.get("value1"),data.get("value2"));
					clickOnCOntractIDinLineItemsList();
					selectCOntractSubTabName(data.get("contractTab1"));// Pricing
					// Get data from the pricing tab
					List<String> Price = new ArrayList<>();
					int a = Integer.parseInt(data.get("Value"));
					Price = getPriceValueFromPricingTab(data.get("idValue"), data.get("expValue"), a);// ZPFX--0
					System.out.println(Price.size());
					String Price1 = "";
					float price1value = 0;
					for (int b = 0; b < Price.size(); b++) {
						if(b==0){ //ZPFX--0
	                    	 Price1 = Price.get(b);
	                    	 String P1 = Price1.replace(",", "");
	                         price1value = Float.parseFloat(P1);
	                    	}
					}
					clickonRightArrowforLineItem();
					
					List<String> Price12 = new ArrayList<>();
					int b = Integer.parseInt(data.get("Value"));
					Price12 = getPriceValueFromPricingTab(data.get("idValue"), data.get("expValue"), a);// ZPFX--0
					System.out.println(Price12.size());
					String Price2 = "";
					float price2value = 0;
					for (int b1 = 0; b1 < Price.size(); b1++) {
						if(b1==0){ //ZPFX--0
	                    	 Price2 = Price12.get(b1);
	                    	 String P2 = Price2.replace(",", "");
	                         price2value = Float.parseFloat(P2);
	                    	}
					}
					clickDoneButton();
					clickOnCOntractIDinLineItemsList();
					selectCOntractSubTabName(data.get("contactTabName"));// Contracts
					float sellprice1 =getSellPriceFromContract(data.get("contractid2"));
					//float sellprice1 =  (float) contract1;
					clickonRightArrowforLineItem();
					float sellprice2 =getSellPriceFromContract(data.get("contractid2"));
					//float sellprice2 = (float) contract2;				
					clickDoneButton();
					if(price1value==price2value){
						reporter.SuccessReport("Verify the ZPFX value for line 10 and 20 are same.", "Line 10 & 20 ZPFX values are same",
								"Line 10 ZPFX value: "+Price1+" -- Line 20 ZPFX value: "+Price2);
					} else {
						reporter.failureReport("Verify the ZPFX value for line 10 and 20 are same.", "Line 10 & 20 ZPFX values are same",
								"Line 10 ZPFX value: "+Price1+" -- Line 20 ZPFX value: "+Price2, driver);
					}
					if(sellprice1==sellprice2){
						reporter.SuccessReport("verify the values of contract ", "contract "+data.get("contractid1")+" equals to contract "+data.get("contractid2"),
								data.get("contractid1")+ "Sell Price : "+sellprice1 + data.get("contractid2")+" Sell Price : "+sellprice2);
					} else {
						reporter.failureReport("verify the values of contract",  data.get("contractid1")+" not equals to contract "+ data.get("contractid2"), 
								data.get("contractid1")+" Sell Price : "+sellprice1 + data.get("contractid2")+" Sell Price : "+sellprice2, driver);
					}
					String Qn = GetQuoteNumber();
					
					clickClosthedocument(Qn);
					clickYesButtontocloseDocument();

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
			ReportStatus.fnUpdateResultStatus("CQT27_IPSContractpricing_ReferenceContract", "TC_027",
					ReportStatus.strMethodName, 1, browser);
			throw new RuntimeException(e);
		} finally {
			ReportControl.fnEnableJoin();
			ReportStatus.fnUpdateResultStatus("CQT27_IPSContractpricing_ReferenceContract", "TC_027",
					ReportStatus.strMethodName, counter, browser);
			fnCloseTest();
			ReportControl.fnNextTestJoin(nextTestJoin);
		}
	}

}
