package com.insight.SmartTest.Scripts.IPS;

import com.insight.SmartTest.Lib.HomeLib;
import com.insight.SmartTest.Lib.ObjectsLib;
import com.insight.SmartTest.Lib.loginLib;
import com.insight.accelerators.ActionEngine;
import com.insight.accelerators.ReportControl;
import com.insight.accelerators.TestEngineWeb;
import com.insight.googledrive.ReportStatus;
import com.insight.utilities.TestUtil;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

public class CQT24_IPSContractpricingFixedPriceContract extends ActionEngine {

	// #############################################################################################################
		// #       Name of the Test         :  CQT24_IPSContractpricingFixedPriceContract
		// #       Migration Author         :  Cigniti Technologies
		// #
		// #       Date of Migration        : November 2019
		// #       DESCRIPTION              : This method is to verify SWEPQuote
		// #       Parameters               : StartRow ,EndRow , nextTestJoin
		// #
		// ###############################################################################################################

	@Parameters({ "StartRow", "EndRow", "nextTestJoin" })
	@Test()

	public void TC_CQT24(int StartRow,String EndRow, boolean nextTestJoin) throws Throwable {
		int counter = 0;
		try {
				int intStartRow = StartRow;
				int intEndRow = ReportControl.fnGetEndRowCunt(EndRow, "CQT24_IPSContractpricingFixedPriceContract",TestData_Smart,"Create_Quote");
				for (int intCounter = intStartRow; intCounter <= intEndRow; intCounter++) {

					
					try {
						counter = intCounter;
						fnOpenTest();
						ReportStatus.fnDefaultReportStatus();
						ReportControl.intRowCount = intCounter;
						Hashtable<String, String> data = TestUtil.getDataByRowNo("CQT24_IPSContractpricingFixedPriceContract", TestData_Smart, "Create_Quote", intCounter);
						TestEngineWeb.reporter.initTestCaseDescription("IPSContractpricingFixedPriceContract");
					HomeLib home=new HomeLib();
					loginLib  login=new loginLib();
					ObjectsLib  object=new ObjectsLib();
					navigateToApplication("SMART");
					//Login functionality
					login.loginIntoSmartApplication(data.get("username"),data.get("password"));
					//Home Page
					home.clickCreateQuoteButton();
					home.clickSideBarSmart();
					home.enterSoldTo("0010529929");
					//home.clickAdvancedHeader();
					//home.clickAdvancedHeaderTab(data.get("contracts"));
					home.clickOnProductSearchButton();
					object.loadingSymbol();
					if(object.verifyAvailabilityOfProductSearchPopup())
					{

						String inputValue = data.get("keyword");
						String facilityCodeValue[] = inputValue.split("#");
						for(int i=0;i<facilityCodeValue.length;i++) {
							if(!object.verifyAvailabilityOfKeywordSearchTextField()){
								home.clickOnProductSearchButton();
							}
							object.enterKeywordInProductSearchWindow(facilityCodeValue[i]);
							object.clickOnSearchButtonInProductSearchWindow();
							object.loadingSymbol();
							if (object.clickOnMaterialID()) {
								
								if (home.verifyAddToOrderPopup()) {
									object.loadingSymbol();
									home.clickOnAddToOrderButton();
									object.closebuttonInProductSearch();
								} else {
									reporter.failureReport("Add to Order Popup", "Add to order popup is not visible", "", driver);
								}
							}else {
								reporter.failureReport("Results found Status","No results found","",driver);
							}
						}

									object.clickOnCrossiConUnderConColSingleRow();
									if(object.availabilityOfItemDetailsPopup())
									{
										home.clickOnTabsInLineItemDetailsPopUp(data.get("Contracts"));
										object.clickOnContactIDByPassingValueFromExcel(data.get("ContractID"));
										home.clickOnCopyContarctToallLineItems();
										home.clickYesButtontocloseDocument();
										object.clickOnDoneButtonInContractsTab();
										
										home.clickUpdateCosting();
										home.clickonSaveasQuote();
										
										home.clickCancel();
										String QuoteNumber=object.getQuoteNumberValue();
										object.clickOnCrossiConUnderConColSingleRow();
										object.clickOnContactIDByPassingValueFromExcel(data.get("ContractID"));
										float sellingPrice=home.getSellPriceFromContract(data.get("ContractID"));
										
										//String sellingPrice11=object.getContractPricingFromTable(data.get("ContractID"));
										//String sellingPrice=object.getSellPriceFromContract(data.get("ContractID"));

										home.clickOnTabsInLineItemDetailsPopUp("Pricing");
										
										List<String> Price = new ArrayList<>();
										int a = Integer.parseInt(data.get("Value"));
										Price = home.getPriceValueFromPricingTab(data.get("idValue"), data.get("priceType"), a);
										System.out.println(Price.size());
					                    String	Price1="";
					                    
					                    float priceValue = 0;
					                    for(int b =0;b<Price.size();b++){
					                    	if(b==0){ //ZPFX--0
					                    	 Price1 = Price.get(b);
					                    	 String P1 = Price1.replace(",", "");
					                    	 priceValue = Float.parseFloat(P1);
					                    	}
					                    }
					                    home.clickOnTabsInLineItemDetailsPopUp("Contracts");
					                    home.clickOnTabsInLineItemDetailsPopUp("Pricing");
					                    List<String> Price11 = new ArrayList<>();
										
										Price11 = home.getPriceValueFromPricingTab(data.get("idValue"), data.get("priceType1"), a);
										System.out.println(Price11.size());
					                    String	Price2="";
					                    
					                    float priceValue1 = 0;
					                    for(int b =0;b<Price11.size();b++){
					                    	if(b==0){ //ZP00--0
					                    	 Price2 = Price11.get(b);
					                    	 String P1 = Price2.replace(",", "");
					                    	 priceValue1 = Float.parseFloat(P1);
					                    	}
					                    }
										

										//String priceValue=object.selectproductinPricingTable(data.get("priceType"));
										//String priceValue1=object.selectproductinPricingTable(data.get("priceType1"));

										//******************* Validations
if(data.get("TCID").equalsIgnoreCase("CQT24")) {
	if (priceValue==priceValue1) {
		reporter.SuccessReport("Price value comparision", data.get("priceType") + "Value is " + priceValue + " and  " + data.get("priceType1") + "Value is " + priceValue1 + "both are same", "");
	} else {
		reporter.failureReport("Price value comparision", data.get("priceType") + "Value is " + priceValue + " and  " + data.get("priceType1") + "Value is " + priceValue1 + "both are same", "", driver);
	}
	if (sellingPrice==priceValue) {
		reporter.SuccessReport("Price value comparision", "both are same", "");
	} else {
		reporter.failureReport("Price value comparision", "both are same", "", driver);
	}
}

}else {
		reporter.failureReport("Item details popup: ", "item details pop up not opened", "", driver);								//Item Details popup failure
	}
					}
					else {
						reporter.failureReport("Item details popup: ", "item details pop up not opened", "", driver);		
					}


					System.out.println("Test completed");
				}  catch (Exception e) {
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
			//ReportStatus.fnUpdateResultStatus("CQT24_IPSContractpricingFixedPriceContract", "TC_CQT24", ReportStatus.strMethodName, 1, browser);
			throw new RuntimeException(e);
		}
	    finally {
			ReportControl.fnEnableJoin();
			ReportStatus.fnUpdateResultStatus("CQT24_IPSContractpricingFixedPriceContract", "TC_CQT24", ReportStatus.strMethodName, counter, browser);
			fnCloseTest();
			ReportControl.fnNextTestJoin(nextTestJoin);
		}
	}
}