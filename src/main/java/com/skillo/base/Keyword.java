package com.skillo.base;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.safari.SafariDriver;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.skillo.exceptions.InvalidException;

public class Keyword {

    private static final Logger LOG = LogManager.getLogger(Keyword.class);

    // 🔥 SINGLE DRIVER (same as your friend)
    public static RemoteWebDriver driver;

    // Getter
    public static RemoteWebDriver getDriver() {
        return driver;
    }

    // Open Browser
    public static void openBrowser(String browserName) {

        LOG.info("Opening browser: {}", browserName);

        if (browserName.equalsIgnoreCase("chrome")) {

            ChromeOptions options = new ChromeOptions();
            options.addArguments("--disable-notifications");
            options.addArguments("--start-maximized");

            driver = new ChromeDriver(options);
            LOG.info("Chrome browser launched successfully");

        } else if (browserName.equalsIgnoreCase("firefox")) {

            FirefoxOptions options = new FirefoxOptions();
            driver = new FirefoxDriver(options);
            LOG.info("Firefox browser launched successfully");

        } else if (browserName.equalsIgnoreCase("safari")) {

            driver = new SafariDriver();
            LOG.info("Safari browser launched successfully");

        } else {
            LOG.error("Invalid browser provided: {}", browserName);
            throw new InvalidException(browserName);
        }

        // Implicit wait (same as your friend)
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
    }

    // Launch URL
    public static void launchUrl(String url) {

        LOG.info("Launching URL: {}", url);

        driver.get(url);

        LOG.info("Successfully launched URL: {}", url);
    }

    // Close Browser
    public static void closeBrowser() {

        if (driver != null) {
            driver.quit();
            LOG.info("Browser closed successfully");
        }
    }

    // Find Element
    public static WebElement getElement(String locatorType, String locatorValue) {

        WebElement element;

        if (locatorType.equalsIgnoreCase("id")) {
            element = driver.findElement(By.id(locatorValue));

        } else if (locatorType.equalsIgnoreCase("name")) {
            element = driver.findElement(By.name(locatorValue));

        } else if (locatorType.equalsIgnoreCase("xpath")) {
            element = driver.findElement(By.xpath(locatorValue));

        } else if (locatorType.equalsIgnoreCase("cssSelector")) {
            element = driver.findElement(By.cssSelector(locatorValue));

        } else if (locatorType.equalsIgnoreCase("className")) {
            element = driver.findElement(By.className(locatorValue));

        } else {
            throw new InvalidException(locatorType);
        }

        return element;
    }
}