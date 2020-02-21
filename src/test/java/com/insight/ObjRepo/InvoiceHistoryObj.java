package com.insight.ObjRepo;



import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.insight.accelerators.ActionEngine;

public class InvoiceHistoryObj extends ActionEngine {
	public static By searchResultsTable = By.xpath("//div[@id='resultsTable']");

	public static By ADVANCED_SEARCH = By.xpath("//h3[text()='Advanced Search']");
	public static By ADAVANCED_SEARCH_SEARCHBUTTON = By.xpath("//input[@id='invoiceAdvSearch'] | //*[text()='Advanced order search']");
	public static By ACCOUNT_SELECTION_DROPDOWN = By.xpath("//div[@class='nice-select m-b-md']");
	public static By CLOSE_ACCOUNT_SELECTION_DROPDOWN = By.xpath("//div[@class='nice-select m-b-md open']");

	//public static By COSE_ACCOUNT_TOOLS=By.xpath("//div[@id='manage-wrapper']//div[@class='sidebar-nav']");
	public static By COSE_ACCOUNT_TOOLS = By.xpath("//div[@class='sidebar-nav']//*[@class='ion-chevron-left']");


	public static By START_DATE_CALENDER = By.xpath("//input[@id='startDate']");
	public static By MONTH_AND_YEAR_START_DATE_CALENDER = By.xpath("//table[@class=' table-condensed']//th[@class='date-switch']");
	public static By BACK_BUTTON_START_DATE_CALENDER = By.xpath("//div[@class='datepicker-months']//thead//i[@class='fa fa-chevron-left fi-arrow-left'] | (//div[@class='datepicker-months']//thead//i[@class='fa fa-chevron-left fi-arrow-left'])[1]");
	public static By YEAR_START_DATE_CALENDER = By.xpath("//div[@class='datepicker-months']//th[@class='date-switch']");

	public static By dayInStartDayCalender(String date) {
		return By.xpath("//table[@class=' table-condensed']//tbody//tr//td[text()='" + date + "' and @class='day undefined ']");
	}

	public static By monthInStartDateCalnder(String month) {
		return By.xpath("//div[@class='datepicker-months']//tbody/tr//td//span[contains(@class,'month') and contains(text(),'" + month + "')]");
	}

	//end date calender
	public static By END_DATE_CALENDER = By.xpath("//input[@id='endDate']");
	public static By MONTH_AND_YEAR_END_DATE_CALENDER = By.xpath("(//table[@class=' table-condensed']//th[@class='date-switch'])[2]");
	public static By BACK_BUTTON_END_DATE_CALENDER = By.xpath("(//div[@class='datepicker-months']//thead//i[@class='fa fa-chevron-left fi-arrow-left'])[2]");
	public static By YEAR_END_DATE_CALENDER = By.xpath("(//div[@class='datepicker-months']//th[@class='date-switch'])[2]");

	public static By dayInEndDayCalender(String date) {
		return By.xpath("//table[@class=' table-condensed']//tbody//tr//td[text()='" + date + "' and @class='day undefined ']");
	}

	public static By monthInEndDateCalnder(String month) {
		return By.xpath("(//div[@class='datepicker-months']//tbody/tr//td//span[contains(@class,'month') and contains(text(),'" + month + "')])[2]");
	}

	public static By accountSelectionHirerachyOption(String option) {
		return By.xpath("//div[@class='nice-select m-b-md open']//ul//li[text()='" + option + "']");
	}

	//Invoice page after search results
	public static By HEADER_LEVEL_INFO = By.xpath("//a[text()='Header Level Info']");
	public static By LINE_LEVEL_INFO = By.xpath("//td[@class='footable-visible footable-last-column footable-first-column']");
	public static By LICENSE_PROOF_LINK = By.xpath("//a[@data-page='licenseProof']");
	public static By LICENSE_PROOF_POP_UP = By.xpath("//h5[text()='Proof of License Purchase']");
	public static By CLOSE_POPUP=By.xpath("//a[@class='close-reveal-modal']");
	public static By INVOICE_DETAILS_TAB=By.xpath("//a[@href='#invoiceDetails']");

	public static By licensePopUpDetails(String details) {
		return By.xpath("//h5[text()='Proof of License Purchase']//parent::div//dl//dt[contains(text(),'" + details + "')]");
	}

