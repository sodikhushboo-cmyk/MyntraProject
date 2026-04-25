package com.skillo.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.skillo.base.BaseClass;
import com.skillo.base.Keyword;
import com.skillo.pages.AddToCartPage;
import com.skillo.pages.HomePage;
import com.skillo.pages.ProductDetails;
import com.skillo.pages.ProductListingPage;

public class CartPageTest extends BaseClass {

    private HomePage home;
    private ProductListingPage plp;
    private ProductDetails pdp;
    private AddToCartPage cart;

    private static final String PRODUCT = "t-shirt";

    @BeforeMethod
    public void setupPages() {
        home = new HomePage();
        plp = new ProductListingPage();
        pdp = new ProductDetails();
        cart = new AddToCartPage();
    }

    // 🔁 COMMON FLOW → reach cart page
    private void navigateToCartPage() {

        home.enterTextOnSearchBar(PRODUCT);
        home.enterPressOnSearchBar();

        plp.clickProduct(0);
        plp.switchToChildWindow();

        pdp.selectSize(); // ✅ important fix
        pdp.clickaddToBagProduct();
        pdp.goToBag();
    }

    // ================= CORE =================

    @Test(priority = 1, groups = "smoke")
    public void verifyCartPageLoaded() {

        navigateToCartPage();

        Assert.assertTrue(
                cart.isCartPageDisplayed(),
                "❌ Cart page not loaded"
        );
    }

    // ================= FUNCTIONAL =================

    @Test(priority = 2, groups = "smoke")
    public void verifyProductAddedToCart() {

        SoftAssert softly = new SoftAssert();

        navigateToCartPage();

        softly.assertTrue(
                cart.isCartPageDisplayed(),
                "Cart page not displayed"
        );

        softly.assertAll();
    }

    // ================= DATA VALIDATION =================

    @Test(priority = 3, groups = "regression")
    public void verifyProductDetailsInCart() {

        SoftAssert softly = new SoftAssert();

        home.enterTextOnSearchBar(PRODUCT);
        home.enterPressOnSearchBar();

        // Expected data
        String expectedBrand = plp.getProductBrand(0);
        int expectedPrice = plp.getProductPrice(0);

        plp.clickProduct(0);
        plp.switchToChildWindow();

        pdp.selectSize();
        pdp.clickaddToBagProduct();
        pdp.goToBag();

        // Actual data
        String actualBrand = cart.getProductBrand();
        int actualPrice = cart.getPriceOfProduct();

        softly.assertEquals(actualBrand, expectedBrand, "Brand mismatch");
        softly.assertEquals(actualPrice, expectedPrice, "Price mismatch");

        softly.assertAll();
    }

    // ================= STABILITY =================

    @Test(priority = 4, groups = "regression")
    public void verifyOnlyNavigation() {

        navigateToCartPage();

        Keyword.getDriver().navigate().back();
        Keyword.getDriver().navigate().forward();

        Assert.assertTrue(
                cart.isCartPageDisplayed(),
                "❌ Cart page unstable after navigation"
        );
    }

    @Test(priority = 5, groups = "regression")
    public void verifyOnlyRefresh() {

        navigateToCartPage();

        Keyword.getDriver().navigate().refresh();

        Assert.assertTrue(
                cart.isCartPageDisplayed(),
                "❌ Cart page unstable after refresh"
        );
    }
}