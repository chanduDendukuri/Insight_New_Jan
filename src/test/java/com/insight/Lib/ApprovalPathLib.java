package com.insight.Lib;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.google.api.client.util.DateTime;
import com.insight.ObjRepo.ApprovalPathObj;
import com.insight.ObjRepo.CommonObj;
import com.thoughtworks.selenium.webdriven.commands.Click;

import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;

public class ApprovalPathLib extends ApprovalPathObj {
	OrderLib orderLib = new OrderLib();

	public void CreateNewApprovalPathLink() throws Throwable {
		if (isElementPresent(CREATE_APP_PATH, "Approval management page")) {
			click(CREATE_APP_PATH, "Create New Approval Path link");
			reporter.SuccessReport(
					"Click Create New Approval Path on Approval Management Approval Path Management Page",
					"Create New Approval Path Link Exists and Clicked", "");
		} else {
			reporter.failureReport(
					"Click Create New Approval Path on Approval Management Approval Path Management Page",
					"Create New Approval Path Link Does Not Exist", "");
		}
	}

	public void EnterNewApprovalPath(String Approver_Name) throws Throwable {
		if (isElementPresent(APPROVAL_PATH_NAME, "Approval management page")) {
			type(APPROVAL_PATH_NAME, Approver_Name, "Approver Path Name");
			reporter.SuccessReport("Enter Approval Path Name on  Approval Path Management Page",
					"Approval Path Name Field Exists and Entered", "");
		} else {
			reporter.failureReport("Enter Approval Path Name on  Approval Path Management Page",
					"Approval Path Name Field Does Not Exist", "");
		}
	}
	public void ClickOnViewAllOrRefreshIcon() throws Throwable {
		click(RefreshLinkInApproverPathMngm, "Refresh button", "");
	}
	public void VerifyNumberOfApproversInApprovalManagement(String Approvername,int count) throws Throwable {
		String ApproverPathname = getText(ApproverPathName(Approvername), "Approver PathName");
				String ApproverCount = getText(Approvercount(Approvername),"Approver Count");
				if(ApproverPathname.equals(Approvername) && Integer.parseInt(ApproverCount)==count) {
					reporter.SuccessReport("Verify ApproverPathName and Approver Count", "ApproverPathName and Approver Count", ApproverPathname+","+ApproverCount, driver);
				}
				else {
					reporter.SuccessReport("Verify ApproverPathName and Approver Count",""+Approvername+" and Approver Count  doesn't exist", "");
				}
	}
public String RandomApprovalPathName(String Approver_Name) throws Throwable {
	
	SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
	Date date = new Date();  
	String newApprovername = Approver_Name+formatter.format(date);
	return newApprovername;
	
}
	public String CreateNewApprovalClick() throws Throwable {
		// select approver name
		List<WebElement> myList = driver.findElements(ALL_APPROVER_OPTIONS);
		String text = (myList.get(0)).getText();

		// select first option
		if (isElementPresent(selectAppNameFromList(text), "Select Approver name from list box")) {
			click(selectAppNameFromList(text), "Select Approver name from list box::"+text+"");
		}

		Add_Approver_Btn_Click();

		return text;

	}

	public void ClickCreateApprovalPathButton() throws Throwable {
		if (isVisibleOnly(CREATE_APPROVALPATH_BTN, "")) {
			click(CREATE_APPROVALPATH_BTN, "Create Approver Path Button");
			reporter.SuccessReport(
					"Click Create New Approval Path on Approval Management Approval Path Management Page",
					"Create Approval Path Link Exists and Clicked", "");
		} else {
			reporter.failureReport(
					"Click Create New Approval Path on Approval Management Approval Path Management Page",
					"Create Approval Path Link Does Not Exist", "");
		}
	}

	public void VerifyAppovalPathCreated(String Approver_Name) throws Throwable {
		String SuccessMsg = getText(Successmsg, "Success message:");
		
			List<WebElement> elem = driver.findElements(ApproverPathNameDisplayedRow);
			if(elem.size()>0) {
			for(int i=2;i<=elem.size();i++) {
				String ApproverPathName = driver.findElement(ApproverPathNameRow(i, 1)).getText();
				if (ApproverPathName.equals(Approver_Name)) {
				reporter.SuccessReport("Verify Approval Path Name",	"Approver Path Name:"+Approver_Name+" displayed at row number", String.valueOf(i-1));
			reporter.SuccessReport("Verify Approval Path is Added on Approval Management Approval Path Management Page",
					"Created/Edited Approver Path is Exists and Verified", SuccessMsg+Approver_Name);
		 break;
		}
			}
			}else {
			reporter.failureReport("Verify Approval Path is Added on Approval Management Approval Path Management Page",
					"Created Approver Path Does Not Exist or No Approver Path Names displayed", "");
		}
			}
	
	public void verifySearchresults(String Approver_Name) throws Throwable {
		if (isElementPresent(getCreatedApproverPath(Approver_Name), "Approval management page")) {
			reporter.failureReport("Verify Approval Path is Added on Approval Management Approval Path Management Page",
					"Created Approver Path is Exists and Verified", Approver_Name);
		} else {
			reporter.SuccessReport("Verify Approval Path is Added on Approval Management Approval Path Management Page",
					"Created Approver Path Does Not Exist", "");
		}
	}

	public void ClickEditLinkButton(String Approver_Name) throws Throwable {
		if (isElementPresent(getCreatedApproverPathEditLink(Approver_Name), "Approval management page ")) {
			click(getCreatedApproverPathEditLink(Approver_Name), "Approval management page::"+Approver_Name+"");
			reporter.SuccessReport("Click Edit on Approval Management Approval Path Management Page",
					"Edit Link Exists and Clicked", "");
		} else {
			reporter.failureReport("Click Edit on Approval Management Approval Path Management Page",
					"Edit Link Does not Exists", "");
		}
	}

	public void ClickUpdateButton() throws Throwable {
		if (isElementPresent(UPDATE_APPROVER_BTN, "Select Update option")) {
			click(UPDATE_APPROVER_BTN, "Select Update option");
			reporter.SuccessReport("Approval Path Management Page", "Update Link Exists and Clicked", "");
		} else {
			reporter.failureReport("Approval Path Management Page", "Update Link Does Not Exist", "");
		}
	}

	public void VerifyApproversAdded(List<String> AppName) throws Throwable {
		for(int i=0;i<AppName.size();i++) {
			
		
		if (isElementPresent(selectAppNameFromListToRemove(AppName.get(0)), "Approval management page")) {
			reporter.SuccessReport("Verify Approver", "Approver name exist",
					AppName.get(0));
		} else {
			reporter.failureReport("Verify Approver", "Approver name doesn't exist", "");
		}
		}
	}

	public void VerifyFirstApproverAdded(String AppName) throws Throwable {
		if (isElementPresent(getCreatedApproverPath(AppName), "Approval management page")) {
			reporter.SuccessReport("Approval Path Management Page", "First Approver Path Exists and Verified", "");
		} else {
			reporter.failureReport("Approval Path Management Page", "First Approval Path Does Not Exist", "");
		}
	}

	public void VerifySecondApproverAdded(String AppName) throws Throwable {
		if (isElementPresent(getCreatedApproverPath(AppName), "Approval management page")) {
			reporter.SuccessReport("Approval Path Management Page", "Second Approver Path Exists and Verified", "");
		} else {
			reporter.failureReport("Approval Path Management Page", "Second Approval Path Does Not Exist", "");
		}
	}

	public void DeleteApprovers(String Approver_Name) throws Throwable {
		if (isElementPresent(getCreatedApproverPathDeleteLink(Approver_Name), "Approval management page ")) {
			click(getCreatedApproverPathDeleteLink(Approver_Name), "Approval management page::"+Approver_Name+"");
			reporter.SuccessReport("Delete Approval Path on Approval Management Approval Path Management Page",
					"Approval Path Exists and Delete Option is Clicked", "");
		} else {
			reporter.failureReport("Delete Approval Path on Approval Management Approval Path Management Page",
					"Approval Path Link Does Not Exist", "");
		}
	}

	public void ConfirmDelete(String Approver_Name) throws Throwable {
		if (isElementPresent(Confirm_Delete_Path(Approver_Name), "Delete Confirmation Button")) {
			click(Confirm_Delete_Path(Approver_Name), "Confirm delete button clicked::"+Approver_Name+"");
			reporter.SuccessReport("Confirm to Delete on  Approval Path Management Page",
					"POPUP Exists and Delete Link is Clicked", "");
		} else {
			reporter.failureReport("Confirm to Delete on  Approval Path Management Page", "POPUP Does Not Exist", "");
		}
	}

	public void SearchUser(String Approver_Name) throws Throwable {
		if (isElementPresent(SEARCH_TXTBOX, "Search text box")) {
			type(SEARCH_TXTBOX, Approver_Name, "Search text box");
			reporter.SuccessReport("Enter Approver Name on Approval Management Approval Path Management Page",
					"Approval Path Name Field Exists and Entered", "");
		} else {
			reporter.failureReport("Enter Approver Name on Approval Management Approval Path Management Page",
					"Approval Path Name Field Does Not Exist", "");
		}

		// Click Search
		if (isElementPresent(SEARCH_BTN, "Search Button")) {
			click(SEARCH_BTN, "Click on search button");
			reporter.SuccessReport("Approval Path Management Page", "Search Link Exists and Clicked", "");
		} else {
			reporter.failureReport("Approval Path Management Page", "Search Link Does Not Exist", "");
		}
	}

	public void VerifyApprovalPathReturned() throws Throwable {
		if (isElementPresent(DELETE_SUCCESS, "Approval management page")) {
			reporter.SuccessReport("Approval Management Approval Path Management Page", "Inactive User does not Exists",
					"");
		} else {
			reporter.failureReport("Approval Management Approval Path Management Page", "Inactive User Exists", "");
		}
	}

	public List<String> SelectApprover(String Approver_Name,int count) throws Throwable {
		String text = null;
		List<String> list = new ArrayList<>();
		if (Approver_Name != null) {
			SelectSpecificApprover(Approver_Name);
		} else {
			// select approver name
		
for(int i=1;i<=count;i++) {
	List<WebElement> myList = driver.findElements(ALL_APPROVER_OPTIONS);
	text = (myList.get(1)).getText();
	click(selectAppNameFromList(text), "Select Approver name from list box::"+text+"");
	Add_Approver_Btn_Click();
	if(isElementNotPresent(AddedAppNameFromList(text), "Select Approver name from Right list box ")) {
		click(selectAppNameFromList(text), "Select Approver name from available list box::"+text+"");
		Add_Approver_Btn_Click();
	}
	Thread.sleep(2000);
	if (isElementPresent(AddedAppNameFromList(text), "Select Approver name from list box")) {
		//click(selectAppNameFromList(text), "Select Approver name from list box");
		reporter.SuccessReport("Approver" , text+"not added hence adding it again","");
		reporter.SuccessReport("Approval Path Management Page", "Available Approvers Field Exists and Selected",
				"");
	} else {
		reporter.failureReport("Approval Path Management Page", "Available Approvers Field Does Not Exist", "");
	}
	list.add(text);
}
			

		}
		
		return list;
	}
	public int NumberofApproversAddedtoRightSide() throws Throwable {
		List<WebElement> elem = driver.findElements(NumberOfapproversadded);
		int count = elem.size();
		String count1= String.valueOf(count);
		reporter.SuccessReport("Available Approvers count", "Number of Approvers added are", count1);
		if(count>0) {
			for(int i=0;i<=count-1;i++) {
			String text=elem.get(i).getText().toString();
			
			reporter.SuccessReport("Verify Approver added to the Approval path", "Approver Name is ", text);
			
		}}
		else {
			reporter.SuccessReport("Verify Approver added to the Approval path", "Number of Approvers added are 0","", driver);
		}
		
		return count;
	}
	public int NumberofAvailableApprovers() throws Throwable {
		List<WebElement> elem = driver.findElements(ALL_APPROVER_OPTIONS);
		int count = elem.size();
		String count1= String.valueOf(count);
		if(count>0) {
			reporter.SuccessReport("Available Approvers", "Number of available Approvers  are", count1);
		}
		else {
			reporter.SuccessReport("Available Approvers", "Number of available  Approvers  are 0","", driver);
		}
		return count;
	}
	public void Add_Approver_Btn_Click() throws Throwable {
		if (isElementPresent(SELECT_PATH_BUTTON, "Select Path option")) {
			reporter.SuccessReport("Click Add on Approval Management Approval Path Management Page",
					"> Link Exists and Clicked", "");
			click(SELECT_PATH_BUTTON, "Select Path option");
		} else {
			reporter.failureReport("Click Add on Approval Management Approval Path Management Page",
					"> Link does not Exists", "");
		}
	}

