package com.skillo.base;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.safari.SafariDriver;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.skillo.exceptions.InvalidException;

public class Keyword {

    // Logger
    private static final Logger LOG = LogManager.getLogger(Keyword.class);

    // ThreadLocal Driver
    private static ThreadLocal<RemoteWebDriver> threadDriver = new ThreadLocal<>();

    // Getter
    public static RemoteWebDriver getDriver() {
        return threadDriver.get();
    }

    // Setter
    public static void setDriver(RemoteWebDriver driver) {
        threadDriver.set(driver);
        LOG.info("Driver set successfully for thread: {}", Thread.currentThread().getId());
    }

    // Remove driver after test
    public static void unload() {
        LOG.info("Removing driver for thread: {}", Thread.currentThread().getId());
        threadDriver.remove();
    }

    // Open Browser
    public static void openBrowser(String browserName) {

        LOG.info("Opening browser: {}", browserName);

        RemoteWebDriver driver;

        if (browserName.equalsIgnoreCase("Chrome")) {

            ChromeOptions options = new ChromeOptions();
            options.addArguments("--disable-notifications");

            options.addArguments(
                    "--user-agent=Mozilla/5.0 (Windows NT 10.0; Win64; x64) "
                            + "AppleWebKit/537.36 (KHTML, like Gecko) "
                            + "Chrome/120.0.0.0 Safari/537.36");

            //options.addArguments("--start-maximized");

            driver = new ChromeDriver(options);
            LOG.info("Chrome browser launched successfully");

        } else if (browserName.equalsIgnoreCase("Firefox")) {

            driver = new FirefoxDriver();
            LOG.info("Firefox browser launched successfully");

        } else if (browserName.equalsIgnoreCase("Safari")) {

            driver = new SafariDriver();
            LOG.info("Safari browser launched successfully");

        } else {
            LOG.error("Invalid browser provided: {}", browserName);
            throw new InvalidException(browserName);
        }

        setDriver(driver);
        getDriver().manage().window().maximize();
    }

    // Launch URL
    public static void launchUrl(String url) {

        LOG.info("Launching URL: {}", url);

        try {
            getDriver().get(url);
            LOG.info("Successfully launched URL: {}", url);
        } catch (Exception e) {
            LOG.error("Failed to launch URL: {}", url, e);
            throw e;
        }
    }

    // Find Element
    public static WebElement getElement(String locatorType, String locatorValue) {

        LOG.debug("Finding element using {} = {}", locatorType, locatorValue);

        try {

            WebElement element;

            if (locatorType.equalsIgnoreCase("id")) {
                element = getDriver().findElement(By.id(locatorValue));

            } else if (locatorType.equalsIgnoreCase("name")) {
                element = getDriver().findElement(By.name(locatorValue));

            } else if (locatorType.equalsIgnoreCase("xpath")) {
                element = getDriver().findElement(By.xpath(locatorValue));

            } else if (locatorType.equalsIgnoreCase("cssSelector")) {
                element = getDriver().findElement(By.cssSelector(locatorValue));

            } else if (locatorType.equalsIgnoreCase("className")) {
                element = getDriver().findElement(By.className(locatorValue));

            } else {
                LOG.error("Invalid locator type: {}", locatorType);
                throw new InvalidException(locatorType);
            }

            LOG.info("Element found successfully: {} = {}", locatorType, locatorValue);
            return element;

        } catch (Exception e) {
            LOG.error("Failed to find element: {} = {}", locatorType, locatorValue, e);
            throw e;
        }
    }
}