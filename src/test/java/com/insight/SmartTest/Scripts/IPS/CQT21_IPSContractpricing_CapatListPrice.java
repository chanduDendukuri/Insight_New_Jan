package com.insight.SmartTest.Scripts.IPS;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.insight.SmartTest.Lib.HomeLib;
import com.insight.SmartTest.Lib.loginLib;
import com.insight.accelerators.ReportControl;
import com.insight.accelerators.TestEngineWeb;
import com.insight.googledrive.ReportStatus;
import com.insight.utilities.TestUtil;

public class CQT21_IPSContractpricing_CapatListPrice extends HomeLib {
	
	loginLib loginlib=new loginLib();
	// #############################################################################################################
				// # Name of the Test : CQT21_IPSContractpricing_CapatListPrice
				// # Migration Author : Cigniti Technologies
				// #
				// # Date of Migration : Nov 2019
				// # DESCRIPTION : This Test is used to verify IPSContractpricing_CapatListPrice
				// functionality in the products display page.
				// # Parameters : StartRow ,EndRow , nextTestJoin
				// #
				// ###############################################################################################################

				@Parameters({ "StartRow", "EndRow", "nextTestJoin" })
				@Test
				public void TC_CQT21(int StartRow, String EndRow, boolean nextTestJoin) throws Throwable {
					int counter = 0;
			        try {
				    
					int intStartRow = StartRow;
					int intEndRow = ReportControl.fnGetEndRowCunt(EndRow, "CQT21_IPSContractpricing_CapatListPrice",TestData_Smart,"Create_Quote");
					for (int intCounter = intStartRow; intCounter <= intEndRow; intCounter++) {
						try {
						counter = intCounter;
						fnOpenTest();
						ReportStatus.fnDefaultReportStatus();
						ReportControl.intRowCount = intCounter;
						Hashtable<String, String> data = TestUtil.getDataByRowNo("CQT21_IPSContractpricing_CapatListPrice", TestData_Smart, "Create_Quote", intCounter);
						TestEngineWeb.reporter.initTestCaseDescription("IPSContractpricing_CapatListPrice");
						LoginToApplicationAndSearchForSoldToAct(data.get("UserName"), data.get("Password"), data.get("SoldToValue"));
					/*
					 * navigateToApplication("SMART");
					 * loginlib.loginIntoSmartApplication(data.get("UserName"),data.get("Password"))
					 * ; clickOnCreateQuoteLink(); enterSoldToValue(data.get("SoldToValue"));
					 * clickOnSoldToSearchIcon();//10529929
					 */		AddMaterialOnLineItem(data.get("MaterialID"));	
							clickOnCOntractIDinLineItemsList();
							selectCOntractID(data.get("contactid"),data.get("contactTab"));
							clickDoneButton();
							clickUpdateCosting();
							
							//ClickOnsideViewBar();
							clickonSaveasQuote();
							enterCancelButtonInPoupHdr();
							String QuoteNum= GetQuoteNumber();
							clickOnCOntractIDinLineItemsList();
							selectCOntractSubTabName(data.get("contactTab2"));//Pricing
							
							//Get data from the pricing tab
							List<String> Price = new ArrayList<>();
							int a = Integer.parseInt(data.get("Value"));
							Price = getPriceValueFromPricingTab(data.get("idValue"), data.get("expValue"), a);// ZPFX--0
							System.out.println(Price.size());
							 String	Price1="";
			                    String	Price2="";
			                    String	Price3="";
			                    String	Price4="";
			                    float price1value = 0, price2value =0,price3value =0,price4value = 0;
							 for(int b =0;b<Price.size();b++){
			                    	if(b==0){ //ZPLS--0
			                    	 Price1 = Price.get(b);
			                    	 String P1 = Price1.replace(",", "");
			                         price1value = Float.parseFloat(P1);
			                    	}
			                    	if(b==1){  // ZPFX--1
			                            Price2 = Price.get(b);
			                            String P2 = Price2.replace(",", "");
			                            price2value = Float.parseFloat(P2);
			                    	}
			                    	if(b==2){   // ZP00--2
			                            Price3 = Price.get(b);
			                            String P3 = Price3.replace(",", "");
			                            price3value = Float.parseFloat(P3);
			                    	}
			                    	if(b==3){    // YP00--3
			                           Price4 = Price.get(b);
			                           price4value = Float.parseFloat(Price4);
			                    	}
			                    }
			                    
			                   				
							selectCOntractID(data.get("contactid"),data.get("contactTab"));//Contracts
							VerifyContractPriceShouldbeEqualToYPOO(Price4,data.get("contactid"));
							clickDoneButton();
							//Need to compare pricing
							VerifyZPFXShouldbeGreaterThanZPLS(price2value,price1value);
							VerifyZPLSShouldbeEqualToZPOOandYPOO(price1value,price3value,price4value ); //	Verify ZPLS should equal YP00 & ZP00
  						    
							clickSideBarSmart();
							clickClosthedocument(QuoteNum);
							System.out.println("Test case completed");		
							
							
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
					//ReportStatus.fnUpdateResultStatus("CQT21_IPSContractpricing_CapatListPrice", "TC_CQT21", ReportStatus.strMethodName, 1, browser);
					throw new RuntimeException(e);
				}
			    finally {
					ReportControl.fnEnableJoin();
					ReportStatus.fnUpdateResultStatus("CQT21_IPSContractpricing_CapatListPrice", "TC_CQT21", ReportStatus.strMethodName, counter, browser);
					fnCloseTest();
					ReportControl.fnNextTestJoin(nextTestJoin);
				}
			}


			}

