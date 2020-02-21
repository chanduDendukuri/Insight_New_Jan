package com.insight.Lib;

import org.omg.PortableServer.THREAD_POLICY_ID;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;

import java.io.IOException;
import java.nio.file.*;
import java.time.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.google.gdata.data.codesearch.File;
import com.insight.ObjRepo.CanadaObj;
import com.insight.ObjRepo.InvoiceHistoryObj;
import com.insight.ObjRepo.productsDisplayInfoObj;
import org.testng.reporters.VerboseReporter;

public class InvoiceHistoryLib extends InvoiceHistoryObj {

	/**
	 * Method is used to quick search in invoice history
	 * 
	 * @param searchBy
	 * @param text
	 * 
	 * @throws Throwable
	 */
	public void quickSearchAndVerifySearchResults(String searchBy, String text) throws Throwable {
		Thread.sleep(10000);

		waitForVisibilityOfElement(CanadaObj.SEARCHBY_DROPDOWN, "Quick Search");
		if (isVisibleOnly(CanadaObj.SEARCHBY_DROPDOWN, "Quick Search")) {

			click(CanadaObj.SEARCHBY_DROPDOWN, "Clicking on SearchBy Dropdown");
			// selectSearchBy(searchBy);
			// click(CanadaLib.drpSearchByDropdownValue,"Search bY Dropdown Value");
			click(CanadaObj.getSearchByTextOrder(searchBy), "Open Search By and select " + searchBy,searchBy);
			Thread.sleep(3000);
			// click(CanadaObj.QUICK_SEARCH_TEXT, "Click on Text");
			type(CanadaObj.QUICK_SEARCH_TEXT, text, "Text ");
			click(CanadaObj.SEARCH, "Search");
			Thread.sleep(5000);

			waitForVisibilityOfElement(searchResultsTable, "search results table");
			if (isVisibleOnly(searchResultsTable, "search results table")) {
				reporter.SuccessReport("Verify search results in Invoice hisory page  ",
						" search results are displayed", searchBy);
				List<WebElement> record = driver.findElements(getInvoiceNumber);
				for (int i = 0; i < record.size(); i++) {
					record.get(i).getText();
					reporter.SuccessReport("Product Details are ", "Given value is matched wtih how mnay ",
							record.get(i).getText());

				}

			} else {

				reporter.failureReport("Verify search results in Invoice hisory page   ",
						" search results are not displayed", searchBy, driver);
			}
		}

	}

	/**
	 * Method is used to advanced search in invoice history
	 * 
	 * @throws Throwable
	 */
	public void clickOnAdvancedSearch() throws Throwable {
		clickUntil(ADVANCED_SEARCH, START_DATE_CALENDER, "Advanced search", "Advanced Search");
		Thread.sleep(6000);
	}

	/**
	 * Method is used to click on search button of advanced search in invoice
	 * history
	 * 
	 * @throws Throwable
	 */
	public void clickOnAdvancedSearchSearchButton() throws Throwable {
		click(ADAVANCED_SEARCH_SEARCHBUTTON, "Advancedsearch search button");
	}

	/**
	 * Method is used to verify search results
	 * 
	 * @throws Throwable
	 */
	public void verifySearchResultsAreDisplayed() throws Throwable {
		Thread.sleep(10000);
		boolean status = false;
		waitForVisibilityOfElement(searchResultsTable, "search results table");
		if (isVisibleOnly(searchResultsTable, "search results table")) {
			status = true;
			reporter.SuccessReport("Verify search results in Invoice hisory page  ", " search results are displayed",
					Boolean.toString(status));
		} else {
			status = false;
			reporter.failureReport("Verify search results in Invoice hisory page   ",
					" search results are not displayed", Boolean.toString(status), driver);
		}
	}

	/**
	 * Method is used to select date from start date calender
	 * 
	 * @throws Throwable
	 */
	public void datePickerStartDateCalender(String date) throws Throwable {
		Thread.sleep(3000);
		System.out.println("Inside date picker");
		String day = date.split(" ")[0];
		String month = date.split(" ")[1];
		String year = date.split(" ")[2];
		System.out.println("day : " + day);
		System.out.println("month : " + month);
		Thread.sleep(5000);
		reporter.SuccessReport("Selected Date ", "Selected Date is ", date);
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollTo(0, -document.body.scrollHeight);");
		Thread.sleep(8000);
		if (click(START_DATE_CALENDER, "Start date calender", day)) {
			reporter.SuccessReport("Selected Date ", "Selected Date is ", "");
		}
		if (click(MONTH_AND_YEAR_START_DATE_CALENDER, "month and year", "")) {
			reporter.SuccessReport("Selected Month and Year  ", "Selected Month and Year is is ", "");

		}

		Thread.sleep(3000);
		while (true) {
			// Get Month and Year text

			String text = getText(YEAR_START_DATE_CALENDER, " year");

			System.out.println("yeartext" + text);
			if (text.equals(year)) {
				Thread.sleep(5000);
				if (click(monthInStartDateCalnder(month), "month", month)) {
					reporter.SuccessReport("Start Month ", "Selected Start Month  is ", "");
				}
				// driver.findElement(monthInStartDateCalnder(month)).click();
				Thread.sleep(5000);
				if (click(dayInStartDayCalender(day), "Day", day)) {
					reporter.SuccessReport("Start Date ", "Selected Start date  is ", "");
				}
				break;
			} else {
				// Click on back button

				click(BACK_BUTTON_START_DATE_CALENDER, "Back button");
				Thread.sleep(5000);
			}
		}

	}

