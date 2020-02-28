package com.insight.WebTest.EUF;

import java.util.Hashtable;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.insight.Lib.CMTLib;
import com.insight.Lib.CanadaLib;
import com.insight.Lib.CartLib;
import com.insight.Lib.CommonLib;
import com.insight.Lib.EndUserFeaturesLib;
import com.insight.Lib.OrderHistoryLib;
import com.insight.Lib.OrderLib;
import com.insight.Lib.ProductDetailLib;
import com.insight.Lib.ProductDisplayInfoLib;
import com.insight.Lib.SearchLib;
import com.insight.Lib.ShipBillPayLib;
import com.insight.accelerators.ReportControl;
import com.insight.accelerators.TestEngineWeb;
import com.insight.googledrive.ReportStatus;
import com.insight.utilities.TestUtil;

public class EUF19_FavoriteLinksTest extends EndUserFeaturesLib{
	CMTLib cmtLib = new CMTLib();
	CommonLib commonLib = new CommonLib();
	CartLib cartLib = new CartLib();
	SearchLib searchLib = new SearchLib();
	ProductDetailLib prodDetailsLib=new ProductDetailLib();
	OrderLib orderLib=new OrderLib();
	ProductDisplayInfoLib pipLib=new ProductDisplayInfoLib();
	ShipBillPayLib sbpLib=new ShipBillPayLib();
	CanadaLib canadaLib = new CanadaLib();
	OrderHistoryLib orderHistoryLib=new OrderHistoryLib();
	   
	    // #############################################################################################################
		// #       Name of the Test         :  EUF19_FavoriteLinks
		// #       Migration Author         :  Cigniti Technologies
		// #
		// #       Date of Migration        : October 2019
		// #       DESCRIPTION              : This Test is used to Test FCTWebEndUserManageSoldtos
		// #       Parameters               : StartRow ,EndRow , nextTestJoin
		// #
		// ###############################################################################################################
		@Parameters({ "StartRow", "EndRow", "nextTestJoin" })
		@Test
		public void TC_EUF19(int StartRow,String EndRow, boolean nextTestJoin) throws Throwable {
			int counter = 0;
			try {
				int intStartRow = StartRow;
				int intEndRow = ReportControl.fnGetEndRowCunt(EndRow, "EUF19_FavoriteLinks", TestDataInsight, "End_User");
				for (int intCounter = intStartRow; intCounter <= intEndRow; intCounter++) {
					try {
						fnOpenTest();
						ReportStatus.fnDefaultReportStatus();
						ReportControl.intRowCount = intCounter;
						Hashtable<String, String> data = TestUtil.getDataByRowNo("EUF19_FavoriteLinks", TestDataInsight, "End_User", intCounter);
						TestEngineWeb.reporter.initTestCaseDescription("FavoriteLinks");
                        
						cmtLib.loginToCMTSearchWebGrpAndUser(data.get("Header"), data.get("WebGrp"),
								data.get("LnameEmailUname"), data.get("ContactName"));
						
						cmtLib.clickOnloginAs();
						switchToChildWindow();

						commonLib.clickOnAccountToolsAndClickOnProductGrp(data.get("Tools_Menu"), data.get("Tools_Menu_DD"));

						clickOnTabInUserProfile(data.get("Tab_Name"));
						Thread.sleep(14000);
						commonLib.spinnerImage();
						deleteAllFavouriteLinks();
						clickAvailableLink(data.get("Available_Link"));
						selectFavourite(data.get("Available_Item1"));
						selectFavourite(data.get("Available_Item2"));
						clickUpdateButtonInFavouritesLinks();
						//Verifying 1st favourite link
						selectToolsDropDownInHomepage(data.get("Order_Tracking"));
					//	orderHistoryLib.verifyOrderHistoryPage();
						//Verifying 1st favourite link
						Thread.sleep(7000);
						selectToolsOnHeader();
						selectToolsDropDownInHomepage(data.get("Invoice_History"));
						//canadaLib.verifyInvoiceHistoryPageOpened();
						//manage fav link
						selectToolsOnHeader();

						clickSettingsIconInToolsDD();

						Thread.sleep(5000);
						//scrollToBottomWithCordinate("-300");
	//Extra requirement
						//clickOnTabInUserProfile("Favorites");
						scrollToBottomWithCordinate("500");
						verifyFavouritesLinksPageOpened();
						//verifyAccountFavouritesLinksPageOpened();

						deleteAllFavouriteLinks();
						clickUpdateButtonInFavouritesLinks();
						selectToolsDropdownAndVerifyFavouriteLinkIsNotPresent(data.get("Order_Tracking"));
						selectToolsOnHeader();

						selectToolsDropdownAndVerifyFavouriteLinkIsNotPresent(data.get("Invoice_History"));


						commonLib.clickLogOutLink(data.get("Logout_Header"));
						System.out.println("Test completed");
					} catch (Exception e) {
						ReportStatus.blnStatus = false;
						//e.getClass().getSimpleName();
						gErrorMessage = e.getClass().getSimpleName();
						gTestStatus = false;
					}
									}
			} catch (Exception e) {
				e.printStackTrace();
				ReportStatus.blnStatus = false;
				gErrorMessage = e.getClass().getSimpleName();
				gTestStatus = false;
				ReportStatus.fnUpdateResultStatus("FavoriteLinks", "TC_EUF19", ReportStatus.strMethodName, 1, browser);
				throw new RuntimeException(e);
			}

			finally {
            	ReportControl.fnEnableJoin();
            	ReportStatus.fnUpdateResultStatus("FavoriteLinks", "TC_EUF19", ReportStatus.strMethodName, counter, browser);
				fnCloseTest();
				ReportControl.fnNextTestJoin(nextTestJoin);
			}
		}

}
