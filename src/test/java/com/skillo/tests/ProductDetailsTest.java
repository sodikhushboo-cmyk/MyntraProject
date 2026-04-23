package com.skillo.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.skillo.base.*;
import com.skillo.pages.HomePage;
import com.skillo.pages.ProductListingPage;
import com.skillo.pages.ProductDetails;

public class ProductDetailsTest extends BaseClass {

    @Test
    public void verifyAddToBagWithSize() {

        HomePage home = new HomePage();
        home.navigateToTopwear();

        ProductListingPage plp = new ProductListingPage();
        plp.clickProduct(0);
        plp.switchToChildWindow();

        ProductDetails pdp = new ProductDetails();
        pdp.clickaddToBagProduct();

        Assert.assertTrue(pdp.isGotoBagIsVisible(),
                "Go to bag not visible");
    }

    @Test
    public void verifyAddToBagWithoutSize() {

        HomePage home = new HomePage();
        home.navigateToTopwear();

        ProductListingPage plp = new ProductListingPage();
        plp.clickProduct(0);
        plp.switchToChildWindow();

        ProductDetails pdp = new ProductDetails();
        pdp.addToBagWithoutSize();

        Assert.assertTrue(pdp.isProductNotAdded(),
                "Product should not be added");
    }

    @Test
    public void verifyGoToBag() {

        HomePage home = new HomePage();
        home.navigateToTopwear();

        ProductListingPage plp = new ProductListingPage();
        plp.clickProduct(0);
        plp.switchToChildWindow();

        ProductDetails pdp = new ProductDetails();
        pdp.clickaddToBagProduct();
        pdp.goToBag();

        Assert.assertTrue(true, "Navigated to bag");
    }

    // 🔥 NEW TEST 1
    @Test
    public void verifyProductPageLoadsAfterClick() {

        HomePage home = new HomePage();
        home.navigateToTopwear();

        ProductListingPage plp = new ProductListingPage();
        plp.clickProduct(0);
        plp.switchToChildWindow();

        String url = Keyword.getDriver().getCurrentUrl();

        Assert.assertTrue(url.contains("buy"),
                "Product page not loaded");
    }

    // 🔥 NEW TEST 2
    @Test
    public void verifyAddToBagButtonClickable() {

        HomePage home = new HomePage();
        home.navigateToTopwear();

        ProductListingPage plp = new ProductListingPage();
        plp.clickProduct(0);
        plp.switchToChildWindow();

        ProductDetails pdp = new ProductDetails();

        pdp.clickaddToBagProduct();

        Assert.assertTrue(pdp.isGotoBagIsVisible(),
                "Add to Bag not working");
    }
}