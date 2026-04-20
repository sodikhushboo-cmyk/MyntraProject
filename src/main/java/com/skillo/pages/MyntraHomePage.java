package com.skillo.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.skillo.base.Keyword;
import com.skillo.utils.PropUtil;
import com.skillo.utils.Waits;

public class MyntraHomePage {

    private static final Logger LOG = LogManager.getLogger(MyntraHomePage.class);

    private final WebDriver driver = Keyword.getDriver();
    private final PropUtil locator = new PropUtil("./src/main/resources/OR.properties");

    public MyntraHomePage() {
        PageFactory.initElements(driver, this);
        LOG.info("MyntraHomePage initialized");
    }

    public void navigateToTopwear() {

        LOG.info("Navigating to Topwear section");

        try {
            WebElement popup = driver.findElement(By.xpath(locator.get("popupClose")));
            popup.click();
            LOG.info("Popup closed successfully");
        } catch (Exception e) {
            LOG.debug("No popup displayed");
        }

        try {
            WebElement men = driver.findElement(By.xpath(locator.get("menTab")));
            LOG.debug("Hovering on Men tab");

            new Actions(driver).moveToElement(men).perform();

            Waits.safeClick(By.xpath(locator.get("topwearMenu")));
            LOG.info("Clicked on Topwear menu");

            Waits.waitForPageToLoad();
            LOG.info("Topwear page loaded successfully");

        } catch (Exception e) {
            LOG.error("Failed to navigate to Topwear section", e);
            throw e;
        }
    }

    public void applyFilters() {

        LOG.info("Applying filters on Topwear page");

        try {
            Waits.waitForPageToLoad();

            Waits.safeClick(By.xpath(locator.get("jacketCategory")));
            LOG.info("Jackets category selected");

            WebElement brandSection = driver.findElement(By.xpath(locator.get("brandSection")));

            ((JavascriptExecutor) driver)
                    .executeScript("arguments[0].scrollIntoView(true);", brandSection);

            LOG.debug("Scrolled to brand section");

            Waits.safeClick(By.xpath(locator.get("pumaBrand")));
            LOG.info("Puma filter applied");

            Waits.safeClick(By.xpath(locator.get("roadsterBrand")));
            LOG.info("Roadster filter applied");

        } catch (Exception e) {
            LOG.error("Failed while applying filters", e);
            throw e;
        }
    }

    public void openFirstProduct() {

        LOG.info("Opening first product");

        try {
            Waits.safeClick(By.xpath(locator.get("firstProduct")));
            LOG.info("First product clicked successfully");
        } catch (Exception e) {
            LOG.error("Failed to click first product", e);
            throw e;
        }
    }

    // ================= VALIDATION METHODS =================

    public boolean isTopwearPageDisplayed() {

        try {
            boolean result = driver.getCurrentUrl().contains("topwear");
            LOG.debug("Topwear page displayed: {}", result);
            return result;

        } catch (Exception e) {
            LOG.error("Error while validating Topwear page", e);
            return false;
        }
    }

    public boolean isProductsDisplayed() {

        try {
            boolean result = driver.findElements(By.xpath(locator.get("firstProduct"))).size() > 0;
            LOG.debug("Products displayed: {}", result);
            return result;

        } catch (Exception e) {
            LOG.error("Error while checking products display", e);
            return false;
        }
    }

    public boolean isProductPageDisplayed() {

        try {
            boolean result = driver.getWindowHandles().size() > 1
                    || driver.getCurrentUrl().contains("buy");

            LOG.debug("Product page displayed: {}", result);
            return result;

        } catch (Exception e) {
            LOG.error("Error while validating product page", e);
            return false;
        }
    }
}