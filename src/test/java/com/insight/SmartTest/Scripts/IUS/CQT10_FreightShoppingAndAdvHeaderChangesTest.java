package com.insight.SmartTest.Scripts.IUS;

import java.util.Hashtable;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.insight.SmartTest.Lib.HomeLib;
import com.insight.SmartTest.Lib.loginLib;
import com.insight.accelerators.ReportControl;
import com.insight.accelerators.TestEngineWeb;
import com.insight.googledrive.ReportStatus;
import com.insight.utilities.TestUtil;

public class CQT10_FreightShoppingAndAdvHeaderChangesTest extends HomeLib {
	
	loginLib loginlib=new loginLib();
	// #############################################################################################################
	// #       Name of the Test         :  CQT10_FreightShoppingAndAdvHeaderChanges
	// #       Migration Author         :  Cigniti Technologies
	// #
	// #       Date of Migration        : November 2019
	// #       DESCRIPTION              : This method is to verify FreightShoppingAndAdvHeaderChanges
	// #       Parameters               : StartRow ,EndRow , nextTestJoin
	// #
	// ###############################################################################################################

	@Parameters({ "StartRow", "EndRow", "nextTestJoin" })
	@Test
	public void TC_CQT10(int StartRow, String EndRow, boolean nextTestJoin) throws Throwable {
		int counter = 0;
		try {
				int intStartRow = StartRow;
				int intEndRow = ReportControl.fnGetEndRowCunt(EndRow, "CQT10_FreightShoppingAndAdvHeaderChanges",TestData_Smart,"Create_Quote");
				for (int intCounter = intStartRow; intCounter <= intEndRow; intCounter++) {

					
					try {
						counter = intCounter;
						fnOpenTest();
						ReportStatus.fnDefaultReportStatus();
						ReportControl.intRowCount = intCounter;
						Hashtable<String, String> data = TestUtil.getDataByRowNo("CQT10_FreightShoppingAndAdvHeaderChanges", TestData_Smart, "Create_Quote", intCounter);
						TestEngineWeb.reporter.initTestCaseDescription("FreightShoppingAndAdvHeaderChanges");
						LoginToApplicationAndSearchForSoldToAct(data.get("UserName"), data.get("Password"), data.get("SoldToAcct"));
					/*
					 * navigateToApplication("SMART");
					 * loginlib.loginIntoSmartApplication(data.get("UserName"),data.get("Password"))
					 * ; clickOnCreateQuoteLink(); ClickOnsideViewBar();
					 * enterSoldToValue(data.get("SoldToAcct")); clickOnSoldToSearchIcon();
					 */				        
				        
				        AddLineItems("material",data.get("Material"),0);
				        clickAdvancedHeader();	
				        clickAdvancedHeaderTab("Freight");
				        selectShippingCodnition("10 Next day");
				        clickUpdateCosting();
				        SwipeUpapplication();
				        clickAdvancedHeader();	
				        String FrghtCost = getmfgPricevalue("000010","frtCost","");		        
				        String FrghtCharge = getmfgPricevalue("000010","frtCharge","");
				        String FrghtFloor =getmfgPricevalue("000010","frtFloor","");
				        String FrghtTarget =getmfgPricevalue("000010","frtTarget","");
				        SelectoptionfromDropdown("Freight", "sales-analysis");
				        sortingofcolumns("Cost");
				        doubleclickonCostcell("cost", 0);
				        clickUpdateCosting();

				        String FrghtCostaftersorting = getmfgPricevalue("000010","frtCost","");	
				        String FrghtChargeaftersorting = getmfgPricevalue("000010","frtCharge","");	
				        String FrghtFlooraftersorting = getmfgPricevalue("000010","frtFloor","");	
				        String FrghtTargetaftersorting = getmfgPricevalue("000010","frtTarget","");	
				        if(FrghtCostaftersorting!=FrghtCost) {
				        	reporter.SuccessReport("Friegt Cost after sorting: ", "Frieght cost is "+FrghtCostaftersorting+"", FrghtCostaftersorting);
				        }
				        else {
				        	reporter.failureReport("Friegt Cost after sorting: ", "Frieght cost is "+FrghtCostaftersorting+" is not as expected", FrghtCostaftersorting);
				        }
				        if(FrghtChargeaftersorting!=FrghtCharge) {
				        	reporter.SuccessReport("Friegt Charge after sorting: ", "Frieght Charge is "+FrghtChargeaftersorting+"", FrghtChargeaftersorting);
				        }
				        else {
				        	reporter.failureReport("Friegt Charge after sorting: ", "Frieght Charge is "+FrghtChargeaftersorting+" is not as expected", FrghtChargeaftersorting);
				        }
				        if(FrghtFlooraftersorting!=FrghtFloor) {
				        	reporter.SuccessReport("Friegt Floor after sorting: ", "Frieght Floor is "+FrghtFlooraftersorting+"", FrghtFlooraftersorting);
				        }
				        else {
				        	reporter.failureReport("Friegt Floor after sorting: ", "Frieght Floor is "+FrghtFlooraftersorting+" is not as expected", FrghtFlooraftersorting);
				        }
				        if(FrghtTargetaftersorting!=FrghtTarget) {
				        	reporter.SuccessReport("Friegt Target after sorting: ", "Frieght Target is "+FrghtTargetaftersorting+"", FrghtTargetaftersorting);
				        }
				        else {
				        	reporter.failureReport("Friegt Target after sorting: ", "Frieght Target is "+FrghtCostaftersorting+" is not as expected", FrghtTargetaftersorting);
				        }
				        
				      
				        clickAdvancedHeader();
				        clickAdvancedHeaderTab("Freight");
				        selectCarrierdropdownvalue(data.get("Carrier"));
				        selectShippingCodnition("05 Next day AM");
				        clickUpdateCosting();
				        
				        SwipeUpapplication();
				        clickAdvancedHeader();
				        String FrghtCost2 = getmfgPricevalue("000010","frtCost","");		        
				        String FrghtCharge2 = getmfgPricevalue("000010","frtCharge","");
				        String FrghtFloor2 =getmfgPricevalue("000010","frtFloor","");
				        String FrghtTarget2 =getmfgPricevalue("000010","frtTarget","");
				        reporter.SuccessReport("Freight values: ", "Frieghtcost after sorting: "+FrghtCost2+", FrieghtCharge after sorting: "+FrghtCharge2+",FrieghtFloor after sorting: "+FrghtFloor2+",FrieghtTarget after sorting: "+FrghtTarget2+"", "");
				        if(FrghtCost2!=FrghtCostaftersorting) {
				        	reporter.SuccessReport("freight Cost: ","The  freight costs were updated as expected.", FrghtCost2);
				        }
				        else {
				        	
				        	reporter.failureReport("freightCost: ", "The  freight costs were not updated as expected.","",driver);
				        }
				        if(FrghtCharge2!=FrghtChargeaftersorting) {
				        	reporter.SuccessReport("freight charge: ","The  freight charge were updated as expected.", FrghtCharge2);
				        }
				        else {
				        	reporter.failureReport("freight charge: ", "The freight charge were not updated as expected.","",driver);
				        }
				        if(FrghtFlooraftersorting!=FrghtFloor2) {
				        	reporter.SuccessReport("freight Floor: ","The freight Floor were updated as expected.",FrghtFloor2);
				        }
				        else {
				        	reporter.failureReport("freight Floor: ", "The freight Floor were not updated as expected.","",driver);
				        }
				        if(FrghtTargetaftersorting!=FrghtTarget2) {
				        	reporter.SuccessReport("freight Target: ","The  freight Target were updated as expected.", FrghtTarget2);
				        }
				        else {
				        	reporter.failureReport("freight Target: ", "The freight Target were not updated as expected.","",driver);
				        }
				        
				        
				     
					       System.out.println("Testcase completed");
					}      
				        catch (Exception e) {
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
					//ReportStatus.fnUpdateResultStatus("CQT10_FreightShoppingAndAdvHeaderChanges", "TC_CQT10", ReportStatus.strMethodName, 1, browser);
					throw new RuntimeException(e);
				}
			    finally {
					ReportControl.fnEnableJoin();
					ReportStatus.fnUpdateResultStatus("CQT10_FreightShoppingAndAdvHeaderChanges", "TC_CQT10", ReportStatus.strMethodName, counter, browser);
					fnCloseTest();
					ReportControl.fnNextTestJoin(nextTestJoin);
				}
			}


			}


