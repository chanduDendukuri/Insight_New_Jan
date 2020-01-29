package com.insight.WebTest.InvoiceHistory;

import java.util.Hashtable;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.insight.Lib.CMTLib;
import com.insight.Lib.CanadaLib;
import com.insight.Lib.CommonLib;
import com.insight.Lib.InvoiceHistoryLib;
import com.insight.accelerators.ReportControl;
import com.insight.accelerators.TestEngineWeb;
import com.insight.googledrive.ReportStatus;
import com.insight.utilities.TestUtil;

public class IVH03_InvoiceDetailsHierachyTreeControlsTest extends InvoiceHistoryLib {
	@Parameters({ "StartRow", "EndRow", "nextTestJoin" })
	@Test
	public void TC_IVH03(int StartRow, String EndRow, boolean nextTestJoin) throws Throwable {
	
					
					int counter = 0;
					try {
						int intStartRow = StartRow;
						int intEndRow = ReportControl.fnGetEndRowCunt(EndRow, "IVH03_InvoiceDetailsHierachyTreeControls", TestDataInsight,
								"Invoice_History");
						for (int intCounter = intStartRow; intCounter <= intEndRow; intCounter++) {
							try {
								counter = intCounter;
								fnOpenTest();
								ReportStatus.fnDefaultReportStatus();
								ReportControl.intRowCount = intCounter;
								Hashtable<String, String> data = TestUtil.getDataByRowNo("IVH03_InvoiceDetailsHierachyTreeControls",
										TestDataInsight, "Invoice_History", intCounter);
								TestEngineWeb.reporter.initTestCaseDescription("InvoiceDetailsHierachyTreeControls");
					CMTLib cmtLib = new CMTLib();
					CanadaLib canadaLib = new CanadaLib();
					InvoiceHistoryLib invoiceHistoryLib = new InvoiceHistoryLib();
					CommonLib commonLib = new CommonLib();

					cmtLib.loginToCMTSearchWebGrpAndUser(data.get("Header"), data.get("WebGrp"),
							data.get("LnameEmailUname"), data.get("ContactName"));

					cmtLib.setPermissions(data.get("Menu_Name"), data.get("Set_Permission"));
					cmtLib.permissionFromDD(data.get("Set_Permission"), data.get("Permission_Dropdown_Option"));
					cmtLib.setPermissions(data.get("Menu_Name"), data.get("Set_Permission1"));

					cmtLib.clickOnloginAs();
					switchToChildWindow();

					cmtLib.loginVerification(data.get("ContactName"));
					closeAccountTools();
					canadaLib.clickOnSideMenuSelectAccountToolOptions(data.get("Tools_Menu"),
							data.get("Tools_Menu_DD"));
					canadaLib.verifyInvoiceHistoryPageOpened();
					invoiceHistoryLib.clickOnAdvancedSearch();
					scrollUp();
					clickShowAccountHirerachy();
					verifyAccountHirearchyPopUp();
					verifyTree();
					verifyGGPDetails();
					verifyGPAccountHierarchyTree();
					verifyRPAccountHierarchyTree();
					
					closeHierarchyPopUp();

					canadaLib.clickOnSideMenuSelectAccountToolOptions(data.get("Tools_Menu"),
							data.get("Tools_Menu_DD"));
					canadaLib.verifyInvoiceHistoryPageOpened();
					invoiceHistoryLib.clickOnAdvancedSearch();
					scrollUp();
					verifySelectedOptionInAccountSelectionDD(data.get("Default_Option"));
					// ######## GGP Level ################################
					canadaLib.clickOnSideMenuSelectAccountToolOptions(data.get("Tools_Menu"),
							data.get("Tools_Menu_DD"));
					canadaLib.verifyInvoiceHistoryPageOpened();
					invoiceHistoryLib.clickOnAdvancedSearch();
					scrollUp();
					verifySelectedOptionInAccountSelectionDD(data.get("Default_Option"));
					closeAccountHirearchyDropdow();
					setAccountHirerachydropdown(data.get("GGP"));
					clickShowAccountHirerachy();
					verifyAccountHirearchyPopUp();
					verifyTree();
					verifyGGPDetails();
					verifyGPAccountHierarchyTree();
					verifyRPAccountHierarchyTree();
					/*
					 * getAccountNumber(data.get("Account_Number"));
					 * getAccountName(data.get("Account_Name"));
					 */
					closeHierarchyPopUp();
					// ######## GGP Level default account ################################
					canadaLib.clickOnSideMenuSelectAccountToolOptions(data.get("Tools_Menu"),
							data.get("Tools_Menu_DD"));
					canadaLib.verifyInvoiceHistoryPageOpened();
					invoiceHistoryLib.clickOnAdvancedSearch();
					scrollUp();
					verifySelectedOptionInAccountSelectionDD(data.get("Default_Option"));
					closeAccountHirearchyDropdow();
					setAccountHirerachydropdown(data.get("GGP"));
					clickShowAccountHirerachy();
					verifyAccountHirearchyPopUp();
					verifyTree();
					verifyGGPDetails();
					verifyGPAccountHierarchyTree();
					verifyRPAccountHierarchyTree();
					/*
					 * getAccountNumber(data.get("Account_Number1"));
					 * getAccountName(data.get("Account_Name1"));
					 */
					verifyDefaultSelectedAccount(data.get("Default_Account"));
					closeHierarchyPopUp();
					// ######## RP Level default account ################################
					canadaLib.clickOnSideMenuSelectAccountToolOptions(data.get("Tools_Menu"),
							data.get("Tools_Menu_DD"));
					canadaLib.verifyInvoiceHistoryPageOpened();
					invoiceHistoryLib.clickOnAdvancedSearch();
					scrollUp();
					 verifySelectedOptionInAccountSelectionDD(data.get("Default_Option"));
					 closeAccountHirearchyDropdow();
					setAccountHirerachydropdown(data.get("RP"));
					clickShowAccountHirerachy();
					verifyAccountHirearchyPopUp();
					verifyTree();
					verifyGGPDetails();
					verifyGPAccountHierarchyTree();
					verifyRPAccountHierarchyTree();
					verifyDefaultSelectedAccount(data.get("Default_Account1"));
					closeHierarchyPopUp();
					commonLib.clickLogOutLink(data.get("Logout_Header"));
					
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
						ReportStatus.fnUpdateResultStatus("InvoiceDetailsHierachyTreeControls", "TC_IVH03", ReportStatus.strMethodName, 1, browser);
						throw new RuntimeException(e);
					} finally {
						ReportControl.fnEnableJoin();
						ReportStatus.fnUpdateResultStatus("InvoiceDetailsHierachyTreeControls", "TC_IVH03", ReportStatus.strMethodName, counter, browser);
						fnCloseTest();
						ReportControl.fnNextTestJoin(nextTestJoin);
					}
				}
				}