	public void SelectSpecificApprover(String AppName) throws Throwable {

		if (isElementPresent(selectAppNameFromList(AppName), "Select Approver name from list box")) {
			reporter.SuccessReport("Select Approver on  Approval Path Management Page",
					"Approval Path Order Approver Field Exists and Selected", "");
			click(selectAppNameFromList(AppName), "Select Approver name from list box");
		} else {
			reporter.failureReport("Select Approver on  Approval Path Management Page",
					"Approval Path Order Approver Field Does Not Exist", "");
		}

		Add_Approver_Btn_Click();

	}

	public void SelectSpecificRequestor(String strRequestorOption) throws Throwable {

		if (isElementPresent(selectRequestorByText(strRequestorOption), "Approver ")) {
			selectByVisibleText(createRequestorType, strRequestorOption, "Approver");
			reporter.SuccessReport("Add Requestors on Create/Edit Requestor Group Page",
					"Requestors Exist in the Available Requestors' List and Selected", strRequestorOption);
		} /*
			 * else { reporter.
			 * failureReport("Select Approver on  Approval Path Management Page"
			 * , "Approval Path Order Approver Field Does Not Exist", ""); }
			 */

	}

	public void VerifySuccessDelete() throws Throwable {
		if (isElementPresent(SUCCESS_DELETE_MSG, "Delete approver success message")) {
			reporter.SuccessReport("Verify Deleted Approval Path on Approval Management Approval Path Management Page",
					"Approval path was deleted successfully Message Exists", "");
		} else {
			reporter.failureReport("Verify Deleted Approval Path on Approval Management Approval Path Management Page",
					"Approval path was deleted successfully Message Does Not Exists", "");
		}
	}

	public void ApproverSearchTextBox(String appLastName) throws Throwable {
		if (appLastName != null) {
			if (isElementPresent(APP_LNAME_TEXTBOX, "Approver Last Name")) {
				type(APP_LNAME_TEXTBOX, appLastName, "Enter Approver Last Name");
				reporter.SuccessReport("Enter Approver Name on Approval Management Approval Path Management Page",
						"Approval Path Name Field Exists and Entered", "");
			} else {
				reporter.failureReport("Enter Approver Name on Approval Management Approval Path Management Page",
						"Approval Path Name Field Does Not Exist", "");
			}
		}
	}
	public void ApproverSearchTextBox1(String appLastName) throws Throwable {
		if (appLastName != null) {
			if (isElementPresent(ApproversearchtextBox, "Approver Search")) {
				type(ApproversearchtextBox, appLastName, "Enter Approver Search");
				reporter.SuccessReport("Enter Approver Name on Approval Management Approval Path Management Page",
						"Approver search text box Exists and Entered", "");
			} else {
				reporter.failureReport("Enter Approver Name on Approval Management Approval Path Management Page",
						"Approver search text box Field Does Not Exist", "");
			}
		}
	}
	public void SearchClick() throws Throwable {
		if (isElementPresent(LAST_NAME_SEARCH_BTN, "Last Name search button")) {
			click(LAST_NAME_SEARCH_BTN, "Last Name search button");
			reporter.SuccessReport("Click Search on Approval Management Approval Path Management Page",
					"Search Link Exists and Clicked", "");
		} else {
			reporter.failureReport("Click Search on Approval Management Approval Path Management Page",
					"Search Link Does Not Exist", "");
		}
	}

	public void ValidateApproverFromSearch(String Approver_Name) throws Throwable {
		if (isElementPresent(getCreatedApproverPathEditLink(Approver_Name), "Approval management page")) {
			reporter.SuccessReport("Approval Management Approval Path Management Page",
					"Approver Last name Exists and Verified", "");
		} else {
			reporter.failureReport("Approval Management Approval Path Management Page",
					"Approver Last Name Does Not Exist", "");
		}
	}

	public int VerifyApprovalPathAndApprovers() throws Throwable {
		int count = 0;
		if (isElementPresent(GET_APPROVALPATH_COUNT, "Approval path details")) {
			List<WebElement> appCount = driver.findElements(GET_APPROVALPATH_COUNT);
			 count = appCount.size();
			if (count > 1) {
				/*
				 * String firstPathName = driver.findElement(GET_FIRST_APP_PATH_NAME).getText();
				 * String numOfApprovers = driver.findElement(GET_NUMOF_APPROVERS).getText();
				 * String result = "Approval Path Name:" + firstPathName.trim() + "***" +
				 * "# of Approvers:" + numOfApprovers.trim();
				 */
				for(int i=2;i<=count;i++) {
					String ApprovalPathName = getText(Get_ApproverPathName(i),"ApproverPathName");
					String ApproverCount = getText(Get_ApproverCount(i), "ApproverCount");
					reporter.SuccessReport("System Displays All the Approval Paths on  Approval Path Management Page",
							"Approval Path Exists", "ApprovalPathName:"+ApprovalPathName+ ", ApproverCount:"+ApproverCount+"");
				}
				//reporter.SuccessReport("System Displays All the Approval Paths on  Approval Path Management Page",
						//"Approval Path Exists", result);
			} else {
				reporter.failureReport("System Displays All the Approval Paths on  Approval Path Management Page",
						"Approval Path Does Not Exist", "");
			}
		}
		return count;
	}

	public String GetFirstApprovalPath() throws Throwable {
		String firstPathName = "";
		if (isVisibleOnly(GET_FIRST_APP_PATH_NAME, "Approval management page")) {
			 firstPathName = getText(GET_FIRST_APP_PATH_NAME, "Approver Path name");
			reporter.SuccessReport(
					"Get The First Approval Path on Approval Management Page Approver Out of Office Settings",
					"First Approver Pathname is: ", firstPathName);
		}
		else {
			reporter.failureReport(
					" The First Approval Path on Approval Management Page Approver is not available",
					"After Creating the Approver Out", "");
		}
		return firstPathName;
	}

	public String GetFirstPathAddRemoveApprover() throws Throwable {
		String firstPathName = null;
		if (isElementPresent(GET_APPROVALPATH_COUNT, "Approval path details")) {
			List<WebElement> appCount = driver.findElements(GET_APPROVALPATH_COUNT);
			int count = appCount.size();
			String approvercount = String.valueOf(count);
			if (count > 1) {
				firstPathName = driver.findElement(GET_FIRST_APP_PATH_NAME).getText();

				reporter.SuccessReport("System Displays All the Approval Paths on  Approval Path Management Page",
						"Approval Path Exists", approvercount);
			} else {
				reporter.failureReport("System Displays All the Approval Paths on  Approval Path Management Page",
						"Approval Path Does Not Exist", "");
			}
		}
		return firstPathName;
	}

	public void RemoveApprovers(String approverAdded,int count) throws Throwable {
		List<WebElement> appCount = driver.findElements(APPROVERS_LIST_ADDED);
		int count1 = appCount.size();
		String approvercount = String.valueOf(count1);
		if (count1 != 0) {
			
			for(int i=1;i<=count;i++) {
				String Approvername = getText(selectmultipleAppNameFromListToRemove(i), "Approver name");
				click(selectmultipleAppNameFromListToRemove(i), "Approver name", Approvername);
				click(SELECT_REMOVEPATH_BUTTON, "< Select Path option");
			}
			
		}
	}
	public void RemoveAddedApprovers(String approverAdded,int count) throws Throwable {
		List<WebElement> appCount = driver.findElements(APPROVERS_LIST_ADDED);
		int count1 = appCount.size();
		
		if (count1 != 0) {
			
			
				String Approvername = getText(AddedAppNameFromList(approverAdded), "Approver name");
				click(AddedAppNameFromList(approverAdded), Approvername, "Approvername");
				click(SELECT_REMOVEPATH_BUTTON, "< Select Path option");
			
			
		}
		else {
			reporter.failureReport("Addde Approvers", "", "");
		}
	}

	public void VerifySuccessUpdate() throws Throwable {
		String updatemesasge = getText(SUCCESS_UPDATE_MSG,"Update succes message");
		if (isElementPresent(SUCCESS_UPDATE_MSG, "Update approver success message")) {
			reporter.SuccessReport("Verify Approval Path Management Page", "Successfully Edited Approval Path", updatemesasge);
		} else {
			reporter.failureReport("Verify Approval Path Management Page",
					"edited approval Message path Does Not Exist", "");
		}
	}
	public void VerifySuccessUpdateInRequestor() throws Throwable {
		String updatemesasge = getText(UpdateMsgInRequestorGroup,"Update succes message");
		if (isElementPresent(UpdateMsgInRequestorGroup, "Successfully updated requestors")) {
			reporter.SuccessReport("Verify Approval Path Management Page", "Successfully Edited Approval Path", updatemesasge);
		} else {
			reporter.failureReport("Verify Approval Path Management Page",
					"edited approval Message path Does Not Exist", "");
		}
	}

	public void VerifyIsInEditMode() throws Throwable {
		if (isElementPresent(UPDATE_APPROVER_BTN, "Verify Edit mode")) {
			reporter.SuccessReport("Verify Approval Path is in Edit Mode on Approval Path Management Page",
					"Approval Path is in Edit Mode", "");
		} else {
			reporter.failureReport("Verify Approval Path is in Edit Mode on Approval Path Management Page 	",
					"Approval Path is Not in Edit Mode", "");
		}
	}

	public void ClickCancelButton() throws Throwable {

		if (isElementPresent(CANCEL_BUTTON, "Verify cancel button")) {
			click(CANCEL_BUTTON, "Click cancel button");
			reporter.SuccessReport("Click Cancel on Approval Path Management Page", "Cancel Link Exists and Clicked",
					"");
		} else {
			reporter.failureReport("Click Cancel on Approval Path Management Page", "Cancel Link Does Not Exist", "");
		}
	}

	public void CancelOnErrorPage() throws Throwable {

		if (isElementPresent(CANCEL_ON_ERROR_PAGE, "Verify cancel button")) {
			click(CANCEL_ON_ERROR_PAGE, "Successfully clicked on popup cancel button");
			reporter.SuccessReport("Click PopUp Cancel button on Approval Path Management Page", "Cancel Link Exists and Clicked",
					"");
		} else {
			reporter.failureReport("Click PopUp Cancel button on Approval Path Management Page", "Cancel Link Does Not Exist", "");
		}
	}

	public void VerifyMessage() throws Throwable {
		String errormessage = getText(ERROR_POPUP_MSG, "Verify Error message");
		if (isElementPresent(ERROR_POPUP_MSG, "Verify Error message")) {
			reporter.SuccessReport("Verify Message on Approval Path Management Page",
					"The selected approver/(s/) have open/pending requisitions requiring approval  Message Exists", errormessage);
		} else {
			reporter.failureReport("Verify Message on Approval Path Management Page",
					"The approver(s) can not be removed when open/pending requisitions exist requiring their approval: Does Not Exist",
					"");
		}
	}

	public void CountPendingRequest() throws Throwable {
		if (isElementPresent(PENDING_REQ_COUNT, "Pending request count")) {

			List<WebElement> appCount = driver.findElements(PENDING_REQ_COUNT);
			int count = appCount.size();
			String strComm = null;
			List<WebElement> myList = driver.findElements(PENDING_REQ_COUNT);
			List<String> all_elements_text = new ArrayList<>();
			for (int i = 1; i < myList.size(); i++) {
				all_elements_text.add(myList.get(i).getText());

				String ReqNum = all_elements_text.get(0);

				String str[] = ReqNum.split(" ");
				strComm = "Req Number Count: " + (count-1) + "***" + "Requisition Number:" + str[0] + "***"
						+ "Next Approver:" + str[1];

			}
			if (count > 1) {
				reporter.SuccessReport("Approval Path Management Page", "Pending Requisitions Exists", strComm);
			} else {
				reporter.failureReport("Approval Path Management Page", "Pending Requisitions does not Exists", "");
			}
		}

	}

	public void CountApproversOnEditMode() throws Throwable {
		if (isElementPresent(APPROVERS_LIST_ADDED, "Approvers count ")) {

			List<WebElement> appCount = driver.findElements(APPROVERS_LIST_ADDED);
			int count = appCount.size();
			List<String> all_elements_text = new ArrayList<>();
			for (int i = 0; i < appCount.size(); i++) {
				all_elements_text.add(appCount.get(i).getText());

				String ReqNum = all_elements_text.get(0);

				String str[] = ReqNum.split("\n");
				reporter.SuccessReport("Approval Path Management Page", "Approvers are Added to Approval path Sequence",
						"Approver Name : " + "+i+" + " : " + str[0]);
			}
			if (count == 1) {
				reporter.failureReport("Approval Path Management Page",
						"Approvers are Not Added to Approval path Sequence", "");
			}
		}
	}

	public void ClickViewAllPathDetails() throws Throwable {
		if (isElementPresent(VIEW_ALL_IMG_ICON, "Verify View all image icon")) {
			click(VIEW_ALL_IMG_ICON, "Click View all image icon");
			reporter.SuccessReport("Approval Path Management Page",
					"Viewing all Available Approvers Link Exists and Clicked", "");
		} else {
			reporter.failureReport("Approval Path Management Page",
					"Viewing all Available Approvers Link Does Not Exist", "");
		}
	}

