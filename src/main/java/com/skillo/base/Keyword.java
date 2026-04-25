package com.skillo.base;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;

import org.openqa.selenium.*;
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

    // ✅ Single Driver Instance
    public static RemoteWebDriver driver;

    public static RemoteWebDriver getDriver() {
        return driver;
    }

    // ================= BROWSER =================

    public static void openBrowser(String browserName) {

        LOG.info("Opening browser: {}", browserName);

        if (browserName.equalsIgnoreCase("chrome")) {

            ChromeOptions options = new ChromeOptions();
            options.addArguments("--disable-notifications");
            options.addArguments("--start-maximized");

            driver = new ChromeDriver(options);

        } else if (browserName.equalsIgnoreCase("firefox")) {

            FirefoxOptions options = new FirefoxOptions();
            driver = new FirefoxDriver(options);

        } else if (browserName.equalsIgnoreCase("safari")) {

            driver = new SafariDriver();

        } else {
            throw new InvalidException(browserName);
        }

        // ✅ Stable implicit wait
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }

    // ================= URL =================

    public static void launchUrl(String url) {

        LOG.info("Launching URL: {}", url);

        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(60));
        driver.get(url);

        LOG.info("URL launched successfully");
    }

    // ================= CLOSE =================

    public static void closeBrowser() {
        if (driver != null) {
            driver.quit();
            LOG.info("Browser closed successfully");
        }
    }

    // ================= SCREENSHOT =================

    public static byte[] takeScreenshot(String scenarioName, String status) {

        try {
            String folderPath = System.getProperty("user.dir") + "/screenshots/" + status;

            File folder = new File(folderPath);
            if (!folder.exists()) {
                folder.mkdirs();
            }

            File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

            String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
            String fileName = scenarioName.replaceAll(" ", "_") + "_" + timeStamp + ".png";

            File dest = new File(folderPath + "/" + fileName);

            Files.copy(src.toPath(), dest.toPath());

            LOG.info("Screenshot saved at: {}", dest.getAbsolutePath());

            return ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);

        } catch (IOException e) {
            LOG.error("Screenshot failed", e);
            return null;
        }
    }

    // ================= ELEMENT =================

    public static WebElement getElement(String locatorType, String locatorValue) {

        switch (locatorType.toLowerCase()) {

            case "id":
                return driver.findElement(By.id(locatorValue));

            case "name":
                return driver.findElement(By.name(locatorValue));

            case "xpath":
                return driver.findElement(By.xpath(locatorValue));

            case "cssselector":
                return driver.findElement(By.cssSelector(locatorValue));

            case "classname":
                return driver.findElement(By.className(locatorValue));

            default:
                throw new InvalidException(locatorType);
        }
    }
}