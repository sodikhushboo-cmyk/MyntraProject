package com.skillo.stepDefinations;

import com.skillo.base.Keyword;
import com.skillo.pages.ProductDetails;
import com.skillo.pages.ProductListingPage;

import io.cucumber.java.en.*;

import org.testng.Assert;

public class ProductSteps {

    private ProductListingPage listingPage;
    private ProductDetails detailsPage;

    // ================= GIVEN =================

    @Given("Browser is opened and product page is launched")
    public void open_product_page() {

        Keyword.launchUrl("https://www.myntra.com/men-tshirts");

        listingPage = new ProductListingPage();
        listingPage.openFirstProduct();

        detailsPage = new ProductDetails();
    }

    // ================= WHEN =================

    @When("user switches to product page tab")
    public void switch_to_product_tab() {
        detailsPage.switchToProductTab();
    }

    @When("user selects size")
    public void user_selects_size() {
        detailsPage.selectSize();   // ✅ FIXED
    }

    @When("user clicks Add to Bag")
    public void user_clicks_add_to_bag() {
        detailsPage.clickaddToBagProduct();   // ✅ FIXED
    }

    @When("user selects size and adds product to bag")
    public void select_size_and_add() {
        detailsPage.selectSize();
        detailsPage.clickaddToBagProduct();   // ✅ FIXED
    }

    @When("user clicks on go to bag")
    public void go_to_bag() {
        detailsPage.goToBag();
    }

    @When("user clicks on Add to Bag without selecting size")
    public void add_without_size() {
        detailsPage.addToBagWithoutSize();
    }

    // ================= THEN =================

    @Then("product should be added to bag successfully")
    public void verify_product_added() {
        Assert.assertTrue(
                detailsPage.isProductAdded(),
                "❌ Product was not added to bag"
        );
    }

    @Then("product should not be added to bag")
    public void verify_not_added() {
        Assert.assertTrue(
                detailsPage.isProductNotAdded(),
                "❌ Product should NOT be added"
        );
    }

    @Then("size should be selected successfully")
    public void size_selected() {
        Assert.assertTrue(
                detailsPage.isSizeOptionsAvailable(),
                "❌ Size not selected / not available"
        );
    }

    @Then("add to bag action should be triggered")
    public void add_to_bag_triggered() {
        Assert.assertTrue(
                detailsPage.isGotoBagIsVisible(),
                "❌ Add to bag action failed"
        );
    }

    @Then("user should be navigated to bag page")
    public void bag_page_navigation() {
        Assert.assertTrue(
                Keyword.getDriver().getCurrentUrl().contains("cart"),
                "❌ Not navigated to cart page"
        );
    }

    @Then("add to bag button should be visible")
    public void add_to_bag_visible() {
        Assert.assertTrue(
                detailsPage.isAddToBagVisible(),
                "❌ Add to Bag button not visible"
        );
    }

    @Then("sizes should be available")
    public void sizes_available() {
        Assert.assertTrue(
                detailsPage.isSizeOptionsAvailable(),
                "❌ Sizes not available"
        );
    }
}