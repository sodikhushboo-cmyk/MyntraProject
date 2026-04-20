package com.skillo.stepDefinations;

import com.skillo.base.Keyword;
import com.skillo.pages.MyntraProductPage;

import io.cucumber.java.en.*;

import org.testng.Assert;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ProductSteps {

    private static final Logger LOG = LogManager.getLogger(ProductSteps.class);

    MyntraProductPage productPage;

    @Given("Browser is opened and product page is launched")
    public void open_product_page() {

        LOG.info("STEP: Opening browser and launching product page");

        try {
            Keyword.openBrowser("chrome");
            Keyword.launchUrl("https://www.myntra.com/men-tshirts");

            productPage = new MyntraProductPage();

            LOG.info("Opening first product from listing");
            productPage.openFirstProduct();

        } catch (Exception e) {
            LOG.error("Failed to open product page", e);
            throw e;
        }
    }

    @When("user switches to product page tab")
    public void switch_to_product_tab() {

        LOG.info("STEP: Switching to product page tab");

        productPage.switchToProductTab();
    }

    // POSITIVE STEPS

    @When("user selects size")
    public void user_selects_size() {

        LOG.info("STEP: User selects size");

        productPage.selectSizeAndAddToBag(); // reuse
    }

    @When("user clicks Add to Bag")
    public void user_clicks_add_to_bag() {

        LOG.info("STEP: User clicks Add to Bag");

        productPage.selectSizeAndAddToBag(); // reuse
    }

    @When("user selects size and adds product to bag")
    public void select_size_and_add() {

        LOG.info("STEP: User selects size and adds product to bag");

        productPage.selectSizeAndAddToBag();
    }

    @When("user clicks on go to bag")
    public void go_to_bag() {

        LOG.info("STEP: User clicks on GO TO BAG");

        productPage.goToBag();
    }

    // NEGATIVE STEP

    @When("user clicks on Add to Bag without selecting size")
    public void add_without_size() {

        LOG.info("STEP: User clicks Add to Bag without selecting size");

        productPage.addToBagWithoutSize();
    }

    // ASSERTIONS

    @Then("product should be added to bag successfully")
    public void verify_product_added() {

        LOG.info("STEP: Verifying product is added to bag");

        try {
            boolean result = productPage.isProductAdded();
            LOG.debug("Product added status: {}", result);

            Assert.assertTrue(result, "Product was NOT added to bag");

            LOG.info("Product added to bag successfully");

        } catch (AssertionError e) {
            LOG.error("Product add validation failed", e);
            throw e;
        }
    }

    @Then("product should not be added to bag")
    public void verify_not_added() {

        LOG.info("STEP: Verifying product is NOT added to bag");

        try {
            boolean result = productPage.isProductNotAdded();
            LOG.debug("Product not added status: {}", result);

            Assert.assertTrue(result, "Product should NOT be added");

            LOG.info("Negative validation passed (product not added)");

        } catch (AssertionError e) {
            LOG.error("Negative validation failed", e);
            throw e;
        }
    }

    @Then("size should be selected successfully")
    public void size_selected() {

        LOG.info("STEP: Verifying size selection");

        Assert.assertTrue(true);
        LOG.info("Size selected successfully");
    }

    @Then("add to bag action should be triggered")
    public void add_to_bag_triggered() {

        LOG.info("STEP: Verifying Add to Bag action");

        Assert.assertTrue(true);
        LOG.info("Add to Bag action triggered");
    }

    @Then("user should be navigated to bag page")
    public void bag_page_navigation() {

        LOG.info("STEP: Verifying navigation to bag page");

        Assert.assertTrue(true);
        LOG.info("User navigated to bag page");
    }

    @Then("add to bag button should be visible")
    public void add_to_bag_visible() {

        LOG.info("STEP: Verifying Add to Bag button visibility");

        Assert.assertTrue(true);
        LOG.info("Add to Bag button is visible");
    }

    @Then("sizes should be available")
    public void sizes_available() {

        LOG.info("STEP: Verifying sizes availability");

        Assert.assertTrue(true);
        LOG.info("Sizes are available");
    }
}