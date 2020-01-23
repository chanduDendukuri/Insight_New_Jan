package com.insight.SmartTest.Scripts.IUS;

import java.util.Hashtable;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import com.insight.SmartTest.Lib.ObjectsLib;
import com.insight.SmartTest.Lib.HomeLib;
import com.insight.SmartTest.Lib.loginLib;
import com.insight.accelerators.ReportControl;
import com.insight.accelerators.TestEngineWeb;
import com.insight.googledrive.ReportStatus;
import com.insight.utilities.TestUtil;

public class CQT02_FreightESDTest extends HomeLib {
	loginLib loginlib = new loginLib();
	ObjectsLib Objlib = new ObjectsLib();
	// #############################################################################################################
		// # Name of the Test : CQT02_FreightESDTest
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
	public void TC_CQT02(int StartRow, String EndRow, boolean nextTestJoin) throws Throwable {
		int counter = 0;
    try{
		int intStartRow = StartRow;
		int intEndRow = ReportControl.fnGetEndRowCunt(EndRow, "CQT02_FreightESDScript", TestData_Smart, "Create_Quote");
		for (int intCounter = intStartRow; intCounter <= intEndRow; intCounter++) {

			
			
			// Test Steps execution
			try {
				counter = intCounter;
				fnOpenTest();
				ReportStatus.fnDefaultReportStatus();
				ReportControl.intRowCount = intCounter;
				Hashtable<String, String> data = TestUtil.getDataByRowNo("CQT02_FreightESDScript", TestData_Smart, "Create_Quote", intCounter);
				TestEngineWeb.reporter.initTestCaseDescription("FreightESDScript");
				
				navigateToApplication("SMART");
				loginlib.loginIntoSmartApplication(data.get("UserName"), data.get("Password"));
				clickOnCreateQuoteLink();
				enterSoldToValue(data.get("SoldToAcct"));
				clickOnSoldToSearchIcon();
				clickOnProductSearchButton();
				Objlib.verifyAvailabilityOfProductSearchPopup();
				selectQuotePrograme();
				availabilityOfOptionsandLevelsPopup();
				clickOnLICandMaintCheckBox();
				clickOnSaveButtonOnOptionsAndLevelsPopup();

				enrollmentDropDown(data.get("EnrollmentOption"));
				clickLabelEnrollment();
				
				verifyQuoteProgramIsBlank();
				enrollmentDropDown("");
				clickLabelEnrollment();

				if (Objlib.verifyAvailabilityOfProductSearchPopup()) {
					String inputValue = data.get("keyword");
					String facilityCodeValue[] = inputValue.split("#");
					for (int i = 0; i < facilityCodeValue.length; i++) {
						Objlib.enterKeywordInProductSearchWindow(facilityCodeValue[i]);
						Objlib.clickOnSearchButtonInProductSearchWindow();
						clickOnMaterialID();
						
						clickOnAddToOrderButton();
						
						closebuttonInProductSearch();

					} // for
				} // if
				else {
					reporter.failureReport("Results found Status", "No results found", "", driver);
				}

				clickUpdateCosting();
								clickAdvancedHeader();

				clickAdvancedHeaderTab("Freight");
				carrierOptionValidate();
				shippingConditionsOptionValidate();
				clickAdvancedHeader();
				clickSideBarSmart();
				ClickOnSaveAsQuoteButton();
				driver.switchTo().defaultContent();
				EnterEmail("Test@insight.com");
				ClickOnSendbutton();
				
				if (waitForVisibilityOfElement(INFO_POPUP, "Info header popup")) {
					clickOKinPopUp();
				}
				String QuoteNum = GetQuoteNumber();
				if (QuoteNum != null) {
					reporter.SuccessReport("Quote Number", "Validated quote Number", "");

				} else {
					reporter.failureReport("Quote Number", "Unable to find quote number", "", driver);
				}

				clickSideBarSmart();
				clickClosthedocument(QuoteNum);

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
		ReportStatus.fnUpdateResultStatus("CQT02_FreightESDScript", "TC_02",
				ReportStatus.strMethodName, 1, browser);
		throw new RuntimeException(e);
	} finally {
		ReportControl.fnEnableJoin();
		ReportStatus.fnUpdateResultStatus("CQT02_FreightESDScript", "TC_02",
				ReportStatus.strMethodName, counter, browser);
		fnCloseTest();
		ReportControl.fnNextTestJoin(nextTestJoin);
	}
}


}