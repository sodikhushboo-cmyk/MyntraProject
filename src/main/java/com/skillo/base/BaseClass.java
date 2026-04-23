package com.skillo.base;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import com.skillo.utils.App;

public class BaseClass {

    @BeforeMethod
    public void setUp() {
        Keyword.openBrowser(App.browser());
        Keyword.launchUrl(App.url());
    }

    @AfterMethod
    public void tearDown() {
        Keyword.closeBrowser();   // ✅ updated
    }
}