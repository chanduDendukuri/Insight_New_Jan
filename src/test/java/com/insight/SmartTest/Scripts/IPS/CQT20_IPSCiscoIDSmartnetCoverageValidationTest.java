package com.insight.SmartTest.Scripts.IPS;

import java.util.Calendar;
import java.util.Hashtable;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.insight.SmartTest.Lib.HomeLib;
import com.insight.SmartTest.Lib.loginLib;
import com.insight.accelerators.ReportControl;
import com.insight.accelerators.TestEngineWeb;
import com.insight.googledrive.ReportStatus;
import com.insight.utilities.TestUtil;

public class CQT20_IPSCiscoIDSmartnetCoverageValidationTest extends HomeLib {
	
	loginLib loginlib=new loginLib();
	// #############################################################################################################
		// #       Name of the Test         :  CQT20_IPSCiscoIDSmartnetCoverageValidation
		// #       Migration Author         :  Cigniti Technologies
		// #
		// #       Date of Migration        : November 2019
		// #       DESCRIPTION              : This method is to verify IPSCiscoIDSmartnetCoverageValidation
		// #       Parameters               : StartRow ,EndRow , nextTestJoin
		// #
		// ###############################################################################################################

	@Parameters({ "StartRow", "EndRow", "nextTestJoin" })
	@Test
	public void TC_CQT20(int StartRow, String EndRow, boolean nextTestJoin) throws Throwable {
		int counter = 0;
		try {
				int intStartRow = StartRow;
				int intEndRow = ReportControl.fnGetEndRowCunt(EndRow, "CQT20_IPSCiscoIDSmartnetCoverageValidation",TestData_Smart,"Create_Quote");
				for (int intCounter = intStartRow; intCounter <= intEndRow; intCounter++) {

		try {
				counter = intCounter;
				fnOpenTest();
				ReportStatus.fnDefaultReportStatus();
				ReportControl.intRowCount = intCounter;
				Hashtable<String, String> data = TestUtil.getDataByRowNo("CQT20_IPSCiscoIDSmartnetCoverageValidation", TestData_Smart, "Create_Quote", intCounter);
				TestEngineWeb.reporter.initTestCaseDescription("IPSCiscoIDRefreshIcon");
				LoginToApplicationAndSearchForSoldToAct(data.get("UserName"), data.get("Password"), data.get("SoldToAcct"));
					/*
					 * navigateToApplication("SMART");
					 * loginlib.loginIntoSmartApplication(data.get("UserName"),data.get("Password"))
					 * ; clickOnCreateQuoteLink(); ClickOnsideViewBar();
					 * enterSoldToValue(data.get("SoldToAcct")); clickOnSoldToSearchIcon();
					 * loadingSymbol();
					 */
		        clickLineItemHeaderTab("Acquire Estimate/Quote");
		        enterEstimateId(data.get("Material"));
		        SearchButton();
		        loadingSymbol();
		        ClickOnXsymbolunderCon();
		        //SelectContractId(2);
		        selectCOntractID(data.get("ContractId"), data.get("ContractTabName"));
		        ClickOnCopyContracttoallLines();
		        SearchButton();
		        clickDoneButton(); 
		        clickOnLItem00020CON("00020","con");
		        clickOnVCTab("VC");
		        enterDurationUnderVC("12");
		        ClickonArrowNextToLineitem();
		        ClickonArrowNextToLineitem();
		        enterDurationUnderVC(data.get("Duration1"));
		        ClickonArrowNextToLineitem();
		        ClickonArrowNextToLineitem();
		        enterDurationUnderVC(data.get("Duration2"));
		        clickDoneButton(); 
		        clickAdvancedHeader();
		        clickAdvancedHeaderTab("Cisco");
		        enterDurationUnderAdvanceHeader(data.get("Duration1"));
		        clickOnCopyDuartion();
		        clickAdvancedHeader();
		        clickOnLItem00020CON("00020","con");
		        clickOnVCTab("VC");
		        String duration =  getDurationFieldValue();
		        if(duration.equals("24"))
		        	System.out.println("Duration is 24");
		        VerifyDuration(data.get("Duration1"));
		        VerifyDuration(data.get("Duration1"));
		        clickDoneButton(); 
		        clickUpdateCosting();		        
		        String ExpMrflstPrice = getmfgPricevalue("00020","mfrList","20");	
		        String ExpMrflstPrice1 = getmfgPricevalue("00040","mfrList","20");		
		        String ExpMrflstPrice2 = getmfgPricevalue("00060","mfrList","20");	
		        if(ExpMrflstPrice.equals("20")&&ExpMrflstPrice1.equals("20")&&ExpMrflstPrice2.equals("20")) {
		        	reporter.SuccessReport(ExpMrflstPrice, "ExpMrflstPrice value is 20", "");
		        }
		        
		        clickAdvancedHeader();
		        clickAdvancedHeaderTab("Cisco");
		       String Startdate = getStartDateunderCiscoTab();
		        String EndDate  = Enter36MonthstoEndDateField(36);
		        clickOnCopydates();
		        clickOnLItem00020CON("00020","con");
		        clickOnVCTab("VC");
		        String duration1 =  getDurationFieldValue();
		        if(duration1.equals("36.03"))
		        	System.out.println("Duration is 36.03");
		        VerifyDuration("36.03");
		        VerifyDuration("36.03");
		        checkdurationfieldenabledordisabled();
		        ClickonArrowNextToLineitem();
		        ClickonArrowNextToLineitem();
		        clickOnVCTab("Coverage/Billing");
		        String CoverageStratdate = coverageStartDate();
		        String CoverageEnddate = coverageEndDate();
		        if(CoverageStratdate.equals(Startdate) && CoverageEnddate.equals(EndDate)) {
		        	reporter.SuccessReport("Line item 20 Start date on Coverage/Billing tab ", "Exists as Expected and Enabled", "");
		        }
		        else {
		        	reporter.SuccessReport("Line item 20 Start date on Coverage/Billing tab ", "not exists as Expected and Enabled", "");
		        }
		        clickDoneButton(); 
		        clickUpdateCosting();		
		        String ExpMrflstPrice3=getmfgPricevalue("00020","mfrList","30.03");		        
		        String ExpMrflstPrice4=getmfgPricevalue("00040","mfrList","30.03");
		        String ExpMrflstPrice5 =getmfgPricevalue("00060","mfrList","30.03");
		        if(ExpMrflstPrice3.equals("30.03") && ExpMrflstPrice4.equals("30.03") &&ExpMrflstPrice5.equals("30.03")) {
		        	reporter.SuccessReport("MrflstPrice", "Exists as Expected", "");
		        }
		        else {
		        	reporter.SuccessReport("MrflstPrice ", "not exists as Expected", "");
		        }
		        clickOnLItem00020CON("00020","con");
		        clickOnVCTab("Coverage/Billing");
		        ClearEnddateCoveragefield();
		        clickOnVCTab("VC");
		        String DurationValueInVCTab =  getDurationFieldValue();
		        enterDurationUnderVC("36.03");
		        
		        clickDoneButton(); 
		        clickUpdateCosting();	
		       
		        clickonSaveasQuote();
		        SelectAdherencetoflooroption("Client Satisfaction", "UFT Test");
		        String QuoteNum1 = GetQuoteNumber();
				if(QuoteNum1!=null) {
					reporter.SuccessReport("QuoteNumber:", "Quotenumber is displayed", "");
				}
				else {
					reporter.failureReport("QuoteNumber:", "Quotenumber is not displayed", "",driver);
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
		//ReportStatus.fnUpdateResultStatus("CQT20_IPSCiscoIDSmartnetCoverageValidation_Action1_Script", "TC_CQT20", ReportStatus.strMethodName, 1, browser);
		throw new RuntimeException(e);
	}
    finally {
		ReportControl.fnEnableJoin();
		ReportStatus.fnUpdateResultStatus("CQT20_IPSCiscoIDSmartnetCoverageValidation_Action1_Script", "TC_CQT20", ReportStatus.strMethodName, counter, browser);
		fnCloseTest();
		ReportControl.fnNextTestJoin(nextTestJoin);
	}
}


}

