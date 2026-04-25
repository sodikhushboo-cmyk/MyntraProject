package com.skillo.base;

import java.time.Duration;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import com.skillo.utils.App;

	public class BaseClass {

    @BeforeMethod
    public void setUp() {

        Keyword.openBrowser(App.browser());

        // ✅ IMPORTANT FIX (stability)
        Keyword.getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        Keyword.getDriver().manage().timeouts().pageLoadTimeout(Duration.ofSeconds(60));

        Keyword.launchUrl(App.url());
    }

    @AfterMethod
    public void tearDown() {

        Keyword.closeBrowser();   // ✅ clean close
    }
}