	public static By CLOSE_LICENSE_PROOF = By.xpath("//div[@id='js-license-proof']//a[@class='close-reveal-modal']");
	public static By EMAIL_LINK = By.xpath("//a[@class='icon email has-tip tip-top']");
	public static By SEND_TO_COLLEAUGE = By.xpath("//h2[text()='Send to Colleague']");
	public static By YOUR_NAME = By.xpath("//input[@id='yournameinput']");
	public static By YOUR_EMAIL = By.xpath("//input[@id='youremailinput']");
	public static By YOUR_RECEIPENT_EMAIL = By.xpath("//input[@id='recipientemailinput']");
	public static By YOUR_COMMENTS = By.xpath("//textArea[@id='yourcommentsinput']");
	public static By SEND_EMAIL = By.xpath("//a[@id='send-email']");
	public static By EMAIL_SENT_MESSAGE = By.xpath("//div[@class='displayMessage']");
	public static By CLOSE_EMAIL_POPUP = By.xpath("//div[@id='send-email-modal']//a[@class='close-reveal-modal']");
	public static By PRINT_POP_UP = By.xpath("//div[@id='js-print-invoice-target']");
	public static By INVOICE_PREVIEW_LINK = By.xpath("//div[@id='resultsTable']//td[@class='footable-visible footable-last-column']//ul//li//a");
	public static By INVOICE_PREVIEW_POP_UP = By.xpath("//h3[text()='Invoice Preview:']");

	public static By invoicePreviewDetails(String details) {
		return By.xpath("//div[@id='previewInvoice']//dl//dt[text()='" + details + "']");
	}

	public static By INVOICE_PREVIEW_DETAILS = By.xpath("//div[@id='previewInvoice']//dl//dt");
	public static By INVOICE_PREVIEW_DETAILSOutput = By.xpath("//div[@id='previewInvoice']//dl//dd");
	public static By INVOICE_PREVIEW_FOOT_DETAILS = By.xpath("//table[@class='footable dark']//tfoot//tr//tbody//tr//td[1]");
	public static By INVOICE_PREVIEW_TABLE_HEADER_DETAILS = By.xpath("//table[@class='footable dark']//thead//th");
	public static By CLOSE_INVOICE_PREVIEW = By.xpath("//a[@class='icon close ']");
	public static By SEE_INVOICE_FULL_PREVIEW = By.xpath("//a[text()='See full invoice details']");
	public static By INVOICE_PREVIEW_SHIP_TO = By.xpath("//label[text()='Ship To:']");
	public static By INVOICE_PREVIEW_BILL_TO = By.xpath("//label[text()='Bill To:']");
	public static By INVOICE_DETAILS = By.xpath("//section[@id='invoiceDetails']//dl//dt");

	public static By QUICK_SEARCH = By.xpath("//h3[text()='Quick Search']");
	//Advanced search
	public static By SORT_BY_ASC = By.xpath("//input[@id='ascInv']//parent::label//span");
	public static By SORT_BY_DES = By.xpath("//input[@id='descInv']//parent::label//span");
	public static By ROWS_DROPDOWN = By.xpath("//legend[text()='Rows']//parent::div//div[@class='nice-select invert']");

	public static By rowsDropdownOptions(String numberOfRows) {
		return By.xpath("//legend[text()='Rows']//parent::div//div[@class='nice-select invert open']//ul//li[text()='" + numberOfRows + "']");
	}

	public static By SORTBY_DROPDOWN = By.xpath("//legend[text()='Sort By:']//parent::div//div[@class='nice-select invert']");

	public static By sortByDropdownOptions(String numberOfRows) {
		return By.xpath("//legend[text()='Sort By:']//parent::div//div[@class='nice-select invert open']//ul//li[text()='Status']");
	}