	/**
	 * Method is used to select date from start date calender
	 * 
	 * @throws Throwable
	 */
	public void datePickerEndDateCalender(String date) throws Throwable {
		System.out.println("Inside date picker");
		String day = date.split(" ")[0];
		String month = date.split(" ")[1];
		String year = date.split(" ")[2];
		System.out.println("day" + day);
		System.out.println("month" + month);
		Thread.sleep(5000);
		// JavascriptExecutor js = (JavascriptExecutor) driver;
		// js.executeScript("window.scrollTo(0, -document.body.scrollHeight);");
		// reporter.SuccessReport("Selected Date ", "Selected Date is ","");
		/*
		 * click(END_DATE_CALENDER, "Start date calender",date);
		 * click(MONTH_AND_YEAR_END_DATE_CALENDER,"month and year",month + year);
		 */
		if (click(END_DATE_CALENDER, "Start date calender", date)) {
			reporter.SuccessReport("Selected Date ", "Selected Date is ", date);

		}
		if (click(MONTH_AND_YEAR_END_DATE_CALENDER, "month and year", "")) {
			reporter.SuccessReport("Selected Month and Year  ", "Selected Month and Year is  ", "");
		}

		Thread.sleep(2000);
		while (true) {
			// Get Month and Year text

			String text = getText(YEAR_END_DATE_CALENDER, " year");

			System.out.println("yeartext" + text);
			if (text.equals(year)) {
				Thread.sleep(5000);
				if (click(monthInEndDateCalnder(month), "month", "")) {
					reporter.SuccessReport("Selected Month ", "Selected Month is ", "");

				}
				// driver.findElement(monthInStartDateCalnder(month)).click();
				Thread.sleep(5000);
				if (click(dayInEndDayCalender(day), "Day", date)) {
					reporter.SuccessReport("Selected Date ", "Selected Date is ", "");

				}
				break;
			} else {
				// Click on back button

				click(BACK_BUTTON_END_DATE_CALENDER, "Back button");
				Thread.sleep(5000);
			}
		}

	}

	/**
	 * Method is used to close account tools
	 * 
	 * @throws Throwable
	 */
	public void closeAccountTools() throws Throwable {
		if(isVisibleOnly(COSE_ACCOUNT_TOOLS, "close account tools")) {
			click(COSE_ACCOUNT_TOOLS, "close account tools");
		}
	}

	/**
	 * Method is used to get previous date from current date
	 * 
	 * @throws Throwable
	 */
	public String getPreviousDay(String NuberOfDaysToSubtract) {
		LocalDate today = LocalDate.now();
		LocalDate newDate = today.minusDays(Integer.valueOf(NuberOfDaysToSubtract));
		String year = newDate.toString().split("-")[0];
		String month = newDate.toString().split("-")[1];
		month = Month.of(Integer.parseInt(month)).name().toString().toLowerCase().substring(0, 3);
		// month=month.substring(0,2);
		month = month.substring(0, 1).toUpperCase() + month.substring(1);
		;
		String date = newDate.toString().split("-")[2];
		date = date.replace("0", "");
		System.out.println("month" + month);
		System.out.println("required" + date + " " + month + " " + year);
		return date + "-" + month + "-" + year;
	}

	/**
	 * Method is used to set account hirerachy dropdown in invoice history page
	 * 
	 * @throws Throwable
	 */
	public void setAccountHirerachydropdown(String option) throws Throwable {
		Thread.sleep(2000);
		click(ACCOUNT_SELECTION_DROPDOWN, "Account hirerachy dropdown");
		Thread.sleep(2000);
		click(accountSelectionHirerachyOption(option), "Account heirarchy option");
	}

	public void closeAccountHirearchyDropdow() throws Throwable {
		click(CLOSE_ACCOUNT_SELECTION_DROPDOWN, "Account hirerachy dropdown");
	}

	/**
	 * Method is used to verify header level info
	 * 
	 * @throws Throwable
	 */
	public void verifyHeaderLevelInfo() throws Throwable {
		if (isVisibleOnly(HEADER_LEVEL_INFO, "Header level info")) {
			click(HEADER_LEVEL_INFO, "Header level info", "Header level info");
			List <WebElement> element=driver.findElements(By.xpath("//section[@id='invoiceHeader']//dl"));
			for(int  i=0;i<element.size();i++) {
				if(!element.get(i).getText().equals("")) {
					reporter.SuccessReport("Verify Header evel info", "Header level info present ",element.get(i).getText(), driver);
				}else {
					reporter.failureReport("Verify Header evel info", "Header level info not present ",element.get(i).getText(), driver);
				}
			}
		} else {
			reporter.failureReport("Verify Hedder Level Smart Trackers on Account Management - Invoice History Page",
					"Hedder Level Smart Trackers not Exist", "", driver);
		}
	}

	/**
	 * Method is used to verify line level info
	 * 
	 * @throws Throwable
	 */
	public void verifyLineLevelInfo() throws Throwable {
		if (isVisibleOnly(LINE_LEVEL_INFO, "Header level info")) {
			reporter.SuccessReport("Verify Line Level Smart Trackers on Account Management - Invoice History Page","Line Level Smart Trackers is present", "");
			for(int  i=1;i<=4;i++) {
				By LNLinfo=By.xpath("(//tr//td[@class='footable-visible footable-last-column footable-first-column']//following::text()[1])");
			//if(!element.get(i).getText().equals("")) {
					reporter.SuccessReport("Verify Line Level Smart Trackers on Account Manaement ", "Line Level Smart Trackers on Account Management  present ",getText(LNLinfo, "Line level info").trim(), driver);
				/*}else {
					reporter.failureReport("Verify Line Level Smart Trackers on Account Management ", "Line Level Smart Trackers on Account Management  not present ",element.get(i).getText(), driver);
				}*/
		} 
		}
		else {
			reporter.failureReport("Verify Line Level Smart Trackers on Account Management - Invoice History Page",
					"Line Level Smart Trackers not Exist", "", driver);
	  }
	}

