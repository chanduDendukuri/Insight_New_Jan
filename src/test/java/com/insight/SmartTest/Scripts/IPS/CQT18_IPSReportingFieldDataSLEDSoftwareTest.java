package com.insight.SmartTest.Scripts.IPS;

import java.util.Hashtable;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.insight.SmartTest.Lib.HomeLib;
import com.insight.SmartTest.Lib.loginLib;
import com.insight.accelerators.ReportControl;
import com.insight.accelerators.TestEngineWeb;
import com.insight.googledrive.ReportStatus;
import com.insight.utilities.TestUtil;

public class CQT18_IPSReportingFieldDataSLEDSoftwareTest extends HomeLib {
	
	loginLib loginlib=new loginLib();
	// #############################################################################################################
	// #       Name of the Test         :  CQT18_IPSReportingFieldDataSLEDSoftware
	// #       Migration Author         :  Cigniti Technologies
	// #
	// #       Date of Migration        : November 2019
	// #       DESCRIPTION              : This method is to verify IPSReportingFieldDataSLEDSoftware
	// #       Parameters               : StartRow ,EndRow , nextTestJoin
	// #
	// ###############################################################################################################

	@Parameters({ "StartRow", "EndRow", "nextTestJoin" })
	@Test
	public void TC_CQT18(int StartRow, String EndRow, boolean nextTestJoin) throws Throwable {
		int counter = 0;
		try {
				int intStartRow = StartRow;
				int intEndRow = ReportControl.fnGetEndRowCunt(EndRow, "CQT18_IPSReportingFieldDataSLEDSoftware",TestData_Smart,"Create_Quote");
				for (int intCounter = intStartRow; intCounter <= intEndRow; intCounter++) {

					
					try {
						counter = intCounter;
						fnOpenTest();
						ReportStatus.fnDefaultReportStatus();
						ReportControl.intRowCount = intCounter;
						Hashtable<String, String> data = TestUtil.getDataByRowNo("CQT18_IPSReportingFieldDataSLEDSoftware", TestData_Smart, "Create_Quote", intCounter);
						TestEngineWeb.reporter.initTestCaseDescription("IPSReportingFieldDataSLEDSoftware");

				navigateToApplication("SMART");
				loginlib.loginIntoSmartApplication(data.get("UserName"),data.get("Password"));
				String keywords = data.get("Keyword1");
				String array[] =  keywords.split(",");				
				clickOnQuoteandAddlineitemsfromProductSearch(array,data.get("SoldToAcct"));
				VerifyXconsymbolispresentforallthematerials(data.get("ItemNum1"));
				
				ClickOnXsymbolunderCon();
		        driver.switchTo().defaultContent();
		        selectCOntractID(data.get("ContractId1"),data.get("ContractTabName"));
		        VerifyLineItems("000010");
		        VerifyLineItems("000020");
		        VerifyLineItems("000030");
		        
		        
					/*
					 * String lineitemvalue0=getLineItemText(); if(lineitemvalue0.equals("000010"))
					 * System.out.println("LineItem 000010 is displayed");
					 * ClickonArrowNextToLineitem(); String lineitemvalue=getLineItemText();
					 * if(lineitemvalue.equals("000020"))
					 * System.out.println("LineItem 000020 is displayed");
					 * ClickonArrowNextToLineitem(); String lineitemvalue1=getLineItemText();
					 * if(lineitemvalue1.equals("000030"))
					 * System.out.println("LineItem 000030 is displayed");
					 * ClickonArrowNextToLineitem();
					 */
		        selectCOntractID(data.get("ContractId3"),data.get("ContractTabName"));
				EnterUSCOMMember("MICROSOFT GOVERNMENT:",data.get("MICROSOFTGOVERNMENT"));
				EnterUSCOMMember("AUTO-SCRIPT TEST:",data.get("AUTOSCRIPTTEST"));
				clickonCopyreportingfieldstoallthelines();
				SearchButton();
		        clickDoneButton();
		        clickUpdateCosting();
		        
		        clickonSaveasQuote();
		        CloseButtonofOutputform();
		       // closebuttonInProductSearch();
		        ClickOnXsymbolunderCon();
		        validatetheLineitemfiledsaftersaveasquote(data.get("MICROSOFTGOVERNMENT"),data.get("AUTOSCRIPTTEST"));
		      
		        ClickonArrowNextToLineitem();
		        
		        ClickonArrowNextToLineitem();
		        validatetheLineitemfiledsaftersaveasquote(data.get("MICROSOFTGOVERNMENT"),data.get("AUTOSCRIPTTEST"));
		        clickDoneButton();
		        ClickOnDisplayMode();
		        
		        ClickOnXsymbolunderCon();
		        validatetheLineitemfiledsaftersaveasquote(data.get("MICROSOFTGOVERNMENT"),data.get("AUTOSCRIPTTEST"));
		        clickDoneButton();
		        ClickOnConverToOrder();
		        
		        SwipeUpapplication();
		        enterPONumber(data.get("PONumber"));
		        ClickOkInPoNumberPopup();
		        
		        
		        clickonSaveasOrder();
		        ClickOKbuttonifdisplayedafterclickingOnSaveasorder();
		        clickOnSaveorderwithoutAttchment();
		       
		        String OrderNumber = GetSubjectlinevalue();
		        CloseButtonofOutputform(); 
		        String QN =GetQuoteNumber();
		        if(OrderNumber.contains(QN))
		        	System.out.println("OrderNumber displayed");
		        if(OrderNumber.contains("test1"))
		        	System.out.println("PONumber displayed");
		       System.out.println("testcase completed");
			} catch (Exception e) {
				ReportStatus.blnStatus = false;
			//	gErrorMessage = e.getMessage();
				gTestStatus = false;
			}
		}
	} catch (Exception e) {
		e.printStackTrace();
		ReportStatus.blnStatus = false;
		//gErrorMessage = e.toString();
		gTestStatus = false;
		//ReportStatus.fnUpdateResultStatus("CQT18_IPSReportingFieldDataSLEDSoftware_Action1_Script", "TC_CQT18", ReportStatus.strMethodName, 1, browser);
		throw new RuntimeException(e);
	}
    finally {
		ReportControl.fnEnableJoin();
		ReportStatus.fnUpdateResultStatus("CQT18_IPSReportingFieldDataSLEDSoftware_Action1_Script", "TC_CQT18", ReportStatus.strMethodName, counter, browser);
		fnCloseTest();
		ReportControl.fnNextTestJoin(nextTestJoin);
	}
}


}