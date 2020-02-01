package com.insight.WebTest.Canada;

import java.util.Hashtable;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import com.insight.Lib.CanadaLib;
import com.insight.Lib.CartLib;
import com.insight.Lib.ChinaLib;
import com.insight.Lib.CommonLib;
import com.insight.Lib.OrderLib;
import com.insight.Lib.ProductDetailLib;
import com.insight.Lib.ProductDisplayInfoLib;
import com.insight.Lib.SearchLib;
import com.insight.accelerators.ReportControl;
import com.insight.accelerators.TestEngineWeb;
import com.insight.googledrive.ReportStatus;
import com.insight.utilities.TestUtil;

public class CAN18_SearchIncludingFrenchTest extends  CanadaLib{
	
	ChinaLib chinaLib=new ChinaLib();
	SearchLib searchLib = new SearchLib();
	ProductDisplayInfoLib pipLib=new ProductDisplayInfoLib();
	CartLib cartLib=new CartLib();
	CommonLib commonLib=new CommonLib();
	OrderLib orderLib=new OrderLib();
	ProductDetailLib prodLib=new ProductDetailLib();
	CanadaLib canadaLib = new CanadaLib();
	
			// #############################################################################################################
			// #       Name of the Test         :  CAN18_SearchIncludingFrench
			// #       Migration Author         :  Cigniti Technologies
			// #
			// #       Date of Migration        : November 2019
			// #       DESCRIPTION              : To Test basic Search Including French
			// #       Parameters               : StartRow ,EndRow , nextTestJoin
			// #
			// ###############################################################################################################
			@Parameters({ "StartRow", "EndRow", "nextTestJoin" })
			@Test
			public void TC_CAN18(int StartRow,String EndRow, boolean nextTestJoin) throws Throwable {
				
							int counter = 0;
							try {
								int intStartRow = StartRow;
								int intEndRow = ReportControl.fnGetEndRowCunt(EndRow, "CAN18_SearchIncludingFrench", TestDataInsight, "Canada");
								for (int intCounter = intStartRow; intCounter <= intEndRow; intCounter++) {
									try {
										counter = intCounter;
										fnOpenTest();
										ReportStatus.fnDefaultReportStatus();
										ReportControl.intRowCount = intCounter;
										Hashtable<String, String> data = TestUtil.getDataByRowNo("CAN18_SearchIncludingFrench", TestDataInsight,
												"Canada", intCounter);
										TestEngineWeb.reporter.initTestCaseDescription("SearchIncludingFrench");
										chinaLib.selectLanguageOnHomePage(data.get("Country"), data.get("Language"));
										verifyCountry(data.get("Country_Code"));
							//navigateToApplication("CANADA");
							// select language and country

										chinaLib.clickOnConnectionslink();
										navigateToApplication("CANADA");
							//shipbLib.verifyWEbsiteIsCannada();




//				*****************************
// Search for a product
									//	searchLib.searchInHomePage(data.get("SearchText"));
										searchLib.searchInHomePage("Workstations");
										//searchLib.verifyBreadCrumbInSearchResultsPage(data.get("SearchText"));
										searchLib.verifyBreadCrumbInSearchResultsPage("Workstations");
										Thread.sleep(3000);
										// Verify Best Match option
										canadaLib.verifySortOption(data.get("Sort_Option"));
										// select Filter HP INC
										searchLib.filterSelectionInProductsSearchPage(data.get("Manufacturer"));
										Thread.sleep(3000);
										chinaLib.verifyManufacturerOnSearchResultsPage(data.get("Manufacturer"));
										Thread.sleep(3000);
										String firstProdPrice=pipLib.getFirtProductListPrice();
										cartLib.selectFirstProductDisplay();
										pipLib.verifyThePriceInProdDetailsPage(firstProdPrice); // Verifying price in product details page
										// back to results
										pipLib.backToResultsProductDetailsPage();
										Thread.sleep(3000);
										// Adding products to compare list
										int itemscount = Integer.valueOf(data.get("Items_Count")); // 3 items to compare list
										searchLib.clickOnAddToMyCompareListLink(itemscount);
										//Verify  products added to list
										Thread.sleep(3000);
										String compareNum=searchLib.verifyCompareList();
										// click compare items link
										searchLib.clickOnComparedItemsLink();
										Thread.sleep(3000);
										// verify products added in compare list
										chinaLib.verifyCompareProductsPage(itemscount);
										// Verify new part added
										Thread.sleep(3000);
										//chinaLib.addAnotherPartInCompareProductsPage(data.get("Part_Number"));
										chinaLib.addAnotherPartInCompareProductsPage("L9K20UT#ABA");
										//chinaLib.verifyPartNumberAddedInCompareProductListPage(data.get("Part_Number"));
										chinaLib.verifyPartNumberAddedInCompareProductListPage("L9K20UT#ABA");
										// Verify number of products displayed
										chinaLib.verifyCompareProductsPage(itemscount+1);
										// Remove first product
										Thread.sleep(3000);
										searchLib.clickOnCloseIconInCompareProductsPage();
										// verify product count after removal
										Thread.sleep(3000);
										// Verify number of products displayed
										chinaLib.verifyCompareProductsPage(itemscount);
										// add to cart
										pipLib.addToCart();
										orderLib.continueToCheckOutOnAddCart();
	//*******************************************************************************************











/*




							// Search on HomePage
							searchLib.searchInHomePage(data.get("SearchText"));  // Workstations
										searchLib.verifyTheResultsForSearchTerm(data.get("SearchText"));

										verifySortOption(data.get("Sort_Option")); // Meilleurs résultat

										verifyFilterByManufacturerOnSearchResultsPage();
							searchLib.filterSelectionInProductsSearchPage(data.get("Manufacturer_Name")); // HP INC
							pipLib.verifyManufacturerNameInsearchResults(data.get("Manufacturer_Name").replace("INC", "").trim());
							String firstProdPrice = pipLib.getFirtProductListPrice();
							cartLib.selectFirstProductDisplay();
							pipLib.verifyThePriceInProdDetailsPage(firstProdPrice); // Verifying price in product details page
										searchLib.verifyTheResultsForSearchTerm(data.get("SearchText"));
										searchLib.filterSelectionInProductsSearchPage(data.get("Manufacturer_Name")); // HP INC
//verify prices from search prcice and product page
										pipLib.backToResultsProductDetailsPage();
							
							// Click on add to my compare list  --  3 items to compare list
							int itemscount = Integer.valueOf(data.get("Items_Count"));
							searchLib.clickOnAddToMyCompareListLink(itemscount); 
							
							//Verify  products added to list
							String compareNum=searchLib.verifyFrenchCompareList();
							assertTextStringMatching(compareNum, data.get("Items_Count"));
							// click compare items link
							searchLib.clickOnComparedItemsLink();
							// verify products added in compare list
							chinaLib.verifyCompareProductsPage(itemscount);
							
							// Verify new part added >> L9K20UT#ABA
							chinaLib.addAnotherPartInCompareProductsPage(data.get("Part_Number"));
//No of proucts to be verified
							chinaLib.verifyPartNumberAddedOnFrenchCompareProductListPage(data.get("Part_Number"));
							// Remove first product
							searchLib.clickOnCloseIconInCompareProductsPage();
							// verify product count after removal
							assertTextStringMatching(compareNum, data.get("Items_Count"));
							// add to cart 
							pipLib.addToCartForFrench();
							orderLib.continueToCheckOutOnAddCart();
					     	
							System.out.println("Test completed");
							*/
						} catch (Exception e) {
							ReportStatus.blnStatus = false;
						//	gErrorMessage = e.getMessage();
							gTestStatus = false;
						}
					}
				} catch (Exception e) {
					e.printStackTrace();
					ReportStatus.blnStatus = false;
				//	gErrorMessage = e.toString();
					gTestStatus = false;
					ReportStatus.fnUpdateResultStatus("SearchIncludingFrench", "TC_CAN18", ReportStatus.strMethodName, 1, browser);
					throw new RuntimeException(e);
				}
			    finally {
			    	ReportControl.fnEnableJoin();
					ReportStatus.fnUpdateResultStatus("SearchIncludingFrench", "TC_CAN18", ReportStatus.strMethodName, counter, browser);
					fnCloseTest();
					ReportControl.fnNextTestJoin(nextTestJoin);
				}
			}
			}