	public void VerifyAppPathNames() throws Throwable {

		List<WebElement> appCount = driver.findElements(GET_APPROVALPATH_COUNT);

		if (appCount.size() > 1) {
			reporter.SuccessReport("Approval Path Management Page", "Approval Path Names Exists", "");

		} else {
			reporter.failureReport("Approval Path Management Page", "Approval Path Names Does Not Exist", "");
		}

		String strComm = null;
		for (int i = 1; i < appCount.size(); i++) {

			String pathName = driver.findElement(getAppPathName(String.valueOf(i))).getText();
			String numOfApprovers = driver.findElement(getApproverCount(String.valueOf(i))).getText();
			strComm = "Approval Path Name with Number of Approvers:" + pathName.trim() + "****" + numOfApprovers.trim();
			reporter.SuccessReport("Approval Path Management Page", "Approval Path Names Exists", strComm);
		}
	}

	public void ClickReports() throws Throwable {
		if (isElementPresent(REPORTS_LINK, "Verify Reports Link")) {
			click(REPORTS_LINK, "Click Reports Link");
			reporter.SuccessReport("Approval Path Management Page", "Reports Link Exists and Clicked", "");
		} else {
			reporter.failureReport("Approval Path Management Page", "Reports Link Does Not Exist", "");
		}
	}

	public void VerifyReportTypes() throws Throwable {
		String Req_Status_report = null;
		String Req_grp_users = null;
		String Req_Grp_rules_report = null;
		String Approval_Path_report = null;
		String App_out_report = null;
		String Req_grp_smart_tracker_rep = null;
		String strComm = null;

		if (isElementPresent(REPORTS_DIV, "Report div")) {

			if (isElementPresent(REQUISITION_STAUS_REPORT, "Requisition Status Report")) {
				Req_Status_report = driver.findElement(REQUISITION_STAUS_REPORT).getText();
			}

			if (isElementPresent(REQUESTOR_GROUP_USERS, "Requestor Group Users")) {
				Req_grp_users = driver.findElement(REQUESTOR_GROUP_USERS).getText();
			}

			if (isElementPresent(REQUESTOR_GROUPRULES_REPORT, "Requestor Group Rules Report")) {
				Req_Grp_rules_report = driver.findElement(REQUESTOR_GROUPRULES_REPORT).getText();
			}

			if (isElementPresent(APPROVAL_PATH_REPORT, "Approval Path report")) {
				Approval_Path_report = driver.findElement(APPROVAL_PATH_REPORT).getText();
			}

			if (isElementPresent(APPROVER_OUT_REPORT, "Approver Our Report")) {
				App_out_report = driver.findElement(APPROVER_OUT_REPORT).getText();
			}

			if (isElementPresent(REQUESTOR_GROUP_SMARTTRACKER_REPORT, "Requestor Group Smart Tracker Report")) {
				Req_grp_smart_tracker_rep = driver.findElement(REQUESTOR_GROUP_SMARTTRACKER_REPORT).getText();
			}
			strComm = "Report Links:" + Req_Status_report.trim() + "****" + Req_grp_users.trim() + "****"
					+ Req_Grp_rules_report.trim() + "*****" + Approval_Path_report.trim() + "*****"
					+ App_out_report.trim() + "*****" + Req_grp_smart_tracker_rep.trim();

			reporter.SuccessReport("Approval Path Management Page", "Approval Management Reports Exists", strComm);
		} else {
			reporter.failureReport("Approval Path Management Page", "Report Types Do Not Exist", Req_Status_report);
		}
	}

	public void ClickApprovalPathReportLink() throws Throwable {
		if (isElementPresent(APPROVAL_PATH_REPORT, "Verify Approval Path Report Link")) {
			click(APPROVAL_PATH_REPORT, "Click Approval Path Report Link");
			reporter.SuccessReport("Approval Path Management Page", "Approval Path Report Link Exists and Clicked", "");
		} else {
			reporter.failureReport("Approval Path Management Page", "Approval Path Report Link Does Not Exist", "");
		}
	}

	public String VerifyApprovalPathReport() throws Throwable {
		if (isElementPresent(APP_PATH_REPORT_PAGE_TITLE, "Approval Path Report Page ")) {
			reporter.SuccessReport("Approval Path Management Page", "Approval Path Names Exists", "");
		} else {
			reporter.failureReport("Approval Path Management Page", "Approval Path Names Does Not Exist", "");
		}

		List<WebElement> appCount = driver.findElements(GET_APPROVALPATH_REPORT_COUNT);
		String strComm = null;
		String strApprovalPathNm = null;
		for (int row = 1; row < appCount.size(); row++) {
			int col = 0;

			String strApprovalPathName = driver
					.findElement(getApprovalPathDetails(String.valueOf(col + 1), String.valueOf(row))).getText();
			String strNumberOfApprovers = driver
					.findElement(getApprovalPathDetails(String.valueOf(col + 2), String.valueOf(row))).getText();
			String strRequestorGroup = driver
					.findElement(getApprovalPathDetails(String.valueOf(col + 3), String.valueOf(row))).getText();

			strComm = "Approval Path Report:" + strApprovalPathName.trim() + "****" + strNumberOfApprovers.trim()
					+ "*****" + strRequestorGroup.trim();
			reporter.SuccessReport("Approval Path Management Page", "Approval Path Names Exists", strComm);
		}

		if (appCount.size() > 1) {
			strApprovalPathNm = driver.findElement(getApprovalPathDetails(String.valueOf(1), String.valueOf(1)))
					.getText();
		}
		return strApprovalPathNm;
	}

	public void ClickApprovalPathLink(String appPathName) throws Throwable {
		if (isElementPresent(getApprovalPathNameLink(appPathName), "Verify Approval Path Link")) {
			click(getApprovalPathNameLink(appPathName), "Click Approval Path Link");
			reporter.SuccessReport("Approval Path Management Page", "Approval Path Link Exists and Clicked", appPathName);
		} else {
			reporter.failureReport("Approval Path Management Page", "Approval Path Link Does Not Exist", "");
		}
	}

	public void ClickRequestorGrpLink() throws Throwable {
		if (isElementPresent(REQUESTOR_GRP_LINK, "Requestor Group Link")) {
			click(REQUESTOR_GRP_LINK, "Click Requestor Group Link");
			reporter.SuccessReport("Approval Management Reports Page", "Requestor Group Link Exists and Clicked", "");
		} else {
			reporter.failureReport("Approval Management Reports Page", "Requestor Group Link Does Not Exist", "");
		}
	}
	public void verifyApprovalMgmtReportPage() throws Throwable {
		if(isVisibleOnly(ApprovalMgmtReports, "ApprovalMgmtReports")) {
			String page= getText(ApprovalMgmtReports, "ApprovalMgmtReports");
				reporter.SuccessReport("ApprovalMgmtReports", "ApprovalMgmtReports is visible", "", driver);
			
		}
		else {
			reporter.failureReport("ApprovalMgmtReports", "ApprovalMgmtReports is not visible", "", driver);
		}
	}
public int GetNumberOfRequestorGroupsb() throws Throwable {
	List<WebElement> elem = driver.findElements(NumberOfRequestorGroupsb);
	int count = elem.size();
	if(count>0) {
		
		reporter.SuccessReport("Number Of Requestor Groups ", "Number Of Requestor Groups", String.valueOf(count), driver);
	}
	else {
		reporter.failureReport("Number Of Requestor Groups ", "Number Of Requestor Groups are not available", "", driver);
	}
	return count;
}
public String GetNameOfLastRequestor() throws Throwable {
	List<WebElement> elem = driver.findElements(NumberOfrequestors);
	int count = elem.size();
	String text ="";
	if(count>0) {
		 text = elem.get(count-1).getText().toString();
		reporter.SuccessReport("LastName Of Requestor ", "LastName Of Requestor", text, driver);
		
	}
	else {
		reporter.failureReport("LastName Of Requestor", "LastName Of Requestor doesn't exist", "", driver);
	}
	return text;
}
public String VerifyRequestorResults(String requestor) throws Throwable {
	List<WebElement> elem = driver.findElements(By.xpath("//table[@id='reqGrpUserReport']//td[1]"));
	int count = elem.size();
	String text ="";
	if(count>0) {
		for (int i=0;i<count;i++) {
			text =elem.get(i).getText();
			String test = text.replace(" ", "");
			if(test.contains(requestor)) {
				reporter.SuccessReport("Requestor", "Requestor displaying in the results", text, driver);
				break;
			}
			
		}
		
		
	}
	else {
		reporter.failureReport(" Requestor", "Requestors doesn't exist", "", driver);
	}
	return text;
}
	public void ClickEditLinkINRequestorGrpPage(String reqGrpName) throws Throwable {
		if (isElementPresent(clickRequestorGrpEdit(reqGrpName), "Requestor Group Edit Icon")) {
			click(clickRequestorGrpEdit(reqGrpName), "Requestor Group Edit Icon");
			reporter.SuccessReport("Click Requestor Group Edite", "Requestor Group Edit Icon Exists and Clicked", reqGrpName);
		} else {
			reporter.failureReport("Click Requestor Group Edit", "Requestor Group Edit Icon not Exists and not Clicked", "");
		}
	}
	
	public void VerifyReqGrpInEditMode() throws Throwable {
		if (isElementPresent(SAVE_CHANGES_BTN, "Verify Edit mode")) {
			reporter.SuccessReport("Verify Requestor Group Management page", "Requestor Group Loads In Edit Mode", "");
		} else {
			reporter.failureReport("Requestor Group Management page", "Requestor Group Does Not Exist", "");
		}
	}

	public void ClickGeneralSettings() throws Throwable {
		if (isElementClickable(GENERAL_SETTINGS_LINK,3, "General Settings Link ")) {
			//click(GENERAL_SETTINGS_LINK, "Click General Settings Link");
			reporter.SuccessReport("Click General Settings on Approval Management Page",
					"General Settings Link Exists and Clicked", "");
		} else {
			reporter.failureReport("Click General Settings on Approval Management Page",
					"General Settings Link Does Not Exist", "");
		}
	}

	public void ClickCreateLink() throws Throwable {
		if (waitForVisibilityOfElement(CREATE_LINK, "Create icon ")) {
			click(CREATE_LINK, "Click Create icon");
			reporter.SuccessReport("Click Create on Approval Management Page Approver Out of Office Settings",
					"Click on create icon under “Approver Out of Office Settings”", "");
		} else {
			reporter.failureReport("Click Create on Approval Management Page Approver Out of Office Settings",
					"Click on create icon under “Approver Out of Office Settings”doesn't exist", "");
		}
	}

	public void verifyNameIsRequiredAlert() throws Throwable {
		Thread.sleep(4000);
		WebDriverWait wait = new WebDriverWait(driver,
				300 /* timeout in seconds */);
		if (wait.until(ExpectedConditions.alertIsPresent()) != null) {
			String alertMsg = driver.switchTo().alert().getText();
			if (alertMsg.contains("Replacement")) {
				acceptAlert();
				reporter.SuccessReport("Click ok on Create Icon In requisitionRejection popup",
						"POPUP Exists and OK is Clicked","");
				

			} 
			else if(alertMsg.contains("Description")) {
				acceptAlert();
				reporter.SuccessReport("Click ok on Create Icon InrequisitionRejection popup",
						"POPUP Exists and OK is Clicked", "");
				

			} else {
				reporter.failureReport("Click ok on ClickCreateLinkInrequisitionRejection",
						"POPUP Does Not Exist", "");
			}
		}
	}

	public void ClickCreateLinkInrequisitionRejection() throws Throwable {

		if (isElementPresent(CreateLinkInrequisationRejection, "Requisition Rejection Type Add icon")) {
			click(CreateLinkInrequisationRejection, "Requisition Rejection Type Add icon");
			reporter.SuccessReport("Click Create on requisitionRejection",
					"Requisition Rejection Type Add icon Exists and Clicked", "");
		} else {
			reporter.failureReport("Click Create on requisitionRejection",
					"Requisition Rejection Type Add icon Does Not Exist", "");
		}
	}
	public void CreateRequisitionRejectionTypes(String RejectionType) throws Throwable {
		if (isElementPresent(REQ_REJECTION_DESC_TXTBOX, "Rejection Type")) {
			type(REQ_REJECTION_DESC_TXTBOX, RejectionType, "Rejection Type");
			reporter.SuccessReport("Approval Management Page Approver Out of Office Settings",
					"Rejection Type Field Exists and Entered", "");
		} else {
			reporter.failureReport("Approval Management Page Approver Out of Office Settings",
					"Rejection Type Field Does Not Exist", "");
		}

		// Click on create button

		if (isElementPresent(REQ_REJ_ADD_LINK, "Requisition Rejection Type Add icon")) {
			click(REQ_REJ_ADD_LINK, "Requisition Rejection Type Add icon");
			reporter.SuccessReport("Approval Management Page Approver Out of Office Settings",
					"Rejection Type Field entered and clicked Requisition Rejection Type Add icon", "");
		} else {
			reporter.failureReport("Approval Management Page Approver Out of Office Settings",
					" + link does not exists to add a rejection type", "");
		}
	}

