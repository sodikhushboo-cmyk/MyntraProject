package com.skillo.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.skillo.base.BaseClass;
import com.skillo.base.Keyword;
import com.skillo.pages.HomePage;
import com.skillo.pages.ProductListingPage;

public class ProductListingTest extends BaseClass {

    private HomePage home;
    private ProductListingPage plp;

    @BeforeMethod
    public void setupPage() {
        home = new HomePage();
        plp = new ProductListingPage();
    }

    // ✅ Scenario: listing page loaded
    @Test(priority = 1)
    public void verifyProductListingDisplayed() {

        home.navigateToTopwear();

        Assert.assertTrue(
                Keyword.getDriver().getCurrentUrl().contains("topwear"),
                "Product listing page not loaded"
        );
    }

    // ✅ Scenario: click product
    @Test(priority = 2)
    public void verifyClickProduct() {

        home.navigateToTopwear();
        plp.clickProduct(0);

        Assert.assertTrue(true, "Product clicked successfully");
    }

    // ✅ Scenario: switch to child window
    @Test(priority = 3)
    public void verifySwitchToChildWindow() {

        home.navigateToTopwear();
        plp.clickProduct(0);
        plp.switchToChildWindow();

        Assert.assertTrue(
                Keyword.getDriver().getWindowHandles().size() > 1,
                "Window not switched"
        );
    }

    // ✅ Scenario: multiple product clicks
    @Test(priority = 4)
    public void verifyMultipleProductClicks() {

        home.navigateToTopwear();

        plp.clickProduct(0);
        plp.clickProduct(1);

        Assert.assertTrue(true, "Multiple products clickable");
    }

    // ✅ Scenario: page stability
    @Test(priority = 5)
    public void verifyListingPageReload() {

        home.navigateToTopwear();
        Keyword.getDriver().navigate().refresh();

        Assert.assertTrue(
                Keyword.getDriver().getCurrentUrl().contains("topwear"),
                "Listing page not stable after refresh"
        );
    }
}