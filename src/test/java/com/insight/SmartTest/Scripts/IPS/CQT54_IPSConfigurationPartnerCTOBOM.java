package com.insight.SmartTest.Scripts.IPS;

import java.util.Hashtable;
import java.util.concurrent.TimeUnit;

import org.apache.tools.ant.types.CommandlineJava.SysProperties;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.insight.SmartTest.Lib.HomeLib;
import com.insight.SmartTest.Lib.loginLib;
import com.insight.accelerators.ReportControl;
import com.insight.accelerators.TestEngineWeb;
import com.insight.googledrive.ReportStatus;
import com.insight.utilities.TestUtil;

public class CQT54_IPSConfigurationPartnerCTOBOM extends HomeLib {
	
	loginLib loginlib=new loginLib();
	
	// #############################################################################################################
				// # Name of the Test : CQT54_IPSConfigurationPartnerCTOBOM
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
				public void TC_CQT54(int StartRow, String EndRow, boolean nextTestJoin) throws Throwable {
					int counter = 0;
			        try {
				    
					int intStartRow = StartRow;
					int intEndRow = ReportControl.fnGetEndRowCunt(EndRow, "CQT54_IPSConfigurationPartnerCTOBOM",TestData_Smart,"Create_Quote");
					for (int intCounter = intStartRow; intCounter <= intEndRow; intCounter++) {
						try {
						counter = intCounter;
						fnOpenTest();
						ReportStatus.fnDefaultReportStatus();
						ReportControl.intRowCount = intCounter;
						Hashtable<String, String> data = TestUtil.getDataByRowNo("CQT54_IPSConfigurationPartnerCTOBOM", TestData_Smart, "Create_Quote", intCounter);
						TestEngineWeb.reporter.initTestCaseDescription("IPSContractpricing_InsightListminusContract");
						LoginToApplicationAndSearchForSoldToAct(data.get("UserName"), data.get("Password"), data.get("SoldToValue"));
							AddMaterialOnLineItem(data.get("MaterialID1"));
							//AddMaterialOnLineItem(data.get("MaterialID2"));
							//AddMaterialOnLineItem(data.get("MaterialID3"));
							//AddMaterialOnLineItem(data.get("MaterialID4"));
							//AddMaterialOnLineItem(data.get("MaterialID5"));
							
							clickOnRedCrossSymbolinLineItemsList();
							//Need to add materials in VC tab
							AddMaterialOnVCTab(data.get("MaterialID2"), 0, "material");
							AddMaterialOnVCTab(data.get("MaterialID3"),1, "material");
							AddMaterialOnVCTab(data.get("MaterialID4"),2, "material");
							AddMaterialOnVCTab(data.get("MaterialID5"), 3, "material");
							
							
							clickOnVCTAbUpdateButton();
							clickDoneButton();
							
							clickOnCOntractIDinLineItemsList();
							selectCOntractID(data.get("contactid"),data.get("contactTabName"));
							copyAllContractstoAllLines();
							clickDoneButton();
							clickUpdateCosting();
							
							
							String Price1= getPriceValueFromLineItemTable(data.get("PriceValue1"));//2
							float P1= Float.parseFloat(Price1);
							String Price2= getPriceValueFromLineItemTable(data.get("PriceValue2"));//3
							float P2= Float.parseFloat(Price2);
							String Price3= getPriceValueFromLineItemTable(data.get("PriceValue3"));//4
							float P3= Float.parseFloat(Price3);
							String Price4= getPriceValueFromLineItemTable(data.get("PriceValue4"));	//5
							float P4= Float.parseFloat(Price4);
							float totalPriceofFour = P1+P2+P3+P4;
							System.out.println("totalPriceofFour: "+totalPriceofFour);
							String TotalPrice1 = getFirstElementPriceValueFromLineItem();
							String TotalPrice = TotalPrice1.replace(",", "");
							float PricefoLine10= Float.parseFloat(TotalPrice);
							System.out.println("PricefoLine10: "+PricefoLine10);
							//Need to validate price values
							if (totalPriceofFour == PricefoLine10) {
								reporter.SuccessReport("Confirm the sell price on line 10 equals the combined total of lines 20-50","The sell price on line 10 equals the combined total of lines 20-50",
										"Sell Price on Line 10: "+PricefoLine10+"/ Total Price of lines 10-50:"+totalPriceofFour);
							} else {
								reporter.failureReport("Confirm the sell price on line 10 equals the combined total of lines 20-50", "The sell price on line 10 not equals to total of lines 20-50",
										"", driver);
							}
							
							//clickSideBarSmart();
							clickonSaveasQuote();
							
							enterCancelButtonInPoupHdr();
							String QuoteNum1= GetQuoteNumber();
							if(QuoteNum1!=null) {
								reporter.SuccessReport("QuoteNumber:", "Quotenumber is displayed", "");
							}
							else {
								reporter.failureReport("QuoteNumber:", "Quotenumber is not displayed", "",driver);
							}
							//click change mode button
							ClickOnDisplayMode();				
							enterRepMarginPercent(data.get("RepPercentage"),1); //4
							enterRepMarginPercent(data.get("RepPercentage"),2);
							enterRepMarginPercent(data.get("RepPercentage"),3);
							enterRepMarginPercent(data.get("RepPercentage"),4);
							clickUpdateCosting();
							String FinalPrice1= getPriceValueFromLineItemTable(data.get("PriceValue1"));
							float FP1= Float.parseFloat(FinalPrice1);
							String FinalPrice2= getPriceValueFromLineItemTable(data.get("PriceValue2"));
							float FP2= Float.parseFloat(FinalPrice2);
							String FinalPrice3= getPriceValueFromLineItemTable(data.get("PriceValue3"));
							float FP3= Float.parseFloat(FinalPrice3);
							String FinalPrice4= getPriceValueFromLineItemTable(data.get("PriceValue4"));
							float FP4= Float.parseFloat(FinalPrice4);
							float FinalPriceofFour = FP1+FP2+FP3+FP4;
							String TotalPriceAfterUpdation1 = getFirstElementPriceValueFromLineItem();
							String TotalPriceAfterUpdation = TotalPriceAfterUpdation1.replace(",", "");
							float PricefoLine10Value= Float.parseFloat(TotalPriceAfterUpdation);

							if (PricefoLine10Value==FinalPriceofFour) {
								reporter.SuccessReport("Confirm the sell price on line 10 equals the combined total of lines 20-50","The sell price on line 10 equals the combined total of lines 20-50",
										"Sell Price on Line 10: "+PricefoLine10Value+"/ Total Price of lines 10-50:"+FinalPriceofFour);
							} else {
								reporter.failureReport("Confirm the sell price on line 10 equals the combined total of lines 20-50", "The sell price on line 10 not equals to total of lines 20-50",
										"", driver);
							}
								
							clickSideBarSmart();
							clickClosthedocument(QuoteNum1);
							
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
			//ReportStatus.fnUpdateResultStatus("CQT54_IPSConfigurationPartnerCTOBOM", "CQT_54", ReportStatus.strMethodName, 1, browser);
			throw new RuntimeException(e);
		}
        finally {
			ReportControl.fnEnableJoin();
			ReportStatus.fnUpdateResultStatus("CQT54_IPSConfigurationPartnerCTOBOM", "CQT_54", ReportStatus.strMethodName, counter, browser);
			fnCloseTest();
			ReportControl.fnNextTestJoin(nextTestJoin);
		}
	}


}