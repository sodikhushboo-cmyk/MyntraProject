package com.skillo.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.skillo.base.*;
import com.skillo.pages.HomePage;

public class HomePageTest extends BaseClass {

    @Test
    public void verifyTopwearNavigation() {

        HomePage home = new HomePage();

        home.navigateToTopwear();

        Assert.assertTrue(home.isTopwearPageDisplayed(),
                "Topwear page is not displayed");
    }

    @Test
    public void verifyFiltersOnTopwear() {

        HomePage home = new HomePage();

        home.navigateToTopwear();
        home.applyFilters();

        Assert.assertTrue(home.isProductsDisplayed(),
                "Filtered products not displayed");
    }

    @Test
    public void verifyOpenProductFromHome() {

        HomePage home = new HomePage();

        home.navigateToTopwear();
        home.openFirstProduct();

        Assert.assertTrue(home.isProductPageDisplayed(),
                "Product page not opened");
    }
}