	/**
	 * Method is used to verify license proof link and click on license proof link
	 * 
	 * @throws Throwable
	 */
	public boolean verifyLicenseProofLinkAndClick() throws Throwable {
		boolean status = false;
		if (isVisibleOnly(LICENSE_PROOF_LINK, "License proof link")) {
			status = true;
			reporter.SuccessReport("Verify LicenceProof Link Exist", "LicenceProof Link Exist", "");
			JSClick(LICENSE_PROOF_LINK, "License proof link");

		} else {
			reporter.failureReport("Verify LicenceProof Link Exist", "LicenceProof Link Exist", "", driver);
			status = false;
		}
		return status;
	}

	/**
	 * Method is used to verify license proof pop up
	 * 
	 * @throws Throwable
	 */
	public void verifyLicenseProofPopUp() throws Throwable {
		if (isVisibleOnly(LICENSE_PROOF_POP_UP, "License proof link")) {
			reporter.SuccessReport("Verify Invoice License Proof", "Invoice License Proof Information POPUP Exist", "");
			click(CLOSE_POPUP, "Close popup", "close popup");
		} else {
			reporter.failureReport("Verify Invoice License Proof", "Invoice License Proof Information POPUP not Exist",
					"", driver);
		}

	}

	/**
	 * Method is used to verify details in license proof pop up
	 * 
	 * @throws Throwable
	 */
	public void verifyLicenseProofPopUpDetails(String invoiceNumber, String invoicedate, String salesOrderNumber,
			String customerPONumber) throws Throwable {
		if (isVisibleOnly(licensePopUpDetails(invoiceNumber), "invoice Number")) {
			reporter.SuccessReport("Verify Invoice number", "Invoice number in License Proof Information POPUP Exist",
					"Invoice number");
		} else {
			reporter.failureReport("Verify Invoice number", "Invoice number in License Proof Information POPUP Exist",
					"Invoice number", driver);
		}

		if (isVisibleOnly(licensePopUpDetails(invoicedate), "invoice date")) {
			reporter.SuccessReport("Verify Invoice date", "Invoice date in License Proof Information POPUP Exist",
					"Invoice date");
		} else {
			reporter.failureReport("Verify Invoice date", "Invoice date in License Proof Information POPUP Exist",
					"Invoice date", driver);
		}

		if (isVisibleOnly(licensePopUpDetails(salesOrderNumber), "sales Order Number")) {
			reporter.SuccessReport("Verify sales Order Number",
					"sales Order Number in License Proof Information POPUP Exist", "sales Order Number");
		} else {
			reporter.failureReport("Verify sales Order Number",
					"sales Order Number in License Proof Information POPUP Exist", "sales Order Number", driver);
		}

		if (isVisibleOnly(licensePopUpDetails(customerPONumber), "customer PO Number")) {
			reporter.SuccessReport("Verify customer PO Number",
					"customer PO Number in License Proof Information POPUP Exist", "customer PO Number");
		} else {
			reporter.failureReport("Verify customer PO Number",
					"customer PO Number in License Proof Information POPUP Exist", "customer PO Number", driver);
		}
	}

	/**
	 * Method is used to close license proof pop up
	 * 
	 * @throws Throwable
	 */
	public void closeLicenseProofPopUp() throws Throwable {
		click(CLOSE_LICENSE_PROOF, "Close license proof");
	}

	/**
	 * Method is used to click email link
	 * 
	 * @throws Throwable
	 */
	public void clickEmailLink() throws Throwable {
		if (click(EMAIL_LINK, "email link")) {
			reporter.SuccessReport("Click on Email", "Clicking on ", getText(EMAIL_LINK, "email link") + " Email link");
		}
	}

	/**
	 * Method is used to send mail to colleauge
	 * 
	 * @throws Throwable
	 */
	public void sendToColleauge(String name, String email, String emailReceipents, String comments) throws Throwable {
		if (isVisibleOnly(SEND_TO_COLLEAUGE, "Send to colleauge")) {
			reporter.SuccessReport("Verifying email pop", "Email pop is exists and opened", "");
		} else {
			reporter.failureReport("Verifying email pop", "Email pop is not exists", "");
		}
		if (isVisibleOnly(YOUR_NAME, "Your name")) {
			type(YOUR_NAME, name, "Your name");
		}
		if (isVisibleOnly(YOUR_EMAIL, "Your email")) {
			type(YOUR_EMAIL, email, "Your email");
		}
		if (isVisibleOnly(YOUR_RECEIPENT_EMAIL, " email Receipents")) {
			type(YOUR_RECEIPENT_EMAIL, emailReceipents, " email Receipents");
		}
		if (isVisibleOnly(YOUR_COMMENTS, "comments")) {
			type(YOUR_COMMENTS, comments, "comments");
		}
		click(SEND_EMAIL, "send email");
		waitForVisibilityOfElement(EMAIL_SENT_MESSAGE, "email sent message");
		if (isVisibleOnly(EMAIL_SENT_MESSAGE, "email sent message")) {
			reporter.SuccessReport("Verify email is sent", "Email was sucessfully sent", "");
		} else {
			reporter.failureReport("Verify email is sent", "Email was not sent", "", driver);
		}
		click(CLOSE_EMAIL_POPUP, "Close email pop up");
	}

