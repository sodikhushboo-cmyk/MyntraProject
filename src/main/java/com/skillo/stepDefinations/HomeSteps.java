package com.skillo.stepDefinations;

import org.testng.Assert;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.skillo.pages.HomePage;

import io.cucumber.java.en.*;

public class HomeSteps {

    private static final Logger LOG = LogManager.getLogger(HomeSteps.class);

    private HomePage homePage;

    // ✅ COMMON INIT (SAFE)
    private void initHomePage() {
        if (homePage == null) {
            homePage = new HomePage();
            LOG.debug("HomePage initialized inside step");
        }
    }

    @Given("Browser is opened and home page is launched")
    public void openBrowserAndHomePage() {

        LOG.info("STEP: Browser opened and home page launched");

        homePage = new HomePage(); // Hooks already opened browser
    }

    // ✅ SINGLE SEARCH STEP (ONLY HERE)
    @When("user search for the Various {string}")
    public void searchProduct(String product) {

        LOG.info("STEP: Searching product: {}", product);

        initHomePage();

        homePage.enterTextOnSearchBar(product);
        homePage.enterPressOnSearchBar();
    }

    // ✅ SEARCH RESULT VALIDATION (IMPORTANT ADD)
    @Then("user should see the result for the valid product")
    public void verify_search_result() {

        initHomePage();

        Assert.assertTrue(
                homePage.isProductsDisplayed(),
                "Search results not displayed"
        );
    }

    // ================= OTHER STEPS =================

    @When("user navigates to Topwear section")
    public void user_navigates_to_topwear() {
        initHomePage();
        homePage.navigateToTopwear();
    }

    @When("user applies filters")
    public void user_applies_filters() {
        initHomePage();
        homePage.applyFilters();
    }

    @When("user opens first product")
    public void user_opens_first_product() {
        initHomePage();
        homePage.openFirstProduct();
    }

    @When("user navigates again to Topwear section")
    public void user_navigates_again_to_topwear() {
        initHomePage();
        homePage.navigateToTopwear();
    }

    @When("user opens first product again")
    public void user_opens_product_again() {
        initHomePage();
        homePage.openFirstProduct();
    }

    @When("user applies filters again")
    public void user_applies_filters_again() {
        initHomePage();
        homePage.applyFilters();
    }

    @Then("Topwear page should be displayed")
    public void verify_topwear_page() {
        initHomePage();
        Assert.assertTrue(homePage.isTopwearPageDisplayed(),
                "Topwear page not displayed");
    }

    @Then("filtered products should be displayed")
    public void verify_filtered_products() {
        initHomePage();
        Assert.assertTrue(homePage.isProductsDisplayed(),
                "Filtered products not displayed");
    }

    @Then("product page should be displayed")
    public void verify_product_page() {
        initHomePage();
        Assert.assertTrue(homePage.isProductPageDisplayed(),
                "Product page not opened");
    }

    @Then("products should still be visible")
    public void products_should_be_visible() {

        Assert.assertTrue(false, "❌ Intentional Failure: Products not visible");
    }
}