package com.skillo.pages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.Keys;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.skillo.base.Keyword;
import com.skillo.utils.PropUtil;
import com.skillo.utils.Waits;

public class HomePage {

    private static final Logger LOG = LogManager.getLogger(HomePage.class);

    private final WebDriver driver = Keyword.getDriver();
    private final WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));

    private final PropUtil locator = new PropUtil("./src/main/resources/OR.properties");

    public HomePage() {
        PageFactory.initElements(driver, this);
        LOG.info("HomePage initialized");
    }

    // ================= SEARCH =================

    public void enterTextOnSearchBar(String text) {

        LOG.info("Entering text in search bar: {}", text);

        WebElement searchBox = wait.until(
                ExpectedConditions.elementToBeClickable(
                        By.xpath("//input[contains(@placeholder,'Search')]")
                )
        );

        searchBox.clear();
        searchBox.sendKeys(text);
    }

    public void enterPressOnSearchBar() {

        LOG.info("Pressing ENTER on search bar");

        WebElement searchBox = wait.until(
                ExpectedConditions.elementToBeClickable(
                        By.xpath("//input[contains(@placeholder,'Search')]")
                )
        );

        searchBox.sendKeys(Keys.ENTER);
        Waits.waitForPageToLoad();
    }

    // ================= NAVIGATION =================

    public void navigateToTopwear() {

        LOG.info("Navigating to Topwear section");

        try {
            WebElement popup = wait.until(
                    ExpectedConditions.visibilityOfElementLocated(
                            By.xpath(locator.get("popupClose"))
                    )
            );
            popup.click();
            LOG.info("Popup closed");
        } catch (Exception e) {
            LOG.info("Popup not present");
        }

        WebElement men = wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        By.xpath(locator.get("menTab"))
                )
        );

        new Actions(driver).moveToElement(men).perform();

        Waits.safeClick(By.xpath(locator.get("topwearMenu")));
        Waits.waitForPageToLoad();
    }

    // ================= FILTER =================

    public void applyFilters() {

        LOG.info("Applying filters");

        Waits.waitForPageToLoad();

        Waits.safeClick(By.xpath(locator.get("jacketCategory")));

        WebElement brandSection = wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        By.xpath(locator.get("brandSection"))
                )
        );

        ((JavascriptExecutor) driver)
                .executeScript("arguments[0].scrollIntoView(true);", brandSection);

        Waits.safeClick(By.xpath(locator.get("pumaBrand")));
        Waits.safeClick(By.xpath(locator.get("roadsterBrand")));

        LOG.info("Filters applied successfully");
    }

    // ================= PRODUCT =================

    public void openFirstProduct() {

        LOG.info("Opening first product");

        Waits.waitForPageToLoad();

        WebElement product = wait.until(
                ExpectedConditions.elementToBeClickable(
                        By.xpath(locator.get("firstProduct"))
                )
        );

        product.click();
    }

    // ================= VALIDATIONS =================

    public boolean isTopwearPageDisplayed() {

        String url = driver.getCurrentUrl();
        LOG.info("Current URL: {}", url);

        return url.contains("topwear");
    }

    public boolean isProductsDisplayed() {

        int count = driver.findElements(By.xpath(locator.get("firstProduct"))).size();
        LOG.info("Product count: {}", count);

        return count > 0;
    }

    public boolean isProductPageDisplayed() {

        return driver.getWindowHandles().size() > 1
                || driver.getCurrentUrl().contains("buy");
    }
}