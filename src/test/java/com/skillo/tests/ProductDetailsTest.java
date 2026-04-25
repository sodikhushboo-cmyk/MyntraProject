package com.skillo.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.skillo.base.BaseClass;
import com.skillo.base.Keyword;
import com.skillo.pages.HomePage;
import com.skillo.pages.ProductDetails;
import com.skillo.pages.ProductListingPage;

public class ProductDetailsTest extends BaseClass {

    private HomePage home;
    private ProductListingPage plp;
    private ProductDetails pdp;

    @BeforeMethod
    public void setupPage() {
        home = new HomePage();
        plp = new ProductListingPage();
        pdp = new ProductDetails();
    }

    private void navigateToPDP() {
        home.navigateToTopwear();
        plp.clickProduct(0);
        plp.switchToChildWindow();
    }

    // ❌ Negative
    @Test(priority = 1)
    public void verifyAddToBagWithoutSize() {

        navigateToPDP();

        pdp.addToBagWithoutSize();

        Assert.assertTrue(pdp.isProductNotAdded());
    }

    // ✅ Select size
    @Test(priority = 2)
    public void verifySelectSize() {

        navigateToPDP();

        pdp.selectSize();

        Assert.assertTrue(pdp.isSizeOptionsAvailable());
    }

	/*
	 * // ✅ Add to bag
	 * 
	 * @Test(priority = 3) public void verifyAddToBagWithSize() {
	 * 
	 * navigateToPDP();
	 * 
	 * pdp.selectSize(); pdp.clickaddToBagProduct();
	 * 
	 * Assert.assertTrue(pdp.isGotoBagIsVisible()); }
	 */

    // ✅ Go to bag
    @Test(priority = 4)
    public void verifyGoToBag() {

        navigateToPDP();

        pdp.selectSize();
        pdp.clickaddToBagProduct();
        pdp.goToBag();

        Assert.assertTrue(
                Keyword.getDriver().getCurrentUrl().contains("cart"),
                "Navigation to cart failed"
        );
    }

    // ❌ Edge case
    @Test(priority = 5)
    public void verifyMultipleAddToBagWithoutSize() {

        navigateToPDP();

        pdp.addToBagWithoutSize();
        pdp.addToBagWithoutSize();

        Assert.assertTrue(pdp.isProductNotAdded());
    }

    // ✅ Product page
    @Test(priority = 6)
    public void verifyProductPageLoaded() {

        navigateToPDP();

        Assert.assertTrue(
                Keyword.getDriver().getCurrentUrl().contains("buy")
        );
    }

    // ✅ Button visible
    @Test(priority = 7)
    public void verifyAddToBagButtonVisible() {

        navigateToPDP();

        Assert.assertTrue(pdp.isAddToBagVisible());
    }

    // ✅ Sizes visible
    @Test(priority = 8)
    public void verifySizeOptionsAvailable() {

        navigateToPDP();

        Assert.assertTrue(pdp.isSizeOptionsAvailable());
    }
}