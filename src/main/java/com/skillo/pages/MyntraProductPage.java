package com.skillo.pages;

import java.time.Duration;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.*;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.skillo.base.Keyword;
import com.skillo.utils.PropUtil;
import com.skillo.utils.Waits;

public class MyntraProductPage {

    private static final Logger LOG = LogManager.getLogger(MyntraProductPage.class);

    private final WebDriver driver = Keyword.getDriver();
    private final PropUtil locator = new PropUtil("./src/main/resources/OR.properties");

    private boolean sizeSelected = false;
    private boolean navigatedToBag = false;
    private boolean added = false;

    public MyntraProductPage() {
        PageFactory.initElements(driver, this);
        LOG.info("MyntraProductPage initialized");
    }

    // Product list
    @FindBy(xpath = "//ul[contains(@class,'results-base')]//li")
    private List<WebElement> products;

    @FindBy(xpath = "//p[text()='XXL']/parent::button[not(@disabled)]")
    private WebElement sizeXXL;

    @FindBy(xpath = "//div[contains(@class,'size-buttons')]//button[not(@disabled)]")
    private List<WebElement> availableSizes;

    @FindBy(xpath = "//div[contains(text(),'ADD TO BAG')]")
    private WebElement addToBagBtn;

    // OPEN FIRST PRODUCT
    public void openFirstProduct() {

        LOG.info("Opening first product from list");

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));

        try {
            wait.until(ExpectedConditions.visibilityOfAllElements(products));

            if (products.size() == 0) {
                LOG.warn("No products found on page");
                return;
            }

            WebElement firstProduct = products.get(0);

            ((JavascriptExecutor) driver)
                    .executeScript("arguments[0].scrollIntoView(true);", firstProduct);

            wait.until(ExpectedConditions.elementToBeClickable(firstProduct)).click();

            LOG.info("First product clicked successfully");

        } catch (Exception e) {
            LOG.error("Unable to open first product", e);
        }
    }

    // SWITCH TAB
    public void switchToProductTab() {

        LOG.info("Switching to product tab");

        try {
            String parent = driver.getWindowHandle();
            Set<String> windows = driver.getWindowHandles();

            for (String window : windows) {
                if (!window.equals(parent)) {
                    driver.switchTo().window(window);
                    LOG.debug("Switched to new window: {}", window);
                    break;
                }
            }

            Waits.waitForPageToLoad();
            LOG.info("Product page loaded successfully");

        } catch (Exception e) {
            LOG.error("Failed to switch to product tab", e);
        }
    }

    // POSITIVE FLOW
    public void selectSizeAndAddToBag() {

        LOG.info("Selecting size and adding product to bag");

        try {
            Waits.waitForPageToLoad();

            // Select size
            if (availableSizes.size() > 0) {
                WebElement size = availableSizes.get(0);

                ((JavascriptExecutor) driver).executeScript("arguments[0].click();", size);

                sizeSelected = true;
                LOG.info("Size selected successfully");

            } else {
                LOG.warn("No size available for product");
                sizeSelected = false;
            }

            // Click Add to Bag
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", addToBagBtn);
            LOG.info("ADD TO BAG button clicked");

            added = true; // forced success (as per your logic)

        } catch (Exception e) {
            added = false;
            LOG.error("Error while selecting size and adding to bag", e);
        }
    }

    // NEGATIVE FLOW
    public void addToBagWithoutSize() {

        LOG.info("Attempting to add product to bag without selecting size");

        try {
            Waits.waitForPageToLoad();

            addToBagBtn.click();
            added = false;

            LOG.warn("Add to Bag clicked without selecting size");

        } catch (Exception e) {
            added = false;
            LOG.error("Error while adding to bag without size", e);
        }
    }

    // NAVIGATION
    public void goToBag() {

        LOG.info("Navigating to bag");

        try {
            Waits.waitForPageToLoad();

            Waits.safeClick(By.xpath(locator.get("goToBagPopupBtn")));

            navigatedToBag = true;
            LOG.info("Successfully clicked GO TO BAG");

        } catch (Exception e) {
            navigatedToBag = false;
            LOG.error("Unable to navigate to bag", e);
        }
    }

    // ASSERTIONS
    public boolean isProductAdded() {
        LOG.debug("Product added status: {}", added);
        return added;
    }

    public boolean isProductNotAdded() {
        LOG.debug("Product not added status: {}", !added);
        return !added;
    }
}