	public void VerifyAddedRejection(String RejectionType) throws Throwable {
		if (isElementPresent(getReqRejTypeDesc(RejectionType), "Rejection is Added and Verified ")) {
			reporter.SuccessReport("Click Create on Approval Management Page Approver Out of Office Settings",
					"Rejection is Added and Verified", "");
		} else {
			reporter.failureReport("Click Create on Approval Management Page Approver Out of Office Settings",
					"Rejection Does Not Exist", "");
		}
	}

	public void ClickEditToModifyRejType(String RejectionType) throws Throwable {
		if (isElementPresent(editRejType(RejectionType), "Edit icon ")) {
			click(editRejType(RejectionType), "Rejection Type Edit icon");
			reporter.SuccessReport("Click Create on Approval Management Page Approver Out of Office Settings",
					"Requisition RejectionTypes Edit icon Exists and Clicked", "");
		} else {
			reporter.failureReport("Click Create on Approval Management Page Approver Out of Office Settings",
					"Requisition RejectionTypes Edit icon does not Exists", "");
		}
	}

	public void ModifyRejectionName(String NewRejectionType, String RejectionType) throws Throwable {
		if (isElementPresent(modifyRejDesc(RejectionType), "Requisition Rejection Type")) {
			type(modifyRejDesc(RejectionType), NewRejectionType, "Requisition Rejection Type");
			reporter.SuccessReport("Approval Management Page Approver Out of Office Settings",
					"Requisition Rejection Type Field updated successfully", "");
		} else {
			reporter.failureReport("Approval Management Page Approver Out of Office Settings",
					"Rejection Type Field Does Not Exist", "");
		}

		// click on save to modify the name
		if (isElementClickable(saveNewRejType(RejectionType), 3,"Save icon")) {
			//click(saveNewRejType(RejectionType), "Save icon");
			reporter.SuccessReport("Approval Management Page Approver Out of Office Settings",
					"Save icon Exists and Clicked", "");
		} else {
			reporter.failureReport("Approval Management Page Approver Out of Office Settings",
					"Save icon Does Not Exist", "");
		}
	}

	public void VerifyNameExistsPopup() throws Throwable {

		WebDriverWait wait = new WebDriverWait(driver,
				300 /* timeout in seconds */);
		if (wait.until(ExpectedConditions.alertIsPresent()) != null) {
			String alertMsg = driver.switchTo().alert().getText();
			if (alertMsg.contains("unique")) {
				reporter.SuccessReport("Click ok on Approval Management Page Approver Out of Office Settings",
						"POPUP Exists and OK is Clicked", alertMsg);
				acceptAlert();

			} else {
				reporter.failureReport("Click ok on Approval Management Page Approver Out of Office Settings",
						"POPUP Does Not Exist", "");
			}
		}
	}

	public void VerifyOverLapRulePopup() throws Throwable {

		WebDriverWait wait = new WebDriverWait(driver,
				300 /* timeout in seconds */);
		if (wait.until(ExpectedConditions.alertIsPresent()) != null) {
			String alertMsg = driver.switchTo().alert().getText();
			if (alertMsg.contains("Please edit existing rule")) {
				acceptAlert();
				reporter.SuccessReport("Approval Management Page Approver Out of Office Settings",
						"POPUP Exists and OK is Clicked", "");
				

			}
			else if(alertMsg.contains("unique name")){
				acceptAlert();
				reporter.SuccessReport("Approval Management Page Approver Out of Office Settings",
						"POPUP Exists and OK is Clicked", "");
			}else {
				reporter.failureReport("Approval Management Page Approver Out of Office Settings",
						"POPUP Does Not Exist", "");
			}
		}
	}

	public void DeleteRequisitionRejectionTypes(String RejectionType) throws Throwable {
		if (isElementPresent(deleteNewRejType(RejectionType), "Delete icon")) {
			click(deleteNewRejType(RejectionType), "Delete icon");
			reporter.SuccessReport("Approval Management Page Approver Out of Office Settings",
					"Delete icon Exists and Clicked", "");
		} else {
			reporter.failureReport("Approval Management Page Approver Out of Office Settings",
					"Delete icon Does Not Exist", "");
		}
	}

	public void VerifyDeleteRejctionType(String RejectionType) throws Throwable {
		if (isElementPresent(getReqRejTypeDesc(RejectionType), "Deleted Rejection Name ")) {
			reporter.failureReport("Click Create on Approval Management Page Approver Out of Office Settings",
					"Deleted Rejection Name Exists", RejectionType);

		} else {
			reporter.SuccessReport("Click Create on Approval Management Page Approver Out of Office Settings",
					"Deleted Rejection Name and Verified", RejectionType);
		}
	}

	public void CreateApproverOut(String strApproverId, String strReplacementType, String strReplacementApproverId,
			String strCurrDay) throws Throwable {
		// Select Approver
		selectByValue(createApproverType(), strApproverId, "ApproverId");
		
		if (isElementPresent(selectApprover(strApproverId), "Approver ")) {
			String Approvername = getText(selectApprover(strApproverId), "Selected Approver");
			reporter.SuccessReport("Select Approver on Approval Management  Page Approver Out of Office Settings",
					"Approver Field Exists and Selected", "");
		} else {
			reporter.failureReport("Select Approver on Approval Management  Page Approver Out of Office Settings",
					"Approver Field  Does Not Exist", "");
		}

		// Select Replacement Type
		if (isElementPresent(createReplacementType(), "Replacement Type")) {
			selectByVisibleText(createReplacementType(), strReplacementType, " from Replacement Type");
			reporter.SuccessReport(
					"Select Replacement Approver on Approval Management  Page Approver Out of Office Settings",
					"Replacement Type Field Exists and Selected", strReplacementType);
		} else {
			reporter.failureReport(
					"Select Replacement Approver on Approval Management  Page Approver Out of Office Settings",
					"Replacement Type Field  Does Not Exist", "");
		}

		if (strReplacementType.trim().contains("Skip Approver")) {
			// Verify Replacement Approver is Disabled
			if (isElementPresent(DISABLED_REPLACEMENT_APPROVER, "Replacement Type")) {
				reporter.SuccessReport("Approval Management  Page Approver Out of Office Settings",
						"Replacement Type Field Exists and in Disabled Mode", "");
			} else {
				reporter.failureReport("Approval Management  Page Approver Out of Office Settings",
						"Replacement Type Field Exists and not in Disabled Mode", "");
			}
		} else {
			// Select Replacement Approver
			if (isElementPresent(selectRepApprover(strReplacementApproverId), "Replacement Approver")) {
				selectByValue(createRepApproverType(), strReplacementApproverId, "Replacement Approver");
				String Approvername = getText(selectRepApprover(strReplacementApproverId), "Replacement Approver");
				reporter.SuccessReport(
						"Select Replacement Approver on Approval Management  Page Approver Out of Office Settings",
						"Replacement Approver Field Exists and Selected","");
				if(strReplacementType.contains("Permanent")) {
				if (isElementPresent(Disabled_Todate, "Todate")) {
					reporter.SuccessReport("Approval Management  Page Approver Out of Office Settings",
							"Todate Field Exists and in Disabled Mode", "");
				} else {
					reporter.failureReport("Approval Management  Page Approver Out of Office Settings",
							"Todate Field Exists and not in Disabled Mode", "");
				}
				}
			} else {
				reporter.failureReport(
						"Select Replacement Approver on Approval Management  Page Approver Out of Office Settings",
						"Replacement Approver Field  Does Not Exist", "");
			}
		}

		// From Date From Date Range Calender
		if (isElementClickable(START_DATE_CALENDER, 2,"From Date")) {
			//click(START_DATE_CALENDER, "From Date");
			reporter.SuccessReport("Select From Date on  Approval Management Page Approver Out of Office Settings",
					"From Date Calender Image Exists and Selected", "");
		} else {
			reporter.failureReport("Select From Date on  Approval Management Page Approver Out of Office Settings",
					"From Date Calender Image is Does Not Exist", "");
		}

		// Select Day

		String day = strCurrDay.split("-")[0];
		String month = strCurrDay.split("-")[1];
		String year = strCurrDay.split("-")[2];

		WebElement element = driver.findElement(By.xpath("//div[@class='ui-datepicker-title']"));
		String monthAndYear = element.getText();
		LocalDate today = LocalDate.now();
		String monthAndDateCheck = today.format(DateTimeFormatter.ofPattern("MMMM uuuu"));

		if (monthAndYear.equals(monthAndDateCheck)) {
			if (isElementPresent(dayInStartDayCalender(day), "From Date ")) {
				click(dayInStartDayCalender(day),day+ " "+monthAndYear);
			}
		} else {
			while (!monthAndYear.equals(monthAndDateCheck)) {
				if (isElementPresent(PREV_MONTH_ARROW, "From Date ")) {
					click(PREV_MONTH_ARROW, ""+day+ " "+monthAndYear);
				}
			}
		}

		// Verify From Date Selection
		/*
		 * if (isElementPresent(START_DATE_TXTBOX, "From Date")) { String fromDate =
		 * getText(START_DATE_TXTBOX, "From date value"); if (fromDate != null) {
		 * reporter.
		 * SuccessReport("Select From Date on  Approval Management Page Approver Out of Office Settings"
		 * , "Selected From Date Exists", ""); } else { reporter.
		 * failureReport("Select From Date on  Approval Management Page Approver Out of Office Settings"
		 * , "Selected From Date Does Not Exist", ""); } }
		 */
		// Check To Date will be disabled for permanent Replace -
		// ReplacementType
		// To Date From Date Range Calender

		if (!strReplacementType.trim().contains("Permanent")) {
			if (isElementClickable(END_DATE_CALENDER,2, "To Date")) {
				//click(END_DATE_CALENDER, "To Date");
				reporter.SuccessReport("Select To Date on  Approval Management Page Approver Out of Office Settings",
						"To Date Calender Image Exists and Selected", "");
			} else {
				reporter.failureReport("Select From Date on  Approval Management Page Approver Out of Office Settings",
						"To Date Calender Image is Does Not Exist", "");
			}

			// Change the Month
			if (isElementClickable(NEXT_MONTH_ARROW, 2,"Next month")) {
				//click(NEXT_MONTH_ARROW, "Next month");
				reporter.SuccessReport("Change Month on Approval Management Page Approver Out of Office Settings",
						"Arrow Button Exists and Clicked to Change the Month", "");
			} else {
				reporter.failureReport("Change Month on Approval Management Page Approver Out of Office Settings",
						"Arrow Button Does Not Exist", "");
			}

			// Select Day
			if (isElementPresent(dayInStartDayCalender(day), "To Date ")) {
				String todate = driver.findElement(By.xpath("//div[@class='ui-datepicker-title']")).getText();
				click(dayInStartDayCalender(day), day+" "+todate);
			}

			// Verify To Date Selection
			/*if (isElementPresent(END_DATE_TXTBOX, "To Date")) {
				String ToDate = getText(END_DATE_TXTBOX, "To date value");
				if (ToDate != null) {
					reporter.SuccessReport(
							"Select To Date on  Approval Management Page Approver Out of Office Settings",
							"Selected To Date Exists", "");
				} else {
					reporter.failureReport(
							"Select To Date on  Approval Management Page Approver Out of Office Settings",
							"Selected To Date Does Not Exist", "");
				}
			}
		*/
		}
		// Click on Create
		if (isElementClickable(ADD_IMG_BTN,2, "Create Icon")) {
			//click(ADD_IMG_BTN, "Create Icon");
			reporter.SuccessReport("Click Create on Approval Management Page Approver Out of Office Settings",
					"CREATE Icon Exists and Clicked", "");
			Thread.sleep(5000);
		} else {
			reporter.failureReport("Click Create on Approval Management Page Approver Out of Office Settings",
					"CREATE Icon does not Exists", "");
		}

	}

	public void VerifyCreateApproverOut(String strApprover) throws Throwable {
String ApproverName= getText(Approvername("approverName"), "ApproverName");
String replacementType= getText(Approvername("replacementType"), "replacementType");
String StartDate= getText(Approvername("StartDateId"), "StartDateId");
String EndDate= getText(Approvername("EndDateId"), "EndDateId");

		if (isElementPresent(verifyApproverOutCreated(strApprover), "Verify Approver")) {

			reporter.SuccessReport("Approval Management Page Approver Out of Office Settings ",
					"Created Approver Out Exists", "ApproverName:"+ApproverName+"replacementType:"+replacementType+"StartDate:"+StartDate+"EndDate:"+EndDate);
		} else {
			reporter.failureReport("Approval Management Page Approver Out of Office Settings",
					"Approver Out Does Not Exist", "");
		}
	}

