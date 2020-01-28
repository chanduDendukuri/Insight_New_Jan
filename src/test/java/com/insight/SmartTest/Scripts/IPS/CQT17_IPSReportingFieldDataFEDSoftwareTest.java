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

public class CQT17_IPSReportingFieldDataFEDSoftwareTest extends HomeLib {
	
	loginLib loginlib=new loginLib();
	// #############################################################################################################
	// #       Name of the Test         :  CQT17_IPSReportingFieldDataFEDSoftware
	// #       Migration Author         :  Cigniti Technologies
	// #
	// #       Date of Migration        : November 2019
	// #       DESCRIPTION              : This method is to verify IPSReportingFieldDataFEDSoftware
	// #       Parameters               : StartRow ,EndRow , nextTestJoin
	// #
	// ###############################################################################################################

	@Parameters({ "StartRow", "EndRow", "nextTestJoin" })
	@Test
	public void TC_CQT17(int StartRow, String EndRow, boolean nextTestJoin) throws Throwable {
		int counter = 0;
		try {
				int intStartRow = StartRow;
				int intEndRow = ReportControl.fnGetEndRowCunt(EndRow, "CQT17_IPSReportingFieldDataFEDSoftware",TestData_Smart,"Create_Quote");
				for (int intCounter = intStartRow; intCounter <= intEndRow; intCounter++) {

					
					try {
						counter = intCounter;
						fnOpenTest();
						ReportStatus.fnDefaultReportStatus();
						ReportControl.intRowCount = intCounter;
						Hashtable<String, String> data = TestUtil.getDataByRowNo("CQT17_IPSReportingFieldDataFEDSoftware", TestData_Smart, "Create_Quote", intCounter);
						TestEngineWeb.reporter.initTestCaseDescription("IPSReportingFieldDataFEDSoftware");

						
				navigateToApplication("SMART");
				loginlib.loginIntoSmartApplication(data.get("UserName"),data.get("Password"));
				String keywords = data.get("Keyword1");
				String array[] =  keywords.split(",");
				
				
				clickOnQuoteandAddlineitemsfromProductSearch(array,data.get("SoldToAcct"));
				VerifyXconsymbolispresentforallthematerials(data.get("ItemNum1"));
				ClickOnXsymbolunderCon();
		        driver.switchTo().defaultContent();
				//SelectContractId(1);
		        selectCOntractID(data.get("ContractId"), data.get("ContractTabName"));
				enterUSCOMMmember(data.get("USCOMMmember"));
				ClickonArrowNextToLineitem();
				String lineitemvalue=getLineItemText();
				if(lineitemvalue.equals("000020"))
					selectContractID(data.get("ContractId2"));
				enterUSCOMMmember(data.get("USCOMMmember"));
				ClickonArrowNextToLineitem();  
				String lineitemvalue1=getLineItemText();
				if(lineitemvalue1.equals("000030"))
					selectContractID(data.get("ContractId3"));
				enterUSCOMMmember(data.get("USCOMMmember"));
				clickDoneButton();
				clickUpdateCosting();
		        clickonSaveasQuote();
		        
		       
		        EnterEmail(data.get("Email"));
		        ClickOnSendbutton();
		        okPopUp();
		        ClickOnXsymbolunderCon();
		        validatetheLineitemfiledsaftersaving();
		        clickDoneButton();
		        ClickOnDisplayMode();
		        ClickOnXsymbolunderCon();
		        validatetheLineitemfiledsaftersaving();
		        //reportingFiledData();
		        ClickonArrowNextToLineitem();
		        validatetheLineitemfiledsaftersaving();
		        //reportingFiledData();
		        ClickonArrowNextToLineitem();
		        validatetheLineitemfiledsaftersaving();
		        //reportingFiledData();
		        clickDoneButton();
		        
		        System.out.println("testcase completed");
		        
		     
			
			} catch (Exception e) {
				ReportStatus.blnStatus = false;
				//gErrorMessage = e.getMessage();
				gTestStatus = false;
			}
		}
	}catch (Exception e) {
				e.printStackTrace();
				ReportStatus.blnStatus = false;
				//gErrorMessage = e.toString();
				gTestStatus = false;
				//ReportStatus.fnUpdateResultStatus("CQT17_IPSReportingFieldDataFEDSoftware_Action1_Script", "TC_CQT17", ReportStatus.strMethodName, 1, browser);
				throw new RuntimeException(e);
			}
		    finally {
				ReportControl.fnEnableJoin();
				ReportStatus.fnUpdateResultStatus("CQT17_IPSReportingFieldDataFEDSoftware_Action1_Script", "TC_CQT17", ReportStatus.strMethodName, counter, browser);
				fnCloseTest();
				ReportControl.fnNextTestJoin(nextTestJoin);
			}
		}


		}
	

