package com.skillo.base;

import java.time.Duration;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import com.skillo.utils.App;

public class BaseClass {

    @BeforeMethod
    public void setUp() {

        // Open browser
        Keyword.openBrowser(App.browser());

        // Add waits (stability)
        Keyword.getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        Keyword.getDriver().manage().timeouts().pageLoadTimeout(Duration.ofSeconds(60));

        // Launch application
        Keyword.launchUrl(App.url());
    }

    @AfterMethod
    public void tearDown() {

        // Close browser
        Keyword.closeBrowser();
    }
}