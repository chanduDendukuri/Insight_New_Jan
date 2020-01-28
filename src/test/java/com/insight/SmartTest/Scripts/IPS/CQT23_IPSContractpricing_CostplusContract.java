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

  
public class CQT23_IPSContractpricing_CostplusContract extends HomeLib {
	
	loginLib loginlib=new loginLib();
	// #############################################################################################################
				// # Name of the Test : CQT23_IPSContractpricing_CostplusContract
				// # Migration Author : Cigniti Technologies
				// #
				// # Date of Migration : Nov 2019
				// # DESCRIPTION : This Test is used to verify IPSContractpricing_CostplusContract
				// functionality in the products display page.
				// # Parameters : StartRow ,EndRow , nextTestJoin
				// #
				// ###############################################################################################################

				@Parameters({ "StartRow", "EndRow", "nextTestJoin" })
				@Test
				public void TC_CQT23(int StartRow, String EndRow, boolean nextTestJoin) throws Throwable {
					int counter = 0;
			        try {
				    
					int intStartRow = StartRow;
					int intEndRow = ReportControl.fnGetEndRowCunt(EndRow, "CQT23_IPSContractpricing_CostplusContract",TestData_Smart,"Create_Quote");
					for (int intCounter = intStartRow; intCounter <= intEndRow; intCounter++) {
						try {
						counter = intCounter;
						fnOpenTest();
						ReportStatus.fnDefaultReportStatus();
						ReportControl.intRowCount = intCounter;
						Hashtable<String, String> data = TestUtil.getDataByRowNo("CQT23_IPSContractpricing_CostplusContract", TestData_Smart, "Create_Quote", intCounter);
						TestEngineWeb.reporter.initTestCaseDescription("IPSContractpricing_CostplusContract");
						LoginToApplicationAndSearchForSoldToAct(data.get("UserName"), data.get("Password"), data.get("SoldToValue"));
							AddMaterialOnLineItem(data.get("MaterialID1"));
							AddMaterialOnLineItem(data.get("MaterialID2"));
							AddMaterialOnLineItem(data.get("MaterialID3"));
							VerifyXconsymbolispresentforallthematerials(data.get("ItemNum1"));
							VerifyXconsymbolispresentforallthematerials(data.get("ItemNum1"));
							VerifyXconsymbolispresentforallthematerials(data.get("ItemNum1"));
							clickOnCOntractIDinLineItemsList();
							selectCOntractID(data.get("ContractId"),data.get("ContractTabName"));
							copyAllContractstoAllLines();
							clickDoneButton();
							clickUpdateCosting();
							
							//clickSideBarSmart();
							clickonSaveasQuote();
							enterCancelButtonInPoupHdr();
							String QuoteNum= GetQuoteNumber();
							clickOnCOntractIDinLineItemsList();
							selectCOntractSubTabName(data.get("ContractTab2"));//Pricing
							
							//Get data from the pricing tab
							List<String> Price = new ArrayList<>();
							int a = Integer.parseInt(data.get("Value"));
							Price = getPriceValueFromPricingTab(data.get("idValue"), data.get("expValue"), a);// Z0RC--0
							 System.out.println(Price.size());
			                    String	Price1="";
			                    String	Price2="";
			                    String	Price3="";
			                    String	Price4="";
			                    float price1value = 0, price2value =0,price3value =0,price4value = 0;
			                    for(int b =0;b<Price.size();b++){
			                    	if(b==0){ //ZP00--0
			                    	 Price1 = Price.get(b);
			                    	 String P1 = Price1.replace(",", "");
			                         price1value = Float.parseFloat(P1);
			                    	}
			                    	if(b==1){  // ZORC--1
			                            Price2 = Price.get(b);
			                            String P2 = Price2.replace(",", "");
			                            price2value = Float.parseFloat(P2);
			                    	}
			                    	if(b==2){   // YP00--2
			                            Price3 = Price.get(b);
			                            String P3 = Price3.replace(",", "");
			                            price3value = Float.parseFloat(P3);
			                    	}
			                    	if(b==3){    // YMSM--3
			                           Price4 = Price.get(b);
			                           String P4 = Price4.replace(",", "");
			                           price4value = Float.parseFloat(P4);
			                    	}
			                    }
							selectCOntractID(data.get("ContractId"),data.get("ContractTabName"));
							clickDoneButton();
							//Need to compare pricing
							VerifyZ0RCPlusYMSMequalstheYP00andZP00( price2value, price4value, price3value );
							VerifyZPOOShouldbeEqualToYPOO(price1value,price3value);
							clickOnCOntractIDinLineItemsList();
							selectCOntractSubTabName(data.get("ContractTabName"));
							VerifyContractPriceShouldbeEqualToYPOO(price3value,data.get("ContractId"));
							clickDoneButton();
							clickSideBarSmart();
							clickClosthedocument(QuoteNum);
							System.out.println("test case completed");			
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
					//ReportStatus.fnUpdateResultStatus("CQT23_IPSContractpricing_CostplusContract", "TC_CQT23", ReportStatus.strMethodName, 1, browser);
					throw new RuntimeException(e);
				}
			    finally {
					ReportControl.fnEnableJoin();
					ReportStatus.fnUpdateResultStatus("CQT23_IPSContractpricing_CostplusContract", "TC_CQT23", ReportStatus.strMethodName, counter, browser);
					fnCloseTest();
					ReportControl.fnNextTestJoin(nextTestJoin);
				}
			}


			}


