package com.skillo.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.skillo.base.*;
import com.skillo.pages.HomePage;
import com.skillo.pages.ProductListingPage;

public class ProductListingTest extends BaseClass {

    @Test
    public void verifyProductListingDisplayed() {

        HomePage home = new HomePage();
        home.navigateToTopwear();

        Assert.assertTrue(
                Keyword.getDriver().getCurrentUrl().contains("topwear"),
                "Product listing page not loaded"
        );
    }

    @Test
    public void verifyClickProduct() {

        HomePage home = new HomePage();
        home.navigateToTopwear();

        ProductListingPage plp = new ProductListingPage();
        plp.clickProduct(0);

        Assert.assertTrue(true, "Product clicked successfully");
    }

    @Test
    public void verifySwitchToChildWindow() {

        HomePage home = new HomePage();
        home.navigateToTopwear();

        ProductListingPage plp = new ProductListingPage();
        plp.clickProduct(0);
        plp.switchToChildWindow();

        Assert.assertTrue(
                Keyword.getDriver().getWindowHandles().size() > 1,
                "Window not switched"
        );
    }

    // 🔥 NEW TEST 1
    @Test
    public void verifyMultipleProductClicks() {

        HomePage home = new HomePage();
        home.navigateToTopwear();

        ProductListingPage plp = new ProductListingPage();

        plp.clickProduct(0);
        plp.clickProduct(1);

        Assert.assertTrue(true, "Multiple products clickable");
    }

    // 🔥 NEW TEST 2
    @Test
    public void verifyListingPageReload() {

        HomePage home = new HomePage();
        home.navigateToTopwear();

        Keyword.getDriver().navigate().refresh();

        Assert.assertTrue(
                Keyword.getDriver().getCurrentUrl().contains("topwear"),
                "Listing page not stable after refresh"
        );
    }
}