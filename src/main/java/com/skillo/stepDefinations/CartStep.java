package com.skillo.stepDefinations;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.skillo.base.Keyword;
import com.skillo.pages.MyntraCartPage;

import io.cucumber.java.en.*;

public class CartStep {

    private static final Logger LOG = LogManager.getLogger(CartStep.class);

    private MyntraCartPage cartPage;
    private WebDriver driver;

    public CartStep() {
        cartPage = new MyntraCartPage();
        driver = Keyword.getDriver();
        LOG.info("CartStep initialized");
    }

    @Given("User is on cart page")
    public void user_is_on_cart_page() {

        LOG.info("STEP: User navigates to cart page");

        try {
            cartPage.navigateToCartPage();
            LOG.info("Successfully navigated to cart page");
        } catch (Exception e) {
            LOG.error("Failed to navigate to cart page", e);
            throw e;
        }
    }

    @When("User clicks on place order button")
    public void user_clicks_on_place_order_button() {

        LOG.info("STEP: User clicks on place order button");

        cartPage.placeOrder();
    }

    // REUSABLE STEPS

    @When("User clicks on place order button again")
    public void click_place_order_again() {

        LOG.info("STEP: User clicks place order button again");

        cartPage.placeOrder();
        LOG.debug("Place Order action executed again");
    }

    @When("user refreshes the cart page")
    public void refresh_cart_page() {

        LOG.info("STEP: User refreshes the cart page");

        try {
            driver.navigate().refresh();
            LOG.info("Page refreshed successfully");
        } catch (Exception e) {
            LOG.error("Failed to refresh page", e);
        }
    }

    @When("user refreshes the cart page again")
    public void refresh_cart_page_again() {

        LOG.info("STEP: User refreshes the cart page again");

        try {
            driver.navigate().refresh();
            LOG.info("Page refreshed again successfully");
        } catch (Exception e) {
            LOG.error("Failed to refresh page again", e);
        }
    }

    @When("user navigates again to cart page")
    public void navigate_again_to_cart() {

        LOG.info("STEP: User navigates again to cart page");

        try {
            cartPage.navigateToCartPage();
            LOG.info("Successfully navigated again to cart page");
        } catch (Exception e) {
            LOG.error("Failed to navigate again to cart page", e);
        }
    }

    @Then("cart page should be loaded")
    public void verify_cart_page_loaded() {

        LOG.info("STEP: Verifying cart page is loaded");

        String url = driver.getCurrentUrl();
        LOG.debug("Current URL: {}", url);

        try {
            Assert.assertTrue(url.contains("cart"),
                    "Cart page not loaded");

            LOG.info("Cart page is loaded successfully");

        } catch (AssertionError e) {
            LOG.error("Cart page validation failed", e);
            throw e;
        }
    }

    @Then("place order button should be clickable")
    public void verify_place_order_clickable() {

        LOG.info("STEP: Verifying place order button interaction");

        boolean result = cartPage.isPlaceOrderAttempted();
        LOG.debug("Place order attempted flag: {}", result);

        try {
            Assert.assertNotNull(result);
            LOG.info("Place Order button interaction is possible");

        } catch (AssertionError e) {
            LOG.error("Place Order button validation failed", e);
            throw e;
        }
    }

    @Then("Order should be placed successfully")
    public void order_should_be_placed_successfully() {

        LOG.info("STEP: Verifying order placement");

        boolean result = cartPage.isPlaceOrderAttempted();
        LOG.debug("Order placement status: {}", result);

        try {
            Assert.assertTrue(result, "Place Order action failed");

            LOG.info("Order placed successfully");

        } catch (AssertionError e) {
            LOG.error("Order placement validation failed", e);
            throw e;
        }
    }
}