	/**
	 * Method is used to verify print pop up
	 * 
	 * @throws Throwable
	 */
	public void verifyPrintPopUp() throws Throwable {
		if (isVisibleOnly(PRINT_POP_UP, "print pop up")) {
			reporter.SuccessReport("Verify print pop up", "print pop up Exist", "");
		} else {
			reporter.failureReport("Verify print pop up", "print pop up not Exist", "", driver);
		}

	}

	/**
	 * Method is used to click on invoice preview
	 * 
	 * @throws Throwable
	 */
	public void clickInvoicePreviewLink() throws Throwable {
		Thread.sleep(5000);
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollTo(0,500);");
		waitForVisibilityOfElement(INVOICE_PREVIEW_LINK, "invoice preview link");
		clickUntil(INVOICE_PREVIEW_LINK, INVOICE_PREVIEW_POP_UP, "Invoice preview link");
	}

	/**
	 * Method is used to verify invoice preview pop up
	 * 
	 * @throws Throwable
	 */
	public void verifyInvoicePreviewPopUp() throws Throwable {
		if (isVisibleOnly(INVOICE_PREVIEW_POP_UP, "invoice preview pop up")) {
			reporter.SuccessReport("Verifying invoice preview pop up", "invoice preview pop up is opened", "");
		} else {
			reporter.failureReport("Verifying invoice preview pop up", "invoice preview pop up is not opened", "",
					driver);
		}
	}

	/**
	 * Method is used to verify invoice preview details
	 * 
	 * @throws Throwable
	 */
	public void verifyInvoicePreviewDetails(String details) throws Throwable {
		String result = null;
		String resultOut = null;
		List<WebElement> myList = driver.findElements(INVOICE_PREVIEW_DETAILS);
		List<WebElement> myListOutput = driver.findElements(INVOICE_PREVIEW_DETAILSOutput);
		List<String> all_elements_text = new ArrayList<>();
		for (int i = 0; i < myList.size(); i++) {
			// loading text of each element in to array all_elements_text
			all_elements_text.add(myList.get(i).getText());
			all_elements_text.add(myListOutput.get(i).getText());
			result = myList.get(i).getText();
			resultOut = myListOutput.get(i).getText();

			String strArray[] = details.split(",");
			if (result.equals(strArray[i])) {
				reporter.SuccessReport("Verfying invoice preview details", strArray[i] + " " + "is avilable",
						strArray[i] + "and the value is " + resultOut);
			} else {
				reporter.failureReport("Verfying invoice preview details", strArray[i] + " " + "is not avilable",
						strArray[i], driver);
			}
		}

	}

	/**
	 * Method is used to verify invoice preview foot details
	 * 
	 * @throws Throwable
	 */
	public void verifyInvoicePreviewFootDetails(String footDetails) throws Throwable {
		String result = null;
		List<WebElement> myList = driver.findElements(INVOICE_PREVIEW_FOOT_DETAILS);
		List<String> all_elements_text = new ArrayList<>();
		for (int i = 0; i < myList.size(); i++) {
			// loading text of each element in to array all_elements_text
			all_elements_text.add(myList.get(i).getText());
			result = myList.get(i).getText();
			String strArray[] = footDetails.split(",");
			System.out.println("result" + result);
			System.out.println("all_elements_text" + all_elements_text);
			if (result.equals(strArray[i])) {
				reporter.SuccessReport("Verfying invoice preview details", strArray[i] + " " + "is avilable",
						strArray[i]);
			} else {
				reporter.failureReport("Verfying invoice preview details", strArray[i] + " " + "is not avilable",
						strArray[i], driver);
			}
		}

	}

	/**
	 * Method is used to verify invoice preview header details
	 * 
	 * @throws Throwable
	 */
	public void verifyInvoicePreviewHeaderDetails(String headerDetails) throws Throwable {
		String result = null;
		List<WebElement> myList = driver.findElements(INVOICE_PREVIEW_TABLE_HEADER_DETAILS);
		List<String> all_elements_text = new ArrayList<>();
		for (int i = 0; i < myList.size(); i++) {
			// loading text of each element in to array all_elements_text
			all_elements_text.add(myList.get(i).getText());
			result = myList.get(i).getText();
			String strArray[] = headerDetails.split(",");

			if (result.equals(strArray[i])) {
				reporter.SuccessReport("Verfying invoice preview details", strArray[i] + " " + "is avilable",
						strArray[i]);
			} else {
				reporter.failureReport("Verfying invoice preview details", strArray[i] + " " + "is not avilable",
						strArray[i], driver);
			}
		}

	}

	/**
	 * Method is used to click on close invoice preview
	 * 
	 * @throws Throwable
	 */
	public void closeInvoicePreview() throws Throwable {
		click(CLOSE_INVOICE_PREVIEW, "Close invoice preview");
	}

	/**
	 * Method is used to click on invoice full preview
	 * 
	 * @throws Throwable
	 */
	public void clickOnInvoiceFullPreview() throws Throwable {
		click(SEE_INVOICE_FULL_PREVIEW, "Close invoice preview");
	}

	/**
	 * Method is used to verify invoice preview details ship to and bill to
	 * 
	 * @throws Throwable
	 */
	public void verifyInvoicePreviewShipToAndBillTo() throws Throwable {
		boolean status = false;
		if (isVisibleOnly(INVOICE_PREVIEW_SHIP_TO, "Ship to")) {
			status = true;
			String s1 = Boolean.toString(status);
			reporter.SuccessReport("Verifying invoice preview ship to", "Ship to in invoice pop up is available", s1);
		} else {
			status = false;
			String s1 = Boolean.toString(status);
			reporter.failureReport("Verifying invoice preview ship to", "Ship to in invoice pop up is not available",
					s1, driver);
		}

		if (isVisibleOnly(INVOICE_PREVIEW_BILL_TO, "bill to")) {
			status = true;
			String s1 = Boolean.toString(status);
			reporter.SuccessReport("Verifying invoice preview bill to", "Bill to in invoice pop up is available", s1);
		} else {
			status = false;
			String s1 = Boolean.toString(status);
			reporter.failureReport("Verifying invoice preview bill to", "Bill to in invoice pop up is not available",
					s1, driver);
		}
	}

