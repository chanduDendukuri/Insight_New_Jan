package com.insight.Lib;

import java.util.ArrayList;
import java.util.List;

import com.insight.ObjRepo.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class ProductDisplayInfoLib extends productsDisplayInfoObj {

    SearchLib searchLib = new SearchLib();
    CommonLib commonLib = new CommonLib();
    CartLib cartLib = new CartLib();
    OrderLib orderLib = new OrderLib();
    CanadaLib canadaLib = new CanadaLib();

    /**
     * This method is to fill the Product Research Request details and submit it
     * and verify the success message.
     *
     * @param name
     * @param email
     * @param country
     * @param quantity
     * @param partNo
     * @param mnfr
     * @param prodDesc
     * @throws Throwable
     */
    public void clickProductResearchRequestAndFillDetails(String name, String email, String country, String quantity,
                                                          String partNo, String mnfr, String prodDesc) throws Throwable {
        //click(productsDisplayInfoObj.PRODUCT_RESEARCH, "Product research request link");
        waitForVisibilityOfElement(productsDisplayInfoObj.PROD_RESEARCH_NAME_TXT_BOX, "Name");
        type(productsDisplayInfoObj.PROD_RESEARCH_NAME_TXT_BOX, name, "Your name text box");
        type(productsDisplayInfoObj.PROD_RESEARCH_EMAIL_TXT_BOX, email, "email text box");
        type(productsDisplayInfoObj.PROD_RESEARCH_COUNTRY_TXT_BOX, country, "Country text box");
        type(productsDisplayInfoObj.PROD_RESEARCH_QUANTITY_TXT_BOX, quantity, "Quantity text box");
        type(productsDisplayInfoObj.PROD_RESEARCH_PARTNO_TXT_BOX, partNo, "Part number text box");
        type(productsDisplayInfoObj.PROD_RESEARCH_MANFR_TXT_BOX, mnfr, "Manufacturer text box");
        type(productsDisplayInfoObj.PROD_RESEARCH_PROD_DESC_TXT_BOX, prodDesc, "Product description text box");
        clickUntil(productsDisplayInfoObj.PRODUCT_REQ_SEND_BTN, productsDisplayInfoObj.PROD_REQ_SENT_MSG, "Product Research request screen send button");
        //JSClick(productsDisplayInfoObj.PRODUCT_REQ_SEND_BTN, "send button");
        if (isElementPresent(productsDisplayInfoObj.PROD_REQ_SENT_MSG, "Success message")) {

            reporter.SuccessReport("Verify the success message", "Product Research Request sent successfully", "");
        } else {
            reporter.failureReport("Verify the success message", "Product Research Request not sent.", "");
        }
        click(productsDisplayInfoObj.CLOSE, "Close button");
    }


    public void verifyProductResearchRequestPopupFields() throws Throwable {

        // Your name
        if (isVisibleOnly(productsDisplayInfoObj.PROD_RESEARCH_NAME_TXT_BOX, "Name text box")) {
            reporter.SuccessReport("verify product research name text box exists", "Your Name Field Exists", "Your Name");
        } else {
            reporter.failureReport("verify product research name text box exists", "Your Name Field does not Exists", "Your Name");
        }
        // Email
        if (isVisibleOnly(productsDisplayInfoObj.PROD_RESEARCH_EMAIL_TXT_BOX, "email text box")) {
            reporter.SuccessReport("verify product research email text box exists", "email Field Exists", "Email");
        } else {
            reporter.failureReport("verify product research email text box exists", "email Field does not Exists", "Email");
        }

        // Additional Email
        if (isVisibleOnly(productsDisplayInfoObj.PROD_RESEARCH_ADDEMAIL_TXT_BOX, "Additional email text box")) {
            reporter.SuccessReport("verify product research Additional email text box exists", "Additional email Field Exists", "Additional Email");
        } else {
            reporter.failureReport("verify product research Additional email text box exists", "Additional email Field does not Exists", "Additional Email");
        }

        //country
        if (isVisibleOnly(productsDisplayInfoObj.PROD_RESEARCH_COUNTRY_TXT_BOX, "Country")) {
            reporter.SuccessReport("verify product research Country text box exists", "Country Field Exists", "Country");
        } else {
            reporter.failureReport("verify product research Country text box exists", "Country Field does not Exists", "Country");
        }

        // quantity
        if (isVisibleOnly(productsDisplayInfoObj.PROD_RESEARCH_QUANTITY_TXT_BOX, "quantity")) {
            reporter.SuccessReport("verify product research quantity text box exists", "quantity Field Exists", "quantity");
        } else {
            reporter.failureReport("verify product research quantity text box exists", "quantity Field does not Exists", "quantity");
        }

        // partNo
        if (isVisibleOnly(productsDisplayInfoObj.PROD_RESEARCH_PARTNO_TXT_BOX, "part Number")) {
            reporter.SuccessReport("verify product research part Number text box exists", "part Number Field Exists", "part Number");
        } else {
            reporter.failureReport("verify product research part Number text box exists", "part Number Field does not Exists", "part Number");
        }

        // Manufacturer
        if (isVisibleOnly(productsDisplayInfoObj.PROD_RESEARCH_MANFR_TXT_BOX, "Manufacturer")) {
            reporter.SuccessReport("verify product research Manufacturer text box exists", "Manufacturer Field Exists", "Manufacturer ");
        } else {
            reporter.failureReport("verify product research Manufacturer text box exists", "Manufacturer Field does not Exists", "Manufacturer ");
        }

        // product description
        if (isVisibleOnly(productsDisplayInfoObj.PROD_RESEARCH_PROD_DESC_TXT_BOX, "product description")) {
            reporter.SuccessReport("verify product research product description text box exists", "product description Field Exists", "product description ");
        } else {
            reporter.failureReport("verify product research product description text box exists", "product description Field does not Exists", "product description ");
        }

        // Maintenance
        if (isVisibleOnly(productsDisplayInfoObj.MAINTENANCE_FIELD, "Maintenance")) {
            reporter.SuccessReport("verify product research Maintenance filed exists", "Maintenance Radio Selecton Exists", "Maintenance selection");
        } else {
            reporter.failureReport("verify product research Maintenance field exists", "Maintenance Radio Selecton does not Exists", "Maintenance selection");
        }
    }


    /**
     * This method is to verify the product search results displayed correctly
     * or not.
     *
     * @throws Throwable
     */
    public void verifyTheSearchResultsDisplayed() throws Throwable {

        List<WebElement> myList = driver.findElements(LIST_OF_ITEMS_SEARCH_RESULTS);
        for (int i = 0; i < myList.size(); i++) {
            String productName = getText(productsDisplayInfoObj.getproductName(i), "Get product name");
            if (!productName.isEmpty()) {
                reporter.SuccessReport("Verify the product name", "Product name is displayed as: ", productName);
            } else {
                reporter.failureReport("Verify the product name", "Product name is not displayed", "");
            }

            String productImage = getAttributeBySrc(productsDisplayInfoObj.getproductImage(i), "Getting Src");
            System.out.println(productImage);
            if (productImage.isEmpty() || productImage.contains("noImageAvailable")) {

                reporter.failureReport("Verify the product Image", "Product image is not displayed", "", driver);
            } else {
                reporter.SuccessReport("Verify the product Image", "Product image is displayed", "");
            }

            String features = getText(productsDisplayInfoObj.getProductFeatures(i), "get product features");
            System.out.println(features);
            if (!features.isEmpty()) {
                reporter.SuccessReport("Verify the product features", "Product features are displayed as : ", features);
            } else {
                reporter.failureReport("Verify the product features", "Product features are not displayed", "", driver);
            }

            String price = getText(productsDisplayInfoObj.getProductPrice(i), "get product price");
            System.out.println(price);
            if (!price.isEmpty()) {
                reporter.SuccessReport("Verify the product price", "Product price is displayed as: ", price);
            } else {
                reporter.failureReport("Verify the product price", "Product price is not displayed", "", driver);
            }

            String partNumber = getText(productsDisplayInfoObj.getPartNumber(i), "get product number");
            System.out.println(partNumber);
            if (!partNumber.isEmpty()) {
                reporter.SuccessReport("Verify the product part Number", "Product part Number is displayed as : ",
                        partNumber);
            } else {
                reporter.failureReport("Verify the product part Number", "Product part Number is not displayed", "", driver);
            }
        }
    }

    /**
     * Method is to click on the Product research request link in the Product
     * search results page.
     *
     * @throws Throwable
     */
    public void clickProductResearchRequest() throws Throwable {
        Thread.sleep(3000);
        click(productsDisplayInfoObj.PRODUCT_RESEARCH, "Product research request link", "Product research request link");
    }

    /**
     * @param productName
     * @throws Throwable
     */
    public void clickSendWithoutFillingRequestProductAndVerify(String productName) throws Throwable {
        Thread.sleep(2000);
        click(productsDisplayInfoObj.PRODUCT_REQ_SEND_BTN, "Product Research request screen send button", "Send button");
        //JSClick(productsDisplayInfoObj.PRODUCT_REQ_SEND_BTN, "send button");
        if (isElementPresent(productsDisplayInfoObj.ERROR_MSG, "Error message in Product Research Request screen exists")) {
            reporter.SuccessReport("Verify Error Message", "Error message displayed", "Please enter the fields error message");
        } else {
            reporter.failureReport("Verify Error Message", "Error message not displayed", "", driver);
        }
        click(productsDisplayInfoObj.PRODUCT_REQ_CANCEL_BTN, "Product Research request screen CANCEL button");
    }

    /**
     * Method is to click on Compare similar link in the products page.
     *
     * @throws Throwable
     */
    public String clickOnCompareSimilarLink(String itemNo) throws Throwable {
        int number = Integer.parseInt(itemNo);
        String productName = getText(productsDisplayInfoObj.getproductName(number), "Get product name");
        clickOnly(productsDisplayInfoObj.COMPARE_SIMILAR_LINK(itemNo), "Compare similar link");
        return productName;
    }

    /**
     * Method is to click on Add to Cart button
     *
     * @throws Throwable
     */
    public void addToCart() throws Throwable {
        click(productsDisplayInfoObj.ADD_TO_CART, "Add to cart");
        if (isElementPresent(productsDisplayInfoObj.ADDED_TO_CART_LABEL, "Add to cart screen") || isElementPresent(productsDisplayInfoObj.ADDED_TO_CART_CANADA, "Add to cart screen")) {
            reporter.SuccessReport("Verify the product added  to cart ", "Product added to cart sucessfully", "");
        } else {
            reporter.failureReport("Verify the product added  to cart ", "Product not added to cart", "");
        }
    }

    /**
     * Method is to click on Add to cart and verify
     *
     * @throws Throwable
     */
    public void addToCartForFrench() throws Throwable {
        click(productsDisplayInfoObj.ADD_TO_CART, "Add to cart");
        if (isElementPresent(productsDisplayInfoObj.ADD_TO_CART_FRENCH_MSG, "Add to cart screen")) {
            reporter.SuccessReport("Verify the product added  to cart ", "Product added to cart sucessfully", "");
        } else {
            reporter.failureReport("Verify the product added  to cart ", "Product not added to cart", "");
        }
    }


    /**
     * Method is to enter the price details in the filter and verify.
     *
     * @param minPrice
     * @param maxPrice
     * @throws Throwable
     */
    public void enterPriceDetailsFilters(String minPrice, String maxPrice) throws Throwable {

        String priceFilter = null;
        String result = null;
        boolean flag = true;

        type(productsDisplayInfoObj.MIN_PRICE, minPrice, "Minimum price");
        type(productsDisplayInfoObj.MAX_PRICE, maxPrice, "Maximum price");
        click(productsDisplayInfoObj.PRICE_SUBMIT, "filter price GO button");
        //JSClick(productsDisplayInfoObj.PRICE_SUBMIT, "filter price GO button");
        if (flag) {
            // adding the filter elements to list
            List<WebElement> myList = driver.findElements(productsDisplayInfoObj.FILTER_ITEM);
            List<String> all_elements_text = new ArrayList<>();
            for (int i = 0; i < myList.size(); i++) {
                all_elements_text.add(myList.get(i).getText());
                result = myList.get(i).getText().replace(",", "");
                Thread.sleep(2000);

                priceFilter = "$" + minPrice.replace(",", "").replace(".00", "") + "-$" + maxPrice;
                if (result.replace(".00", "").contains(priceFilter)) {
                    Thread.sleep(2000);
                    reporter.SuccessReport("Verify the results for search term in products display page ",
                            "Verification is sucessfull. Expected filter is:", "price filter : " + result);
                }
            }
        } else {
            reporter.failureReport("Verify the results for search term in products display page ",
                    "Verification is not sucessfull. Actual filter is:" + result + " Expected is: " + priceFilter, "");
        }
    }

    public void verifyListPrice() throws Throwable {
        if (isVisibleOnly(productsDisplayInfoObj.MIN_PRICE, "min price") && isVisibleOnly(productsDisplayInfoObj.MAX_PRICE, "Max price")) {
            reporter.SuccessReport("List Price Range in Narrow Section on Search Results Page", "List Price Range Exists", "");
        } else {
            reporter.failureReport("List Price Range in Narrow Section on Search Results Page", "List Price Range does not Exists", "", driver);
        }
    }

    /**
     * This method is to click on pagination numbers.
     *
     * @throws Throwable
     */
    public void pagination() throws Throwable {
        // scrollToWebElement(productsDisplayInfoObj.PAGINATON);
        waitForVisibilityOfElement(productsDisplayInfoObj.PAGINATON, "PAGINATON", driver);
        click(productsDisplayInfoObj.PAGINATON, "PAGINATON");
    }

    /**
     * Method is to click on the first product and navigate to the product
     * details page and return back.
     *
     * @throws Throwable
     */
    public void selectFirstProductAndReturnBack() throws Throwable {

        Thread.sleep(3000);
        click(productsDisplayInfoObj.FIRST_PROD_NAME, "First product in search results page");
        isElementPresent(productsDisplayInfoObj.BACK_TO_RESULTS, "Back to results", true);
        click(productsDisplayInfoObj.BACK_TO_RESULTS, "Back to results link");
    }

    /**
     * Method is to click on the first image and navigate to the product details
     * page and return back.
     *
     * @throws Throwable
     */
    public void selectFirstProductImageAndReturnBack() throws Throwable {
        click(productsDisplayInfoObj.FIRST_PROD_IMAGE, "First name in product search page");
        click(productsDisplayInfoObj.BACK_TO_RESULTS, "Back to results link");
    }

    /**
     * This method is to select the sort by options in the product search
     * results page.
     *
     * @param sortOption
     * @throws Throwable
     */
    public void selectSortByOptions(String sortOption) throws Throwable {

        String strArray[] = sortOption.split(",");
        for (i = 0; i <= strArray.length; i++) {
            click(productsDisplayInfoObj.SORT_DD, "Sort By drop down");
            click(productsDisplayInfoObj.getSortByOptions(strArray[i]), "Select sort option");
            waitForVisibilityOfElement(productsDisplayInfoObj.SORT_DD, "Sort option");
            Thread.sleep(1000);
            String actualSort = getText(productsDisplayInfoObj.SORT_DD, " Actual sort");
            if (actualSort.equals(strArray[i])) {
                reporter.SuccessReport("Verify the results for sort By filter option ",
                        "Verification is sucessfull. Expected filter is:", strArray[i]);
            } else {
                reporter.failureReport("Verify the results for sort By filter option ",
                        "Verification is not sucessfull. Actual filter is:" + actualSort + " .Expected is :",
                        strArray[i]);
            }
        }
    }

    /**
     * This method is to select the first product in the search results and
     * verify the presence of Personal product list link and click on it.
     *
     * @throws Throwable
     */
    public void selectProductAndverifyPersonalProductListLinkPresent() throws Throwable {

        isElementPresent(productsDisplayInfoObj.BACK_TO_RESULTS, "Back to results");
        scrollToBottomWithCordinate("150");
        if (isElementPresent(ADD_TO_PERSONAL_PROD_LINK, "Add to personal product link")) {
            reporter.SuccessReport("Verify personal product link present", "Add to Product Center' Link Exists", "");
            click(ADD_TO_PERSONAL_PROD_LINK, "Add to personal product link", "Add to personal product link");
        } else {
            reporter.failureReport("Verify personal product link present ",
                    "Personal product is not present. Enable the settings and Login As", "");
        }
    }


    /**
     * verify the non presence of Personal product list link
     *
     * @throws Throwable
     * @throws InterruptedException
     */
    public void verifyPersonalProductListLinkNotPresent() throws InterruptedException, Throwable {
        if (isElementNotPresent(ADD_TO_PERSONAL_PROD_LINK, "Add to personal product link")) {
            Thread.sleep(3000);
            reporter.SuccessReport("Verify personal product link present ",
                    "Add to Product Center' Link does not Exist", "");
            LOG.info("Enable the settings in CMT and Login As to get the Add to Personal product list link");
        } else {
            reporter.failureReport("Verify personal product link present ",
                    "Personal product is present", "");
        }
    }

    /**
     * Method is to verify the products added to the personal product list.
     *
     * @param url
     * @throws Throwable
     */
    public void verifyPersonalProductListPage(String url) throws Throwable {

        String productName = getText(PRODUCT_NAME, "Product  name - product details page");
        click(ADDED_TO_PERSONAL_PROD_LIST, "Added to personal product link");
        verify_url(WebDriver, url);
        String actualName = getText(PROD_NAME_PERSONAL_PROD_LIST, "product name n personal product list");
        if (productName.equals(actualName)) {
            reporter.SuccessReport("Verify personal product page product name ",
                    "Product is added to the personal product list", "");
        } else {
            reporter.failureReport("Verify personal product page product name ",
                    "Product is not added to the personal product list", "");
        }
    }

    /**
     * This method is to add part item to the Personal Product List and verify
     * the products added.
     *
     * @param partNo
     * @throws Throwable
     */
    public void addItemsToProductList(String partNo) throws Throwable {
        //click(ADDED_TO_PERSONAL_PROD_LIST, "ADDED TO PERSONAL PRODUCT LIST");
        if (isVisibleOnly(ADD_ITEMS_TEXTBOX, "items text box")) {
            reporter.SuccessReport("Verify Personal Product List Page ", "page Exists", "");
            type(ADD_ITEMS_TEXTBOX, partNo, "Add item(s) to your list");
            click(ADD_BTN, "Add button", "Add button to Add Part to Personal products list");
            verifyManufacturerPartInPersonalListPage(partNo);
        } else {
            reporter.failureReport("Verify Personal Product List Page ", "page does not Exists", "");
        }

    }

    public void ClickAddedItemsToPersonalProductList() throws Throwable {
        waitForVisibilityOfElement(ADDED_TO_PERSONAL_PROD_LIST, "ADDED TO PERSONAL PRODUCT LIST", driver);
        click(ADDED_TO_PERSONAL_PROD_LIST, "ADDED TO PERSONAL PRODUCT LIST", "Added Items to personal product list");
        if (isVisibleOnly(PPL_LABEL, "PPL label")) {
            reporter.SuccessReport("Verify personal product list page exists", "Personal product list page loaded", "");
        } else {
            reporter.failureReport("Verify personal product list page exists", "Personal product list page not loaded", "", driver);
        }
    }

    /**
     * @param partNum
     * @throws Throwable
     */
    public void verifyManufacturerPartInPersonalListPage(String partNum) throws Throwable {
        Thread.sleep(3000);
        if (isElementPresent(getMfrNumber(partNum), "Manufacturer number")) {
            reporter.SuccessReport("Verify the part number", "Product Exists and Added", "part Number : " + partNum);
        } else {
            reporter.failureReport("Verify the part number", "Product does not Exists ", "part Number : " + partNum);
        }

    }

    /**
     * This method is to add the personal product list to the cart and verify
     * it. Later deletes the personal product list.
     *
     * @param partNumber
     * @throws Throwable
     */
    public void addToCartAndVerify(String partNumber) throws Throwable {

        clickUntil(getAddToCartBtn(partNumber), getGoToCartBtn(partNumber), "Add cart button", "Add cart button");
        click(getGoToCartBtn(partNumber), "Go to cart Button", "Go to cart");
        //waitForVisibilityOfElement(ADDED_TO_CART_PPC_PART_NO, "Part number");
        cartLib.verifyCartBreadCrumb();
        String partNo = getText(ADDED_TO_CART_PPC_PART_NO, "part number in the personal product list cart");
        String actualpartNo = partNo.replaceAll("Insight Part #:", "").trim();
        List<String> prodDesc1 = orderLib.getProductDescriptionOfCartProduct();
        List<String> totalPrice1 = orderLib.getCartProductTotalPrice();
        if ((actualpartNo).equals(partNumber)) {
            reporter.SuccessReport("Verify the part added to cart ", "Part sucessfully added to cart. number is : ", "Part Number : " + partNo + "  prod Description : " + prodDesc1.get(0) + " Quantity : 1" + "Total Price: " + totalPrice1.get(0));
        } else {
            reporter.failureReport("Verify the part added to cart ", "Part is not added to cart.", "", driver);
        }

        Thread.sleep(3000);
		/*click(CONTINUE_SHOPPING_BUTTON, "Continue shopping");
		click(DELETE_BTN, "Delete button");
		Thread.sleep(1000);
		click(DELETE_BTN, "Delete button");
		isElementPresent(PART_DELETED_MSG, "Part deleted from your list", true);
		isVisible(LIST_EMPTY_MSG, "Your Personal Product List is currently empty.");*/
    }

    /**
     * @throws Throwable
     */
    public void deleteItemFromPersonalizedList() throws Throwable {
        click(DELETE_BTN, "Delete button");
    }

    /**
     * @throws Throwable
     */
    public void verifyPersonalizedListEmpyMessagePresent() throws Throwable {
        isElementPresent(PART_DELETED_MSG, "Part deleted from your list", true);
        if (isVisible(LIST_EMPTY_MSG, "Your Personal Product List is currently empty.")) {
            reporter.SuccessReport("Verify PP list empty", "Personal Product List is currently empty", "");
        } else {
            reporter.failureReport("Verify PP list empty", "Verify PP list is not empty", "", driver);
        }
    }

    /**
     * This method is to verify the contract name in the product display page
     * and add product to cart.
     *
     * @param contractName
     * @param quantity
     * @throws Throwable
     */
    public void verifyContractNameInProdDetailsPageAndAddToCart(String contractName, String quantity) throws Throwable {
        String expectedContractName = getText(SELECTED_CONTRACT, "Selected contract").replace("Contract – ", "")
                .replace("...", "");
        click(productsDisplayInfoObj.FIRST_PROD_NAME, "First product in search results page");
        isElementPresent(PROD_DETAILS_PAGE_CONTRACT_NAME, "PROD DETAILS PAGE CONTRACT NAME");
        String actualContractName = getText(PROD_DETAILS_PAGE_CONTRACT_NAME, "PRODUCT DETAILS PAGE CONTRACT NAME");
        if (actualContractName.contains(expectedContractName)) {
            reporter.SuccessReport("Verify the contract name", " Contract name verified successfully ", "");
        } else {
            reporter.failureReport("Verify the contract name", " Contract name not displayed correctly", "");
        }
        typeOnly(QUANTITY, quantity, "quantity");
        commonLib.addToCartAndVerify();
        click(CartObj.CONTINUE_TO_SHOPPING, "continue shoping");
    }

    /**
     * @param contractName
     * @throws Throwable
     */
    public void verifyContractInCartScreen(String contractName) throws Throwable {
        String actualcontractName = getText(CART_CONTRACT_NAME, "contract name");
        if (contractName.contains(actualcontractName)) {
            reporter.SuccessReport("Verify the contract name", " Contract name verified successfully in cart page and is same as selected", actualcontractName);
        } else {
            reporter.failureReport("Verify the contract name", " Contract name not displayed correctly in cart page", actualcontractName, driver);
        }
    }

    /**
     * Method is to verify YOUR PRICE in product search results
     *
     * @throws Throwable
     */
    public void verifyYourPriceExists() throws Throwable {
        if (isElementPresent(YOUR_PRICE_ON_PRODUCTS_LIST, "Your price in the product list page")) {
            String yourPrice = getText(YOUR_PRICE_ON_PRODUCTS_LIST, "Your price ");
            reporter.SuccessReport("Verify your price displayed", " Price Shows 'Your Price' in Product Results Page  ", "YourPrice: " + yourPrice);
        } else {
            reporter.failureReport("Verify your price displayed", " Your price is not displayed", "", driver);
        }
    }

    /**
     * This method is to verify the mfr number displayed in the product details
     * page and cart are same
     *
     * @throws Throwable
     */
    public void selectFirstProductAddToCartAndVerifyCart() throws Throwable {

        cartLib.selectFirstProductDisplay();
        waitForVisibilityOfElement(MFR_NUMBER_PRODUCT_DETAILS_PAGE, "Mfr number");
        String prodMfrNumber = getText(MFR_NUMBER_PRODUCT_DETAILS_PAGE, "MFR_NUMBER_PRODUCT_DETAILS_PAGE")
                .replace("\"", "").replace("Mfr. #", "").trim();
        commonLib.addToCartAndVerify();
        orderLib.continueToCheckOutOnAddCart();
        canadaLib.verifyPlaceCartLabel();
        cartLib.verifyItemInCartByInsightPart(prodMfrNumber);
    }


    /**
     * Method is to verify the stock number for the displayed search results
     *
     * @throws Throwable
     */
    public void verifyProductStockNumberInSearchResultsPage() throws Throwable {

        Thread.sleep(2000);
        if (isElementPresent(STOCK_IN_SEARCH_RESULTS, "stock in search results")) {
            reporter.SuccessReport("Verify stock number in search results page", "Stock or call for availability is present in sarch results page", "In stock or call for availability");
            List<WebElement> myList = driver.findElements(STOCK_IN_SEARCH_RESULTS);
            for (i = 0; i < myList.size(); i++) {
                String stockNumber = getText(getProductStockNumber(i), "Stock Number");
                String productName = getText(productsDisplayInfoObj.getproductName(i), "Get product name");
                if (stockNumber.isEmpty() || stockNumber == null) {
                    reporter.failureReport("Verify the In-Stock or Call for Availability displayed", "Stok number is empty or null for " + i + " product", "", driver);
                } else {
                    reporter.SuccessReport("Verify the In-Stock or Call for Availability for the products displayed", "Stock number is displayed for " + (i + 1) + " Product as :" + stockNumber, "Link: " + stockNumber);
                    reporter.SuccessReport("In-Stock or Call for Availability link for product", "Product Stock' Link is Displayed for the product Name: ", "Product Nmae: " + productName + "product Stock  : " + stockNumber);
                    break;
                }
            }
        } else {
            reporter.failureReport("verify stock or call for availability ", "Stock or call for availability is not present", "Stock or call for availability ", driver);
        }

    }

    /**
     * Method is to get the stock number of first product in search results page.
     *
     * @return
     * @throws Throwable
     */
    public String getStockNumebrOfFirstProductInSearchResults() throws Throwable {
        return getText(STOCK_NUMBER_OF_FIRST_PROD, "Stock number");
    }

    /**
     * Method is to verify the stock number in first product in search results page and product details page
     *
     * @param stockNumber
     * @throws Throwable
     */
    public void verifyStockNumberInProductDetailsPage(String stockNumber) throws Throwable {
        Thread.sleep(2000);
        String actualStockNumber = getText(STOCK_NUMBER_IN_PROD_DETAIL, "stock number").replace("Stock", "").trim();

        if (actualStockNumber.equals(stockNumber)) {
            reporter.SuccessReport("Verify the stock number in the product details page", "Stock Availability Exists and Same as in Product details Page", "Stock Availability : " + stockNumber);
        } else {
            reporter.failureReport("Verify the stock number in the product details page", "Stock Availability verification is not successful", "", driver);
        }
    }

    /**
     * This method is to verify the stock availability in the company standards screen.
     *
     * @throws Throwable
     */
    public void verifyStockNumberInCompanyStandardsProductGroup() throws Throwable {
        if (isVisible(STOCK_AVAILABILITY_IN_COMPANY_STANDARDS, "stock availability link")) {
            List<WebElement> myList = driver.findElements(ADD_ITEMS_CHECKBOX);
            List<WebElement> stockLinks = driver.findElements(STOCK_AVAILABILITY_IN_COMPANY_STANDARDS);
            for (int j = 0; j < myList.size(); j++) {
                String text = stockLinks.get(j).getText();
                if (text.isEmpty() || text == null) {
                    reporter.failureReport("verify stock link in company standards", "Stock link is not present", "", driver);
                } else {
                    // Do nothing
                }
            }
            reporter.SuccessReport("Products in Products Configuration Section on Product Standards Page", "Availability information in Stock Column Exists", "No: of Products Having Stock Availability information: " + myList.size());
        }else {
        	reporter.failureReport("Verify stock in company standards", "Stock field is not available in company standards", "", driver);
        }
    }

    /**
     * Method is to Verify Inventory Blowout in Technology deals page
     *
     * @throws Throwable
     */
    public void verifyInventoryBlowOutInTechnologyDealsPage() throws Throwable {
        List<WebElement> myList = driver.findElements(FEATURED_TECH_DEALS_PRODUCTS);
        List<WebElement> inventory_Blowout = driver.findElements(INVENTORY_BLOWOUT_LABEL);
        for (i = 0; i < myList.size(); i++) {
            if (inventory_Blowout.get(i).isDisplayed()) {
                reporter.SuccessReport("verify inventory blow out label in Featured tech deals",
                        "Blowout label exists in Technology deals page", "Inventory Blow out");
                break;
            } else {
                reporter.failureReport("verify inventory blow out label in Featured tech deals",
                        "inventory blow out label is not present", "Inventory BlowOut");
            }
        }
    }

    /**
     * This method is to click on the see details button of the product in Technology deals page and verify Inventory BlowOut label
     *
     * @throws Throwable
     */
    public void clickSeeDetailsVerifyInventoryBlowOutOfProductDetails() throws Throwable {
        click(PRODUCT_SEE_DETAILS_BTN, "see details button");
        if (isVisible(INVENTORY_BLOWOUT_LABEL, "Inventory blow out")) {
            reporter.SuccessReport("verify inventory blow out label  on products page",
                    "inventory	Blowout label exists on search results page", "Inventory BlowOut");
        } else {
            reporter.failureReport("verify inventory blow out label  on products page",
                    "inventory 	Blowout label exists on search results page", "", driver);
        }
    }

    /**
     * This method is to verify whether correct manufacturer number displayed on the product details page.
     *
     * @param mnfNumber
     * @throws Throwable
     */
    public String verifyTheManufacturerNumberInProductDetailsPage(String mnfNumber) throws Throwable {
        String prodMfrNumber = getText(MFR_NUMBER_PRODUCT_DETAILS_PAGE, "MFR_NUMBER_PRODUCT_DETAILS_PAGE")
                .replace("\"", "").replace("Mfr. #", "").trim();
        if (mnfNumber.contains(prodMfrNumber)) {
            reporter.SuccessReport("Verify manufacturer number " + mnfNumber + "in product details page", "Mfr Part# Exists and is same as searched result", "Mfr Part# " + mnfNumber);
        } else {
            reporter.failureReport("Verify manufacturer number in product details page", "Manufacturer number is not displayed correctly", mnfNumber, driver);
        }
        return prodMfrNumber;
    }

    public void deleteSelectedProducts() throws Throwable {
        List<WebElement> deleteIcon = driver.findElements(deleteProductInfo);
        List<WebElement> prodDetails = driver.findElements(ProductCompleteDetailsInViewCart);
        for (int i = 0; i < deleteIcon.size(); i++) {
            deleteIcon.get(i).click();
            reporter.SuccessReport("Delete product", "Deleted product", prodDetails.get(i).getText() + "Selected product was deleted successfully");

        }
        if (isVisibleOnly(emptyShoppingCart, "Empty cart")) {
            reporter.SuccessReport("Empty Cart", " All the products are deleted", getText(emptyShoppingCart, "Empty cart"));
        }else {
        	reporter.failureReport("Empty Cart", " All the products are not deleted from cart","",driver);
        }
    }

    public void getProductManfNumber(String mfn) throws Throwable {

        List<WebElement> mylist = driver.findElements(MFR_NUMBER_Cart_DETAILS_PAGE);
        List<WebElement> prodDetails = driver.findElements(ProductCompleteDetailsInViewCart);
        List<WebElement> prodPrice = driver.findElements(productTotalPrice);
        for (int i = 0; i < mylist.size(); i++) {
            //String mfName = getText(MFR_NUMBER_Cart_DETAILS_PAGE, "EWR Fees");
            String mfName = mylist.get(i).getText();
            String prodName = prodDetails.get(i).getText();
            String prodPriceVal = prodPrice.get(i).getText();
            reporter.SuccessReport("Product Manufacturer ", "Selected item is added to cart", prodName + " and its total price value is " + prodPriceVal);
        }
    }

    public void getSummaryCartDetails() throws Throwable {
        reporter.SuccessReport("Cart Summary details ", "Selected product summary details are ", getText(viewSummaryDetails, "Summary Details"));
    }

    public void enterQuantityForProductsInViewCartPage(String data) throws Throwable {
        //driver.findElement(txtQuanityNumberInWarrentyPage).clear();
		/*clearData(txtQuanityNumberInWarrentyPage);
		type(txtQuanityNumberInWarrentyPage,data,"Quanity");*/

        clearData(productsDisplayInfoObj.txtQuanityNumberInWarrentyPage);
        type(productsDisplayInfoObj.txtQuanityNumberInWarrentyPage, data, "Quantity");

    }

    public String getManfNumberFromWarrentiesPage(String index) throws Throwable {
        return getText(MFR_NUMBER_warrenty_PAGE(index), "Manufacturer Number");
    }

    /**
     * Method is to verify the manufacturer number in the overview tab of product details page.
     *
     * @param mfrNumber
     * @throws Throwable
     */
    public void verifyManufacturerNumberInOverviewTab(String mfrNumber) throws Throwable {
        String actualmfrnum = getText(MNR_NUM_OVERVIEW, "overview tab mfr number").trim();
        if (mfrNumber.equals(actualmfrnum)) {
            reporter.SuccessReport("Verify manufacturer number in product details page overview tab", "Manufacturer number displayed in overview tab", "Mfr Part# :" + mfrNumber);
        } else {
            reporter.failureReport("Verify manufacturer number in product details page overview tab", "Manufacturer number is not displayed correctly.expected is : " + mfrNumber + " Actual Mfr number is  :" + actualmfrnum, mfrNumber, driver);
        }
    }

    /**
     * Method is to verify the part number and description in the company standards screen.
     *
     * @throws Throwable
     */
    public void verifyProductDescAndPartNumberInCompanyStandards() throws Throwable {
        List<WebElement> desc = driver.findElements(By.xpath("//table[@id='prodGroupTable']//td//div//a"));
        List<WebElement> partNum = driver.findElements(By.xpath("//table[@id='prodGroupTable']//td//div"));
        for (i = 0; i < desc.size(); i++) {
            if (desc.get(i).getText().isEmpty() || partNum.get(i).getText().isEmpty()) {
                reporter.failureReport("Verify part number and description in company standards product group", "Part number or discription is empty", "", driver);
            } else {
                // do nothing
            }
        }
        reporter.SuccessReport("Verify part number and description in company standards product group", "Product Description and Part Number Exists ", "No: of Products Description Having Mrf Parts # : " + partNum.size());
    }

    /**
     * Method is to verify the images present in the search results page
     *
     * @throws Throwable
     */
    public String verifyProductImage() throws Throwable {
        String actualImgSrc = null;
        List<WebElement> myList = driver.findElements(LIST_OF_ITEMS_SEARCH_RESULTS);
        for (int i = 0; i < myList.size(); i++) {
            Thread.sleep(2000);
            actualImgSrc = getAttributeBySrc(productsDisplayInfoObj.getproductImage(i), "Getting Src");
            String productName = getText(productsDisplayInfoObj.getproductName(i), "Product name ");
            if (!actualImgSrc.isEmpty() || !actualImgSrc.contains("noImageAvailable")) {
                reporter.SuccessReport("Verify image in search results page", "Image displayed in search results page", "");
                click(productsDisplayInfoObj.getproductImage(i), "Product image : " + actualImgSrc + "   Product name: " + productName, "Product image : " + actualImgSrc + "Product name: " + productName);
                break;
            } else {
                reporter.failureReport("Verify image in search results page", "Image is not displayed search results page", "", driver);
            }
        }
        return actualImgSrc;
    }

    /**
     * This method is to verify the image present in the product details page.s
     *
     * @throws Throwable
     */
    public void verifyFrontImageInProductDetailsPage() throws Throwable {
        String prodImage = getAttributeBySrc(IMG_PRODUCT_DETAILS_FRONT, "getting image src");
        if (prodImage.isEmpty() || prodImage.contains("noImageAvailable")) {

            reporter.failureReport("Verify the product Image", "Product image is not displayed", "", driver);
        } else {
            reporter.SuccessReport("Verify the product Image", "Product that has an available Image Exists", "Image Src on product details page: " + prodImage);
        }
    }

    /**
     * Method is to click on Back to results in the product details page
     *
     * @throws Throwable
     */
    public void backToResultsProductDetailsPage() throws Throwable {
        click(productsDisplayInfoObj.BACK_TO_RESULTS, "Back to results link");
    }


    /**
     * This method is to verify the image present in the product details page.s
     *
     * @throws Throwable
     */
    public void verifyLeftAngleImageInProductDetailsPage() throws Throwable {
        String prodImage = getAttributeBySrc(IMG_LEFT_ANGLE, "getting image src");
        if (prodImage.isEmpty() || prodImage.contains("noImageAvailable")) {

            reporter.failureReport("Verify the product Image", "Product image is not displayed", "", driver);
        } else {
            reporter.SuccessReport("Verify the product Image", "Product image is displayed as ", "Image name : " + prodImage);
        }
    }

    /**
     * This method is to verify the recently viewed products
     *
     * @param imgSrc
     * @throws Throwable
     */
    public void verifyRecentlyViewedProductsImage(String imgSrc) throws Throwable {
        waitForVisibilityOfElement(getRecentlyViewedProductImage(imgSrc), "img src");
        if (isElementPresent(getRecentlyViewedProductImage(imgSrc), "image src")) {
            reporter.SuccessReport("Verify the product Image in recently viewed products", "Product image is displayed in recently viewed products", imgSrc);
        } else {
            reporter.failureReport("Verify the product Image in recently viewed products", "Product image is not displayed in recently viewed products", imgSrc, driver);
        }
    }

    /**
     * This method is to verify the List prices available in the Search results page
     *
     * @throws Throwable
     */
    public void verifyTheProductPricesInSearchResultsPage() throws Throwable {
        List<WebElement> myList = driver.findElements(LIST_OF_ITEMS_SEARCH_RESULTS);
        for (int i = 0; i < myList.size(); i++) {
            String price = getText(productsDisplayInfoObj.getProductPrice(i), "get product price");
            System.out.println(price);
            if (!price.isEmpty()) {
                reporter.SuccessReport("Verify the product price", "Product list Price exists and  displayed as: ", price);
                break;
            } else {
                reporter.failureReport("Verify the product price", "Product price is not displayed", "", driver);
            }
        }
    }

    /**
     * Method is to get the price of the first product in the search results page
     *
     * @return
     * @throws Throwable
     */
    public String getFirtProductListPrice() throws Throwable {
        String prodPrice = null;
        if (isElementVisible(FIRST_PRODUCT_PRICE, 3, "Product price")) {
            prodPrice = getText(FIRST_PRODUCT_PRICE, "List price");
            reporter.SuccessReport("Verify the List Price on Search Results Page", "Product list Price exists", "Price for 1st Product: " + prodPrice);
        } else {
            reporter.failureReport("Verify the List Price on Search Results Page", "Product price does not exists", prodPrice, driver);
        }
        return prodPrice;


    }

    /**
     * Method is to verify the list price in product details page
     *
     * @param Actualprice
     * @throws Throwable
     */
    public void verifyThePriceInProdDetailsPage(String Actualprice) throws Throwable {
        String price = getText(PRICE_IN_PROD_DETAILS, "Product detail price");
        Thread.sleep(3000);
        if (Actualprice.equals(price)) {
            reporter.SuccessReport("Verify the product price", "Product price is displayed in the detail page and is same as product search results page.Price is :  ", price);
        } else {
            reporter.failureReport("Verify the product price", "Product price is not displayed or not same as product search results page.", price, driver);
        }
    }

    /**
     * Method is to get the first product quantity in the search results page
     *
     * @return
     * @throws Throwable
     */
    public String getFirstProdQuantity() throws Throwable {

        String quantity = getAttributeByValue(QUANTITY_FIRST_PROD, "First prod quantity");
        reporter.SuccessReport("Verify the Quantity on Product details page", "Quantity exists", quantity);
        return quantity;
    }

    /**
     * method is to verify the quantity in product details page
     *
     * @param actaulQty
     * @throws Throwable
     */
    public void verifyQuantityInProdDetailsPage(String actaulQty) throws Throwable {
        String quantity = getAttributeByValue(QUANTITY, "quantity");
        if (quantity.equals(actaulQty)) {
            reporter.SuccessReport("Verify the product quantity", "Product quantity Exists and same as search results page", "Quantity : " + actaulQty);
        } else {
            reporter.failureReport("Verify the product quantity", "Product quantity is not displayed correctly as search results pages", "", driver);
        }
    }

    /**
     * Method is to click on Add to cart button in Product details page
     *
     * @throws Throwable
     */
    public void addToCartInProductDetailsPage() throws Throwable {
        click(CartObj.ADD_TO_CART_IN_PRODUCT_DISPLAY, " ADD TO CART IN PRODUCT DISPLAY");
    }

    /**
     * Method to verify Your price in product details page
     *
     * @throws Throwable
     */
    public void verifyYourPriceInProductDetailsPage() throws Throwable {
        String yourPrice = getText(productsDisplayInfoObj.YOUR_PRICE_ON_PROD_DETAILS, "Your price");
        if (isElementPresent(productsDisplayInfoObj.YOUR_PRICE_ON_PROD_DETAILS, "Your price")) {
            reporter.SuccessReport("Verify the product your price in product details", "Your price is displayed as: ", "Your price: " + yourPrice);
        } else {
            reporter.failureReport("Verify the product your price in product details", "Your price is not displayed correctly", "", driver);
        }
    }

    /**
     * Method is to click on warranties tab in product details page
     *
     * @throws Throwable
     */
    public void clickOnWarrantiesTabOnProductDetailsPage() throws Throwable {

        if(assertTrue(isVisibleOnly(WARRANTIES_PROD_DETAILS,"Warrentites Tab"),"Avilability of Warrienties")) {
            click(WARRANTIES_PROD_DETAILS, "warranties");
        }
    }

    public void clickOnAddToCartButtonInWarrentiesPage(String index) throws Throwable {
        click(btnAddToCartinWarrentiesPage(index), "Add to cart", "Clicked on " + index + " Add to cart button");
    }

    /**
     * Method is to check the price in the warranties tab.
     *
     * @throws Throwable
     */
    public void verifyPriceInWarrantiesTab() throws Throwable {
        if (isElementPresent(PRICE_IN_WARRANTIES_PROD_DETAILS, "price in warranties tab")) {
            String warrantyPrice = getText(PRICE_IN_WARRANTIES_PROD_DETAILS, "Warranty price");
            reporter.SuccessReport("Verify price in warranties tab", "Price displayed in warranties tab", "warranty price : " + warrantyPrice);
        } else {
            reporter.failureReport("Verify price in warranties tab", "Price is not displayed in warranties tab", "", driver);
        }
    }

    /**
     * Method is to verify Open market PRICE in product search results
     *
     * @throws Throwable
     */
    public void verifyOpenMarketPriceExists() throws Throwable {
        if (isElementPresent(OPEN_MARKET_PRICE_PRODUCT_LIST, "Open market price in the product list page")) {
            String openMarketPrice = getText(OPEN_MARKET_PRICE_PRODUCT_LIST, "Open market price ");
            reporter.SuccessReport("Verify your price displayed", " Open market price is displayed ", "Openmarket price :" + openMarketPrice);
        } else {
            reporter.failureReport("Verify your price displayed", " Open market price is displayed", "", driver);
        }
    }

    /**
     * Method to verify Open market price in product details page
     *
     * @throws Throwable
     */
    public void verifyOpenMarketPriceInProductDetailsPage() throws Throwable {
        String openaMarketPrice = getText(productsDisplayInfoObj.OPEN_MARKET_PRICE_ON_PROD_DETAILS, "Open market price");
        if (isElementPresent(productsDisplayInfoObj.OPEN_MARKET_PRICE_ON_PROD_DETAILS, "Open market price")) {
            reporter.SuccessReport("Verify the product Open market price in product details", "Open market price is displayed as: " + openaMarketPrice, "");
        } else {
            reporter.failureReport("Verify the product Open market price in product details", "Open market price is not displayed correctly", "", driver);
        }
    }

    /**
     * Method is to test the US contract present in the search results page.
     *
     * @throws Throwable
     */
    public void verifyUSDContractPricePresentInSearchResults() throws Throwable {
        String contractLabel = getText(productsDisplayInfoObj.DEFAULT_CONTRACT_LABEL_PROD_LIST, "DEFAULT CONTRACT LABEL PRODUCT LIST");
        if (isElementPresent(productsDisplayInfoObj.DEFAULT_USC_LABEL_PROD_LIST, "USC label")) {

            reporter.SuccessReport("Verify the default contracts displayed in product search page first product ", "USC contract price is displayed by default for the product.Displayed contract is: " + contractLabel, contractLabel);
        } else {
            reporter.failureReport("Verify the default contracts displayed in product search page first product ", "USC is displayed by default" + contractLabel, "", driver);
        }
    }

    /**
     * Method is to check the mfr part in the warranties tab.
     *
     * @throws Throwable
     */
    public void verifyMfrpartInWarrantiesTab() throws Throwable {
        if (isElementPresent(MFR_PART_WARRANTIES_TAB, "price in warranties tab")) {
            List<WebElement> warranties = driver.findElements(MFR_PART_WARRANTIES_TAB);
            int size = warranties.size();
            reporter.SuccessReport("Verify Mfr part in warranties tab", "Mfr part displayed in warranties tab", "No of Warranties Having Mrf Parts # :" + size);
        } else {
            reporter.failureReport("Verify Mfr part in warranties tab", "Mfr part is not displayed in warranties tab", "", driver);
        }
    }


    /**
     * Method is to click on warranties tab in product details page
     *
     * @throws Throwable
     */
    public void clickOnAccessoriesTabInProductDetailsPage() throws Throwable {
        if (isVisibleOnly(ACCESSORIES_PROD_DETAILS, "Accessories tab")) {
            click(ACCESSORIES_PROD_DETAILS, "Accessories");
        } else {
            reporter.failureReport("verify Accessories tab", "Accessories tab is not present in product details page", "", driver);
        }

    }

    /**
     * Method is to verify the accessories mfr part in the product details page.
     *
     * @throws Throwable
     */
    public void verifyMfrpartInAccessorirs() throws Throwable {
        if (isElementPresent(MFR_NUM_ACCESSORIES, "Part in accessories tab")) {
            List<WebElement> accessories = driver.findElements(MFR_NUM_ACCESSORIES);
            int size = accessories.size();
            reporter.SuccessReport("Verify Mfr part in Accessories tab", "Mfr part displayed in Accessories tab", "No of Products having Mfr Part#s: " + size);
        } else {
            reporter.failureReport("Verify Mfr part in Accessories tab", "Mfr part is not displayed in Accessories tab", "", driver);
        }
    }

    /**
     * This method is to verify the short description in the product details page matching with the search results page
     *
     * @throws Throwable
     */

    public void verifyShortDescriptionOnProductDetailsPage(String actualDesc) throws Throwable {
        waitForVisibilityOfElement(PRODUCT_NAME, "Product name");
        String productName = getText(PRODUCT_NAME, "Product  name - product details page");
        if (isElementPresent(PRODUCT_NAME, "Product displayed") && !productName.isEmpty() && productName.contains(actualDesc)) {
            reporter.SuccessReport("Verify the Short description on Product Details Page", "Product Short description exists.", "Short Description is : " + productName);
        } else {
            reporter.failureReport("Verify the Short description on Product Details Page", "Product Short description does not exist or is empty", "", driver);
        }
    }

    /**
     * Method is to get the first product Description in the search results page
     *
     * @return
     * @throws Throwable
     */
    public String getFirstProdDescription() throws Throwable {
        String prodDesc = null;
        waitForVisibilityOfElement(FIRST_PROD_NAME, "First product description");
        if (isElementVisible(FIRST_PROD_NAME, 3, "First product description")) {
            prodDesc = getText(FIRST_PROD_NAME, "First prod Description");
            reporter.SuccessReport("verify description exists", "Product description exists", prodDesc);
        } else {
            reporter.failureReport("verify description exists", "Product description does not exists", prodDesc, driver);
        }

        return prodDesc;
    }

    /**
     * Method is to verify the Long description in product details page
     *
     * @throws Throwable
     */
    public void verifyLongDescOnProductDetails() throws Throwable {
        String longDesc = getText(LONG_DESC_PROD_DETAILS, "Product long desc");
        if (isElementPresent(LONG_DESC_PROD_DETAILS, "Product long description") && !longDesc.isEmpty()) {
            reporter.SuccessReport("Verify the Long description on Product Details Page", "Product Long description exists", " Long Description is : " + longDesc);
        } else {
            reporter.failureReport("Verify the Long description on Product Details Page", "Product Long description does not exist or is empty", "", driver);
        }
    }

    /**
     * Method is to verify the short description in the accessories tab.
     *
     * @throws Throwable
     */
    public void verifyShortDescOnAccessoriesTab() throws Throwable {
        String shortDesc = getText(ACCESSORIES_DESC, "Accessories short description");
        if (isElementPresent(ACCESSORIES_DESC, "Accessories short description") && !shortDesc.isEmpty()) {
            reporter.SuccessReport("Verify prod short description of Accessorries Tab on Product Details Page", "Short description of Accessorries Tab is present", "");
        } else {
            reporter.failureReport("Verify prod short description of Accessorries Tab on Product Details Page", "Short description of Accessorries Tab is not present", "", driver);
        }
    }

    /**
     * Method is to verify the long description on the Accessories tab
     *
     * @throws Throwable
     */
    public void verifyProductDescriptionOnAccessoriesTab() throws Throwable {
        click(SEE_MORE_LINK, "See more link");
        String longDesc = getText(LONG_DEC_ACCESSORIES, "Accessories Long description");
        if (isElementPresent(LONG_DEC_ACCESSORIES, "Accessories Long description") && !longDesc.isEmpty()) {
            reporter.SuccessReport("Verify product description of Accessorries Tab on Product Details Page", "Long description of Accessorries Tab is present", "Product: " + longDesc);
        } else {
            reporter.failureReport("Verify prduct description of Accessorries Tab on Product Details Page", "Long description of Accessorries Tab is not present", longDesc, driver);
        }
    }

    /**
     * Method is to verify the description of most often purchased item s in product details tab
     *
     * @throws Throwable
     */
    public void verifyProductDescForMostOftenPurchasedItems() throws Throwable {
        if (isVisible(MOST_OFTEN_PURCHASED_ITEM_LABEL, "MOST_OFTEN_PURCHASED_ITEM_LABEL")) {
            Thread.sleep(3000);
            List<WebElement> elements = driver.findElements(MOST_OFTEN_PURCHASED_ITEM_DESC);
            for (i = 0; i < elements.size(); i++) {
                String Desc = elements.get(i).getText();
                if (Desc.isEmpty()) {
                    reporter.failureReport("Verify prod description of most often purchased items", "product description of most often purchased item is not present", "", driver);
                } else {
                    reporter.SuccessReport("Verify prod description of most often purchased items", "Most often purchased products exists.product description of most often purchased item is present", "Short desc of product: " + Desc);
                }
            }
        } else {
            reporter.failureReport("verify most offen purchased items exists", "verify most offen purchased items label does not exists", "", driver);
        }

    }

    public void verifyProductDescForPeopelWithSimilarInterestBought() throws Throwable {

        Thread.sleep(3000);
        List<WebElement> elements = driver.findElements(PEOPLE_WITH_SIMILAR_INTEREST_BROUGHT);
        for (i = 0; i < elements.size(); i++) {
            String Desc = elements.get(i).getText();
            if (Desc.isEmpty()) {
                reporter.failureReport("Verify prod description of most often purchased items", "product description of most often purchased item is not present", "", driver);
            } else {
                reporter.SuccessReport("Verify prod description of most often purchased items", "Most often purchased products exists.product description of most often purchased item is present", "Short desc of product: " + Desc);
            }
        }
    }

    public void verifyProductDescForPopularItems() throws Throwable {

        Thread.sleep(3000);
        List<WebElement> elements = driver.findElements(POPULAR_ITEMS);
        for (i = 0; i < elements.size(); i++) {
            String Desc = elements.get(i).getText();
            if (Desc.isEmpty()) {
                reporter.failureReport("Verify prod description of most often purchased items", "product description of most often purchased item is not present", "", driver);
            } else {
                reporter.SuccessReport("Verify prod description of most often purchased items", "Most often purchased products exists.product description of most often purchased item is present", "Short desc of product: " + Desc);
            }
        }
    }


    /**
     * Method is to select new contract
     *
     * @param contractName
     * @throws Throwable
     */
    public void selectNewcontract(String contractName) throws Throwable {
        if (isElementPresent(CONTRACT_DD, "Contarcat all drop down")) {
            clickUntil(CONTRACT_DD, getContractsFromDD(contractName), "contract drop down");
            click(getContractsFromDD(contractName), "Contract name : " + contractName);
        } else {
            reporter.failureReport("Verify contract all drop down exists", "Contract all drop down does not exists", "Contract all link", driver);
        }

    }

    /**
     * Method is to get the product display info bread crumb
     *
     * @return
     * @throws Throwable
     */
    public String getProductDisplayInfoBreadCrumb() throws Throwable {
        return getText(PROD_DISPLAY_INFO_BREAD_CRUMB, "product display info bread crumb");

    }

    /**
     * Method is to change the first product quantity
     *
     * @param qty
     * @throws Throwable
     */
    public void changeFirstProductQuantity(String qty) throws Throwable {

        if (isElementPresent(QUANTITY_FIRST_PROD, "First prod quantity")) {
            type(QUANTITY_FIRST_PROD, qty, "Quantity");
        } else {
            reporter.failureReport("verify Quantity present", "Quantity input is not present", "", driver);
        }
    }

    /**
     * @param contractName
     * @throws Throwable
     */
    public void getSelectedContract(String contractName) throws Throwable {
        if (isElementPresent(EndUserFeaturesObj.getContractOnHomePage(contractName), "Contract on Home page")) {
            reporter.SuccessReport("Verify selected contract ", "Selected contract verified successfully", contractName);
        } else {
            reporter.failureReport("Verify selected contract ", "Selected contract not verified.", contractName, driver);
        }
    }

    /**
     * Method is to verify the manufacturer name in search results
     *
     * @param manufacturer
     * @throws Throwable
     */
    public void verifyManufacturerNameInsearchResults(String manufacturer) throws Throwable {

        List<WebElement> myList = driver.findElements(LIST_OF_ITEMS_SEARCH_RESULTS);
        for (int i = 0; i < myList.size(); i++) {
            String productName = getText(productsDisplayInfoObj.getproductName(i), "Get product name");

            if (!productName.isEmpty() && productName.contains(manufacturer)) {
                reporter.SuccessReport("Verify the product name", "Product name is displayed as: ", productName);
            } else {
                reporter.failureReport("Verify the product name", "Product name is not displayed", "");
            }
        }
    }

    /**
     * To get the part number of first product in search results page
     *
     * @return
     * @throws Throwable
     */
    public String getPartNumberInSearchResultsPage() throws Throwable {
        String partNumber = getText(productsDisplayInfoObj.getPartNumber(0), "get product number");
        System.out.println(partNumber);
        if (!partNumber.isEmpty()) {
            reporter.SuccessReport("Verify the product part Number", "Product part Number is displayed as : ",
                    "part Number # : " + partNumber);
        } else {
            reporter.failureReport("Verify the product part Number", "Product part Number is not displayed", "", driver);
        }
        return partNumber;
    }
    
    
    public String getPartNumberExactlyInSearchResultsPage() throws Throwable {
        String partNumber = getText(productsDisplayInfoObj.getPartNumber(0), "get product number").split("Mfr Part #:")[1];
        System.out.println(partNumber);
        if (!partNumber.isEmpty()) {
            reporter.SuccessReport("Verify the product part Number", "Product part Number in search results page is displayed as : ",
                    "part Number # : " + partNumber);
        } else {
            reporter.failureReport("Verify the product part Number", "Product part Number is not displayed", "", driver);
        }
        return partNumber;
    }
    
    public String getSecondPartNumberInSearchResultsPage() throws Throwable {
        String partNumber = getText(productsDisplayInfoObj.getPartNumber(1), "get product number").split("Mfr Part #:")[1];
        System.out.println(partNumber);
        if (!partNumber.isEmpty()) {
            reporter.SuccessReport("Verify the product part Number", "Product part Number is displayed as : ",
                    "part Number # : " + partNumber);
        } else {
            reporter.failureReport("Verify the product part Number", "Product part Number is not displayed", "", driver);
        }
        return partNumber;
    }

    /**
     * Method is to verify the welcome page
     *
     * @throws Throwable
     */
    public void verifyWelcomePage() throws Throwable {
        waitForVisibilityOfElement(ShipBillPayObj.WELCOMEMSG_DASHBOARD, "Welcome to Insight");
        if (isVisibleOnly(ShipBillPayObj.WELCOMEMSG_DASHBOARD, "welcome dashboard")) {
            reporter.SuccessReport("Verify welcome page ", "Welcome page is visible", "Welcome Home page");
        } else {
            reporter.failureReport("Verify welcome page", "Welcome page is not visible", "", driver);
        }
    }

    /**
     * Verify master product label
     *
     * @throws Throwable
     */
    public void verifyMasterProductLabelExists() throws Throwable {

        if (isVisibleOnly(MASTER_PRODUCT_LABEL, "Master product label")) {
            reporter.SuccessReport("Verify Master Product in Compare Similar Products Page ", "Master Product Column Exists", "");
        } else {
            reporter.failureReport("Verify Master Product in Compare Similar Products Page ", "Master Product Column does not Exists", "", driver);
        }
    }

    /**
     * Verify master product label
     *
     * @throws Throwable
     */
    public void verifySimilarProductLabelExists() throws Throwable {
        if (isVisibleOnly(SIMILAR_PRODUCTS_COLUMN, "Similar product label")) {
            reporter.SuccessReport("Similar Products in Compare Similar Products Page ", "Similar Products Column Exists", "");
        } else {
            reporter.failureReport("Verify Similar Product in Compare Similar Products Page ", "Similar Products Column does not Exists", "", driver);
        }
    }

    public void verifyCartPageAndPartDetails() throws Throwable {
        List<String> prodDesc1 = orderLib.getProductDescriptionOfCartProduct();
        List<String> totalPrice1 = orderLib.getCartProductTotalPrice();
        List<String> unitPrice1 = orderLib.getCartProductUnitPrice();
        List<String> quantity = orderLib.getCartProductQuantity();
        List<String> stock = orderLib.getCartProductStock();
        List<String> currency = orderLib.getCurrencyTypeOfCartProduct();

        if (prodDesc1.get(0) != null && totalPrice1 != null) {
            reporter.SuccessReport("Verify the part added to cart ", "Part added to cart and cart details are: ",
                    "  prod Description : " + prodDesc1.get(0) + " Quantity : " + quantity
                            + "Total Price: " + currency.get(0) + " " + totalPrice1.get(0) + " Unit price: " + currency.get(0) + " " + unitPrice1 + "Stock :" + stock);
        } else {
            reporter.failureReport("Verify the part added to cart ", "Part is not added to cart.", "", driver);
        }
    }


    public void contractNameOfFirstproduct() throws Throwable {
        Thread.sleep(3000);
        if (isVisibleOnly(CONTRACT_IN_SEARCH_RESULTS, "contract in search results")) {
            String contract = getText(CONTRACT_IN_SEARCH_RESULTS, "contract");
            if (contract.startsWith("US ")) {
                reporter.failureReport("Contract of the first part in Search Result Exists and Value Returned", "USD is default contract", "", driver);
            } else {
                reporter.SuccessReport("Contract of the first part in Search Result Exists and Value Returned", "Contract of the first part in Search Result Exists and Value Returned", "contract of first product: " + contract);
            }

        } else {
            reporter.failureReport("Contract of the first part in Search Result Exists not viible", "Contract not visisble", "", driver);
        }
    }

    /**
     * Method is to verify the open market price in search results page
     *
     * @throws Throwable
     */
    public void verifyOpenMarketOnSearchResultsPage() throws Throwable {
        if (isVisibleOnly(LIST_OF_ITEMS_SEARCH_RESULTS, "search results")) {
            List<WebElement> myList = driver.findElements(LIST_OF_ITEMS_SEARCH_RESULTS);
            for (int i = 0; i < myList.size(); i++) {
                String partNumber = getText(productsDisplayInfoObj.getPartNumber(i), "get product number");
                String openMarketLabel = getText(openMarketLabelSearchResults(i), "open market");
                String price = getText(productsDisplayInfoObj.getProductPrice(i), "get product price");
                if (!partNumber.isEmpty() && openMarketLabel.equals("Open Market Price")) {
                    reporter.SuccessReport("Verify Open Market Price", "Open Market Price exists", partNumber + ", " + openMarketLabel + "Price : " + price);
                } else {
                    reporter.failureReport("Verify Open Market Price", "Open Market Price does not exists", "", driver);
                }
            }
        }
    }

    /**
     * Method is to enter quantity
     *
     * @param quantity
     * @throws Throwable
     */
    public void enterQuantityOnProductDetailsPage(String quantity) throws Throwable {
        if (isVisibleOnly(QUANTITY, "QUANTITY")) {
            typeOnly(QUANTITY, quantity, "quantity");
        } else {
            reporter.failureReport("verify quantity exists", "Quantity field does not exists", "", driver);
        }

    }

    public void verifyCartPageAndPartDetailsForRecentlyItem() throws Throwable {
        String prodDesc1 = orderLib.getProductDescriptionOfCartProductForRecentlyAddedItem();
        String totalPrice1 = orderLib.getCartProductTotalPriceForRecentlyAddedItem();
        String unitPrice1 = orderLib.getCartProductUnitPriceForRecentlyAddedItem();
        String quantity = orderLib.getCartProductQuantityForRecentlyAddedItem();
        String stock = orderLib.getCartProductStockForRecentlyAddedItem();
        if (prodDesc1 != null && totalPrice1 != null) {
            reporter.SuccessReport("Verify the part added to cart ", "Part added to cart and cart details are: ",
                    "  prod Description : " + prodDesc1 + " Quantity : " + quantity
                            + "Total Price: " + totalPrice1 + " Unit price: " + unitPrice1 + "Stock :" + stock);
        } else {
            reporter.failureReport("Verify the part added to cart ", "Part is not added to cart.", "", driver);
        }
    }


    public void verifyCartPageAndPartDetailsForRecentlyItemDynamically(String prodcut) throws Throwable {
        List<WebElement> stockList = null;
        List<WebElement> DecList = driver.findElements(CartObj.CART_PROD_DESC_RECENTLYADDEDTEM_loop);
        List<WebElement> priceList = driver.findElements(CartObj.CART_PROD_TOTAL_PRICE_RECENTLYADDEDTEM_loop);
        List<WebElement> UnitPriceList = driver.findElements(CartObj.CART_PROD_UNIT_PRICE_RECENTLYADDEDTEM_loop1);
        List<WebElement> qtyList = driver.findElements(CartObj.CART_PROD_QTY_RECENTLYADDEDTEM_loop);
        if (isVisibleOnly(CartObj.CART_PROD_STOCK_RECENTLYADDEDTEM_loop, "Stock")) {
            stockList = driver.findElements(CartObj.CART_PROD_STOCK_RECENTLYADDEDTEM_loop);
        }
        List<WebElement> partNum = driver.findElements(CartObj.Cart_Prod_Insight_Part_Number);

        for (int i = 0; i < DecList.size(); i++) {
        	System.out.println("i"+i);
            if (partNum.get(i).getText().contains(prodcut) || DecList.get(i).getText().contains(prodcut)) {
                if (DecList.get(i).isDisplayed()) {
                    reporter.SuccessReport("Product Description  ", "Product Description is for " + partNum.get(i).getText() + " is ", DecList.get(i).getText());
                }
                if (priceList.get(i).isDisplayed()) {
                    reporter.SuccessReport("Total Price List", "Total Price is  " + partNum.get(i).getText() + " is ", priceList.get(i).getText());
                }
                if (UnitPriceList.get(i).isDisplayed()) {
                    reporter.SuccessReport("Unit Price ", "Unit price is " + partNum.get(i).getText() + " is ", UnitPriceList.get(i).getText());
                }
                if (qtyList.get(i).isDisplayed()) {
                    reporter.SuccessReport("Quantity List", "Quantity List is " + partNum.get(i).getText() + " is ", qtyList.get(i).getText());
                }
                if (stockList.get(i) != null || stockList.get(i).isDisplayed()) {
                    /*if (stockList.get(i).isDisplayed()) {*/
                    reporter.SuccessReport("Stock List", "Stock List is " + partNum.get(i).getText() + " is ", "Available and the value is " +  stockList.get(i).getText());
                    /*}*/
                    reporter.SuccessReport("Product details are ", "Product details are  " + partNum.get(i).getText() + " is ", DecList.get(i).getText()+ "<b>Quantity</b>  "+qtyList.get(i).getText() + "<b>Price</b>  "+priceList.get(i).getText() + UnitPriceList.get(i).getText() + stockList.get(i).getText());

                }else {
                    reporter.SuccessReport("Product details are ", "Product details are " + partNum.get(i).getText() + " is ", DecList.get(i).getText() + qtyList.get(i).getText() + priceList.get(i).getText() + UnitPriceList.get(i).getText());
                }
            }

        }

    }

    public void verifyQuantity() throws Throwable {
        List<String> prodDesc1 = orderLib.getProductDescriptionOfCartProduct();
        List<String> totalPrice1 = orderLib.getCartProductTotalPrice();
        List<String> unitPrice1 = orderLib.getCartProductUnitPrice();
        List<String> quantity = orderLib.getCartProductQuantity();
        List<String> stock = orderLib.getCartProductStock();
        List<String> currency = orderLib.getCurrencyTypeOfCartProduct();

        if (prodDesc1.get(0) != null && totalPrice1 != null) {
            reporter.SuccessReport("Verify the part added to cart ", "Part added to cart and cart details are: ",
                    "  prod Description : " + prodDesc1.get(0) + " Quantity : " + quantity
                            + "Total Price: " + currency.get(0) + " " + totalPrice1.get(0) + " Unit price: " + currency.get(0) + " " + unitPrice1 + "Stock :" + stock);
        } else {
            reporter.failureReport("Verify the part added to cart ", "Part is not added to cart.", "", driver);
        }
    }
    public void verifyCartPageAndPartDetailsForRecentlyItemDynamicaly(String prodcut) throws Throwable {
        List<WebElement> stockList = null;
        List<WebElement> DecList = driver.findElements(CartObj.CART_PROD_DESC_RECENTLYADDEDTEM_loop);
        List<WebElement> priceList = driver.findElements(CartObj.CART_PROD_TOTAL_PRICE_RECENTLYADDEDTEM_loop);
        List<WebElement> UnitPriceList = driver.findElements(CartObj.CART_PROD_UNIT_PRICE_RECENTLYADDEDTEM_loop1);
        List<WebElement> qtyList = driver.findElements(CartObj.CART_PROD_QTY_RECENTLYADDEDTEM_loop);
        if (isVisibleOnly(CartObj.CART_PROD_STOCK_RECENTLYADDEDTEM_loop, "Stock")) {
            stockList = driver.findElements(CartObj.CART_PROD_STOCK_RECENTLYADDEDTEM_loop);
        }
        List<WebElement> partNum = driver.findElements(CartObj.Cart_Prod_Insight_Part_Number);

        for (int i = 0; i < DecList.size(); i++) {
        	System.out.println("i"+i);
            if (partNum.get(i).getText().contains(prodcut) || DecList.get(i).getText().contains(prodcut)) {
                if (DecList.get(i).isDisplayed()) {
                    reporter.SuccessReport("Product Description  ", "Product Description is for " + partNum.get(i).getText() + " is ", DecList.get(i).getText());
                }
                if (priceList.get(i).isDisplayed()) {
                    reporter.SuccessReport("Total Price List", "Total Price is  " + partNum.get(i).getText() + " is ", priceList.get(i).getText());
                }
                if (UnitPriceList.get(i).isDisplayed()) {
                    reporter.SuccessReport("Unit Price ", "Unit price is " + partNum.get(i).getText() + " is ", UnitPriceList.get(i).getText());
                }
                if (qtyList.get(i).isDisplayed()) {
                    reporter.SuccessReport("Quantity List", "Quantity List is " + partNum.get(i).getText() + " is ", qtyList.get(i).getAttribute("value"));
                }
                if (stockList.get(i) != null) {
                    /*if (stockList.get(i).isDisplayed()) {*/
                    reporter.SuccessReport("Stock List", "Stock List is " + partNum.get(i).getText() + " is ", "Available and the value is " +  stockList.get(i).getText());
                    /*}*/
                    reporter.SuccessReport("Product description ", "Total product description " + partNum.get(i).getText() + " is ", DecList.get(i).getText()+ "<b>Quantity</b>  "+qtyList.get(i).getText() + "<b>Price</b>  "+priceList.get(i).getText() + UnitPriceList.get(i).getText() + stockList.get(i).getText());

                }else {
                    reporter.SuccessReport("Product description ", "Total product description " + partNum.get(i).getText() + " is ", DecList.get(i).getText() + qtyList.get(i).getText() + priceList.get(i).getText() + UnitPriceList.get(i).getText());
                }
            }

        }

    }

    public void verifyContract2InCartScreen(String contractName) throws Throwable {
        String actualcontractName = getText(CART_CONTRACT_NAME2, "contract name");
        if (contractName.contains(actualcontractName)) {
            reporter.SuccessReport("Verify the contract name", " Contract name verified successfully in cart page and is same as selected", actualcontractName);
        } else {
            reporter.failureReport("Verify the contract name", " Contract name not displayed correctly in cart page", actualcontractName, driver);
        }
    }

}
  
	