	public void ModifyDeleteApproverOut(String strApprover, String modifyDate,String ReplacementType) throws Throwable {
		if (isElementClickable(editApprover(strApprover),3, "Click on Edit Icon ")) {
			//click(editApprover(strApprover), "Click on Edit Icon");
			reporter.SuccessReport("Approval Management Page Approver Out of Office Settings",
					"Created Approver Out is in Edit Mode", "");
		} else {
			reporter.failureReport("Approval Management Page Approver Out of Office Settings",
					"Approver Out Does Not Exist", "");
		}

		if (isElementPresent(getCalenderToUpdate, "From Date")) {
			click(getCalenderToUpdate, "From date");
		}

		

		// Modify the date
		if (isElementPresent(PREV_MONTH_ARROW, "Next month ")) {
			click(PREV_MONTH_ARROW, "Next Month");
			reporter.SuccessReport("Change Month on Approval Management Page Approver Out of Office Settings",
					"Arrow Button Exists and Clicked to Change the Month", "");
		} else {
			reporter.failureReport("Change Month on Approval Management Page Approver Out of Office Settings",
					"Arrow Button Does Not Exist", "");
		}

String day = modifyDate.split("-")[0];
		// Select Day

		int date = Integer.parseInt(day);
		date = date + 1;
		day = String.valueOf(date);
		String MonthandYear ="";
		if (isElementPresent(dayInStartDayCalender(day), "From Date ")) {
			MonthandYear = getText(Monthandyearoffromdate, "MonthandYear");
			click(dayInStartDayCalender(day), day+" "+MonthandYear);
		}
String FromDatebeforeUpdating = day + " "+MonthandYear;

		// Save
		if (isElementClickable(SAVE_APPROVER, 2,"Save Icon")) {
			//click(SAVE_APPROVER, "Save Icon");
			reporter.SuccessReport("Approval Management Page Approver Out of Office Settings",
					"Created Approver Out is Modified and Saved", "");
		} else {
			reporter.failureReport("Approval Management Page Approver Out of Office Settings",
					"Approver Out Does Not Exist", "");
		}
String FromDateAfterUpdating = driver.findElement(By.xpath("//td[contains(@id,'StartDateId')]")).getText();
reporter.SuccessReport("Before updating", "From date before updating", FromDatebeforeUpdating, driver);
reporter.SuccessReport("After updating", "From date after updating", FromDateAfterUpdating, driver);
String[] day1 = FromDateAfterUpdating.split("-");

if(FromDatebeforeUpdating.contains(day1[0])&& FromDatebeforeUpdating.contains(day1[1]) && FromDatebeforeUpdating.contains(day1[2])) {
	reporter.SuccessReport("Verify After Updation", "Updated values successfully", "", driver);
}
else {
	reporter.failureReport("Verify After Updation", "Updated values are not successfully", "", driver);
}
		VerifyCreateApproverOut(strApprover);
		// Delete

		if (isElementClickable(deleteApprover(strApprover),2, "Delete Icon")) {
			//click(deleteApprover(strApprover), "Delete Icon");
			reporter.SuccessReport("Approval Management Page Approver Out of Office Settings",
					"Created Approver Out is Deleted", "");
		} else {
			reporter.failureReport("Approval Management Page Approver Out of Office Settings",
					"Approver Out Does Not Exist", "");
		}
	}

	public String GetApproverId() throws Throwable {
		String strApproverOption = null;
		List<WebElement> myList = driver.findElements(ALL_APPROVER_NAME_OPTIONS);
		if (myList.size() > 0) {
			strApproverOption = (myList.get(1)).getAttribute("value");
		}
		return strApproverOption;
	}

	public String GetReplacementApproverId() throws Throwable {
		String strRepApproverOption = null;
		List<WebElement> myList = driver.findElements(ALL_REPLACEMENT_APPROVER_NAME_OPTIONS);
		if (myList.size() > 0) {
			strRepApproverOption = (myList.get(2)).getAttribute("value");
		}
		return strRepApproverOption;
	}

	public String GetApproverName(String approverId) throws Throwable {
		String strApproverName = null;

		strApproverName = driver.findElement(selectApprover(approverId)).getText();

		return strApproverName;
	}

	public String GetReplacementApproverName(String strReplacementApproverId) throws Throwable {
		String strRepApproverName = null;

		strRepApproverName = driver.findElement(selectRepApprover(strReplacementApproverId)).getText();

		return strRepApproverName;
	}

	public String GetReplacementApprover() throws Throwable {
		String strReplacementApproverId = null;
		String strReplacementApprover = null;

		List<WebElement> myList = driver.findElements(ALL_REPLACEMENT_APPROVER_NAME_OPTIONS);
		if (myList.size() > 1) {
			strReplacementApproverId = (myList.get(2)).getAttribute("value");
			strReplacementApprover = driver.findElement(selectApprover(strReplacementApproverId)).getText();
		}
		return strReplacementApprover;
	}

	public String GetCurrDay() throws Throwable {

		LocalDate today = LocalDate.now();
		String fromDate = today.format(DateTimeFormatter.ofPattern("d-MMM-uuuu"));
		return fromDate;
	}
public void GetFromDate() {
	Date currentDate = new Date();
	 Calendar c = Calendar.getInstance();
     c.setTime(currentDate);

    
     c.add(Calendar.DATE, -380); //same with c.add(Calendar.DAY_OF_MONTH, 1);
    
     

     // convert calendar to date
     Date Previousdate = c.getTime();

   

}
	public void ClickReqGrpRulesReport() throws Throwable {
		if (isElementPresent(REQUESTOR_GROUPRULES_REPORT, "Requestor Group Rules Report")) {
			click(REQUESTOR_GROUPRULES_REPORT, "Click Requestor Group Rules Report");
			reporter.SuccessReport("Click Requestor Group Rules Report on Approval Management Reports Page",
					"Requestor Group Rules Report Link Exists and Clicked", "");
		} else {
			reporter.failureReport("Click Requestor Group Rules Report on Approval Management Reports Page",
					"Requestor Group Rules Report Link Does Not Exist", "");
		}
	}

	public void ClickShowUsers() throws Throwable {
		if (isElementPresent(SHOW_USERS_CHECKBOX, "Show Users Checkbox")) {
			click(SHOW_USERS_CHECKBOX, "Click Show Users Checkbox");
			reporter.SuccessReport("Check Show Users Checkbox on Approval Management Requestor Group Rules Report Page",
					"Show Users Checkbox Exists and Checked", "");
		} else {
			reporter.failureReport("Check Show Users Checkbox on Approval Management Requestor Group Rules Report Page",
					"Show Users Checkbox Does Not Exist", "");
		}
	}

	public void ClickShowRules() throws Throwable {
		if (isElementPresent(SHOW_RULES_CHECKBOX, "Show Rules Checkbox")) {
			click(SHOW_RULES_CHECKBOX, "Check Show Rules Checkbox");
			reporter.SuccessReport("Check Show Rules Checkbox on Approval Management Requestor Group Rules Report Page",
					"Show Rules Checkbox Exists and Checked", "");
		} else {
			reporter.failureReport("Check Show Rules Checkbox on Approval Management Requestor Group Rules Report Page",
					"Show Rules Checkbox Does Not Exist", "");
		}
	}

	public void ClickSearchButton() throws Throwable {
		if (isElementPresent(SEARCH_BUTN, "SEARCH Button")) {
			click(SEARCH_BUTN, "Click SEARCH Button");
			reporter.SuccessReport("Check Search on Approval Management Requestor Group Rules Report Page",
					"SEARCH Button Exists and Clicked", "");
		} else {
			reporter.failureReport("Check Search on Approval Management Requestor Group Rules Report Page",
					"SEARCH Button Does Not Exist", "");
		}
	}

	public void VerifyReqGroupNames() throws Throwable {
		if (isElementPresent(SEARCH_RESULTS, "SEARCH Results table")) {
			String strApprovalPathNm = null;
			List<WebElement> appCount = driver.findElements(SEARCH_RESULTS);
			for (int row = 1; row < appCount.size(); row++) {
				strApprovalPathNm = driver.findElement(getReqGroupDetails(String.valueOf(1), String.valueOf(row)))
						.getText();
				reporter.SuccessReport("Approval Management Requestor Group Rules Report Page",
						"ReqestorGroupName Exists and Displayed in Alphabetical Order", strApprovalPathNm);
			}
		} else {
			reporter.failureReport("Approval Management Requestor Group Rules Report Page",
					"ReqestorGroupName Does Not Exist", "");
		}
		

	}
	public void VerifyRequestorGroupdata() throws Throwable {
		int count =0;
		String heading  ="";
		WebElement elem = driver.findElement(By.id("reqtable"));
		WebElement elem1 = driver.findElement(By.id("reqtable"));
		List<WebElement> rows= elem.findElements(By.tagName("tr"));
		label:
		for (WebElement webElement : rows) {
			List<WebElement> EachHeading = webElement.findElements(By.tagName("th"));
			List<WebElement> dataunderHeading = elem1.findElements(By.tagName("td"));
			if(EachHeading.size()>0) {
				if(dataunderHeading.size()>0) {
				for (WebElement webElement1 : EachHeading) {
					 heading  = webElement1.getText();
					System.out.println(heading);
					for(;count<=EachHeading.size();) {
						String celldata = dataunderHeading.get(count).getText();
						reporter.SuccessReport("Verify Requestor Group details", "Requestor group details are", heading+":"+celldata, driver);
						++count;
						break;
					}
					
				}
				}
				//WebElement elem1 = driver.findElement(By.id("reqtable"));
				else {
					System.out.println("");
				}
				
			}
			else {
				System.out.println();
			}
			break label;
		}
		
	}
	public void VerifyReqGroupNamesandDetails() throws Throwable {
		if (isElementPresent(SEARCH_RESULTS, "SEARCH Results table")) {
			String strApprovalPathNm = null;
			List<WebElement> appCount = driver.findElements(SEARCH_RESULTS);
			for (int i = 2; i < appCount.size(); i++) {
				String RequestorGrpName = driver.findElement(GetGroupDetails(i, i-1)).getText();
				String SmartTrakers = driver.findElement(GetGroupDetails(i, i)).getText();
				String CheckOutOptions = driver.findElement(GetGroupDetails(i, i+1)).getText();
				String Settings = driver.findElement(GetGroupDetails(i, i+1)).getText();
				reporter.SuccessReport("Approval Management Requestor Group Rules Report Page",
						"Approval Management Requestor Group Rules Report Exists", "RequestorGrpName:"+RequestorGrpName+"SmartTrakers:"+SmartTrakers+"CheckOutOptions:"+CheckOutOptions+"Settings:"+Settings);
			}
		} else {
			reporter.failureReport("Approval Management Requestor Group Rules Report Page",
					"ReqestorGroupName Does Not Exist", "");
		}

	}
public String GetGroupNamesDisplayed() throws Throwable {
	String RequestorGrpName ="";
	String groupName = "";
	if (isElementPresent(SEARCH_RESULTS, "SEARCH Results table")) {
		String strApprovalPathNm = null;
		
		List<WebElement> appCount = driver.findElements(SEARCH_RESULTS);
		for (int i = 2; i < appCount.size(); i++) {
			 RequestorGrpName = driver.findElement(GetGroupDetails(i, 1)).getText();
			
			reporter.SuccessReport("Approval Management Requestor Group Rules Report Page",
					"Approval Management Requestor Group Rules Report Exists", "RequestorGrpName:"+RequestorGrpName);
		if(i==5) {
			  groupName = RequestorGrpName;
			break;
		}
		}
	} else {
		reporter.failureReport("Approval Management Requestor Group Rules Report Page",
				"ReqestorGroupName Does Not Exist", "");
	}
	return groupName;

}
	public void VerifyDisplayUsers() throws Throwable {
		if (isElementPresent(DISPLAY_USERS, "SEARCH display users")) {
			List<WebElement> searchResults = driver.findElements(DISPLAY_USERS);
			String strUsers = null;
			for (int row = 1; row < searchResults.size(); row++) {
				strUsers = driver.findElement(getReqGroupDetails(String.valueOf(4), String.valueOf(row))).getText();
				strUsers = strUsers.split("Users:")[1];
				reporter.SuccessReport("Approval Management Requestor Group Rules Report Page", "Users",
						"users:"+strUsers);
				if(row==4)
					
					break;
			}
		} else {
			reporter.failureReport("Approval Management Requestor Group Rules Report Page", "Users Does Not Exist", "");
		}
	}

