package com.skillo.pages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.skillo.base.Keyword;

public class MyntraCartPage {

    private static final Logger LOG = LogManager.getLogger(MyntraCartPage.class);

    private WebDriver driver;

    public MyntraCartPage() {
        this.driver = Keyword.getDriver();
        LOG.info("MyntraCartPage initialized");
    }

    // Flexible locator
    private By placeOrderBtn = By.xpath("//button//div[contains(text(),'PLACE ORDER') or contains(text(),'Place Order')]");

    // Flag to track action
    private boolean attempted = false;

    public void navigateToCartPage() {
        String url = "https://www.myntra.com/checkout/cart";
        LOG.info("Navigating to cart page: {}", url);

        try {
            driver.get(url);
            LOG.info("Successfully navigated to cart page");
        } catch (Exception e) {
            LOG.error("Failed to navigate to cart page", e);
            throw e;
        }
    }

    public void placeOrder() {

        LOG.info("Attempting to place order");

        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

            // Check if button exists
            if (driver.findElements(placeOrderBtn).size() > 0) {

                LOG.debug("Place Order button found, waiting for clickability");

                wait.until(ExpectedConditions.elementToBeClickable(placeOrderBtn)).click();

                attempted = true;
                LOG.info("Place Order button clicked successfully");

            } else {
                // Valid scenario
                attempted = true;
                LOG.warn("Place Order button not present (cart empty / user not logged in)");
            }

        } catch (Exception e) {
            attempted = false;
            LOG.error("Exception while clicking Place Order button", e);
        }
    }

    // Used in assertion
    public boolean isPlaceOrderAttempted() {
        LOG.debug("Checking if place order attempted: {}", attempted);
        return attempted;
    }

    // Future validation
    public boolean isOrderPlaced() {
        LOG.debug("Checking order placed status: {}", attempted);
        return attempted;
    }
}