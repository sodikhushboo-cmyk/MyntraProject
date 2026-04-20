package com.skillo.utils;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import com.skillo.base.Keyword;

public class ScreenshotUtil {

    public static byte[] takeScreenshot() {
        return ((TakesScreenshot) Keyword.getDriver())
                .getScreenshotAs(OutputType.BYTES);
    }
}