	/**
	 * Method is used to verify invoice details
	 * 
	 * @throws Throwable
	 */
	public void verifyInvoiceDetails(String invoiceDetails) throws Throwable {
		String result = null;
		List<WebElement> myList = driver.findElements(INVOICE_DETAILS);
		List<String> all_elements_text = new ArrayList<>();
		for (int i = 0; i < myList.size(); i++) {
			// loading text of each element in to array all_elements_text
			all_elements_text.add(myList.get(i).getText());
			result = myList.get(i).getText();
			String strArray[] = invoiceDetails.split(",");
			System.out.println("result" + result);
			System.out.println("all_elements_text" + all_elements_text);
			System.out.println("strArray[i]" + strArray[i]);
			if (result.equals(strArray[i])) {
				reporter.SuccessReport("Verfying invoice preview details", strArray[i] + " " + "is avilable",
						strArray[i]);
			} else {
				reporter.failureReport("Verfying invoice preview details", strArray[i] + " " + "is not avilable",
						strArray[i], driver);
			}
		}

	}

	/**
	 * Method is used to verify advanced search
	 * 
	 * @throws Throwable
	 */
	public void verifyAdvancedSearch() throws Throwable {
		boolean status = false;
		if (isVisibleOnly(ADVANCED_SEARCH, "Advanced search")) {
			status = true;
			String s1 = Boolean.toString(status);
			reporter.SuccessReport("Verify Advaced Search on Invoice History Page", "Advanced Search Link is Exists",
					s1);
		} else {
			status = false;
			String s1 = Boolean.toString(status);
			reporter.failureReport("Verify Advaced Search on Invoice History Page",
					"Advanced Search Link is not Exists", s1, driver);
		}
	}

	/**
	 * Method is used to verify quick search
	 * 
	 * @throws Throwable
	 */
	public void verifyQuickSearch() throws Throwable {
		boolean status = false;

		if (isVisibleOnly(QUICK_SEARCH, "Quick search")) {
			status = true;
			String s1 = Boolean.toString(status);
			reporter.SuccessReport("Verify Quick Search on Invoice History Page", "Quick Search Link is Exists", s1);
		} else {
			status = false;
			String s1 = Boolean.toString(status);
			reporter.failureReport("Verify Quick Search on Invoice History Page", "Quick Search Link is not Exists", s1,
					driver);
		}
	}

	/**
	 * Method is used to click on ascending sort option
	 * 
	 * @throws Throwable
	 */
	public void clickAscendingSortOption() throws Throwable {
		scrollUp();
		radioClick(SORT_BY_ASC, "Ascending order");
	}

	/**
	 * Method is used to click on descending sort option
	 * 
	 * @throws Throwable
	 */
	public void clickDescendingSortOption() throws Throwable {
		scrollUp();
		click(SORT_BY_DES, "descending order");
	}

	/**
	 * Method is used to select number of rows
	 * 
	 * @throws Throwable
	 */
	public void selectRowsDropdown(String numberOfRows) throws Throwable {
		click(ROWS_DROPDOWN, "Rows dropdown");
		click(rowsDropdownOptions(numberOfRows), "Number of rows selected", numberOfRows);
	}

	/**
	 * Method is used to select sort by
	 * 
	 * @throws Throwable
	 */
	public void selectSortByDropdown(String sortByOption) throws Throwable {
		click(SORTBY_DROPDOWN, "Sort By dropdown");
		click(sortByDropdownOptions(sortByOption), "sort By Option");
	}

	/**
	 * Method is used to verify number of results per page
	 * 
	 * @throws Throwable
	 */
	public void verifyResultsPerPage(String resultsPerPage) throws Throwable {
		String actualText = getText(RESULTS_PER_PAGE, "Results per page");

		/*
		 * if(actualText.contains(resultsPerPage)) {
		 * reporter.SuccessReport("Verify Sort By Fields on Invoice SearchPage",
		 * "SortBy Web List Exist", actualText); } else {
		 * reporter.failureReport("Verify Sort By Fields on Invoice SearchPage",
		 * "SortBy Web List not Exist", actualText,driver); }
		 */
	}

	/**
	 * Method is used to click on back to search
	 * 
	 * @throws Throwable
	 */
	public void clickBackToSearch() throws Throwable {
		scrollToBottomWithCordinate("400");
		click(BACK_BUTTON_IN_SEARCH_RESULTS, "Back to search results");
	}

	/**
	 * Method is used to verify back to search link
	 * 
	 * @throws Throwable
	 */
	public void verifyBackToSearchLink() throws Throwable {
		boolean status = false;
		if (isVisibleOnly(BACK_BUTTON_IN_SEARCH_RESULTS, "Back to search results")) {
			status = true;
			String s1 = Boolean.toString(status);

			reporter.SuccessReport("Back to Search Results on  Invoice History Page", "Back To Search Link Exist", s1);
		} else {
			status = false;
			String s1 = Boolean.toString(status);
			reporter.failureReport("Back to Search Results on  Invoice History Page", "Back To Search Link Not Exist",
					s1, driver);
		}
	}