	public static By RESULTS_PER_PAGE = By.xpath("//div[@id='filter-by-count']//div[@class='nice-select ']//span");
	public static By BACK_BUTTON_IN_SEARCH_RESULTS = By.xpath("//a[@class='icon back has-tip tip-top backToSearch']");
	public static By EXPORT_TO_EXCEL = By.xpath("//a[@class='icon export has-tip tip-top exportToExcel']");
	public static By SEARCH_RESULTS_TABLE_HEADERS = By.xpath("//div[@id='resultsTable']//thead//tr[1]//th");
	public static By PAGINATION = By.xpath("//div[@id='filter-by-count']");
	public static By TRACK_LINK = By.xpath("//a[text()='Track']");
	public static By ORDER_DETAILS_PAGE_HEADER = By.xpath("//h1[@class='order-details__header-title']");
	public static By BACK_TO_ORDERS = By.xpath("//a[text()='Back to orders']");
	public static By CONTACT_NAME = By.xpath("//span[@class='customer-detail__label']");
	public static By CUSTOMER_DETAILS_TAB = By.xpath("//a[text()='Customer details']");
	public static By SHOW_ACCOUNT_HIERARCHY = By.xpath("//a[@id='accountHierarchytoggle']");
	public static By ACCOUNT_HIERARCHY_POP_UP = By.xpath("//div[@id='accountSelectionModal']");
	//public static By HIERARCHY_TREE = By.xpath("//*[@id='TableContent']//td[@class='wtvtd1']//input");
	public static By HIERARCHY_TREE = By.xpath("//*[@type='checkbox']");
	public static By CLOSE_HIERARCHY_POP_UP = By.xpath("//a[@class='button small m-b-none']");
	public static By SELECTED_OPTION_IN_ACCOUNT_SELECTION = By.xpath("//div[@class='nice-select m-b-md open']//ul//li[@class='option selected focus']");
	//Hierarchy pop up
	public static By ACCOUNT_NAME = By.xpath("//td[@class='wtvtd1']//tbody//tr//td[1]");
	public static By ACCOUNT_NUMBER = By.xpath("//td[@class='wtvtd1']//tbody//tr//td[2]");

	public static By defaultSelectedAccount(String accountNumber) {
		return By.xpath("//table[@class='wtvtable1']//tbody//tr//td//input[contains(@id,'" + accountNumber + "')]");
	}

	public static By btnsearchUnderAdvancedSearch = By.xpath("//input[@id='quoteAdvSearch']");
	public static By lblExpirationDate = By.xpath("(//*[text()='Expiration Date'])[2]");
	public static By recentHistoryHeader = By.xpath("//*[text()='Recent Orders']");

	public static By startAndEndDate = By.xpath("//*[@class='form__field date-picker__input']");
	public static By MonthOfstartDate = By.xpath("//*[@class='react-datepicker__month-read-view--selected-month']");
	public static By YearOfstartDate = By.xpath("//*[@class='react-datepicker__year-read-view--selected-year']");
	public static By dateForStartDate = By.xpath("//*[@class='react-datepicker__month-read-view--selected-month']");

	public static By startDate =By.xpath("(//*[@class='form__field date-picker__input'])[1]");
	public static By EndDate =By.xpath("(//*[@class='form__field date-picker__input'])[2]");



	public static By selectMonthFromCalender(String month) {

			return By.xpath("//*[@class='react-datepicker__month-dropdown']//div[text()='"+month+"']");
}
public static By selectYearFromCalender(String year) {

			return By.xpath("//*[@class='react-datepicker__year-dropdown']//div[text()='"+year+"']");
}
public static By selectDateFromCalender(String date) {

			return By.xpath("(//*[@class='react-datepicker__month']//div[text()='"+date+"'])[1]");
}

	//public static By btnsearchUnderAdvancedSearch= By.xpath("//input[@id='quoteAdvSearch']");
	//public static By lblExpirationDate=By.xpath("(//*[text()='Expiration Date'])[2]");
	
	public static By GGPDetails=By.xpath("(//*[contains(@id,'b0')]//parent::td/following::tr)[1]");
	public static By GP=By.xpath("//*[contains(@id,'b2')]");
	public static By GPDetails(int i) {
		return By.xpath("(//*[contains(@id,'b2')]//parent::td/following::td/div/table/tbody/tr)["+i+"]");
	}
	public static By RP=By.xpath("//*[contains(@id,'b3')]");
	public static By RPDetails(int i) {
		return By.xpath("((//*[contains(@id,'b3')])[1]//parent::td/following::td/table/tbody/tr)["+i+"]");
	}
	public static By INVOICE_NUMBER =By.xpath("//*[@id='invoiceSearch']/tbody/tr/td/a");
	public static By INVOICE_STATUS=By.xpath("(//*[@id='invoiceSearch']/tbody/tr/td[5])[1]");

	public static By headerInvoiceHistory =By.xpath("//h2[text()='Invoice History']");
	public static By getInvoiceNumber = By.xpath("//*[@id=\"invoiceSearch\"]//tbody//td//span//following-sibling::a");
}
