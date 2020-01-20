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

public class CQT49_IPSCappingAtListPriceWithTICTest extends HomeLib{
	
	loginLib loginlib=new loginLib();

	// #############################################################################################################
		// # Name of the Test : CQT49_IPSCappingAtListPriceWithTIC
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
		public void TC_CQT49(int StartRow, String EndRow, boolean nextTestJoin) throws Throwable {
			int counter = 0;
			try {
				int intStartRow = StartRow;
				int intEndRow = ReportControl.fnGetEndRowCunt(EndRow, "CQT49_IPSCappingAtListPriceWithTIC", TestData_Smart, "Create_Quote");
				for (int intCounter = intStartRow; intCounter <= intEndRow; intCounter++) {
					try {
						counter = intCounter;
					fnOpenTest();
					ReportStatus.fnDefaultReportStatus();
					ReportControl.intRowCount = intCounter;
					Hashtable<String, String> data = TestUtil.getDataByRowNo("CQT49_IPSCappingAtListPriceWithTIC",TestData_Smart, "Create_Quote", intCounter);
					TestEngineWeb.reporter.initTestCaseDescription("IPSCappingAtListPriceWithTIC");
					MethodfromSmartLoginTillSalesOrgselection(data.get("UserName"), data.get("Password"),
							data.get("SoldToAcct"), data.get("SalesOrg"));
					Addmaterail(data.get("Material1"));
					Addmaterail(data.get("Material2"));
					Addmaterail(data.get("Material3"));
					clickonConXSystem(data.get("ItemNum1"));// 000010
					clickOnContractId(data.get("contactid"));
					clickOnCopyContarctToallLineItems();
					clickYesButtontocloseDocument();
					clickonNextLineItemArrowsymbolinPopUp();
					clickonNextLineItemArrowsymbolinPopUp();
					clickDoneButton();
					
					clickUpdateCosting();
					
					clickonConXSystem(data.get("ItemNum1"));// 000010
					clickOnTabsInLineItemDetailsPopUp(data.get("Tab1"));// Pricing
					// Get data from the pricing tab
					List<String> Price = new ArrayList<>();
					int a= Integer.parseInt(data.get("Value1"));
					Price = getPriceValueFromPricingTab(data.get("idValue"), data.get("expValue1"),a);
                    System.out.println(Price.size());
                    String	Price1="";
                    String	Price2="";
                    String	Price3="";
                    
                    float price1value = 0, price2value =0,price3value =0,price4value = 0;
                    for(int b =0;b<Price.size();b++){
                    	if(b==0){ //zpls--0
                    	 Price1 = Price.get(b);
                    	 String P1 = Price1.replace(",", "");
                         price1value = Float.parseFloat(P1);
                         
                    	}
                    	if(b==1){  // yp00--1
                            Price2 = Price.get(b);
                            String P2 = Price2.replace(",", "");
                            price2value = Float.parseFloat(P2);
                    	}
                    	if(b==2){   //zp00--2
                            Price3 = Price.get(b);
                            String P3 = Price3.replace(",", "");
                            price3value = Float.parseFloat(P3);
                    	}
                    	
                    	
                    }
                  
					clickOnTabsInLineItemDetailsPopUp(data.get("Tab2"));//Contracts
					String ContractId=getContractId(data.get("contactid"));
					VerifyContractPriceShouldbeEqualToYPOO(price2value,ContractId);
					VerifyContractPriceShouldbeEqualToZPOO(price3value,ContractId);
					VerifyContractPriceShouldbeEqualToZPLS(price1value,ContractId);
					clickDoneButton();
                    //Update Trade in Valude for line item 10
					
					EnterTradeInAmtvalue("000010", "tradeIn",  "20");
					EnterTradeInAmtvalue("000020", "tradeIn",  "5");
					EnterTradeInAmtvalue("000030", "tradeIn",  "500");
					clickUpdateCosting();
				    
				    clickonConXSystem(data.get("ItemNum1"));//000010
				    clickOnTabsInLineItemDetailsPopUp(data.get("Tab1"));//Pricing
					//Get data from the pricing tab
					 List<String> Pricelineitem1 = new ArrayList<>();
					int a1= Integer.parseInt(data.get("Value2"));

					Pricelineitem1 = getPriceValueFromPricingTab(data.get("idValue"), data.get("expValue2"),a1);// ZPLS--0

                    System.out.println(Price.size());
                    String	Price4="";
                    String	Price5="";
                    String	Price6="";
                    String	Price7="";
                    
                    float Line1zplspricevalue = 0, Line1ZTICpricevalue =0,Line1YP00pricevalue =0,Line1ZP00pricevalue = 0;
                    for(int b1 =0;b1<Price.size();b1++){
                    	if(b1==0){ //ZPLS--0
                    	 Price4 = Pricelineitem1.get(b1);
                    	 String P4 = Price4.replace(",", "");
                    	 Line1zplspricevalue = Float.parseFloat(P4);
                         
                    	}
                    	if(b1==1){  // ZTIC--1
                            Price5 = Pricelineitem1.get(b1);
                            String P5 = Price5.replace(",", "");
                            Line1ZTICpricevalue = Float.parseFloat(P5);
                    	}
                    	if(b1==2){   // YP00--2
                            Price6 = Pricelineitem1.get(b1);
                            String P6 = Price6.replace(",", "");
                            Line1YP00pricevalue = Float.parseFloat(P6);
                    	}
                    	if(b1==3){   // YP00--2
                            Price7 = Pricelineitem1.get(b1);
                            String P7 = Price7.replace(",", "");
                            Line1ZP00pricevalue = Float.parseFloat(P7);
                    	}
                    	
                    	
                    }
					
					clickonNextLineItemArrowsymbolinPopUp();
					clickOnTabsInLineItemDetailsPopUp(data.get("Tab1"));//Pricing
					//Get data from the pricing tab
					 List<String> Pricelineitem2 = new ArrayList<>();
					int b= Integer.parseInt(data.get("Value2"));

					Pricelineitem2 = getPriceValueFromPricingTab(data.get("idValue"), data.get("expValue2"),a1);// ZPLS--0

					
                    System.out.println(Price.size());
                    String	Price8="";
                    String	Price9="";
                    String	Price10="";
                    String	Price11="";
                    
                    float Line2ZPLSpricevalue = 0, Line2ZTICpricevalue =0,Line2YP00pricevalue =0,Line2ZP00pricevalue = 0;
                    for(int b2 =0;b2<Pricelineitem2.size();b2++){
                    	if(b2==0){ //ZPLS--0
                    		Price8 = Pricelineitem2.get(b2);
                    	 String P8 = Price8.replace(",", "");
                    	 Line2ZPLSpricevalue = Float.parseFloat(P8);
                         
                    	}
                    	if(b2==1){  // ZTIC--1
                            Price9 = Pricelineitem2.get(b2);
                            String P9 = Price9.replace(",", "");
                            Line2ZTICpricevalue = Float.parseFloat(P9);
                    	}
                    	if(b2==2){   // YP00--2
                            Price10 = Pricelineitem2.get(b2);
                            String P10 = Price10.replace(",", "");
                            Line2YP00pricevalue = Float.parseFloat(P10);
                    	}
                    	if(b2==3){   // ZP00--2
                            Price11 = Pricelineitem2.get(b2);
                            String P11 = Price11.replace(",", "");
                            Line2ZP00pricevalue = Float.parseFloat(P11);
                    	}
                    	
                    	
                    }
					
					clickonNextLineItemArrowsymbolinPopUp();
					clickOnTabsInLineItemDetailsPopUp(data.get("Tab1"));//Pricing
					//Get data from the pricing tab
					 List<String> Pricelineitem3 = new ArrayList<>();
					int c= Integer.parseInt(data.get("Value2"));

					Pricelineitem3 = getPriceValueFromPricingTab(data.get("idValue"), data.get("expValue2"),a1);// ZPLS--0

                    System.out.println(Price.size());
                    String	Price12="";
                    String	Price13="";
                    String	Price14="";
                    String	Price15="";
                    
                    float Line3ZPLSpricevalue = 0, Line3ZTICpricevalue =0,Line3YP00pricevalue =0,Line3ZP00pricevalue = 0;
                    for(int b2 =0;b2<Price.size();b2++){
                    	if(b2==0){ //ZPLS--0
                    		Price12 = Pricelineitem3.get(b2);
                    	 String P12 = Price12.replace(",", "");
                    	 Line3ZPLSpricevalue = Float.parseFloat(P12);
                         
                    	}
                    	if(b2==1){  // ZTIC--1
                    		Price13 = Pricelineitem3.get(b2);
                            String P13 = Price13.replace(",", "");
                            Line3ZTICpricevalue = Float.parseFloat(P13);
                    	}
                    	if(b2==2){   // YP00--2
                    		Price14 = Pricelineitem3.get(b2);
                            String P14 = Price14.replace(",", "");
                            Line3YP00pricevalue = Float.parseFloat(P14);
                    	}
                    	if(b2==3){   // ZP00--2
                    		Price15 = Pricelineitem3.get(b2);
                            String P15 = Price15.replace(",", "");
                            Line3ZP00pricevalue = Float.parseFloat(P15);
                    	}
                    	
                    	
                    }
					clickDoneButton();
				    VerifyYP00houldbeEqualToAllLineItems(price2value,Line1YP00pricevalue,Line2YP00pricevalue,Line3YP00pricevalue);
				    VerifyZP00houldbeEqualToAllLineItems(price3value,Line1ZP00pricevalue, Line2ZP00pricevalue ,Line3ZP00pricevalue);
				    VerifyZPLSShouldbeEqualToAllLineItems(price1value,Line1zplspricevalue,Line2ZPLSpricevalue,Line3ZPLSpricevalue);
				    VerifyZTICMinusYP00ShouldbeEqualToZP00(Line1ZTICpricevalue,Line2ZTICpricevalue,Line3ZTICpricevalue,Line1YP00pricevalue,Line2YP00pricevalue,Line3YP00pricevalue,Line1ZP00pricevalue,Line2ZP00pricevalue,Line3ZP00pricevalue);
				    clickUpdateCosting();
				    clickonSaveasQuote();
				    
				    enterCancelButtonInPoupHdr();
				    
				    String QuoteNum= GetQuoteNumber();
				    
				    clickSideBarSmart();
					clickClosthedocument(QuoteNum);
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
				//ReportStatus.fnUpdateResultStatus("IPSCappingAtListPriceWithTIC", "CQT_49", ReportStatus.strMethodName, 1, browser);
				throw new RuntimeException(e);
			}
	        finally {
				ReportControl.fnEnableJoin();
				ReportStatus.fnUpdateResultStatus("IPSCappingAtListPriceWithTIC", "CQT_49", ReportStatus.strMethodName, counter, browser);
				fnCloseTest();
				ReportControl.fnNextTestJoin(nextTestJoin);
			}
		}


	}