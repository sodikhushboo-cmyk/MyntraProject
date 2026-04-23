package com.skillo.tests;

import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.skillo.base.*;
import com.skillo.pages.*;

public class CartPageTest extends BaseClass {

    SoftAssert softly = new SoftAssert();

    @Test
    public void verifyProductAddedToCart() {

        HomePage home = new HomePage();
        home.enterTextOnSearchBar("t-shirt");
        home.enterPressOnSearchBar();

        ProductListingPage plp = new ProductListingPage();
        plp.clickProduct(0);
        plp.switchToChildWindow();

        ProductDetails pdp = new ProductDetails();
        pdp.clickaddToBagProduct();

        softly.assertTrue(pdp.isGotoBagIsVisible(), "Go to bag not visible");

        pdp.goToBag();

        AddToCartPage cart = new AddToCartPage();
        softly.assertTrue(cart.isProductDisplayed());

        softly.assertAll();
    }
    
    @Test
    public void verifyProductDetailsInCart() {

        SoftAssert softly = new SoftAssert();

        HomePage home = new HomePage();
        home.enterTextOnSearchBar("t-shirt");
        home.enterPressOnSearchBar();

        ProductListingPage plp = new ProductListingPage();

        // Expected data
        String expectedBrand = plp.getProductBrand(0);
        int expectedPrice = plp.getProductPrice(0);

        plp.clickProduct(0);
        plp.switchToChildWindow();

        ProductDetails pdp = new ProductDetails();
        pdp.clickaddToBagProduct();

        softly.assertTrue(
                pdp.isGotoBagIsVisible(),
                "Go to bag not visible"
        );

        pdp.goToBag();

        AddToCartPage cart = new AddToCartPage();

        // Actual data
        String actualBrand = cart.getProductBrand();
        int actualPrice = cart.getPriceOfProduct();

        // Validations
        softly.assertEquals(actualBrand, expectedBrand, "Brand mismatch");
        softly.assertEquals(actualPrice, expectedPrice, "Price mismatch");

        softly.assertAll();
    }
}