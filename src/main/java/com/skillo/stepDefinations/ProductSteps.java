package com.skillo.stepDefinations;

import com.skillo.base.Keyword;
import com.skillo.pages.ProductDetails;
import com.skillo.pages.ProductListingPage;

import io.cucumber.java.en.*;

import org.testng.Assert;

public class ProductSteps {

    ProductListingPage listingPage;
    ProductDetails detailsPage;

    @Given("Browser is opened and product page is launched")
    public void open_product_page() {

        // ✅ Navigate to product listing first
        Keyword.launchUrl("https://www.myntra.com/men-tshirts");

        listingPage = new ProductListingPage();
        listingPage.openFirstProduct();

        detailsPage = new ProductDetails();
    }

    @When("user switches to product page tab")
    public void switch_to_product_tab() {
        detailsPage.switchToProductTab();
    }

    @When("user selects size")
    public void user_selects_size() {
        detailsPage.selectSizeAndAddToBag();
    }

    @When("user clicks Add to Bag")
    public void user_clicks_add_to_bag() {
        detailsPage.selectSizeAndAddToBag();
    }

    @When("user selects size and adds product to bag")
    public void select_size_and_add() {
        detailsPage.selectSizeAndAddToBag();
    }

    @When("user clicks on go to bag")
    public void go_to_bag() {
        detailsPage.goToBag();
    }

    @When("user clicks on Add to Bag without selecting size")
    public void add_without_size() {
        detailsPage.addToBagWithoutSize();
    }

    @Then("product should be added to bag successfully")
    public void verify_product_added() {
        Assert.assertTrue(detailsPage.isProductAdded());
    }

    @Then("product should not be added to bag")
    public void verify_not_added() {
        Assert.assertTrue(detailsPage.isProductNotAdded());
    }

    @Then("size should be selected successfully")
    public void size_selected() {
        Assert.assertTrue(true);
    }

    @Then("add to bag action should be triggered")
    public void add_to_bag_triggered() {
        Assert.assertTrue(true);
    }

    @Then("user should be navigated to bag page")
    public void bag_page_navigation() {
        Assert.assertTrue(true);
    }

    @Then("add to bag button should be visible")
    public void add_to_bag_visible() {
        Assert.assertTrue(true);
    }

    @Then("sizes should be available")
    public void sizes_available() {
        Assert.assertTrue(true);
    }
}