	/**
	 * Method is used to verify export to excel link
	 * 
	 * @throws Throwable
	 */
	public void verifyExportToExcelLink() throws Throwable {
		boolean status = false;
		if (isVisibleOnly(EXPORT_TO_EXCEL, "Export to excel")) {
			status = false;
			String s1 = Boolean.toString(status);
			reporter.SuccessReport("Verify Export to Excell on Invoice History Pagee", "Export To Excel Link Exist",
					s1);
		} else {
			status = false;
			String s1 = Boolean.toString(status);
			reporter.failureReport("Verify Export to Excell on Invoice History Page", "Export To Excel Link Not Exist",
					s1, driver);
		}
	}

	/**
	 * Method is used to verify search results headers
	 * 
	 * @throws Throwable
	 */
	public void verifySearchResultsHeaders(String headers) throws Throwable {
		Thread.sleep(5000);
		String result = null;
		List<WebElement> myList = driver.findElements(SEARCH_RESULTS_TABLE_HEADERS);
		List<String> all_elements_text = new ArrayList<>();
		for (int i = 0; i < myList.size() - 1; i++) {
			// loading text of each element in to array all_elements_text
			all_elements_text.add(myList.get(i).getText());
			result = myList.get(i).getText();
			String strArray[] = headers.split(",");
			System.out.println("result" + result);
			System.out.println("all_elements_text" + all_elements_text);
			if (result.equals(strArray[i])) {
				reporter.SuccessReport("Verify Invoice Fields on Invoice History Page",
						strArray[i] + " " + "Invoice History Records is Exists", strArray[i]);
			} else {
				reporter.failureReport("Verify Invoice Fields on Invoice History Page",
						strArray[i] + " " + "Invoice History Records is Not Exists", strArray[i], driver);
			}
		}

	}

	/**
	 * Method is used to verify pagination
	 * 
	 * @throws Throwable
	 */
	public void verifyPagination() throws Throwable {
		boolean status = false;
		if (isVisibleOnly(PAGINATION, "Pagination")) {
			status = true;
			String s1 = Boolean.toString(status);
			reporter.SuccessReport("Verify Pagination on  Invoice History Page", "Paginations are Exist", s1);
		} else {
			status = false;
			String s1 = Boolean.toString(status);
			reporter.failureReport("Verify Pagination on  Invoice History Page", "Paginations are Not Exist", s1,
					driver);
		}
	}

	/**
	 * Method is used to click on track
	 * 
	 * @throws Throwable
	 */
	public void clickTrackLink() throws Throwable {
		click(TRACK_LINK, "Track link");
	}

	/**
	 * Method is used to verify pagination
	 * 
	 * @throws Throwable
	 */
	public void verifyOrderDetailsPage() throws Throwable {
		boolean status = false;
		waitForVisibilityOfElement(ORDER_DETAILS_PAGE_HEADER, "Order details page");

		if (isVisibleOnly(ORDER_DETAILS_PAGE_HEADER, "Order details page")) {
			status = true;
			String s1 = Boolean.toString(status);
			reporter.SuccessReport("Verify  Account Management - Order Tracking/HistoryPage Loads",
					"Order Details Loaded", s1);
		} else {
			status = false;
			String s1 = Boolean.toString(status);
			reporter.failureReport("Verify  Account Management - Order Tracking/HistoryPage Loads",
					"Order Details Not Loaded", s1, driver);
		}
	}

	/**
	 * Method is used to click back to orders
	 * 
	 * @throws Throwable
	 */
	public void clickBackToOrders() throws Throwable {
		click(BACK_TO_ORDERS, "back to orders");
	}

	/**
	 * Method is used to verify contact name
	 * 
	 * @throws Throwable
	 */
	public void verifyContactName() throws Throwable {
		boolean status = false;
		if (isVisibleOnly(CONTACT_NAME, "Contact name")) {
			status = true;
			String s1 = Boolean.toString(status);
			reporter.SuccessReport("Verifying contact name in order details page", "Contact name exists", s1);
		} else {
			status = false;
			String s1 = Boolean.toString(status);
			reporter.failureReport("Verifying contact name in order details page", "Contact name exists", s1, driver);
		}
	}

	/**
	 * Method is used to click on customer details tab
	 * 
	 * @throws Throwable
	 */
	public void clickCustomerDetailsTab() throws Throwable {
		click(CUSTOMER_DETAILS_TAB, "Customer details tab");
	}

	/**
	 * Method is used to click on account hirearchy link
	 * 
	 * @throws Throwable
	 */

	public void clickShowAccountHirerachy() throws Throwable {
		click(SHOW_ACCOUNT_HIERARCHY, "Show account hirearchy");
	}

	/**
	 * Method is used to verify account hirearchy pop up
	 * 
	 * @throws Throwable
	 */
	public void verifyAccountHirearchyPopUp() throws Throwable {
		boolean status = false;
		if (isVisibleOnly(ACCOUNT_HIERARCHY_POP_UP, "Account hirearchy pop up")) {
			status =true;
			String s1 = Boolean.toString(status);
			reporter.SuccessReport("Verify the Account Hierarchy Tree Popup on ", "Account Hierarchy Tree Popup Exists",
					s1);
		} else {
			status = false;
			String s1 = Boolean.toString(status);
			reporter.failureReport("Verify the Account Hierarchy Tree Popup on ",
					"Account Hierarchy Tree Popup Does Not Exists", s1, driver);
		}

	}

