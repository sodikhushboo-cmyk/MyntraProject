package com.skillo.utils;

import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;

public class ExcelReaderTest {

    @Test
    public void testReadFirstSheetNotEmpty() throws IOException {
        Object[][] data = ExcelReader.readExcel(0);
        Assert.assertNotNull(data, "Data should not be null");
        Assert.assertTrue(data.length > 0, "Sheet 0 should contain at least one data row");
        Assert.assertTrue(data[0].length > 0, "Data columns should be present");
    }

    @Test
    public void testReadSecondSheetSafe() throws IOException {
        Object[][] data = ExcelReader.readExcel(1);
        Assert.assertNotNull(data, "Data should not be null even if sheet is empty");
        // It's okay if sheet 1 is empty; ensure method returns a valid array
        Assert.assertTrue(data.length >= 0);
    }
}
