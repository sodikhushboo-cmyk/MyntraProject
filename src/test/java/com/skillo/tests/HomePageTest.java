package com.skillo.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.skillo.base.BaseClass;
import com.skillo.pages.HomePage;

public class HomePageTest extends BaseClass {

    private HomePage homePage;

    // ✅ 1. Navigate to Topwear
    @Test(priority = 1)
    public void verifyNavigateToTopwear() {

        homePage = new HomePage();

        homePage.navigateToTopwear();

        Assert.assertTrue(
                homePage.isTopwearPageDisplayed(),
                "Topwear page not displayed"
        );
    }

    // ✅ 2. Apply Filters
    @Test(priority = 2)
    public void verifyApplyFilters() {

        homePage = new HomePage();

        homePage.navigateToTopwear();
        homePage.applyFilters();

        Assert.assertTrue(
                homePage.isProductsDisplayed(),
                "Filtered products not displayed"
        );
    }

    // ✅ 3. Open Product
    @Test(priority = 3)
    public void verifyOpenProduct() {

        homePage = new HomePage();

        homePage.navigateToTopwear();
        homePage.openFirstProduct();

        Assert.assertTrue(
                homePage.isProductPageDisplayed(),
                "Product page not displayed"
        );
    }

    // ✅ 4. Navigate Multiple Times
    @Test(priority = 4)
    public void verifyMultipleNavigationToTopwear() {

        homePage = new HomePage();

        homePage.navigateToTopwear();
        homePage.navigateToTopwear();

        Assert.assertTrue(
                homePage.isTopwearPageDisplayed(),
                "Topwear page not displayed after multiple navigation"
        );
    }

    // ✅ 5. Products Visible After Navigation
    @Test(priority = 5)
    public void verifyProductsVisibleAfterNavigation() {

        homePage = new HomePage();

        homePage.navigateToTopwear();

        Assert.assertTrue(
                homePage.isProductsDisplayed(),
                "Products not visible after navigation"
        );
    }

    // ✅ 6. Filter Does Not Break Navigation
    @Test(priority = 6)
    public void verifyFilterDoesNotBreakNavigation() {

        homePage = new HomePage();

        homePage.navigateToTopwear();
        homePage.applyFilters();
        homePage.navigateToTopwear();

        Assert.assertTrue(
                homePage.isTopwearPageDisplayed(),
                "Navigation failed after applying filters"
        );
    }

    // ❌ 7. Repeated Product Click Handling (Edge Case)
    @Test(priority = 7)
    public void verifyRepeatedProductClickHandling() {

        homePage = new HomePage();

        homePage.navigateToTopwear();
        homePage.openFirstProduct();
        homePage.openFirstProduct();

        Assert.assertTrue(
                homePage.isProductPageDisplayed(),
                "Product page not handled correctly on repeated click"
        );
    }
}