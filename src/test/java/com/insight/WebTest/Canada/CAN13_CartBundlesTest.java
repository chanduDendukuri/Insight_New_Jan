package com.insight.WebTest.Canada;
import com.insight.Lib.*;
import com.insight.accelerators.ReportControl;
import com.insight.accelerators.TestEngineWeb;
import com.insight.googledrive.ReportStatus;
import com.insight.utilities.TestUtil;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import java.util.Hashtable;

public class CAN13_CartBundlesTest extends CanadaLib{
	
	CMTLib cmtLib = new CMTLib();
	CommonLib commonLib = new CommonLib();
	CartLib cartLib = new CartLib();
	SearchLib searchLib = new SearchLib();
	ProductDetailLib prodDetailsLib=new ProductDetailLib();
	OrderLib orderLib=new OrderLib();
	ProductDisplayInfoLib pipLib=new ProductDisplayInfoLib();
	ShipBillPayLib sbpLib=new ShipBillPayLib();
	   
	    // #############################################################################################################
		// #       Name of the Test         :  CAN13_CartBundlesTest
		// #       Migration Author         :  Cigniti Technologies
		// #
		// #       Date of Migration        : October 2019
		// #       DESCRIPTION              : This method is to verify MenuSearch for canada
		// #       Parameters               : StartRow ,EndRow , nextTestJoin
		// #
		// ###############################################################################################################
		@Parameters({ "StartRow", "EndRow", "nextTestJoin" })
		@Test
		public void TC_CAN13(int StartRow,String EndRow, boolean nextTestJoin) throws Throwable {
			
						int counter = 0;
						try {
							int intStartRow = StartRow;
							int intEndRow = ReportControl.fnGetEndRowCunt(EndRow, "CAN13_CartBundles", TestDataInsight, "Canada");
							for (int intCounter = intStartRow; intCounter <= intEndRow; intCounter++) {
								try {
									counter = intCounter;
									fnOpenTest();
									ReportStatus.fnDefaultReportStatus();
									ReportControl.intRowCount = intCounter;
									Hashtable<String, String> data = TestUtil.getDataByRowNo("CAN13_CartBundles", TestDataInsight,
											"Canada", intCounter);
									TestEngineWeb.reporter.initTestCaseDescription("CartBundles");
						CommonLib commonLib = new CommonLib();
						CMTLib cmtLib = new CMTLib();
						CartLib cartLib = new CartLib();
						OrderLib orderLib = new OrderLib();
						ShipBillPayLib shipbLib = new ShipBillPayLib();
						CanadaLib canadaLib = new CanadaLib();


						cmtLib.loginToCMTSearchWebGrpAndUser(data.get("Header"), data.get("WebGrp"), data.get("LnameEmailUname"),
								data.get("ContactName"));
						cmtLib.clickOnloginAs();
						switchToChildWindow();
						canadaLib.verifyCanadaWebgroup();
						commonLib.clickAccountToolsFromSideMenuAndClickOnProductGrp(data.get("Tools_Menu"),
								data.get("Tools_Menu_DD"), data.get("Product_Group"), data.get("Product_Name"));
						searchLib.clickAddToOrderOnCompanyStandardsScreen();
						commonLib.clickCart();
						commonLib.verifyBundleIsAddedToCart();
						canadaLib.verifyAvailabilityOfTheProductInCart();
						shipbLib.verifyPriceIsCAD(data.get("CANDAIAN_DOLLAR"));
						commonLib.clickLogOutLink(data.get("Logout_Header"));
						//fnCloseTest();
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
				ReportStatus.fnUpdateResultStatus("_CartBundles", "TC_CAN13", ReportStatus.strMethodName, 1, browser);
				throw new RuntimeException(e);
			}
		    finally {
		    	ReportControl.fnEnableJoin();
				ReportStatus.fnUpdateResultStatus("_CartBundles", "TC_CAN13", ReportStatus.strMethodName, counter, browser);
				fnCloseTest();
				ReportControl.fnNextTestJoin(nextTestJoin);
			}
		}
		}