	public void verifyTree() throws Throwable {
		Thread.sleep(6000);

		// waitForVisibilityOfElement(HIERARCHY_TREE, "Hierarchy tree");
		List<WebElement> myList = driver.findElements(HIERARCHY_TREE);
		int countc0 = 0;
		int countc1 = 0;
		int countc2 = 0;
		int countc3 = 0;
		for (int i = 0; i < myList.size(); i++) {
			String tree = myList.get(i).getAttribute("id");
			String status = myList.get(i).getAttribute("checked");

			System.out.println("tree" + tree);
			
			if (tree.contains("c0")) {
				if (countc0 == 0) {
					reporter.SuccessReport("Verify Tree Fields on Insight Invoice or Order History Page ",
							"Tree Level Displays with Great Grand Parent", "C0 is checked:" + status);
					countc0++;
				}
			}

			else if (tree.contains("c1")) {
				if (countc1 == 0) {
					reporter.SuccessReport("Verify Tree Fields on Insight Invoice or Order History Page ",
							"Tree Level Displays with Grand Parent", "C1 is checked:" + status);
					countc1++;
				}

			}

			else if (tree.contains("c2")) {
				if (countc2 == 0) {
					reporter.SuccessReport("Verify Tree Fields on Insight Invoice or Order History Page ",
							" Tree Level Displays with Reporting Parent", "C2 is checked:" + status);
					countc2++;
				}
			} else if (tree.contains("c3")) {
				if (countc3 == 0) {
					reporter.SuccessReport("Verify Tree Fields on Insight Invoice or Order History Page ",
							"Tree Level Displays with Reporting Parent", "C3 is checked:" + status);
					countc3++;
				}
			}
		}

		/*
		 * waitForVisibilityOfElement(HIERARCHY_TREE, "Hierarchy tree"); String
		 * tree=driver.findElement(HIERARCHY_TREE).getAttribute("id"); List<WebElement>
		 * myList=driver.findElements(HIERARCHY_TREE); String
		 * status=driver.findElement(HIERARCHY_TREE).getAttribute("checked");
		 * if(tree.contains("c0")||tree.contains("c1")||tree.contains("c2")) {
		 * for(i=0;i<myList.size();i++) { if(tree.contains("c0")) { reporter.
		 * SuccessReport("Verify Tree Fields on Insight Invoice or Order History Page ",
		 * "Tree Level Displays with Great Grand Parent",
		 * "Tree level hierarchy is checked:" +status); }
		 * 
		 * else if(tree.contains("c1")) { reporter.
		 * SuccessReport("Verify Tree Fields on Insight Invoice or Order History Page ",
		 * "Tree Level Displays with  Grand Parent", "Tree level hierarchy is checked:"
		 * +status); }
		 * 
		 * else if(tree.contains("c2")) { reporter.
		 * SuccessReport("Verify Tree Fields on Insight Invoice or Order History Page ",
		 * "Tree Level Displays with reporting Parent",
		 * "Tree level hierarchy is checked:" +status); } } }
		 */

	}

	/**
	 * Method is used to click on close button of account hierarchy pop up
	 * 
	 * @throws Throwable
	 */
	public void closeHierarchyPopUp() throws Throwable {
		Thread.sleep(5000);
		click(CLOSE_HIERARCHY_POP_UP, "Close hierarchy pop up");
		Thread.sleep(3000);
	}

	public void verifySelectedOptionInAccountSelectionDD(String expected) throws Throwable {
		click(ACCOUNT_SELECTION_DROPDOWN, "Account hirerachy dropdown");
		String selecetdOption = getText(SELECTED_OPTION_IN_ACCOUNT_SELECTION, "Selected option");
		System.out.println("selecetdOption" + selecetdOption);
		System.out.println("expected" + expected);
		if (selecetdOption.equalsIgnoreCase(expected)) {
			reporter.SuccessReport("Verifying default selected option for account selection dropdown",
					"Default selected option is:" + " " + expected, expected);
		} else {
			reporter.failureReport("Verifying default selected option for account selection dropdown",
					"Default selected option is not :" + " " + expected, expected, driver);
		}
	}

	public void getAccountNumber(String expectedAccountNumber) throws Throwable {
		waitForVisibilityOfElement(ACCOUNT_NUMBER, "Account number");
		String strArray[] = expectedAccountNumber.split(",");
		System.out.println("strArray.length" + strArray.length);
		List<WebElement> myList = driver.findElements(ACCOUNT_NUMBER);
		for (int i = 0; i < strArray.length; i++) {
			String accountNumber = myList.get(i).getText();

			if (accountNumber.equalsIgnoreCase(strArray[i])) {
				reporter.SuccessReport("Verifying account number", "Account number is:" + " " + accountNumber,
						strArray[i]);
			} else {
				reporter.failureReport("Verifying account number", "Account number is not:" + " " + accountNumber,
						strArray[i], driver);
			}
		}
	}

	public void getAccountName(String expectedAccountName) throws Throwable {
		waitForVisibilityOfElement(ACCOUNT_NAME, "Account name");
		String strArray[] = expectedAccountName.split(",");
		List<WebElement> myList = driver.findElements(ACCOUNT_NAME);
		for (int i = 0; i < strArray.length; i++) {
			String accountName = myList.get(i).getText();

			if (accountName.equalsIgnoreCase(strArray[i])) {
				System.out.println("strArray[i]" + strArray[i]);
				reporter.SuccessReport("Verifying account name", "Account name is:" + " " + accountName, strArray[i]);
			} else {
				reporter.failureReport("Verifying account name", "Account name is not :" + " " + accountName,
						strArray[i], driver);
			}
		}
	}