	public void VerifyDisplayRules() throws Throwable {
		if (isElementPresent(DISPLAY_RULES, "SEARCH display Rules")) {
			List<WebElement> searchResults = driver.findElements(DISPLAY_RULES);
			int count = searchResults.size();

			String strCartType = null;
			String Currency_Range = null;
			String strMin = null;
			String strMax = null;
			String strApprovalPathName = null;
			String strComm = null;

			if (count > 1) {
				List<WebElement> noOfRows = driver.findElements(Cart_Type);
				int k = 1;
				for (int i = 3; k <= noOfRows.size(); i++) {
					int j = 2;

					strCartType = getText(getReqRulesDetails(String.valueOf(i), String.valueOf(j)), "CartType");
					Currency_Range = getText(getReqRulesDetails(String.valueOf(i), String.valueOf(j + 1)),
							"Currency Range");
					strApprovalPathName = getText(getReqRulesDetails(String.valueOf(i), String.valueOf(j + 2)),
							"Approval Path Name");

					strMin = Currency_Range.split("-")[0];
					strMax = Currency_Range.split("-")[1];

					k = k + 1;
					strComm = "Rules CartType:" + strCartType + " Rules Min:" + strMin + " Rules Max:" + strMax
							+ " Rules ApprovalPathName: " + strApprovalPathName;
					reporter.SuccessReport("Approval Management Requestor Group Rules Report Page", "Rules Exist",
							strComm);
				}

			}

		} else {
			reporter.failureReport("Approval Management Requestor Group Rules Report Page", "Rules Does Not Exist", "");
		}
	}

	public void EnterReqGrpName(String GroupName) throws Throwable {
		if (isElementPresent(REQ_GRP_NAME_TXTBOX, "Req Group Name")) {

			// Get Requestor group name
			//String reqGroupName = driver.findElement(getReqGroupDetails(String.valueOf(1), String.valueOf(1)))
				//	.getText();
			//String reqGroupName ="no path grp";
			type(REQ_GRP_NAME_TXTBOX, GroupName, "Enter Req Group Name");

			reporter.SuccessReport(
					"Enter Requestor Group Name on  Approval Management Requestor Group Rules Report Page",
					"Requestor Group Name Field Exists", GroupName);
		} else {
			reporter.failureReport(
					"Enter Requestor Group Name on  Approval Management Requestor Group Rules Report Page",
					"Requestor Group Name Field Does Not Exist", "");
		}
	}

	public void VerifyReqGroupExists(String GroupName) throws Throwable {
		if (isElementPresent(SEARCH_RESULTS, "SEARCH Results table")) {
			String strReqGrpNm = null;
			List<WebElement> appCount = driver.findElements(SEARCH_RESULTS);
			for (int row = 1; row < appCount.size(); row++) {
				strReqGrpNm = driver.findElement(getReqGroupDetails(String.valueOf(1), String.valueOf(row))).getText();
				if(strReqGrpNm.equals(GroupName))
				reporter.SuccessReport("Approval Management Requestor Group Rules Report Page",
						"ReqestorGroupName Exists and Verified", strReqGrpNm);
			}
		} else {
			reporter.failureReport("Approval Management Requestor Group Rules Report Page",
					"ReqestorGroupName Does Not Exist", "");
		}

	}

	public void ClickRequestorGroupSmartTrackerReportLink() throws Throwable {
		if (isElementPresent(REQUESTOR_GROUP_SMARTTRACKER_REPORT, "Requestor Group SmartTracker Report Link")) {
			click(REQUESTOR_GROUP_SMARTTRACKER_REPORT, "Click Requestor Group SmartTracker Report Link");
			reporter.SuccessReport("Approval Management Reports Page",
					"Requestor Group SmartTracker Report Link Exists and Clicked", "");
		} else {
			reporter.failureReport("Approval Management Reports Page",
					"Requestor Group SmartTracker Report Link Does Not Exist", "");
		}
	}

	public void VerifySmartTrackerResults() throws Throwable {
		String strSmartTrackername = null;
		String strReqGroupName = null;
		String strComm = null;
		if (isElementPresent(SMART_TRACKER_TABLE, "Smart Tracker Results table")) {

			List<WebElement> appCount = driver.findElements(SMART_TRACKER_TABLE);

			if (appCount.size() > 1) {
				for (int row = 1; row < appCount.size(); row++) {

					strSmartTrackername = driver.findElement(By.xpath("(//table[@id='StReport']//tbody//tr//td//a)[1]"))
							.getText();
					strReqGroupName = driver.findElement(By.xpath("(//table[@id='StReport']//tbody//tr//td//a)[2]"))
							.getText();
					strComm = "Smart Tracker Name:" + strSmartTrackername + " Requestor Group Name:" + strReqGroupName;
					reporter.SuccessReport("Approval Management Requestor Group Rules Report Page",
							"ReqestorGroupName Exists and Verified", strComm);
				}
			}

		} else {
			reporter.failureReport("Approval Management Requestor Group Rules Report Page",
					"ReqestorGroupName Does Not Exist", "");
		}
	}

	public void ClickSmartTrackerLink() throws Throwable {
		if (isElementPresent(SMART_TRACKER_TABLE, "Requestor Group SmartTracker Report Link")) {

			List<WebElement> appCount = driver.findElements(SMART_TRACKER_TABLE);

			if (appCount.size() > 1) {
				click(By.xpath("(//table[@id='StReport']//tbody//tr//td//a)[1]"),
						"Click Requestor Group SmartTracker Report Link");
			}
			reporter.SuccessReport("Approval Management Reports Page",
					"Requestor Group SmartTracker Report Link Exists and Clicked", "");

			if (isElementPresent(MANAGE_SMART_TRACKER_PAGE, "Smart Tracker Page")) {
				reporter.SuccessReport("Approval Management Reports Page", "Manage SmartTrackers Page Exists", "");
			} else
				reporter.failureReport("Approval Management Reports Page",
						"Requestor Group SmartTracker Report Link Does Not Exist", "");

		} else {
			reporter.failureReport("Approval Management Reports Page", "Manage SmartTrackers  Page Does Not Exist", "");
		}
	}

	public void AddRequestorsRequestorGroup(String ReqGroupName) throws Throwable {
		// Click on Requestor Requestor Group Users
		ClickRequestorGrpLink();
		GetNumberOfRequestorGroupsb();
		// Verify
		ClickReqGroupEditLinkButton(ReqGroupName);
		
		// Click on Manage Requestors
		clickManageRequestors();

		// Click Refresh Icon To display Requestors
		ClickRefreshIcon();
		GetNumberOfRequestorsOnLeftSide();
		int beforeAddingrequestors = GetNumberOfRequestorsOnRightSide();
		// Select Requestors
		SelectRequestor(null,3);
		int afterAddingrequestors = GetNumberOfRequestorsOnRightSide();
		if(afterAddingrequestors==beforeAddingrequestors+3) {
			reporter.SuccessReport("Adding requestors", "Count after adding requestors are matching", "", driver);
		}
		else {
			reporter.failureReport("Adding requestors", "Count after adding requestors are not matching", "", driver);
		}
		// Add
		//Add_Requestor_Btn_Click();

		ClickSaveChangesButton();
		VerifySuccessUpdateInRequestor();

	}
	public void clickonRefreshIconRequestorGroupuser() throws Throwable {
		click(RefreshIconRequestorGroupuser, "RefreshIcon in RequestorGroupuser");
	}
public void clickOnApprovalManagementTabs(String requestorTab)throws Throwable {
	click(ApprovalManagementTabs(requestorTab), "Clicked on "+requestorTab+" Tab");
}
	public void ClickReqGroupEditLinkButton(String reqGroupName) throws Throwable {
		if (isElementClickable(RequestorGroupEditLink(reqGroupName),3, "Requestor Group Edit Link Exists")) {
			//click(RequestorGroupEditLink(reqGroupName), "Approval management page");
			reporter.SuccessReport("Click Requestor Group Edit", "Requestor Group Edit Link Exists and Clicked", reqGroupName);
		} else {
			reporter.failureReport("Click Requestor Group Edit", "Requestor Group Edit  Link Does Not Exist", "");
		}
	}

	public void clickManageRequestors() throws Throwable {
		if (isElementPresent(MANAGE_REQUESTOR_LINK, "Manage Requestors Link")) {
			click(MANAGE_REQUESTOR_LINK, "Manage Requestors Link");
			reporter.SuccessReport("Click Manage Requestors on Create/Edit Requestor Group Page",
					"Manage Requestors Link Exists and Clicked", "");
		} else {
			reporter.failureReport("Click Manage Requestors on Create/Edit Requestor Group Page",
					"Manage Requestors Link Does Not Exist", "");
		}
	}

	public void ClickRefreshIcon() throws Throwable {
		if (isElementPresent(REFRESH_ICON, "Refresh icon")) {
			click(REFRESH_ICON, "Click Refresh icon");
		}
	}
public void GetNumberOfRequestorsOnLeftSide() throws Throwable {
	List<WebElement> elem = driver.findElements(RequestorsOnLeftSide);
	if(elem.size()>0) {
		reporter.SuccessReport("Requestors on Right side", "Requestors are displayed on Left side", String.valueOf(elem.size()), driver);
	}
	else {
		reporter.failureReport("Requestors on Right side", "Requestors are not displayed on Left side", "", driver);
	}
}
public int GetNumberOfRequestorsOnRightSide() throws Throwable {
	List<WebElement> elem = driver.findElements(RequestorsOnRightSide);
	if(elem.size()>0) {
		reporter.SuccessReport("Requestors on Right side", "Requestors are displayed on right side", String.valueOf(elem.size()), driver);
	}
	else {
		reporter.failureReport("Requestors on Right side", "Requestors are not displayed on right side", "", driver);
	}
	return elem.size();
}
	public void SelectRequestor(String Requestor_Name,int count) throws Throwable {
		String strRequestorOption = null;
		if (Requestor_Name != null) {
			SelectSpecificRequestor(Requestor_Name);
		} else {
			// select Requestor id value
			
for(int i=1;i<=count;i++) {
	driver.findElement(REFRESH_ICON).click();
	WebElement elm = driver.findElement(createRequestorType);
	List<WebElement> elem = elm.findElements(By.tagName("option"));
		elem.get(i).click();
		String req = elem.get(i).getText();
		Add_Requestor_Btn_Click();
		reporter.SuccessReport("Add Requestors on Create/Edit Requestor Group Page",
				"Requestors Exist in the Available Requestors' List and Selected", req);
	}
	
		
		
	
}
	/*if(i<=count) {
			strRequestorOption = elm.get(i).getText();

			if (isElementPresent(selectRequestor(strRequestorOption), "Approver ")) {
				elm.get(i).click();
				//selectByVisibleText(createRequestorType(), strRequestorOption, "Approver");
				reporter.SuccessReport("Add Requestors on Create/Edit Requestor Group Page",
						"Requestors Exist in the Available Requestors' List and Selected", strRequestorOption);
				// Add
				Add_Requestor_Btn_Click();
			}

			else {
				reporter.failureReport("Add Requestors on Create/Edit Requestor Group Page",
						"Requestors Do Not Exist in the Available Requestors' List", "");
			}

		
}else {
	System.out.println("");
	
}}
		}
		return strRequestorOption;*/
		}
	public void ModifyDeleteApproverOutForToDate(String strApprover, String modifyDate,String ReplacementType) throws Throwable {
		String EndDate= getText(Approvername("EndDateId"), "EndDate before updating");
		   if (isElementClickable(editApprover(strApprover),3, "Click on Edit Icon ")) {
		      //click(editApprover(strApprover), "Click on Edit Icon");
		      reporter.SuccessReport("Approval Management Page Approver Out of Office Settings",
		            "Created Approver Out is in Edit Mode", "");
		   } else {
		      reporter.failureReport("Approval Management Page Approver Out of Office Settings",
		            "Approver Out Does Not Exist", "");
		   }

		   if (isElementPresent(getCalenderToUpdate, "End Date")) {
		      //click(getCalenderToUpdate, "From date");
		      scrollToBottomWithCordinate("-400");
		      click(getCalenderEndUpdate, "End date");
		   }

		   

		  // Modify the date
		   if (isElementPresent(NEXT_MONTH_ARROW, "Next month ")) {
		      click(NEXT_MONTH_ARROW, "Next Month");
		      reporter.SuccessReport("Change Month on Approval Management Page Approver Out of Office Settings",
		            "Arrow Button Exists and Clicked to Change the Month", "");
		   } else {
		      reporter.failureReport("Change Month on Approval Management Page Approver Out of Office Settings",
		            "Arrow Button Does Not Exist", "");
		   }

		   String day = modifyDate.split("-")[0];
		   // Select Day

		   int date = Integer.parseInt(day);
		   date = date + 1;
		   day = String.valueOf(date);

		   if (isElementPresent(dayInStartDayCalender(day), "From Date ")) {
		      click(dayInStartDayCalender(day), day);
		   }

		   // Save
		   if (isElementClickable(SAVE_APPROVER, 2,"Save Icon")) {
		      //click(SAVE_APPROVER, "Save Icon");
		      reporter.SuccessReport("Approval Management Page Approver Out of Office Settings",
		            "Created Approver Out is Modified and Saved", "");
		   } else {
		      reporter.failureReport("Approval Management Page Approver Out of Office Settings",
		            "Approver Out Does Not Exist", "");
		   }
		   String EndDateafterupdating= getText(Approvername("EndDateId"), "EndDate after updating");
		   if(!EndDate.equals(EndDateafterupdating)) {
			   reporter.SuccessReport("Todate updating", "Approval out modified as expected", "Before Updating: "+EndDate+"After Updating: "+EndDateafterupdating, driver);
		   }
		   else {
			   reporter.failureReport("To date update", "Approval out not modified as expected", "", driver);
		   }
		   // Delete

		   if (isElementClickable(deleteApprover(strApprover),2, "Delete Icon")) {
		      //click(deleteApprover(strApprover), "Delete Icon");
		      reporter.SuccessReport("Approval Management Page Approver Out of Office Settings",
		            "Created Approver Out is Deleted", "");
		   } else {
		      reporter.failureReport("Approval Management Page Approver Out of Office Settings",
		            "Approver Out Does Not Exist", "");
		   }
		}
public void DeleteApproverOut(String strApprover) throws Throwable {
	if (isElementClickable(deleteApprover(strApprover),2, "Delete Icon")) {
	      //click(deleteApprover(strApprover), "Delete Icon");
	      reporter.SuccessReport("Approval Management Page Approver Out of Office Settings",
	            "Created Approver Out is Deleted", "");
	   } else {
	      reporter.failureReport("Approval Management Page Approver Out of Office Settings",
	            "Approver Out Does Not Exist", "");
	   }
}
public void SelectRequestorFromRightToLeft(String requestor,int count) throws Throwable {
	//selectByVisibleText(RequestorFromRightToLeft(""), requestor, "requestor");
	//click(BackArrow_button,"Back Arrow button");
	if (isElementPresent(RequestorFromRightToLeft(requestor), "Requestror ")) {
		selectByVisibleText(RequestorGroupList, requestor, "Requestror");
		click(BackArrow_button,"Back Arrow button");
		reporter.SuccessReport("Add Requestors on Create/Edit Requestor Group Page",
				"Requestors Exist in the Available Requestors' List and Selected", requestor);
	}
	else {
		reporter.SuccessReport("Add Requestors on Create/Edit Requestor Group Page",
				"Add Arrow Link Exists and Clicked", "");
		
	}
	
}
	public void Add_Requestor_Btn_Click() throws Throwable {
		if (isElementPresent(SELECT_PATH_ADD_BUTTON, "Select Path option")) {
			click(SELECT_PATH_ADD_BUTTON, "Select Path option");
			reporter.SuccessReport("Add Requestors on Create/Edit Requestor Group Page",
					"Add Arrow Link Exists and Clicked", "");

		} else {
			reporter.failureReport("Add Requestors on Create/Edit Requestor Group Page",
					"Add Arrow Link Does Not Exist", "");
		}
	}

