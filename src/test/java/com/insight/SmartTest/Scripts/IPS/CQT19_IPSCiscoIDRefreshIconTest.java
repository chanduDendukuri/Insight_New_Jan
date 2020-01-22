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



public class CQT19_IPSCiscoIDRefreshIconTest extends HomeLib {
	
	loginLib loginlib=new loginLib();
	// #############################################################################################################
	// #       Name of the Test         :  CQT19_IPSCiscoIDRefreshIcon
	// #       Migration Author         :  Cigniti Technologies
	// #
	// # Date of Migration : November 2019
	// # DESCRIPTION : This method is to verify IPSCiscoIDRefreshIcon
	// # Parameters : StartRow ,EndRow , nextTestJoin
	// #
	// ###############################################################################################################

	@Parameters({ "StartRow", "EndRow", "nextTestJoin" })
	@Test
	public void TC_CQT19(int StartRow, String EndRow, boolean nextTestJoin) throws Throwable {
		int counter = 0;
		try {
			int intStartRow = StartRow;
			int intEndRow = ReportControl.fnGetEndRowCunt(EndRow, "CQT19_IPSCiscoIDRefreshIcon",
					TestData_Smart, "Create_Quote");
			for (int intCounter = intStartRow; intCounter <= intEndRow; intCounter++) {

				try {
					counter = intCounter;
					fnOpenTest();
					ReportStatus.fnDefaultReportStatus();
					ReportControl.intRowCount = intCounter;
					Hashtable<String, String> data = TestUtil.getDataByRowNo("CQT19_IPSCiscoIDRefreshIcon", TestData_Smart, "Create_Quote", intCounter);
					TestEngineWeb.reporter.initTestCaseDescription("IPSCiscoIDRefreshIcon");
					LoginToApplicationAndSearchForSoldToAct(data.get("UserName"), data.get("Password"),
							data.get("SoldToAcct"));

					clickLineItemHeaderTab("Acquire Estimate/Quote");
					enterEstimateId(data.get("Material"));
					SearchButton();
					loadingSymbol();
					clickonSaveasQuote();
					

					SelectAdherencetoflooroption("Client Satisfaction",data.get("Adherencetofloor"));
					//Adherencetofloorreason(data.get("Adherencetofloor"));
					//ClickOncancelbutton();
					String quotenumber = GetQuoteNumber();
					ClickOnDisplayMode();
					doubleclickOnLineItem("000010");
					clickOnVCTab("Contracts");
					clickOnrefreshContracts();
					selectContractIdwithDesc("STATE OF TEXAS");
					ClickOnCopyContracttoallLines();
					SearchButton();
					clickDoneButton();
					clickOnLItem00020CON("00020","con");
					clickOnVCTab("VC");
					String duration = getDurationFieldValue();

					if (duration.equals("12.00"))
						reporter.SuccessReport("Duration: ", "Duration is ", duration);

					VerifyDuration("24");
					VerifyDuration("36");
					clickDoneButton();
					clickAdvancedHeader();
					clickAdvancedHeaderTab("Cisco");
					enterDurationUnderAdvanceHeader("24");
					clickOnCopyDuartion();
					clickAdvancedHeader();
					clickUpdateCosting();

					ClickOncancelbutton();
					String ExpMrflstPrice = getmfgPricevalue("00020", "mfrList","");
					String ExpMrflstPrice1 = getmfgPricevalue("00040", "mfrList", "");
					String ExpMrflstPrice2  =getmfgPricevalue("00060", "mfrList", "");
					 if(ExpMrflstPrice.equals("20.00")&&ExpMrflstPrice1.equals("20.00")&&ExpMrflstPrice2.equals("20.00")) {
				        	reporter.SuccessReport(ExpMrflstPrice, "ExpMrflstPrice value is ", "ExpMrflstPrice");
				        }
					 else
						 reporter.failureReport(ExpMrflstPrice, "ExpMrflstPrice value is not as expected ", "");
					clickAdvancedHeader();
					clickAdvancedHeaderTab("Cisco");
					String CoverageStartDate = getStartDateunderCiscoTab();
					String CoverateEndDate = Enter36MonthstoEndDateField(36);
					clickOnCopydates();

					Swipedownapplication();
					clickOnLItem00020CON("00020", "con");
					clickOnVCTab("VC");
					String duration1 = getDurationFieldValue();
					if (duration1.equals("36.03"))
						reporter.SuccessReport("Duration: ", "Duration is ", duration);
					checkdurationfieldenabledordisabled();
					VerifyDuration("36.03");
					checkdurationfieldenabledordisabled();
					VerifyDuration("36.03");
					checkdurationfieldenabledordisabled();
					
					
					clickOnVCTab("Coverage/Billing");
					String Cstartdate = coverageStartDate();
					String CendDate = coverageEndDate();
					if (CoverateEndDate.equals(CendDate))
						reporter.SuccessReport("Coverage Start and End date: ","Start date and End dates are matching",  "");
					else
						reporter.failureReport("Coverage Start and End date: ","Start date and End dates are not matching",  "");
					if (CoverageStartDate.equals(Cstartdate))
						reporter.SuccessReport("Coverage Start and End date: ","Start date and End dates are matching",  "");
					else
						reporter.failureReport("Coverage Start and End date: ","Start date and End dates are not matching",  "");
					
					clickDoneButton();
					clickUpdateCosting();
					VerifyUpdateCosingPopup();
					CancelButtonInUpdateCosting();

					Swipedownapplication();
					String expmrgprice = getmfgPricevalue("00020", "mfrList","30.03");
					String expmrgprice1 = getmfgPricevalue("00040", "mfrList", "30.03");
					String expmrgpric2 = getmfgPricevalue("00060", "mfrList", "30.03");
					if(expmrgprice.equals("30.03")&&expmrgprice1.equals("30.03")&&expmrgpric2.equals("30.03")) {
			        	reporter.SuccessReport(ExpMrflstPrice, "ExpMrflstPrice value is ", "ExpMrflstPrice");
			        }
				 else
					 reporter.failureReport(ExpMrflstPrice, "ExpMrflstPrice value is not as expected ", "");
				
					SwipeUpapplication();

					doubleclickOnLineItem("000020");
					clickOnVCTab("Coverage/Billing");
					ClearEnddateCoveragefield();
					ClickonArrowNextToLineitem();
					ClickonleftArrowpriorToLineitem();
					clickOnVCTab("VC");
					checkdurationfieldenabledordisabled();

					enterDurationUnderVC("36.03");

					clickDoneButton();
					clickUpdateCosting();
					VerifyUpdateCosingPopup();
					CancelButtonInUpdateCosting();
					clickonSaveasQuote();
					clickSideBarSmart();
					String QN = GetQuoteNumber();
					clickClosthedocument(QN);
                    clickYesButtontocloseDocument();
					
					System.out.println("Testcase completed");
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
			 ReportStatus.fnUpdateResultStatus("CQT19_IPSCiscoIDRefreshIcon_Action1_Script",
			 "TC_CQT19", ReportStatus.strMethodName, 1, browser);
			throw new RuntimeException(e);
		} finally {
			ReportControl.fnEnableJoin();
			ReportStatus.fnUpdateResultStatus("CQT19_IPSCiscoIDRefreshIcon_Action1_Script", "TC_CQT19",
					ReportStatus.strMethodName, counter, browser);
			 fnCloseTest();
			ReportControl.fnNextTestJoin(nextTestJoin);
		}
	}

}
