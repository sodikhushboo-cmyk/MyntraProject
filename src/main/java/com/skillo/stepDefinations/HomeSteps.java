package com.skillo.stepDefinations;

import org.testng.Assert;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.skillo.pages.MyntraHomePage;

import io.cucumber.java.en.*;

public class HomeSteps {

    private static final Logger LOG = LogManager.getLogger(HomeSteps.class);

    private MyntraHomePage homePage;

    @Given("Browser is opened and home page is launched")
    public void openBrowserAndHomePage() {

        LOG.info("STEP: Browser opened and home page launched");

        try {
            homePage = new MyntraHomePage(); // Driver from Hooks
            LOG.info("User is on home page successfully");
        } catch (Exception e) {
            LOG.error("Failed to initialize home page", e);
            throw e;
        }
    }

    @When("user navigates to Topwear section")
    public void user_navigates_to_topwear() {

        LOG.info("STEP: User navigates to Topwear section");

        homePage.navigateToTopwear();
    }

    @When("user applies filters")
    public void user_applies_filters() {

        LOG.info("STEP: User applies filters");

        homePage.applyFilters();
    }

    @When("user opens first product")
    public void user_opens_first_product() {

        LOG.info("STEP: User opens first product");

        homePage.openFirstProduct();
    }

    // Reusable step
    @When("user navigates again to Topwear section")
    public void user_navigates_again_to_topwear() {

        LOG.info("STEP: User navigates again to Topwear section");

        homePage.navigateToTopwear();
        LOG.debug("Repeated navigation to Topwear executed");
    }

    // Reusable step
    @When("user opens first product again")
    public void user_opens_product_again() {

        LOG.info("STEP: User opens first product again");

        homePage.openFirstProduct();
        LOG.debug("Repeated product open executed");
    }

    // Reusable step
    @When("user applies filters again")
    public void user_applies_filters_again() {

        LOG.info("STEP: User applies filters again");

        homePage.applyFilters();
        LOG.debug("Repeated filter application executed");
    }

    @Then("Topwear page should be displayed")
    public void verify_topwear_page() {

        LOG.info("STEP: Verifying Topwear page is displayed");

        try {
            boolean result = homePage.isTopwearPageDisplayed();
            LOG.debug("Topwear page validation result: {}", result);

            Assert.assertTrue(result, "Topwear page not displayed");

            LOG.info("Topwear page verified successfully");

        } catch (AssertionError e) {
            LOG.error("Topwear page validation failed", e);
            throw e;
        }
    }

    @Then("filtered products should be displayed")
    public void verify_filtered_products() {

        LOG.info("STEP: Verifying filtered products are displayed");

        try {
            boolean result = homePage.isProductsDisplayed();
            LOG.debug("Filtered products visible: {}", result);

            Assert.assertTrue(result, "Filtered products not displayed");

            LOG.info("Filtered products are visible");

        } catch (AssertionError e) {
            LOG.error("Filtered products validation failed", e);
            throw e;
        }
    }

    @Then("product page should be displayed")
    public void verify_product_page() {

        LOG.info("STEP: Verifying product page is displayed");

        try {
            boolean result = homePage.isProductPageDisplayed();
            LOG.debug("Product page displayed: {}", result);

            Assert.assertTrue(result, "Product page not opened");

            LOG.info("Product page opened successfully");

        } catch (AssertionError e) {
            LOG.error("Product page validation failed", e);
            throw e;
        }
    }

    @Then("products should still be visible")
    public void verify_products_visible() {

        LOG.info("STEP: Verifying products are still visible");

        try {
            boolean result = homePage.isProductsDisplayed();
            LOG.debug("Products visibility status: {}", result);

            Assert.assertTrue(result, "Products not visible");

            LOG.info("Products are visible");

        } catch (AssertionError e) {
            LOG.error("Products visibility validation failed", e);
            throw e;
        }
    }
}