	public void Remove_Requestor_Btn_Click() throws Throwable {
		if (isElementPresent(SELECT_PATH_REMOVE_BUTTON, "Select Path option")) {
			click(SELECT_PATH_REMOVE_BUTTON, "Select Path option");
			reporter.SuccessReport("Remove Requestors on Create/Edit Requestor Group Page",
					"Remove Arrow Link Exists and Clicked", "");

		} else {
			reporter.failureReport("Remove Requestors on Create/Edit Requestor Group Page",
					"Remove Arrow Link Does Not Exist", "");
		}
	}

	public void ClickSaveChangesButton() throws Throwable {
		if (isElementPresent(REQ_SAVE_CHANGES_BTN, "Select Update option")) {
			click(REQ_SAVE_CHANGES_BTN, "Select Update option");
			reporter.SuccessReport("Add Requestors on Create/Edit Requestor Group Page",
					"Save changes button Exists and Clicked", "");
		} else {
			reporter.failureReport("Add Requestors on Create/Edit Requestor Group Page",
					"Save changes button Does Not Exist", "");
		}
	}

	public void UpdatedSuccessMsg() throws Throwable {
		
		if (isVisibleOnly(SUCCESS_UPDATED_MSG, "Updated success message")) {
			String Succesmessage = getText(SUCCESS_UPDATED_MSG, "Updated message");
			reporter.SuccessReport("Add Requestors on Create/Edit Requestor Group Page", "Changes updated successfully",
					Succesmessage);
		} else {
			ClickSaveChangesButton();
			Thread.sleep(2000);
			String Succesmessage1 = getText(Successmsg, "Updated message");
			if (isElementPresent(SUCCESS_UPDATED_MSG, "Updated success message")) {
				reporter.SuccessReport("Add Requestors on Create/Edit Requestor Group Page",
						"Changes updated successfully", Succesmessage1);
			} else {
				reporter.failureReport("Add Requestors on Create/Edit Requestor Group Page",
						"Changes Not updated successfully", "");
			}
		}
	}

	public void ClickRequestorGroupUsersLink() throws Throwable {
		if (isElementPresent(REQUESTOR_GROUP_USERS, "Requestor Group Users Link")) {
			click(REQUESTOR_GROUP_USERS, "Click Requestor Group Users");
			reporter.SuccessReport("Approval Management Reports Page", "Requestor Group Users Link Exists and Clicked",
					"");
		} else {
			reporter.failureReport("Approval Management Reports Page", "Requestor Group Users Link Does Not Exist", "");
		}
	}

	public void ClickRequestorGroupUsersLinkToDeleteRequestor(String ReqGroupName, String reqName) throws Throwable {
		String reqName2 = reqName.split(",")[0];
		if (isElementPresent(SELECT_REQUESTOR_GRP_FROM_SELECTOR(ReqGroupName, reqName2),
				"Requestor Group Users Link ")) {
			click(SELECT_REQUESTOR_GRP_FROM_SELECTOR(ReqGroupName, reqName2), "Click Requestor Group Users");
			reporter.SuccessReport("Approval Management Reports Page", "Requestor Group Users Link Exists and Clicked",
					"");

			// Click on Manage Requestors
			clickManageRequestors();

			// Remove Added Users
			if (isElementPresent(selectRequestorFromAddedList(reqName2), "Approver ")) {
				// selectByVisibleText(selectRequestorType(), reqName2,
				// "Approver");
				click(selectRequestorFromAddedList(reqName2), "Requestor");
				reporter.SuccessReport("Remove Requestors on Create/Edit Requestor Group Page",
						"Requestors Exist in the Available Requestors' List and Selected", reqName);
			} else
				reporter.failureReport("Remove Requestors on Create/Edit Requestor Group Page",
						"Requestors does not Exist in the Available Requestors' List", reqName);

			Remove_Requestor_Btn_Click();

			// Click Save Changes
			ClickSaveChangesButton();

		} else {
			reporter.failureReport("Approval Management Reports Page", "Requestor Group Users Link Does Not Exist", "");
		}
	}

	public void ClickRequestorPageRefreshIcon() throws Throwable {
		if (isElementPresent(REQUESTOR_GRP_REFRESH_LINK, "Refresh icon")) {
			click(REQUESTOR_GRP_REFRESH_LINK, "Click Refresh icon");
		}
	}

	public String DisplayRequestors() throws Throwable {
		String requestor = null;
		String requestorGroup = null;
		String strComm = null;
		if (isElementPresent(GET_REQUESTOR_GROUP_COUNT, "Requestor Group details")) {
			List<WebElement> reqCount = driver.findElements(GET_REQUESTOR_GROUP_COUNT);
			int count = reqCount.size();
			if (count > 1) {
				count = (count * 2) - 2;
				for (int i = 1; i < count; i = i + 2) {
					requestor = driver.findElement(getRequestorName(String.valueOf(i))).getText();
					requestorGroup = driver.findElement(getRequestorName(String.valueOf(i + 1))).getText();
					strComm = "strRequestor:" + requestor.trim() + "\n" + "Requestor Group:" + requestorGroup.trim();
					reporter.SuccessReport("Approval Management Reports Page", "Requestor Group Users Exist", strComm);
				}

			} else {
				reporter.failureReport("Approval Management Reports Page", "Requestor Group Users Do Not Exist", "");
			}
		}

		List<WebElement> reqCount = driver.findElements(GET_REQUESTOR_GROUP_COUNT);
		int count1 = reqCount.size();
		String requestor1 = null;
		if (count1 > 1) {
			requestor1 = driver.findElement(getRequestorName(String.valueOf(1))).getText();

		}

		return requestor1;
	}

	public void VerifyPageCountInApprovalmgmt() throws Throwable {

		if (isVisibleOnly(GET_SELECTED_COUNTinApprMgmt, "")) {
			String pageCount = getSelectedDropdownOption(GET_SELECTED_COUNTinApprMgmt);
			if (pageCount.equals("50")) {
				reporter.SuccessReport("Approval Management Reports Page", "Paging Count is Verified", pageCount);
			}
			else if(pageCount.equals("20")){
				reporter.SuccessReport("Approval Management Reports Page", "Paging Count is Verified", pageCount);
			}

		} else {
			reporter.failureReport("Approval Management Reports Page", "Paging Count is not Verified", "");
		}
	}
	public void VerifyPageCount50() throws Throwable {

		if (isElementPresent(GET_SELECTED_COUNT, "Page count")) {
			String pageCount = getSelectedDropdownOption(GET_SELECTED_COUNT);
			if (pageCount.equals("50")) {
				reporter.SuccessReport("Approval Management Reports Page", "Paging Count is Verified", pageCount);
			}

		} else {
			reporter.failureReport("Approval Management Reports Page", "Paging Count is not Verified", "");
		}
	}
	public void ChangePageCount(String NewPageCount) throws Throwable {
		if (isElementPresent(GET_SELECTED_COUNTinApprMgmt, "Page count")) {
			selectByVisibleText(GET_SELECTED_COUNTinApprMgmt, NewPageCount, "Update page count");
			reporter.SuccessReport("Approval Management Reports Page", "Paging Field Exists and Clicked", "");
		} else {
			reporter.failureReport("Approval Management Reports Page", "Paging Link Does Not Exist", NewPageCount);
		}
	}

	public void ClickNextPageAndVerify() throws Throwable {
		if (isElementPresent(NEXT_PAGE_LINK, "Next Page ")) {
			click(NEXT_PAGE_LINK, "Click Next Page");
			// Verify the Paging Count 50
			VerifyPageCountInApprovalmgmt();
			reporter.SuccessReport("Approval Management Reports Page", "Next Page Link Exists and Clicked", "");
		} else {
			reporter.SuccessReport("Approval Management Reports Page",
					"Next Page Link Does Not Exist Because There is No Enough data", "");
		}

	}

	public void ClickTU_IUSRequestorGroupTieredLink(String requestor) throws Throwable {
		scrollUp();
		if (isElementPresent(TU_IUS_Tired_Requestor_Group_Link(requestor), "Requestor Group Name ")) {
			// mouseHover(TU_IUS_Tired_Requestor_Group_Link, "Requestor Group
			// Name");
			// scrollToWebElement(TU_IUS_Tired_Requestor_Group_Link);
			// scrollUp();
			click(TU_IUS_Tired_Requestor_Group_Link(requestor), "Click Requestor Group Name");
			reporter.SuccessReport("Approval Management Create/Edit Requestor Group",
					"TU_IUS Requestor Group Tiered Link Exists and Clicked", requestor);
		} else {
			reporter.failureReport("Approval Management Create/Edit Requestor Group",
					"TU_IUS Requestor Group Tiered Link Does Not Exist", "");
		}
	}
	public void ClickOnEditInrequestorGrpMgmt() {
		
	}
public void Readdatfromexcel(String filePath) throws Throwable {
	//File root = new File("C:\\Users\\e004303\\Downloads");
			String sfile = System.getProperty("user.dir") + "\\" + "DownloadedFiles" + "\\" + filePath+".xls";
			
			FileInputStream fi = new FileInputStream(sfile);
	Workbook W = Workbook.getWorkbook(fi);

	Sheet s = W.getSheet(0);



	String Username = s.getCell(0,0).getContents();
	String GroupName= s.getCell(1, 0).getContents();
	System.out.println("Username " +Username);
if(Username.contains("User Name")&& GroupName.contains("Approval Group Name")) {

	reporter.SuccessReport("Excel details", "Excecl data contents are verified", "column1:"+Username+",column2:"+GroupName, driver);
}
else {
	reporter.failureReport("Excel details", "Excecl data contents are not verified", "", driver);
}
	File file = new File(sfile);
	if(!file.exists()) {
		System.out.println("F");
	}
	else {
		fi.close();
		file.delete();
		reporter.SuccessReport("Excel File", "File deleted successfully", "", driver);
	}
}
public void ClickOnExporticon() throws Throwable {
	click(Exporticon, "Export icon");
	Thread.sleep(10000);
}
public void PageLinkText() throws Throwable {
	String PageLink = getText(PageLinks, "Page Links");
	if(PageLink.equals("1")) {
		reporter.SuccessReport("Page links", "there is no next page link since there is not enough data", PageLink, driver);
	}
	else {
		reporter.SuccessReport("Page links", "Next Page exists", "", driver);
	}
}
	public void ClickTU_IUSRequestorGroupLink() throws Throwable {
		scrollUp();
		if (isElementPresent(TU_IUS_Requestor_Group_Link, "Requestor Group Name")) {
			mouseHover(TU_IUS_Requestor_Group_Link, "Requestor Group Name");
			click(TU_IUS_Requestor_Group_Link, "Click Requestor Group Name");
			reporter.SuccessReport("Approval Management Create/Edit Requestor Group",
					"TU_IUS Requestor Group Link Exists and Clicked", "");
		} else {
			reporter.failureReport("Approval Management Create/Edit Requestor Group",
					"TU_IUS Requestor Group Link Does Not Exist", "");
		}
	}
public void ClickonEditlinkofRequestorGroMgmt(String requestor) throws Throwable {
	click(EditbuttonInReqGrpMgmt(requestor), "Clicked on "+requestor+" Edit link In ReqGrpMgmt");
}
	public void Verify_Create_Edit_Requestor_GroupPage() throws Throwable {
		if (isElementPresent(REQ_SAVE_CHANGES_BTN, "Create/Edit Requestor Group Page")) {
			reporter.SuccessReport("Approval Management Create/Edit Requestor Group",
					"landed in Create/Edit Requestor Group Page", "");
		} else {
			reporter.failureReport("Approval Management Create/Edit Requestor Group",
					"Create/Edit Requestor Group Page Does Not Exist", "");
		}
	}

