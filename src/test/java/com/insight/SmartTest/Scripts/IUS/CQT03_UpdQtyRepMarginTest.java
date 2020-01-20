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

public class CQT03_UpdQtyRepMarginTest extends HomeLib {

	loginLib loginlib = new loginLib();

	// #############################################################################################################
	// # Name of the Test : CQT03_UpdQtyRepMargin_Action1_Script
	// # Migration Author : Cigniti Technologies
	// #
	// # Date of Migration : November 2019
	// # DESCRIPTION : This method is to verify UpdQtyRepMargin
	// # Parameters : StartRow ,EndRow , nextTestJoin
	// #
	// ###############################################################################################################
	@Parameters({ "StartRow", "EndRow", "nextTestJoin" })
	@Test
	public void TC_CQT03(int StartRow, String EndRow, boolean nextTestJoin) throws Throwable {
		int counter = 0;
		try {

			int intStartRow = StartRow;
			int intEndRow = ReportControl.fnGetEndRowCunt(EndRow, "CQT03_UpdQtyRepMargin",
					TestData_Smart, "Create_Quote");
			for (int intCounter = intStartRow; intCounter <= intEndRow; intCounter++) {
				try {
					counter = intCounter;
					fnOpenTest();
					ReportStatus.fnDefaultReportStatus();
					ReportControl.intRowCount = intCounter;
					Hashtable<String, String> data = TestUtil.getDataByRowNo("CQT03_UpdQtyRepMargin",
							TestData_Smart, "Create_Quote", intCounter);
					TestEngineWeb.reporter.initTestCaseDescription("UpdQtyRepMargin");
					// Test Steps execution
					LoginToApplicationAndSearchForSoldToAct(data.get("UserName"), data.get("Password"),
							data.get("SoldToValue"));
					SwipeUpapplication();
					EnterDataInShipToAttn(data.get("ShipToAttn"));
					/*
					 * clickAdvancedHeader(); clickAdvancedHeaderTab("Programs");
					 * VerifyRowsUnderContractsTab(); clickAdvancedHeaderTab("Contracts");
					 */
					// AddLineItems(data.get("Material"));
					Swipedownapplication();
					clickLineItemHeaderTab("Product Search");
					driver.switchTo().defaultContent();
					selectoptionfromManufacturer(data.get("Manufacturer"));
					checkStokInOnlyCheckbox();
					selectpricing();
					EnterkeywordSearch(data.get("search"));
					SearchButton();
					SelectProductWithAvailability();

					driver.switchTo().defaultContent();
					clickOnAddToOrderButton();
					closebuttonInProductSearch();
					driver.switchTo().parentFrame();
					
					rejectitem(data.get("ItemNum1"));
					clickYesToRejectItem();
					AddLineItems("material",data.get("Material"),0);
					
					String displayedtotalweight = TotalWeight();
					if (displayedtotalweight.equals("0.800")) {
						reporter.SuccessReport("Product Weight::"," Product weight is  0.8 and the weight is: "+displayedtotalweight+" ","");
					} else {
						reporter.failureReport("Product Weight::"," Total Weight not as expected ","");
					}
					AddQuantity("100", data.get("ItemNum1"));
					clickUpdateCosting();
					String displayedtotalweight1 = TotalWeight();
					if (displayedtotalweight1.equals("80.000")) {
						reporter.SuccessReport("Product Weight::"," Product weight is 80.000 and the weight is: "+displayedtotalweight1+" ","");
					} else {
						reporter.failureReport("Product Weight::"," Total Weight not as expected ","");
					}
					ClickOnSaveAsQuoteButton();
					driver.switchTo().defaultContent();
					EnterEmail(data.get("emailid"));
					ClickOnSendbutton();
					clickOkButton();
					ClickOnDisplayMode();
					AddLineItems("material",data.get("Material2"),1);
					clickUpdateCosting();
					SwipeUpapplication();
					clickAdvancedHeader();
					clickAdvancedHeaderTab(data.get("Tab"));
					SelectvaluefronSelectGroupingDropdown(data.get("DDoption"));
					SelectvaluefromotherGroupingDropdown(data.get("otherGroupingoption1"));
					
					ClickonOkbuttonafterenteringnewrepmarginper("10");
					
					deSelectvaluefromotherGroupingDropdown(data.get("otherGroupingoption1"));
					SelectvaluefromotherGroupingDropdown(data.get("otherGroupingoption2"));
					
					ClickonOkbuttonafterenteringnewrepmarginper("11");
					
					deSelectvaluefromotherGroupingDropdown(data.get("otherGroupingoption2"));
					SelectvaluefromotherGroupingDropdown(data.get("otherGroupingoption3"));
					
					//ok button
					ClickonOkbuttonafterenteringnewrepmarginper("12");
					SwipeUpapplication();
					clickAdvancedHeader();
					String repmargin1 =  getRepmarginLineItem("000010");
					String repmargin2 = getRepmarginLineItem("000020");
					String repmargin3 = getRepmarginLineItem("000030");
					if(repmargin1.equals("10.000%") && repmargin2.equals("11.000%") && repmargin3.equals("12.000%")){
						reporter.SuccessReport("Rep Margins validate:", "Rep Margins validated successfully", "");	
					}
					else {
						reporter.failureReport("Rep Margins validate:", "Rep Margins not validated successfully", "");	
					}
					
					
					ClickOnSaveAsQuoteButton();
					String QN = GetQuoteNumber();
					clickSideBarSmart();
					clickClosthedocument(QN);
					clickYesButtontocloseDocument();
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
			// ReportStatus.fnUpdateResultStatus("CQT03_UpdQtyRepMargin_Action1_Script","TC_CQT03",
			// ReportStatus.strMethodName, 1, browser);
			throw new RuntimeException(e);
		} finally {
			ReportControl.fnEnableJoin();
			ReportStatus.fnUpdateResultStatus("CQT03_UpdQtyRepMargin_Action1_Script", "TC_CQT03",
					ReportStatus.strMethodName, counter, browser);
			fnCloseTest();
			ReportControl.fnNextTestJoin(nextTestJoin);
		}
	}

}