	public void verifyDefaultSelectedAccount(String AccountNumber) throws Throwable {

		String strArray[] = AccountNumber.split(",");
		for (int i = 0; i < strArray.length; i++) {

			if (isCheckBoxSelected(defaultSelectedAccount(strArray[i]))) {
				System.out.println("strArray[i]" + strArray[i]);
				reporter.SuccessReport("Verifying default account number",
						"default account number is:" + " " + strArray[i], strArray[i]);
			} else {
				reporter.failureReport("Verifying default account number",
						"default account number is not:" + " " + strArray[i], strArray[i], driver);
			}
		}
	}

	public void clickOnSearchUnderAdvancedSearch() throws Throwable {
		click(btnsearchUnderAdvancedSearch, "search button under advanced search");
		Thread.sleep(10000);
	}

	public void verifyQuoteHistoryReults() throws Throwable {
		isVisibleOnly(lblExpirationDate, "quote history results");
	}

	public void verifyRecentHistoryHeader() throws Throwable {
		isVisibleOnly(recentHistoryHeader, "Recent History");
	}

	public void selectStartandEndDateInRecentHistory(String startDatev) throws Throwable {
		// List<WebElement> date = driver.findElements(startAndEndDate);
		String sday = startDatev.split(" ")[0];
		String smonth = startDatev.split(" ")[1];
		String syear = startDatev.split(" ")[2];

		reporter.SuccessReport("Start date ", "Start Date", startDatev);
		// date.get(i).click();
		click(startDate, "Start date");
		click(MonthOfstartDate, "Clicking on month");
		click(selectMonthFromCalender(smonth), "Month", smonth);

		click(YearOfstartDate, "Year of the start date");
		click(selectYearFromCalender(syear), "Year ", syear);

		click(selectDateFromCalender(sday), "Date", sday);

	}

	public void verifyGPAccountHierarchyTree() throws Throwable {
		List<WebElement> myList = driver.findElements(GP);
		if (myList.size() != 0) {
			for (int i = 1; i <= 2; i++) {
				String gdpdetails = getText(GPDetails(i), "GP Account hierarchy details");
				reporter.SuccessReport("Verify the GP Account Hierarchy Tree Load", "GP Account Hierarchy Tree Loaded",
						"Account name,Account number and address are:" + gdpdetails);
			}
		} else {
			reporter.failureReport("Verify the GP Account Hierarchy Tree Load", "Could not load GP Account Hierarchy ",
					"", driver);
		}
	}

	public void verifyRPAccountHierarchyTree() throws Throwable {
		waitForVisibilityOfElement(ACCOUNT_NUMBER, "Account number");

		List<WebElement> myList = driver.findElements(GP);
		if (myList.size() != 0) {
			for (int i = 1; i <= 2; i++) {
				String rpdetails = getText(RPDetails(i), "RP Account hierarchy details");
				reporter.SuccessReport("Verify the RP Account Hierarchy Tree Load", "RP Account Hierarchy Tree Loaded",
						"Account name,Account number and address are:" + rpdetails);
			}
		} else {
			reporter.failureReport("Verify the RP Account Hierarchy Tree Load", "Could not load RP Account Hierarchy ",
					"", driver);
		}
	}

	public void verifyGGPDetails() throws Throwable {
		getText(GGPDetails, "GGP Account details");
	}

	public void selectEndDateInRecentHistory(String endDate) throws Throwable {
		// List<WebElement> date = driver.findElements(startAndEndDate);

		String eday = endDate.split(" ")[0];
		String emonth = endDate.split(" ")[1];
		String eyear = endDate.split(" ")[2];
		reporter.SuccessReport("End Date ", "End Date is", endDate);
		click(EndDate, "End date");
		click(MonthOfstartDate, "Clicking on month");
		click(selectMonthFromCalender(emonth), "Month", emonth);

		click(YearOfstartDate, "Year of the start date");
		click(selectYearFromCalender(eyear), "Year ", eyear);

		click(selectDateFromCalender(eday), "Date", eday);

	}

	public void getInvoiceNumberFromSearchResults() throws Throwable {
		List<WebElement> myList = driver.findElements(INVOICE_NUMBER);
		if (myList.size() != 0) {
			for (int i = 0; i <= 3; i++) {
				String invoicenumber = (myList.get(i)).getText();
				reporter.SuccessReport("Verify the Invoice details", "Invoice results are displayed",
						"Invoice number:" + invoicenumber);
			}
		} else {
			reporter.failureReport("Verify the Invoice details", "Invoice results are not displayed", "", driver);
		}
	}

	public void getInvoiceStatus() throws Throwable {
		getText(INVOICE_STATUS, "Invoice status");
	}

	public void verifyExcelDownload() throws Throwable {
		Files.deleteIfExists(Paths.get("./\\DownloadedFiles\\orderhistory.xls"));
		click(EXPORT_TO_EXCEL, "export to excel");
		Thread.sleep(5000);
		if (Files.exists(Paths.get("./\\DownloadedFiles\\orderhistory.xls"))) {
			reporter.SuccessReport("Export excel verification", "Exported excel file successfully", "");
		} else {
			reporter.failureReport("Excel download verification", "Could not export excel file", "", driver);
		}
	}

	public boolean verifyInvoiceHistoryLabel() throws Throwable {
		return isVisibleOnly(headerInvoiceHistory, "Invoice History");
	}

	public void clickOnSearchByInInvoiceHistory() throws Throwable {
		Thread.sleep(3000);
		click(CanadaObj.drpSearchByInInvoiceHistory, "Sort by drop down");
	}

	public void selectSearchBy(String searchby) throws Throwable {
		selectByVisibleText(CanadaObj.drpSearchBy, searchby, "Sort by dropdown");
	}
	
	public void clickOnInvoiceDetailsTab() throws Throwable {
		click(INVOICE_DETAILS_TAB, "Invoice detais tab", "");
	}

}
