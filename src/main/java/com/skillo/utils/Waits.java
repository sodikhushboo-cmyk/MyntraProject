package com.skillo.utils;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.skillo.base.Keyword;

public class Waits {

    private static final Logger LOG = LogManager.getLogger(Waits.class);

    private static final int DEFAULT_TIMEOUT = 20;

    private Waits() {}

    public static WebDriverWait getWait() {
        return new WebDriverWait(Keyword.getDriver(), Duration.ofSeconds(DEFAULT_TIMEOUT));
    }

    // ================= WAIT METHODS =================

    public static WebElement waitForVisible(By locator) {
        LOG.debug("Waiting for visibility of element: {}", locator);

        try {
            return getWait().until(ExpectedConditions.visibilityOfElementLocated(locator));
        } catch (Exception e) {
            LOG.error("Element not visible: {}", locator, e);
            throw e;
        }
    }

    public static WebElement waitForClickable(By locator) {
        LOG.debug("Waiting for element to be clickable: {}", locator);

        try {
            return getWait().until(ExpectedConditions.elementToBeClickable(locator));
        } catch (Exception e) {
            LOG.error("Element not clickable: {}", locator, e);
            throw e;
        }
    }

    public static WebElement waitForClickable(WebElement element) {
        LOG.debug("Waiting for WebElement to be clickable");

        try {
            return getWait().until(ExpectedConditions.elementToBeClickable(element));
        } catch (Exception e) {
            LOG.error("WebElement not clickable", e);
            throw e;
        }
    }

    public static List<WebElement> waitForAllVisible(By locator) {
        LOG.debug("Waiting for all elements to be visible: {}", locator);

        try {
            return getWait().until(ExpectedConditions.visibilityOfAllElementsLocatedBy(locator));
        } catch (Exception e) {
            LOG.error("Elements not visible: {}", locator, e);
            throw e;
        }
    }

    // ================= SCROLL =================

    public static void scrollToElement(WebElement element) {
        LOG.debug("Scrolling to element");

        ((JavascriptExecutor) Keyword.getDriver())
                .executeScript("arguments[0].scrollIntoView({block:'center'});", element);
    }

    // ================= SAFE CLICK =================

    public static void safeClick(By locator) {

        LOG.info("Attempting safe click on locator: {}", locator);

        pause(1);

        try {

            WebElement element = waitForVisible(locator);
            element = waitForClickable(locator);

            scrollToElement(element);
            element.click();

            LOG.info("Click successful: {}", locator);

        } catch (Exception e) {

            LOG.warn("Standard click failed, trying JS click: {}", locator);

            try {
                WebElement element = Keyword.getDriver().findElement(locator);

                ((JavascriptExecutor) Keyword.getDriver())
                        .executeScript("arguments[0].click();", element);

                LOG.info("JS click successful: {}", locator);

            } catch (Exception ex) {
                LOG.error("JS click also failed: {}", locator, ex);
                throw ex;
            }
        }
    }

    public static void safeClick(WebElement element) {

        LOG.info("Attempting safe click on WebElement");

        try {

            getWait().until(ExpectedConditions.visibilityOf(element));
            getWait().until(ExpectedConditions.elementToBeClickable(element));

            scrollToElement(element);
            element.click();

            LOG.info("Click successful on WebElement");

        } catch (Exception e) {

            LOG.warn("Standard click failed, trying JS click on WebElement");

            try {
                ((JavascriptExecutor) Keyword.getDriver())
                        .executeScript("arguments[0].click();", element);

                LOG.info("JS click successful on WebElement");

            } catch (Exception ex) {
                LOG.error("JS click failed on WebElement", ex);
                throw ex;
            }
        }
    }

    // ================= SEND KEYS =================

    public static void safeSendKeys(By locator, String text) {

        LOG.info("Sending keys to element: {}", locator);

        try {
            WebElement element = waitForVisible(locator);
            element.clear();
            element.sendKeys(text);

            LOG.debug("Text entered successfully");

        } catch (Exception e) {
            LOG.error("Failed to send keys: {}", locator, e);
            throw e;
        }
    }

    // ================= PAGE LOAD =================

    public static void waitForPageToLoad() {

        LOG.debug("Waiting for page to load");

        if (Keyword.getDriver() == null) {
            LOG.error("Driver is NULL before waitForPageToLoad()");
            throw new RuntimeException("Driver is NULL before waitForPageToLoad()");
        }

        try {
            new WebDriverWait(Keyword.getDriver(), Duration.ofSeconds(30))
                    .until(driver ->
                            ((JavascriptExecutor) driver)
                                    .executeScript("return document.readyState")
                                    .equals("complete"));

            LOG.debug("Page loaded successfully");

        } catch (Exception e) {
            LOG.error("Page did not load properly", e);
            throw e;
        }
    }

    // ================= PAUSE =================

    public static void pause(int seconds) {

        LOG.debug("Pausing execution for {} seconds", seconds);

        try {
            Thread.sleep(seconds * 1000);
        } catch (InterruptedException e) {
            LOG.error("Pause interrupted", e);
            Thread.currentThread().interrupt();
        }
    }

    public static void waitForVisibility(WebElement element) {

        LOG.debug("Waiting for WebElement visibility");

        try {
            getWait().until(ExpectedConditions.visibilityOf(element));
        } catch (Exception e) {
            LOG.error("WebElement not visible", e);
            throw e;
        }
    }
}