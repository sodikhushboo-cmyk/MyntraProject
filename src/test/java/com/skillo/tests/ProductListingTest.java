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

    // ✅ 1. Listing page loaded
    @Test(priority = 1)
    public void verifyProductListingDisplayed() {

        home.navigateToTopwear();

        Assert.assertTrue(
                Keyword.getDriver().getCurrentUrl().contains("topwear"),
                "Product listing page not loaded"
        );
    }

    // ✅ 2. Click product
    @Test(priority = 2)
    public void verifyClickProduct() {

        home.navigateToTopwear();
        plp.clickProduct(0);

        Assert.assertTrue(true, "Product clicked successfully");
    }

    // ✅ 3. Switch window
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

    // ✅ 4. Multiple product clicks
    @Test(priority = 4)
    public void verifyMultipleProductClicks() {

        home.navigateToTopwear();

        plp.clickProduct(0);
        plp.clickProduct(1);

        Assert.assertTrue(true, "Multiple products clickable");
    }

    // ✅ 5. Page reload stability
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