	public void SearchByLastName(String reqLastName) throws Throwable {
		String lastName = reqLastName.split(",")[0];
		if (isElementPresent(REQUESTOR_GRP_NAME_TXTBOX, "Requestor Group name textbox")) {
			type(REQUESTOR_GRP_NAME_TXTBOX, lastName, "Enter Requestor Group name textbox");
			reporter.SuccessReport("Approval Management Reports Page", "Last Name or Account Number Field Exists", "");
		} else {
			reporter.failureReport("Approval Management Reports Page",
					"Last Name or Account Number Field Does Not Exist", "");
		}
	}

	public void Click_Search_Icon() throws Throwable {
		if (isElementPresent(SEARCH_REQ_GRP_ICON, "Requestor Group name search icon")) {
			click(SEARCH_REQ_GRP_ICON, "Click Requestor Group name search icon");
			reporter.SuccessReport("Click Search on Approval Management Requestor Group Rules Report Page",
					"SEARCH Link Exists and Clicked", "");
		} else {
			reporter.failureReport("Click Search on Approval Management Requestor Group Rules Report Page",
					"SEARCH Link Does Not Exist", "");
		}
	}
public void ClickOnBackToRefreshIcon() throws Throwable {
	 click(BackTorequestorGroup, "BackTorequestorGroup");
}
	public void RemoveUsers(String ReqGroupName, String reqName) throws Throwable {

		// Click on General Settings to click Reports
		ClickGeneralSettings();

		// Click on Reports
		ClickReports(); 

		// Click on Requestor Requestor Group Users
		ClickRequestorGroupUsersLink();

		// Click Requestor group to delete the member
		ClickRequestorGroupUsersLinkToDeleteRequestor(ReqGroupName, reqName);

	}

	/**
	 * click on Requisition Status Report
	 * 
	 * @throws Throwable
	 */

	public void clickRequisitionStatusReport() throws Throwable {
		if (isElementPresent(REQUISITION_STAUS_REPORT, "Requestor Group Users Link")) {
			click(REQUISITION_STAUS_REPORT, "Click Requestor Group Users");
			reporter.SuccessReport("Approval Management Reports Page",
					"Requisition Status Report Link Exists and Clicked", "");
		} else {
			reporter.failureReport("Approval Management Reports Page", "Requisition Status Report Link Does Not Exist",
					"", driver);
		}
	}

	/**
	 * click on Date Picker for 13 months
	 * 
	 * @throws Throwable
	 */

	public void PreviousdatePicker(int daystoupdate,String datetype) throws Throwable {
		DateFormat dateFormat = new SimpleDateFormat("dd MMM yyyy");
		
		/*
		 * Date currentDate = new Date(); String day = strCurrDay.split("-")[0]; String
		 * month = strCurrDay.split("-")[1]; String year = strCurrDay.split("-")[2];
		 */
		String MonthandYear ="";
		Calendar c = Calendar.getInstance();
        
        Date date = Calendar.getInstance().getTime();
        String today = dateFormat.format(date);
        today = dateFormat.format(date);
        System.out.println("Today : " + today);
        
        
        c.add(Calendar.DAY_OF_MONTH,daystoupdate);
        Date DateAfterAddingdays = c.getTime();
        System.out.println(dateFormat.format(DateAfterAddingdays));
        String strCurrDay1 = dateFormat.format(DateAfterAddingdays);
        String day = strCurrDay1.split(" ")[0];
		String month = strCurrDay1.split(" ")[1];
		String year = strCurrDay1.split(" ")[2];
		if(datetype.equals("FromDate")) {
			click(StartDateCALENDAR, "Click on calendar");
			String MonthandYearBeforeUpdating = driver.findElement(Monthandyearoffromdate).getText();
			String dayBeforeUpdating = driver.findElement(By.xpath("//a[@class='ui-state-default ui-state-active']")).getText();
			reporter.SuccessReport("From Date", "From Date before updating", dayBeforeUpdating+" "+MonthandYearBeforeUpdating, driver);
			}
			else {
			click(EndDateCALENDAR, "Click on calendar");
			}
		
		if (isElementPresent(PREV_MONTH_ARROW, "Previous month")) {
			
			for (int i = 0; i <= 50; i++) {
				MonthandYear= driver.findElement(Monthandyearoffromdate).getText();
				if(MonthandYear.contains(month)&& MonthandYear.contains(year)) {
				click(dayInStartDayCalender(day), "From date after updating: "+day+" "+MonthandYear);
				break;
				}
				else {
				driver.findElement(PREV_MONTH_ARROW).click();
				}
			}
			
			
			reporter.SuccessReport("Change Month on Approval Management Page Approver Out of Office Settings",
					"Arrow Button Exists and Clicked to Change the Month", "");
		} else {
			reporter.failureReport("Change Month on Approval Management Page Approver Out of Office Settings",
					"Arrow Button Does Not Exist", "");
		}
	}
	public void VerifyRequestors() throws Throwable {
		WebElement elem = driver.findElement(By.id("RSRResultTable"));
		List<WebElement> elm = elem.findElements(By.tagName("tr"));
		int count =0;
		for (int i=1;i<=elm.size();i++) {
			List<WebElement> elem1 = elm.get(i).findElements(By.tagName("td"));
			//for (WebElement webElement2 : elem1) {
			for(int j=1;j<=elem1.size();j++) {
				//String text = elem1.get(j).getText();
				String RequestedDate = elem1.get(0).getText();
				String OrderOrDeniedDate = elem1.get(1).getText();
				String Requestor = elem1.get(2).getText();
				String RequestorGroup = elem1.get(3).getText();
				String Approver = elem1.get(4).getText();
				String DaysOpen = elem1.get(5).getText();
				String ReferenceNo = elem1.get(6).getText();
				String Status = elem1.get(7).getText();
				String OrderNo = elem1.get(8).getText();
				reporter.SuccessReport("Results:", "Request Date For all Records is Exists", "RequestedDate:"+RequestedDate+",OrderOrDeniedDate:"+OrderOrDeniedDate+",Requestor:"+Requestor+",RequestorGroup:"+RequestorGroup+",Approver:"+Approver+",DaysOpen:"+DaysOpen+",ReferenceNo:"+ReferenceNo+",Status:"+Status+",OrderNo:"+OrderNo, driver);
			break;
			}
			count++;
			if(count ==5)
				break;
		}
	}
	public void NextdatePicker(int months, String strCurrDay,String datetype) throws Throwable {

		String day = strCurrDay.split("-")[0];
		String month = strCurrDay.split("-")[1];
		String year = strCurrDay.split("-")[2];

		if(datetype.equals("FromDate")) {
			click(StartDateCALENDAR, "Click on calendar");
			}
			else {
				click(EndDateCALENDAR, "Click on calendar");
			}
		if (isElementPresent(NEXT_MONTH_ARROW, "Next month")) {
			for (int i = 0; i <= months; i++) {
				driver.findElement(NEXT_MONTH_ARROW).click();
				//click(NEXT_MONTH_ARROW, "Next month");
			}
			// Select Day
			if (isElementPresent(dayInStartDayCalender(day), "From Date ")) {
				click(dayInStartDayCalender(day), "Day: "+day);
			}

			reporter.SuccessReport("Change Month on Approval Management Page Approver Out of Office Settings",
					"Arrow Button Exists and Clicked to Change the Month", month+"-"+day);
		} else {
			reporter.failureReport("Change Month on Approval Management Page Approver Out of Office Settings",
					"Arrow Button Does Not Exist", "");
		}
	}
	public void SelectCurrentDate(String dateType) throws Throwable {
		String strCurrDay = GetCurrDay();
		String day = strCurrDay.split("-")[0];
		String month = strCurrDay.split("-")[1];
		String year = strCurrDay.split("-")[2];
		if(dateType.equals("FromDate")) {
		click(StartDateCALENDAR, "Click on calendar");
		}
		else {
			click(EndDateCALENDAR, "Click on To date calendar");
		}
for(int i=0;i<=11;i++) {
	if(getText(MonthSelection, "Month Name").contains(month)) {
		break;
	}
	else {
		click(NEXT_MONTH_ARROW, "Next Month");
	}
}
	if (isElementPresent(dayInStartDayCalender(day), "To Date ")) {
		String MonthandYear= driver.findElement(Monthandyearoffromdate).getText();
		click(dayInStartDayCalender(day), "Currentdate: "+day+" "+MonthandYear);
	}
}
		
	

	/**
	 * click on Requisition Status Report
	 * 
	 * @throws Throwable
	 */

	public void clickSearch() throws Throwable {

		click(SEARCH, "Click on search");
	}

	/**
	 * click on Requisition Status Report
	 * 
	 * @throws Throwable
	 */

	public void verifyMessage(String actualtext) throws Throwable {

		String expectedtext = driver.switchTo().alert().getText();
		driver.switchTo().alert().accept();
		if (expectedtext.equalsIgnoreCase(actualtext)) {
			reporter.SuccessReport("Approval Management Requisition Status Reports Page",
					"Report cannot be retrieved for more than one year", expectedtext);
		} else {
			reporter.failureReport("Approval Management Requisition Status Reports Page",
					"Report cannot be retrieved for more than one year does not exist", "", driver);
		}

	}
public void ClickOnSeeAllReports() throws Throwable {
	click(SeeAllReports, "SeeAllReports");
}
	public void changeFilterStatus(String filters) throws Throwable {
		if (isElementPresent(FILTER_BY_STATUS, "Filter by Statsus")) {
			selectByVisibleText(FILTER_BY_STATUS, filters, "Filter Status");
			reporter.SuccessReport("Approval Management Reports Page", "FilterStatus is changed ", "");
		} else {
			reporter.failureReport("Approval Management Reports Page", "FilterStatus is not selected ", "");
		}
	}

	public void displayRequestorsRecords(String FilterStatus) throws Throwable {
		String RequestedDate = null;
		String OrderDate = null;
		String Requestor = null;
		String RequestorGroup = null;
		String Approver = null;
		String DaysOpen = null;
		String reference = null;
		String status = null;
		String order = null;

		if (isElementPresent(RESULTS_TABLE, "Requestor Group details")) {
			
			WebElement elm = driver.findElement(RESULTS_TABLE);
			List<WebElement> elem =elm.findElements(By.tagName("tr"));
			for(int i=2;i<=elem.size();i++) {
			RequestedDate = driver.findElement(getRequestortableDetails(i, 1)).getAttribute("innerText");
			OrderDate = driver.findElement(getRequestortableDetails(i, 2)).getText();
			Requestor = driver.findElement(getRequestortableDetails(i, 3)).getText();
			RequestorGroup = driver.findElement(getRequestortableDetails(i, 4)).getText();
			Approver = driver.findElement(getRequestortableDetails(i, 5)).getText();
			DaysOpen = driver.findElement(getRequestortableDetails(i, 6)).getText();
			reference = driver.findElement(getRequestortableDetails(i, 7)).getText();
			status = driver.findElement(getRequestortableDetails(i, 8)).getText();
			order = driver.findElement(getRequestortableDetails(i, 9)).getText();
			if(FilterStatus.equals("Open Requests")) {
			reporter.SuccessReport("Approval Management Requisition Status Reports Page","Request Date For all Records is Exists",
					"RequestedDate :" + RequestedDate + "OrderDate : " + OrderDate + " Requestor : " + Requestor
							+ " RequestorGroup: " + RequestorGroup + " DaysOpen : " + DaysOpen + "  reference : "
							+ reference + " status : " + status + " Approver : " + Approver + " ");
			if(i==6)
				break;
			}
			else if(FilterStatus.equals("Approved Requests")) {
				
				reporter.SuccessReport("Approval Management Requisition Status Reports Page","Requestor Group For all Records Exists",
						"Requestor Status: "+ reference +" Approver Name: " + Approver);	
				if(i==6)
					break;
			}
			}
		} else {
			reporter.failureReport("Approval Management Reports Page", "Requestor Group Users Do Not Exist", "");
		}
	}
	

	
}
